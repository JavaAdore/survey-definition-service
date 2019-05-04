package com.survey.microservice.surveydefinitionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SurveyDefinitionQuestionChoice {


	private String title;

	public SurveyDefinitionQuestionChoice(String title) {
		super();
		this.title = title;
	}
 
}
