@Events
Feature: Events
  Feature file for adding or checking Household Notes

  Background:
  Given I am logged into the Latitude Desktop



   
   @events @checkEvents
  Scenario:  Finding an Event
   Given  I have account "A1SI87726036253" open in Latitude
   Then I can check for events matching the following details
  #|FIELD NAME				|VALUE																				|M|NOTES																																						|
   |Count							|1																						|	|	Number of Matches expected																											|
   |Date							|$date,-2,dd/MM/yyyy													| |	will ignore the time element in the notes																				|
   |User							|jbroad - jbroad															| | 																																								|
   |Event							|Queue Level Changed													| | 																																								|  
   |Details						|Queue level changed to 599 ACCOUNT WORKED		| | 																																								|  
