package com.volvo.in.claim;

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
import com.volvo.in.page.claim.ListClaimJobs;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.welcomepage.WelcomePage;

public class ManageClaimJobs_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    ManageClaimJobs manageClaimjobs;
    ListClaimJobs listClaimJob;
    HashMap<String, Object> listClaimJobsData = null;
    HashMap<String, Object> manageClaimJobData = null;
    AdvanceSearch advanceSearch = null;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        listClaimJob = new ListClaimJobs(driver);
        manageClaimjobs = new ManageClaimJobs(driver);
        advanceSearch = new AdvanceSearch(driver);
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
    @Test
    public void TC01_SearchAndVerifyRefNoRepDateInNotFinishedAndOpenClaimJob() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(3);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(1);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.listAndVerifyClaimStatusInNotFinished(listClaimJobsData);
        manageClaimjobs.openNotFinishedClaimJobAndVerifyDetails();
        manageClaimjobs.updateInformationInClaimTabs(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC02_UpdatePartPriceForClaimJobOpenedFromNotFinishedTab() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(39);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(2);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchClaimsForManageClaimJob(listClaimJobsData);
        manageClaimjobs.openNotFinishedClaimJobAndVerifyDetails();
        manageClaimjobs.updateInformationInClaimTabs(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC03_UpdateOtherCostLabourCategoryForClaimJobOpenedFromNotFinishedTab() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(40);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(3);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchClaimsForManageClaimJob(listClaimJobsData);
        manageClaimjobs.openNotFinishedClaimJobAndVerifyDetails();
        manageClaimjobs.updateInformationInClaimTabs(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC04_UpdateClosedAppealStateClaimJobFromAppealTab() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(41);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(4);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchClaimsForManageClaimJob(listClaimJobsData);
        manageClaimjobs.OpenAndUpdateAppealStatusClaimInAppealTab();
        manageClaimjobs.updateRemarksInReleasedClaimJob(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC05_UpdateRequestAppealStateClaimJobFromAppealTab() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(42);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(5);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchClaimsForManageClaimJob(listClaimJobsData);
        manageClaimjobs.OpenAndUpdateAppealStatusClaimInAppealTab();
        manageClaimjobs.updateRemarksInReleasedClaimJob(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC05_AddWarrantyClaimJobForClaimJobOpenedFromNotFinishedTab() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(22);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(5);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchClaimsForManageClaimJob(listClaimJobsData);
        manageClaimjobs.openNotFinishedClaimJobAndVerifyDetails();
        manageClaimjobs.addNewVehilceClaimJobForExistingClaim(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC06_AddPartsClaimJobForClaimJobOpenedFromNotFinishedTab() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(22);
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(6);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchClaimsForManageClaimJob(listClaimJobsData);
        manageClaimjobs.openNotFinishedClaimJobAndVerifyDetails();
        manageClaimjobs.addNewNoVehilceClaimJobForExistingClaim(manageClaimJobData);
        manageClaimjobs.saveClaimjobUpdateClaimJobFlow();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC03_searchWithMITypeInMaterialInstructionTabAndOpenClaimJob() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(7);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchWithMITypeInMaterialInstructionTab(listClaimJobsData);
        // Verify claim details in list and claim job
        manageClaimjobs.searchWithMITypeInMaterialInstructionTab(listClaimJobsData);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC04_searchWithAppealStatusInAppealTabAndOpenClaimJob() {
        listClaimJobsData = (HashMap<String, Object>) lstData.get(12);
        listClaimJob.navigateToListClaimJob();
        listClaimJob.searchWithAppealStatusInAppealTab(listClaimJobsData);
        manageClaimjobs.OpenAndUpdateAppealStatusClaimInAppealTab();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT102_16_ReturnClaimWarranty() {
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(8);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.selectTypeOfConcern(manageClaimJobData);
        advanceSearch.searchWithRegDateAndClaimStatus(manageClaimJobData);
        advanceSearch.openTheFirstRecordFromTheSearchResultGrid();
        manageClaimjobs.returnClaimJob(manageClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT102_16_ReturnClaimContract() {
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(9);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.selectTypeOfConcern(manageClaimJobData);
        advanceSearch.searchWithRegDateAndClaimStatus(manageClaimJobData);
        advanceSearch.openTheFirstRecordFromTheSearchResultGrid();
        manageClaimjobs.returnClaimJob(manageClaimJobData);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT103_29_EvaluateClaimJob_ReturnClaim_AppealStatus() {
        manageClaimJobData = (HashMap<String, Object>) lstSecondaryData.get(10);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.selectTypeOfConcern(manageClaimJobData);
        advanceSearch.searchWithRegDateAndClaimStatus(manageClaimJobData);
        advanceSearch.openTheFirstRecordFromTheSearchResultGrid();
        String ref = manageClaimjobs.returnClaimJob(manageClaimJobData);
        manageClaimjobs.getTheAppealStatusAfterReturningClaim();
        manageClaimjobs.clickOnClaimJobHeader(manageClaimJobData);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(manageClaimJobData, "UserID"), appBasedUtils.getStringData(manageClaimJobData, "Pwd"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithImpDealerImpRefNoAndStatus(manageClaimJobData);
        advanceSearch.openAClaimJob(ref);
        manageClaimjobs.getTheAppealStatusAfterReturningClaim();
        manageClaimjobs.updateAppealStatusOfClaimJob(manageClaimJobData);
        manageClaimjobs.verifyTheLogsInApprealTab(manageClaimJobData);

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