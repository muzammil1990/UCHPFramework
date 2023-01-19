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

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.UIUtils;

public class PrintOutStandingClaimSummaryCompany {

    WebDriver driver;
    public int i = 0;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    UIUtils keywords = new UIUtils(driver);
    SoftAssert softAssert = new SoftAssert();
    String fromDate;
    String toDate;

    public SoftAssert testCaseAssertion;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Filter')]")
    WebElement filterBtnElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Crediting ']")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Print outstanding claim summary (Company) ')]")
    WebElement printOutStandingSummCompanySubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Print outstanding claim summary (Company)')]")
    WebElement printOutStandingClaimSummCompanyTitleElement;

    @FindBy(how = How.XPATH, using = "(//input[@class='form-control'])[1]")
    WebElement creationDateFromElement;

    @FindBy(how = How.XPATH, using = "(//input[@class='form-control'])[2]")
    WebElement creationDateToElement;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridTableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    int numberOfPreviousDays = 180;

    public PrintOutStandingClaimSummaryCompany(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /***********************************************************************
     * #Description : This is a common function to navigate from "Crediting" main menu to "CreateOutStandingSummary" sub menu
     ***********************************************************************/
    public void navigateToPrintOutStandingSumm() {
        // driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.visibilityOf(homePageLink));
        keywords.performMenuAction(driver, creditingMainMenuElement, printOutStandingSummCompanySubMenuElement, printOutStandingClaimSummCompanyTitleElement);
        // validate whether page navigation is successfull
        wait.until(ExpectedConditions.visibilityOf(printOutStandingClaimSummCompanyTitleElement));
    }

    /***********************************************************************
     * #Description : function to filter claim summary by specifying Creation date #Input Parameters: data: data for the test case creation date
     ***********************************************************************/
    public void filterWithCreationDate(HashMap<String, Object> data) {
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays,DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.input(creationDateFromElement, fromDate, "Creation from Date");
        keywords.keyTab(creationDateFromElement);
        keywords.input(creationDateToElement, toDate, "Creation To date");
        keywords.keyTab(creationDateToElement);
        keywords.clickOnButton(driver, filterBtnElement);
        driver.switchTo().defaultContent();
        String columnheader[] = { "CreationDate", "Download" };
        POCSVerifySearchResultInGrid("creationDate_2 DownloadDoc_3", data, columnheader);
        cleardata();
    }

    /***********************************************************************
     * #Description : function to filter claim summary by specifying Importer, Dealer, Brand #Input Parameters: data: data for the test case i.e Importer,
     * Dealer Brand etc
     ************************************************************************/
    public void filterWithNoConditionAndGridValidatioin(HashMap<String, Object> data) {
        keywords.clickOnButton(driver, filterBtnElement);
        driver.switchTo().defaultContent();
        String columnheader[] = { "Download" };
        POCSVerifySearchResultInGrid("DownloadDoc_3", data, columnheader);
        cleardata();
    }

    /***********************************************************************
     * #Description : Method to verify and validate Grid result in print outstanding claim summary page #Input Parameters: noOfColumnsToValidate: Hexadecimal
     * character used as index in the Xpath data: data for testing i.e impporter, dealer, brand etc columnheader: SearchGrid column names used as key fetch the
     * data from collection.
     ***********************************************************************/
    public void POCSVerifySearchResultInGrid(String noOfColumnsToValidate, HashMap<String, Object> data, String columnheader[]) {
        int columnIndexkInSearchGrid = 0;
        keywords.APP_LOGS.info("Validating search grid  in \"Print Outstanding Claim Summary company\" page...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 4);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            wait.until(ExpectedConditions.visibilityOf(gridTableElement));
            for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {
                if ("creationDate_2 ".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexkInSearchGrid] + "\" Column.");
                    appBasedUtils.validateDate(driver,
                                               XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]",
                                               fromDate, toDate, testCaseAssertion);
                } else if ("DownloadDoc_3 ".contains(hexaStrToXpath)) {
                    keywords.APP_LOGS.info("Validating \"Attachment\" Column.");
                    appBasedUtils.attachementValidationGrid(data, XpathFirstPart
                            + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert, driver, "-Company");
                }
                columnIndexkInSearchGrid++;
            }
            softAssert.assertAll();
        } catch (Exception e) {
            if (keywords.elementIsDisplayed(errorMsg, "Error message pop up"))
                Assert.fail("Error in validation  " + keywords.getTextValue(errorMsg));
            else
                Assert.fail("Fail!!! Search result not found '");
        }
    }

    public void cleardata() {
        keywords.clearText(creationDateFromElement, "creation DateFromElement");
        keywords.keyTab(creationDateFromElement);
        keywords.clearText(creationDateToElement, "creation Date To Element");
    }
}
