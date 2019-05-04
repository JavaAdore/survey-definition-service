package com.survey.microservice.surveydefinitionservice.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionChoiceEntity;

@Repository
public interface JpaSurveyDefinitionQuestionChoiceDao extends JpaRepository<SurveyDefinitionQuestionChoiceEntity, Long> {

	
	@Modifying
	@Query("delete from "  + SurveyDefinitionQuestionChoiceEntity.ENTITY_NAME + " sdqc where sdqc.surveyDefinitionQuestion.id = :#{#surveyDefinitionQuestionId}"  )
	public void deleteSurveyDefinitionQuestionChoicesBySurveyDefinitionQuestionId(@Param("surveyDefinitionQuestionId") Long surveyDefinitionQuestionId);
}
