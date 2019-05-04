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
 * The persistent class for the survey_definition_question database table.
 * 
 */
@Entity
@Table(name="survey_definition_question")
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyDefinitionQuestionEntity extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_DEFINITION_QUESTION_PK_SEQ")
	@SequenceGenerator(name="SURVEY_DEFINITION_QUESTION_PK_SEQ" , sequenceName="SURVEY_DEFINITION_QUESTION_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="title")
	private String title;

	@Column(name="code")
	private String code;
	
 	@ManyToOne
	@JoinColumn(name="survey_definition_id")
	private SurveyDefinitionEntity surveyDefinition;

 	@OneToMany(mappedBy="surveyDefinitionQuestion")
	private List<SurveyDefinitionQuestionChoiceEntity> surveyDefinitionQuestionChoices;

	 

}