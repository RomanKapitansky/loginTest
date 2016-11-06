package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditAccountPage extends BasePage{

    private By emailFieldAccount = By.name("email");

    public EditAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isElementLoaded(By.className("account-header__title"), DEFAULT_WAIT_TIME);
    }

    public String userEmailText() {
        return driver.findElement(emailFieldAccount).getAttribute("value");
    }
}
