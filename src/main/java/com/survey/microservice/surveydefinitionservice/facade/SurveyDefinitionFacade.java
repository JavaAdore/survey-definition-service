package com.survey.microservice.surveydefinitionservice.facade;

import java.util.List;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.surveydefinitionservice.model.SurveyBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;
import com.survey.microservice.surveydefinitionservice.model.SurveyMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyQuestionMetaData;

public interface SurveyDefinitionFacade {

	// maintain survey definition
	Long createNewSurveyDefinition(SurveyDefinition surveyDefinition) throws ServiceException;
	Long updateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition)	 throws ServiceException;
	SurveyBasicInfo publishSurveyDefinition(Long surveyDefinitionId) throws ServiceException;;
	List<SurveyDefinitionBasicInfo> getAllSurveyDefintionsBasicInfo();
	SurveyDefinitionMetaData getSurveyDefinitionMetaData(Long surveyDefinitionId) throws ServiceException;
	
	// maintain survey definition question
	Long createNewSurveyDefinitionQuestion(Long surveyDefinitionId, SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException;
	Long updateSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId,SurveyDefinitionQuestion surveyDefinitionQuestion)  throws ServiceException;
	Long deleteSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId) throws ServiceException;
	
	
	
	
	// maintain survey definition question choice
	Long createNewSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException;
	Long updateSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId, Long surveyDefinitionQuestionChoiceId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException;
	Long deleteSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,Long surveyDefinitionQuestionChoiceId)  throws ServiceException;
	
	// to create a draf version of survey to modify on then publish and then used as a latest version
	Long editSurvey(Long surveyId) throws ServiceException;

	
	SurveyMetaData getSurveyMetaData(Long surveyId) throws ServiceException;
	SurveyMetaData getSurveyMetaData(Long surveyId, Long surveyVersion) throws ServiceException;
	List<SurveyQuestionMetaData> getSurveyQuestions(Long surveyId) throws ServiceException;
	List<SurveyQuestionMetaData> getSurveyQuestions(Long surveyId, Long surveyVersion) throws ServiceException;
	
	List<SurveyBasicInfo> getAllSurveys();

	void deleteAllData();
	
	
 
	
  
	 
}
