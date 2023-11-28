@email 
Feature: Email
  Feature file for adding, amending, deleting or checking email

  Background:
  Given I am logged into the Latitude Desktop


	@complaint @amendACustomer
  Scenario:  Adding an Email Address
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can add and email address for "Mr Harlan Gilbert" 
   	|Email Address				|debtor1@email.com		|
  	|Type									|Home									|
 		|Status								|Good									|
		|Is Primary						|true									|
		|Is Correspondence		|true									|
		|Consent to Email			|true									|
		|Obtained From				|Mr Harlan Gilbert		|
		|Method								|written							|
		|Comment 							|this is a comment		|						   

    
    