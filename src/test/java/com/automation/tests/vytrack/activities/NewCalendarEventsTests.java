package com.automation.tests.vytrack.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewCalendarEventsTests extends AbstractTestBase {
    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

@Test
public void defaultOptionsTest() {
    test = report.createTest("Verify default login options");
    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
    loginPage.login();
    calendarEventsPage.navigateTo("Activities", "Calendar Events");
    BrowserUtils.wait(1);
    calendarEventsPage.clickToCreateCalendarEvent();
    Assert.assertEquals(calendarEventsPage.getOwnerName(), calendarEventsPage.getCurrentUserName());
    String actualStartDate = calendarEventsPage.getStartDate();
    String expectedStartDate = DateTimeUtilities.getCurrentDate("MMM d, yyyy");
    Assert.assertEquals(actualStartDate, expectedStartDate);
    test.pass("Default options verified");
}

    @Test
    public void timeDifference(){
        test = report.createTest("Verify time difference");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        String startTime = calendarEventsPage.getStartTime();
        String endTime = calendarEventsPage.getEndTime();
        String format = "h:m a"; //format 5:15 AM for example

        long actual = DateTimeUtilities.getTimeDifference(startTime,endTime,format);

        Assert.assertEquals(actual,1,"Time difference is not correct");
        test.pass("Time difference verified");

    }

    @Test
    public void verifyColumnNamesTest(){
        test = report.createTest("Verify column names");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END", "RECURRENT", "RECURRENCE", "INVITATION STATUS");
        Assert.assertEquals(calendarEventsPage.getColumnNames(), expected);
        test.pass("Column names verified");

    }

    @Test(dataProvider = "calendarEvents")
    public void createCalendarEventTest(String title, String description) {
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        test = report.createTest("Create calendar event for " + title);
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.enterCalendarEventTitle(title);
        calendarEventsPage.enterCalendarEventDescription(description);
        calendarEventsPage.clickOnSaveAndClose();
        //verify that calendar event info is correct
        Assert.assertEquals(calendarEventsPage.getGeneralInfoDescriptionText(), description);
        Assert.assertEquals(calendarEventsPage.getGeneralInfoTitleText(), title);
        test.pass("Calendar event was created successfully!");
    }

    @DataProvider
    public Object[][] calendarEvents(){
        return new Object[][]{
                {"Daily Stand-up", "Scrum meeting to provide updates"},
                {"Daily Review", "Scrum meeting where team discusses previous sprint"},
                {"Daily Planning", "Scrum meeting where team discusses upcoming sprint backlog"}
        };
    }
}
