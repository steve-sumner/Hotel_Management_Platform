package tests.ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import project.Constants;
import project.TestBase;

import java.util.Random;

/**
 * Created by Steve on 25/03/17.
 */
public class BasicUITest extends TestBase {

    @Test()
    public void BasicUITest(){
        HomePage hp = PageFactory.initElements(driver, HomePage.class);

        // Open URL & check the page title
        driver.get(Constants.ENVIRONMENT_URL);
        Assert.assertEquals("Hotel Management Platform", driver.getTitle());

        // Login to the site
        hp.login("admin", "password");

        // Create a single new hotel entry
        Assert.assertTrue(hp.createHotel(RandomStringUtils.randomAlphabetic(5) + " " +  RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomNumeric(9),RandomStringUtils.randomAlphabetic(5) + "@" +
                RandomStringUtils.randomAlphabetic(5) + "." + RandomStringUtils.randomAlphabetic(5) + ".com" ));


        // Delete functionality to go here but cannot get the button to work!


        // Create multiple new hotel entries
        for(int i = 1; i <= 3; i++ ){
            Assert.assertTrue(hp.createHotel(RandomStringUtils.randomAlphabetic(5) + " " +  RandomStringUtils.randomAlphabetic(5),
                    RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(5),
                    RandomStringUtils.randomNumeric(9),RandomStringUtils.randomAlphabetic(5) + "@" +
                    RandomStringUtils.randomAlphabetic(5) + "." + RandomStringUtils.randomAlphabetic(5) + ".com" ));
        }
    }
}