package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxHonor;
import com.acc.model.BxToken;
import com.acc.model.UserInfo;
import com.acc.service.IBxHonorService;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.HonorQuery;
import com.acc.vo.Page;
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
@RequestMapping(value="/honorWeb")
public class BxHonorWebController {
	private static Logger _logger = LoggerFactory.getLogger(BxHonorWebController.class);

	@Autowired
	private IBxHonorService bxHonorService;

    @Autowired
    private IBxTokenService bxTokenService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 荣誉信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHonorList", method = {RequestMethod.GET,RequestMethod.POST})
	public void getHonorList(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute HonorQuery query) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
	    try{
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            Page<BxHonor> page = null;
            if(staff!=null){
                if(query!=null){
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    query.setSortColumns("c.HONOR_ORDER");
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxHonorService.selectPage(query);
                    }
                    String url;
                    for (BxHonor bxHonor:page.getResult()){
                        url = basePath+ Constants.honorImgPath+bxHonor.getMemberId()+"/"+bxHonor.getImageUrl();
                        bxHonor.setImageUrl(url);
                    }
                }
                map.put("page", page);
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
     * 更新个人荣誉榜图片
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public void updateById(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxHonor bxHonor, @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message;
        int status = 0;
        try {
            if (bxHonor != null) {
                if(file!=null && file.length>0){
                    String openIdWeb = request.getParameter("openIdWeb");
                    UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
                    if(staff!=null){
                        bxHonor.setModifierId(String.valueOf(staff.getId()));
                        //敏感信息验证
                        BxToken bxToken = bxTokenService.getToken(1);
                        if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                            int checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                            if(checkImgResult == 0){
                                if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                                    bxHonor.setImageUrl(null);
                                    bxHonorService.updateById(bxHonor);
                                    message = "更新成功!";
                                }else{
                                    BxHonor oldBxHonor = bxHonorService.getHonorById(bxHonor.getId());
                                    bxHonor.setMemberId(oldBxHonor.getMemberId());
                                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                    String fileSavePath=path + Constants.honorImgPath + bxHonor.getMemberId() + "/";
                                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                    if(re==0){
                                        String imgUrl = null;
                                        if(bxHonor.getImageUrl()!=null && !"".equals(bxHonor.getImageUrl())){
                                            imgUrl = bxHonor.getImageUrl().split("/")[bxHonor.getImageUrl().split("/").length-1];
                                        }
                                        new File(fileSavePath+imgUrl).delete();
                                        List<String> list = (List<String>)mapImg.get("list");
                                        if(list!=null && list.size()>0){
                                            bxHonor.setImageUrl(list.get(0));
                                            //操作新的文件
                                            bxHonorService.updateById(bxHonor);
                                        }
                                        message = "更新成功!";
                                    }else if(re==-1){
                                        status = -1;
                                        message = "没有文件!";
                                    }else{
                                        status = -1;
                                        message = "上传文件有问题!";
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
                        message = "未登录，请先登录!";
                    }
                }else{
                    status = -1;
                    message = "没有文件";
                }
            } else {
                status = -1;
                message = "参数有误，请联系管理员!";
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
     * 上传荣誉信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addHonor", method = RequestMethod.POST)
    public void addHonor(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxHonor bxHonor, @RequestParam(value="file",required=true) MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message;
        int status = 0;
        try{
            if (bxHonor != null) {
                if(file!=null && file.length>0){
                    String openIdWeb = request.getParameter("openIdWeb");
                    UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
                    if(staff!=null){
                        if(bxHonor.getMemberId()==0){
                            bxHonor.setMemberId(staff.getId());
                        }
                        bxHonor.setCreaterId(staff.getId());

                        //敏感信息验证
                        BxToken bxToken = bxTokenService.getToken(1);
                        if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                            int checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                            if(checkImgResult == 0){
                                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                String fileSavePath=path + Constants.honorImgPath + bxHonor.getMemberId() + "/";
                                Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                                int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                if(re==0){
                                    if(re==0){
                                        List<String> list = (List<String>)mapImg.get("list");
                                        if(list!=null && list.size()>0){
                                            bxHonor.setImageUrl(list.get(0));
                                            bxHonorService.insert(bxHonor);
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
                        message = "未登录，请先登录!";
                    }
                }else{
                    status = -1;
                    message = "没有文件";
                }
            }else{
                status = -1;
                message = "参数有误，请联系管理员!";
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
