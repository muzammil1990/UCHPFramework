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
import com.volvo.in.page.crediting.PrintOutStandingClaimSummary;
import com.volvo.in.page.welcomepage.WelcomePage;

public class PrintOutStandingClaimSummary_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    PrintOutStandingClaimSummary printOutstndngSumm;
    WebDriver driver;
    @Parameters({  "browserType", "appURL","Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName, @Optional("MissingSecondaryDataFile") String secondaryDataFileName)throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        printOutstndngSumm = new PrintOutStandingClaimSummary(driver);
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

    // execute first testcase before executing any other cases
    @Test()
    @SuppressWarnings("unchecked")
    public void TC_001_printOutstandingClaimSummary_VolvoBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrand(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_002_verifyTheListOfBrandNames() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.verifyListOfBrandNamesInDropDown(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_003_FilterWithImporterDealerCreationDate() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerCreationDate(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_004_filterWithImporterDealerBrandDownloadDocument() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrandDownloadDocument(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_005_printOutstandingClaimSummary_MultiBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrand(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_009_printOutstandingClaimSummary_RenaultBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(9);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrand(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_008_printOutstandingClaimSummary_EicherBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrand(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_010_printOutstandingClaimSummary_SDLG() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrand(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_011_printOutstandingClaimSummary_Terex() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        printOutstndngSumm.navigateToPrintOutStandingSumm(driver);
        printOutstndngSumm.filterWithImporterDealerBrand(data);
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
