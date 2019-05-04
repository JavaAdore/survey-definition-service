package com.survey.microservice.surveydefinitionservice.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionChoiceDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionDao;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionChoiceEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionQuestionChoiceService;

import lombok.extern.java.Log;

@Log
@Service
public class SurveyDefinitionQuestionChoiceServiceBean implements SurveyDefinitionQuestionChoiceService {

 	
 	private final JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao;
	private final JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao;
	private final ModelMapper modelMapper;

	public SurveyDefinitionQuestionChoiceServiceBean(
			JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao,
			JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao,
			ModelMapper modelMapper) {
		super();
  		this.jpaSurveyDefinitionQuestionDao=jpaSurveyDefinitionQuestionDao;
 		this.jpaSurveyDefinitionQuestionChoiceDao=jpaSurveyDefinitionQuestionChoiceDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Long createNewSurveyDefinitionQuestionChoice(Long surveyDefinitionQuestionId,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) {
		
		SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity = jpaSurveyDefinitionQuestionDao.findById(surveyDefinitionQuestionId).get();
		
		SurveyDefinitionQuestionChoiceEntity  surveyDefinitionQuestionChoiceEntity = 	createNewSurveyDefinitionQuestionChoice(surveyDefinitionQuestionEntity , surveyDefinitionQuestionChoice);
		
		return surveyDefinitionQuestionChoiceEntity.getId();
	}

	@Override
	public SurveyDefinitionQuestionChoiceEntity createNewSurveyDefinitionQuestionChoice(
			SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) {
		
		SurveyDefinitionQuestionChoiceEntity surveyDefinitionQuestionChoiceEntity = modelMapper.map(surveyDefinitionQuestionChoice, SurveyDefinitionQuestionChoiceEntity.class);
		surveyDefinitionQuestionChoiceEntity.setSurveyDefinitionQuestion(surveyDefinitionQuestionEntity);
		
		surveyDefinitionQuestionChoiceEntity = jpaSurveyDefinitionQuestionChoiceDao.save(surveyDefinitionQuestionChoiceEntity);
		return surveyDefinitionQuestionChoiceEntity;
	}

	@Override
	public Optional<SurveyDefinitionQuestionChoiceEntity> findSurveyQuestionChoiceDefinitionById(
			Long surveyDefinitionQuestionChoiceId) {
		return jpaSurveyDefinitionQuestionChoiceDao.findById(surveyDefinitionQuestionChoiceId);
	}

	@Override
	public Long updateSurveyDefinitionQuestionChoice(Long surveyDefinitionQuestionChoiceId,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) {
		
		SurveyDefinitionQuestionChoiceEntity surveyDefinitionQuestionChoiceEntity = 	jpaSurveyDefinitionQuestionChoiceDao.findById(surveyDefinitionQuestionChoiceId).get();
		
		surveyDefinitionQuestionChoiceEntity.setTitle(surveyDefinitionQuestionChoice.getTitle());
		
		surveyDefinitionQuestionChoiceEntity = jpaSurveyDefinitionQuestionChoiceDao.save(surveyDefinitionQuestionChoiceEntity);
		
		return surveyDefinitionQuestionChoiceEntity.getId();
	}

	@Override
	public Long deleteSurveyDefinitionQuestionChoice(Long surveyDefinitionQuestionChoiceId) {
		 jpaSurveyDefinitionQuestionChoiceDao.deleteById(surveyDefinitionQuestionChoiceId);;
		 return surveyDefinitionQuestionChoiceId;
	}
 

	 
	 

}
