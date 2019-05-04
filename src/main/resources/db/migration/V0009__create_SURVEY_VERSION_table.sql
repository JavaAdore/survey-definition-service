CREATE SEQUENCE SURVEY_VERSION_PK_SEQ START WITH 1;  

CREATE TABLE SURVEY_VERSION
(
       ID              						INT8 NOT NULL,
       ACTION_ID        					INT8 NOT NULL,
       SURVEY_ID 	       					INT8 NOT NULL,
       SURVEY_VERSION 	       				INT8 NOT NULL,
       CREATED_BY      						INT8,
       CREATED_DATE    						TIMESTAMP WITH TIME ZONE,
       PRIMARY KEY (ID)
);


ALTER TABLE SURVEY_VERSION 
ADD 
CONSTRAINT SURVEY_VERSION_SURVEY_ACTION_FK
FOREIGN KEY (ACTION_ID) REFERENCES ACTIONS;

ALTER TABLE SURVEY_VERSION 
ADD 
CONSTRAINT SURVEY_VERSION_SURVEY_ID_FK
FOREIGN KEY (SURVEY_ID) REFERENCES SURVEY;


CREATE INDEX SURVEY_VERSION_SURVEY_INDEX ON SURVEY_VERSION(SURVEY_ID);