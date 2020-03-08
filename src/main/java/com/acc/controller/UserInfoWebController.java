package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.exception.SelectException;
import com.acc.frames.web.Md5PwdEncoder;
import com.acc.model.BxToken;
import com.acc.model.UserInfo;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.util.CalendarUtil;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/userWeb")
public class UserInfoWebController {
	private static Logger _logger = LoggerFactory.getLogger(UserInfoWebController.class);

	@Autowired
	private IUserInfoService userInfoService;

    @Autowired
    private IBxTokenService bxTokenService;

	/**
	 * 跳转修改用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/goEdit", method = RequestMethod.GET)
	public void goEdit (final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
		try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String userId = request.getParameter("userId");
                if(userId==null || userId.equals("")){
                    userId = String.valueOf(staff.getId());
                }
                UserInfo userInfo = userInfoService.getById(userId);
                if(userInfo!=null){
                    String basePath = Constants.webPath;
                    String fileSavePath=basePath + Constants.memberImgPath + userInfo.getId() + "/";
                    userInfo.setMemberImg(fileSavePath+userInfo.getMemberImg());
                    if(userInfo.getWxaCode()!=null && !userInfo.getWxaCode().equals("")){
                        fileSavePath=basePath + Constants.memberImgWxaCodePath;
                        userInfo.setWxaCode(fileSavePath+userInfo.getWxaCode());
                    }
                }
                map.put("userInfo", userInfo);
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
		} catch (Exception e) {
            status = -1;
            message = "操作失败，请联系管理员!";
            _logger.error("操作失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
		}
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
	}

	/**
	 * 修改用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public void editUser (final HttpServletRequest request, final HttpServletResponse response, UserInfo user,@RequestParam(value="file",required=false) MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
	    String userId = request.getParameter("id");
		try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                //敏感信息验证
                BxToken bxToken = bxTokenService.getToken(1);
                if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                    String content = user.getName()+user.getUserRealname()+user.getPost_name()
                            +user.getPhone()+user.getCompany_addr()+user.getCompany_name()
                            +user.getLatitude()+user.getLongitude()+user.getYears()+user.getSignature()
                            +user.getWechat()+user.getPage_style()+user.getIntroduce();
                    int checkMsgResult = WechatUtil.checkMsg(bxToken.getAccessToken(),content);
                    int checkImgResult = 0;
                    if(file!=null && file.length>0){
                        checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                    }
                    if(checkMsgResult== 0 && checkImgResult == 0){
//                        if(file!=null && file.length>0){
                            if(file==null){
                                user.setMemberImg(null);
                            }else{
                                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                String fileSavePath=path + Constants.memberImgPath + user.getId() + "/";
                                String imgUrl = null;
                                if(user.getMemberImg()!=null && !"".equals(user.getMemberImg())){
                                    imgUrl = user.getMemberImg().split("/")[user.getMemberImg().split("/").length-1];
                                }
                                new File(fileSavePath+imgUrl).delete();
                                //生成新图片
                                Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                                int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                if(re==0){
                                    List<String> list = (List<String>)mapImg.get("list");
                                    if(list!=null && list.size()>0){
                                        user.setMemberImg(list.get(0));
                                    }
                                }
                            }
//                        }
                        user.setModifierId(staff.getId()+"");
                        user.setModifyDate(CalendarUtil.getCurrentDate());
                        user.setRoleId(null);
                        UserInfo userInfo = userInfoService.getById(userId);
                        //密码用MD5加密
                        if (StringUtils.isNotEmpty(user.getUserPassword())) {
                            user.setUserPassword(Md5PwdEncoder.getMD5Str(user.getUserPassword()+"Diegoxhr"));
                        } else {
                            user.setUserPassword(userInfo.getUserPassword());
                        }
                        user.setModifierId(String.valueOf(staff.getId()));
                        user.setStatus(userInfo.getStatus());
                        userInfoService.update(user);
                    }else{
                        status = 99;
                        message = "信息校验错误，请联系管理员!";
                    }
                }else{
                    status = 2;
                    message = "信息校验错误，请联系管理员!";
                }
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
		} catch (Exception e) {
            status = -1;
            message = "操作失败，请联系管理员!";
            _logger.error("操作失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
		}
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
	}

	/**
	 * 验证用户名称 用户名是否存在  存在 false 不存在 true
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/validateUserName")
	public boolean validateUserName (final HttpServletRequest request) {
		String newUserName = request.getParameter("newUserName");
		try {
            UserInfo userInfo = userInfoService.getByUserName(newUserName.trim());
			//当前登录名称不存在
			if (userInfo == null) return false;
		} catch (SelectException e) {
			return true;
		}
		return true;
	}

	/**
	 * 禁用或者启用用户
	 * @param request
	 * @return
	 * @author TANGCY
	 * @since 2016年10月25日
	 */
	@ResponseBody
	@RequestMapping(value="/userUseOrNot")
	public boolean userUseOrNot (final HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		try {
			userInfoService.updateUserStatus(userId, status);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    /**
     * 获取openID
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getOpenId", method = RequestMethod.POST)
    @ResponseBody
    public void getOpenId(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String code = request.getParameter("code");
            System.out.println("code:" + code);
            String openid = WechatUtil.getOpenIdAndSessionKeyWeb(code).get("openid");
            if(openid!=null && !openid.equals("")){
                result.put("openid",openid);
                UserInfo userInfo = userInfoService.getByOpenIdWeb(openid);
                if(userInfo!=null){
                    if(userInfo.getStatus()!=null && "1".equals(userInfo.getStatus())){
                        //关联用户正常
                        result.put("isRelate",1);//已关联 正常
                        result.put("userInfo",userInfo);
                        result.put("code",0);
                        result.put("message","成功");
                    }else{
                        //关联用户已被删除
                        result.put("isRelate",-1);//已关联 异常
                        result.put("code",-1);
                        result.put("message","关联用户异常，请联系管理员!");
                    }
                }else{
                    result.put("isRelate",0);//未关联
                    result.put("code",0);
                    result.put("message","成功");
                }
            }else{
                result.put("code",-1);
                result.put("message","操作失败!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","获取openid失败!");
            _logger.error("getOpenId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
    /**
     * 关联用户名
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/relateOpenIdWeb", method = RequestMethod.POST)
    @ResponseBody
    public void relateOpenIdWeb(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String userName = request.getParameter("userName");
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo userInfo = userInfoService.getByUserName(userName);
            if(userInfo!=null && userName!=null && openIdWeb!=null && !userName.equals("") &&!openIdWeb.equals("")){
                System.out.println("userName==="+userName);
                System.out.println("openIdWeb==="+openIdWeb);
                if(userInfo.getOpenIdWeb()!=null && !"".equals(userInfo.getOpenIdWeb())){
                    result.put("code",3);
                    result.put("message","该用户已被关联，请联系管理员!");
                }else{
                    userInfo.setOpenIdWeb(openIdWeb);
                    if(userInfo.getStatus()!=null && "1".equals(userInfo.getStatus())){
                        userInfoService.updateOpenIdWeb(userInfo);
                        result.put("code",0);
                        result.put("message","操作成功!");
                    }else{
                        result.put("code",2);
                        result.put("message","关联用户异常，请联系管理员!");
                    }
                }
            }else{
                result.put("code",1);
                result.put("message","认证失败，该用户不存在!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","操作失败!");
            _logger.error("操作失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

}
