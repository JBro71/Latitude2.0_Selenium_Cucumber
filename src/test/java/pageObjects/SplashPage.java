package pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Logging;

public class SplashPage {
	
	WebDriver driver;
	
	public SplashPage(WebDriver driver) {
		//initialisation
		this.driver=driver;
	}

			
	public void splash (String action)  {
		

		if (action == "goto desktop") {
		driver.findElement(By.xpath("//a[normalize-space()='Agent Desktop']")).click();
		Logging.logToConsole("DEBUG", "Agent Desktop Opened");
		}
		else {
		driver.findElement(By.xpath("//a[contains(text(),'Account Request')]")).click();
		Logging.logToConsole("DEBUG", "Account Request Form Opened");
		}
		
		//******************Switch to new window****************
		//main screen opens as a new tab in browser so need to switch to that window
		Set<String> windows  = driver.getWindowHandles(); //parent id and child id
		Iterator<String>it = windows.iterator();
		String Id = it.next();
		Id = it.next();
		driver.switchTo().window(Id);
		Logging.logToConsole("DEBUG", "Switch to Child Window Complete");
	}
	
}
