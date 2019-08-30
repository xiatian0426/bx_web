package com.acc.exception;

/**
 * 更新异常
 */
public class UpdateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9110975474800756869L;

	public UpdateException() {
		super();
	}

	public UpdateException(String msg) {
		super(msg);
	}

	public UpdateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UpdateException(Throwable cause) {
		super(cause);
	}
}
