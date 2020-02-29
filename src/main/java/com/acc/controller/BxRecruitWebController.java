package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxRecruit;
import com.acc.model.BxToken;
import com.acc.model.UserInfo;
import com.acc.service.IBxRecruitService;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.Page;
import com.acc.vo.RecruitQuery;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping(value="/recruitWeb")
public class BxRecruitWebController {
	private static Logger _logger = LoggerFactory.getLogger(BxRecruitWebController.class);

	@Autowired
	private IBxRecruitService bxRecruitService;

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
	@RequestMapping(value = "/getRecruitList", method = {RequestMethod.POST,RequestMethod.GET})
	public void getRecruitList(final HttpServletRequest request, final HttpServletResponse response,@ModelAttribute RecruitQuery query) throws IOException {
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
                query.setSortColumns("c.RECRUIT_ORDER");
                Page<BxRecruit> page = null;
                String memberId = String.valueOf(staff.getId());
                if(StringUtils.isNotEmpty(memberId) ){
                    query.setMemberId(Integer.valueOf(memberId));
                    page = bxRecruitService.selectPage(query);
                }
                String basePath = Constants.webPath;
                for (BxRecruit bxRecruit:page.getResult()){
                    bxRecruit.setImageUrl(basePath+ Constants.recruitImgPath+bxRecruit.getMemberId()+"/"+bxRecruit.getImageUrl());
                }
                map.put("page",page);
            }else{
                status = -1;
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
    @RequestMapping(value = "/addRecruit", method = RequestMethod.POST)
    public void addRecruit(final HttpServletRequest request, final HttpServletResponse response,
                           @ModelAttribute BxRecruit bxRecruit, @RequestParam(value="file") MultipartFile[] file) throws IOException {
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
                if (bxRecruit != null) {
                    if(file!=null && file.length>0){
                        if(bxRecruit.getMemberId()==0){
                            bxRecruit.setMemberId(staff.getId());
                        }
                        bxRecruit.setCreaterId(staff.getId());
                        //敏感信息验证
                        BxToken bxToken = bxTokenService.getToken(1);
                        if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                            int checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                            if(checkImgResult == 0){
                                if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                                    bxRecruit.setImageUrl(null);
                                    bxRecruitService.updateById(bxRecruit);
                                }else{
                                    //删除图片
                                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                    String fileSavePath=path + Constants.recruitImgPath + bxRecruit.getMemberId() + "/";
                                    String imgUrl = null;
                                    if(bxRecruit.getImageUrl()!=null && !"".equals(bxRecruit.getImageUrl())){
                                        imgUrl = bxRecruit.getImageUrl().split("/")[bxRecruit.getImageUrl().split("/").length-1];
                                    }
                                    new File(fileSavePath+imgUrl).delete();
                                    //保存新的图片
                                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                    if(re==0){
                                        List<String> list = (List<String>)mapImg.get("list");
                                        if(list!=null && list.size()>0){
                                            bxRecruit.setImageUrl(list.get(0));
                                            if(bxRecruit.getId()!=0){
                                                bxRecruitService.updateById(bxRecruit);
                                            }else{
                                                bxRecruitService.insert(bxRecruit);
                                            }
                                        }
                                        message = "添加成功!";
                                    }else if(re==-1){
                                        status = -1;
                                        message = "没有文件";
                                    }else{
                                        status = -1;
                                        message = "上传文件有问题";
                                    }
                                }
                            }else{
                                status = -1;
                                message = "信息校验错误，请联系管理员!";
                            }
                        }else{
                            status = -1;
                            message = "信息校验错误，请联系管理员!";
                        }
                    }else{
                        status = -1;
                        message = "没有文件";
                    }
                }else{
                    status = -1;
                    message = "参数有误，请联系管理员!";
                }
            }else{
                status = -1;
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
    @RequestMapping(value = "/deleteRecruitById", method = RequestMethod.POST)
    public void deleteRecruitById (final HttpServletRequest request,
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
                    BxRecruit bxRecruit = bxRecruitService.getRecruitById(id);
                    if(bxRecruit!=null){
                        bxRecruitService.deleteById(id);
                        Integer memberId = bxRecruit.getMemberId();
                        String imageUrl = bxRecruit.getImageUrl();
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.recruitImgPath + memberId + "/";
                        String imgUrl = null;
                        if(imageUrl!=null && !"".equals(imageUrl)){
                            imgUrl = imageUrl.split("/")[imageUrl.split("/").length-1];
                        }
                        new File(fileSavePath+imgUrl).delete();
                    }else{
                        status = 1;
                        message = "参数不正确!";
                    }
                }else{
                    status = 1;
                    message = "参数不正确!";
                }
            }else{
                status = -1;
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
