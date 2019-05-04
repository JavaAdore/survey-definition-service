package com.survey.microservice.surveydefinitionservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.survey.microservice.base.trace.entity.BaseAuditEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyDefinitionStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the survey_definition database table.
 * 
 */
@Entity(name=SurveyDefinitionEntity.ENTITY_NAME)
@Table(name=SurveyDefinitionEntity.TABLE_NAME)
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyDefinitionEntity extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String TABLE_NAME="survey_definition";
	public final static String ENTITY_NAME="SurveyDefinition";

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_DEFINITION_PK_SEQ")
	@SequenceGenerator(name="SURVEY_DEFINITION_PK_SEQ" , sequenceName="SURVEY_DEFINITION_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="code")
	private String code;

	@Column(name="survey_definition_status")
	@Enumerated(EnumType.STRING)
	private SurveyDefinitionStatus surveyDefinitionStatus;

	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "previous_action_id", referencedColumnName = "id")
	private ActionEntity previousAction;
	
	@ManyToOne
	@JoinColumn(name = "action_id", referencedColumnName = "id")
	private ActionEntity action;
	
	@ManyToOne
	@JoinColumn(name = "survey_id", referencedColumnName = "id")
	private SurveyEntity survey;
 
	@OneToMany(mappedBy="surveyDefinition")
	private List<SurveyDefinitionQuestionEntity> surveyDefinitionQuestions;

}