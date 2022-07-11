package com.cydeo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

// "Singleton Design pattern"
public class Driver {

    /*
    Creating a private constructor, so we are closing
    access to the object of this class from outside the class
     */
    private Driver(){}

    /*
    We make WebDriver private, because we want to close access from outside the class
    We make it static because we will use it in a static method
     */
//    private static WebDriver driver;    // value is null by default

    // this will create me the driver pool
    // and we already created the maven plugin for the Threads
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create a reusable method which will return same driver instance when we call it
     */
    public static WebDriver getDriver(){

        if(driverPool.get() == null){       // instead of "driver" --> driverPool.get()

            /*
            We read our browserType from configuration.properties.
            This way, we can control which browser is opened from outside our code, from configuration.properties
             */
            String browserType = ConfigurationReader.getProperty("browser");

            /*
            Depending on the browserType that will be return from configuration.properties file
            switch statement will determine the case, ande open the matching browser
             */
            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver();      // instead of this, we changed to below line (for parallel testing)
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();   // instead of "driver" --> driverPool.get()
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   // instead of "driver" --> driverPool.get()
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
            }
        }
        return driverPool.get();   // instead of "driver" --> driverPool.get()

    }

    //driver.quit() --> nosuchsession
    //driver.close() -->

    //try to create a method named closeDriver
    /*
    This method will make sure our driver value is always null after using quit() method
    */
    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit(); // this line will terminate the existing session. value will not even be null
            driverPool.remove();    // instead of driver == null --> driverPool.remove()
        }
    }
}
