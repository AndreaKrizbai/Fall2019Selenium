package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class JumpToNewWindow {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/open_new_tab");
        Thread.sleep(5000);

        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);

        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(windowHandles);
        System.out.println("Before switch: "+ driver.getCurrentUrl());

        for (String windowId : windowHandles) {
            if(!windowId.equals(windowHandle)){
                driver.switchTo().window(windowId);
            }
        }

        System.out.println("After switch: " + driver.getCurrentUrl());
        driver.close();
    }

    public static void switchToWindowBasedOnTitle (String pageTitle, WebDriver driver){
        Set<String>windows = driver.getWindowHandles();
        for(String window : windows){
            driver.switchTo().window(window);
            if(driver.getTitle().equals(pageTitle)){
                break;
            }
        }
    }


}
