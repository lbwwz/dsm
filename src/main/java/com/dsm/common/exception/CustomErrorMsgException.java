package com.dsm.common.exception;

/**
 * 自定义错误信息异常
 * <p>
 *     用于抛出执行的异常信息，设置为{@link com.dsm.model.BackMsg#message}
 * </p>
 * @author lbw
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