package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PracticeTests {
    WebDriver driver;

    @Test
    public void loginTest(){
        driver.findElement(By.linkText("Form Authentication")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
     //   driver.findElement(By.name("password")).sendKeys("SuperSecretPassword", Keys.ENTER);
        BrowserUtils.wait(2);
        driver.findElement(By.id("wooden_spoon")).click();
        BrowserUtils.wait(2);
        String header = driver.findElement(By.tagName("h4")).getText();
     //   String subheader = driver.findElement(By.className("subheader")).getText();
        System.out.println(header);
        Assert.assertEquals(header, "Welcome to the Secure Area. When you are done click logout below.", "Sub-header message is not matching!");
        driver.findElement(By.linkText("Logout")).click();
        BrowserUtils.wait(2);
    }

    @Test
    public void forgotPasswordTest(){
        driver.findElement(By.linkText("Forgot Password")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.name("email")).sendKeys("blabla@gmail.com");
        BrowserUtils.wait(2);
        driver.findElement(By.id("form_submit")).click();
        BrowserUtils.wait(2);
        String message = driver.findElement(By.tagName("h4")).getText();
        String expected = "Your e-mail's been sent!";
        Assert.assertEquals(message,expected);
    }

    @Test
    public void checkboxTest1(){
        driver.findElement(By.linkText("Checkboxes")).click();
        BrowserUtils.wait(2);
       // driver.findElement(By.tagName("input")).click();
        //locator for specific checkbox, xpath: //input[0], cssSelector: input:nth-of-type(1)
        // //input[@type="checkbox"][1]
        List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
        checkboxes.get(0).click();
       // driver.findElement(By.xpath("//input[1]")).click();
        BrowserUtils.wait(2);
        Assert.assertTrue(driver.findElement(By.tagName("input")).isSelected());
      //  Assert.assertTrue(checkboxes.get(0).isSelected(),"Checkbox #1 is not selected");
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        //INTERVIEW QUESTION: HOW TO HANDLE SSL ISSUES IN SELENIUM?
        //ChromeOptions - use to customize browser for tests
        ChromeOptions chromeOptions = new ChromeOptions();
        //to ignore "Your connection is not private issue"
        chromeOptions.setAcceptInsecureCerts(true);
        //provide chromeOptions object into chromedriver constructor
        driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
