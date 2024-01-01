@insolvency
Feature: Insolvency Template
  Feature file for adding, amending, deleting or checking Insolvency
  Background:
  Given I am logged into the Latitude Desktop



  

	@insolvency @insolvencyAdd
  Scenario:  Adding Insolvency
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "add" an Insolvency with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
    |party												|$customerName,1	  			|	|																																									|
   	|insolvency type							|Bankruptcy Order					|	|																																									|
   	|start date										|$date,+0,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
   	|expiration date							|$date,+20,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|insolvency date							|$date,-10,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	#|discharged date							|$date,-2,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|indefinite discharge					|false										|	|																																									|  	
  	|iva failed date							|$date,-5,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|dro date											|$date,-7,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|dro schedule received				|$date,-12,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|	
   	|dro amount										|100											|	|																																									|   
   	|courts												|Barnsley Law Courts			|	|																																									|  
   	|insolvency practitioner			|AFA Insolvency						|	|																																									|   	
   	|status												|Current									|	|																																									|  	
   	|court reference number				|123456										|	|																																									|  	
   	|home owner										|true											|	|																																									| 	
   	|legal action required				|true											|	|																																									| 	
   	|moratorium period expired		|true											|	|																																									| 	
   	|comment											|this is a comment				|	|																																									| 
   		
  @insolvency @insolvencyUpdate
  Scenario:  Update Insolvency
   Given  I have account "A3EE80657316494ABC" open in Latitude 		
  Then I can "update" an Insolvency with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
    |party												|$customerName,1	  			|	|																																									|
   	|insolvency type							|Debt Relief Orders				|	|																																									|
   	|start date										|$date,+1,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
   	|expiration date							|$date,+21,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|insolvency date							|$date,-11,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|indefinite discharge					|false										|	|																																									|  	
  	|discharged date							|$date,-3,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|iva failed date							|$date,-6,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|dro date											|$date,-8,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|
  	|dro schedule received				|$date,-11,dd/MM/yyyy			|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY			|	
   	|dro amount										|1000											|	|																																									|   
   	|courts												|Aldershot Justice Centre	|	|																																									|  
   	|insolvency practitioner			|Adcrofts									|	|																																									|   	
   	|status												|Completed								|	|																																									|  	
   	|court reference number				|654321										|	|																																									|  	 	
   	|home owner										|false										|	|																																									| 	
   	|legal action required				|false										|	|																																									| 	
   	|moratorium period expired		|false										|	|																																									| 	
   	|comment											|NEW comment							|	|																																									| 
 
  @insolvency @insolvencyDelete
  Scenario:  Delete Insolvency
  Given  I have account "A3EE80657316494ABC" open in Latitude 		
  Then I can delete an Insolvency for customer "$customerName,1"
  
  
  @insolvency @insolvencyCheck
  Scenario:  Adding Insolvency
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "check" an Insolvency with the following details
   #|FIELD NAME										|VALUE										|M|NOTES																																						|
    |party												|$customerName,1	  			|	|																																									|
   	|home owner										|true											|	|																																									|
 