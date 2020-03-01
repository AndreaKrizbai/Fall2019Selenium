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
        driver.manage().window().maximize();
      //  driver.manage().window().fullscreen();
        Thread.sleep(3000); // for demo, wait 3 seconds

        String title = driver.getTitle(); //returns <title>something</title> text
        String expectedTitle = "Google";
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());

        if(expectedTitle.equals(title)){
            System.out.println("GOOGLE TEST PASSED");
        }else{
            System.out.println("GOOGLE TEST FAILED");
        }

        driver.navigate().to("http://amazon.com");
        Thread.sleep(3000);

        if(driver.getTitle().toLowerCase().contains("amazon")){
            System.out.println("AMAZON TEST PASSED");
        }else{
            System.out.println("AMAZON TEST FAILED");
        }

        driver.navigate().back();
        Thread.sleep(3000);
        verifyEquals(driver.getTitle(),expectedTitle);
        driver.navigate().forward();
        Thread.sleep(3000);
        System.out.println("Title: " + driver.getTitle());

        driver.navigate().refresh(); //to reload
        Thread.sleep(3000);

        driver.close(); // to close browser
    }

    public static void verifyEquals(String arg1, String arg2){
        if(arg1.equals(arg2)){
            System.out.println("Method TEST PASSED");
        }else{
            System.out.println("Method TEST FAILED");
        }

    }




}
