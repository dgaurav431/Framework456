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
import vtiger.genericUtility.JavaUtility;
import vtiger.pomRepository.CampagnInformationPage;
import vtiger.pomRepository.CampaignPage;
import vtiger.pomRepository.CreatingNewCampaignPage;
import vtiger.pomRepository.CreatingNewOpportunitiesPage;
import vtiger.pomRepository.CreatingNewOrganizationPage;
import vtiger.pomRepository.HomePage;
import vtiger.pomRepository.LoginPage;
import vtiger.pomRepository.OpportunitiesPage;
import vtiger.pomRepository.OpportunityInformationPage;
import vtiger.pomRepository.OrganizationInformationPage;
import vtiger.pomRepository.OrganizationPage;

public class ToCreateAnOpportunity {
	int randomNumber;
	
	@Given("The user launches the browser")
	public void the_user_launches_the_browser() throws IOException {
		
		System.out.println("The user is on the Login Page");

	}

	@And("The user logs in to the Application")
	public void the_user_logs_in_to_the_application() throws IOException {
		String username = FileUtility.fetchDataFromPropertyFile("username");
		String password = FileUtility.fetchDataFromPropertyFile("password");

		LoginPage login = new LoginPage(TestRunner.driver);
		login.loginAction(username, password);

		System.out.println("Login to the Application========This is create a Opportunity without hook");
	}

	@When("The User creates an Organization")
	public void the_user_creates_an_organization() throws EncryptedDocumentException, IOException {
		
		randomNumber = JavaUtility.generateRandomNumber(10000);
		
		String organizationName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.ORGANIZATION_SHEET_NAME, 1, 0)+randomNumber;
		
		
		HomePage home=new HomePage(TestRunner.driver);
		OrganizationPage organization=new OrganizationPage(TestRunner.driver);
		CreatingNewOrganizationPage createOrganization=new CreatingNewOrganizationPage(TestRunner.driver);
		OrganizationInformationPage organizationInfo=new OrganizationInformationPage(TestRunner.driver);
		
		
		
		home.clickOnOrganizationModule();
		organization.clickOnOrganizationPlusButton();
		createOrganization.enterOrganizationName(organizationName);
		createOrganization.clickOnSaveButton();
		
		String actualOrganizartionDetails = organizationInfo.fetchOrganizationInformation(organizationName);
		Assert.assertTrue(actualOrganizartionDetails.contains(organizationName));
		System.out.println("Pass: the organization has been created========This is create a Opportunity without hook");
		
		
	}

	@When("The user creates a Campaign")
	public void the_user_creates_a_campaign() throws EncryptedDocumentException, IOException {
		String campaignName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.CAMPAIGN_SHEET_NAME, 1, 0);

		HomePage home=new HomePage(TestRunner.driver);
		CampaignPage campaign=new CampaignPage(TestRunner.driver);
		CreatingNewCampaignPage createCampaign=new CreatingNewCampaignPage(TestRunner.driver);
		CampagnInformationPage campaignInfo=new CampagnInformationPage(TestRunner.driver);
		

		home.clickOnCampaignModule();
		campaign.clickOnCampaignPlusButton();
		createCampaign.enterCampaignName(campaignName);
		createCampaign.clickOnSaveButton();
		
		String actualCampaignDetails = campaignInfo.fetchCampaignInformation(campaignName);
		Assert.assertTrue(actualCampaignDetails.contains(campaignName));
		System.out.println("Pass: the Campaign has been created========This is create a Opportunity without hook");
	}

	@When("The user creates an opportunity")
	public void the_user_creates_an_opportunity() throws EncryptedDocumentException, IOException {
		
		String organizationName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.ORGANIZATION_SHEET_NAME, 1, 0)+randomNumber;
		String campaignName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.CAMPAIGN_SHEET_NAME, 1, 0);
		String opportunityName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 1, 0);
		String opportunityClosingDate = ExcelUtility.fetchDateDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 1, 1);
		String opportunityLookUpPageTitle = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 1, 2);
		String campaignLookUpPageTitle = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 1, 3);

		HomePage home=new HomePage(TestRunner.driver);
		OpportunitiesPage opportunity=new OpportunitiesPage(TestRunner.driver);
		CreatingNewOpportunitiesPage createOpportunity=new CreatingNewOpportunitiesPage(TestRunner.driver);
		
		home.clickOnOpportunityModule();
		opportunity.clickOnOpportunitiesPlusButton();
		createOpportunity.enterOpportunityName(opportunityName);
		createOpportunity.clickAndSelectOrganizationNameFromLookUpPage(organizationName, opportunityLookUpPageTitle);
		createOpportunity.clickAndSelectCampaignNameFromLookUpPage(campaignName, campaignLookUpPageTitle);
		createOpportunity.enterClosingDate(opportunityClosingDate);
		createOpportunity.clickOnSaveButton();
		
		
	}

	@Then("The user verifies the Opportunity Details")
	public void the_user_verifies_the_opportunity_details() throws EncryptedDocumentException, IOException {
		String opportunityName = ExcelUtility.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 1, 0);
		
		OpportunityInformationPage opportunityInfo=new OpportunityInformationPage(TestRunner.driver);
		
		String actualOpportunityInfo = opportunityInfo.fetchOpportunityInformation(opportunityName);
		Assert.assertTrue(actualOpportunityInfo.contains(opportunityName));
		System.out.println("Pass: the Opportunity has been created========This is create a Opportunity without hook");
		
		HomePage home=new HomePage(TestRunner.driver);
		home.signOutAction();
		System.out.println("The user has logged out");
		
		
	}


}
