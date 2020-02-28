/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.vo;

import com.acc.model.BxCase;
import com.acc.model.BxProductImg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class ProductQuery extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756257302889590553L;

	private int id;

	private int productOrder;

    private String productName;

    /**
     * 简短概括
     */
    private String productDesc;
    /**
     * 产品视频
     */
    private String productVideo;
    /**
     * 产品本身图片
     */
	private String productImg;
    /**
     * 产品图片链接
     */
    private String imageUrl;

    private int imageOrder;

    private BxCase bxCase;

    private int createId;

    private int memberId;

    private int imgId;

    /**
     * 用于确定是添加还是更新 0：添加 1：更新
     */
    private String type;

    //BxCase的属性 start
    private String bxCaseName;

    private String bxCaseProductName;

    private int bxCaseAge;

    private double bxCaseCost;

    private String bxCaseTimeLimit;

    private String bxCaseTbContext;

    private String bxCaseLpContext;

    private String bxCaseCxContext;
    //BxCase的属性 end
    private int status;

    private String sortColumns;

    private List<BxProductImg> bxProductImgList = new ArrayList<BxProductImg>();

    private List<String> imgUrlList = new ArrayList<String>();

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductVideo() {
        return productVideo;
    }

    public void setProductVideo(String productVideo) {
        this.productVideo = productVideo;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public int getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(int imageOrder) {
        this.imageOrder = imageOrder;
    }

    public List<BxProductImg> getBxProductImgList() {
        return bxProductImgList;
    }

    public void setBxProductImgList(List<BxProductImg> bxProductImgList) {
        this.bxProductImgList = bxProductImgList;
    }

    public BxCase getBxCase() {
        return bxCase;
    }

    public void setBxCase(BxCase bxCase) {
        this.bxCase = bxCase;
    }

    public int getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(int productOrder) {
        this.productOrder = productOrder;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBxCaseName() {
        return bxCaseName;
    }

    public void setBxCaseName(String bxCaseName) {
        this.bxCaseName = bxCaseName;
    }

    public String getBxCaseProductName() {
        return bxCaseProductName;
    }

    public void setBxCaseProductName(String bxCaseProductName) {
        this.bxCaseProductName = bxCaseProductName;
    }

    public int getBxCaseAge() {
        return bxCaseAge;
    }

    public void setBxCaseAge(int bxCaseAge) {
        this.bxCaseAge = bxCaseAge;
    }

    public double getBxCaseCost() {
        return bxCaseCost;
    }

    public void setBxCaseCost(double bxCaseCost) {
        this.bxCaseCost = bxCaseCost;
    }

    public String getBxCaseTimeLimit() {
        return bxCaseTimeLimit;
    }

    public void setBxCaseTimeLimit(String bxCaseTimeLimit) {
        this.bxCaseTimeLimit = bxCaseTimeLimit;
    }

    public String getBxCaseTbContext() {
        return bxCaseTbContext;
    }

    public void setBxCaseTbContext(String bxCaseTbContext) {
        this.bxCaseTbContext = bxCaseTbContext;
    }

    public String getBxCaseLpContext() {
        return bxCaseLpContext;
    }

    public void setBxCaseLpContext(String bxCaseLpContext) {
        this.bxCaseLpContext = bxCaseLpContext;
    }

    public String getBxCaseCxContext() {
        return bxCaseCxContext;
    }

    public void setBxCaseCxContext(String bxCaseCxContext) {
        this.bxCaseCxContext = bxCaseCxContext;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }
}

