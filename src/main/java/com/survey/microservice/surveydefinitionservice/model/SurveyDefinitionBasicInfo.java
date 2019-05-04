package com.survey.microservice.surveydefinitionservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
public class SurveyDefinitionBasicInfo extends SurveyDefinition {
	
	private Long id;
	private Long surveyId;
	private Long surveyVersion;
	private SurveyDefinitionStatus surveyDefinitionStatus;
	public SurveyDefinitionBasicInfo(String title, Long id, Long surveyId, Long surveyVersion,
			SurveyDefinitionStatus surveyDefinitionStatus) {
		super(title);
		this.id = id;
		this.surveyId = surveyId;
		this.surveyVersion = surveyVersion;
		this.surveyDefinitionStatus = surveyDefinitionStatus;
	}
	 
	
	
}
