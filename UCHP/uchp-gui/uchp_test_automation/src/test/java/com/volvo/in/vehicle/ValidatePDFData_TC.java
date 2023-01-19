/*package com.volvo.in.vehicle;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.ListenerClass;
import com.volvo.in.page.crediting.ValidatePDFData;
@Listeners(ListenerClass.class)
public class ValidatePDFData_TC extends BaseClass {

    WebDriver driver;
    ExcelReadAndWrite excelReadWrite;
    public Logger APP_LOGS = Logger.getLogger("UCHP-Logger");
    List<Object> lstData;
    
    @BeforeClass
    public void setUp() {
        driver=getDriver();
        excelReadWrite = new ExcelReadAndWrite(".\\resources\\TestData\\PDFDocumentTestData.xlsx","OutStandingClaimSummary");
        lstData = excelReadWrite.readAll();
    }
    
    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
        APP_LOGS.info("Test case is being executed is :::"+method.getName()); 
       
    }
    @Test
    public void ValidatePDFdataTC1() throws InterruptedException{
        ValidatePDFData validatepdfdata = new ValidatePDFData(driver);
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(0);
        
        validatepdfdata.navigateToPrintCreditDocument();
        validatepdfdata.downlaoadAndVerifydata(data);
        
        //printCreditDoc.searchWithImporterDealer(data);
    }
    
    @Test
    public void ValidateExceldataTC2() throws InterruptedException, IOException{
        ValidatePDFData validateExceldata = new ValidatePDFData(driver);
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(0);
        
        validateExceldata.navigateToPrintCreditDocument();
        validateExceldata.downlaoadAndVerifyExceldata(data);
        
        //printCreditDoc.searchWithImporterDealer(data);
    }
    


    @AfterTest
    public void logout() {
        //driver.quit();
    }
    
}
*/