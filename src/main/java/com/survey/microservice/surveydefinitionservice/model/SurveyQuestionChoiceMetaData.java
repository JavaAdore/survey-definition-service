package com.survey.microservice.surveydefinitionservice.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SurveyQuestionChoiceMetaData  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String title;

}
