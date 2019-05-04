package com.survey.microservice.surveydefinitionservice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SurveyDefinitionQuestionInfo extends SurveyDefinitionQuestion {

	private Long id;
	
	private List<SurveyDefinitionQuestionChoiceInfo> choices;
	
	public SurveyDefinitionQuestionInfo(String title, Long id) {
		super(title);
		this.id = id;
	}
	
	public void add(SurveyDefinitionQuestionChoiceInfo surveyDefinitionQuestionChoiceInfo)
	{
		if(null == choices)
		{
			choices = new ArrayList<>();
		}
		choices.add(surveyDefinitionQuestionChoiceInfo);
	}
}
