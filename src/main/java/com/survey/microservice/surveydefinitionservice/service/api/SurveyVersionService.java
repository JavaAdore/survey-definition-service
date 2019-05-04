package com.survey.microservice.surveydefinitionservice.service.api;

import java.util.Optional;

import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;

public interface SurveyVersionService {

	Optional<SurveyVersionEntity> findLatestRecordOfSurveyVersion(Long surveyId);

	Optional<SurveyVersionEntity> findSurveyVersionRecordBy(Long surveyId, Long surveyVersion);

 
}
