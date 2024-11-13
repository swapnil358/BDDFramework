package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    By careersLink = By.xpath("//div[@class=\"text text-navy body2\"]//a[text()=\"Careers\"]");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCareersLink() {
        waitForElementToBeVisible(careersLink);
    }

}
