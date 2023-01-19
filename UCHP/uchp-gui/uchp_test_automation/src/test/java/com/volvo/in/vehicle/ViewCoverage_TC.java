package com.volvo.in.vehicle;

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

import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.vehicle.ViewCoverage;
import com.volvo.in.page.welcomepage.WelcomePage;

public class ViewCoverage_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    ViewCoverage viewCoverage;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        viewCoverage = new ViewCoverage(driver);
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
    public void TC_01_verifyViewCoverageGUI() {
        viewCoverage.navigateToViewCoverage();
        viewCoverage.verifyViewCovergeGUI();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_02_ViewCoverageforVehicle_ChassisNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchCoverageforVehicleChassisNumber(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_03_ViewCoverageforVehicle_RegisterNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchCoverageforVehicleRegisterNumber(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_04_ViewCoverageforVehicle_VIN() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchCoverageforVehicleUsingVINField(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_05_ViewCoverageBrandMarketTypeChassisID() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchWithBrandMarketTypeChassisID(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_06_ViewCoverageBrandUnitNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchWithBrandUnitNumber(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_07_ViewCoverageForSingleVehicleInBrandUnitNumberCriteria() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchWithBrandUnitNumberSingleVehicleScenario(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_08_ViewCoverageForSingleVehicleInBrandMaketyTypeChassisCriteria() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchWithBrandMarketTypeChassisNumberSingleVehicleScenario(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_09_ViewCoverageVerifyListOfBrandNamesInDropDown() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.verifyListOfBrandNamesInDropDown(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_10_ViewCoverageDownLoadCoverageDocument() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        viewCoverage.navigateToViewCoverage();
        viewCoverage.searchCoverageforVehicleUsingVINField(data);
        viewCoverage.downLoadCoverageAttachment();
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
