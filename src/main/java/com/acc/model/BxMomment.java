/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxMomment implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756258302889590556L;

	private int id;

    /**
     * 0：普通客户 1：游客
     */
    private int role;
    /**
     * 评论人微信号
     */
    private String commentator_wechat;
    /**
     * 评论人昵称
     */
	private String commentator_name;
    /**
     * 评论人头像
     */
	private String commentator_img;

    /**
     * 评论内容
     */
	private String comment_context;
    /**
     * 评论时间
     */
	private String create_date;

    /**
     * 0：未审核 1：已删除 2：审核通过 3：审核不通过
     */
	private int status;

	private String member_img;

	private int member_id;

	private int respondent_id;

	private String comment_tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCommentator_name() {
        return commentator_name;
    }

    public void setCommentator_name(String commentator_name) {
        this.commentator_name = commentator_name;
    }

    public String getComment_context() {
        return comment_context;
    }

    public void setComment_context(String comment_context) {
        this.comment_context = comment_context;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMember_img() {
        return member_img;
    }

    public void setMember_img(String member_img) {
        this.member_img = member_img;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getCommentator_wechat() {
        return commentator_wechat;
    }

    public void setCommentator_wechat(String commentator_wechat) {
        this.commentator_wechat = commentator_wechat;
    }

    public String getCommentator_img() {
        return commentator_img;
    }

    public void setCommentator_img(String commentator_img) {
        this.commentator_img = commentator_img;
    }

    public int getRespondent_id() {
        return respondent_id;
    }

    public void setRespondent_id(int respondent_id) {
        this.respondent_id = respondent_id;
    }

    public String getComment_tag() {
        return comment_tag;
    }

    public void setComment_tag(String comment_tag) {
        this.comment_tag = comment_tag;
    }
}

