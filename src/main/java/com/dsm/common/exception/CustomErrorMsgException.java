package com.dsm.common.exception;

/**
 * 文件上传必要参数未定义的异常
 * @author Think
 */
public class CustomErrorMsgException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	public CustomErrorMsgException() {
		super();
	}

	public CustomErrorMsgException(String msg){
		super(msg);
	}

}