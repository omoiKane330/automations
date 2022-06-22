package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReportsPage extends BasePage{
    private By reportsNavigation = By.xpath("//a[text()='Reports']");
    private By reportPageHeading = By.xpath("//*[text()='Level Reports']");
    private By DailyLink = By.xpath("//a[text()='Daily']");
    private By monthlyLink = By.xpath("//a[text()='Monthly']");
    private By recentEntryData = By.xpath("//tr/td[3]");
    private By minEntry = By.xpath("//tr/td[4]");
    private By maxEntry = By.xpath("//tr/td[5]");
    private By avgEntry = By.xpath("//tr/td[6]");
    private By reportDetailsGrid = By.xpath("//div[@class='panel panel-default']");


    public void validateReportsPAgeElements(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        SoftAssert softAssert = new SoftAssert();
        List<By> locators = new ArrayList<>();
        locators.add(reportsNavigation);
        locators.add(reportPageHeading);
        locators.add(DailyLink);
        locators.add(monthlyLink);
        locators.add(recentEntryData);
        locators.add(reportDetailsGrid);
        locators.stream().forEach(x->
                softAssert.assertTrue(driver.findElement(x).isDisplayed()));
        softAssert.assertAll();
    }

    public boolean navigateToReportsSection(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(reportsNavigation).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver.findElement(reportPageHeading).isDisplayed();
    }

    public void validateReport(WebDriver driver,List<Integer> values) {
        int sum=0;
        SoftAssert softAssert = new SoftAssert();
        int inTableValues = driver.findElements(recentEntryData).size();
        List<Integer> reportValues = new ArrayList<>();
        for (int i = 0; i < inTableValues; i++) {
            reportValues.add(Integer.parseInt(driver.findElements(recentEntryData).get(i).getText()));
        }
        softAssert.assertTrue(reportValues.containsAll(values),"All values are not listed");
        Collections.sort(values);
        softAssert.assertTrue(driver.findElement(minEntry).getText().equals(Integer.toString(values.get(0))));
        softAssert.assertTrue(driver.findElement(maxEntry).getText().equals(Integer.toString(values.get(values.size()-1))));
        for(int i:values)
        {
            sum+=i;
        }
        softAssert.assertTrue(driver.findElement(avgEntry).getText().equals(Integer.toString(sum/4)));
        softAssert.assertAll();
    }



}
