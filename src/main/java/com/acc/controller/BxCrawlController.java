package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxCrawl;
import com.acc.model.BxHonor;
import com.acc.service.IBxCrawlService;
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
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/crawl")
public class BxCrawlController {
	private static Logger _logger = LoggerFactory.getLogger(BxCrawlController.class);

	@Autowired
	private IBxCrawlService bxCrawlService;

	/**
	 * 爬虫信息凡客
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCrawlList", method = RequestMethod.GET)
	public void getCrawlList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
	    try{
            String source = request.getParameter("source");
//            String memberId = request.getParameter("memberId");
//            if(StringUtils.isNotEmpty(memberId) ){
                List<BxCrawl> bxCrawlList = bxCrawlService.getCrawlList(source);
//                String basePath = Constants.imgVideoPath;
//                String url;
//                for (BxCrawl bxCrawl:bxCrawlList){
//                    url = basePath+Constants.honorImgPath+bxCrawl.getMemberId()+"/"+bxCrawl.getImg();
//                    bxCrawl.setImg(url);
//                }
                map.put("list",bxCrawlList);
//            }
        } catch (Exception e) {
            _logger.error("getCrawlList失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}
    public  void makeImg(String imgUrl,String fileURL) {
        try {
            // 创建流
            BufferedInputStream in = new BufferedInputStream(new URL(imgUrl)
                    .openStream());
            // 生成图片名
            int index = imgUrl.lastIndexOf("/");
            String sName = imgUrl.substring(index+1, imgUrl.length());
            System.out.println(sName);
            // 存放地址
            File img = new File(fileURL+sName);
            // 生成图片
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(img));
            byte[] buf = new byte[2048];
            int length = in.read(buf);
            while (length != -1) {
                out.write(buf, 0, length);
                length = in.read(buf);
            }
            in.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
