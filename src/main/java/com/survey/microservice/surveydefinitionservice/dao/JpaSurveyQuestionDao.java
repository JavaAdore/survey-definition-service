package com.survey.microservice.surveydefinitionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.survey.microservice.surveydefinitionservice.entity.ActionEntity;
import com.survey.microservice.surveydefinitionservice.entity.SurveyQuestionEntity;

@Repository
public interface JpaSurveyQuestionDao extends JpaRepository<SurveyQuestionEntity, Long> {

	
	
	@Modifying
	@Query("update  "  + SurveyQuestionEntity.ENTITY_NAME + "  set closedByAction  = :#{#closingAction} where closedByAction is null and survey.id =  :#{#surveyId}"  )
    void markQuestionsOfSurveyForPreviousVersionAsClosed(@Param("surveyId") Long surveyId, @Param("closingAction") ActionEntity action) ;

	@Query("select sq from "+SurveyQuestionEntity.ENTITY_NAME + " sq where sq.survey.id =:#{#surveyId} and  sq.closedByAction is null   "  )
	List<SurveyQuestionEntity> getLatestSurveyQuestions(@Param("surveyId") Long surveyId);

	@Query("select sq from "+SurveyQuestionEntity.ENTITY_NAME + " sq where sq.survey.id =:#{#surveyId}  and sq.action.id =:#{#actionId}   "  )
	List<SurveyQuestionEntity> getSurveyQuestions(@Param("surveyId")  Long surveyId, @Param("actionId")  Long actionId); 

}
