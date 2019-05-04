package com.survey.microservice.surveydefinitionservice.service.api;

import java.util.List;
import java.util.Optional;

import com.survey.microservice.surveydefinitionservice.entity.SurveyEntity;

public interface SurveyService {

	Optional<SurveyEntity> findSurveyById(Long surveyId);

	Boolean isExist(Long surveyId);

	List<SurveyEntity> getAllSurveyes();

 
}
