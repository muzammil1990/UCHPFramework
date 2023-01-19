package com.volvo.in.page.crediting;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class CreateOutstandingClaimSummaryCompany {

    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    SoftAssert softAssert = new SoftAssert();

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Create document')]")
    WebElement createDocumentBtnElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Crediting ']")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Create outstanding claim summary (Company) ')]")
    WebElement createOutStandingSummSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Create outstanding claim summary (Company)')]")
    WebElement createOutStandingClaimSummTitleElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Yes')]")
    WebElement modalDailogueYesBtnElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'No')]")
    WebElement modalDailogueNoBtnElement;

    @FindBy(how = How.XPATH, using = "//div[@class='toast uchp-toast uchp-toast-info']/div/div[@class='toast-message']")
    WebElement captureErrorMsg;

    // div[@ng-click='tapToast()']/button[@class='toast-close-button ng-scope']

    public CreateOutstandingClaimSummaryCompany(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Creding" main menu to "CreateOutStandingSummary" sub menu.
     *****************************************/
    public void navigateToCreateOutStandingSummary() {

        keywords.performMenuAction(driver, creditingMainMenuElement, createOutStandingSummSubMenuElement, createOutStandingClaimSummTitleElement);
        // validate whether page navigation is successfull
    }

    /**********************************************
     * #Description : This is a common function to create OutStandingClaimSummary document in "CreateOutStandingSummary" window.16
     **********************************************/
    public void createDocument(HashMap<String, Object> data) {
        keywords.waitForVisibilityOfElement(driver, 20, createDocumentBtnElement);
        keywords.clickOnButton(driver, createDocumentBtnElement);
        keywords.handleModalDailogues(driver);
        keywords.keyEnter(modalDailogueYesBtnElement);
        driver.switchTo().defaultContent();
        keywords.waitTime1(2);
        try {
            if (keywords.elementIsDisplayed(captureErrorMsg, "Error message pop-up")) {
                keywords.APP_LOGS.info(keywords.getTextValue(captureErrorMsg));
                keywords.APP_LOGS.info("Failed to create document for company");
                ExtentTestManager.getTest().log(Status.INFO, "Error while create outstanding claim summary  " + keywords.getTextValue(captureErrorMsg));
                Assert.fail("Error while create outstanding claim summary  " + keywords.getTextValue(captureErrorMsg));
            } else {
                keywords.APP_LOGS.info("Document created for company");
                ExtentTestManager.getTest().log(Status.INFO, "Document created for company");
            }
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
            Assert.fail();
        }
    }
}
