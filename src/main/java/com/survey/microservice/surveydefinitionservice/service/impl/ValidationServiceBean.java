package com.survey.microservice.surveydefinitionservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.base.model.ErrorMessageCode;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionChoiceEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyVersionEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionStatus;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionQuestionChoiceService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionQuestionService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyService;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyVersionService;
import com.survey.microservice.surveydefinitionservice.service.api.ValidationService;


@Service
public class ValidationServiceBean implements ValidationService {

	private final SurveyDefinitionService surveyDefinitionService;
	private final SurveyDefinitionQuestionService surveyDefinitionQuestionService;
	private final SurveyDefinitionQuestionChoiceService surveyDefinitionQuestionChoiceService;
	private final SurveyService surveyService;
	private final SurveyVersionService surveyVersionService;

	public ValidationServiceBean(SurveyDefinitionService surveyDefinitionService,
			SurveyDefinitionQuestionService surveyDefinitionQuestionService,
			SurveyDefinitionQuestionChoiceService surveyDefinitionQuestionChoiceService,
			SurveyService surveyService,SurveyVersionService surveyVersionService) {
		super();
		this.surveyDefinitionService = surveyDefinitionService;
		this.surveyDefinitionQuestionService = surveyDefinitionQuestionService;
		this.surveyDefinitionQuestionChoiceService=surveyDefinitionQuestionChoiceService;
		this.surveyService=surveyService;
		this.surveyVersionService=surveyVersionService;
	}

	@Override
	public void validateCreateNewSurveyDefinition(SurveyDefinition surveyDefinition) throws ServiceException {

		// survey deifnition not expected to be with null value .. so no need to check
		valdiateSurveyDefinitionTitleExistance(surveyDefinition.getTitle());
	}

	private void valdiateSurveyDefinitionTitleExistance(String title) throws ServiceException {
		if (StringUtils.isEmpty(title)|| StringUtils.isEmpty(title.trim())) {
			throw new ServiceException(ErrorMessageCode.NO_TITLE_PROVIDED);
		
		}
		
	}

	@Override
	public void validateCreateNewSurveyDefinitionQuestion(Long surveyDefinitionId,
			SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException {

		validateSurveyDefinitionDraftStatus(surveyDefinitionId);

		valdiateSurveyDefinitionTitleExistance(surveyDefinitionQuestion.getTitle());

	}

	private SurveyDefinitionEntity validateSurveyDefinitionDraftStatus(Long surveyDefinitionId) throws ServiceException {
		Optional<SurveyDefinitionEntity> surveyDefinitionEntityOptional = surveyDefinitionService
				.findSurveyDefinitionById(surveyDefinitionId);
		if (!surveyDefinitionEntityOptional.isPresent()) {
			throw new ServiceException(ErrorMessageCode.NO_SUCH_SURVEY_DEFINITION);
		}

		SurveyDefinitionEntity surveyDefinitionEntity = surveyDefinitionEntityOptional.get();
		if (SurveyDefinitionStatus.DRAFT != surveyDefinitionEntity.getSurveyDefinitionStatus()) {
			throw new ServiceException(ErrorMessageCode.PROVIDED_SURVEY_DEFINITION_IS_NOT_IN_DRAFT_STATUS);

		}
		
		return surveyDefinitionEntity;
	}

	@Override
	public void validateUpdateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition)
			throws ServiceException {

		validateSurveyDefinitionDraftStatus(surveyDefinitionId);

		validateSurveyDefinitionTitle(surveyDefinition.getTitle());

	}

	private void validateSurveyDefinitionTitle(String title) throws ServiceException {

		valdiateSurveyDefinitionTitleExistance(title);
		validateSurveyDefintionLength(title);
	}

	private void validateSurveyDefintionLength(String title) {
		// TODO Auto-generated method stub

	}
	
	private void validateSurveyDefintionQuestionTitle(String title) {
		// TODO Auto-generated method stub
		
	}
	
	private void validateSurveyDefinitionQuestionChoiceTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateUpdateSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException {

		validateSurveyDefinitionDraftStatus(surveyDefinitionId);
		
		validateQuestionSurveyDefinitionAssociation(surveyDefinitionId,surveyDefinitionQuestionId);
		
		validateSurveyDefintionQuestionTitle(surveyDefinitionQuestion.getTitle());
		
		// validate survey doesn't not has question with exact same value
		// validate new value is deffierent than new value
	}

	

	private void validateQuestionSurveyDefinitionAssociation(Long surveyDefinitionId, Long surveyDefinitionQuestionId) throws ServiceException {
		Optional<SurveyDefinitionQuestionEntity> surveyDefinitionQuestionEntityOptional = surveyDefinitionQuestionService
				.findSurveyDefinitionQuestionById(surveyDefinitionQuestionId);
		if (!surveyDefinitionQuestionEntityOptional.isPresent()) {
			throw new ServiceException(ErrorMessageCode.NO_SUCH_SURVEY_DEFINITION_QUESTION);
		}		
		SurveyDefinitionEntity surveyDefinitionEntity = surveyDefinitionQuestionEntityOptional.get().getSurveyDefinition();
		if(!surveyDefinitionEntity.getId().equals(surveyDefinitionId))
		{
			throw new ServiceException(ErrorMessageCode.PROVIDED_SURVEY_QUESTION_DEFINITION_IS_NOT_ASSOCIATED_WITH_PROVIDED_SURVEY_DEFINITION);

		}
	}

	@Override
	public void validateDeleteSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId)
			throws ServiceException {
		
		validateSurveyDefinitionDraftStatus(surveyDefinitionId);
		
		validateQuestionSurveyDefinitionAssociation(surveyDefinitionId,surveyDefinitionQuestionId);		
	}

	@Override
	public void validateCreateNewSurveyDefinitionQuestionChoice(Long surveyDefinitionId,
			Long surveyDefinitionQuestionId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException {
		
		validateSurveyDefinitionDraftStatus(surveyDefinitionId);
		
		validateQuestionSurveyDefinitionAssociation(surveyDefinitionId,surveyDefinitionQuestionId);	
		
		validateSurveyDefinitionQuestionChoiceTitle(surveyDefinitionQuestionChoice.getTitle());
	}

	@Override
	public void validateUpdateSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			Long surveyDefinitionQuestionChoiceId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice)
			throws ServiceException {
		
		validateSurveyDefinitionDraftStatus(surveyDefinitionId);
		
		validateQuestionSurveyDefinitionAssociation(surveyDefinitionId,surveyDefinitionQuestionId);
		
		validateChoiceQuestionDefinitionAssociation(surveyDefinitionQuestionId,surveyDefinitionQuestionChoiceId);
		
		validateSurveyDefinitionQuestionChoiceTitle(surveyDefinitionQuestionChoice.getTitle());
		
	}

	private void validateChoiceQuestionDefinitionAssociation(Long surveyDefinitionQuestionId,Long surveyDefinitionQuestionChoiceId) throws ServiceException {
		Optional<SurveyDefinitionQuestionChoiceEntity> surveyChoiceQuestionDefinitionEntityOptional =  surveyDefinitionQuestionChoiceService.findSurveyQuestionChoiceDefinitionById(surveyDefinitionQuestionChoiceId)	;	
		if(!surveyChoiceQuestionDefinitionEntityOptional.isPresent())
		{
			throw new ServiceException(ErrorMessageCode.NO_SUCH_SURVEY_DEFINITION_QUESTION_CHOICE);
		}
		
		SurveyDefinitionQuestionChoiceEntity surveyDefinitionQuestionChoiceEntity = surveyChoiceQuestionDefinitionEntityOptional.get();
		if(!surveyDefinitionQuestionChoiceEntity.getSurveyDefinitionQuestion().getId().equals(surveyDefinitionQuestionId))
		{
			throw new ServiceException(ErrorMessageCode.PROVIDED_SURVEY_DEFINITION_QUESTION_CHOICE_IS_NOT_ASSOCIATED_WITH_PROVIDED_SURVEY_QUESTION_DEFINITION);

		}
	}

	@Override
	public void validateDeleteSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,
			Long surveyDefinitionQuestionChoiceId) throws ServiceException {
		
		validateSurveyDefinitionDraftStatus(surveyDefinitionId);
		
		validateQuestionSurveyDefinitionAssociation(surveyDefinitionId,surveyDefinitionQuestionId);
		
		validateChoiceQuestionDefinitionAssociation(surveyDefinitionQuestionId,surveyDefinitionQuestionChoiceId);		
	}

	@Override
	public void validatePublishSurveyDefinition(Long surveyDefinitionId) throws ServiceException {
		
		SurveyDefinitionEntity surveyDefinitionEntity = validateSurveyDefinitionDraftStatus(surveyDefinitionId);
		List<SurveyDefinitionQuestionEntity> surveyDefinitionQuestions=	surveyDefinitionEntity.getSurveyDefinitionQuestions();
		if(surveyDefinitionQuestions.size()==0)
		{
			throw new ServiceException(ErrorMessageCode.SURVEY_DEFINITION_SHOULD_CONTAINS_AT_LEAST_ONE_QUESTION);
		}
		
		for(SurveyDefinitionQuestionEntity question : surveyDefinitionQuestions)
		{
			if(question.getSurveyDefinitionQuestionChoices().size()<2)
			{
				throw new ServiceException(ErrorMessageCode.SURVEY_DEFINITION_QUESTION_SHOULD_CONTAINS_AT_LEAST_TWO_CHOICES,question.getId(),null);
			} 
		}
		 
	}

	@Override
	public void validateEditSurvey(Long surveyId) throws ServiceException {
		
		validateSurveyExistance(surveyId);
	}


	private void validateSurveyExistance(Long surveyId) throws ServiceException {
		Boolean exists = 	surveyService.isExist(surveyId);
		if(!exists)
		{
			throw new ServiceException(ErrorMessageCode.NO_SUCH_SURVEY,new Object[] {surveyId});

		}		
	}

	@Override
	public void validateGetSurveyMetaData(Long surveyId) throws ServiceException {
		validateSurveyExistance(surveyId);
 	}

	@Override
	public SurveyVersionEntity validateSurveyVersion(Long surveyId, Long surveyVersion) throws ServiceException {
	
		Optional<SurveyVersionEntity> surveyVersionEntityOptional=  surveyVersionService.findSurveyVersionRecordBy(surveyId,surveyVersion);
	    
		if(!surveyVersionEntityOptional.isPresent())
	    {
			throw new ServiceException(ErrorMessageCode.NO_SUCH_SURVEY_VERSION_FOR_PROVIDED_SURVEY,new Object[] {surveyId,surveyVersion}); 
	    }
		return surveyVersionEntityOptional.get();
	}

	@Override
	public void validateGetSurveyQuestions(Long surveyId) throws ServiceException {
		
		validateSurveyExistance(surveyId);
	}

	@Override
	public void validateGetSurveyDefinitionMetaData(Long surveyDefinitionId) throws ServiceException {
		 
		Optional<SurveyDefinitionEntity> surveyDefinitionEntityOptional = surveyDefinitionService
				.findSurveyDefinitionById(surveyDefinitionId);
		if (!surveyDefinitionEntityOptional.isPresent()) {
			throw new ServiceException(ErrorMessageCode.NO_SUCH_SURVEY_DEFINITION);
		}
	}

 
	

	 

}
