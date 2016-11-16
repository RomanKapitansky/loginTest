package tests;

import org.apache.commons.lang3.ObjectUtils;
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

public class Driver {
    private String siteUrl = "https://ru.wikipedia.org/wiki/";
    private WebDriver driver;
    private String Null;


    @BeforeSuite
    public void preCondition(){
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(siteUrl);
    }

    @Test
    public void enterClick() {
        WebElement searchBtn = driver.findElement(By.xpath("//*[@class='searchButton']"));
        searchBtn.click();
        String btnPropertyName = searchBtn.getAttribute("name");
        if (btnPropertyName != Null)
        {
            System.out.println(btnPropertyName + "element was clicked");
        }


        ;

        String btnName = searchBtn.getAttribute("name");
        //System.out.println(btnName);

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
