package com.endava.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andpopescu on 1/6/2017.
 */
public class EmployeePage{
    private WebDriver driver;
    private Candidate candidate;

    public EmployeePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='member-connections']/strong/a")
    private WebElement connectionButton;

    @FindBy(xpath = "//div[@id='connections-view']//a[@class='shared']")
    private WebElement sharedButton;

    @FindBy(xpath = "//a[@class='connections-name']")
    private List<WebElement> candidates;

    @FindBy(xpath = "//div[@id='name']//span[@class='full-name']")
    private WebElement candidateName;

    @FindBy(xpath = "//li[contains(@class,'endorse-item')]//a[@class='endorse-item-name-text']")
    private List<WebElement> candidateSkills;

    @FindBy(xpath = "//span[@class='more-text']")
    private WebElement showMoreButton;

    public Candidate checkSkills() throws IOException {
        ReadData objExcelFile = new ReadData();
        List<String> keyWords = (List<String>) objExcelFile.readExcel("test.xls", "Sheet2");
        candidate = new Candidate();

        List<String> machingSkills = new ArrayList<>();

        boolean present;

        try {
            showMoreButton.isDisplayed();
            present = true;
        } catch (NoSuchElementException e){
            present = false;
        }

        if(present==true)
        {
            showMoreButton.click();
        }
        for (WebElement skill : candidateSkills) {

            if (keyWords.contains(skill.getText())) {
                machingSkills.add(skill.getText());
            }
        }
        candidate.setName(candidateName.getText());
        candidate.setContactPerson("contact");
        candidate.setSkills(machingSkills);
        return candidate;
    }


    public List<Candidate> checkCandidate() throws IOException, InterruptedException {

        List<Candidate> candidatesList = new ArrayList<>();

        JavascriptExecutor js = ((JavascriptExecutor) driver);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        sharedButton.click();
        for(WebElement candidate : candidates)
        {

            String winHandleBefore = driver.getWindowHandle();

            Actions newwin = new Actions(driver);
            newwin.keyDown(Keys.SHIFT).click(candidate).keyUp(Keys.SHIFT).build().perform();

            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }

            System.out.println(driver.getCurrentUrl());

            // Perform the actions on new window

            Candidate candidateDescription = checkSkills();
            candidatesList.add(candidateDescription);
            driver.close();

            // Switch back to original browser (first window)
            driver.switchTo().window(winHandleBefore);

            System.out.println(driver.getCurrentUrl());

            //CandidatePage candidatePage = PageFactory.initElements(driver, CandidatePage.class);
            //Candidate candidateDescription = candidatePage.checkSkills();
            //driver.navigate().back();
        }
        return candidatesList;
    }
}
