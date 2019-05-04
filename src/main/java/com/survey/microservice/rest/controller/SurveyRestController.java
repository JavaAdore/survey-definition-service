package com.survey.microservice.rest.controller;



import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.base.model.ServiceResponse;
import com.survey.microservice.base.model.SuccessServiceResponse;
import com.survey.microservice.surveydefinitionservice.facade.SurveyDefinitionFacade;
import com.survey.microservice.surveydefinitionservice.model.SurveyBasicInfo;
import com.survey.microservice.surveydefinitionservice.model.SurveyMetaData;
import com.survey.microservice.surveydefinitionservice.model.SurveyQuestionMetaData;
 
@RestController()
@RequestMapping("survey")
public class SurveyRestController {

	private SurveyDefinitionFacade surveyDefinitionFacade;

	public SurveyRestController(SurveyDefinitionFacade surveyDefinitionFacade) {
 		this.surveyDefinitionFacade = surveyDefinitionFacade;
	}
	
	@RequestMapping(path="/",  method = RequestMethod.GET)
	public ServiceResponse<List<SurveyBasicInfo>> getAllSurveys()
	{ 
		List<SurveyBasicInfo> SurveyBasicInfoList = 	surveyDefinitionFacade.getAllSurveys();
		return new SuccessServiceResponse<List<SurveyBasicInfo>>(SurveyBasicInfoList);
	}
	
	
	@RequestMapping(path="edit/{surveyId}" , method = RequestMethod.PUT)
	public ServiceResponse<Long> editSurvey(@PathVariable("surveyId") Long surveyId) throws ServiceException
	{
		Long surveyDefinitionId = surveyDefinitionFacade.editSurvey(surveyId);
		
		return new SuccessServiceResponse<Long>(surveyDefinitionId);
	}
	
	
	@RequestMapping(path="metadata/latest/{surveyId}" , method = RequestMethod.GET)
	public ServiceResponse<SurveyMetaData> getLatestSurveyMetaData( @PathVariable("surveyId") Long surveyId) throws ServiceException
	{
		SurveyMetaData surveyMetaData = surveyDefinitionFacade.getSurveyMetaData(surveyId);
		
		return new SuccessServiceResponse<SurveyMetaData>(surveyMetaData);
	}
	
	// graph object contains meta data about the survey with it's questions and answers
	@RequestMapping(path="metadata/{surveyId}/{surveyVersion}" , method = RequestMethod.GET)
	public ServiceResponse<SurveyMetaData> getSurveyMetaData( @PathVariable("surveyId") Long surveyId , @PathVariable("surveyVersion") Long surveyVersion) throws ServiceException
	{
		SurveyMetaData surveyMetaData = surveyDefinitionFacade.getSurveyMetaData( surveyId,surveyVersion);
		
		return new SuccessServiceResponse<SurveyMetaData>(surveyMetaData);
	}
	
	
	@RequestMapping(path="{surveyId}/question" , method = RequestMethod.GET)
	public ServiceResponse<List<SurveyQuestionMetaData>> getSurveyQuestions(@PathVariable("surveyId") Long surveyId) throws ServiceException
	{
		List<SurveyQuestionMetaData> surveyQuestionsMetaData = surveyDefinitionFacade.getSurveyQuestions(surveyId);
		
		return new SuccessServiceResponse<List<SurveyQuestionMetaData>>(surveyQuestionsMetaData);
	}
	
	
	@RequestMapping(path="{surveyId}/{surveyVersion}/question" , method = RequestMethod.GET)
	public ServiceResponse<List<SurveyQuestionMetaData>> getSurveyQuestions(@PathVariable("surveyId") Long surveyId , @PathVariable("surveyVersion") Long surveyVersion) throws ServiceException
	{
		List<SurveyQuestionMetaData> surveyQuestionsMetaData = surveyDefinitionFacade.getSurveyQuestions(surveyId,surveyVersion);
		
		return new SuccessServiceResponse<List<SurveyQuestionMetaData>>(surveyQuestionsMetaData);
	}
	
	@RequestMapping(path = "delete-all-data", method = RequestMethod.DELETE)
 	public ServiceResponse<Object> reset()
 	{
		surveyDefinitionFacade.deleteAllData();
		return new SuccessServiceResponse<Object>(null);
 	}
	
}
