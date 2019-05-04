package com.survey.microservice.surveydefinitionservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class SurveyMetaData extends SurveyBasicInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 
	private List<SurveyQuestionMetaData> surveyQuestionMetaDataList;
	
	public void addSurveyQuestionMetaData(SurveyQuestionMetaData surveyQuestionMetaData)
	{
		if(null == surveyQuestionMetaDataList)
		{
			surveyQuestionMetaDataList = new ArrayList<>();
		}
		
		surveyQuestionMetaDataList.add(surveyQuestionMetaData);
	}
	
	
}
