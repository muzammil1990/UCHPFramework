package com.volvo.in.tma;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.AdvanceSearch;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.claim.NewClaimJob;
import com.volvo.in.page.claim.UpdateSeveralClaimJobs;
import com.volvo.in.page.tma.AnalyseMaterial;
import com.volvo.in.page.tma.ManualStartReceiveOrAnalyze;
import com.volvo.in.page.tma.NewMaterialRequest;
import com.volvo.in.page.welcomepage.WelcomePage;

public class NewMaterialRequest_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    NewMaterialRequest newMaterialRequest;
    AnalyseMaterial analyseMaterial;
    NewClaimJob newClaimjob;
    ManageClaimJobs manageClaimjobs;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils;
    ExcelReadAndWrite claimExcelSheet;
    HashMap<String, Object> data = null;
    AdvanceSearch advanceSearch = null;
    String referenceNo = null;
    ManualStartReceiveOrAnalyze manualStartReceiveOrAnalyze = null;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        newMaterialRequest = new NewMaterialRequest(driver);
        analyseMaterial = new AnalyseMaterial(driver);
        manageClaimjobs = new ManageClaimJobs(driver);
        newClaimjob = new NewClaimJob(driver);
        advanceSearch = new AdvanceSearch(driver);
        appBasedUtils = new AppBasedUtils();
        manualStartReceiveOrAnalyze = new ManualStartReceiveOrAnalyze(driver);
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

    @SuppressWarnings("unchecked")
    @Test(priority = 1)
    public void createNewMaterialRequest() {
        data = (HashMap<String, Object>) lstData.get(1);
        newMaterialRequest.navigateToNewMaterialRequest();
        newMaterialRequest.createNewMaterialRequestWarranty(data);
        keywords.waitTime1(5);
    }

    @SuppressWarnings("unchecked")
    @Test(priority = 2)
    public void createNewMaterialRequestForContract() {
        data = (HashMap<String, Object>) lstData.get(2);
        newMaterialRequest.navigateToNewMaterialRequest();
        newMaterialRequest.createNewMaterialRequestWarranty(data);
        keywords.waitTime1(5);
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "SetReferenceNo", priority = 3)
    public void TC01_CreateVehicleWarrantyClaimJobMatchingMaterialRequest(int receiveRefRowNo, int receiveRefColNo) {
        welcomePage.logout(driver);
        data = (HashMap<String, Object>) lstData.get(1);
        keywords.waitTime1(4);
        welcomePage.login(data.get("EvaluatorUserId").toString(), data.get("EvaluatorUserPassword").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(data);
        manageClaimjobs.updateInformationInClaimTabs(data);
        String referenceNo = manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateMINameOnClaim(data);
        claimExcelSheet = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewMaterialRequestAndNewClaim.xlsx",
                                                BaseClass.excelSheetName);
        claimExcelSheet.updateExcelRowData(receiveRefRowNo, receiveRefColNo, referenceNo);

    }

    @SuppressWarnings("unchecked")
    @Test(priority = 8)
    public void TC02_CreateContractClaimAndVerifyMatchingNewMaterialRequest() {
        welcomePage.logout(driver);
        data = (HashMap<String, Object>) lstData.get(2);
        keywords.waitTime1(4);
        welcomePage.login(data.get("EvaluatorUserId").toString(), data.get("EvaluatorUserPassword").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(data);
        manageClaimjobs.updateLabourTabInformation(data);
        manageClaimjobs.updateReplacedMaterialClaimJob(data);
        manageClaimjobs.updateOtherCostClaimJob(data);
        manageClaimjobs.updateRemarksClaimJob(data);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateMINameOnClaim(data);

    }

    @SuppressWarnings("unchecked")
    @Test(priority = 4)
    public void RT_TMA_001_RecieveMaterial() throws IOException {
        ExcelReadAndWrite excel = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewMaterialRequestAndNewClaim.xlsx",
                                                        BaseClass.excelSheetName);
        lstData = excel.readAll();
        data = (HashMap<String, Object>) lstData.get(3);
        welcomePage.logout(driver);
        welcomePage.login(data.get("EvaluatorUserId").toString(), data.get("EvaluatorUserPassword").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithRefNoRepairDate(appBasedUtils.getStringData(data, "ReferenceNo"));
        keywords.waitTime1(2);
        advanceSearch.openTheFirstRecordFromTheSearchResultGrid();
        manualStartReceiveOrAnalyze.getClaimCostDetailsInClaimJob(data);
        welcomePage.logout(driver);
        welcomePage.login(data.get("TMAAdminUserId").toString(), data.get("TMAUserPwd").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manualStartReceiveOrAnalyze.navigateToManualStartReceiveOrAnalyzePage();
        keywords.waitTime1(2);
        manualStartReceiveOrAnalyze.receiveTMAMaterial(data);
    }

    @SuppressWarnings("unchecked")
    @Test(priority = 5)
    public void RT_TMA_002_AnalyseMaterial() throws IOException {
        ExcelReadAndWrite excel = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewMaterialRequestAndNewClaim.xlsx",
                                                        BaseClass.excelSheetName);
        lstData = excel.readAll();
        data = (HashMap<String, Object>) lstData.get(3);
        welcomePage.logout(driver);
        welcomePage.login(data.get("TMAAdminUserId").toString(), data.get("TMAUserPwd").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manualStartReceiveOrAnalyze.navigateToManualStartReceiveOrAnalyzePage();
        manualStartReceiveOrAnalyze.manualStartReceiveOrAnalyze(data);
        manualStartReceiveOrAnalyze.clickOnAnalyzeButtonInManualStartOrReceivePage();
        analyseMaterial.validateTMADecisionPartlyApproved(data);
        manageClaimjobs.closeThePageAndReturnToHomePage();
        welcomePage.logout(driver);
        welcomePage.login(data.get("EvaluatorUserId").toString(), data.get("EvaluatorUserPassword").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithRefNoRepairDate(appBasedUtils.getStringData(data, "ReferenceNo"));
        keywords.waitTime1(2);
        advanceSearch.openTheFirstRecordFromTheSearchResultGrid();
        manageClaimjobs.validateTMADecisionInClaimJobInformationTab(data);
        manageClaimjobs.closeThePageAndReturnToHomePage();
        welcomePage.logout(driver);
        welcomePage.login(data.get("TMACoWorkerUserID").toString(), data.get("TMACoWorkerUserPwd").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manualStartReceiveOrAnalyze.navigateToManualStartReceiveOrAnalyzePage();
        manualStartReceiveOrAnalyze.manualStartReceiveOrAnalyze(data);
        manualStartReceiveOrAnalyze.clickOnAnalyzeButtonInManualStartOrReceivePage();
        analyseMaterial.updateNextTMAStatusToAnalyze(data);
        analyseMaterial.upadteAnaysisSection(data);
        analyseMaterial.validateAdmPreviousAnalysisScreen(data);
        manageClaimjobs.closeThePageAndReturnToHomePage();
        welcomePage.logout(driver);
        welcomePage.login(data.get("EvaluatorUserId").toString(), data.get("EvaluatorUserPassword").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithRefNoRepairDate(appBasedUtils.getStringData(data, "ReferenceNo"));
        keywords.waitTime1(2);
        advanceSearch.openTheFirstRecordFromTheSearchResultGrid();
        manageClaimjobs.validateTMADecisionInClaimJobInformationTab(data);

    }

    @SuppressWarnings("unchecked")
    @Test(priority = 6)
    public void RT_TMA_003_AnalyseMaterialThirdParty() throws IOException {
        ExcelReadAndWrite excel = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewMaterialRequestAndNewClaim.xlsx",
                                                        BaseClass.excelSheetName);
        lstData = excel.readAll();
        data = (HashMap<String, Object>) lstData.get(4);
        welcomePage.logout(driver);
        welcomePage.login(data.get("TMAAdminUserId").toString(), data.get("TMAUserPwd").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manualStartReceiveOrAnalyze.navigateToManualStartReceiveOrAnalyzePage();
        manualStartReceiveOrAnalyze.manualStartReceiveOrAnalyze(data);
        manualStartReceiveOrAnalyze.clickOnRecieveButtonInManualStartOrReceivePage();
        keywords.waitTime1(2);
        manualStartReceiveOrAnalyze.analyseMaterialThirdParty(data);
        manualStartReceiveOrAnalyze.createShippingDocument(data);
        manualStartReceiveOrAnalyze.addToStorage(data);

    }

    @SuppressWarnings("unchecked")
    @Test(priority = 7)
    public void RT_TMA_004_ShippingViaPalletRacks() throws IOException {
        ExcelReadAndWrite excel = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewMaterialRequestAndNewClaim.xlsx",
                                                        BaseClass.excelSheetName);
        lstData = excel.readAll();
        data = (HashMap<String, Object>) lstData.get(4);
        welcomePage.logout(driver);
        welcomePage.login(data.get("TMAAdminUserId").toString(), data.get("TMAUserPwd").toString(), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manualStartReceiveOrAnalyze.navigateToStorageAndShippingPalletRacks();
        manualStartReceiveOrAnalyze.selectStorageLocation(data);
        manualStartReceiveOrAnalyze.createShippingDocument(data);
        manualStartReceiveOrAnalyze.createSupplierFile(data);
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
    
    @DataProvider(name = "SetReferenceNo")
    public static Object[][] setExcelvalues() {
        return new Object[][] { { 4, 103 }, { 5, 103 } };

    }
}
