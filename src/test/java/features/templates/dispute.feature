@disputes
Feature: Disputes
  Feature file for adding, amending, deleting or checking Disputes

  Background:
  Given I am logged into the Latitude Desktop

	@dispute @disputeAdd
  Scenario:  Adding A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "add" a Dispute with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
    |Dispute Details							|Dispute comment	  			|	|																																									|
   	|Date Received								|$date,-37,dd/MM/YYYY			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
   	|Dispute Type									|Full Balance Dispute			|X|																																									|
    |Dispute Against							|Client										|	|																																									|
    |Category											|Civil Dispute						|X|																																									|
    |Referred By	 								|Client										|X|																																									|
    |justified										|Yes											|	|																																									|
    |Outcome											|Dispute Raised in Error	|	|																																									|
    |Proof Requested							|true 										|	|																																									|
    |Insufficient Proof Received	|true 										|	|																																									| 
    |Proof Received								|true 										|	|																																									|


	@dispute @DisputeCheck @check
  Scenario: checking A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Check" a Dispute with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
   	|Dispute Key									|category									|X|Dispute Key values:  first, last, <a number>, <dispute id>, category							|
    |Dispute ID										|47 							  			|	|																																									|
    |Dispute Details							|Dispute comment	  			|	|																																									|
   	|Date Received								|$date,+0,dd/MM/YYYY			|	|	date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
   	|Dispute Type									|Full Balance Dispute			|	|																																									|
    |Dispute Relates To						|Client										|	|																																									|
    |Dispute Against							|Client 									|	|																																									|
    |Category											|Civil Dispute						|	|																																									|
    |Referred By	 								|Client										|	|																																									|
    |Recourse Date								|													|	|																																									|
   	|justified										|Yes											|	|																																									|
    |Outcome											|Dispute Raised in Error 	|	|																																									|
    |Proof Required 							|false										|	|																																									|   
    |Proof Requested							|true 										|	|																																									| 
    |Insufficient Proof Received	|true 										|	|																																									| 
    |Proof Received								|true											|	|																																									|
    
    @dispute @disputeEdit
     Scenario: updating  A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "edit" a Dispute with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
   	|Dispute Key									|category									|X|Dispute Key values:  first, last, <a number>, <dispute id>, category							|
    |Dispute ID										|2 								  			|	|																																									|
    |Category											|Client Dispute						|	|																																									|
    |Referred By	 								|Client										|	|																																									|
    |Recourse Date								|													|	|																																									|
   	|justified										|No												|	|																																									|
    |Outcome											|Dispute Raised in Error 	|	|																																									|
    
		@dispute @disputeClose
     Scenario: close A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "close" a Dispute with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
   	|Dispute Key									|last											|X|Dispute Key values:  first, last, <a number>, <dispute id>, category							|
   	
   	@dispute @disputeReopen
     Scenario: reopen A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "reopen" a Dispute with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
   	|Dispute Key									|last											|X|Dispute Key values:  first, last, <a number>, <dispute id>, category							|

   