package pages.popUp;

import pages.base.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarPopUp extends PageObject {
    public CalendarPopUp(@Nonnull WebDriver driver) {
        super(driver);
    }

    public CalendarPopUp clickOnDate(Calendar date) {
        findDateElement(date).click();
        return this;
    }

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String getXPathForDate(Calendar date) {
        String dataDate = dateFormat.format(date.getTime());
        return "//td[contains(@data-date, '" + dataDate + "')]";
    }

    private WebElement findDateElement(Calendar date) {
        return webDriver.findElement(By.xpath(getXPathForDate(date)));
    }

    public boolean isDateEnabled(Calendar calendar) {
        return !findDateElement(calendar).getAttribute("class")
                .equals("bui-calendar__date bui-calendar__date--disabled");
    }
//    public Boolean ifNotEnabledPastDate() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, -1);
//        int pastDayOfCalendar = calendar.get(Calendar.DAY_OF_MONTH);
//        WebElement pastDay = webDriver.findElement(By.xpath("//td[@data-bui-ref=\"calendar-date\" and contains(text(),'" + pastDayOfCalendar + "')]"));
//        return !pastDay.isEnabled();
//    }
}
