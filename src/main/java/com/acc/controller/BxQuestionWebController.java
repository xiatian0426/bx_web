package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxQuestion;
import com.acc.model.UserInfo;
import com.acc.service.IBxQuestionService;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.vo.Page;
import com.acc.vo.QAQuery;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value="/QuestionWeb")
public class BxQuestionWebController {
	private static Logger _logger = LoggerFactory.getLogger(BxQuestionWebController.class);
	
	@Autowired
	private IBxQuestionService bxQuestionService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * Question信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getQuestionList", method = {RequestMethod.POST,RequestMethod.GET})
	public void getQAList( final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute QAQuery query) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
	    try{
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                query.setSortColumns("c.CREATE_DATE desc");
                Page<BxQuestion> page = null;
                String memberId = String.valueOf(staff.getId());
                if(StringUtils.isNotEmpty(memberId) ){
                    query.setMemberId(Integer.valueOf(memberId));
                    page = bxQuestionService.selectPage(query);
                }
                map.put("page", page);
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
     * 根据id查QA信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getQuestionById", method = RequestMethod.GET)
    public void getQAById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String qaId = request.getParameter("id");
                if(StringUtils.isNotEmpty(qaId) ){
                    BxQuestion bxQuestion = bxQuestionService.getQuestionById(qaId);
                    map.put("bxQuestion",bxQuestion);
                    map.put("code",0);
                    map.put("message","获取成功!");
                }
            }else{
                map.put("code",98);
                map.put("message","未登录，请先登录!");
            }
        } catch (Exception e) {
            map.put("code",-1);
            map.put("message","获取失败!");
            _logger.error("getQAById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
