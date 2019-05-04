package com.survey.microservice.surveydefinitionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SurveyDefinitionQuestion {

	public SurveyDefinitionQuestion(String title) {
		super();
		this.title = title;
	}

	private String title;
}
