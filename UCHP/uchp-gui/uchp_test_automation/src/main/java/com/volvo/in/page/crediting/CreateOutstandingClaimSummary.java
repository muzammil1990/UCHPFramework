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
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class CreateOutstandingClaimSummary {

    WebDriver driver;
    UIUtils keywords = new UIUtils();
    BaseClass baseclass = new BaseClass();
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();

    @FindBy(how = How.XPATH, using = "//input[@id='repairingImporter']")
    WebElement importerTextBoxElement;

    @FindBy(how = How.ID, using = "repairing_Importer")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "repairing_Dealer")
    WebElement dealerDropDownElement;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingDealer']")
    WebElement dealerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//select[@id='droplistMenuBrand']")
    WebElement brandElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Create document')]")
    WebElement createDocumentBtnElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Crediting ']")
    WebElement creditingMainMenuElement;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Create outstanding claim summary')]")
    WebElement createOutStandingSummSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Create outstanding claim summary')]")
    WebElement createOutStandingClaimSummTitleElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Yes')]")
    WebElement modalDailogueYesBtnElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'No')]")
    WebElement modalDailogueNoBtnElement;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-title-container']/*[contains(., ' Create document')]")
    WebElement modalDailogueTextMessage1;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-body']/div[@class='modal-message-header ng-binding']")
    WebElement modalDailogueTextMessage2;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-body']/*[contains(., 'Document will be found in a few minutes under menu')]")
    WebElement modalDailogueTextMessage3;

    @FindBy(how = How.XPATH, using = "//div[@ng-click='tapToast()']/div[@on='allowHtml']/*[contains(., 'Internal Server Error')]")
    WebElement internalServerError;

    @FindBy(how = How.XPATH, using = "//div[@ng-click='tapToast()']/div[@on='allowHtml']/*[contains(., 'Document will be found in a few minutes')]")
    WebElement documentCreatedPassed;

    @FindBy(how = How.XPATH, using = "//div[@class='toast uchp-toast uchp-toast-error']")
    WebElement captureErrorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement closeValidationAlert;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    public CreateOutstandingClaimSummary(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Creding" main menu to "CreateOutStandingSummary" sub menu
     * @param driver TODO
     * @param driver 
     *****************************************/
    public void navigateToCreateOutStandingSummary(WebDriver driver) {
        keywords.waitForVisibilityOfElement(driver, 30, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        keywords.waitForVisibilityOfElement(driver, 8, creditingMainMenuElement);
        keywords.performMenuAction(driver, creditingMainMenuElement, createOutStandingSummSubMenuElement, createOutStandingClaimSummTitleElement);
        //keywords.waitForPageLoadSuccessfully(driver);
    }

    /**********************************************
     * #Description : This is a common function to create OutStandingClaimSummary document in "CreateOutStandingSummary" window.
     **********************************************/
    @SuppressWarnings("static-access")
    public void createDocument(HashMap<String, Object> data) {
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, data.get("Dealer").toString(), driver);
        keywords.selectVisibleText(brandElement, appBasedUtils.getStringData(data, "Brand"));
        keywords.clickOnButton(driver, createDocumentBtnElement);
        keywords.handleModalDailogues(driver);
        keywords.keyEnter(modalDailogueYesBtnElement);
        driver.switchTo().defaultContent();
        keywords.waitTime1(2);
        try {
            if (keywords.elementIsDisplayed(captureErrorMsg, "Error message pop-up")) {
                keywords.APP_LOGS.info(keywords.getTextValue(captureErrorMsg));
                String valErrorMsg = keywords.getTextValue(captureErrorMsg);
                keywords.clickOnButton(driver, closeValidationAlert);
                ExtentTestManager.getTest().log(Status.FAIL, valErrorMsg);
                Assert.fail("Error while create outstanding claim summary  " + valErrorMsg);
            } else if (keywords.elementIsDisplayed(documentCreatedPassed, "Document created pop-up")) {
                String valMsg = keywords.getTextValue(documentCreatedPassed);
                keywords.clickOnButton(driver, closeValidationAlert);
                ExtentTestManager.getTest().log(Status.PASS, valMsg);
            } else {
                keywords.APP_LOGS.info("Error while create outstanding claim summary  ");
                keywords.APP_LOGS.info("Failed to create document for brand  " + appBasedUtils.getStringData(data, "Brand"));
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /*************************************************
     * #Description : This is a common function to validate all WebElements in the modal dialogue of "CreateOutStandingSummary" window.
     ***************************************************/
    @SuppressWarnings("static-access")
    public void validateModelDailogueMessages(HashMap<String, Object> data) {
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.selectVisibleText(brandElement, appBasedUtils.getStringData(data, "Brand"));
        keywords.clickOnButton(driver, createDocumentBtnElement);
        keywords.handleModalDailogues(driver);
        keywords.waitTime1(5);
        keywords.assertEqual("Create document", modalDailogueTextMessage1.getText(), softAssert);
        if (appBasedUtils.getStringData(data, "Company").equals("Volvo Penta Corporation")) {
            keywords.assertEqual("Document will be found in a few minutes under menu Print outstanding claim summary (Business partner)",
                                 modalDailogueTextMessage3.getText(), softAssert);
        } else {
            keywords.assertEqual("Document will be found in a few minutes under menu Print outstanding claim summary (Dealer)",
                                 modalDailogueTextMessage3.getText(), softAssert);
        }
        keywords.assertEqual("No", modalDailogueNoBtnElement.getText(), softAssert);
        keywords.assertEqual("Yes", modalDailogueYesBtnElement.getText(), softAssert);
        keywords.waitForElementToBeClickable(driver, 5, modalDailogueNoBtnElement, "clicked on \"Create document\" button");
        driver.switchTo().defaultContent();
        keywords.APP_LOGS.info("Gui functionlaity is verified successfully");
        softAssert.assertAll();
        keywords.waitTime1(2);
        ExtentTestManager.getTest().log(Status.INFO, "Gui functionlaity is verified successfully");
    }

}
