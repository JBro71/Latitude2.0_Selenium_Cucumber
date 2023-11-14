
@tag
Feature: DMC
  Feature file for adding, amending or deleting DMC

  #Background:
  #Given I am logged into the Latitude Desktop

	@tag1
  Scenario:  Adding A DMC
  #Given  I have account "PT68899261611" open in Latitude
   Then I can Add a DMC with the following details
   |Customer	|1	|
   |company		|121 Debt Solutions		|
   |contact		|John Smith						|
   |client id	|123456								|
   |creditor id	|123456							|
   |Date Accepted|$+365						  |
