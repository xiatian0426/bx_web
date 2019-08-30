
package com.acc.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */
public class BxToken implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1546258302889590552L;

	private String accessToken;

	private String lastTime;
	
	private String liftTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLiftTime() {
        return liftTime;
    }

    public void setLiftTime(String liftTime) {
        this.liftTime = liftTime;
    }
}

