package com.arthur.exception;

public class MngException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Object[] args;

	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public MngException(String message) {
		super(message);
	}
	public MngException(String message, Object[] args) {
		super(message);
		this.args = args;
		}

	public MngException(String message, Throwable ex) {
		super(message, ex);
	}

	public MngException(String message, Throwable ex, Object[] args) {
		super(message, ex);
		this.args = args;
	}
}
