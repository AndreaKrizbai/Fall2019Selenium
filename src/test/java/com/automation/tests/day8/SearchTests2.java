package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests2 {

    private WebDriver driver;

//    @Test
//    public void googleSearchTest(){
//        driver.get("http://google.com");
//        driver.findElement(By.name("q")).sendKeys("java", Keys.ENTER);
//        BrowserUtils.wait(2);
//        //since every search item has a tag name<h3>, this is the easiest way to collect all of them
//        List<WebElement> searchItems = driver.findElements(By.tagName("h3"));
//        for(WebElement each : searchItems){
//            String var = each.getText();
//            if(!var.isEmpty()) {
//                System.out.println(var);
//               Assert.assertTrue(var.toLowerCase().contains("java"));
//                System.out.println(var.toLowerCase());
//                System.out.println();
//            }
//        }
//    }

    @Test(description = "Search for Java book on Amazon")
    public void amazonSearchTest(){
       driver.get("http://amazon.com");
        //there is a chance that item is not visible
        //so we need to maximize window before clicking on it
        driver.manage().window().maximize();
       BrowserUtils.wait(2);
       driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java", Keys.ENTER);
       BrowserUtils.wait(2);
       //find all links inside h2 ellements because h2 element is not clickable itself
       List<WebElement>searchItems = driver.findElements(By.xpath("//h2//a"));
       for(WebElement searchItem : searchItems){
           System.out.println("Title: " + searchItem.getText());
       }

       searchItems.get(0).click();
       BrowserUtils.wait(2);

       WebElement productTitle = driver.findElement(By.id("title"));
      // String productTitleString = productTitle.getText();
        System.out.println("First item: " + productTitle.getText());

        Assert.assertTrue(productTitle.getText().contains("Java"));


    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
