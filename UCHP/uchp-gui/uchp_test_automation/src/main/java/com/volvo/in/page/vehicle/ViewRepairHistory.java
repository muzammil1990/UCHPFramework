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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class ViewRepairHistory {
    public Logger APP_LOGS = Logger.getLogger(ViewRepairHistory.class);

    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();

    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";

    String companyName;

    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle'])[2]")
    WebElement vehicleMainMenuElement;

    @FindBy(how = How.ID, using = "registrationNo")
    WebElement registrationNumberElement;

    @FindBy(how = How.XPATH, using = "//*[@name='chassisseries']")
    WebElement chassisIdPart1Element;

    @FindBy(how = How.XPATH, using = "//input[@id='chassisSeries']")
    WebElement chassisSeries;

    @FindBy(how = How.XPATH, using = "//input[@id='chassisNo']")
    WebElement chassisNumber;

    @FindBy(how = How.XPATH, using = "//*[@name='marketchassisno']")
    WebElement chassisIdPart2Element;

    @FindBy(how = How.ID, using = "grid_multiple_view_coverage")
    WebElement labelVehicleList;

    @FindBy(how = How.ID, using = "vin")
    WebElement VINElement;

    @FindBy(how = How.ID, using = "droplistMenuBrandMarket")
    WebElement brandMarketElement;

    @FindBy(how = How.ID, using = "droplistMenuBrandUnit")
    WebElement brandUnitElement;

    @FindBy(how = How.ID, using = "marketingType")
    WebElement marketTypeElement;

    @FindBy(how = How.ID, using = "marketchassisno")
    WebElement chassisNumberElement;

    @FindBy(how = How.ID, using = "brandUnitNo")
    WebElement unitNumberElement;

    @FindBy(how = How.XPATH, using = "//button[@type='submit' and contains(., 'Search')]")
    WebElement searchButtonElement;

    @FindBy(how = How.XPATH, using = "//a[@ui-sref='app.view-repair-history' and contains(., 'View repair history ')]")
    WebElement viewRepairHistorySubMenuElement;

    @FindBy(how = How.XPATH, using = "(//view-repair-history/*[contains(., 'repair history')])[1]")
    WebElement viewRepairHistoryHeaderElement;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-primary btn-collapse']")
    WebElement collapseButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-sm-6']/label[contains(., 'Chassis Id')]/../../div[@class='col-sm-6 ng-binding']")
    WebElement searchResultChassisID;

    @FindBy(how = How.XPATH, using = "//div[@class='col-sm-6']/label[contains(., 'VIN')]/../../div[@class='col-sm-6 ng-binding']")
    WebElement searchResultVIN;

    @FindBy(how = How.XPATH, using = "//div[@class='col-sm-6']/label[contains(., 'Registration No.')]/../../div[@class='col-sm-6 ng-binding']")
    WebElement searchResultRegistrationNumber;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Registration no.')]")
    WebElement registrationNumberLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-lg-4'])[4]")
    WebElement chassisIDLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4'])[2]")
    WebElement VINLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Marketing type')]")
    WebElement marketingTypeLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4'])[5]")
    WebElement chassisNumberLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4']/label[contains(.,'Brand')])[1]")
    WebElement BrandMarketLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-5 col-lg-4']/label[contains(.,'Brand')])[2]")
    WebElement BrandUnitLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Unit no.')]")
    WebElement UnitNumberLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Repair date')]")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-pager-count']")
    WebElement gridPageCount;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//div[@class='fieldValueOverflow ng-binding']")
    WebElement chassisId;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Status')]/../../div[2]")
    WebElement status;

    @FindBy(how = How.XPATH, using = "(//*[contains(text(),'Delivering ')]//following::div)[1]")
    WebElement deliveryImporter;

    @FindBy(how = How.XPATH, using = "(//*[contains(text(),'Age')]//following::div)[1]")
    WebElement age;

    @FindBy(how = How.XPATH, using = "(//*[contains(text(),'Vehicle operation')]//following::div)[1]")
    WebElement vehicleOperation;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label ng-binding'][contains(text(),'Registration no.')]//following::div[1]")
    WebElement regNo;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering dealer')]//following::div[1]")
    WebElement deliveryDealer;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering date')]//following::div[1]")
    WebElement deliveryDate;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Vehicle operation desc.')]//following::div[1]")
    WebElement vehicleOperatDesc;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label ng-binding'][contains(text(),'VIN')]//following::div[1]")
    WebElement VIN;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Country of operation')]//following::div[1]")
    WebElement countryOfOperation;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Mileage (km)')]//following::div[1]")
    WebElement mileage;

    @FindBy(how = How.XPATH, using = "//label[@class='control-label ng-binding'][contains(text(),'Marketing type')]//following::div[1]")
    WebElement marketingType;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Using customer')]//following::div[1]")
    WebElement usingCustomer;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivery mileage (km)')]//following::div[1]")
    WebElement deliveryMileageVH;

    @FindBy(how = How.XPATH, using = "//div[@class='divTableCell']//label[@class='control-label ng-binding'][contains(text(),'Unit no.')]//following::div[1]")
    WebElement unitNo;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-pager-count']")
    WebElement noOfItem;

    @FindBy(how = How.XPATH, using = "(//a[@class='ng-binding'])[2]")
    WebElement firstRow;

    @FindBy(how = How.XPATH, using = "//*[text()='Chassis id']//following::div[1]")
    WebElement claimRepairChassisID;

    @FindBy(how = How.XPATH, using = "//*[text()='Machine id']//following::div[1]")
    WebElement claimRepairMachineID;

    @FindBy(how = How.XPATH, using = "//*[text()='Driveline id']//following::div[1]")
    WebElement claimRepairDrivelineId;

    @FindBy(how = How.XPATH, using = "//b[contains(text(),'Importer')]//following::div[1]")
    WebElement claimRepairImporter;

    @FindBy(how = How.XPATH, using = "//b[contains(text(),'Marketing')]//following::div[1]")
    WebElement claimRepairMarketingType;

    @FindBy(how = How.XPATH, using = "//b[contains(text(),'Using customer')]//following::div[1]")
    WebElement claimRepairUsingCustomer;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Header')]")
    WebElement header;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Registration no.')]/../../div[2]")
    WebElement claimRegNo;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Chassis id')]/../../div[2]")
    WebElement claimChassisId;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Machine id')]/../../div[2]")
    WebElement claimMachineId;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Driveline id')]/../../div[2]")
    WebElement claimDrivelineId;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'VIN')]/../../div[2]")
    WebElement claimVIN;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Marketing type')]/../../div[2]")
    WebElement claimMarketingType;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Chassis no')]/../../div[2]")
    WebElement claimChassisNo;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Serial no')]/../../div[2]")
    WebElement serialNo;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Driveline no.')]/../../div[2]")
    WebElement drivelineNo;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering importer')]/../../div[2]")
    WebElement claimDeliveringImp;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering warranty area')]/../../div[2]")
    WebElement claimWarrantyArea;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering warranty')]/../../div[2]")
    WebElement claimDeliveringWarranty;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering business')]/../../div[2]")
    WebElement claimDeliveringBusinessPartner;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivering dealer')]/../../div[2]")
    WebElement claimDeliveringDealer;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Delivery date')]/../../div[2]")
    WebElement claimDeliveryDate;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Using cust')]/../../div[2]")
    WebElement claimUsingCust;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Operating hours')]/../../div[6]")
    WebElement headerOperatingHours;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement close;

    @FindAll({ @FindBy(xpath = "//div[@class='divTableCell']/../div[2]"), @FindBy(xpath = "//div[@class='divTableCell']/../div[4]"),
            @FindBy(xpath = "//div[@class='divTableCell']/../div[6]"), @FindBy(xpath = "//div[@class='divTableCell']/../div[8]") })
    List<WebElement> vehicleInformationElements;

    String columnheaderWarranty[] = { "Type", "Repair Date", "Claim Job Id", "Repair Order No.", "Milage(km)/OperatingHours", "Age", "Main Op", "Casual Part",
            "Casual part name", "SCC" };
    String noOfColumnsToValidateWarranty = "ClaimType_1 RepDate_2 ClaimJobId_3 RepOrderNum_4 Mileage_5 Age_6 MainOp_7 CausualPart_8 CausalPartName_9 SCC_10";

    String noOfColumnsToValidateVehiclePopUpWarrantyContract = "1V 1W 1X 1Y 1Z 20 21 22 23 24 25 26";
    String noOfColumnsToValidateVehiclePopUpWarranty = "1V 1W 1X 1Y 1Z 20 21 22 23 24";

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp'])[2]")
    WebElement vehicleInformation;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='text-left ui-grid-cell-contents ng-binding ng-scope']]]/div[1])[2]/descendant::span")
    WebElement vinModelDailogueElement;

    @FindBy(how = How.XPATH, using = "//input[@id='contractNo']")
    WebElement Contractno;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfConcern']")
    WebElement dropdownTypeOfConcern;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfConcern']")
    WebElement deliveryMileage;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    public String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    String columnheaderWarrantyContract[] = { "Type of concern", "Type", "Repair Date", "Claim Job Id", "Repair Order No.", "Milage(km)", "Age", "Main Op",
            "Casual Part", "Casual part name", "SCC", "Contract no." };
    String noOfColumnsToValidateWarrantyContract = "TypeOfConcern_1 ClaimType_2 RepDate_3 ClaimJobId_4 RepOrderNum_5 Mileage_6 Age_7 MainOperation_8 CasualPart_9 CasualPartName_10 SCC_11 ContractNo_12";

    String alertTextMessage = new String();
    String currentCompany;
    String vehicleRepairDate = " //div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-009Y']";
    String vehicleSearchResultColumnNames[] = { "Chassis id.", "Registration number", "Delivering importer", "Delivering dealer", "Delivering date", "Age",
            "Marketing type", "Using Customer", "Unit no.", "Delivery mileage", "Mileage", "Operating hours", "Vehicle operation", "Vehilce operation desc." };
    String vehicleSearchResultColumnNamesVCEPenta[] = { "Chassis id.", "Registration number", "Delivering importer", "Delivering dealer", "Delivering date",
            "Age", "Marketing type", "Using Customer", "Unit no.", "Operating hours", "Vehicle operation", "Vehilce operation desc." };

    List<String> previouseRunVehicleDetails = new ArrayList<String>();
    List<String> currentRunVehicleDetails = new ArrayList<String>();

    public ViewRepairHistory(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /**
     * # #Function Name : navigateToViewRepairHistory()
     * 
     * #Description : Method to navigate from Vehicle->ViewRepairHistory #Date of creation : 15-11-2016 #Input Parameters:
     * 
     * 15-11-2016
     */
    public void navigateToViewRepairHistory() {
        try {
            keywords.performMenuAction(driver, vehicleMainMenuElement, viewRepairHistorySubMenuElement, viewRepairHistoryHeaderElement);
            currentCompany = companyNameElement.getText();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /*
     * # #Function Name : searchVehicleRepairInformationChassisNumber()
     * 
     * #Description : Method to view view vehicle repair details using Chassis series and chassis number #Date of creation : 15-11-2016 #Input Parameters:
     * 
     * 15-11-2016
     */
    public void searchVehicleRepairInformationChassisNumber(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        try {
            String ChassisSeries = appBasedUtils.getStringData(data, "ChassisSeries");
            String ChassisNumber = appBasedUtils.getStringData(data, "ChassisNumber");
            keywords.waitForVisibilityOfElement(driver, 10, chassisIdPart1Element);
            keywords.input(chassisIdPart1Element, ChassisSeries, "Chassi series");
            keywords.input(chassisIdPart2Element, ChassisNumber, "Chassis number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert,
                                                                                    validationMessageClose);
                currentRunVehicleDetails.clear();
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + ChassisSeries + ChassisNumber + "  is given below:");
                printVehicleInformation();
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(ChassisSeries + "-" + ChassisNumber, currentRunVehicleDetails.get(0), softAssert);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softAssert.assertAll();
        keywords.clearText(chassisIdPart1Element, "Chassis Series");
        keywords.clearText(chassisIdPart2Element, "Chassis number");
    }

    /*
     * # #Function Name : searchVehicleRepairInformationVIN()
     * 
     * #Description : Method to view view vehicle repair details using VIN #Date of creation : 15-11-2016 #Input Parameters:
     * 
     * 15-11-2016
     */
    public void searchVehicleRepairInformationVIN(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 10, VINElement);
            keywords.input(VINElement, appBasedUtils.getStringData(data, "VIN"), "VIN");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert,
                                                                                    validationMessageClose);
                currentRunVehicleDetails.clear();
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "VIN") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
                keywords.clearText(VINElement, "VIN");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    /*****
     * # #Function Name : searchCoverageforVehicleChassisNumber()
     * 
     * #Description : To searching coverage details for vehicle using Registration Number #Date of creation : 26-10-2016 #Input Parameters:
     * 
     * 26-10-2016
     */
    public void ViewRepairHistoryforVehicleRegisterNumber(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 10, registrationNumberElement);
            keywords.input(registrationNumberElement, appBasedUtils.getStringData(data, "RegistrationNumber"), "Register number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert,
                                                                                    validationMessageClose);
                currentRunVehicleDetails.clear();
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "RegistrationNumber") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "RegistrationNumber"), currentRunVehicleDetails.get(1), softAssert);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softAssert.assertAll();
        keywords.clearText(registrationNumberElement, "Register number");
    }

    /*
     * # #Function Name : validateElement()
     * 
     * #Description : Method to save vehicle information to ArrayList placeholder #Date of creation : 15-11-2016 #Input Parameters:
     * 
     * 15-11-2016
     */
    public void persistVehicleInformation() {
        try {
            keywords.waitForVisibilityOfElement(driver, 20, vehicleInformationElements.get(0));
            for (int i = 0; i < vehicleInformationElements.size(); i++)
                currentRunVehicleDetails.add(vehicleInformationElements.get(i).getText());
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /*
     * # #Function Name : validateElement()
     * 
     * #Description : Method to save vehicle information to ArrayList placeholder #Date of creation : 15-11-2016 #Input Parameters:
     * 
     * 15-11-2016
     */

    public void compareVehicleDetails(List<String> currentRunVehicleDetails, List<String> previouseRunVehicleDetails) {
        try {
            softAssert.assertTrue(keywords.compareList(previouseRunVehicleDetails, currentRunVehicleDetails), "Expected is Chassis ID is:"
                    + previouseRunVehicleDetails.get(0) + " :: But actual Chassis ID is :" + currentRunVehicleDetails.get(0));
            keywords.APP_LOGS.info("Previous search vehicle information as follows:");
            keywords.printAllElements(previouseRunVehicleDetails, vehicleSearchResultColumnNames);
            keywords.APP_LOGS.info("Current search vehicle information as follows:");
            keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
            softAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /**
     * # #Function Name : verifyViewRepairHistoryGUI()
     * 
     * #Description : Method to verify all search fields in the view Repair history page #Date of creation : 15-11-2016 #Input Parameters:
     * 
     * 15-11-2016
     */
    public void verifyViewRepairHistoryGUI() {
        SoftAssert sa = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, chassisIDLableElement);
            if (currentCompany.equalsIgnoreCase("VOLVO CONSTRUCTION EQUIPMENT")) {
                keywords.APP_LOGS.info("Verifying \"Machine ID\" Field...");
                appBasedUtils.asserVerification("Machine id *", chassisIDLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"PIN\" Field...");
                appBasedUtils.asserVerification("PIN *", VINLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"Machine Number\" Field...");
                appBasedUtils.asserVerification("Serial no. ", chassisNumberLableElement.getText(), sa);
            } else if (currentCompany.equalsIgnoreCase("Volvo Penta Corporation")) {
                keywords.APP_LOGS.info("Verifying \"Driveline Id\" Field...");
                appBasedUtils.asserVerification("Driveline id *", chassisIDLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"HIN/Machine ID/VIN *\" Field...");
                appBasedUtils.asserVerification("HIN/Machine ID/VIN *", VINLableElement.getText(), sa);
                keywords.APP_LOGS.info("Verifying \"Driveline Number\" Field...");
                appBasedUtils.asserVerification("Driveline no. ", chassisNumberLableElement.getText(), sa);
            } else {
                keywords.APP_LOGS.info("Verifying search fields in View coverage page");
                keywords.APP_LOGS.info("Verifying \"Registration Number\" Field...");
                appBasedUtils.asserVerification("Registration no. *", registrationNumberLableElement.getText(), sa);
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
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
            sa.fail(e.toString());
        }
        sa.assertAll();
    }

    /**
     * # #Function Name : searchWithBrandMarketTypeChassisID()
     * 
     * #Description : Search coverage with Brand, MarketType and chassis Number search criteria #Date of creation : 26-10-2016 #Input Parameters:
     * 
     * 26-10-2016
     */
    public void searchWithBrandMarketTypeChassisID(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandMarketElement);
            keywords.selectVisibleText(brandMarketElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(marketTypeElement, appBasedUtils.getStringData(data, "MarketType"), "Market Type");
            keywords.input(chassisNumber, appBasedUtils.getStringData(data, "ChassisNumber"), "Market Type");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElement(driver, 10, vehicleInformation);
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "ChassisSeries") + " "
                        + appBasedUtils.getStringData(data, "ChassisNumber") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries2") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"),
                                                currentRunVehicleDetails.get(0), softAssert);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
                keywords.clearText(chassisIdPart1Element, "Chassis series");
                keywords.clearText(chassisIdPart2Element, "Chassis number");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softAssert.assertAll();
    }

    /**
     * # #Function Name : searchWithBrandUnitNumber()
     * 
     * #Description : Search coverage with Brand and unit number search criteria #Date of creation : 26-10-2016 #Input Parameters:
     * 
     * 26-10-2016
     */
    public void searchWithBrandUnitNumber(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandUnitElement);
            keywords.selectVisibleText(brandUnitElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(unitNumberElement, appBasedUtils.getStringData(data, "UnitNumber"), "Unit number");
            keywords.clickOnButton(driver, searchButtonElement);
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                if (keywords.elementIsDisplayed(labelVehicleList, "Vehicle List Screen")) {
                    String Xpath = "(//div[contains(text(),'" + appBasedUtils.getStringData(data, "UnitNumber") + "')])[1]";
                    WebElement unitNumber = driver.findElement(By.xpath(Xpath));
                    keywords.clickOnButton(driver, unitNumber);
                    keywords.waitForVisibilityOfElement(driver, 20, vehicleInformation);
                    persistVehicleInformation();
                    keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "ChassisSeries") + " "
                            + appBasedUtils.getStringData(data, "ChassisNumber") + "  is given below:");
                    keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                    appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries2") + "-"
                            + appBasedUtils.getStringData(data, "ChassisNumber"), currentRunVehicleDetails.get(0), softAssert);
                    if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                        gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                    } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                        gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                    } else
                        keywords.APP_LOGS.info("Test data issue. Please check test data");
                }
            } else {
                keywords.waitForVisibilityOfElement(driver, 20, vehicleInformation);
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "ChassisSeries") + " "
                        + appBasedUtils.getStringData(data, "ChassisNumber") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries2") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"),
                                                currentRunVehicleDetails.get(0), softAssert);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softAssert.assertAll();
    }

    /**
     * # #Function Name : searchWithBrandUnitNumber()
     * 
     * #Description : Search coverage with Brand and unit number search criteria #Date of creation : 26-10-2016 #Input Parameters:
     * 
     * 26-10-2016
     */
    public void searchWithBrandUnitNumberSingleVehicleScenario(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandUnitElement);
            keywords.selectVisibleText(brandUnitElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(unitNumberElement, appBasedUtils.getStringData(data, "UnitNumber"), "Unit number");
            keywords.clickOnButton(driver, searchButtonElement);
            /* driver.switchTo().defaultContent(); */
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElement(driver, 20, vehicleInformation);
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "ChassisSeries") + " "
                        + appBasedUtils.getStringData(data, "ChassisNumber") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"),
                                                currentRunVehicleDetails.get(0), softassert);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softassert.assertAll();
    }

    /**
     * # #Function Name : searchWithBrandUnitNumber()
     * 
     * #Description : Search coverage with Brand and unit number search criteria #Date of creation : 26-10-2016 #Input Parameters:
     * 
     * 26-10-2016
     */
    public void searchWithBrandMarketTypeChassisNumSingleVehicleScenario(HashMap<String, Object> data) {
        SoftAssert softassert = new SoftAssert();
        try {
            keywords.waitForVisibilityOfElement(driver, 20, brandMarketElement);
            keywords.selectVisibleText(brandMarketElement, appBasedUtils.getStringData(data, "Brand"));
            keywords.input(marketTypeElement, appBasedUtils.getStringData(data, "MarketType"), "Market Type");
            keywords.input(chassisNumberElement, appBasedUtils.getStringData(data, "ChassisNumber"), "Market Type");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                keywords.waitForVisibilityOfElement(driver, 20, vehicleInformation);
                persistVehicleInformation();
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "ChassisSeries") + " "
                        + appBasedUtils.getStringData(data, "ChassisNumber") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"),
                                                currentRunVehicleDetails.get(0), softassert);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                } else
                    keywords.APP_LOGS.info("Test data issue. Please check test data");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        softassert.assertAll();
    }

    /**
     * # #Function Name : verifyListOfBrandNamesInDropDown()
     * 
     * #Description : To verify the brand values in the dropdown box #Date of creation : 26-10-2016 #Input Parameters:
     * 
     * 26-10-2016
     */
    public void verifyListOfBrandNamesInDropDown(HashMap<String, Object> data) {
        SoftAssert softAssert = new SoftAssert();
        keywords.APP_LOGS.info("Verifing the brand names in brand list");
        String brandValue = appBasedUtils.getStringData(data, "ExpectedBrand");
        String[] brandName = brandValue.split(",");
        keywords.verifyListOfItemsFromDropDown(brandMarketElement, brandName, softAssert);
    }

    /************************************
     * #Description : To verify the result in the grid #Input Parameters:
     ************************************/
    public void gridValidation(String columnheader[], String noOfColumnsToValidate) {
        alertTextMessage = keywords.getTextElementIsDisplayed(commonMessageAfterAlert);
        if (alertTextMessage == null) {
            int columnIndexInSearchGrid = 0;
            try {
                keywords.waitForVisibilityOfElement(driver, 20, gridColumnName);
                int noOfitemsInSearchResult = (Integer.parseInt(appBasedUtils.extractStringFromOtherString(gridPageCount.getText(), " - ", " / "))) + 1;
                keywords.APP_LOGS.info("Number of times vehicle got repaired is:" + noOfitemsInSearchResult);
                for (String xpathSecondPart : noOfColumnsToValidate.split("\\s")) {
                    if ("TypeOfConcern_1 ClaimType_2 RepDate_3 ClaimJobId_4 RepOrderNum_5 Mileage_6 Age_7 MainOperation_8 CasualPart_9 CasualPartName_10 SCC_11 ContractNo_12 ClaimType_1 RepDate_2 ClaimJobId_3 RepOrderNum_4 Mileage_5 Age_6 MainOp_7 CausualPart_8 CausalPartName_9 SCC_10 ".contains(xpathSecondPart)) {
                        keywords.APP_LOGS.info("Displaying \"" + columnheader[columnIndexInSearchGrid] + "\" Column values...");
                        appBasedUtils.displayGridColumnValues(XpathFirstPart, xpathSecondPart, softAssert, driver);
                        columnIndexInSearchGrid++;
                    }
                }
                softAssert.assertAll();
            } catch (Exception e) {
                APP_LOGS.error(e.getMessage());
                Assert.fail("Error in validation");
            }
        } else {
            keywords.APP_LOGS.info("Fail!!! Error message is '" + alertTextMessage + "'");
            ExtentTestManager.getTest().log(Status.FAIL, " Fail!!! Error message is '" + alertTextMessage);
            Assert.fail("Fail!!! Error message is '" + alertTextMessage + "'");
        }
    }

    /****************************************
     * #Description : Method to view view vehicle repair details using VIN #Input Parameters: excel data
     *****************************************/
    public void VerifyTypeofConcernContractnoSearchresultsB(HashMap<String, Object> data) {
        try {
            keywords.waitForVisibilityOfElement(driver, 20, chassisIdPart1Element);
            keywords.input(chassisIdPart1Element, appBasedUtils.getStringData(data, "ChassisSeries"), "Chassi series");
            keywords.input(chassisIdPart2Element, appBasedUtils.getStringData(data, "ChassisNumber"), "Chassis number");
            keywords.clickOnButton(driver, searchButtonElement);
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                WebDriverWait wait = new WebDriverWait(driver, 4);
                wait.until(ExpectedConditions.visibilityOf(vehicleInformation));
                currentRunVehicleDetails.clear();
                persistVehicleInformation();
                // compareVehicleDetails(currentRunVehicleDetails,previouseRunVehicleDetails);
                keywords.APP_LOGS.info("Details of vehicle " + appBasedUtils.getStringData(data, "VIN") + "  is given below:");
                keywords.printAllElements(currentRunVehicleDetails, vehicleSearchResultColumnNames);
                if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Both")) {
                    gridValidation(columnheaderWarrantyContract, noOfColumnsToValidateWarrantyContract);
                } else if (appBasedUtils.getStringData(data, "TypeOfConcern").equalsIgnoreCase("Warranty")) {
                    gridValidation(columnheaderWarranty, noOfColumnsToValidateWarranty);
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

    /********************************
     * #Description : Method to list and verify Dropdown values #Input Parameters: noOfColumnsToValidate: Hexadecimal character used as index in the Xpath
     * dropdown: dropdown webelement expectedDropDownValues: Expected test data
     ***********************************/
    public void verifyTypeofConcernContractnoB(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        keywords.elementIsEnabled(Contractno);
        String TypeofConcern = appBasedUtils.getStringData(data, "TypeOfConcern");
        String[] TypeofConcernValue = TypeofConcern.split(",");
        keywords.verifyListOfItemsFromDropDown(dropdownTypeOfConcern, TypeofConcernValue, softAssert);

        keywords.clickOnButton(driver, dropdownTypeOfConcern);
        keywords.selectVisibleText(dropdownTypeOfConcern, "Contract");
        keywords.elementIsEnabled(Contractno);

        keywords.clickOnButton(driver, dropdownTypeOfConcern);
        keywords.selectVisibleText(dropdownTypeOfConcern, "Warranty");
        // keywords.elementIsEnabled(Contractno);

        if (Contractno.isEnabled() == false) {
            keywords.APP_LOGS.info("Element is disabled.");
        } else {
            Assert.fail("Element is enabled");
        }
    }

    /********************************
     * #Description : Logs vehicle information captured to log files
     * 
     * #Input Parameters: noOfColumnsToValidate: Hexadecimal character used as index in the Xpath dropdown: dropdown webelement expectedDropDownValues: Expected
     * test data
     ***********************************/
    public void printVehicleInformation() {
        if ((!currentCompany.equalsIgnoreCase("Volvo Construction Equipment") || (!currentCompany.equalsIgnoreCase("Volvo Penta Corporation")))) {
            for (int j = 0; j < vehicleSearchResultColumnNames.length; j++) {
                if (j >= 12) {
                    keywords.APP_LOGS.info(vehicleSearchResultColumnNames[j] + ": " + currentRunVehicleDetails.get(j + 1));
                }
                keywords.APP_LOGS.info(vehicleSearchResultColumnNames[j] + ": " + currentRunVehicleDetails.get(j));
            }
        } else
            for (int j = 0; j < vehicleSearchResultColumnNamesVCEPenta.length; j++) {
                keywords.APP_LOGS.info(vehicleSearchResultColumnNamesVCEPenta[j] + ": " + currentRunVehicleDetails.get(j));
            }
    }

    /****************************************
     * #Description : Method to verify Vehicle history in Vehicle repair history search #Input Parameters: excel data
     *****************************************/
    public void vehicleHistoryValidation(HashMap<String, Object> data, String company) {
        Assert.assertEquals(chassisId.getText(), appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
        Assert.assertEquals(deliveryImporter.getText(), appBasedUtils.getStringData(data, "Delivery Importer"));
        Assert.assertEquals(vehicleOperation.getText(), appBasedUtils.getStringData(data, "Vehicle Operation"));
        Assert.assertEquals(regNo.getText(), appBasedUtils.getStringData(data, "RegistrationNumber"));
        Assert.assertEquals(deliveryDealer.getText(), appBasedUtils.getStringData(data, "Delivery Dealer"));
        Assert.assertEquals(deliveryDate.getText(), appBasedUtils.getStringData(data, "Delivery Date"));
        Assert.assertEquals(vehicleOperatDesc.getText(), appBasedUtils.getStringData(data, "Vehicle Operation Desc"));
        Assert.assertEquals(VIN.getText(), appBasedUtils.getStringData(data, "VIN"));
        Assert.assertEquals(countryOfOperation.getText(), appBasedUtils.getStringData(data, "Country Of Operation"));
        if (VOLVO_CONSTRUCTION_EQUIPMENT.equalsIgnoreCase(company) || VOLVO_PENTA_CORPORATION.equalsIgnoreCase(company)) {
            Assert.assertEquals(headerOperatingHours.getText(), appBasedUtils.getStringData(data, "Mileage"));
        } else {
            Assert.assertEquals(mileage.getText(), appBasedUtils.getStringData(data, "Mileage"));
        }
        Assert.assertEquals(marketingType.getText(), appBasedUtils.getStringData(data, "MarketType"));
        Assert.assertEquals(usingCustomer.getText(), appBasedUtils.getStringData(data, "Using Customer"));
        if (!(VOLVO_CONSTRUCTION_EQUIPMENT.equalsIgnoreCase(company))) {
            Assert.assertEquals(deliveryMileageVH.getText(), appBasedUtils.getStringData(data, "Delivery Mileage"));
        }

        Assert.assertEquals(keywords.getTextValue(unitNo), appBasedUtils.getStringData(data, "UnitNumber"));
    }

    /****************************************
     * #Description : Method to verify Repair header information in claim opened from View repair history #Input Parameters: excel data
     *****************************************/
    public void claimRepairValidation(HashMap<String, Object> data, String company) {
        if (VOLVO_CONSTRUCTION_EQUIPMENT.equalsIgnoreCase(company)) {
            Assert.assertEquals(claimRepairMachineID.getText(),
                                appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
            Assert.assertEquals(claimRepairUsingCustomer.getText(), appBasedUtils.getStringData(data, "Using Customer"));
            Assert.assertEquals(claimRepairMarketingType.getText(), appBasedUtils.getStringData(data, "MarketType"));

        } else if (VOLVO_PENTA_CORPORATION.equalsIgnoreCase(company)) {
            Assert.assertEquals(claimRepairDrivelineId.getText(),
                                appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
            Assert.assertEquals(claimRepairMarketingType.getText(), appBasedUtils.getStringData(data, "MarketType"));

        } else {
            Assert.assertEquals(claimRepairChassisID.getText(),
                                appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
            Assert.assertEquals(claimRepairMarketingType.getText(), appBasedUtils.getStringData(data, "MarketType"));
            Assert.assertEquals(claimRepairUsingCustomer.getText(), appBasedUtils.getStringData(data, "Using Customer"));

        }
    }

    /****************************************
     * #Description : Method to verify claim header in claim opened from View repair history #Input Parameters: excel data
     *****************************************/
    public void claimHeaderValidationForOtherStatusClaim(HashMap<String, Object> data, String company) {
        if (VOLVO_CONSTRUCTION_EQUIPMENT.equalsIgnoreCase(company)) {
            Assert.assertEquals(claimMachineId.getText(),
                                appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
            Assert.assertEquals(serialNo.getText(), appBasedUtils.getStringData(data, "ChassisNumber"));
            String[] deliveryWarrantyArea = claimWarrantyArea.getText().split("-");
            Assert.assertEquals(deliveryWarrantyArea[0].trim(), appBasedUtils.getStringData(data, "Delivery Importer"));
            String[] deliveringDealer = claimDeliveringDealer.getText().split("-");
            Assert.assertEquals(deliveringDealer[0].trim(), appBasedUtils.getStringData(data, "Delivery Dealer"));
            Assert.assertEquals(claimUsingCust.getText(), appBasedUtils.getStringData(data, "Using Customer"));
        } else if (VOLVO_PENTA_CORPORATION.equalsIgnoreCase(company)) {
            Assert.assertEquals(claimDrivelineId.getText(),
                                appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
            Assert.assertEquals(drivelineNo.getText(), appBasedUtils.getStringData(data, "ChassisNumber"));
            String[] deliveryWarrantyDistrict = claimDeliveringWarranty.getText().split("-");
            Assert.assertEquals(deliveryWarrantyDistrict[0].trim(), appBasedUtils.getStringData(data, "Delivery Importer"));

            String[] deliveryBusinessPartner = claimDeliveringBusinessPartner.getText().split("-");
            Assert.assertEquals(deliveryBusinessPartner[0].trim(), appBasedUtils.getStringData(data, "Delivery Dealer"));

        } else {
            Assert.assertEquals(claimChassisId.getText(),
                                appBasedUtils.getStringData(data, "ChassisSeries") + "-" + appBasedUtils.getStringData(data, "ChassisNumber"));
            Assert.assertEquals(claimChassisNo.getText(), appBasedUtils.getStringData(data, "ChassisNumber"));
            String[] deliveryImporter = claimDeliveringImp.getText().split("-");
            Assert.assertEquals(deliveryImporter[0].trim(), appBasedUtils.getStringData(data, "Delivery Importer"));

            String[] deliveringDealer = claimDeliveringDealer.getText().split("-");
            Assert.assertEquals(deliveringDealer[0].trim(), appBasedUtils.getStringData(data, "Delivery Dealer"));
            Assert.assertEquals(claimUsingCust.getText(), appBasedUtils.getStringData(data, "Using Customer"));
        }
        Assert.assertEquals(claimMarketingType.getText(), appBasedUtils.getStringData(data, "MarketType"));
        Assert.assertEquals(claimDeliveryDate.getText(), appBasedUtils.getStringData(data, "Delivery Date"));
    }

    /****************************************
     * #Description : Method to verify claim header in claim opened from View repair history #Input Parameters: excel data
     *****************************************/
    public void claimHeaderValidationForValidatedOk(HashMap<String, Object> data, String company) {
        if (VOLVO_CONSTRUCTION_EQUIPMENT.equalsIgnoreCase(company)) {
            Assert.assertEquals(serialNo.getText(), appBasedUtils.getStringData(data, "ChassisNumber"));
            String[] deliveryWarrantyArea = claimWarrantyArea.getText().split("-");
            Assert.assertEquals(deliveryWarrantyArea[0].trim(), appBasedUtils.getStringData(data, "Delivery Importer"));
            String[] deliveringDealer = claimDeliveringDealer.getText().split("-");
            Assert.assertEquals(deliveringDealer[0].trim(), appBasedUtils.getStringData(data, "Delivery Dealer"));
            Assert.assertEquals(claimUsingCust.getText(), appBasedUtils.getStringData(data, "Using Customer"));
        } else if (VOLVO_PENTA_CORPORATION.equalsIgnoreCase(company)) {

            Assert.assertEquals(drivelineNo.getText(), appBasedUtils.getStringData(data, "ChassisNumber"));
            String[] deliveryWarrantyDistrict = claimDeliveringWarranty.getText().split("-");
            Assert.assertEquals(deliveryWarrantyDistrict[0].trim(), appBasedUtils.getStringData(data, "Delivery Importer"));

            String[] deliveryBusinessPartner = claimDeliveringBusinessPartner.getText().split("-");
            Assert.assertEquals(deliveryBusinessPartner[0].trim(), appBasedUtils.getStringData(data, "Delivery Dealer"));

        } else {

            Assert.assertEquals(claimChassisNo.getText(), appBasedUtils.getStringData(data, "ChassisNumber"));
            String[] deliveryImporter = claimDeliveringImp.getText().split("-");
            Assert.assertEquals(deliveryImporter[0].trim(), appBasedUtils.getStringData(data, "Delivery Importer"));

            String[] deliveringDealer = claimDeliveringDealer.getText().split("-");
            Assert.assertEquals(deliveringDealer[0].trim(), appBasedUtils.getStringData(data, "Delivery Dealer"));
            Assert.assertEquals(claimUsingCust.getText(), appBasedUtils.getStringData(data, "Using Customer"));
        }
        Assert.assertEquals(claimMarketingType.getText(), appBasedUtils.getStringData(data, "MarketType"));
        Assert.assertEquals(claimDeliveryDate.getText(), appBasedUtils.getStringData(data, "Delivery Date"));
    }

    /****************************************
     * #Description : Method to verify View repair history search and open claim #Input Parameters: excel data
     *****************************************/
    public void verifyHeaderDetailsAndReturnToHistoryPage(HashMap<String, Object> data) {
        companyName = keywords.getTextValue(companyNameElement);
        softAssert = new SoftAssert();
        keywords.input(chassisIdPart1Element, appBasedUtils.getStringData(data, "ChassisSeries"), "Chassi series");
        keywords.input(chassisNumberElement, appBasedUtils.getStringData(data, "ChassisNumber"), "Chassis number");
        keywords.clickOnButton(driver, searchButtonElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        vehicleHistoryValidation(data, companyName);
        String noOfItemsFirst = noOfItem.getText();
        keywords.clickOnLink(firstRow, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        String statusStr = status.getText();
        claimRepairValidation(data, companyName);
        keywords.clickOnButton(driver, header);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        if (statusStr.equalsIgnoreCase("Validated OK") || statusStr.equalsIgnoreCase("error") || statusStr.equalsIgnoreCase("return")) {
            claimHeaderValidationForValidatedOk(data, companyName);
        } else {
            claimHeaderValidationForOtherStatusClaim(data, companyName);
        }
        keywords.clickOnButton(driver, close);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        String noOfItemsSecond = noOfItem.getText();
        Assert.assertEquals(noOfItemsFirst, noOfItemsSecond);
    }

}
