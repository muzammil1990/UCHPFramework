package com.volvo.in.page.claim;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class AdvanceSearch {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert softAssert;
    String fromDate;
    String toDate;
    String fromTextValue;
    String toTextValue;
    public Logger APP_LOGS = Logger.getLogger(AdvanceSearch.class);
    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claim;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Advanced search ')]")
    WebElement advanceSearch;

    @FindBy(how = How.XPATH, using = "//advanced-search/descendant::h4[text()='Advanced search']")
    WebElement advanceSearchTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingImporter']")
    WebElement importerTextBoxEelement;

    @FindBy(how = How.ID, using = "repairing_Importer")
    WebElement importerDropDownEelement;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingDealer']")
    WebElement dealerTextBoxElements;

    @FindBy(how = How.ID, using = "repairing_Dealer")
    WebElement dealerDropDownElements;

    @FindBy(how = How.XPATH, using = "//input[@id='refNoFrom']")
    WebElement refNoFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='refNoTo']")
    WebElement refNoTo;

    @FindBy(how = How.ID, using = "claimJobStatus")
    WebElement claimJobStatus;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement search;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Status')]]]//input[@type='checkbox']")
    WebElement statusCheckBox;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Search type')]]]//input[@type='checkbox']")
    WebElement searchTypeCheckBox;

    @FindBy(how = How.ID, using = "searchType")
    WebElement searchTypeListbox;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'TMA status')]]]//input[@type='checkbox']")
    WebElement TMAStatusCheckBox;

    @FindBy(how = How.ID, using = "tmaStatus")
    WebElement TMAStatusListbox;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input")
    WebElement repairDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement repairDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input")
    WebElement registrationDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement registrationDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']/input")
    WebElement repairDateTo;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']/input/../descendant::span[@class='glyphicon glyphicon-calendar']")
    WebElement repairDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input")
    WebElement registrationDateTo;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement registrationDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Repair date')]]]//input[@type='checkbox']")
    WebElement repairDateCheckBox;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Registration date')]]]//input[@type='checkbox']")
    WebElement registrationDateCheckBox;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Credited date')]]]//input[@type='checkbox']")
    WebElement creditDateCheckBox;

    @FindBy(how = How.XPATH, using = "//div[@id='creditedDateFrom']/input")
    WebElement creditedDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='creditedDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement creditedDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='creditedDateTo']/input")
    WebElement creditedDateTo;

    @FindBy(how = How.XPATH, using = "//div[@id='creditedDateTo']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement creditedDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Repair order no.')]]]//input[@type='checkbox']")
    WebElement repairOrderNoCheckBox;

    @FindBy(how = How.ID, using = "repairOrderNoFrom")
    WebElement repairOrderNoFrom;

    @FindBy(how = How.ID, using = "repairOrderNoTo")
    WebElement repairOrderNoTo;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp'])[2]")
    WebElement vehicleInformationLabel;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp'])[1]")
    WebElement claimJobInformationLabel;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp header_bgColor alignMarginTop'])[1]")
    WebElement remarksLabel;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp header_bgColor alignMarginTop'])[2]")
    WebElement decisionLabel;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp header_bgColor alignMarginTop'])[3]")
    WebElement costInformationLabel;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp header_bgColor alignMarginTop'])[4]")
    WebElement materiaInformationLabel;

    @FindBy(how = How.XPATH, using = "//select[@id='claimJobStatus']")
    WebElement dropdownStatus;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfClaim']")
    WebElement dropdownTypeOfClaim;

    @FindBy(how = How.XPATH, using = "//a[text()='New search']")
    WebElement newSearch;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement TypeofconcernB;

    @FindBy(how = How.XPATH, using = "//input[@id='contractNo']")
    WebElement Contractno;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Contract')]")
    WebElement typeofconcern;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Contract no.')]]]//input[@type='checkbox']")
    WebElement ContractnoCheckBox;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//b[text()='No items to display']")
    WebElement searchResultNoItemToDisplay;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[4])[1]")
    WebElement referenceNumberInSearchResult;

    @FindBy(how = How.XPATH, using = "//a[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)']")
    WebElement openClaimJobOnClickRefNo;

    @FindBy(how = How.XPATH, using = "//input[@id='causalPartPrefix']")
    WebElement causalPartPrefix;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.XPATH, using = "//div[div[label[contains(text(),'Type of concern')]]]//input[@type='checkbox']")
    WebElement typeOfConcernCheckBox;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../span")
    WebElement typeOfConcernLabelValue;

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    @FindBy(how = How.XPATH, using = "(//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope'])[2]")
    WebElement firstRecordInSearchResultGrid;

    @FindBy(how = How.ID, using = "downloadReportFile")
    WebElement openSelectedRecordBtn;
    
    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'material_total_debit_')]") })
    List<WebElement> debitCodeOfMaterial;

    int numberOfPreviousDays = 185;

    public AdvanceSearch(WebDriver driver) {  //here driver comes from test case class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "Advance search" sub menu #Input Parameters:
     ******************************************/
    public void navigateToAdvanceSearch() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 20, claim);
        keywords.performMenuAction(driver, claim, advanceSearch, advanceSearchTitle);
    }

    public void VerifySearchCriteriaGroups() {
        try {
            SoftAssert sa = new SoftAssert();
            APP_LOGS.info("Verifying search fields in advanced search page");
            if (companyNameElement.getText().equalsIgnoreCase("VOLVO CONSTRUCTION EQUIPMENT")) {
                APP_LOGS.info("Verifying \"Machine information\" Field...");
                appBasedUtils.asserVerification("Machine information", vehicleInformationLabel.getText(), sa);
            } else if (companyNameElement.getText().equalsIgnoreCase("VOLVO PENTA CORPORATION")) {
                APP_LOGS.info("Verifying \"Driveline information\" Field...");
                appBasedUtils.asserVerification("Driveline information", vehicleInformationLabel.getText(), sa);
            } else {
                APP_LOGS.info("Verifying \"Claim job information\" Field...");
                appBasedUtils.asserVerification("Claim job information", claimJobInformationLabel.getText(), sa);
                APP_LOGS.info("Verifying \"Remarks\" Field...");
                appBasedUtils.asserVerification("Remarks", remarksLabel.getText(), sa);
                APP_LOGS.info("Verifying \"Decision\" Field...");
                appBasedUtils.asserVerification("Decision", decisionLabel.getText(), sa);
                APP_LOGS.info("Verifying \"Cost information\" Field...");
                appBasedUtils.asserVerification("Cost information", costInformationLabel.getText(), sa);
                APP_LOGS.info("Verifying \"Material information\" Field...");
                appBasedUtils.asserVerification("Material information", materiaInformationLabel.getText(), sa);
            }
            sa.assertAll();
        } catch (Exception e) {
            if (keywords.elementIsDisplayed(errorMsg, "Error message pop up")) {
                String alertTextMessage = keywords.getTextValue(errorMsg);
                Assert.fail("Error in validation" + alertTextMessage);
            } else {
                Assert.fail("Fail!!! Search result not found '");
            }
            APP_LOGS.error("failed!", e);
        }
    }

    /********************************
     * #Description : Method to list and verify Dropdown values. #Input Parameters: noOfColumnsToValidate: Hexadecimal character used as index in the Xpath
     * dropdown: dropdown webelement expectedDropDownValues: Expected test data.
     */
    public void verifyListOfValuesInStatusDropDown(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        APP_LOGS.info("Verifing the brand names in brand list");
        String statusValue = appBasedUtils.getStringData(data, "Status");
        String[] statusName = statusValue.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownStatus, statusName, softAssert);

    }

    /********************************
     * #Description : Method to list and verify Dropdown values in search type dropdown #Date of creation : 06-10-2016
     */
    public void verifyListOfValuesInSearchTypeDropDown(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        APP_LOGS.info("Verifing the values in the drop down field");
        String searchTypeDropDownValue = appBasedUtils.getStringData(data, "SearchType");
        String[] searchTypeDropDownNames = searchTypeDropDownValue.split(",");
        keywords.verifyListOfItemsFromDropDown(searchTypeListbox, searchTypeDropDownNames, softAssert);
    }

    /********************************
     * #Description : Method to list and verify Dropdown values in TMA status dropdown value
     */
    public void verifyListOfValuesInTMAStatusDropDown(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        APP_LOGS.info("Verifing the values in the drop down field");
        String TMAStatusDropdownValue = appBasedUtils.getStringData(data, "TMAStatus");
        String[] TMAStatusDropdownName = TMAStatusDropdownValue.split(",");
        keywords.verifyListOfItemsFromDropDown(TMAStatusListbox, TMAStatusDropdownName, softAssert);
    }

    /********************************
     * #Description : Method to list and verify Dropdown in type of claim dropdown value
     */
    public void verifyListOfValuesInTypeOfClaimDropDown(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        APP_LOGS.info("Verifing the values in the drop down field");
        String claimDropdownValue = appBasedUtils.getStringData(data, "ExpectedBrand");
        String[] claimDropdownName = claimDropdownValue.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownTypeOfClaim, claimDropdownName, softAssert);
    }

    /****************************************
     * #Description : searchWithImporterDealerRefNoAndSearchType
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporterDealerRefNoAndSearchType(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "RefNoFrom", "SearchType" };
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElements, dealerDropDownElements,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(refNoFrom, appBasedUtils.getStringData(data, "RefNoFrom"), "RefNoFrom");
        keywords.input(refNoTo, appBasedUtils.getStringData(data, "RefNoTo"), "RefNoTo");
        keywords.scrollPage(driver, 250);
        keywords.waitForVisibilityOfElement(driver, 10, searchTypeCheckBox);
        keywords.clickOnButton(driver, searchTypeCheckBox);
        keywords.selectVisibleText(searchTypeListbox, appBasedUtils.getStringData(data, "SearchType"));
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 ReferenceNumber_4 SearchType_5");

    }

    /****************************************
     * #Description :searchWithImpDealerImpRefNoAndStatus
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImpDealerImpRefNoAndStatus(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "RefNoFrom", "Status" };
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElements, dealerDropDownElements,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(refNoFrom, appBasedUtils.getStringData(data, "RefNoFrom"), "RefNoFrom");
        keywords.input(refNoTo, appBasedUtils.getStringData(data, "RefNoTo"), "RefNoTo");
        keywords.scrollPage(driver, 250);
        keywords.waitForVisibilityOfElement(driver, 10, statusCheckBox);
        keywords.clickOnButton(driver, statusCheckBox);
        keywords.selectVisibleText(claimJobStatus, appBasedUtils.getStringData(data, "Status"));
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 4, search);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 ReferenceNumber_4 Status_5");
    }

    /****************************************
     * #Description : This is a common function to searchWithImpDealerRefNoAndTMAStatus
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImpDealerRefNoAndTMAStatus(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "RefNoFrom", "TMAStatus" };
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElements, dealerDropDownElements,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(refNoFrom, appBasedUtils.getStringData(data, "RefNoFrom"), "RefNoFrom");
        keywords.input(refNoTo, appBasedUtils.getStringData(data, "RefNoTo"), "RefNoTo");
        // script to scroll : required else element not found execution is thrown.
        keywords.scrollPage(driver, 500);
        keywords.waitForVisibilityOfElement(driver, 10, TMAStatusCheckBox);
        keywords.clickOnButton(driver, TMAStatusCheckBox);
        keywords.selectVisibleText(TMAStatusListbox, appBasedUtils.getStringData(data, "TMAStatus"));
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 ReferenceNumber_4 TMAStatus_5");
    }

    /****************************************
     * #Description : This is a common function to searchWithImpAndRepairDate
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImpAndRepairDate(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "RepairDateFrom" };
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.waitForVisibilityOfElement(driver, 10, repairDateFrom);
        keywords.input(repairDateFrom, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT), "RepairDateFrom");
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.input(repairDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "RepairDateTo");
        keywords.clickOnButton(driver, repairDateToDatePickerIcon);
        keywords.clickOnButton(driver, repairDateToDatePickerIcon);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        keywords.clickOnButton(driver, repairDateCheckBox);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.clickOnButton(driver, search);
        // Since date column xpath is dynamic based on the checkbox selection the value of Xpath vary fron C to D,so if its date pass the variable as Y
        advanceSearchResultValidation(data, columnHeader, "Importer_2 RepairDate_5");
    }

    /****************************************
     * #Description : This is a common function to navigate searchWithImpAndCreditedDate
     ******************************************/
    @SuppressWarnings("static-access")
    public void searchWithImpAndCreditedDate(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "CreditedFrom", "CreditedTo" };
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        keywords.scrollPage(driver, 500);
        keywords.waitForVisibilityOfElement(driver, 10, creditedDateFrom);
        keywords.input(creditedDateFrom, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT), "CreditedFrom");
        keywords.clickOnButton(driver, creditedDateFromDatePickerIcon);
        keywords.clickOnButton(driver, creditedDateFromDatePickerIcon);
        keywords.input(creditedDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "CreditedTo");
        keywords.clickOnButton(driver, creditedDateToDatePickerIcon);
        keywords.clickOnButton(driver, creditedDateToDatePickerIcon);
        keywords.clickOnButton(driver, creditDateCheckBox);
        keywords.keyTab(creditedDateTo);
        keywords.scrollPage(driver, 700);
        keywords.waitTime(1000);
        keywords.clickOnButton(driver, advanceSearchTitle);
        keywords.clickOnButton(driver, causalPartPrefix);
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "Importer_2 CreditedDate_5");
    }

    /****************************************
     * #Description : This is a common function to searchWithRegDateAndClaimStatus
     ******************************************/
    public void searchWithRegDateAndClaimStatus(HashMap<String, Object> data) {
        String[] columnHeader = { "RegistrationDateFrom", "Status" };
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.scrollPage(driver, 50);
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        keywords.waitForVisibilityOfElement(driver, 10, registrationDateFrom);
        keywords.input(registrationDateFrom, fromDate, "RegistrationDateFrom");
        keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
        keywords.input(registrationDateTo, toDate, "RegistrationDateTo");
        keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateCheckBox);
        keywords.keyTab(registrationDateCheckBox);
        keywords.scrollPage(driver, 250);
        keywords.waitForVisibilityOfElement(driver, 10, statusCheckBox);
        keywords.clickOnButton(driver, statusCheckBox);
        keywords.selectVisibleText(claimJobStatus, appBasedUtils.getStringData(data, "Status"));
        keywords.keyTab(statusCheckBox);
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.clickOnButton(driver, search);
        // Since date column xpath is dynamic based on the checkbox selection the value of Xpath vary fron C to D,so if its date pass the variable as Y
        advanceSearchResultValidation(data, columnHeader, "RegistrationDate_6 Status_7");

    }

    /****************************************
     * #Description : This is a common function to searchWithRepairOrderNumberClaimStatusAndTMAStatus
     ******************************************/
    public void searchWithRepairOrderNumberClaimStatusAndTMAStatus(HashMap<String, Object> data) {
        String[] columnHeader = { "RepairOderNumFrom", "Status", "TMAStatus" };
        fromTextValue = appBasedUtils.getStringData(data, "RepairOderNumFrom");
        toTextValue = appBasedUtils.getStringData(data, "RepairOderNumTo");
        keywords.scrollPage(driver, 250);
        keywords.waitForVisibilityOfElement(driver, 10, repairOrderNoCheckBox);
        keywords.clickOnButton(driver, repairOrderNoCheckBox);
        keywords.input(repairOrderNoFrom, appBasedUtils.getStringData(data, "RepairOderNumFrom"), "RepairOderNumFrom");
        keywords.input(repairOrderNoTo, appBasedUtils.getStringData(data, "RepairOderNumTo"), "RepairOderNumTo");
        keywords.clickOnButton(driver, statusCheckBox);
        keywords.selectVisibleText(claimJobStatus, appBasedUtils.getStringData(data, "Status"));
        keywords.scrollPage(driver, 600);
        keywords.waitForVisibilityOfElement(driver, 10, TMAStatusCheckBox);
        keywords.clickOnButton(driver, TMAStatusCheckBox);
        keywords.selectVisibleText(TMAStatusListbox, appBasedUtils.getStringData(data, "TMAStatus"));
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "RepairOderNumber_5 Status_6 TMAStatus_7");
    }

    /********************************
     * #Description : Method to list and verifyTypeofConcernContractnoAndTypeofclaimB
     */
    public void verifyTypeofConcernContractnoAndTypeofclaimB(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        APP_LOGS.info("Verifing the values in the drop down field");
        String brandValue = appBasedUtils.getStringData(data, "TypeOfConcern");
        String[] brandName = brandValue.split(",");
        keywords.verifyListOfItemsFromDropDown(TypeofconcernB, brandName, softAssert);
        keywords.elementIsEnabled(Contractno);
        String ClaimType = appBasedUtils.getStringData(data, "TypeOfClaimB");
        String[] ClaimTypeValue = ClaimType.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownTypeOfClaim, ClaimTypeValue, softAssert);
        keywords.clickOnButton(driver, TypeofconcernB);
        keywords.selectVisibleText(TypeofconcernB, "Contract");
        keywords.elementIsEnabled(Contractno);
        String ContractClaim = appBasedUtils.getStringData(data, "TypeOfClaimC");
        String[] ContractClaimValue = ContractClaim.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownTypeOfClaim, ContractClaimValue, softAssert);
        keywords.clickOnButton(driver, TypeofconcernB);
        keywords.selectVisibleText(TypeofconcernB, "Warranty");
        String WarrantyClaim = appBasedUtils.getStringData(data, "TypeOfClaimW");
        String[] WarrantyClaimValue = WarrantyClaim.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownTypeOfClaim, WarrantyClaimValue, softAssert);
        if (Contractno.isEnabled() == false) {
            APP_LOGS.info("Element is disabled.");
        } else {
            Assert.fail("Element is enabled");
        }
    }

    /********************************
     * #Description : Method to list and  verifytypeofconcernandcontractnoSearchresultsB
     */
    public void verifytypeofconcernandcontractnoSearchresultsB(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        String[] columnHeader = { "RefNoFrom", "ExpectedTypeOfConcern", "Contractno" };
        APP_LOGS.info("Verifing the values in the drop down field");
        keywords.waitForVisibilityOfElement(driver, 10, refNoFrom);
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        keywords.input(refNoFrom, appBasedUtils.getStringData(data, "RefNoFrom"), "RefNoFrom");
        keywords.input(refNoTo, appBasedUtils.getStringData(data, "RefNoTo"), "RefNoTo");
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        keywords.input(Contractno, appBasedUtils.getStringData(data, "Contractno"), "Contractno");
        keywords.clickOnButton(driver, ContractnoCheckBox);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "ReferenceNumber_4 TypeOfConcern_5 Contractno_6");

    }

    /********************************
     * #Description : Method to verifyTypeofconcernContractnoClaimtypeC
     */
    public void verifyTypeofconcernContractnoClaimtypeC(HashMap<String, Object> data) {
        keywords.assertEqual("Contract", typeofconcern.getText(), softAssert);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        APP_LOGS.info("Verifing the values in the drop down field");
        String brandValue = appBasedUtils.getStringData(data, "TypeOfClaimC");
        String[] brandName = brandValue.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownTypeOfClaim, brandName, softAssert);
        keywords.elementIsEnabled(Contractno);

    }

    /*******************************
     * #Description : Method to lverifySearchresultsTypeofconcernContractnoClaimtypeC
     *******************************/
    public void verifySearchresultsTypeofconcernContractnoClaimtypeC(HashMap<String, Object> data) {
        String[] columnHeader = { "RefNoFrom", "ExpectedTypeOfConcern", "Contractno" };
        APP_LOGS.info("Verifing the values in search results");
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        keywords.input(refNoFrom, appBasedUtils.getStringData(data, "RefNoFrom"), "RefNoFrom");
        keywords.input(refNoTo, appBasedUtils.getStringData(data, "RefNoTo"), "RefNoTo");
        keywords.input(Contractno, appBasedUtils.getStringData(data, "Contractno"), "Contractno");
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        keywords.clickOnButton(driver, ContractnoCheckBox);
        keywords.clickOnButton(driver, search);
        advanceSearchResultValidation(data, columnHeader, "ReferenceNumber_4 TypeOfConcern_5 Contractno_6");

    }

    /****************************************
     * #Description : This is a common function to advanceSearchResultValidation
     ******************************************/
    public void advanceSearchResultValidation(HashMap<String, Object> data, String columnheader[], String noOfColumnsToValidate) {
        softAssert = new SoftAssert();
        // first row column count start with Zero and iterate the columnIndexInSearchGrid by one till the lst row
        int columnIndexInSearchGrid = 0;
        // wait until search result is loaded for 15 second
        try {
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 6, gridColumnName, errorMsg, validationMessageClose);
                for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {
                    if ("Importer_2 Dealer_3 Status_5 Status_6 Status_7 SearchType_5 TMAStatus_5 TMAStatus_7 Status_6 Contractno_6 TypeOfConcern_5 SearchType_6 ContractNo_5".contains(hexaStrToXpath)) {
                        APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.textValidationGrid(driver, (appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid])), XpathFirstPart
                                        + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert);
                        columnIndexInSearchGrid++;
                    }
                    // Reference number and Interger comparision
                    else if ("ReferenceNumber_4 RepairOderNumber_5".contains(hexaStrToXpath)) {
                        APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.verifySearchResultInGridBtwRangeForIntergerValue(driver, fromTextValue, toTextValue, XpathFirstPart
                                + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert);
                        columnIndexInSearchGrid++;
                    } else if ("RepairDate_5 RepairDate_6 CreditedDate_5 CreditedDate_6 RegistrationDate_5 RegistrationDate_6".contains(hexaStrToXpath)) {
                        APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                        appBasedUtils.validateDate(driver,
                                                   XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]",
                                                   DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT),
                                                   DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), softAssert);
                        columnIndexInSearchGrid++;
                    } else {
                        APP_LOGS.info("Value of char didnot match " + hexaStrToXpath);
                        Assert.fail();
                    }
                }
                ExtentTestManager.getTest().log(Status.INFO, "Verified the search result");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softAssert.assertAll();
    }

    /*
     * Description : Search claim using reference number, and returs true if claim found else return false(No result).
     */
    public boolean searchClaimUsingRefNumberAndReturnSearchStatus(String referenceNumber, String JobNumber) {
        try {
            keywords.waitForVisibilityOfElement(driver, 10, refNoFrom);
            keywords.input(refNoFrom, referenceNumber, "Reference number from");
            keywords.input(refNoTo, referenceNumber, "Reference number to");
            keywords.scrolltoEndOfPage(driver);
            keywords.waitForVisibilityOfElement(driver, 10, search);
            keywords.scrollPageTillBottomOfPage(driver);
            keywords.clickOnButton(driver, search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            if ((keywords.elementIsDisplayed(searchResultNoItemToDisplay, "No items to display")))
                return false;
            else if ((keywords.waitForVisibilityOfElement(driver, 5, referenceNumberInSearchResult))) {
                if (keywords.getTextValue(referenceNumberInSearchResult).contains(referenceNumber + " - " + JobNumber))
                    return true;
            }
        } catch (Exception e) {
            APP_LOGS.info(e);
        }
        return false;
    }

    /****************************************
     * #Description : This is to open a claim job
     ******************************************/
    public void openAClaimJob(String refNo) {
        String firstPartOfXpath = "//div[@uib-tooltip='";
        String secondPartOfXpath = " - 1']/a";
        String completeXpath = firstPartOfXpath + refNo + secondPartOfXpath;
        driver.findElement(By.xpath(completeXpath)).click();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
    }

    /****************************************
     * #Description : This is a common function to searchWithRefNoRepairDate
     ******************************************/
    public void searchWithRefNoRepairDate(String refNo) {
        keywords.input(refNoFrom, refNo, "RefNoFrom");
        keywords.input(refNoTo, refNo, "RefNoTo");
        keywords.input(repairDateFrom, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "RepairDateFrom");
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.input(repairDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "RepairDateTo");
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.waitForVisibilityOfElement(driver, 10, repairDateFrom);
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, search);
        keywords.waitTime1(4);
        keywords.clickOnButton(driver, search);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
    }

    /*
     * #Description : Search contract claim using importer ,dealer ,RefNo And Status.
     */
    public void contractSearchWithImpDealerImpRefNoAndStatus(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "RefNoFrom", "ExpectedTypeOfConcern", "Status" };
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        enterImporterandDealer(data);
        enterReferenceNumber(data);
        selectTypeOfConcern(data);
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        selectStatus(data);
        keywords.scrollPageTillBottomOfPage(driver);
        clickOnSearch();
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 ReferenceNumber_4 TypeOfConcern_5 Status_6");
    }

    /****************************************
     * #Description : This is a common function to enter importer and dealer #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    private void enterImporterandDealer(HashMap<String, Object> data) {
        APP_LOGS.info("Verifing the values in the drop down field");
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(BaseClass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(BaseClass.userContextForDealer, dealerTextBoxElements, dealerDropDownElements,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
    }

    /****************************************
     * #Description : This is a common function to enter reference number #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    private void enterReferenceNumber(HashMap<String, Object> data) {
        keywords.input(refNoFrom, appBasedUtils.getStringData(data, "RefNoFrom"), "RefNoFrom");
        keywords.input(refNoTo, appBasedUtils.getStringData(data, "RefNoTo"), "RefNoTo");
    }

    /****************************************
     * #Description : This is a common function to select status #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    private void selectStatus(HashMap<String, Object> data) {
        keywords.scrollPage(driver, 300);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobStatus);
        keywords.selectVisibleText(claimJobStatus, appBasedUtils.getStringData(data, "Status"));
        keywords.clickOnButton(driver, statusCheckBox);
    }

    /****************************************
     * #Description : This is a common function to click on search #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    private void clickOnSearch() {
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 4, search);
        keywords.clickOnButton(driver, search);
    }

    /****************************************
     * #Description : This is a common function to select typeOfConcern #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    public void selectTypeOfConcern(HashMap<String, Object> data) {
        if ((appBasedUtils.getStringData(data, "TypeOfConcern_1")).equalsIgnoreCase("WarrantyOnly")
                || (appBasedUtils.getStringData(data, "TypeOfConcern_1")).equalsIgnoreCase("ContractOnly"))
            keywords.APP_LOGS.info("Type of concern picked for claim creation is:" + keywords.getTextValue(typeOfConcernLabelValue));
        else if ((appBasedUtils.getStringData(data, "TypeOfConcern_1")).equalsIgnoreCase("Both"))
            keywords.clickOnButton(driver, typeOfConcernElement);
        keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(data, "SelectTypeOfConcern"));
        //keywords.clickOnButton(typeOfConcernCheckBox);
    }

    /*
     * #Description : Search contract claim using importer Dealer RefNo And SearchType.
     */
    public void contractSearchWithImpDealerRefNoAndSearchType(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "RefNoFrom", "ExpectedTypeOfConcern", "SearchType" };
        fromTextValue = appBasedUtils.getStringData(data, "RefNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "RefNoTo");
        enterImporterandDealer(data);
        enterReferenceNumber(data);
        selectTypeOfConcern(data);
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        selectSearchType(data);
        clickOnSearch();
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 ReferenceNumber_4 TypeOfConcern_5 SearchType_6");
    }

    /****************************************
     * #Description : This is a common function to select search type #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    private void selectSearchType(HashMap<String, Object> data) {
        keywords.waitForVisibilityOfElement(driver, 10, searchTypeCheckBox);
        keywords.clickOnButton(driver, searchTypeCheckBox);
        keywords.selectVisibleText(searchTypeListbox, appBasedUtils.getStringData(data, "SearchType"));
    }

    /*
     * #Description : Search contract claim using importer dealer and repairDate.
     */
    public void contractSearchWithImpDealerAndRepairDate(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "ExpectedTypeOfConcern", "RepairDateFrom" };
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        enterImporterandDealer(data);
        selectRepairFromandTo();
        selectTypeOfConcern(data);
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        clickOnSearch();
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 TypeOfConcern_5 RepairDate_6");
    }

    /****************************************
     * #Description : This is a common function to select repair date from and to #Date of creation :28-03-17 #Input Parameters: execel data
     ******************************************/
    private void selectRepairFromandTo() {
        keywords.waitForVisibilityOfElement(driver, 10, repairDateFrom);
        keywords.input(repairDateFrom, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT), "RepairDateFrom");
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
        keywords.input(repairDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "RepairDateTo");
        keywords.clickOnButton(driver, repairDateToDatePickerIcon);
        keywords.clickOnButton(driver, repairDateToDatePickerIcon);
        keywords.clickOnButton(driver, repairDateCheckBox);
    }

    /****************************************
     * #Description : This is a common function to ncontractSearchWithImpDealerAndCreditedDate
     ******************************************/
    public void contractSearchWithImpDealerAndCreditedDate(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "ExpectedTypeOfConcern", "RepairDateFrom" };
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        enterImporterandDealer(data);
        selectTypeOfConcern(data);
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        selectCreditedDateFromandTo();
        clickOnSearch();
        advanceSearchResultValidation(data, columnHeader, "Importer_2 Dealer_3 TypeOfConcern_5 CreditedDate_6");
    }

    /****************************************
     * #Description : This is a common function to select credited date from and to #Date of creation :10-08-18 #Input Parameters: execel data
     ******************************************/
    private void selectCreditedDateFromandTo() {
        keywords.scrollPageTillBottomOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, creditedDateFrom);
        keywords.input(creditedDateFrom, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT), "CreditedFrom");
        keywords.clickOnButton(driver, creditedDateFromDatePickerIcon);
        keywords.clickOnButton(driver, creditedDateFromDatePickerIcon);
        keywords.input(creditedDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "CreditedTo");
        keywords.clickOnButton(driver, creditedDateToDatePickerIcon);
        keywords.clickOnButton(driver, creditedDateToDatePickerIcon);
        keywords.clickOnButton(driver, creditDateCheckBox);

    }

    /****************************************
     * #Description : This is a common function to contractSearchWithRegDateAndClaimStatus
     ******************************************/
    public void contractSearchWithRegDateAndClaimStatus(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedTypeOfConcern", "RegistrationDateFrom", "Status" };
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        selectRegDateFromandTo();
        selectStatus(data);
        selectTypeOfConcern(data);
        keywords.clickOnButton(driver, typeOfConcernCheckBox);
        clickOnSearch();
        advanceSearchResultValidation(data, columnHeader, "TypeOfConcern_5 RegistrationDate_6 Status_7");
    }

    /****************************************
     * #Description : This is a common function to select registration date from and to #Date of creation :10-08-18 #Input Parameters: execel data
     ******************************************/
    private void selectRegDateFromandTo() {
        keywords.scrollPage(driver, 50);
        keywords.waitForVisibilityOfElement(driver, 10, registrationDateFrom);
        keywords.input(registrationDateFrom, fromDate, "RegistrationDateFrom");
        keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
        keywords.input(registrationDateTo, toDate, "RegistrationDateTo");
        keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateCheckBox);
        keywords.keyTab(registrationDateCheckBox);
        keywords.scrollPage(driver, 250);
    }

    /****************************************
     * #Description : This is a common function to select the first record in the search result grid and open a claim job execel data
     ******************************************/
    public void openTheFirstRecordFromTheSearchResultGrid() {
        keywords.setCheckboxIfNotSelected(firstRecordInSearchResultGrid);
        keywords.waitForVisibilityOfElement(driver, 10, openSelectedRecordBtn);
        keywords.waitTime1(3);
        keywords.clickOnButton(driver, openSelectedRecordBtn);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
    }
}
