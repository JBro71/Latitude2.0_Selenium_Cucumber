@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop


		@UpdateEmailAddress
  	Scenario: Creating Care and Hardship records
   	Given  I have account "LB78012617239" open in Latitude
 		Then I can update email address "debtor1email5@email.com" belonging to "Mrs Ciaran Colon"		
  #|FIELD NAME						|VALUE													|M|NOTES																												| 
	  |Type									|Work														|X|case sensitive																								|						
 		|Status								|Bad														|X|case sensitive																								|
		|Is Primary						|false													|	|true/false																										|
		|Is Correspondence		|false													|	|true/false																										|
		|Consent to Email			|false													|	|true/false																										|
		|Obtained From				|$customerName,2								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Method								|verbal													|	|written/verbal																								|
		|Comments							|new Comment										|	|																															|