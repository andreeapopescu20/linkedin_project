package com.endava.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andpopescu on 1/6/2017.
 */
public class CandidatePage {
    private WebDriver driver;
    private Candidate candidate;

    public CandidatePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='name']//span[@class='full-name']")
    private WebElement candidateName;

    @FindBy(xpath = "//li[contains(@class,'endorse-item')]//a[@class='endorse-item-name-text']")
    private List<WebElement> candidateSkills;

    @FindBy(xpath = "//span[@class='more-text']")
    private WebElement showMoreButton;

    public Candidate checkSkills(String contact) throws IOException {
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
        candidate.setContactPerson(contact);
        candidate.setSkills(machingSkills);
        return candidate;

    }
}

