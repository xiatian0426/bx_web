package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxQA;
import com.acc.service.IBxQAService;
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
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/QA")
public class BxQAController {
	private static Logger _logger = LoggerFactory.getLogger(BxQAController.class);
	
	@Autowired
	private IBxQAService bxQAService;

	/**
	 * QA信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getQAList", method = RequestMethod.GET)
	public void getQAList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
	    try{
            String memberId = request.getParameter("memberId");
            if(StringUtils.isNotEmpty(memberId) ){
                Integer count = bxQAService.getQACount(memberId);
                map.put("count",count);
                List<BxQA> bxQAList = bxQAService.getQAList(memberId);
                map.put("list",bxQAList);
            }
        } catch (Exception e) {
            _logger.error("getMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}

    /**
     * 管理端--删除QA信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public void deleteById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String qaId = request.getParameter("id");
            if(StringUtils.isNotEmpty(qaId)){
                bxQAService.deleteById(qaId);
                result.put("code",0);
                result.put("message","删除成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","删除失败!");
            _logger.error("deleteRecruit失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 管理端--根据id查QA信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getQAById", method = RequestMethod.GET)
    public void getQAById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String qaId = request.getParameter("id");
            if(StringUtils.isNotEmpty(qaId) ){
                BxQA bxQA = bxQAService.getQAById(qaId);
                map.put("bxQA",bxQA);
            }
        } catch (Exception e) {
            _logger.error("getQAById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 理端--根据id查QA信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public void updateById(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxQA bxQA) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            if(bxQA != null){
                bxQAService.updateById(bxQA);
                result.put("code",0);
                result.put("message","更新成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","更新失败!");
            _logger.error("updateById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 理端--添加QA信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addQA", method = RequestMethod.POST)
    public void addQA(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxQA bxQA) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            if(bxQA != null){
                bxQAService.insert(bxQA);
                result.put("code",0);
                result.put("message","保存成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","保存失败!");
            _logger.error("addQA失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}
