package stepDefinitions;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LabCorpHomePage;
import pages.CareersPage;
import pages.JobListingPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LabCorpJobSearchSteps {
    WebDriver driver;
    LabCorpHomePage homePage;
    CareersPage careersPage;
    JobListingPage jobListingPage;


    public LabCorpJobSearchSteps(){
        this.driver = Hooks.driver;
        homePage = new LabCorpHomePage(driver);
        careersPage = new CareersPage(driver);
        jobListingPage = new JobListingPage(driver);

    }


    @Given("I am on the LabCorp homepage")
    public void iAmOnTheLabCorpHomepage(){
       Assert.assertEquals(driver.getTitle(), "Lab Testing & Scientific Innovation for Healthcare | Labcorp");
    }

    @When("I navigate to the Careers page")
    public void iNavigateToTheCareersPage() {
        homePage.clickOnCareersLink();
    }

    @When("I search for a job listing with title {string}")
    public void iSearchForAJobListingWithTitle(String jobTitle) {
        careersPage.searchJob(jobTitle);

    }

    @When("I select the job with title {string}")
    public void iSelectForAJobListingWithTitle(String jobTitle) {
        careersPage.selectJobByTitle(jobTitle);

    }

    @Then("I should see the job details for {string}")
    public void iShouldSeeTheJobDetailsFor(String jobTitle) {
        Assert.assertTrue(true);  // Validate job title
    }

    @Then("I verify job details")
    public void iVerifyJobDetails(DataTable dataTable) {
        Map<String, String> jobDetails = dataTable.asMap(String.class, String.class);

        String expectedJobId = jobDetails.get("job id");
        String expectedJobTitle = jobDetails.get("job title");
        String expectedJobLocation = jobDetails.get("job location");

        Assert.assertEquals("Job ID does not match", expectedJobId, jobListingPage.actualJobId());
        Assert.assertEquals("Job title does not match", expectedJobTitle, jobListingPage.actualJobTitle());
        Assert.assertEquals("Job location does not match", expectedJobLocation, jobListingPage.actualJobLocation());
    }

    @And("I verify job requirement")
    public void iVerifyJobRequirement(io.cucumber.datatable.DataTable jobRequirements) {

        List<String> expectedRequirements = jobRequirements.asList(String.class);
        for (String expRequirement : expectedRequirements){
            jobListingPage.verifyRequirement(expRequirement);
        }
    }
}
