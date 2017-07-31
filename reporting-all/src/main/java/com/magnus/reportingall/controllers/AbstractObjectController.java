package com.magnus.reportingall.controllers;

import com.magnus.reportingall.domain.system.ResponseCode;
import com.magnus.reportingall.domain.system.ResponseDTO;

public abstract class AbstractObjectController {

	protected static final ResponseDTO<String> OK_RESPONSE;
	
	protected static final ResponseDTO<String> UNEXPECTED_ERROR_RESPONSE;
	
	static {
		OK_RESPONSE = new ResponseDTO<>(ResponseCode.OK, null, null);
		UNEXPECTED_ERROR_RESPONSE = new ResponseDTO<>(ResponseCode.ERROR, "unexpected_error", null);
	}
	
	protected ResponseDTO<?> createResponseForObject(Object o) {
		return new ResponseDTO<>(ResponseCode.OK, null, o);
	}
	
}
