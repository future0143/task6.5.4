import config.ConfigSetup;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebElement;

import static steps.StepsForCityChange.*;
import static validator.ValidationOfElements.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityChange extends ConfigSetup {

    @Test
    @DisplayName("Смена города")
    @Description("Смена города")
    public void changeCity() {
        String urlHomePage = driver.getCurrentUrl();
        clickChangeCity(driver);

        String cityForInput = "Санкт-Петербург";
        inputCityInSearchLine(driver, cityForInput);

        String firstAddress = chooseFirstAddressFromList(driver);

//Открылась информация о центре выдачи
        WebElement infoAboutPoint = findInfoAboutPickUpPoint(driver);
        checkElementDisplayed(infoAboutPoint);
//Адрес пункта выдачи совпадает с тем адресом, что был предложен в списке адресов
        checkText(firstAddress, findAddressIntoInfoAboutPoint(infoAboutPoint));

        clickButtonChoose(driver);

        String currentUrlAfterChoosing = driver.getCurrentUrl();
        validateUrlEquals(urlHomePage, currentUrlAfterChoosing);

        String currentAddress = findCurrentAddress(driver);
        checkText(firstAddress,currentAddress);
    }
}