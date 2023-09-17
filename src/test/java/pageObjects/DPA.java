package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Logging;

public class DPA {

	WebDriver driver;
	
	public DPA(WebDriver driver) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver=driver;
	}
	
	
	public void dismisDPA()  {
		try 
			{
			driver.findElement(By.xpath("//button[@class='btn btn-primary'][normalize-space()='Close']")).click();
			Logging.logToConsole("DEBUG", "DPA Cancelled");
			}
			catch(Exception e)
				{
				Logging.logToConsole("WARNING", "No DPA: " ); // + e);
				}
	}
	
}
