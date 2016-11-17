package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by spodgorov on 11/16/2016.
 */
public class Main {
    private String a = "abc";
    float b = 6;
    int c = 5;
    @Test
    public void driverTest(){
        Driver driver = new Driver();
        Driver driver2 = new Driver();
        boolean result = driver.clickElement("button");
        Assert.assertTrue(result);

        boolean result2 = driver.clickElement(null);
        Assert.assertFalse(result2);
    }
}
