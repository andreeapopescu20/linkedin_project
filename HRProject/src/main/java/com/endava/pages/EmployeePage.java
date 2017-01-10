package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by andpopescu on 1/6/2017.
 */
public class EmployeePage {
    private WebDriver driver;

    public EmployeePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='member-connections']/strong/a")
    private WebElement connectionButton;

    @FindBy(xpath = "//a[@class='connections-name']")
    private List<WebElement> candidates;

    public CandidatePage checkCandidate(){
        connectionButton.click();
        candidates.get(0).click();
        CandidatePage candidatePage = PageFactory.initElements(driver, CandidatePage.class);
        return candidatePage;
    }
}
