package com.survey.microservice.surveydefinitionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SurveyDefinitionQuestionChoiceInfo extends SurveyDefinitionQuestionChoice{

	private Long id;

	public SurveyDefinitionQuestionChoiceInfo(String title, Long id) {
		super(title);
		this.id = id;
	}
	
	

	 
}
