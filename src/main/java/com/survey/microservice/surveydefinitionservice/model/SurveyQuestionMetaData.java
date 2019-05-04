package com.survey.microservice.surveydefinitionservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SurveyQuestionMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String title;
	
	
	private String code;
	
	private List<SurveyQuestionChoiceMetaData> surveyQuestionChoiceMetaDataList;
	
	
	public void addSurveyQuestionChoiceMetaData(SurveyQuestionChoiceMetaData surveyQuestionChoiceMetaData)
	{
		if(surveyQuestionChoiceMetaDataList == null)
		{
			surveyQuestionChoiceMetaDataList =  new ArrayList<>();
		}
		surveyQuestionChoiceMetaDataList.add(surveyQuestionChoiceMetaData);
		
	}
}
