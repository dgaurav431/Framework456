package runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import vtiger.genericUtility.FileUtility;
import vtiger.genericUtility.WebDriverUtility;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "stepDefinition",
				plugin = {"pretty", "html:target/html_reports/cucumber_vtiger_Report.html", 
						  "json:target/json_reports/cucumber_vtiger_Report.json", "junit:target/xml_reports/cucumber_vtiger_Report.xml"})
public class TestRunner {
 
	public static WebDriver driver;
	
	@BeforeClass
	public static void setUp() throws IOException {
		
		String browser = FileUtility.fetchDataFromPropertyFile("browser");
		String url = FileUtility.fetchDataFromPropertyFile("url");

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}

		else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		WebDriverUtility.maximizeTheWindow(driver);
		WebDriverUtility.waitForElement(driver);
		System.out.println("Launch the Browser");
		driver.get(url);
		System.out.println("Navigate to the URL");
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
		System.out.println("The browser is closed");
	}
	
	

}
