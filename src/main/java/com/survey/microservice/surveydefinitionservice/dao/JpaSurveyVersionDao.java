package com.survey.microservice.surveydefinitionservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;

@Repository
public interface JpaSurveyVersionDao extends org.springframework.data.jpa.repository.JpaRepository<SurveyVersionEntity, Long> {

	@Query("select sve from "  + SurveyVersionEntity.ENTITY_NAME + " sve where sve.survey.id = :#{#surveyId}"  )
	Page<SurveyVersionEntity> findLatestRecordOfSurveyVersaion(@Param("surveyId")Long surveyId, Pageable pagable);

	@Query("select sve from "  + SurveyVersionEntity.ENTITY_NAME + " sve where sve.survey.id = :#{#surveyId} and sve.surveyVersion = :#{#surveyVersion}"  )
	Optional<SurveyVersionEntity> findSurveyVersionRecordBy(@Param("surveyId") Long surveyId,@Param("surveyVersion") Long surveyVersion);
	
	@Query("select sde from " + SurveyDefinitionEntity.ENTITY_NAME + " sde where sde.action.id = :#{#actionId} ")
	public Optional<SurveyDefinitionEntity> findByActionId(@Param("actionId") Long actionId);

}
