package com.survey.microservice.surveydefinitionservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.survey.microservice.surveydefinitionservice.model.ActionType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the actions database table.
 * 
 */
@Entity
@Table(name="actions")
@Setter@Getter 
@EqualsAndHashCode
@ToString
public class ActionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="ACTION_PK_SEQ")
	@SequenceGenerator(name="ACTION_PK_SEQ" , sequenceName="ACTION_PK_SEQ",allocationSize=1)
	private Long id;

	@Column(name="action_date")
	private LocalDateTime actionDate;

	@Column(name="action_type")
	@Enumerated(EnumType.STRING)
	private ActionType actionType;

	
	
	 

}