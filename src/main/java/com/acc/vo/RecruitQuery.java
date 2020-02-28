
package com.acc.vo;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class RecruitQuery extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756958302889390552L;

	private int id;
	
	private String imageUrl;

	private int memberId;
	
	private int status;

    private String createDate;

    private int createrId;

    private int recruitOrder;

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

    public int getRecruitOrder() {
        return recruitOrder;
    }

    public void setRecruitOrder(int recruitOrder) {
        this.recruitOrder = recruitOrder;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }
}

