package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final int DEFAULT_WAIT_TIME = 10;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected boolean isElementLoaded(By by, int waitTime) {
        WebElement expectedElement;
        try {
            expectedElement = findObject(by, waitTime);
        } catch (WebDriverException e) {
            return false;
        }
        return expectedElement.isDisplayed();
    }

    protected WebElement findObject(final By by, int waitTime) {
        return (new WebDriverWait(driver, waitTime))
                .until(new ExpectedCondition<WebElement>() {
                    public WebElement apply(WebDriver d) {
                        return d.findElement(by);
                    }
                });
    }

    protected void waitUntilInvisible(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}