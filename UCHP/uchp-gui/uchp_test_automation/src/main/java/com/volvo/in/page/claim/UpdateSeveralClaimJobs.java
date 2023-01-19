package com.volvo.in.page.claim;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

public class UpdateSeveralClaimJobs {

    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();

    SoftAssert softAssert;
    String fromDate;
    String toDate;
    String fromTextValue;
    String toTextValue;
    public Logger APP_LOGS = Logger.getLogger(UpdateSeveralClaimJobs.class);

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claim;

    @FindBy(how = How.XPATH, using = "//a[text()='Update several claim jobs ']")
    WebElement updateSeveralClaimJobsMenu;

    @FindBy(how = How.XPATH, using = "//a[text()='Search claim jobs ']")
    WebElement searchClaimJobsMenu;

    @FindBy(how = How.XPATH, using = "//multi-update-claim-jobs-search/descendant::h4[text()='Search claim jobs']")
    WebElement pageTitle;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingImporter']")
    WebElement importerTextBoxEelement;

    @FindBy(how = How.ID, using = "repairing_Importer")
    WebElement importerDropDownEelement;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../span")
    WebElement typeOfConcernLabelValue;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement TypeofconcernB;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingDealer']")
    WebElement dealerTextBoxElements;

    @FindBy(how = How.ID, using = "repairing_Dealer")
    WebElement dealerDropDownElements;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement importerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Dealer')]/../../descendant::span[contains(@class,'ng-binding ng-scope')]")
    WebElement dealerGreyedOutValue;

    @FindBy(how = How.XPATH, using = "(//h5)[2]")
    WebElement expandVehicleInforamtion;

    @FindBy(how = How.ID, using = "chassisSeries")
    WebElement chassisSeries;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.multiUpdateClaimJobsSearchForm.chassisSeriesInc']")
    WebElement includeChassisSeries;

    @FindBy(how = How.XPATH, using = "//input[@id='chassisNoFrom']")
    WebElement chassisNoFrom;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.multiUpdateClaimJobsSearchForm.chassisNoInc']")
    WebElement includeChassisNo;

    @FindBy(how = How.ID, using = "chassisNoTo")
    WebElement chassisNoTo;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input")
    WebElement repairDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement repairDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input")
    WebElement registrationDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input")
    WebElement registrationDateTo;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement registrationDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement registrationDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//select[@id='claimJobStatus']")
    WebElement dropdownStatus;

    @FindBy(how = How.XPATH, using = "//h5[text()='Material information']")
    WebElement expandMaterialInformation;

    @FindBy(how = How.ID, using = "causalPartPrefix")
    WebElement causalPartPrefix;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.multiUpdateClaimJobsSearchForm.causalPartPrefixInc']")
    WebElement includeCausalPartPrefix;

    @FindBy(how = How.ID, using = "causalPartNo")
    WebElement causalPartNo;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.multiUpdateClaimJobsSearchForm.causalPartNoInc']")
    WebElement includeCausalPartNo;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.multiUpdateClaimJobsSearchForm.registrationDateInc']")
    WebElement includeRegistrationDate;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement search;

    @FindBy(how = How.NAME, using = "debitCodeFrom")
    WebElement debitCodeExisting;

    @FindBy(how = How.NAME, using = "debitCodeTo")
    WebElement debitCodeNew;

    @FindBy(how = How.NAME, using = "followupCodeTo")
    WebElement followupCode;

    @FindBy(how = How.XPATH, using = "//textarea[@name='description']")
    WebElement description;

    @FindBy(how = How.ID, using = "updateSeveralClaimJobs")
    WebElement updateSeveralClaimJobsElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement successMsgElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//a[text()='List order ']")
    WebElement listOrderMenu;

    @FindBy(how = How.XPATH, using = "//h4[text()='List order']")
    WebElement listOrderPageTitle;

    @FindAll({ @FindBy(xpath = "//tr[1]/td") })
    List<WebElement> listOrderFirstRow;

    @FindBy(how = How.XPATH, using = "(//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[4])[1]")
    WebElement referenceNumberInSearchResult;


    @FindBy(how = How.XPATH, using = "//tr[1]/td[4]/i")
    WebElement fileDownLoading;

    @FindAll({ @FindBy(xpath = "//*[@class='ng-isolate-scope']/div[8]") })
    List<WebElement> debitCodeInGrid;

    @FindBy(how = How.XPATH, using = "//h5[text()='Cost information']")
    WebElement costInfromation;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.multiUpdateClaimJobsSearchForm.debitCodeActiveInc']")
    WebElement selectDebitCodeCheckBox;

    @FindBy(how = How.XPATH, using = "(//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope'])[2]")
    WebElement checkTheIsRowSelectedCheckBox;


    String debitCodeFromGrid;

    int numberOfPreviousDays = 185;

    @FindBy(how = How.NAME, using = "followUpCode")
    WebElement followUpCodeInClaimJObInfo;

    public static String downloadLocationFileForUpdateSeveralClaim =null;

    public File updateServealClaimfile = null;

    private static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[";

    public UpdateSeveralClaimJobs(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /****************************************
     * #Description : This is a common function to navigate from "Claim" main menu to "update claim  jobs, search claim jobs" sub menu #Input Parameters:
     ******************************************/
    public void navigateToSearchClaimJobsPage() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 20, claim);
        keywords.performSecondLevelMenuAction(driver, claim, updateSeveralClaimJobsMenu,searchClaimJobsMenu, pageTitle);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
    }

    public void navigateToListOrder(){
        keywords.clickOnLink(homePageLink, driver);
        keywords.performSecondLevelMenuAction(driver, claim, updateSeveralClaimJobsMenu,listOrderMenu, listOrderPageTitle);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 4, loadingIcon);
    }
    /******************************************
     * #Description : This is a common function to update search with ImporterDealerChassisRegNoAndStatus and update
     *******************************************/
    public void searchWithImporterDealerChassisRegNoAndStatus(HashMap<String, Object> data) {
        String column[] = {"Importer", "Dealer","Registration date", "Status", "ChassisSeries", "ChassisNo", "CausalPartPrefix", "CausalPartNo"};
        enterImporterandDealer(data);
        enterRegistrationDate(data);
        selectTypeOFConcern(data);
        enterStatus(data);
        enterChassisSeriesAndNo(data);
        enterCausalPartPrefix(data);
        selectDebitCodeFromCheckBox();
        clickOnSearch();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 4, loadingIcon);
        debitCodeFromGrid = debitCodeOfFirstRowInGrid();
        if (debitCodeFromGrid.contains(",")){
            String debitCode[] = debitCodeFromGrid.split(",");
            debitCodeFromGrid = debitCode[0];
        }
        validateTheSearchResult(data, column, "Importer_2 Dealer_3 RegistrationDate_5 Status_1 ChassisSeries_6 ChassisNo_7 CausalPartPrefix_9");
        enterDebitCode(data, debitCodeFromGrid);
        enterFollowUpCode(data);
        enterDescription(data);
        selectRowInGrid();
        String date = updateTheClaimJobs();
        keywords.waitTime(12);
        navigateToListOrder();
        readTheFirstRowRecordValue(data, date);
        excelRead(data);
    }

    /********************************
     * #Description : Method to Verify Type of concern and ContractNo and Type of claim #Date of creation : 06-10-2016 #Input Parameters: noOfColumnsToValidate:
     * Hexadecimal character used as index in the Xpath dropdown: dropdown webelement expectedDropDownValues: Expected test data
     */
    private void enterImporterandDealer(HashMap<String, Object> data) {
        APP_LOGS.info("Verifing the values in the drop down field");
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(BaseClass.userContextForImporter, importerTextBoxEelement, importerDropDownEelement,
                                                                        importerGreyedOutValue, appBasedUtils.getStringData(data, "Importer"), driver);
        appBasedUtils.enterValueToTextFieldOrDropdownBasedOnUserContext(BaseClass.userContextForDealer, dealerTextBoxElements, dealerDropDownElements,
                                                                        dealerGreyedOutValue, appBasedUtils.getStringData(data, "Dealer"), driver);
    }

    private void enterRegistrationDate(HashMap<String, Object> data){
        fromDate = DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT);
        toDate = DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        keywords.scrollPage(driver, 50);
        keywords.waitForVisibilityOfElement(driver, 10, registrationDateFrom);
        keywords.input(registrationDateFrom, fromDate, "RegistrationDateFrom");
        keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
        keywords.setCheckboxIfNotSelected(includeRegistrationDate);
        keywords.input(registrationDateTo, toDate, "RegistrationDateTo");
        keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
        keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
        keywords.setCheckboxIfNotSelected(includeRegistrationDate);
    }

    private void enterStatus(HashMap<String, Object> data){

        keywords.selectVisibleText(dropdownStatus, appBasedUtils.getStringData(data, "Status"));
    }

    private void enterChassisSeriesAndNo(HashMap<String, Object> data){
        keywords.scrollPage(driver, 250);
        keywords.clickOnButton(driver, expandVehicleInforamtion);
        keywords.waitForVisibilityOfElement(driver, 10,includeChassisSeries);
        keywords.setCheckboxIfNotSelected(includeChassisSeries);
        keywords.input(chassisSeries, appBasedUtils.getStringData(data, "ChassisSeries"), "chassisSeries");
        keywords.setCheckboxIfNotSelected(includeChassisNo);
        fromTextValue =  appBasedUtils.getStringData(data, "ChassissNoFrom");
        toTextValue = appBasedUtils.getStringData(data, "ChassissNoTo");
        keywords.input(chassisNoFrom, fromTextValue, "chassisNoFrom");
        keywords.input(chassisNoTo, toTextValue, "chassisNoTo");
        keywords.setCheckboxIfNotSelected(includeChassisNo);
    }
    private void enterCausalPartPrefix(HashMap<String, Object> data){
        keywords.scrollPage(driver, 500);
        keywords.clickOnButton(driver, expandMaterialInformation);
        keywords.input(causalPartPrefix, appBasedUtils.getStringData(data, "CausalPartPrefix"), "causalPartPrefix");
        keywords.setCheckboxIfNotSelected(includeCausalPartPrefix);
    }

    private void clickOnSearch(){
        keywords.clickOnButton(driver, search);
    }

    private void enterDebitCode(HashMap<String, Object> data, String debitCode){
        keywords.input(debitCodeExisting, debitCode, "DebitCodeExisting");
        keywords.input(debitCodeNew, appBasedUtils.getStringData(data, "DebitCodeNew"), "DebitCodeNew");
        if(debitCode.equals(appBasedUtils.getStringData(data, "DebitCodeNew"))) {
        APP_LOGS.info("Debit code in grid is "+ debitCode + "debit code from list and debit code from excel sheet are same");
        keywords.input(debitCodeExisting, debitCode+1, "DebitCodeExisting");
            }
        }

    private void enterFollowUpCode(HashMap<String, Object> data){
        keywords.input(followupCode, appBasedUtils.getStringData(data, "FollowUpCode"), "FollowUpCode");
    }

    private void enterDescription(HashMap<String, Object> data){
        keywords.input(description, appBasedUtils.getStringData(data, "Description"), "Description");
        keywords.keyTab(description);
        keywords.clickOnButton(driver, pageTitle);
    }

    private void selectRowInGrid(){
        keywords.clickOnButton(driver, checkTheIsRowSelectedCheckBox);
    }

    private void validateTheSearchResult(HashMap<String, Object> data, String columnheader[], String noOfColumnsToValidate){
        //to be addedd
            softAssert = new SoftAssert();
            // first row column count start with Zero and iterate the columnIndexInSearchGrid by one till the lst row
            int columnIndexInSearchGrid = 0;
            //wait until search result is loaded for 2 second
            try {
                if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                    keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 6, gridColumnName, errorMsg, validationMessageClose);
                    for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {
                        if ("Importer_2 Dealer_3 SearchType_5 Status_6 TMAStatus_5 TMAStatus_7 ChassisSeries_6 CausalPartPrefix_9 CausalPartNo_10".contains(hexaStrToXpath)) {
                            APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                            appBasedUtils.textValidationGrid(driver, (appBasedUtils.getStringData(data, columnheader[columnIndexInSearchGrid])), XpathFirstPart
                                            + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert);
                            columnIndexInSearchGrid++;
                        }
                        // Reference number and Interger comparision
                        else if ("ChassisNo_7".contains(hexaStrToXpath)) {
                            APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                            appBasedUtils.verifySearchResultInGridBtwRangeForIntergerValue(driver, fromTextValue, toTextValue, XpathFirstPart
                                    + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]", softAssert);
                            columnIndexInSearchGrid++;
                        } else if ("RepairDate_5 CreditedDate_5 RegistrationDate_5".contains(hexaStrToXpath)) {
                            APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                            appBasedUtils.validateDate(driver,
                                                       XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1, hexaStrToXpath.length()) + "]",
                                                       DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT),
                                                       DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), softAssert);
                            columnIndexInSearchGrid++;
                        }
                        else if("Status_1".contains(hexaStrToXpath)){
                            keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                            appBasedUtils.claimStatusIconValidationInGrid(appBasedUtils.getStringData(data, "Status"),
                                                                          XpathFirstPart + hexaStrToXpath.substring(hexaStrToXpath.indexOf('_') + 1,
                                                                                                                    hexaStrToXpath.length())
                                                                                  + "]/descendant::span",
                                                                                  softAssert, driver);
                            columnIndexInSearchGrid++;
                        }
                        else {
                            APP_LOGS.info("Value of char didnot match " + hexaStrToXpath);
                            Assert.fail();
                        }
                    }
                    softAssert.assertAll();
                    ExtentTestManager.getTest().log(Status.INFO, "Verified the search result");
                }
            } catch (Exception e) {
                APP_LOGS.error(e.getMessage());
                Assert.fail("Fail!!! Search result not found '");
            }

        }

    private String updateTheClaimJobs(){
        keywords.clickOnButton(driver, updateSeveralClaimJobsElement);
        // write condition
        keywords.isSuccessMessageVisible(driver,3,successMsgElement, validationMessageClose);
        return DateUtility.getCurrentDateAndTime();
    }
   public void readTheFirstRowRecordValue(HashMap<String, Object> data, String dateIST){
      try{
        List<WebElement>  rowValues = listOrderFirstRow;
        keywords.assertContains((DateUtility.getCETTimeFromCurrentIST(dateIST)).toString(), rowValues.get(0).getText());
        if(rowValues.get(1).getText().equalsIgnoreCase("PROCESS_ONGOING")|| rowValues.get(1).getText().equalsIgnoreCase("ORDERED")){
            APP_LOGS.info(rowValues.get(1).getText());
            keywords.waitTime(20);
            navigateToListOrder();
        }
        assertTrue(keywords.assertContains("FINISHED",rowValues.get(1).getText()));
        assertTrue(keywords.assertContains(appBasedUtils.getStringData(data, "Description"), rowValues.get(4).getText()));
        APP_LOGS.info("Updated claim job is listed in List order grid");
      }
      catch(Exception e) {
          APP_LOGS.error(e.getMessage());
          Assert.fail("List order is not displayed ");
      }
   }
   public String getRefNo(){
       return keywords.getTextValue(referenceNumberInSearchResult);
   }

   private String debitCodeOfFirstRowInGrid(){
       return debitCodeInGrid.get(0).getText();
   }

   private void selectDebitCodeFromCheckBox(){

       keywords.clickOnButton(driver, costInfromation);
       keywords.clickOnButton(driver, selectDebitCodeCheckBox);
   }

   private void excelRead(HashMap<String, Object> data){
       try{
           String sPathSep = System.getProperty("file.separator");
           downloadLocationFileForUpdateSeveralClaim = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep + "multiUpdateClaimJobsListOrder.xls";
           PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
           updateServealClaimfile = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep
                                                  + "multiUpdateClaimJobsListOrder.xls");

           File fileDownloadLocaiton = new File(downloadLocationFileForUpdateSeveralClaim);
           pdfReadAndValidate.deleteFileFromFolder(fileDownloadLocaiton);
           keywords.waitTime1(6);
           pdfReadAndValidate.downloadDocuments(BaseClass.browserName, fileDownLoading, driver, new File(downloadLocationFileForUpdateSeveralClaim));
           keywords.waitTime1(2);
           pdfReadAndValidate.listFolderFiles(fileDownloadLocaiton);
           keywords.waitTime1(4);
           ExcelReadAndWrite excel = new ExcelReadAndWrite(downloadLocationFileForUpdateSeveralClaim, "Sheet0");
           SoftAssert dataValidation = new SoftAssert();
           keywords.assertEqual( debitCodeFromGrid, excel.readExcelDataOnSpecifiedColumn(downloadLocationFileForUpdateSeveralClaim, "Sheet0", 5, 7),dataValidation);
           keywords.assertEqual( appBasedUtils.getStringData(data, "DebitCodeNew"), excel.readExcelDataOnSpecifiedColumn(downloadLocationFileForUpdateSeveralClaim, "Sheet0", 5, 8), dataValidation);
           keywords.assertEqual( appBasedUtils.getStringData(data, "FollowUpCode"), excel.readExcelDataOnSpecifiedColumn(downloadLocationFileForUpdateSeveralClaim, "Sheet0", 5, 10), dataValidation);
           keywords.assertEqual( "Updated",excel.readExcelDataOnSpecifiedColumn(downloadLocationFileForUpdateSeveralClaim, "Sheet0", 5, 11), dataValidation);
           if(excel.readExcelDataOnSpecifiedColumn(downloadLocationFileForUpdateSeveralClaim, "Sheet0", 5, 11).equalsIgnoreCase("Updated"))
               APP_LOGS.info("Either of debit code or the follow up code is updated successfully");
           dataValidation.assertAll();
        }
       catch(Exception e) {
           APP_LOGS.error(e.getMessage());
           Assert.fail();
       }

   }
   public void selectTypeOFConcern(HashMap<String, Object> data){
       keywords.scrollPage(driver, 250);
       if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(data, "TypeOfConcern"))) {
          if ((appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                  || (appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
              keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
          else if ((appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("Both"))
              keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(data, "SelectTypeOfConcern"));
       }
    }

}
