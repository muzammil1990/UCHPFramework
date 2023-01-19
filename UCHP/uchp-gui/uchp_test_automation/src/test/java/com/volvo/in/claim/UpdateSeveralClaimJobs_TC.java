package com.volvo.in.claim;

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
import com.volvo.in.page.claim.ListClaimJobs;
import com.volvo.in.page.claim.UpdateSeveralClaimJobs;
import com.volvo.in.page.welcomepage.WelcomePage;

public class UpdateSeveralClaimJobs_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    UpdateSeveralClaimJobs updateSeveralClaimJobs;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        updateSeveralClaimJobs = new UpdateSeveralClaimJobs(driver);
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

    @Test(description = "description search with importer,dealer,Reg no, Status, Chassis series,Chassis no,causual part")
    @SuppressWarnings("unchecked")
    public void RT107_Claim_015_ClaimUpdate() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        updateSeveralClaimJobs.navigateToSearchClaimJobsPage();
        updateSeveralClaimJobs.searchWithImporterDealerChassisRegNoAndStatus(data);
    }

    @Test(description = "description search with importer,dealer,Reg no, Status, Chassis series,Chassis no,causual part")
    @SuppressWarnings("unchecked")
    public void RT107_Claim_015_ClaimUpdateContract() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        updateSeveralClaimJobs.navigateToSearchClaimJobsPage();
        updateSeveralClaimJobs.searchWithImporterDealerChassisRegNoAndStatus(data);
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
