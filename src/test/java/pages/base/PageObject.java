package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.annotation.Nonnull;

public abstract class PageObject {

    protected final WebDriver webDriver;

    protected PageObject(@Nonnull WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
}
