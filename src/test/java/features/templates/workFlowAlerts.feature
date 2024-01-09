@workFlowAlerts
Feature: HouseholdNotes
  Feature file for adding or checking Household Notes

  Background:
  Given I am logged into the Latitude Desktop


   @workFlowAlerts @checkWorkFlowAlerts
  Scenario:  Finding a WorkFlowAlert
   Given  I have account "A1RO38YHOQ13873" open in Latitude
   Then I can check for workflow Alerts matching the following details
  #|FIELD NAME				|VALUE																												|M|NOTES																					|
   |Count							|1																														|	|	Number of Matches expected										|
   |PagesToCheck			|1																														|	|	The number of pages of comments to check			|
   |ID								|340																													| |	will ignore the time element in the notes			|
   |Alert Date				|$date,-5,dd/MM/yyyy																					|	|																								|
   |Business Area			|Back Office																									| | 																							|
   |WorkFlow Name			|Dispute Feed																									| | 																							|  
   |Alert Description	|Dispute Record Created/Updated On Active Complaint Account		| | 																							|  
   |Action Required		|Please Review Account And Action Accordingly									| | 																							| 
 
 #RS0873738833ABC 
 #A1RO38YHOQ13873