package testComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.DPA;
import pageObjects.SearchPage;
import utils.Logging;

public class OpenAccount {

	WebDriver driver;
	PageUtils pageUtils;
	SearchPage searchPage;
	DPA dpa;
		
		public OpenAccount(WebDriver driver, PageUtils pageUtils, SearchPage searchPage, DPA dpa) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			this.searchPage = searchPage;
			this.dpa = dpa;
		}
	
	
	
	public void OpenNewAccount(String accountNumber) throws InterruptedException {
		//close existing account if one open
		try 
		{
		pageUtils.ImplictWait(0);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "OpenAccount/OpenNewAccount: Account Closed");
		}
		catch(Exception e){}
		pageUtils.DefaultImplictWait();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		
		//**********************find and open new account******************
		searchPage.openAccount(accountNumber);
		
		//**********************dismiss DPA screen******************
		dpa.dismisDPA();
		
		//wait until the page is fully loaded by checking if this element is populated before doing anything
		for(int i=1;i<20 ;i++) { 
			Thread.sleep(500);
			Logging.logToConsole("DEBUG", "OpenAccount/OpenNewAccount:: waiting for page to load");
			try {
				if (driver.findElement(By.xpath("//input[@name='amount']")).getAttribute("value") != "") 
				{
					Logging.logToConsole("INFO", "OpenAccount/OpenNewAccount:: waited " + i*500 + " miliseconds for arrangement panel");
					Thread.sleep(500);
					break;
				}
			}
			catch(Exception e)
				{
				Thread.sleep(500);
				}
		}
		pageUtils.testMap.put("customer1", null);
		pageUtils.testMap.put("customer2", null);
	}
	
	
}
