package steps;

import config.ConfigSetup;
import elements.ElemOfWorkWithSearchBarCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static elements.ElemOfWorkWithSearchBarCase.*;

public class StepsForWorkWithSearchBar extends ElemOfWorkWithSearchBarCase {

    @Step(value = "Найти поле поиска на странице и ввести в него название товара")
    public static WebElement enterProductNameToSearchLine(WebDriver driver, String text) {
        WebElement searchLine = driver.findElement(By.id(searchLineSelector));
        searchLine.sendKeys(text);

        WebElement searchButton = driver.findElement(By.xpath(searchButtonSelector));
        searchButton.click();

        return searchLine;
    }

    @Step(value = "Поиск текста на странице")
    public static String findTextOnPage(WebDriver driver) {
        WebElement newPageText = driver.findElement(By.xpath(pathNameOfCatalog));
        return newPageText.getText();
    }

    @Step(value = "Поиск фильтра сортировки на странице")
    public static String findNameOfSorterFilter(WebDriver driver) {
        WebElement filter = driver.findElement(By.cssSelector(filterOfSorter));

        return filter.getText();
    }

    @Step(value = "Поиск первого фильтра на странице")
    public static String findTextOnFirstFilter(WebDriver driver) {
        WebElement filter = driver.findElement(By.cssSelector(firstFilter));

        return filter.getText();
    }

    @Step(value = "Поиск наименования бренда первого товара из найденного списка товаров")
    public static String findBrandOfFirstProduct(WebDriver driver, int indexOfProduct) {
        List<WebElement> listOfGoodsInProductCard = driver.findElements(By.className(listOfGoodsInProductCardSelector));
        WebElement productFromList = listOfGoodsInProductCard.get(indexOfProduct);
        WebElement element = productFromList.findElement(By.cssSelector(brandOfProduct));

        return element.getText();
    }

    @Step(value = "Очистить содержимое поисковой строки нажатием на крестик")
    public static void clearSearchBar(WebDriver driver) {
        WebElement clearSearchButton = driver.findElement(By.cssSelector(buttonSearchBarClear));
        clearSearchButton.click();
//        waitTime(5000);
    }
}
