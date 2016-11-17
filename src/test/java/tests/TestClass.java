package tests;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by spodgorov on 11/17/2016.
 */
public class TestClass {
    private String siteURL = "https://google.com.ua";

    private WebDriver driver;

    @BeforeSuite
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get(siteURL);
    }
    @Test
    public void googleSearch(){
        WebElement inputSearch = driver.findElement(By.className("gsfi"));
        inputSearch.sendKeys("test");
        WebElement submit = driver.findElement(By.name("//*[@name='btnK']"));
        submit.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void closeBrowser(){
    driver.quit();
    }

}


