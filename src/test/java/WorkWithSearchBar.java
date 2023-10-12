import config.ConfigSetup;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebElement;

import static steps.StepsForWorkWithSearchBar.*;
import static validator.ValidationOfElements.checkFieldIsEmpty;
import static validator.ValidationOfElements.checkText;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WorkWithSearchBar extends ConfigSetup {

    @Test
    @DisplayName("Работа с поисковой строкой")
    @Description("Работа с поисковой строкой")
    public void workWithSearchBar() {
        String keys = "iphone 13";
        WebElement searchBar = enterProductNameToSearchLine(driver, keys);

        String expectedTextOnPage = "По запросу " + "«" + keys + "»" + " найдено";
        checkText(expectedTextOnPage, findTextOnPage(driver));

        String expectedTextOfSorterFilter = "По популярности";
        checkText(expectedTextOfSorterFilter, findNameOfSorterFilter(driver));

        String expectedTextOfFirstFilter = "iphone 13";
        checkText(expectedTextOfFirstFilter, findTextOnFirstFilter(driver));

        int indexOfGoods = 0;
        String brandOfFirstProduct = findBrandOfFirstProduct(driver, indexOfGoods);
        checkText("Apple", brandOfFirstProduct);

        clearSearchBar(driver);

        checkFieldIsEmpty(searchBar);
    }
}