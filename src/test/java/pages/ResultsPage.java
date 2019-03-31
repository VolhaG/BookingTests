package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.PageObject;
import pages.results.Hotel;
import tests.TestHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResultsPage extends PageObject {

    public ResultsPage(@Nonnull WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@aria-label, 'Search results')]")
    private WebElement hotelList;

    @FindBy(xpath = "//a[@class = 'sort_option ' and @data-category = 'price']")
    private WebElement priceList;

    @FindBy(xpath = "//div[@id = 'filter_review']//span[contains(text(), '8+')]")
    private WebElement ratingUpper8;

    @FindBy(xpath = "//div[@id = 'filter_review']//span[contains(text(), '9+')]")
    private WebElement ratingUpper9;

    @FindBy(xpath = "//div[@id = 'filter_class']//span[contains(text(), '5 stars')]")
    private WebElement ratingFiveStars;

    @FindBy(xpath = "//div[@id ='filter_uf']/*/*/*/span[contains(text(),'Ubud')]")
    private WebElement cityUbudInBali;

    public int getResultsCount() {
        List<WebElement> searchResults = hotelList.findElements(By.xpath("//div[@data-hotelid]"));
        return searchResults.size();
    }

    public ResultsPage clickOnPriceOrder() {
        priceList.click();
        TestHelper.pause(5);
        return this;
    }

    public List<Hotel> getHotelsList(){
        List <WebElement> searchResults = hotelList.findElements(By.xpath("//div[@data-hotelid]"));
        List <Hotel> hotelList = new ArrayList<Hotel>();
        for (WebElement element: searchResults) {
            hotelList.add(new Hotel(element));
        }
        return hotelList;
    }

    public ResultsPage clickOnRatingUpper9() {
        ratingUpper9.click();
        TestHelper.pause(5);
        return this;
    }

    public ResultsPage clickOnFiveStarsRating() {
        ratingFiveStars.click();
        TestHelper.pause(5);
        return this;
    }

    public ResultsPage clickOnRatingUpper8() {
        ratingUpper8.click();
        TestHelper.pause(5);
        return this;
    }

    public HotelPage openHotelPage(Hotel hotel) {
        String hotelName = hotel.getName();
        hotel.openHotelPage();

        Set<String> handles = webDriver.getWindowHandles();
        for (String winHandle : handles) {
            WebDriver driver = webDriver.switchTo().window(winHandle);
            if (driver.getTitle().startsWith(hotelName)) {
                return new HotelPage(driver);
            }
        }
        return null;
    }

    public ResultsPage clickOnCity(String city) {
        webDriver.findElement(By.xpath("//div[@id ='filter_uf']/*/*/*/span[contains(text(),'" + city + "')]")).click();
        TestHelper.pause(5);
        return this;
    }
}
