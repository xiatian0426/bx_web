package com.acc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acc.dao.BxCommentTagMapper;
import com.acc.frames.web.ResourceExposer;
import com.acc.model.*;
import com.acc.service.IBxHomePageService;
import com.acc.service.IBxHonorService;
import com.acc.service.IBxTokenService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.BaseQuery;
import com.acc.vo.Page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.acc.exception.ExceptionUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/homePage")
public class BxHomePageController {
    private static Logger _logger = LoggerFactory.getLogger(BxHomePageController.class);

    @Autowired
    private IBxHomePageService bxHomePageService;

    @Autowired
    private IBxHonorService bxHonorService;

    @Autowired
    private IBxTokenService bxTokenService;

    @Autowired
    private BxCommentTagMapper bxCommentTagMapper;

    /**
     * 根据id获取会员信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMemberById", method = RequestMethod.GET)
    public void getMemberById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        BxMember bxMember = null;
        try {
            String memberId = request.getParameter("id");
            if (StringUtils.isNotEmpty(memberId)) {
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                bxMember = bxHomePageService.getMemberById(Integer.parseInt(memberId));
                if(bxMember!=null){
                    bxMember.setMemberImg(basePath + Constants.memberImgPath + bxMember.getId() + "/" + bxMember.getMemberImg());
                    if(bxMember.getWxaCode()!=null && !bxMember.getWxaCode().equals("")){
                        bxMember.setWxaCode(basePath + Constants.memberImgWxaCodePath + bxMember.getWxaCode());
                    }
                }
            }
        } catch (Exception e) {
            _logger.error("getMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("member", bxMember);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 根据id获取会员信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCommentTag", method = RequestMethod.GET)
    public void getCommentTag(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        List<BxCommentTag> bxCommentTagList = new ArrayList<BxCommentTag>();
        try {
            bxCommentTagList = bxCommentTagMapper.getCommentTagList(null);
        } catch (Exception e) {
            _logger.error("getCommentTagByMemberId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("bxCommentTagList", bxCommentTagList);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 根据id获取所有评论
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMommentListById", method = RequestMethod.GET)
    public void getMommentListById(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxMember query) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String result = "";
        try {
            String memberId = request.getParameter("id");
//            String page = request.getParameter("page");
//            String size = request.getParameter("size");
            if (StringUtils.isNotEmpty(memberId) && query!=null && query.getStatus()!=null) {
                Page<BxMomment> page = bxHomePageService.selectPage(query);
                Map<String, Object> map = new HashMap<String, Object>();
                List<BxMomment> bxMommentList = (List<BxMomment>)page.getResult();
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                for (BxMomment bxMomment : bxMommentList) {
                    bxMomment.setMember_img(basePath + Constants.memberImgPath + bxMomment.getMember_id() + "/" + bxMomment.getMember_img());
                    if(bxMomment.getComment_tag()!=null && !bxMomment.getComment_tag().equals("")){
                        List<BxCommentTag> bxCommentTagList = bxCommentTagMapper.getCommentTagList(bxMomment.getComment_tag());
                        bxMomment.setCommentTagList(bxCommentTagList);
                    }
                }
                map.put("page", page);
                result = JSON.toJSONString(map);
            }
        } catch (Exception e) {
            _logger.error("getMommentListById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 根据用户id获取该用户所有评论标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMemberTagById", method = RequestMethod.GET)
    public void getMemberTagById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String memberId = request.getParameter("id");
            List<BxMemberTag> bxMemberTagList = bxHomePageService.getMemberTagById(memberId);
            map.put("bxMemberTagList", bxMemberTagList);
        } catch (Exception e) {
            _logger.error("getMemberTagById：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 添加评论
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editMommentStatus", method = RequestMethod.POST)
    public void editMommentStatus(@ModelAttribute BxMomment bxMomment,final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            if (bxMomment!=null) {
                bxHomePageService.updateMommentStatus(bxMomment);
                //更新用户的评价标签
                if(bxMomment.getStatus()==2 && bxMomment.getComment_tag()!=null && !bxMomment.getComment_tag().equals("")){//审核通过 添加或更新用户评论标签
                    List<BxMemberTag> bxMemberTagList = bxHomePageService.getMemberTagById(String.valueOf(bxMomment.getRespondent_id()));
                    String[] strs = bxMomment.getComment_tag().split(",");
                    for (String str:strs){
                        boolean boo=false;
                        BxMemberTag bxMemberTagNew = new BxMemberTag();
                        for(BxMemberTag bxMemberTag:bxMemberTagList){
                            if(Integer.valueOf(str).intValue()==bxMemberTag.getComment_tag_id()){
                                boo=true;
                                bxMemberTagNew = bxMemberTag;
                                break;
                            }
                        }
                        if(boo){
                            //更新
                            bxMemberTagNew.setCount(bxMemberTagNew.getCount()+1);
                            bxHomePageService.updateMemberTag(bxMemberTagNew);
                        }else{
                            //保存
                            bxMemberTagNew.setMember_id(bxMomment.getRespondent_id());
                            bxMemberTagNew.setComment_tag_id(Integer.valueOf(str).intValue());
                            bxMemberTagNew.setCount(1);
                            bxHomePageService.saveMemberTag(bxMemberTagNew);
                        }
                    }
                }
                result = "操作成功!";
            }else{
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "保存失败，请联系管理员!";
            _logger.error("saveMomment失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
    /**
     * 添加评论
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveMomment", method = RequestMethod.POST)
    public void saveMomment(@ModelAttribute BxMomment bxMomment,final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            if (bxMomment!=null) {
                BxToken bxToken = bxTokenService.getToken(0);
                if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                    Integer res = WechatUtil.checkMsg(bxToken.getAccessToken(),bxMomment.getComment_context());
                    if(res.intValue()==0){
                        if(bxMomment.getComment_tag()!=null && bxMomment.getComment_tag().endsWith(",")
                                && bxMomment.getComment_tag().length()>1){
                            bxMomment.setComment_tag(bxMomment.getComment_tag().substring(0,bxMomment.getComment_tag().length()-1));
                        }
                        bxHomePageService.insert(bxMomment);
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
            _logger.error("saveMomment失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 后台管理--个人信息查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMemberByIdAdmin", method = RequestMethod.GET)
    public void getMemberByIdAdmin(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        BxMember bxMember = null;
        try{
            String memberId = request.getParameter("memberId");
            if (StringUtils.isNotEmpty(memberId)) {
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                basePath = Constants.imgVideoPath;
                bxMember = bxHomePageService.getMemberById(Integer.parseInt(memberId));
                if (bxMember != null) {
                    bxMember.setMemberImg(basePath + Constants.memberImgPath + bxMember.getId() + "/" + bxMember.getMemberImg());
                    List<String> ll = new ArrayList<String>();
                    ll.add(bxMember.getMemberImg());
                    bxMember.setMemberImgs(ll);
                    List<BxHonor> bxHonorList = bxHonorService.getHonorList(memberId);
                    for (BxHonor bxHonor : bxHonorList) {
                        bxHonor.setImageUrl(basePath + Constants.honorImgPath + bxHonor.getMemberId() + "/" + bxHonor.getImageUrl());
                    }
                    bxMember.setBxHonorList(bxHonorList);
                }
            }
        } catch (Exception e) {
            _logger.error("getMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("member", bxMember);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 后台管理--更新个人信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateMemberById", method = RequestMethod.POST)
    public void updateMemberById(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxMember bxMember,@RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        boolean boo = true;
        boolean boo1 = true;
        try {
            if (bxMember != null) {
                if(file!=null && file.length>0){
                    if(bxMember.getType()!=null){
                        if(bxMember.getType().equals("0")){
                            BxMember oldBxMember = bxHomePageService.getMemberByWechat(bxMember.getWechat());
                            if(oldBxMember==null){
                                bxHomePageService.addMember(bxMember);
                            }else{
                                boo1 = false;
                            }
                        }else{
                            System.out.println("id==========="+bxMember.getId());
                            System.out.println(bxMember.toString());
                            BxMember BxMember = bxHomePageService.getMemberById(bxMember.getId());
                            if(BxMember==null){
                                boo = false;
                            }
                        }
                        if(boo1){
                            if(boo){
                                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                String fileSavePath=path + Constants.memberImgPath + bxMember.getId() + "/";
                                Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,true);
                                int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                if(re==0){
                                    List<String> list = (List<String>)mapImg.get("list");
                                    if(list!=null && list.size()>0){
                                        bxMember.setMemberImg(list.get(0));
                                        if(bxMember.getType()!=null){
                                            bxHomePageService.updateMemberById(bxMember);
                                        }
                                    }
                                    result = "更新成功!";
                                }else if(re==-1){
                                    bxHomePageService.deleteMemberById(bxMember.getId());
                                    status = 3;
                                    result = "没有文件!";
                                }else{
                                    bxHomePageService.deleteMemberById(bxMember.getId());
                                    status = 2;
                                    result = "上传文件有问题!";
                                }
                            }else{
                                status = 1;
                                result = "参数有误，请联系管理员!";
                            }
                        }else{
                            status = 99;
                            result = "该微信号用户已存在!";
                        }
                    }else{
                        status = 1;
                        result = "参数有误，请联系管理员!";
                    }
                }else{
                    status = 3;
                    result = "没有文件";
                }
            } else {
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "更新失败，请联系管理员!";
            _logger.error("updateMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 后台管理--更新个人荣誉榜图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateHonorImg", method = RequestMethod.POST)
    public void updateHonorImg(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxHonor bxHonor,@RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        _logger.info("===================================================================进入updateHonorImg");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            BxMember bxMember = bxHomePageService.getMemberById(bxHonor.getMemberId());
            if(bxMember!=null){
                if (bxHonor != null) {
                    if(file!=null && file.length>0){
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.honorImgPath + bxHonor.getMemberId() + "/";
                        if(bxHonor.getIsFirst()!=null){
                            if("0".equals(bxHonor.getIsFirst())){
                                //删除该代理商的整个荣誉文件夹
                                bxHonorService.deleteByMemId(bxHonor.getMemberId());
                            }
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re==0){
                                List<String> list = (List<String>)mapImg.get("list");
                                if(list!=null && list.size()>0){
                                    bxHonor.setImageUrl(list.get(0));
                                    //操作新的文件
                                    _logger.info("===================================================================保存bxHonor开始");
                                    bxHonorService.insert(bxHonor);
                                    _logger.info("===================================================================保存bxHonor结束");
                                }
                                if("0".equals(bxHonor.getIsLast())){
                                    List<BxHonor> bxHonorList = bxHonorService.getHonorList(bxHonor.getMemberId()+"");
                                    File fileTemp = new File(fileSavePath);
                                    boolean falg = fileTemp.exists();
                                    if (falg) {
                                        String[] png = fileTemp.list();
                                        boolean boo;
                                        for (int i = 0; i < png.length; i++) {
                                            boo = true;
                                            for(BxHonor bxHonorr:bxHonorList){
                                                if(bxHonorr.getImageUrl().equals(png[i])){
                                                    boo = false;
                                                    break;
                                                }
                                            }
                                            if(boo){
                                                new File(fileSavePath + png[i]).delete();
                                            }
                                        }
                                    }
                                }

                                result = "添加/更新成功!";
                            }else if(re==-1){
                                status = 3;
                                result = "没有文件!";
                            }else{
                                status = 2;
                                result = "上传文件有问题!";
                            }
                        }else{
                            status = 1;
                            result = "参数有误，请联系管理员!";
                        }
                    }else{
                        status = 3;
                        result = "没有文件";
                    }
                } else {
                    status = 1;
                    result = "参数有误，请联系管理员!";
                }
            }else{
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加/更新失败，请联系管理员!";
            _logger.error("updateMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 后台管理--更新个人荣誉榜图片(废弃)
     *
     * @param request
     * @param response
     * @return
     *//*
    @RequestMapping(value = "/updateHonorImg2", method = RequestMethod.POST)
    public void updateHonorImg2(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxHonor bxHonor,@RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
       _logger.info("===================================================================进入updateHonorImg");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        boolean boo = true;
        try {
            if (bxHonor != null) {
                if(file!=null && file.length>0){
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.honorImgPath + bxHonor.getMemberId() + "/";
                    BxHonor oldBxHonor;
                    if(!"0".equals(bxHonor.getType())) {
                        oldBxHonor = bxHonorService.getHonorById(bxHonor.getId());
                        if(oldBxHonor==null){
                            boo = false;
                        }else{
                            //删除之前文件
                            new File(fileSavePath+oldBxHonor.getImageUrl()).delete();
                        }
                    }
                    if(boo){
                        Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                        int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxHonor.setImageUrl(list.get(0));
                                if(bxHonor.getType()!=null){//添加
                                    //操作新的文件
                                    if("0".equals(bxHonor.getType())) {
                                        _logger.info("===================================================================保存bxHonor开始");
                                        bxHonorService.insert(bxHonor);
                                        _logger.info("===================================================================保存bxHonor结束");
                                    }else{//更新
                                        bxHonorService.updateById(bxHonor);
                                    }
                                }
                            }
                            result = "添加/更新成功!";
                        }else if(re==-1){
                            status = 3;
                            result = "没有文件!";
                        }else{
                            status = 2;
                            result = "上传文件有问题!";
                        }
                    }else{
                        status = 1;
                        result = "参数有误，请联系管理员!";
                    }
                }else{
                    status = 3;
                    result = "没有文件";
                }
            } else {
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加/更新失败，请联系管理员!";
            _logger.error("updateMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }*/
}
