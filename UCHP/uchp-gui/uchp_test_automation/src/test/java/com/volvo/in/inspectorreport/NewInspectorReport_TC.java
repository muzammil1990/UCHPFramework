package com.volvo.in.inspectorreport;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.claim.NewClaimJob;
import com.volvo.in.page.claim.UpdateSeveralClaimJobs;
import com.volvo.in.page.inspectorreport.NewInspectorReport;
import com.volvo.in.page.welcomepage.WelcomePage;

public class NewInspectorReport_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    NewInspectorReport newInspectorReport;
    NewClaimJob newClaimjob;
    ManageClaimJobs manageClaimjobs;
    HashMap<String, Object> newInspectorReportData = null;
    HashMap<String, Object> newClaimJobData = null;
    UIUtils keywords = new UIUtils(driver);

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        newInspectorReport = new NewInspectorReport(driver);
        newClaimjob = new NewClaimJob(driver);
        manageClaimjobs = new ManageClaimJobs(driver);
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

    @Test()
    @SuppressWarnings("unchecked")
    public void RT102_24_CreateInspectorReport_Warranty() {
        newInspectorReportData = (HashMap<String, Object>) lstData.get(1);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT102_23_InspectorReportMultipleMatch_Warranty() {
        newInspectorReportData = (HashMap<String, Object>) lstData.get(2);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newInspectorReportData, "UserIDInspector"),
                          appBasedUtils_1.getStringData(newInspectorReportData, "PwdInspector"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        keywords.waitTime1(5);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        keywords.waitTime1(5);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newInspectorReportData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newInspectorReportData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newInspectorReportData);
        manageClaimjobs.updateLabourTabInformation(newInspectorReportData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newInspectorReportData);
        manageClaimjobs.updateRemarksClaimJob(newInspectorReportData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateOneOrMultipleIRmatchedForClaim(newInspectorReportData);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void RT102_24_CreateInspectorReport_Contract() {
        newInspectorReportData = (HashMap<String, Object>) lstData.get(3);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT102_23_InspectorReportMultipleMatch_Contract() {
        newInspectorReportData = (HashMap<String, Object>) lstData.get(4);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newInspectorReportData, "UserIDInspector"),
                          appBasedUtils_1.getStringData(newInspectorReportData, "PwdInspector"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        keywords.waitTime1(5);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        keywords.waitTime1(5);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newInspectorReportData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newInspectorReportData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newInspectorReportData);
        manageClaimjobs.updateLabourTabInformation(newInspectorReportData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newInspectorReportData);
        manageClaimjobs.updateRemarksClaimJob(newInspectorReportData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateOneOrMultipleIRmatchedForClaim(newInspectorReportData);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC_03_VerifyCreatedInspectorReportIsPickedForContractClaim() {
        newInspectorReportData = (HashMap<String, Object>) lstData.get(5);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_1 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_1.getStringData(newInspectorReportData, "UserIDInspector"),
                          appBasedUtils_1.getStringData(newInspectorReportData, "PwdInspector"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        newInspectorReport.navigateToNewInspectorReport();
        newInspectorReport.supplyVehicleInformation_ChassisId(newInspectorReportData);
        newInspectorReport.supplyDescription(newInspectorReportData);
        keywords.waitTime1(5);
        newInspectorReport.validateVehicleDetailsPopulatedInInspectorReport(newInspectorReportData);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils_2 = new AppBasedUtils();
        welcomePage.login(appBasedUtils_2.getStringData(newInspectorReportData, "EvaluatorUserId"),
                          appBasedUtils_2.getStringData(newInspectorReportData, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        manageClaimjobs.userGroup = "Central";
        newClaimjob.navigateToNewClaimJob();
        newClaimjob.createClaimJobUsingVehicle(newInspectorReportData);
        manageClaimjobs.updateLabourTabInformation(newInspectorReportData);
        manageClaimjobs.updateReplacedMaterialClaimJob(newInspectorReportData);
        manageClaimjobs.updateRemarksClaimJob(newInspectorReportData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        manageClaimjobs.validateOneOrMultipleIRmatchedForClaim(newInspectorReportData);
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
