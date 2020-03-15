package com.automation.tests.vytrack.fleet;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.input.BrokenInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VehiclesPageTests {

    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";
    private String username = "storeManager85";
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");
    private By subtitleBy = By.className("oro-subtitle");

    @Test
    public void verifyPageSubTitle(){
        //click on fleet
       // driver.findElement(fleetBy).click();

        //move to element instead of click
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Vehicles")).click();
        BrowserUtils.wait(5);
       // WebElement subTitleElement = driver.findElement(subtitleBy);
        System.out.println(driver.findElement(subtitleBy).getText());
        String expected = "All Cars";
        String actual = driver.findElement(subtitleBy).getText();
        Assert.assertEquals(expected,actual);
    }




    @BeforeMethod
    public void setup(){
    WebDriverManager.chromedriver().version("79").setup();
    driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(2);
    }

    @AfterMethod
    public void teardown(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

}
