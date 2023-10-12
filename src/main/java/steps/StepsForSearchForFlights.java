package steps;

import elements.ElemOfSearchForFlightsCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static elements.ElemOfCityChangeCase.cssSelectorForCity;

public class StepsForSearchForFlights extends ElemOfSearchForFlightsCase {

    //Выбрать "Фильтры" - "Путешествия" - "Авиабилеты"
    @Step(value = "Выбрать фильтры")
    public static void chooseFilters(WebDriver driver, String firstFilter, String secondFilter) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement appliancesFilter = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(firstFilter)));

        Actions actions = new Actions(driver);
        actions.moveToElement(appliancesFilter).perform();

        WebElement homeAppliancesFilter = driver.findElement(By.xpath(secondFilter));
        homeAppliancesFilter.click();
    }


}