package com.volvo.in.claim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.AdvanceSearch;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.claim.NewClaimJob;
import com.volvo.in.page.coverage.NewCoverageCreation;
import com.volvo.in.page.maintain.AutoException;
import com.volvo.in.page.maintain.ContractPriceSource;
import com.volvo.in.page.maintain.HoldForCrediting;
import com.volvo.in.page.maintain.StairsSplit;
import com.volvo.in.page.validation.NewScenario;
import com.volvo.in.page.welcomepage.WelcomePage;

public class NewClaimJob_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    NewClaimJob newClaimjob;
    ManageClaimJobs manageClaimjobs;
    UIUtils keywords;
    ContractPriceSource contractPriceSource;
    HoldForCrediting holdForCrediting;
    NewCoverageCreation newCoverageCreation;
    AutoException autoException;
    NewScenario newScenario;
    StairsSplit stairsSplit;
    HashMap<String, Object> newClaimJobData = null;
    HashMap<String, Object> NewCoverage = null;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        manageClaimjobs = new ManageClaimJobs(driver);
        keywords = new UIUtils(driver);
        newClaimjob = new NewClaimJob(driver);
        holdForCrediting = new HoldForCrediting(driver);
        autoException = new AutoException(driver);
        contractPriceSource = new ContractPriceSource(driver);
        newCoverageCreation = new NewCoverageCreation(driver);
        stairsSplit = new StairsSplit(driver);
        newScenario = new NewScenario(driver);
        excelSheetName = sheetName;
        ExcelReadAndWrite excelReadWriteSecondaryDataFile;
        excelReadWrite = new ExcelReadAndWrite(AppConstants.RESOURCES_TEST_DATA + getEnvironmentName() + "\\" + testDataFileName, sheetName);
        lstData = excelReadWrite.readAll();
        if (!secondaryDataFileName.equals("MissingSecondaryDataFile")) {
            excelReadWriteSecondaryDataFile = new ExcelReadAndWrite(AppConstants.RESOURCES_TEST_DATA + getEnvironmentName() + "\\" + secondaryDataFileName,
                                                                    sheetName);
            lstSecondaryData = excelReadWriteSecondaryDataFile.readAll();
        }
    }

    @Parameters({ "Company", "userName", "passWord" })
    @BeforeMethod(alwaysRun = true)
    public void loginBeforeMethod(String sheetName, String userName, String password) {
        welcomePage = new WelcomePage(driver);
        applicationUserName = userName;
        if (getEnvironmentName().equalsIgnoreCase("QA")) {
            welcomePage.loginQA(userName, password);
        } else {
            welcomePage.login(userName, password, driver);
        }
        welcomePage.switchToCompany(sheetName, driver);

    }

    @DataProvider(name = "SaveTestData")
    public static Object[][] saveTestDataForOtherPages() {
        return new Object[][] { { "AdvanceSearch.xlsx", 2, 7 } };
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "SaveTestData")
    public void TC01_CreateVehicleWarrantyClaimJob(String targetTestDatafileName, int rowNumber, int columnNumber) {
        newClaimJobData = (HashMap<String, Object>) lstData.get(1);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        AppBasedUtils appUtil = new AppBasedUtils();
        appUtil.updateApplicationTestData(targetTestDatafileName, rowNumber, columnNumber, manageClaimjobs.claimReferenceNumber);
        appUtil.updateApplicationTestData(targetTestDatafileName, rowNumber, columnNumber - 1,
                                          appUtil.substractValueFromString(manageClaimjobs.claimReferenceNumber, 500));
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC02_CreatePartsClaimJobWithoutVehicle() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(2);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createPartsClaimJob(newClaimJobData);
        manageClaimjobs.updateNoVehicleClaimJobTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC03_CreateCampaignClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(3);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        newClaimjob.supplyCampaignDetails(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC04_CreateServiceWarrantyClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(4);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC05_CreateTechnicalGoodWillClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(5);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        // updateClaimJobInformationTab test this
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC06_CreateSpecificationDeviationClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(6);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC07_CreateCommercialGoodwillClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(7);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC08_CreateBreakdownClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(8);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC09_CreateProductionRelatedFailureClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(9);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC10_CreateExtendedCoverageClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(10);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC11_CreateVehicleWarrantyClaimJobWithVSTLabour() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(11);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC12_CreateAttachmentWarrantyClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(12);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    /*
     * TC to create a claim both Mileage and Operating hours. Add new operation in labour tab of the claim
     */
    @SuppressWarnings("unchecked")
    @Test()
    public void TC13_RegisterAndCompleteClaimJob_LabourTab() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(13);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.addNewLabourDetailsToClaim(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyLabourNetTotalInLabourTabAndClaimJobInfomationTab();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC14_RegisterAndCompleteClaimJob_MaterialTab() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(14);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.addNewMaterialDetailsToClaim(newClaimJobData);
        manageClaimjobs.copyMaterialDetailsInClaim(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyMaterialNetTotalInMaterialTabAndClaimJobInfomationTab();
        manageClaimjobs.releaseClaimjob();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC15_RegisterAndCompleteClaimJob_OtherCost() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(15);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.reloadClaimJob();
        manageClaimjobs.verifyOtherCostNetTotalInOtherCostTabAndClaimJobInfomationTab();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC16_RegisterClaimJob_Attachment_UploadHigherVersionDocuments() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(16);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.attachDocumentToClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyUploadedAttachments(newClaimJobData);
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC17_RegisterClaimJob_Attachment_UploadLowerVersionDocuments() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(17);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.attachDocumentToClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyUploadedAttachments(newClaimJobData);
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC18_EvaluateClaimJob_ClaimJob_AppealRequest() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(18);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.updateAppealStatusOfClaimJob(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC19_CreateClaimJobPickVehicleFromVehicleList() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(19);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.supplyMileageAndOperatingHoursAndCreateClaimUsingVehicleList(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.addNewLabourDetailsToClaim(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyLabourNetTotalInLabourTabAndClaimJobInfomationTab();
        manageClaimjobs.releaseClaimjob();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC20_CreateClaimJobVerifyLabourAndPartInformation() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(20);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.verifyFieldsAndSupplyVSTLabourDetails(newClaimJobData);
        manageClaimjobs.verifyPartPrefixAndEnterValues(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyMaterialNetTotalInMaterialTabAndClaimJobInfomationTab();
        manageClaimjobs.releaseClaimjob();
        newClaimJobData = (HashMap<String, Object>) lstData.get(2);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createPartsClaimJob(newClaimJobData);
        manageClaimjobs.updateNoVehicleClaimJobTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC21_CreateErrorClaimJobAndDeleteClaimJob() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(21);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.deleteClaimJob();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT103_19_EvaluateClaimJob_FullApprove() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(22);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.forceClaimJob();
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        String refNo = manageClaimjobs.claimReferenceNumber;
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchClaimUsingRefNumberAndReturnSearchStatus(refNo, "1");
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.applyDebitCodeAndValidate(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT103_20_EvaluateClaimJob_FullReject() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(23);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.forceClaimJob();
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        String refNo = manageClaimjobs.claimReferenceNumber;
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchWithRefNoRepairDate(refNo);
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.applyRejectCodeAndValidate(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT103_21_EvaluateClaimJob_PartialReject() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(24);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.forceClaimJob();
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        String refNo = manageClaimjobs.claimReferenceNumber;
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchWithRefNoRepairDate(refNo);
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.partialRejectAndValidate(newClaimJobData);

    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT102_17_AutoException() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(25);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "UserIDClaimAdmCentral"),
                          appBasedUtils_1.getStringData(newClaimJobData, "PwdClaimAdmCentral"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        autoException.navigateToAutoException();
        autoException.createAutoException(newClaimJobData);
        autoException.validateAutoException(newClaimJobData);
        welcomePage.logout(driver);
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        String refNo = manageClaimjobs.claimReferenceNumber;
        manageClaimjobs.closeThePageAndReturnToHomePage();
        welcomePage.logout(driver);
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchWithRefNoRepairDate(refNo);
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.getAutoexceptionReason(newClaimJobData);

    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=CTDI,labour rate code=TWS and labour time indicator=claimed")
    public void TC_06_CreateServiceContractClaimJobWhereCAFGreaterthanContractDefinition() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(26);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_01_CreateContractClaimJobAndVerifyPartsPriceFetchingWithDiffrentPriceSourceCombinationSequenceYes() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(27);
        contractPriceSource.navigateToMaintainImporterContractPriceSource();
        contractPriceSource.selectPriceSource(newClaimJobData);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_20_CreateContractClaimWithMoreThan2PartsAndPartialyRejectInAssessmentTab() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(28);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.forceClaimJob();
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.addNewMaterialDetailsToClaim(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        String refNo = manageClaimjobs.claimReferenceNumber;
        manageClaimjobs.closeThePageAndReturnToHomePage();
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchWithRefNoRepairDate(refNo);
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.partialRejectAndValidate(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_21_CreateContractClaimJobAndVerifyResponsibleDealerInRepairHeader() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(29);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        newClaimjob.checkResponsibleDealerNumber(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=CTDI,labour rate code=TWS and labour time indicator=claimed")
    public void TC_08_CreateServiceContractClaimJobWhereCAFEqualToContractDefinition() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(30);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=CTDI,labour rate code=TWS and labour time indicator=claimed")
    public void TC_09_CreateRepairContractClaimJobWhereCAFEqualToContractDefinition() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(31);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=Fixed,labour rate code=001 and labour time indicator=labour master")
    public void TC_10_CreateRepairContractClaimJobWhereCAFLessThanInContractDefinition() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(32);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=Fixed,labour rate code=TWS and labour time indicator=labour master")
    public void TC_11_CreateServiceContractClaimJobWhereCAFLessThanInContractDefinition() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(33);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=CTDI,labour rate code=TWS and labour time indicator=claimed")
    public void TC_07_CreateRepairContractClaimJobWhereCAFGreaterthanContractDefinition() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(34);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "labour rate indicator=CTDI,labour rate code=TWS and labour time indicator=claimed")
    public void TC_14_CreateHoldForCreditingForContractVerifyContractClaimWithHoldForCrediting() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(35);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "UserIDClaimAdmCentral"),
                          appBasedUtils_1.getStringData(newClaimJobData, "PwdClaimAdmCentral"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        holdForCrediting.navigateToNewHoldForCrediting();
        holdForCrediting.createHoldForCrediting(newClaimJobData);
        holdForCrediting.validateHoldForCrediting(newClaimJobData);
        String HoldForCreditingReason = holdForCrediting.descriptionText;
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.validateHoldForCreditingandReason(HoldForCreditingReason);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC_04_VerifyReturnOfNewContractClaimWithReturnCodes() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(36);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.returnClaimJob(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Manual evalution for service claim")
    public void TC_16_VerifyManualEvaluationForServiceClaim() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(37);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.updateDebitCodeInAssessmenttab(newClaimJobData);
        manageClaimjobs.validateDebitCodeInAssessmenttab(newClaimJobData);
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateDebitCodeInAssessmenttab(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Adding contract Auto exception and checking contract claim is Auto failed with reason")
    public void TC_13_CreateAutoExceptionForContractVerifyContractClaimWithAutoFailed() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(38);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "UserIDClaimAdmCentral"),
                          appBasedUtils_1.getStringData(newClaimJobData, "PwdClaimAdmCentral"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        autoException.navigateToAutoException();
        autoException.createAutoException(newClaimJobData);
        autoException.validateAutoException(newClaimJobData);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validatecontractDetailsPopulatedInClaimJob(newClaimJobData);
        manageClaimjobs.getAutoexceptionReason(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Creating and validating Hold for crediting for warranty and validating warranty claim with Hold for crediting")
    public void RT102_12_RegisterAndCompleteClaimJob_Verify_HoldForCrediting() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(39);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "UserIDClaimAdmCentral"),
                          appBasedUtils_1.getStringData(newClaimJobData, "PwdClaimAdmCentral"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        holdForCrediting.navigateToNewHoldForCrediting();
        holdForCrediting.createHoldForCrediting(newClaimJobData);
        holdForCrediting.validateHoldForCrediting(newClaimJobData);
        String holdForCreditingReason = holdForCrediting.descriptionText;
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.updateTravelExpensesClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.validateHoldForCreditingandReason(holdForCreditingReason);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "In claim update CAF in assessment tab and check the updated and old cost in labour ,material ,travel and other cost")
    public void RT102_15_CostAdjustmentFactor() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(40);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateTravelExpensesClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        String refNo = manageClaimjobs.claimReferenceNumber;
        manageClaimjobs.closeThePageAndReturnToHomePage();
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchWithRefNoRepairDate(refNo);
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.validateSavedDataInClaimTabs(newClaimJobData);
        manageClaimjobs.updateNewCAFInAssessmenttab(newClaimJobData);
        manageClaimjobs.validateCAFInAssessmenttab(newClaimJobData);
        manageClaimjobs.validateCostChangedInLabourMaterialOthertab(newClaimJobData);
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Create coverage with defect code range ,repaired importer and claim type & create claim with same data and verify coverage is picked")
    public void RT_Coverage_003_CreateClaimJobForCoverage() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(42);
        NewCoverage = (HashMap<String, Object>) lstSecondaryData.get(3);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils1.getStringData(NewCoverage, "EvaluatorUserId"), appBasedUtils1.getStringData(NewCoverage, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        newCoverageCreation.navigateToNewCoverage();
        newCoverageCreation.createNewCoverageWithMandatoryFields(NewCoverage);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils2.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils2.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createPartsClaimJob(newClaimJobData);
        manageClaimjobs.updateNoVehicleClaimJobTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateCoverage(NewCoverage);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils3 = new AppBasedUtils();
        welcomePage.login(appBasedUtils3.getStringData(NewCoverage, "EvaluatorUserId"), appBasedUtils3.getStringData(NewCoverage, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        newCoverageCreation.navigateToListCoverages();
        newCoverageCreation.deleteCoverage(NewCoverage);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT103_21_EvaluateClaimJob_SplitFunction() {
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        newClaimJobData = (HashMap<String, Object>) lstData.get(43);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.forceClaimJob();
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        String refNo = manageClaimjobs.claimReferenceNumber;
        welcomePage.logout(driver);
        welcomePage.login(appBasedUtils.getStringData(newClaimJobData, "EvaluatorUserId"), appBasedUtils.getStringData(newClaimJobData, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Evaluator";
        AdvanceSearch advancesearch = new AdvanceSearch(driver);
        advancesearch.navigateToAdvanceSearch();
        advancesearch.searchWithRefNoRepairDate(refNo);
        advancesearch.openAClaimJob(refNo);
        manageClaimjobs.partialRejectAndValidate(newClaimJobData);
        manageClaimjobs.splitCostAndValidate(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();

    }

    @SuppressWarnings("unchecked")
    @Test(description = "Creates new stairs and maps to the claim")
    public void RT103_25_EvaluateClaimJob_StairsSplit() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(41);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "UserIDClaimAdmCentral"),
                          appBasedUtils_1.getStringData(newClaimJobData, "PwdClaimAdmCentral"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        stairsSplit.navigateToNewStairs();
        stairsSplit.createNewStairs(newClaimJobData);
        welcomePage.logout(driver);
        welcomePage.login(appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_1.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.updateTravelExpensesClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateStairsInAssessmentTab(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();

    }

    @SuppressWarnings("unchecked")
    @Test(description = "Verify view contract info link in contract repair header")
    public void TC_19_VerifyViewContractInfoLinkInContractClaimHeader() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(45);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        newClaimjob.verifyViewContractInfoLink(newClaimJobData);
        manageClaimjobs.closeThePageAndReturnToHomePage();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_102_10_RegisterAClaimAndValidateTheLabourHoursCost() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(46);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateInformationInClaimTabs(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateLabourHoursCost(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_102_04_RegisterAndCompleteClaimJob_EnterOtherCost_WithOutSysParameter() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(47);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.setUplannedStopInClaimJobInfoTab(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateRepairedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateOtherCostClaimJob(newClaimJobData);
        manageClaimjobs.validateIfCategoryisDisabledInOtherCostTab();
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "dealer";
        manageClaimjobs.releaseClaimjob();
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_102_13_RegisterAClaimAndValidateTheTravelExepnseCostDetails() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(48);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateTravelExpensesClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        newClaimjob.validateTravelExpenseCostDetails(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_012_RegisterclaimAndUpdateTheHeaderInformation_Vehicle() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(49);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateTravelExpensesClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.verifyHeaderScreen(newClaimJobData);
        manageClaimjobs.verifyRepairHeaderSection(newClaimJobData);
        manageClaimjobs.verifyVehicleIdentificationSection(newClaimJobData);
        newClaimjob.updateHeaderInformation(newClaimJobData);
        manageClaimjobs.verifyUpdatedHeaderInformation(newClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_004_VerifyScenarioPickedForAClaim() {
        newClaimJobData = (HashMap<String, Object>) lstData.get(50);
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newClaimJobData);
        manageClaimjobs.updateLabourTabInformation(newClaimJobData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newClaimJobData);
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newClaimJobData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);

        newScenario.navigateToListCheckScenario();
        newScenario.searchScenarioAndValidate(newClaimJobData);

    }

    @AfterMethod(alwaysRun = true)
    public void logoutAfterMethod(ITestResult result) {
        welcomePage.closeUnsavedAlertAndRemoveErrorIcon(driver);
        welcomePage.logout(driver);
    }

    @AfterClass(alwaysRun = true)
    protected void afterSuite() {
        driver.close();
    }
}
