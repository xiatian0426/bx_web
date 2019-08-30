package com.acc.exception;

/**
 * 查询异常
 */
public class SelectException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9110975474800756869L;

	public SelectException() {
		super();
	}

	public SelectException(String msg) {
		super(msg);
	}

	public SelectException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SelectException(Throwable cause) {
		super(cause);
	}
}
