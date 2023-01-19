package com.volvo.in.page.tma;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class NewMaterialRequest {
    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert openSelectedClaimJobAssert;
    UIUtils keywords = new UIUtils(driver);
    public WebDriverWait wait = null;
    SoftAssert softAssert;
    public String newMaterialId;
    public Logger APP_LOGS = Logger.getLogger(NewMaterialRequest.class);

    @FindBy(how = How.XPATH, using = "//*[@id='navigation-container']//a[contains(text(),'TMA')]")
    WebElement tmaMainMenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Material request')]")
    WebElement materialRequestSubMenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New material request ')]")
    WebElement newMaterialRequestSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[text()='New material request']")
    WebElement newMaterialRequestTitle;

    @FindBy(how = How.XPATH, using = "//label[text()='Id']")
    WebElement labelID;

    @FindBy(how = How.XPATH, using = "//span[contains(@ng-click,'showRequesterModal')]/i")
    WebElement listRequesterName;

    @FindBy(how = How.ID, using = "filterList")
    WebElement textBoxRequesterNameFiler;

    @FindBy(how = How.XPATH, using = "//label[text()='Filter']")
    WebElement labelFilter;

    @FindBy(how = How.XPATH, using = "//*[@class='table-responsive uchp-table modal-remarks']/table/tbody/tr[2]/td")
    WebElement selectFilteredOption;

    @FindBy(how = How.XPATH, using = "//div[@id='startDate']/span/span")
    WebElement startDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='startDate']/span/span")
    WebElement closeDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//*[@id='startDate']/input")
    WebElement startDate;

    @FindBy(how = How.XPATH, using = "//*[@id='closeDate']/input")
    WebElement closeDate;

    @FindBy(how = How.ID, using = "materialInstr")
    WebElement dropDownMIName;

    @FindBy(how = How.ID, using = "state")
    WebElement dropDownState;

    @FindBy(how = How.ID, using = "prio")
    WebElement textBoxPriority;

    @FindBy(how = How.ID, using = "typeOfConcern1")
    WebElement dropDownTypeOfConcern;

    @FindBy(how = How.XPATH, using = "(//span[@class='ng-binding ng-scope'])[2]")
    WebElement typeOfConcernLabelValue;

    @FindBy(how = How.ID, using = "comment")
    WebElement textBoxRequestComment;

    @FindBy(how = How.ID, using = "description")
    WebElement textBoxDescription;

    @FindBy(how = How.ID, using = "currency")
    WebElement dropDownCurrency;

    @FindBy(how = How.ID, using = "limitQty")
    WebElement textBoxLimitQuantity;

    @FindBy(how = How.ID, using = "notificationQty")
    WebElement textBoxNotificationQuantity;

    @FindBy(how = How.ID, using = "companyCheckbox_1")
    WebElement checkBoxCompanyVTC;

    @FindBy(how = How.ID, using = "companyCheckbox_2")
    WebElement checkBoxCompanyVBC;

    @FindBy(how = How.ID, using = "warranty_1")
    WebElement checkBoxClaimJobTypeVehicleWarranty;

    @FindBy(how = How.ID, using = "warranty_2")
    WebElement checkBoxClaimJobTypeParts;

    @FindBy(how = How.ID, using = "warranty_3")
    WebElement checkBoxClaimJobTypeCampaign;

    @FindBy(how = How.ID, using = "warranty_4")
    WebElement checkBoxClaimJobTypeServiceWarranty;

    @FindBy(how = How.ID, using = "warranty_5")
    WebElement checkBoxClaimJobTypeTechnicalGoodwill;

    @FindBy(how = How.ID, using = "warranty_6")
    WebElement checkBoxClaimJobTypeSpecificationDeviation;

    @FindBy(how = How.ID, using = "warranty_8")
    WebElement checkBoxClaimJobTypeCommercialGoodwill;

    @FindBy(how = How.ID, using = "warranty_9")
    WebElement checkBoxClaimJobTypeBreakdown;

    @FindBy(how = How.ID, using = "warranty_10")
    WebElement checkBoxClaimJobTypeProductionRelatedFailure;

    @FindBy(how = How.ID, using = "warranty_11")
    WebElement checkBoxClaimJobTypeExtendedCoverage;

    @FindBy(how = How.XPATH, using = "//span[text()='Approved net cost (total)']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleApprovedNetCostTotal;

    @FindBy(how = How.XPATH, using = "//span[text()='Approved net cost (material)']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleApprovedNetCostMaterial;

    @FindBy(how = How.XPATH, using = "//span[text()='Approved net cost (other cost)']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleApprovedNetCostOtherCost;

    @FindBy(how = How.XPATH, using = "//span[text()='Causal part no.']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleCausalPartNo;

    @FindBy(how = How.XPATH, using = "//span[text()='Causal part no. prefix']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleCausalPartNoPrefix;

    @FindBy(how = How.XPATH, using = "//span[text()='Chassis number']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleChassisNo;

    @FindBy(how = How.XPATH, using = "//span[text()='Chassis number series']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleChassisNoSeries;

    @FindBy(how = How.XPATH, using = "//span[text()='Claimed Labour hours']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleClaimedLabourHours;

    @FindBy(how = How.XPATH, using = "//span[text()='Using customer number']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleUsingCustomerNo;

    @FindBy(how = How.XPATH, using = "//span[text()='Debit code']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleDebitCode;

    @FindBy(how = How.XPATH, using = "//span[text()='Demo delivery age']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleDemoDeliveryAge;

    @FindBy(how = How.XPATH, using = "//span[text()='Function group']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleFunctionGroup;

    @FindBy(how = How.XPATH, using = "//span[text()='Included material function group']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleIncludedMaterialFunctionGroup;

    @FindBy(how = How.XPATH, using = "//span[text()='Included material part number']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleIncludedMaterialPartNo;

    @FindBy(how = How.XPATH, using = "//span[text()='Included material part number prefix']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleIncludedMaterialPartNoPrefix;

    @FindBy(how = How.XPATH, using = "//span[text()='Included operation']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleIncludedOperation;

    @FindBy(how = How.XPATH, using = "//span[text()='Main operation']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleMainOperation;

    @FindBy(how = How.XPATH, using = "//span[text()='Marketing type']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleMarketingType;

    @FindBy(how = How.XPATH, using = "//span[text()='Material instruction']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleMaterialInstruction;

    @FindBy(how = How.XPATH, using = "//span[text()='Material request']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleMaterialRequest;

    @FindBy(how = How.XPATH, using = "//span[text()='Operating hours']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleOperatingHours;

    @FindBy(how = How.XPATH, using = "//span[text()='Repair date']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleRepairDate;

    @FindBy(how = How.XPATH, using = "//span[text()='Repairing dealer']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleRepairingDealer;

    @FindBy(how = How.XPATH, using = "//span[text()='Repairing importer']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleRepairingImporter;

    @FindBy(how = How.XPATH, using = "//span[text()='Supplier']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleSupplier;

    @FindBy(how = How.XPATH, using = "//span[text()='Vehicle age']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleVehicleAge;

    @FindBy(how = How.XPATH, using = "//span[text()='Mileage(km)']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleMileageInKM;

    @FindBy(how = How.XPATH, using = "//span[text()='Claimed non-VST labour hours']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleClaimedNonVSTHours;

    @FindBy(how = How.XPATH, using = "//span[text()='Assembly date']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleAssemblyDate;

    @FindBy(how = How.XPATH, using = "//span[text()='Claim registration date']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleClaimRegistrationDate;

    @FindBy(how = How.XPATH, using = "//span[text()='Contract type']/parent::td/preceding-sibling::td/input")
    WebElement checkBoxRuleContractType;

    @FindBy(how = How.ID, using = "includeRule_APPRCLAIMJOBNETCOST")
    WebElement textBoxIncludeRuleApprovedNetCostTotal;

    @FindBy(how = How.ID, using = "excludeRule_APPRCLAIMJOBNETCOST")
    WebElement textBoxIExcludeRuleApprovedNetCostTotal;

    @FindBy(how = How.ID, using = "includeRule_APPRMTRLNETCOST")
    WebElement textBoxIncludeRuleApprovedNetCostMaterial;

    @FindBy(how = How.ID, using = "excludeRule_APPRMTRLNETCOST")
    WebElement textBoxIExcludeRuleApprovedNetCostMaterial;

    @FindBy(how = How.ID, using = "includeRule_APPROTHERCOSTNETCOST")
    WebElement textBoxIncludeRuleApprovedNetCostOtherCost;

    @FindBy(how = How.ID, using = "excludeRule_APPROTHERCOSTNETCOST")
    WebElement textBoxIExcludeRuleApprovedNetCostOtherCost;

    @FindBy(how = How.ID, using = "includeRule_CAUSALPARTNO")
    WebElement textBoxIncludeRuleCausalPartNo;

    @FindBy(how = How.ID, using = "excludeRule_CAUSALPARTNO")
    WebElement textBoxIExcludeRuleCausalPartNo;

    @FindBy(how = How.ID, using = "includeRule_CAUSALPARTNOPREFIX")
    WebElement textBoxIncludeRuleCausalPartPrefix;

    @FindBy(how = How.ID, using = "excludeRule_CAUSALPARTNOPREFIX")
    WebElement textBoxIExcludeRuleCausalPartPrefix;

    @FindBy(how = How.ID, using = "includeRule_CHASSISNUMBER")
    WebElement textBoxIncludeRuleChassisNo;

    @FindBy(how = How.ID, using = "excludeRule_CHASSISNUMBER")
    WebElement textBoxIExcludeRuleChassisNo;

    @FindBy(how = How.ID, using = "includeRule_CHASSISNUMBERSERIES")
    WebElement textBoxIncludeRuleChassisNoSeries;

    @FindBy(how = How.ID, using = "excludeRule_CHASSISNUMBERSERIES")
    WebElement textBoxIExcludeRuleChassisNoSeries;

    @FindBy(how = How.ID, using = "includeRule_DEBITCODE")
    WebElement textBoxIncludeRuleDebitCode;

    @FindBy(how = How.ID, using = "excludeRule_DEBITCODE")
    WebElement textBoxIExcludeRuleDebitCode;

    @FindBy(how = How.ID, using = "includeRule_FUNCTIONGROUP")
    WebElement textBoxIncludeRuleFunctionGroup;

    @FindBy(how = How.ID, using = "excludeRule_FUNCTIONGROUP")
    WebElement textBoxIExcludeRuleFunctionGroup;

    @FindBy(how = How.ID, using = "includeRule_INCLMTRLPARTNO")
    WebElement textBoxIncludeRuleIncludedMaterialPartNo;

    @FindBy(how = How.ID, using = "excludeRule_INCLMTRLPARTNO")
    WebElement textBoxIExcludeRuleIncludedMaterialPartNo;

    @FindBy(how = How.ID, using = "includeRule_INCLMTRLPARTNOPREFIX")
    WebElement textBoxIncludeRuleIncludedMaterialPartNoPrefix;

    @FindBy(how = How.ID, using = "excludeRule_INCLMTRLPARTNOPREFIX")
    WebElement textBoxIExcludeRuleIncludedMaterialPartNoPrefix;

    @FindBy(how = How.ID, using = "includeRule_MAINOPER")
    WebElement textBoxIncludeRuleMainOperation;

    @FindBy(how = How.ID, using = "excludeRule_MAINOPER")
    WebElement textBoxIExcludeRuleMainOperation;

    @FindBy(how = How.ID, using = "includeRule_MARKETTYPE")
    WebElement textBoxIncludeRuleMarketingType;

    @FindBy(how = How.ID, using = "excludeRule_MARKETTYPE")
    WebElement textBoxIExcludeRuleMarketingType;

    @FindBy(how = How.ID, using = "includeRule_OPERATINGHOURS")
    WebElement textBoxIncludeRuleOperatingHours;

    @FindBy(how = How.ID, using = "excludeRule_OPERATINGHOURS")
    WebElement textBoxIExcludeRuleOperatingHours;

    @FindBy(how = How.ID, using = "includeRule_REPAIRDATE")
    WebElement textBoxIncludeRuleRepairDate;

    @FindBy(how = How.ID, using = "excludeRule_REPAIRDATE")
    WebElement textBoxIExcludeRuleRepairDate;

    @FindBy(how = How.ID, using = "includeRule_REPAIRINGDEALER")
    WebElement textBoxIncludeRuleRepairingDealer;

    @FindBy(how = How.ID, using = "excludeRule_REPAIRINGDEALER")
    WebElement textBoxIExcludeRuleRepairingDealer;

    @FindBy(how = How.ID, using = "includeRule_REPAIRINGIMPORTER")
    WebElement textBoxIncludeRuleRepairingImporter;

    @FindBy(how = How.ID, using = "excludeRule_REPAIRINGIMPORTER")
    WebElement textBoxIExcludeRuleRepairingImporter;

    @FindBy(how = How.ID, using = "includeRule_VEHICLEAGE")
    WebElement textBoxIncludeRuleVehicleAge;

    @FindBy(how = How.ID, using = "excludeRule_VEHICLEAGE")
    WebElement textBoxIExcludeRuleVehicleAge;

    @FindBy(how = How.ID, using = "includeRule_VEHICLEMILEAGE")
    WebElement textBoxIncludeRuleVehicleMileage;

    @FindBy(how = How.ID, using = "excludeRule_VEHICLEMILEAGE")
    WebElement textBoxIExcludeRuleVehicleMileage;

    @FindBy(how = How.ID, using = "includeRule_ASSEMBLYDATE")
    WebElement textBoxIncludeRuleAssemblyDate;

    @FindBy(how = How.ID, using = "excludeRule_ASSEMBLYDATE")
    WebElement textBoxIExcludeRuleAssemblyDate;

    @FindBy(how = How.ID, using = "includeRule_CLAIMREGISTRATIONDATE")
    WebElement textBoxIncludeRuleClaimRegistrationDate;

    @FindBy(how = How.ID, using = "excludeRule_CLAIMREGISTRATIONDATE")
    WebElement textBoxIExcludeRuleClaimRegistrationDate;

    @FindBy(how = How.ID, using = "includeRule_CONTRACTTYPE")
    WebElement textBoxIncludeRuleContractType;

    @FindBy(how = How.ID, using = "excludeRule_CONTRACTTYPE")
    WebElement textBoxIExcludeRuleContractType;

    @FindBy(how = How.ID, using = "saveMaterialRequests")
    WebElement buttonSave;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-7 col-md-8 form-control-static ng-binding'])[1]")
    WebElement materialId;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//th[contains(text(),'Name')]")
    WebElement nameInTable;
  
    public NewMaterialRequest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToNewMaterialRequest() #Author : Tarkeshwar #Description : This is a common function to
     * navigate from "TMA" main menu to "New material request" sub menu #Date of creation :23-11-18 #Input Parameters: #Name of person modifying: Tarkeshwar
     * #Date of modification: 23-11-18
     ******************************************/
    public void navigateToNewMaterialRequest() {

        // To clear the search result each time refresh code is written
        keywords.waitTime(6);
        keywords.performSecondLevelMenuAction(driver, tmaMainMenu, materialRequestSubMenu, newMaterialRequestSubMenu, newMaterialRequestTitle);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : createNewMaterialRequestWarranty() #Author : Tarkeshwar #Description : This is a common function to
     * create a "New material request" sub menu #Date of creation :23-11-18 #Input Parameters: #Name of person modifying: Tarkeshwar #Date of modification:
     * 14-11-18
     ******************************************/

    public void createNewMaterialRequestWarranty(HashMap<String, Object> data) {
            softAssert = new SoftAssert();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(labelID));
            keywords.clickOnButton(driver, listRequesterName);
            wait.until(ExpectedConditions.visibilityOf(labelFilter));
            keywords.input(textBoxRequesterNameFiler, data.get("RequesterID"), "Filter");
            keywords.clickOnButton(driver, selectFilteredOption);
            keywords.clickOnButton(driver, closeDate);
            keywords.input(closeDate, DateUtility.getCurrentDate("dd/MM/yyyy"), "Close date");
            keywords.clickOnButton(driver, closeDate);
            keywords.waitTime1(2);
            keywords.selectVisibleText(dropDownMIName, appBasedUtils.getStringData(data, "MIName"));
            keywords.selectVisibleText(dropDownState, appBasedUtils.getStringData(data, "State"));
            keywords.input(textBoxPriority, data.get("Priority"), "Priority");
            if ((appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("WarrantyOnly")
                    || (appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("ContractOnly"))
                keywords.APP_LOGS.info("Type of concern selected value is" + keywords.getTextValue(typeOfConcernLabelValue));
            else if ((appBasedUtils.getStringData(data, "TypeOfConcern")).equalsIgnoreCase("Both"))
                keywords.selectVisibleText(dropDownTypeOfConcern, appBasedUtils.getStringData(data, "SelectTypeOfConcern"));
            keywords.selectVisibleText(dropDownCurrency, appBasedUtils.getStringData(data, "Currency"));
            keywords.input(textBoxLimitQuantity, data.get("LimitQuantity"), "Limit qty.");
            keywords.input(textBoxNotificationQuantity, data.get("NotificationQuantity"), "Notification qty.");
            keywords.input(textBoxRequestComment, data.get("RequestComment"), "Request comment");
            keywords.input(textBoxDescription, data.get("Description"), "Description");
            String firstXpath = "//label[text()='";
            String secondXpath = "']/../input";
            String brand = firstXpath + appBasedUtils.getStringData(data, "VehicleBrand") + secondXpath;
            if (!driver.findElement(By.xpath(brand)).isSelected()) {
                driver.findElement(By.xpath(brand)).click();
            }
            keywords.scrollPage(driver, 2000);
            String claimType = firstXpath + appBasedUtils.getStringData(data, "ClaimType") + secondXpath;
            if (!driver.findElement(By.xpath(claimType)).isSelected()) {
                driver.findElement(By.xpath(claimType)).click();
            }
            keywords.clickOnButton(driver, nameInTable);
            keywords.waitTime1(2);
            keywords.scrollPage(driver, 2000);
           
            keywords.waitTime1(2);
            keywords.setCheckboxIfNotSelected(checkBoxRuleApprovedNetCostTotal);
            keywords.input(textBoxIncludeRuleApprovedNetCostTotal, data.get("ApprovedNetCost(Total)"), "Approved Net Cost (Total)");
            keywords.waitTime1(2);
            keywords.setCheckboxIfNotSelected(checkBoxRuleMarketingType);
            keywords.input(textBoxIncludeRuleMarketingType, data.get("MarketingType"), "Marketing type");
            keywords.setCheckboxIfNotSelected(checkBoxRuleCausalPartNo);
            keywords.setCheckboxIfNotSelected(checkBoxRuleCausalPartNoPrefix);
            keywords.input(textBoxIncludeRuleCausalPartNo, data.get("CausalPartNo"), "Causal part no");
            keywords.input(textBoxIncludeRuleCausalPartPrefix, data.get("CausalPartPrefix"), "Causal part no. prefix");
            keywords.clickOnButton(driver, buttonSave);
            APP_LOGS.info("New Material Request has been saved successfully");
            keywords.scrollPage(driver, -1000);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.waitTime1(4);
            newMaterialId = keywords.getTextValue(materialId);
            APP_LOGS.info("New Material Request has been saved successfully, Id: " +  newMaterialId);
            ExtentTestManager.getTest().log(Status.INFO, "New Material Request has been saved successfully");
    }

}
