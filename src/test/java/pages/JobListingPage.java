package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import hooks.Hooks;

import java.util.List;
import java.util.Map;

public class JobListingPage extends BasePage{


    By jobId = By.xpath("(//*[contains(normalize-space(text()), 'Job Id')]/..)[2]");
    By jobTitle = By.xpath("//h1[@class='job-title']");
    By jobLocation = By.xpath("//div[@class='job-other-info']//span[text()='Location']/..");
    By listOfJobRequirement = By.xpath("//p[text()=\"Responsibilities:\"]/..//ul//li");
    By applyNow = By.xpath("//*[text()=\"Apply Now\"]");
    By signInBtn = By.xpath("//div[@data-automation-id=\"click_filter\"]");
    Map<String, Object> map = Hooks.map;
    public JobListingPage(WebDriver driver) {
        super(driver);
    }


    public String actualJobId(){
        String jobId = driver.findElement(this.jobId).getText().replaceAll("[^0-9]", "");
        map.put("jobId", jobId);
        return jobId;
    }
    public String actualJobTitle(){
        String jobTitle = driver.findElement(this.jobTitle).getText();
        map.put("jobTitle", jobTitle);
        return jobTitle;
    }
    public String actualJobLocation(){
        String locationText = driver.findElement(jobLocation).getText();
        return locationText.split(",")[0].replace("Location", "").trim();
    }

    public void verifyRequirement(String expectedRequirement) {
        List<WebElement> list = driver.findElements(listOfJobRequirement);

        for (int i = 1; i <= list.size(); i++) {
            String xpathExpression = "(//p[text()='Responsibilities:']/..//ul//li)[" + i + "]";
            WebElement actualValue = driver.findElement(By.xpath(xpathExpression));
            String actualValueText = actualValue.getText();

            if (actualValueText.contains(expectedRequirement)) {
                return;
            }
        }
        Assert.fail("Requirement not found: " + expectedRequirement);
    }

    public void clickOnApplyJob(){
        scrollToView(driver,applyNow);
        waitForElementToBeVisible(applyNow);
        click(applyNow);
        waitForElementToBeVisible(signInBtn);
    }

    public void navigateBackToJobPage() {
        driver.navigate().back();
        waitForElementToBeVisible(jobId);
    }




}
