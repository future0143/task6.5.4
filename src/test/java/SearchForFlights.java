import config.ConfigSetup;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static elements.ElemOfSearchForFlightsCase.filterFlightsSelector;
import static elements.ElemOfSearchForFlightsCase.filterTripsSelector;
import static steps.StepsForSearchForFlights.chooseFilters;
import static steps.StepsForWorkWithFilters.clickFilters;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchForFlights extends ConfigSetup {

    @Test
    @DisplayName("Поиск авиабилетов")
    @Description("Поиск авиабилетов")
    public void changeCity() {
        clickFilters(driver);

        chooseFilters(driver, filterTripsSelector, filterFlightsSelector);
    }
}
