package com.volvo.in.page.campaign;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.claim.NewClaimJob;

public class CreateCampaign {

    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction equipment";
    private static final String TSM_AMERICAS = "TSM Americas";
    public String campaignIdNumberValue;
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    ManageClaimJobs manageClaimjobs = new ManageClaimJobs(driver);
    NewClaimJob claimJob = new NewClaimJob(driver);
    BaseClass baseclass = new BaseClass();
    public Logger APP_LOGS = Logger.getLogger(CreateCampaign.class);

    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle' and text()='Campaign '])[1]")
    WebElement campaignMainMenu;

    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle' and text()='Campaign '])[2]")
    WebElement campaignSubMenu;

    @FindBy(how = How.XPATH, using = "//a[text()='New campaign ']")
    WebElement newCampaignSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[@class='page-title ng-binding']")
    WebElement campaignPageTitle;

    @FindBy(how = How.XPATH, using = "//select[@name='localCampaign']")
    WebElement localCampaignList;

    @FindBy(how = How.ID, using = "campaignTitle")
    WebElement title;

    @FindBy(how = How.XPATH, using = "//select[@class='form-control ng-pristine ng-untouched ng-scope ng-empty ng-invalid ng-invalid-required']")
    WebElement sccType;

    @FindBy(how = How.XPATH, using = "//select[@name='campaignCurrency']")
    WebElement currency;

    @FindBy(how = How.XPATH, using = "//select[@id='brand']")
    WebElement brand;

    @FindBy(how = How.NAME, using = "campaignResponsible")
    WebElement campaignResponsible;

    @FindBy(how = How.XPATH, using = "//button[@uib-tooltip='Generate Next']")
    WebElement generateButton;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'comment_')]") })
    List<WebElement> progressComment;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindAll({ @FindBy(xpath = "//select[contains(@name,'campaignConditionState_')]") })
    List<WebElement> campaignConditionState;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'General')]")
    WebElement generalTab;

    @FindBy(how = How.NAME, using = "materialRegressCode")
    WebElement materialRegressCode;

    @FindBy(how = How.XPATH, using = "//select[(@ng-options='option for option in ctrl.reasonCodeList')]")
    WebElement reasonCode;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-3'])[3]/div")
    WebElement problemDescriptionAfterReleased;

    @FindBy(how = How.ID, using = "problemDescription")
    WebElement problemDescription;

    @FindBy(how = How.XPATH, using = "//select[@name='problemSolved']")
    WebElement problemSolved;

    @FindBy(how = How.XPATH, using = "//input[@name='problemSolvComment']")
    WebElement problemSolvComment;

    @FindBy(how = How.ID, using = "suggestedAction")
    WebElement suggestedAction;

    @FindBy(how = How.XPATH, using = "//select[@name='debitCode']")
    WebElement debitCode;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2']//div[@class='ng-binding ng-scope'])[2]")
    WebElement debitCodeAfterReleased;

    @FindBy(how = How.XPATH, using = "//div[@id='adminClosingDate']//input")
    WebElement adminClosingDate;

    @FindBy(how = How.XPATH, using = "//div[@id='lastRepairDate']//input")
    WebElement lastRepairDate;

    @FindBy(how = How.XPATH, using = "//div[@id='lastClaimSubmDate']//input")
    WebElement lastClaimSubmDate;

    @FindBy(how = How.XPATH, using = "//input[@id='vptContrLabourCost']")
    WebElement vptContrLabourCost;

    @FindBy(how = How.XPATH, using = "//input[@id='vptContrMtrlCost']")
    WebElement vptContrMtrlCost;

    @FindBy(how = How.XPATH, using = "//input[@id='vptContrOtherCost']")
    WebElement vptContrOtherCost;

    @FindBy(how = How.XPATH, using = "//input[@id='extraCost']")
    WebElement extraCost;

    @FindBy(how = How.XPATH, using = "//input[@id='extraCostDescription']")
    WebElement extraCostDescription;

    @FindBy(how = How.XPATH, using = "//input[@id='maxTravelExpenses']")
    WebElement travelExpensesLimit;

    @FindBy(how = How.XPATH, using = "//input[@value='true']")
    WebElement travelExpensesAllowed;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Vehicles')]")
    WebElement vehiclesTabForVTC;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Machines')]")
    WebElement machinesTabForVCE;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Drivelines')]")
    WebElement drivelinesTabForVPC;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Importer/Region')]")
    WebElement importerRegionTabForVTC;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Warranty Area/Region')]")
    WebElement warrantyAreaRegionTabForVCE;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Warranty District/Group')]")
    WebElement warrantyDistrictRegionTabForVPC;

    @FindBy(how = How.XPATH, using = "//select[@id='workWithList']")
    WebElement workWithVehicles;

    @FindBy(how = How.XPATH, using = "//input[@id='vehTypeMinMileage']")
    WebElement minMileage;

    @FindBy(how = How.XPATH, using = "//input[@id='vehTypeMaxMileage']")
    WebElement maxMileage;

    @FindBy(how = How.XPATH, using = "//input[@id='vehMinAge']")
    WebElement minAge;

    @FindBy(how = How.XPATH, using = "//input[@id='vehMaxAge']")
    WebElement maxAge;

    @FindBy(how = How.ID, using = "vehTypeMinOperationHours")
    WebElement minOperatingHours;

    @FindBy(how = How.ID, using = "vehTypeMaxOperationHours")
    WebElement maxOperatingHours;

    @FindBy(how = How.XPATH, using = "//input[@id='marketingType_0']")
    WebElement marketingType;

    @FindBy(how = How.XPATH, using = "//input[@id='chassisSeries_0']")
    WebElement chassisSeries;

    @FindBy(how = How.XPATH, using = "//input[@id='chassisNoFrom_0']")
    WebElement chassisNoFrom;

    @FindBy(how = How.XPATH, using = "//input[@id='chassisNoTo_0']")
    WebElement chassisNoTo;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-7 ng-scope']//label[@class='btn btn-xs btn-success btn-file ng-binding']")
    WebElement vehicleBrowseButton;

    @FindBy(how = How.ID, using = "selectionNo_0")
    WebElement selectionNumber;

    @FindBy(how = How.ID, using = "selectionParam_0")
    WebElement selectionParameters;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Campaign saved successfully.')]")
    WebElement saveSuccessMessage;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Ok')]")
    WebElement saveCampaignYesButton;

    @FindBy(how = How.NAME, using = "maxLabourHours")
    WebElement maxLabourHours;

    @FindBy(how = How.NAME, using = "operationNo_0")
    WebElement operationNo;

    @FindBy(how = How.NAME, using = "operationId_0")
    WebElement operationId;

    @FindBy(how = How.XPATH, using = "//table[@class='table table-bordered table-striped']//input[@name='quantity_0']")
    WebElement labourQunatity;

    @FindBy(how = How.NAME, using = "hours_0")
    WebElement labourHours;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.labourTabForm']//input[@name='vehicleFrequency_0']")
    WebElement labourVehicleFrequency;

    @FindBy(how = How.ID, using = "causalPartNoPrefix")
    WebElement causalPartNoPrefix;

    @FindBy(how = How.ID, using = "causalPartNo")
    WebElement causalPartNo;

    @FindBy(how = How.ID, using = "functionGroup")
    WebElement functionGroup;

    @FindBy(how = How.NAME, using = "defectCode")
    WebElement defectCode;

    @FindBy(how = How.XPATH, using = "//td[@class='ng-scope']/descendant::div[@class='ng-binding ng-scope']")
    WebElement defectCodeAfterReleased;

    @FindBy(how = How.ID, using = "materialPartNoPrefix_0")
    WebElement materialPartNoPrefix;

    @FindBy(how = How.ID, using = "materialPartNo")
    WebElement materialPartNo;

    @FindBy(how = How.ID, using = "quantity_0")
    WebElement quantity;

    @FindBy(how = How.ID, using = "vehicleFrequency_0")
    WebElement vehicleFrequecy;

    @FindBy(how = How.NAME, using = "maxMaterialCost")
    WebElement maxMaterialCost;

    @FindBy(how = How.NAME, using = "maxOtherCost")
    WebElement maxOtherCost;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.otherCostTabForm']//input[@name='description_0']")
    WebElement otherCostDescription;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.otherCostTabForm']//input[@name='vehicleFrequency_0']")
    WebElement otherCostVehicleFrequency;

    @FindBy(how = How.NAME, using = "orgCost_0")
    WebElement orgCost;

    @FindBy(how = How.ID, using = "importerNo_0")
    WebElement importerNo;

    @FindBy(how = How.ID, using = "noOfVehicles_0")
    WebElement noOfVehicles;

    @FindBy(how = How.XPATH, using = "//input[contains(@name,'fulfillment_0')]")
    WebElement fulfillment;

    String editablefulfillmentfirstXpath = "//input[contains(@name,'fulfillment_";
    String editablefulfillmentsecondXpath = "')]";

    String uneditablefulfillmentfirstXpath = "//table[@ng-if='ctrl.campaign.campaignImpRegionDTOList']/descendant::tr[";
    String uneditablefulfillmentsecondXpath = "]//td[4]/div";

    String noOfVehiclesfirstXpath = "//table[@ng-if='ctrl.campaign.campaignImpRegionDTOList']/descendant::tr[";
    String noOfVehiclessecondXpath = "]//td[3]/div";

    @FindBy(how = How.ID, using = "orderNumber")
    WebElement orderNumber;

    @FindBy(how = How.NAME, using = "tsuId_0")
    WebElement tsuId;

    @FindBy(how = How.XPATH, using = "//div[@id='issuedDate_0']//input")
    WebElement issuedDate;

    @FindBy(how = How.XPATH, using = "//div[@id='decisionDate_0']//input")
    WebElement decisionDate;

    @FindBy(how = How.XPATH, using = "//select[@ng-model='row.approved']")
    WebElement decision;

    @FindBy(how = How.NAME, using = "appliedAmount_0")
    WebElement appliedAmount;

    @FindBy(how = How.NAME, using = "approvedAmount_0")
    WebElement approvedAmount;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Part order')]")
    WebElement partOrder;

    @FindBy(how = How.NAME, using = "lanchQuantity_0")
    WebElement launchQuantity;

    @FindBy(how = How.NAME, using = "firstYearQuantity_0")
    WebElement firstYearQuantity;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Send order')]")
    WebElement sendOrder;

    @FindBy(how = How.XPATH, using = "//select[@id='campaignState']")
    WebElement campaignState;

    @FindBy(how = How.XPATH, using = "(//div[@class='text-left ui-grid-cell-contents ng-binding ng-scope'])[3]")
    WebElement campaignStateInHistory;

    @FindBy(how = How.XPATH, using = "//nav[@id='navbar-main']/ul/li/a[contains(text(),'Part orders')]")
    WebElement partOrdersMenu;

    @FindBy(how = How.XPATH, using = "//a[@href='/uchp/client/list-part-orders']")
    WebElement listPartOdersSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[text()='List part orders']")
    WebElement partOderTitleName;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Yes')]")
    WebElement partOrderClickOnYesButton;

    @FindBy(how = How.XPATH, using = "(//div[@class='text-left ui-grid-cell-contents ng-binding ng-scope'])[1]")
    WebElement firstRowInGrid;

    @FindBy(how = How.XPATH, using = "//input[@class='form-control']")
    WebElement plannedInStock;

    @FindBy(how = How.ID, using = "availableInStock")
    WebElement availableInStock;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Save')]")
    WebElement saveButtonInPartOrders;

    @FindBy(how = How.ID, using = "saveCampaign")
    WebElement campaignSave;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding'][contains(text(),'Material')]")
    WebElement materialTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Other cost')]")
    WebElement otherCostTab;

    @FindBy(how = How.XPATH, using = "//select[@id='region']")
    WebElement region;

    @FindBy(how = How.XPATH, using = "//a[@class='ng-binding ng-scope'][contains(text(),'Search campaign')]")
    WebElement searchCampaignMenu;

    @FindBy(how = How.XPATH, using = "//input[@id='sccCode']")
    WebElement sccCode;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'TSU')]")
    WebElement tsuTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Decision/History')]")
    WebElement decisionOrHistoryTab;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    WebElement searchBtn;

    @FindBy(how = How.ID, using = "status")
    WebElement status;

    @FindBy(how = How.XPATH, using = "//h4[text()='List campaigns']")
    WebElement listCampaigns;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Labour')]")
    WebElement labourTab;

    @FindBy(how = How.NAME, using = "validation")
    WebElement labourValidation;

    @FindBy(how = How.NAME, using = "vst")
    WebElement labourVST;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.labourTabForm']//input[@name='description_0']")
    WebElement labourDescription;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.ID, using = "availableInStock")
    WebElement availbleInStock;

    @FindBy(how = How.XPATH, using = "(//div[@class='campaign-header-value ng-binding ng-scope'])[2]")
    WebElement localCampaignVal;

    @FindBy(how = How.XPATH, using = "(//div[@class='campaign-header-value fieldValueOverflow ng-binding'])[2]")
    WebElement sccTypeVal;

    @FindBy(how = How.XPATH, using = "(//div[@class='campaign-header-value ng-binding ng-scope'])[3]")
    WebElement currencyVal;

    @FindBy(how = How.XPATH, using = "(//div[@class='campaign-header-value ng-binding ng-scope'])[1]")
    WebElement campaignOwnerVal;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-7 fieldValueCellalignment ng-binding ng-scope']")
    WebElement vehicleSelection;

    @FindBy(how = How.XPATH, using = "(//tr[@ng-repeat='row in ctrl.campaign.campaignImpRegionDTOList track by $index']/td)[1]")
    WebElement importerVal;

    @FindBy(how = How.XPATH, using = "(//tr[@ng-repeat='row in ctrl.campaign.campaignImpRegionDTOList track by $index']/td)[2]")
    WebElement regionVal;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.costList')]/child::td[1]/span") })
    List<WebElement> otherCostTabQuantitySavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.costList')]/child::td[2]/span") })
    List<WebElement> otherCostTabPriceSavedValue;

    @FindAll({ @FindBy(xpath = "//input[@name='operationType']") })
    List<WebElement> mainOperationRadioButton;

    @FindBy(how = How.ID, using = "campaignId")
    WebElement campaignId;

    @FindBy(how = How.XPATH, using = "//div[@class='campaign-header-value ng-binding']")
    WebElement campaignIdNumber;

    @FindBy(how = How.ID, using = "campaignTitle")
    WebElement campaignTitle;

    @FindBy(how = How.ID, using = "importerNo")
    WebElement importerNoSearch;

    @FindBy(how = How.ID, using = "make")
    WebElement brandSearch;

    @FindBy(how = How.ID, using = "scctypes")
    WebElement sccTypes;

    @FindBy(how = How.XPATH, using = "//input[@id='lastRepairDateStart']")
    WebElement lastRepairDateStart;
    
    @FindBy(how = How.XPATH, using = "//input[@id='lastRepairDateStart']/following-sibling::span")
    WebElement lastRepairDateStartIcon;

    @FindBy(how = How.ID, using = "lastRepairDateEnd")
    WebElement lastRepairDateEnd;

    @FindBy(how = How.XPATH, using = "//input[@id='lastRepairDateEnd']/following-sibling::span")
    WebElement lastRepairDateEndIcon;
    
    @FindBy(how = How.ID, using = "registrationDateStart")
    WebElement registrationDateStart;

    @FindBy(how = How.ID, using = "registrationDateEnd")
    WebElement registrationDateEnd;

    @FindBy(how = How.ID, using = "statusDateStart")
    WebElement statusDateStart;

    @FindBy(how = How.ID, using = "statusDateEnd")
    WebElement statusDateEnd;

    @FindBy(how = How.ID, using = "status")
    WebElement statusSearch;

    @FindBy(how = How.ID, using = "responsible")
    WebElement responsible;

    @FindBy(how = How.ID, using = "qj")
    WebElement qj;

    @FindBy(how = How.ID, using = "functionGroupStart")
    WebElement functionGroupStart;

    @FindBy(how = How.ID, using = "functionGroupEnd")
    WebElement functionGroupEnd;

    @FindBy(how = How.ID, using = "mainOperationStart")
    WebElement mainOperationStart;

    @FindBy(how = How.ID, using = "mainOperationEnd")
    WebElement mainOperationEnd;

    @FindBy(how = How.ID, using = "includedOperationStart")
    WebElement includedOperationStart;

    @FindBy(how = How.ID, using = "includedOperationEnd")
    WebElement includedOperationEnd;

    @FindBy(how = How.ID, using = "causalPartNoPrefix")
    WebElement causalPartNoPrefixSearch;

    @FindBy(how = How.ID, using = "causalPartNo")
    WebElement causalPartNoSearch;

    @FindBy(how = How.ID, using = "includedPartNoPrefix")
    WebElement includedPartNoPrefix;

    @FindBy(how = How.ID, using = "includedPartNo")
    WebElement includedPartNo;

    @FindBy(how = How.ID, using = "orderNumber")
    WebElement orderNumberSearch;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding'][contains(text(),'New')]")
    WebElement newTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Assigned')]")
    WebElement assignedTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'TSU Approval')]")
    WebElement tsuApprovalTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'TSU Approved')]")
    WebElement tsuApprovedTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Prepared')]")
    WebElement preparedTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Closed')]")
    WebElement closedTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Search result')]")
    WebElement searchResultTab;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-pager-count']//span[@class='ng-binding']")
    WebElement itemPerPage;

    String campaignIDInSearchResult;

    @FindBy(how = How.XPATH, using = "(//div[@class='text-left ui-grid-cell-contents ng-binding ng-scope'])[1]")
    WebElement campaignIdInFirstRow;

    @FindBy(how = How.XPATH, using = "//div[@class='ng-isolate-scope']/div[3]")
    WebElement titleInSearchResult;

    @FindBy(how = How.XPATH, using = "(//div[@class='ui-grid-cell-contents ng-binding ng-scope'])[1]")
    WebElement sccCodeInFristRow;

    @FindBy(how = How.XPATH, using = "//div[@class='row general-tab-row ng-scope']//a[@class='ng-binding'][contains(text(),'Edit')]")
    WebElement connectedQJ;

    @FindBy(how = How.XPATH, using = "//*[contains(@ng-if,'ctrl.campaign.qjsLink')]")
    WebElement qjLinkTextFromGeneralTab;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Connect')]")
    WebElement connect;

    @FindBy(how = How.XPATH, using = "//span[@aria-hidden='true']")
    WebElement closeEditQJConnections;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='ctrl.historyBack()']")
    WebElement clickOnCloseButtonHistory;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.listCampaignForm.mainOperationId']")
    WebElement operationIDCheckBox;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'operationId_')]") })
    List<WebElement> operationIdList;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'operationNo_')]") })
    List<WebElement> operationNoList;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.listCampaignForm.mainOperationId']")
    WebElement operationIdCheckBox;

    @FindBy(how = How.ID, using = "currentId")
    WebElement connectQJ;

    @FindAll({ @FindBy(xpath = "//table[@ng-if='ctrl.campaign.campaignImpRegionDTOList']/tbody/tr") })
    List<WebElement> importerTabTotalRows;
    
    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedCampaign(row)'])[6]")
    WebElement statusInsearchResultGrid;
    
    String sccCodeGenerated;

    SoftAssert creareCampaignAssert;

    SoftAssert searchCampaign;

    String lastRepairDateFromApp;
    
    String campaignStatus;
    
    String statusFromGridAfterSearch;
    
    @FindBy(how = How.XPATH, using = "//div[div[label[text()='Last repair date']]]/div[4]")
    WebElement repairDateFromCampaign;
    
    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'row in ctrl.campaign.causalPartList track by')]/td[6]")
    WebElement functionGroupNonEditable;
  
    @FindBy(how = How.XPATH, using = "(//td[@class='importer-region-width']/div[@class='ng-scope']/a[@class='ng-binding'])[1]")
    WebElement labourCostInImporterRegionTab;
    
    @FindBy(how = How.XPATH, using = "(//td[@class='importer-region-width']/div[@class='ng-scope']/a[@class='ng-binding'])[2]")
    WebElement materialCostInImporterRegionTab;
    
    @FindBy(how = How.XPATH, using = "(//td[@class='importer-region-width']/div[@class='ng-scope']/a[@class='ng-binding'])[3]")
    WebElement otherCostInImporterRegionTab;
    
    @FindBy(how = How.XPATH, using = "div[@class='modal-title-container']")
    WebElement costOrImporterPopUp;
    
    @FindBy(how = How.XPATH, using = "//input[@id='labourRate']")
    WebElement labourRateInCostOrImporterPopUp;
    
    @FindBy(how = How.XPATH, using = "//*[@name='costImporterRegion']//a[contains(text(),'Material')]")
    WebElement materialTabInCostOrImporterPopup;
    
    @FindBy(how = How.XPATH, using = "//input[@id='unitPartPrice_0']")
    WebElement materialPriceInCostOrImporterPopup;
    
    @FindBy(how =  How.XPATH, using = "//*[@name='costImporterRegion']//a[contains(text(),'Other cost')]")
    WebElement otherCostTabInCostOrimporterPopup;
    
    @FindBy(how = How.XPATH, using = "//input[@id='cost_0']")
    WebElement otherCostInCostOrImporterPoup;
    
    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs btn-primary text-left ng-binding ng-scope']")
    WebElement okButtonInCostOrImporterPoup;

    
    
    
    
    
    
    
  

    public CreateCampaign(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /**************************************
     * #Method Name: navigateToNewCampaign Date of creation : 29-11-2018 #Input Parameters: NA
     */
    public void navigateToNewCampaign() {
        keywords.performSecondLevelMenuAction(driver, campaignMainMenu, campaignSubMenu, newCampaignSubMenu, campaignPageTitle);
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(campaignPageTitle));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /**************************************
     * #Method Name: navigateToSearchCampaign Date of creation : 29-11-2018 #Input Parameters: NA
     */
    public void navigateToSearchCampaign() {
        keywords.performSecondLevelMenuAction(driver, campaignMainMenu, campaignSubMenu, searchCampaignMenu, listCampaigns);
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(listCampaigns));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /**************************************
     * #Method Name: navigateToPartOderMenu Date of creation : 29-11-2018 #Input Parameters: NA
     */
    public void navigateToPartOderMenu() {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.performMenuAction(driver, partOrdersMenu, listPartOdersSubMenu, partOderTitleName);
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(partOderTitleName));
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
    }

    /******************************
     * #Project Name : UCHP - Automation #Method Name : campaignHeaderSave #Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     ******************************/
    public void setCampaignHeaderDetails(HashMap<String, Object> campaignData) {
        try {
            keywords.selectVisibleText(localCampaignList, appBasedUtils.getStringData(campaignData, "LocalCampaign"));
            keywords.selectVisibleText(sccType, appBasedUtils.getStringData(campaignData, "SCCType"));
            keywords.selectVisibleText(brand, appBasedUtils.getStringData(campaignData, "Brand"));
            keywords.selectVisibleText(currency, appBasedUtils.getStringData(campaignData, "Currency"));
            keywords.input(title, appBasedUtils.getStringData(campaignData, "Title"), "Title");
            keywords.input(campaignResponsible, appBasedUtils.getStringData(campaignData, "Responsible"), "Responsible");
            keywords.clickOnButton(driver, generateButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            sccCodeGenerated = keywords.getAttributeValue(sccCode, "value");
            campaignData.put("SccCode", sccCodeGenerated);
            ExtentTestManager.getTest().log(Status.INFO, "Campaign scc code generated: " + sccCodeGenerated);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /******************************
     * #Method Name : campaignHeaderSave #Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     ******************************/
    public void campaignProgressTab(HashMap<String, Object> campaignData) {
        try {
            keywords.input(progressComment.get(0), appBasedUtils.getStringData(campaignData, "MaterialOder"), "MaterialOder");
            keywords.input(progressComment.get(1), appBasedUtils.getStringData(campaignData, "MainTSU"), "MainTSU");
            if (!(campaignConditionState.isEmpty())) {
                for (int i = 0; i < campaignConditionState.size(); i++) {
                    keywords.selectVisibleText(campaignConditionState.get(i), "NA");
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: GeneralTab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignGeneralTab(HashMap<String, Object> campaignData) {
        try {
            keywords.scrollPage(driver, -250);
            keywords.clickOnButton(driver, generalTab);
            keywords.input(materialRegressCode, appBasedUtils.getStringData(campaignData, "MaterialRegressCode"), "MaterialRegressCode");
            keywords.selectVisibleText(reasonCode, appBasedUtils.getStringData(campaignData, "ReasonCode"));
            keywords.input(problemDescription, appBasedUtils.getStringData(campaignData, "ProblemDescription"), "ProblemDescription");
            keywords.clickOnLink(connectedQJ, driver);
            keywords.waitTime(2);
            keywords.input(connectQJ, appBasedUtils.getStringData(campaignData, "ConnectQJ"), " set ConnectQJ value ");
            keywords.clickOnButton(driver, connect);
            keywords.clickOnButton(driver, closeEditQJConnections);
            keywords.input(suggestedAction, appBasedUtils.getStringData(campaignData, "SuggestedSolution"), "SuggestedSolution");
            keywords.selectVisibleText(problemSolved, appBasedUtils.getStringData(campaignData, "SolvedInProduction"));
            keywords.input(problemSolvComment, appBasedUtils.getStringData(campaignData, "SolvedInProductionText"), "SolvedInProductionText");
            keywords.selectVisibleText(debitCode, appBasedUtils.getStringData(campaignData, "DebitCode"));
            keywords.input(adminClosingDate, appBasedUtils.getStringData(campaignData, "AdminClosingDate"), "AdminClosingDate");
            keywords.input(lastRepairDate, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "LastRepairDate");
            keywords.input(lastClaimSubmDate, appBasedUtils.getStringData(campaignData, "LastSubmissionDate"), "lastClaimSubmDate");
            keywords.input(vptContrLabourCost, appBasedUtils.getStringData(campaignData, "SupplierContributionLabourCost"), "SupplierContributionLabourCost");
            keywords.input(vptContrMtrlCost, appBasedUtils.getStringData(campaignData, "SupplierContributionMaterialCost"), "SupplierContributionMaterialCost");
            keywords.scrollPage(driver, -550);
            keywords.waitTime1(2);
            keywords.input(vptContrOtherCost, appBasedUtils.getStringData(campaignData, "SupplierContributionOtherCost"), "SupplierContributionOtherCost");
            keywords.input(extraCost, appBasedUtils.getStringData(campaignData, "ExtraCost"), "ExtraCost");
            keywords.input(extraCostDescription, appBasedUtils.getStringData(campaignData, "ExtraCostDescription"), "ExtraCostDescription");
            if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_PENTA_CORPORATION)
                    || keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                keywords.clickOnButton(driver, travelExpensesAllowed);
                keywords.input(travelExpensesLimit, appBasedUtils.getStringData(campaignData, "TravelExpenseLimit"), "TravelExpenseLimit");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Vehicletab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignVehicleTab(HashMap<String, Object> campaignData) {
        try {
            keywords.scrollPage(driver, -250);
            if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT))
                keywords.clickOnButton(driver, machinesTabForVCE);

            else if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_PENTA_CORPORATION))
                keywords.clickOnButton(driver, drivelinesTabForVPC);
            else {
                keywords.clickOnButton(driver, vehiclesTabForVTC);
            }
            if (appBasedUtils.getStringData(campaignData, "VehicleWorkWith").equalsIgnoreCase("Concerned vehicles")) {
                keywords.selectVisibleText(workWithVehicles, appBasedUtils.getStringData(campaignData, "VehicleWorkWith"));
                keywords.input(minMileage, appBasedUtils.getStringData(campaignData, "MinMileage"), "minMileage");
                keywords.input(maxMileage, appBasedUtils.getStringData(campaignData, "MaxMileage"), "maxMileage");
                keywords.input(minAge, appBasedUtils.getStringData(campaignData, "MinAge"), "minAge");
                keywords.input(maxAge, appBasedUtils.getStringData(campaignData, "MaxAge"), "maxAge");
                keywords.input(minOperatingHours, appBasedUtils.getStringData(campaignData, "MinOperatingHours"), "minOperatingHours");
                keywords.input(maxOperatingHours, appBasedUtils.getStringData(campaignData, "MaxOperatingHours"), "maxOperatingHours");
                keywords.input(marketingType, appBasedUtils.getStringData(campaignData, "MarketingType"), "marketingType");
                keywords.input(chassisSeries, appBasedUtils.getStringData(campaignData, "ChassisSeries"), "chassisSeries");
                keywords.input(chassisNoFrom, appBasedUtils.getStringData(campaignData, "ChassisNumberFrom"), "chassisNoFrom");
                keywords.input(chassisNoTo, appBasedUtils.getStringData(campaignData, "ChassisNumberTo"), "chassisNoTo");
                keywords.clickOnButton(driver, campaignSave);
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            } else {
                campaignIdNumberValue = keywords.getTextValue(campaignIdNumber);
                keywords.selectVisibleText(workWithVehicles, appBasedUtils.getStringData(campaignData, "VehicleWorkWith"));
                PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
                String sPathSep = System.getProperty("file.separator");
                String document = appBasedUtils.getStringData(campaignData, "VehicleSelectionAttachment".trim());
                keywords.waitForVisibilityOfElement(driver, 20, vehicleBrowseButton);
                driver.switchTo().defaultContent();
                keywords.waitTime1(2);
                File documentLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "Attachments" + sPathSep + document);
                keywords.waitTime1(2);
                keywords.clickOnLink(vehicleBrowseButton, driver);
                keywords.waitTime1(2);
                pdfReadAndValidate.uploadDocuments(baseclass.getBrowserType(), documentLocation);
                keywords.waitTime(200);
                keywords.input(minAge, appBasedUtils.getStringData(campaignData, "MinAge"), "minAge");
                keywords.input(maxAge, appBasedUtils.getStringData(campaignData, "MaxAge"), "maxAge");
                keywords.input(selectionNumber, appBasedUtils.getStringData(campaignData, "SelectionNumber"), "SelectionNumber");
                keywords.input(selectionParameters, appBasedUtils.getStringData(campaignData, "SelectionParameters"), "SelectionParameters");
                keywords.clickOnButton(driver, campaignSave);
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
                keywords.handleModalDailogues(driver);
                keywords.waitForVisibilityOfElement(driver, 25, saveSuccessMessage);
                keywords.waitTime1(4);
                keywords.clickOnButton(driver, saveSuccessMessage);
                keywords.clickOnButton(driver, saveCampaignYesButton);
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Vehicletab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignLabourTab(HashMap<String, Object> campaignData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.clickOnButton(driver, labourTab);
            boolean isValidateSelected = appBasedUtils.getStringData(campaignData, "Validate").equalsIgnoreCase("Yes");
            boolean isVSTSelected = appBasedUtils.getStringData(campaignData, "VST").equalsIgnoreCase("Yes");
            if (isValidateSelected && isVSTSelected) {
                keywords.setCheckboxIfNotSelected(labourValidation);
                keywords.setCheckboxIfNotSelected(labourVST);
                if (appBasedUtils.getStringData(campaignData, "SaveWithOperationNo").equalsIgnoreCase("Yes")) {
                    keywords.input(operationNo, appBasedUtils.getStringData(campaignData, "OperationNumberLabour"), "OperationNo");
                } else {
                    keywords.input(operationId, appBasedUtils.getStringData(campaignData, "OperationId"), "OperationId");
                }
            } else if (isValidateSelected && !isVSTSelected) {
                keywords.setCheckboxIfNotSelected(labourValidation);
                if ((keywords.isCheckboxElementSelected(labourVST))) {
                    keywords.clickOnButton(driver, labourVST);
                    keywords.input(operationNo, appBasedUtils.getStringData(campaignData, "OperationNumberLabour"), "OperationNo");
                    keywords.input(operationId, appBasedUtils.getStringData(campaignData, "OperationId"), "OperationId");
                    keywords.input(labourDescription, appBasedUtils.getStringData(campaignData, "OperationDescLabourTab"), "LabourDescription");
                }
            } else if (!isValidateSelected && isVSTSelected) {
                keywords.setCheckboxIfNotSelected(labourVST);
                if ((keywords.isCheckboxElementSelected(labourValidation))) {
                    keywords.clickOnButton(driver, labourValidation);
                    keywords.input(operationNo, appBasedUtils.getStringData(campaignData, "OperationNumberLabour"), "OperationNo");
                    keywords.input(operationId, appBasedUtils.getStringData(campaignData, "OperationId"), "OperationId");
                    keywords.input(labourDescription, appBasedUtils.getStringData(campaignData, "OperationDescLabourTab"), "LabourDescription");
                }
            } else {
                keywords.clickOnButton(driver, labourValidation);
                keywords.clickOnButton(driver, labourVST);
                keywords.input(operationNo, appBasedUtils.getStringData(campaignData, "OperationNumberLabour"), "OperationNo");
                keywords.input(operationId, appBasedUtils.getStringData(campaignData, "OperationId"), "OperationId");
                keywords.input(labourDescription, appBasedUtils.getStringData(campaignData, "OperationDescLabourTab"), "LabourDescription");
            }

            keywords.input(maxLabourHours, appBasedUtils.getStringData(campaignData, "LabourMaxLaboursHours"), "LabourMaxLaboursHours");
            keywords.input(labourQunatity, appBasedUtils.getStringData(campaignData, "LabourQuantity"), "LabourQuantity");
            keywords.input(labourHours, appBasedUtils.getStringData(campaignData, "LabourHours"), "LabourHours");
            keywords.input(labourVehicleFrequency, appBasedUtils.getStringData(campaignData, "labourVehicleFrequency"), "labourvehicleFrequency");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Material tab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignMaterialTab(HashMap<String, Object> campaignData) {
        try {
            keywords.scrollPage(driver, -250);
            keywords.clickOnButton(driver, materialTab);
            keywords.input(materialPartNoPrefix, appBasedUtils.getStringData(campaignData, "ReplacedPartPrefix"), "Prefix");
            keywords.input(materialPartNo, appBasedUtils.getStringData(campaignData, "ReplacedPartNo"), "PartNumber");
            keywords.input(functionGroup, appBasedUtils.getStringData(campaignData, "FunctionGroup"), "FunctionGroup");
            keywords.input(defectCode, appBasedUtils.getStringData(campaignData, "DefectCode"), "DefectCode");
            keywords.input(maxMaterialCost, appBasedUtils.getStringData(campaignData, "MaxMaterialCostForVehicle"), "MaxMaterialCostForVehicle");
            keywords.input(causalPartNoPrefix, appBasedUtils.getStringData(campaignData, "CausalPartPrefix"), "IncludedMaterialPrefix");
            keywords.input(causalPartNo, appBasedUtils.getStringData(campaignData, "CausalPartNo"), "IncludedMaterialPartNumber");
            keywords.input(quantity, appBasedUtils.getStringData(campaignData, "MaterialQuantity"), "Qunatity");
            keywords.input(vehicleFrequecy, appBasedUtils.getStringData(campaignData, "MachineFreq"), "MachineFreq");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Material tab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignOtherCostTab(HashMap<String, Object> campaignData) {
        try {
            keywords.clickOnButton(driver, otherCostTab);
            keywords.input(maxOtherCost, appBasedUtils.getStringData(campaignData, "MaxOtherCost"), "MaxOtherCost");
            keywords.input(otherCostDescription, appBasedUtils.getStringData(campaignData, "DescriptionOtherCost"), "OtherCostDesc");
            keywords.input(otherCostVehicleFrequency, appBasedUtils.getStringData(campaignData, "OtherCostVehicleFreq"), "OtherCostVehicleFreq");
            keywords.input(orgCost, appBasedUtils.getStringData(campaignData, "OtherCost_Cost"), "OtherCost_Cost");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Importer tab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignImporterTab(HashMap<String, Object> campaignData) {
        try {
            if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                keywords.clickOnButton(driver, warrantyAreaRegionTabForVCE);
            } else if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
                keywords.clickOnButton(driver, warrantyDistrictRegionTabForVPC);
            } else {
                keywords.clickOnButton(driver, importerRegionTabForVTC);
            }
            if (appBasedUtils.getStringData(campaignData, "VehicleWorkWith").equalsIgnoreCase("Concerned vehicles")) {
                if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(campaignData, "Importer"))) {
                    keywords.input(importerNo, appBasedUtils.getStringData(campaignData, "Importer"), "Importer");
                } else {
                    keywords.selectVisibleText(region, appBasedUtils.getStringData(campaignData, "ImporterRegion"));
                }
                keywords.input(noOfVehicles, appBasedUtils.getStringData(campaignData, "TotalVehiclesInImporter"), "TotalVehiclesInImporter");
            }
            String operationNumbers[] = appBasedUtils.getStringData(campaignData, "Fullfillment").split(";");
            for (int i = 0; i < importerTabTotalRows.size(); i++) {
                keywords.input(driver.findElement(By.xpath(editablefulfillmentfirstXpath + i + editablefulfillmentsecondXpath)), operationNumbers[i],
                               "Fullfillment");
            }

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: TSU tab Date of creation : 29-11-2018 #Input Parameters: #Name of person modifying
     */
    public void campaignTSUTab(HashMap<String, Object> campaignData) {
        keywords.clickOnButton(driver, tsuTab);
        keywords.input(orderNumber, appBasedUtils.getStringData(campaignData, "TSU_ClaimAcctOrderNo"), "TSU_ClaimAcctOrderNo");
        keywords.input(tsuId, appBasedUtils.getStringData(campaignData, "TSUID"), "TSUID");
        keywords.input(issuedDate, appBasedUtils.getStringData(campaignData, "IssueDate"), "IssueDate");
        keywords.clickOnButton(driver, issuedDate);
        keywords.clickOnButton(driver, issuedDate);
        keywords.keyEnter(issuedDate);
        keywords.clickOnButton(driver, tsuTab);
        keywords.input(issuedDate, appBasedUtils.getStringData(campaignData, "IssueDate"), "IssueDate");
        keywords.clickOnButton(driver, issuedDate);
        keywords.keyEnter(issuedDate);
        keywords.clickOnButton(driver, tsuTab);
        keywords.input(decisionDate, appBasedUtils.getStringData(campaignData, "DecisionDate"), "DecisionDate");
        keywords.clickOnButton(driver, decisionDate);
        keywords.clickOnButton(driver, decisionDate);
        keywords.keyEnter(decisionDate);
        keywords.clickOnButton(driver, tsuTab);
        keywords.input(decisionDate, appBasedUtils.getStringData(campaignData, "DecisionDate"), "DecisionDate");
        keywords.clickOnButton(driver, decisionDate);
        keywords.keyEnter(decisionDate);
        keywords.clickOnButton(driver, tsuTab);
        keywords.selectVisibleText(decision, appBasedUtils.getStringData(campaignData, "Decision"));
        keywords.input(appliedAmount, appBasedUtils.getStringData(campaignData, "AppliedAmt"), "AppliedAmt");
        keywords.input(approvedAmount, appBasedUtils.getStringData(campaignData, "ApprovedAmt"), "ApprovedAmt");
        keywords.clickOnButton(driver, campaignSave);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
    }

    /**************************************
     * #Method Name: Search with SCC code
     */
    public void campaignSearchWithSccCode(HashMap<String, Object> campaignData) {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        keywords.input(sccCode, appBasedUtils.getStringData(campaignData, "SccCode"), "sccCode");
    }

    /**************************************
     * #Method Name: Search with Status
     */
    public void campaignSearchWithStatus(HashMap<String, Object> campaignData) {
        keywords.selectVisibleText(status, appBasedUtils.getStringData(campaignData, "DescisionHistory"));
    }

    /**************************************
     * #Method Name: click on SearchButton
     */
    public void search() {
        try {
            keywords.clickOnButton(driver, searchBtn);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 7, loadingIcon);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: select first row from grid
     */
    public void selectFirstRow() {
        try {
            keywords.clickOnButton(driver, firstRowInGrid);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: SetCampaignStatus as assigned
     */
    public void setCampaignStatusAsAssigned(HashMap<String, Object> campaignData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.clickOnButton(driver, decisionOrHistoryTab);
            keywords.selectVisibleText(campaignState, appBasedUtils.getStringData(campaignData, "DescisionHistory"));
            keywords.clickOnButton(driver, campaignSave);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.checkVisibilityOfElementErrorMessage(driver, 2, errorMsgElement, validationMessageClose);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: SetCampaignStatus as TSU approval
     */
    public void setCampaignStatusAsApproval(HashMap<String, Object> campaignData) {
        try {
            keywords.clickOnButton(driver, decisionOrHistoryTab);
            keywords.selectVisibleText(campaignState, appBasedUtils.getStringData(campaignData, "DecisionHistoryForTSUApproval"));
            keywords.clickOnButton(driver, campaignSave);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.checkVisibilityOfElementErrorMessage(driver, 2, errorMsgElement, validationMessageClose);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: SetCampaignStatus as approved
     */
    public void setCampaignStatusAsApproved(HashMap<String, Object> campaignData) {
        try {
            keywords.clickOnButton(driver, decisionOrHistoryTab);
            keywords.selectVisibleText(campaignState, appBasedUtils.getStringData(campaignData, "DecisionHistoryForApproved"));
            keywords.clickOnButton(driver, campaignSave);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.checkVisibilityOfElementErrorMessage(driver, 2, errorMsgElement, validationMessageClose);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: set part order for approval
     */
    public void setPartOrder(HashMap<String, Object> campaignData) {
        keywords.scrollPage(driver, 250);
        keywords.clickOnButton(driver, partOrder);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        keywords.input(launchQuantity, appBasedUtils.getStringData(campaignData, "LaunchQuantity"), "LaunchQuantity");
        keywords.input(firstYearQuantity, appBasedUtils.getStringData(campaignData, "FirstYearQuantity"), "FirstYearQuantity");
        keywords.clickOnButton(driver, sendOrder);
        keywords.clickOnButton(driver, partOrderClickOnYesButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
        keywords.checkVisibilityOfElementErrorMessage(driver, 4, errorMsgElement, validationMessageClose);
    }

    /**************************************
     * #Method Name: SetCampaignStatus as prepared
     */
    public void setCampaignStatusAsPrepared(HashMap<String, Object> campaignData) {
        keywords.clickOnButton(driver, decisionOrHistoryTab);
        keywords.selectVisibleText(campaignState, appBasedUtils.getStringData(campaignData, "DecisionHistoryForPrepared"));
        keywords.clickOnButton(driver, campaignSave);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
        keywords.checkVisibilityOfElementErrorMessage(driver, 2, errorMsgElement, validationMessageClose);
    }

    /**************************************
     * #Method Name: SetCampaignStatus as released
     */
    public void setCampaignStatusAsReleased(HashMap<String, Object> campaignData) {
        keywords.clickOnButton(driver, decisionOrHistoryTab);
        keywords.selectVisibleText(campaignState, appBasedUtils.getStringData(campaignData, "DecisionHistoryForReleased"));
        keywords.clickOnButton(driver, campaignSave);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
        keywords.checkVisibilityOfElementErrorMessage(driver, 2, errorMsgElement, validationMessageClose);
    }

    /**************************************
     * #Method Name: save part order
     */
    public void partOrderSave(HashMap<String, Object> campaignData) {
        try {

            String firstXpath = "//div[contains(text(),'";
            String secondXpath = "')]";
            String sccCodeInGrid = firstXpath + appBasedUtils.getStringData(campaignData, "SccCode") + secondXpath;
            keywords.waitTime1(2);
            driver.findElement(By.xpath(sccCodeInGrid)).click();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.clickOnButton(driver, availbleInStock);
            if (keywords.elementIsEnabled(availableInStock)) {
                keywords.waitTime1(2);
                keywords.clickOnButton(driver, saveButtonInPartOrders);
            } else {
                Assert.fail("saveButtonInPartOrders button not enabled");
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.checkVisibilityOfElementErrorMessage(driver, 2, errorMsgElement, validationMessageClose);
            ExtentTestManager.getTest().log(Status.INFO, "Parts are approved");
            keywords.waitTime1(4);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign header details
     */
    public void validateHeaderTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            appBasedUtils.asserVerification("localCampaign", appBasedUtils.getStringData(campaignData, "LocalCampaign"),
                                            keywords.getTextValue(localCampaignVal).trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("sscType", appBasedUtils.getStringData(campaignData, "SCCType"), keywords.getTextValue(sccTypeVal).trim(),
                                            creareCampaignAssert);
            appBasedUtils.asserVerification("currency", appBasedUtils.getStringData(campaignData, "Currency"), keywords.getTextValue(currencyVal).trim(),
                                            creareCampaignAssert);
            appBasedUtils.asserVerification("brand", appBasedUtils.getStringData(campaignData, ""), keywords.getFirstSelectedItemFromDropDown(brand),
                                            creareCampaignAssert);
            appBasedUtils.asserVerification("campaignOwner", appBasedUtils.getStringData(campaignData, "CampaignOwner"),
                                            keywords.getTextValue(campaignOwnerVal).trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("title", appBasedUtils.getStringData(campaignData, "Title"), keywords.getAttributeValue(title, "value").trim(),
                                            creareCampaignAssert);
            appBasedUtils.asserVerification("campaignResponsible", appBasedUtils.getStringData(campaignData, "Responsible"),
                                            keywords.getAttributeValue(campaignResponsible, "value"), creareCampaignAssert);
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign general tab
     */
    public void validateGeneralTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            keywords.clickOnButton(driver, generalTab);

            appBasedUtils.asserVerification("materialRegressCode", appBasedUtils.getStringData(campaignData, "MaterialRegressCode"),
                                            keywords.getAttributeValue(materialRegressCode, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("reasonCode", appBasedUtils.getStringData(campaignData, "ReasonCode"),
                                            keywords.getFirstSelectedItemFromDropDown(reasonCode), creareCampaignAssert);
            appBasedUtils.asserVerification("problemDescription", appBasedUtils.getStringData(campaignData, "ProblemDescription"),
                                            keywords.getTextValue(problemDescriptionAfterReleased).trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("suggestedAction", appBasedUtils.getStringData(campaignData, "SuggestedSolution"),
                                            keywords.getAttributeValue(suggestedAction, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("problemSolved", appBasedUtils.getStringData(campaignData, "SolvedInProduction"),
                                            keywords.getFirstSelectedItemFromDropDown(problemSolved), creareCampaignAssert);
            keywords.scrollPage(driver, 250);
            appBasedUtils.asserVerification("problemDescription", appBasedUtils.getStringData(campaignData, "ProblemDescription"),
                                            keywords.getTextValue(problemDescriptionAfterReleased).trim(), creareCampaignAssert);

            appBasedUtils.asserVerification("debitCode", appBasedUtils.getStringData(campaignData, "DebitCode"), keywords.getTextValue(debitCodeAfterReleased),
                                            creareCampaignAssert);
            appBasedUtils.asserVerification("lastRepairDate", DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT),
                                            keywords.getAttributeValue(lastRepairDate, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("adminClosingDate", appBasedUtils.getStringData(campaignData, "AdminClosingDate"),
                                            keywords.getAttributeValue(adminClosingDate, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("lastClaimSubmDate", appBasedUtils.getStringData(campaignData, "LastSubmissionDate"),
                                            keywords.getAttributeValue(lastClaimSubmDate, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("vptContrLabourCost", appBasedUtils.getStringData(campaignData, "SupplierContributionLabourCost"),
                                            keywords.getAttributeValue(vptContrLabourCost, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("vptContrMtrlCost", appBasedUtils.getStringData(campaignData, "SupplierContributionMaterialCost"),
                                            keywords.getAttributeValue(vptContrMtrlCost, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("vptContrOtherCost", appBasedUtils.getStringData(campaignData, "SupplierContributionOtherCost"),
                                            keywords.getAttributeValue(vptContrOtherCost, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("extraCost", appBasedUtils.getStringData(campaignData, "ExtraCost"),
                                            keywords.getAttributeValue(extraCost, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("extraCostDescription", appBasedUtils.getStringData(campaignData, "ExtraCostDescription"),
                                            keywords.getAttributeValue(extraCostDescription, "value").trim(), creareCampaignAssert);
            if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_PENTA_CORPORATION)
                    || keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                if ((keywords.isCheckboxElementSelected(travelExpensesAllowed))) {
                    appBasedUtils.asserVerification("TravelExpenseLimit", appBasedUtils.getStringData(campaignData, "TravelExpenseLimit"),
                                                    keywords.getAttributeValue(travelExpensesLimit, "value").trim(), creareCampaignAssert);
                }
            }
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign vehicle tab details
     */
    public void validateVehicleTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            keywords.scrollPage(driver, -250);
            if (keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                keywords.clickOnButton(driver, machinesTabForVCE);
            } else if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
                keywords.clickOnButton(driver, drivelinesTabForVPC);
            } else {
                keywords.clickOnButton(driver, vehiclesTabForVTC);
            }
            appBasedUtils.asserVerification("vehicleSelection", appBasedUtils.getStringData(campaignData, "VehicleWorkWith"),
                                            keywords.getTextValue(vehicleSelection).trim(), creareCampaignAssert);
            if (keywords.getTextValue(vehicleSelection).trim().contains("Concerned")) {
                appBasedUtils.asserVerification("minMileage", appBasedUtils.getStringData(campaignData, "MinMileage"),
                                                keywords.getAttributeValue(minMileage, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("maxMileage", appBasedUtils.getStringData(campaignData, "MaxMileage"),
                                                keywords.getAttributeValue(maxMileage, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("minAge", appBasedUtils.getStringData(campaignData, "MinAge"),
                                                keywords.getAttributeValue(minAge, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("maxAge", appBasedUtils.getStringData(campaignData, "MaxAge"),
                                                keywords.getAttributeValue(maxAge, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("minOperatingHours", appBasedUtils.getStringData(campaignData, "MinOperatingHours"),
                                                keywords.getAttributeValue(minOperatingHours, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("maxOperatingHours", appBasedUtils.getStringData(campaignData, "MaxOperatingHours"),
                                                keywords.getAttributeValue(maxOperatingHours, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("marketingType", appBasedUtils.getStringData(campaignData, "MarketingType"),
                                                keywords.getAttributeValue(marketingType, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("chassisSeries", appBasedUtils.getStringData(campaignData, "ChassisSeries"),
                                                keywords.getAttributeValue(chassisSeries, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("chassisNoFrom", appBasedUtils.getStringData(campaignData, "ChassisNumberFrom"),
                                                keywords.getAttributeValue(chassisNoFrom, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("chassisNoTo", appBasedUtils.getStringData(campaignData, "ChassisNumberTo"),
                                                keywords.getAttributeValue(chassisNoTo, "value").trim(), creareCampaignAssert);
            }
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign labour tab details
     */
    public void validateLabourTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            keywords.clickOnButton(driver, labourTab);
            appBasedUtils.asserVerification("maxLabourHours", appBasedUtils.getStringData(campaignData, "LabourMaxLaboursHours"),
                                            keywords.getAttributeValue(maxLabourHours, "value").trim(), creareCampaignAssert);

            if ((appBasedUtils.getStringData(campaignData, "SaveWithOperationNo")).equalsIgnoreCase("Yes")) {
                appBasedUtils.asserVerification("operationNo", appBasedUtils.getStringData(campaignData, "OperationNumberLabour"),
                                                keywords.getAttributeValue(operationNo, "value").trim(), creareCampaignAssert);
            } else {
                appBasedUtils.asserVerification("operationId", appBasedUtils.getStringData(campaignData, "OperationId"),
                                                keywords.getAttributeValue(operationId, "value").trim(), creareCampaignAssert);
            }
            if ((!(keywords.isCheckboxElementSelected(labourValidation))) || (!(keywords.isCheckboxElementSelected(labourVST)))) {
                appBasedUtils.asserVerification("operationNo", appBasedUtils.getStringData(campaignData, "OperationNumberLabour"),
                                                keywords.getAttributeValue(operationNo, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("operationId", appBasedUtils.getStringData(campaignData, "OperationId"),
                                                keywords.getAttributeValue(operationId, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("labourDescription", appBasedUtils.getStringData(campaignData, "OperationDescLabourTab"),
                                                keywords.getAttributeValue(labourDescription, "value").trim(), creareCampaignAssert);
            }
            appBasedUtils.asserVerification("labourQunatity", appBasedUtils.getStringData(campaignData, "LabourQuantity"),
                                            keywords.getAttributeValue(labourQunatity, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("labourHours", appBasedUtils.getStringData(campaignData, "LabourHours"),
                                            keywords.getAttributeValue(labourHours, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("vehicleFrequecy", appBasedUtils.getStringData(campaignData, "labourVehicleFrequency"),
                                            keywords.getAttributeValue(labourVehicleFrequency, "value").trim(), creareCampaignAssert);
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign material tab details
     */
    public void validateMaterialTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            keywords.clickOnButton(driver, materialTab);
            appBasedUtils.asserVerification("causalPartNoPrefix", appBasedUtils.getStringData(campaignData, "ReplacedPartPrefix"),
                                            keywords.getAttributeValue(causalPartNoPrefix, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("causalPartNo", appBasedUtils.getStringData(campaignData, "ReplacedPartNo"),
                                            keywords.getAttributeValue(causalPartNo, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("functionGroup", appBasedUtils.getStringData(campaignData, "FunctionGroup"),
                                            keywords.getAttributeValue(functionGroup, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("defectCode", appBasedUtils.getStringData(campaignData, "DefectCode"),
                                            keywords.getTextValue(defectCodeAfterReleased).trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("maxMaterialCost", appBasedUtils.getStringData(campaignData, "MaxMaterialCostForVehicle"),
                                            keywords.getAttributeValue(maxMaterialCost, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("materialPartNoPrefix", appBasedUtils.getStringData(campaignData, "CausalPartPrefix"),
                                            keywords.getAttributeValue(materialPartNoPrefix, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("materialPartNo", appBasedUtils.getStringData(campaignData, "CausalPartNo"),
                                            keywords.getAttributeValue(materialPartNo, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("quantity", appBasedUtils.getStringData(campaignData, "MaterialQuantity"),
                                            keywords.getAttributeValue(quantity, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("vehicleFrequecy", appBasedUtils.getStringData(campaignData, "MachineFreq"),
                                            keywords.getAttributeValue(vehicleFrequecy, "value").trim(), creareCampaignAssert);
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign other cost tab details
     */
    public void validateOtherCostTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            keywords.clickOnButton(driver, otherCostTab);
            appBasedUtils.asserVerification("maxOtherCost", appBasedUtils.getStringData(campaignData, "MaxOtherCost"),
                                            keywords.getAttributeValue(maxOtherCost, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("otherCostDescription", appBasedUtils.getStringData(campaignData, "DescriptionOtherCost"),
                                            keywords.getAttributeValue(otherCostDescription, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("vehicleFrequecy", appBasedUtils.getStringData(campaignData, "OtherCostVehicleFreq"),
                                            keywords.getAttributeValue(vehicleFrequecy, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("orgCost", appBasedUtils.getStringData(campaignData, "OtherCost_Cost"),
                                            keywords.getAttributeValue(orgCost, "value").trim(), creareCampaignAssert);
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign importer tab details
     */
    public void validateImporterTab(HashMap<String, Object> campaignData) {
        try {
            creareCampaignAssert = new SoftAssert();
            if (keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                keywords.clickOnButton(driver, warrantyAreaRegionTabForVCE);
            } else if (keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
                keywords.clickOnButton(driver, warrantyDistrictRegionTabForVPC);
            } else {
                keywords.clickOnButton(driver, importerRegionTabForVTC);
            }
            if (keywords.getTextValue(importerVal) != null) {
                appBasedUtils.asserVerification("ImporterVal", appBasedUtils.getStringData(campaignData, "Importer"), keywords.getTextValue(importerVal).trim(),
                                                creareCampaignAssert);
            } else {
                appBasedUtils.asserVerification("regionVal", appBasedUtils.getStringData(campaignData, "ImporterRegion"),
                                                keywords.getTextValue(regionVal).trim(), creareCampaignAssert);
            }
            if (appBasedUtils.getStringData(campaignData, "VehicleWorkWith").equalsIgnoreCase("Concerned vehicles")) {
                appBasedUtils.asserVerification("noOfVehicles", appBasedUtils.getStringData(campaignData, "TotalVehiclesInImporter"),
                                                keywords.getAttributeValue(noOfVehicles, "value").trim(), creareCampaignAssert);
                appBasedUtils.asserVerification("fulfillment", appBasedUtils.getStringData(campaignData, "Fullfillment"),
                                                keywords.getAttributeValue(fulfillment, "value").trim(), creareCampaignAssert);
            } else {
                String totalVehicles[] = appBasedUtils.getStringData(campaignData, "TotalVehiclesInImporter").split(";");
                for (int i = 0; i < importerTabTotalRows.size(); i++) {
                    appBasedUtils.asserVerification("noOfVehicles", totalVehicles[i], keywords.getTextValue(driver.findElement(By.xpath(noOfVehiclesfirstXpath
                            + (i + 2) + noOfVehiclessecondXpath))).trim(), creareCampaignAssert);
                }
            }
            Assert.assertEquals(appBasedUtils.getStringData(campaignData, "LabourCostInImporterRegionTab"), keywords.getTextValue(labourCostInImporterRegionTab).trim().replace(" ", ""));
            Assert.assertEquals(appBasedUtils.getStringData(campaignData, "MaterialCostInImporterRegionTab"), keywords.getTextValue(materialCostInImporterRegionTab).trim().replace(" ", ""));
            Assert.assertEquals(appBasedUtils.getStringData(campaignData, "OtherCostInImporterRegionTab"),keywords.getTextValue(otherCostInImporterRegionTab).trim().replace(" ", ""));
            keywords.clickOnLink(labourCostInImporterRegionTab, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.input(labourRateInCostOrImporterPopUp, appBasedUtils.getStringData(campaignData, "LabourCostInCostOrImporterTab"), "LabourCostInCostOrImporterTab");
            keywords.clickOnLink(materialTabInCostOrImporterPopup, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.input(materialPriceInCostOrImporterPopup, appBasedUtils.getStringData(campaignData, "NewMaterialCostInImporterRegionTab"), "NewMaterialCostInImporterRegionTab");
            keywords.clickOnLink(otherCostTabInCostOrimporterPopup, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.input(otherCostInCostOrImporterPoup, appBasedUtils.getStringData(campaignData, "NewOtherCostInImporterRegionTab"), "NewOtherCostInImporterRegionTab");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.clickOnLink(okButtonInCostOrImporterPoup, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 6, loadingIcon);
            keywords.clickOnButton(driver, campaignSave);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            Assert.assertEquals(appBasedUtils.getStringData(campaignData, "NewLabourCostInImporterRegionTab"),
                                            keywords.getTextValue(labourCostInImporterRegionTab).trim().replace(" ", ""));
            Assert.assertEquals(appBasedUtils.getStringData(campaignData, "NewMaterialCostInImporterRegionTab"),
                                            keywords.getTextValue(materialCostInImporterRegionTab).trim().replace(" ", ""));
            Assert.assertEquals(appBasedUtils.getStringData(campaignData, "NewOtherCostInImporterRegionTab"),
                                            keywords.getTextValue(otherCostInImporterRegionTab).trim().replace(" ", ""));   
            APP_LOGS.info("Labour, material and other cost info updated in importer/region tab..!!");
            ExtentTestManager.getTest()
                             .log(Status.INFO,
                                  "Labour, material and other cost info updated in importer/region tab..!!");
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign TSU tab details
     */
    public void validateTSUTab(HashMap<String, Object> campaignData) {
        try {
            keywords.clickOnButton(driver, tsuTab);
            creareCampaignAssert = new SoftAssert();
            appBasedUtils.asserVerification("orderNumber", appBasedUtils.getStringData(campaignData, "TSU_ClaimAcctOrderNo"),
                                            keywords.getAttributeValue(orderNumber, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("tsuId", appBasedUtils.getStringData(campaignData, "TSUID"), keywords.getAttributeValue(tsuId, "value").trim(),
                                            creareCampaignAssert);
            appBasedUtils.asserVerification("issuedDate", appBasedUtils.getStringData(campaignData, "IssueDate"),
                                            keywords.getAttributeValue(issuedDate, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("decisionDate", appBasedUtils.getStringData(campaignData, "DecisionDate"),
                                            keywords.getAttributeValue(decisionDate, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("decision", appBasedUtils.getStringData(campaignData, "Decision"),
                                            keywords.getFirstSelectedItemFromDropDown(decision), creareCampaignAssert);
            appBasedUtils.asserVerification("appliedAmount", appBasedUtils.getStringData(campaignData, "AppliedAmt"),
                                            keywords.getAttributeValue(appliedAmount, "value").trim(), creareCampaignAssert);
            appBasedUtils.asserVerification("approvedAmount", appBasedUtils.getStringData(campaignData, "ApprovedAmt"),
                                            keywords.getAttributeValue(approvedAmount, "value").trim(), creareCampaignAssert);
            creareCampaignAssert.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate the campaign decision tab details
     */
    public void validateDecisionTab(HashMap<String, Object> campaignData) {
        keywords.clickOnButton(driver, decisionOrHistoryTab);
        keywords.waitTime1(2);
        keywords.scrollPage(driver, 250);
        creareCampaignAssert = new SoftAssert();
        appBasedUtils.asserVerification("campaignState", appBasedUtils.getStringData(campaignData, "DecisionHistoryForReleased"),
                                        keywords.getTextValue(campaignStateInHistory), creareCampaignAssert);
        ExtentTestManager.getTest().log(Status.INFO, "Campaign status is: " + keywords.getTextValue(campaignStateInHistory));
        creareCampaignAssert.assertAll();
    }

    /**************************************
     * #Method Name: set scc code in excel sheet
     */
    public void writeSccCodeInExcelSheet(HashMap<String, Object> campaignData) {
        ExcelReadAndWrite claimExcelSheet = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewClaimJob.xlsx",
                                                                  BaseClass.excelSheetName);
        claimExcelSheet.updateExcelRowData(4, 13, sccCodeGenerated);
        ExcelReadAndWrite campaignExcelSheet = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewCampaign.xlsx",
                                                                     BaseClass.excelSheetName);
        campaignExcelSheet.updateExcelRowData(4, 74, sccCodeGenerated);
    }

    /**************************************
     * #Method Name: Verify the tabs and search buttons are displayed
     */
    public void verifyGuiInSearchCampaignPage() {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        assertTrue(keywords.elementIsEnabled(searchBtn));
        assertTrue(keywords.elementIsDisplayed(newTab, "New tab in SearchPage"));
        assertTrue(keywords.elementIsDisplayed(assignedTab, "Assigned tab in SearchPage"));
        assertTrue(keywords.elementIsDisplayed(tsuApprovalTab, "TSU tab in SearchPage"));
        assertTrue(keywords.elementIsDisplayed(preparedTab, "Prepared tab in SearchPage"));
        assertTrue(keywords.elementIsDisplayed(closedTab, "Closed tab in SearchPage"));
        assertTrue(keywords.elementIsDisplayed(searchResultTab, "Search result tab in SearchPage"));
        ExtentTestManager.getTest().log(Status.INFO, "Verified the gui");
    }

    /**************************************
     * #Method Name: search with searchWithCampaignId
     */
    public void enterTheCampaignIdToSearch() {
        campaignIDInSearchResult = keywords.getTextValue(campaignIdInFirstRow).trim();
        keywords.input(campaignId, campaignIDInSearchResult, "Campaign Id");
    }

    /**************************************
     * #Method Name: search with searchWithTitle
     */
    public void enterTheTitle(HashMap<String, Object> campaignData) {
        keywords.input(title, appBasedUtils.getStringData(campaignData, "Title"), "Title");
    }

    /**************************************
     * #Method Name: search with searchWithQJ
     */
    public void enterTheQJ(HashMap<String, Object> campaignData) {
        keywords.input(qj, appBasedUtils.getStringData(campaignData, "ConnectQJ"), "ConnectedQJ");
    }

    /**************************************
     * #Method Name: set with searchWithOeprationId
     */
    public void setTheOperationID(HashMap<String, Object> campaignData) {
        if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(TSM_AMERICAS)) {
            keywords.waitTime1(2);
            keywords.setCheckboxIfNotSelected(operationIdCheckBox);
        }
    }

    /**************************************
     * #Method Name: set with searchWithOeprationId
     */
    public void enterTheIncludedOperation(HashMap<String, Object> campaignData) {
        keywords.input(includedOperationStart, appBasedUtils.getStringData(campaignData, "OperationNumberLabour"), "OperationNumberLabour");
        keywords.input(includedOperationEnd, appBasedUtils.getStringData(campaignData, "OperationNumberLabour"), "OperationNumberLabour");
    }

    /**************************************
     * #Method Name: set with causal part
     */
    public void enterTheCausalPart(HashMap<String, Object> campaignData) {
        keywords.input(causalPartNoPrefixSearch, appBasedUtils.getStringData(campaignData, "CausalPartPrefix"), "CausalPartPrefix");
        keywords.input(causalPartNoSearch, appBasedUtils.getStringData(campaignData, "CausalPartNo"), "CausalPartNo");
    }

    /**************************************
     * #Method Name: set with enterTheIncludedPart
     */
    public void enterTheIncludedPart(HashMap<String, Object> campaignData) {
        keywords.input(includedPartNoPrefix, appBasedUtils.getStringData(campaignData, "CausalPartPrefix"), "CausalPartPrefix");
        keywords.input(includedPartNo, appBasedUtils.getStringData(campaignData, "CausalPartNo"), "CausalPartNo");
    }

    /**************************************
     * #Method Name: set with enterTheMainOperationId
     */
    public void enterTheMainOperationId(HashMap<String, Object> campaignData) {
        keywords.input(mainOperationStart, appBasedUtils.getStringData(campaignData, "OperationId"), "OperationId");
        keywords.input(mainOperationEnd, appBasedUtils.getStringData(campaignData, "OperationId"), "OperationId");
    }

    /**************************************
     * #Method Name: set with enterTheOrderNoSearch
     */
    public void enterTheOrderNoSearch(HashMap<String, Object> campaignData) {
        keywords.input(orderNumberSearch, appBasedUtils.getStringData(campaignData, "TSU_ClaimAcctOrderNo"), "TSU_ClaimAcctOrderNo");
    }

    /**************************************
     * #Method Name: set with enterTheFunctionGroup
     */
    public void enterTheFunctionGroup(HashMap<String, Object> campaignData) {
        keywords.input(functionGroupStart, appBasedUtils.getStringData(campaignData, "FunctionGroup"), "FunctionGroup");
        keywords.input(functionGroupEnd, appBasedUtils.getStringData(campaignData, "FunctionGroup"), "FunctionGroup");
    }

    /**************************************
     * #Method Name: set with enterRepairDate
     */
    public void enterRepairDate(HashMap<String, Object> campaignData) {
        keywords.waitTime1(2);
        keywords.input(lastRepairDateStart, appBasedUtils.getStringData(campaignData, "LastRepairDate"), "LastRepairDate");
        keywords.clickOnButton(driver, lastRepairDateStartIcon);
        keywords.clickOnButton(driver, lastRepairDateStartIcon);
        keywords.input(lastRepairDateEnd, appBasedUtils.getStringData(campaignData, "LastRepairDate"), "LastRepairDate");
        keywords.clickOnButton(driver, lastRepairDateEndIcon);
        keywords.clickOnButton(driver, lastRepairDateEndIcon);
    }

    /**************************************
     * #Method Name: Search with searchWithCampaignId
     */
    public void searchWithCampaignId(HashMap<String, Object> campaignData) {
        enterTheCampaignIdToSearch();
        search();
        validateCampaignIdSearch(campaignData);
        keywords.clearText(campaignId, "All text fields in Campaign page");
        ExtentTestManager.getTest().log(Status.INFO, "Search with Campaign id was successful");
    }

    /**************************************
     * #Method Name: Search with searchWithSCCCode
     */
    public void searchWithSCCCode(HashMap<String, Object> campaignData) {
        campaignSearchWithSccCode(campaignData);
        search();
        validateSccCodeSearch(campaignData);
        keywords.clearText(sccCode, "All text fields in Campaign page");
        ExtentTestManager.getTest().log(Status.INFO, "Search with SCC code was successful");
    }

    /**************************************
     * #Method searchWithConnectedQJ
     */
    public void searchWithConnectedQJ(HashMap<String, Object> campaignData) {
        enterTheQJ(campaignData);
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        selectFirstRow();
        validateConnectedQJ(campaignData);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(qj, "All text fields in Campaign page");
        ExtentTestManager.getTest().log(Status.INFO, "Search with Connected QJ was successful");
    }

    /**************************************
     * #Method Name: searchWithMainOperation
     */
    public void searchWithMainOperation(HashMap<String, Object> campaignData) {
        setTheOperationID(campaignData);
        enterTheMainOperationId(campaignData);
        keywords.selectVisibleText(statusSearch, "All");
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        selectFirstRow();
        validateSearchResultOfLabourOpeartionId(campaignData);
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(mainOperationStart, "Main operation Id start text Box");
        keywords.clearText(mainOperationEnd, "Main operation Id end text Box");
        ExtentTestManager.getTest().log(Status.INFO, "Search with main operation was successful");
    }

    /**************************************
     * #Method Name: searchWithIncludedOperation
     */
    public void searchWithIncludedOperation(HashMap<String, Object> campaignData) {
        enterTheIncludedOperation(campaignData);
        keywords.selectVisibleText(statusSearch, "TSU Approved");
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        selectFirstRow();
        assertTrue(validateSearchResultOfLabourIncludedOpeartionNo(campaignData));
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(includedOperationStart, "Included operation start text Box");
        keywords.clearText(includedOperationEnd, "Included operation end text Box");
        ExtentTestManager.getTest().log(Status.INFO, "Search with included operation was successful");
    }

    /**************************************
     * #Method Name: searchWithPartNo
     */
    public void searchWithPartNo(HashMap<String, Object> campaignData) {
        enterTheCausalPart(campaignData);
        enterTheIncludedPart(campaignData);
        keywords.selectVisibleText(statusSearch, "Released");
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        selectFirstRow();
        validateCampaignPartNoSearch(campaignData);
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(causalPartNoPrefixSearch, "Main operation No start text Box");
        keywords.clearText(causalPartNoSearch, "Main operation No end text Box");
        keywords.clearText(includedPartNoPrefix, "Main operation No start text Box");
        keywords.clearText(includedPartNo, "Main operation No end text Box");
        ExtentTestManager.getTest().log(Status.INFO, "Search with Causal and included part was successful");
    }

    /**************************************
     * #Method Name: searchWithOrderNo
     */
    public void searchWithOrderNo(HashMap<String, Object> campaignData) {
        enterTheOrderNoSearch(campaignData);
        keywords.selectVisibleText(statusSearch, "All");
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        selectFirstRow();
        validateOrderNo(campaignData);
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(orderNumberSearch, "orderNumberSearch text Box");
        keywords.clearText(sccCode, "sccCode text Box");
        ExtentTestManager.getTest().log(Status.INFO, "Search with part no was successful");
    }

    /**************************************
     * #Method Name: searchWithFunctionGroup
     */
    public void searchWithFunctionGroup(HashMap<String, Object> campaignData) {
        enterTheFunctionGroup(campaignData);
        keywords.selectVisibleText(statusSearch, "Closed");
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        selectFirstRow();
        validateTheFunctionGroup(campaignData);
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(functionGroupStart, "functionGroupStart text Box");
        keywords.clearText(functionGroupEnd, "functionGroupEnd text Box");
        ExtentTestManager.getTest().log(Status.INFO, "Search with function group was successful");
    }

    /**************************************
     * #Method Name: searchWithLastRepairDate
     */
    public void searchWithLastRepairDate(HashMap<String, Object> campaignData) {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        enterRepairDate(campaignData);
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        statusFromGridAfterSearch = keywords.getTextValue(statusInsearchResultGrid).trim();
        selectFirstRow();
        validateTheLastRepairDate(campaignData);
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clearText(lastRepairDateStart, "lastRepairDateStart text Box");
        keywords.clearText(lastRepairDateEnd, "lastRepairDateEnd text Box");
        ExtentTestManager.getTest().log(Status.INFO, "Search with last repair date was successful");
    }

    /**************************************
     * #Method Name:searchWithTitle
     */
    public void searchWithTitle(HashMap<String, Object> campaignData) {
        enterTheTitle(campaignData);
        search();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        validateTitleSearch(campaignData);
        keywords.clearText(title, "All text fields in Campaign page");
        ExtentTestManager.getTest().log(Status.INFO, "Search with Title was successful");
    }

    /**************************************
     * #Method Name:searchWithStatus
     */
    public void searchWithStatus(HashMap<String, Object> campaignData) {
        String xpathOfStatusInSearchResultGrid = "//div[contains(@class,'ui-grid-cell ng-scope ui-grid-coluiGrid')][6]";
        searchCampaign = new SoftAssert();
        Select selectDropdownElement = new Select(status);
        List<WebElement> listDropdownElements = selectDropdownElement.getOptions();
        listDropdownElements.size();
        for (int i = 0; i < listDropdownElements.size(); i++) {
            selectDropdownElement.selectByVisibleText(listDropdownElements.get(i).getText());
            search();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            if (i == 0) {
                appBasedUtils.dropdownValueValidationInGrid(appBasedUtils.getStringData(campaignData, "Status"), xpathOfStatusInSearchResultGrid,
                                                            searchCampaign, driver);
            } else {
                appBasedUtils.dropdownValueValidationInGrid(listDropdownElements.get(i).getText().trim(), xpathOfStatusInSearchResultGrid, searchCampaign,
                                                            driver);
            }
        }
        selectDropdownElement.selectByVisibleText(listDropdownElements.get(0).getText());
        ExtentTestManager.getTest().log(Status.INFO, "Search with status was successful");
    }

    /**************************************
     * #Method Name: Validate search result
     */
    public void validateCampaignIdSearch(HashMap<String, Object> campaignData) {
        searchCampaign = new SoftAssert();
        appBasedUtils.asserVerification("Campaign Id", campaignIDInSearchResult, keywords.getTextValue(campaignIdInFirstRow).trim(), searchCampaign);
        appBasedUtils.asserVerification("items count per page", "1 - 1 / 1 items", itemPerPage.getText(), searchCampaign);
        searchCampaign.assertAll();
    }

    /**************************************
     * #Method Name: Validate search result
     */
    public void validateSccCodeSearch(HashMap<String, Object> campaignData) {
        searchCampaign = new SoftAssert();
        appBasedUtils.asserVerification("Scc code", appBasedUtils.getStringData(campaignData, "SccCode"), keywords.getTextValue(sccCodeInFristRow).trim(),
                                        searchCampaign);
        appBasedUtils.asserVerification("items count per page", "1 - 1 / 1 items", itemPerPage.getText(), searchCampaign);
        searchCampaign.assertAll();
    }

    /**************************************
     * #Method Name: Validate search result
     */
    public void validateTitleSearch(HashMap<String, Object> campaignData) {
        String xpath = "//div[@class='ng-isolate-scope']/div[3]";
        searchCampaign = new SoftAssert();
        appBasedUtils.textValidationGrid(driver, appBasedUtils.getStringData(campaignData, "Title"), xpath, searchCampaign);
        searchCampaign.assertAll();
    }

    /**************************************
     * #Method Name: Validate search result
     */
    public void validateConnectedQJ(HashMap<String, Object> campaignData) {
        keywords.clickOnButton(driver, generalTab);
        try {
            Assert.assertEquals(keywords.getAttributeValue(qjLinkTextFromGeneralTab, "value").trim(), appBasedUtils.getStringData(campaignData, "ConnectQJ"));
            lastRepairDateFromApp = keywords.getAttributeValue(lastRepairDate, "value");
        } catch (NullPointerException e) {
            if (lastRepairDateFromApp == null) {
                lastRepairDateFromApp = keywords.getTextValue(repairDateFromCampaign);
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        keywords.clickOnButton(driver, decisionOrHistoryTab);
        keywords.waitTime1(2);
        campaignStatus = keywords.getTextValue(campaignStateInHistory);
        keywords.clickOnButton(driver, clickOnCloseButtonHistory);
    }

    /**************************************
     * #Method Name: Validate search result
     */
    public boolean validateSearchResultOfLabourOpeartionId(HashMap<String, Object> campaignData) {
        searchCampaign = new SoftAssert();
        keywords.clickOnButton(driver, labourTab);
        if (keywords.getTextValue(companyNameElement).trim().equalsIgnoreCase(TSM_AMERICAS)) {
            for (int i = 0; i < mainOperationRadioButton.size(); i++) {
                if ((mainOperationRadioButton.get(i).getAttribute("checked") != null) || (mainOperationRadioButton.get(i).getAttribute("checked") == "true")) {
                    operationIdList.get(i).getAttribute("value");
                    appBasedUtils.asserVerification("Operation Id", appBasedUtils.getStringData(campaignData, "OperationId"),
                                                    operationIdList.get(i).getAttribute("value").trim(), searchCampaign);
                    return true;
                }
            }
        } else {
            validateSearchResultOfLabourOpeartionNo(campaignData);
        }
        searchCampaign.assertAll();
        return false;
    }

    /**************************************
     * #Method Name: Validate search result
     *
     * @return
     */
    public boolean validateSearchResultOfLabourOpeartionNo(HashMap<String, Object> campaignData) {
        try {
            keywords.clickOnButton(driver, labourTab);
            for (int i = 0; i < mainOperationRadioButton.size(); i++) {
                String flag = mainOperationRadioButton.get(i).getAttribute("checked");
                if ((flag != null) || (flag == "true")) {
                    if ((appBasedUtils.getStringData(campaignData, "OperationNumberLabour")).equalsIgnoreCase(operationNoList.get(i).getAttribute("value")
                                                                                                                             .trim())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
        return false;
    }

    /**************************************
     * #Method Name: Validate search result
     *
     * @return
     */
    public boolean validateSearchResultOfLabourIncludedOpeartionNo(HashMap<String, Object> campaignData) {
        try {
            keywords.clickOnButton(driver, labourTab);
            for (int i = 0; i < mainOperationRadioButton.size(); i++) {
                if((appBasedUtils.getStringData(campaignData, "IncludedOperationNo")).equalsIgnoreCase(operationNoList.get(i).getAttribute("value")
                                                                                                                               .trim())) {
                    return true;
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
        return false;
    }

    /**************************************
     * #Method Name: Validate search result
     *
     * @return
     */
    public void validateCampaignPartNoSearch(HashMap<String, Object> campaignData) {
        try {
            searchCampaign = new SoftAssert();
            keywords.clickOnButton(driver, materialTab);
            appBasedUtils.asserVerification("causalPartNoPrefix", appBasedUtils.getStringData(campaignData, "ReplacedPartPrefix"),
                                            keywords.getAttributeValue(causalPartNoPrefix, "value").trim(), searchCampaign);
            appBasedUtils.asserVerification("causalPartNo", appBasedUtils.getStringData(campaignData, "ReplacedPartNo"),
                                            keywords.getAttributeValue(causalPartNo, "value").trim(), searchCampaign);
            appBasedUtils.asserVerification("materialPartNoPrefix", appBasedUtils.getStringData(campaignData, "CausalPartPrefix"),
                                            keywords.getAttributeValue(materialPartNoPrefix, "value").trim(), searchCampaign);
            appBasedUtils.asserVerification("materialPartNo", appBasedUtils.getStringData(campaignData, "CausalPartNo"),
                                            keywords.getAttributeValue(materialPartNo, "value").trim(), searchCampaign);
            searchCampaign.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /**************************************
     * #Method Name: Validate search result
     *
     * @return
     */
    public void validateOrderNo(HashMap<String, Object> campaignData) {
        searchCampaign = new SoftAssert();
        keywords.clickOnButton(driver, tsuTab);
        creareCampaignAssert = new SoftAssert();
        appBasedUtils.asserVerification("orderNumber", appBasedUtils.getStringData(campaignData, "TSU_ClaimAcctOrderNo"),
                                        keywords.getAttributeValue(orderNumber, "value").trim(), creareCampaignAssert);
        searchCampaign.assertAll();
    }

    /**************************************
     * #Method Name: Validate search result
     *
     * @return
     */
    public void validateTheFunctionGroup(HashMap<String, Object> campaignData) {
        searchCampaign = new SoftAssert();
        keywords.clickOnButton(driver, materialTab);
        appBasedUtils.asserVerification("functionGroup", appBasedUtils.getStringData(campaignData, "FunctionGroup"),
                                        keywords.getTextValue(functionGroupNonEditable).trim(), creareCampaignAssert);
        searchCampaign.assertAll();
    }

    /**************************************
     * #Method Name: Validate search result
     *
     * @return
     */
    public void validateTheLastRepairDate(HashMap<String, Object> campaignData) {
        searchCampaign = new SoftAssert();
        String getRepairDate;
        keywords.clickOnButton(driver, generalTab);
        if(statusFromGridAfterSearch.trim().equalsIgnoreCase("Closed")){
            getRepairDate = keywords.getTextValue(repairDateFromCampaign);
        }
        else{
            getRepairDate = keywords.getAttributeValue(lastRepairDate, "value").trim();
        }
        appBasedUtils.asserVerification("lastRepairDate", DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT),
                                        getRepairDate.trim(), creareCampaignAssert);
        searchCampaign.assertAll();
    }
}
