import config.ConfigSetup;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebElement;

import java.util.List;

import static elements.ElemOfWorkWithFiltersCase.*;
import static steps.StepsForAddProductToBasket.chooseFilters;
import static steps.StepsForAddProductToBasket.findTitleOfCatalog;
import static steps.StepsForWorkWithFilters.*;
import static validator.ValidationOfElements.checkElementDisplayed;
import static validator.ValidationOfElements.checkTitleOfCatalog;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WorkWithFilters extends ConfigSetup {

    @Test
    @DisplayName("Работа с фильтрами")
    @Description("Работа с фильтрами")
    public void addProductToBasket() {
        clickFilters(driver);

        chooseFilters(driver, filterElectronicsSelector, filterLaptopsAndPC, filterLaptops);

        String expectedTitle = "Ноутбуки и ультрабуки";
        checkTitleOfCatalog(expectedTitle, findTitleOfCatalog(driver));

        String minPrice = "100 000";
        String maxPrice = "149 000";
        List<String> listOfFilters = applyFilters(driver, minPrice, maxPrice);

        checkFilterActivated(driver);

        checkCountOfProducts(driver);

        checkSelectedFiltersOnPage(driver, listOfFilters);

        WebElement buttonToResetAll = findButtonToResetAll(driver);
        checkElementDisplayed(buttonToResetAll);
    }
}
