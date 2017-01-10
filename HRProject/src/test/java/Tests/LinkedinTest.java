package Tests;

import com.endava.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by andpopescu on 1/5/2017.
 */
public class LinkedinTest {
    private WebDriver driver;
    private LogInPage logInPage;

    @Before
    public void before(){
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andpopescu\\Documents\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        logInPage = PageFactory.initElements(driver, LogInPage.class);
    }

    @Test
    public void test() throws IOException {
        HomePage homePage = logInPage.accountLogIn();
        List<String> contacts = homePage.getContactsList();
        for (String contact : contacts){
            ResultsPage resultsPage = homePage.searchContact(contact);
            EmployeePage employeePage = resultsPage.navigateToEmployeePage();
            CandidatePage candidatePage = employeePage.checkCandidate();
            Candidate candidate = candidatePage.checkSkills(contact);
            System.out.println(candidate.getName() +": " + candidate.getContactPerson() + ", Skilluri: "+ candidate.getSkills());
        }
    }
}