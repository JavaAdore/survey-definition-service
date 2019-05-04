package com.survey.microservice.surveydefinitionservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.constant.ServiceConstant;
import com.survey.microservice.surveydefinitionservice.entity.SurveyEntity;

@Repository
public interface JpaSurveyDao extends JpaRepository<SurveyEntity, Long> {
	
 	@Query(value="select '"+ServiceConstant.SURVEY_CODE_PREFEX+"'|| nextval('survey_definition_service.SURVEY_CODE_SEQ')",nativeQuery = true)
	String getNextSurveyCode();

}
