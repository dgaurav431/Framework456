package stepDefinition;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import runner.TestRunner;
import vtiger.genericUtility.ExcelUtility;
import vtiger.genericUtility.FileUtility;
import vtiger.genericUtility.IPathConstant;
import vtiger.genericUtility.JavaUtility;
import vtiger.pomRepository.CreatingNewLeadsPage;
import vtiger.pomRepository.CreatingNewOrganizationPage;
import vtiger.pomRepository.HomePage;
import vtiger.pomRepository.LeadsInformationPage;
import vtiger.pomRepository.LeadsPage;
import vtiger.pomRepository.LoginPage;
import vtiger.pomRepository.OrganizationInformationPage;
import vtiger.pomRepository.OrganizationPage;

public class ToCreateASimpleLead {
	int randomNumber;
	@Given("the admin loggs in to the application")
	public void the_admin_loggs_in_to_the_application() throws IOException {
		String username = FileUtility.fetchDataFromPropertyFile("username");
		String password = FileUtility.fetchDataFromPropertyFile("password");

		LoginPage login = new LoginPage(TestRunner.driver);
		login.loginAction(username, password);

		System.out.println("Login to the Application");
	}

	@When("the admin creates a lead")
	public void the_admin_creates_a_lead() throws EncryptedDocumentException, IOException {
		// TestData
		String leadSalutation = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 0);
		String leadFirstName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 1);
		String leadLastName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 2);
		String leadCompanyName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 3);

		// POM Object Creation
		HomePage home = new HomePage(TestRunner.driver);
		LeadsPage lead = new LeadsPage(TestRunner.driver);
		CreatingNewLeadsPage createLead = new CreatingNewLeadsPage(TestRunner.driver);

		home.clickOnLeadsModule();
		lead.clickOnLeadPlusButton();
		createLead.selectLeadSalutation(leadSalutation);
		createLead.enterLeadFirstName(leadFirstName);
		createLead.enterLeadLastName(leadLastName);
		createLead.enterLeadCompanyName(leadCompanyName);
		createLead.clickOnSaveButton();
		System.out.println("the user creates a Lead");
	}

	@Then("the admin verifies the lead information")
	public void the_admin_verifies_the_lead_information() throws EncryptedDocumentException, IOException {
		String leadLastName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 2);
		HomePage home = new HomePage(TestRunner.driver);
		LeadsInformationPage leadInfo = new LeadsInformationPage(TestRunner.driver);
		String actualLeadInformation = leadInfo.fetchLeadInformation(leadLastName);

		Assert.assertTrue(actualLeadInformation.contains(leadLastName));
		System.out.println("Pass: the lead has been created");

		home.signOutAction();
	}

	@When("the admin creates a Organization")
	public void the_admin_creates_a_organization() throws EncryptedDocumentException, IOException {
		randomNumber = JavaUtility.generateRandomNumber(10000);

		String organizationName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.ORGANIZATION_SHEET_NAME, 1,
				0) + randomNumber;

		HomePage home = new HomePage(TestRunner.driver);
		OrganizationPage organization = new OrganizationPage(TestRunner.driver);
		CreatingNewOrganizationPage createOrganization = new CreatingNewOrganizationPage(TestRunner.driver);

		home.clickOnOrganizationModule();
		organization.clickOnOrganizationPlusButton();
		createOrganization.enterOrganizationName(organizationName);
		createOrganization.clickOnSaveButton();

	}

	@Then("the admin verifies the organization information")
	public void the_admin_verifies_the_organization_information() throws EncryptedDocumentException, IOException {

		OrganizationInformationPage organizationInfo = new OrganizationInformationPage(TestRunner.driver);
		String organizationName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.ORGANIZATION_SHEET_NAME, 1,
				0) + randomNumber;

		String actualOrganizartionDetails = organizationInfo.fetchOrganizationInformation(organizationName);
		Assert.assertTrue(actualOrganizartionDetails.contains(organizationName));
		System.out.println("Pass: the organization has been created");
		
		HomePage home=new HomePage(TestRunner.driver);
		home.signOutAction();
		System.out.println("The user has logged out");
	}

}
