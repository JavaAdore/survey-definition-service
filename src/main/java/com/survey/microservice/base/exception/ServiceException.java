package com.survey.microservice.base.exception;

import com.survey.microservice.base.model.ErrorMessageCode;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorMessageCode errorMessageCode;
	private Object[] params;
	private Object content;

	public ServiceException(ErrorMessageCode errorMessageCode) {
		this(errorMessageCode, null, null);
	}

	public ServiceException(ErrorMessageCode errorMessageCode, Object[] params) {
		this(errorMessageCode, null, params);
	}

	public ServiceException(ErrorMessageCode errorMessageCode, Object content, Object[] params) {
		this.errorMessageCode = errorMessageCode;
		this.content = content;
		this.params = params;

	}

	public String getCode() {
		if (null != errorMessageCode) {
			return errorMessageCode.getCode();
		}
		return null;
	}

	public String getMessageKey() {
		if (null != errorMessageCode) {
			return errorMessageCode.getMessageKey();
		}
		return null;
	}
}
