package com.volvo.in.page.crediting;

import java.util.HashMap;

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

public class PrintOutStandingClaimSummary {

    WebDriver driver;
    public int i = 0;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    UIUtils keywords = new UIUtils();

    @FindBy(how = How.ID, using = "importerNo")
    WebElement importerTextBoxElement;

    @FindBy(how = How.ID, using = "importer_No")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "dealerNo")
    WebElement dealerTextBoxElement;

    @FindBy(how = How.ID, using = "dealer_No")
    WebElement dealerDropDownElement;

    @FindBy(how = How.XPATH, using = "//select[@id='droplistMenuBrand']")
    WebElement brandElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Filter')]")
    WebElement filterBtnElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Crediting ']")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Print outstanding claim summary')]")
    WebElement printOutStandingSummSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Print outstanding claim summary')]")
    WebElement printOutStandingClaimSummTitleElement;

    @FindBy(how = How.XPATH, using = "(//input[@class='form-control'])[1]")
    WebElement creationDateFromElement;

    @FindBy(how = How.XPATH, using = "(//input[@class='form-control'])[2]")
    WebElement creationDateToElement;

    @FindBy(how = How.XPATH, using = "//span[contains(@id,'uiGrid') and contains(., 'Importer')]")
    WebElement gridImporterElement;

    @FindBy(how = How.XPATH, using = "//span[contains(@id,'uiGrid') and contains(., 'Dealer')]")
    WebElement gridDealerElement;

    @FindBy(how = How.XPATH, using = "//span[contains(@id,'uiGrid') and contains(., 'Brand')]")
    WebElement gridBrandElement;

    @FindBy(how = How.XPATH, using = "//span[contains(@id,'uiGrid') and contains(., 'Crediting Date')]")
    WebElement gridCreditingTypeElement;

    @FindBy(how = How.XPATH, using = "//span[contains(@id,'uiGrid') and contains(., 'Document Type')]")
    WebElement gridDocumentTypeElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    @FindBy(how = How.XPATH, using = "//div/button[@class='toast-close-button ng-scope']")
    WebElement errorMsgClose;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridTable;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    int numberOfPreviousDays = 180;

    public PrintOutStandingClaimSummary(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /***********************************************************************
     * #Function Name : navigateToPrintOutStandingSumm()
     *
     * #Description : This is a common function to navigate from "Crediting" main menu to "CreateOutStandingSummary" sub menu
     *
     * #Input Parameters:
     * @param driver TODO
     * @param driver 
     ***********************************************************************/
    public void navigateToPrintOutStandingSumm(WebDriver driver) {
        keywords.waitForVisibilityOfElement(driver, 30, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        keywords.waitForVisibilityOfElement(driver, 8, creditingMainMenuElement);
        keywords.performMenuAction(driver, creditingMainMenuElement, printOutStandingSummSubMenuElement, printOutStandingClaimSummTitleElement);
        // validate whether page navigation is successfull
        keywords.waitForVisibilityOfElement(driver, 8, printOutStandingClaimSummTitleElement);
    }

    /***********************************************************************
     * #Function Name : filterOutStadingClaimSummary()
     *
     * #Description : function to filter claim summary by specifying Importer, Dealer, Brand
     *
     * #Input Parameters: data: data for the test case i.e Importer, Dealer Brand etc
     ***********************************************************************/
    @SuppressWarnings("static-access")
    public void filterWithImporterDealerBrand(HashMap<String, Object> data) {
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.waitForVisibilityOfElement(driver, 10, brandElement);
        keywords.waitTime(500);
        keywords.selectVisibleText(brandElement, appBasedUtils.getStringData(data, "Brand"));
        keywords.clickOnButton(driver, filterBtnElement);
        String columnheader[] = { "ExpectedImportervalue", "ExpectedDealerValue", "ExpectedBrand" };
        POCSVerifySearchResultInGrid("Importer_2 Dealer_3 Brand_4", data, columnheader);
        cleardata();

    }

    /***********************************************************************
     * #Function Name : filterOutStadingClaimSummary()
     *
     * #Description : function to filter claim summary by specifying Importer, Dealer, Brand
     *
     * #Input Parameters: data: data for the test case i.e Importer, Dealer Brand etc
     ***********************************************************************/
    @SuppressWarnings("static-access")
    public void filterWithImporterDealerCreationDate(HashMap<String, Object> data) {
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.input(creationDateFromElement, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, "dd/MM/yyyy"), "Creation from Date");
        keywords.keyTab(creationDateFromElement);
        keywords.input(creationDateToElement, DateUtility.getCurrentDate("dd/MM/yyyy"), "Creation To date");
        keywords.keyTab(creationDateToElement);
        keywords.clickOnButton(driver, filterBtnElement);
        String columnheader[] = { "ExpectedImportervalue", "ExpectedDealerValue", "CreditingFromDate", "CreditingToDate" };
        POCSVerifySearchResultInGrid("Importer_2 Dealer_3 CreationDate_5", data, columnheader);
    }

    /***********************************************************************
     * #Function Name : filterOutStadingClaimSummary()
     *
     * #Description : function to filter claim summary by specifying Importer, Dealer, Brand
     *
     * #Input Parameters: data: data for the test case i.e Importer, Dealer Brand etc
     ************************************************************************/
    @SuppressWarnings("static-access")
    public void filterWithImporterDealerBrandDownloadDocument(HashMap<String, Object> data) {
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
        keywords.waitForVisibilityOfElement(driver, 10, brandElement);
        keywords.selectVisibleText(brandElement, appBasedUtils.getStringData(data, "Brand"));
        keywords.clickOnButton(driver, filterBtnElement);
        String columnheader[] = { "ExpectedImportervalue", "ExpectedDealerValue", "ExpectedBrand", "Attachemnt" };
        POCSVerifySearchResultInGrid("Importer_2 Dealer_3 Brand_4 Attachement_6", data, columnheader);
    }

    /***********************************************************************
     * #Description : Method to verify and validate Grid result in print outstanding claim summary page
     *
     * #Input Parameters: noOfColumnsToValidate: Hexadecimal character used as index in the Xpath data: data for testing i.e impporter, dealer, brand etc
     * columnheader: SearchGrid column names used as key fetch the data from collection.
     ***********************************************************************/
    public void POCSVerifySearchResultInGrid(String noOfColumnsToValidate, HashMap<String, Object> data, String columnheader[]) {
        int columnIndexkInSearchGrid = 0;
        SoftAssert softAssert = new SoftAssert();
        keywords.APP_LOGS.info("Validating search grid  in \"Print Outstanding Claim Summary\" page...");
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            WebDriverWait wait = new WebDriverWait(driver, 4);
            wait.until(ExpectedConditions.visibilityOf(gridTable));
            for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {
                if ("Importer_2 Dealer_3 CreditDocumentNumber_5".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexkInSearchGrid] + "\" Column.");
                    appBasedUtils.textValidationGrid(driver,
                                                     (appBasedUtils.getStringData(data, columnheader[columnIndexkInSearchGrid])),
                                                     XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert);
                } else if ("Brand_4".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexkInSearchGrid] + "\" Column.");
                    appBasedUtils.dropdownValueValidationInGrid(appBasedUtils.getStringData(data, "ExpectedBrand"), XpathFirstPart
                            + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert, driver);
                } else if ("CreationDate_5".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"CreationDate\" Column.");
                    appBasedUtils.validateDate(driver,
                                               XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]",
                                               DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, "dd/MM/yyyy"),
                                               DateUtility.getCurrentDate("dd/MM/yyyy"), softAssert);
                } else if ("Attachement_6".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"Attachment\" Column.");
                    appBasedUtils.attachementValidationGrid(data, XpathFirstPart
                            + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert, driver, "-Dealer");
                } else
                    keywords.APP_LOGS.info("Value of character did not match " + hexaStrToXpath);

                columnIndexkInSearchGrid++;
            }
            softAssert.assertAll();
        } catch (Exception e) {
            if (((keywords.elementIsDisplayed(commonMessageAfterAlert, "No Result message pop up")))) {
                String val = keywords.getTextValue(commonMessageAfterAlert);
                keywords.clickOnButton(driver, errorMsgClose);
                ExtentTestManager.getTest().log(Status.FAIL, "Search result not found.\n" + val);
                Assert.fail("Search result not found" + val);
            } else {
                Assert.fail("Search result not found");
            }
        }
    }

    /********************************
     * #Description : Method to list and verify Dropdown values #Input Parameters: excel data
     */
    public void verifyListOfBrandNamesInDropDown(HashMap<String, Object> data) {
        SoftAssert softAssert = new SoftAssert();
        keywords.APP_LOGS.info("Verifing the brand names in brand list");
        String brandValue = appBasedUtils.getStringData(data, "ExpectedBrand");

        String[] brandName = brandValue.split(",");
        keywords.verifyListOfItemsFromDropDown(brandElement, brandName, softAssert);
        ExtentTestManager.getTest().log(Status.INFO, "verifed the item names in list");
    }

    /********************************
     * #Description : Method to clear all the search criteria
     */
    public void cleardata() {
        // keywords.clearText(importerElement,"Importer");
        // keywords.clearText(dealerElement,"Dealer");
        keywords.clearText(creationDateFromElement, "creation DateFromElement");
        keywords.keyTab(creationDateFromElement);
        keywords.clearText(creationDateToElement, "creation Date To Element");
        keywords.keyTab(creationDateToElement);
        keywords.selectVisibleText(brandElement, "All");
        keywords.keyTab(brandElement);
    }
}
