package pages.results;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Hotel {
    private final WebElement webElement;

    public Hotel(WebElement webElement) {
        this.webElement = webElement;
    }


    public Double getPrice() {
        try {
            WebElement priceElement = webElement.findElement(By.xpath(".//b"));
            String priceText = priceElement.getText();
            int lengthOfPriceText = priceText.length();
            priceText = priceText.substring(priceText.indexOf(" ") + 1, lengthOfPriceText);
            return Double.parseDouble(priceText);
        } catch (NoSuchElementException exc) {
            return null;
        }
    }

    public Double getRating() {
        WebElement ratingElement = webElement.findElement(By.xpath(".//div[@class ='bui-review-score__badge']"));
        String ratingText = ratingElement.getText();
        ratingText.replace(" ", "");
        return Double.parseDouble(ratingText);
    }

    public int getStars() {
        WebElement ratingElement = webElement.findElement(By.xpath(".//span[@class='invisible_spoken' and contains(text(),'star')]"));
        String ratingText = ratingElement.getText();
        String stars = ratingText.substring(0, 1);
        return Integer.parseInt(stars);
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public String getName() {
        WebElement hotelName = webElement.findElement(By.className("sr-hotel__name\n"));
        return hotelName.getText();
    }

    public void openHotelPage() {
        WebElement hotelName = webElement.findElement(By.className("sr-hotel__name\n"));
        hotelName.click();
    }
}
