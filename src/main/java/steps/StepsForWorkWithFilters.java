package steps;

import elements.ElemOfWorkWithFiltersCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static elements.ElemOfAddProductToBasketCase.buttonFiltersCssSelector;
import static util.WorkWithDataOfElements.convertTextPriceToNumber;
import static util.WorkWithDataOfElements.getListOfTextsFromListOfElements;
import static validator.ValidationOfElements.*;

public class StepsForWorkWithFilters extends ElemOfWorkWithFiltersCase {

    @Step(value = "Нажать на кнопку \"Фильтры\"")
    public static void clickFilters(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement buttonFilter = wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(buttonFiltersCssSelector))));
        buttonFilter.click();
    }

    @Step(value = "Применить несколько фильтров")
    public static List<String> applyFilters(WebDriver driver, String minPrice, String maxPrice) {
        List<String> listOfFilters = new ArrayList<>();

        clickButton(driver, buttonAllFiltersSelector);

        inputTextInFilter(driver, minPriceFilterName, minPrice);
        inputTextInFilter(driver, maxPriceFilterName, maxPrice);
        listOfFilters.add("от " + minPrice + " до "  + maxPrice);

        applyOneFilter(driver, deliveryTimeSelector, listOfFilters);

        applyOneFilter(driver, brandFilterSelector, listOfFilters);

        applyOneFilter(driver, screenDiagonalSelector, listOfFilters);

        clickButton(driver, buttonToShowResults);

        return listOfFilters;
    }

    private static void applyOneFilter(WebDriver driver, String xpath, List<String> listOfFilters) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement deliveryTime = driver.findElement(By.xpath(xpath));
        deliveryTime.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        listOfFilters.add(deliveryTime.getText().replaceAll("\\d+$", ""));
    }

    private static void clickButton(WebDriver driver, String xpath) {
        WebElement buttonToClick = driver.findElement(By.cssSelector(xpath));
        buttonToClick.click();
    }

    private static void inputTextInFilter(WebDriver driver, String path, String text) {
        WebElement filterForInput = driver.findElement(By.name(path));
        filterForInput.clear();
        filterForInput.sendKeys(text);
    }

    @Step(value = "Проверить, что фильтр активировался")
    public static void checkFilterActivated(WebDriver driver) {
        WebElement yourChoiceList = driver.findElement(By.cssSelector(listOfSelectedFilters));
        checkElementDisplayed(yourChoiceList);
    }

    @Step(value = "Проверить, что кол-во товара на страница = количеству товара на странице")
    public static void checkCountOfProducts(WebDriver driver) {
        WebElement productList = driver.findElement(By.className(listProductsAfterFiltersAsElement));

        List<WebElement> listProductsAfterFilters = productList.findElements(By.className(listProductsAfterFiltersSelector));
        int countOfProductsExpected = listProductsAfterFilters.size();

        WebElement countOfProductsElement = driver.findElement(By.className(countOfProductsElementName));
        String countOfProductsText = countOfProductsElement.getText();
        int countOfProductsActual = convertTextPriceToNumber(countOfProductsText);

        validateSumOrCount(countOfProductsExpected, countOfProductsActual);
    }

    @Step(value = "Проверить, что выбранные фильтры отображаются на странице")
    public static void checkSelectedFiltersOnPage(WebDriver driver, List<String> listOfFiltersTextExpected) {
        WebElement filterList = driver.findElement(By.cssSelector(listOfSelectedFilters));
        List <WebElement> listOfFiltersActual = filterList.findElements(By.tagName(tagNameOfFiltersList));

        List<String> listOfFiltersTextActual = getListOfTextsFromListOfElements(listOfFiltersActual);

        checkListsForSimilarity(listOfFiltersTextActual, listOfFiltersTextExpected);
    }

    @Step(value = "Найти кнопку \"Сбросить все\"")
    public static WebElement findButtonToResetAll (WebDriver driver) {
        return driver.findElement(By.xpath(buttonToResetFilters));
    }
}