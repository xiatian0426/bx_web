/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxCommentTag implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756258332889590556L;

	private int id;

    /**
     * 标签内容
     */
    private String tag_content;
    /**
     * 创建时间
     */
	private String create_date;

    private int creater_id;

    /**
     * 0：未删除 1：已删除
     */
	private int status;

    /**
     * 创建时间
     */
    private String modify_date;

    private int modifier_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_content() {
        return tag_content;
    }

    public void setTag_content(String tag_content) {
        this.tag_content = tag_content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getCreater_id() {
        return creater_id;
    }

    public void setCreater_id(int creater_id) {
        this.creater_id = creater_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }

    public int getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(int modifier_id) {
        this.modifier_id = modifier_id;
    }
}

