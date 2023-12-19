@tag
Feature: Test Stages Template
  Feature file test stages

  
	@testStages
  Scenario: find test data and run stages
  Given that I am running test "test1"
 	And that I have an account open in Latitude with the following details 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
		|Client								|$defaultClient													|	|																															|   
		|Stage								|Collections														|	|																															|
		|Household Size				|1																			|	|																															|
	Then I wait to run stage "2" until "10" "seconds" after the "last" stage
	Then I wait to run stage "3" until "10" "minutes" after the "last" stage	
	Then I wait to run stage "4" until "1" "hour" after the "first" stage
	Then I wait to run stage "5" until "1" "day" after the "first" stage
	Then Test is complete