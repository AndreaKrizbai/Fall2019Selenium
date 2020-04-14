package com.automation.tests.vytrack.login;

import com.automation.pages.LoginPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import com.automation.utilities.ExcelUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewLoginTests extends AbstractTestBase {

    @Test
    public void verifyPageTitle(){
        //we have to add this line at the beginning of every test
        test = report.createTest("Verify page title");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        //wait.until(ExpectedConditions.titleContains("Dashboard"));
        //Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        BrowserUtils.wait(3);
        Assert.assertEquals(Driver.getDriver().getTitle(),"Dashboard");
        //if assertion passed, it will set test status in report to passed
        test.pass("Page title Dashboard was verified");
    }

    @Test
    public void verifyWarningMessage(){
        test = report.createTest("Verify warning message");

        LoginPage loginPage = new LoginPage();
        loginPage.login("wrong", "wrong");
        test.info("login as ");
        BrowserUtils.wait(2);
        Assert.assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");
        //take a screenshot
        BrowserUtils.getScreenshot("warning_message");

        test.pass("Warning message was verified");
    }

    @Test(dataProvider = "credentials")
    public void loginWithDDT(String userName, String password) {
        test = report.createTest("Verify page title");
        LoginPage loginPage = new LoginPage();
        loginPage.login(userName, password);
        test.info("Login as " + userName);//log some steps
        BrowserUtils.wait(2);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        test.pass("Page title Dashboard was verified");
    }
    @Test(dataProvider = "credentialsFromExcel")
    public void loginTestWithExcel(String execute, String username, String password, String firstname, String lastname, String result) {
        test = report.createTest("Login test for username :: " + username);
        if (execute.equals("y")) {
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            test.info("Login as " + username);//log some steps
            test.info(String.format("First name: %s, Last name: %s, Username: %s", firstname, lastname, username));
            test.pass("Successfully logged in as " + username);
        } else {
            test.skip("Test was skipped for user: " + username);
            //to skip some test in testng
            throw new SkipException("Test was skipped for user: " + username);
        }
    }

    @DataProvider
    public Object[][] credentialsFromExcel() {
        String path = "VytrackTestUsers.xlsx";
        String spreadSheet = "QA3-short";
        ExcelUtil excelUtil = new ExcelUtil(path, spreadSheet);
        //execute	username	password	firstname	lastname	result
        return excelUtil.getDataArray();
    }

    //it can return Object[][] or Object[] or Iterator<Object[]>
    //Object[] - 1 column with a data
    //Object[][] 2+
    @DataProvider
    public Object[][] credentials(){
        return new Object[][]{
                {"storemanager85",  "UserUser123"},
                {"salesmanager110", "UserUser123"},
                {"user16",          "UserUser123"},
        };
    }

}
