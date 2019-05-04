package com.survey.microservice.surveydefinitionservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.survey.microservice.base.trace.entity.BaseAuditEntity;
import com.survey.microservice.surveydefinitionservice.model.SurveyStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the survey database table.
 * 
 */
@Entity
@Table(name="survey")
@Setter@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SurveyEntity extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SURVEY_PK_SEQ")
	@SequenceGenerator(name="SURVEY_PK_SEQ" , sequenceName="SURVEY_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="code")
	private String code;
	
	@Column(name="title")
	private String title;
	
	@Column(name="survey_version")
	private Long surveyVersion;	
	
	@Column(name="survey_status")
	@Enumerated(EnumType.STRING)
	private SurveyStatus surveyStatus;
	
 

}