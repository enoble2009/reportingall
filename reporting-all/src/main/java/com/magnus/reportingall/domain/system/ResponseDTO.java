package com.magnus.reportingall.domain.system;

public class ResponseDTO<T> {

	private ResponseCode code;
	
	private String msg;
	
	private T object;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(ResponseCode code, String msg, T object) {
		super();
		this.code = code;
		this.msg = msg;
		this.object = object;
	}
	
	public static ResponseDTO<?> createSuccess(Object o) {
		return new ResponseDTO<>(ResponseCode.OK, null, o);
	}
	
	public static ResponseDTO<?> createMsgError(String msg) {
		return new ResponseDTO<>(ResponseCode.ERROR, msg, null);
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
}
