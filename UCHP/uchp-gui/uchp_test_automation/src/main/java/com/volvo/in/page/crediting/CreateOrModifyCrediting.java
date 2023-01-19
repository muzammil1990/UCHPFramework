package com.volvo.in.page.crediting;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.AdvanceSearch;
import com.volvo.in.page.claim.ManageClaimJobs;

public class CreateOrModifyCrediting {
    WebDriver driver;
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();
    BaseClass baseclass = new BaseClass();
    ManageClaimJobs manageClaimjobs;
    

    public Logger APP_LOGS = Logger.getLogger(AdvanceSearch.class);
    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claim;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Crediting')]")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(text(), 'Create/modify scheduling')])[1]")
    WebElement creditingCreateModifySchedulingSubMenu;

    @FindBy(how = How.XPATH, using = "//create-modify-schedule/descendant::h4[contains(., 'Create/modify scheduling')]")
    WebElement creditingCreateModifySchedulingTitle;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../../div/span")
    WebElement typeOfConcernValue;

    @FindBy(how = How.ID, using = "typeOfConcern")
    WebElement typeOfConcernElement;

    @FindBy(how = How.XPATH, using = "//button[contains(@ng-click,'ctrl.openImporter')]")
    WebElement selectImporter;

    @FindBy(how = How.XPATH, using = "//button[contains(@ng-click,'ctrl.openDealer')]")
    WebElement selectDealer;

    @FindAll({ @FindBy(xpath = "//label[@class='checkbox-inline ng-binding']") })
    List<WebElement> allImportersInList;

    @FindBy(how = How.XPATH, using = "//input[@name='interval']")
    WebElement importerSearch;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Ok')]")
    WebElement okButton;

    @FindBy(how = How.XPATH, using = "//select[@ng-model='ctrl.creditSchedule.make']")
    WebElement selectBrand;

    @FindBy(how = How.ID, using = "creditSearchModify")
    WebElement creditSearchModify;

    @FindBy(how = How.ID, using = "timeInterval")
    WebElement timeInterval;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Schedule')]")
    WebElement schedule;

    @FindBy(how = How.XPATH, using = "//div[@id='displayPrepStartTime']/input")
    WebElement displayPrepStartTime;

    @FindBy(how = How.XPATH, using = "//div[@id='toCreditingTime']/input")
    WebElement displayPrepEndTime;

    @FindBy(how = How.XPATH, using = "//div[@id='endApprWinTime']/input")
    WebElement displayAppWinEndTime;

    @FindBy(how = How.XPATH, using = "//div[@id='creationStartTime']/input")
    WebElement displayCreaStartTime;

    @FindBy(how = How.XPATH, using = "//h4[text()='Preview scheduling']")
    WebElement previewScheduling;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.ID, using = "confirmSchedule")
    WebElement confirmSchedule;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'System admin')])[1]")
    WebElement systemAdminMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[text()='Batch scheduler '])[1]")
    WebElement batchSchedulerSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[text()='List batch jobs']")
    WebElement listBatchJobPage;

    @FindBy(how = How.XPATH, using = "//td[contains(text(),'CreateCreditOccasionsBatch')]")
    WebElement createCreditOccasionsBatch;

    @FindBy(how = How.XPATH, using = "//button[@id='tiggerBatchJob']")
    WebElement tiggerBatchJob;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Batch job CreateCreditOccasionsBatch has been triggered for company')]")
    WebElement batchConfirmationMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement closeButton;

    @FindBy(how = How.XPATH, using = "(//div[@class='text-left ui-grid-cell-contents ng-binding ng-scope'])[1]")
    WebElement scheduleIdWebElement;

    @FindBy(how = How.XPATH, using = "(//div[@class='text-left ui-grid-cell-contents ng-binding ng-scope'])[2]")
    WebElement occasionIdWebelement;

    @FindBy(how = How.XPATH, using = "(//a[contains(@ng-click,'grid.appScope.ctrl.openOccasionState')])[1]")
    WebElement occasionStatus;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridSearchResult;

    @FindBy(how = How.XPATH, using = "//td[contains(text(),'CreditingStartUpBatch')]")
    WebElement creditingStartUpBatch;

    @FindBy(how = How.ID, using = "active")
    WebElement activeCheckBox;

    @FindBy(how = How.XPATH, using = "//button[@id='saveBatchScheduler']")
    WebElement saveBatchScheduler;

    @FindBy(how = How.XPATH, using = "//button[text()='Yes']")
    WebElement confirmYes;

    @FindBy(how = How.NAME, using = "creditOccId")
    WebElement creditOccId;

    @FindBy(how = How.XPATH, using = "//div[div[div[text()='6625528']]]/div[7]")
    WebElement documents;

    @FindBy(how = How.XPATH, using = "(//div[@class='text-left ui-grid-cell-contents ng-binding ng-scope'])[5]")
    WebElement documentNameFirstRecord;

    @FindBy(how = How.XPATH, using = "//div[div[div[i[@class='print-download-file fa fa-file-pdf-o grid-icon']]]]/div[7]")
    WebElement documentNameColumn;

    @FindBy(how = How.XPATH, using = "(//span[@ng-if='row.entity.creditTypeId'])[1]")
    WebElement creditingTypeInCreateOrModifySchedulingPage;

    @FindBy(how = How.XPATH, using = "(//i[@class='print-download-file fa fa-file-pdf-o grid-icon'])[1]")
    WebElement downloadDocument;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Prepared')]")
    WebElement preparedStatus;

    @FindBy(how = How.XPATH, using = "//input[@name='removeIsLockedForCreditingCreation']")
    WebElement removeIsLockedForCreditingCreation;

    @FindBy(how = How.XPATH, using = "//button[@id='saveRestartCredit']")
    WebElement saveRestartCredit;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Yes')]")
    WebElement yes;

    @FindBy(how = How.XPATH, using = "//strong[contains(text(),'Batch job CreditingStartUpBatch has been triggered for company')]")
    WebElement batchJobTriggeredConfirmationMsg;

    public static String occasionId;
    String scheduleId;
    String creditingType;
    public static String creditDocNoFromGrid;

    public CreateOrModifyCrediting(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*****************************************************
     * #Description : This is a common function to navigate from "createOrModifyCrediting" page
     ***************************************************/
    public void navigateToCreateOrModifyCrediting() {
        keywords.waitTime1(2);
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        keywords.waitTime1(2);
        keywords.performMenuAction(driver, creditingMainMenuElement, creditingCreateModifySchedulingSubMenu, creditingCreateModifySchedulingTitle);
        keywords.waitTime1(2);
    }

    /*****************************************************
     * #Description : This is a common function to navigate from "batchSchedulerMenu" page
     ***************************************************/
    public void navigateToBatchSchedulerSubMenu() {
        keywords.waitTime1(2);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.performMenuAction(driver, systemAdminMainMenuElement, batchSchedulerSubMenu, listBatchJobPage);
        keywords.waitTime1(2);
    }


    /****************************************
     * #Description : This method is to scheduleImporter level crediting
     *
     * @throws ParseException
     ******************************************/
    public void scheduleImporterLevelCrediting(HashMap<String, Object> creditingData) throws ParseException {
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        selectCreditSearchModify(creditingData);
        setTimeInterval(creditingData);
        setPreparationTime();
        clickOnSchedule();
        clickOnConfrimSchedule();
        navigateToBatchSchedulerSubMenu();
        triggerCreateCreditOccasionBatch();
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        search();
        getScheduleAndOccationId();
        //status scheduled
        getCreditOccationStatusSchduled(creditingData);
        navigateToBatchSchedulerSubMenu();
        keywords.waitTime1(120);
        triggerCreditingStartUpBatch();
        //Status prepared
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        getCreditOccationStatusPrepared(creditingData);
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        // Status creation ongoing
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        //Status Finalized
        getCreditOccationStatusFinalized(creditingData);

        PrintCreditDocument print = new PrintCreditDocument(driver);
        print.navigateToPrintCreditDocument();
        keywords.waitTime1(2);
        print.searchWithCreditingDateImporterAndToC(creditingData);
        validateDocumentNameGeneratedForCreditingType(creditingData);

    }

    /****************************************
     * #Description : This method is to schedule for Brand level crediting
     *
     * @throws ParseException
     ******************************************/
    public void scheduleBrandLevelCrediting(HashMap<String, Object> creditingData) throws ParseException {
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        selectCreditSearchModify(creditingData);
        setTimeInterval(creditingData);
        setPreparationTime();
        clickOnSchedule();
        clickOnConfrimSchedule();
        navigateToBatchSchedulerSubMenu();
        triggerCreateCreditOccasionBatch();
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        search();
        getScheduleAndOccationId();
        //status scheduled
        getCreditOccationStatusSchduled(creditingData);
        navigateToBatchSchedulerSubMenu();
        keywords.waitTime1(120);
        triggerCreditingStartUpBatch();
        //Status prepared
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        searchWithOccasionId();
        search();
        getCreditOccationStatusPrepared(creditingData);
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        // Status creation ongoing
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        searchWithOccasionId();
        search();
        //Status Finalized
        getCreditOccationStatusFinalized(creditingData);

        PrintCreditDocument print = new PrintCreditDocument(driver);
        print.navigateToPrintCreditDocument();
        keywords.waitTime1(2);
        print.searchWithCreditingDateImporterAndToC(creditingData);
        validateDocumentNameGeneratedForCreditingType(creditingData);

    }

    /****************************************
     * #Description : This method is to schedule No brand level crediting
     *
     * @throws ParseException
     ******************************************/
    public void scheduleNoBrandLevelCrediting(HashMap<String, Object> creditingData) throws ParseException {
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        selectCreditSearchModify(creditingData);
        setTimeInterval(creditingData);
        setPreparationTime();
        clickOnSchedule();
        clickOnConfrimSchedule();
        navigateToBatchSchedulerSubMenu();
        triggerCreateCreditOccasionBatch();
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        search();
        getScheduleAndOccationId();
        //status scheduled
        getCreditOccationStatusSchduled(creditingData);
        navigateToBatchSchedulerSubMenu();
        keywords.waitTime1(120);
        triggerCreditingStartUpBatch();
        //Status prepared
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        getCreditOccationStatusPrepared(creditingData);
        removeLockForCreation();
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        // Status creation ongoing
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        //Status Finalized
        getCreditOccationStatusFinalized(creditingData);
        PrintCreditDocument print = new PrintCreditDocument(driver);
        print.navigateToPrintCreditDocument();
        keywords.waitTime1(2);
        print.searchWithCreditingDateImporterAndToC(creditingData);
        validateDocumentNameGeneratedForCreditingType(creditingData);

    }

    /****************************************
     * #Description : This method is to select the importer from the list
     ******************************************/
    public void selectImporter(HashMap<String, Object> creditingData) {
        keywords.clickOnButton(driver, selectImporter);
        keywords.waitTime1(2);
        keywords.input(importerSearch, appBasedUtils.getStringData(creditingData, "Importer"), "Importer value");
        for (int i = 0; i < allImportersInList.size(); i++) {
            if (allImportersInList.get(i).getText().equals(appBasedUtils.getStringData(creditingData, "Importer"))) {
                allImportersInList.get(i).click();
                APP_LOGS.info("Selected importer is : " + allImportersInList.get(i).getText());
            }
        }
        keywords.clickOnButton(driver, okButton);
    }
    /****************************************
     * #Description : This method is to select the dealer from the list
     ******************************************/
    public void selectDealer(HashMap<String, Object> creditingData) {
        keywords.clickOnButton(driver, selectDealer);
        keywords.waitTime1(2);
        keywords.input(importerSearch, appBasedUtils.getStringData(creditingData, "Dealer"), "Dealer value");
        for (int i = 0; i < allImportersInList.size(); i++) {
            if (allImportersInList.get(i).getText().equals(appBasedUtils.getStringData(creditingData, "Dealer"))) {
                allImportersInList.get(i).click();
                APP_LOGS.info("Selected Dealer is : " + allImportersInList.get(i).getText());
            }
        }
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, okButton);
    }
    /****************************************
     * #Description : This method is to click on Ok button
     ******************************************/
    public void selectBrand(HashMap<String, Object> creditingData) {
        keywords.selectVisibleText(selectBrand, appBasedUtils.getStringData(creditingData, "Brand"));
        keywords.waitTime1(1);
    }

    /****************************************
     * #Description : This method is to click on Ok button
     ******************************************/
    public void selectCreditSearchModify(HashMap<String, Object> creditingData) {
        keywords.clickOnButton(driver, creditSearchModify);
        keywords.waitTime1(1);
    }

    /****************************************
     * #Description : This method is to select the type of concern from the list
     ******************************************/
    public void selectTypeOfConcern(HashMap<String, Object> creditingData) {
        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(creditingData, "TypeOfConcernPrivilege"))) {
            if ((appBasedUtils.getStringData(creditingData, "TypeOfConcernPrivilege")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(creditingData, "TypeOfConcernPrivilege")).equalsIgnoreCase("ContractOnly")) {
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernValue));
            } else if ((appBasedUtils.getStringData(creditingData, "TypeOfConcernPrivilege")).equalsIgnoreCase("Both")) {
                keywords.selectVisibleText(typeOfConcernElement, appBasedUtils.getStringData(creditingData, "TypeOfConcern"));
            }
        }
    }

    /****************************************
     * #Description : This method is to set the preparation start time and end time
     ******************************************/
    public void setPreparationTime() throws ParseException {
        int min = Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 2;
        if(min >=60){
            keywords.waitTime1(2);
        }
        String currentCETHourAndMin = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 2);
        keywords.input(displayPrepStartTime, currentCETHourAndMin, "Preparation start time");
        keywords.keyEnter(displayPrepStartTime);
        String futureCETHourAndMinute = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 5);
        String futureCETHourAndMinuteForAppWind = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 6);
        String futureCETHourAndMinuteForCreStrtTime = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 7);
        if ((Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 7)>=59){
            keywords.waitTime1(7);
            currentCETHourAndMin = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 2);
            keywords.input(displayPrepStartTime, currentCETHourAndMin, "Preparation start time");
            keywords.keyEnter(displayPrepStartTime);
            futureCETHourAndMinute = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 5);
            keywords.keyEnter(displayPrepEndTime);
            futureCETHourAndMinuteForAppWind = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 6);
            futureCETHourAndMinuteForCreStrtTime = DateUtility.getCETTimeConversion("HH") + ":" + (Integer.parseInt(DateUtility.getCETTimeConversion("mm")) + 7);
        }
        keywords.input(displayPrepEndTime, futureCETHourAndMinute, "Preparation end time");
        keywords.keyEnter(displayPrepEndTime);
        keywords.input(displayAppWinEndTime, futureCETHourAndMinuteForAppWind, "Approval end time");
        keywords.keyEnter(displayAppWinEndTime);
        keywords.input(displayCreaStartTime, futureCETHourAndMinuteForCreStrtTime, "Creation start time");
        keywords.keyEnter(displayCreaStartTime);
    }

    /****************************************
     * #Description : This method is to set time interval
     ******************************************/
    public void setTimeInterval(HashMap<String, Object> creditingData) {
        keywords.waitTime1(1);
        keywords.input(timeInterval, appBasedUtils.getStringData(creditingData, "TimeInterval"), " time interval ");
    }
    /****************************************
     * #Description : This method is to click on Schedule button
     ******************************************/
    public void clickOnSchedule() {
        keywords.clickOnButton(driver, schedule);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 2, previewScheduling, errorMsgElement, validationMessageClose);
    }

    /****************************************
     * #Description : This method is to click on confirm schedule
     ******************************************/
    public void clickOnConfrimSchedule() {
        keywords.clickOnButton(driver, confirmSchedule);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 2, creditingCreateModifySchedulingTitle, errorMsgElement, validationMessageClose);
    }
    /****************************************
     * #Description : This method is to create occasion batch
     ******************************************/
    public void triggerCreateCreditOccasionBatch(){
        keywords.clickOnButton(driver, createCreditOccasionsBatch);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitTime1(1);
        if(!(keywords.isCheckboxElementSelected(activeCheckBox))){
            keywords.setCheckboxIfNotSelected(activeCheckBox);
            keywords.clickOnButton(driver, saveBatchScheduler);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        }
        keywords.clickOnButton(driver, tiggerBatchJob);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 2, batchConfirmationMsg, errorMsgElement, validationMessageClose);

    }
    /****************************************
     * #Description : This method is to click on search button
     ******************************************/
    public void search(){
        keywords.waitTime1(4);
        keywords.clickOnButton(driver, searchButton);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 8, gridSearchResult, errorMsgElement, validationMessageClose);
    }
    /****************************************
     * #Description : This method is to get schedule and occasion id
     ******************************************/
    public void getScheduleAndOccationId(){
        keywords.APP_LOGS.info("Schdule id is: " + keywords.getTextValue(scheduleIdWebElement));
        keywords.APP_LOGS.info("Occasion id is: " + keywords.getTextValue(occasionIdWebelement));
        scheduleId = keywords.getTextValue(scheduleIdWebElement);
        occasionId = keywords.getTextValue(occasionIdWebelement);
        if(occasionId.equals("")){
            search();
            occasionId = keywords.getTextValue(occasionIdWebelement);
        }
        creditingType = keywords.getTextValue(creditingTypeInCreateOrModifySchedulingPage);
        keywords.waitTime1(2);
    }
    /****************************************
     * #Description : This method is to verify occasion id
     ******************************************/
    public void getCreditOccationStatusSchduled(HashMap<String, Object> creditingData){
        keywords.APP_LOGS.info("Occasion status is: " + keywords.getTextValue(occasionStatus));
        if(appBasedUtils.getStringData(creditingData, "OccasionStatus_1")!=(keywords.getTextValue(occasionStatus))){
            keywords.waitTime1(50);
            search();
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(creditingData, "OccasionStatus_1"), keywords.getTextValue(occasionStatus), softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : This method is to verify credit status as creation ongoing
     ******************************************/
    public void getCreditOccationStatusCreationOngoing(HashMap<String, Object> creditingData){
        keywords.APP_LOGS.info("Occasion status is: " + keywords.getTextValue(occasionStatus));
        if(appBasedUtils.getStringData(creditingData, "OccasionStatus_3")!=(keywords.getTextValue(occasionStatus))){
            keywords.waitTime1(50);
            search();
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(creditingData, "OccasionStatus_3"), keywords.getTextValue(occasionStatus), softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : This method is to verify credit status as prepared
     ******************************************/
    public void getCreditOccationStatusPrepared(HashMap<String, Object> creditingData){
        keywords.APP_LOGS.info("Occasion status is: " + keywords.getTextValue(occasionStatus));
        if(appBasedUtils.getStringData(creditingData, "OccasionStatus_5").equalsIgnoreCase(keywords.getTextValue(occasionStatus))){
            keywords.waitTime1(50);
            search();
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(creditingData, "OccasionStatus_2"), keywords.getTextValue(occasionStatus), softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : This method is to verify credit status as finalized
     ******************************************/
    public void getCreditOccationStatusFinalized(HashMap<String, Object> creditingData){
        keywords.APP_LOGS.info("Occasion status is: " + keywords.getTextValue(occasionStatus));
        if(keywords.getTextValue(occasionStatus)=="Creation ongoing"){
            keywords.waitTime1(6);
            keywords.clickOnButton(driver, searchButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        }
        if(appBasedUtils.getStringData(creditingData, "OccasionStatus_3").equalsIgnoreCase(keywords.getTextValue(occasionStatus))){
          keywords.waitTime1(30);
          search();
        }
        appBasedUtils.asserVerification(appBasedUtils.getStringData(creditingData, "OccasionStatus_4"), keywords.getTextValue(occasionStatus), softAssert);
        softAssert.assertAll();
    }

    /****************************************
     * #Description : This method is to trigger credit occasion batch
     ******************************************/
    public void triggerCreditingStartUpBatch(){
        keywords.clickOnButton(driver, creditingStartUpBatch);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.setCheckboxIfNotSelected(activeCheckBox);
        keywords.clickOnButton(driver, tiggerBatchJob);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 2, batchJobTriggeredConfirmationMsg, errorMsgElement, validationMessageClose);
    }

    /****************************************
     * #Description : This method is to searcg with occasion id
     ******************************************/
    public void searchWithOccasionId(){
        keywords.input(creditOccId, occasionId, "occasionId value");
    }

    /****************************************
     * #Description : This method is to verify document genearted
     ******************************************/
    public void validateDocumentNameGeneratedForCreditingType(HashMap<String, Object> creditingData) {
        keywords.APP_LOGS.info("Document generated for crediting type : "+creditingType + keywords.getTextValue(documentNameFirstRecord));
        appBasedUtils.asserVerification(keywords.getTextValue(documentNameFirstRecord).trim(), appBasedUtils.getStringData(creditingData, "CreditingTypeDocument").trim(), softAssert);
        softAssert.assertAll();

    }
    /****************************************
     * #Description : This method is to scheduleImporter level crediting
     *
     * @throws ParseException
     ******************************************/
    public void scheduleDealerLevelCrediting(HashMap<String, Object> creditingData) throws ParseException {
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        selectCreditSearchModify(creditingData);
        setTimeInterval(creditingData);
        setPreparationTime();
        clickOnSchedule();
        clickOnConfrimSchedule();
        navigateToBatchSchedulerSubMenu();
        triggerCreateCreditOccasionBatch();
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        search();
        getScheduleAndOccationId();
        //status scheduled
        getCreditOccationStatusSchduled(creditingData);
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        //Status prepared
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        getCreditOccationStatusPrepared(creditingData);
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        // Status creation ongoing
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        //Status Finalized
        getCreditOccationStatusFinalized(creditingData);

        PrintCreditDocument print = new PrintCreditDocument(driver);
        print.navigateToPrintCreditDocument();
        keywords.waitTime1(2);
        print.searchWithCreditingDateImporterAndToC(creditingData);
        validateDocumentNameGeneratedForCreditingType(creditingData);

    }

    /****************************************
     * #Description : This method is to remove lock for creation
     ******************************************/
    public void removeLockForCreation(){
        keywords.waitTime1(1);
        String company = keywords.getTextValue(companyNameElement);
        if (company.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)){
        keywords.clickOnButton(driver, preparedStatus);
        keywords.waitTime1(1);
        keywords.setCheckboxIfNotSelected(removeIsLockedForCreditingCreation);
        keywords.clickOnButton(driver, saveRestartCredit);
        keywords.clickOnButton(driver, yes);
        }
    }
    
    public void scheduleDealerLevelCreditingBeforeApprove(HashMap<String, Object> creditingData) throws ParseException {
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        selectCreditSearchModify(creditingData);
        setTimeInterval(creditingData);
        setPreparationTime();
        clickOnSchedule();
        clickOnConfrimSchedule();
        navigateToBatchSchedulerSubMenu();
        triggerCreateCreditOccasionBatch();
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        search();
        getScheduleAndOccationId();
        //status scheduled
        getCreditOccationStatusSchduled(creditingData);
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        //Status prepared
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        getCreditOccationStatusPrepared(creditingData);
    }
    
    public void runDealerLevelCreditingAfterApprove(HashMap<String, Object> creditingData) throws ParseException {
        // Status creation ongoing
        keywords.waitTime1(2);
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        getCreditOccationStatusPrepared(creditingData);
        removeLockForCreation();
        navigateToBatchSchedulerSubMenu();
        triggerCreditingStartUpBatch();
        navigateToCreateOrModifyCrediting();
        selectTypeOfConcern(creditingData);
        selectImporter(creditingData);
        selectDealer(creditingData);
        selectBrand(creditingData);
        searchWithOccasionId();
        search();
        //Status Finalized
        getCreditOccationStatusFinalized(creditingData);
        PrintCreditDocument print = new PrintCreditDocument(driver);
        print.navigateToPrintCreditDocument();
        keywords.waitTime1(2);
        print.searchWithCreditingDateImporterAndToC(creditingData);
        validateDocumentNameGeneratedForCreditingType(creditingData);
    }
    
}