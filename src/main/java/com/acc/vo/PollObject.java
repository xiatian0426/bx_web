package com.acc.vo;

import com.acc.util.CachePool;

public class PollObject {

	private static CachePool cachePool;
	
	private static PollObject pollObject;
	
	public PollObject() {
		cachePool = CachePool.getInstance();
	}
	
	public static PollObject getInstance() {
		if (pollObject == null) {
			pollObject = new PollObject();
		}
		return pollObject;
	}
	

	public static final  String COMPLETE = "1";
	public static final  String SUCCESS = "1";
	public static final  String NULL = null;
	
	public void put(String key, String value) {
		cachePool.put(key, value);
	}
	
	public String get(String key) {
		return (String)cachePool.get(key);
	}
}
