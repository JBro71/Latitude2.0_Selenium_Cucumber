@batchAPI
Feature: BatchAPI template
  Feature file template for finding and checking the details on batch API entries

  Background:
  Given I am logged into the Latitude Desktop


	@getBatchApi 
  Scenario:  checking for batch API entries and then checking the JSON values
	Given  I have account "LB78012617239" open in Latitude
  Then I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE								|M|NOTES																																						|
		|Count								|1										| |	Expected number of matches, zero if no match expected														|																																						
    |Date Created					|29/11/2023						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|	
   	|Event								|Strategy Score				|	|																																									|
   	|Value								|911									| |																																									|
   	|Debtor ID						|$customerId,1				| |customerId or $customerId,1 or 2	e.g.$customerId,1																|

	
	And I can check if the batchAPI entry contains the following JSON values
   #|FIELD NAME						|VALUE								|M|NOTES																																						|	
   |AccountRef						|A2EE80657316496ABC		|	|case sensitive																																		|	
   |Name									|$customerName,2			|	|																																									|
   |Current0							|3660.04							| |																																									|
