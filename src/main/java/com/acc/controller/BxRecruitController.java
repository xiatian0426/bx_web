package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxMember;
import com.acc.model.BxRecruit;
import com.acc.service.IBxRecruitService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value="/recruit")
public class BxRecruitController {
	private static Logger _logger = LoggerFactory.getLogger(BxRecruitController.class);

	@Autowired
	private IBxRecruitService bxRecruitService;

	/**
	 * 招聘信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getRecruitList", method = RequestMethod.GET)
	public void getRecruitList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
	    try{
            String memberId = request.getParameter("memberId");
            if(StringUtils.isNotEmpty(memberId) ){
                Integer count = bxRecruitService.getRecruitCount(memberId);
                map.put("count",count);
                List<BxRecruit> bxRecruitList = bxRecruitService.getRecruitList(memberId);
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                for (BxRecruit bxRecruit:bxRecruitList){
                    bxRecruit.setImageUrl(basePath+Constants.recruitImgPath+bxRecruit.getMemberId()+"/"+bxRecruit.getImageUrl());
                }
                map.put("list",bxRecruitList);
            }
        } catch (Exception e) {
            _logger.error("bxRecruitService失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}
    /**
     * 管理端--删除招聘信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteRecruit", method = RequestMethod.POST)
    public void deleteRecruit(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String recruitId = request.getParameter("recruitId");
            if(StringUtils.isNotEmpty(recruitId)){
                //删除图片
                BxRecruit bxRecruit = bxRecruitService.getRecruitById(recruitId);
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.recruitImgPath + bxRecruit.getMemberId() + "/";
                new File(fileSavePath+bxRecruit.getImageUrl()).delete();
                //删除数据
                bxRecruitService.deleteById(recruitId);
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
     * 管理端--上传招聘信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addRecruit", method = RequestMethod.POST)
    public void addRecruit(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxRecruit bxRecruit,@RequestParam(value="file",required=false) MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try{
            if (bxRecruit != null) {
                if(file!=null && file.length>0){
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.recruitImgPath + bxRecruit.getMemberId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re==0){
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxRecruit.setImageUrl(list.get(0));
                                bxRecruitService.insert(bxRecruit);
                            }
                        }
                        result = "添加成功!";
                    }else if(re==-1){
                        result = "没有文件";
                    }else{
                        result = "上传文件有问题";
                    }
                }else{
                    result = "没有文件";
                }
            }else{
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加失败，请联系管理员!";
            _logger.error("addRecruit失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
