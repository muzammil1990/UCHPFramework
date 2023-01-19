package com.volvo.in.maintain;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.UpdateSeveralClaimJobs;
import com.volvo.in.page.maintain.CampaignSetup;
import com.volvo.in.page.welcomepage.WelcomePage;

public class MaintainCampaingnSetup_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    CampaignSetup maintainCampaignSetup;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        maintainCampaignSetup = new CampaignSetup(driver);
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


    @Test
    @SuppressWarnings("unchecked")
    public void RT250_005_MaintainCampaign_Create() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        maintainCampaignSetup.navigateToSccTypes();
        maintainCampaignSetup.createSccTypes(data);
        maintainCampaignSetup.navigateToRegion();
        maintainCampaignSetup.createRegion(data);
        maintainCampaignSetup.navigateToSupplierContribution();
        maintainCampaignSetup.createSupplierContribution(data);
        maintainCampaignSetup.navigateToOriginalDocuments();
        maintainCampaignSetup.createOriginalDocuments(data);
        maintainCampaignSetup.navigateToCampaignBoardAssignees();
        maintainCampaignSetup.createCampaignboardassignees(data);
        maintainCampaignSetup.navigateToTranslationlanguages();
        maintainCampaignSetup.createTranslationLanguages(data);
        maintainCampaignSetup.navigateToImpCampAdmInfo();
        maintainCampaignSetup.createImpCampgnAdmInfo(data);
        maintainCampaignSetup.navigateToCampaignConditions();
        maintainCampaignSetup.createCampaignConditions(data);
        maintainCampaignSetup.navigateToMaterialOrderParameters();
        maintainCampaignSetup.createMaterialOrderParam(data);
        maintainCampaignSetup.navigateToLeadTimeWarningLimits();
        maintainCampaignSetup.createLeadTimeWarningLimit(data);
        maintainCampaignSetup.navigateToReasonCodes();
        maintainCampaignSetup.createReasonCodes(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void RT250_006_007_MaintainCampaign_ViewandDelete() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        maintainCampaignSetup.navigateToRegion();
        maintainCampaignSetup.viewRegionAndDelete(data);
        maintainCampaignSetup.navigateToSccTypes();
        maintainCampaignSetup.viewSccTypeAndDelete(data);
        maintainCampaignSetup.navigateToOriginalDocuments();
        maintainCampaignSetup.viewOriginalDocumentsAndDelete(data);
        maintainCampaignSetup.navigateToCampaignBoardAssignees();
        maintainCampaignSetup.viewCampBrdAgnsAndDelete(data);
        maintainCampaignSetup.navigateToTranslationlanguages();
        maintainCampaignSetup.viewTranslationLanguagesAndDelete(data);
        maintainCampaignSetup.navigateToImpCampAdmInfo();
        maintainCampaignSetup.viewImporterCampaignAdmininfoAndDelete(data);
        maintainCampaignSetup.navigateToCampaignConditions();
        maintainCampaignSetup.viewCampaignConditionsAndDelete(data);
        maintainCampaignSetup.navigateToMaterialOrderParameters();
        maintainCampaignSetup.viewMaterialOrderParameterAndDelete(data);
        maintainCampaignSetup.navigateToLeadTimeWarningLimits();
        maintainCampaignSetup.viewLeadTimeWarningLimitsAndDelete(data);
        maintainCampaignSetup.navigateToReasonCodes();
        maintainCampaignSetup.viewReasonCodeAndDelete(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void RT250_008_MaintainCampaign_Modify() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        maintainCampaignSetup.navigateToSccTypes();
        maintainCampaignSetup.viewSccTypeAndModify(data);
        maintainCampaignSetup.navigateToRegion();
        maintainCampaignSetup.viewRegionAndModify(data);
        maintainCampaignSetup.navigateToOriginalDocuments();
        maintainCampaignSetup.viewOriginalDocumentsAndModify(data);
        maintainCampaignSetup.navigateToCampaignBoardAssignees();
        maintainCampaignSetup.viewCampBrdAgnsAndModify(data);
        maintainCampaignSetup.navigateToTranslationlanguages();
        maintainCampaignSetup.viewTranslationLanguagesAndModify(data);
        maintainCampaignSetup.navigateToImpCampAdmInfo();
        maintainCampaignSetup.viewImporterCampaignAdmininfoAndModify(data);
        maintainCampaignSetup.navigateToCampaignConditions();
        maintainCampaignSetup.viewCampaignConditionsAndModify(data);
        maintainCampaignSetup.navigateToMaterialOrderParameters();
        maintainCampaignSetup.viewMaterialOrderParameterAndModify(data);
        maintainCampaignSetup.navigateToLeadTimeWarningLimits();
        maintainCampaignSetup.viewLeadTimeWarningLimitsAndModify(data);
        maintainCampaignSetup.navigateToReasonCodes();
        maintainCampaignSetup.viewReasonCodeAndModify(data);
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
