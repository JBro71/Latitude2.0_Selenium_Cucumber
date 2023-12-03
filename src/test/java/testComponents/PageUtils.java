package testComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

public class PageUtils extends BaseTest {
	WebDriver driver;
	public HashMap<String, String> testMap;
	JavascriptExecutor js;
	
	
	public PageUtils( WebDriver driver, HashMap<String, String> testMap ) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.testMap = testMap;
		js = (JavascriptExecutor)driver;

	}
	
	public void handlePopup(String buttonText) {
		try {
			tempImplictWait(0);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//button[contains(text(),'"+buttonText+"')]")).click();
			//driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
			}catch(Exception e) {
		}
		lastImplictWait();
	}
	
	public void updateTestMap(String key, String value) {
		testMap.put(key,value);
		staticTestMap.put(key,value);
	}
	
	public void defaultImplictWait() {
		String wait = prop.getProperty("implicitWait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(wait)));
		testMap.put("lastImplicitWait",wait);
	}
	
	public void implictWait(int wait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
		testMap.put("lastImplicitWait",Integer.toString(wait));
	}
	
	public void tempImplictWait(int wait) {
		//change the implicit wait without updating the last value
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
	}
	
	public void lastImplictWait() {
		//change the implicit wait without updating the last value
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(testMap.get("lastImplicitWait"))));
	}

	public void openAnchorPanel() throws InterruptedException {
	ReturnHome();
	 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']/i"));
	 if(toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-down")) {
		 driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']")).click();
	 }
	 //delay until the panel is definitely open
	 for(int i=0; i<5; i++) {
		 try {
			 driver.findElement(By.xpath("//dt[normalize-space()='Account Overview']")).getText();
			 return;
		 	}catch (Exception e) {Thread.sleep(100);}
	 	}
	 
	}
	
	public void closeAnchorPanel() throws InterruptedException {
		ReturnHome();
		 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']/i"));
		 if(toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-up")) {
			 driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']")).click();
		 	}
		 //delay until panel is no longer accessible
		 for(int i=0; i<5; i++) {
			 try {
				 driver.findElement(By.xpath("//dt[normalize-space()='Account Overview']")).getText();
				Thread.sleep(100);
			 	}catch (Exception e) {return;}
		 	}
		}
	
	
	public void CloseAccount() {
		//close existing account if one open
	try 
	{
		implictWait(0);		
		ReturnHome();
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "Account Closed");
		//context.DefaultImplictWait();
	}
	catch(Exception e){
		Logging.logToConsole("DEBUG", "No Existing open account to close");
		}
	defaultImplictWait();
	}
	
	
	public void ReturnHome() throws InterruptedException {
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	public void Scroll(int move) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(300);
			js.executeScript("window.scrollBy(0," + move + ")");						
	}
	
	public void ScrollBy(int move) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			js.executeScript("window.scrollBy(0," + move + ")");	
			
	}
	
	public void openLowerPanel(String panelName) throws Exception { 
		//open the named lower panel
		Scroll(200);
		driver.findElement(By.xpath("//button[normalize-space()='Available Panels']")).click();
		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='dropdown-menu show']/div/a"));
		for (WebElement menuItem : menu) {
		try {
			String menuItemText = menuItem.getText();
			if (menuItemText.equalsIgnoreCase(panelName)) {
				menuItem.click();
				return;
				}
				}catch(Exception e) {
						 throw new Exception(
								 "PageUtils/openLowerPanel"+ testMap.get("account")+" :  unable to open "+panelName+" lower panel");
					 }
			}
		 throw new Exception(
				 "PageUtils/openLowerPanel"+ testMap.get("account")+" :  cannot find "+panelName+" lower panel");
	}
	
	
	// use JS to get all the atributes of a web element
	//Object attrs = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",inputWebElement);
	//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + "Attributes" +" "+ attrs);	
}
