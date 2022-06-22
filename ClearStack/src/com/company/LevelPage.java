package com.company;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LevelPage extends BasePage{

    private By levelNavigationButton = By.xpath("//a[text()='Levels']");
    private By levelPageHeading = By.xpath("//*[text()='Level Entries']");
    private By maxLevelEntryError = By.xpath("//*[text()='Maximum entries reached for this date.']");
    private By levelEntryField = By.id("entry_level");
    private By monthsComboBox = By.id("entry_recorded_at_2i");
    private By daysComboBox = By.id("entry_recorded_at_3i");
    private By addNewButton = By.xpath("//a[text()='Add new']");
    private By submitButton = By.xpath("//input[@value='Submit']");
    private By cancelButton = By.xpath("//a[text()='cancel']");
    private By deleteButton = By.xpath("//a[text()='Delete']");
    private By recentEntryData = By.xpath("//tr/td");
    private By recentEntriesGrid = By.xpath("//*[text()='Recent Entries']/ancestor::div[@class='panel panel-default']");
    private By entriesWarningMessage = By.xpath("//div[@class='alert alert-info']");


    public void validateLevelPageElements(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        SoftAssert softAssert = new SoftAssert();
        List<By> locators = new ArrayList<>();
        locators.add(levelPageHeading);
        locators.add(addNewButton);
        locators.add(recentEntriesGrid);
        locators.stream().forEach(x->
                softAssert.assertTrue(driver.findElement(x).isDisplayed()));
        softAssert.assertAll();
    }

    public void clickAddNew(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(addNewButton).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public boolean validateNavigationToLevel(WebDriver driver)
    {
        driver.findElement(levelNavigationButton).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver.findElement(levelPageHeading).isDisplayed();
    }

    public void validateActualEntriesPageElements(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        SoftAssert softAssert = new SoftAssert();
        List<By> locators = new ArrayList<>();
        locators.add(levelEntryField);
        locators.add(entriesWarningMessage);
        locators.add(submitButton);
        locators.add(cancelButton);
        locators.stream().forEach(x->
                softAssert.assertTrue(driver.findElement(x).isDisplayed()));
        softAssert.assertAll();
    }

    public void enterLevelValue(int value,WebDriver driver)
    {
        sendKeys(driver,value,levelEntryField);
        driver.findElement(submitButton).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void clickCancel(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(cancelButton).click();
    }

    public boolean validateIfMaxEntryErrorIsShown(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver.findElement(maxLevelEntryError).isDisplayed();
    }

    public void deleteAllRecords(WebDriver driver)
    {
        Alert simpleAlert;
        int numOfDeletesReq = driver.findElements(deleteButton).size();
        if(!(numOfDeletesReq == 0)){
            for(int i =0; i<numOfDeletesReq;i++) {
                driver.findElements(deleteButton).get(0).click();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                simpleAlert = driver.switchTo().alert();
                simpleAlert.accept();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }
    }

    public boolean validateEntries(WebDriver driver, List<Integer> values)
    {
        int inTableValues = driver.findElements(recentEntryData).size();
        String entry;
        List<Integer> tableEntries = new ArrayList<>();
        for(int i =0; i<inTableValues;i++) {
            entry = driver.findElements(recentEntryData).get(i).getText();
            if(!entry.equals("Delete")) {
                tableEntries.add(Integer.parseInt(entry.split("")[0]));
            }
        }
        return tableEntries.containsAll(values);
    }

    public  List<Integer> getAllEntries(WebDriver driver)
    {
        int inTableValues = driver.findElements(recentEntryData).size();
        String entry;
        List<Integer> tableEntries = new ArrayList<>();
        for(int i =0; i<inTableValues;i++) {
            entry = driver.findElements(recentEntryData).get(i).getText();
            if(!entry.equals("Delete")) {
                tableEntries.add(Integer.parseInt(entry.split("")[0]));
            }
        }
        return tableEntries;
    }

    public boolean validateTheDateFilter(WebDriver driver)
    {
        List<String> monthsAndDays = new ArrayList<>();
        List<String> comboBoxOptions = new ArrayList<>();
        monthsAndDays.add("January");
        monthsAndDays.add("February");
        monthsAndDays.add("March");
        monthsAndDays.add("April");
        monthsAndDays.add("May");
        monthsAndDays.add("June");
        monthsAndDays.add("July");
        monthsAndDays.add("August");
        monthsAndDays.add("September");
        monthsAndDays.add("October");
        monthsAndDays.add("November");
        monthsAndDays.add("December");
        for(int i=1;i<=31;i++)
        {
            monthsAndDays.add(Integer.toString(i));
        }
        Select select = new Select(driver.findElement(monthsComboBox));
        for(var i:select.getOptions())
        {
            if(!i.getText().isEmpty())
            {
                comboBoxOptions.add(i.getText());
            }
        }
        select = new Select(driver.findElement(daysComboBox));
        for(var i:select.getOptions())
        {
            if(!i.getText().isEmpty())
            {
                comboBoxOptions.add(i.getText());
            }
        }
        return monthsAndDays.containsAll(comboBoxOptions);
    }
}
