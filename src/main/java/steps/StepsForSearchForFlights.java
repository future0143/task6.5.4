package steps;

import elements.ElemOfSearchForFlightsCase;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static validator.ValidationOfElements.checkText;

public class StepsForSearchForFlights extends ElemOfSearchForFlightsCase {

    @Step(value = "Выбрать фильтры")
    public static void chooseFilters(WebDriver driver, String firstFilter, String secondFilter) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement appliancesFilter = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(firstFilter)));

        Actions actions = new Actions(driver);
        actions.moveToElement(appliancesFilter).perform();

        WebElement homeAppliancesFilter = driver.findElement(By.linkText(secondFilter));
        homeAppliancesFilter.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(fieldForWaiting)));
    }

    @Step(value = "Проверить что произошел переход на страницу https://vmeste.wildberries.ru/avia")
    public static void checkNavigationToPage(WebDriver driver, String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        checkText(currentUrl, expectedUrl);
    }

    @Step(value = "Ввести в поля откуда/куда наименование города")
    public static void enterCity(WebDriver driver, String xpath, String text) {
        WebElement field = driver.findElement(By.id(xpath));
        field.sendKeys(text);

        driver.findElement(By.xpath("//div[contains(@label, '" + text + "')]"));
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        actions.sendKeys(Keys.RETURN).build().perform();
    }

    @Step(value = "Ввести в поле \"Дата\" - указать дату")
    public static void enterDateInField(WebDriver driver) {
        LocalDate nowDate = LocalDate.now();
        LocalDate dateForInput = nowDate.plusDays(2);
        String dateForInputString = dateForInput.toString();

        WebElement pickerPanel = driver.findElement(By.cssSelector(fieldDateThere));
        pickerPanel.click();
        WebElement dateContainer = driver.findElement(By.className(dateContainerSelector));

        List<WebElement> dateElements = dateContainer.findElements(By.cssSelector(attributeForDates));

        for (WebElement dateElement : dateElements) {
            if (dateElement.getAttribute("title").equals(dateForInputString)) {
                dateElement.click();
            }
        }
    }

    @Step(value = "Выбрать два взрослых пассажира")
    public static void choosePassengers(WebDriver driver) {
        WebElement fieldPassengers = driver.findElement(By.cssSelector(buttonPassengers));
        fieldPassengers.click();

        List<WebElement> desiredElements = driver.findElements(By.cssSelector(buttonCounter));
        desiredElements.get(1).click();
    }

    @Step(value = "Нажать \"Найти билеты\"")
    public static void clickSearchTickets(WebDriver driver) {
        WebElement buttonSearch = driver.findElement(By.cssSelector(buttonSearchTickets));
        buttonSearch.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(listOfTicketsSelector)));
    }

    @Step(value = "Проверить результаты поиска билетов")
    public static void checkResultsOfSearch(WebDriver driver) {
        List<WebElement> listOfTickets = driver.findElements(By.cssSelector(listOfTicketsSelector));

        if (listOfTickets.size() == 0) {
            WebElement textNoTickets = driver.findElement(By.cssSelector(fieldWithTextNoTickets));
            String actualText = textNoTickets.getText();
            String expectedText = "Мы не нашли билеты по вашему запросу";
            checkText(expectedText, actualText);
        } else {
            Assertions.assertFalse(listOfTickets.isEmpty());
        }
    }
}