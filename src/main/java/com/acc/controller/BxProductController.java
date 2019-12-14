package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.*;
import com.acc.service.IBxHomePageService;
import com.acc.service.IBxProductService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/product")
public class BxProductController {
	private static Logger _logger = LoggerFactory.getLogger(BxProductController.class);
	
	@Autowired
	private IBxProductService bxProductService;

    /**
     * 根据会员id获取对应权限产品
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getProductByMemId", method = RequestMethod.GET)
    public void getProductByMemId(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String result = "";
        try{
            String memberId = request.getParameter("memberId");
            if(StringUtils.isNotEmpty(memberId)){
                List<BxProduct> list = bxProductService.getProductByPerm(memberId);
                if(list != null && list.size()>0){
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    basePath = Constants.imgVideoPath;
                    for(BxProduct bxProduct:list){
                        bxProduct.setProductImg(basePath+Constants.proImgPath+bxProduct.getId()+"/"+bxProduct.getProductImg());
                    }
                }
                result = JSON.toJSONString(list);
            }
        } catch (Exception e) {
            _logger.error("getProductByMemId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 根据产品id获取产品详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getProductDetail", method = RequestMethod.GET)
    public void getProductDetail(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String result = "";
        try{
            String productId = request.getParameter("productId");
            if(StringUtils.isNotEmpty(productId)){
                List<BxProduct> list = bxProductService.getDetailByProductId(productId);
                if(list != null && list.size()>0){
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    basePath = Constants.imgVideoPath;
                    BxProduct resultBxProduct = new BxProduct();
                    resultBxProduct.setId(list.get(0).getId());
                    resultBxProduct.setProductName(list.get(0).getProductName());
                    resultBxProduct.setProductVideo(basePath+Constants.proVideoPath+list.get(0).getId()+"/"+list.get(0).getProductVideo());
                    List<String> imgUrlList = new ArrayList<String>();
                    for(BxProduct bxProduct:list){
                        imgUrlList.add(basePath+Constants.proDetailImgPath+bxProduct.getId()+"/"+bxProduct.getImageUrl());
                    }
                    resultBxProduct.setImgUrlList(imgUrlList);
                    result = JSON.toJSONString(resultBxProduct);
                }
            }
        } catch (Exception e) {
            _logger.error("getDetailByProductId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(result);
        out.flush();
        out.close();
    }
    /**
     * 获取案例产品详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCaseDetail", method = RequestMethod.GET)
    public void getCaseDetail(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String result = "";
        try{
            String productId = request.getParameter("productId");
            if(StringUtils.isNotEmpty(productId)){
                BxCase bxCase = bxProductService.getCaseDetail(productId);
                result = JSON.toJSONString(bxCase);
            }
        } catch (Exception e) {
            _logger.error("getCaseDetail失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(result);
        out.flush();
        out.close();
    }
    /**
     * 管理端--点击删除
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
        try{
            String productId = request.getParameter("productId");
            if(StringUtils.isNotEmpty(productId)){
                bxProductService.deleteById(productId);
                result.put("code",0);
                result.put("message","删除成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","删除失败!");
            _logger.error("deleteById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 管理端--点击修改/查看(回显)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getProDetail", method = RequestMethod.GET)
    public void getProDetail(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String result = "";
        try{
            String productId = request.getParameter("productId");
            if(StringUtils.isNotEmpty(productId)){
                List<BxProduct> bxProductList = bxProductService.getProDetail(productId);
                if(bxProductList!=null && bxProductList.size()>0){
                    BxProduct bxProductResult= new BxProduct();
                    bxProductResult.setId(bxProductList.get(0).getId());
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    basePath = Constants.imgVideoPath;
                    bxProductResult.setProductImg(basePath+Constants.proImgPath+bxProductList.get(0).getId()+"/"+bxProductList.get(0).getProductImg());
                    bxProductResult.setProductName(bxProductList.get(0).getProductName());
                    bxProductResult.setProductDesc(bxProductList.get(0).getProductDesc());
                    bxProductResult.setProductVideo(basePath+Constants.proVideoPath+bxProductList.get(0).getId()+"/"+bxProductList.get(0).getProductVideo());
                    List<BxProductImg> bxProductImgList = new ArrayList<BxProductImg>();
                    BxProductImg bxProductImg;
                    for(BxProduct bxProduct:bxProductList){
                        bxProductImg = new BxProductImg();
                        bxProductImg.setImageUrl(basePath+Constants.proDetailImgPath+bxProduct.getId()+"/"+bxProduct.getImageUrl());
                        bxProductImg.setImageOrder(bxProduct.getImageOrder());
                        bxProductImgList.add(bxProductImg);
                    }
                    bxProductResult.setBxProductImgList(bxProductImgList);
                    BxCase bxCase = bxProductService.getCaseDetail(productId);
                    bxProductResult.setBxCase(bxCase);
                    result = JSON.toJSONString(bxProductResult);
                }
            }
        } catch (Exception e) {
            _logger.error("getProDetail失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
    /**
     * 后台管理--更新商品信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addOrUpdateProductById", method = RequestMethod.POST)
    public void addOrUpdateProductById(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProduct bxProduct, @RequestParam(value="file",required=false)MultipartFile[] file
                                  ) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        boolean boo = true;
        try {
            if (bxProduct != null) {
                if(bxProduct.getType()!=null && !"".equals(bxProduct.getType())){
                    if(file != null){
                        if(bxProduct.getType().equals("0")){
                            bxProductService.addProduct(bxProduct);
                        }else{
                            BxProduct oldBxProduct = bxProductService.getProductById(bxProduct.getId());
                            if(oldBxProduct==null){
                                boo = false;
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
                                    bxProductService.updateProduct(bxProduct);
                                    result = "更新成功!";
                                }else{
                                    status = 1;
                                    result = "参数有误，请联系管理员!";
                                }
                            }else{
                                status = 3;
                                result = "添加/更新失败!";
                            }
                        }else{
                            status = 1;
                            result = "参数有误，请联系管理员!";
                        }
                    }else{
                        status = 2;
                        result = "文件不能为空!";
                    }
                }else{
                    status = 1;
                    result = "参数有误，请联系管理员!";
                }
            } else {
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加/更新失败，请联系管理员!";
            _logger.error("updateProductById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
    /**
     * 后台管理--添加商品视频信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addProductVideo", method = RequestMethod.POST)
    public void addProductVideo(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductVideo bxProductVideo,
                                       @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            BxProduct bxProduct = bxProductService.getProductById(bxProductVideo.getProductId());
            if(bxProduct!=null){
                if (bxProductVideo != null) {
                    if(file != null){
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
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加失败，请联系管理员!";
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
     * 后台管理--删除商品视频信息
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
     * 后台管理--添加商品图片信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addProductDetailImg", method = RequestMethod.POST)
    public void addProductDetailImg(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductImg bxProductImg,
                                    @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            BxProduct bxProduct = bxProductService.getProductById(bxProductImg.getProductId());
            if(bxProduct!=null){
                if (bxProductImg != null) {
                    if(file != null){
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
                        if(bxProductImg.getIsFirst()!=null){
                            if("0".equals(bxProductImg.getIsFirst())){
                                bxProductService.deleteProductDetailImgByProId(""+bxProductImg.getProductId());
                            }
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re == 0){
                                List<String> imgNameList = (List<String>)mapImg.get("list");
                                if(imgNameList!=null && imgNameList.size()>0){
                                    bxProductImg.setImageUrl(imgNameList.get(0));
                                    bxProductService.insertProductImg(bxProductImg);
                                }
                                if("0".equals(bxProductImg.getIsLast())){
                                    //如果是最后一次上传图片  获取产品整个图片  进行匹配删除
                                    List<BxProductImg> bxProductImgList = bxProductService.getProductDetailImgByProId(bxProductImg.getProductId()+"");
                                    File fileTemp = new File(fileSavePath);
                                    boolean falg = fileTemp.exists();
                                    if (falg) {
                                        String[] png = fileTemp.list();
                                        boolean boo;
                                        for (int i = 0; i < png.length; i++) {
                                            boo = true;
                                            for(BxProductImg bxProductImgg:bxProductImgList){
                                                if(bxProductImgg.getImageUrl().equals(png[i])){
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
                                result = "添加成功";
                            }else{
                                result = "添加失败!";
                            }
                        }else{
                            status = 1;
                            result = "参数有误，请联系管理员!";
                        }
                    }else{
                        status = 2;
                        result = "文件不能为空!";
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
            result = "添加失败，请联系管理员!";
            _logger.error("addProductImg失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
    /**
     * 后台管理--添加商品图片信息
     *
     * @param request
     * @param response
     * @return
     *//*
    @RequestMapping(value = "/addProductDetailImg", method = RequestMethod.POST)
    public void addProductDetailImg(final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductImg bxProductImg,
                                @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            if (bxProductImg != null) {
                if(file != null){
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re == 0){
                        List<String> imgNameList = (List<String>)mapImg.get("list");
                        if(imgNameList!=null && imgNameList.size()>0){
                            bxProductImg.setImageUrl(imgNameList.get(0));
                            bxProductService.insertProductImg(bxProductImg);
                        }
                        result = "添加成功";
                    }else{
                        result = "添加失败!";
                    }
                }else{
                    status = 2;
                    result = "文件不能为空!";
                }
            }else{
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加失败，请联系管理员!";
            _logger.error("addProductImg失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("result", result);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }*/
//    /**
//     * 后台管理--删除商品图片信息
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/deleteProductDetailImgById", method = RequestMethod.POST)
//    public void deleteProductDetailImgById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        Map<String,Object> result = new HashMap<String, Object>();
//        try {
//            String id = request.getParameter("id");
//            if(StringUtils.isNotEmpty(id)){
//                //删除图片
//                BxProductImg bxProductImg = bxProductService.getProductDetailImgById(id);
//                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
//                String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
//                new File(fileSavePath+bxProductImg.getImageUrl()).delete();
//                //删除数据
//                bxProductService.deleteProductDetailImgById(id);
//                result.put("code",0);
//                result.put("message","删除成功!");
//            }
//        } catch (Exception e) {
//            result.put("code",-1);
//            result.put("message","删除失败!");
//            _logger.error("deleteRecruit失败：" + ExceptionUtil.getMsg(e));
//            e.printStackTrace();
//        }
//        out.print(JSON.toJSONString(result));
//        out.flush();
//        out.close();
//    }
}
