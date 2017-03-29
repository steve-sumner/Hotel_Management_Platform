package project;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * Created by SteveS on 29/03/17.
 *
 * Command Line:
 *
 *mvn -Dtest=BasicUITest -Dbrowser="Chrome" test
 *
 * To Do:
 * Logging
 * Error Handling
 * External Data Driving - hotel details, multiple entries etc...
 *
 */

public class TestBase {
    public static WebDriver driver;
    private project.DriverFactory driverFactory = new project.DriverFactory();


    @Before
    public void setUp(){
        // Do some setup stuff
        System.out.println("Initialising UI test: [" + this.getClass().getSimpleName() + "].");
        driver = driverFactory.createDriver();
    }


    @After
    public void tearDown(){
        // Do some tidy up stuff
        System.out.println("Test complete");
        driverFactory.quitDriver();
    }
}
