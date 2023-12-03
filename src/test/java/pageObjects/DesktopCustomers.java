package pageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopCustomers {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
		
		public DesktopCustomers(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
	public void getCustomers() throws InterruptedException  {
		Logging.logToConsole("INFO","Desktopcustomers/getCustomers: start");
		if (pageUtils.testMap.get("customer1") == null) {;
		//HashMap<String,String> dataMap = new HashMap<String, String>();
		
		//scroll the screen to the section with the Customer panel;
		pageUtils.ReturnHome();
		pageUtils.defaultImplictWait();
		//Click the customer down arrow
		WebElement buttonCustomer = driver.findElement(By.xpath("//tasks-customer-menu[@class='ng-scope ng-isolate-scope']/div/ng-switch/span"));
		// if menu is closed then open it
		if (buttonCustomer.getAttribute("class").equalsIgnoreCase("icon-caret-right ng-scope")){buttonCustomer.click();}
		
		List<WebElement> customers = driver.findElements(By.xpath("//tasks-customer-menu[@class='ng-scope ng-isolate-scope']/div[2]/div"));
		//Store Debtor 1 details in the map
		customers.get(0).click();
		// page takes time to refresh. need to check the info on the page matches the customer selected
		for( int i=0; i<=20; i++) {
			if(i>0) {Thread.sleep(200); }
		if(compareName(customers.get(0).getText())) {break;}
		}
		pageUtils.updateTestMap("customer1",customers.get(0).getText());
		Logging.logToConsole("DEBUG","Desktopcustomers/getCustomers: customer1: " + pageUtils.testMap.get("customer1"));
		// check if there is a second household member and if so if they are a customer or not
		
		if (customers.size() > 1) 
		{
			
			customers.get(1).click();
			// page takes time to refresh. need to check the info on the page matches the customer selected before proceeding
			for( int i=0; i<=20; i++) {
				if(i>0) {Thread.sleep(100); }
			if(compareName(customers.get(1).getText())) {break;}
			}
			
			Select relationDropdown = new Select(driver.findElement(By.xpath("//select[@ng-disabled='vm.canChangeRelation()']")));	
			//String x = relationDropdown.getFirstSelectedOption().getText();
			if(relationDropdown.getFirstSelectedOption().getText().equalsIgnoreCase("customer")) 
			{
				pageUtils.updateTestMap("customer2",customers.get(1).getText());
			}
		}
		Logging.logToConsole("DEBUG","Desktopcustomers/getCustomers: customer2: " + pageUtils.testMap.get("customer2"));
		
		//housekeeping
		//click back to customer1 and close the menu
		customers.get(0).click();
		driver.findElement(By.xpath("//tasks-customer-menu[@class='ng-scope ng-isolate-scope']/div/ng-switch/span")).click();
		pageUtils.ReturnHome();
		}
		//return dataMap;
	}
	
	public boolean compareName(String inputName) throws InterruptedException  {
		try {
			String name = null;		
			Select titleDropdown = new Select(driver.findElement(By.xpath("//div[@name='prefix']/select")));	
			String title = titleDropdown.getFirstSelectedOption().getText();
					
			if(!title.equalsIgnoreCase("none")) 
				{ 
				name = title + " ";
				}
	
			name = name + driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value") + " ";	
			name = name + driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
			Logging.logToConsole("DEBUG","Desktopcustomers/getCustomers: input name: " + inputName + " found name: " + name);
			if (inputName.equalsIgnoreCase(name)) {return true;}
		}catch(Exception e) {Logging.logToConsole("DEBUG","Desktopcustomers/getCustomers: compare name error");}
		return false;
	}
	
	public void amendCustomer(String customer, HashMap<String,String> paramsMap) throws Exception  {
			String action = "edit";
			String account = pageUtils.testMap.get("account");
			Logging.logToConsole("INFO","DesktopCustomers/amendCustomer/"+account+ "start");

			//open the customer menu, find the right record and wait for it to populate
			boolean found = selectCustomer(customer);
			
			//ensure all the keys in the map are lower case	
			HashMap<String,String> lowercaseParamsMap = new HashMap<String, String>();
			paramsMap.forEach((key, value) -> {
				lowercaseParamsMap.put(key.toLowerCase(), value);
			});
			
			Boolean check = false;
			if (action.equalsIgnoreCase("check")) {check = true;};


			if(action.equalsIgnoreCase("add"))	
			{	
				// insert add functionality here
			}
			
			
			//iterate through the keys that have a value and execute them in correct entry order
			String[] entryOrder = {"prefix","first name","last name","middle name","suffix","gender","aka name","is business",
					"business name","preferred contact method"}; 
			WebElement inputWebElement; // temp element to hold values in case statement
			Select dropdown;
			Boolean isSelected;
			for (String i : entryOrder) {
				String value = lowercaseParamsMap.get(i);
				if (value != null )
				{ 
				Logging.logToConsole("DEBUG", "DesktopCustomers/amendCustomer/"+account+ " Key: " +i+ " Value: "+ value);	
				switch (i) {
				
				case "prefix":
					/*
					if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeCategory']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
					}
					else {
					dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.category']")));
					dropdown.selectByVisibleText(value);
					}
					*/
					break;
				case "first name":
					/*
					if (check){
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.dateReceived']"));
						resultsMap.put(i,inputWebElement.getAttribute("value"));  
							}
							else {
							inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm."+action+"Record.dateReceived']"));
							inputWebElement.clear();
							inputWebElement.sendKeys(value);
							}
						*/
					break;
				case "last name":
					
					break;
				case "middle name":
					
					break;	
				case "suffix":	
					
					break;
				case "gender":
				
					break;	
				case "aka name":	
					
					break;
				case "is business":	

					break;	
				case "business name":

					break;
				case "preferred contact method":
					dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.contactData.preferredContactMethod']")));
					if (check){
						resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
						dropdown.selectByVisibleText(value);
						}
					break;
				case "justified":
					
					break;	
				case "outcome":
					
					break;	
				case "proof received":
					
					break;
				case "proof requested":
					
					break;
				case "proof required":
					break;
				case "recourse date":
					break;
				case "referred by":
					break;			
					}
				}
			}
			
			WebElement button;
			switch (action.toLowerCase()) {
			case "add":
				button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
				if (button.isEnabled() == true) {button.click();
				Logging.logToConsole("INFO","DesktopDispute/dispute: New dispute submitted");}
					else { Logging.logToConsole("WARNING","DesktopDMC/AddDispute: unable to add Dispute: " + action + " button disabled");}
				break;
			case "edit":
				button = driver.findElement(By.xpath("//button[@ng-click='vm.save()']"));
				if (button.isEnabled() == true) {button.click();
				Logging.logToConsole("INFO","DesktopCustomers/amendCustomer/"+account+ "update saved");}
					else { throw new Exception("DesktopCustomers/amendCustomer/"+account+ " :  cannot save update. button disabled");}
				break;
			case "close":
				driver.findElement(By.xpath("//button[normalize-space()='Close Dispute']")).click();
				
				break;
			case "reopen":
				driver.findElement(By.xpath("//button[normalize-space()='ReOpen']")).click();
				break;	
				}
			
			//driver.switchTo().defaultContent(); //switch out of iframe
			pageUtils.ReturnHome();
			
			//return resultsMap;
		}	
		
	public boolean selectCustomer(String inputCustomer) throws InterruptedException {
			// open the customer tab, click on the correct customer and wait for it to open 
			pageUtils.ReturnHome();
			pageUtils.defaultImplictWait();
			//Click the customer down arrow
			WebElement buttonCustomer = driver.findElement(By.xpath("//tasks-customer-menu[@class='ng-scope ng-isolate-scope']/div/ng-switch/span"));
			// if menu is closed then open it
			if (buttonCustomer.getAttribute("class").equalsIgnoreCase("icon-caret-right ng-scope")){buttonCustomer.click();}
			
			List<WebElement> customers = driver.findElements(By.xpath("//tasks-customer-menu[@class='ng-scope ng-isolate-scope']/div[2]/div"));
			for ( WebElement customer: customers) {
				// click on the matching customer then wait for it to open fully
				if(customer.getText().equalsIgnoreCase(inputCustomer)) {
					customer.click();
					for( int i=0; i<=20; i++) {
						if(i>0) {Thread.sleep(200); }
					if(compareName(customer.getText())) {return true;}
					}
				}
			}
		return false;	
	}
	
}
