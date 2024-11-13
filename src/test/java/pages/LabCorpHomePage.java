package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LabCorpHomePage extends BasePage{

    By careersLink = By.xpath("//div[@class=\"text text-navy body2\"]//a[text()=\"Careers\"]");
    public LabCorpHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCareersLink() {
        waitForElementToBeVisible(careersLink);
        click(careersLink);
    }
}
