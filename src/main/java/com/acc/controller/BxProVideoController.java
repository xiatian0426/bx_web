package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxProVideo;
import com.acc.service.IBxProVideoService;
import com.acc.util.Constants;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value="/proVideo")
public class BxProVideoController {
	private static Logger _logger = LoggerFactory.getLogger(BxProVideoController.class);

	@Autowired
	private IBxProVideoService bxProVideoService;

	/**
	 * 用户视频信息
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "/getProVideoList", method = RequestMethod.GET)
	public void getProVideoList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
	    try{
            String memberId = request.getParameter("memberId");
            if(StringUtils.isNotEmpty(memberId) ){
                Integer count = bxProVideoService.getProVideoCount(memberId);
                map.put("count",count);
                List<BxProVideo> bxProVideoList = bxProVideoService.getProVideoList(memberId);
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                for (BxProVideo bxProVideo:bxProVideoList){
                    bxProVideo.setVideoUrl(basePath+Constants.proVideoVideoPath+bxProVideo.getMemberId()+"/"+bxProVideo.getVideoUrl());
                }
                map.put("list",bxProVideoList);
            }
        } catch (Exception e) {
            _logger.error("BxProVideoService失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}*/
}
