package com.volvo.in.maintain;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.claim.ListClaimJobs;
import com.volvo.in.page.maintain.VAT;
import com.volvo.in.page.welcomepage.WelcomePage;

public class MaintainVAT_TC extends BaseClass {
    WebDriver driver;
    VAT vat;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        vat = new VAT(driver);
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

    @Test(priority = 1)
    @SuppressWarnings("unchecked")
    public void TC_Maintain_VAT_Create() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        vat.navigateToVAT();
        vat.createNewVat(data);
    }

    @Test(priority = 2)
    @SuppressWarnings("unchecked")
    public void TC_Maintain_VAT_Update() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        vat.navigateToVAT();
        vat.updateExisitingVat(data);
    }

    @Test(priority = 3)
    @SuppressWarnings("unchecked")
    public void TC_Maintain_VAT_Delete() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        vat.navigateToVAT();
        vat.deleteExisitingVat(data);
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
