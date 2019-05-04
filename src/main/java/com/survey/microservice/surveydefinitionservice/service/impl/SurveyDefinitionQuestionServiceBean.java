package com.survey.microservice.surveydefinitionservice.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionChoiceDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionDao;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionQuestionService;

import lombok.extern.java.Log;

@Log
@Service
public class SurveyDefinitionQuestionServiceBean implements SurveyDefinitionQuestionService {

 	
	private final JpaSurveyDefinitionDao jpaSurveyDefinitionDao;
	private final JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao;
	private final JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao;
	private final ModelMapper modelMapper;

	public SurveyDefinitionQuestionServiceBean(JpaSurveyDefinitionDao jpaSurveyDefinitionDao,
			JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao,
			JpaSurveyDefinitionQuestionChoiceDao jpaSurveyDefinitionQuestionChoiceDao,
			ModelMapper modelMapper) {
		super();
		this.jpaSurveyDefinitionDao=jpaSurveyDefinitionDao;
 		this.jpaSurveyDefinitionQuestionDao=jpaSurveyDefinitionQuestionDao;
 		this.jpaSurveyDefinitionQuestionChoiceDao=jpaSurveyDefinitionQuestionChoiceDao;
		this.modelMapper = modelMapper;
	}
 

	@Override
	public Long createNewSurveyDefinitionQuestion(Long surveyDefinitionId,
			SurveyDefinitionQuestion surveyDefinitionQuestion) {
		// no way to come with null value
		SurveyDefinitionEntity surveyDefintionEntity = jpaSurveyDefinitionDao.findById(surveyDefinitionId).get();
		SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity =  createNewSurveyDefinitionQuestion(surveyDefintionEntity, surveyDefinitionQuestion);
		return surveyDefinitionQuestionEntity.getId();
	}

	@Override
	public SurveyDefinitionQuestionEntity createNewSurveyDefinitionQuestion(SurveyDefinitionEntity surveyDefintionEntity,
			SurveyDefinitionQuestion surveyDefinitionQuestion) {
		SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity = modelMapper.map(surveyDefinitionQuestion, SurveyDefinitionQuestionEntity.class);
		surveyDefinitionQuestionEntity.setSurveyDefinition(surveyDefintionEntity);
		if(surveyDefinitionQuestionEntity.getCode() ==null) {
			surveyDefinitionQuestionEntity.setCode(jpaSurveyDefinitionQuestionDao.generateSurveyDefinitionCode());
		}
		
		surveyDefinitionQuestionEntity = jpaSurveyDefinitionQuestionDao.save(surveyDefinitionQuestionEntity);
		return surveyDefinitionQuestionEntity;
	}


	@Override
	public Optional<SurveyDefinitionQuestionEntity> findSurveyDefinitionQuestionById(Long surveyDefinitionQuestionId) {
		return jpaSurveyDefinitionQuestionDao.findById(surveyDefinitionQuestionId);
	}


	@Override
	public Long updateSurveyDefinitionQuestion(Long surveyDefinitionQuestionId,
			SurveyDefinitionQuestion surveyDefinitionQuestion) {
		SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity = findSurveyDefinitionQuestionById(surveyDefinitionQuestionId).get();
		surveyDefinitionQuestionEntity.setTitle(surveyDefinitionQuestion.getTitle());
		surveyDefinitionQuestionEntity = jpaSurveyDefinitionQuestionDao.save(surveyDefinitionQuestionEntity);
		return surveyDefinitionQuestionEntity.getId();
	}

	@Transactional
	@Override
	public Long deleteSurveyDefinitionQuestion(Long surveyDefinitionQuestionId) {
		jpaSurveyDefinitionQuestionChoiceDao.deleteSurveyDefinitionQuestionChoicesBySurveyDefinitionQuestionId(surveyDefinitionQuestionId);
		jpaSurveyDefinitionQuestionDao.deleteById(surveyDefinitionQuestionId);
		return surveyDefinitionQuestionId;
	}

	 

}
