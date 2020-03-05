package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxCompany;
import com.acc.model.BxCompany;
import com.acc.service.IBxCompanyService;
import com.acc.service.IBxCompanyService;
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
@RequestMapping(value="/company")
public class BxCompanyController {
	private static Logger _logger = LoggerFactory.getLogger(BxCompanyController.class);

	@Autowired
	private IBxCompanyService bxCompanyService;

	/**
	 * 企业风采信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCompanyList", method = RequestMethod.GET)
	public void getCompanyList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
	    try{
            String memberId = request.getParameter("memberId");
            if(StringUtils.isNotEmpty(memberId) ){
                List<BxCompany> bxCompanyList = bxCompanyService.getCompanyList(memberId);
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                for (BxCompany bxCompany:bxCompanyList){
                    bxCompany.setImageUrl(basePath+Constants.companyImgPath+bxCompany.getMemberId()+"/"+bxCompany.getImageUrl());
                }
                map.put("list",bxCompanyList);
            }
        } catch (Exception e) {
            _logger.error("bxCompanyService失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}
    /**
     * 管理端--删除企业风采信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    public void deleteCompany(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String CompanyId = request.getParameter("CompanyId");
            if(StringUtils.isNotEmpty(CompanyId)){
                //删除图片
                BxCompany bxCompany = bxCompanyService.getCompanyById(CompanyId);
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.companyImgPath + bxCompany.getMemberId() + "/";
                new File(fileSavePath+bxCompany.getImageUrl()).delete();
                //删除数据
                bxCompanyService.deleteById(CompanyId);
                result.put("code",0);
                result.put("message","删除成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","删除失败!");
            _logger.error("deleteCompany失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
    /**
     * 管理端--上传企业风采信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public void addCompany(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxCompany bxCompany,@RequestParam(value="file",required=false) MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try{
            if (bxCompany != null) {
                if(file!=null && file.length>0){
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.companyImgPath + bxCompany.getMemberId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re==0){
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxCompany.setImageUrl(list.get(0));
                                bxCompanyService.insert(bxCompany);
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
            _logger.error("addCompany失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
