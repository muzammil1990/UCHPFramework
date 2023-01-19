package com.volvo.in.claim;

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

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.page.claim.AdvanceSearch;
import com.volvo.in.page.welcomepage.WelcomePage;

public class AdvanceSearch_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    AdvanceSearch advanceSearch;
    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        advanceSearch = new AdvanceSearch(driver);
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
    public void TC_001_SearchWithImpDealerImpRefNoAndStatus() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithImpDealerImpRefNoAndStatus(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_002_SearchWithImpDealerRefNoAndSearchType() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithImporterDealerRefNoAndSearchType(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_003_SearchWithImpDealerRefNoAndTMAStatus() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithImpDealerRefNoAndTMAStatus(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_004_SearchWithImpAndRepairDate() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithImpAndRepairDate(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_005_SearchWithImpAndCreditedDate() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithImpAndCreditedDate(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_006_SearchWithRegDateAndClaimStatus() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithRegDateAndClaimStatus(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_007_SearchWithRepairOrderNumberClaimStatusAndTMAStatus() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.searchWithRepairOrderNumberClaimStatusAndTMAStatus(data);
    }

   @Test
    public void TC_008_VerifyAdvancedSearchGUI_VerifySearchCriteriaGroups() {
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.VerifySearchCriteriaGroups();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_009_AdvancedSearchGUI_verifyListOfValuesInStatusDropDown() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifyListOfValuesInStatusDropDown(data);
        ExtentTestManager.getTest().log(Status.INFO, "List of items verified in Status dropdown");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_010_AdvancedSearchGUI_verifyListOfValuesInSearchTypeDropDown() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(9);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifyListOfValuesInSearchTypeDropDown(data);
        ExtentTestManager.getTest().log(Status.INFO, "List of items verified in Search type dropdown");

    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_011_AdvancedSearchGUI_verifyListOfValuesInTMAStatusDropDown() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(10);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifyListOfValuesInTMAStatusDropDown(data);
        ExtentTestManager.getTest().log(Status.INFO, "List of items verified in TMAStatus dropdown");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_012_AdvancedSearchGUI_verifyTypeofconcernContractnoClaimtypeC() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(11);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifyTypeofconcernContractnoClaimtypeC(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_013_AdvancedSearchGUI_verifySearchresultsTypeofconcernContractNo() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(12);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifySearchresultsTypeofconcernContractnoClaimtypeC(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_014_AdvancedSearchGUI_verifyTypeofConcernContractnoAndTypeofclaimB() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(13);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifyTypeofConcernContractnoAndTypeofclaimB(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_015_AdvancedSearchGUI_verifytypeofconcernandcontractnoSearchresultsB() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(14);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.verifytypeofconcernandcontractnoSearchresultsB(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_016_ContractSearchWithImpDealerImpRefNoAndStatus() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(15);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.contractSearchWithImpDealerImpRefNoAndStatus(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_017_ContractSearchWithImpDealerRefNoAndSearchType() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(16);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.contractSearchWithImpDealerRefNoAndSearchType(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_018_ContractSearchWithImpDealerAndRepairDate() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(17);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.contractSearchWithImpDealerAndRepairDate(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_019_ContractSearchWithImpDealerAndCreditedDate() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(18);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.contractSearchWithImpDealerAndCreditedDate(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_020_ContractSearchWithRegDateAndClaimStatus() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(19);
        advanceSearch.navigateToAdvanceSearch();
        advanceSearch.contractSearchWithRegDateAndClaimStatus(data);
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
