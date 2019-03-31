package pages;

import pages.base.PageObject;
import pages.popUp.CalendarPopUp;
import pages.popUp.GuestsPopUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nonnull;
import java.util.Calendar;

public class MainPage extends PageObject {

    public MainPage(@Nonnull WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ss")
    private WebElement destinationField;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[2]/div/div/div/div/span")
    private WebElement checkInField;

    @FindBy(id = "xp__guests__toggle")
    private WebElement guestsField;

    @FindBy(xpath = "//button[@data-sb-id ='main']")
    private WebElement searchField;

    public MainPage enterDestination(String dest) {
        destinationField.sendKeys(dest);
        return this;
    }

    public MainPage enterDates(Calendar checkIn, Calendar checkOut) {
        clickOnCheckIn()
                .clickOnDate(checkIn)
                .clickOnDate(checkOut);
        return this;
    }

    public CalendarPopUp clickOnCheckIn() {
        checkInField.click();
        return new CalendarPopUp(webDriver);
    }

    public MainPage enterGuests(int rooms, int adults, int children) {
        clickOnSelectGuests()
                .setRooms(rooms)
                .setAdults(adults);
        return this;
    }

    private GuestsPopUp clickOnSelectGuests() {
        guestsField.click();
        return new GuestsPopUp(webDriver);
    }

    public ResultsPage clickSearch() {
        searchField.click();
        return new ResultsPage(webDriver);
    }

}
