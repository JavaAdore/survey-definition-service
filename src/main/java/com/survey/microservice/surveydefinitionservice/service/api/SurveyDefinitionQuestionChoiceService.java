package com.survey.microservice.surveydefinitionservice.service.api;

import java.util.Optional;

import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionChoiceEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;

public interface SurveyDefinitionQuestionChoiceService {

	Long createNewSurveyDefinitionQuestionChoice(Long surveyDefinitionQuestionId,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice);

	Optional<SurveyDefinitionQuestionChoiceEntity> findSurveyQuestionChoiceDefinitionById(Long surveyDefinitionQuestionChoiceId);

	Long updateSurveyDefinitionQuestionChoice(Long surveyDefinitionQuestionChoiceId,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice);

	Long deleteSurveyDefinitionQuestionChoice(Long surveyDefinitionQuestionChoiceId);

	SurveyDefinitionQuestionChoiceEntity createNewSurveyDefinitionQuestionChoice(
			SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice);

 
 
 
	 
	 
	
	
}
