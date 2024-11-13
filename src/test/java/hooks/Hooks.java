package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
    public static WebDriver driver;
    public static Map<String, Object> map;



    @Before("@UI")
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.labcorp.com");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            map = new HashMap<>();
        }
    }

    @After("@UI")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
//    @Before("@API")
//    public void setUpAPI() {
//        // Setup code for API tests (if any)
//    }
//
//    @After("@API")
//    public void tearDownAPI() {
//        // Cleanup code for API tests (if any)
//    }
}
