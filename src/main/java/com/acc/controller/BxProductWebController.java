package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.*;
import com.acc.service.IBxProductService;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.Page;
import com.acc.vo.ProductQuery;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序后台
 */
@Controller
@RequestMapping(value="/productWeb")
public class BxProductWebController {
	private static Logger _logger = LoggerFactory.getLogger(BxProductWebController.class);
	
	@Autowired
	private IBxProductService bxProductService;

    @Autowired
    private IBxTokenService bxTokenService;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 根据会员id获取对应权限产品
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProductByMemId", method = {RequestMethod.GET,RequestMethod.POST})
    public void getProductByMemId(final HttpServletRequest request,final HttpServletResponse response, @ModelAttribute ProductQuery query) throws IOException {
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
                query.setSortColumns("c.PRODUCT_ORDER");
                Page<BxProduct> page = null;
                String memberId = String.valueOf(staff.getId());
                if(StringUtils.isNotEmpty(memberId) ){
                    query.setMemberId(Integer.valueOf(memberId));
                    page = bxProductService.selectPage(query);
                }
                if(page != null && page.getResult()!=null && page.getResult().size()>0){
                    String basePath = Constants.webPath;
                    for(BxProduct bxProduct:page.getResult()){
                        bxProduct.setProductImg(basePath+ Constants.proImgPath+bxProduct.getId()+"/"+bxProduct.getProductImg());
                    }
                }
                map.put("page", page);
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "保存产品信息失败，请联系管理员!";
            _logger.error("保存产品信息失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 添加或更新商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addOrUpdateProductById", method = RequestMethod.POST)
    public void addOrUpdateProductById(final HttpServletRequest request, final HttpServletResponse response,@RequestParam(value="file",required=false)MultipartFile[] file, @ModelAttribute BxProduct bxProduct
    ) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message;
        int status = 0;
        boolean boo = true;
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                if (bxProduct != null) {
                    if(bxProduct.getType()!=null && !"".equals(bxProduct.getType())){
//                        if(file != null){
                            //敏感信息验证
                            BxToken bxToken = bxTokenService.getToken(1);
                            if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                                String content = bxProduct.getProductName()+bxProduct.getProductDesc()+bxProduct.getBxCaseProductName()
                                        +bxProduct.getBxCaseName()+bxProduct.getBxCaseTimeLimit()+bxProduct.getBxCaseTbContext()
                                        +bxProduct.getBxCaseLpContext()+bxProduct.getBxCaseCxContext();
                                int checkMsgResult = WechatUtil.checkMsg(bxToken.getAccessToken(),content);
                                int checkImgResult = 0;
                                if(file!=null && file.length>0){
                                    checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                                }
                                if(checkMsgResult== 0 && checkImgResult == 0){
                                    if(bxProduct.getType().equals("0")){//添加
                                        if(file != null){
                                            if(bxProduct.getMemberId()==0){
                                                bxProduct.setMemberId(staff.getId());
                                            }
                                            bxProduct.setCreateId(staff.getId());
                                            bxProductService.addProduct(bxProduct);
                                        }else{
                                            boo = false;
                                        }
                                    }else{
                                        if(file == null){
                                            boo = false;
                                            bxProduct.setProductImg(null);
                                            bxProduct.setModifierId(String.valueOf(staff.getId()));
                                            bxProductService.updateProduct(bxProduct);
                                        }
                                    }
                                    if(boo){
                                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                        String fileSavePath=path + Constants.proImgPath + bxProduct.getId() + "/";
                                        Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,true);
                                        int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                        List<String> imgNameList = (List<String>)mapImg.get("list");
                                        if(re == 0){
                                            if(imgNameList!=null && imgNameList.size()>0){
                                                bxProduct.setProductImg(imgNameList.get(0));
                                                bxProduct.setModifierId(String.valueOf(staff.getId()));
                                                bxProductService.updateProduct(bxProduct);
                                                message = "添加/更新成功!";
                                            }else{
                                                status = 5;
                                                message = "参数有误，请联系管理员!";
                                            }
                                        }else{
                                            status = 4;
                                            message = "添加/更新失败!";
                                        }
                                    }else{
                                        status = 0;
                                        message = "操作成功!";
                                    }
                                }else{
                                    status = 99;
                                    message = "信息校验有误，请联系管理员!";
                                }
                            }else{
                                status = 3;
                                message = "信息校验错误，请联系管理员!";
                            }
//                        }else{
//                            status = -1;
//                            message = "文件不能为空!";
//                        }
                    }else{
                        status = -1;
                        message = "参数有误，请联系管理员!";
                    }
                } else {
                    status = 2;
                    message = "参数有误，请联系管理员!";
                }
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "保存产品信息失败，请联系管理员!";
            _logger.error("保存产品信息失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 进入修改产品页
     * @param request
     * @return
     */
    @RequestMapping(value = "/goEditProductByMemId", method = RequestMethod.GET)
    public void goEditProductByMemId(final HttpServletRequest request, final HttpServletResponse response,@ModelAttribute ProductQuery query) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message;
        int status = 0;
        Page<BxProduct> page = null;
        try{
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                query.setSortColumns("c.PRODUCT_ORDER");
                if(query.getMemberId()!=0){
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxProductService.selectPage(query);
                    }
                }
                if(page != null && page.getResult()!=null && page.getResult().size()>0){
                    String basePath = Constants.webPath;
                    for(BxProduct bxProduct:page.getResult()){
                        bxProduct.setProductImg(basePath+ Constants.proImgPath+bxProduct.getId()+"/"+bxProduct.getProductImg());
                    }
                }
                message = "操作成功!";
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "进入失败，请联系管理员!";
            _logger.error("saveMomment失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        map.put("page", page);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 点击修改/查看(回显)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProDetail", method = RequestMethod.GET)
    public void getProDetail(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
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
                String basePath = "";
                String productId = request.getParameter("productId");
                if(StringUtils.isNotEmpty(productId)){
                    List<BxProduct> bxProductList = bxProductService.getProDetail(productId);
                    if(bxProductList!=null && bxProductList.size()>0){
                        BxProduct bxProductResult= new BxProduct();
                        bxProductResult.setId(bxProductList.get(0).getId());
                        basePath = Constants.webPath;
                        bxProductResult.setProductImg(basePath+ Constants.proImgPath+bxProductList.get(0).getId()+"/"+bxProductList.get(0).getProductImg());
                        bxProductResult.setProductName(bxProductList.get(0).getProductName());
                        bxProductResult.setProductDesc(bxProductList.get(0).getProductDesc());
                        bxProductResult.setProductOrder(bxProductList.get(0).getProductOrder());
                        if(bxProductList.get(0).getProductVideo()!=null && !"".equals(bxProductList.get(0).getProductVideo())){
                            bxProductResult.setProductVideo(basePath+ Constants.proVideoPath+bxProductList.get(0).getId()+"/"+bxProductList.get(0).getProductVideo());
                        }
                        List<BxProductImg> bxProductImgList = new ArrayList<BxProductImg>();
                        BxProductImg bxProductImg;
                        for(BxProduct bxProduct:bxProductList){
                            if(bxProduct.getImgId()!=0){
                                bxProductImg = new BxProductImg();
                                bxProductImg.setId(bxProduct.getImgId());
                                bxProductImg.setImageUrl(basePath+ Constants.proDetailImgPath+bxProduct.getId()+"/"+bxProduct.getImageUrl());
                                bxProductImg.setImageOrder(bxProduct.getImageOrder());
                                bxProductImgList.add(bxProductImg);
                            }
                        }
                        bxProductResult.setBxProductImgList(bxProductImgList);
                        //产品详情最多上传10个图片 所以需要生成
                        List imgNulllist = new ArrayList();
                        for (int i=0;i<10-bxProductImgList.size();i++){
                            imgNulllist.add(1);
                        }
                        map.put("imgNulllist",imgNulllist);
                        BxCase bxCase = bxProductService.getCaseDetail(productId);
                        bxProductResult.setBxCase(bxCase);
                        map.put("bxProductResult",bxProductResult);
                    }
                }
                map.put("result", request.getParameter("result"));
                map.put("basePath",basePath);
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "操作失败!";
            _logger.error("getProDetail失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 点击删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public void deleteById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> result = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
        try{
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String productId = request.getParameter("productId");
                if(StringUtils.isNotEmpty(productId)){
                    bxProductService.deleteById(productId);
                    result.put("code",0);
                    result.put("message","删除成功!");
                }
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "操作失败!";
            _logger.error("deleteById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        result.put("status", status);
        result.put("message", message);
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

   /**
    * 添加商品视频信息
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/addProductVideo", method = RequestMethod.POST)
    public void deleteById(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductVideo bxProductVideo,
                                       @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> result = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                BxProduct bxProduct = bxProductService.getProductById(bxProductVideo.getProductId());
                if(bxProduct!=null){
                    if (bxProductVideo != null) {
                        if(file != null){
//                        //敏感信息验证
//                        BxToken bxToken = bxTokenService.getToken();
//                        if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
//                            int checkVideoResult = WechatUtil.checkMedia(bxToken.getAccessToken(),file[0]);
//                            if(checkVideoResult== 0){
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.proVideoPath + bxProductVideo.getProductId() + "/";
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,false);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re == 0){
                                List<String> videoNameList = (List<String>)mapImg.get("list");
                                if(videoNameList!=null && videoNameList.size()>0){
                                    bxProductVideo.setVideoUrl(videoNameList.get(0));
                                    bxProductService.insertProductVideo(bxProductVideo);
                                }
                                message = "添加成功";
                            }else{
                                status = 4;
                                message = "添加失败!";
                            }
                        }else{
                            status = 3;
                            message = "信息校验错误，请联系管理员!";
                        }
//                        }else{
//                            status = 99;
//                            message = "信息校验错误，请联系管理员!";
//                        }
//                    }else{
//                        status = -1;
//                        message = "视频文件不能为空!";
//                    }
                    }else{
                        status = -1;
                        message = "参数有误，请联系管理员!";
                    }
                }else{
                    status = 2;
                    message = "参数有误，请联系管理员!";
                }
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "操作失败!";
            _logger.error("deleteById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        result.put("status", status);
        result.put("message", message);
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
    /**
     * 修改商品图片信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editProductDetailImg", method = RequestMethod.POST)
    public void editProductDetailImg(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductImg bxProductImg,
                                             @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> result = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
//                if(file != null && file.length>0){
                    BxToken bxToken = bxTokenService.getToken(1);
                    if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                        int checkImgResult = 0;
                        if(file!=null && file.length>0){
                            checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                        }
                        if(checkImgResult == 0){
                            if(file == null){
                                bxProductImg.setImageUrl(null);
                                bxProductService.updateProductImg(bxProductImg);
                                message = "更新成功";
                            }else{
                                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                                String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
                                Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                                int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                                if(re == 0){
                                    //删除老图片
                                    String imgUrl = null;
                                    if(bxProductImg.getImageUrl()!=null && !"".equals(bxProductImg.getImageUrl())){
                                        imgUrl = bxProductImg.getImageUrl().split("/")[bxProductImg.getImageUrl().split("/").length-1];
                                    }
                                    File oldFile = new File(fileSavePath+imgUrl);
                                    oldFile.delete();
                                    List<String> imgNameList = (List<String>)mapImg.get("list");
                                    if(imgNameList!=null && imgNameList.size()>0){
                                        bxProductImg.setImageUrl(imgNameList.get(0));
                                        if(bxProductImg.getId()!=0){
                                            //更新
                                            bxProductService.updateProductImg(bxProductImg);
                                        }else{
                                            //添加
                                            bxProductService.insertProductImg(bxProductImg);
                                        }
                                    }
                                    message = "添加成功";
                                }else{
                                    status = 3;
                                    message = "添加失败!";
                                }
                            }
                        }else{
                            status = 99;
                            message = "信息校验有误，请联系管理员!";
                        }
                    }else{
                        status = 2;
                        message = "信息校验有误，请联系管理员!";
                    }
//                }else{
//                    status = -1;
//                    message = "文件不能为空!";
//                }
            }else{
                status = 98;
                message = "未登录，请先登录!";
            }
        } catch (Exception e) {
            status = -1;
            message = "操作失败!";
            _logger.error("deleteById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        result.put("status", status);
        result.put("message", message);
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 删除产品详情图片
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteProductDetailImgById", method = RequestMethod.POST)
    public void deleteProductDetailImgById (final HttpServletRequest request,
                                                           final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String id = request.getParameter("id");
                if(StringUtils.isNotEmpty(id)){
                    BxProductImg bxProductImg = bxProductService.getProductDetailImgById(id);
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
                    new File(fileSavePath+bxProductImg.getImageUrl()).delete();
                    bxProductService.deleteProImgById(id);
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
     * 删除商品视频信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteProductDetailVideoById", method = RequestMethod.POST)
    public void deleteProductDetailVideoById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            String openIdWeb = request.getParameter("openIdWeb");
            UserInfo staff = userInfoService.getByOpenIdWeb(openIdWeb);
            if(staff!=null){
                String id = request.getParameter("id");
                if(StringUtils.isNotEmpty(id)){
                    //删除视频
                    BxProductVideo bxProductVideo = bxProductService.getProductDetailVideoById(id);
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.proVideoPath + bxProductVideo.getProductId() + "/";
                    new File(fileSavePath+bxProductVideo.getVideoUrl()).delete();
                    //删除数据
                    bxProductService.deleteProductDetailVideoById(id);
                    result.put("code",0);
                    result.put("message","删除成功!");
                }
            }else{
                result.put("code",98);
                result.put("message","未登录，请先登录!");
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
}
