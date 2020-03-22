package com.automation.tests.day12;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExplicitWait {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
    }

    @Test
    public void waitForTitle(){
       driver.get("http://google.com");
       WebDriverWait wait = new WebDriverWait(driver,10);
       wait.until(ExpectedConditions.titleContains("Google"));

       driver.navigate().to("https://amazon.com");
       wait.until(ExpectedConditions.titleContains("Amazon"));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
