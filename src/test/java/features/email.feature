@email 
Feature: Email
  Feature file for adding, amending, deleting or checking email

  Background:
  Given I am logged into the Latitude Desktop


	@complaint @amendACustomer
  Scenario:  Adding an Email Address
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can add and email address for "Mr Harlan Gilbert" 
   #|FIELD NAME						|VALUE								|M|NOTES																							|
   	|Email Address				|debtor1@email.com		|X|																										|				
  	|Type									|Home									|X|case sensitive																			|						
 		|Status								|Good									|X|case sensitive																			|
		|Is Primary						|true									|	|true/false																					|
		|Is Correspondence		|true									|	|true/false																					|
		|Consent to Email			|true									|	|true/false																					|
		|Obtained From				|Mr Harlan Gilbert		|	|actual name (case sensitive) OR customer1/customer2|
		|Method								|written							|	|written/verbal																			|
		|Comments							|this is a comment		|	|																										|					   

    
    