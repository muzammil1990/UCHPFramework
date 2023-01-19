package com.volvo.in.campaign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.campaign.DealerOutstandingCampaign;
import com.volvo.in.page.welcomepage.WelcomePage;

public class DealerOutstandingCampaign_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    DealerOutstandingCampaign dealerOutstandingCampaign;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        dealerOutstandingCampaign = new DealerOutstandingCampaign(driver);
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
    @SuppressWarnings("unchecked")
    public void TC_001_SearchDealerOutstandingCampaign_CampaignResponsibleDealer() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        dealerOutstandingCampaign.navigateToDealerOutstandingCampaignInformation();
        dealerOutstandingCampaign.searchResponsibleDealerOutstandingCampaign(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_002_SearchDealerOutstandingCampaign_SellingDealer() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        dealerOutstandingCampaign.navigateToDealerOutstandingCampaignInformation();
        dealerOutstandingCampaign.searchSellingDealerOutstandingCampaign(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_002_SearchDealerOutstandingCampaign_ServicingDealer() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        dealerOutstandingCampaign.navigateToDealerOutstandingCampaignInformation();
        dealerOutstandingCampaign.searchServicingDealerOutstandingCampaign(data);
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
