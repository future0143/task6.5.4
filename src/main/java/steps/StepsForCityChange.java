package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import elements.ElemOfAddProductToBasketCase;

import java.time.Duration;

import static elements.ElemOfCityChangeCase.*;

public class StepsForCityChange extends ElemOfAddProductToBasketCase {

    @Step(value = "Кликнуть на кнопку \"Смена города\"")
    public static void clickChangeCity(WebDriver driver) {
        WebElement buttonCity = driver.findElement(By.cssSelector(cssSelectorForCity));
        buttonCity.click();
    }

    @Step(value = "В поисковую строку ввести \"Санкт-Петербург\"")
    public static void inputCityInSearchLine(WebDriver driver, String city) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchLine = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(searchCityLine)));

        searchLine.sendKeys(city);
        searchLine.sendKeys(Keys.ENTER);
    }

    @Step(value = "Выбрать первый адрес из списка адресов")
    public static String chooseFirstAddressFromList(WebDriver driver) {
        WebElement listOfAddress = driver.findElement(By.id(idListOfAddress));
        WebElement dataOfElement = listOfAddress.findElement(By.xpath(firstAddress));
        dataOfElement.click();

        return dataOfElement.getText();
    }

    @Step(value = "Найти инфорацию о пункте выдачи")
    public static WebElement findInfoAboutPickUpPoint(WebDriver driver) {
        return driver.findElement(By.className(infoAboutPickUpPoint));
    }

    @Step(value = "Найти адрес в информации о пункте выдачи")
    public static String findAddressIntoInfoAboutPoint(WebElement infoPoint) {
        WebElement addressOfPoint = infoPoint.findElement(By.className(addressOnInfoPoint));

        return addressOfPoint.getText();
    }

    @Step(value = "Нажать на кнопку \"Выбрать\"")
    public static void clickButtonChoose(WebDriver driver) {
        WebElement buttonChoose = driver.findElement(By.cssSelector(buttonChoosePoint));
        buttonChoose.click();
    }

    @Step(value = "Поиск текущего адреса на главной странице")
    public static String findCurrentAddress(WebDriver driver) {
        WebElement buttonCity = driver.findElement(By.cssSelector(cssSelectorForCity));

        return buttonCity.getText();
    }
}