package com.company;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import java.util.logging.Logger;

public class BasePage {

    public Logger logger;
    public WebDriver driver;
    public WebDriverWait wait;
    public Alert alert;
    public String correctUsername = "sivakarthik330@gmail.com";
    public String correctPassword = "codetheory";


    public WebDriver Launch()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\S.Karthikeyan\\Downloads\\chromedriver.exe");
        String site = "http://damp-sands-8561.herokuapp.com/";
        String urlToBe = "https://damp-sands-8561.herokuapp.com/users/sign_in";
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(site);
        return driver;
    }

    public void sendKeys(WebDriver driver, int value, By element)
    {
        String s = Integer.toString(value);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(s);
    }

    @AfterMethod
    public void closeWindow()
    {
        driver.quit();
    }
}
