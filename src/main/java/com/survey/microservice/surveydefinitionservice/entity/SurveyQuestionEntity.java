package com.survey.microservice.surveydefinitionservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.survey.microservice.base.trace.entity.BaseAuditEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the survey_question database table.
 * 
 */
@Entity(name =SurveyQuestionEntity.ENTITY_NAME )
@Table(name=SurveyQuestionEntity.TABLE_NAME)
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyQuestionEntity extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ENTITY_NAME = "SurveyQuestion";
	public static final String TABLE_NAME  = "survey_question";

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_QUESTION_PK_SEQ")
	@SequenceGenerator(name="SURVEY_QUESTION_PK_SEQ" , sequenceName="SURVEY_QUESTION_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="title")
	private String title;

	@Column(name="code")
	private String code;
	
	@ManyToOne
	@JoinColumn(name="action_id")
	private ActionEntity action;

	@ManyToOne
	@JoinColumn(name="closed_by_action_id")
	private ActionEntity closedByAction;

	@ManyToOne
	@JoinColumn(name="survey_id")
	private SurveyEntity survey;

 	@OneToMany(mappedBy="surveyQuestion")
	private List<SurveyQuestionChoiceEntity> surveyQuestionChoices;

	 
}