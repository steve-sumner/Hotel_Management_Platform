package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Steve on 25/03/17.
 */
public class DriverFactory {
    private WebDriver driver;

    public WebDriver createDriver(){
        // Create the required driver
        try {
            if (Constants.BROWSER.equalsIgnoreCase("Chrome")){
                driver = createChromeDriver();
            }else if (Constants.BROWSER.equalsIgnoreCase("Firefox")){
                // Create Firefox driver
            }else if (Constants.BROWSER.equalsIgnoreCase("IE")){
                // Create IE driver
            }else{
                System.out.println("Unknown WebDriver Browser: [" + Constants.BROWSER + "], defaulting to Chrome.");
                driver = createChromeDriver();
            }

            // Maximise the browser window and set some timeout defaults
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (Exception e){
            throw new RuntimeException("An exception occurred while creating " + Constants.BROWSER + " driver.  Details: [" + e.toString() + "].");
        }

        return driver;
    }


    public WebDriver createChromeDriver(){
        // Setup and return a chrome driver
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        File file = new File(Constants.CHROME_DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        return new ChromeDriver(cap);
    }


    public void quitDriver(){
        if (null != driver){
            driver.quit();
        }
    }
}
