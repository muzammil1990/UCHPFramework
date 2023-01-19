package com.volvo.in.vehicle;

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
import com.volvo.in.page.vehicle.ViewContractCoverage;
import com.volvo.in.page.welcomepage.WelcomePage;

public class ViewContractCoverage_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    ViewContractCoverage viewCoverage;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        viewCoverage = new ViewContractCoverage(driver);
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

    // TC_131_02_ViewVehicleCoverage

    @Test
    public void TC_01_verifyViewCoverageGUI() {
        viewCoverage.navigateToViewContractCoverage();
        viewCoverage.verifyViewContractCovergeGUI();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_02_ViewContractCoverage_ElementTypeService() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        viewCoverage.navigateToViewContractCoverage();
        viewCoverage.searchContractCoverage(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_03_ViewContractCoverage_ElementTypeRepair() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        viewCoverage.navigateToViewContractCoverage();
        viewCoverage.searchContractCoverage(data);
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