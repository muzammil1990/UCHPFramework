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
import com.volvo.in.page.vehicle.ViewRepairHistory;
import com.volvo.in.page.welcomepage.WelcomePage;

public class ViewRepairHistory_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    ViewRepairHistory viewRepairHistory;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        viewRepairHistory = new ViewRepairHistory(driver);
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
    public void TC_01_verifyViewRepairHistoryGUI() {
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.verifyViewRepairHistoryGUI();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_02_ViewRepairHistoryforVehicle_ChassisNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.searchVehicleRepairInformationChassisNumber(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_03_ViewRepairHistoryforVehicle_RegisterNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.ViewRepairHistoryforVehicleRegisterNumber(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_04_ViewRepairHistoryforVehicle_VIN() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.searchVehicleRepairInformationVIN(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_05_ViewRepairHistoryforVehicleBrandMarketTypeChassisNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.searchWithBrandMarketTypeChassisID(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_06_ViewRepairHistoryUsingVehicleBrandUnitNumber() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.searchWithBrandUnitNumber(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_07_ViewRepairHistorySingleVehicleInBrandUnitNumberCriteria() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(9);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.searchWithBrandUnitNumberSingleVehicleScenario(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_08_ViewRepairHistorySingleVehicleInBrandMarketTypeChassisNumCriteria() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(10);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.searchWithBrandMarketTypeChassisNumSingleVehicleScenario(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_09_ViewRepairHistoryVerifyListOfBrandNamesInDropDown() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.verifyListOfBrandNamesInDropDown(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_10_ViewRepairHistoryVerifyTypeofConcernContractnoB() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.verifyTypeofConcernContractnoB(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_11_ViewRepairHistoryVerifyTypeofConcernContractnoSearchresultsB() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.VerifyTypeofConcernContractnoSearchresultsB(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_19_Vehicle_ViewRepairHistory_OpenAClaimAndVerifyHeaderDetailsAndReturnToHistoryPage() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(11);
        viewRepairHistory.navigateToViewRepairHistory();
        viewRepairHistory.verifyHeaderDetailsAndReturnToHistoryPage(data);
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
