@customers 
Feature: Customers
  Feature file for adding, amending, deleting or checking household members

  Background:
  Given I am logged into the Latitude Desktop


	@complaint @amendACustomer
  Scenario:  Amending a Customer
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can amend "Preferred Contact Method" to "Email" for "Mr Harlan Gilbert" 
    

    
    