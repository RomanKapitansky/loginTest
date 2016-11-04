package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    @Test(description="")
    public void registerNewUserAtLamoda() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //open site
        driver.get("http://www.lamoda.ua/");

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("header")).isDisplayed();
            }
        });
        WebElement startPopup = driver.findElement(By.className("popup__inner"));
        if(startPopup.isDisplayed()) {
            WebElement startPopupCloseButton = startPopup.findElement(By.className("popup__close"));
            startPopupCloseButton.click();
        }

        //click Enter link
        WebElement enterLink = driver.findElement(By.xpath("//*[@data-action='showAuth']"));
        enterLink.isDisplayed();
        enterLink.click();

        WebElement loginForm = driver.findElement(By.className("login-form"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not appeared after click on the Login link");
        loginForm.findElement(By.className("login-form__register")).click();

        //fill registration form
        WebElement registrationForm = driver.findElement(By.className("register-form"));
        registrationForm.isDisplayed();
        registrationForm.findElement(By.name("email")).sendKeys("name@mail.com");
        registrationForm.findElement(By.name("password")).sendKeys("namemailcom");
        registrationForm.findElement(By.name("password2")).sendKeys("namemailcom");
        registrationForm.findElement(By.name("first_name")).sendKeys("namemailcom");
//        registrationForm.findElement(By.className("button_blue")).click();

        //after registration link to the Profile page should be present
        //driver.findElement(By.className("user-nav__link"));


        driver.quit();
    }
}