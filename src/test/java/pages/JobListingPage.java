package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JobListingPage extends BasePage{

    By jobId = By.xpath("(//*[contains(normalize-space(text()), 'Job Id')]/..)[2]");
    By jobTitle = By.xpath("//h1[@class='job-title']");
    By jobLocation = By.xpath("//div[@class='job-other-info']//span[text()='Location']/..");
    By listOfJobRequirement = By.xpath("//p[text()=\"Responsibilities:\"]/..//ul//li");

    public JobListingPage(WebDriver driver) {
        super(driver);
    }


    public String actualJobId(){
        return  driver.findElement(jobId).getText().replaceAll("[^0-9]", "");
    }
    public String actualJobTitle(){
        return  driver.findElement(jobTitle).getText();
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




}
