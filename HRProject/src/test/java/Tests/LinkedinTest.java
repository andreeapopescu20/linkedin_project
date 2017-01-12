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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by andpopescu on 1/5/2017.
 */
public class LinkedinTest {
    private WebDriver driver;
    private LogInPage logInPage;
    private WriteData writeData;

    @Before
    public void before(){
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andpopescu\\Documents\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logInPage = PageFactory.initElements(driver, LogInPage.class);
    }

    @Test
    public void test() throws IOException, InterruptedException {

        List<Candidate> candidates = new ArrayList<>();
        HomePage homePage = logInPage.accountLogIn();
        List<String> contacts = homePage.getContactsList();
        for (String contact : contacts) {
            ResultsPage resultsPage = homePage.searchContact(contact);
            EmployeePage employeePage = resultsPage.navigateToEmployeePage();
            CandidatePage candidatePage = employeePage.checkCandidate();
            Candidate candidate = candidatePage.checkSkills(contact);
            candidates.add(candidate);
            //System.out.println(candidate.getName() +": " + candidate.getContactPerson() + ", Skilluri: "+ candidate.getSkills());
        }

        writeData = new WriteData();
        Map<Integer, Object[]> data = new HashMap<>();
        data.put(1, new Object[] {"Candidate Name", "Contact Person", "Skills"});
        for (Candidate candidateInfo : candidates) {
                Object[] obj = new Object[]{candidateInfo.getName(), candidateInfo.getContactPerson(), candidateInfo.getSkills().toString()};
                data.put(candidates.indexOf(candidateInfo)+2, obj);
        }
        writeData.writeExcel(data);
    }
}

