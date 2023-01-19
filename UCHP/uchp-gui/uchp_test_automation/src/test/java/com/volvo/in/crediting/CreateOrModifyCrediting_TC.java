package com.volvo.in.crediting;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.claim.NewClaimJob;
import com.volvo.in.page.crediting.ApproveCreditRuns;
import com.volvo.in.page.crediting.CreateOrModifyCrediting;
import com.volvo.in.page.welcomepage.WelcomePage;

public class CreateOrModifyCrediting_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    CreateOrModifyCrediting createOrModifyCrediting;
    NewClaimJob newClaimjob;
    ManageClaimJobs manageClaimjobs;
    ApproveCreditRuns approveCreditRuns;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        createOrModifyCrediting = new CreateOrModifyCrediting(driver);
        approveCreditRuns = new ApproveCreditRuns(driver);
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

    @DataProvider(name = "SaveTestData")
    public static Object[][] saveTestDataForOtherPages() {
        return new Object[][] { { "PrintCreditDocument.xlsx", 7, 8 } };
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

    @Test(dataProvider = "SaveTestData")
    @SuppressWarnings("unchecked")
    public void RT_Crediting_001_ImporterLevel_Crediting(String targetTestDatafileName, int rowNumber, int columnNumber) throws ParseException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleImporterLevelCrediting(data);
        AppBasedUtils appUtil = new AppBasedUtils();
        appUtil.updateApplicationTestData(targetTestDatafileName, rowNumber, columnNumber, CreateOrModifyCrediting.creditDocNoFromGrid);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_22_Verify_Importer_Level_Crediting_Is_Successful_For_Crediting_Type_16() throws ParseException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleImporterLevelCrediting(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_21_Verify_Dealer_LevelCrediting_Successful_ForCrediting_Type_16() throws ParseException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleDealerLevelCrediting(data);
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Create claim and run brand level crediting")
    public void RT_Crediting_013_BrandVolvo_Central() throws ParseException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        HashMap<String, Object> newClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(44);
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
        manageClaimjobs.updateRemarksClaimJob(newClaimJobData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.releaseClaimjob();
        welcomePage.logout(driver);
        welcomePage.login(appBasedUtils.getStringData(data, "EvaluatorUserId"), appBasedUtils.getStringData(data, "EvaluatorPassword"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleBrandLevelCrediting(data);
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Create claim and run crediting without brand selected ")
    public void RT_Crediting_014_NoBrand_Central() throws ParseException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleNoBrandLevelCrediting(data);
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Create claim and run dealer level crediting")
    public void RT_Crediting_004_DealerLevel_Crediting() throws ParseException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleDealerLevelCrediting(data);
    }
    
    @SuppressWarnings("unchecked")
    @Parameters({ "userName", "passWord" })
    @Test(description = "Create claim and run dealer level crediting")
    public void TC_01_Verify_Scheduled_and_Approved_CreditRuns(String SysAdmUserId, String SysAdmUserPwd) throws ParseException, IOException {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.scheduleDealerLevelCreditingBeforeApprove(data);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(data, "EvaluatorUserId"), appBasedUtils.getStringData(data, "EvaluatorPassword"),
                          driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        approveCreditRuns.navigateToApproveCreditRuns(driver);
        approveCreditRuns.approveCreditRun();
        welcomePage.logout(driver);
        welcomePage.login(SysAdmUserId, SysAdmUserPwd, driver);
        createOrModifyCrediting.navigateToCreateOrModifyCrediting();
        createOrModifyCrediting.runDealerLevelCreditingAfterApprove(data);
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
