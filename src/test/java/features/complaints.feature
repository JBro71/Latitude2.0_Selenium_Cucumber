@complaints
Feature: Complaints
  Feature file for adding, amending, deleting or checking Complaints

  Background:
  Given I am logged into the Latitude Desktop


	@complaint @addComplaint @stage1
  Scenario:  Adding A Complaint
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Add" a complaint with the following details
    |Dispute Details							|Dispute comment	  			|
   	|Date Received								|$-37											|
   	|Dispute Type									|Full Balance Dispute			|
    |Dispute Against							|Client										|
    |Category											|Civil Dispute						|
    |Referred By	 								|Client										|
    |justified										|Yes											|
    |Outcome											|Dispute Raised in Error 	|
    |Proof Requested							|true 										|    
    |Insufficient Proof Received	|true 										|     
    |Proof Received								|true 										|  


	@dispute @DisputeCheck @check
  Scenario: checking  A Complaint
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Check" a Dispute with the following details
   	|Dispute Key									|category								|
   	# Dispute Key values:  first, "last", <a number>, "dispute id", "category"
    |Dispute ID										|47 							  			|   
    |Dispute Details							|Dispute comment	  			|
   	|Date Received								|$-37											|
   	|Date Closed									|													|
   	|Dispute Type									|Full Balance Dispute			|
    |Dispute Relates To						|Client										|
    |Dispute Against							|Client 									|
    |Category											|Client Dispute						|
    |Referred By	 								|Client										|
    |Recourse Date								|													|
   	|justified										|Yes											|
    |Outcome											|Dispute Raised in Error 	|
    |Proof Required 							|false										|     
    |Proof Requested							|true 										|    
    |Insufficient Proof Received	|true 										|     
    |Proof Received								|true										|  
    
    @dispute @disputeEdit
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
    
		@dispute @disputeClose
     Scenario: close A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "close" a Dispute with the following details
   	|Dispute Key									|last								|
   	# Dispute Key values:  first, "last", <a number>, "dispute id", "category"
   	
   	@dispute @disputeReopen
     Scenario: reopen A Dispute
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "reopen" a Dispute with the following details
   	|Dispute Key									|last								|
   	# Dispute Key values:  first, "last", <a number>, "dispute id", "category"
   