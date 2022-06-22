package Tests;

import com.company.BasePage;
import com.company.LevelPage;
import com.company.LogInPage;
import com.company.ReportsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ReportTests extends BasePage {
    private WebDriver driver;
    private LogInPage logInPage = new LogInPage();
    private LevelPage levelPage = new LevelPage();
    private ReportsPage reportsPage = new ReportsPage();



    @Test
    public void reportsUIValidation() {
        driver = Launch();
        int i =1;
        List<Integer> values = new ArrayList<>();
        logInPage.logIn(correctUsername, correctPassword, driver);
        levelPage.validateNavigationToLevel(driver);
        levelPage.deleteAllRecords(driver);
        while (i <= 4) {
            levelPage.clickAddNew(driver);
            values.add(i);
            levelPage.enterLevelValue(i, driver);
            i++;
        }
        Assert.assertTrue(reportsPage.navigateToReportsSection(driver));
        reportsPage.validateReportsPAgeElements(driver);//As there is a assert all happening inside the method, the method will only go to the next step if assert all passes
        Assert.assertTrue(true);
        reportsPage.validateReport(driver,values);
        Assert.assertTrue(true);
    }
}

