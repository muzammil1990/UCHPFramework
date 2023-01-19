package com.volvo.in.page.inspectorreport;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.UIUtils;

public class NewInspectorReport {
    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    UIUtils keywords = new UIUtils(driver);
    public WebDriverWait wait = null;
    private static final Logger APP_LOGS = Logger.getLogger(NewInspectorReport.class);

    private static final String VOLVO_PENTA_CORPORATION2 = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction equipment";
    private String companyName;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Inspector report ']")
    WebElement inspectorReportMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[@ng-if='::!subItem.direct && !subItem.submenu' and contains(text(),'New report ')]")
    WebElement newReportSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[@class='page-title ng-binding']")
    WebElement newIinspectorReportTitleElement;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]")
    WebElement typeOfConcernLabelValue;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.ID, using = "chassisSeries")
    WebElement chassisSeriesElement;

    @FindBy(how = How.ID, using = "chassisNo")
    WebElement chassisNumberElement;

    @FindBy(how = How.ID, using = "vin")
    WebElement vinElement;

    @FindBy(how = How.XPATH, using = "//div[@ng-if='ctrl.showVehicleMandtoryFields']/div[2]/div[2]/div[2]")
    WebElement registrationNoElement;

    @FindBy(how = How.ID, using = "droplistBrand")
    WebElement BrandDropDownElement;

    @FindBy(how = How.ID, using = "importer")
    WebElement importerElement;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//select[@id='Importer']")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "functionGroup")
    WebElement functionGroupElement;

    @FindBy(how = How.ID, using = "marketingType")
    WebElement marketingTypeElement;

    @FindBy(how = How.XPATH, using = "//div[@id='closeDate']/input")
    WebElement closeDateElement;

    @FindBy(how = How.XPATH, using = "//span/i[@class='fa fa-calendar']")
    WebElement closeDatePickerButton;

    @FindBy(how = How.ID, using = "droplistStatus")
    WebElement statusElement;

    @FindBy(how = How.ID, using = "marketingchassisno")
    WebElement markertingChassisNumberElement;

    @FindBy(how = How.XPATH, using = "//span[@uib-tooltip='Reset vehicle information']/i[2]")
    WebElement resetVehicleInformationElement;

    @FindBy(how = How.XPATH, using = "//label[text()='Inspector']//parent::div//following-sibling::div")
    WebElement InspectorValueElement;

    @FindBy(how = How.XPATH, using = "//label[text()='Registration date']//parent::div//following-sibling::div")
    WebElement registrationDateElement;

    @FindBy(how = How.ID, using = "casualPartPrefix")
    WebElement causalPartPrefixElement;

    @FindBy(how = How.ID, using = "casualPartNo")
    WebElement casualPartNoElement;

    @FindBy(how = How.ID, using = "reportMilage")
    WebElement reportMilageElement;

    @FindBy(how = How.ID, using = "reportOpHours")
    WebElement reportOpHoursElement;

    @FindBy(how = How.XPATH, using = "//*[@id='droplistCurrency']")
    WebElement currencyDropdownElement;

    @FindBy(how = How.ID, using = "defectCode")
    WebElement defectCodeElement;

    @FindBy(how = How.ID, using = "followUpCode")
    WebElement followUpCodeElement;

    @FindBy(how = How.ID, using = "sccCode")
    WebElement sccCodeElement;

    @FindBy(how = How.XPATH, using = "//label[text()='Using customer']//parent::div//following-sibling::div")
    WebElement usingCustomerElement;

    @FindBy(how = How.ID, using = "debitCode")
    WebElement debitCodeElement;

    @FindBy(how = How.ID, using = "labourAmount")
    WebElement labourAmountElement;

    @FindBy(how = How.ID, using = "labourPerc")
    WebElement labourPercElement;

    @FindBy(how = How.ID, using = "materialAmount")
    WebElement materialAmountElement;

    @FindBy(how = How.ID, using = "materialPerc")
    WebElement materialPercElement;

    @FindBy(how = How.ID, using = "costAmount")
    WebElement otherCostAmountElement;

    @FindBy(how = How.ID, using = "costPercentage")
    WebElement otherCostPercentageElement;

    @FindBy(how = How.ID, using = "travelAmount")
    WebElement travellExpAmountElement;

    @FindBy(how = How.ID, using = "travelPercentage")
    WebElement travellExpPercentageElement;

    @FindBy(how = How.ID, using = "totalAmount")
    WebElement totalAmountElement;

    @FindBy(how = How.ID, using = "totalPercentage")
    WebElement totalPercentageElement;

    @FindBy(how = How.ID, using = "reportText")
    WebElement reportTextElement;

    @FindBy(how = How.ID, using = "internalRemark")
    WebElement internalRemarkElement;

    @FindBy(how = How.XPATH, using = "//div[@class='toast-message']")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//a[contains(@ng-click,'checkErrorList')]/span")
    WebElement errorIconLink;

    @FindBy(how = How.XPATH, using = "//tbody/tr[contains(@ng-repeat,'d in vm.simpleList')][1]")
    WebElement firstErrorMessageInPopup;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//button[@type='button' and contains(., 'Save')]")
    WebElement saveButtonElement;

    public NewInspectorReport(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Inspector Report" main menu to "New Inspector Report" sub menu
     ******************************************/
    public void navigateToNewInspectorReport() {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 60, inspectorReportMainMenuElement);
        keywords.waitTime1(3);
        keywords.performMenuAction(driver, inspectorReportMainMenuElement, newReportSubMenuElement, newIinspectorReportTitleElement);
        keywords.APP_LOGS.info("Navigated to menu " + keywords.getTextValue(newIinspectorReportTitleElement));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
    }

    /****************************************
     * #Description : Supplies Vehicle information Chassis id, Importer, Function group, Close date
     ******************************************/
    @SuppressWarnings("static-access")
    public void supplyVehicleInformation_ChassisId(HashMap<String, Object> newInspectorReportData) {
        keywords.waitTime(500);
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newInspectorReportData, "TypeOfConcern"))) {
            if ((appBasedUtils.getStringData(newInspectorReportData, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(newInspectorReportData, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(newInspectorReportData, "TypeOfConcern")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(newInspectorReportData, "SelectTypeOfConcern"));
        }
        keywords.input(chassisSeriesElement, appBasedUtils.getStringData(newInspectorReportData, "ChassisSeries"), "Chassis series");
        keywords.input(chassisNumberElement, appBasedUtils.getStringData(newInspectorReportData, "ChassisNumber"), "Chassis number");
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(newInspectorReportData, "Importer"),
                                                                        driver);
        keywords.input(functionGroupElement, appBasedUtils.getStringData(newInspectorReportData, "FunctionGroup"), "Function group");
        keywords.input(closeDateElement, DateUtility.getCurrentDate("dd/MM/yyyy"), "close date");
        keywords.clickOnButton(driver, closeDatePickerButton);
        keywords.clickOnButton(driver, closeDatePickerButton);
        keywords.clickOnButton(driver, saveButtonElement);
        keywords.waitTime1(4);
        try {
            if (keywords.isErrorMessageVisible(driver, 10, errorMsgElement, validationMessageClose)) {
                APP_LOGS.info("Inspector report could not be saved");
            }
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }

    }

    /****************************************
     * #Description : Supplies description information Part, Milage, Op hours, Currency, Defect, fallowup and scc codes
     ******************************************/
    public void supplyDescription(HashMap<String, Object> newInspectorReportData) {

        try {
            keywords.waitTime(500);
            keywords.input(causalPartPrefixElement, appBasedUtils.getStringData(newInspectorReportData, "CausalPartPrefix"), "Causal part prefix");
            keywords.input(casualPartNoElement, appBasedUtils.getStringData(newInspectorReportData, "CausalPartNo"), "Causal part no.");
            keywords.input(reportMilageElement, appBasedUtils.getStringData(newInspectorReportData, "Mileage"), "Report mileage (km)");
            keywords.input(reportOpHoursElement, appBasedUtils.getStringData(newInspectorReportData, "OperatingHours"), "Report op hours");
            keywords.selectVisibleText(currencyDropdownElement, appBasedUtils.getStringData(newInspectorReportData, "Currency"));
            keywords.input(defectCodeElement, appBasedUtils.getStringData(newInspectorReportData, "DefectCode"), "Defect code");
            keywords.input(followUpCodeElement, appBasedUtils.getStringData(newInspectorReportData, "FollowUpCode"), "Follow up code");
            keywords.input(sccCodeElement, appBasedUtils.getStringData(newInspectorReportData, "SCCCode"), "SCC code");
            supplyAmountValues(newInspectorReportData);
            keywords.input(reportTextElement, appBasedUtils.getStringData(newInspectorReportData, "ReportText"), "Report text");
            keywords.input(internalRemarkElement, appBasedUtils.getStringData(newInspectorReportData, "InternalRemarks"), "Internal remarks");
            keywords.clickOnButton(driver, saveButtonElement);
            keywords.waitTime1(2);
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Supplies amount values for labour,material, otherCost and travellExp
     ******************************************/
    public void supplyAmountValues(HashMap<String, Object> newInspectorReportData) {
        try {
            companyName = keywords.getTextValue(companyNameElement);
            keywords.waitTime(500);
            keywords.input(labourAmountElement, appBasedUtils.getStringData(newInspectorReportData, "LabourAmount"), "Labour");
            keywords.elementIsEnabled(labourPercElement);
            keywords.input(materialAmountElement, appBasedUtils.getStringData(newInspectorReportData, "MaterialAmount"), "Material");
            keywords.input(otherCostAmountElement, appBasedUtils.getStringData(newInspectorReportData, "OtherCostsAmount"), "Other cost");
            keywords.scrollPage(driver, 250);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION2)))
                keywords.input(travellExpAmountElement, appBasedUtils.getStringData(newInspectorReportData, "TravellExpAmount"), "Travel expenses");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Supplies Percentage values for labour,material, otherCost and travellExp
     ******************************************/
    public void supplyPercentageValues(HashMap<String, Object> newInspectorReportData) {
        try {
            keywords.waitTime(500);
            keywords.input(labourPercElement, appBasedUtils.getStringData(newInspectorReportData, "LabourPercentage"), "Labour");
            keywords.input(materialPercElement, appBasedUtils.getStringData(newInspectorReportData, "MaterialPercentage"), "Material");
            keywords.input(otherCostPercentageElement, appBasedUtils.getStringData(newInspectorReportData, "OtherCostPercentage"), "Other cost");
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION2)))
                keywords.input(travellExpPercentageElement, appBasedUtils.getStringData(newInspectorReportData, "TravellExpPercentage"), "Travel expenses");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Validates the details populated after saving the new Inspector report
     ******************************************/
    public void validateVehicleDetailsPopulatedInInspectorReport(HashMap<String, Object> newInspectorReportData) {
        try {
            String VINValuePopulated = keywords.getAttributeValue(vinElement, "value");
            String registrationNovaluePopulated = keywords.getTextValue(registrationNoElement);
            String marketingTypeValuePopulated = keywords.getAttributeValue(marketingTypeElement, "value");
            String marketingChassisNumberValuePopulated = keywords.getAttributeValue(markertingChassisNumberElement, "value");
            String inspectorValuePopulated = keywords.getTextValue(InspectorValueElement);
            APP_LOGS.info("Validating values populated in Vehicle information section");
            APP_LOGS.info("VIN no picked is: " + VINValuePopulated);
            APP_LOGS.info("Registration no. picked is: " + registrationNovaluePopulated);
            APP_LOGS.info("Marketing Type picked is: " + marketingTypeValuePopulated);
            APP_LOGS.info("Marketing Chassis no. picked is: " + marketingChassisNumberValuePopulated);
            APP_LOGS.info("Inspector Value picked is: " + inspectorValuePopulated);
            Assert.assertEquals(VINValuePopulated, appBasedUtils.getStringData(newInspectorReportData, "VIN"));
            Assert.assertEquals(registrationNovaluePopulated, appBasedUtils.getStringData(newInspectorReportData, "ExpectedRegistrationNo"));
            Assert.assertEquals(marketingTypeValuePopulated, appBasedUtils.getStringData(newInspectorReportData, "MarketType"));
            Assert.assertEquals(marketingChassisNumberValuePopulated, appBasedUtils.getStringData(newInspectorReportData, "ChassisNumber"));
            Assert.assertEquals(inspectorValuePopulated, appBasedUtils.getStringData(newInspectorReportData, "ExpectedInspector"));
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

}
