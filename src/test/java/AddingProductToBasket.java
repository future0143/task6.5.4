import config.ConfigSetup;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static steps.StepsForAddProductToBasket.*;
import static validator.ValidationOfElements.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddingProductToBasket extends ConfigSetup {

    @Test
    @DisplayName("Добавление товара в корзину")
    @Description("Добавление товара в корзину")
    public void addProductToBasket() {
        clickFilters(driver);

        chooseFilters(driver, filterAppliances, homeAppliances, vacuumAndSteamCleaners);

        String expectedTitle = "Пылесосы и пароочистители";
        checkTitleOfCatalog(expectedTitle, findTitleOfCatalog(driver));

        checkPathOfFilters(driver);

        int indexOfProduct = 0;
        ArrayList<String> dataAboutProduct = addFirstProductToBasket(driver, indexOfProduct);

        int expectedCount = 1;
        checkCountOfProductsInBasket(driver, expectedCount);

        goToBasket(driver);

        checkDataOfProduct(driver, dataAboutProduct);

        ArrayList<Integer> totalPricesOfProducts = getTotalPriceOfProducts(driver);
        validateSumOrCount(totalPricesOfProducts.get(0), totalPricesOfProducts.get(1));

        WebElement buttonToOrder = findButtonToOrder(driver);
        checkButtonIsEnabled(buttonToOrder);
    }
}
