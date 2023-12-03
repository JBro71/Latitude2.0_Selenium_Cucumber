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
   #|FIELD NAME				|VALUE								|M|NOTES																																						|
    |Method						|Print							  |	|																																									|
   	|Code							|NOS01								|	|																																									|
   	|Date Requested		|29/11/2023						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|  
   	|Recipient				|$customerName,1			| |name of customer or $customerName,1 or 2	e.g.$customerName,1											|
   	|Subject 					|$customerName,1			| |name of customer or $customerName,1 or 2	e.g.$customerName,1											|
   	
And I can check if a communication was sent with the following details
   #|FIELD NAME				|VALUE								|M|NOTES																																						|
    |Method						|SMS							  	|	|																																									|
   	|Code							|VUN01								|	|																																									|
   	|Date Requested		|$date,-25,dd/MM/yyyy	|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|  	
   	|Recipient				|Mr Hu Harper					|	|																																									|
   	|Subject    			|$customerName,2			| |name of customer or $customerName,1 or 2	e.g.$customerName,1											|

