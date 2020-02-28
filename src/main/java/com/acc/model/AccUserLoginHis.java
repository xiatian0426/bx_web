/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.acc.model;

import java.util.Date;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class AccUserLoginHis implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AccUserLoginHis";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_NAME = "userName";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_URL = "url";
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */	
	
	private int id;
    /**
     * userName       db_column: USER_NAME 
     */	
	private String userName;
    /**
     * createTime       db_column: CREATE_TIME 
     */	
	
	private Date createTime;
    /**
     * url       db_column: URL 
     */	
	private String url;

	private int type;
	//columns END

	public AccUserLoginHis(){
	}

	public AccUserLoginHis(
		int id
	){
		this.id = id;
	}

	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return this.id;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

