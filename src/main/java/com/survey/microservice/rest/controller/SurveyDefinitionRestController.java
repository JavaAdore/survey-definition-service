package com.survey.microservice.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.base.model.ServiceResponse;
import com.survey.microservice.base.model.SuccessServiceResponse;
import com.survey.microservice.surveydefinitionservice.facade.SurveyDefinitionFacade;
import com.survey.microservice.surveydefinitionservice.model.SurveyBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionMetaData;

@RestController()
@RequestMapping("definition/survey")
 public class SurveyDefinitionRestController {
	
	private SurveyDefinitionFacade surveyDefinitionFacade;

	public SurveyDefinitionRestController(SurveyDefinitionFacade surveyDefinitionFacade) {
 		this.surveyDefinitionFacade = surveyDefinitionFacade;
	}
	
	
	@RequestMapping(path = "/" , method = RequestMethod.POST)
	public ServiceResponse<Long> createNewSurveyDefinition(@RequestBody SurveyDefinition surveyDefinition) throws ServiceException
	{
		Long surveyDefinitionId = surveyDefinitionFacade.createNewSurveyDefinition(surveyDefinition);
		return new SuccessServiceResponse<Long>(surveyDefinitionId);
	}
	
	@RequestMapping(path = "/" , method = RequestMethod.GET)
	public ServiceResponse<List<SurveyDefinitionBasicInfo>> getAllSurveyDefinitionsBasicInfo()
	{
		List<SurveyDefinitionBasicInfo>  allUnderProccessSurveyDefintionsBasicInfo = surveyDefinitionFacade.getAllSurveyDefintionsBasicInfo();
		return new SuccessServiceResponse<List<SurveyDefinitionBasicInfo>>(allUnderProccessSurveyDefintionsBasicInfo);
	}
	
	@RequestMapping(path = "/{surveyDefinitionId}" , method = RequestMethod.GET)
	public ServiceResponse<SurveyDefinitionMetaData> getSurveyDefinitionMetaData(@PathVariable("surveyDefinitionId") Long surveyDefinitionId) throws ServiceException
	{
		SurveyDefinitionMetaData surveyDefinitionMetaData = surveyDefinitionFacade.getSurveyDefinitionMetaData(surveyDefinitionId);
		return new  SuccessServiceResponse<SurveyDefinitionMetaData>(surveyDefinitionMetaData);
	}
	
	
	@RequestMapping(path = "/{surveyDefinitionId}" , method = RequestMethod.PUT)
	public ServiceResponse<Long> updateNewSurveyDefinition(@PathVariable(name = "surveyDefinitionId") Long surveyDefinitionId ,  @RequestBody SurveyDefinition surveyDefinition) throws ServiceException
	{
		surveyDefinitionId = surveyDefinitionFacade.updateNewSurveyDefinition(surveyDefinitionId ,surveyDefinition);
		return new SuccessServiceResponse<Long>(surveyDefinitionId);
	}
	
	
	@RequestMapping(path = "/publish/{surveyDefinitionId}" , method = RequestMethod.POST)
	public ServiceResponse<SurveyBasicInfo> publishSurveyDefinition(@PathVariable("surveyDefinitionId") Long surveyDefinitionId ) throws ServiceException
	{
		SurveyBasicInfo surveyBasicInfo=surveyDefinitionFacade.publishSurveyDefinition(surveyDefinitionId);
		return new SuccessServiceResponse<SurveyBasicInfo>(surveyBasicInfo);
	}
	
	
	
}