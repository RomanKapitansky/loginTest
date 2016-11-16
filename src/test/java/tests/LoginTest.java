package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by spodgorov on 11/10/2016.
 */

public class LoginTest {
    private String siteUrl = "http://www.lamoda.ua";
    private WebDriver driver;


    @BeforeSuite
    public void preCondition(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(siteUrl);
    }

    @Test
    public void test1(){
        String field1 = "testvalue1";
        String field2 = "testvalue1";
        Assert.assertEquals(field1, field2, "expected behaviour message");
        Assert.assertTrue(field1.equals(field2));

        WebElement popCloseBtn = driver.findElement(By.className("popup__close"));
        popCloseBtn.click();
        boolean isButtonDisplay = popCloseBtn.isDisplayed(); // Передаем знаение true или false в переменную isButtonDisplay
        Assert.assertFalse(isButtonDisplay); // проверяем, что переданное значение = false

        //WebElement el = findObject(By.className("popup__close"), 4000);



        WebElement searchBtn = driver.findElement(By.xpath("//div[@class='button button_blue search__button js-search-button']"));
        searchBtn.click();
        WebElement searchField = driver.findElement(By.xpath("//input[@class='text-field text-field_large search__input js-search-field']"));

        Assert.assertEquals(searchField.isDisplayed(),true);// isDisplayed вернёт актуальное состояние, а через запятую ExpectedResult
        String searchWord = "обувь";
        searchField.sendKeys(searchWord);
        searchBtn.click();

        WebElement resultTitleBlock = findObject(By.className("search-page__title"),10000); // ожидание появления на странице элемента  по заданному критерию на протяжении 10 секунд
        Assert.assertTrue(resultTitleBlock.getText().contains(searchWord));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @AfterSuite
    public void postConditon(){
        driver.quit();
    }

    // Данный метод возвращает WebElement
    private  WebElement findObject(final By by, int waitTime) {
        return (new WebDriverWait(driver, waitTime))
                .until(new ExpectedCondition<WebElement>() {
                    public WebElement apply(WebDriver d) {
                        return d.findElement(by);
                    }
                });
    }
}
