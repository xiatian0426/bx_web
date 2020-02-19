package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxPublicUserInfo;
import com.acc.model.BxToken;
import com.acc.model.BxVisitHistory;
import com.acc.service.IBxPublicUserInfoService;
import com.acc.service.IBxTokenService;
import com.acc.service.IBxVisitHistoryService;
import com.acc.util.weChat.WechatUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 公众号接口
 */
@Controller
@RequestMapping(value="/weChatPublic")
public class BxWeChatPublicController {

	private static Logger _logger = LoggerFactory.getLogger(BxWeChatPublicController.class);

    @Autowired
    private IBxPublicUserInfoService bxPublicUserInfoService;

    /**
     * 获取用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String code = request.getParameter("code");
            System.out.println("code:" + code);
            //通过code换取网页授权access_token
            Map<String,String> resultMap = WechatUtil.getPublicOpenIdAndSessionKey(code);
            String openid = resultMap.get("openid");
            String access_token = resultMap.get("access_token");
            String refresh_token = resultMap.get("refresh_token");
            //校验access_token是否有效
            Map<String,Object> checkResult = WechatUtil.checkPublicAccessToken(access_token,openid);
            if(checkResult.get("errcode")==null || (Integer)checkResult.get("errcode")!=0){
                //刷新access_token
                Map<String,Object> rr = WechatUtil.updatePublicAccessToken(refresh_token);
                openid = (String)rr.get("openid");
                access_token = (String)rr.get("access_token");
            }
            //拉取用户信息
            Map<String,Object> map = WechatUtil.getPublicUserInfo(access_token,openid);
            result.put("result",map);
            if(map!=null && map.size()>0){
                //保存或更新个人信息
                BxPublicUserInfo bxPublicUserInfo = new BxPublicUserInfo();
                bxPublicUserInfo.setOpenid((String)map.get("openid"));
                bxPublicUserInfo.setCity((String)map.get("city"));
                bxPublicUserInfo.setCountry((String)map.get("country"));
                bxPublicUserInfo.setHeadimgurl((String)map.get("headimgurl"));
                bxPublicUserInfo.setNickname((String)map.get("nickname"));
                bxPublicUserInfo.setPrivilege(JSON.toJSONString((JSONArray)map.get("privilege")));
                bxPublicUserInfo.setProvince((String)map.get("province"));
                bxPublicUserInfo.setSex((Integer)map.get("sex"));
                bxPublicUserInfo.setUnionid((String)map.get("unionid"));
                BxPublicUserInfo oldBxPublicUserInfo = bxPublicUserInfoService.getUserInfoByOpenId(bxPublicUserInfo.getOpenid());
                if(oldBxPublicUserInfo==null){
                    //保存
                    bxPublicUserInfoService.savePublicUserInfo(bxPublicUserInfo);
                }else{
                    //更新
                    bxPublicUserInfoService.updatePublicUserInfo(bxPublicUserInfo);
                }
                result.put("code",0);
                result.put("message","获取用户信息成功!");
            }else{
                result.put("code",-1);
                result.put("message","获取数据失败!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","获取数据失败!");
            _logger.error("getOpenId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 根据openid获取用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getUserInfoByOpenId", method = RequestMethod.GET)
    @ResponseBody
    public void getUserInfoByOpenId(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String openid = request.getParameter("openid");
            System.out.println("openid:" + openid);
            if(StringUtils.isNotEmpty(openid) ){
                BxPublicUserInfo bxPublicUserInfo = bxPublicUserInfoService.getUserInfoByOpenId(openid);
                result.put("result",bxPublicUserInfo);
                result.put("code",0);
                result.put("message","获取用户信息成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","获取数据失败!");
            _logger.error("getOpenId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}
