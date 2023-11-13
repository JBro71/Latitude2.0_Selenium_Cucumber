package pageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testComponents.PageUtils;
import utils.Logging;

public class DesktopDMC {

	
	WebDriver driver;
	PageUtils pageUtils;
		
		public DesktopDMC(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
		}
	
	
	public void addDMC(HashMap<String,String> paramsMap, String action) throws InterruptedException  {
		Logging.logToConsole("INFO","DesktopDMC/AddDMC: "+action+" DMC");

		//scroll the screen to the section with the DMC
		pageUtils.Scroll(300);
		
		//Click the Debt management button then switch to iframe
		driver.findElement(By.xpath("//div[contains(text(),'Debt Management')]")).click();
		driver.switchTo().frame(driver.findElement(By.id("DebtManagement21")));

		
		//check how many customers there are and see if there is an existing DMC for either of them
		WebElement button = driver.findElement(By.xpath("//button[@id='deleteButton']"));
		//WebElement staticDropdown = driver.findElement(By.xpath("//select[@id='customer']"));
		Select customerDropdown = new Select(driver.findElement(By.xpath("//select[@id='customer']")));
		List<WebElement> customerCount = customerDropdown.getOptions(); //get size of drop down = number of customers
		
		for(int i=1; i <= customerCount.size(); i++) { // iterate through customers
			customerDropdown.selectByIndex(i - 1);	//select customer
			if (button.isEnabled() == true) {Logging.logToConsole("INFO","DesktopDMC/AddDMC: Existing DMC for customer: " + i );}
			else{Logging.logToConsole("INFO","DesktopDMC/AddDMC: NO Existing DMC for customer: " + i );}
		}
	
		
		// order in which the data should be entered
		String[] entryOrder = {"customer", "company","contact","client id", "creditor id", "date accepted", "amount accepted", "comment"};	
		
		//ensure all the keys in the map are lower case	
		HashMap<String,String> lowercaseParamsMap = new HashMap<String, String>();
		paramsMap.forEach((key, value) -> {
			lowercaseParamsMap.put(key.toLowerCase(), value);
		});
		
		//iterate through the keys that have a value and execute them in correct entry order
		WebElement inputWebElement; // temp element to hold values in case statement
		for (String i : entryOrder) {
			String value = lowercaseParamsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", "DesktopDMC/AddDMC: DMC Key: " +i+ " Value: "+ value);	
			switch (i) {
			case "company":
				driver.findElement(By.xpath("//input[@type='search']")).sendKeys(value);
				driver.findElement(By.xpath("//li[@id='vs1__option-0']")).click();
				break;
			case "contact":
				inputWebElement=driver.findElement(By.xpath("//input[@id='contact']"));
				inputWebElement.clear();
				inputWebElement.sendKeys(value); 
				break;
			case "client id":
				inputWebElement=driver.findElement(By.xpath("//input[@id='client-id']"));
				inputWebElement.clear();
				inputWebElement.sendKeys(value); 
				break;
			case "creditor id":
				inputWebElement=driver.findElement(By.xpath("//input[@id='creditor-id']"));
				inputWebElement.clear();
				inputWebElement.sendKeys(value); 
				break;
			case "date accepted":
				driver.findElement(By.xpath("//input[@id='date-accepted']")).sendKeys(value);
				break;	
			case "amount accepted":
				inputWebElement=driver.findElement(By.xpath("//input[@id='amount-accepted']"));
				inputWebElement.clear();
				inputWebElement.sendKeys(value); 
				break;
			case "comment":
				driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys(" New Comment: " + value);
				break;	
			case "customer":
				customerDropdown.selectByIndex(Integer.parseInt(value) - 1);
				if(action == "add") { dmcDelete();}
				break;	
			}
			}
		}
	
		switch (action.toLowerCase()) {
		case "add":
			button = driver.findElement(By.xpath("//button[@id='submitButton']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopDMC/AddDMC: New DMC submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddDMC: unable to click DMC: " + action + " button disabled");}
			break;
		case "update":
			button = driver.findElement(By.xpath("//button[@id='submitButton']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopDMC/AddDMC: DMC updated");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddDMC: unable to click DMC: " + action + " button disabled");}
			break;
		case "delete":
			dmcDelete();
			break;
		case "cancel":
			button = driver.findElement(By.xpath("//button[@id='cancelButton']"));
			if (button.isEnabled() == true) {button.click();}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddDMC: unable to click DMC: " + action + " button disabled");}
			break;	
			}
		driver.switchTo().defaultContent(); //switch out of iframe
		pageUtils.ReturnHome();
	}	
	
	public void dmcDelete() throws InterruptedException {
		
		WebElement button = driver.findElement(By.xpath("//button[@id='deleteButton']"));
		if (button.isEnabled() == true) 
			{
			button.click();	
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			//driver.switchTo().defaultContent(); //switch out of iframe
			//driver.switchTo().frame(driver.findElement(By.id("deleteModal___BV_modal_content_")));
			driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
			Logging.logToConsole("INFO","DesktopDMC/AddDMC: Existing DMC deleted");
			Thread.sleep(2000);
			//driver.switchTo().frame(driver.findElement(By.id("DebtManagement29")));
			}
			
	}
}
