package Tests;

import com.company.BasePage;
import com.company.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTest extends BasePage {

    private WebDriver driver;
    private LogInPage logInPage = new LogInPage();

    private String wrongUser = "sdfsdf@sad.com";
    private String wrongPass = "sdfsdf";

    @Test
    public void LogInTests()
    {
        driver = Launch();

        //Validation of all elements
        logInPage.validationOfLogInPage(driver);//As there is a assert all happening inside the method, the method will only go to the next step if assert all passes
        Assert.assertTrue(true);
        //WrongUser and Wrong pass validation
        logInPage.logIn(wrongUser,wrongPass,driver);
        Assert.assertTrue(logInPage.verifyLogInFailErrorMessage(driver));

        //WrongUser and CorrectPass
        logInPage.logIn(wrongUser,correctPassword,driver);
        Assert.assertTrue(logInPage.verifyLogInFailErrorMessage(driver));

        //CorrectUser and wrongPass
        logInPage.logIn(correctUsername,wrongPass,driver);
        Assert.assertTrue(logInPage.verifyLogInFailErrorMessage(driver));

        //CorrectUser and CorrectPass
        logInPage.logIn(correctUsername,correctPassword,driver);
        Assert.assertTrue(logInPage.logInSuccessValidation(correctUsername,driver));
    }
}
