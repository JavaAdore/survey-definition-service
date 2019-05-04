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
 * The persistent class for the survey_version database table.
 * 
 */
@Entity(name = SurveyVersionEntity.ENTITY_NAME)
@Table(name="survey_version")
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyVersionEntity  extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public  static final String TABLE_NAME = "survey_version";
	public  static final String ENTITY_NAME = "SurveyVersion";

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_VERSION_PK_SEQ")
	@SequenceGenerator(name="SURVEY_VERSION_PK_SEQ" , sequenceName="SURVEY_VERSION_PK_SEQ",allocationSize=1)
	private Long id;



	@Column(name="survey_version")
	private Long surveyVersion;
	
 	@ManyToOne
 	@JoinColumn(name = "action_id" , referencedColumnName = "id")
	private ActionEntity action;

 	@ManyToOne
 	@JoinColumn(name = "survey_id" , referencedColumnName = "id")
	private SurveyEntity survey;

	 
}