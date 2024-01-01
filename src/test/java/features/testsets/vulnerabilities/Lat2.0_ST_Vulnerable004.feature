
Feature: basic vulnerabilities
  basic one person Vulnerabilites

  Background:
  Given I am logged into the Latitude Desktop

  @basicVulnerable 
  Scenario: Care and hardship record of type MENTAL HEALTH. Check the correct batchapi and SMS comms outputs.
  Given that I am running test "Lat2.0_ST_Vulnerable004"
  
 	And that I have an account open in Latitude with the following details 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
		|Client								|$defaultClient													|	|																															|   
		|Stage								|Collections														|	|																															|
		|Household Size				|1																			|	|																															|
		
  #Then I can amend "Preferred Contact Method" to "SMS" for "$customerName,1"
   		
	Then I can add a Care and Hardship record with the following details
	 #|FIELD NAME						|VALUE																	|M|NOTES																												|
	  |Customer							|$customerName,1												|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|
	  |Have Consent					|true																		|X|																															|
	 	|Care Type						|Mental Health													|X|																															|
		|Financial Hardship		|IMPACT - Communicate										|X|																															|
	 	|Confirmed Care				|true																		|	|																															| 	
	 	
	Then I wait to run stage "2" until "2" "minutes" after the "first" stage
	
  Then I can search for a care record with the following details
	 #|FIELD NAME								|VALUE											|M|NOTES																																						|
	 	|Count										|1													| |Expected number of matching records																							|
	  |Customer									|$customerName,1						| |Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
	 	|Care Type								|Mental Health							| |																																									|
		|Date Open								|$startDate,+0,dd/MM/yyyy		|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
	 	|Status										|														| |																																									|
		|Date Closed							|														|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
		
	And I can check the selected Care and Hardship record contains the following details
	 #|FIELD NAME								|VALUE											|M|NOTES																																						|
	  |Hold Days								|30													|	|																																									|
	  |hold days approved				|true												|	|																																									|
	  |Expiration Date					|$startDate,+30,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy			|	
	
	Then the anchor panels will display the following values
	 #|ANCHOR PANEL									|FIELD								|EXPECTED VALUE																		|
		|Account Overview							|Active Vulnerable		|Yes - Mental Health															|
		|Account Position Overview		|Desk									|COLLECTIONS VULNERABLE														|
		|Active Hold Details					|Hold Type Level			|Debtor																						|
		|Active Hold Details					|Hold Reason					|																									|	
		|Active Hold Details					|Hold Expiry Date			|$startDate,+30,dd/MM/yyyy												|	
		
	 And I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE													|M|NOTES																																						|
   	|Count								|1															| |Expected number of matching records, zero if no match expected										|
    |Date Created					|$startDate,+0,dd/MM/yyyy				|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
   	|Event								|Vulnerable											|	|																																									|
   	|Debtor ID						|$customerId,1									| |customerId or $customerId,1 or 2	e.g.$customerId,1																|
	
	And I can check if the batchAPI entry contains the following JSON values
   #|FIELD NAME										|VALUE												|M|NOTES																																						|	
 	  |FullName											|$customerName,1							|	|																																									|
 	  |Title												|$customerTitle,1							|	|																																									| 
 	  |FirstName										|$customerFirstName,1					|	|																																									| 
 	  |LastName											|$customerSurname,1						|	|																																									| 
		|AccountRef										|$accountNumber								|	|																																									| 
 	  |CareType											|Mental Health								| |																																									|
 	  |HardshipType									|IMPACT - Communicate					| |																																									|
 	  |Comments											|															| |																																									|
 	  |Status												|															| |																																									|
  	|HoldDaysApproved							|1														| |																																									|
 	  |HoldExpirationDate						|$startDate,+30,yyyy-MM-dd		| |																																									|
 	  |Consent											|1														| |																																									| 	  	  
		|HoldDays											|30														| |																																									|
		|CareProofRequested						|0														| |																																									|
 	  |CareProofReceived						|0														| |																																									| 	  	  
		|HardshipProofRequested				|0														| |																																									|
 	  |HardshipProofReceived				|0														| |																																									| 	  	  
		|Confirmed										|1														| |																																									|		
		|DateClosed										|															| |																																									| 	  	  
		|VulnerableDriverType					|Health												| |																																									|		

	Then I wait to run stage "3" until "1" "calendarDay" after the "first" stage
	
	And I can check if a communication was sent with the following details
   #|FIELD NAME				|VALUE												|M|NOTES																																						|
   	|Count						|0														| |not expecting output for this high risk care type																|
   	|Date Requested		|$startDate,+0,dd/MM/yyyy			|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy			|  	
   	|Recipient				|$customerName,1							|	|																																									|
   	
  Then I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE													|M|NOTES																																						|
		|Count								|0															| |Expected number of matching records, zero if no match expected										|
    |Date Created					|$startDate,+0,dd/MM/yyyy				|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
   	|Event								|Communications									|	|																																									|
   	|Debtor ID						|$customerId,1									| |customerId or $customerId,1 or 2	e.g.$customerId,1																|   	

	Then Test is complete
	
	