package com.volvo.in.page.claim;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

import junit.framework.Assert;

public class NewClaimJob {

    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert openSelectedClaimJobAssert;
    UIUtils keywords = new UIUtils(driver);
    public WebDriverWait wait = null;
    public Logger APP_LOGS = Logger.getLogger(NewClaimJob.class);

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claimMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New claim job ')]")
    WebElement newClaimJobSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[text()='New claim job']")
    WebElement newClaimJobPageTitleElement;

    @FindBy(how = How.ID, using = "Importer")
    WebElement importerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//select[@id='Importer']")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "Dealer")
    WebElement dealerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//select[@id='Dealer']")
    WebElement dealerDropDownElement;

    @FindBy(how = How.ID, using = "RepairOrderNumber")
    WebElement repairOrderNumberElement;

    @FindBy(how = How.XPATH, using = "//div[@id='RepairDate']/input")
    WebElement repairDateElement;

    @FindBy(how = How.ID, using = "typeOfClaims")
    WebElement claimTypeElement;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.ID, using = "vehicleOperation")
    WebElement primaryApplicationVCE;

    @FindBy(how = How.ID, using = "Novehicle")
    WebElement noVehicleCheckboxElement;

    @FindBy(how = How.ID, using = "RegistrationNumber")
    WebElement registrationNumberElement;

    @FindBy(how = How.ID, using = "ChassisIdFrom")
    WebElement chassisSeriesElement;

    @FindBy(how = How.ID, using = "ChassisIdTo")
    WebElement chassisNumberElement;

    @FindBy(how = How.ID, using = "ChassisNo")
    WebElement vehiclelistChassiNumber;

    @FindBy(how = How.ID, using = "Brand_1")
    WebElement noVehicleBrandElement;

    @FindBy(how = How.ID, using = "marketingType")
    WebElement marketingTypeElement;

    @FindBy(how = How.ID, using = "vin")
    WebElement vinElement;

    @FindBy(how = How.ID, using = "Brand_2")
    WebElement vehicleListBrandElement;

    @FindBy(how = How.ID, using = "Mileage")
    WebElement mileageElement;

    @FindBy(how = How.ID, using = "contractNumber")
    WebElement contractNumberTextbox;

    @FindBy(how = How.ID, using = "OperatingHours")
    WebElement operatingHoursElement;

    @FindBy(how = How.XPATH, using = "//select[@name='mileageUnit']")
    WebElement mileageUnitElement;

    @FindBy(how = How.XPATH, using = "//button[@type='submit' and contains(text(),'Continue')]")
    WebElement continueButtonElement;

    @FindBy(how = How.XPATH, using = "//div[div[div[div[div[a[contains(text(),'-')]]]]]]/div[1]//a")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//div/p[contains(text(),'header information')]")
    WebElement claimRepairHeaderInformationElement;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Remarks')]")
    WebElement remarksTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Reference no.']]]//div[2]")
    WebElement claimJobReferenceNo;

    @FindBy(how = How.XPATH, using = "//input[@id='scccode']")
    WebElement sccCodeForCampaignClaimJobElement;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs btn-primary ng-binding']")
    WebElement selectButtonForCampaignClaimJobElement;

    @FindBy(how = How.XPATH, using = "//a[contains(@ng-click,'checkErrorList')]/span")
    WebElement errorIconLink;

    @FindBy(how = How.XPATH, using = "//tbody/tr[contains(@ng-repeat,'d in vm.simpleList')][1]")
    WebElement firstErrorMessageInPopup;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//span/i[@class='fa fa-calendar']")
    WebElement datePickerButton;

    @FindBy(how = How.XPATH, using = "//input[@name='scc']")
    WebElement sccCodeInClaimJobInformation;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../span")
    WebElement typeOfConcernLabelValue;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Brand')]/../../descendant::span")
    WebElement singleBrandLabelElement;

    @FindAll({ @FindBy(xpath = "//li[contains(@ng-repeat,'claimValidationErrorTextDTOList')]") })
    List<WebElement> claimJobCheckErrors;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='text-left ui-grid-cell-contents ng-binding ng-scope']]]/div[1])[1]/descendant::span")
    WebElement chassisIdModelDailogueElement;

    @FindBy(how = How.XPATH, using = "//h4[@class='ng-binding ng-scope' and contains(.,'The claim job is saved')]")
    WebElement claimJobCheckErrorMessageHeader;

    @FindBy(how = How.XPATH, using = "//div[@class='divTableCell ng-binding']/span")
    WebElement responsibleDealerIcon;

    @FindBy(how = How.XPATH, using = "//i[@class='fa ng-isolate-scope fa-angle-double-left']")
    WebElement repairHeaderinformationLinksOption;

    @FindAll({ @FindBy(xpath = "//i[@class='fa ng-isolate-scope fa-angle-double-left']") })
    List<WebElement> repairHeaderinformationLinksOptionNew;

    @FindBy(how = How.XPATH, using = "//span[text()='View contract information']")
    WebElement viewContractinformationLink;

    @FindBy(how = How.XPATH, using = "//h3[@class='modal-title-header ng-binding']")
    WebElement titleViewContractinformationPopup;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Options')]")
    WebElement optionsTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Contract Notes')]")
    WebElement contractNotesTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Material pricing')]")
    WebElement materialPricingTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Labour pricing')]")
    WebElement labourPricingTab;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Travel expenses')]")
    WebElement travelExpensesTabManageClaimJob;

    @FindBy(how = How.NAME, using = "hoursAmount")
    WebElement travelHoursTravelExpenses;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'travelList')]/../descendant::td[1]")
    WebElement travelExpTabHoursSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'travelList')]/../descendant::td[2]")
    WebElement travelExpTabRateSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'travelList')]/../descendant::td[3]")
    WebElement travelExpTabHoursNetSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'claimJob.travelList')]/table[2]/tbody[1]/tr[1]/td[1]")
    WebElement travelExpTabDistanceSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'claimJob.travelList')]/table[2]/tbody[1]/tr[1]/td[2]")
    WebElement travelExpTabDistanceRateSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'claimJob.travelList')]/table[2]/tbody[1]/tr[1]/td[3]")
    WebElement travelExpTabTravelDistanceNetSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'claimJob.travelList')]/table[3]/tbody[1]/tr[1]/td[1]")
    WebElement travelExpTabOtherTravelExpenseSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'claimJob.travelList')]/table[3]/tbody[1]/tr[1]/td[2]")
    WebElement travelExpOtherTravelExpenseNetSavedValue;

    @FindBy(how = How.XPATH, using = "//button[@id='headersavebtn']")
    WebElement saveHeaderButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Yes']")
    WebElement saveHeaderConfirmationButton;

    @FindBy(how = How.ID, using = "chassisSeries")
    WebElement chassisSeries;

    @FindBy(how = How.ID, using = "chassisNo")
    WebElement chassisNumber;

    @FindBy(how = How.ID, using = "updateVehicleInformation")
    WebElement updateVehicleInformationCheckBox;

    String currentCompanyName;

    public static HashMap<String, Object> applicationData = new HashMap<String, Object>();

    public NewClaimJob(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "New claim job" sub menu
     ******************************************/
    public void navigateToNewClaimJob() {
        // driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 60, claimMainMenuElement);
        keywords.performMenuAction(driver, claimMainMenuElement, newClaimJobSubMenuElement, newClaimJobPageTitleElement);
        keywords.APP_LOGS.info("Navigated to menu " + keywords.getTextValue(newClaimJobPageTitleElement));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
    }

    /****************************************
     * #Description : Creates a claim job using vehicle details #Input parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void createClaimJobUsingVehicle(HashMap<String, Object> newClaimJobData) {
        keywords.waitTime(500);
        currentCompanyName = keywords.getTextValue(companyNameElement);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(newClaimJobData, "Importer"),
                                                                        driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(newClaimJobData, "Dealer"), driver);
        keywords.input(repairDateElement, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Repair date");
        keywords.clickOnButton(driver, datePickerButton);
        keywords.clickOnButton(driver, datePickerButton);
        keywords.input(chassisSeriesElement, appBasedUtils.getStringData(newClaimJobData, "ChassisSeries"), "Chassis series");
        keywords.input(chassisNumberElement, appBasedUtils.getStringData(newClaimJobData, "ChassisNumber"), "Chassis number");
        if ((currentCompanyName.equalsIgnoreCase("Volvo Construction Equipment")) || (currentCompanyName.equalsIgnoreCase("Volvo Penta Corporation"))) {
            keywords.waitForVisibilityOfElement(driver, 20, operatingHoursElement);
            keywords.input(operatingHoursElement, appBasedUtils.getStringData(newClaimJobData, "OperatingHours"), "operating hours");
            if (keywords.getTextValue(companyNameElement).equalsIgnoreCase("Volvo Construction Equipment"))
                keywords.selectVisibleText(primaryApplicationVCE, appBasedUtils.getStringData(newClaimJobData, "PrimaryApplication"));
        } else
            keywords.input(mileageElement, appBasedUtils.getStringData(newClaimJobData, "Mileage"), "Mileage");
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern"))) {
            if ((appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(newClaimJobData, "SelectTypeOfConcern"));
        }
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJobData, "ContractNumber"))) {
            keywords.input(contractNumberTextbox, appBasedUtils.getStringData(newClaimJobData, "ContractNumber"), "Contract number");
        }

        keywords.waitForVisibilityOfElement(driver, 20, claimTypeElement);
        keywords.selectVisibleText(claimTypeElement, appBasedUtils.getStringData(newClaimJobData, "ClaimType"));

        clickOnContinuebutton(newClaimJobData);
    }

    /****************************************
     * #Description : Creates a claim job using one of the vehicle in vehicle list. #Input parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void supplyMileageAndOperatingHoursAndCreateClaimUsingVehicleList(HashMap<String, Object> newClaimJobData) {
        keywords.waitTime(500);
        currentCompanyName = keywords.getTextValue(companyNameElement);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(newClaimJobData, "Importer"),
                                                                        driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(newClaimJobData, "Dealer"), driver);
        keywords.input(repairDateElement, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Repair date");
        keywords.clickOnButton(driver, datePickerButton);
        keywords.clickOnButton(driver, datePickerButton);
        keywords.waitForVisibilityOfElement(driver, 20, vehicleListBrandElement);
        keywords.selectVisibleText(vehicleListBrandElement, appBasedUtils.getStringData(newClaimJobData, "VehicleBrand"));
        keywords.input(marketingTypeElement, appBasedUtils.getStringData(newClaimJobData, "MarketType"), "Marketing type");
        keywords.input(vehiclelistChassiNumber, appBasedUtils.getStringData(newClaimJobData, "ChassisNumber"), "Chassis number");
        keywords.waitForVisibilityOfElement(driver, 20, operatingHoursElement);
        if ((currentCompanyName.equalsIgnoreCase("Volvo Construction Equipment")) || (currentCompanyName.equalsIgnoreCase("Volvo Penta Corporation"))
                || (currentCompanyName.equalsIgnoreCase("TSM Americas"))) {
            keywords.waitForVisibilityOfElement(driver, 20, operatingHoursElement);
            keywords.input(operatingHoursElement, appBasedUtils.getStringData(newClaimJobData, "OperatingHours"), "operating hours");
        }
        keywords.input(mileageElement, appBasedUtils.getStringData(newClaimJobData, "Mileage"), "Mileage");
        // if(appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getTestData(newClaimJobData,"TypeOfConcern")))
        // {
        if ((appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                || (appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
            keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
        else if ((appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("Both"))
            keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(newClaimJobData, "SelectTypeOfConcern"));
        // }
        keywords.waitForVisibilityOfElement(driver, 20, claimTypeElement);
        keywords.selectVisibleText(claimTypeElement, appBasedUtils.getStringData(newClaimJobData, "ClaimType"));
        clickOnContinuebuttonVehicleList(newClaimJobData);
    }

    /****************************************
     * #Description : Supplies the Campaign details to "SCC" code popup. #Input parameters: excel data
     ******************************************/
    public void supplyCampaignDetails(HashMap<String, Object> newClaimJobData) {
        keywords.input(sccCodeForCampaignClaimJobElement, appBasedUtils.getStringData(newClaimJobData, "SCCCode"), "SCC code");
        keywords.keyTab(sccCodeForCampaignClaimJobElement);
        keywords.waitForVisibilityOfElement(driver, 15, selectButtonForCampaignClaimJobElement);
        keywords.clickOnButton(driver, selectButtonForCampaignClaimJobElement);
        if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
            keywords.waitTime(500);
            keywords.waitForVisibilityOfElement(driver, 15, claimRepairHeaderInformationElement);
            keywords.keyTabAndEnter(sccCodeInClaimJobInformation);
            keywords.waitTime(500);
        }
    }

    /****************************************
     * #Description : Creates parts claim job without vehicle. #Input parameters: excel data
     ******************************************/
    @SuppressWarnings("static-access")
    public void createPartsClaimJob(HashMap<String, Object> newClaimJobData) {
            keywords.waitTime1(1);
            appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForImporter, importerTextBoxElement, importerDropDownElement,
                                                                            importerGreyedOutValue, appBasedUtils.getStringData(newClaimJobData, "Importer"),
                                                                            driver);
            appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(baseclass.userContextForDealer, dealerTextBoxElement, dealerDropDownElement,
                                                                            dealerGreyedOutValue, appBasedUtils.getStringData(newClaimJobData, "Dealer"),
                                                                            driver);
            keywords.input(repairDateElement, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Repair date");
            keywords.clickOnButton(driver, datePickerButton);
            keywords.waitTime1(1);
            keywords.clickOnButton(driver, datePickerButton);
            keywords.waitTime1(2);
            keywords.clickOnButton(driver, repairOrderNumberElement);

            // if(appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getTestData(newClaimJobData,"TypeOfConcern")))
            // {
            if ((appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern picked for claim creation is:" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(newClaimJobData, "TypeOfConcern")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(newClaimJobData, "SelectTypeOfConcern"));
            // }
            if (appBasedUtils.getStringData(newClaimJobData, "NoVehicle").equals("Yes")) {
                keywords.setCheckboxIfNotSelected(noVehicleCheckboxElement);
                // Checks brand value, if it is label. dropdown selection will not happen.
                if ((keywords.getTextValue(companyNameElement).equalsIgnoreCase("Volvo Penta Corporation"))
                        || (keywords.getTextValue(companyNameElement).equalsIgnoreCase("Volvo Bus Corporation")))
                    keywords.APP_LOGS.info("Brand picked for the claim creation is:" + keywords.getTextValue(singleBrandLabelElement));
                else {
                    keywords.waitForVisibilityOfElement(driver, 5, noVehicleBrandElement);
                    keywords.selectVisibleText(noVehicleBrandElement, appBasedUtils.getStringData(newClaimJobData, "VehicleBrand"));
                }
                keywords.waitForVisibilityOfElement(driver, 5, claimTypeElement);
                keywords.selectVisibleText(claimTypeElement, appBasedUtils.getStringData(newClaimJobData, "ClaimType"));
            } else {
                keywords.input(chassisSeriesElement, appBasedUtils.getStringData(newClaimJobData, "ChassisSeries"), "Chassis series");
                keywords.input(chassisNumberElement, appBasedUtils.getStringData(newClaimJobData, "ChassisNumber"), "Chassis number");
                if (keywords.getTextValue(companyNameElement).equalsIgnoreCase("Volvo Construction Equipment")) {
                    keywords.input(operatingHoursElement, appBasedUtils.getStringData(newClaimJobData, "OperatingHours"), "perating hours");
                } else
                    keywords.input(mileageElement, appBasedUtils.getStringData(newClaimJobData, "Mileage"), "Mileage");
            }
            clickOnContinuebutton(newClaimJobData);
    }

    /****************************************
     * #Description : Clicks on continue button and capture the error messages if any #Input parameters: excel data
     ******************************************/
    public void clickOnContinuebutton(HashMap<String, Object> newClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 20, continueButtonElement);
        keywords.elementIsEnabled(continueButtonElement);
        keywords.clickOnButton(driver, continueButtonElement);
        if (!appBasedUtils.getStringData(newClaimJobData, "ClaimType").equalsIgnoreCase("Campaign")) {
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                if (keywords.waitAndReturnVisibilityOfElement(driver, 12, claimRepairHeaderInformationElement)
                        || (keywords.isErrorMessageVisible(driver, 20, errorMsgElement, validationMessageClose)) || checkClaimHeaderCheckErros()
                        || (appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup))) {
                    keywords.APP_LOGS.info("Claim header is created.");
                }
            }
        } else {
            driver.switchTo().defaultContent();
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                if (keywords.waitAndReturnVisibilityOfElement(driver, 12, sccCodeForCampaignClaimJobElement)
                        || (keywords.isErrorMessageVisible(driver, 20, errorMsgElement, validationMessageClose))
                        || (appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup)))
                    keywords.APP_LOGS.info("Claim header is created.");
            }
        }
    }

    /****************************************
     * #Description : Clicks on continue button and capture the error messages if any #Input parameters: excel data
     ******************************************/
    public void clickOnContinuebuttonVehicleList(HashMap<String, Object> newClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 20, continueButtonElement);
        keywords.elementIsEnabled(continueButtonElement);
        keywords.clickOnButton(driver, continueButtonElement);
        driver.switchTo().defaultContent();
        if (!appBasedUtils.getStringData(newClaimJobData, "ClaimType").equalsIgnoreCase("Campaign")) {
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                if (keywords.waitAndReturnVisibilityOfElement(driver, 12, chassisIdModelDailogueElement)
                        || (keywords.isErrorMessageVisible(driver, 20, errorMsgElement, validationMessageClose))
                        || (appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup))) {
                    String vehicleFromVehicleList = keywords.getTextValue(chassisIdModelDailogueElement);
                    keywords.clickOnButton(driver, chassisIdModelDailogueElement);
                    keywords.APP_LOGS.info("Vehicle selected from the pop up is " + vehicleFromVehicleList);
                    if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                        if (keywords.waitAndReturnVisibilityOfElement(driver, 12, claimRepairHeaderInformationElement)
                                || (keywords.isErrorMessageVisible(driver, 20, errorMsgElement, validationMessageClose))
                                || (appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup)))
                            keywords.APP_LOGS.info("Claim header is created.");
                    }
                }
            }
        } else {
            if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                if (keywords.waitAndReturnVisibilityOfElement(driver, 12, chassisIdModelDailogueElement)
                        || (keywords.isErrorMessageVisible(driver, 20, errorMsgElement, validationMessageClose))
                        || (appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup))) {
                    String vehicleFromVehicleList = keywords.getTextValue(chassisIdModelDailogueElement);
                    keywords.clickOnButton(driver, chassisIdModelDailogueElement);
                    keywords.APP_LOGS.info("Vehicle selected from the pop up is " + vehicleFromVehicleList);
                    if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                        if (keywords.waitAndReturnVisibilityOfElement(driver, 12, sccCodeForCampaignClaimJobElement))
                            keywords.APP_LOGS.info("Claim header is created.");
                    }
                }
            }
        }
    }

    /****************************************
     * #Description : checkes whether claim job is created successfully.
     ******************************************/
    /*
     * public void claimSaveWithReferenceNumber() { try { keywords.waitForVisibilityOfElement(driver, 30, claimJobReferenceNo); keywords.APP_LOGS.info(
     * "Claim job created successfully. Reference number:" + keywords.getTextValue(claimJobReferenceNo) + " - " + keywords.getTextValue(claimJobNo));
     * ExtentTestManager.getTest().log(LogStatus.INFO, "Claim job created successfully. Reference number:" + keywords.getTextValue(claimJobReferenceNo) + " - "
     * + keywords.getTextValue(claimJobNo)); } catch (Exception e) { keywords.APP_LOGS.error(e); } } }
     */

    /****************************************
     * #Description : captures header check errors and fails the test test case when continue button is clicked in the claim header page. sure next operation
     * not getting stuck.
     ******************************************/
    public boolean checkClaimHeaderCheckErros() {
        try {
            if (keywords.waitAndReturnVisibilityOfElement(driver, 2, claimJobCheckErrorMessageHeader)) {
                keywords.APP_LOGS.info("Claim job is saved but contains the following check errors");
                keywords.printTextValueOFElements(claimJobCheckErrors);
                Assert.fail(keywords.getTextValueOFElements(claimJobCheckErrors));
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return false;
    }

    /****************************************
     * #Description : Check wheahter correct responsible dealer number is displayed or not in repair header.
     ******************************************/
    public void checkResponsibleDealerNumber(HashMap<String, Object> data) {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        appBasedUtils.getStringData(data, "ResponsibleDealerNo");
        String ResponsibleIcon = keywords.getAttributeValue(responsibleDealerIcon, "uib-tooltip");
        if (ResponsibleIcon.equals(appBasedUtils.getStringData(data, "ResponsibleDealerNo"))) {
            keywords.APP_LOGS.info("Valid responsible dealer number is displayed" + " " + ResponsibleIcon);
        } else {
            keywords.APP_LOGS.info("Valid responsible dealer number is not displayed" + " " + ResponsibleIcon);
        }

    }

    /****************************************
     * #Description : Verify view contract info link in contract claim repair header.
     ******************************************/
    public void verifyViewContractInfoLink(HashMap<String, Object> data) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.clickOnButton(driver, repairHeaderinformationLinksOption);
            keywords.waitTime1(2);
            keywords.clickOnButton(driver, viewContractinformationLink);
            keywords.waitTime1(2);
            if ((optionsTab.isDisplayed()) && (contractNotesTab.isDisplayed()) && (materialPricingTab.isDisplayed()) && (labourPricingTab.isDisplayed())) {
                keywords.APP_LOGS.info("View contract information popup is displayed with options, contract notes, material pricing and labour pricing tabs");
                ExtentTestManager.getTest()
                                 .log(Status.INFO,
                                      "View contract information popup is displayed with options, contract notes, material pricing and labour pricing tabs");
            } else {
                keywords.APP_LOGS.info("View contract information popup is not displayed with options or contract notes or material pricing or labour pricing tabs");
                ExtentTestManager.getTest()
                                 .log(Status.INFO,
                                      "View contract information popup is not displayed with options or contract notes or material pricing or labour pricing tabs");
                Assert.fail();
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }

    }

    /****************************************
     * #Description : Validate travel expense cost details in claim job for VCE and Penta
     ******************************************/
    public void validateTravelExpenseCostDetails(HashMap<String, Object> newClaimJobData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", travelExpensesTabManageClaimJob);
            keywords.waitForVisibilityOfElement(driver, 20, travelExpensesTabManageClaimJob);
            keywords.clickOnLink(travelExpensesTabManageClaimJob, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            APP_LOGS.info("Validating values saved in Travel Expense tab");
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJobData, "TravelHourTravelExpenses"))) {
                Assert.assertEquals(travelExpTabHoursSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "TravelHourTravelExpenses"));
                Assert.assertEquals(travelExpTabRateSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "TravelRateTravelExpenses"));
                Assert.assertEquals(travelExpTabHoursNetSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "TravelHoursNetTravelExpenses"));
                Assert.assertEquals(travelExpTabDistanceSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "TravelDistanceTravelExpenses"));
                Assert.assertEquals(travelExpTabDistanceRateSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "TravelDistanceRateTravelExpenses"));
                Assert.assertEquals(travelExpTabTravelDistanceNetSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "TravelDistanceNetTravelExpenses"));
                Assert.assertEquals(travelExpTabOtherTravelExpenseSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "OtherTravelTravelExpenses"));
                Assert.assertEquals(travelExpOtherTravelExpenseNetSavedValue.getText().trim().replaceAll("\\s", ""),
                                    appBasedUtils.getStringData(newClaimJobData, "OtherTravelTravelExpenseNet"));
                APP_LOGS.info("Travel hours, rate, travel hours Net, Distance, Distance Rate, Distance NET, other travel expense and Other travel expense NET data enterded in Travel Expense tab is saved..!!");
                ExtentTestManager.getTest()
                                 .log(Status.INFO,
                                      "Travel hours, rate, travel hours Net, Distance, Distance Rate, Distance NET, other travel expense and Other travel expense NET data enterded in Travel Expense tab is saved..!!");

            } else {
                keywords.APP_LOGS.info("Travel expense tab is saved with invlaid data");
                ExtentTestManager.getTest().log(Status.INFO, "Travel expense tab is saved with invlaid data");
                Assert.fail();
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /****************************************************************************************************************
     * #Description : Update Header information details after save.
     ****************************************************************************************************************/
    public void updateHeaderInformation(HashMap<String, Object> data) {
        try {
            keywords.input(repairOrderNumberElement, data.get("UpdatedRepairOrderNo"), "Repair order no");
            keywords.input(chassisSeries, data.get("UpdatedChassisSeries"), "Chassis series");
            keywords.input(chassisNumber, data.get("UpdatedChassisNumber"), "Chassis number");
            Assert.assertEquals(keywords.elementIsEnabled(vinElement), false);
            keywords.setCheckboxIfNotSelected(updateVehicleInformationCheckBox);
            keywords.clickOnButton(driver, saveHeaderButton);
            keywords.clickOnButton(driver, saveHeaderConfirmationButton);
            APP_LOGS.info("Header information is updated successfully");
            ExtentTestManager.getTest().log(Status.INFO, "Header information is updated successfully");
        } catch (Exception e) {
            e.getMessage();
            Assert.fail();
        }
    }
}