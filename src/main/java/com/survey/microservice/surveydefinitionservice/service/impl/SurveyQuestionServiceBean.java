package com.survey.microservice.surveydefinitionservice.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionChoiceDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyQuestionDao;
import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionEntity;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyQuestionsService;

import lombok.extern.java.Log;

@Log
@Service
public class SurveyQuestionServiceBean implements SurveyQuestionsService {

 	
 	private final JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao;
	private final JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao;
	private final JpaSurveyDao jpaSurveyDao;
	private final JpaSurveyQuestionDao jpaSurveyQuestionDao;
	private final ModelMapper modelMapper;

	public SurveyQuestionServiceBean(
			JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao,
			JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao,
			JpaSurveyDao jpaSurveyDao,JpaSurveyQuestionDao jpaSurveyQuestionDao,
			ModelMapper modelMapper) {
		super();
  		this.jpaSurveyDefinitionQuestionDao=jpaSurveyDefinitionQuestionDao;
 		this.jpaSurveyDefinitionQuestionChoiceDao=jpaSurveyDefinitionQuestionChoiceDao;
 		this.jpaSurveyDao=jpaSurveyDao;
 		this.jpaSurveyQuestionDao=jpaSurveyQuestionDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<SurveyQuestionEntity> getLatestSurveyQuestions(Long SurveyId) {
 		return jpaSurveyQuestionDao.getLatestSurveyQuestions(SurveyId);
	}

	@Override
	public List<SurveyQuestionEntity> getSurveyQuestions(Long SurveyId, Long actionId) {
		return jpaSurveyQuestionDao.getSurveyQuestions( SurveyId,  actionId);
	}

	 

	 

	 
	 

}
