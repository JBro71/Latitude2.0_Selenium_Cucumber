package pageObjects;


import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import testComponents.BaseTest;
import utils.Logging;



public class SearchPage {
		
		WebDriver driver;
		Integer path = 0;
		
		public SearchPage(WebDriver driver) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver=driver;
		}
		
		//********************Search Window***************
		public void search(HashMap<String,String> paramsMap) {
			Logging.logToConsole("DEBUG", "Search by: " + paramsMap);
			HashMap<String,String> lcParamsMap = new HashMap<String, String>();
			paramsMap.forEach((key, value) -> {
				lcParamsMap.put(key.toLowerCase(), value);
			});
			//button[contains(text(),'Close All')] // interactions
			driver.findElement(By.xpath("//a[contains(text(),'Search Accounts')]")).click(); // click the search form button
			driver.findElement(By.xpath("//a[normalize-space()='Simple']")).click(); // click the search form button
			driver.findElement(By.xpath("//button[@type='reset']")).click(); // Clear the form
			// look up the correct Xpath for the key data
			String[] searchFields = {"file","name", "address","city","postalCode","phone","client","account","status","desk"};
			
			paramsMap.forEach((key, value) -> { 
				
				int path = ArrayUtils.indexOf(searchFields, key); //get the index of the value from the array
				
				switch (path) {
					case -1: // value not found
						Logging.logToConsole("ERROR", "Search criteria not found: " + key);
					break;
					case 0:
						driver.findElement(By.xpath("//input[@ng-model='vm.request.accountId']")).sendKeys(value); //File number
					break;
					default:
						driver.findElement(By.xpath("(//input[@ng-model='vm.request[key]'])["+path+"]")).sendKeys(value);
					break;
				}
						
			});
			
			driver.findElement(By.xpath("//button[@type='submit']")).click(); //submit search form	
		
		}	
		
		public void openAccount(String accountNumber) throws InterruptedException {
			Logging.logToConsole("DEBUG", "opening account: " + accountNumber);
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(accountNumber);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			//code to retry if the account number search fails as it sometimes does
			for(int i=0;i<3; i++) {
			Select search =  new Select(driver.findElement(By.xpath("//lat-quicksearch[@class='ng-isolate-scope']//div//select")));
			search.selectByVisibleText("[ a ]   Account #");	
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(accountNumber);
			driver.findElement(By.xpath("//i[@class='icon-search']")).click();	
			
			for(int z=0;z<5; z++) {
				//check if there has been a no match and if so break out to the outer loop
				Thread.sleep(200);
				Logging.logToConsole("DEBUG", "placeholder: " + driver.findElement(By.xpath("//input[@type='text']")).getAttribute("placeholder")
						+ "Text: " + driver.findElement(By.xpath("//input[@type='text']")).getText());
				try {
					if (driver.findElement(By.xpath("//input[@type='text']")).getAttribute("placeholder").equalsIgnoreCase("No matches")){
						Logging.logToConsole("DEBUG", "account open failed, no match retrying");
						break;
					}else if (!driver.findElement(By.xpath("//input[@type='text']")).getText().equals("")) {
						Logging.logToConsole("DEBUG", "account open failed, error retrying");
						break;
					}
					}catch (Exception e) {
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(BaseTest.prop.getProperty("implicitWait"))));
						return;}
				if (z==4) {
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(BaseTest.prop.getProperty("implicitWait"))));
					return;
				}
				}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(BaseTest.prop.getProperty("implicitWait"))));
		}
		
}
