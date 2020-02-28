package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.frames.web.Md5PwdEncoder;
import com.acc.frames.web.WebCookies;
import com.acc.model.AccUserLoginHis;
import com.acc.model.UserInfo;
import com.acc.service.IAccUserLoginHisService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.MakePicture;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序后台登录 退出（废弃）
 */
@Controller
@RequestMapping(value="/accountWeb")
public class AccAccountWebController {
	private static Logger _logger = LoggerFactory.getLogger(AccAccountWebController.class);
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IAccUserLoginHisService accUserLoginHisService;
	
	/**
	 * 登录按钮
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
		try {
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("pwd");
            UserInfo userInfo = userInfoService.getByUserName(userName);
			if (userInfo == null) {
                status = -1;
                message = "该用户不存在";
			} else if ("0".equals(userInfo.getStatus())){
                status = -1;
                message =  "该用户被删除";
			} else {
				String password = userInfo.getUserPassword();
				if (!Md5PwdEncoder.getMD5Str(passwd+"Diegoxhr").equals(password)){
                    status = -1;
                    message =  "密码错误";
				}
			}
			//存入session
			HttpSession session = request.getSession();
			session.setAttribute(Constants.LOGINUSER, userInfo);
			//用户名存入cookies
			WebCookies.addCookie(response, Constants.COOKIESUSERNAME, userInfo.getUserName(), Constants.COOKIESTIMES,"/");
			request.getSession().setAttribute(Constants.LOGINUSER, userInfo);
			//保存登陆日志
			AccUserLoginHis accUserLoginHis = new AccUserLoginHis();
			accUserLoginHis.setUserName(userName);
			accUserLoginHis.setCreateTime(new Date());
			accUserLoginHis.setUrl(request.getRequestURL().toString());
            accUserLoginHis.setType(1);//小程序后台登录
			accUserLoginHisService.insert(accUserLoginHis);
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
	 * 生产验证码图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/verifycode")
	public void verifycode(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			MakePicture mp = new MakePicture();
			String str = mp.drawPicture(60, 20, response.getOutputStream());
			System.out.println(str);
			request.getSession().setAttribute(Constants.VALIDATESESSION, str);
			response.getOutputStream().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 退出系统
	 * @param mav
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public void logout(final ModelAndView mav, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
		try{
            request.getSession().setAttribute(Constants.LOGINUSER, null);
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
}
