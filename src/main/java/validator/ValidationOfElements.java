package validator;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class ValidationOfElements {

    public static void validateUrlNotEquals(String urlBeforeSwitch, String currentUrlNewWindow) {
        Assertions.assertNotEquals(urlBeforeSwitch, currentUrlNewWindow);
    }

    public static void validateUrlEquals(String urlBeforeSwitch, String currentUrlNewWindow) {
        Assertions.assertEquals(urlBeforeSwitch, currentUrlNewWindow);
    }

    public static void validateElementByText(WebElement newPageText, String expectedText) {
        Assertions.assertEquals(expectedText, newPageText.getText());
    }

    public static void validateAlertByText(Alert alertText, String expectedText) {
        Assertions.assertEquals(expectedText, alertText.getText());
    }

    public static void checkText(String expectedText, String actualText) {
        Assertions.assertEquals(expectedText, actualText);
    }

    public static void checkFieldIsEmpty(WebElement field) {
        Assertions.assertTrue(field.getText().isEmpty());
    }
    public static void checkElementDisplayed(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());
    }

    public static void checkTitleOfCatalog(String expectedTitle, String actualTitle) {
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    public static void checkPathOfFilter(String expectedPath, String actualPath) {
        Assertions.assertEquals(expectedPath, actualPath);
    }

    public static void validateSumOrCount(int expectedSum, int actualSum) {
        Assertions.assertEquals(expectedSum, actualSum);
    }

    public static void checkButtonIsEnabled (WebElement button) {
        Assertions.assertTrue(button.isEnabled());
    }
    public static void checkListsForSimilarity (List<String> listOfFiltersExpected, List<String> listOfFiltersActual) {
        Assertions.assertTrue(!Collections.disjoint(listOfFiltersActual, listOfFiltersExpected));
    }
}