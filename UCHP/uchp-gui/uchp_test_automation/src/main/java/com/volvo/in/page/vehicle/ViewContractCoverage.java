package com.volvo.in.page.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.UIUtils;

public class ViewContractCoverage {
    public Logger APP_LOGS = Logger.getLogger(ViewContractCoverage.class);
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;
    
    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle'])[2]")
    WebElement vehicleMainMenuElement;

    @FindBy(how = How.ID, using = "importerNo")
    WebElement importerElement;

    @FindBy(how = How.ID, using = "operationNoFrom")
    WebElement operationNoFromElement;

    @FindBy(how = How.ID, using = "operationNoTo")
    WebElement operationNoToElement;

    @FindBy(how = How.ID, using = "contractNo")
    WebElement contractNoElement;

    @FindBy(how = How.ID, using = "operationDescription")
    WebElement operationDescriptionElement;

    @FindBy(how = How.ID, using = "elementType")
    WebElement elementDropDownElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Search')]")
    WebElement searchButtonElement;

    @FindBy(how = How.XPATH, using = "//a[@ui-sref='app.view-contract-coverage' and contains(., 'View contract coverage ')]")
    WebElement viewContractCoverageSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[text()='View contract coverage']")
    WebElement viewContractCoverageHeaderElement;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-collapse ng-binding']")
    WebElement collapseButtonElement;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label wrapping-control-label ng-binding' and contains(.,'Importer')]")
    WebElement importerNumberLableElement;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label wrapping-control-label ng-binding' and contains(.,'Contract no')]")
    WebElement contractNoLableElement;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label wrapping-control-label ng-binding' and contains(.,'Operation no.')]")
    WebElement operationNoLableElement;

    @FindBy(how = How.XPATH, using = "//label[contains(.,'Operation description')]")
    WebElement operationDescriptionLableElement;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label wrapping-control-label ng-binding' and contains(.,'Element')]")
    WebElement elementLableElement;
   
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'ui-grid-row ng-scope')]")
    List<WebElement> searchResultNumberOfOperationNo;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp'])[2]")
    WebElement coverageInformation;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div/button")
    WebElement closeValidationAlert;

    @FindBy(how = How.XPATH, using = "//div[@class='panel-heading']/descendant::span")
    WebElement searchResultElement;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    public String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    @FindAll({ @FindBy(xpath = "//single-view-contract-coverage/descendant::div[10]"), @FindBy(xpath = "//single-view-contract-coverage/descendant::div[15]"),
            @FindBy(xpath = "//single-view-contract-coverage/descendant::div[20]")})
    List<WebElement> coverageInformationElements;

    String noOfColumnsToValidate = "OperationNo_1 OperationDescription_2 AgeFrom_3 AgeTo_4 MilageFrom_5 MilageTo_6 OperatinghoursFrom_7 OperatinghoursTo_8 Element_9";

    WebElement webElement;
    List<String> previouseRuncoverageDetails = new ArrayList<String>();

    List<String> previousecoverageInformation = new ArrayList<String>();
    List<String> currentcoverageInformation = new ArrayList<String>();

    List<String> contractCoverageDetails = new ArrayList<String>();
    String coverageSearchResultColumnNames[] = { "Importer", "Contract no.", "Contract type code" };
    String coverageColumnNames[] = { "Operation no.", "Operation description", "Age From", "Age To", "Milage From", "Milage To", "Operating hours From", "Operating hours To", "Element" };
   
    public static String alertTextMessage = new String();


    public ViewContractCoverage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*******************************************
     * #Description : Method to navigate from Vehicle->ViewCoverage
     *******************************************/
    public void navigateToViewContractCoverage() {
        try {
            keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
            keywords.clickOnLink(homePageLink, driver);
            keywords.waitForVisibilityOfElement(driver, 8, vehicleMainMenuElement);
            keywords.waitTime1(4);
            keywords.performMenuAction(driver, vehicleMainMenuElement, viewContractCoverageSubMenuElement, viewContractCoverageHeaderElement);
            keywords.waitForVisibilityOfElement(driver, 8, viewContractCoverageHeaderElement);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /********************************************
     * #Description : Method to verify all search fields in the view Contract coverage page
     *********************************************/
    public void verifyViewContractCovergeGUI() {
        SoftAssert sa = new SoftAssert();
        try {
            keywords.APP_LOGS.info("Verifying search fields in View contract coverage page");
            keywords.APP_LOGS.info("Verifying \"Importer\" Field...");
            keywords.waitForVisibilityOfElement(driver, 20, importerElement);
            appBasedUtils.asserVerification("Importer *", importerNumberLableElement.getText(), sa);
            keywords.waitForVisibilityOfElement(driver, 20, contractNoLableElement);
            keywords.APP_LOGS.info("Verifying \"Contract no.\" Field...");
            appBasedUtils.asserVerification("Contract no. *", contractNoLableElement.getText(), sa);
            keywords.APP_LOGS.info("Verifying \"Operation no.\" Field...");
            appBasedUtils.asserVerification("Operation no.", operationNoLableElement.getText(), sa);
            keywords.APP_LOGS.info("Verifying \"Operation description\" Field...");
            appBasedUtils.asserVerification("Operation description", operationDescriptionLableElement.getText(), sa);
            keywords.APP_LOGS.info("Verifying \"Element\" Field...");
            appBasedUtils.asserVerification("Element", elementLableElement.getText(), sa);
        }

        catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            sa.assertTrue(false, "Element not found");
        }

        sa.assertAll();
    }        
    
    /********************************************
     * #Description : To searching coverage details for vehicle using Importer, contract no. , Operation ranges and element type 
     *********************************************/
    public void searchContractCoverage(HashMap<String, Object> data) {
        try {
            SoftAssert softassert = new SoftAssert();
            keywords.waitForVisibilityOfElement(driver, 20, importerElement);
            keywords.input(importerElement  , appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.input(contractNoElement  , appBasedUtils.getStringData(data, "Contract no."), "Contract no.");
            keywords.input(operationNoFromElement, appBasedUtils.getStringData(data, "Operation no. From"), "Operation number From" );
            keywords.input(operationNoToElement, appBasedUtils.getStringData(data, "Operation no. To"), "Operation number To" );
            keywords.selectVisibleText(elementDropDownElement, appBasedUtils.getStringData(data, "Element"));
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, coverageInformation, commonMessageAfterAlert, validationMessageClose);
            persistCoverageInformation();
            keywords.APP_LOGS.info("Details of contract coverage " + appBasedUtils.getStringData(data, "Contract no.") + "  is given below:");
            keywords.printAllElements(currentcoverageInformation, coverageSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "Contract no."), currentcoverageInformation.get(1), softAssert);
            currentcoverageInformation.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, contractCoverageDetails.size(), "Total Operation No Linked to Contract");
            if (!(contractCoverageDetails.size() == 0)) {
                keywords.printAllElements(contractCoverageDetails, coverageColumnNames);
            }
            keywords.clearText(contractNoElement, "Contract no.");
            keywords.clearText(importerElement, "Importer");
            keywords.clearText(operationNoFromElement, "Operation no. From");
            keywords.clearText(operationNoToElement, "Operation no. To");
            softassert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }
    
    /*******************************************
     * #Description : Method to save vehicle information to ArrayList placeholder
     *******************************************/
    public void persistCoverageInformation() {
        try {
            keywords.waitForVisibilityOfElement(driver, 20, coverageInformationElements.get(0));
            for (int i = 0; i < coverageInformationElements.size(); i++)
                currentcoverageInformation.add(coverageInformationElements.get(i).getText());
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }
    
    /*****************************************
     * #Description : Method to get the coverage details for the verhicle.
     *****************************************/
    public void getCoverageDetails(String noOfColumnsToValidate) {
        try {
            keywords.APP_LOGS.info("Number of coverages for the vehicle is::" + searchResultNumberOfOperationNo.size());
            for (int row = 0; row < searchResultNumberOfOperationNo.size(); row++) {
                for (String xpathSecondPart : noOfColumnsToValidate.split("\\s")) {
                    contractCoverageDetails.add(getCoverageTextFromGRID(driver, XpathFirstPart
                            + xpathSecondPart.substring(xpathSecondPart.indexOf('_') + 1, xpathSecondPart.length()) + "]"));
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }
    
    /***************************************
     * #Description : returns coverage
     *****************************************/
    public String getCoverageTextFromGRID(WebDriver driver, String Xpath) {
        String strCoverage = new String();
        try {
            webElement = driver.findElement(By.xpath(Xpath));
            strCoverage = webElement.getText();
        }

        catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return strCoverage;
    }
}
