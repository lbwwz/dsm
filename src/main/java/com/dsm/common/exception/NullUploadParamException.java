package com.dsm.common.exception;

/**
 * 文件上传必要参数未定义的异常
 * @author Think
 */
public class NullUploadParamException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NullUploadParamException() {
		super();
	}
	
	public NullUploadParamException(String msg){
		super(msg);
	}

}