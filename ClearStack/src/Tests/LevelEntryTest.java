package Tests;

import com.company.BasePage;
import com.company.LevelPage;
import com.company.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LevelEntryTest extends BasePage {
    private WebDriver driver;
    private LogInPage logInPage = new LogInPage();
    private LevelPage levelPage = new LevelPage();


    @Test
    public void LevelEntryUIValidationTest() {
        driver = Launch();
        logInPage.logIn(correctUsername, correctPassword, driver);
        Assert.assertTrue(levelPage.validateNavigationToLevel(driver));
        levelPage.validateLevelPageElements(driver);//As there is a assert all happening inside the method, the method will only go to the next step if assert all passes
        Assert.assertTrue(true);
        levelPage.clickAddNew(driver);
        levelPage.validateActualEntriesPageElements(driver);//As there is a assert all happening inside the method, the method will only go to the next step if assert all passes
        Assert.assertTrue(true);
    }

    @Test
    public void LevelEntryMoreThan4EntriesTest() {
        int i = 1;
        List<Integer> values = new ArrayList<>();
        driver = Launch();
        logInPage.logIn(correctUsername, correctPassword, driver);
        levelPage.validateNavigationToLevel(driver);
        levelPage.deleteAllRecords(driver);
        while (i <= 5) {
            levelPage.clickAddNew(driver);
            values.add(i);
            levelPage.enterLevelValue(i, driver);
            i++;
        }
        values.remove(4);
        Assert.assertTrue(levelPage.validateIfMaxEntryErrorIsShown(driver));
        levelPage.validateNavigationToLevel(driver);
        Assert.assertTrue(levelPage.validateEntries(driver,values));
        levelPage.clickAddNew(driver);
        Assert.assertTrue(levelPage.validateTheDateFilter(driver));
    }
}
