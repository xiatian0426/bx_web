package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxCompany;
import com.acc.model.BxToken;
import com.acc.model.UserInfo;
import com.acc.service.IBxCompanyService;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.Page;
import com.acc.vo.CompanyQuery;
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
@RequestMapping(value="/companyWeb")
public class BxCompanyWebController {
	private static Logger _logger = LoggerFactory.getLogger(BxCompanyWebController.class);

	@Autowired
	private IBxCompanyService bxCompanyService;

    @Autowired
    private IBxTokenService bxTokenService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 招聘信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCompanyList", method = {RequestMethod.POST,RequestMethod.GET})
	public void getCompanyList(final HttpServletRequest request, final HttpServletResponse response,@ModelAttribute CompanyQuery query) throws IOException {
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
                query.setSortColumns("c.COMPANY_ORDER,c.CREATE_DATE");
                Page<BxCompany> page = null;
                String memberId = String.valueOf(staff.getId());
                if(StringUtils.isNotEmpty(memberId) ){
                    query.setMemberId(Integer.valueOf(memberId));
                    page = bxCompanyService.selectPage(query);
                }
                String basePath = Constants.webPath;
                for (BxCompany bxCompany:page.getResult()){
                    bxCompany.setImageUrl(basePath+ Constants.companyImgPath+bxCompany.getMemberId()+"/"+bxCompany.getImageUrl());
                }
                map.put("page",page);
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
     * 上传招聘信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public void addCompany(final HttpServletRequest request, final HttpServletResponse response,
                           @ModelAttribute BxCompany bxCompany, @RequestParam(value="file",required=false) MultipartFile[] file) throws IOException {
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
                if (bxCompany != null) {
//                    if(file!=null && file.length>0){
                        if(bxCompany.getMemberId()==0){
                            bxCompany.setMemberId(staff.getId());
                        }
                        bxCompany.setCreaterId(staff.getId());
                        //敏感信息验证
                        BxToken bxToken = bxTokenService.getToken(1);
                        if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                            int checkImgResult = 0;
                            if(file!=null && file.length>0){
                                checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                            }
                            if(checkImgResult == 0){
                                if(file==null){
                                    bxCompany.setImageUrl(null);
                                    bxCompanyService.updateById(bxCompany);
                                }else{
                                    //删除图片
                                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                    String fileSavePath=path + Constants.companyImgPath + bxCompany.getMemberId() + "/";
                                    String imgUrl = null;
                                    if(bxCompany.getImageUrl()!=null && !"".equals(bxCompany.getImageUrl())){
                                        imgUrl = bxCompany.getImageUrl().split("/")[bxCompany.getImageUrl().split("/").length-1];
                                    }
                                    new File(fileSavePath+imgUrl).delete();
                                    //保存新的图片
                                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                    if(re==0){
                                        List<String> list = (List<String>)mapImg.get("list");
                                        if(list!=null && list.size()>0){
                                            bxCompany.setImageUrl(list.get(0));
                                            if(bxCompany.getId()!=0){
                                                bxCompanyService.updateById(bxCompany);
                                            }else{
                                                bxCompanyService.insert(bxCompany);
                                            }
                                        }
                                        message = "添加成功!";
                                    }else if(re==-1){
                                        status = 3;
                                        message = "没有文件";
                                    }else{
                                        status = 4;
                                        message = "上传文件有问题";
                                    }
                                }
                            }else{
                                status = 99;
                                message = "信息校验错误，请联系管理员!";
                            }
                        }else{
                            status = 2;
                            message = "信息校验错误，请联系管理员!";
                        }
//                    }else{
//                        status = -1;
//                        message = "没有文件";
//                    }
                }else{
                    status = -1;
                    message = "参数有误，请联系管理员!";
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
     * 删除招聘
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteCompanyById", method = RequestMethod.POST)
    public void deleteCompanyById (final HttpServletRequest request,
                                                  final HttpServletResponse response) throws IOException {
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
                String id = request.getParameter("id");
                if(StringUtils.isNotEmpty(id)){
                    BxCompany bxCompany = bxCompanyService.getCompanyById(id);
                    if(bxCompany!=null){
                        bxCompanyService.deleteById(id);
                        Integer memberId = bxCompany.getMemberId();
                        String imageUrl = bxCompany.getImageUrl();
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.companyImgPath + memberId + "/";
                        String imgUrl = null;
                        if(imageUrl!=null && !"".equals(imageUrl)){
                            imgUrl = imageUrl.split("/")[imageUrl.split("/").length-1];
                        }
                        new File(fileSavePath+imgUrl).delete();
                    }else{
                        status = 2;
                        message = "参数不正确!";
                    }
                }else{
                    status = 1;
                    message = "参数不正确!";
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
}
