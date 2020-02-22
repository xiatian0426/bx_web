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


public class BxMemberTag implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1757258302889590552L;

	private int id;

    private int member_id;

    private int comment_tag_id;

    private int count;

    private String tag_content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getComment_tag_id() {
        return comment_tag_id;
    }

    public void setComment_tag_id(int comment_tag_id) {
        this.comment_tag_id = comment_tag_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTag_content() {
        return tag_content;
    }

    public void setTag_content(String tag_content) {
        this.tag_content = tag_content;
    }
}

