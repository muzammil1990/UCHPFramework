package com.volvo.in.page.tma;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class SearchTMAInformation {
    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    UIUtils keywords = new UIUtils(driver);
    public WebDriverWait wait = null;
    SoftAssert softAssert;
    public String newMaterialId;
    public static String testCaseName;
    public Logger APP_LOGS = Logger.getLogger(SearchTMAInformation.class);

    @FindBy(how = How.XPATH, using = "//*[@id='navigation-container']//a[contains(text(),'TMA')]")
    WebElement tmaMainMenu;

    @FindBy(how = How.XPATH, using = "(//a[contains(text(),'Search TMA information')])[1]")
    WebElement searchTMAInformation;

    @FindBy(how = How.XPATH, using = "//search-tma-information/descendant::h4[//a[contains(text(),'Search TMA information')]]")
    WebElement searchTMAInformationTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='importerIdFrom']")
    WebElement importerIdFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='importerIdTo']")
    WebElement importerIdTo;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerIdFrom']")
    WebElement dealerFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerIdTo']")
    WebElement dealerTo;

    @FindBy(how = How.XPATH, using = "//input[@id='referenceNoFrom']")
    WebElement referenceFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='referenceNoTo']")
    WebElement referenceTo;

    @FindBy(how = How.XPATH, using = "//input[@id='jobNo']")
    WebElement jobNo;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']//input")
    WebElement repairDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']//input")
    WebElement repairDateTo;

    @FindBy(how = How.XPATH, using = "//input[@id='materialRequestIdFrom']")
    WebElement materialRequestIdFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='materialRequestIdTo']")
    WebElement materialRequestIdTo;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    WebElement search;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][1]") })
    List<WebElement> searchResult1;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][2]") })
    List<WebElement> searchResult2;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][3]") })
    List<WebElement> searchResult3;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][4]") })
    List<WebElement> searchResult4;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][5]") })
    List<WebElement> searchResult5;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][6]") })
    List<WebElement> searchResult6;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][7]") })
    List<WebElement> searchResult7;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'colContainer')][8]") })
    List<WebElement> searchResult8;

    @FindBy(how = How.XPATH, using = "//div[@class='panel panel-default ng-scope']")
    WebElement rootCauseSummary;

    @FindBy(how = How.XPATH, using = "//button[@id='generate-report-file']")
    WebElement claimJobId;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'materialRequestIdIncluded')]")
    WebElement materialRequestIdCheckBox;

    @FindBy(how = How.XPATH, using = "//input[@id='materialRequestIdFrom']")
    WebElement materialReqIdFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='materialRequestIdTo']")
    WebElement materialReqIdTo;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement company;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[2]/div/div/span[1]")
    WebElement col2;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[3]/div/div/span[1]")
    WebElement col3;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[4]/div/div/span[1]")
    WebElement col4;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[5]/div/div/span[1]")
    WebElement col5;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[6]/div/div/span[1]")
    WebElement col6;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[7]/div/div/span[1]")
    WebElement col7;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Claim job Id')]/../../../../div[8]/div/div/span[1]")
    WebElement col8;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'tmaStatusIncluded')]")
    WebElement tmaStatusCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'tmaStatusDateIncluded')]")
    WebElement materialStatusCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'materialStatusIncluded')]")
    WebElement tmaStatusDateCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'materialStatusDateIncluded')]")
    WebElement materialStatusDateCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'tmaDecisionIncluded')]")
    WebElement tmaDecisionCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'tmaDecisionCodeApproveIncluded')]")
    WebElement tmaApprovalCodeCheckBox;

    @FindBy(how = How.XPATH, using = "//select[@id='tmaStatus']")
    WebElement tmaStatus;

    @FindBy(how = How.XPATH, using = "//div[@id='tmaStatusDateFrom']//input")
    WebElement tmaStatusDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='tmaStatusDateTo']//input")
    WebElement tmaStatusDateTo;

    @FindBy(how = How.XPATH, using = "//select[@id='materialStatus']")
    WebElement materialStatus;

    @FindBy(how = How.XPATH, using = "//div[@id='materialStatusDateFrom']//input")
    WebElement materialStatusDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='materialStatusDateTo']//input")
    WebElement materialStatusDateTo;

    @FindBy(how = How.XPATH, using = "//select[@id='tmaDecision']")
    WebElement tmaDecision;

    @FindBy(how = How.XPATH, using = "//select[@id='tmaDecisionCodeApprove']")
    WebElement tmaDecisionCodeApprove;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']/input/../descendant::span[@class='glyphicon glyphicon-calendar']")
    WebElement repairDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement repairDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='tmaStatusDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement tmaStatusDateFromPickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='tmaStatusDateTo']/input/../descendant::span[@class='glyphicon glyphicon-calendar']")
    WebElement tmaStatusDateToPickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='materialStatusDateTo']/input/../descendant::span[@class='glyphicon glyphicon-calendar']")
    WebElement materialStatusDateToPickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='materialStatusDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement materialStatusDateFromPickerIcon;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'rootCauseProblemIncluded')]")
    WebElement rootCauseProblemCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'partPrefixIncluded')]")
    WebElement partPrefixCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'partNoIncluded')]")
    WebElement partNoCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'partDescriptionIncluded')]")
    WebElement partDescriptionCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'thirdPartyIdIncluded')]")
    WebElement thirdPartyIdCheckBox;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'thirdPartyNameIncluded')]")
    WebElement thirdPartyNameCheckBox;

    @FindBy(how = How.XPATH, using = "//select[@id='rootCauseProblemId']")
    WebElement rootCauseProblem;

    @FindBy(how = How.XPATH, using = "//input[@id='partPrefix']")
    WebElement partPrefix;

    @FindBy(how = How.XPATH, using = "//input[@id='partNoFrom']")
    WebElement partNoFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='partNoTo']")
    WebElement partNoTo;

    @FindBy(how = How.XPATH, using = "//input[@id='thirdPartyIdFrom']")
    WebElement thirdPartyIdFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='thirdPartyIdTo']")
    WebElement thirdPartyIdTo;

    @FindBy(how = How.XPATH, using = "//input[@id='thirdPartyName']")
    WebElement thirdPartyName;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Include root cause summary in search result')]/../span/input")
    WebElement includeRootCauseSummary;

    int numberOfPreviousDays = 600;

    public SearchTMAInformation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToSearchTMAInformation() #Author : Aditi #Description : This is a common function to navigate
     * from "TMA" main menu to "Search TMA Information" sub menu #Date of creation :02-01-19 #Input Parameters: #Name of person modifying: Aditi #Date of
     * modification: 02-01-19
     ******************************************/
    public void navigateToSearchTMAInformation() {
        keywords.waitTime1(8);
        keywords.performMenuAction(driver, tmaMainMenu, searchTMAInformation, searchTMAInformationTitle);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : searchTMAInfoWithImporterDealerRefNoJobNoAndMatReqID() #Author : Aditi #Description : This is a common
     * function to search TMA information with importer, dealer , ref no ,Job no and material request id #Date of creation :04-01-19 #Input Parameters: #Name of
     * person modifying: Aditi #Date of modification:04-01-19
     ******************************************/
    public void searchTMAInfoWithImporterDealerRefNoJobNoAndMatReqID(HashMap<String, Object> data) {
        try {
            softAssert = new SoftAssert();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.input(importerIdFrom, appBasedUtils.getStringData(data, "ImporterFrom"), "Importer from");
            keywords.input(importerIdTo, appBasedUtils.getStringData(data, "ImporterTo"), "Importer to");
            keywords.input(dealerFrom, appBasedUtils.getStringData(data, "DealerFrom"), "Dealer from");
            keywords.input(dealerTo, appBasedUtils.getStringData(data, "DealerTo"), "Dealer to");
            keywords.input(referenceFrom, appBasedUtils.getStringData(data, "RefFrom"), "reference from");
            keywords.input(referenceTo, appBasedUtils.getStringData(data, "RefTo"), "Refrence to");
            keywords.input(jobNo, appBasedUtils.getStringData(data, "JobNo"), "Job no.");
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.input(repairDateFrom, appBasedUtils.getStringData(data, "RepairDateFrom1"), "Repair Date from");
            keywords.clickOnButton(driver, repairDateToDatePickerIcon);
            keywords.input(repairDateTo, appBasedUtils.getStringData(data, "RepairDateTo1"), "Repair Date To");
            keywords.setCheckboxIfNotSelected(materialRequestIdCheckBox);
            keywords.input(materialReqIdFrom, appBasedUtils.getStringData(data, "MaterialReqIdFrom"), "Material request id from");
            keywords.input(materialReqIdTo, appBasedUtils.getStringData(data, "MaterialReqIdTo"), "Material request id to");
            keywords.setCheckboxIfNotSelected(includeRootCauseSummary);
            keywords.clickOnButton(driver, search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            ExtentTestManager.getTest().log(Status.INFO, "Search with required search criteria");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : searchTMAInfoWithDecisionAndStatus() #Author : Aditi #Description : This is a common function to
     * search TMA info with decision and status #Date of creation :08-01-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification: 08-01-19
     ******************************************/
    public void searchTMAInfoWithDecisionAndStatus(HashMap<String, Object> data) {
        try {
            softAssert = new SoftAssert();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.input(repairDateFrom, appBasedUtils.getStringData(data, "RepairDateFrom1"), "Repair Date from");
            keywords.clickOnButton(driver, repairDateToDatePickerIcon);
            keywords.input(repairDateTo, appBasedUtils.getStringData(data, "RepairDateTo1"), "Repair Date To");
            keywords.setCheckboxIfNotSelected(tmaStatusCheckBox);
            keywords.setCheckboxIfNotSelected(materialStatusCheckBox);
            keywords.setCheckboxIfNotSelected(tmaStatusDateCheckBox);
            keywords.setCheckboxIfNotSelected(materialStatusDateCheckBox);
            keywords.setCheckboxIfNotSelected(tmaDecisionCheckBox);
            keywords.setCheckboxIfNotSelected(tmaApprovalCodeCheckBox);
            keywords.selectVisibleText(tmaStatus, appBasedUtils.getStringData(data, "TMA status"));
            keywords.selectVisibleText(materialStatus, appBasedUtils.getStringData(data, "Material status"));
            keywords.input(tmaStatusDateFrom, appBasedUtils.getStringData(data, "TMA status date from"), "ITMA status date from");
            keywords.clickOnButton(driver, tmaStatusDateFromPickerIcon);
            keywords.clickOnButton(driver, tmaStatusDateFromPickerIcon);
            keywords.clickOnButton(driver, tmaStatusDateToPickerIcon);
            keywords.input(tmaStatusDateTo, appBasedUtils.getStringData(data, "TMA status date to"), "TMA status date to");
            keywords.clickOnButton(driver, materialStatusDateFromPickerIcon);
            keywords.input(materialStatusDateFrom, appBasedUtils.getStringData(data, "Material status date from"), "Material status date from");
            keywords.clickOnButton(driver, materialStatusDateToPickerIcon);
            keywords.input(materialStatusDateTo, appBasedUtils.getStringData(data, "Material status date to"), "Material status date to");
            keywords.selectVisibleText(tmaDecision, appBasedUtils.getStringData(data, "TMA decision"));
            keywords.selectVisibleText(tmaDecisionCodeApprove, appBasedUtils.getStringData(data, "TMA approval code"));
            keywords.clickOnButton(driver, search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            ExtentTestManager.getTest().log(Status.INFO, "Search with decision and status");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : searchTMAInfoWithRootCauseIdPartDetailsAndThirdPartyDetails() #Author : Aditi #Description : This is a
     * common function to Search TMA info with root cause id and part details sub menu #Date of creation :08-01-19 #Input Parameters: #Name of person modifying:
     * Aditi #Date of modification: 08-01-19
     ******************************************/
    public void searchTMAInfoWithRootCauseIdPartDetailsAndThirdPartyDetails(HashMap<String, Object> data) {
        try {
            softAssert = new SoftAssert();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.input(repairDateFrom, appBasedUtils.getStringData(data, "RepairDateFrom1"), "Repair Date from");
            keywords.clickOnButton(driver, repairDateToDatePickerIcon);
            keywords.input(repairDateTo, appBasedUtils.getStringData(data, "RepairDateTo1"), "Repair Date To");
            keywords.setCheckboxIfNotSelected(rootCauseProblemCheckBox);
            keywords.setCheckboxIfNotSelected(partPrefixCheckBox);
            keywords.setCheckboxIfNotSelected(partNoCheckBox);
            keywords.setCheckboxIfNotSelected(partDescriptionCheckBox);
            keywords.setCheckboxIfNotSelected(thirdPartyIdCheckBox);
            keywords.setCheckboxIfNotSelected(thirdPartyNameCheckBox);
            keywords.selectVisibleText(rootCauseProblem,
                                       appBasedUtils.getStringData(data, "Root cause id") + " - " + appBasedUtils.getStringData(data, "Description"));
            keywords.input(partPrefix, appBasedUtils.getStringData(data, "Part prefix"), "Part prefix");
            keywords.input(partNoFrom, appBasedUtils.getStringData(data, "Part number from"), "Part no from");
            keywords.input(partNoTo, appBasedUtils.getStringData(data, "Part number to"), "Part no to");
            keywords.input(thirdPartyIdFrom, appBasedUtils.getStringData(data, "Third party Id from"), "Third party Id from");
            keywords.input(thirdPartyIdTo, appBasedUtils.getStringData(data, "Third party Id to"), "Third party Id to");
            keywords.input(thirdPartyName, appBasedUtils.getStringData(data, "Third party name"), "Third party name");
            keywords.clickOnButton(driver, search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            ExtentTestManager.getTest().log(Status.INFO, "Search with third party detail and parts details");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : searchTMAInfoWithRepairDate() #Author : Aditi #Description : This is a common function to search TMA
     * information with repaire dates #Date of creation :08-01-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification:08-01-19
     ******************************************/
    public void searchTMAInfoWithRepairDate(HashMap<String, Object> data) {
        try {
            softAssert = new SoftAssert();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.input(repairDateFrom, appBasedUtils.getStringData(data, "RepairDateFrom2"), "Repair Date from");
            keywords.clickOnButton(driver, repairDateToDatePickerIcon);
            keywords.input(repairDateTo, appBasedUtils.getStringData(data, "RepairDateTo2"), "Repair Date To");
            keywords.setCheckboxIfNotSelected(includeRootCauseSummary);
            keywords.clickOnButton(driver, search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            ExtentTestManager.getTest().log(Status.INFO, "Search with required search criteria");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : validateSearchTMAInfoResult() #Author : Aditi #Description : validating search result #Date of
     * creation :08-01-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification:08-01-19
     ******************************************/
    public void validateSearchTMAInfoResult(HashMap<String, Object> data) {
        try {
            AppBasedUtils obj = new AppBasedUtils();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithImporterDealerRefNoJobNoAndMatReqID") {
                for (int i = 1; i < (searchResult1.size()); i++) {
                    String value[] = searchResult1.get(i).getText().split("-");
                    obj.isNumberBetweenRange(value[0].trim(), appBasedUtils.getStringData(data, "ImporterFrom"),
                                             appBasedUtils.getStringData(data, "ImporterTo"));
                    obj.isNumberBetweenRange(value[1].trim(), appBasedUtils.getStringData(data, "DealerFrom"), appBasedUtils.getStringData(data, "DealerTo"));
                    obj.isNumberBetweenRange(value[2].trim(), appBasedUtils.getStringData(data, "RefFrom"), appBasedUtils.getStringData(data, "RefTo"));
                    Assert.assertEquals(value[3].trim(), appBasedUtils.getStringData(data, "JobNo"));
                }
            }
            String secondCol = keywords.getTextValue(col2);
            switch (secondCol) {
            case "Material request Id":
                for (int i = 1; i < (searchResult2.size()); i++) {
                    obj.isNumberBetweenRange(searchResult2.get(i).getText().trim(), appBasedUtils.getStringData(data, "MaterialReqIdFrom"),
                                             appBasedUtils.getStringData(data, "MaterialReqIdTo"));
                }
                break;
            case "TMA status":
                for (int i = 1; i < (searchResult2.size()); i++) {
                    Assert.assertEquals(searchResult2.get(i).getText().trim(), appBasedUtils.getStringData(data, "TMA status"));
                }
                break;
            case "Root cause Id":
                for (int i = 1; i < (searchResult2.size()); i++) {
                    Assert.assertEquals(searchResult2.get(i).getText().trim(), appBasedUtils.getStringData(data, "Root cause id"));
                }
                break;
            }
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithDecisionAndStatus"
                    || testCaseName == "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails") {
                String thirdCol = keywords.getTextValue(col3);
                switch (thirdCol) {
                case "TMA status date":
                    for (int i = 1; i < (searchResult3.size()); i++) {
                        obj.isDateBetweenRange(searchResult3.get(i).getText().trim(), appBasedUtils.getStringData(data, "TMA status date from"),
                                               appBasedUtils.getStringData(data, "TMA status date to"));
                    }
                    break;
                case "Description":
                    for (int i = 1; i < (searchResult3.size()); i++) {
                        Assert.assertEquals(searchResult3.get(i).getText().trim(), appBasedUtils.getStringData(data, "Description"));
                    }
                    break;
                }
            }
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithDecisionAndStatus"
                    || testCaseName == "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails") {
                String fourthCol = keywords.getTextValue(col4);
                switch (fourthCol) {
                case "Material status":
                    for (int i = 1; i < (searchResult4.size()); i++) {
                        Assert.assertEquals(searchResult4.get(i).getText().trim(), appBasedUtils.getStringData(data, "Material status"));
                    }
                    break;
                case "Part prefix":
                    for (int i = 1; i < (searchResult4.size()); i++) {
                        Assert.assertEquals(searchResult4.get(i).getText().trim(), appBasedUtils.getStringData(data, "Part prefix"));
                    }
                    break;
                }
            }
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithDecisionAndStatus"
                    || testCaseName == "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails") {
                String fifthCol = keywords.getTextValue(col5);
                switch (fifthCol) {
                case "Material status date":
                    for (int i = 1; i < (searchResult5.size()); i++) {
                        obj.isDateBetweenRange(searchResult5.get(i).getText().trim(), appBasedUtils.getStringData(data, "Material status date from"),
                                               appBasedUtils.getStringData(data, "Material status date to"));
                    }
                    break;
                case "Part number":
                    for (int i = 1; i < (searchResult5.size()); i++) {
                        obj.isNumberBetweenRange(searchResult5.get(i).getText().trim(), appBasedUtils.getStringData(data, "Part number from"),
                                                 appBasedUtils.getStringData(data, "Part number to"));
                    }
                    break;
                }
            }
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithDecisionAndStatus"
                    || testCaseName == "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails") {
                String sixthCol = keywords.getTextValue(col6);
                switch (sixthCol) {
                case "TMA decision":
                    for (int i = 1; i < (searchResult6.size()); i++) {
                        Assert.assertEquals(searchResult6.get(i).getText().trim(), appBasedUtils.getStringData(data, "TMA decision"));
                    }
                    break;
                case "Part name":
                    for (int i = 1; i < (searchResult6.size()); i++) {
                        Assert.assertEquals(searchResult6.get(i).getText().trim(), appBasedUtils.getStringData(data, "Part name"));
                    }
                    break;
                }
            }
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithDecisionAndStatus"
                    || testCaseName == "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails") {
                String seventhCol = keywords.getTextValue(col7);
                if (seventhCol.equalsIgnoreCase("Third party Id")) {
                    for (int i = 1; i < (searchResult7.size()); i++) {
                        obj.isNumberBetweenRange(searchResult7.get(i).getText().trim(), appBasedUtils.getStringData(data, "Third party Id from"),
                                                 appBasedUtils.getStringData(data, "Third party Id to"));
                    }
                }
            }
            if (testCaseName == "RT_TMA_005_SearchTMAinformationWithRootCauseIdPartDetailsAndThirdPartyDetails") {
                String eighthCol = keywords.getTextValue(col8);
                switch (eighthCol) {
                case "Third party name":
                    for (int i = 1; i < (searchResult8.size()); i++) {
                        Assert.assertEquals(searchResult8.get(i).getText().trim(), appBasedUtils.getStringData(data, "Third party name"));
                    }
                    break;
                }
            }
            if (searchResult1.size() == 0) {
                Assert.fail();
            }
            ExtentTestManager.getTest().log(Status.INFO, "Validated search result");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : validateSearchTMAInfoResultTable() #Author : Aditi #Description : Validating Root cause summary table
     * #Date of creation :08-01-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification:08-01-19
     ******************************************/
    public void validateSearchTMAInfoResultTable() {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            if (keywords.elementIsDisplayed(rootCauseSummary, "Root cause summary")) {
                ExtentTestManager.getTest().log(Status.INFO, "Root cause summary table is displaying");
            } else {
                Assert.fail("Root cause summary table is not displaying");
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }
}