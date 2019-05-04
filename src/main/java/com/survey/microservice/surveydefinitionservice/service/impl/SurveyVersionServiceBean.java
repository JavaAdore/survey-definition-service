package com.survey.microservice.surveydefinitionservice.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyVersionDao;
import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyVersionService;

import lombok.extern.java.Log;

@Log
@Service
public class SurveyVersionServiceBean implements SurveyVersionService {

 	 
	 private final JpaSurveyVersionDao jpaSurveyVersionDao;
	 
	 public SurveyVersionServiceBean(JpaSurveyVersionDao jpaSurveyVersionDao)
	 {
		 this.jpaSurveyVersionDao=jpaSurveyVersionDao;
	 }
	 

	 @Override
	 public Optional<SurveyVersionEntity> findLatestRecordOfSurveyVersion( Long surveyId)
	 {
		Pageable pageable = PageRequest.of(0, 1, Sort.Direction.DESC, "createDateTime");
		 Page<SurveyVersionEntity> surveyVersionEntityPage = jpaSurveyVersionDao
					.findLatestRecordOfSurveyVersaion(surveyId, pageable);
		 SurveyVersionEntity surveyVersionEntity = null;	
		 if (!surveyVersionEntityPage.isEmpty()) {
			 surveyVersionEntity = surveyVersionEntityPage.getContent().get(0);
			}
		 
		 return Optional.ofNullable(surveyVersionEntity);
	 }


	@Override
	public Optional<SurveyVersionEntity> findSurveyVersionRecordBy(Long surveyId, Long surveyVersion) {
		return jpaSurveyVersionDao.findSurveyVersionRecordBy( surveyId,  surveyVersion);
	}

	 
	
	 
	 

}
