package com.survey.microservice.surveydefinitionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SurveyDefinition {

	private String title;

	public SurveyDefinition(String title) {
		super();
		this.title = title;
	}
	
}
