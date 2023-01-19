package com.volvo.in.page.tma;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class AnalyseMaterial {

    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    ManualStartReceiveOrAnalyze manualStartReceiveAnalyze = new ManualStartReceiveOrAnalyze(driver);
    SoftAssert openSelectedClaimJobAssert;
    UIUtils keywords = new UIUtils(driver);
    public WebDriverWait wait = null;
    SoftAssert softAssert;
    public Logger APP_LOGS = Logger.getLogger(AnalyseMaterial.class);

    String previousAnalysisLabelText;
    String problemText;
    String analysisReusltText;
    String dealerFeedbackText;

    @FindBy(how = How.XPATH, using = "//b[text()='TMA status']/../following-sibling::td[1]/span")
    WebElement TMAStatusElement;

    @FindBy(how = How.ID, using = "nextTMAStatus")
    WebElement dropDownNextTMAStatus;

    @FindBy(how = How.ID, using = "saveReceiveAnalyse")
    WebElement buttonSave;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Add to storage')]")
    WebElement buttonAddToStorage;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Scrap material')]")
    WebElement buttonScrapMaterial;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Initiate shipping')]")
    WebElement buttonInitiateShipping;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Change TMA site')]")
    WebElement buttonChangeTMASite;

    @FindBy(how = How.ID, using = "rdTmaApproved")
    WebElement radioButtonTMAApproved;

    @FindBy(how = How.ID, using = "selectApproved")
    WebElement dropDownTMAApproved;

    @FindBy(how = How.ID, using = "rdTmaPartlyApproved")
    WebElement radioButtonTMAPartlyApproved;

    @FindBy(how = How.ID, using = "selectPartlyApproved")
    WebElement dropDownTMAPartlyApproved;

    @FindBy(how = How.XPATH, using = "//th[text()='Analysis']")
    WebElement labelAnalysis;

    @FindBy(how = How.ID, using = "selectUsableAnalysis")
    WebElement dropDownUsableAnalysis;

    @FindBy(how = How.ID, using = "analysisProblem")
    WebElement textBoxAnalysisProblem;

    @FindBy(how = How.ID, using = "analysisResult")
    WebElement textBoxAnalysisResult;

    @FindBy(how = How.ID, using = "dealerFeedback")
    WebElement textBoxDealerFeedback;

    @FindBy(how = How.ID, using = "addToPreviousAnalysis")
    WebElement checkBoxAddToPreviousAnalysis;

    @FindBy(how = How.XPATH, using = "//a[text()='Adm. previous analysis']")
    WebElement admPreviousAnalysis;

    @FindBy(how = How.XPATH, using = "//h4[text()='Previous analysis']")
    WebElement labelPreviousAnalysis;

    @FindBy(how = How.XPATH, using = "//button[@id='closePrevAnalysis']/span")
    WebElement buttonPreviousAnalysisClose;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    public AnalyseMaterial(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : this method is to get the currect TMA Status in Analyse screen
     ****************************************/
    public String getTMAStatus() {
        keywords.waitTime1(3);
        String textTMAStatus = keywords.getTextValue(TMAStatusElement);
        APP_LOGS.info("TMA Status is : " + textTMAStatus);
        return textTMAStatus;

    }

    /****************************************
     * #Description : this method is to verify the button enabled/disabled in Analyse material page
     ****************************************/

    public void verifyButtonsEnableOrDisabledInAnalyseMaterialPage() {
        assertTrue(!keywords.elementIsEnabled(buttonSave));
        assertTrue((keywords.elementIsEnabled(buttonAddToStorage)));
        assertTrue((keywords.elementIsEnabled(buttonScrapMaterial)));
        assertTrue((keywords.elementIsEnabled(buttonInitiateShipping)));
        assertTrue((keywords.elementIsEnabled(buttonChangeTMASite)));
    }

    /****************************************
     * #Description : this method is to verify TMA Decision = Partly approved
     ****************************************/

    public void validateTMADecisionPartlyApproved(HashMap<String, Object> data) throws IOException {
        softAssert = new SoftAssert();
        keywords.waitTime1(4);
        verifyButtonsEnableOrDisabledInAnalyseMaterialPage();
        appBasedUtils.asserVerification(getTMAStatus(), appBasedUtils.getStringData(data, "TMAStatusReceived"), softAssert);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", radioButtonTMAPartlyApproved);
        keywords.clickOnButton(driver, radioButtonTMAPartlyApproved);
        keywords.selectVisibleText(dropDownTMAPartlyApproved, appBasedUtils.getStringData(data, "PartlyApprovalType"));
        keywords.waitTime1(2);
        assertTrue((keywords.elementIsEnabled(buttonSave)));
        keywords.clickOnButton(driver, buttonSave);
        ExtentTestManager.getTest().log(Status.INFO, "TMA Decision is saved as: Partly Approved");
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        appBasedUtils.asserVerification(getTMAStatus(), appBasedUtils.getStringData(data, "TMAStatusAnalysisPending"), softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : this method is to Update the next TMA Status: Analysed
     ****************************************/

    public void updateNextTMAStatusToAnalyze(HashMap<String, Object> data) {
        APP_LOGS.info("***Updating next TMA status to Analysed***");
        keywords.selectVisibleText(dropDownNextTMAStatus, appBasedUtils.getStringData(data, "NextTMAStatus"));
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, buttonSave);
        ExtentTestManager.getTest().log(Status.INFO, "TMA Status is changed to: Analysed");
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);

    }

    /****************************************
     * #Description : this method is to update the Analyse section in Analyse material page
     ****************************************/

    public void upadteAnaysisSection(HashMap<String, Object> data) {
        try {
            APP_LOGS.info("***Updating Analysis section***");
            keywords.waitTime1(3);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView();", labelAnalysis);
            keywords.input(textBoxAnalysisProblem, appBasedUtils.getStringData(data, "AnayisisProblem"), "Problem");
            keywords.input(textBoxAnalysisResult, appBasedUtils.getStringData(data, "AnalysisResult"), "Result");
            keywords.input(textBoxDealerFeedback, appBasedUtils.getStringData(data, "DealerFeedback"), "Dealer feedback");
            keywords.setCheckboxIfNotSelected(checkBoxAddToPreviousAnalysis);
            keywords.scrollPage(driver, -500);
            keywords.waitTime1(2);
            keywords.clickOnButton(driver, buttonSave);
            ExtentTestManager.getTest().log(Status.INFO, "Analyse section details have been updated and saved");
        } catch (Exception e) {
            APP_LOGS.error("Analysis details could not be saved");
        }

    }

    /****************************************
     * #Description : this method is to validate the details present in Previous Analysis screen
     ****************************************/

    public void validateAdmPreviousAnalysisScreen(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        keywords.waitTime1(5);
        APP_LOGS.info("*** Clicking on Adm Previous Analysis link ***");
        keywords.clickOnButton(driver, admPreviousAnalysis);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        previousAnalysisLabelText = keywords.getTextValue(labelPreviousAnalysis);
        APP_LOGS.info("Opening Previous Analysis Window");

        appBasedUtils.asserVerification("Previous Analysis Label", appBasedUtils.getStringData(data, "PreviousAnalysisLabelText"), previousAnalysisLabelText,
                                        softAssert);

        String xpath1 = "(//div[text()='";
        String xpath2 = "'])[1]";

        String problemXpath = xpath1 + appBasedUtils.getStringData(data, "AnayisisProblem") + xpath2;
        WebElement problemElement = driver.findElement(By.xpath(problemXpath));

        appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "AnayisisProblem"), keywords.getTextValue(problemElement), softAssert);

        String analysisResultXpath = xpath1 + appBasedUtils.getStringData(data, "AnalysisResult") + xpath2;
        WebElement analysisResultElement = driver.findElement(By.xpath(analysisResultXpath));

        appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "AnalysisResult"), keywords.getTextValue(analysisResultElement), softAssert);

        String dealerFeedBackXpath = xpath1 + appBasedUtils.getStringData(data, "DealerFeedback") + xpath2;
        WebElement dealerFeedBackElement = driver.findElement(By.xpath(dealerFeedBackXpath));

        appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "DealerFeedback"), keywords.getTextValue(dealerFeedBackElement), softAssert);
        keywords.waitTime1(4);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", buttonPreviousAnalysisClose);
        ExtentTestManager.getTest().log(Status.INFO, "Clicked on Adm Previous Analysis link and verified the details entered in Analysis section");
    }

}
