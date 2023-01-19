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
import com.volvo.in.page.crediting.PrintCreditDocument;
import com.volvo.in.page.crediting.PrintOutStandingClaimSummaryCompany;
import com.volvo.in.page.welcomepage.WelcomePage;

//@Listeners(ListenerClass.class)
public class PrintOutStandingClaimSummaryCompany_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    PrintOutStandingClaimSummaryCompany printOutstndngSummCompany;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        printOutstndngSummCompany = new PrintOutStandingClaimSummaryCompany(driver);
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

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_001_printOutstandingClaimSummaryCompany_CreationDate() {

        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        printOutstndngSummCompany.navigateToPrintOutStandingSumm();
        printOutstndngSummCompany.filterWithCreationDate(data);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void TC_001_printOutstandingClaimSummaryCompany_GridValidation() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        printOutstndngSummCompany.navigateToPrintOutStandingSumm();
        printOutstndngSummCompany.filterWithNoConditionAndGridValidatioin(data);
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
