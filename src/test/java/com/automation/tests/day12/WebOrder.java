package com.automation.tests.day12;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebOrder {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        wait = new WebDriverWait(driver, 10);
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test", Keys.ENTER);
      //  driver.findElement(By.id("ctl00_MainContent_login_button")).click();
    }

    @Test
    public void checkBoxTest() {
        driver.findElement(By.id("ctl00_MainContent_btnCheckAll")).click();
        BrowserUtils.wait(3);
       // List<WebElement> checkboxes = driver.findElements(By.xpath("//table//tbody//td[1]"));
        List<WebElement> checkboxes = driver.findElements(By.id("ctl00_MainContent_orderGrid_ctl02_OrderSelector"));
        for (WebElement checkBox : checkboxes){
            Assert.assertTrue(checkBox.isSelected());
        }
    }

    @Test
    public void updateZipCode(){
        WebElement zipCode = driver.findElement(By.xpath("//table//tbody//tr[4]//td[9]"));
       // WebElement zipcode2 = driver.findElement(By.xpath("//td[text()='Steve Johns']//following-sibling::td[7]"));
        Assert.assertEquals(zipCode.getText(), "21233");

        driver.findElement(By.xpath("//table//tbody//tr[4]//td[13]")).click();
        // driver.findElement(By.xpath(" //td[text()='Steve Johns']//following-sibling::td/input")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).clear();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys("20002");
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
        BrowserUtils.wait(2);
        zipCode = driver.findElement(By.xpath("//table//tbody//tr[4]//td[9]"));
       // zipcode2 = driver.findElement(By.xpath("//td[text()='Steve Johns']//following-sibling::td[7]"));
        Assert.assertEquals(zipCode.getText(), "20002");
    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(3);
        driver.quit();
    }
}
