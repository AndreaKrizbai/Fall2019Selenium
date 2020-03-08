package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestForiFrame {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/iframe");
        BrowserUtils.wait(4);

        driver.switchTo().frame("mce_0_ifr");
        WebElement textInput = driver.findElement(By.id("tinymce"));
        textInput.clear();
        textInput.sendKeys("Hello, World!");
        BrowserUtils.wait(4);
        System.out.println(textInput.getText());

        driver.switchTo().defaultContent();
        WebElement heading = driver.findElement(By.tagName("h3"));
        System.out.println(heading.getText());


        driver.quit();
    }
}
