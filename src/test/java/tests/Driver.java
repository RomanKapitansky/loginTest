package tests;

import org.openqa.selenium.WebDriver;

public class Driver {


    /**
     * return 1 of everything OK
     * return 0 if not ok
     */
    public boolean clickElement(String args) {
        if (args != null) {
            System.out.println("element " + args + " was clicked");
            return true;
        } else {
            return false;
        }

    }
}

