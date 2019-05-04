package com.survey.microservice.surveydefinitionservice.service.api;

import java.util.List;

import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionEntity;

public interface SurveyQuestionsService {

	List<SurveyQuestionEntity> getLatestSurveyQuestions(Long SurveyId);
	
	List<SurveyQuestionEntity> getSurveyQuestions(Long SurveyId,Long actionId);


}
