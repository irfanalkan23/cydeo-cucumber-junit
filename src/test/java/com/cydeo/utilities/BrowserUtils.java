package com.cydeo.utilities;

// In this class only general utility methods that are not related to some specific page

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BrowserUtils {

    //Accept int (seconds) and execute Thread.sleep() for given duration
    public static void sleep(int second){
        second *= 1000;
        try{
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }

    //Switch window and verify title (day7 TC2 explained at the bottom)
    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle){
        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();
        for (String each : allWindowsHandles) {
            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());
            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }

        //Assert: Title contains expectedInTitle
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));

    }

    //Accept a String "expectedTitle" and Assert if it is true
    public static void verifyTitle(String expectedTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    //Creating a utility method for ExplicitWait so we don't have to repeat the lines
    public static void waitForInvisibilityOf(WebElement webElement){
//        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);     // turning off the Driver class implicitWait(10) to 0.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }
}
