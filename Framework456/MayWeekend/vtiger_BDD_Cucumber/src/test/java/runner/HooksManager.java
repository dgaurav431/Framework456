package runner;

import java.io.IOException;

import org.openqa.selenium.WebDriverException;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import vtiger.genericUtility.WebDriverUtility;

public class HooksManager {
	
	@After
	public void screenShot(Scenario scenario) throws WebDriverException, IOException {
		
		if(scenario.isFailed()) {
		WebDriverUtility.takeAScreenShot(TestRunner.driver, "demoShot.png");
		}
	}

}
