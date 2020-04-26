package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxProVideo;
import com.acc.model.BxProduct;
import com.acc.model.BxProductVideo;
import com.acc.model.UserInfo;
import com.acc.service.IBxProVideoService;
import com.acc.service.IUserInfoService;
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
@RequestMapping(value="/userVideo")
public class BxProVideoController {
	private static Logger _logger = LoggerFactory.getLogger(BxProVideoController.class);

	@Autowired
	private IBxProVideoService bxProVideoService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 用户视频信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getUserVideoList", method = RequestMethod.GET)
	public void getUserVideoList(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
        String result="";
        int status = 0;
	    try{
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String memberId = staff.getId()+"";
                if(StringUtils.isNotEmpty(memberId) ){
                    List<BxProVideo> bxProVideoList = bxProVideoService.getProVideoList(memberId);
                    String basePath = Constants.imgVideoPath;
                    for (BxProVideo bxProVideo:bxProVideoList){
                        bxProVideo.setVideoUrl(basePath+Constants.userVideoPath+bxProVideo.getMemberId()+"/"+bxProVideo.getVideoUrl());
                    }
                    if(bxProVideoList!=null && bxProVideoList.size()>0){
                        map.put("bxUserVideo",bxProVideoList.get(0));
                    }
                }
            }else{
                status = 98;
                result = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            result = "操作失败，请联系管理员!";
            _logger.error("getUserVideoList 失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
	    out.flush();
	    out.close();
	}
    /**
     * 添加用户视频信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addUserVideo", method = RequestMethod.POST)
    public void addUserVideo(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProVideo bxProVideo,
                                @RequestParam(value="file",required=true)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                if (bxProVideo != null) {
                    if(file != null){
                        bxProVideo.setMemberId(staff.getId());
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.userVideoPath + bxProVideo.getMemberId() + "/";
                        Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,false);
                        int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                        if(re == 0){
                            List<String> videoNameList = (List<String>)mapImg.get("list");
                            if(videoNameList!=null && videoNameList.size()>0){
                                bxProVideo.setVideoUrl(videoNameList.get(0));
                                BxProVideo oldProVideo = bxProVideoService.getProVideoBymemberId(bxProVideo.getMemberId());
                                if(oldProVideo!=null){
                                    bxProVideo.setModifierId(staff.getId()+"");
                                    bxProVideoService.updateById(bxProVideo);
                                }else{
                                    bxProVideo.setCreaterId(staff.getId());
                                    bxProVideoService.insert(bxProVideo);
                                }
                            }
                            result = "添加成功";
                        }else{
                            result = "添加失败!";
                        }
                    }else{
                        status = 2;
                        result = "视频文件不能为空!";
                    }
                }else{
                    status = 1;
                    result = "参数有误，请联系管理员!";
                }
            }else{
                status = 98;
                result = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            result = "更新失败，请联系管理员!";
            _logger.error("addProductVideo失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
    /**
     * 删除用户视频信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteUserVideoById", method = RequestMethod.POST)
    public void deleteUserVideoById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String, Object>();
        String result="";
        int status = 0;
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String id = staff.getId()+"";
                if(StringUtils.isNotEmpty(id)){
                    //删除视频
                    BxProVideo proVideo = bxProVideoService.getProVideoById(id);
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.userVideoPath + proVideo.getMemberId() + "/";
                    new File(fileSavePath+proVideo.getVideoUrl()).delete();
                    //删除数据
                    bxProVideoService.deleteById(id);
                    result = "删除成功!";
                }
            }else{
                status = 98;
                result = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            result = "操作失败，请联系管理员!";
            _logger.error("deleteRecruit失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
