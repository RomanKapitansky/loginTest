package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.EditAccountPage;
import pages.MainPage;
import pages.WishListPage;
import utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTeTest {

    private Utils utils = new Utils();
    private WebDriver driver;
    private String homePage = "http://www.lamoda.ua/";

    @BeforeSuite
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(homePage);
    }

    @Test(description="")
    public void registerNewUserAtLamoda() {
        String expectedUserName = "TestName";
        String expectedUserEmail = utils.generateTestEmail();

        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.isPageLoaded(), "Main page was not loaded");

        mainPage.closeStartPopup();
        mainPage.enterLink().click();
        assertTrue(mainPage.loginForm().isDisplayed(), "Login form expected to be shown");
        mainPage.registrationLink().click();
        mainPage.fillRegistrationFormWithData(expectedUserEmail, "TestPassword", expectedUserName);
        assertTrue(mainPage.profileLink().isDisplayed(), "Link to the profile page must be visible");
        mainPage.clickOnWishListLink();

        WishListPage wishListPage = new WishListPage(driver);
        assertTrue(wishListPage.isPageLoaded(), "Wish List page was not loaded");
        assertTrue(wishListPage.titleText().contains(expectedUserName), "Title text must contain username");
//        wishListPage.clickEditAccountLink();
//
//        EditAccountPage editAccountPage = new EditAccountPage(driver);
//        assertTrue(editAccountPage.isPageLoaded(), "Edit account page was not loaded");
//        assertEquals(editAccountPage.userEmailText(), expectedUserEmail);

    }

    @AfterSuite
    public void afterMethod() {
        driver.quit();
    }

}