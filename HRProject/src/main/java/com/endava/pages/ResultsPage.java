package com.endava.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by andpopescu on 1/6/2017.
 */
public class ResultsPage {
    private WebDriver driver;

    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//ol[@id='results']/li/div[@class='bd']//a[@class='title main-headline']")
    private List<WebElement> contactsName;

    @FindBy(xpath = "//ol[@id='results']/li/div[@class='bd']/div[@class='description']")
    private List<WebElement> contactsDescription;

    @FindBy(xpath = "//*[@id='results-pagination']//li[@class='next']/a")
    private WebElement resultsNextButton;


    public EmployeePage navigateToEmployeePage() throws InterruptedException {
        boolean nextButtonPresent;
        do {
            Thread.sleep(1000);
            for (int i = 0; i < contactsDescription.size(); i++) {
                if (contactsDescription.get(i).getText().contains("Endava")) {
                    contactsName.get(i).click();
                    EmployeePage employeePage = PageFactory.initElements(driver, EmployeePage.class);
                    return employeePage;
                }
            }
            try {
                resultsNextButton.isDisplayed();
                nextButtonPresent = true;
            } catch (NoSuchElementException e) {
                nextButtonPresent = false;
            }
            if (nextButtonPresent == true) {
                resultsNextButton.sendKeys(Keys.RETURN);
            }
        } while (nextButtonPresent);
        return null;
    }
}
