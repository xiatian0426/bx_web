
package com.acc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxCase implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1746258302889590552L;

	private int id;
	
	private String name;

	private int age;
	
	private String productName;
	
	private double cost;

    private String timeLimit;

    private int relaProductId;

    private int memberId;

    private String tbContext;

    private String lpContext;

    private String cxContext;

    private int createId;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTbContext() {
        return tbContext;
    }

    public void setTbContext(String tbContext) {
        this.tbContext = tbContext;
    }

    public String getLpContext() {
        return lpContext;
    }

    public void setLpContext(String lpContext) {
        this.lpContext = lpContext;
    }

    public String getCxContext() {
        return cxContext;
    }

    public void setCxContext(String cxContext) {
        this.cxContext = cxContext;
    }

    public int getRelaProductId() {
        return relaProductId;
    }

    public void setRelaProductId(int relaProductId) {
        this.relaProductId = relaProductId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }
}

