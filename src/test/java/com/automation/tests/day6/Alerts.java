package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Alerts {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        BrowserUtils.wait(3);

        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        buttons.get(0).click();
        BrowserUtils.wait(3);

        String popupText = driver.switchTo().alert().getText();
        System.out.println(popupText);
        driver.switchTo().alert().accept();

        String expected = "You successfully clicked an alert";
        String actual = driver.findElement(By.id("result")).getText();
        //if will fail, because there is a typo ##BUG##
        if(expected.equals(actual)){
            System.out.println("TEST PASSED");
        }else {
            System.out.println("TEST FAILED");
            System.out.println("Expected: "+expected);
            System.out.println("Actual:   "+actual);
        }
        BrowserUtils.wait(3);

        buttons.get(1).click();
        BrowserUtils.wait(3);
        driver.switchTo().alert().dismiss(); //to click cancel
        String expected2 = "You clicked: Cancel";
        String actual2 = driver.findElement(By.id("result")).getText();
        if(expected2.equals(actual2)){
            System.out.println("TEST PASSED");
        }else {
            System.out.println("TEST FAILED");
            System.out.println("Expected: "+expected2);
            System.out.println("Actual:   "+actual2);
        }

        buttons.get(2).click();
        BrowserUtils.wait(3);
        String input = "Hello, World";
        driver.switchTo().alert().sendKeys(input);
        BrowserUtils.wait(10);
        driver.switchTo().alert().accept();
        BrowserUtils.wait(3);

        String expected3 = "You entered: " + input;
        String actual3 = driver.findElement(By.id("result")).getText();
        if(expected3.endsWith(actual3)){
            System.out.println("TEST PASSED");
        }else {
            System.out.println("TEST FAILED");
            System.out.println("Expected: "+expected3);
            System.out.println("Actual:   "+actual3);
        }





        driver.quit();
    }
}





