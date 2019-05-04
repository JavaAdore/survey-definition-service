package com.survey.microservice.surveydefinitionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.constant.ServiceConstant;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionStatus;

@Repository
public interface JpaSurveyDefinitionDao extends JpaRepository<SurveyDefinitionEntity, Long> {

 	@Query( value = "select '"+ServiceConstant.SURVEY_DEFINITION_CODE_PREFEX+"'|| nextval('survey_definition_service.SURVEY_DEFINITION_CODE_SEQ')",nativeQuery = true)
	public String generateSurveyDefinitionCode();

 	@Query("select sde from " + SurveyDefinitionEntity.ENTITY_NAME + " sde where sde.survey.id = :#{#surveyId} and sde.surveyDefinitionStatus = :#{#surveyDefinitionStatus} ")
	public List<SurveyDefinitionEntity> findSurveyDefinitionForSurvey(@Param("surveyId") Long surveyId, @Param("surveyDefinitionStatus") SurveyDefinitionStatus surveyDefinitionStatus);

 	@Query("select sde from " + SurveyDefinitionEntity.ENTITY_NAME + " sde  ")
	public List<SurveyDefinitionEntity> findAllSurveyDefinitions();

	
}
 