@email 
Feature: Email
  Feature file for adding, amending, deleting or checking email

  Background:
  Given I am logged into the Latitude Desktop


	@email @amendACustomer
  Scenario:  Adding an Email Address
   Given  I have account "LB78012617239" open in Latitude																													|					   
	Then I can add an email address for "$customerName,1" 
   #|FIELD NAME						|VALUE													|M|NOTES																												|
   	|Email Address				|debtor1email1@email.com				|X|																															|				
  	|Type									|Home														|X|case sensitive																								|						
 		|Status								|Good														|X|case sensitive																								|
		|Is Primary						|true														|	|true/false																										|
		|Is Correspondence		|true														|	|true/false																										|
		|Consent to Email			|true														|	|true/false																										|
		|Obtained From				|$customerName,2								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Method								|written												|	|written/verbal																								|
		|Comments							|this is a comment							|	|																															|					   
    
    