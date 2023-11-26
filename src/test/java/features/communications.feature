@communications
Feature: Communications
  Feature file for checking what communications were sent out

  Background:
  Given I am logged into the Latitude Desktop


	@getCommunications @addComplaint
  Scenario:  checking for comms
   Given  I have account "SM65133721423" open in Latitude
   #Given  I have account "A2EE80657316496ABC" open in Latitude
   Then I can check if a communication was sent with the following details
    |Method						|Print							  			|
   	|Code							|NOA01											|
   	|Date Requested		|29/08/2023									|  
   	#date in format dd/MM/yyyy or delta from today in format $+x where x is an integer >=0
   	|Recipient				|1													| 
   	# name of customer or customer number 1-2
   	|Subject 					|1													| 
   	# name of customer or customer number 1-2	
And I can check if a communication was sent with the following details
    |Method						|SMS							  			|
   	|Code							|VUN01										|
   	|Date Requested		|$-25											| 
   	#date in format dd/MM/yyyy or delta from today in format $+x where x is an integer >=0    	
   	|Recipient				|Mr Hu Harper							|
   	|Subject    			|2									 			| 
   	# name of customer or customer number 1-2	   

