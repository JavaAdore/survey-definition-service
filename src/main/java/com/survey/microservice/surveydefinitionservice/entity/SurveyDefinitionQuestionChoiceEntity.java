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
 * The persistent class for the survey_definition_question_choice database table.
 * 
 */
@Entity(name = SurveyDefinitionQuestionChoiceEntity.ENTITY_NAME)
@Table(name=SurveyDefinitionQuestionChoiceEntity.TABLE_NAME)
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyDefinitionQuestionChoiceEntity extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String TABLE_NAME="survey_definition_question_choice";
	public final static String ENTITY_NAME="SurveyDefinitionQuestionChoice";

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_DEFINITION_QUESTION_CHOICE_PK_SEQ")
	@SequenceGenerator(name="SURVEY_DEFINITION_QUESTION_CHOICE_PK_SEQ" , sequenceName="SURVEY_DEFINITION_QUESTION_CHOICE_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="title")
	private String title;

	//bi-directional many-to-one association to SurveyDefinitionQuestionEntity
	@ManyToOne
	@JoinColumn(name="survey_definition_question_id")
	private SurveyDefinitionQuestionEntity surveyDefinitionQuestion;

 
}