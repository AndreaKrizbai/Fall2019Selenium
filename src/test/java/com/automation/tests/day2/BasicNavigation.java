package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasicNavigation {
    public static void main(String[] args) throws InterruptedException {
//to start selenium script we need setup webdriver (browser driver) and create webdriver object

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); //polymorphism

        driver.get("http://google.com"); // to open site
        Thread.sleep(3000); // for demo, wait 3 seconds
        String title = driver.getTitle(); //returns <title>something</title> text
        String expectedTitle = "Google";
        System.out.println("Title is " + title);

        if(expectedTitle.equals(title)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }

        driver.close(); // to close browser






    }
}
