@tag
Feature: Disputes
  Feature file for adding, amending, deleting or checking Disputes

  Background:
  Given I am logged into the Latitude Desktop

	@tag1
  Scenario:  Adding A Dispute
   #Given  I have account "A3EE80657316494ABC" open in Latitude
   #Then I can "Add" a Dispute with the following details
    #|Dispute Details	|Dispute comment	  			|
   	#|Date Received		|$-37								|
   	#|Dispute Type			|Full Balance Dispute			|
    #|Dispute Against	|Client										|
    #|Category					|Civil Dispute						|
    #|Referred By	 		|Client										|
    #|justified				|Yes											|
    #|Outcome					|Dispute Raised in Error 	|
    #|Proof Requested	|true 										|    
    #|Insufficient Proof Received	|true 										|     
    #|Proof Received		|true 										|  


	@tag1
  Scenario:  Adding A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Check" a Dispute with the following details
    |Dispute ID										|46								  			|   
    |Dispute Details							|Dispute comment	  			|
   	|Date Received								|$-37											|
   	|Date Closed									|													|
   	|Dispute Type									|Full Balance Dispute			|
    |Dispute Relates To						|Client										|
    |Dispute Against							|Client 									|
    |Category											|Civil Dispute						|
    |Referred By	 								|Client										|
    |Recourse Date								|													|
   	|justified										|Yes											|
    |Outcome											|Dispute Raised in Error 	|
    |Proof Required 							|false										|     
    |Proof Requested							|true 										|    
    |Insufficient Proof Received	|true 										|     
    |Proof Received								|true										|  
