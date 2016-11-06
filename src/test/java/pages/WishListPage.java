package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends BasePage{

    private By titleTextLocator = By.tagName("h2");
    private By editAccountLinkLocator = By.xpath("//*[@class='leftside-menu__item']//a[contains(@href, 'account/edit/')]");

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isElementLoaded(By.className("wishlist-help"), DEFAULT_WAIT_TIME);
    }

    public String titleText() {
        return driver.findElement(titleTextLocator).getText();
    }

    public void clickEditAccountLink() {
        driver.findElement(editAccountLinkLocator).click();
    }

}