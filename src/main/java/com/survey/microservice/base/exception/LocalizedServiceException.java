package com.survey.microservice.base.exception;

 
public class LocalizedServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String bundledMessage;
	
	 
	public LocalizedServiceException()
	{
 	}
	public LocalizedServiceException(String code, String bundledMessage) {
  		this.code = code;
		this.bundledMessage = bundledMessage;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBundledMessage() {
		return bundledMessage;
	}
	public void setBundledMessage(String bundledMessage) {
		this.bundledMessage = bundledMessage;
	}

}
