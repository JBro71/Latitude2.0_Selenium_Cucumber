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

public class DesktopDMC {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
		
		public DesktopDMC(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
	public HashMap<String, String> dmc(HashMap<String,String> paramsMap, String action) throws InterruptedException  {
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
		
		/*
		for(int i=1; i <= customerCount.size(); i++) { // iterate through customers
			customerDropdown.selectByIndex(i - 1);	//select customer
			if (button.isEnabled() == true) {Logging.logToConsole("INFO","DesktopDMC/AddDMC: Existing DMC for customer: " + i );}
			else{Logging.logToConsole("INFO","DesktopDMC/AddDMC: NO Existing DMC for customer: " + i );}
		}
		*/
	
		
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
			Logging.logToConsole("DEBUG", "DesktopDMC/DMC: DMC Key: " +i+ " Value: "+ value);	
			switch (i) {
			case "company":
				
				if (action.equalsIgnoreCase("check")) {
					inputWebElement =  driver.findElement(By.xpath("//span[@class='vs__selected']"));
					//Object attrs = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",inputWebElement);
					//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + "Attributes" +" "+ attrs);	
					Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + i +" "+ inputWebElement.getText());	
					resultsMap.put(i,inputWebElement.getText());  
				}else {
					driver.findElement(By.xpath("//input[@type='search']")).sendKeys(value);
					driver.findElement(By.xpath("//li[@id='vs1__option-0']")).click();
				}
				
				break;
			case "contact":
				inputWebElement=driver.findElement(By.xpath("//input[@id='contact']"));
				implementAction(action,inputWebElement,value,i);
				break;
			case "client id":
				inputWebElement=driver.findElement(By.xpath("//input[@id='client-id']"));
				implementAction(action,inputWebElement,value,i);
				break;
			case "creditor id":
				inputWebElement=driver.findElement(By.xpath("//input[@id='creditor-id']"));
				implementAction(action,inputWebElement,value,i);
				break;
			case "date accepted":
				Logging.logToConsole("DEBUG","DesktopDMC/AddDMC: " + "Date accepted" +" "+ value);	
				inputWebElement=driver.findElement(By.xpath("//input[@id='date-accepted']"));
				implementAction(action,inputWebElement,value,i); 
				break;	
			case "amount accepted":
				inputWebElement=driver.findElement(By.xpath("//input[@id='amount-accepted']"));
				implementAction(action,inputWebElement,value,i); 
				break;
			case "comment":
				inputWebElement=driver.findElement(By.xpath("//textarea[@id='comment']"));
				implementAction(action,inputWebElement,value,i); 
				break;	
			case "customer":
				if (action.equalsIgnoreCase("check")) {
					customerDropdown.selectByIndex(Integer.parseInt(value) - 1);
					resultsMap.put(i,value);  
					if(action == "add") { dmcDelete();}
				}

				break;	
			}
			}
		}
	
		switch (action.toLowerCase()) {
		case "add":
			button = driver.findElement(By.xpath("//button[@id='submitButton']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopDMC/dmc: New DMC submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddDMC: unable to click DMC: " + action + " button disabled");}
			break;
		case "update":
			button = driver.findElement(By.xpath("//button[@id='submitButton']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopDMC/dmc: DMC updated");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddDMC: unable to click DMC: " + action + " button disabled");}
			break;
		case "delete":
			dmcDelete();
			break;
		case "cancel":
			button = driver.findElement(By.xpath("//button[@id='cancelButton']"));
			if (button.isEnabled() == true) {button.click();}
				else { Logging.logToConsole("WARNING","DesktopDMC/dmc: unable to click DMC: " + action + " button disabled");}
			break;	
			}
		driver.switchTo().defaultContent(); //switch out of iframe
		pageUtils.ReturnHome();
		
		return resultsMap;
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
	
	public void implementAction(String action, WebElement inputWebElement, String value, String attribute ) throws InterruptedException {
		if (action.equalsIgnoreCase("check")) {
			//get the value and store in the results map
			try {
			//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + attribute +" "+ inputWebElement.getAttribute("value"));	
			resultsMap.put(attribute,inputWebElement.getAttribute("value"));  
			}catch (Exception e){resultsMap.put(attribute,"No Value"); }
		}
		else {
			if(attribute.equalsIgnoreCase("comment")) {
				value = "/n" + value;
				}else {
						inputWebElement.clear(); //don't clear the comments add to them
						} 
			
			inputWebElement.sendKeys(value); 
			}
		}

}
