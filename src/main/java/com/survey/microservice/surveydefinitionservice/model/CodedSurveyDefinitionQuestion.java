package com.survey.microservice.surveydefinitionservice.model;

import lombok.Data;

@Data
public class CodedSurveyDefinitionQuestion extends  SurveyDefinitionQuestion {

	private String code;
}
