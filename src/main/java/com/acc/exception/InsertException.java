package com.acc.exception;

/**
 * 查询异常
 */
public class InsertException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9110975474800756869L;

	public InsertException() {
		super();
	}

	public InsertException(String msg) {
		super(msg);
	}

	public InsertException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InsertException(Throwable cause) {
		super(cause);
	}
}
