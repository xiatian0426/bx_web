package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxToken;
import com.acc.model.BxVisitHistory;
import com.acc.service.IBxTokenService;
import com.acc.service.IBxVisitHistoryService;
import com.acc.util.weChat.WechatUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    /**
     * 获取用户信息
     * @param code
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
                result.put("code",0);
                result.put("message","获取用户信息成功!");
                //保存或更新个人信息

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
}
