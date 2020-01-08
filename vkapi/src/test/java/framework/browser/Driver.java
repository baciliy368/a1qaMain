package framework.browser;

import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserManager;
import aquality.selenium.forms.Form;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.Arrays;

public class Driver {
    public static WebElement getElement(By locator) {
        return BrowserManager.getBrowser().getDriver().findElement(locator);
    }

    public static WebElement getElementAfterWaitOfVisible(By locator) {
        return new FluentWait<>(BrowserManager.getBrowser())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(ElementNotVisibleException.class, NoSuchElementException.class)
                .until((Browser driver) -> driver.getDriver().findElement(locator));
    }

    public static boolean isElementDeleted(WebElement element) {
        return new FluentWait<>(BrowserManager.getBrowser())
                .withTimeout(Duration.ofSeconds(
                        Integer.parseInt(PropertiesReader.getValue("FLUENT_TIME"))))
                .pollingEvery(Duration.ofSeconds(
                        Integer.parseInt(PropertiesReader.getValue("FLUENT_TIME_UPDATE"))))
                .ignoring(ElementNotVisibleException.class, NoSuchElementException.class)
                .until((Browser driver) -> !element.isDisplayed());
    }

    public static String getUrlOfPage(Form form) {
        if ((form.isFormDisplayed(Long.parseLong(PropertiesReader.getValue("DEFAULT_TIME_OF_CUSTOM_WAITING"))))) {
            return BrowserManager.getBrowser().getCurrentUrl();
        }
        ElementNotVisibleException elementNotVisibleException = new ElementNotVisibleException("It isn`t this page");
        Log.error(Arrays.toString(elementNotVisibleException.getStackTrace()));
        throw elementNotVisibleException;
    }
}
