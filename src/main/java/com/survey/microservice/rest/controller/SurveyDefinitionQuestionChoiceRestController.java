package com.survey.microservice.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.base.model.ServiceResponse;
import com.survey.microservice.base.model.SuccessServiceResponse;
import com.survey.microservice.surveydefinitionservice.facade.SurveyDefinitionFacade;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestionChoice;

@RestController
@RequestMapping("definition/choice")
 public class SurveyDefinitionQuestionChoiceRestController {
	
	private SurveyDefinitionFacade surveyDefinitionFacade;

	public SurveyDefinitionQuestionChoiceRestController(SurveyDefinitionFacade surveyDefinitionFacade) {
 		this.surveyDefinitionFacade = surveyDefinitionFacade;
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}/{surveyDefinitionQuestionId}" , method = RequestMethod.POST)
	public ServiceResponse<Long> createNewSurveyDefinitionQuestionChoice(@PathVariable(name ="surveyDefinitionId") Long surveyDefinitionId , @PathVariable(name="surveyDefinitionQuestionId") Long surveyDefinitionQuestionId,  @RequestBody SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException
	{
		Long surveyDefinitionQuestionChoiceId = surveyDefinitionFacade.createNewSurveyDefinitionQuestionChoice(surveyDefinitionId ,surveyDefinitionQuestionId ,surveyDefinitionQuestionChoice);
		return new SuccessServiceResponse<Long>(surveyDefinitionQuestionChoiceId);
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}/{surveyDefinitionQuestionId}/{surveyDefinitionQuestionChoiceId}" , method = RequestMethod.PUT)
	public ServiceResponse<Long> updateurveyDefinitionQuestionChoice(@PathVariable(name ="surveyDefinitionId") Long surveyDefinitionId ,  @PathVariable(name="surveyDefinitionQuestionId") Long surveyDefinitionQuestionId  , @PathVariable(name="surveyDefinitionQuestionChoiceId") Long surveyDefinitionQuestionChoiceId ,    @RequestBody SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) throws ServiceException
	{
		surveyDefinitionQuestionChoiceId = surveyDefinitionFacade.updateSurveyDefinitionQuestionChoice(surveyDefinitionId ,surveyDefinitionQuestionId ,surveyDefinitionQuestionChoiceId, surveyDefinitionQuestionChoice);
		return new SuccessServiceResponse<Long>(surveyDefinitionQuestionChoiceId);
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}/{surveyDefinitionQuestionId}/{surveyDefinitionQuestionChoiceId}" , method = RequestMethod.DELETE)
	public ServiceResponse<Long> deleteSurveyDefinitionQuestionChoice(@PathVariable(name ="surveyDefinitionId") Long surveyDefinitionId ,  @PathVariable(name="surveyDefinitionQuestionId") Long surveyDefinitionQuestionId  , @PathVariable(name="surveyDefinitionQuestionChoiceId") Long surveyDefinitionQuestionChoiceId) throws ServiceException
	{
		surveyDefinitionQuestionId = surveyDefinitionFacade.deleteSurveyDefinitionQuestionChoice(surveyDefinitionId,surveyDefinitionQuestionId,surveyDefinitionQuestionChoiceId);
		return new SuccessServiceResponse<Long>(surveyDefinitionQuestionId);

	}
	
	 
	
	
	
	
}



	 

	
	
	
	 
