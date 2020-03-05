package com.automation.tests.day4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EBayTaskWarmUp {
    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://ebay.com");
        WebElement search = driver.findElement(By.id("gh-ac"));
        search.sendKeys("java book");
        Thread.sleep(2000);

        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();

        String result = driver.findElement(By.tagName("h1")).getText();
        Thread.sleep(2000);
        System.out.println("result = " + result);

        driver.quit();




    }
}
