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
import com.volvo.in.page.vehicle.ViewCampaign;
import com.volvo.in.page.welcomepage.WelcomePage;

public class ViewCampaign_TC extends BaseClass{
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    ViewCampaign viewCampaign;
    
    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        viewCampaign = new ViewCampaign(driver);
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
    
    
    //    TC_131_02_ViewVehicleCampaign
    
    @Parameters({ "Company","userName", "passWord" })
    @BeforeMethod(alwaysRun = true)
    public void initializeTestBaseSetup(String sheetName,String userName, String password) {
            welcomePage = new WelcomePage(driver);
            applicationUserName = userName;
            if (getEnvironmentName().equalsIgnoreCase("QA")){
                welcomePage.loginQA(userName, password);
            }
            else{
                welcomePage.login(userName, password, driver);
            }
            welcomePage.switchToCompany(sheetName, driver);
    }
    
    @Test
    public void TC_01_VerifyViewCampaignGUI() { 
        viewCampaign.navigateToViewCampaignPage();
        viewCampaign.verifyViewCampaignGUI();
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void TC_02_ViewCampaignforVehicle_ChassisNumber() { 
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        viewCampaign.navigateToViewCampaignPage();
        viewCampaign.searchCampaignforVehicleChassisNumber(data);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void TC_03_ViewCampaignforVehicle_RegisterNumber() { 
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        viewCampaign.navigateToViewCampaignPage();
        viewCampaign.searchCampaignforVehicleRegisterNumber(data);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void TC_04_ViewCampaignforVehicle_VIN() { 
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        viewCampaign.navigateToViewCampaignPage();
        viewCampaign.searchCampaignforVehicleVIN(data);
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
