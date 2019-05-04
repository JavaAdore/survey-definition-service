package com.survey.microservice.surveydefinitionservice.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SurveyBasicInfo implements Serializable {
	
	private String title;
	
	private Long surveyId;
	
	private String surveyCode;
	
	private Long surveyVersion;
}
