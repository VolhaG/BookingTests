package tests;

import pages.MainPage;
import pages.ResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.results.Hotel;

import java.util.Calendar;
import java.util.List;

public class BookingTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void setupTest() {
       // System.setProperty("webdriver.chrome.driver", "/Users/Olya/Applications/chromedriver");

        webDriver = new ChromeDriver();

        webDriver.navigate().to("https://booking.com");
    }

    //-----------------------------------Tests-----------------------------------

    @Test(testName = "Founded count of hotels in Minsk for weekend is more than 3.", priority = 1)
    public void hotelsInMinskOnWeekend() {

        MainPage mainPage = new MainPage(webDriver);

        Calendar nextSaturday = TestHelper.getNextSaturday();
        Calendar nextSunday = (Calendar) nextSaturday.clone();
        nextSunday.add(Calendar.DATE, 1);


        ResultsPage resultsPage = mainPage.enterDestination("Minsk")
                .enterDates(nextSaturday, nextSunday)
                .enterGuests(1, 2, 0)
                .clickSearch();

        Assert.assertTrue(resultsPage.getResultsCount() > 3, "Found at least 3 proposals");
    }


    @Test(testName = "Hotels in Minsk in ascending order price.", priority = 2,
            description = "This test can be failed, because as it was seen promoted hotels are not in sorting order. " +
                    "I think it is bug on the site.")
    public void hotelsInMinskInAscendingOrderPrice() {

        hotelsInMinskOnWeekend();
        ResultsPage resultsPage = new ResultsPage(webDriver);
        resultsPage = resultsPage.clickOnPriceOrder();

        List<Hotel> hotels = resultsPage.getHotelsList();
        double minPriceOfTwoHotels = 0;
        boolean pricesAreInAscendingOrder = true;
        for (Hotel hotel: hotels) {
            Double curPrice = hotel.getPrice();

            if (curPrice == null) {
                continue;
            }
            if (minPriceOfTwoHotels <= curPrice) {
                minPriceOfTwoHotels = curPrice;
            }
            else {
                pricesAreInAscendingOrder = false;
                break;
            }
        }

        Assert.assertTrue(pricesAreInAscendingOrder, "Prices for hotels are in ascending order");
    }

    @Test(testName = "Hotels in Minsk with rating upper 8.",  priority = 3)
    public void hotelsInMinskWithRatingUpper8() {

        hotelsInMinskOnWeekend();
        ResultsPage resultsPage = new ResultsPage(webDriver);
        resultsPage = resultsPage.clickOnRatingUpper8();
        List<Hotel> hotels = resultsPage.getHotelsList();
        boolean ratingIsUpper9 = true;
        for (Hotel hotel: hotels) {
            if (hotel.getRating() < 8) {
                ratingIsUpper9 = false;
                break;
            }
        }

        Assert.assertTrue(ratingIsUpper9, "Are found only hotels with rating upper 8");
    }

    @Test(testName = "Hotels in Minsk with rating upper 9.",  priority = 4)
    public void hotelsInMinskWithRatingUpper9() {

        hotelsInMinskOnWeekend();
        ResultsPage resultsPage = new ResultsPage(webDriver);
        resultsPage = resultsPage.clickOnRatingUpper9();
        List<Hotel> hotels = resultsPage.getHotelsList();
        boolean ratingIsUpper9 = true;
        for (Hotel hotel: hotels) {
            if (hotel.getRating() < 9) {
                ratingIsUpper9 = false;
                break;
            }
        }

        Assert.assertTrue(ratingIsUpper9, "Are found only hotels with rating upper 9");
    }

    @Test(testName = "Check if past check-in date is not accessible in search for Minsk.", priority = 5)
    public void checkIfPastCheckInDateIsNotAccessibleInSearchForMinsk() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.enterDestination("Minsk");

        Calendar calendar = Calendar.getInstance();
        Assert.assertTrue(mainPage.clickOnCheckIn().isDateEnabled(calendar), "Current check-in date is enabled");

        calendar.add(Calendar.DATE, -1);
        Assert.assertFalse(mainPage.clickOnCheckIn().isDateEnabled(calendar), "Past check-in date is not enabled");
    }

    @Test(testName ="Search for hotels in Bali with five stars rating.", priority = 6)
    public void searchForHotelsInBaliWithFiveStarsRating() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.enterDestination("Bali").clickSearch();
        ResultsPage resultsPage = new ResultsPage(webDriver);
        resultsPage = resultsPage.clickOnFiveStarsRating();
        List<Hotel> hotels = resultsPage.getHotelsList();
        boolean isFiveStarsRating = true;
        for (Hotel hotel: hotels) {
            int stars = hotel.getStars();
            if (stars != 5) {
                isFiveStarsRating = false;
                break;
            }
        }

        Assert.assertTrue(isFiveStarsRating, "Searching for hotels in Bali with 5 stars rating");
    }

    @Test(testName = "Check if count of hotels in Bali in city Ubud is more than 5.", priority = 7)
    public void checkIfCountOfHotelsInBaliInCityUbudIsMoreThan5() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.enterDestination("Bali").clickSearch();
        ResultsPage resultsPage = new ResultsPage(webDriver);
        resultsPage = resultsPage.clickOnCity("Ubud");
        List<Hotel> hotels = resultsPage.getHotelsList();

        Assert.assertTrue(resultsPage.getResultsCount() > 5,
                "Searching if count of hotels in Bali in Ubud is more than 5");
    }

    @Test(testName = "Book first found hotel in Minsk.", priority = 8)
    public void bookFirstHotelInMinsk() {
        hotelsInMinskOnWeekend();
        ResultsPage resultsPage = new ResultsPage(webDriver);
        Hotel firstHotel = resultsPage.getHotelsList().get(0);

        Assert.assertTrue(resultsPage.openHotelPage(firstHotel).isBookingAvailable(),
                "Book first found hotel in Minsk on selected dates");
    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        webDriver.quit();
    }
}

