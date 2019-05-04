CREATE SEQUENCE SURVEY_DEFINITION_PK_SEQ START WITH 1;  

CREATE SEQUENCE SURVEY_DEFINITION_CODE_SEQ START WITH 1000000;  

CREATE TABLE SURVEY_DEFINITION
(
       ID              						INT8 NOT NULL,
       CODE 	        					VARCHAR(12),
       TITLE 	        					VARCHAR(40),
       SURVEY_DEFINITION_STATUS 	        VARCHAR(40),
       PREVIOUS_ACTION_ID					INT8  NULL,
       ACTION_ID							INT8  NULL,
       SURVEY_ID							INT8  NULL,
       CREATED_BY      						INT8,
       CREATED_DATE    						TIMESTAMP WITH TIME ZONE,
       PRIMARY KEY(ID)
);


 
CREATE INDEX SURVEY_DEFINITION_CODE ON SURVEY_DEFINITION(CODE);






