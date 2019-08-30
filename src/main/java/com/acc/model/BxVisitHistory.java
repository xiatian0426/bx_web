
package com.acc.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxVisitHistory implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1757958302889590552L;

	private int id;
    /**
     * 头像
     */
	private String headSculpture;
    /**
     * 昵称
     */
	private String nickName;

    /**
     * 用于获取openid
     */
	private String code;

	private String openId;

    private String sex;

    private Integer memberId;

    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

