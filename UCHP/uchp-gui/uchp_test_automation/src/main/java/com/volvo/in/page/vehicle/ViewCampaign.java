package com.volvo.in.page.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.UIUtils;

import junit.framework.Assert;

public class ViewCampaign {
    public Logger APP_LOGS = Logger.getLogger(ViewCampaign.class);
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();

    @FindBy(how = How.ID, using = "registrationNo")
    WebElement registrationNumberElement;

    @FindBy(how = How.ID, using = "chassisSeries")
    WebElement machineIDSeriesElement;

    @FindBy(how = How.ID, using = "chassisno")
    WebElement machineIDNumberElement;

    @FindBy(how = How.ID, using = "vin")
    WebElement PINElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Search')]")
    WebElement searchButtonElement;

    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle'])[2]")
    WebElement vehicleMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'View campaign')])[1]")
    WebElement viewCampaignSubMenuElement;

    @FindBy(how = How.XPATH, using = "//view-campaign/descendant::h4[contains(., 'iew campaign')]")
    WebElement viewCampaignHeaderElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    @FindBy(how = How.XPATH, using = "//div[@class='toast uchp-toast uchp-toast-error']")
    WebElement captureErrorMsg;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'ui-grid-row ng-scope')]")
    List<WebElement> searchResultNumberOfCampaign;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Registration no.')]")
    WebElement registrationNumberLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'Machine id')]")
    WebElement MachineIDLableElement;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-5 col-lg-4']/label[contains(.,'PIN')]")
    WebElement VINLableElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='header-uchp'])[2]")
    WebElement vehicleInformation;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'SCC')]")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-pager-count']")
    WebElement gridPageCount;

    String vehicleSearchResultColumnNames[] = { "Machine id" };

    String campaignColumnNames[] = { "SCC", "Description", "Closing date", "SCC type", "Responsible Warranty Area", "Responsible dealer" };

    public String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    String noOfColumnsToValidateWarrantyContract = "SCC_1 Description_2 ClosingDate_3 SCCType_4 ResponsibleWarrantyArea_5 ResponsileDealer_6";

    WebElement webElement;

    List<String> currentRunVehicleInformation = new ArrayList<String>();

    List<String> currentRunCampaignDetails = new ArrayList<String>();

    public ViewCampaign(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /*************************************
     * #Description : Method to navigate from Vehicle->ViewCampaign
     */
    public void navigateToViewCampaignPage() {
        try {
            keywords.performMenuAction(driver, vehicleMainMenuElement, viewCampaignSubMenuElement, viewCampaignHeaderElement);
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /************************************
     * #Description : To searching Campaign details for vehicle using Chassis Series and Chassis Number. #Input Parameters: excel data
     ************************************/
    public void searchCampaignforVehicleChassisNumber(HashMap<String, Object> data) {
        int indexOfVehiclePersisted = 0;
        keywords.waitForVisibilityOfElement(driver, 20, machineIDSeriesElement);
        keywords.input(machineIDSeriesElement, appBasedUtils.getStringData(data, "MachineSeries"), "Machine series");
        keywords.input(machineIDNumberElement, appBasedUtils.getStringData(data, "MachineNumber"), "Machine number");
        keywords.clickOnButton(driver, searchButtonElement);
        viewCampaignVerifyVehicleInformationAndCampaignInformation(appBasedUtils.getStringData(data, "MachineSeries") + " - "
                + appBasedUtils.getStringData(data, "MachineNumber"), indexOfVehiclePersisted);
        keywords.clearText(machineIDSeriesElement, "Machine series");
        keywords.clearText(machineIDNumberElement, "Machine number");
    }

    /************************************
     * 
     * #Description : To searching Campaign details for vehicle using Chassis Series and Chassis Number.
     *************************************/
    public void searchCampaignforVehicleRegisterNumber(HashMap<String, Object> data) {
        int indexOfVehiclePersisted = 0;
        keywords.waitForVisibilityOfElement(driver, 20, registrationNumberElement);
        keywords.input(registrationNumberElement, appBasedUtils.getStringData(data, "RegistrationNumber"), "Register number");
        keywords.clickOnButton(driver, searchButtonElement);
        viewCampaignVerifyVehicleInformationAndCampaignInformation(appBasedUtils.getStringData(data, "MachineSeries") + " - "
                + appBasedUtils.getStringData(data, "MachineNumber"), indexOfVehiclePersisted);
        keywords.clearText(registrationNumberElement, "Register number");
    }

    /***********************************
     * #Description : To searching Campaign details for vehicle using Chassis Series and Chassis Number
     ***********************************/
    public void searchCampaignforVehicleVIN(HashMap<String, Object> data) {
        try {
            int indexOfVehiclePersisted = 0;
            keywords.waitForVisibilityOfElement(driver, 20, PINElement);
            keywords.input(PINElement, appBasedUtils.getStringData(data, "PIN"), "PIN");
            keywords.clickOnButton(driver, searchButtonElement);
            viewCampaignVerifyVehicleInformationAndCampaignInformation(appBasedUtils.getStringData(data, "MachineSeries") + " - "
                    + appBasedUtils.getStringData(data, "MachineNumber"), indexOfVehiclePersisted);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
        keywords.clearText(PINElement, "PIN");
    }

    /************************************
     * #Description : Method to save vehicle information to ArrayList placeholder
     ************************************/
    public void persistVehicleInformation() {
        try {
            for (int i = 0; i < vehicleSearchResultColumnNames.length; i++) {
                currentRunVehicleInformation.add(driver.findElement(By.xpath("(//div[@class='col-md-8 form-group']/descendant::label[contains(., '"
                        + vehicleSearchResultColumnNames[i] + "')]/../../div)[2]")).getText());
            }
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /***********************************
     * #Description : Method to validate whether proper vehicle information present in the search grid
     */
    public void getCampaignDetails(String columnheader[], String noOfColumnsToValidate) {
        int columnIndexInSearchGrid = 0;
        try {
            keywords.waitForVisibilityOfElement(driver, 20, gridColumnName);
            int noOfitemsInSearchResult = (Integer.parseInt(appBasedUtils.extractStringFromOtherString(gridPageCount.getText(), " - ", " / "))) + 1;
            keywords.APP_LOGS.info("Number of campaigns vehicle having is:" + noOfitemsInSearchResult);
            for (String xpathSecondPart : noOfColumnsToValidate.split("\\s")) {
                if ("SCC_1 Description_2 ClosingDate_3 SCCType_4 ResponsibleWarrantyArea_5 ResponsileDealer_6".contains(xpathSecondPart)) {
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
    }

    /*******************************
     * #Description : returns Campaign #Input Parameters: 1. Driver: Driver instance 2. Xpath
     */
    public String getCampaignGridText(WebDriver driver, String Xpath) {
        String strvehicleCampaign = new String();
        try {
            webElement = driver.findElement(By.xpath(Xpath));
            strvehicleCampaign = webElement.getText();
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
        return strvehicleCampaign;
    }

    /*******************************************
     * #Description : Method to verify all search fields in the view coverage page.
     *******************************************/
    public void verifyViewCampaignGUI() {
        try {
            SoftAssert sa = new SoftAssert();
            keywords.waitForVisibilityOfElement(driver, 20, registrationNumberLableElement);
            keywords.APP_LOGS.info("Verifying search fields in View coverage page");
            keywords.APP_LOGS.info("Verifying \"Registration Number\" Field...");
            appBasedUtils.asserVerification("Registration no. *", keywords.getTextValue(registrationNumberLableElement), sa);
            keywords.APP_LOGS.info("Verifying \"Machine ID\" Field...");
            appBasedUtils.asserVerification("Machine id *", keywords.getTextValue(MachineIDLableElement), sa);
            keywords.APP_LOGS.info("Verifying \"PIN\" Field...");
            appBasedUtils.asserVerification("PIN *", keywords.getTextValue(VINLableElement), sa);
            sa.assertAll();
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /*******************************************
     * #Description : Method to verify search result matching the search criteria
     *******************************************/
    public void viewCampaignVerifyVehicleInformationAndCampaignInformation(String vehicle, int searchCriteria) {
        try {
            driver.switchTo().defaultContent();
            keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 4, vehicleInformation, commonMessageAfterAlert, validationMessageClose);
            persistVehicleInformation();
            keywords.APP_LOGS.info("Campaign information of the Machine \"" + vehicle + "\":");
            keywords.printAllElements(currentRunVehicleInformation, vehicleSearchResultColumnNames);
            getCampaignDetails(campaignColumnNames, noOfColumnsToValidateWarrantyContract);
            /*
             * keywords.APP_LOGS.info("Campaign details for the vehicle \"" + vehicle + "\":"); keywords.printAllElements(currentRunCampaignDetails,
             * campaignColumnNames);
             */
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Fail!!! Search result not found '");
        }
    }

}
