package com.survey.microservice.surveydefinitionservice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SurveyDefinitionMetaData extends SurveyDefinitionBasicInfo {
	  
	private List<SurveyDefinitionQuestionInfo> surveyDefinitionsQuestions;
	
	
	public void addQuestionInfo(SurveyDefinitionQuestionInfo surveyDefinitionQuestionInfo)
	{
		if(null == surveyDefinitionsQuestions)
		{
			surveyDefinitionsQuestions = new ArrayList<>();
		}
		surveyDefinitionsQuestions.add(surveyDefinitionQuestionInfo);
	}
	
}
