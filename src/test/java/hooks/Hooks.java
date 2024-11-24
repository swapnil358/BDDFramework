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
    public static Map<String, Object> globalMap;



    @Before("@UI")
    public void setUp() {
        System.out.println("UI mode started");
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.labcorp.com");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            globalMap = new HashMap<>();
        }
    }

    @After("@UI")
    public void tearDown() {
        System.out.println("UI mode tear down");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    @Before("@API")
    public void setUpAPI() {
        System.out.println("API mode started");
    }

    @After("@API")
    public void tearDownAPI() {
        System.out.println("API mode tear down");
    }

}
