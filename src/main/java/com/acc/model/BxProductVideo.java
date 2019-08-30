/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxProductVideo implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756258302889590593L;

	private int id;

	private int productId;

	private String videoUrl;

    private String status;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

