package com.volvo.in.page.crediting;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

public class ValidatePDFData {
    PDFReadAndValidate pdfReadAndValidate;
    WebDriver driver;
    String newDownloadLocation;
    UIUtils keywords = new UIUtils(driver);
    public Logger APP_LOGS = Logger.getLogger(ValidatePDFData.class);
    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Crediting ']")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "//li/*[@ui-sref='app.print-credit-document' and contains(., 'Print credit document ')]")
    WebElement printCreditdocSubMenuElement;

    @FindBy(how = How.XPATH, using = "//print-credit-document/*[contains(., 'print credit document')]")
    WebElement printCreditTitle;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-sm' and contains(., 'Filter')]")
    WebElement FilterButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,'-0-uiGrid-000D')]/*[contains(@class, 'text-center ng-scope')]/*[contains(@class, 'print-download-file fa fa-file-pdf-o grid-icon')]")
    WebElement Filedownloading;

    @FindBy(how = How.ID, using = "droplistMenuMime")
    WebElement documentTypeElement;

    String browserType;

    public ValidatePDFData(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void navigateToPrintCreditDocument() {
        keywords.performMenuAction(driver, creditingMainMenuElement, printCreditdocSubMenuElement, printCreditTitle);
        // validate whether page navigation is successfull
    }

    public String getBrowserType() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        return cap.getBrowserName();
    }

    public void downlaoadAndVerifydata(HashMap<String, Object> data) throws InterruptedException {
        String sPathSep = System.getProperty("file.separator");
        String downloadLocation = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation";
        keywords.clickOnButton(driver, FilterButton);
        pdfReadAndValidate = new PDFReadAndValidate();
        File fileDownloadLocaiton = new File(downloadLocation);
        pdfReadAndValidate.cleanFolder(fileDownloadLocaiton);
        pdfReadAndValidate.downloadDocuments(getBrowserType(), Filedownloading, driver, new File(downloadLocation + sPathSep + "listCreditingDocuments.pdf"));
        Thread.sleep(2000);
        pdfReadAndValidate.listFolderFiles(fileDownloadLocaiton);
        Thread.sleep(4000);
        try {
            pdfReadAndValidate.readPDFFile(new File(downloadLocation + sPathSep + "listCreditingDocuments.pdf"),data);
        } catch (IOException e) {
            APP_LOGS.error(e.getMessage());
        }
        pdfReadAndValidate.validatePDFdata(data, new File(downloadLocation + sPathSep + "listCreditingDocuments.pdf"), "");
    }

    public void downlaoadAndVerifyExceldata(HashMap<String, Object> data) throws InterruptedException, IOException {
        String sPathSep = System.getProperty("file.separator");
        String downloadLocation = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation";
        keywords.waitTime1(3);
        keywords.selectVisibleText(documentTypeElement, "Excel");
        keywords.clickOnButton(driver, FilterButton);
        pdfReadAndValidate = new PDFReadAndValidate();
        File fileDownloadLocaiton = new File(downloadLocation);
        pdfReadAndValidate.cleanFolder(fileDownloadLocaiton);
        keywords.waitTime1(3);
        pdfReadAndValidate.downloadDocuments("Chrome", Filedownloading, driver, new File(downloadLocation + sPathSep + "listCreditingDocuments.xls"));
        keywords.waitTime1(2);
        pdfReadAndValidate.listFolderFiles(fileDownloadLocaiton);
        keywords.waitTime1(4);
        ExcelReadAndWrite excelReadAndWrite = new ExcelReadAndWrite(downloadLocation + sPathSep + "listCreditingDocuments.xls", "Specification Summary");
        excelReadAndWrite.extractTextFromExcel();
        excelReadAndWrite.validateExceldata(data, new File(downloadLocation + sPathSep + "listCreditingDocuments.pdf"));
    }

}
