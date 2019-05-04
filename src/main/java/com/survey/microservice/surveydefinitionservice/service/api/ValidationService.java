package com.survey.microservice.surveydefinitionservice.service.api;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;

public interface ValidationService {

	void validateCreateNewSurveyDefinition(SurveyDefinition surveyDefinition) throws ServiceException;

	void validateCreateNewSurveyDefinitionQuestion(Long surveyDefinitionId, SurveyDefinitionQuestion surveyDefinitionQuestion)  throws ServiceException;

	void validateUpdateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition) throws ServiceException;

	void validateUpdateSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId, SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException;

	void validateDeleteSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId)throws ServiceException;

	void validateCreateNewSurveyDefinitionQuestionChoice(Long surveyDefinitionId,Long surveyDefinitionQuestionId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException;

	void validateUpdateSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId, Long surveyDefinitionQuestionChoiceId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice)  throws ServiceException;

	void validateDeleteSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId, Long surveyDefinitionQuestionChoiceId) throws ServiceException;

	void validatePublishSurveyDefinition(Long surveyDefinitionId) throws ServiceException;

	void validateEditSurvey(Long surveyId) throws ServiceException;

 	void validateGetSurveyMetaData(Long surveyId)  throws ServiceException;

	SurveyVersionEntity validateSurveyVersion(Long surveyId, Long surveyVersion)  throws ServiceException;

	void validateGetSurveyQuestions(Long surveyId)   throws ServiceException;

	void validateGetSurveyDefinitionMetaData(Long surveyDefinitionId)   throws ServiceException;


  

	 
}
