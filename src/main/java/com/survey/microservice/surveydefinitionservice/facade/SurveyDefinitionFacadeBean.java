package com.survey.microservice.surveydefinitionservice.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.surveydefinitionservice.entity.ActionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;
import com.survey.microservice.surveydefinitionservice.model.CodedSurveyDefinitionQuestion;
import com.survey.microservice.surveydefinitionservice.model.SurveyBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;
import com.survey.microservice.surveydefinitionservice.model.SurveyMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyQuestionChoiceMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyQuestionMetaData;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionQuestionChoiceService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionQuestionService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyQuestionsService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyVersionService;
import com.survey.microservice.surveydefinitionservice.service.api.ValidationService;

@Service
public class SurveyDefinitionFacadeBean implements SurveyDefinitionFacade {

	private final ValidationService validationService;
	private final SurveyDefinitionService surveyDefinitionService;
	private final SurveyDefinitionQuestionService surveyDefinitionQuestionService;
	private final SurveyDefinitionQuestionChoiceService surveyDefinitionQuestionChoiceService;
	private final SurveyService surveyService;
	private final SurveyQuestionsService surveyQuestionService;
	private final SurveyVersionService surveyVersionService;
	private final ModelMapper modelMapper;

	public SurveyDefinitionFacadeBean(SurveyDefinitionService surveyDefinitionService,
			SurveyDefinitionQuestionService surveyDefinitionQuestionService, 
			SurveyDefinitionQuestionChoiceService surveyDefinitionQuestionChoiceService,
			SurveyService surveyService,SurveyQuestionsService surveyQuestionService,
			ModelMapper modelMapper,SurveyVersionService surveyVersionService,
			ValidationService validationService) {
		this.surveyDefinitionService = surveyDefinitionService;
		this.surveyDefinitionQuestionService = surveyDefinitionQuestionService;
		this.surveyDefinitionQuestionChoiceService=surveyDefinitionQuestionChoiceService;
		this.surveyService=surveyService;
		this.surveyQuestionService=surveyQuestionService;
		this.surveyVersionService=surveyVersionService;
		this.modelMapper=modelMapper;
		this.validationService = validationService;
	
		
	}

	@Override
	public Long createNewSurveyDefinition(SurveyDefinition surveyDefinition) throws ServiceException {

		validationService.validateCreateNewSurveyDefinition(surveyDefinition);

		return surveyDefinitionService.createNewSurveyDefinition(surveyDefinition);
	}

	@Override
	public Long createNewSurveyDefinitionQuestion(Long surveyDefinitionId,
			SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException {

		validationService.validateCreateNewSurveyDefinitionQuestion(surveyDefinitionId, surveyDefinitionQuestion);

		return surveyDefinitionQuestionService.createNewSurveyDefinitionQuestion(surveyDefinitionId,
				surveyDefinitionQuestion);

	}

	@Override
	public Long updateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition)
			throws ServiceException {
		
		validationService.validateUpdateNewSurveyDefinition(surveyDefinitionId, surveyDefinition);
		
		return surveyDefinitionService.updateNewSurveyDefinition(surveyDefinitionId, surveyDefinition);

	}

	@Override
	public Long updateSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException {
		
		validationService.validateUpdateSurveyDefinitionQuestion(surveyDefinitionId, surveyDefinitionQuestionId,surveyDefinitionQuestion);
				
		return surveyDefinitionQuestionService.updateSurveyDefinitionQuestion(surveyDefinitionQuestionId,surveyDefinitionQuestion);
	}

	
	@Override
	public Long deleteSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId) throws ServiceException {
		
		validationService.validateDeleteSurveyDefinitionQuestion(surveyDefinitionId,   surveyDefinitionQuestionId);
		
		return surveyDefinitionQuestionService.deleteSurveyDefinitionQuestion(surveyDefinitionQuestionId);
	}

	@Override
	public Long createNewSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException {
		
		validationService.validateCreateNewSurveyDefinitionQuestionChoice( surveyDefinitionId ,  surveyDefinitionQuestionId,surveyDefinitionQuestionChoice);
		
		return surveyDefinitionQuestionChoiceService.createNewSurveyDefinitionQuestionChoice(  surveyDefinitionQuestionId, surveyDefinitionQuestionChoice);
	}

	@Override
	public Long updateSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			Long surveyDefinitionQuestionChoiceId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice)
			throws ServiceException {
		
		validationService.validateUpdateSurveyDefinitionQuestionChoice(  surveyDefinitionId,   surveyDefinitionQuestionId, surveyDefinitionQuestionChoiceId,   surveyDefinitionQuestionChoice);
			
		return surveyDefinitionQuestionChoiceService.updateSurveyDefinitionQuestionChoice(surveyDefinitionQuestionChoiceId,   surveyDefinitionQuestionChoice);
		
		
	}

	@Override
	public Long deleteSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			Long surveyDefinitionQuestionChoiceId) throws ServiceException {
		
		validationService.validateDeleteSurveyDefinitionQuestionChoice( surveyDefinitionId,  surveyDefinitionQuestionId, surveyDefinitionQuestionChoiceId);
		
		return surveyDefinitionQuestionChoiceService.deleteSurveyDefinitionQuestionChoice(surveyDefinitionQuestionChoiceId);
	}

	@Override
	public SurveyBasicInfo publishSurveyDefinition(Long surveyDefinitionId) throws ServiceException {
		
		validationService.validatePublishSurveyDefinition(surveyDefinitionId);
		
		return surveyDefinitionService.publishSurveyDefinition(surveyDefinitionId);
	}

	@Transactional
	@Override
	public Long editSurvey(Long surveyId) throws ServiceException {
		
		validationService.validateEditSurvey(surveyId);
		
		SurveyEntity surveyEntity = surveyService.findSurveyById(surveyId).get();
		
		Optional<SurveyDefinitionEntity> surveyDefinitionEntity = surveyDefinitionService.findDraftSurveyDefinitionForSurvey(surveyId);
		if(surveyDefinitionEntity.isPresent())
		{
			return surveyDefinitionEntity.get().getId();
		}
		return createDraftSurveyDefinitionForSurvey(surveyEntity);
		
		
		 
	}

	private Long createDraftSurveyDefinitionForSurvey(SurveyEntity surveyEntity) {
		final SurveyDefinitionEntity surveyDefinitionEntity = surveyDefinitionService.createDraftSurveyDefinitionOfSurvey(surveyEntity);
		
		final List<SurveyQuestionEntity> surveyQuestions =surveyQuestionService.getLatestSurveyQuestions(surveyEntity.getId());
		surveyQuestions.stream().forEach(sq->
		{
			CodedSurveyDefinitionQuestion surveyDefinitionQuestion = modelMapper.map(sq, CodedSurveyDefinitionQuestion.class);
			
			final SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity = surveyDefinitionQuestionService.createNewSurveyDefinitionQuestion(surveyDefinitionEntity, surveyDefinitionQuestion);
				sq.getSurveyQuestionChoices().stream().forEach(sqc ->{
				SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice =  modelMapper.map(sqc, SurveyDefinitionQuestionChoice.class);
				surveyDefinitionQuestionChoiceService.createNewSurveyDefinitionQuestionChoice(surveyDefinitionQuestionEntity,surveyDefinitionQuestionChoice );
			});
			
		});
		
		return surveyDefinitionEntity.getId();
	}

	
	@Transactional 
	@Override
	public SurveyMetaData getSurveyMetaData(Long surveyId) throws ServiceException {

		validationService.validateGetSurveyMetaData(surveyId);
		
		SurveyVersionEntity surveyVersionEntity = surveyVersionService.findLatestRecordOfSurveyVersion(surveyId).get();
		
		return prepareSurveyMetaData(surveyVersionEntity);
	}

	private SurveyMetaData prepareSurveyMetaData(SurveyVersionEntity surveyVersionEntity) {
		SurveyEntity surveyEntity =surveyVersionEntity.getSurvey();
		SurveyMetaData surveyMetaData = prepareSurveyInfoPartOfSurveyMetaData(surveyVersionEntity);
		ActionEntity action = surveyVersionEntity.getAction();
		final List<SurveyQuestionEntity> surveyQuestions =surveyQuestionService.getSurveyQuestions(surveyEntity.getId() ,action.getId()   );
		surveyQuestions.stream().forEach(sq->
		{
 			final SurveyQuestionMetaData surveyQuestionMetaData = modelMapper.map(sq, SurveyQuestionMetaData.class);
			surveyMetaData.addSurveyQuestionMetaData(surveyQuestionMetaData);
			sq.getSurveyQuestionChoices().stream().forEach(sqc->
			{
				SurveyQuestionChoiceMetaData surveyQuestionChoiceMetaData =	modelMapper.map(sqc, SurveyQuestionChoiceMetaData.class);
				surveyQuestionMetaData.addSurveyQuestionChoiceMetaData(surveyQuestionChoiceMetaData);
			});
		});
	
		return surveyMetaData;
	}

	private SurveyMetaData prepareSurveyInfoPartOfSurveyMetaData(SurveyVersionEntity surveyVersionEntity) {
		return prepareSurveyInfoPartForSurveyMetaData(surveyVersionEntity.getSurvey(), surveyVersionEntity.getSurveyVersion());
	}

	private SurveyMetaData prepareSurveyInfoPartForSurveyMetaData(SurveyEntity surveyEntity, Long surveyVersion) {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setSurveyCode(surveyEntity.getCode());
		surveyMetaData.setTitle(surveyEntity.getTitle());
		surveyMetaData.setSurveyId(surveyEntity.getId());
		surveyMetaData.setSurveyVersion(surveyVersion);
		return surveyMetaData;
	}

	@Override
	public SurveyMetaData getSurveyMetaData(Long surveyId, Long surveyVersion) throws ServiceException {
		
		SurveyVersionEntity surveyVersionEntity = validationService.validateSurveyVersion(surveyId,surveyVersion);
		
		SurveyMetaData surveyMetaData = prepareSurveyMetaData(surveyVersionEntity);
 		
		return surveyMetaData;
	}

	@Override
	public List<SurveyQuestionMetaData> getSurveyQuestions(Long surveyId) throws ServiceException {
		
		validationService.validateGetSurveyQuestions(surveyId);
		
		SurveyVersionEntity surveyVersionEntity = surveyVersionService.findLatestRecordOfSurveyVersion(surveyId).get();
		
		return getSurveyQuestions(surveyVersionEntity);
	}



	@Override
	public List<SurveyQuestionMetaData> getSurveyQuestions(Long surveyId, Long surveyVersion) throws ServiceException {
		SurveyVersionEntity surveyVersionEntity =validationService.validateSurveyVersion(surveyId, surveyVersion);
		return getSurveyQuestions(surveyVersionEntity);
	}
	
	private List<SurveyQuestionMetaData> getSurveyQuestions(SurveyVersionEntity surveyVersionEntity) {
		SurveyEntity surveyEntity = surveyVersionEntity.getSurvey();
		ActionEntity action = surveyVersionEntity.getAction();
		List<SurveyQuestionEntity> surveyQuestions =surveyQuestionService.getSurveyQuestions(surveyEntity.getId() ,action.getId()   );
		
		List<SurveyQuestionMetaData> surveyQuestionMetaDataList =  surveyQuestions.stream().map(sq ->  modelMapper.map(sq, SurveyQuestionMetaData.class)).collect(Collectors.toList());
			
		return surveyQuestionMetaDataList;
	}

	@Override
	public void deleteAllData() {
		surveyDefinitionService.deleteAllData();		
	}

	@Override
	public List<SurveyDefinitionBasicInfo> getAllSurveyDefintionsBasicInfo() {
		List<SurveyDefinitionEntity> surveyDefinitionEntities = surveyDefinitionService.getAllSurveyDefinitions();
		return surveyDefinitionEntities.stream().map(sd -> modelMapper.map(sd, SurveyDefinitionBasicInfo.class)).collect(Collectors.toList());
	}

	@Override
	public SurveyDefinitionMetaData getSurveyDefinitionMetaData(Long surveyDefinitionId) throws ServiceException {
		validationService.validateGetSurveyDefinitionMetaData(surveyDefinitionId);
		return surveyDefinitionService.getSurveyDefinitionMetaData(surveyDefinitionId);
	}

	@Override
	public List<SurveyBasicInfo> getAllSurveys() {
	List<SurveyEntity>  surveyEntityList = surveyService.getAllSurveyes();
		return surveyEntityList.stream().map(s-> modelMapper.map(s, SurveyBasicInfo.class)).collect(Collectors.toList());
	}
	

	
}
