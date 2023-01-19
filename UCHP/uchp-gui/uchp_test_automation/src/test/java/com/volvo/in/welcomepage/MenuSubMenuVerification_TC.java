package com.volvo.in.welcomepage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.vehicle.ViewRepairHistory;
import com.volvo.in.page.welcomepage.MenuSubMenuVerification;

public class MenuSubMenuVerification_TC extends BaseClass
{
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    MenuSubMenuVerification menuSubMenuVerification;
    
    @BeforeClass
    public void setUp(){
        driver=getDriver();
        menuSubMenuVerification = new MenuSubMenuVerification(driver);
    }

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        menuSubMenuVerification = new MenuSubMenuVerification(driver);
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
    
    @SuppressWarnings("unchecked")
    @Test()
    public void TC_01_MenuAndSubMenuVerification_Dealer() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        menuSubMenuVerification.verifyrMenuSubmenu_Dealer(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_02_MenuAndSubMenuVerification_Central() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        menuSubMenuVerification.verifyMenuSubmenu_Central(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_03_MenuAndSubMenuVerification_SystemAdministrator() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        menuSubMenuVerification.verifyMenuSubmenu_SystemAdministrator(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_04_MenuAndSubMenuVerification_CampaignAdminCentral() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        menuSubMenuVerification.verifyMenuSubmenu_CampaignAdminCentral(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_05_MenuAndSubMenuVerification_ClaimAdminCentral() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        menuSubMenuVerification.verifyMenuSubmenu_ClaimAdminCentral(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_06_MenuAndSubMenuVerification_CoverageAdmin() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        menuSubMenuVerification.verifyMenuSubmenu_CoverageAdmin(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_07_MenuAndSubMenuVerification_CreditingAdmCentral() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        menuSubMenuVerification.verifyMenuSubmenu_CreditingAdmCentral(data);
    }

    @SuppressWarnings("unchecked")
    @Test()
    public void TC_08_MenuAndSubMenuVerification_Inspector() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        menuSubMenuVerification.verifyMenuSubmenu_Inspector(data);
    }
    @SuppressWarnings("unchecked")
    @Test()
    public void TC_09_MenuAndSubMenuVerification_TMAAdministrator() throws InterruptedException{
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(9);
        menuSubMenuVerification.verifyMenuSubmenu_TMAAdministrator(data);
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
