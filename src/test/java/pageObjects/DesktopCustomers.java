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
		pageUtils.Scroll(300);
		pageUtils.DefaultImplictWait();
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
	
	
}
