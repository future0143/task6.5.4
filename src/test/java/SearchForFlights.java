import config.ConfigSetup;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static elements.ElemOfSearchForFlightsCase.filterFlightsSelector;
import static elements.ElemOfSearchForFlightsCase.filterTripsSelector;
import static steps.StepsForSearchForFlights.*;
import static steps.StepsForWorkWithFilters.clickFilters;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchForFlights extends ConfigSetup {

    @Test
    @DisplayName("Поиск авиабилетов")
    @Description("Поиск авиабилетов")
    public void searchTickets() {
        clickFilters(driver);

        chooseFilters(driver, filterTripsSelector, filterFlightsSelector);

        String expectedUrl = "https://vmeste.wildberries.ru/avia";
        checkNavigationToPage(driver, expectedUrl);

        String from = "Домодедово";
        enterCity(driver, fieldFromForInput, from);

        String where = "Пулково";
        enterCity(driver, fieldWhereForInput, where);

        enterDateInField(driver);

        choosePassengers(driver);

        clickSearchTickets(driver);

        checkResultsOfSearch(driver);
    }
}