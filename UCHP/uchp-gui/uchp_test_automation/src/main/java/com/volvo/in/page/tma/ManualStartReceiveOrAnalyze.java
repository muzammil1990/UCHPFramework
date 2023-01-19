package com.volvo.in.page.tma;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

public class ManualStartReceiveOrAnalyze {

    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    UIUtils keywords = new UIUtils(driver);
    PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();

    SoftAssert softAssert;

    private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
    private static final String VOLVO_BUS_CORPORATION = "Volvo Bus Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";
    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";

    public Logger APP_LOGS = Logger.getLogger(ManualStartReceiveOrAnalyze.class);

    @FindBy(how = How.XPATH, using = "(//a[text()='TMA '])[1]")
    WebElement tmaMainMenu;

    @FindBy(how = How.XPATH, using = "(//a[text()='Delivery confirm, receive and analyze '])[1]")
    WebElement deliveryConfirmReceiveAndAnalyze;

    @FindBy(how = How.XPATH, using = "//a[text()='Manual start receive/analyze ']")
    WebElement manualReieveOrAnalyze;

    @FindBy(how = How.XPATH, using = "//h4[text()='Manual start receive/analyze']")
    WebElement manualStartReceiveOrStartPageTitle;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.ID, using = "tmaSiteCompanyNo")
    WebElement selectCompanyName;

    @FindBy(how = How.ID, using = "importer")
    WebElement importer;

    @FindBy(how = How.ID, using = "dealer")
    WebElement dealer;

    @FindBy(how = How.ID, using = "refNo")
    WebElement refNo;

    @FindBy(how = How.ID, using = "jobNo")
    WebElement jobNo;

    @FindBy(how = How.XPATH, using = "//button[text()='Receive']")
    WebElement receiveButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Receive']")
    WebElement receiveTab;

    @FindBy(how = How.XPATH, using = "//button[text()='Save']")
    WebElement save;

    @FindBy(how = How.XPATH, using = "//button[text()='Undo received']")
    WebElement undoReceived;

    @FindBy(how = How.XPATH, using = "//button[text()='Analyse']")
    WebElement analyse;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Material request Id']]]/td[2]")
    WebElement materialRequestId;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.receiveMaterialData.recCausalPartNo']")
    WebElement receivedPartNo;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.receiveMaterialData.recCausalPartNoPrefix']")
    WebElement receivedPartPrefix;

    @FindBy(how = How.XPATH, using = "//input[@id='caseReportNo']")
    WebElement caseReportNo;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='TMA status']]]/td[2]")
    WebElement tmaStatus;

    @FindBy(how = How.XPATH, using = "//a[text()='Claim job']")
    WebElement claimJobTab;

    @FindBy(how = How.XPATH, using = "//a[text()='View printable version']")
    WebElement viewPrintableVersion;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Importer']]]/td[2]")
    WebElement importerInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Warranty area']]]/td[2]")
    WebElement warrantyAreaInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Warranty district']]]/td[2]")
    WebElement warrantyDistrictInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Reference no.']]]/td[4]")
    WebElement referenceNoInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Claim job type']]]/td[6]")
    WebElement claimTypeInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Dealer']]]/td[2]")
    WebElement dealerNoInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Business partner']]]/td[2]")
    WebElement businessPartnerInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Job no.']]]/td[4]")
    WebElement jobNoInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='SCC']]]/td[6]")
    WebElement sccCodInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Repair date']]]/td[2]")
    WebElement repairDateInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Repair date']]]/td[4]")
    WebElement inspectorReportInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Repair date']]]/td[6]")
    WebElement sccDescriptionInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "(//td[@class='ng-binding'])[8]")
    WebElement chassisIdInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "(//td[@class='ng-binding'])[9]")
    WebElement mileageInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Operating hours']]]/td[4]")
    WebElement operatingHoursInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Marketing type']]]/td[2]")
    WebElement marketingTypeTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Marketing type']]]/td[6]")
    WebElement deliveryDateTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[1]")
    WebElement debitCodeInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[2]")
    WebElement labourCostInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[3]")
    WebElement materialCostInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[4]")
    WebElement otherCostInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[5]")
    WebElement otherCostInTMAClaimTabForVCEOrVPC;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[5]")
    WebElement totalLabourHoursInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[6]")
    WebElement totalLabourHoursInTMAClaimTabForVCEOrVPC;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[6]")
    WebElement claimJobTotalInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.debitList']/td[7]")
    WebElement claimJobTotalInTMAClaimTabForVCEOrVPC;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Part number']]]/td[2]")
    WebElement causalPartInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Serial number']]]/td[2]")
    WebElement serailNumberInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Defect code']]]/td[2]")
    WebElement defectCodeInTMAClaimTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.includedMaterialList']/td[2]")
    WebElement includedMaterialPrefixInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.includedMaterialList']/td[3]")
    WebElement includedMaterialPartNumberInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.includedMaterialList']/td[4]")
    WebElement includedMaterialPartDescInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.includedMaterialList']/td[6]")
    WebElement includedMaterialUnitPriceInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.includedMaterialList']/td[7]")
    WebElement includedMaterialQuantityInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.labourList']/td[2]")
    WebElement labourOperationInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.labourList']/td[3]")
    WebElement labourOperationDescInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.labourList']/td[4]")
    WebElement labourOperationQuantityTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.labourList']/td[5]")
    WebElement labourOperationHoursQuantityInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.labourList']/td[6]")
    WebElement labourRateInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.labourList']/td[7]")
    WebElement labourTotalHoursInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claimJobData.otherCostList']/td[1]")
    WebElement otherCostDescInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Complaint']]]/td[2]")
    WebElement complaintInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Cause']]]/td[2]")
    WebElement causeInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Correction']]]/td[2]")
    WebElement correctionInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Comment']]]/td[2]")
    WebElement commentInTMAClaimJobTab;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Claim job information')]")
    WebElement claimJobInformationTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "(//tr[td[b[text()='Labour']]]/td[3])[1]")
    WebElement LabourTotalHoursInClaimJob;

    @FindBy(how = How.XPATH, using = "//a[text()='Assessment']")
    WebElement claimJobAssessmentTab;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[1]")
    WebElement debitCodeValueInClaimJob;

    @FindBy(how = How.XPATH, using = "(//td[@class='text-nowrap text-right forth-column-width ng-binding'])[1]")
    WebElement totalCostInInClaimJob;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[3]")
    WebElement labourCostInInClaimJob;

    @FindBy(how = How.XPATH, using = "(//td[contains(@ng-if,'ctrl.rules.showLabourRate')])[1]")
    WebElement labourCostPerHourInInClaimJob;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[4]")
    WebElement materialCostInInClaimJob;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[5]")
    WebElement otherCostsCostInInClaimJob;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[6]")
    WebElement otherCostsCostInInClaimJobForVCEOrVPC;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'row in ctrl.claimJob.claimFunctionData.includedMaterialList')]/td[4]")
    WebElement includedMaterialDescInCliamJob;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'row in ctrl.claimJob.claimFunctionData.includedMaterialList')]/td[7]")
    WebElement includedMaterialPriceInClaimJob;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'row in ctrl.claimJob.claimFunctionData.labourList')]/td[7]")
    WebElement labourCostOrHourDesc;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'claimFunctionData.labourList')]/td[8]")
    WebElement labourCostPerHourValue;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Labour')]")
    WebElement labourTabManageClaimJob;
    @FindBy(how = How.XPATH, using = "//a[text()='Material']")
    WebElement claimJobMaterial;

    @FindBy(how = How.XPATH, using = "//a[text()='Header']")
    WebElement claimJobHeader;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-9 col-md-10 col-lg-10 form-control-static ng-binding'])[1]")
    WebElement claimHeaderImporter;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-9 col-md-10 col-lg-10 form-control-static ng-binding'])[2]")
    WebElement claimHeaderDealer;

    @FindBy(how = How.ID, using = "nextTMAStatus")
    WebElement tmaNextStatus;

    @FindBy(how = How.ID, using = "thirdPartyTypeSelect")
    WebElement thirdPartyTypeSelect;

    @FindBy(how = How.ID, using = "thirdPartyId")
    WebElement thirdPartyId;

    @FindBy(how = How.XPATH, using = "//select[@id='shippingTypeList']")
    WebElement shippingTypeList;

    @FindBy(how = How.XPATH, using = "//select[@id='thirdPartyTypeList']")
    WebElement thirdPartyTypeList;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Create shipping document')]")
    WebElement createShippingDocument;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Create supplier file')]")
    WebElement createSupplierFile;

    @FindBy(how = How.XPATH, using = "//span[contains(@ng-click,'ctrl.getSelectedThirdPartyType')]/i")
    WebElement selectThirdParty;

    @FindBy(how = How.XPATH, using = "(//a[contains(@ng-click,'grid.appScope.ctrl.openSelectedRow')])[1]")
    WebElement selectFirstRecordInGrid;

    @FindBy(how = How.XPATH, using = "//button[text()='Update']")
    WebElement updateButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Ship material']")
    WebElement shipMaterialButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Initiate shipping']")
    WebElement initiateShipping;

    @FindBy(how = How.ID, using = "address1")
    WebElement attNAme;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class,'ui-grid-disable-selection ng-scope')]//div[contains(@class,'ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope')])[1]")
    WebElement selectFirstRecordInPallectRack;

    @FindBy(how = How.XPATH, using = "//span[@ng-show='::ctrl.shippingRequestDTO.shippingId']")
    WebElement shippingIdAfterSave;

    @FindBy(how = How.XPATH, using = "//button[@id='addToStorage']")
    WebElement addToStorage;

    @FindBy(how = How.ID, using = "storageLocationName")
    WebElement storageLocationName;

    @FindBy(how = How.ID, using = "saveLocation")
    WebElement saveLocation;

    @FindBy(how = How.XPATH, using = "(//a[text()='Storage and shipping '])[1]")
    WebElement storageAndShippingSubMenu;

    @FindBy(how = How.XPATH, using = "(//a[text()='Pallet racks '])[1]")
    WebElement palletRackSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[text()='Storage']")
    WebElement storageTitle;

    @FindBy(how = How.ID, using = "selectPalletRack")
    WebElement selectPalletRack;

    @FindBy(how = How.ID, using = "displayPalletRacks")
    WebElement displayPalletRacks;

    @FindBy(how = How.XPATH, using = "(//div[@class='pallet-rack ng-scope  occupied']/span)[1]")
    WebElement occupiedStorageLocation;

    @FindBy(how = How.XPATH, using = "(//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope'])[1]")
    WebElement selectAllFromGridForStorageLocation;

    @FindBy(how = How.XPATH, using = "(//th[@class='ng-binding'])[3]")
    WebElement noOfItemsToShip;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement historyBackButtonToClose;


    String currentCompanyName;
    String labourTotalHoursInClaim;
    String debitCodeInClaim;
    String netTotalInClaim;
    String labourCostInClaim;
    String materialCostInClaim;
    String otherCostInClaim;
    String labourCostOrHourDescInClaim;
    String labourCostPerHourValueInClaim;
    String includedMaterialDescInClaim;
    String includedMaterialPriceInClaim;
    String claimHeaderImporterInClaimJob;
    String claimHeaderDealerInClaimJob;
    String includedMaterialDescInCliam;

    public ManualStartReceiveOrAnalyze(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToManualStartReceiveOrAnalyzePage() #Author : Nithya #Description : This is a common function
     * to navigate from "TMA" main menu to "navigateToManualStartReceiveOrAnalyzePage" sub menu
     ******************************************/
    public void navigateToManualStartReceiveOrAnalyzePage() {
        keywords.waitForVisibilityOfElement(driver, 10, tmaMainMenu);
        keywords.performSecondLevelMenuAction(driver, tmaMainMenu, deliveryConfirmReceiveAndAnalyze, manualReieveOrAnalyze, manualStartReceiveOrStartPageTitle);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : this method is to navigate to storage menu
     ****************************************/
    public void navigateToStorageAndShippingPalletRacks() {
        keywords.waitForVisibilityOfElement(driver, 10, tmaMainMenu);
        keywords.performSecondLevelMenuAction(driver, tmaMainMenu, storageAndShippingSubMenu, palletRackSubMenu, storageTitle);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : this method is to manualStartReceiveOrAnalyze
     ****************************************/
    public void manualStartReceiveOrAnalyze(HashMap<String, Object> newClaimJobData) {
        keywords.waitTime(2);
        currentCompanyName = keywords.getTextValue(companyNameElement);
        if ((currentCompanyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION)) || (currentCompanyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION))) {
            keywords.selectVisibleText(selectCompanyName, BaseClass.excelSheetName);
        }
        keywords.input(importer, appBasedUtils.getStringData(newClaimJobData, "Importer"), "Importer No");
        keywords.input(dealer, appBasedUtils.getStringData(newClaimJobData, "Dealer"), "dealer No");
        keywords.input(refNo, appBasedUtils.getStringData(newClaimJobData, "ReferenceNo"), "reference No");
        keywords.input(jobNo, appBasedUtils.getStringData(newClaimJobData, "JobNo"), "job No");
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.APP_LOGS.info("Navigated to Receive material page");
    }

    /****************************************
     * #Description : Click on receive button
     ****************************************/
    public void clickOnRecieveButtonInManualStartOrReceivePage() {
        keywords.scrollPage(driver, -500);
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, receiveButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : Click on receive Tab
     ****************************************/
    public void clickOnRecieveTabInManualStartOrReceivePage() {
        keywords.scrollPage(driver, -500);
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, receiveTab);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : Click on Analyse button
     ****************************************/
    public void clickOnAnalyzeButtonInManualStartOrReceivePage() {
        keywords.scrollPage(driver, -500);
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, analyse);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : Click on receive button
     *
     * @throws IOException
     ****************************************/
    public void receiveTMAMaterial(HashMap<String, Object> newClaimJobData) throws IOException {
        manualStartReceiveOrAnalyze(newClaimJobData);
        clickOnRecieveButtonInManualStartOrReceivePage();
        verifyButtonsEnableOrDisabledInReceiveMaterialPage();
        saveTMA();
        setCaseSourceNoInReceiveTab(newClaimJobData);
        saveTMA();
        validateTMAStatus(newClaimJobData);
        clickOnClaimJobTab();
        validateClaimJobInformation(newClaimJobData);
        validateVehicleDataInformation(newClaimJobData);
        validateCostSummaryInformation(newClaimJobData);
        clickOnRecieveTabInManualStartOrReceivePage();
        clickOnViewPrintableVersion(newClaimJobData);

    }

    /****************************************
     * #Description : this method is to verify the button enabled/disabled in Recieve material page
     ****************************************/
    public void verifyButtonsEnableOrDisabledInReceiveMaterialPage() {
        assertTrue(keywords.elementIsEnabled(save));
        assertTrue(!(keywords.elementIsEnabled(undoReceived)));
        assertTrue(!(keywords.elementIsEnabled(analyse)));
    }

    /****************************************
     * #Description : this method is to click on save button
     ****************************************/
    public void saveTMA() {
        keywords.clickOnButton(driver, save);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
        if (keywords.elementIsDisplayed(errorMsgElement, "error message is displayed")) {
            APP_LOGS.info(keywords.getTextValue(errorMsgElement));
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsgElement));
            Assert.fail(keywords.getTextValue(errorMsgElement));
        }
    }

    /****************************************
     * #Description : this method is to set case source No in receive tab
     ****************************************/
    public void setCaseSourceNoInReceiveTab(HashMap<String, Object> newClaimJobData) {
        keywords.waitTime1(3);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", caseReportNo);
        keywords.scrollPage(driver, 1000);
        keywords.input(caseReportNo, appBasedUtils.getStringData(newClaimJobData, "CaseSourceNo"), "case source no ");
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
    }

    /****************************************
     * #Description : this method is to click on ClaimJob tab
     ****************************************/
    public void clickOnClaimJobTab() {
        keywords.scrollPage(driver, -250);
        keywords.clickOnButton(driver, claimJobTab);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : this method is validate TMA status
     ****************************************/
    public void validateTMAStatus(HashMap<String, Object> newClaimJobData) {
        softAssert = new SoftAssert();
        keywords.waitTime1(2);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", tmaStatus);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "TMAStatusReceived"), keywords.getTextValue(tmaStatus), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "CausalPartPrefix"),
                                        keywords.getAttributeValue(receivedPartPrefix, "value"), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "CausalPartNo"), keywords.getAttributeValue(receivedPartNo, "value"),
                                        softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : this method is to validate claim job information in TMA -> Claim job tab
     ****************************************/
    public void validateClaimJobInformation(HashMap<String, Object> newClaimJobData) {
        softAssert = new SoftAssert();
        currentCompanyName = keywords.getTextValue(companyNameElement);
        if (currentCompanyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
            appBasedUtils.asserVerification(claimHeaderImporterInClaimJob, keywords.getTextValue(warrantyAreaInTMAClaimTab), softAssert);
        } else if (currentCompanyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
            appBasedUtils.asserVerification(claimHeaderImporterInClaimJob, keywords.getTextValue(warrantyDistrictInTMAClaimTab), softAssert);
        } else {
            appBasedUtils.asserVerification(claimHeaderImporterInClaimJob, keywords.getTextValue(importerInTMAClaimTab), softAssert);
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "ReferenceNo"), keywords.getTextValue(referenceNoInTMAClaimTab),
                                        softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "ClaimType"), keywords.getTextValue(claimTypeInTMAClaimTab), softAssert);
        if (currentCompanyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
            appBasedUtils.asserVerification(claimHeaderDealerInClaimJob, keywords.getTextValue(businessPartnerInTMAClaimTab), softAssert);
        } else {
            appBasedUtils.asserVerification(claimHeaderDealerInClaimJob, keywords.getTextValue(dealerNoInTMAClaimTab), softAssert);
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "JobNo"), keywords.getTextValue(jobNoInTMAClaimTab), softAssert);
        appBasedUtils.asserVerification(DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), keywords.getTextValue(repairDateInTMAClaimTab), softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : this method is to validate VehicleData in TMA -> Claim job tab
     ****************************************/
    public void validateVehicleDataInformation(HashMap<String, Object> newClaimJobData) {
        softAssert = new SoftAssert();
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "ChassisId"), keywords.getTextValue(chassisIdInTMAClaimTab), softAssert);
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJobData, "Mileage"))) {
            appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "Mileage"), keywords.getTextValue(mileageInTMAClaimTab), softAssert);
        } else {
            appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "OperatingHours"), keywords.getTextValue(operatingHoursInTMAClaimTab),
                                            softAssert);
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "MarketingType"), keywords.getTextValue(marketingTypeTMAClaimTab),
                                        softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : this method is to validate validateCostSummaryInformation in TMA -> Claim job tab
     ****************************************/
    public void validateCostSummaryInformation(HashMap<String, Object> newClaimJobData) {
        String[] temp;
        softAssert = new SoftAssert();
        appBasedUtils.asserVerification(debitCodeInClaim, keywords.getTextValue(debitCodeInTMAClaimTab), softAssert);
        appBasedUtils.asserVerification(labourCostInClaim.replaceAll("\\s", ""),
                                        appBasedUtils.addDoubleValueInString(keywords.getTextValue(labourCostInTMAClaimTab)), softAssert);
        appBasedUtils.asserVerification(materialCostInClaim.replaceAll("\\s", ""),
                                        appBasedUtils.addDoubleValueInString(keywords.getTextValue(materialCostInTMAClaimTab)), softAssert);
        currentCompanyName = keywords.getTextValue(companyNameElement);
        if (currentCompanyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT) || currentCompanyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
            appBasedUtils.asserVerification(otherCostInClaim, appBasedUtils.addDoubleValueInString(keywords.getTextValue(otherCostInTMAClaimTabForVCEOrVPC)),
                                            softAssert);
            appBasedUtils.asserVerification(appBasedUtils.addDoubleValueInString(labourTotalHoursInClaim),
                                            keywords.getTextValue(totalLabourHoursInTMAClaimTabForVCEOrVPC), softAssert);
            appBasedUtils.asserVerification(netTotalInClaim.replaceAll("\\s", ""), keywords.getTextValue(claimJobTotalInTMAClaimTabForVCEOrVPC), softAssert);
        } else {
            appBasedUtils.asserVerification(otherCostInClaim.replaceAll("\\s", ""),
                                            appBasedUtils.addDoubleValueInString(keywords.getTextValue(otherCostInTMAClaimTab)), softAssert);
            appBasedUtils.asserVerification(appBasedUtils.addDoubleValueInString(labourTotalHoursInClaim), keywords.getTextValue(totalLabourHoursInTMAClaimTab),
                                            softAssert);
            appBasedUtils.asserVerification(netTotalInClaim.replaceAll("\\s", ""), keywords.getTextValue(claimJobTotalInTMAClaimTab), softAssert);
        }
        String partNumberInClaimJob = appBasedUtils.getStringData(newClaimJobData, "CausalPartPrefix") + " - "
                + appBasedUtils.getStringData(newClaimJobData, "CausalPartNo");
        String value = keywords.getTextValue(causalPartInTMAClaimTab);
        keywords.waitTime1(1);
        temp = value.split("\\(");
        appBasedUtils.asserVerification(partNumberInClaimJob, temp[0].trim(), softAssert);
        temp = keywords.getTextValue(defectCodeInTMAClaimTab).split("\\-");
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "DefectCode"), temp[0].trim(), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "ReplacedPartPrefix"),
                                        keywords.getTextValue(includedMaterialPrefixInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "ReplacedPartNo"),
                                        keywords.getTextValue(includedMaterialPartNumberInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(includedMaterialDescInCliam, keywords.getTextValue(includedMaterialPartDescInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.addDoubleValueInString(appBasedUtils.getStringData(newClaimJobData, "MaterialQuantity")),
                                        keywords.getTextValue(includedMaterialQuantityInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "OperationNumberLabour"),
                                        keywords.getTextValue(labourOperationInTMAClaimJobTab), softAssert);
        /*
         * appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "OperationDescLabourTab"),
         * keywords.getTextValue(labourOperationDescInTMAClaimJobTab), softAssert);
         */
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "LabourQuantity"),
                                        keywords.getTextValue(labourOperationQuantityTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.addDoubleValueInString(appBasedUtils.getStringData(newClaimJobData, "LabourHours")),
                                        keywords.getTextValue(labourOperationHoursQuantityInTMAClaimJobTab), softAssert);
        keywords.scrollPage(driver, 500);
        appBasedUtils.asserVerification(labourCostPerHourValueInClaim, keywords.getTextValue(labourRateInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "DescriptionOtherCost"),
                                        keywords.getTextValue(otherCostDescInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "ComplaintRemarks"), keywords.getTextValue(complaintInTMAClaimJobTab),
                                        softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "CauseRemarks"), keywords.getTextValue(causeInTMAClaimJobTab), softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "CorrectionRemarks"), keywords.getTextValue(correctionInTMAClaimJobTab),
                                        softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJobData, "CommentsRemarks"), keywords.getTextValue(commentInTMAClaimJobTab),
                                        softAssert);
        softAssert.assertAll();
    }

    /**************************************
     * #Method Name: write Reference No in excel sheet
     */
    public void getClaimCostDetailsInClaimJob(HashMap<String, Object> data) {
        keywords.scrollPage(driver, -250);
        keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
        labourTotalHoursInClaim = keywords.getTextValue(LabourTotalHoursInClaimJob);
        keywords.clickOnButton(driver, claimJobAssessmentTab);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        debitCodeInClaim = keywords.getTextValue(debitCodeValueInClaimJob);
        netTotalInClaim = keywords.getTextValue(totalCostInInClaimJob);
        labourCostInClaim = keywords.getTextValue(labourCostInInClaimJob);
        materialCostInClaim = keywords.getTextValue(materialCostInInClaimJob);
        currentCompanyName = keywords.getTextValue(companyNameElement);
        if ((currentCompanyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (currentCompanyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            otherCostInClaim = keywords.getTextValue(otherCostsCostInInClaimJobForVCEOrVPC);
        } else {
            otherCostInClaim = keywords.getTextValue(otherCostsCostInInClaimJob);
        }
        keywords.clickOnButton(driver, labourTabManageClaimJob);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        labourCostOrHourDescInClaim = keywords.getTextValue(labourCostOrHourDesc);
        labourCostPerHourValueInClaim = keywords.getTextValue(labourCostPerHourInInClaimJob);
        keywords.clickOnButton(driver, claimJobMaterial);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        includedMaterialDescInCliam = keywords.getTextValue(includedMaterialDescInCliamJob);
        includedMaterialPriceInClaim = keywords.getTextValue(includedMaterialPriceInClaimJob);
        includedMaterialPriceInClaim = includedMaterialPriceInClaim.replaceAll("\\s", "");
        keywords.clickOnButton(driver, claimJobHeader);
        claimHeaderImporterInClaimJob = keywords.getTextValue(claimHeaderImporter);
        claimHeaderDealerInClaimJob = keywords.getTextValue(claimHeaderDealer);
        data.put("LabourTotalHoursInClaim", labourTotalHoursInClaim);
        APP_LOGS.info("debitCodeInClaim: " + debitCodeInClaim + "\n" + "netTotalInClaim: " + netTotalInClaim + "\n" + "labourCostInClaim: " + labourCostInClaim
                + "\n" + "materialCostInClaim: " + materialCostInClaim + "\n" + "otherCostInClaim: " + otherCostInClaim + "\n" + "labourCostOrHourDescInClaim: "
                + labourCostOrHourDescInClaim + "\n" + "labourCostPerHourValueInClaim: " + labourCostPerHourValueInClaim + "\n"
                + "includedMaterialDescInCliam: " + includedMaterialDescInCliam + "\n" + "includedMaterialUnitPriceInClaim: " + includedMaterialPriceInClaim
                + "\n" + "LabourTotalHoursInClaim: " + labourTotalHoursInClaim + "\n" + "claimReferenceNumber:");
        keywords.clickOnButton(driver, historyBackButtonToClose);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clickOnButton(driver, historyBackButtonToClose);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitTime1(3);
    }

    /****************************************
     * #Description : this method is to validate VehicleData in TMA -> Claim job tab
     *
     * @throws IOException
     ****************************************/
    public void clickOnViewPrintableVersion(HashMap<String, Object> newClaimJobData) throws IOException {
        softAssert = new SoftAssert();
        keywords.clickOnButton(driver, viewPrintableVersion);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        Path file = Paths.get(".", "resources\\DownloadLocation\\", appBasedUtils.getStringData(newClaimJobData, "DownloadedFileName"));
        File filePath = new File(file.toString());
        keywords.waitTime1(6);
        pdfReadAndValidate.readPDFFile(filePath, newClaimJobData);
        validateMaterialInstructionPDFdata(newClaimJobData, softAssert);
        pdfReadAndValidate.deleteFileFromFolder(filePath);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : this method is to validate the dealer name and reference number in pdf
     *
     * @throws IOException
     ****************************************/
    public void validateMaterialInstructionPDFdata(HashMap<String, Object> expectedPDFData, SoftAssert testCaseAssertion) {
        keywords.APP_LOGS.info("Verifying \"Reference Number\" and \"Dealer\" in the document.");
        testCaseAssertion.assertTrue(keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "ReferenceNo"), PDFReadAndValidate.gstrPDFFileData));
        testCaseAssertion.assertTrue(keywords.assertContains(appBasedUtils.getStringData(expectedPDFData, "Dealer"), PDFReadAndValidate.gstrPDFFileData));
    }

    /****************************************
     * #Description : this method is for third party analyse
     ****************************************/
    public void analyseMaterialThirdParty(HashMap<String, Object> tmaData) {
        saveTMA();
        softAssert = new SoftAssert();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clickOnButton(driver, analyse);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.selectVisibleText(thirdPartyTypeSelect, appBasedUtils.getStringData(tmaData, "ThirdPartyType"));
        keywords.input(thirdPartyId, appBasedUtils.getStringData(tmaData, "ThirdPartyId"), "Third party id value");
        keywords.selectVisibleText(tmaNextStatus, appBasedUtils.getStringData(tmaData, "TMAStatusThirdPartyAnalyse"));
        keywords.waitTime1(1);
        saveTMA();
        ExtentTestManager.getTest().log(Status.INFO, "TMA status status is Third party analyse: Ref no. " + appBasedUtils.getStringData(tmaData, "ReferenceNo"));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(tmaData, "TMAStatusThirdPartyAnalyse"), keywords.getTextValue(tmaStatus), softAssert);
        keywords.clickOnButton(driver, initiateShipping);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.input(attNAme, appBasedUtils.getStringData(tmaData, "TMAAttName"), "TMA Atlernate Name");
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        appBasedUtils.asserVerification(keywords.getFirstSelectedItemFromDropDown(shippingTypeList), appBasedUtils.getStringData(tmaData, "ShippingType"),
                                        softAssert);
        appBasedUtils.asserVerification(keywords.getFirstSelectedItemFromDropDown(thirdPartyTypeList), appBasedUtils.getStringData(tmaData, "ThirdPartyType"),
                                        softAssert);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(tmaData, "ThirdPartyId"), keywords.getAttributeValue(thirdPartyId, "value"), softAssert);
        keywords.clickOnButton(driver, shipMaterialButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        assertTrue(!(keywords.elementIsDisplayed(shipMaterialButton, "Verify initiate shipping button is not displayed")));
        softAssert.assertAll();
        keywords.waitTime1(2);
        keywords.APP_LOGS.info("Shipping id :" + keywords.getTextValue(shippingIdAfterSave));
        ExtentTestManager.getTest().log(Status.INFO, "Shipping Id :" + keywords.getTextValue(shippingIdAfterSave));
    }

    /****************************************
     * #Description : this method is to create shippping document
     ****************************************/
    public void createShippingDocument(HashMap<String, Object> tmaData) throws IOException {
        softAssert = new SoftAssert();
        keywords.clickOnButton(driver, createShippingDocument);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        Path file = Paths.get(".", "resources\\DownloadLocation\\", appBasedUtils.getStringData(tmaData, "DownloadedFileName"));
        File filePath = new File(file.toString());
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        pdfReadAndValidate.readPDFFile(filePath, tmaData);
        validateMaterialInstructionPDFdata(tmaData, softAssert);
        pdfReadAndValidate.deleteFileFromFolder(filePath);
        keywords.waitTime1(1);
        ExtentTestManager.getTest().log(Status.INFO, "Create shipping document is generated ");

    }

    /****************************************
     * #Description : this method is to add to storage
     ****************************************/
    public void addToStorage(HashMap<String, Object> tmaData) {
        keywords.clickOnButton(driver, historyBackButtonToClose);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clickOnButton(driver, addToStorage);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.input(storageLocationName, appBasedUtils.getStringData(tmaData, "StorageLocationName"), "Storage location name");
        keywords.clickOnButton(driver, saveLocation);
        if (keywords.elementIsDisplayed(errorMsgElement, "error message is displayed")) {
            APP_LOGS.info(keywords.getTextValue(errorMsgElement));
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsgElement));
            Assert.fail(keywords.getTextValue(errorMsgElement));
        }
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        ExtentTestManager.getTest().log(Status.INFO, "Material is stored in location : "+ appBasedUtils.getStringData(tmaData, "StorageLocationName"));
    }

    /****************************************
     * #Description : this method is to inititate shipping from the selected storage location
     ****************************************/
    public void selectStorageLocation(HashMap<String, Object> tmaData) throws IOException {
        keywords.selectVisibleText(selectPalletRack, appBasedUtils.getStringData(tmaData, "PalletRacks"));
        keywords.clickOnButton(driver, displayPalletRacks);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clickOnButton(driver, occupiedStorageLocation);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clickOnButton(driver, selectAllFromGridForStorageLocation);
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, initiateShipping);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        createShippingDocument(tmaData);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /****************************************
     * #Description : this method is to download supplier file
     ****************************************/
    public void createSupplierFile(HashMap<String, Object> tmaData) {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitTime1(2);
        String sPathSep = System.getProperty("file.separator");
        String downloadLocationFileForUpdateSeveralClaim = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation";
        pdfReadAndValidate = new PDFReadAndValidate();
        File fileDownloadLocaiton = new File(downloadLocationFileForUpdateSeveralClaim);
        keywords.waitTime1(6);
        keywords.clickOnButton(driver, createSupplierFile);
        keywords.waitTime1(2);
        pdfReadAndValidate.listFolderFiles(fileDownloadLocaiton);
        keywords.waitTime1(4);
        pdfReadAndValidate.cleanFolder(fileDownloadLocaiton);
        APP_LOGS.info("SupplierDocument.xls file is downloaded");
        ExtentTestManager.getTest().log(Status.INFO, "SupplierDocument.xls file is downloaded");
    }
}
