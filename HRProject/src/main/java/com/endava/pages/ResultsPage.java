package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public EmployeePage navigateToEmployeePage(){
        for (int i=0;i<contactsDescription.size();i++){
            if(contactsDescription.get(i).getText().contains("Endava")){
                contactsName.get(i).click();
                EmployeePage employeePage = PageFactory.initElements(driver, EmployeePage.class);
                return employeePage;
            }
        }
        return null;
    }
}
