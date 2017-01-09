package com.endava.pages;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by andpopescu on 1/5/2017.
 */
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='main-search-box']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class = 'search-button']")
    private WebElement searchButton;

    public ResultsPage searchContact()throws IOException{
        ReadData objExcelFile = new ReadData();
        List<Employees> contacts = (List<Employees>) objExcelFile.readExcel("test.xls", "Sheet1");
        //System.out.println(contact.getFirstName() + " " +contact.getLastName());

        searchField.sendKeys(contacts.get(0).getFirstName() + " " +contacts.get(0).getLastName());
        searchButton.click();
        ResultsPage resultsPage = PageFactory.initElements(driver, ResultsPage.class);
        return resultsPage;
    }
}
