package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxQuestion;
import com.acc.model.BxToken;
import com.acc.service.IBxQuestionService;
import com.acc.service.IBxTokenService;
import com.acc.util.weChat.WechatUtil;
import com.alibaba.fastjson.JSON;
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
@RequestMapping(value="/question")
public class BxQuestionController {
	private static Logger _logger = LoggerFactory.getLogger(BxQuestionController.class);
	
	@Autowired
	private IBxQuestionService bxQuestionService;

    @Autowired
    private IBxTokenService bxTokenService;

    /**
     * 添加Question信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public void addQA(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxQuestion bxQuestion) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try{
            if(bxQuestion != null){
                BxToken bxToken = bxTokenService.getToken(0);
                if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                    Integer res = WechatUtil.checkMsg(bxToken.getAccessToken(),bxQuestion.getQuestion()+bxQuestion.getPhone());
                    if(res.intValue()==0){
                        bxQuestionService.insert(bxQuestion);
                        result = "操作成功!";
                    }else{
                        status = 2;
                        result = "信息不符合要求!";
                    }
                }else{
                    status = 1;
                    result = "参数有误，请联系管理员!";
                }
            }else{
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "保存失败，请联系管理员!";
            _logger.error("addQA失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
