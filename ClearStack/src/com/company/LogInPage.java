package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class LogInPage extends BasePage{

    private By userNameField = By.id("user_email");
    private By userNameText = By.xpath("//label[text()='Email']");
    private By passwordField = By.id("user_password");
    private By passwordText = By.xpath("//label[text()='Password']");
    private By logInButton = By.xpath("//input[@value='Log in']");
    private By logInFailErrorMssg = By.xpath("//div[text()='Invalid email or password.']");
    private By logInPassMssg = By.xpath("//div[text()='Signed in successfully.']");
    private String welcomeMssg = "Welcome to Sweety";

    private By recentEntriesGrid = By.xpath("//*[text()='Recent Entries']/ancestor::div[@class='panel panel-default']");
    private By reportsGrid = By.xpath("//*[text()='Reports']/ancestor::div[@class='panel panel-default']");



    public void logIn(String username, String password,WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(userNameField).clear();
        driver.findElement(passwordField).clear();
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(logInButton).click();
    }

    public boolean logInSuccessValidation(String username,WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver.findElement(logInPassMssg).isDisplayed();
    }

    public boolean verifyLogInFailErrorMessage(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver.findElement(logInFailErrorMssg).isDisplayed();
    }


    public void validationOfLogInPage(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        SoftAssert softAssert = new SoftAssert();
        List<By> locators = new ArrayList<>();
        locators.add(userNameField);
        locators.add(userNameText);
        locators.add(passwordField);
        locators.add(passwordText);
        locators.stream().forEach(x->
                softAssert.assertTrue(driver.findElement(x).isDisplayed()));
        softAssert.assertAll();
    }

    public void validationOfLandingPage(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        SoftAssert softAssert = new SoftAssert();
        List<By> locators = new ArrayList<>();
        locators.add(reportsGrid);
        locators.add(recentEntriesGrid);
        softAssert.assertTrue(driver.findElement(By.tagName("h2")).getText().equals(welcomeMssg));
        locators.stream().forEach(x->
                softAssert.assertTrue(driver.findElement(x).isDisplayed()));
        softAssert.assertAll();
    }



}
