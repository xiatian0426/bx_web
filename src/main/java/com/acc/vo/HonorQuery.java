
package com.acc.vo;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class HonorQuery extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756958302789590552L;

	private int id;
	
	private String imageUrl;

	private int memberId;
	
	private int status;

    private String createDate;

    private int createrId;

    private int honorOrder;
    /**
     * 0:添加 1：更新
     */
    private String type;

    private String isFirst;

    private String isLast;

    private String sortColumns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreaterId() {
        return createrId;
    }

    public void setCreaterId(int createrId) {
        this.createrId = createrId;
    }

    public int getHonorOrder() {
        return honorOrder;
    }

    public void setHonorOrder(int honorOrder) {
        this.honorOrder = honorOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }

    public String getIsLast() {
        return isLast;
    }

    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }
}

