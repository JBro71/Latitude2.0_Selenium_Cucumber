package pageObjects;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testComponents.*;
import utils.Logging;

public class DesktopAnchorPanels {

	WebDriver driver;
	PageUtils pageUtils;
	public static HashMap<String, String> accountAnchorMap = new HashMap<String, String>();
	public static HashMap<String, String> customerAnchorMap = new HashMap<String, String>();
	public static HashMap<String, String> clientAnchorMap = new HashMap<String, String>();
	
	
	public DesktopAnchorPanels(WebDriver driver, PageUtils pageutils) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.pageUtils = pageutils;
	}
	
	// *********************Get Anchor Panel Info***********************
	public String queryAnchorPanels(String panelName, String field) throws InterruptedException {

		Map<String, String> panelOrder = Map.ofEntries(Map.entry("Account Overview", "1"), Map.entry("Customer Overview", "2"),
				Map.entry("Client Overview", "3"),Map.entry("Party Information", "3"),Map.entry("Account Position Overview", "4"),
				Map.entry("Active Hold Details", "4"),Map.entry("Delinquency Account Information", "5"),
				Map.entry("Contractual Due Information", "5"),Map.entry("Last Payment Received", "5"),
				Map.entry("Arrangement & Settlement Overview", "6"));
		
		String panelIndex = panelOrder.get(panelName);
		
		if (panelIndex == null) {
			Logging.logToConsole("ERROR", "Panel Name: " + panelName + " NOT FOUND");
			return panelName + "Not Found";
		}
	
		Logging.logToConsole("DEBUG", "***********Anchor Panel: " + panelName + "***********");
						
		// get panel labels
		List<WebElement> tempWebElement = driver.findElements(By.xpath("(//div[@class='well customAnchorPanelCard'])["+ panelIndex +"]//div//dl")); 
		
		//pageUtils.ImplictWait(0);	
		// elements are numbered so loop through all the numbers 1 to n and for each
		// store the value in hashmap
		for (int i = 1; i <= tempWebElement.size(); i++) {
			String key = driver
					.findElement(By.xpath("((//div[@class='well customAnchorPanelCard'])["+ panelIndex +"]//div//dl//dt)[" + i + "]"))
					.getText();
			String val = new String();
			try {
				val = driver.findElement(By.xpath("((//div[@class='well customAnchorPanelCard'])["+ panelIndex +"]//div//dl//dd)[" + i + "]"))
						.getText();
			} catch (Exception e) {
				val = "null";
			}

			Logging.logToConsole("DEBUG","Key: " + key.split(":")[0] +  "  Value: " + val);
			//String keyName = key.split(":")[0].toLowerCase();
			if (key.split(":")[0].equalsIgnoreCase(field)) {
			pageUtils.defaultImplictWait();

				return val;
			}
		}

		// reset the implicit wait to standard value
		pageUtils.defaultImplictWait();

		return  "not found";

	}
		


}
