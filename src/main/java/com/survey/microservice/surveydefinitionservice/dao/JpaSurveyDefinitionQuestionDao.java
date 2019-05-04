package com.survey.microservice.surveydefinitionservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.constant.ServiceConstant;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;

@Repository
public interface JpaSurveyDefinitionQuestionDao extends JpaRepository<SurveyDefinitionQuestionEntity, Long> {

	
	@Query( value = "select '"+ServiceConstant.QUSTION_CODE_PREFEX+"'|| nextval('survey_definition_service.SURVEY_QUESTION_CODE_SEQ')",nativeQuery = true)
	public String generateSurveyDefinitionCode();
}
