package com.volvo.in.page.vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

import junit.framework.Assert;

public class ViewCoverage {
    public Logger APP_LOGS = Logger.getLogger(ViewCoverage.class);
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();

    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle'])[2]")
    WebElement vehicleMainMenuElement;

    @FindBy(how = How.ID, using = "registrationNo")
    WebElement registrationNumberElement;

    @FindBy(how = How.XPATH, using = "//*[@name='chassisseries']")
    WebElement chassisIdPart1Element;

    @FindBy(how = How.XPATH, using = "//*[@name='marketchassisno']")
    WebElement chassisIdPart2Element;

    @FindBy(how = How.ID, using = "vin")
    WebElement VINElement;

    @FindBy(how = How.ID, using = "droplistMenuBrandMarket")
    WebElement brandMarketElement;

    @FindBy(how = How.ID, using = "droplistMenuBrandUnit")
    WebElement brandUnitElement;

    @FindBy(how = How.ID, using = "marketingType")
    WebElement marketTypeElement;

    @FindBy(how = How.ID, using = "chassisNo")
    WebElement chassisNumberElement;

    @FindBy(how = How.ID, using = "brandUnitNo")
    WebElement unitNumberElement;

    @FindBy(how = How.XPATH, using = "RepairDate")
    WebElement validThisDateElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Search')]")
    WebElement searchButtonElement;

    @FindBy(how = How.XPATH, using = "//a[@ui-sref='app.view-coverage' and contains(., 'View warranty coverage ')]")
    WebElement viewCoverageSubMenuElement;

    @FindBy(how = How.XPATH, using = "(//view-coverage/*[contains(., 'View warranty coverage')])[1]")
    WebElement viewCoverageHeaderElement;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-primary btn-collapse']")
    WebElement collapseButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Registration no.')]")
    WebElement registrationNumberLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Valid this date')]")
    WebElement validThisDateLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4'])[20]")
    WebElement chassisIDLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4'])[4]")
    WebElement VINLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Marketing type')]")
    WebElement marketingTypeLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4'])[7]")
    WebElement chassisNumberLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4']/label[contains(.,'Brand')])[1]")
    WebElement BrandMarketLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4']/label[contains(.,'Brand')])[2]")
    WebElement BrandUnitLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Unit no.')]")
    WebElement UnitNumberLableElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'ui-grid-row ng-scope')]")
    List<WebElement> searchResultNumberOfCoverage;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp'])[2]")
    WebElement vehicleInformation;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div/button")
    WebElement closeValidationAlert;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='text-left ui-grid-cell-contents ng-binding ng-scope']]]/div[1])[2]/descendant::span")
    WebElement chassisIdModelDailogueElement;

    @FindBy(how = How.XPATH, using = "//div[@class='panel-heading']/descendant::span")
    WebElement searchResultElement;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    public String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    @FindAll({ @FindBy(xpath = "//div[@class='divTableCell']/../div[2]"), @FindBy(xpath = "//div[@class='divTableCell']/../div[4]"),
            @FindBy(xpath = "//div[@class='divTableCell']/../div[6]"), @FindBy(xpath = "//div[@class='divTableCell']/../div[8]") })
    List<WebElement> vehicleInformationElements;

    // String noOfColumnsToValidateVehiclePopUp="1V 1W 1X 1Y 1Z";
    String noOfColumnsToValidate = "CoverageCode_1 Description_2 Attachment_3 StartDate_4 EndDate_5";

    WebElement webElement;
    List<String> previouseRuncoverageDetails = new ArrayList<String>();

    List<String> previouseRunVehicleDetails = new ArrayList<String>();
    List<String> currentRunVehicleDetails = new ArrayList<String>();

    List<String> coverageDetailsOfVehicle = new ArrayList<String>();
    String vehicleSearchResultColumnNames[] = { "Chassis Id", "Registration number.", "VIN", "Delivering importer", "Delivering date", "Country of operation",
            "Vehicle operation", "Marketing type", "Using Customer", "Unit no.", "Vehicle opearation desc.", "Mileage", "Operating hours", "Delivery mileage" };
    String coverageColumnNames[] = { "Coverage Code", "Description", "Attachment", "StartDate", "EndDate" };
    public static String alertTextMessage = new String();

    File downloadLocation = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
            + "DownloadLocation");

    public ViewCoverage(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /*******************************************
     * #Description : Method to navigate from Vehicle->ViewCoverage
     *******************************************/
    public void navigateToViewCoverage() {
        try {
            keywords.waitTime(5);
            keywords.waitForVisibilityOfElement(driver, 20, vehicleMainMenuElement);
            keywords.performMenuAction(driver, vehicleMainMenuElement, viewCoverageSubMenuElement, viewCoverageHeaderElement);
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /*******************************************
     * #Description : To searching coverage details for vehicle using Chassis Series and Chassis Number. #Input Parameters:
     *******************************************/
    public void searchCoverageforVehicleChassisNumber(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            String ChassisSeries = appBasedUtils.getStringData(data, "ChassisSeries");
            String ChassisNumber = appBasedUtils.getStringData(data, "ChassisNumber");
            keywords.waitForVisibilityOfElement(driver, 20, chassisIdPart1Element);
            keywords.input(chassisIdPart1Element, ChassisSeries, "Chassis series");
            keywords.input(chassisIdPart2Element, ChassisNumber, "Chassis number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                /*keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert,
                                                                                    validationMessageClose);*/
                currentRunVehicleDetails.clear();
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " +ChassisSeries+ChassisNumber+ "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(ChassisSeries + "-" + ChassisNumber, currentRunVehicleDetails.get(0), softassert);
                coverageDetailsOfVehicle.clear();
                getCoverageDetails(noOfColumnsToValidate);
                keywords.waitTime1(2);
                softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
                if (!(coverageDetailsOfVehicle.size() == 0)) {
                    keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
                }
                keywords.clearText(chassisIdPart1Element, "Chassis series");
                keywords.clearText(chassisIdPart2Element, "Chassis number");

            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softassert.assertAll();
    }

    /******************************************
     * #Description : To searching coverage details for vehicle using Registration Number. #Input Parameters:
     ******************************************/
    public void searchCoverageforVehicleRegisterNumber(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, registrationNumberElement);
            keywords.input(registrationNumberElement, appBasedUtils.getStringData(data, "RegistrationNumber"), "Register number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert, validationMessageClose);
            currentRunVehicleDetails.clear();
            persistVehicleInformation();
            keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "RegistrationNumber") + "  is given below:");
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "RegistrationNumber"), currentRunVehicleDetails.get(1), softassert);
            coverageDetailsOfVehicle.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
            if (!(coverageDetailsOfVehicle.size() == 0)) {
                keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
            }
            keywords.clearText(registrationNumberElement, "Register number");

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softassert.assertAll();
    }

    /********************************************
     * #Description : To searching coverage details for vehicle using VIN
     *********************************************/
    public void searchCoverageforVehicleUsingVINField(HashMap<String, Object> data) {
        try {
            SoftAssert softassert = new SoftAssert();
            keywords.waitForVisibilityOfElement(driver, 20, VINElement);
            keywords.input(VINElement, appBasedUtils.getStringData(data, "VIN"), "VIN/Register number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert, validationMessageClose);
            persistVehicleInformation();
            keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "VIN") + "  is given below:");
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "VIN"), currentRunVehicleDetails.get(2), softAssert);
            coverageDetailsOfVehicle.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
            if (!(coverageDetailsOfVehicle.size() == 0)) {
                keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
            }
            keywords.clearText(VINElement, "VIN ");
            softassert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    /******************************************
     * #Description : Method to validate whether proper vehicle information present in the search grid.
     ******************************************/
    public void validateElement(WebElement gridElement, String expectedText) {
        try {
            keywords.assertEqual(expectedText, gridElement.getText(), softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /*****************************************
     * #Description : Method to get the coverage details for the verhicle.
     *****************************************/
    public void getCoverageDetails(String noOfColumnsToValidate) {
        try {
            keywords.APP_LOGS.info("Number of coverages for the vehicle is::" + searchResultNumberOfCoverage.size());
            for (int row = 0; row < searchResultNumberOfCoverage.size(); row++) {
                for (String xpathSecondPart : noOfColumnsToValidate.split("\\s")) {
                    coverageDetailsOfVehicle.add(getCoverageTextFromGRID(driver, XpathFirstPart
                            + xpathSecondPart.substring(xpathSecondPart.indexOf('_') + 1, xpathSecondPart.length()) + "]"));
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /*******************************************
     * #Description : Method to verify all search fields in the view coverage page
     *********************************************/
    public void verifyViewCovergeGUI() {
        SoftAssert sa = new SoftAssert();
        try {
            keywords.APP_LOGS.info("Verifying search fields in View coverage page");
            keywords.APP_LOGS.info("Verifying \"Registration Number\" Field...");
            keywords.waitForVisibilityOfElement(driver, 20, registrationNumberElement);
            appBasedUtils.asserVerification("Registration no. *", registrationNumberLableElement.getText(), sa);
            keywords.APP_LOGS.info("Verifying \"Valid this date\" Field...");
            appBasedUtils.asserVerification("Valid this date", validThisDateLableElement.getText(), sa);
            if (companyNameElement.getText().equals("VOLVO BUS CORPORATION") || (companyNameElement.getText().equals("TSM AMERICAS"))
                    || (companyNameElement.getText().equals("VOLVO TRUCK CORPORATION"))) {
                keywords.APP_LOGS.info("Verifying \"Brand(MarketType)\" Field...");
                appBasedUtils.asserVerification("Brand *", BrandMarketLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"Market type\" Field...");
                appBasedUtils.asserVerification("Marketing type *", marketingTypeLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"Brand(UnitNo.)\" Field...");
                appBasedUtils.asserVerification("Brand *", BrandUnitLableElement.getText(), sa);
                keywords.elementIsDisplayed(BrandUnitLableElement, "Brand(Unit)");
                keywords.APP_LOGS.info("Verifying \"Unit number\" Field...");
                appBasedUtils.asserVerification("Unit no. *", UnitNumberLableElement.getText(), sa);
            }
            if (companyNameElement.getText().equalsIgnoreCase("VOLVO CONSTRUCTION EQUIPMENT")) {
                keywords.APP_LOGS.info("Verifying \"Machine ID\" Field...");
                appBasedUtils.asserVerification("Machine id. *", chassisIDLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"PIN\" Field...");
                appBasedUtils.asserVerification("PIN *", VINLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"Serial Number\" Field...");
                appBasedUtils.asserVerification("Serial no.. *", chassisNumberLableElement.getText(), sa);
            }
            if (companyNameElement.getText().equals("VOLVO PENTA CORPORATION")) {
                keywords.APP_LOGS.info("Verifying \"Driveline Id\" Field...");
                appBasedUtils.asserVerification("Driveline id. *", chassisIDLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"HIN//Machine ID//VIN *\" Field...");
                appBasedUtils.asserVerification("HIN/Machine ID/VIN *", VINLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"Driveline Number\" Field...");
                appBasedUtils.asserVerification("Driveline no. *", chassisNumberLableElement.getText(), sa);
            }

        }

        catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
            sa.assertTrue(false, "Element not found");
        }

        sa.assertAll();
    }

    /***************************************
     * #Description : returns coverage
     *****************************************/
    public String getCoverageTextFromGRID(WebDriver driver, String Xpath) {
        String strvehicleCoverage = new String();
        try {
            webElement = driver.findElement(By.xpath(Xpath));
            strvehicleCoverage = webElement.getText();
        }

        catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
        return strvehicleCoverage;
    }

    /********************************
     * #Description : Method to compare vehicle coverage details of two runs
     *******************************/
    public void compareCoveages(List<String> currentRuncoverageDetails, List<String> previouseRuncoverageDetails) {
        try {
            softAssert.assertTrue(keywords.compareList(previouseRuncoverageDetails, currentRuncoverageDetails));
            {
                keywords.APP_LOGS.info("Coverage is matched with the previouse test case coverage!!!");
                keywords.APP_LOGS.info("Previous Test case coverage details as follows:");
                keywords.printAllElements(previouseRuncoverageDetails, coverageColumnNames);
                keywords.APP_LOGS.info("Current Test case coverage details as follows:");
                keywords.printAllElements(currentRuncoverageDetails, coverageColumnNames);
            }
            softAssert.assertAll();
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }

    }

    /*******************************************
     * #Description : Method to save vehicle information to ArrayList placeholder
     *******************************************/
    public void persistVehicleInformation() {
        try {
            keywords.waitForVisibilityOfElement(driver, 20, vehicleInformationElements.get(0));
            for (int i = 0; i < vehicleInformationElements.size(); i++)
                currentRunVehicleDetails.add(vehicleInformationElements.get(i).getText());
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /******************************************
     * #Description : Method to compare vehicle details of two search criteria.
     *******************************************/

    public void compareVehicleInformation(List<String> currentRunVehicleDetails, List<String> previouseRunVehicleDetails) {
        softAssert.assertTrue(keywords.compareList(previouseRunVehicleDetails, currentRunVehicleDetails), "Expected is Chassis ID is:"
                + previouseRunVehicleDetails.get(0) + " :: But actual Chassis ID is :" + currentRunVehicleDetails.get(0));
        keywords.APP_LOGS.info("Previous Search vehicle information as follows:");
        keywords.printAllElements(previouseRunVehicleDetails, vehicleSearchResultColumnNames);
        keywords.APP_LOGS.info("Current search vehicle information as follows:");
        keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
        softAssert.assertAll();
    }

    /******************************************
     * #Description : Search coverage with Brand, MarketType and chassis Number search criteria #Input Parameters: excel data
     ******************************************/
    public void searchWithBrandMarketTypeChassisID(HashMap<String, Object> data) {
        try {
            SoftAssert softassert = new SoftAssert();
            keywords.waitForVisibilityOfElement(driver, 20, brandMarketElement);
            keywords.selectVisibleText(brandMarketElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(marketTypeElement, appBasedUtils.getStringData(data, "MarketType"), "Market Type");
            keywords.input(chassisNumberElement, appBasedUtils.getStringData(data, "ChassisNumber"), "Market Type");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
          /* keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, chassisIdModelDailogueElement, commonMessageAfterAlert,
                                                                                validationMessageClose);
            keywords.clickOnButton(chassisIdModelDailogueElement);
            driver.switchTo().defaultContent();*/
            keywords.waitForVisibilityOfElement(driver, 20, vehicleInformation);
            currentRunVehicleDetails.clear();
            persistVehicleInformation();
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries2") + " - " + appBasedUtils.getStringData(data, "ChassisNumber"),
                                            currentRunVehicleDetails.get(0), softAssert);
            coverageDetailsOfVehicle.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
            if (!(coverageDetailsOfVehicle.size() == 0)) {
                keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
            }
            softassert.assertAll();

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    /******************************************
     * #Description : Search coverage with Brand and unit number search criteria #Input Parameters: excel data
     */
    public void searchWithBrandUnitNumber(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandUnitElement);
            keywords.selectVisibleText(brandUnitElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(unitNumberElement, appBasedUtils.getStringData(data, "UnitNumber"), "Unit number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, chassisIdModelDailogueElement, commonMessageAfterAlert,
                                                                                validationMessageClose);
            keywords.clickOnLink(chassisIdModelDailogueElement, driver);
            driver.switchTo().defaultContent();
            keywords.waitForVisibilityOfElement(driver, 4, vehicleInformation);
            currentRunVehicleDetails.clear();
            persistVehicleInformation();
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries2") + " - " + appBasedUtils.getStringData(data, "ChassisNumber"),
                                            currentRunVehicleDetails.get(0), softAssert);
            coverageDetailsOfVehicle.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
            if (!(coverageDetailsOfVehicle.size() == 0)) {
                keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
            }
            softassert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }

    }

    /*************************************
     * #Description : Search coverage with Brand and unit number search criteria #Input Parameters: excel data
     *************************************/
    public void searchWithBrandUnitNumberSingleVehicleScenario(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandUnitElement);
            keywords.selectVisibleText(brandUnitElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(unitNumberElement, appBasedUtils.getStringData(data, "UnitNumber"), "Unit number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert, validationMessageClose);
            currentRunVehicleDetails.clear();
            persistVehicleInformation();
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries") + " - " + appBasedUtils.getStringData(data, "ChassisNumber"),
                                            currentRunVehicleDetails.get(0), softassert);
            coverageDetailsOfVehicle.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
            if (!(coverageDetailsOfVehicle.size() == 0)) {
                keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
            }
            softassert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    /************************************
     * #Description : Search coverage with Brand and unit number search criteria #Input Parameters: excel data
     ************************************/
    public void searchWithBrandMarketTypeChassisNumberSingleVehicleScenario(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandMarketElement);
            keywords.selectVisibleText(brandMarketElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(marketTypeElement, appBasedUtils.getStringData(data, "MarketType"), "Market type");
            keywords.input(chassisNumberElement, appBasedUtils.getStringData(data, "ChassisNumber"), "Chassis number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert, validationMessageClose);
            currentRunVehicleDetails.clear();
            persistVehicleInformation();
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries") + " - " + appBasedUtils.getStringData(data, "ChassisNumber"),
                                            currentRunVehicleDetails.get(0), softassert);
            coverageDetailsOfVehicle.clear();
            getCoverageDetails(noOfColumnsToValidate);
            keywords.waitTime1(2);
            softassert.assertNotEquals(0, coverageDetailsOfVehicle.size(), "No coverage for the vehicle");
            if (!(coverageDetailsOfVehicle.size() == 0)) {
                keywords.printAllElements(coverageDetailsOfVehicle, coverageColumnNames);
            }
            softassert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    /***************************************************
     * #Description : Search with brand and credit document number
     **************************************************/
    public void verifyListOfBrandNamesInDropDown(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        keywords.APP_LOGS.info("Verifing the brand names in brand list");
        String brandValue = appBasedUtils.getStringData(data, "ExpectedBrand");
        String[] brandName = brandValue.split(",");
        keywords.waitTime1(3);
        keywords.verifyListOfItemsFromDropDown(brandMarketElement, brandName, softAssert);
    }

    /**********************************************
     * #Description : Method to get the coverage details for the verhicle. #Input Parameters:
     **********************************************/
    public void downLoadCoverageAttachment() {
        SoftAssert sa = new SoftAssert();
        BaseClass baseclass = new BaseClass();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
        try {
            for (int row = 1; row < searchResultNumberOfCoverage.size(); row++) {
                for (int coverageCol = 4; coverageCol <= 8; coverageCol++) {
                    if (coverageCol == 6) {
                        String Xpath = "//i[contains(@class,'print-download-file')]";
                        WebElement ele = driver.findElement(By.xpath(Xpath));     
                        if (keywords.elementIsDisplayed(ele, "attachment")){
                            pdfReadAndValidate.downloadDocuments(baseclass.getBrowserType(), ele, baseclass.getDriver(), downloadLocation);                           
                        }
                    } 
                }
                break;
            }
        } catch (Exception e) {
            sa.assertTrue(false, "Coverage does not have attachement");
            APP_LOGS.error(e.getMessage());
        }
        sa.assertAll();
    }
}
