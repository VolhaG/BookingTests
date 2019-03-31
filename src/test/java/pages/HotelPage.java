package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.PageObject;

import javax.annotation.Nonnull;

public class HotelPage extends PageObject {

    @FindBy(id = "hp_book_now_button")
    private WebElement bookNowButton;

    public HotelPage(@Nonnull WebDriver driver) {
        super(driver);
    }

    public boolean isBookingAvailable() {
        try {
            return bookNowButton.isDisplayed() && bookNowButton.isEnabled();
        } catch (NoSuchElementException exc) {
            return false;
        }
    }
}
