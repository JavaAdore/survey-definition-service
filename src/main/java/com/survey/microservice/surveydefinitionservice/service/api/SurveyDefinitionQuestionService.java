package com.survey.microservice.surveydefinitionservice.service.api;

import java.util.Optional;

import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;

public interface SurveyDefinitionQuestionService {

 
	Long createNewSurveyDefinitionQuestion(Long surveyDefinitionId, SurveyDefinitionQuestion surveyDefinitionQuestion);

	Optional<SurveyDefinitionQuestionEntity> findSurveyDefinitionQuestionById(Long surveyDefinitionQuestionId);

	Long updateSurveyDefinitionQuestion(Long surveyDefinitionQuestionId, SurveyDefinitionQuestion surveyDefinitionQuestion);

	Long deleteSurveyDefinitionQuestion(Long surveyDefinitionQuestionId);

	SurveyDefinitionQuestionEntity createNewSurveyDefinitionQuestion(SurveyDefinitionEntity surveyDefintionEntity,
			SurveyDefinitionQuestion surveyDefinitionQuestion);
 
 
	 
	 
	
	
}
