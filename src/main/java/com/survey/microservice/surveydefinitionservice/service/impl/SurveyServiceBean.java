package com.survey.microservice.surveydefinitionservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionChoiceDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionDao;
import com.survey.microservice.surveydefinitionservice.entity.SurveyEntity;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyService;

import lombok.extern.java.Log;

@Log
@Service
public class SurveyServiceBean implements SurveyService {

 	
 	private final JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao;
	private final JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao;
	private final JpaSurveyDao jpaSurveyDao;
	private final ModelMapper modelMapper;

	public SurveyServiceBean(
			JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao,
			JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao,
			JpaSurveyDao jpaSurveyDao,
			ModelMapper modelMapper) {
		super();
  		this.jpaSurveyDefinitionQuestionDao=jpaSurveyDefinitionQuestionDao;
 		this.jpaSurveyDefinitionQuestionChoiceDao=jpaSurveyDefinitionQuestionChoiceDao;
 		this.jpaSurveyDao=jpaSurveyDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<SurveyEntity> findSurveyById(Long surveyId) {
		return jpaSurveyDao.findById(surveyId);
	}

	@Override
	public Boolean isExist(Long surveyId) {
		return jpaSurveyDao.existsById(surveyId);
	}

	@Override
	public List<SurveyEntity> getAllSurveyes() {
		return jpaSurveyDao.findAll();
	}

	 

	 
	 

}
