@incomeAndExpenditure
Feature: HouseholdNotes
  Feature file for adding Income and Expenditure

  Background:
  Given I am logged into the Latitude Desktop


	@incomeAndExpenditure @incomeAndExpenditureAdd
  Scenario:  Adding Income and Expenditure
   Given  I have account "A1CY08656250323" open in Latitude
   Then I can add "Partial" Income and Expenditure figures
	 #|TAB										|PANEL											|FIELD NAME																	|FREQUENCY		|VALUE											|M|NOTES											|
		|Summary								|Status											|Customers Employment Status								|							|Not Working due to Health	|	|														|
   	|Income									|Benefits										|Jobseekers Allowance (Contribution Based)	|Fortnightly	|	20												|	|														|   
   	|Expenditure (Fixed)		|Pensions and Insurances 		|Mortgage Payment Protection								|Fortnightly	|	35												|	|														|  
   	|Expenditure (Flexible) |Communications and Leisure	|Newspapers, Magazines, Stationary, Postage	|Monthly			|	15												|	|														| 
		|Expenditure (Flexible) |Food and Housekeeping			|School Meals and Meals at Work							|4-Weekly			|	40												|	|														| 
		|Expenditure (Flexible) |Personal Costs							|Clothing and Footwear											|Annually			|	1500											|	|														| 
   	|Savings								|Savings										|Monthly Savings Amount											|							|	100												|	|														|   	
  	|Debts									|Monthly Non Priority Debts	|Store Card Arrears													|Weekly				|	2000/30										|	|														| 
		|Notes									|														|																						|							|	New Note									|	|														| 
		
		
 
 