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
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionQuestion;

@RestController
@RequestMapping("definition/question")
 public class SurveyDefinitionQuestionRestController {
	
	private SurveyDefinitionFacade surveyDefinitionFacade;

	public SurveyDefinitionQuestionRestController(SurveyDefinitionFacade surveyDefinitionFacade) {
 		this.surveyDefinitionFacade = surveyDefinitionFacade;
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}" , method = RequestMethod.POST)
	public ServiceResponse<Long> createNewSurveyDefinitionQuestion(@PathVariable(name ="surveyDefinitionId") Long surveyDefinitionId ,  @RequestBody SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException
	{
		Long surveyDefinitionQuestionId = surveyDefinitionFacade.createNewSurveyDefinitionQuestion(surveyDefinitionId ,surveyDefinitionQuestion);
		return new SuccessServiceResponse<Long>(surveyDefinitionQuestionId);
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}/{surveyDefinitionQuestionId}" , method = RequestMethod.PUT)
	public ServiceResponse<Long> updateSurveyDefinitionQuestion(@PathVariable(name ="surveyDefinitionId") Long surveyDefinitionId ,  @PathVariable(name="surveyDefinitionQuestionId") Long surveyDefinitionQuestionId  ,  @RequestBody SurveyDefinitionQuestion surveyDefinitionQuestion) throws ServiceException
	{
		surveyDefinitionQuestionId = surveyDefinitionFacade.updateSurveyDefinitionQuestion(surveyDefinitionId ,surveyDefinitionQuestionId , surveyDefinitionQuestion);
		return new SuccessServiceResponse<Long>(surveyDefinitionQuestionId);
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}/{surveyDefinitionQuestionId}" , method = RequestMethod.DELETE)
	public ServiceResponse<Long> deleteSurveyDefinitionQuestion(@PathVariable(name ="surveyDefinitionId") Long surveyDefinitionId ,  @PathVariable(name="surveyDefinitionQuestionId") Long surveyDefinitionQuestionId) throws ServiceException
	{
		surveyDefinitionQuestionId = surveyDefinitionFacade.deleteSurveyDefinitionQuestion(surveyDefinitionId,surveyDefinitionQuestionId);
		return new SuccessServiceResponse<Long>(surveyDefinitionQuestionId);

	}
	
	 
	
	
	
	
}



	 

	
	
	
	 
