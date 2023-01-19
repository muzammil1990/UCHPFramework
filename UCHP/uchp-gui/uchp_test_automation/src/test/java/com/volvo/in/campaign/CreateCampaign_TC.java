package com.volvo.in.campaign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.campaign.CreateCampaign;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.claim.NewClaimJob;
import com.volvo.in.page.welcomepage.WelcomePage;

public class CreateCampaign_TC extends BaseClass {
    WebDriver driver;
    CreateCampaign createCampaign;
    ManageClaimJobs manageClaimjobs;
    NewClaimJob claimJob;
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        createCampaign = new CreateCampaign(driver);
        manageClaimjobs = new ManageClaimJobs(driver);
        claimJob = new NewClaimJob(driver);
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
    @Parameters({ "userName", "passWord" })
    @Test()
    public void RT_Campaign_001_CreationOfCampaign(String CampainAdminUserId, String CampaignUserPwd) {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        createCampaign.navigateToNewCampaign();
        createCampaign.setCampaignHeaderDetails(data);
        createCampaign.campaignProgressTab(data);
        createCampaign.campaignGeneralTab(data);
        createCampaign.campaignVehicleTab(data);
        createCampaign.campaignLabourTab(data);
        createCampaign.campaignMaterialTab(data);
        createCampaign.campaignOtherCostTab(data);
        createCampaign.campaignImporterTab(data);
        createCampaign.campaignTSUTab(data);
        createCampaign.setCampaignStatusAsAssigned(data);
        createCampaign.setCampaignStatusAsApproval(data);
        createCampaign.setCampaignStatusAsApproved(data);
       createCampaign.setPartOrder(data);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(data, "PartOderUserId"), appBasedUtils.getStringData(data, "PartOderUserPwd"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        createCampaign.navigateToPartOderMenu();
        createCampaign.partOrderSave(data);
        welcomePage.logout(driver);
        welcomePage.login(CampainAdminUserId, CampaignUserPwd, driver);
        createCampaign.navigateToSearchCampaign();
        createCampaign.campaignSearchWithSccCode(data);
        createCampaign.search();
        createCampaign.selectFirstRow();
        createCampaign.setCampaignStatusAsPrepared(data);
        createCampaign.setCampaignStatusAsReleased(data);
        createCampaign.validateGeneralTab(data);
        createCampaign.validateVehicleTab(data);
        createCampaign.validateLabourTab(data);
        createCampaign.validateMaterialTab(data);
        createCampaign.validateOtherCostTab(data);
        createCampaign.validateTSUTab(data);
        createCampaign.validateImporterTab(data);
        createCampaign.validateDecisionTab(data);
        createCampaign.writeSccCodeInExcelSheet(data);
    }

    @SuppressWarnings("unchecked")
    @Parameters({ "ClaimHandlerCentral", "passWord" })
    @Test(dependsOnMethods = { "RT_Campaign_001_CreationOfCampaign" }, alwaysRun = true)
    public void RT_Campaign_003_CreationOfCampaignClaim(String ClaimHandlerCentral, String CampaignUserPwd) throws IOException {
        welcomePage.logout(driver);
        ExcelReadAndWrite excel = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewClaimJob.xlsx",
                                                        BaseClass.excelSheetName);
        lstSecondaryData = excel.readAll();
        HashMap<String, Object> claimData = (HashMap<String, Object>) lstSecondaryData.get(3);
        welcomePage.login(ClaimHandlerCentral, CampaignUserPwd, driver);
        welcomePage.switchToCompany(BaseClass.excelSheetName, driver);
        claimJob.navigateToNewClaimJob();
        claimJob.createClaimJobUsingVehicle(claimData);
        claimJob.supplyCampaignDetails(claimData);
        manageClaimjobs.updateOtherCostClaimJob(claimData);
        manageClaimjobs.saveClaimJobNewClaimJobFlow();
        manageClaimjobs.userGroup = "Evaluator";
        manageClaimjobs.releaseClaimjob();
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        manageClaimjobs.validateSavedDataInLabourTab(data, "NewCampaign.xlsx");
        manageClaimjobs.validateSavedDataInMaterialTab(data);
        manageClaimjobs.validateSavedDataInOtherCostTabForCampaignClaimSave(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT_Campaign_002_CreationOfCampaignWithoutPartsAndTSU() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        createCampaign.navigateToNewCampaign();
        createCampaign.setCampaignHeaderDetails(data);
        createCampaign.campaignProgressTab(data);
        createCampaign.campaignGeneralTab(data);
        createCampaign.campaignVehicleTab(data);
        createCampaign.campaignLabourTab(data);
        createCampaign.campaignMaterialTab(data);
        createCampaign.campaignOtherCostTab(data);
        createCampaign.campaignImporterTab(data);
        createCampaign.setCampaignStatusAsAssigned(data);
        createCampaign.setCampaignStatusAsApproval(data);
        createCampaign.setCampaignStatusAsApproved(data);
        createCampaign.setCampaignStatusAsPrepared(data);
        createCampaign.setCampaignStatusAsReleased(data);
        createCampaign.validateGeneralTab(data);
        createCampaign.validateVehicleTab(data);
        createCampaign.validateLabourTab(data);
        createCampaign.validateMaterialTab(data);
        createCampaign.validateOtherCostTab(data);
        createCampaign.validateImporterTab(data);
        createCampaign.validateDecisionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void RT_Campaign_004_SearchCampaign() {
        createCampaign.navigateToSearchCampaign();
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        createCampaign.verifyGuiInSearchCampaignPage();
        createCampaign.searchWithCampaignId(data);
        createCampaign.searchWithSCCCode(data);
        createCampaign.searchWithTitle(data);
        createCampaign.searchWithConnectedQJ(data);
        createCampaign.searchWithMainOperation(data);
        createCampaign.searchWithIncludedOperation(data);
        createCampaign.searchWithPartNo(data);
        createCampaign.searchWithOrderNo(data);
        createCampaign.searchWithFunctionGroup(data);
        createCampaign.searchWithLastRepairDate(data);
        createCampaign.searchWithStatus(data);
    }
    
    @SuppressWarnings("unchecked")
    @Parameters({ "userName", "passWord" })
    @Test()
    public void RT_Campaign_001_CreationOfCampaignWithVehicleSelection(String CampainAdminUserId, String CampaignUserPwd) {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        createCampaign.navigateToNewCampaign();
        createCampaign.setCampaignHeaderDetails(data);
        createCampaign.campaignProgressTab(data);
        createCampaign.campaignGeneralTab(data);
        createCampaign.campaignVehicleTab(data);
        createCampaign.navigateToSearchCampaign();
        createCampaign.campaignSearchWithSccCode(data);
        createCampaign.search();
        createCampaign.selectFirstRow();
        createCampaign.campaignLabourTab(data);
        createCampaign.campaignMaterialTab(data);
        createCampaign.campaignOtherCostTab(data);
        createCampaign.campaignImporterTab(data);
        createCampaign.campaignTSUTab(data);
        createCampaign.setCampaignStatusAsAssigned(data);
        createCampaign.setCampaignStatusAsApproval(data);
        createCampaign.setCampaignStatusAsApproved(data);
        createCampaign.setPartOrder(data);
        welcomePage.logout(driver);
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        welcomePage.login(appBasedUtils.getStringData(data, "PartOderUserId"), appBasedUtils.getStringData(data, "PartOderUserPwd"), driver);
        welcomePage.switchToCompany(excelSheetName, driver);
        createCampaign.navigateToPartOderMenu();
        createCampaign.partOrderSave(data);
        welcomePage.logout(driver);
        welcomePage.login(CampainAdminUserId, CampaignUserPwd, driver);
        createCampaign.navigateToSearchCampaign();
        createCampaign.campaignSearchWithSccCode(data);
        createCampaign.search();
        createCampaign.selectFirstRow();
        createCampaign.setCampaignStatusAsPrepared(data);
        createCampaign.setCampaignStatusAsReleased(data);
        createCampaign.validateGeneralTab(data);
        createCampaign.validateVehicleTab(data);
        createCampaign.validateLabourTab(data);
        createCampaign.validateMaterialTab(data);
        createCampaign.validateOtherCostTab(data);
        createCampaign.validateTSUTab(data);
        createCampaign.validateImporterTab(data);
        createCampaign.validateDecisionTab(data);
        createCampaign.writeSccCodeInExcelSheet(data);
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
