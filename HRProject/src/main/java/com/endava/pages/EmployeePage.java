package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by andpopescu on 1/6/2017.
 */
public class EmployeePage {
    private WebDriver driver;

    public EmployeePage(WebDriver driver){
        this.driver = driver;
    }

    //@FindBy(xpath = "//li[contains(@id,'connection-')]/strong/span/strong/a")
    @FindBy(xpath = "//a[@class='connections-name']")
    private List<WebElement> candidates;

    public void checkCandidate(){
        System.out.println(candidates.get(0).getText());
        candidates.get(0).click();
    }
}
