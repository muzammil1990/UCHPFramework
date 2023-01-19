package com.volvo.in.crediting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.crediting.CreateOutstandingClaimSummaryCompany;
import com.volvo.in.page.crediting.PrintCreditDocument;
import com.volvo.in.page.welcomepage.WelcomePage;

//@Listeners(ListenerClass.class)
public class PrintCreditDocument_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    PrintCreditDocument printCreditDoc;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        printCreditDoc = new PrintCreditDocument(driver);
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

    @DataProvider(name = "SaveCreditDocumentNumber")
    public static Object[][] saveTestDataForOtherPages() {
        return new Object[][] { { "PrintCreditDocument.xlsx.", 5, 8 } };
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
    public void TC_001_PrintCreditDocument_SearchWithImporter() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        printCreditDoc.navigateToPrintCreditDocument();
        // Print document based on importer and dealer
        printCreditDoc.searchWithImporter(data);
    }

    // Print document based on importer and dealer
    @Test(dataProvider = "SaveCreditDocumentNumber")
    @SuppressWarnings("unchecked")
    public void TC_002_PrintCreditDocument_SearchWithDealer(String targetTestDatafileName, int rowNumber, int columnNumber) {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        printCreditDoc.navigateToPrintCreditDocument();
        printCreditDoc.searchWithDealer(data);
        Reporter.log("Search with dealer" + data.get("Dealer").toString() + " was successful");
        AppBasedUtils appUtil = new AppBasedUtils();
        appUtil.updateApplicationTestData(targetTestDatafileName, rowNumber, columnNumber, printCreditDoc.creditDocumentNumberString);
        appUtil.updateApplicationTestData(targetTestDatafileName, rowNumber + 2, columnNumber, printCreditDoc.creditDocumentNumberString);
        appUtil.updateApplicationTestData(targetTestDatafileName, rowNumber + 4, columnNumber, printCreditDoc.creditDocumentNumberString);
    }

    // Print document based on importer and dealer
    @Test
    @SuppressWarnings("unchecked")
    public void TC_003_PrintCreditDocument_SearchWithBrand() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        printCreditDoc.navigateToPrintCreditDocument();
        printCreditDoc.searchWithBrand(data);
    }

    // Print document based on importer and dealer
    @Test
    @SuppressWarnings("unchecked")
    public void TC_004_PrintCreditDocument_SearchWithCreditDocumentNo() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        printCreditDoc.navigateToPrintCreditDocument();
        printCreditDoc.searchWithCreditDocumentNo(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_006_searchWithAll() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        printCreditDoc.navigateToPrintCreditDocument();
        printCreditDoc.searchWithAll(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_007_PrintCreditDocument_VerifyListOfBrandNamesInDropDown() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        printCreditDoc.navigateToPrintCreditDocument();
        printCreditDoc.verifyListOfBrandNamesInDropDown(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_008_PrintCreditDocument_SearchWithCreditDocumentNoPDFDocument() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        printCreditDoc.navigateToPrintCreditDocument();
        printCreditDoc.searchWithCreditDocumentNoVerifyPDFDocument(data);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TC_009_PrintCreditDocument_SearchWithToC() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(9);
        printCreditDoc.navigateToPrintCreditDocument();
        // Print document based on importer and dealer
        printCreditDoc.searchWithtypeOfConcern(data);
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
