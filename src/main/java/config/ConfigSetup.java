package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ConfigSetup {

    private final String url = TestProperties.getValue("test.url");
    protected WebDriver driver;

    @BeforeAll
    void openChrome() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        WebDriverManager.chromedriver().driverVersion("117.0.5938.92").setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.main-page__banner.banner")));
    }

    @AfterAll
    void driverClose() {
        driver.quit();
    }
}