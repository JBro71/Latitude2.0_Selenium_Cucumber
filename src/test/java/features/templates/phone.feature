@phone
Feature: phones
  Feature file for adding, amending, deleting or checking phone

  Background:
  Given I am logged into the Latitude Desktop

@addPhone
  Scenario:  Adding an phone number
  Given  I have account "LB78012617239" open in Latitude				   
  Then I can add an phone number for "$customerName,2" 
   #|FIELD NAME						|VALUE													|M|NOTES																												|
   	|Phone Number					|07876544891										|X|																															|				
  	|Phone Extension			|122														|X|case sensitive																								|						
 		|Type									|Work														|X|case sensitive																								|
		|Status								|Good														|	|true/false																										|
		|Name									|pam														|	|true/false																										|
		|Is Correspondence		|true														|	|true/false																										|
		|Consent To Call			|true														|	|true/false																										|
		|Consent To Auto Dial	|true														|	|true/false																										|
		|Consent To SMS				|true														|	|true/false																										|
		|Consent To FAX				|true														|	|true/false																										|
		|Obtained From				|$customerName,2								|	|true/false																										|	
		|Method								|verbal													|	|written/verbal																								|	
		|Comments							|this is a comment							|	|																															|
	
	@findPhoneNumber
  Scenario:  Search for a phone number
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can search for a phone number with following details
	 #|FIELD NAME										|VALUE													|M|NOTES																												|
		|Count												|1															| |																															|	
		|Number												|07184 248232										| |																															|				 
	 	|Type 												|Mobile													| |																															|				
		|Consent to Call							|false													| |																															|		
		|Consent to Call via dialer		|false													| |																															|	
		|Consent to Fax								|true														| |																															|							
		|Consent to Sms								|true														| |																															|	
		|Status												|true														| |true = good, false = bad																			|									
		|Customer											|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|																																
		|Attempts											|0															| |																															|	
		|Last Attempt									|																| |																															|	
		|Name													|																| |																															|	
		|Correspondence								|false													| |																															|	
		|on hold											|																| |																															|	
		|hold expiration							|																| |																															|	
  
  
  	
		@findAndUpdatePhoneNumber
  Scenario:  Seach for and edit a  phone number
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can search for a phone number with following details
	 #|FIELD NAME								|VALUE													|M|NOTES																												|
		|Count										|1															| |																															|	
		|Number										|07184 248232										| |																															|				 
	 	|Type 										|Mobile													| |																															|				
		|Customer									|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|	
 Then I can update the phone number above with following values
   #|FIELD NAME								|VALUE													|M|NOTES																																				|
 		|Type											|Home														|X|case sensitive																																|
		|Status										|Good														|	|true/false																																		|
		|Customer	on Account			|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1									|	
		|Name											|pam														|	|true/false																																		|
		|on Hold									|true														| |																																							|
	 	|Hold Expiration Date			|01/02/2024											|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY	|
	 	|Is Correspondence				|true														|	|true/false																																		|
		|Consent To Call					|true														|	|true/false																																		|
		|Consent To Auto Dial			|true														|	|true/false																																		|
		|Consent To SMS						|true														|	|true/false																																		|
		|Consent To FAX						|true														|	|true/false																																		|
		|Obtained From						|$customerName,1								|	|true/false																																		|	
		|Method										|verbal													|	|written/verbal																																|	
		|Comments									|this is a comment							|	|																																							|		
		
		@updatePhoneNumber
  Scenario:  Seach for and edit a  phone number
  Given  I have account "LB78012617239" open in Latitude				   
 	Then I can update phone number "01406 727156" belonging to "$customerName,1"
   #|FIELD NAME								|VALUE													|M|NOTES																																				|
 		|Type											|Home														|	|case sensitive																																|
		|Status										|Good														|	|true/false																																		|
		|Customer	on Account			|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1									|	
		|Name											|pam														|	|true/false																																		|
		|on Hold									|true														| |																																							|
	 	|Hold Expiration Date			|01/02/2024											|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY	|
	 	|Is Correspondence				|false													|	|true/false																																		|
		|Consent To Call					|false													|	|true/false																																		|
		|Consent To Auto Dial			|false													|	|true/false																																		|
		|Consent To SMS						|false													|	|true/false																																		|
		|Consent To FAX						|false													|	|true/false																																		|
		|Obtained From						|$customerName,1								|	|true/false																																		|	
		|Method										|written												|	|written/verbal																																|	
		|Comments									|this is a comment							|	|																																							|		
		
		
		