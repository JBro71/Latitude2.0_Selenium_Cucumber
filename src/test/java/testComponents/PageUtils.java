package testComponents;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Logging;

public class PageUtils extends BaseTest {
	
	WebDriver driver;
	public HashMap<String, String> testMap;
	JavascriptExecutor js;
	
	
	public PageUtils(WebDriver driver, HashMap<String, String> testMap) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.testMap = testMap;
		js = (JavascriptExecutor)driver;
		
	}

	public void updateTestMap(String key, String value) {
		testMap.put(key,value);
		staticTestMap.put(key,value);
	}
	
	public void DefaultImplictWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
	}
	
	public void ImplictWait(int wait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
	}

	public void OpenAnchorPanel() {
	 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']"));
	 if(toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-up")) {
		 toggle.click();
	 }
	}
	
	public void CloseAnchorPanel() {
		 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']"));
		 if(!toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-up")) {
			 toggle.click();
		 }
		}
	
	
	public void CloseAccount() {
		//close existing account if one open
	try 
	{
		ImplictWait(0);		
		ReturnHome();
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "Account Closed");
		//context.DefaultImplictWait();
	}
	catch(Exception e){
		Logging.logToConsole("DEBUG", "No Existing open account to close");
		}
	DefaultImplictWait();
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
	
	
	// use JS to get all the atributes of a web element
	//Object attrs = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",inputWebElement);
	//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + "Attributes" +" "+ attrs);	
}
