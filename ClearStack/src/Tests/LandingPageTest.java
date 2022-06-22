package Tests;

import com.company.BasePage;
import com.company.LogInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTest extends BasePage {

    private LogInPage logInPage = new LogInPage();



    @Test
    public void LandingPageValidationTest()
    {
        driver = Launch();
        //CorrectUser and CorrectPass
        logInPage.logIn(correctUsername,correctPassword,driver);
        Assert.assertTrue(logInPage.logInSuccessValidation(correctUsername,driver));
        logInPage.validationOfLandingPage(driver);//As there is a assert all happening inside the method, the method will only go to the next step if assert all passes
        Assert.assertTrue(true);
    }
}
