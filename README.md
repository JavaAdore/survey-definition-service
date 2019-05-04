# survey-definition-service
service maintains survey definitions </br>
Mostly used by (Survey Creators)

# prerequisites

survey eureka server should be up and run<br/>
<a href="https://github.com/JavaAdore/eureka-server">https://github.com/JavaAdore/survey-eureka-server</a> <br/>

# Some parts this service code is copied from my open source service "user-service"
<a href="https://github.com/JavaAdore/user-service">https://github.com/JavaAdore/user-service</a>


zipkin server nice be up and run so you can track the request<br/>
<a href="https://github.com/JavaAdore/survey-zipkin-server">https://github.com/JavaAdore/survey-zipkin-server</a> <br/>


Postgres DB <br/>


environment variables should be added

# SURVEY_ZIPKIN_SERVER_IP = 127.0.0.1
127.0.0.1 the ip of machine where zipkin server runs
  

# SURVEY_POSTGRES_SERVER_IP    = 127.0.0.1
# SURVEY_POSTGRES_SERVER_PORT  = 5432
# SURVEY_POSTGRES_DBNAME 	    = postgres     
 
  
# SURVEY_EUREKA_SERVER_IP      = 127.0.0.1
# SURVEY_EUREKA_SERVER_PORT    = 8761
# SURVEY_POSTGRES_USER         = postgres
# SURVEY_POSTGRES_PASS         = survey_db_password



# survey-definition-service provides the following functionalities
##maintain survey definition<br/>                                                                                                                                                                                           
Long createNewSurveyDefinition(SurveyDefinition surveyDefinition)                                                                                                                                    
Long updateNewSurveyDefinition(Long surveyDefinitionId, SurveyDefinition surveyDefinition)	                                                                                                         
SurveyBasicInfo publishSurveyDefinition(Long surveyDefinitionId)   
List<SurveyDefinitionBasicInfo> getAllSurveyDefintionsBasicInfo()
SurveyDefinitionMetaData getSurveyDefinitionMetaData(Long surveyDefinitionId)                                                                                                                                  
                                                                                                                                                                                                                             
##maintain survey definition question                                                                                                                                                                                       
Long createNewSurveyDefinitionQuestion(Long surveyDefinitionId, SurveyDefinitionQuestion surveyDefinitionQuestion)                                                                                   
Long updateSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId,SurveyDefinitionQuestion surveyDefinitionQuestion)                                                      
Long deleteSurveyDefinitionQuestion(Long surveyDefinitionId, Long surveyDefinitionQuestionId)                                                                                                        
                                                                                                                                                                                                                             
                                                                                                                                                                                                                             
                                                                                                                                                                                                                             
##maintain survey definition question choice                                                                                                                                                                                
Long createNewSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice)                                
Long updateSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId, Long surveyDefinitionQuestionChoiceId, SurveyDefinitionQuestionChoice surveyDefinitionQuestionChoice) 
Long deleteSurveyDefinitionQuestionChoice(Long surveyDefinitionId, Long surveyDefinitionQuestionId,Long surveyDefinitionQuestionChoiceId)                                                            
                                                                                                                                                                                                                             
##To create a draf version of survey to modify on then publish and then used as a latest version                                                                                                                            
Long editSurvey(Long surveyId)                                                                                                                                                                       
                                                                                                                                                                                                                             
##To Get Survey Metadata                                                                                                                                                                                                                                                                                                           
SurveyMetaData getSurveyMetaData(Long surveyId)                                                                                                                                                      
SurveyMetaData getSurveyMetaData(Long surveyId, Long surveyVersion)                                                                                                                                  
List<SurveyQuestionMetaData> getSurveyQuestions(Long surveyId)                                                                                                                                       
List<SurveyQuestionMetaData> getSurveyQuestions(Long surveyId, Long surveyVersion)                                                                                                                   

## General
List<SurveyBasicInfo> getAllSurveys();

void deleteAllData();
                                                                                                                                                                                                                                  
# build
as root/Administration <br/>
build without dockerize : <br/>
mvn clean install <br/>
build and dockerize : <br/>
mvn clean install docker:removeImage docker:build


# run
java -jar target/survey-definition-service.jar
