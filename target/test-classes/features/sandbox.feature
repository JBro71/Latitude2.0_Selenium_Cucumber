@tag
Feature: DMC
  Feature file for adding, amending or deleting DMC

  Background:
  Given I am logged into the Latitude Desktop

	 Scenario: updating  A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "edit" a Dispute with the following details
   	|Dispute Key									|category								|
   	# Dispute Key values:  first, "last", <a number>, "dispute id", "category"
    |Dispute ID										|47 							  			|   
    |Category											|Client Dispute						|
    |Referred By	 								|Client										|
    |Recourse Date								|													|
   	|justified										|No												|
    |Outcome											|Dispute Raised in Error 	|
