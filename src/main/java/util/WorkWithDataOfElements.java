package util;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WorkWithDataOfElements {

    public static int convertTextPriceToNumber(String textOfElement) {
        String expectedTotalAmountText = textOfElement.replaceAll("[^0-9]", ""); // Преобразуем строку в число, удаляя пробелы
        System.out.println(expectedTotalAmountText);

        return Integer.parseInt(expectedTotalAmountText);
    }

    public static int getTotalPriceFromListOfWebElements(List<WebElement> priceElements) {
        int actualTotalAmount = 0;
        for (WebElement priceElement : priceElements) {
            actualTotalAmount += convertTextPriceToNumber(priceElement.getText());
        }
        return actualTotalAmount;
    }

    public static List<String> getListOfTextsFromListOfElements(List<WebElement> listOfFiltersActual) {
        List<String> textOfFiltersActual = new ArrayList<>();
        for (WebElement element : listOfFiltersActual) {
            textOfFiltersActual.add(element.getText());
        }
        return textOfFiltersActual;
    }
}