
Feature: basic vulnerabilities
  basic one person Vulnerabilites

  Background:
  Given I am logged into the Latitude Desktop

  @basicVulnerable  @Lat2.0_ST_Vulnerable001
  Scenario: Care and hardship record of type PHYSICAL DIFFICULTY. Check the correct batchapi and SMS comms outputs.
  Given that I am running test "Lat2.0_ST_Vulnerable001"
  
 	And that I have an account open in Latitude with the following details 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
		|Client								|$defaultClient													|	|																															|   
		|Stage								|Collections														|	|																															|
		|Household Size				|1																			|	|																															|
		
  Then I can amend "Preferred Contact Method" to "SMS" for "$customerName,1"
   		
	Then I can add a Care and Hardship record with the following details
	 #|FIELD NAME						|VALUE																	|M|NOTES																												|
	  |Customer							|$customerName,1												|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|
	 	|Care Type						|Physical Difficulty										|X|																															|
		|Financial Hardship		|IMPACT - Manage												|X|																															|
	 	|Confirmed Care				|true																		|	|																															| 	
	 	
	Then I wait to run stage "2" until "2" "minutes" after the "first" stage
	
  Then I can search for a care record with the following details
	 #|FIELD NAME								|VALUE											|M|NOTES																																						|
	 	|Count										|1													| |Expected number of matching records																							|
	  |Customer									|$customerName,1						| |Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
	 	|Care Type								|Physical Difficulty				| |																																									|
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
		|Account Overview							|Active Vulnerable		|Yes - Physical Difficulty												|
		|Account Position Overview		|Desk									|COLLECTIONS VULNERABLE														|
		|Active Hold Details					|Hold Type Level			|Debtor																						|
		|Active Hold Details					|Hold Reason					|																									|	
		|Active Hold Details					|Hold Expiry Date			|$startDate,+30,dd/MM/yyyy												|	
		
	 And I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE													|M|NOTES																																						|
   	|Count								|1															| |Expected number of matching records																							|
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
 	  |CareType											|Physical Difficulty					| |																																									|
 	  |HardshipType									|IMPACT - Manage							| |																																									|
 	  |Comments											|															| |																																									|
 	  |Status												|															| |																																									|
 	  |Consent											|0														| |																																									| 	  
  	|HoldDaysApproved							|1														| |																																									|
 	  |HoldExpirationDate						|$startDate,+30,yyyy-MM-dd		| |																																									|
 	  |Consent											|0														| |																																									| 	  	  
		|HoldDays											|30														| |																																									|
		|CareProofRequested						|0														| |																																									|
 	  |CareProofReceived						|0														| |																																									| 	  	  
		|HoldDays											|30														| |																																									|		
		|HardshipProofRequested				|0														| |																																									|
 	  |HardshipProofReceived				|0														| |																																									| 	  	  
		|Confirmed										|1														| |																																									|		
		|DateClosed										|															| |																																									| 	  	  
		|VulnerableDriverType					|Health												| |																																									|		

	Then I wait to run stage "3" until "1" "calendarDay" after the "first" stage
	
	And I can check if a communication was sent with the following details
   #|FIELD NAME				|VALUE												|M|NOTES																																						|
  	|Count						|1														| |Expected number of matching records																							|
    |Method						|SMS								 					|	|																																									|
   	|Code							|VUN01												|	|																																									|
   	|Date Requested		|$startDate,+0,dd/MM/yyyy			|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy			|  	
   	|Recipient				|$customerName,1							|	|																																									|
   	|Subject    			|															| |name of customer or $customerName,1 or 2	e.g.$customerName,1											|
   	
  Then I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE													|M|NOTES																																						|
    |Count								|1															| |Expected number of matching records																							|
    |Date Created					|$startDate,+0,dd/MM/yyyy				|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
   	|Event								|Communications									|	|																																									|
   	|Value								|VUN01													| |																																									|
   	|Debtor ID						|$customerId,1									| |customerId or $customerId,1 or 2	e.g.$customerId,1																|
		
	And I can check if the batchAPI entry contains the following JSON values
   #|FIELD NAME										|VALUE												|M|NOTES																																						|	
 	  |FullName											|$customerName,1							|	|																																									|
 	  |Title												|$customerTitle,1							|	|																																									| 
 	  |FirstName										|$customerFirstName,1					|	|																																									| 
 	  |LastName											|$customerSurname,1						|	|																																									| 
		|AccountReference							|$accountNumber								|	|																																									| 
 	  |Vulnerable_Reason						|Physical Difficulty					| |																																									|
 	  |Vulnerable_Hold_Expiration		|$startDate,+30,yyyy-MM-dd		| |																																									|
		|Vulnerable_Hold_Days					|30														| |																																									|   	

	Then Test is complete
	
	