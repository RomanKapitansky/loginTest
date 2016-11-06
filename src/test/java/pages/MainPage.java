package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{

    private By startPopupLocator = By.className("popup__inner");
    private By startPopupCloseButtonLocator = By.className("popup__close");
    private By enterLinkLocator = By.xpath("//*[@data-action='showAuth']");
    private By loginFormLocator = By.className("login-form");
    private By registrationLinkLocator = By.className("login-form__register");
    private By registrationFormLocator = By.className("register-form");
    private By emailFieldLocator = By.name("email");
    private By passwordFieldLocator = By.name("password");
    private By repeatPasswordFieldLocator = By.name("password2");
    private By nameFieldLocator = By.name("first_name");
    private By registrationButtonLocator = By.className("button_blue");
    private By profilePageLinkLocator = By.xpath("//div[@class='link user-nav__link']");
    private By wishListLinkLocator = By.xpath("//*[@class='user-nav user-nav_signed']//a");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isElementLoaded(By.className("header"), DEFAULT_WAIT_TIME);
    }

    public void closeStartPopup() {
        if(driver.findElement(startPopupLocator).isDisplayed()) {
            driver.findElement(startPopupCloseButtonLocator).click();
        }
    }

    public WebElement enterLink() {
        return driver.findElement(enterLinkLocator);
    }

    public WebElement loginForm() {
        return driver.findElement(loginFormLocator);
    }

    public WebElement registrationLink() {
        return driver.findElement(registrationLinkLocator);
    }

    public void fillRegistrationFormWithData(String email, String password, String name) {
        WebElement registrationForm = findObject(registrationFormLocator, DEFAULT_WAIT_TIME);
        registrationForm.findElement(emailFieldLocator).sendKeys(email);
        registrationForm.findElement(passwordFieldLocator).sendKeys(password);
        registrationForm.findElement(repeatPasswordFieldLocator).sendKeys(password);
        registrationForm.findElement(nameFieldLocator).sendKeys(name);
        registrationForm.findElement(registrationButtonLocator).click();
    }

    public WebElement profileLink() {
        return findObject(profilePageLinkLocator, DEFAULT_WAIT_TIME);
    }

    public void clickOnWishListLink() {
        driver.findElement(wishListLinkLocator).click();
    }

}