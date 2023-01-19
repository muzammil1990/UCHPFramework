package com.volvo.in.crediting;

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
import com.volvo.in.page.crediting.CreateOutstandingClaimSummary;
import com.volvo.in.page.welcomepage.WelcomePage;

public class CreateOutstandingClaimSummary_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    CreateOutstandingClaimSummary outstandingClaimSummary;
    WebDriver driver;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        outstandingClaimSummary = new CreateOutstandingClaimSummary(driver);
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

    @Parameters({ "Company", "userName", "passWord", })
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
    public void TC_001_CreateOutstandingClaimSummary_MultipleBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_002_CreateOutstandingClaimSummary_VolvoBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_007_CreateOutstandingClaimSummary_RenaultBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_005_CreateOutstandingClaimSummary_UDBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_006_CreateOutstandingClaimSummary_EicherBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_003_CreateOutstandingClaimSummary_ValidateModelDailogue() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.validateModelDailogueMessages(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_004_CreateOutstandingClaimSummary_SDLGBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_005_CreateOutstandingClaimSummary_TerexBrand(WebDriver driver) {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        outstandingClaimSummary.navigateToCreateOutStandingSummary(driver);
        outstandingClaimSummary.createDocument(data);
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
