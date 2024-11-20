package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WorkdayLoginPage extends BasePage {
    WebDriver driver;

    // Locators for the login elements
    private By usernameField = By.cssSelector("#input-4"); // Update with the correct locator for the username field
    private By passwordField = By.cssSelector("#input-5"); // Update with the correct locator for the password field
    private By loginButton = By.xpath("//div[@aria-label='Sign In']"); // Update with the correct locator for the login button
    private By careerHome = By.xpath("data-automation-id=\"navigationItem-Careers Home\"");

    // Constructor
    public WorkdayLoginPage(WebDriver driver) {
        super(driver);
    }

    // Method to enter username
    public void enterUsername(String username) {
       enterText(usernameField,username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        enterText(passwordField,password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        click(loginButton);
    }

    public void comeBackToCareerHome(){
        click(careerHome);
    }
}
