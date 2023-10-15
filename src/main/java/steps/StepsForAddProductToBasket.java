package steps;

import elements.ElemOfAddProductToBasketCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static elements.ElemOfWorkWithSearchBarCase.listOfGoodsInProductCardSelector;
import static util.WorkWithDataOfElements.convertTextPriceToNumber;
import static util.WorkWithDataOfElements.getTotalPriceFromListOfWebElements;
import static validator.ValidationOfElements.checkText;

public class StepsForAddProductToBasket extends ElemOfAddProductToBasketCase {

    @Step(value = "Нажать на кнопку \"Фильтры\"")
    public static void clickFilters(WebDriver driver) {
        WebElement buttonFilter = driver.findElement(By.cssSelector(buttonFiltersCssSelector));
        buttonFilter.click();
    }

    @Step(value = "Выбрать фильтры")
    public static void chooseFilters(WebDriver driver, String firstFilter, String secondFilter, String thirdFilter) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement appliancesFilter = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(firstFilter)));

        Actions actions = new Actions(driver);
        actions.moveToElement(appliancesFilter).perform();

        WebElement homeAppliancesFilter = driver.findElement(By.xpath(secondFilter));
        homeAppliancesFilter.click();

        WebElement vacuumCleanerFilter = driver.findElement(By.linkText(thirdFilter));
        vacuumCleanerFilter.click();
    }

    @Step(value = "Найти заглавие каталога")
    public static String findTitleOfCatalog(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement catalogTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='catalog-title']")));

        return catalogTitleElement.getText();
    }

    public static String findPathOfFilter(WebDriver driver, String path) {
        WebElement homeLink = driver.findElement(By.xpath(path));

        return homeLink.getText();
    }

    @Step(value = "Проверить путь фильтра: \"Главная\" - \"Бытовая техника\" - \"Техника для дома\" - \"Пылесосы и пароочистители\"\n")
    public static void checkPathOfFilters(WebDriver driver) {
        String homeText = findPathOfFilter(driver, homeLinkPath);
        String appliancesText = findPathOfFilter(driver, appliancesLinkPath);
        String homeAppliancesText = findPathOfFilter(driver, homeAppliancesLinkPath);
        String vacuumCleanersText = findPathOfFilter(driver, vacuumCleanersLinkPath);

        checkText(homeText, "Главная");
        checkText(appliancesText, "Бытовая техника");
        checkText(homeAppliancesText, "Техника для дома");
        checkText(vacuumCleanersText, "Пылесосы и пароочистители");
    }

    @Step(value = "Навести курсор на первый товар из списка и добавить его в корзину")
    public static ArrayList<String> addFirstProductToBasket(WebDriver driver, int indexOfProduct) {
        List<WebElement> listOfGoodsInProductCard = driver.findElements(By.className(listOfGoodsInProductCardSelector));
        WebElement productFromList = listOfGoodsInProductCard.get(indexOfProduct);

        Actions actions = new Actions(driver);
        actions.moveToElement(productFromList).perform();

        ArrayList<String> dataAboutProduct = getDataAboutProduct(driver);

        WebElement buttonAddToBasket = driver.findElement(By.cssSelector(buttonAddToBasketSelector));
        buttonAddToBasket.click();

        return dataAboutProduct;
    }

    public static ArrayList<String> getDataAboutProduct(WebDriver driver) {
        WebElement expectedNameOfProduct = driver.findElement(By.cssSelector(nameOfProductFromList));
        String expectedProductName = expectedNameOfProduct.getText();

        WebElement expectedBrandOfProduct = driver.findElement(By.cssSelector(brandOfProductFromList));
        String expectedProductBrand = expectedBrandOfProduct.getText();

        WebElement expectedPriceOfProduct = driver.findElement(By.cssSelector(priceOfProductFromList));
        String expectedProductPrice = expectedPriceOfProduct.getText();

        ArrayList<String> dataAboutProduct = new ArrayList<>();
        dataAboutProduct.add(expectedProductName);
        dataAboutProduct.add(expectedProductBrand);
        dataAboutProduct.add(expectedProductPrice);

        return dataAboutProduct;
    }

    @Step(value = "Проверить количество товаров в корзине")
    public static void checkCountOfProductsInBasket(WebDriver driver, int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cartItemCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(countOfProductsInBasket)));
        checkText(String.valueOf(expectedCount), cartItemCountElement.getText());
    }

    @Step(value = "Перейти в корзину")
    public static void goToBasket(WebDriver driver) {
        WebElement basketDiv = driver.findElement(By.cssSelector(basketDivSelector));
        WebElement basketLink = basketDiv.findElement(By.cssSelector(basketLinkSelector));
        basketLink.click();
    }

    @Step(value = "Проверить, что текст и цена товара соответствует цене и названию товара из предыдущих шагов")
    public static void checkDataOfProduct(WebDriver driver, ArrayList<String> dataAboutProduct) {
        String expectedProductName = dataAboutProduct.get(0).substring(2);
        String expectedProductBrand = dataAboutProduct.get(1);
        String expectedProductPrice = dataAboutProduct.get(2).trim();

        WebElement actualNameOfProduct = driver.findElement(By.cssSelector(nameOfProductInBasket));
        String actualProductName = actualNameOfProduct.getText();

        WebElement actualBrandOfProduct = driver.findElement(By.cssSelector(brandOfProductInBasket));
        String actualProductBrand = actualBrandOfProduct.getText().substring(2);

        WebElement actualPriceOfProduct = driver.findElement(By.cssSelector(priceOfProductInBasket));
        String actualProductPrice = actualPriceOfProduct.getText();

        checkText(expectedProductName, actualProductName);
        checkText(expectedProductBrand, actualProductBrand);
        checkText(expectedProductPrice, actualProductPrice);
    }

    @Step(value = "Получить итоговую сумму товаров")
    public static ArrayList<Integer> getTotalPriceOfProducts(WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector("p.b-top__total span[data-jsv]"));
        String textOfElement = element.getText();

        int expectedTotalAmount = convertTextPriceToNumber(textOfElement);

        List<WebElement> priceElements = driver.findElements(By.cssSelector(listOfProductsPrices));
        int actualTotalAmount = getTotalPriceFromListOfWebElements(priceElements);

        ArrayList<Integer> dataAboutTotalAmounts = new ArrayList<>();
        dataAboutTotalAmounts.add(expectedTotalAmount);
        dataAboutTotalAmounts.add(actualTotalAmount);
        return dataAboutTotalAmounts;
    }

    @Step(value = "Найти кнопку \"Заказать\"")
    public static WebElement findButtonToOrder(WebDriver driver) {
        return driver.findElement(By.name(buttonToOrderName));
    }
}