package com.volvo.in.page.claim;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

public class ListClaimJobs {

    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    String fromDate;
    String toDate;
    String fromTextValue;
    String currentUserFirstName;
    public Logger APP_LOGS = Logger.getLogger(ListClaimJobs.class);

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Welcome')][1]")
    WebElement currentUserElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claimMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homeButtonElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'List claim jobs ')]")
    WebElement listClaimJobSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[text()='List claim jobs']")
    WebElement listClaimJobPageTitleElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Not evaluated')]")
    WebElement notEvaluatedTabElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'TMA decision')]")
    WebElement TMADecisionTabElement;

    @FindBy(how = How.XPATH, using = "//a[text()='My not evaluated']")
    WebElement MyNotEvaluatedTabElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Not finished')]")
    WebElement notFinishedTabElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Material instruction')]")
    WebElement materialInstructionTabElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Appeal')]")
    WebElement appealTabElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Search result')]")
    WebElement searchResultTabElement;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingImporter']")
    WebElement importerTextBoxElement;

    @FindBy(how = How.ID, using = "repairing_Importer")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "repairing_Dealer")
    WebElement dealerDropDownElement;

    @FindBy(how = How.XPATH, using = "//input[@id='VIN']")
    WebElement VINElement;

    @FindBy(how = How.XPATH, using = "//input[@name='machidchseries']")
    WebElement chassissSeriesElement;

    @FindBy(how = How.XPATH, using = "//input[@id='machIdCH']")
    WebElement chassissNumberElement;

    @FindBy(how = How.XPATH, using = "//input[@id='registrationNumber']")
    WebElement registrationNumberElement;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingDealer']")
    WebElement dealerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-xs ng-binding']")
    WebElement searchButtonElement;

    @FindBy(how = How.XPATH, using = "//button[@id='shipping-carrires-ok']")
    WebElement okButtonElementPrintDealershippingDocument;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Company')]")
    WebElement gridColumnNameElement;

    @FindBy(how = How.ID, using = "claimSearchTypes")
    WebElement searchTypeListboxElement;

    @FindBy(how = How.ID, using = "typeOfClaims")
    WebElement claimTypeListboxElement;

    @FindBy(how = How.ID, using = "miPrintStatuses")
    WebElement miPrintStatusListboxElement;

    @FindBy(how = How.ID, using = "appealStatuses")
    WebElement appealStatusListboxElement;

    @FindBy(how = How.ID, using = "miTypes")
    WebElement miTypeListboxElement;

    @FindBy(how = How.ID, using = "shippingCarriers")
    WebElement carrierListboxElementPrintDealerShippingDocument;

    @FindBy(how = How.XPATH, using = "//input[@id='trackingId']")
    WebElement trackingIDElementPrintDealerShippingDocument;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input")
    WebElement repairDateFromElement;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input")
    WebElement registrationDateToElement;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input")
    WebElement registrationDateFromElement;

    @FindBy(how = How.XPATH, using = "//div[@id='materialInstrDateFrom']//input")
    WebElement miDateFromElement;

    @FindBy(how = How.XPATH, using = "//input[@id='refNo']")
    WebElement referenceNumberElement;

    @FindBy(how = How.XPATH, using = "//input[@id='causalPartPrefix']")
    WebElement causalPartPrefixElement;

    @FindBy(how = How.XPATH, using = "//input[@id='causalPartNo']")
    WebElement causalPartNumberElement;

    @FindBy(how = How.XPATH, using = "//input[@id='SCC']")
    WebElement sccElement;

    @FindBy(how = How.XPATH, using = "//input[@id='followUpCode']")
    WebElement followUpCodeElement;

    @FindBy(how = How.XPATH, using = "//input[@id='functionGroup']")
    WebElement functionGroupElement;

    @FindBy(how = How.XPATH, using = "//div[@id='materialInstrDateTo']//input")
    WebElement miDateToElement;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']/input")
    WebElement repairDateToElement;

    @FindBy(how = How.ID, using = "repairOrderNo")
    WebElement repairOrderNumberElement;

    @FindBy(how = How.XPATH, using = "//label[text()='Repair order no.']")
    WebElement labelRepairOrderNumber;

    @FindBy(how = How.XPATH, using = "//label[text()='Follow up code']")
    WebElement labelFollowUpCode;

    @FindBy(how = How.XPATH, using = "//label[text()='Function group']")
    WebElement labelFunctionGroup;

    @FindBy(how = How.XPATH, using = "//label[text()='Causal part']")
    WebElement labelCausalPart;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfClaims']")
    WebElement typeOfClaimElement;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageCloseElements;

    @FindBy(how = How.XPATH, using = "//button[@id='newclaimjobbtn']")
    WebElement newClaimJobButtonElement;

    @FindBy(how = How.XPATH, using = "//button[@id='openslectedjobsbtn']")
    WebElement openSelectedClaimJobsButtonElement;

    @FindBy(how = How.ID, using = "printmilistbtn")
    WebElement printMIListButtonElement;

    @FindBy(how = How.ID, using = "printdeliverynotebtn")
    WebElement printDeliveryNoteSummaryButtonElement;

    @FindBy(how = How.ID, using = "updatenoactionbtn")
    WebElement updateNoActionButtonElement;

    @FindBy(how = How.ID, using = "removefromshipmentbtn")
    WebElement removeFromShipmentButtonElement;

    @FindBy(how = How.ID, using = "printdealershippingdocumentbtn")
    WebElement printDealerShippingDocumentButtonElement;

    @FindBy(how = How.ID, using = "printholdscraplistbtn")
    WebElement printHoldScrapListButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement currentCompanyElement;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-collapse ng-binding collapsed']")
    WebElement expandSearchAreaButtonElement;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-collapse btn-default header-remove-padding']")
    WebElement expandClaimJobAreaButtonElement;

    @FindBy(how = How.XPATH, using = "//h5[contains(text(),'Repair header')]")
    WebElement repairHeaderSectionElement;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-collapse ng-binding']")
    WebElement collapseSearchAreaButtonElement;

    @FindBy(how = How.XPATH, using = "//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[1]//a")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-pager-count']")
    WebElement gridPageCount;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    public String checkBoxXpathSearchResult = "(//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope'])[";

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[5])[1]")
    WebElement referenceNumberInSearchResult;

    public String referenceNumberInGRIDFirstPart = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[5])[";

    public String dealerNumberInGRIDFirstPart = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[3])[";

    public String miTypeInGRIDFirstPart = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[9])[";

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Page forward']")
    WebElement gridForwardButton;

    @FindBy(how = How.XPATH, using = "//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[1]//a")
    WebElement gridClaimRefNo;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//button[text()='Next claim job']")
    WebElement nextClaimJob;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Reference no.']]]/div[2]")
    WebElement claimJobReferenceNo;

    @FindBy(how = How.XPATH, using = "//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[1]/div/div[13]/div/button")
    WebElement claimJobDeleteInNotFinishedTab;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'You are')]") // "//div[@class='modal-message-header ng-binding']")
    WebElement claimJobDeleteModalMessage;

    @FindBy(how = How.XPATH, using = "//button[text()='Yes']")
    WebElement claimJobDeleteModalYesButton;

    @FindBy(how = How.XPATH, using = "//b[text()='No items to display']")
    WebElement searchResultNoItemToDisplay;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[1])[1]//span")
    WebElement claimStatusToolTip;

    @FindBy(how = How.XPATH, using = "//select[@class='ng-pristine ng-untouched ng-valid ng-not-empty']")
    WebElement gridItemsToDisplay;

    @FindBy(how = How.XPATH, using = "//a[text()='Claim job information']")
    WebElement claimJobInformation;

    @FindBy(how = How.XPATH, using = "//button[@ ng-click = 'ctrl.historyBack()']")
    WebElement clickOnCloseButton;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../following-sibling::td/span")
    WebElement typeOfConcernLabelValue;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@uib-tooltip,'Grand total')]")
    WebElement columnGrandTotalInSearchResult;

    @FindBy(how = How.XPATH, using = "//div[@id='not_evaluated']/div[1]/div[2]/div[2]/div[1]/div[1]/div/div[11]/div")
    WebElement firstGrandTotalValueInSearchList;

    @FindBy(how = How.XPATH, using = "//b[text()='Grand total']/..//following-sibling::td[1]")
    WebElement claimGrandTotalValue;

    public static HashMap<String, Object> applicationData = new HashMap<String, Object>();

    public String referenceNumberHolder;

    public SoftAssert testCaseAssertion;

    public ListClaimJobs(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "List claim jobs" sub menu :18-05-17 #Input Parameters:
     ******************************************/
    public void navigateToListClaimJob() {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 9, loadingIcon);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 20, homeButtonElement);
        keywords.clickOnLink(homeButtonElement, driver);
        keywords.waitForVisibilityOfElement(driver, 20, claimMainMenuElement);
        keywords.performMenuAction(driver, claimMainMenuElement, listClaimJobSubMenuElement, listClaimJobPageTitleElement);
        keywords.APP_LOGS.info("Navigated to menu " + keywords.getTextValue(listClaimJobPageTitleElement));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 9, loadingIcon);
    }

    /****************************************
     * #Description : This expands the search area to perform standard search.
     ******************************************/
    public void expandStandardSearchArea() {
        keywords.waitTime1(3);
        keywords.waitForVisibilityOfElement(driver, 20, expandSearchAreaButtonElement);
        expandSearchAreaButtonElement.click();
        keywords.waitTime1(3);
    }

    /*****************************************
     * #Description : This expands the Claim job area to perform standard search.
     ******************************************/
    public void expandClaimJobArea() {
        try {
            driver.switchTo().defaultContent();
            keywords.clickOnButton(driver, expandClaimJobAreaButtonElement);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
        }
    }

    public void VerifyButtonAndClaimTabs(HashMap<String, Object> data) {
        try {
            testCaseAssertion = new SoftAssert();
            keywords.waitForVisibilityOfElement(driver, 20, notFinishedTabElement);
            /* Validates roles for ClaimHandlerDealer role */
            appBasedUtils.asserVerification("Not finished", notFinishedTabElement.getText(), testCaseAssertion);
            appBasedUtils.asserVerification("Material instruction", materialInstructionTabElement.getText(), testCaseAssertion);
            appBasedUtils.asserVerification("Appeal", appealTabElement.getText(), testCaseAssertion);
            appBasedUtils.asserVerification("Search result", searchResultTabElement.getText(), testCaseAssertion);
            keywords.waitForVisibilityOfElement(driver, 6, currentUserElement);
            if (appBasedUtils.getStringData(data, "UserGroup").contains("ClaimHandlerCentral") || currentUserElement.getText().contains("ClaimHandlerMarket")) {
                appBasedUtils.asserVerification("Not evaluated", notEvaluatedTabElement.getText(), testCaseAssertion);
                appBasedUtils.asserVerification("TMA decision", TMADecisionTabElement.getText(), testCaseAssertion);
                appBasedUtils.asserVerification("My not evaluated", MyNotEvaluatedTabElement.getText(), testCaseAssertion);
                testCaseAssertion.assertFalse(keywords.elementIsEnabled(updateNoActionButtonElement));
            }
            testCaseAssertion.assertTrue(keywords.elementIsEnabled(newClaimJobButtonElement));
            testCaseAssertion.assertFalse(keywords.elementIsEnabled(openSelectedClaimJobsButtonElement));
            testCaseAssertion.assertFalse(keywords.elementIsEnabled(printDeliveryNoteSummaryButtonElement));
            if (!currentCompanyElement.getText().equalsIgnoreCase("TSM Americas")) {
                testCaseAssertion.assertFalse(keywords.elementIsEnabled(printMIListButtonElement));
            } else {
                testCaseAssertion.assertFalse(keywords.elementIsEnabled(removeFromShipmentButtonElement));
                testCaseAssertion.assertFalse(keywords.elementIsEnabled(printDealerShippingDocumentButtonElement));
                testCaseAssertion.assertFalse(keywords.elementIsEnabled(printHoldScrapListButtonElement));
            }
        } catch (Exception e) {
            if (keywords.elementIsDisplayed(errorMsgElement, "Error message pop up")) {
                String alertTextMessage = keywords.getTextValue(errorMsgElement);
                Assert.fail("Error in validation" + alertTextMessage);
            } else {
                Assert.fail("Fail!!! Search result not found '");
            }
            keywords.APP_LOGS.error("failed!", e);
        }
        testCaseAssertion.assertAll();
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "Advance search" sub menu #Input Parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporterDealerInTMADecisionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "Status", "TMAStatus" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerGreyedOutValue,
                                                                        importerDropDownElement, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerGreyedOutValue,
                                                                        dealerDropDownElement, appBasedUtils.getStringData(data, "Dealer"), driver);
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader,
                                          "VTCCentralTMADecisionImporter_3 VTCCentralTMADecisionDealer_4 VTCCentralTMADecisionStatus_1 VTCCentralTMADecisionTMADecision_11");
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "List Claim Jobs" sub menu and search with Importer, Dealer and Search
     * type = Not evaluated #Input Parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporterDealerPartNumberNotEvaluated(HashMap<String, Object> data) {
        try {
            String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "Status" };
            appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerGreyedOutValue,
                                                                            importerDropDownElement, appBasedUtils.getStringData(data, "Importer"), driver);
            appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerGreyedOutValue,
                                                                            dealerDropDownElement, appBasedUtils.getStringData(data, "Dealer"), driver);
            expandClaimJobArea();
            keywords.waitForVisibilityOfElement(driver, 20, causalPartPrefixElement);
            keywords.input(causalPartPrefixElement, appBasedUtils.getStringData(data, "CausalPartPrefix"), "Causal Part Prefix");
            keywords.input(causalPartNumberElement, appBasedUtils.getStringData(data, "CausalPartNumber"), "Causal Part Number");
            keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
            keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
            keywords.clickOnButton(driver, searchButtonElement);
            listClaimJobsGridResultValidation(data, columnHeader,
                                              "VTCCentralMyNotEvaluatedImporter_3 VTCCentralMyNotEvaluatedDealer_4 VTCCentralMyNotEvaluatetdStatus_1");
        } catch (Exception e) {
            APP_LOGS.error("Search could not be completed");
            Assert.fail();
        }
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "List Claim Jobs" sub menu and verify Grand Total value from Search Result
     * and claim #Input Parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void verifyGrandTotalCostInSearchResultGridAndInClaimJobInformation(HashMap<String, Object> data) {
        try {
            keywords.elementIsDisplayed(columnGrandTotalInSearchResult, "Grand Total column in Search Result");
            ExtentTestManager.getTest().log(Status.INFO, "Grand total column is displayed in Search result grid");
            String grandTotalValueInSearchList = keywords.getTextValue(firstGrandTotalValueInSearchList);
            APP_LOGS.info("First Grand total cost value in the Search list is: " + grandTotalValueInSearchList);
            keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + 2 + "]")));
            keywords.clickOnButton(driver, openSelectedClaimJobsButtonElement);
            keywords.waitForVisibilityOfElement(driver, 20, claimGrandTotalValue);
            String grandTotalValueInClaimJobInformation = keywords.getTextValue(claimGrandTotalValue);
            APP_LOGS.info("Grand total cost in Claim is: " + grandTotalValueInClaimJobInformation);
            if (grandTotalValueInSearchList.equals(grandTotalValueInClaimJobInformation)) {
                APP_LOGS.info("Grand total cost of the first claim in the Search list and in the claim are same");

            } else if (appBasedUtils.isStringNullOrEmptyOrZero(grandTotalValueInSearchList)
                    && appBasedUtils.isStringNullOrEmptyOrZero(grandTotalValueInClaimJobInformation)) {
                APP_LOGS.info("Grand total cost of first claim in the Search list and in the claim are same");
            } else {
                APP_LOGS.info("Grand total cost of the first claim in the Search list and in the claim are not same");
                Assert.fail();
            }
            ExtentTestManager.getTest().log(Status.INFO, "Grand total cost of the first claim in the Search list is: " + grandTotalValueInSearchList
                    + " & Grand total cost in the claim job information tab is: " + grandTotalValueInClaimJobInformation);

        } catch (Exception e) {
            APP_LOGS.error("Could not compare Grand total cost of Search result and Claim job information");
            Assert.fail();
        }

    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "List Claim Jobs" sub menu and verify Search fields #Input Parameters:
     * excel data
     ******************************************/

    public void verifySearchFields() {
        try {
            testCaseAssertion = new SoftAssert();
            String repairOrderNumberText = labelRepairOrderNumber.getText();
            appBasedUtils.asserVerification("Repair order no.", repairOrderNumberText, testCaseAssertion);
            testCaseAssertion.assertTrue(repairOrderNumberElement.isDisplayed());
            expandClaimJobArea();
            keywords.waitForVisibilityOfElement(driver, 20, labelFollowUpCode);
            appBasedUtils.asserVerification("Follow up code", labelFollowUpCode.getText(), testCaseAssertion);
            testCaseAssertion.assertTrue(followUpCodeElement.isDisplayed());
            appBasedUtils.asserVerification("Function group", labelFunctionGroup.getText(), testCaseAssertion);
            testCaseAssertion.assertTrue(functionGroupElement.isDisplayed());
            appBasedUtils.asserVerification("Causal part", labelCausalPart.getText(), testCaseAssertion);
            testCaseAssertion.assertTrue(causalPartPrefixElement.isDisplayed() && causalPartNumberElement.isDisplayed());
            ExtentTestManager.getTest().log(Status.INFO, "Search fields: " + repairOrderNumberText + ", " + labelFollowUpCode.getText() + ", "
                    + labelFunctionGroup.getText() + " and " + labelCausalPart.getText() + " are present in List Claim job screen");
        } catch (NoSuchElementException e) {
            APP_LOGS.error("Search field/s not found in List Claim jobs search screen");
            Assert.fail();
        }
        testCaseAssertion.assertAll();

    }

    /****************************************
     * #Description : This method searches claims with importer dealer, chassis id, and this method is being used for manage claim job test cases #Input
     * Parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchClaimsForManageClaimJob(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "manageClaimJob" };
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "Importer")))
            appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                            importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "Dealer")))
            appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                            dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "ChassisSeries")))
            keywords.input(chassissSeriesElement, appBasedUtils.getStringData(data, "ChassisSeries"), "Chassis series");
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "ChassisNumber")))
            keywords.input(chassissNumberElement, appBasedUtils.getStringData(data, "ChassisNumber"), "Chassis Number");
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "RefNumber")))
            keywords.input(referenceNumberElement, appBasedUtils.getStringData(data, "RefNumber"), "Reference number");
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "SearchType"))) {
            keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "AppealStatus")))
                keywords.selectVisibleText(appealStatusListboxElement, appBasedUtils.getStringData(data, "AppealStatus"));
        }
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "ClaimType")))
            keywords.selectVisibleText(claimTypeListboxElement, appBasedUtils.getStringData(data, "ClaimType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "manageClaimJob");
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "List claim job" sub menu :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void searchWithMITypeInMaterialInstructionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "MIType" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.keyTab(miTypeListboxElement);
        keywords.waitTime(300);
        keywords.clickOnButton(driver, searchButtonElement);
        // Validates MIType column
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerMaterialInstructionMIType_9");
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "List claim job" sub menu :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void searchWithFollowUpCodeFunctionGroupInNotEvaluated(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "ExpectedClaimType" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, followUpCodeElement);
        keywords.input(followUpCodeElement, appBasedUtils.getStringData(data, "FollowUpCode"), "Follow-up code");
        keywords.input(functionGroupElement, appBasedUtils.getStringData(data, "FunctionGroup"), "Function group");
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Validates MIType column
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerNotEvalatedStatus_6");
    }

    /****************************************
     * #Description : This is a common function to searchWithMIDateInMaterialInstructionTab
     ******************************************/
    public void searchWithMIDateInMaterialInstructionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "MIDate" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        fromDate = appBasedUtils.getStringData(data, "MIDateFrom");
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitTime1(1);
        keywords.input(miDateFromElement, fromDate, "MI date from");
        keywords.keyTab(miDateFromElement);
        keywords.input(miDateToElement, toDate, "MI date to");
        keywords.keyTab(miDateToElement);
        keywords.clickOnButton(driver, searchButtonElement);
        // Validates MIType column
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerMaterialInstructionMIDate_10");
    }

    /****************************************
     * #Description : This is a common function to searchWithMIPrintStatusInMaterialInstructionTab
     ******************************************/
    public void searchWithMIPrintStatusInMaterialInstructionTab(HashMap<String, Object> data) {
        try {
            testCaseAssertion = new SoftAssert();
            String[] columnHeader = { "MIType" };
            expandClaimJobArea();
            keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
            keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
            keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
            keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
            keywords.clickOnButton(driver, searchButtonElement);
            keywords.waitTime(5000);
            // Validates MIType column
            listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerMaterialInstructionMIType_9");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /****************************************
     * #Description : This is a common function to searchWithAppealStatusInAppealTab
     ******************************************/
    public void searchWithAppealStatusInAppealTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "ExpectedAppealStatus" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, appealStatusListboxElement);
        keywords.selectVisibleText(appealStatusListboxElement, appBasedUtils.getStringData(data, "AppealStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Validates MIType column
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerAppealAppealStatus_10");
    }

    /****************************************
     * #Description : Searches with reference number, Causal part prefix ,causal part number and appeal status and verifies the result. :28-03-17 #Input
     * Parameters: excel data.
     ******************************************/
    public void searchWithCausalPartsAppealStatusInAppealTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "ExpectedAppealStatus", "RefNumber" };
        keywords.waitForVisibilityOfElement(driver, 20, referenceNumberElement);
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, causalPartPrefixElement);
        keywords.input(causalPartPrefixElement, appBasedUtils.getStringData(data, "CausalPartPrefix"), "Causal part prefix");
        keywords.input(causalPartNumberElement, appBasedUtils.getStringData(data, "CausalPartNumber"), "Causal part number");
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.selectVisibleText(appealStatusListboxElement, appBasedUtils.getStringData(data, "AppealStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Validates MIType column
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerAppealAppealStatus_10");
    }

    /****************************************
     * #Description : Searches with reference number, Causal part prefix ,causal part number and appeal status and verifies the result. #Input Parameters: excel
     * data.
     ******************************************/
    public void searchWithRepairOderNumInNotFinished(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "RepairOderNumber", "ExpectedClaimType" };
        keywords.waitForVisibilityOfElement(driver, 20, repairOrderNumberElement);
        keywords.input(repairOrderNumberElement, appBasedUtils.getStringData(data, "RepairOderNumber"), "Repair order number");
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, sccElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerNotFinishedRepairOrderNum_11");
    }

    /****************************************
     * #Description : This is a common function to searchWithCausalPartsAppealStatusInAppealTab.
     ******************************************/
    public void ClickTMADecisiontabVerifyResult(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "TMAStatus" };
        keywords.waitForVisibilityOfElement(driver, 20, TMADecisionTabElement);
        keywords.clickOnLink(TMADecisionTabElement, driver);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCCentralTMADecisionTMADecision_11");
    }

    /****************************************
     * #Description : This method is to click on appeal tab and verifies if correct result displayed on the screen :28-03-17 #Input Parameters: excel data.
     ******************************************/
    public void ClickAppealtabVerifyResult(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "ExpectedAppealStatus" };
        keywords.waitForVisibilityOfElement(driver, 20, appealTabElement);
        keywords.clickOnLink(appealTabElement, driver);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerAppealAppealStatus_10");
    }

    /****************************************
     * #Description : This method is to click on My not evaluatetd tab and verifies if correct result displayed on the screen #Input Parameters: excel data.
     ******************************************/
    public void ClickMyNotEvaluatedtabVerifyResult(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "Status", "ExpectedImportervalue", "ExpectedDealerValue" };
        keywords.waitForVisibilityOfElement(driver, 6, MyNotEvaluatedTabElement);
        keywords.clickOnLink(MyNotEvaluatedTabElement, driver);
        listClaimJobsGridResultValidation(data, columnHeader,
                                          "VTCCentralMyNotEvaluatetdStatus_1 VTCCentralMyNotEvaluatedImporter_3 VTCCentralMyNotEvaluatedDealer_4");
    }

    /****************************************
     * #Description : This method is to click on My not evaluatetd tab and verifies if correct result displayed on the screen :28-03-17 #Input Parameters: excel
     * data.
     ******************************************/
    public void SearchAndVerifyClaimTypeInMyNotEvaluated(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "Status", "ClaimType" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, claimTypeListboxElement);
        keywords.selectVisibleText(claimTypeListboxElement, appBasedUtils.getStringData(data, "ClaimType"));
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCCentralMyNotEvaluatetdStatus_1 VTCCentralMyNotEvaluatdClaimType_6");
    }

    /****************************************
     * #Description : Function to verify status column in the search result of Not finished column :28-03-17 #Input Parameters: excel data.
     ******************************************/
    public void ClickNotFinishedtabVerifyResult(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "Status" };
        keywords.waitForVisibilityOfElement(driver, 6, notFinishedTabElement);
        keywords.clickOnLink(notFinishedTabElement, driver);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerNotFinishedStatus_1");
    }

    /****************************************
     * #Description : Function to verify MI status column in the search result of Material instruction column :28-03-17 #Input Parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void ClickMatrlInstructiontabVerifyResult(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "MIType" };
        keywords.waitForVisibilityOfElement(driver, 20, materialInstructionTabElement);
        keywords.clickOnLink(materialInstructionTabElement, driver);
        if (baseclass.applicationUserName.equals("ClaimHandlerDealer")) {
            listClaimJobsGridResultValidation(data, columnHeader, "2A7");
        } else {
            listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerMaterialInstructionMIType_9");
        }
    }

    /****************************************
     * #Description : This is a common function tolistWithRegistrationDateInNotEvaluated
     ******************************************/
    public void listWithRegistrationDateInNotEvaluated(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "TypeOfConcern", "RegistrationDate" };
        keywords.waitForVisibilityOfElement(driver, 20, registrationDateFromElement);
        fromDate = appBasedUtils.getStringData(data, "RegistrationDateFrom");
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.input(registrationDateFromElement, fromDate, "Registration date from ");
        keywords.keyTab(registrationDateFromElement);
        keywords.waitForVisibilityOfElement(driver, 20, registrationDateToElement);
        keywords.input(registrationDateToElement, toDate, "Registration date to");
        keywords.keyTab(registrationDateToElement);
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCCentralNotEvaluatedTypeOfConcern_2 VTCCentralNotEvaluatedRegistrationDate_7");
    }

    /****************************************
     * #Description : This is a common function listAndVerifyClaimStatusInNotFinished
     ******************************************/
    public void listAndVerifyClaimStatusInNotFinished(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "Status", "Chassis id" };
        keywords.waitForVisibilityOfElement(driver, 20, repairDateFromElement);
        fromDate = appBasedUtils.getStringData(data, "RepairDateFrom");
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.input(repairDateFromElement, fromDate, "Repair from date");
        keywords.keyTab(repairDateFromElement);
        keywords.input(repairDateToElement, toDate, "Repair to date");
        keywords.keyTab(repairDateToElement);
        keywords.input(chassissSeriesElement, appBasedUtils.getStringData(data, "ChassisSeries"), "Chassis series");
        keywords.input(chassissNumberElement, appBasedUtils.getStringData(data, "ChassisNumber"), "Chassis number");
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerNotFinishedStatus_1 VTCDealerNotFinishedChassisID_8");
    }

    /****************************************
     * #Description : Searched claim using search criteria and verifies finished search result :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void listAndVerifyClaimTypeInNotFinished(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "ExpectedClaimType" };
        keywords.waitForVisibilityOfElement(driver, 20, VINElement);
        keywords.input(VINElement, appBasedUtils.getStringData(data, "VIN"), "VIN ");
        expandClaimJobArea();
        keywords.selectVisibleText(claimTypeListboxElement, appBasedUtils.getStringData(data, "ClaimType"));
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerNotFinishedClaimType_6");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on PrintMI button and validates the result :28-03-17 #Input
     * Parameters: excel data
     ******************************************/
    public void standardSearchPrintMIInMaterialInstruction(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintMI" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintMI");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on PrintMI button and validates the result :28-03-17 #Input
     * Parameters: excel data
     ******************************************/
    public void printMIInMaterialInstructionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintMI" };
        keywords.waitForVisibilityOfElement(driver, 20, materialInstructionTabElement);
        keywords.clickOnLink(materialInstructionTabElement, driver);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintMI");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on Print delivery note summary button and validates the
     * result :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void printDeliveryNoteSummaryInMaterialInstructionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintDeliveryNoteSummary" };
        keywords.waitForVisibilityOfElement(driver, 20, materialInstructionTabElement);
        keywords.clickOnLink(materialInstructionTabElement, driver);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintDeliveryNoteSummary");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on "Print dealer shipping document" button and validates the
     * result :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void standardSearchPrintDealerShippingDocumentInMaterialInstruction(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintDealeShippingDocument" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        if (appBasedUtils.getStringData(data, "CarrierType").equals("NULL") && appBasedUtils.getStringData(data, "TrackingID").equals("NULL"))
            printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "PrintDealerShippingDocumentNoCarrierNoTrackingID");
        else
            printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "PrintDealerShippingDocumentWithCarrierAndTrackingID");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on "Print dealer shipping document" button and validates the
     * result #Input Parameters: excel data
     ******************************************/
    public void printDealerShippingDocumentInMaterialInstructionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintDealeShippingDocument" };
        keywords.waitForVisibilityOfElement(driver, 20, materialInstructionTabElement);
        keywords.clickOnLink(materialInstructionTabElement, driver);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "PrintDealerShippingDocumentMaterialInstructionTab");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on "Print Hold/Scrap list" button and validates the result
     * #Input Parameters: excel data
     ******************************************/
    public void PrintHoldScrapListOfScrapMITypeInMaterialInstructionTab(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintHoldScrapList" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "PrintHoldScrapListOfScrapMITypeInMaterialInstructionTab");
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on "Print dealer shipping document" button and validates the
     * result :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void standardSearchPrintDealerShippingDocumentNegFlowInMaterialInstruction(HashMap<String, Object> data) {
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        keywords.waitForVisibilityOfElement(driver, 20, gridPageCount);
        keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + 2 + "]")));
        keywords.waitForVisibilityOfElement(driver, 20, printDealerShippingDocumentButtonElement);
        validatePrintMIAlertMessages(data, printDealerShippingDocumentButtonElement);
    }

    /****************************************
     * #Description : This is a common function to Checks check first column in the GRID and clicks on "Print dealer shipping document" button and validates the
     * result :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void standardSearchNegFlowRemoveFromShipmentInMaterialInstruction(HashMap<String, Object> data) {
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        keywords.waitForVisibilityOfElement(driver, 20, gridPageCount);
        keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + 2 + "]")));
        keywords.waitForVisibilityOfElement(driver, 20, removeFromShipmentButtonElement);
        validatePrintMIAlertMessages(data, removeFromShipmentButtonElement);
    }

    /****************************************
     * #Description : Validates and compare modal dialogue messages. :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void validatePrintMIAlertMessages(HashMap<String, Object> data, WebElement buttonToBeClicked) {
        keywords.clickOnButton(driver, buttonToBeClicked);
        keywords.handleModalDailogues(driver);
        keywords.waitForVisibilityOfElement(driver, 20, errorMsgElement);
        String errorMessageText = keywords.getTextValue(errorMsgElement);
        ExtentTestManager.getTest().log(Status.INFO, errorMessageText);
        Assert.assertTrue((keywords.assertContains(appBasedUtils.getStringData(data, "ExpectedMessage"), errorMessageText)), "Not expected Error message");
    }

    /****************************************
     * #Description : Validates and compare modal dialogue messages. :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void checkForAlertMessages(HashMap<String, Object> data) {
        keywords.handleModalDailogues(driver);
        keywords.waitForVisibilityOfElement(driver, 20, errorMsgElement);
        String errorMessageText = keywords.getTextValue(errorMsgElement);
        ExtentTestManager.getTest().log(Status.INFO, errorMessageText);
    }

    /****************************************
     * #Description : This is a common function to standardSearchUsingRefNumAndPrintMIInMaterialInstruction Parameters: excel data
     ******************************************/
    public void standardSearchUsingRefNumAndPrintMIInMaterialInstruction(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintMI" };
        keywords.waitForVisibilityOfElement(driver, 20, referenceNumberElement);
        keywords.input(referenceNumberElement, appBasedUtils.getStringData(data, "RefNumber"), "Reference number");
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.waitTime(5);
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintMI");
    }

    /****************************************
     * #Description : This is a common function to standardSearchUsingRefNumAndPrintDeliveryNoteSummaryInMaterialInstruction Parameters: excel data
     ******************************************/
    public void standardSearchUsingRefNumAndPrintDeliveryNoteSummaryInMaterialInstruction(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintDeliveryNoteSummary" };
        keywords.waitForVisibilityOfElement(driver, 20, referenceNumberElement);
        keywords.input(referenceNumberElement, appBasedUtils.getStringData(data, "RefNumber"), "Reference number");
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintDeliveryNoteSummary");
    }

    /****************************************
     * #Description : This is a common function to PrintDealerNoteSummaryOfMITypeInMaterialInstruction Parameters: excel data
     ******************************************/
    public void PrintDealerNoteSummaryOfMITypeInMaterialInstruction(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintDeliveryNoteSummary" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on "Print dealer note summary" button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintDeliveryNoteSummary");
    }

    /****************************************
     * #Description : This is a common function to PrintMIOfHoldMITypeUsingRefNumberInMaterialInstruction Parameters: excel data
     ******************************************/
    public void PrintMIOfHoldMITypeUsingRefNumberInMaterialInstruction(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "PrintMI" };
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.waitForVisibilityOfElement(driver, 20, miPrintStatusListboxElement);
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(data, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        // Checks check first column in the GRID and clicks on PrintMI button and validates the result
        printMaterialInstructionTabDocumentsInSearchGRID(data, columnHeader, "checkBoxForPrintMI");
    }

    /****************************************
     * #Description :Verifies the Claim job of particular MI Type is printed or not :28-03-17 #Input Parameters: excel data
     ******************************************/
    public void VerifyClaimJobAfterMaterialInstructionAction(HashMap<String, Object> data) {
        keywords.waitForVisibilityOfElement(driver, 20, referenceNumberElement);
        keywords.input(referenceNumberElement,
                       appBasedUtils.removeJobNumberFromReferenceNumber(appBasedUtils.getStringData(applicationData, "ReferenceNumber")), "Reference number");
        keywords.waitTime(1000);
        expandClaimJobArea();
        keywords.waitForVisibilityOfElement(driver, 20, searchTypeListboxElement);
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.waitForVisibilityOfElement(driver, 20, miTypeListboxElement);
        keywords.selectVisibleText(miTypeListboxElement, appBasedUtils.getStringData(data, "MIType"));
        keywords.selectVisibleText(miPrintStatusListboxElement, appBasedUtils.getStringData(applicationData, "MIPrintStatus"));
        keywords.clickOnButton(driver, searchButtonElement);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 20, referenceNumberInSearchResult, errorMsgElement, validationMessageClose);
        keywords.waitForVisibilityOfElement(driver, 20, referenceNumberInSearchResult);
        keywords.elementIsDisplayed(referenceNumberInSearchResult, "Reference number in the search result");
        Assert.assertTrue(keywords.elementIsDisplayed(referenceNumberInSearchResult, "Reference number in the search result"),
                          "Reference number element is not displayed in the search result");
    }

    /****************************************
     * #Description :Removes material instruction from the shipment, place it in select for printing #Input Parameters: excel data
     ******************************************/
    public void removeMIFromShipment() {
        String[] columnHeader = { "RemoveFromShipment" };
        printMaterialInstructionTabDocumentsInSearchGRID(applicationData, columnHeader, "checkBoxForRemoveFromShipment");
    }

    /****************************************
     * #Description : This is a common function to listClaimJobsGridResultValidation headers, list of column headers to verify the search result in the grid
     ******************************************/
    /* table for Each tab */

    public void listClaimJobsGridResultValidation(HashMap<String, Object> data, String columnheader[], String columnsToBeValidated) {
        // first row column count start with Zero and iterate the columnIndexInSearchGrid by one till the lst row
        int columnIndexInSearchGrid = 0;
        try {
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon))) {
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 10, gridColumnName, errorMsgElement, validationMessageClose);
                for (String hexaStrToXpath : columnsToBeValidated.split("\\s")) {
                    if ("VTCCentralTMADecisionImporter_3 VTCCentralTMADecisionDealer_4 VTCDealerNotFinishedClaimType_6 VTCDealerNotFinishedRepairOrderNum_11 VTCDealerNotEvalatedStatus_6 VTCCentralMyNotEvaluatedImporter_3 VTCCentralMyNotEvaluatedDealer_4 VTCCentralMyNotEvaluatdClaimType_6 VTCDealerNotFinishedImporter_3 VTCDealerNotFinishedDealer_4".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.textValidationGrid(driver, (appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid])), XpathFirstPart
                                        + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", testCaseAssertion);
                        columnIndexInSearchGrid++;
                    } else if ("VTCCentralTMADecisionTMADecision_11 VTCDealerMaterialInstructionMIType_9 VTCDealerAppealAppealStatus_10 VTCCentralNotEvaluatedTypeOfConcern_2".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.dropdownValueValidationInGrid(appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid]), XpathFirstPart
                                + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", testCaseAssertion, driver);
                        columnIndexInSearchGrid++;
                    } else if ("VTCDealerMaterialInstructionMIDate_10 VTCCentralNotEvaluatedRegistrationDate_7 5I".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.validateDate(driver,
                                                   XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]",
                                                   fromDate, toDate, testCaseAssertion);
                        columnIndexInSearchGrid++;
                    } else if ("VTCDealerNotFinishedChassisID_8".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.textValidationGrid(driver,
                                                         appBasedUtils.getStringData(data, "ChassisSeries").toString() + " - "
                                                                + appBasedUtils.getStringData(data, "ChassisNumber"),
                                                         XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length())
                                                                 + "]", testCaseAssertion);
                        columnIndexInSearchGrid++;
                    } else if ("VTCDealerAppealReferenceNum_5".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.verifySearchResultInGridBtwRangeForIntergerValue(driver,
                                                                                       appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid]),
                                                                                       appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid]),
                                                                                       XpathFirstPart
                                                                                               + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1,
                                                                                                                          hexaStrToXpath.length())
                                                                                               + "]",
                                                                                       testCaseAssertion);
                        columnIndexInSearchGrid++;
                    } else if ("VTCDealerNotFinishedStatus_1 VTCCentralTMADecisionStatus_1 VTCCentralMyNotEvaluatetdStatus_1 VTCCentralNotEvaluatetdStatus_1".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.claimStatusIconValidationInGrid(appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid]),
                                                                      XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1,
                                                                                                                hexaStrToXpath.length())
                                                                              + "]/descendant::span",
                                                                      testCaseAssertion, driver);
                        //// div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[4]/descendant::span
                        columnIndexInSearchGrid++;
                    } else if ("manageClaimJob".contains(hexaStrToXpath)) {
                        keywords.APP_LOGS.info("Search result is displayed.");
                        columnIndexInSearchGrid++;
                    } else {
                        keywords.APP_LOGS.info("Value of char didnot match " + hexaStrToXpath);
                        Assert.fail();
                    }
                }
            }
            ExtentTestManager.getTest().log(Status.PASS,"Verified the search result");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        testCaseAssertion.assertAll();
    }

    /****************************************
     * #Description : This is a common function to printMaterialInstructionTabDocumentsInSearchGRID
     ******************************************/
    /* table for Each tab */

    public void printMaterialInstructionTabDocumentsInSearchGRID(HashMap<String, Object> data, String columnheader[], String columnsToBeValidated) {
        try {
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon))) {
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 8, gridPageCount, errorMsgElement, validationMessageClose);
                int columnIndexInSearchGrid = 0;
                int searchResultRowIndex = 2;
                int noOfitemsInSearchResult = (Integer.parseInt(appBasedUtils.extractStringFromOtherString(gridPageCount.getText(), " - ", " / "))) + 1;
                outerloop: while (searchResultRowIndex <= noOfitemsInSearchResult) {
                    try {
                        for (String hexaStrToXpath : columnsToBeValidated.split("\\s")) {
                            switch (hexaStrToXpath) {
                            case "checkBoxForPrintMI":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 6, printMIListButtonElement, "PrintMI list button is not enabled");
                                referenceNumberHolder = driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]"))
                                                              .getText();
                                applicationData.put("ReferenceNumber", referenceNumberHolder);
                                data.put("ReferenceNumber", referenceNumberHolder);
                                data.put("DealerNumber",
                                         driver.findElement(By.xpath(dealerNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                data.put("MIType", driver.findElement(By.xpath(miTypeInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                printMaterialInstructionDocument(data, printMIListButtonElement, testCaseAssertion);
                                applicationData.put("MIPrintStatus", "Printed");
                                break;
                            case "checkBoxForPrintDeliveryNoteSummary":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 6, printDeliveryNoteSummaryButtonElement,
                                                                     "Print delivery note summary button is not enabled");
                                referenceNumberHolder = driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]"))
                                                              .getText();
                                applicationData.put("ReferenceNumber", referenceNumberHolder);
                                data.put("ReferenceNumber", referenceNumberHolder);
                                data.put("DealerNumber",
                                         driver.findElement(By.xpath(dealerNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                data.put("MIType", driver.findElement(By.xpath(miTypeInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                printDealerNoteSummaryDocument(data, printDeliveryNoteSummaryButtonElement, testCaseAssertion);
                                break;

                            case "PrintDealerShippingDocumentNoCarrierNoTrackingID":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 6, printDealerShippingDocumentButtonElement,
                                                                     "print Dealer shipping document button is not enabled");
                                referenceNumberHolder = driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]"))
                                                              .getText();
                                applicationData.put("ReferenceNumber", referenceNumberHolder);
                                data.put("ReferenceNumber", referenceNumberHolder);
                                data.put("DealerNumber",
                                         driver.findElement(By.xpath(dealerNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                data.put("MIType", driver.findElement(By.xpath(miTypeInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                driver.switchTo().defaultContent();
                                keywords.clickOnButton(driver, printDealerShippingDocumentButtonElement);
                                keywords.waitForVisibilityOfElement(driver, 20, okButtonElementPrintDealershippingDocument);
                                keywords.waitForElementToBeClickable(driver, 20, printDealerShippingDocumentButtonElement,
                                                                     " Ok print Dealer shipping document button is not enabled");
                                printDealerShippingDocument(data, okButtonElementPrintDealershippingDocument, testCaseAssertion);
                                applicationData.put("MIPrintStatus", "Printed");
                                break;

                            case "PrintDealerShippingDocumentWithCarrierAndTrackingID":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 6, printDealerShippingDocumentButtonElement,
                                                                     "print Dealer shipping document button is not enabled");
                                referenceNumberHolder = driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]"))
                                                              .getText();
                                applicationData.put("ReferenceNumber", referenceNumberHolder);
                                data.put("ReferenceNumber", referenceNumberHolder);
                                data.put("DealerNumber",
                                         driver.findElement(By.xpath(dealerNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                data.put("MIType", driver.findElement(By.xpath(miTypeInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                driver.switchTo().defaultContent();
                                keywords.clickOnButton(driver, printDealerShippingDocumentButtonElement);
                                keywords.waitForElementToBeClickable(driver, 20, okButtonElementPrintDealershippingDocument,
                                                                     " OK button in print Dealer shipping document pop is not enabled");
                                keywords.selectVisibleText(carrierListboxElementPrintDealerShippingDocument, appBasedUtils.getStringData(data, "CarrierType"));
                                keywords.waitForVisibilityOfElement(driver, 20, trackingIDElementPrintDealerShippingDocument);
                                keywords.input(trackingIDElementPrintDealerShippingDocument, appBasedUtils.getStringData(data, "TrackingID"), "Tracking id");
                                printDealerShippingDocument(data, okButtonElementPrintDealershippingDocument, testCaseAssertion);
                                applicationData.put("MIPrintStatus", "Printed");
                                break;

                            case "checkBoxForRemoveFromShipment":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 20, removeFromShipmentButtonElement, "remove from shipment button is not enabled");
                                applicationData.put("ReferenceNumber",
                                                    driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                keywords.clickOnButton(driver, removeFromShipmentButtonElement);
                                applicationData.put("MIPrintStatus", "Selected for print");
                                break;

                            case "PrintDealerShippingDocumentMaterialInstructionTab":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 6, printDealerShippingDocumentButtonElement,
                                                                     "print Dealer shipping document button is not enabled");
                                referenceNumberHolder = driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]"))
                                                              .getText();
                                applicationData.put("ReferenceNumber", referenceNumberHolder);
                                data.put("ReferenceNumber", referenceNumberHolder);
                                data.put("DealerNumber",
                                         driver.findElement(By.xpath(dealerNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                data.put("MIType", driver.findElement(By.xpath(miTypeInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                driver.switchTo().defaultContent();
                                if (!appBasedUtils.getStringData(data, "MIType").toString().equals("Send"))
                                    validatePrintMIAlertMessages(applicationData, printDealerShippingDocumentButtonElement);
                                else {
                                    keywords.clickOnButton(driver, printDealerShippingDocumentButtonElement);
                                    keywords.waitForElementToBeClickable(driver, 20, okButtonElementPrintDealershippingDocument, "");
                                    printDealerShippingDocument(data, okButtonElementPrintDealershippingDocument, testCaseAssertion);
                                    applicationData.put("MIPrintStatus", "Printed");
                                }
                                break;

                            case "PrintHoldScrapListOfScrapMITypeInMaterialInstructionTab":
                                keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" button functionality.");
                                keywords.setCheckboxIfNotSelected(driver.findElement(By.xpath(checkBoxXpathSearchResult + searchResultRowIndex + "]")));
                                keywords.waitForElementToBeClickable(driver, 6, printHoldScrapListButtonElement,
                                                                     "print Dealer shipping document button is not enabled");
                                referenceNumberHolder = driver.findElement(By.xpath(referenceNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]"))
                                                              .getText();
                                applicationData.put("ReferenceNumber", referenceNumberHolder);
                                data.put("ReferenceNumber", referenceNumberHolder);
                                data.put("DealerNumber",
                                         driver.findElement(By.xpath(dealerNumberInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                data.put("MIType", driver.findElement(By.xpath(miTypeInGRIDFirstPart + (searchResultRowIndex - 1) + "]")).getText());
                                driver.switchTo().defaultContent();
                                printHoldScrapListDocument(data, printHoldScrapListButtonElement, testCaseAssertion);
                                applicationData.put("MIPrintStatus", "Printed");
                                break;

                            default:
                                keywords.APP_LOGS.info("Value of char didnot match" + hexaStrToXpath);
                                Assert.fail();
                            }
                            break outerloop;
                        }
                        ExtentTestManager.getTest().log(Status.PASS,"Verified the search result");
                    } catch (Exception e) {
                        String errorMessageText = keywords.getTextValue(errorMsgElement);
                        if (errorMessageText.contains("This claim job is being credited and can therefore not to be printed at this time")) {
                            validationMessageCloseElements.click();
                            searchResultRowIndex++;
                        }
                        if (searchResultRowIndex == noOfitemsInSearchResult + 1) {
                            Assert.fail("PrintMI is not clickable for all claim jobs\n" + errorMessageText);
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (keywords.elementIsDisplayed(errorMsgElement, "Error message pop up")) {
                String val = keywords.getTextValue(errorMsgElement);
                ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsgElement));
                ExtentTestManager.getTest().log(Status.INFO,"Verified the search result");
                keywords.clickOnButton(driver, validationMessageClose);
                Assert.fail(val);
            }
        }
        testCaseAssertion.assertAll();
    }

    // Nithya added the code
    /****************************************
     * #Description : This test case is to select a single claim job from list and verify that next is disabled Also verfiy that selected claim is opened in
     * claim job page
     ******************************************/
    public void selectSingleClaimJobAndValidatedTheJobNumber() {
        String expectedClaimRefNo[] = selectClaimJobFromList();
        keywords.clickOnButton(driver, openSelectedClaimJobsButtonElement);
        // verify whether the next claim job button enabled for disabled for single claim selection
        if (!(keywords.elementIsDisplayed(nextClaimJob, "NextClaimJob verify"))) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(claimJobReferenceNo));
            Assert.assertEquals(keywords.getTextValue(claimJobReferenceNo), expectedClaimRefNo[0].trim());
            ExtentTestManager.getTest().log(Status.PASS, "Next button disabled upon opening a claim job");
        } else {
            ExtentTestManager.getTest().log(Status.FAIL, "Next button is enabled upon opening a claim job");
            Assert.fail("Next is enabled");
        }
    }

    /****************************************
     * #Description : This test case is to select a multiple claim job from list and verify that next is enabled Also verfiy that selected claim is opened in
     * claim job page :26-05-17 Nithya
     ******************************************/
    public void selectMultipleClaimJob() {
        keywords.clickOnButton(driver, notFinishedTabElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        List<String> ref = new ArrayList<String>();
        int i = 1;
        int count = 1;
        String claimRefNo = null;

        List<WebElement> selectCheckBox = driver.findElements(By.xpath("//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope']"));
        try {
            // select first 10 records and click on next button for pagination
            // then select next 10 records
            for (i = 1; i < selectCheckBox.size(); i++) {

                if ((count == selectCheckBox.size()) && (keywords.elementIsEnabled(gridForwardButton))) {
                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    jse.executeScript("window.scrollBy(0,250)", "");
                    keywords.clickOnButton(driver, gridForwardButton);
                    keywords.waitTime(2000);
                    i = 1;
                    selectCheckBox.get(i).click();
                    claimRefNo = "//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[" + i + "]//a";
                    String val[] = driver.findElement(By.xpath(claimRefNo)).getText().split("-");
                    ref.add(val[0].trim());
                    // ref.add(driver.findElement(By.xpath(claimRefNo)).getText());
                    // break the loop after the claim has selected the 13 records
                    if (count >= 13) {
                        break;
                    }

                } else if (selectCheckBox.get(i).isEnabled()) {
                    claimRefNo = "//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[" + i + "]//a";
                    selectCheckBox.get(i).click();

                    String[] val = driver.findElement(By.xpath(claimRefNo)).getText().split("-");
                    ref.add((val[0]).trim());
                    if (count >= 13) {
                        break;
                    }

                }
                count++;
            }
            ExtentTestManager.getTest().log(Status.INFO, "Selected multiple claim jobs");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-250)", "");
        keywords.clickOnButton(driver, openSelectedClaimJobsButtonElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        for (int j = 0; j < (ref.size()); j++) {
            String val = ref.get(j);
            // test execution is pending since pagination and grid forward functionality is failing,
            // need to execute for different probabilities like claim with multiple claim job
            ((JavascriptExecutor) driver).executeScript("scroll(0,400)");
            keywords.waitTime(3000);
            Assert.assertEquals(keywords.getTextValue(claimJobReferenceNo), val);
            if (j == ref.size() - 1) {
                if (!(keywords.elementIsDisplayed(nextClaimJob, "Next button ic claim job"))) {
                    ExtentTestManager.getTest().log(Status.PASS, "Next button is not enabled for last claim job");
                } else {
                    ExtentTestManager.getTest().log(Status.FAIL, "Next button is enabled for last claim job");
                }
            } else {
                ((JavascriptExecutor) driver).executeScript("scroll(0,-250)");
                keywords.clickOnButton(driver, nextClaimJob);
                keywords.waitTime(2000);
            }

        }
        ExtentTestManager.getTest().log(Status.PASS, "Selected claim reference number match with claim job reference number");
    }

    /*********************************************
     * List claim job #Description : method to openClaimJobWithSpecificClaimStatus 28-10-2016 Tester 01-August-2016
     ********************************************/
    public void openClaimJobWithSpecificClaimStatus(HashMap<String, Object> data) {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(referenceNumberElement));
        keywords.input(referenceNumberElement, appBasedUtils.getStringData(data, "RefNumber"), "claimReferenceNumber");
        keywords.selectVisibleText(searchTypeListboxElement, appBasedUtils.getStringData(data, "SearchType"));
        keywords.clickOnButton(driver, searchButtonElement);
        keywords.waitTime(3000);
        collapseSearchAreaButtonElement.click();
        keywords.clickOnButton(driver, gridClaimRefNo);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        if ((appBasedUtils.getStringData(data, "RefNumber").toString()).equalsIgnoreCase(keywords.getTextValue(claimJobReferenceNo)));
    }

    /*********************************************
     * printMaterialInstructionDocumnet #Description : Validates attachments, downloads documents, validate the text in the document. #Author : Raju :
     * 28-10-2016 Tester 01-August-2016
     ********************************************/
    public void printMaterialInstructionDocument(HashMap<String, Object> data, WebElement buttonToBeClicked, SoftAssert softassert) {
        try {
            String sPathSep = System.getProperty("file.separator");
            keywords.waitTime1(2);
            File downloadLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
            File downloadedDocumentName = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep
                    + appBasedUtils.getStringData(data, "DownloadedFileName"));
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
            long sizeOfFiles = pdfReadAndValidate.downloadLocationFolderFilesSize();
            data.put("SizeOfFolderWithFilesCount", sizeOfFiles);
            keywords.clickOnButton(driver, buttonToBeClicked);
            keywords.isErrorMessageVisible(driver, 6, errorMsgElement, validationMessageClose);
            pdfReadAndValidate.downloadDocuments(baseclass.getBrowserType(), driver, downloadLocation);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.waitTime1(8);
            pdfReadAndValidate.readPDFFile(new File(downloadLocation + sPathSep + appBasedUtils.getStringData(data, "DownloadedFileName")), data);
            pdfReadAndValidate.validateMaterialInstructionPDFdata(data, downloadedDocumentName, appBasedUtils.getStringData(data, "DownloadedFileName"),
                                                                  testCaseAssertion);
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
        } catch (Exception e) {
            Assert.fail();
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error(e);
        }
    }

    /*********************************************
     * printMaterialInstructionDocumnet #Description : Validates attachments, downloads documents, validate the text in the document. #Author : Raju :
     * 28-10-2016 Tester 01-August-2016
     ********************************************/
    public void printDealerNoteSummaryDocument(HashMap<String, Object> data, WebElement buttonToBeClicked, SoftAssert softassert) {
        try {
            String sPathSep = System.getProperty("file.separator");
            keywords.waitTime1(2);
            File downloadLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
            File downloadedDocumentName = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep
                    + appBasedUtils.getStringData(data, "DownloadedFileName"));
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
            keywords.clickOnButton(driver, buttonToBeClicked);
            keywords.isErrorMessageVisible(driver, 6, errorMsgElement, validationMessageClose);
            pdfReadAndValidate.downloadDocuments(baseclass.getBrowserType(), driver, downloadLocation);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 7, loadingIcon);
            keywords.waitTime1(8);
            pdfReadAndValidate.readPDFFile(new File(downloadLocation + sPathSep + appBasedUtils.getStringData(data, "DownloadedFileName")), data);
            pdfReadAndValidate.validateMaterialInstructionPDFdata(data, downloadedDocumentName, appBasedUtils.getStringData(data, "DownloadedFileName"),
                                                                  testCaseAssertion);
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error(e);
        }
    }

    /*********************************************
     * #Description : Validates attachments, downloads documents, validate the text in the document.
     ********************************************/
    public void printDealerShippingDocument(HashMap<String, Object> data, WebElement buttonToBeClicked, SoftAssert softassert) {
        try {
            String sPathSep = System.getProperty("file.separator");
            keywords.waitTime1(2);
            File downloadLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
            File downloadedDocumentName = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep
                    + appBasedUtils.getStringData(data, "DownloadedFileName"));
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
            keywords.clickOnButton(driver, buttonToBeClicked);
            keywords.isErrorMessageVisible(driver, 6, errorMsgElement, validationMessageClose);
            pdfReadAndValidate.downloadDocuments(baseclass.getBrowserType(), driver, downloadLocation);
            keywords.waitTime1(8);
            pdfReadAndValidate.readPDFFile(new File(downloadLocation + sPathSep + appBasedUtils.getStringData(data, "DownloadedFileName")), data);
            applicationData.put("DownloadedFileName", appBasedUtils.getStringData(data, "DownloadedFileName"));
            applicationData.put("CarrierType", appBasedUtils.getStringData(data, "CarrierType"));
            pdfReadAndValidate.validateMaterialInstructionPDFdata(data, downloadedDocumentName, appBasedUtils.getStringData(data, "DownloadedFileName"),
                                                                  testCaseAssertion);
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error(e);
        }
    }

    /*********************************************
     * printHoldScrapListDocument #Description : Validates attachments, downloads documents, validate the text in the document.
     *
     ********************************************/
    public void printHoldScrapListDocument(HashMap<String, Object> data, WebElement buttonToBeClicked, SoftAssert softassert) {
        try {
            String sPathSep = System.getProperty("file.separator");
            keywords.waitTime1(2);
            File downloadLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
            File downloadedDocumentName = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep
                    + appBasedUtils.getStringData(data, "DownloadedFileName"));
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
            pdfReadAndValidate.downloadDocuments(baseclass.getBrowserType(), buttonToBeClicked, driver, downloadLocation);
            keywords.waitTime1(8);
            pdfReadAndValidate.readPDFFile(new File(downloadLocation + sPathSep + appBasedUtils.getStringData(data, "DownloadedFileName")), data);
            pdfReadAndValidate.validateMaterialInstructionPDFdata(data, downloadedDocumentName, appBasedUtils.getStringData(data, "DownloadedFileName"),
                                                                  testCaseAssertion);
            pdfReadAndValidate.deleteFileFromFolder(downloadedDocumentName);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error(e);
        }
    }

    /*********************************************
     * #Description : delete first record in list and validate selected claim is deleted
     *********************************************/
    public void deleteClaimJobInNotFinishedTab() {
        keywords.clickOnButton(driver, searchButtonElement);
        keywords.waitForVisibilityOfElement(driver, 20, referenceNumberInSearchResult);
        keywords.clickOnButton(driver, notFinishedTabElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        String refAndJobNo = keywords.getTextValue(referenceNumberInSearchResult);
        String referenceNumber = appBasedUtils.removeJobNumberFromReferenceNumber(refAndJobNo);
        keywords.clickOnButton(driver, claimJobDeleteInNotFinishedTab);
        keywords.handleModalDailogues(driver);
        Assert.assertEquals("You are about to delete the last claim job for this repair header. The deletion will also remove the repairing header. Are you sure you want to delete this item?",
                            keywords.getTextValue(claimJobDeleteModalMessage));
        keywords.clickOnButton(driver, claimJobDeleteModalYesButton);
        expandStandardSearchArea();
        keywords.input(referenceNumberElement, referenceNumber, "Reference number");
        expandClaimJobArea();
        keywords.clickOnButton(driver, searchButtonElement);
        try {
            // Validates either no items to be displayed or selected record ref and job number doesnt exist in search result
            if ((keywords.waitForVisibilityOfElement(driver, 5, searchResultNoItemToDisplay))
                    || (keywords.getTextValue(referenceNumberInSearchResult) != refAndJobNo))
                ExtentTestManager.getTest().log(Status.INFO, "Selected claim reference " + referenceNumber + " is deleted");
            else
                ExtentTestManager.getTest().log(Status.ERROR, "Selected claim reference  " + referenceNumber + " is not deleted");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error(e);
        }
    }

    /*********************************************
     * #Description : Select the clain jobs from the list
     *********************************************/
    public String[] selectClaimJobFromList() {
        int i = 1;
        String claimRefNo = null;
        String[] expectedClaimRefNo = null;
        List<WebElement> selectCheckBox = driver.findElements(By.xpath("//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope']"));
        try {
            // check whether the first check boz is enabled, then select the item to open claim job
            while (i < selectCheckBox.size()) {
                if (selectCheckBox.get(i).isEnabled()) {
                    claimRefNo = driver.findElement(By.xpath("(//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[" + i + "]//a)[1]")).getText();
                    expectedClaimRefNo = claimRefNo.split("-");
                    selectCheckBox.get(i).click();
                    break;
                } else
                    i++;
            }
        } catch (Exception e) {
            Assert.fail("No items displayed in grid to open a claim job");
        }
        return expectedClaimRefNo;
    }

    /*********************************************
     * #Description : verify the claim status in the tab Not evaluted tab
     *********************************************/
    public void verifyTheClaimStatusInTheTabs(HashMap<String, Object> data) {
        testCaseAssertion = new SoftAssert();
        String[] columnHeader = { "Status" };
        keywords.clickOnButton(driver, notEvaluatedTabElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
        appBasedUtils.reSizeTheGridSizeInSearchResult(gridItemsToDisplay);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCCentralNotEvaluatetdStatus_1"); // scroll and extract all 50 items
        selectSingleClaimJobAndValidatedTheJobNumber();
        appBasedUtils.asserVerification(claimJobInformation.getText(), "Claim job information", testCaseAssertion);
        keywords.clickOnButton(driver, clickOnCloseButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
        appBasedUtils.asserVerification("List claim jobs", keywords.getTextValue(listClaimJobPageTitleElement), testCaseAssertion);
        expandStandardSearchArea();
    }

    /*********************************************
     * #Description : Click on not finished tab
     *********************************************/
    public void clickOnNotFinishedTab() {
        keywords.clickOnButton(driver, notFinishedTabElement);
    }

    /*********************************************
     * #Description : Search with searchWithImporterDealerAndClaimType
     *********************************************/
    public void searchWithImporterDealerAndClaimType(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "ExpectedClaimType" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(BaseClass.userContextForImporter, importerTextBoxElement, importerGreyedOutValue,
                                                                        importerDropDownElement, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(BaseClass.userContextForDealer, dealerTextBoxElement, dealerGreyedOutValue,
                                                                        dealerDropDownElement, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.clickOnButton(driver, expandClaimJobAreaButtonElement);
        keywords.selectVisibleText(claimTypeListboxElement, appBasedUtils.getStringData(data, "ClaimType"));
        keywords.clickOnButton(driver, searchButtonElement);
        listClaimJobsGridResultValidation(data, columnHeader, "VTCDealerNotFinishedImporter_3 VTCDealerNotFinishedDealer_4 VTCDealerNotFinishedClaimType_6");
    }

    /*********************************************
     * #Description : Select type of concern
     *********************************************/
    public void selectTypeOfConcern(HashMap<String, Object> data) {
        keywords.waitTime(1000);
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "TypeOfConcern"))) {
            if ((appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(data, "SelectTypeOfConcern"));
        }
    }
}
