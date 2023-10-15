package validator;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class ValidationOfElements {

    public static void checkText(String expectedText, String actualText) {
        Assertions.assertEquals(expectedText, actualText);
    }

    public static void checkFieldIsEmpty(WebElement field) {
        Assertions.assertTrue(field.getText().isEmpty());
    }

    public static void checkElementDisplayed(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());
    }

    public static void validateSumOrCount(int expectedSum, int actualSum) {
        Assertions.assertEquals(expectedSum, actualSum);
    }

    public static void checkButtonIsEnabled(WebElement button) {
        Assertions.assertTrue(button.isEnabled());
    }

    public static void checkListsForSimilarity(List<String> listOfFiltersExpected, List<String> listOfFiltersActual) {
        Assertions.assertTrue(!Collections.disjoint(listOfFiltersActual, listOfFiltersExpected));
    }
}