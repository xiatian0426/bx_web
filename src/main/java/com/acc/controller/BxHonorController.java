package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxHonor;
import com.acc.service.IBxHonorService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/honor")
public class BxHonorController {
	private static Logger _logger = LoggerFactory.getLogger(BxHonorController.class);

	@Autowired
	private IBxHonorService bxHonorService;

	/**
	 * 荣誉信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getHonorList", method = RequestMethod.GET)
	public void getHonorList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
	    try{
            String memberId = request.getParameter("memberId");
            if(StringUtils.isNotEmpty(memberId) ){
                Integer count = bxHonorService.getHonorCount(memberId);
                map.put("count",count);
                List<BxHonor> bxHonorList = bxHonorService.getHonorList(memberId);
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                List<String> imageUrl = new ArrayList<String>();
                String url;
                for (BxHonor bxHonor:bxHonorList){
                    url = basePath+Constants.honorImgPath+bxHonor.getMemberId()+"/"+bxHonor.getImageUrl();
                    bxHonor.setImageUrl(url);
                    imageUrl.add(url);
                }
                map.put("list",bxHonorList);
                map.put("imageUrl",imageUrl);
            }
        } catch (Exception e) {
            _logger.error("bxHonorService失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}
}
