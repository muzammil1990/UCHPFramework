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
import com.volvo.in.page.claim.UpdateSeveralClaimJobs;
import com.volvo.in.page.tma.SearchTMAInformation;
import com.volvo.in.page.welcomepage.WelcomePage;

public class SearchTMAInformation_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    AppBasedUtils appBasedUtils;
    SearchTMAInformation searchTMAInformation;
    HashMap<String, Object> searchTMAInformationData = null;
    UIUtils keywords = new UIUtils(driver);

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        searchTMAInformation = new SearchTMAInformation(driver);
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
    @Test(description = "Verifying search result of TMA information with Importer, dealer, refno and Material request id")
    public void RT_TMA_005_SearchTMAinformationWithImporterDealerRefNoJobNoAndMatReqID() {
        HashMap<String, Object> searchTMAInformationData = (HashMap<String, Object>) lstData.get(1);
        searchTMAInformation.testCaseName = "RT_TMA_005_SearchTMAinformationWithImporterDealerRefNoJobNoAndMatReqID";
        searchTMAInformation.navigateToSearchTMAInformation();
        searchTMAInformation.searchTMAInfoWithImporterDealerRefNoJobNoAndMatReqID(searchTMAInformationData);
        searchTMAInformation.validateSearchTMAInfoResult(searchTMAInformationData);
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Verifying search result of TMA information with With Decision And Status")
    public void RT_TMA_005_SearchTMAinformationWithDecisionAndStatus() {
        HashMap<String, Object> searchTMAInformationData = (HashMap<String, Object>) lstData.get(1);
        searchTMAInformation.testCaseName = "RT_TMA_005_SearchTMAinformationWithDecisionAndStatus";
        searchTMAInformation.navigateToSearchTMAInformation();
        searchTMAInformation.searchTMAInfoWithDecisionAndStatus(searchTMAInformationData);
        searchTMAInformation.validateSearchTMAInfoResult(searchTMAInformationData);
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Verifying search result of TMA information with RootCause Id, dealer, Part Details and Third Party Details")
    public void RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails() {
        HashMap<String, Object> searchTMAInformationData = (HashMap<String, Object>) lstData.get(1);
        searchTMAInformation.testCaseName = "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails";
        searchTMAInformation.navigateToSearchTMAInformation();
        searchTMAInformation.searchTMAInfoWithRootCauseIdPartDetailsAndThirdPartyDetails(searchTMAInformationData);
        searchTMAInformation.validateSearchTMAInfoResult(searchTMAInformationData);
    }

    @SuppressWarnings("unchecked")
    @Test(description = "Verifying search result of TMA information with Importer, dealer and verify root summary table")
    public void RT_TMA_005_SearchTMAinformationWithImporterDealerAndVerifyingRootSummaryTable() {
        HashMap<String, Object> searchTMAInformationData = (HashMap<String, Object>) lstData.get(1);
        searchTMAInformation.navigateToSearchTMAInformation();
        searchTMAInformation.searchTMAInfoWithRepairDate(searchTMAInformationData);
        searchTMAInformation.validateSearchTMAInfoResultTable();
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
