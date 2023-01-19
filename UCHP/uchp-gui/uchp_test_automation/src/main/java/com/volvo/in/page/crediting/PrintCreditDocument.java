package com.volvo.in.page.crediting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.volvo.in.commonlibrary.UIUtils;

public class PrintCreditDocument {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert softAssert = new SoftAssert();
    List<String> arrayList = new ArrayList<String>();

    public Logger APP_LOGS = Logger.getLogger(PrintCreditDocument.class);

    @FindBy(how = How.XPATH, using = "(//a[contains(text(),'Crediting')])[1]")
    WebElement creditingMainMenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Print credit document')]")
    WebElement printCreditdocSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'rint credit document')]")
    WebElement printCreditTitle;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfConcern']")
    WebElement dropdownTypeOfConcern;

    @FindBy(how = How.ID, using = "importerNo")
    WebElement importerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../span")
    WebElement typeOfConcernLabelValue;

    @FindBy(how = How.ID, using = "importer_No")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "dealer_No")
    WebElement dealerDropDownElement;

    @FindBy(how = How.ID, using = "dealerNo")
    WebElement dealerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//div[@id='creditingDateFrom']/input")
    WebElement creditingDateFrom;

    @FindBy(how = How.XPATH, using = "//*[@id='creditingDateFrom']/div/ul/li[1]/div/div[1]/table/tbody/tr[5]/td[4]")
    WebElement creditingdatefromClick;

    @FindBy(how = How.XPATH, using = "//div[@id='creditingDateTo']/input")
    WebElement creditingDateTo;

    @FindBy(how = How.ID, using = "creditdocumentNo")
    WebElement creditDocumentNo;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Filter')]")
    WebElement filter;

    @FindBy(how = How.XPATH, using = "//select[@id='droplistMenuBrand']")
    WebElement dropdownBrand;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.ID, using = "droplistMenuMime")
    WebElement dropdownDocumentMimeType;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "(//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope'])[2]")
    WebElement checkBoxXpathSearchResult;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000B'])[1]")
    WebElement documentTypeToDownload;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[5]")
    List<WebElement> creditDocumentElements;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-cell-contents sortable-header']/span[contains(text(),'Crediting date')]")
    WebElement creditingDateColumn;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-isolate-scope'])[1]/div[5]")
    WebElement creditDocumentNoFromGrid;

    public String creditDocumentNumberString;

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    int numberOfPreviousDays = 180;

    public PrintCreditDocument(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPrintCreditDocument() {
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        keywords.waitTime1(5);
        keywords.waitForVisibilityOfElement(driver, 10, creditingMainMenu);
        keywords.performMenuAction(driver, creditingMainMenu, printCreditdocSubMenu, printCreditTitle);
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOf(printCreditTitle));
        //keywords.waitForPageLoadSuccessfully(driver);
        keywords.waitTime(2000);
    }

    /*********************************************
     * #Description : Search the credit document with importer. #Input parameters: excel data
     ********************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporter(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Importer_2");
    }

    /*********************************************
     * #Description : Search the credit document with dealer. #Input parameters: excel data
     ********************************************/
    @SuppressWarnings("static-access")
    public void searchWithDealer(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedDealerValue" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Dealer_3");
        setCreditDocumentFromGRID();
    }

    /*********************************************
     * #Description : Search the credit document with brand. #Input parameters: excel data
     ********************************************/
    public void searchWithBrand(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedBrand" };
        keywords.waitForVisibilityOfElement(driver, 10, printCreditTitle);
        keywords.waitForVisibilityOfElement(driver, 10, dropdownBrand);
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Brand_4");
    }

    /*********************************************
     * #Description : Search the credit document with CreditDocumentNo. #Input parameters: excel data
     ********************************************/

    public void searchWithCreditDocumentNo(HashMap<String, Object> data) {
        String[] columnHeader = { "CreditDocumentNo" };
        keywords.waitForVisibilityOfElement(driver, 10, dropdownBrand);
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        keywords.input(creditDocumentNo, appBasedUtils.getStringData(data, "CreditDocumentNo"), "Crediting document number");
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "CreditDocumentNumber_5");
    }

    /*********************************************
     * #Description : Search the credit document with CreditDocumentNo. #Input parameters: excel data
     ********************************************/
    public void searchWithCreditDocumentNoVerifyPDFDocument(HashMap<String, Object> data) {
        String[] columnHeader = { "CreditDocumentNo", "Attachment" };
        keywords.waitForVisibilityOfElement(driver, 10, dropdownBrand);
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        keywords.input(creditDocumentNo, appBasedUtils.getStringData(data, "CreditDocumentNo"), "Crediting document number");
        keywords.keyTab(creditDocumentNo);
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "CreditDocumentNumber_5 Attachment_9");
    }

    /*********************************************
     * #Description : Search the credit document with CreditingDate. #Input parameters: excel data
     ********************************************/

    public void searchWithCreditingDate(HashMap<String, Object> data) {
        String[] columnHeader = { "CreditingToDate" };
        keywords.waitForVisibilityOfElement(driver, 10, creditingDateFrom);
        keywords.input(creditingDateFrom, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Crediting date from");
        keywords.clickOnButton(driver, creditingDateFrom);
        keywords.keyEnter(creditingDateFrom);
        keywords.input(creditingDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Crediting date to");
        keywords.keyTab(creditingDateTo);
        keywords.clickOnButton(driver, creditingDateTo);
        keywords.keyEnter(creditingDateTo);
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "CreditingDate_6");

    }


    /*********************************************
     * #Description : Search the credit document with CreditingDate, importer, brand and ToC. #Input parameters: excel data
     ********************************************/

    public void searchWithCreditingDateImporterAndToC(HashMap<String, Object> data) {
        String[] columnHeader = { "CreditingToDate", "ExpectedImportervalue", "ExpectedToC" };
        keywords.waitForVisibilityOfElement(driver, 10, creditingDateFrom);
        keywords.input(creditingDateFrom,  DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Crediting date from");
        keywords.clickOnButton(driver, creditingDateFrom);
        keywords.keyEnter(creditingDateFrom);
        keywords.input(creditingDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Crediting date to");
        keywords.keyTab(creditingDateTo);
        keywords.clickOnButton(driver, creditingDateTo);
        keywords.keyEnter(creditingDateTo);
        keywords.input(importerTextBoxElement,  appBasedUtils.getStringData(data, "Importer"), "Importer value");
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "TypeOfConcern"))) {
            if ((appBasedUtils.getStringData(data, "TypeOfConcernPrivilege")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(data, "TypeOfConcernPrivilege")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(data, "TypeOfConcernPrivilege")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(data, "TypeOfConcern"));
        }
        keywords.clickOnButton(driver, filter);
        keywords.waitTime1(2);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "CreditingDate_6 Importer_2 ToC_1");
        CreateOrModifyCrediting.creditDocNoFromGrid = keywords.getTextValue(creditDocumentNoFromGrid);
    }
    /*********************************************
     * #Description : Search the credit document with importer and dealer. #Input parameters: excel data
     ********************************************/

    @SuppressWarnings("static-access")
    public void searchWithImporterDealer(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Importer_2 Dealer_3");

    }

    /****************************************************
     * #Description : Search the credit document with importer, dealer, Brand. #Input parameters: excel data
     ***************************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporterDealerAndBrand(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "Brand" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Importer_2 Dealer_3 Brand_4");
    }

    /****************************************************
     * #Description : Search the credit document with importer, dealer, Document No. #Input parameters: excel data
     ***************************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporterDealerAndDocumentNo(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "CreditDocumentNo" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(creditDocumentNo, appBasedUtils.getStringData(data, "CreditDocumentNo"), "Credit document number");
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Importer_2 Dealer_3 CreditDocumentNumber_5");
    }

    /****************************************************
     * #Description : Search the credit document with importer, dealer, Document No and crediting Date from and To. #Input parameters: excel data
     ***************************************************/
    @SuppressWarnings("static-access")
    public void searchWithImporterDealerDocumentNoAndCreditingDate(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "CreditDocumentNo", "CreditingDateFrom", "CreditingDateTo" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(creditDocumentNo, appBasedUtils.getStringData(data, "CreditDocumentNo"), "Importer");
        keywords.input(creditingDateFrom, appBasedUtils.getStringData(data, "CreditingFromDate"), "Crediting date from");
        keywords.input(creditingDateTo, DateUtility.getCurrentDate(DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT)), "crediting date to");
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Importer_2 Dealer_3 CreditDocumentNumber_5 CreditingDate_6");
    }

    /***************************************************
     * #Description : Search with Importer, Dealer, Brand, Credit Document no, Crediting date, Document Mime Type #Input parameters: excel data
     **************************************************/
    @SuppressWarnings("static-access")
    public void searchWithAll(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedImportervalue", "ExpectedDealerValue", "Brand", "CreditDocumentNo", "CreditingDateFrom", "CreditingDateTo" };
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(creditDocumentNo, appBasedUtils.getStringData(data, "CreditDocumentNo"), "Crediting document number");
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        keywords.input(creditingDateFrom,DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "CreditingDateFrom");
        keywords.input(creditingDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "CreditingDateTo");
        keywords.keyTab(creditingDateTo);
        keywords.selectVisibleText(dropdownDocumentMimeType, appBasedUtils.getStringData(data, "DocumentMimeType"));
        keywords.clickOnButton(driver, filter);

        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Importer_2 Dealer_3 Brand_4 CreditDocumentNumber_5 CreditingDate_6");
    }

    /***************************************************
     * #Description : Search with brand and credit document number #Input parameters: excel data
     **************************************************/
    public void verifyListOfBrandNamesInDropDown(HashMap<String, Object> data) {
        keywords.APP_LOGS.info("Verifing the brand names in brand list");
        String brandValue = appBasedUtils.getStringData(data, "ExpectedBrand");
        String[] brandName = brandValue.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownBrand, brandName, softAssert);

    }

    /***************************************************
     * #Description : Search with brand and credit document number. #Input parameters: excel data
     **************************************************/
    public void searchWithBrandAndDocumentNo(HashMap<String, Object> data) {
        String[] columnHeader = { "Brand", "CreditDocumentNo" };
        keywords.waitForVisibilityOfElement(driver, 10, dropdownBrand);
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        keywords.input(creditDocumentNo, appBasedUtils.getStringData(data, "CreditDocumentNo"), "Crediting document number");
        keywords.clickOnButton(driver, filter);
        // Validation script
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "Brand_4 CreditDocumentNumber_5");
    }

    /***************************************************
     * #Description: to verify the search result in the grid in print credit document page #Input parameters: excel data
     */
    public void printCreditDocumentVerifySearchResultInGrid(HashMap<String, Object> data, String columnheader[], String noOfColumnsToValidate) {
        int columnIndexInSearchGrid = 0;
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 10, gridColumnName, errorMsg, validationMessageClose);
            for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {
                if ("ToC_1 Importer_2 Dealer_3 CreditDocumentNumber_5".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                    appBasedUtils.textValidationGrid(driver,
                                                     (appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid])),
                                                     XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert);
                    columnIndexInSearchGrid++;
                } else if ("Brand_4".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                    appBasedUtils.dropdownValueValidationInGrid(appBasedUtils.getStringData(data, "ExpectedBrand"), XpathFirstPart
                            + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert, driver);
                    columnIndexInSearchGrid++;
                } else if ("CreditingDate_6".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                    appBasedUtils.validateDate(driver, XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length())
                            + "]", DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), softAssert);
                    columnIndexInSearchGrid++;
                } else if ("Attachment_9".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                    appBasedUtils.attachementValidationGrid(data, XpathFirstPart
                            + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert, driver, "");
                    columnIndexInSearchGrid++;
                } else {
                    keywords.APP_LOGS.info("Value of char didnot match" + hexaStrToXpath);
                }
            }
            ExtentTestManager.getTest().log(Status.PASS,"Verified the search result");
            softAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    public void setCreditDocumentFromGRID() {
        keywords.waitForVisibilityOfElement(driver, 10, creditingDateColumn);
        keywords.clickOnLink(creditingDateColumn, driver);
        keywords.clickOnLink(creditingDateColumn, driver);
        keywords.waitTime(2000);
        for (int i = 0; i < creditDocumentElements.size(); i++) {
            if (!keywords.getTextValue(creditDocumentElements.get(i)).equals("")) {
                creditDocumentNumberString = keywords.getTextValue(creditDocumentElements.get(i));
                break;
            }
        }
    }

    /*********************************************
     * #Description : Search the credit document with brand. #Input parameters: excel data
     ********************************************/
    public void searchWithtypeOfConcern(HashMap<String, Object> data) {
        String[] columnHeader = { "ExpectedToC" };
        keywords.waitForVisibilityOfElement(driver, 10, dropdownTypeOfConcern);
        keywords.selectVisibleText(dropdownTypeOfConcern, appBasedUtils.getStringData(data, "TypeOfConcern"));
        keywords.clickOnButton(driver, filter);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "ToC_1");
    }

    /*********************************************
     * #Description : Search the credit document with CreditingDate, importer, Dealer ,brand and ToC. #Input parameters: excel data
     ********************************************/

    public void searchWithCreditingDateImporterDealerAndToC(HashMap<String, Object> data) {
        String[] columnHeader = { "CreditingToDate", "ExpectedImportervalue", "ExpectedToC" };
        keywords.waitForVisibilityOfElement(driver, 10, creditingDateFrom);
        keywords.input(creditingDateFrom,  DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Crediting date from");
        keywords.clickOnButton(driver, creditingDateFrom);
        keywords.keyEnter(creditingDateFrom);
        keywords.input(creditingDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Crediting date to");
        keywords.keyTab(creditingDateTo);
        keywords.clickOnButton(driver, creditingDateTo);
        keywords.keyEnter(creditingDateTo);
        keywords.input(importerTextBoxElement,  appBasedUtils.getStringData(data, "Importer"), "Importer value");
        keywords.input(dealerTextBoxElement,  appBasedUtils.getStringData(data, "Dealer"), "Dealer value");
        keywords.selectVisibleText(dropdownBrand, appBasedUtils.getStringData(data, "Brand"));
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "TypeOfConcern"))) {
            if ((appBasedUtils.getStringData(data, "TypeOfConcernPrivilege")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(data, "TypeOfConcernPrivilege")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(data, "TypeOfConcernPrivilege")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(data, "TypeOfConcern"));
        }
        keywords.clickOnButton(driver, filter);
        keywords.waitTime1(2);
        printCreditDocumentVerifySearchResultInGrid(data, columnHeader, "CreditingDate_6 Importer_2 ToC_1");

    }
}
