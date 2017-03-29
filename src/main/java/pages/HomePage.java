package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static project.TestBase.driver;

/**
 * Created by Steve on 25/03/17.
 */
public class HomePage {

    // Login
    @FindBy(how = How.XPATH, using = "html/body/div[1]/nav/div[1]/ul/li[2]/a")
    public WebElement mnuLogin;

    @FindBy(how = How.ID, using = "username")
    public WebElement edtUsername;

    @FindBy(how = How.ID, using = "password")
    public WebElement edtPassword;

    @FindBy(how = How.ID, using = "doLogin")
    public WebElement btnLogin;

    // Create hotel
    @FindBy(how = How.ID, using = "hotelName")
    public WebElement edtHotelName;

    @FindBy(how = How.ID, using = "address")
    public WebElement edtAddress;

    @FindBy(how = How.ID, using = "owner")
    public WebElement edtOwner;

    @FindBy(how = How.ID, using = "phone")
    public WebElement edtPhone;

    @FindBy(how = How.ID, using = "email")
    public WebElement edtEmail;

    @FindBy(how = How.ID, using = "createHotel")
    public WebElement btnCreate;


    //
    // Login to the site
    //
    public void login(String username, String password){
        mnuLogin.click();
        edtUsername.sendKeys(username);
        edtPassword.sendKeys(password);
        btnLogin.click();
    }


    //
    // Create a new hotel entry
    //
    public boolean createHotel(String name, String address, String owner, String phone, String email){
        List<WebElement> hotels = driver.findElements(By.className("hotelRow"));
        System.out.println("About to add new hotel currently [" + hotels.size() +  "] displayed.");

        edtHotelName.sendKeys(name);
        edtAddress.sendKeys(address);
        edtOwner.sendKeys(owner);
        edtPhone.sendKeys(phone);
        edtEmail.sendKeys(email);
        btnCreate.click();

        // wait a second for the page to catch up
        waitFor(1000);

        // Verify the hotel has been added
        String[] hotelProperties = null;
        boolean hotelFound = false;
        for (int i = 0; i <= hotels.size() - 1; i++){
            hotels = driver.findElements(By.className("hotelRow"));
            hotelProperties = hotels.get(i).getText().split("\n");
            if (hotelProperties[0].contains(name)){
                hotelFound = true;
            }
        }

        hotels = driver.findElements(By.className("hotelRow"));
        if (hotelFound) {
            System.out.println("Hotel with the name [" + hotelProperties[0] + "] has been added.");
            System.out.println("New hotel successfully added, currently [" + hotels.size() + "] displayed.");
        }else{
            System.out.println("Hotel with the name [" + name + "] has not been added!");
            System.out.println("New hotel has NOT been added, currently [" + hotels.size() + "] displayed.");
        }

        return hotelFound;
    }


    // Wait for utility method
    public void waitFor(int milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

}
