package com.survey.microservice.surveydefinitionservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.survey.microservice.base.trace.entity.BaseAuditEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * The persistent class for the survey_question_choice database table.
 * 
 */
@Entity
@Table(name="survey_question_choice")
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyQuestionChoiceEntity extends BaseAuditEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_QUESTION_CHOICE_PK_SEQ")
	@SequenceGenerator(name="SURVEY_QUESTION_CHOICE_PK_SEQ" , sequenceName="SURVEY_QUESTION_CHOICE_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="title")
	private String title; 

 	@ManyToOne
	@JoinColumn(name="survey_question_id")
	private SurveyQuestionEntity surveyQuestion;
 

}