package com.automation.tests.QAhomework;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateCarAutomation {
    private WebDriver driver;
    private Actions actions;
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private String storeManagerUserName="storemanager65";
    private String storeManagerPassword="UserUser123";
    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        actions = new Actions(driver);

        BrowserUtils.wait(3);
        driver.findElement(usernameBy).sendKeys(storeManagerUserName);
        driver.findElement(passwordBy).sendKeys(storeManagerPassword, Keys.ENTER);
        BrowserUtils.wait(3);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtils.wait(2);
        driver.findElement(By.linkText("Vehicles")).click();
        BrowserUtils.wait(3);
    }

    @Test
    public void createCar(){
        driver.findElement(By.linkText("Create Car")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("[id^='custom_entity_type_LicensePlate']")).sendKeys("AutomatedAAA");
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();
      //  Assert.assertTrue(driver.findElement(By.xpath("//button[@class='close']")).isDisplayed());
    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}
