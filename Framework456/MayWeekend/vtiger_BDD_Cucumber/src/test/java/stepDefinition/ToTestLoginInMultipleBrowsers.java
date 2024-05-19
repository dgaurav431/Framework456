package stepDefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import vtiger.genericUtility.FileUtility;
import vtiger.genericUtility.WebDriverUtility;

public class ToTestLoginInMultipleBrowsers {
	WebDriver driver;
	@Given("^Launch the (.*)$")
	public void launch_the_chrome(String browser) throws IOException {

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
		
	}

	@When("Navigate to the URL")
	public void navigate_to_the_url() throws IOException {
		String url = FileUtility.fetchDataFromPropertyFile("url");
		driver.get(url);
		System.out.println("Navigate to the URL");
	}

	@Then("Verify Login Page")
	public void verify_login_page() {
	    String currentTitle = driver.getTitle();
	    
	    Assert.assertTrue(currentTitle.contains("vtiger CRM"));
	    System.out.println("Pass: the User is on the Login Page");
	    
	}


}
