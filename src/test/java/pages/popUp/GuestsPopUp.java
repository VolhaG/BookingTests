package pages.popUp;

import pages.base.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.Nonnull;


public class GuestsPopUp extends PageObject{

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[3]/div[2]/div/div/div[1]/div/div[2]/button[2]")
    private WebElement increaseRoomsCount;
    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[3]/div[2]/div/div/div[1]/div/div[2]/button[1]")
    private WebElement decreaseRoomsCount;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[3]/div[2]/div/div/div[2]/div/div[2]/button[2]")
    private WebElement increaseAdultsCount;
    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[3]/div[2]/div/div/div[2]/div/div[2]/button[1]")
    private WebElement decreaseAdultsCount;

    public GuestsPopUp(@Nonnull WebDriver driver){
        super(driver);
    }

    public GuestsPopUp setRooms(int rooms) {
        int i = 1;
        while (i < rooms) {
            increaseRoomsCount.click();
        }
        return this;
    }

    public GuestsPopUp setAdults(int adults) {
        int i = 2;
        if (adults == 1) {
            decreaseAdultsCount.click();
        }
        while (i < adults) {
            increaseAdultsCount.click();
        }
        return this;
    }
}
