package pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CareersPage extends BasePage {


    By searchButton = By.cssSelector(".submit-icon");
    By jobList = By.xpath("//ul[@aria-label='Job suggestions']//li");
    By applyBtn=By.xpath("//a[contains(normalize-space(),\"Apply Now\")][1]");
    String selectJob = ("//ul[@aria-label='Job suggestions']//li['index']");
    By searchJobTextField = By.xpath("//input[@placeholder=\"Search job title or location\"]");
    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public void searchJob(String jobTitle) {
        waitForElementToBeClickable(searchJobTextField);
       // click(searchButton);
        enterText(searchJobTextField, jobTitle);
    }

    public void clickSearchBtn(){
        click(searchButton);
    }

    public void selectJobByTitle(String title) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> jobList = driver.findElements(this.jobList);
        for (int i = 1; i <= jobList.size(); i++) {
            String selectJobXPath = "//ul[@aria-label='Job suggestions']//li[" + i + "]";
            String actualText = driver.findElement(By.xpath(selectJobXPath)).getText();
            if (actualText.contains(title)) {
                System.out.println("Job title matched: ");
                driver.findElement(By.xpath(selectJobXPath)).click();
                waitForElementToBeVisible(applyBtn);
                break;
            }else {
                System.out.println("Job not found");
            }
        }

    }


}
