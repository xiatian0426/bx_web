/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

import com.acc.vo.BaseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxMember extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756258302889590552L;

	private int id;

    private String userName;
	
	private String name;

	private String company_name;
	
	private String post_name;
	
	private float years;

    private String signature;

    private String introduce;

    private String memberImg;

    private List<String> memberImgs = new ArrayList<String>();

    private String wechat;

    private String phone;

    private int createrId;

    /**
     * 0:添加 1：修改
     */
    private String type;

    private String company_addr;

    private Double longitude;

    private Double latitude;

    private Integer page_style;

    /**
     * 用于辅助查询
     */
    private String status;

    /**
     * 小程序码
     */
    private String wxaCode;

    /**
     * roleId       db_column: ROLE_ID
     */
    private String roleId;
    /**
     * manageDepart       db_column: MANAGEDEPART
     */
    private String manageDepart;
    /**
     * departClass       db_column: DEPARTCLASS
     */
    private String departClass;
    /**
     * userRealname       db_column: USER_REALNAME
     */
    private String userRealname;
    /**
     * maxCustomerNum       db_column: MAX_CUSTOMER_NUM
     */
    private String maxCustomerNum;

    private String email;

    private List<BxHonor> bxHonorList = new ArrayList<BxHonor>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public float getYears() {
        return years;
    }

    public void setYears(float years) {
        this.years = years;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getMemberImg() {
        return memberImg;
    }

    public void setMemberImg(String memberImg) {
        this.memberImg = memberImg;
    }

    public List<BxHonor> getBxHonorList() {
        return bxHonorList;
    }

    public void setBxHonorList(List<BxHonor> bxHonorList) {
        this.bxHonorList = bxHonorList;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCreaterId() {
        return createrId;
    }

    public void setCreaterId(int createrId) {
        this.createrId = createrId;
    }

    public List<String> getMemberImgs() {
        return memberImgs;
    }

    public void setMemberImgs(List<String> memberImgs) {
        this.memberImgs = memberImgs;
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getPage_style() {
        return page_style;
    }

    public void setPage_style(Integer page_style) {
        this.page_style = page_style;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWxaCode() {
        return wxaCode;
    }

    public void setWxaCode(String wxaCode) {
        this.wxaCode = wxaCode;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getManageDepart() {
        return manageDepart;
    }

    public void setManageDepart(String manageDepart) {
        this.manageDepart = manageDepart;
    }

    public String getDepartClass() {
        return departClass;
    }

    public void setDepartClass(String departClass) {
        this.departClass = departClass;
    }

    public String getMaxCustomerNum() {
        return maxCustomerNum;
    }

    public void setMaxCustomerNum(String maxCustomerNum) {
        this.maxCustomerNum = maxCustomerNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

