
package com.acc.model;

import com.acc.vo.BaseQuery;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxCrawl extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1746208302889590552L;

    private Integer id;

    private String name;
    private String img;
    private String trade;
    private String link;
    private String qrcode;
    private Integer source;
    private String createDate;

    private String sortColumns;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }
}

