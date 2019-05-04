package com.survey.microservice.surveydefinitionservice.service.api;

import java.util.List;
import java.util.Optional;

import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionMetaData;

public interface SurveyDefinitionService {

	Long createNewSurveyDefinition(SurveyDefinition surveyDefinition);

	Long updateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition);

	Optional<SurveyDefinitionEntity> findSurveyDefinitionById(Long surveyDefinitionId);

	SurveyBasicInfo publishSurveyDefinition(Long surveyDefinitionId);

	SurveyDefinitionEntity createDraftSurveyDefinitionOfSurvey(SurveyEntity surveyEntity);

	Optional<SurveyDefinitionEntity> findDraftSurveyDefinitionForSurvey(Long surveyId);

	void deleteAllData();

	List<SurveyDefinitionEntity> getAllSurveyDefinitions();

	SurveyDefinitionMetaData getSurveyDefinitionMetaData(Long surveyDefinitionId);
 
	
	 
	 
	
	
}
