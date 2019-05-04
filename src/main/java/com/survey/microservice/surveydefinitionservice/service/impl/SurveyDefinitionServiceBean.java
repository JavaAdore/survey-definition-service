package com.survey.microservice.surveydefinitionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.survey.microservice.surveydefinitionservice.dao.JpaActionDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionChoiceDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionQuestionDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyQuestionChoiceDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyQuestionDao;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyVersionDao;
import com.survey.microservice.surveydefinitionservice.entity.ActionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionChoiceEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;
import com.survey.microservice.surveydefinitionservice.model.ActionType;
import com.survey.microservice.surveydefinitionservice.model.SurveyBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoiceInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionStatus;
import com.survey.microservice.surveydefinitionservice.model.SurveyStatus;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionService;

import lombok.extern.java.Log;

@Log
@Service
public class SurveyDefinitionServiceBean implements SurveyDefinitionService {

	private final JpaSurveyDefinitionDao jpaSurveyDefinitionDao;
	private final JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao;
	private final JpaSurveyDefinitionQuestionChoiceDao   jpaSurveyDefinitionQuestionChoiceDao;
	
	
	private final JpaSurveyDao jpaSurveyDao;
	private final JpaSurveyQuestionDao jpaSurveyQuestionDao;
	private final JpaSurveyQuestionChoiceDao jpaSurveyQuestionChoiceDao;

	
	private final JpaSurveyVersionDao jpaSurveyVersionDao;
	private final JpaActionDao jpaActionDao;

	private final ModelMapper modelMapper;

	public SurveyDefinitionServiceBean(JpaSurveyDefinitionDao jpaSurveyDefinitionDao, JpaSurveyDao jpaSurveyDao,
			JpaSurveyQuestionDao jpaSurveyQuestionDao, JpaActionDao jpaActionDao,
			JpaSurveyQuestionChoiceDao jpaSurveyQuestionChoiceDao,
			JpaSurveyVersionDao jpaSurveyVersionDao,
			JpaSurveyDefinitionQuestionDao jpaSurveyDefinitionQuestionDao,
			JpaSurveyDefinitionQuestionChoiceDao   jpaSurveyDefinitionQuestionChoiceDao
			,ModelMapper modelMapper) {
		super();
		this.jpaSurveyDefinitionDao = jpaSurveyDefinitionDao;
		this.jpaSurveyDao = jpaSurveyDao;
		this.jpaSurveyQuestionDao = jpaSurveyQuestionDao;
		this.jpaActionDao = jpaActionDao;
		this.jpaSurveyVersionDao = jpaSurveyVersionDao;
		this.jpaSurveyQuestionChoiceDao=jpaSurveyQuestionChoiceDao;
		this.jpaSurveyDefinitionQuestionDao=jpaSurveyDefinitionQuestionDao;
		this.jpaSurveyDefinitionQuestionChoiceDao=jpaSurveyDefinitionQuestionChoiceDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Long createNewSurveyDefinition(SurveyDefinition surveyDefinition) {
		ActionEntity actionEntity = createNewAction(ActionType.NEW);
		SurveyDefinitionEntity surveyDefinitionEntity = createSurveyDefinition(null, null,actionEntity, surveyDefinition);
		return surveyDefinitionEntity.getId();
	}

	@Override
	public SurveyDefinitionEntity createDraftSurveyDefinitionOfSurvey(SurveyEntity surveyEntity) {

		Optional<SurveyVersionEntity>surveyVersionEntityOptional = findLatestRecordOfSurveyVersaion(surveyEntity.getId());		
		 ActionEntity previousAction = null;
		 if(surveyVersionEntityOptional.isPresent())
		 {
			 previousAction = surveyVersionEntityOptional.get().getAction();
		 }
		ActionEntity actionEntity = createNewAction(ActionType.UPDATE);
		SurveyDefinition surveyDefinition = modelMapper.map(surveyEntity, SurveyDefinition.class);
		
		SurveyDefinitionEntity surveyDefinitionEntity = createSurveyDefinition(surveyEntity,previousAction, actionEntity,
				surveyDefinition);
		return surveyDefinitionEntity;
	}

	private SurveyDefinitionEntity createSurveyDefinition(SurveyEntity surveyEntity,ActionEntity previousAction, ActionEntity actionEntity,
			 SurveyDefinition surveyDefinition) {
		SurveyDefinitionEntity surveyDefinitionEntity = new SurveyDefinitionEntity();
		surveyDefinitionEntity.setTitle(surveyDefinition.getTitle());
		surveyDefinitionEntity.setAction(actionEntity);
		surveyDefinitionEntity.setSurveyDefinitionStatus(SurveyDefinitionStatus.DRAFT);
		surveyDefinitionEntity.setSurvey(surveyEntity);
		surveyDefinitionEntity.setPreviousAction(previousAction);
		surveyDefinitionEntity.setCode(jpaSurveyDefinitionDao.generateSurveyDefinitionCode());
		surveyDefinitionEntity = jpaSurveyDefinitionDao.save(surveyDefinitionEntity);
		return surveyDefinitionEntity;
	}

	private ActionEntity createNewAction(ActionType new1) {
		ActionEntity actionEntity = new ActionEntity();
		actionEntity.setActionDate(LocalDateTime.now());
		actionEntity.setActionType(ActionType.NEW);
		actionEntity = jpaActionDao.save(actionEntity);
		return actionEntity;
	}

	@Override
	public Optional<SurveyDefinitionEntity> findSurveyDefinitionById(Long surveyDefinitionId) {
		return jpaSurveyDefinitionDao.findById(surveyDefinitionId);
	}

	@Override
	public Long updateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition) {

		SurveyDefinitionEntity surveyDefintionEntity = jpaSurveyDefinitionDao.findById(surveyDefinitionId).get();
		surveyDefintionEntity.setTitle(surveyDefinition.getTitle());
		surveyDefintionEntity = jpaSurveyDefinitionDao.save(surveyDefintionEntity);
		return surveyDefintionEntity.getId();
	}

	@Transactional
	@Override
	public SurveyBasicInfo publishSurveyDefinition(Long surveyDefinitionId) {

		SurveyDefinitionEntity surveyDefintionEntity = jpaSurveyDefinitionDao.findById(surveyDefinitionId).get();
		ActionEntity currentAction = surveyDefintionEntity.getAction();
		final SurveyEntity surveyEntity = loadOrCreateSurveyOfSurveyDefinition(surveyDefintionEntity);
		markQuestionsOfSurveyForPreviousVersionAsClosed(surveyDefintionEntity.getSurvey(), currentAction);
		surveyDefintionEntity.getSurveyDefinitionQuestions().forEach(questionDefinition -> {
			SurveyQuestionEntity surveyQuestionEntity = new SurveyQuestionEntity();
			surveyQuestionEntity.setTitle(questionDefinition.getTitle());
			surveyQuestionEntity.setCode(questionDefinition.getCode());
			surveyQuestionEntity.setSurvey(surveyEntity);
			surveyQuestionEntity.setAction(currentAction);
			final SurveyQuestionEntity presistedSurveyQuestionEntity = jpaSurveyQuestionDao.save(surveyQuestionEntity);

			questionDefinition.getSurveyDefinitionQuestionChoices().stream().forEach(choice -> {
				SurveyQuestionChoiceEntity surveyQuestionChoiceEntity = new SurveyQuestionChoiceEntity();
				surveyQuestionChoiceEntity.setTitle(choice.getTitle());
				surveyQuestionChoiceEntity.setSurveyQuestion(presistedSurveyQuestionEntity);
				jpaSurveyQuestionChoiceDao.save(surveyQuestionChoiceEntity);
			});

		});

		SurveyVersionEntity surveyVersionEntity = incrementSurveyVersion(surveyEntity, surveyDefintionEntity,
				currentAction);

		markSurveyDefinitionAsCompleted(surveyEntity, surveyDefintionEntity);
		
		
		return prepareSurveyBasicInfo(surveyVersionEntity);
	}

	private SurveyBasicInfo prepareSurveyBasicInfo(SurveyVersionEntity surveyVersionEntity) {
		SurveyEntity surveyEntity = surveyVersionEntity.getSurvey();
		SurveyBasicInfo surveyBasicInfo = new SurveyBasicInfo();
		surveyBasicInfo.setSurveyId(surveyEntity.getId());
		surveyBasicInfo.setSurveyCode(surveyEntity.getCode());
		surveyBasicInfo.setSurveyVersion(surveyVersionEntity.getSurveyVersion());
		surveyBasicInfo.setTitle(surveyEntity.getTitle());
		return surveyBasicInfo;
	}

	private void markSurveyDefinitionAsCompleted(SurveyEntity surveyEntity,
			SurveyDefinitionEntity surveyDefintionEntity) {
		// if it's not already associated
		if (surveyDefintionEntity.getSurvey() == null) {
			// associate it
			surveyDefintionEntity.setSurvey(surveyEntity);
			// save to database
		}

		surveyDefintionEntity.setSurveyDefinitionStatus(SurveyDefinitionStatus.COMPLETED);
		jpaSurveyDefinitionDao.save(surveyDefintionEntity);

	}

	private SurveyVersionEntity incrementSurveyVersion(SurveyEntity surveyEntity,
			SurveyDefinitionEntity surveyDefintionEntity, ActionEntity currentAction) {

 		// assuming adding first version
		Long surveyVersion = 0l;
		
		Optional<SurveyVersionEntity> surveyVersionEntityOptional = findLatestRecordOfSurveyVersaion(surveyEntity.getId());
				
		if (surveyVersionEntityOptional.isPresent()) {
			// i'm selecting only one record
			surveyVersion = surveyVersionEntityOptional.get().getSurveyVersion();
		}

		SurveyVersionEntity surveyVersionEntity = new SurveyVersionEntity();
		surveyVersionEntity.setAction(currentAction);
		surveyVersionEntity.setSurvey(surveyEntity);
		// increment version
		surveyVersionEntity.setSurveyVersion(++surveyVersion);
		surveyVersionEntity = jpaSurveyVersionDao.save(surveyVersionEntity);

		surveyEntity.setSurveyVersion(surveyVersionEntity.getSurveyVersion());
		jpaSurveyDao.save(surveyEntity);
		return surveyVersionEntity;
	}

	private Optional<SurveyVersionEntity> findLatestRecordOfSurveyVersaion(Long id) {
		Pageable pageable = PageRequest.of(0, 1, Sort.Direction.DESC, "createDateTime");
		Page<SurveyVersionEntity> surveyVersionEntityPage = jpaSurveyVersionDao
				.findLatestRecordOfSurveyVersaion(id, pageable);
		SurveyVersionEntity surveyVersionEntity = null;
		if(surveyVersionEntityPage.hasContent())
		{
			surveyVersionEntity = surveyVersionEntityPage.getContent().get(0);
		}
		
		return Optional.ofNullable(surveyVersionEntity);
	}

	private void markQuestionsOfSurveyForPreviousVersionAsClosed(SurveyEntity surveyEntity, ActionEntity action) {
		if (null != surveyEntity && null != surveyEntity.getId()) {
			jpaSurveyQuestionDao.markQuestionsOfSurveyForPreviousVersionAsClosed(surveyEntity.getId(), action);
		}

	}

	private SurveyEntity loadOrCreateSurveyOfSurveyDefinition(SurveyDefinitionEntity surveyDefintionEntity) {

		SurveyEntity surveyEntity = surveyDefintionEntity.getSurvey();
		if (surveyEntity != null) {
			return surveyEntity;
		}
		surveyEntity = new SurveyEntity();
		surveyEntity.setTitle(surveyDefintionEntity.getTitle());
		surveyEntity.setCode(jpaSurveyDao.getNextSurveyCode());
		surveyEntity.setSurveyStatus(SurveyStatus.ACTIVE);
		surveyEntity = jpaSurveyDao.save(surveyEntity);
		return surveyEntity;
	}

	@Override
	public Optional<SurveyDefinitionEntity> findDraftSurveyDefinitionForSurvey(Long surveyId) {
		
	List<SurveyDefinitionEntity> surveyDefinitionEntities = jpaSurveyDefinitionDao.findSurveyDefinitionForSurvey(surveyId, SurveyDefinitionStatus.DRAFT);
	SurveyDefinitionEntity surveyDefinitionEntity=null;
	// if there is a draft survey definition .. return it 
	if(!surveyDefinitionEntities.isEmpty())
	{
		// as per design it will be at most only one draft survey definition for particular survey at time
		  surveyDefinitionEntity =surveyDefinitionEntities.get(0);
	}
	return Optional.ofNullable(surveyDefinitionEntity);

	}

	@Override
	public void deleteAllData() {
		
		jpaSurveyVersionDao.deleteAllInBatch();
		
		jpaSurveyDefinitionQuestionChoiceDao.deleteAllInBatch();
		jpaSurveyDefinitionQuestionDao.deleteAllInBatch();
		jpaSurveyDefinitionDao.deleteAllInBatch();
		
		jpaSurveyQuestionChoiceDao.deleteAllInBatch();
		jpaSurveyQuestionDao.deleteAllInBatch();
		jpaSurveyDao.deleteAllInBatch();
		
		jpaActionDao.deleteAllInBatch();
 	
	}

	@Override
	public List<SurveyDefinitionEntity> getAllSurveyDefinitions() {
		return jpaSurveyDefinitionDao.findAllSurveyDefinitions();
	}

	@Override
	public SurveyDefinitionMetaData getSurveyDefinitionMetaData(Long surveyDefinitionId) {
		// i'm sure their is a survey definition with such id, it has been validation in validation layer
		SurveyDefinitionEntity surveyDefinitionEntity = jpaSurveyDefinitionDao.findById(surveyDefinitionId).get();
		SurveyDefinitionMetaData surveyDefinitionMetaData = populateSurveyDefinitionMetaDataSurveyDefinitionInfo(surveyDefinitionEntity);
		surveyDefinitionEntity.getSurveyDefinitionQuestions().forEach(q->
		{
			final SurveyDefinitionQuestionInfo surveyDefinitionQuestionInfo = modelMapper.map(q, SurveyDefinitionQuestionInfo.class);
			// choices are implicitly mapped with questions
			surveyDefinitionMetaData.addQuestionInfo(surveyDefinitionQuestionInfo);
		}); 
		return surveyDefinitionMetaData;
	}

	private SurveyDefinitionMetaData populateSurveyDefinitionMetaDataSurveyDefinitionInfo(
			SurveyDefinitionEntity surveyDefinitionEntity) {
		ActionEntity actionEntity = surveyDefinitionEntity.getPreviousAction();
		SurveyDefinitionMetaData surveyDefinitionMetaData = modelMapper.map(surveyDefinitionEntity, SurveyDefinitionMetaData.class);
		surveyDefinitionMetaData.setSurveyDefinitionStatus(surveyDefinitionEntity.getSurveyDefinitionStatus());
		// if survey definition is update for particular survey
		if(null != surveyDefinitionEntity.getSurvey())
		{
			// set survey id and survey version at time of survey definition
			surveyDefinitionMetaData.setSurveyId(surveyDefinitionEntity.getSurvey().getId());
			Optional<SurveyVersionEntity> surveyVersionEntityOptional = jpaSurveyVersionDao.findById(actionEntity.getId());
			if(surveyVersionEntityOptional.isPresent()) {
				SurveyVersionEntity  surveyVersionEntity =	surveyVersionEntityOptional.get();
				if(surveyVersionEntity.getSurveyVersion() >1l)
				{
					Long surveyPreviousVersion=surveyVersionEntity.getSurveyVersion()-1;
					surveyDefinitionMetaData.setSurveyVersion(surveyPreviousVersion);
				}
			}
			
		}
		return surveyDefinitionMetaData;
	}

}
