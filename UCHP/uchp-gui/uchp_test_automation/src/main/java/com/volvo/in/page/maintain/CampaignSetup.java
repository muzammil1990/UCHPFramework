package com.volvo.in.page.maintain;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

public class CampaignSetup {
    WebDriver driver;
    private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert softAssert;
    UIUtils keywords = new UIUtils(driver);
    public Logger APP_LOGS = Logger.getLogger(CampaignSetup.class);
    public WebDriverWait wait = null;
    String companyName;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintain;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Campaign setup ')]")
    WebElement campaignSetupSubmenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'SCC types ')]")
    WebElement newSccTypes;

    @FindBy(how = How.XPATH, using = "//h4[text()='SCC types']")
    WebElement newSccTypesTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='campaignCondition']")
    WebElement newSccButton;

    @FindBy(how = How.XPATH, using = "//input[@id='sccType']")
    WebElement sccTypeId;

    @FindBy(how = How.XPATH, using = "//*[@id='description']")
    WebElement description;

    @FindBy(how = How.XPATH, using = "//input[@id='validForCampaign']")
    WebElement campaignCheckbox;

    @FindBy(how = How.XPATH, using = "//button[@id='saveMaintain']")
    WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Region ')]")
    WebElement newRegion;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Group ')]")
    WebElement newGroup;

    @FindBy(how = How.XPATH, using = "//h4[text()='Group']")
    WebElement newGroupTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='region']")
    WebElement newRegionButton;

    @FindBy(how = How.XPATH, using = "//h4[text()='Region']")
    WebElement newRegionTitle;

    @FindBy(how = How.XPATH, using = "//h4[text()='Warranty area campaign administrative info']")
    WebElement warrantyCmpnAdmInfoTitle;

    @FindBy(how = How.XPATH, using = "//input[@name='region']")
    WebElement newRegionId;

    @FindBy(how = How.XPATH, using = "//input[@id='labourRateImporterNo']")
    WebElement newRegionImpNo;

    @FindBy(how = How.XPATH, using = "//input[@id='partPriceImporterNo']")
    WebElement newPartPriceImpNo;

    @FindBy(how = How.XPATH, using = "//select[@id='languageCode1']")
    WebElement newLanguageCode1;

    @FindBy(how = How.XPATH, using = " //select[@id='languageCode1'][contains(text(),'en')]")
    WebElement newLanguageCodeOption;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Supplier contribution ')]")
    WebElement newSupplierContribution;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Warranty area campaign administrative info ')]")
    WebElement newWararntyCmpnAdmInfo;

    @FindBy(how = How.XPATH, using = "//h4[text()='Warranty district campaign administrative info']")
    WebElement newWararntyDstAdmInfoTitle;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Warranty district campaign administrative info ')]")
    WebElement newwararntydstadminfo;

    @FindBy(how = How.XPATH, using = "//h4[text()='Supplier contribution']")
    WebElement newSupplierTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='labourPercent']")
    WebElement labourPercent;

    @FindBy(how = How.XPATH, using = "//input[@id='materialPercent']")
    WebElement materialPercent;

    @FindBy(how = How.XPATH, using = "//input[@id='otherCostPercent']")
    WebElement othercostPercent;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Original documents ')]")
    WebElement newOriginalDocuments;

    @FindBy(how = How.XPATH, using = "//h4[text()='Original documents']")
    WebElement newOriginalDocumentsTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='originalDocument']")
    WebElement newOriginalDocumentButton;

    @FindBy(how = How.XPATH, using = "//input[@id='document']")
    WebElement documentId;

    @FindBy(how = How.XPATH, using = "//input[@id='sortNo']")
    WebElement sortNo;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Campaign board assignees ')]")
    WebElement newCampaignBoardAssignees;

    @FindBy(how = How.XPATH, using = "//button[@id='cmpBoardAssignee']")
    WebElement newCampBrdAsgnButton;

    @FindBy(how = How.XPATH, using = "//input[@id='assigneeUserId']")
    WebElement newAsgnUserId;

    @FindBy(how = How.XPATH, using = "//input[@id='assigneeUserName']")
    WebElement newAsgnUserName;

    @FindBy(how = How.XPATH, using = "//h4[text()='Campaign board assignees']")
    WebElement newCampAssignTitle;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Translation languages ')]")
    WebElement newTranslationlanguages;

    @FindBy(how = How.XPATH, using = "//h4[text()='Translation languages']")
    WebElement newTranslationlanguageTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='languageCode']")
    WebElement langCode;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Importer campaign administrative info ')]")
    WebElement newCampAdmInfo;

    @FindBy(how = How.XPATH, using = "//h4[text()='Importer campaign administrative info']")
    WebElement newCampAdmInfoTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='importerNo']")
    WebElement importerNo;

    @FindBy(how = How.XPATH, using = "//select[@id='languageCode1']")
    WebElement langCode1;

    @FindBy(how = How.XPATH, using = "//input[@id='refDealer']")
    WebElement dealerNo;

    @FindBy(how = How.XPATH, using = "//select[@id='make']")
    WebElement brand;

    @FindBy(how = How.XPATH, using = "//select[@id='priceSource']")
    WebElement priceSource;

    @FindBy(how = How.XPATH, using = "//select[@id='priceType']")
    WebElement priceType;

    @FindBy(how = How.XPATH, using = "//input[@id='cafLabour']")
    WebElement cafLabour;

    @FindBy(how = How.XPATH, using = "//input[@id='cafMaterial']")
    WebElement cafMaterial;

    @FindBy(how = How.XPATH, using = "//input[@id='cafOtherCost']")
    WebElement cafOthercost;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Campaign conditions ')]")
    WebElement newCampaignConditions;

    @FindBy(how = How.XPATH, using = "//h4[text()='Campaign conditions']")
    WebElement newCampConditionTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='campaignCondition']")
    WebElement newCampConditionButton;

    @FindBy(how = How.XPATH, using = "//input[@id='task']")
    WebElement conditionId;

    @FindBy(how = How.XPATH, using = " //select[@id='campaignConditionTypeId']")
    WebElement campConditionTypeId;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Material order parameters ')]")
    WebElement newMaterialOrderParameters;

    @FindBy(how = How.XPATH, using = "//h4[text()='Material order parameters']")
    WebElement newMaterialOrderParamTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='mtrlOrderParam']")
    WebElement matOrderParamButton;

    @FindBy(how = How.XPATH, using = "//input[@id='parameter']")
    WebElement matParam;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Lead time warning limits ')]")
    WebElement newLeadTimeWarningLimits;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Lead time warning limits')]")
    WebElement newLeadtimeLimitsTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='leadTimeLimit']")
    WebElement leadTimeLimitButton;

    @FindBy(how = How.XPATH, using = "//select[@id='sccType']")
    WebElement sccType;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//input[@id='leadTime']")
    WebElement leadTimeDays;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Reason codes ')]")
    WebElement newReasonCodes;

    @FindBy(how = How.XPATH, using = "//h4[text()='Reason codes']")
    WebElement newReasonCodesTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='reasonCode']")
    WebElement reasonCode;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//button[@id='deleteMaintain']")
    WebElement deleteButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Yes']")
    WebElement confirmDeleteYes;

    @FindBy(how = How.XPATH, using = "//span[@class='ng-binding'][contains(text(),'Modified time')]")
    WebElement modifiedTimecolumn;

    public CampaignSetup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Scc types" submenu.
     ******************************************/
    public void navigateToSccTypes() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newSccTypes, newSccTypesTitle);
    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup"submenu --> "Region" submenu.
     ******************************************/
    public void navigateToRegion() {
        keywords.waitTime1(6);
        if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
            keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newGroup, newGroupTitle);
        } else {
            keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newRegion, newRegionTitle);
        }
    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Supplier Contribution" submenu.
     ******************************************/
    public void navigateToSupplierContribution() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newSupplierContribution, newSupplierTitle);

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Original Documents" submenu.
     ******************************************/
    public void navigateToOriginalDocuments() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newOriginalDocuments, newOriginalDocumentsTitle);

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Campaign board assignees" submenu.
     ******************************************/
    public void navigateToCampaignBoardAssignees() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newCampaignBoardAssignees, newCampAssignTitle);

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Translation languages" submenu.
     ******************************************/
    public void navigateToTranslationlanguages() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newTranslationlanguages, newTranslationlanguageTitle);

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu -->"Importer campaign administrative info" submenu.
     ******************************************/
    public void navigateToImpCampAdmInfo() {
        keywords.waitTime1(6);

        if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
            keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newWararntyCmpnAdmInfo, warrantyCmpnAdmInfoTitle);
        } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
            keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newwararntydstadminfo, newWararntyDstAdmInfoTitle);
        } else {
            keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newCampAdmInfo, newCampAdmInfoTitle);

        }

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Campaign conditions" sub menu.
     ******************************************/
    public void navigateToCampaignConditions() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newCampaignConditions, newCampConditionTitle);

    }

    /****************************************
     * #Description : * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Material order parameters"
     * submenu.
     ******************************************/
    public void navigateToMaterialOrderParameters() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newMaterialOrderParameters, newMaterialOrderParamTitle);

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Lead time warning limits" sub menu.
     ******************************************/
    public void navigateToLeadTimeWarningLimits() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newLeadTimeWarningLimits, newLeadtimeLimitsTitle);

    }

    /****************************************
     * #Description : This is a common function to navigate from "Maintain"menu to "Campaign Setup" sub menu --> "Reason codes" submenu.
     ******************************************/
    public void navigateToReasonCodes() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, campaignSetupSubmenu, newReasonCodes, newReasonCodesTitle);

    }

    /****************************************
     * #Description : Create a new Scc type under Campaign Setup
     ******************************************/
    public void createSccTypes(HashMap<String, Object> scctypes) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newSccButton);
            keywords.input(sccTypeId, appBasedUtils.getStringData(scctypes, "SccTypeId"), "SccTypeId");
            keywords.input(description, appBasedUtils.getStringData(scctypes, "SccDescription"), "SccDescription");
            keywords.setCheckboxIfNotSelected(campaignCheckbox);
            keywords.waitTime1(2);
            keywords.clickOnButton(driver, saveButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Scc type is saved successfully");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new region under Campaign Setup
     ******************************************/
    public void createRegion(HashMap<String, Object> region) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newRegionButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(newRegionId, appBasedUtils.getStringData(region, "RegionId"), "RegionId");
            keywords.input(description, appBasedUtils.getStringData(region, "SccDescription"), "RegionDescription");
            keywords.input(newRegionImpNo, appBasedUtils.getStringData(region, "ImporterNo"), "ImporterNo");
            keywords.input(newPartPriceImpNo, appBasedUtils.getStringData(region, "PartPriceImporterNo"), "PartPriceImporterNo");
            keywords.clickOnButton(driver, newLanguageCode1);
            keywords.selectVisibleText(newLanguageCode1, appBasedUtils.getStringData(region, "LanguageCode"));
            keywords.waitTime1(2);
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Region is saved successfully");

        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Adding a Supplier Contribution under Campaign Setup
     ******************************************/
    public void createSupplierContribution(HashMap<String, Object> supplierContribution) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(labourPercent, appBasedUtils.getStringData(supplierContribution, "LabourPerc"), "LabourPerc");
            keywords.input(materialPercent, appBasedUtils.getStringData(supplierContribution, "MaterialPerc"), "MaterialPerc");
            keywords.input(othercostPercent, appBasedUtils.getStringData(supplierContribution, "OthercostPerc"), "OthercostPerc");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Supplier contribution is saved successfully");

        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Original document under Campaign Setup
     ******************************************/
    public void createOriginalDocuments(HashMap<String, Object> orginaldocs) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newOriginalDocumentButton);
            keywords.input(documentId, appBasedUtils.getStringData(orginaldocs, "DocumentId"), "DocumentId");
            keywords.input(sortNo, appBasedUtils.getStringData(orginaldocs, "SortNo"), "SortNo");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New original documents is saved successfully");

        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Campaign board assignee under Campaign Setup
     ******************************************/
    public void createCampaignboardassignees(HashMap<String, Object> campbrdasgn) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newCampBrdAsgnButton);
            keywords.input(newAsgnUserId, appBasedUtils.getStringData(campbrdasgn, "CampBrdAsgnId"), "CampBrdAsgnId");
            keywords.input(newAsgnUserName, appBasedUtils.getStringData(campbrdasgn, "CampBrdAsgnName"), "CampBrdAsgnName");
            keywords.clickOnButton(driver, saveButton);
            ExtentTestManager.getTest().log(Status.INFO, "New campain board assignee is saved successfully");
            keywords.waitTime1(5);

        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a translation language and Description under Campaign Setup
     ******************************************/
    public void createTranslationLanguages(HashMap<String, Object> translanguages) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newCampBrdAsgnButton);
            keywords.input(langCode, appBasedUtils.getStringData(translanguages, "TransLangCode"), "TransLangCode");
            keywords.input(description, appBasedUtils.getStringData(translanguages, "SccDescription"), "SccTypeDesc");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New translation language is saved successfully");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Importer campaign administrative info under Campaign Setup
     ******************************************/
    public void createImpCampgnAdmInfo(HashMap<String, Object> campadminfo) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newRegionButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(importerNo, appBasedUtils.getStringData(campadminfo, "CampgnAdmImporterNo"), "CampgnAdmImporterNo");
            keywords.clickOnButton(driver, langCode1);
            keywords.selectVisibleText(langCode1, appBasedUtils.getStringData(campadminfo, "LanguageCode"));
            keywords.input(dealerNo, appBasedUtils.getStringData(campadminfo, "DealerNo"), "DealerNo");
            keywords.clickOnButton(driver, brand);
            keywords.selectVisibleText(brand, appBasedUtils.getStringData(campadminfo, "Brand"));
            keywords.clickOnButton(driver, priceSource);
            keywords.selectVisibleText(priceSource, appBasedUtils.getStringData(campadminfo, "PriceSource"));
            keywords.clickOnButton(driver, priceType);
            keywords.selectVisibleText(priceType, appBasedUtils.getStringData(campadminfo, "PriceType"));
            keywords.input(cafLabour, appBasedUtils.getStringData(campadminfo, "CAFLabour"), "CAFLabour");
            keywords.input(cafMaterial, appBasedUtils.getStringData(campadminfo, "CAFMaterial"), "CAFMaterial");
            keywords.input(cafOthercost, appBasedUtils.getStringData(campadminfo, "CAFOtherCost"), "CAFOtherCost");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Importer campaign administrative info is saved successfully");

        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Campaign Condition under Campaign Setup
     ******************************************/
    public void createCampaignConditions(HashMap<String, Object> campcond) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newCampConditionButton);
            keywords.input(conditionId, appBasedUtils.getStringData(campcond, "ConditionId"), "ConditionId");
            keywords.clickOnButton(driver, campConditionTypeId);
            keywords.selectVisibleText(campConditionTypeId, appBasedUtils.getStringData(campcond, "ConditionType"));
            keywords.input(sortNo, appBasedUtils.getStringData(campcond, "SortNo"), "SortNo");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Campaign conditions is saved successfully");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Material Order Parameter under Campaign Setup
     ******************************************/
    public void createMaterialOrderParam(HashMap<String, Object> matordparam) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, matOrderParamButton);
            keywords.input(matParam, appBasedUtils.getStringData(matordparam, "MaterialParameter"), "MaterialParameter");
            keywords.input(sortNo, appBasedUtils.getStringData(matordparam, "SortNo"), "SortNo");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Material order parameter is saved successfully");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Lead time warning limit under Campaign Setup
     ******************************************/
    public void createLeadTimeWarningLimit(HashMap<String, Object> leadlimit) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, leadTimeLimitButton);
            keywords.clickOnButton(driver, sccType);
            keywords.selectVisibleText(sccType, appBasedUtils.getStringData(leadlimit, "SccTypeDesc"));
            keywords.input(leadTimeDays, appBasedUtils.getStringData(leadlimit, "LeadTimeDays"), "LeadTimeDays");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Lead time limit is saved successfully");

        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Create a new Reason codes under Campaign Setup
     ******************************************/
    public void createReasonCodes(HashMap<String, Object> reasoncodes) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, newCampBrdAsgnButton);
            keywords.input(reasonCode, appBasedUtils.getStringData(reasoncodes, "ReasonCode"), "ReasonCode");
            keywords.input(description, appBasedUtils.getStringData(reasoncodes, "SccDescription"), "ReasonCodeDescription");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "New Reason codes is saved successfully");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Region exists in the list of Regions and delete the Region.
     ******************************************/
    public void viewRegionAndDelete(HashMap<String, Object> regcampsetup) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Regions");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(regcampsetup, "RegionId") + "']";
            WebElement regionId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, regionId);
            keywords.waitForVisibilityOfElement(driver, 20, newRegionTitle);
            APP_LOGS.info("Region exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newRegionTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Region is deleted successfully" + appBasedUtils.getStringData(regcampsetup, "RegionId"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created SccTypes exists in the list of Scctypes and delete.
     ******************************************/
    public void viewSccTypeAndDelete(HashMap<String, Object> scctypeSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of SccTypes");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(scctypeSetup, "SccTypeId") + "']";
            WebElement sccTypeId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, sccTypeId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newSccTypesTitle);
            APP_LOGS.info("SccType exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newSccTypesTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Scc tpe is deleted successfully" + appBasedUtils.getStringData(scctypeSetup, "SccTypeId"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created OriginalDocuments exists in the list and delete.
     ******************************************/
    public void viewOriginalDocumentsAndDelete(HashMap<String, Object> orgDocSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Original Documents");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(orgDocSetup, "DocumentId") + "']";
            WebElement documentnoId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, documentnoId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newOriginalDocumentsTitle);
            APP_LOGS.info("Original document exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newOriginalDocumentsTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Originaldocument is deleted successfully" + appBasedUtils.getStringData(orgDocSetup, "DocumentId"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Campaign board Assignees exists in the list and delete.
     ******************************************/
    public void viewCampBrdAgnsAndDelete(HashMap<String, Object> cmpAsgnSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Campaign board Assignees");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(cmpAsgnSetup, "CampBrdAsgnId") + "']";
            WebElement cmpBrdAsgnId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, cmpBrdAsgnId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newCampAssignTitle);
            APP_LOGS.info("Campaign board Assignees exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newCampAssignTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Campaign board Assignee is deleted successfully" + appBasedUtils.getStringData(cmpAsgnSetup, "CampBrdAsgnId"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Translation languages exists in the list and delete.
     ******************************************/
    public void viewTranslationLanguagesAndDelete(HashMap<String, Object> trnsLangSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Campaign board Assignees");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(trnsLangSetup, "TransLangCode") + "']";
            WebElement trnsLangCode = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, trnsLangCode);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newTranslationlanguageTitle);
            APP_LOGS.info("Translation languages code exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newTranslationlanguageTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Translation language is deleted successfully" + appBasedUtils.getStringData(trnsLangSetup, "TransLangCode"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Importer campaign administrative info exists in the list and delete.
     ******************************************/
    public void viewImporterCampaignAdmininfoAndDelete(HashMap<String, Object> impCampAdminSetup) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(modifiedTimecolumn, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(modifiedTimecolumn, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Importer campaign administrative info");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            String Xpath = "//div[contains(text(), '" + appBasedUtils.getStringData(impCampAdminSetup, "ImporterNo") + "')]";
            WebElement importerNo = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, importerNo);
            if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                keywords.waitForVisibilityOfElement(driver, 20, warrantyCmpnAdmInfoTitle);
            } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                keywords.waitForVisibilityOfElement(driver, 20, newWararntyDstAdmInfoTitle);
            } else {
                keywords.waitForVisibilityOfElement(driver, 20, newCampAdmInfoTitle);
            }

            APP_LOGS.info("Importer/Warranty Area/Warranty District administrative info exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);

            if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {

                keywords.waitForVisibilityOfElement(driver, 20, warrantyCmpnAdmInfoTitle);
            } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                keywords.waitForVisibilityOfElement(driver, 20, newWararntyDstAdmInfoTitle);
            } else {
                keywords.waitForVisibilityOfElement(driver, 20, newCampAdmInfoTitle);

            }
            ExtentTestManager.getTest().log(Status.INFO, "Importer info is deleted successfully" + appBasedUtils.getStringData(impCampAdminSetup, "ImporterNo"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }

    }

    /****************************************
     * #Description :Verify that created Campaign conditions exists in the list and delete.
     ******************************************/
    public void viewCampaignConditionsAndDelete(HashMap<String, Object> cmpCondSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Campaign conditions");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(cmpCondSetup, "ConditionId") + "']";
            WebElement cmpConditionId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, cmpConditionId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newCampConditionTitle);
            APP_LOGS.info("Campaign conditions exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newCampConditionTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Campaign conditions is deleted successfully" + appBasedUtils.getStringData(cmpCondSetup, "ConditionId"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Material order parameters exists in the list and delete.
     ******************************************/
    public void viewMaterialOrderParameterAndDelete(HashMap<String, Object> mttrlOrderParamSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Material order parameters");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(mttrlOrderParamSetup, "MaterialParameter") + "']";
            WebElement mtrlOrdParamId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, mtrlOrdParamId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newMaterialOrderParamTitle);
            APP_LOGS.info("Material order parameters exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newMaterialOrderParamTitle);
            ExtentTestManager.getTest().log(Status.INFO,
                               "Material order parameter is deleted successfully" + appBasedUtils.getStringData(mttrlOrderParamSetup, "MaterialParameter"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Lead time warning limits exists in the list and delete.
     ******************************************/
    public void viewLeadTimeWarningLimitsAndDelete(HashMap<String, Object> leadTimeWarnLimitSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Lead time warning limits");
            String Xpath = "//div[contains(text(), '" + appBasedUtils.getStringData(leadTimeWarnLimitSetup, "LeadTimeDays") + "')]";
            WebElement leadTimeSetup = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, leadTimeSetup);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newLeadtimeLimitsTitle);
            APP_LOGS.info("Lead time warning limits exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newLeadtimeLimitsTitle);
            ExtentTestManager.getTest().log(Status.INFO,
                               "Lead time warning limit is deleted successfully" + appBasedUtils.getStringData(leadTimeWarnLimitSetup, "LeadTimeDays"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Reason codes exists in the list of Reason Codes and delete.
     ******************************************/
    public void viewReasonCodeAndDelete(HashMap<String, Object> reasonCodeSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Reason codes");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(reasonCodeSetup, "ReasonCode") + "']";
            WebElement reasonCodeId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, reasonCodeId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newReasonCodesTitle);
            APP_LOGS.info("Reason Codes exist in list");
            keywords.clickOnButton(driver, deleteButton);
            keywords.waitForVisibilityOfElement(driver, 20, confirmDeleteYes);
            keywords.clickOnButton(driver, confirmDeleteYes);
            keywords.waitForVisibilityOfElement(driver, 20, newReasonCodesTitle);
            ExtentTestManager.getTest().log(Status.INFO, "Lead time warning limit is deleted successfully" + appBasedUtils.getStringData(reasonCodeSetup, "ReasonCode"));
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Region exists in the list of Regions and Modify the Region.
     ******************************************/
    public void viewRegionAndModify(HashMap<String, Object> regcampsetup) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Regions");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(regcampsetup, "RegionId") + "']";
            WebElement regionId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, regionId);
            if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                keywords.waitForVisibilityOfElement(driver, 20, newGroupTitle);
            } else {
                keywords.waitForVisibilityOfElement(driver, 20, newRegionTitle);
            }
            APP_LOGS.info("Region exist in list");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(description, appBasedUtils.getStringData(regcampsetup, "SccDescription"), "RegionDescription");
            keywords.clickOnButton(driver, newLanguageCode1);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.selectVisibleText(newLanguageCode1, appBasedUtils.getStringData(regcampsetup, "LanguageCode"));
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Region/Group is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created SccTypes exists in the list of Scctypes and Modify.
     ******************************************/
    public void viewSccTypeAndModify(HashMap<String, Object> scctypeSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of SccTypes");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(scctypeSetup, "SccTypeId") + "']";
            WebElement sccTypeId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, sccTypeId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newSccTypesTitle);
            APP_LOGS.info("SccType exist in list");
            keywords.input(description, appBasedUtils.getStringData(scctypeSetup, "SccDescription"), "SccDescription");
            keywords.setCheckboxIfNotSelected(campaignCheckbox);
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Scc type is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created OriginalDocuments exists in the list and Modify.
     ******************************************/
    public void viewOriginalDocumentsAndModify(HashMap<String, Object> orgDocSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Original Documents");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(orgDocSetup, "DocumentId") + "']";
            WebElement documentnoId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, documentnoId);
            keywords.waitForVisibilityOfElement(driver, 20, newOriginalDocumentsTitle);
            APP_LOGS.info("Original document exist in list");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(sortNo, appBasedUtils.getStringData(orgDocSetup, "SortNo"), "SortNo");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Original documents modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Campaign board Assignees exists in the list and Modify.
     ******************************************/
    public void viewCampBrdAgnsAndModify(HashMap<String, Object> cmpAsgnSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Campaign board Assignees");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(cmpAsgnSetup, "CampBrdAsgnId") + "']";
            WebElement cmpBrdAsgnId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, cmpBrdAsgnId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newCampAssignTitle);
            APP_LOGS.info("Campaign board Assignees exist in list");
            keywords.input(newAsgnUserName, appBasedUtils.getStringData(cmpAsgnSetup, "CampBrdAsgnName"), "CampBrdAsgnName");
            keywords.clickOnButton(driver, saveButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            ExtentTestManager.getTest().log(Status.INFO, "Campain board assignee is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Translation languages exists in the list and Modify.
     ******************************************/
    public void viewTranslationLanguagesAndModify(HashMap<String, Object> trnsLangSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Campaign board Assignees");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(trnsLangSetup, "TransLangCode") + "']";
            WebElement trnsLangCode = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, trnsLangCode);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newTranslationlanguageTitle);
            APP_LOGS.info("Translation languages code exist in list");
            keywords.input(description, appBasedUtils.getStringData(trnsLangSetup, "SccDescription"), "SccTypeDesc");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Translation language is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Importer campaign administrative info exists in the list and Modify.
     ******************************************/
    public void viewImporterCampaignAdmininfoAndModify(HashMap<String, Object> impCampAdminSetup) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(modifiedTimecolumn, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(modifiedTimecolumn, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Importer campaign administrative info");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            String Xpath = "//div[contains(text(), '" + appBasedUtils.getStringData(impCampAdminSetup, "ImporterNo") + "')]";
            WebElement importerNo = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, importerNo);
            if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                keywords.waitForVisibilityOfElement(driver, 20, warrantyCmpnAdmInfoTitle);
            } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                keywords.waitForVisibilityOfElement(driver, 20, newWararntyDstAdmInfoTitle);
            } else {
                keywords.waitForVisibilityOfElement(driver, 20, newCampAdmInfoTitle);
            }

            APP_LOGS.info("Importer/Warranty Area/Warranty District administrative info exist in list");

            if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {

                keywords.waitForVisibilityOfElement(driver, 20, warrantyCmpnAdmInfoTitle);
            } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                keywords.waitForVisibilityOfElement(driver, 20, newWararntyDstAdmInfoTitle);
            } else {
                keywords.waitForVisibilityOfElement(driver, 20, newCampAdmInfoTitle);

            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(cafLabour, appBasedUtils.getStringData(impCampAdminSetup, "CAFLabour"), "CAFLabour");
            keywords.input(cafMaterial, appBasedUtils.getStringData(impCampAdminSetup, "CAFMaterial"), "CAFMaterial");
            keywords.input(cafOthercost, appBasedUtils.getStringData(impCampAdminSetup, "CAFOtherCost"), "CAFOtherCost");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Importer campaign administrative info is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }

    }

    /****************************************
     * #Description :Verify that created Campaign conditions exists in the list and Modify.
     ******************************************/
    public void viewCampaignConditionsAndModify(HashMap<String, Object> cmpCondSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Campaign conditions");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(cmpCondSetup, "ConditionId") + "']";
            WebElement cmpConditionId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, cmpConditionId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newCampConditionTitle);
            APP_LOGS.info("Campaign conditions exist in list");
            keywords.selectVisibleText(campConditionTypeId, appBasedUtils.getStringData(cmpCondSetup, "ConditionType"));
            keywords.input(sortNo, appBasedUtils.getStringData(cmpCondSetup, "SortNo"), "SortNo");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Campaign condition is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Material order parameters exists in the list and Modify.
     ******************************************/
    public void viewMaterialOrderParameterAndModify(HashMap<String, Object> mttrlOrderParamSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Material order parameters");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(mttrlOrderParamSetup, "MaterialParameter") + "']";
            WebElement mtrlOrdParamId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, mtrlOrdParamId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newMaterialOrderParamTitle);
            APP_LOGS.info("Material order parameters exist in list");
            keywords.input(sortNo, appBasedUtils.getStringData(mttrlOrderParamSetup, "SortNo"), "SortNo");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Material order parameter is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Lead time warning limits exists in the list and Modify.
     ******************************************/
    public void viewLeadTimeWarningLimitsAndModify(HashMap<String, Object> leadTimeWarnLimitSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Lead time warning limits");
            String Xpath = "//div[contains(text(), '" + appBasedUtils.getStringData(leadTimeWarnLimitSetup, "LeadTimeDays") + "')]";
            WebElement leadTimeSetup = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, leadTimeSetup);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newLeadtimeLimitsTitle);
            APP_LOGS.info("Lead time warning limits exist in list");
            keywords.input(leadTimeDays, appBasedUtils.getStringData(leadTimeWarnLimitSetup, "LeadTimeDays"), "LeadTimeDays");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Lead time limit is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description :Verify that created Reason codes exists in the list of Reason Codes and Modify.
     ******************************************/
    public void viewReasonCodeAndModify(HashMap<String, Object> reasonCodeSetup) {
        try {

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.elementIsDisplayed(gridColumnName, "List of Reason codes");
            String Xpath = "//div[text()='" + appBasedUtils.getStringData(reasonCodeSetup, "ReasonCode") + "']";
            WebElement reasonCodeId = driver.findElement(By.xpath(Xpath));
            keywords.clickOnButton(driver, reasonCodeId);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, newReasonCodesTitle);
            APP_LOGS.info("Reason Codes exist in list");
            keywords.input(description, appBasedUtils.getStringData(reasonCodeSetup, "SccDescription"), "ReasonCodeDescription");
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(5);
            ExtentTestManager.getTest().log(Status.INFO, "Reason code is modified successfully");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

}
