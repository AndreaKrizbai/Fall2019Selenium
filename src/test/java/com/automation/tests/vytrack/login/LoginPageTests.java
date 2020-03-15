package com.automation.tests.vytrack.login;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//static import for all assertions
import static org.testng.Assert.*;

public class LoginPageTests {
    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";
    //correct credentials
    private String username = "storeManager85";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    //. in css selector means same thing as / in xpath --> direct child
    private By warningMessageBy = By.cssSelector("[class='alert alert-error']>div");

    @Test(description = "Verify that warning message displays when user enters invalid username")
    public void invalidUsername(){
        driver.findElement(usernameBy).sendKeys("invalidusername");
        driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
        BrowserUtils.wait(2);
        WebElement warningElement = driver.findElement(warningMessageBy);
        assertTrue(warningElement.isDisplayed());
        //assertTrue(driver.findElement(By.className("alert alert-error")).isDisplayed());
        String expected = "Invalid user name or password.";
        String actual = warningElement.getText();
        assertEquals(expected,actual);
    }

    @Test(description = "Login as store manager and verify that title equals to Dashboard")
    public void loginAsStoreManager(){
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(2);
        String expected = "Dashboard";
        String actual = driver.getTitle();
        assertEquals(actual, expected, "Page title is not correct!");
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(){
        //if webdriver object alive
        if(driver != null){
            //close driver, close session
            driver.quit();
            //get rid of driver from heap, destroying webdriver object for good
            driver = null;
        }
    }


}
