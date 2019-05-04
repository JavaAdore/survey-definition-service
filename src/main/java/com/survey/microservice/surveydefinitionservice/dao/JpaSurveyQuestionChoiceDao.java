package com.survey.microservice.surveydefinitionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionChoiceEntity;

@Repository
public interface JpaSurveyQuestionChoiceDao extends JpaRepository<SurveyQuestionChoiceEntity, Long> {


	  
	  
 
}
