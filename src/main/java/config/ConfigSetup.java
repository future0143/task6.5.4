package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static util.WaitTime.waitTime;

public class ConfigSetup {

    private String url = TestProperties.getValue("test.url");
    protected WebDriver driver;

    @BeforeAll
    void openChrome() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        WebDriverManager.chromedriver().driverVersion("117.0.5938.92").setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(url);
        waitTime(10000);
    }

    @AfterAll
    void driverClose() {
        driver.quit();
    }
}