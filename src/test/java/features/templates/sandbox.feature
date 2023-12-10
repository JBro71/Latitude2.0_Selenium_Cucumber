@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop

	@findEmailAddress
  Scenario: Creating Care and Hardship records
   Given  I have account "LB78012617239" open in Latitude
   Then I can search for an Email adresss with following details
	 #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Count								|1															|X|																															|		 
	 	|Email Address				|debtor1email4@email.com				|X|																															|				
		|Contact							|Mrs Ciaran Colon								|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|		
		|Primary							|false													|X|																															|	
		|Type									|Home														|X|																															|							
		|Consent							|true														|X|																															|	
		|Correspondence				|false													|X|																															|	
		|Status								|true														|X|true = good, false = bad																|										