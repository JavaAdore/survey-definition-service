package com.survey.microservice.surveydefinitionservice;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.survey.microservice.SurveyDefinitionApplication;
import com.survey.microservice.base.exception.ServiceException;
import com.survey.microservice.base.model.ErrorMessageCode;
import com.survey.microservice.surveydefinitionservice.dao.JpaSurveyDefinitionDao;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyDefinitionQuestionEntity;
import com.survey.microservice.surveydefinitionservice.facade.SurveyDefinitionFacade;
import com.survey.microservice.surveydefinitionservice.facade.SurveyDefinitionFacadeBean;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinition;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionStatus;
import com.survey.microservice.surveydefinitionservice.service.api.SurveyDefinitionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurveyDefinitionApplication.class)
@ActiveProfiles("test")
public class SurveyDefinitionApplicationTests {

	
	@Autowired
	@InjectMocks
	private SurveyDefinitionFacadeBean surveyDefinitionFacade;
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	
	@MockBean
	private  JpaSurveyDefinitionDao jpaSurveyDefinitionDao;
	
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void createNewSurveyWithEmptyTitle() throws ServiceException
	{
		expectedEx.expect(ServiceException.class);
		expectedEx.expect(new SurviceExceptionCustomMatcher(
				ErrorMessageCode.NO_TITLE_PROVIDED));
		SurveyDefinition surveyDefinition = new SurveyDefinition();
		surveyDefinition.setTitle("     ");
		surveyDefinitionFacade.createNewSurveyDefinition(surveyDefinition);
	}
	
	@Test
	public void publishCompletedSurveyDefinition() throws ServiceException
	{
 		Mockito.when(jpaSurveyDefinitionDao.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(prepareSurveyDefinition(SurveyDefinitionStatus.COMPLETED)));
 		expectedEx.expect(ServiceException.class);
		expectedEx.expect(new SurviceExceptionCustomMatcher(
				ErrorMessageCode.PROVIDED_SURVEY_DEFINITION_IS_NOT_IN_DRAFT_STATUS));
		
		surveyDefinitionFacade.publishSurveyDefinition(1l);
	}
	
	
	@Test
	public void tryToPublishSurveyHasQuestionWithNoChoices() throws ServiceException
	{
 		Mockito.when(jpaSurveyDefinitionDao.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(prepareDraftSurveyContainsQuestionsWithNoAnswers()));
 		expectedEx.expect(ServiceException.class);
		expectedEx.expect(new SurviceExceptionCustomMatcher(
				ErrorMessageCode.SURVEY_DEFINITION_QUESTION_SHOULD_CONTAINS_AT_LEAST_TWO_CHOICES));
		
		surveyDefinitionFacade.publishSurveyDefinition(1l);
	}
	
	
	@Test
	public void tryToPublishSurveyHasNoQuestions() throws ServiceException
	{
 		Mockito.when(jpaSurveyDefinitionDao.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(prepareDraftSurveyWithNoQuestions()));
 		expectedEx.expect(ServiceException.class);
		expectedEx.expect(new SurviceExceptionCustomMatcher(
				ErrorMessageCode.SURVEY_DEFINITION_SHOULD_CONTAINS_AT_LEAST_ONE_QUESTION));
		surveyDefinitionFacade.publishSurveyDefinition(1l);
		
	}


	private SurveyDefinitionEntity prepareDraftSurveyWithNoQuestions() {
		SurveyDefinitionEntity surveyDefinitionEntity = prepareSurveyDefinition(SurveyDefinitionStatus.DRAFT);
		surveyDefinitionEntity.setSurveyDefinitionQuestions(new ArrayList<SurveyDefinitionQuestionEntity>());
		return surveyDefinitionEntity;
	}


	private SurveyDefinitionEntity prepareDraftSurveyContainsQuestionsWithNoAnswers() {
		SurveyDefinitionEntity surveyDefinitionEntity = prepareSurveyDefinition(SurveyDefinitionStatus.DRAFT);
		surveyDefinitionEntity.setSurveyDefinitionQuestions(new ArrayList<SurveyDefinitionQuestionEntity>());
		SurveyDefinitionQuestionEntity surveyDefinitionQuestionEntity = new SurveyDefinitionQuestionEntity();
		surveyDefinitionQuestionEntity.setId(1l);
		surveyDefinitionQuestionEntity.setSurveyDefinitionQuestionChoices(new ArrayList<>());
		surveyDefinitionEntity.getSurveyDefinitionQuestions().add(surveyDefinitionQuestionEntity);
		return surveyDefinitionEntity;
	}


	private SurveyDefinitionEntity prepareSurveyDefinition( SurveyDefinitionStatus surveyDefinitionStatus) {
		SurveyDefinitionEntity surveyDefinitionEntity = new SurveyDefinitionEntity();
		surveyDefinitionEntity.setSurveyDefinitionStatus(surveyDefinitionStatus);
		return surveyDefinitionEntity;
	}
}

