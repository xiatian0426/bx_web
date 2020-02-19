package com.acc.util.weChat;

import com.acc.resolve.WeChatConfig;
import com.acc.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WechatUtil {
    /**
     * 获取openid和session_key
     * @param code
     * @return
     */
    public static Map<String,String> getOpenIdAndSessionKey(String code) {
        Map<String,String> result = new HashMap<String, String>();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatConfig.APP_ID + "&secret="
                + WeChatConfig.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String reusult = HttpClientUtil.doGet(url,null);
        JSONObject oppidObj = JSONObject.parseObject(reusult);
        String openid = (String) oppidObj.get("openid");
        String session_key = (String) oppidObj.get("session_key");
        result.put("openid",openid);
        result.put("session_key",session_key);
        return result;
    }

    /**
     * 获取token
     * @return
     */
    public static String getDDToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeChatConfig.APP_ID + "&secret="+WeChatConfig.APP_SECRET;
        String reusult = HttpClientUtil.doGet(url,null);
        JSONObject oppidObj = JSONObject.parseObject(reusult);
        String access_token = (String) oppidObj.get("access_token");
        return access_token;
    }

    /**
     * 小程序文本验证
     * @param access_token
     * @param content
     * @return
     */
    public static Integer checkMsg(String access_token,String content) {
        try{
            String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token="+access_token;
            JSONObject data = new JSONObject();
            data.put("content",content);
            String argsJSONStr = JSON.toJSONString(data);
            String reusult = HttpClientUtil.doPostJson(url,argsJSONStr);
            JSONObject oppidObj = JSONObject.parseObject(reusult);
            Integer errcode = (Integer) oppidObj.get("errcode");
            return errcode;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------------调用腾讯内容过滤系统出错------------------");
            return -1;
        }
    }
    /**
     * 小程序图片验证
     * @param access_token
     * @param multipartFile
     * @return
     */
    public static Integer checkImg(String access_token,MultipartFile multipartFile) {
            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + access_token);
                request.addHeader("Content-Type", "application/octet-stream");
                InputStream inputStream = multipartFile.getInputStream();
                byte[] byt = new byte[inputStream.available()];
                inputStream.read(byt);
                request.setEntity(new ByteArrayEntity(byt, ContentType.create("image/jpg")));
                CloseableHttpResponse response = httpclient.execute(request);
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
                JSONObject jso = JSONObject.parseObject(result);
                Object errcode = jso.get("errcode");
                Integer errCode = (Integer) errcode;
                return errCode;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
    }
    /**
     * 小程序视频验证(还未完成)
     * @param access_token
     * @param content
     * @return
     */
    public static Integer checkMedia(String access_token,String content) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(checkMsg("29_DMDnVG0TEc4Qd_Z29UYSbAdzTkSxb7YFcgJ_rfa6btzXhwpZz9V0mel5k82CDxZya5VrWLHtwfw42B5Rcl9Z4BsSm5zxJ5pXlCng8Ur95yk_EJdjvxm5KKGjrArp085t8G9nCLbbph28SFlZHBZeADANHF","dfsfd43李zxcz蒜7782法fgnv级"));
    }
    /**
     * 获取用户信息
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionkey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取openid和access_token(公众号)
     * @param code
     * @return
     */
    public static Map<String,String> getPublicOpenIdAndSessionKey(String code) {
        Map<String,String> result = new HashMap<String, String>();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WeChatConfig.PUBLIC_APP_ID+"&secret="+
                WeChatConfig.PUBLIC_APP_SECRET+"&code="+code+"&grant_type=authorization_code";
        String reusult = HttpClientUtil.doGet(url,null);
        JSONObject oppidObj = JSONObject.parseObject(reusult);
        String openid = (String) oppidObj.get("openid");
        String access_token = (String) oppidObj.get("access_token");
        String refresh_token = (String) oppidObj.get("refresh_token");
        result.put("openid",openid);
        result.put("access_token",access_token);
        result.put("refresh_token",refresh_token);
        return result;
    }

    /**
     * 校验access_token是否有效(公众号)
     * @return
     */
    public static Map<String,Object> checkPublicAccessToken(String access_token,String openid) {
        Map<String,Object> result = new HashMap<String, Object>();
        String url = "https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;
        String reusult = HttpClientUtil.doGet(url,null);
        JSONObject oppidObj = JSONObject.parseObject(reusult);
        Integer errcode = (Integer) oppidObj.get("errcode");
        String errmsg = (String) oppidObj.get("errmsg");
        result.put("errcode",errcode);
        result.put("errmsg",errmsg);
        return result;
    }

    /**
     * 校验access_token是否有效(公众号)
     * @return
     */
    public static Map<String,Object> updatePublicAccessToken(String refresh_token) {
        Map<String,Object> result = new HashMap<String, Object>();
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+WeChatConfig.PUBLIC_APP_ID+"&grant_type=refresh_token&refresh_token="+refresh_token;
        String reusult = HttpClientUtil.doGet(url,null);
        JSONObject oppidObj = JSONObject.parseObject(reusult);
        String access_token = (String) oppidObj.get("access_token");
        Integer expires_in = (Integer) oppidObj.get("expires_in");
        String openid = (String) oppidObj.get("openid");
        result.put("access_token",access_token);
        result.put("expires_in",expires_in);
        result.put("openid",openid);
        return result;
    }

    /**
     * 获取用户信息(公众号)
     * @return
     */
    public static Map<String,Object> getPublicUserInfo(String access_token,String openid){
        Map<String,Object> result = new HashMap<String, Object>();
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        String reusult = HttpClientUtil.doGet(url,null);
        JSONObject oppidObj = JSONObject.parseObject(reusult);
        openid = (String) oppidObj.get("openid");
        String nickname = (String) oppidObj.get("nickname");
        Integer sex = (Integer) oppidObj.get("sex");
        String province = (String) oppidObj.get("province");
        String city = (String) oppidObj.get("city");
        String country = (String) oppidObj.get("country");
        String headimgurl = (String) oppidObj.get("headimgurl");
//        String privilege = (String) oppidObj.get("privilege");
//        String unionid = (String) oppidObj.get("unionid");
        result.put("openid",openid);
        result.put("nickname",nickname);
        result.put("sex",sex);
        result.put("province",province);
        result.put("city",city);
        result.put("country",country);
        result.put("headimgurl",headimgurl);
        return result;
    }
}
