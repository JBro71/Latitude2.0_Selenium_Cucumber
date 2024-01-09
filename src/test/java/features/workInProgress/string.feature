@vulnerable
Feature: Latitude 2.0 Vulnerabilites
  Latitude 2.0 vulnerabilites

  @addVulnerable 
  Scenario: Creating Care and Hardship records
  # Then I can convert field names to a string
  #|FIELD NAME						|VALUE													|M|NOTES																												|
   #|Address Owner				|																|x|																															|				
  	#|Type of Address			|Home														|X|case sensitive																								|						
	
		
		 @addVulnerable 
  Scenario: creating I and E strings
   Then I can convert I and E field names to a string
		#|TAB										|PANEL											|FIELD NAME																	|FREQUENCY		|VALUE											|M|NOTES											|
   	|Expenditure (Flexible) |Communications and Leisure	|Newspapers, Magazines, Stationary, Postage	|Monthly			|	15												|	|														| 
		|Expenditure (Flexible) |Food and Housekeeping			|School Meals and Meals at Work							|4-Weekly			|	40												|	|														| 
		|Expenditure (Flexible) |Personal Costs							|Clothing and Footwear											|Annually			|	1500											|	|														| 
   	|Savings								|Savings										|Monthly Savings Amount											|							|	100												|	|														|   	
  	|Debts									|Monthly Non Priority Debts	|Store Card Arrears													|Weekly				|	2000/30										|	|														| 
		|Notes									|														|																						|							|	New Note									|	|											|