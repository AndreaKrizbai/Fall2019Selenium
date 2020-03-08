package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBoxes {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/checkboxes");
        BrowserUtils.wait(2);

        List<WebElement>checkBoxes = driver.findElements(By.tagName("input"));
//        checkBoxes.get(0).click();
//        BrowserUtils.wait(2);
//
//        if(checkBoxes.get(1).isDisplayed() && checkBoxes.get(1).isEnabled() && (!checkBoxes.get(1).isSelected())) {
//            checkBoxes.get(1).click();
//            System.out.println("Clicked on button");
//        }else{
//            System.out.println("Didn't have to click on 2nd checkbox");
//        }
//        BrowserUtils.wait(2);

        for (int i = 0; i < checkBoxes.size(); i++) {
            if(checkBoxes.get(i).isDisplayed() && checkBoxes.get(i).isEnabled() && (!checkBoxes.get(i).isSelected())) {
                checkBoxes.get(i).click();
                System.out.println("Clicked on checkbox " + (i + 1));
                BrowserUtils.wait(2);
            }else{
                System.out.println("Did not click on checkbox " + (i+1));
                BrowserUtils.wait(2);
            }
        }
        //for (WebElement eachCheckBox:checkBoxes
        //     ) {
        //    if (!eachCheckBox.isSelected()){
        //  eachCheckBox.click();
        //    }else {
        //        System.out.println("already selected");
        //    }
        //    BrowserUnits.wait(2);
        //}

        driver.quit();
    }
}
