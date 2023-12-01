@batchAPI
Feature: BatchAPI template
  Feature file template for finding and checking the details on batch API entries

  Background:
  #Given I am logged into the Latitude Desktop


	@getCommunications @addComplaint
  Scenario:  checking for comms
	#Given  I have account "A2EE80657316496ABC" open in Latitude
  Then I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE								|M|NOTES																																						|
    |Date Created					|$date,+0,dd/MM/YYYY	|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|	
   	|Event								|Communications				|	|																																									|
   	|Customer							|$customerName,1			| |name of customer or $customerName,1 or 2	e.g.$customerName,1											|
   	|CustomerID						|$customerId,1				| |customerId or $customerId,1 or 2	e.g.$customerId,1																|
	
	And I can check if the batchAPI entry contains the following JSON values
   #|FIELD NAME						|VALUE								|M|NOTES																																						|	
    |Date Created					|$+0							  	|	|date in format dd/MM/YYY or delta from today in format $+N e.g. $-1 = yesterday	|	
   	|Name									|Communications				|	|																																									|
   	|address line 1				|customer1						| | name of customer or customer number 1-2																					|

