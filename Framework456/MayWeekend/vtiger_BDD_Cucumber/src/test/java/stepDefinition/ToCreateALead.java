package stepDefinition;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import runner.TestRunner;
import vtiger.genericUtility.ExcelUtility;
import vtiger.genericUtility.FileUtility;
import vtiger.genericUtility.IPathConstant;
import vtiger.pomRepository.CreatingNewLeadsPage;
import vtiger.pomRepository.HomePage;
import vtiger.pomRepository.LeadsInformationPage;
import vtiger.pomRepository.LeadsPage;
import vtiger.pomRepository.LoginPage;

public class ToCreateALead {
	@Given("The user is on the Login Page")
	public void the_user_is_on_the_login_page() throws IOException {

		System.out.println("The user is on the Login Page");
	}

	@And("The user Logs in to the application")
	public void the_user_logs_in_to_the_application() throws IOException {

		String username = FileUtility.fetchDataFromPropertyFile("username");
		String password = FileUtility.fetchDataFromPropertyFile("password");

		LoginPage login = new LoginPage(TestRunner.driver);
		login.loginAction(username, password);

		System.out.println("Login to the Application");

	}

	@When("The user creates Lead")
	public void the_user_creates_lead() throws EncryptedDocumentException, IOException {
		// TestData
		String leadSalutation = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 0);
		String leadFirstName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 1);
		String leadLastName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 2);
		String leadCompanyName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 3);

		// POM Object Creation
		HomePage home = new HomePage(TestRunner.driver);
		LeadsPage lead = new LeadsPage(TestRunner.driver);
		CreatingNewLeadsPage createLead = new CreatingNewLeadsPage(TestRunner.driver);

		Assert.fail();
		home.clickOnLeadsModule();
		lead.clickOnLeadPlusButton();
		createLead.selectLeadSalutation(leadSalutation);
		createLead.enterLeadFirstName(leadFirstName);
		createLead.enterLeadLastName(leadLastName);
		createLead.enterLeadCompanyName(leadCompanyName);
		createLead.clickOnSaveButton();
		System.out.println("the user creates a Lead");
	}

	@Then("The user verifies the Lead Information")
	public void the_user_verifies_the_lead_information() throws EncryptedDocumentException, IOException {
		String leadLastName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 2);

		LeadsInformationPage leadInfo = new LeadsInformationPage(TestRunner.driver);
		String actualLeadInformation = leadInfo.fetchLeadInformation(leadLastName);

		Assert.assertTrue(actualLeadInformation.contains(leadLastName));
		System.out.println("Pass: the lead has been created");
	}

	@And("The user loggs out")
	public void the_user_loggs_out() {
		
		HomePage home = new HomePage(TestRunner.driver);
		home.signOutAction();
		System.out.println("The user loggs out");
		
	}

}
