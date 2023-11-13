
@tag
Feature: card payments
  I want to use this template for my feature file

  Background:
  Given I am logged into the Latitude Desktop

	@tag1
  Scenario:  adding a payment card
   Given  I have account "PT68899261611" open in Latitude
   Then if I add a payment card with the following details
   |card number:4263970000005262|card name:John Smith|expiry:12/2025|card cv2:586|
   Then I can add a card arrangement using the following details
   |method:CC-5262|total:$arrears|payments:3|
   
   
   
