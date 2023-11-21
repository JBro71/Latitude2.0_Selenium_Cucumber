@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop

	 @complaint @addComplaint
  Scenario:  Adding A Complaint
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Add" a complaint with the following details
    |Document					|None 										|
   	|Owner						|daird										|
   	|Status						|New - Complaint					|
    |Category					|Service Related					|
    |Referred By	 		|Client										|
    |justified				|Yes											|
    |Outcome					|Dispute Raised in Error 	|
    |Proof Requested							|true 										|    
    |Insufficient Proof Received	|true 										|     
    |Proof Received								|true 										|  

