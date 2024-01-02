@email 
Feature: Email
  Feature file for adding, amending or checking email

  Background:
  Given I am logged into the Latitude Desktop


	@addEmail
  Scenario:  Adding an Email Address
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can add an email address for "$customerName,1" 
   #|FIELD NAME						|VALUE													|M|NOTES																												|
   	|Email Address				|debtor1email10@email.com				|X|																															|				
  	|Type									|Home														|X|case sensitive																								|						
 		|Status								|Good														|X|case sensitive																								|
		|Is Primary						|true														|	|true/false																										|
		|Is Correspondence		|true														|	|true/false																										|
		|Consent to Email			|true														|	|true/false																										|
		|Obtained From				|$customerName,2								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Method								|written												|	|written/verbal																								|
		|Comments							|this is a comment							|	|																															|					   	
		
		
		@findEmailAddress
 	 Scenario: Creating Care and Hardship records
   Given  I have account "LB78012617239" open in Latitude
   Then I can search for an email address with following details
	 #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Count								|1															|X|																															|		 
	 	|Email Address				|debtor1email4@email.com				|X|																															|				
		|Contact							|Mrs Ciaran Colon								|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|		
		|Primary							|false													|X|																															|	
		|Type									|Home														|X|																															|							
		|Consent							|true														|X|																															|	
		|Correspondence				|false													|X|																															|	
		|Status								|true														|X|true = good, false = bad																			|		
		
		
		
		@findAndUpdateEmailAddress
 	 Scenario: Creating Care and Hardship records
   Given  I have account "LB78012617239" open in Latitude
   Then I can search for an email address with following details
	 #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Count								|1															|X|																															|		 
	 	|Email Address				|debtor1email4@email.com				|X|																															|				
		|Contact							|Mrs Ciaran Colon								|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|								
   Then I can update the email address above with following details
   #|FIELD NAME						|VALUE													|M|NOTES																												|
    |Type									|Work														|X|case sensitive																								|						
 		|Status								|Bad														|X|case sensitive																								|
		|Is Primary						|false													|	|true/false																										|
		|Is Correspondence		|false													|	|true/false																										|
		|Consent to Email			|false													|	|true/false																										|
		|Obtained From				|$customerName,2								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Method								|verbal													|	|written/verbal																								|
		|Comments							|new Comment										|	|																															|
		
		@updateEmailAddress
  Scenario:  Seach for and edit a  phone number
  Given  I have account "LB78012617239" open in Latitude				   
 Then I can update email address "debtor1email1@email.com" belonging to "Mrs Ciaran Colon"				
   #|FIELD NAME						|VALUE													|M|NOTES																												|
    |Type									|Work														|X|case sensitive																								|						
 		|Status								|Bad														|X|case sensitive																								|
		|Is Primary						|false													|	|true/false																										|
		|Is Correspondence		|false													|	|true/false																										|
		|Consent to Email			|false													|	|true/false																										|
		|Obtained From				|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Method								|verbal													|	|written/verbal																								|
		|Comments							|new Comment										|	|																															|
		
		