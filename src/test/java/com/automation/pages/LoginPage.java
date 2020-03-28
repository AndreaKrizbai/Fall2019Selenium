package com.automation.pages;

import com.automation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy (id="prependedInput")
    public WebElement username;

    @FindBy (id = "prependedInput2")
    public WebElement password;

    @FindBy (id = "_submit")
    public WebElement login;

    @FindBy(linkText = "Forgot your password?")
    public WebElement forgotPassword;

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this); //LoginPage.class);
    }

}
