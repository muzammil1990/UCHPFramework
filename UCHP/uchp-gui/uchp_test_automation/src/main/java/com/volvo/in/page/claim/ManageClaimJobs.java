package com.volvo.in.page.claim;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.PDFReadAndValidate;
import com.volvo.in.commonlibrary.UIUtils;

public class ManageClaimJobs {
    private static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction equipment";
    String companyName;

    public String claimReferenceNumber;
    public String labourNetCost;
    public String materialNetCost;
    public String otherNetCost;
    public String claimJobNumber;
    public String TMADecisionInClaimJobInformation;

    public String userGroup = "dealer";
    WebDriver driver;
    public static String scenarioValue;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert openSelectedClaimJobAssert;
    SoftAssert saTMADecision;
    UIUtils keywords = new UIUtils(driver);
    ExcelReadAndWrite claimExcelSheet;
    public WebDriverWait wait = null;
    public Logger APP_LOGS = Logger.getLogger(ManageClaimJobs.class);

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//h4[text()='Claim job']")
    WebElement claimJobTitle;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[1]/span")
    WebElement statusFromListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[2]")
    WebElement tocFromListCLaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[3]")
    WebElement importerListClaimJob;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[4]")
    WebElement dealerListClaimJob;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[5]//a")
    WebElement claimRefNoFromList;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[6]")
    WebElement claimTypeFromListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[7]")
    WebElement regDateFromListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[8]")
    WebElement chassisIdFromListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[10]")
    WebElement marketingTypeFromListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[11]")
    WebElement repairOrderNoFromListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[12]")
    WebElement grandTotalFromListClaim;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Reference no.']]]//div[2]")
    WebElement claimJobReferenceNo;

    @FindBy(how = How.XPATH, using = "//div/label[contains(.,'Status')]/../../div[2]")
    WebElement claimJobStatus;

    @FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'materialInst')]")
    public WebElement miName;

    @FindBy(how = How.XPATH, using = "//label[text()='TMA decision']/../following-sibling::div/a")
    WebElement TMADecision;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Registration date']]]//div[4]")
    WebElement claimJobRegistrationDate;

    @FindBy(how = How.XPATH, using = "//div[div[b[contains(.,'id')]]]//div[6]")
    WebElement claimJobChassisId;

    @FindBy(how = How.XPATH, using = "//tr[td[b[text()='Grand total']]]/td[2]")
    WebElement claimJobGrandTotal;

    @FindBy(how = How.XPATH, using = "//a[text()='History']")
    WebElement claimJobHistory;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='row in ::ctrl.claim.claimFunctionData.historyList track by $index'][1]/td[1]")
    WebElement claimJobHistoryJobStatus;

    @FindBy(how = How.XPATH, using = "//a[text()='Header']")
    WebElement claimJobHeader;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Registration date']]]/div[4]")
    WebElement claimHeaderRegDate;

    @FindBy(how = How.XPATH, using = "//div[div[label[@for='registrationNo']]]/div[2]")
    WebElement claimHeaderRegNo;

    @FindBy(how = How.XPATH, using = "(//div[div[label[@for='chassisSeries']]])[1]")
    WebElement claimHeaderChassisId;

    @FindBy(how = How.XPATH, using = "//a[contains(.,'Header')]/../../li[@class='uib-tab nav-item ng-scope ng-isolate-scope active']/a")
    WebElement claimHeaderJobNo;

    @FindBy(how = How.XPATH, using = "//div[div[@class='divTableCell repair-header-col-1']]/div[2]")
    WebElement claimHeaderImporter;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Repair date']]]/div[2]")
    WebElement claimHeaderDealer;

    @FindBy(how = How.XPATH, using = "//div[div[b[text()='Marketing type']]]//div[8]")
    WebElement claimHeaderMarketingType;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[2]")
    WebElement dealerNoMaterialTab;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[4]")
    WebElement refNoMaterialTab;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[5]")
    WebElement claimTypeMaterialTab;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[6]")
    WebElement miNameMaterialTab;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[9]")
    WebElement registrationDateMaterialTab;

    @FindBy(how = How.XPATH, using = "//span[@ng-repeat='thisMi in ctrl.claimJob.allMINames']")
    WebElement claimJobMINameMaterialTab;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[10]")
    WebElement appealStatusAppealTabListClaim;

    @FindBy(how = How.ID, using = "appealState")
    WebElement appealStatusAppealTab;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[11]")
    WebElement marketingTypeAppealTabListClaim;

    @FindBy(how = How.XPATH, using = "(//div[@ng-click='grid.appScope.ctrl.openSelectedClaimJobs(row)'])[12]")
    WebElement grandTotalAppealTabListClaim;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Labour')]")
    WebElement labourTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Claim job information')]")
    WebElement claimJobInformationTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-default dropdown-toggle btn-xs']")
    WebElement claimTypeDropdownButtonElement;

    @FindBy(how = How.ID, using = "newclaimjobbtn")
    WebElement newClaimJobButtonElement;

    @FindBy(how = How.XPATH, using = "//input[contains(@focus-if,'partsFittedDate')]")
    WebElement partsFittedDateElement;

    @FindBy(how = How.ID, using = "deleteclaimbtn")
    WebElement deleteClaimJobButtonElement;

    @FindBy(how = How.NAME, using = "partsMilage")
    WebElement partsMileageElement;

    @FindBy(how = How.NAME, using = "partOperatingHours")
    WebElement partsOperatingHoursElement;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Other costs')]")
    WebElement otherCostTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Travel expenses')]")
    WebElement travelExpensesTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Remarks')]")
    WebElement remarksTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement closeButton;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homeButtonElement;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Attachment')]")
    WebElement attachmentTabManageClaimJob;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'Inspector report')]")
    WebElement inspectorReportTabManageClaimJob;

    @FindBy(how = How.ID, using = "remarkComplaint")
    WebElement complaintRemarksTab;

    @FindBy(how = How.ID, using = "remarkCause")
    WebElement causeRemarksTab;

    @FindBy(how = How.ID, using = "expectAttachment")
    WebElement expectAttachmentCheckBoxAttachmentTab;

    @FindBy(how = How.ID, using = "remarkCorrection")
    WebElement correctionRemarksTab;

    @FindBy(how = How.ID, using = "claimComment")
    WebElement commentRemarksTab;

    @FindBy(how = How.ID, using = "claimjobsavebtn")
    WebElement saveClaimJobButton;

    @FindBy(how = How.XPATH, using = "//button[@id='claimjobreleasebtn']")
    WebElement releaseClaimJobButton;

    @FindBy(how = How.XPATH, using = "//label[@class='btn btn-xs btn-success btn-file ng-binding']")
    WebElement browseButtonAttachement;

    @FindBy(how = How.XPATH, using = "//p[@class='help-block ng-binding']")
    WebElement attachmentLimitMessage;

    @FindBy(how = How.XPATH, using = "//a[text()='Material']")
    WebElement claimJobMaterial;

    @FindBy(how = How.XPATH, using = "//input[@name='partNoPrefix']")
    WebElement claimJobMaterialPrefix;

    @FindBy(how = How.NAME, using = "partNo")
    WebElement claimJobMaterialPartNo;

    @FindBy(how = How.NAME, using = "serialNo")
    WebElement claimJobMaterialserialNo;

    @FindBy(how = How.NAME, using = "defectCode")
    WebElement claimJobMaterialdefectCode;

    @FindBy(how = How.ID, using = "repaired")
    WebElement claimJobMaterialRepaired;

    @FindBy(how = How.ID, using = "replaced")
    WebElement claimJobMaterialReplaced;

    @FindBy(how = How.ID, using = "appealState")
    WebElement claimJobAppealStatus;

    @FindBy(how = How.XPATH, using = "//span[text()='Appeal closed']")
    WebElement claimJobAppealStatusClosed;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'includedMaterialList')]/descendant::input[contains(@name,'contractPrice_')]") })
    List<WebElement> repairedPartPriceInMaterial;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'includedMaterialList')]/descendant::input[contains(@name,'contractPrice_0')]")
    WebElement repairedPartPriceElement;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'partNoPrefix_')]") })
    List<WebElement> repairedPartPrefixInMaterial;

    @FindAll({ @FindBy(xpath = "//input[@name='newMaterial']") })
    List<WebElement> newMaterialRadioButton;

    @FindAll({ @FindBy(xpath = "//li[contains(@ng-repeat,'claimValidationErrorTextDTOList')]") })
    List<WebElement> claimJobCheckErrors;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'partNo_')]") })
    List<WebElement> repairedPartNumberInMaterial;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'includedMaterialList')]/descendant::input[contains(@name,'quantity_')]") })
    List<WebElement> repairedPartQuantiyInMaterial;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-show,'tempList')]/descendant::a") })
    List<WebElement> uploadedAttachments;

    @FindBy(how = How.XPATH, using = "//tr[@id='row_0']/descendant::input[@type='radio']")
    WebElement mainOperationRadioLabourTab;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Labour net total')]/../../th[3]/span")
    WebElement labourNetTotalLabourTabElement;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Material net total')]/../../th[3]/span")
    WebElement materialNetTotalMaterialTabElement;

    @FindBy(how = How.XPATH, using = "//tr/descendant::b[contains(text(),'Labour')]/../../td[2]")
    WebElement labourNetTotalClaimJobInformationTabElement;

    @FindBy(how = How.XPATH, using = "//tr/descendant::b[contains(text(),'Material')]/../../td[2]")
    WebElement materialNetTotalClaimJobInformationTabElement;

    @FindBy(how = How.XPATH, using = "//tr/descendant::b[contains(text(),'Other costs')]/../../td[2]")
    WebElement otherCostNetTotablClaimJobInformationTabElement;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'serialNo_')]") })
    List<WebElement> repairedSerialNumberInMaterial;

    @FindAll({ @FindBy(xpath = "//input[contains(@name,'operationFormat_')]") })
    List<WebElement> operationNumberLabourTab;

    @FindAll({ @FindBy(xpath = "//td[contains(@ng-if,'showLabourRate')]/../descendant::input[contains(@name,'description_')]") })
    List<WebElement> operationDescriptionLabourTab;

    @FindAll({ @FindBy(xpath = "//input[@type='checkbox' and @name='vst']") })
    List<WebElement> vstCheckboxLabourTab;

    @FindAll({ @FindBy(xpath = "//td[contains(@ng-if,'showLabourRate')]/../descendant::input[contains(@name,'hours_')]") })
    List<WebElement> hoursLabourTab;

    @FindAll({ @FindBy(xpath = "//td[contains(@ng-if,'showLabourRate')]/../descendant::input[contains(@name,'quantity_')]") })
    List<WebElement> quantityLabourTab;

    @FindAll({ @FindBy(xpath = "//td[contains(@ng-if,'showLabourRate')]/../descendant::input[contains(@name,'caf_')]") })
    List<WebElement> cafLabourTab;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'includedMaterialList')]/descendant::input[contains(@name,'caf_')]") })
    List<WebElement> cafMaterialTab;

    @FindAll({ @FindBy(xpath = "//div[@class='dropdown-menu open']/descendant::a") })
    List<WebElement> claimTypesDropdownValues;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'costList')]/descendant::input[contains(@name,'quantity_')]") })
    List<WebElement> quantityOtherCostTab;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'costList')]/descendant::input[contains(@name,'unitPrice_')]") })
    List<WebElement> priceOtherCostTab;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'costList')]/descendant::input[contains(@name,'description_')]") })
    List<WebElement> descriptionOtherCostTab;

    @FindAll({ @FindBy(xpath = "//select[@ng-model='row.otherCostCategoryCode']") })
    List<WebElement> categoryDropdownOtherCostTab;

    @FindAll({ @FindBy(xpath = "//tr/td[contains(@ng-if,'visiblePrice')][2]") })
    List<WebElement> otherCostNetCost;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//h4[@class='ng-binding ng-scope' and contains(.,'The claim job is saved')]")
    WebElement claimJobCheckErrorMessageHeader;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.NAME, using = "hoursAmount")
    WebElement travelHoursTravelExpenses;

    @FindBy(how = How.NAME, using = "distanceAmount")
    WebElement distanceAmountTravelExpenses;

    @FindBy(how = How.NAME, using = "otherAmount")
    WebElement otherAmountTravelExpenses;

    @FindBy(how = How.XPATH, using = "//span/i[@class='fa fa-calendar']")
    WebElement datePickerButton;

    @FindBy(how = How.XPATH, using = "//a[contains(@ng-click,'checkErrorList')]/span")
    WebElement errorIconLink;

    @FindBy(how = How.XPATH, using = "//tbody/tr[contains(@ng-repeat,'d in vm.simpleList')][1]")
    WebElement firstErrorMessageInPopup;

    @FindBy(how = How.XPATH, using = "//th[contains(@ng-if,'showLabourRate')]/../descendant::button[contains(text(),'Add row')]")
    WebElement addRowButtonLabourTab;

    @FindBy(how = How.XPATH, using = "//th[contains(@ng-if,'visiblePrice')]/../descendant::button[contains(text(),'Add row')]")
    WebElement addRowButtonOtherCostTab;

    @FindBy(how = How.XPATH, using = "//th[contains(@ng-if,'showMorePrice')]/../descendant::button[contains(text(),'Add row')]")
    WebElement addRowButtonMaterialTab;

    @FindBy(how = How.XPATH, using = "//th[contains(@ng-if,'showMorePrice')]/../descendant::button[contains(text(),'Copy row')]")
    WebElement copyRowButtonMaterialTab;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-yes btn-success ng-binding']")
    WebElement releaseClaimJobYesButton;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'release now')]")
    WebElement releaseClaimJobAlertMessage;

    @FindBy(how = How.XPATH, using = "//div/p[contains(text(),'header information')]")
    WebElement claimRepairHeaderInformationElement;

    @FindBy(how = How.XPATH, using = "//a[@class='nav-link ng-binding' and contains(.,'1')]")
    WebElement firstClaimJob;

    @FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'unplannedStop')]")
    WebElement unplannedStopDropdown;

    @FindBy(how = How.XPATH, using = "//li[contains(@ng-repeat,'claimRepairHeaderDTO.claimJobIDDTOList')]")
    WebElement claimJobNo;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'You are about to delete')]")
    WebElement claimJobDeleteModalMessage;

    @FindBy(how = How.XPATH, using = "//button[text()='Yes']")
    WebElement claimJobDeleteModalYesButton;

    @FindBy(how = How.XPATH, using = "//div[@ng-click='tapToast()']/div[@on='allowHtml']/*[contains(., 'Claim deleted')]")
    WebElement claimDeletedInfoMessage;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement closeValidationAlert;

    @FindBy(how = How.ID, using = "deletedebitstructurebtn")
    WebElement claimJobDeleteDebitStructure;

    @FindBy(how = How.XPATH, using = "//a[text()='Assessment']")
    WebElement claimJobAssessmentTab;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='ctrl.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.forced']")
    WebElement claimJobForceClaimCheckBox;

    @FindBy(how = How.XPATH, using = "//td[@class='text-nowrap text-right ng-binding']")
    WebElement applyDebitCodeNetCost;

    @FindBy(how = How.ID, using = "mainDebitCodeField")
    WebElement applyDebitCodeMainDebitCode;

    @FindBy(how = How.XPATH, using = "//tr[td[@class='text-nowrap text-right ng-binding']]/td[3]")
    WebElement applyDebitCodeCoverage;

    @FindBy(how = How.XPATH, using = "//button[text()='Apply debit code']")
    WebElement applyDebitCode;

    @FindBy(how = How.XPATH, using = "//div[@class='divTableRow ng-scope']/././div[@class='divTableCell ng-binding']/span")
    WebElement autoExceptionReason;

    // @FindBy(how = How.XPATH, using = "//input[@id='labour_total_debit_0']")
    // WebElement debitCodeOfLabour;

    @FindAll({ @FindBy(xpath = "//input[contains(@id,'labour_total_debit_')]") })
    List<WebElement> debitCodeOfLabourSplit;

    // @FindBy(how = How.XPATH, using = "//input[@id='material_total_debit_0']")
    // WebElement debitCodeOfMaterial;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'material_total_debit_')]") })
    List<WebElement> debitCodeOfMaterial;

    // @FindBy(how = How.XPATH, using = "//input[@id='travel_total_debit_0']")
    // WebElement debitCodeOfTravelExpense;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'travel_total_debit_')]") })
    List<WebElement> debitCodeOfTravelExpense;

    // @FindBy(how = How.ID, using = "cost_total_debit_0")
    // WebElement debitCodeOfOtherCost;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'cost_total_debit_')]") })
    List<WebElement> debitCodeOfOtherCost;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_amount_percentage_0']")
    WebElement labourTotalPercentage;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'labour_total_amount_percentage_')]") })
    List<WebElement> labourTotalPerncentageSplit;

    @FindBy(how = How.ID, using = "material_total_amount_percentage_0")
    WebElement materialTotalPercentage;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'material_total_amount_percentage_')]") })
    List<WebElement> materialTotalPercentageList;

    // @FindBy(how = How.ID, using = "travel_total_amount_percentage_0")
    // WebElement travelTotalPercentage;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'travel_total_amount_percentage_')]") })
    List<WebElement> travelTotalPercentage;

    // @FindBy(how = How.ID, using = "cost_total_amount_percentage_0")
    // WebElement otherCostTotalPercentage;

    @FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@id,'cost_total_amount_percentage_')]") })
    List<WebElement> otherCostTotalPercentage;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[1]")
    WebElement debitCodeTotal;

    @FindBy(how = How.XPATH, using = "//tr[td[@class='third-column-width']]/td[4]")
    WebElement netCostTotal;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList')]/td[6]")
    WebElement coverageCodeInTotal;

    @FindBy(how = How.ID, using = "percentagebtn")
    WebElement percentageButton;

    @FindBy(how = How.XPATH, using = "//div[input[@id='labour_total_debit_0']]/span/i")
    WebElement labourDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_total_debit_0']]/span/i")
    WebElement materialDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_total_debit_0']]/span/i")
    WebElement travelExpenseDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='cost_total_debit_0']]/span/i")
    WebElement otherCostsDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='labour_total_reject_0']]/span/i")
    WebElement labourRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_total_reject_0']]/span/i")
    WebElement materialRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_total_reject_0']]/span/i")
    WebElement travelExpenseRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='cost_total_reject_0']]/span/i")
    WebElement costRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'claimFunctionData.labourList')]/td[6]")
    WebElement labourHoursValue;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'claimFunctionData.labourList')]/td[7]")
    WebElement labourCAFValue;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'claimFunctionData.labourList')]/td[8]")
    WebElement labourCostPerHourValue;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'claimFunctionData.includedMaterialList')]/td[8]")
    WebElement materialCAFValue;

    @FindBy(how = How.XPATH, using = "//div[input[@id='labour_total_debit_1']]/span/i")
    WebElement labourSplitCost1DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_total_debit_1']]/span/i")
    WebElement materialSplitCost1DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='cost_total_debit_1']]/span/i")
    WebElement otherCostSplitCost1DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_total_debit_2']]/span/i")
    WebElement travelSplitCost1DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='labour_total_debit_2']]/span/i")
    WebElement labourSplitCost2DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_total_debit_2']]/span/i")
    WebElement materialSplitCost2DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='cost_total_debit_2']]/span/i")
    WebElement otherCostSplitCost2DebitClick;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_total_debit_1']]/span/i")
    WebElement travelSplitCost2DebitClick;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_reject_1']/following-sibling::span/i")
    WebElement splitCost1LabourRejectCode;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_total_reject_1']]/span/i")
    WebElement splitCost1MaterialRejectCode;

    @FindBy(how = How.XPATH, using = "//div[input[@id='cost_total_reject_1']]/span/i")
    WebElement splitCost1OtherCostRejectCode;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_debit_1']")
    WebElement splitCost1LabourDebitCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='material_total_debit_1']")
    WebElement splitCost1MaterialDebitCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='cost_total_debit_1']")
    WebElement splitCost1OtherDebitCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='travel_total_debit_1']")
    WebElement splitCost1TravelDebitCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_reject_1']")
    WebElement splitCost1LabourRejectCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='material_total_reject_1']")
    WebElement splitCost1MaterialRejectCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='cost_total_reject_1']")
    WebElement splitCost1OtherRejectCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='travel_total_reject_1']")
    WebElement splitCost1TravelRejectCodeField;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_amount_percentage_1']")
    WebElement splitCost1LabourPercentage;

    @FindBy(how = How.XPATH, using = "//input[@id='material_total_amount_percentage_1']")
    WebElement splitCost1MaterialPercentage;

    @FindBy(how = How.XPATH, using = "//input[@id='cost_total_amount_percentage_1']")
    WebElement splitCost1OtherPercentage;

    @FindBy(how = How.XPATH, using = "//input[@id='travel_total_amount_percentage_1']")
    WebElement splitCost1TravelPercentage;

    @FindAll({ @FindBy(xpath = "//input[contains(@id,'labour_total_reject_')]") })
    List<WebElement> labourRejectCodeListOfReason;

    @FindAll({ @FindBy(xpath = "//input[contains(@id,'material_total_reject_')]") })
    List<WebElement> materialRejectCodeListOfReason;

    @FindAll({ @FindBy(xpath = "//input[contains(@id,'cost_total_reject_')]") })
    List<WebElement> otherCostRejectCodeListOfReason;

    @FindAll({ @FindBy(xpath = "//input[contains(@id,'travel_total_reject_')]") })
    List<WebElement> travelRejectCodeListOfReason;

    List<WebElement> xpath = null;

    @FindAll({ @FindBy(xpath = " //*[@ng-if='row.vst']") })
    List<WebElement> vstCheckBoxSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.labourList')]/child::td[3]") })
    List<WebElement> labourTabOperationNoSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.labourList')]/child::td[4]") })
    List<WebElement> labourTabOperationDescSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.labourList')]/child::td[5]") })
    List<WebElement> labourTabQuantitySavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.labourList')]/child::td[6]") })
    List<WebElement> labourTabHoursSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.labourList')]/child::td[7]") })
    List<WebElement> labourTabCostPerHoursSavedValue;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.materialForm']/../descendant::td[1]/span")
    WebElement materialTabPartPrefixSavedValue;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.materialForm']/../descendant::td[2]/span")
    WebElement materialTabPartPartnoSavedValue;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.materialForm']/../descendant::td[5]/span")
    WebElement materialTabSerialnoSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.debitCodeSummaryDTOList')]/child::td[6]") })
    List<WebElement> coverageId;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.debitCodeSummaryDTOList')]/child::td[7]") })
    List<WebElement> coverageIdVCE;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.materialForm']/../descendant::td[6]/span")
    WebElement materialTabDefectCodeSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.includedMaterialList')]/child::td[2]") })
    List<WebElement> materialTabIncludedPartPrefixSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.includedMaterialList')]/child::td[3]") })
    List<WebElement> materialTabIncludedPartNoSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.includedMaterialList')]/child::td[6]") })
    List<WebElement> materialTabIncludedSerialnoSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.includedMaterialList')]/child::td[7]") })
    List<WebElement> materialTabIncludedQuantitySavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.costList')]/child::td[1]/span") })
    List<WebElement> otherCostTabQuantitySavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.costList')]/child::td[2]/span") })
    List<WebElement> otherCostTabPriceSavedValue;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.costList')]/child::td[3]/div") })
    List<WebElement> otherCostTabDescSavedValue;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.remarksForm']/../descendant::div[15]")
    WebElement remarksTabComplaintSavedValue;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.remarksForm']/../descendant::div[21]")
    WebElement remarksTabCauseSavedValue;

    @FindBy(how = How.XPATH, using = "//form[@name='ctrl.remarksForm']/../descendant::div[26]")
    WebElement remarksTabCorrectionSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'travelList')]/../descendant::td[1]")
    WebElement travelExpTabHoursSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'travelList')]/../descendant::td[6]/span")
    WebElement travelExpTabDistanceSavedValue;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'travelList')]/../descendant::td[11]/span")
    WebElement travelExpTabOtherExpSavedValue;

    @FindBy(how = How.ID, using = "deletedebitstructurebtn")
    WebElement deleteDebitStructure;

    @FindBy(how = How.XPATH, using = "//select[@ng-model='ctrl.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.materialInst']")
    WebElement selectMINameInClaimJobInfo;

    @FindBy(how = How.XPATH, using = "//input[@name='returnCode_0']")
    WebElement returnCodeValue;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='row.active']")
    WebElement activeCheckBox;

    @FindBy(how = How.XPATH, using = "//div[@class='panel-body']//div[1]/div[2]")
    WebElement claimRepairheaderImporter;

    @FindBy(how = How.XPATH, using = "//div[@class='panel-body']//div[2]/div[2]")
    WebElement claimRepairheaderDealer;

    @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat,'returnList')]/td[1]")
    WebElement returnCodeText;

    @FindBy(how = How.XPATH, using = "//button[@uib-tooltip='Claim - Appeal state log']")
    WebElement claimJobAppealTabLogIcon;

    @FindBy(how = How.XPATH, using = "//td[@ng-switch='vm.topic']")
    WebElement claimJobAppealTabHistory;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='data in vm.simpleList'][1]/td[1]")
    WebElement claimJobAppealTabStatusInHistory;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='data in vm.simpleList'][1]/td[2]")
    WebElement claimJobAppealTabUserIdInHistory;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='data in vm.simpleList'][1]/td[3]")
    WebElement claimJobAppealTabTimeStampInHistory;

    @FindBy(how = How.XPATH, using = "//div[@class='divTableRow']//div[@class='divTableCell ng-binding'][contains(text(),'Hold for crediting')]")
    WebElement holdForCrediting;

    @FindBy(how = How.XPATH, using = "//div[@class='divTableCell ng-binding']//span[@class='ng-binding ng-scope'][1]")
    WebElement holdForCreditingReason;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_caf']")
    WebElement labourCAF;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.debitCodeSummaryDTOList')]/child::td[1]") })
    List<WebElement> debitCodeAssessment;

    @FindBy(how = How.XPATH, using = "//input[@id='material_total_caf']")
    WebElement materialCAF;

    @FindBy(how = How.XPATH, using = "//input[@id='cost_total_caf']")
    WebElement otherCostCAF;

    @FindBy(how = How.XPATH, using = "//input[@id='travel_total_caf']")
    WebElement travelCAF;

    @FindBy(how = How.XPATH, using = "//div[@class='tooltip-inner ng-binding']")
    WebElement originalNetCost;

    @FindBy(how = How.XPATH, using = "(//span[@class='fa fa-warning red_color ng-scope'])[1]")
    WebElement labourTabRedIcon;

    @FindBy(how = How.XPATH, using = "//th[@class='width10 text-nowrap ng-binding ng-scope'][contains(text(),'Net ')]/../../../tbody/tr/td[12]/span")
    WebElement materialTabRedIcon;

    @FindBy(how = How.XPATH, using = "//th[@class='width10 ng-binding ng-scope'][contains(text(),'Rejected')]/../../../tbody/tr/td[08]/span")
    WebElement otherCostTabRedIcon;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.labourList')]/child::td[8]") })
    List<WebElement> labourNet;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.includedMaterialList')]/child::td[9]") })
    List<WebElement> materialNet;

    @FindAll({ @FindBy(xpath = "//tr[contains(@ng-repeat,'.costList')]/child::td[5]") })
    List<WebElement> otherNet;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-if,'travelList')]/../descendant::td[3]") })
    List<WebElement> travelHoursNET;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-if,'travelList')]/../descendant::td[8]") })
    List<WebElement> travelDistanceNET;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-if,'travelList')]/../descendant::td[12]") })
    List<WebElement> otherTravelExpenseNET;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_debit_0']")
    WebElement labourDebitCode;

    @FindBy(how = How.XPATH, using = "//input[@id='material_total_debit_0']")
    WebElement materialDebitCode;

    @FindBy(how = How.XPATH, using = "//input[@id='cost_total_debit_0']")
    WebElement otherCostDebitCode;

    @FindBy(how = How.XPATH, using = "//div[contains(@ng-if,'showFailingReason')]/div[2]")
    WebElement inspectorFailingReason;

    @FindAll({ @FindBy(how = How.XPATH, using = "//div[@ng-if='::ctrl.rules.canChangeStatus']") })
    List<WebElement> totalInspectorReportsMatched;

    @FindAll({ @FindBy(how = How.XPATH, using = "//tr[contains(@ng-repeat, '.matchedInspectorReportList')]/td[5]") })
    List<WebElement> viewInspectorReportslinks;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_total_debit_1']]/span/i")
    WebElement travelCostDebitCodeSelection;

    @FindBy(how = How.ID, using = "isUsed")
    WebElement inspectorReportstatusDropdown;

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='debitCodeSummary in ctrl.claimJob.claimAssessment.debitCodeSummaryDTOList track by $index']/td[1]")
    WebElement debitCodeValueInClaimJob;

    @FindBy(how = How.XPATH, using = "//*[text()='Scenario code']/../../div[2]")
    WebElement scenarioCode;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement closeThePage;

    @FindBy(how = How.ID, using = "updateVehicleInformation")
    WebElement updateVehicleInformationCheckBox;

    @FindBy(how = How.XPATH, using = "//button[text()='Save header']")
    WebElement saveHeaderButton;

    @FindBy(how = How.XPATH, using = "//button[text()='New claim job']")
    WebElement newClaimJobButton;

    @FindBy(how = How.XPATH, using = "//input[@id='vin']")
    WebElement vinValue;

    @FindBy(how = How.XPATH, using = "//label[text()='Marketing type ']/..//following-sibling::div")
    WebElement marketingTypeValue;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-6 col-md-8 col-lg-8 form-control-static ng-binding'])[2]")
    WebElement deliveringImporterValue;

    @FindBy(how = How.XPATH, using = "//label[text()='Delivery date']/..//following-sibling::div")
    WebElement deliveryDateValue;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-6 col-md-8 col-lg-8 form-control-static ng-binding'])[4]")
    WebElement deliveringDealerValue;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-6 col-md-8 col-lg-8 form-control-static ng-binding'])[5]")
    WebElement usingCustomerValue;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-9 col-md-10 col-lg-10 form-control-static ng-binding'])[1]")
    WebElement importerValue;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-sm-9 col-md-10 col-lg-10 form-control-static ng-binding'])[2]")
    WebElement dealerValue;

    @FindBy(how = How.XPATH, using = "//label[text()='Reference no.']/..//following-sibling::div")
    WebElement referenceNoValue;

    @FindBy(how = How.XPATH, using = "//input[@id='repairOrderNo']")
    WebElement repairOrderNoValue;

    @FindBy(how = How.XPATH, using = "//label[text()='Company']/..//following-sibling::div")
    WebElement companyValue;

    @FindBy(how = How.XPATH, using = "//label[text()='Currency']/..//following-sibling::div")
    WebElement currencyValue;

    public static HashMap<String, Object> applicationData = new HashMap<String, Object>();

    public ManageClaimJobs(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * openNotFinishedClaimJobAndVerifyDetails() #Description : Search from list claim job with criteria repairdate, Opens the claim job, Verifies the details
     * 18-12-17 #Input Parameters: Raju 18-05-17
     ****************************************/
    public void openNotFinishedClaimJobAndVerifyDetails() {
        getDataFromNotFinishedTabToVerifyClaimJob();
        verifyClaimJobClaimJobOpenedFromNotFinishedTab(applicationData);
    }

    /****************************************
     * #Description : Updates OtherCost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateClaimJobInformationTab(HashMap<String, Object> manageClaimJobData) {
        companyName = keywords.getTextValue(companyNameElement);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobInformationTabManageClaimJob);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
        if (appBasedUtils.getStringData(manageClaimJobData, "ClaimType").equals("Parts")
                || appBasedUtils.getStringData(manageClaimJobData, "ClaimType").equals("Parts")) {
            keywords.waitForVisibilityOfElement(driver, 10, partsFittedDateElement);
            keywords.input(partsFittedDateElement, DateUtility.getCurrentDate(DD_MM_YYYY), "Parts fitted date");
            keywords.clickOnButton(driver, datePickerButton);
            keywords.clickOnButton(driver, datePickerButton);
            keywords.input(partsMileageElement, appBasedUtils.getStringData(manageClaimJobData, "PartsMileage"), "Parts mileage");
            keywords.focusingOutOfElementUsingAction(driver, partsFittedDateElement, partsMileageElement);
        }
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitForVisibilityOfElement(driver, 5, unplannedStopDropdown);
            keywords.selectVisibleText(unplannedStopDropdown, appBasedUtils.getStringData(manageClaimJobData, "UnplannedStop"));
        }

    }

    public void setUplannedStopInClaimJobInfoTab(HashMap<String, Object> manageClaimJobData) {
        companyName = keywords.getTextValue(companyNameElement);
        keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitForVisibilityOfElement(driver, 5, unplannedStopDropdown);
            keywords.selectVisibleText(unplannedStopDropdown, appBasedUtils.getStringData(manageClaimJobData, "UnplannedStop"));
        }
    }

    /****************************************
     * #Description : Updates OtherCost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateLabourTabInformation(HashMap<String, Object> manageClaimJobData) {
        try {
            String operationNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "OperationNumberLabour").split(";");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, labourTabManageClaimJob);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", labourTabManageClaimJob);
            keywords.clickOnLink(labourTabManageClaimJob, driver);

            WebElement vstCheckBoxElement = vstCheckboxLabourTab.get(0);
            keywords.waitForVisibilityOfElement(driver, 20, vstCheckBoxElement);
            if (appBasedUtils.getStringData(manageClaimJobData, "VST").equalsIgnoreCase("NO")) {
                if (keywords.isCheckboxElementSelected(vstCheckBoxElement)) {
                    keywords.waitTime(2000);
                    keywords.resetCheckboxStatus(vstCheckBoxElement);
                }
                if (!keywords.isCheckboxElementSelected(vstCheckBoxElement)) {
                    APP_LOGS.info("VST checkbox unchecked");
                    keywords.setCheckboxIfNotSelected(mainOperationRadioLabourTab);
                    keywords.waitForVisibilityOfElement(driver, 15, operationNumberLabourTab.get(0));
                    keywords.input(operationNumberLabourTab.get(0), operationNumbers[0], "Operation number");
                    keywords.waitForVisibilityOfElement(driver, 15, operationDescriptionLabourTab.get(0));
                    keywords.input(operationDescriptionLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "OperationDescLabourTab"),
                                   "Operation description");
                    keywords.waitForVisibilityOfElement(driver, 15, hoursLabourTab.get(0));
                    keywords.input(hoursLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "LabourHours"), "Labour hours");
                    keywords.input(quantityLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"), "Labour quantity");
                }
            } else if (appBasedUtils.getStringData(manageClaimJobData, "VST").equalsIgnoreCase("YES")) {
                keywords.input(operationNumberLabourTab.get(0), operationNumbers[0], "Operation number");
                keywords.input(quantityLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"), "Labour quantity");
            }
            enterLabourDetailsForContractClaims(manageClaimJobData);

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /****************************************
     * #Description : Updates opened claim job #Input Parameters:excel data
     ****************************************/
    public void updatePartsClaimJobLabourTabInformation(HashMap<String, Object> manageClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 25, labourTabManageClaimJob);
        keywords.clickOnLink(labourTabManageClaimJob, driver);
        keywords.waitForVisibilityOfElement(driver, 10, operationNumberLabourTab.get(0));
        keywords.setCheckboxIfNotSelected(mainOperationRadioLabourTab);
        keywords.input(operationNumberLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "OperationNumberLabour"), "Operation number");
        keywords.input(operationDescriptionLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "OperationDescLabourTab"),
                       "Operation description");
        keywords.input(hoursLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "LabourHours"), "Labour hours");
        keywords.input(quantityLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"), "Labour quantity");
    }

    /****************************************
     * #Description : Selects a claim job from the claimJob dropsdown and attach it to the claim #Input Parameters: manageClaimJobData
     ****************************************/
    public void selectClaimTypeAndAttachToClaim(HashMap<String, Object> manageClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 10, labourTabManageClaimJob);
        keywords.clickOnButton(driver, claimTypeDropdownButtonElement);
        keywords.waitForVisibilityOfElement(driver, 10, claimTypesDropdownValues.get(1));
        keywords.clickOnLink(getClaimTypeElement(manageClaimJobData), driver);
        keywords.waitForVisibilityOfElement(driver, 10, newClaimJobButtonElement);
        keywords.clickOnButton(driver, newClaimJobButtonElement);
        keywords.waitForVisibilityOfElement(driver, 20, vstCheckboxLabourTab.get(0));
    }

    /****************************************
     * #Description : Updates Material cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateReplacedMaterialClaimJob(HashMap<String, Object> manageClaimJobData) {
        String partPrefixes[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartPrefix").split(";");
        String partNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartNo").split(";");
        keywords.waitForElementToBeClickable(driver, 8, claimJobMaterial, "Material tab");
        keywords.clickOnLink(claimJobMaterial, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.clickOnLink(claimJobMaterial, driver);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterialPrefix);
        keywords.clickOnButton(driver, claimJobMaterialReplaced);
        keywords.input(claimJobMaterialPrefix, appBasedUtils.getStringData(manageClaimJobData, "CausalPartPrefix"), "material prefix");
        keywords.input(claimJobMaterialPartNo, appBasedUtils.getStringData(manageClaimJobData, "CausalPartNo"), "material part nnumber");
        keywords.input(claimJobMaterialserialNo, appBasedUtils.getStringData(manageClaimJobData, "CausalSerialNo"), "serial no.");
        keywords.input(claimJobMaterialdefectCode, appBasedUtils.getStringData(manageClaimJobData, "DefectCode"), "defect code");
        for (int i = 0; i < 1; i++) {
            if (i == 0) {
                keywords.waitTime1(3);
                keywords.setCheckboxIfNotSelected(newMaterialRadioButton.get(i));
            }
            keywords.input(repairedPartPrefixInMaterial.get(i), partPrefixes[0], "PartPrefix");
            keywords.input(repairedPartNumberInMaterial.get(i), partNumbers[0], "Part number");
            keywords.input(repairedSerialNumberInMaterial.get(i), appBasedUtils.getStringData(manageClaimJobData, "ReplacedSerialNo"), "Serial number");
            keywords.input(repairedPartQuantiyInMaterial.get(i), appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"), "Replaced part quantity");
            if ((!appBasedUtils.getStringData(manageClaimJobData, "MaterialPrice").equals("NULL"))
                    && keywords.elementIsDisplayed(repairedPartPriceInMaterial.get(i), "Part price")) {
                keywords.input(repairedPartPriceInMaterial.get(i), appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"),
                               "Replaced part quantity");

            }
        }
    }

    /****************************************
     * #Description : Updates Material cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateRepairedMaterialClaimJob(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.waitForElementToBeClickable(driver, 8, claimJobMaterial, "Material tab");
            keywords.clickOnLink(claimJobMaterial, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.clickOnLink(claimJobMaterial, driver);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterialPrefix);
            keywords.clickOnButton(driver, claimJobMaterialRepaired);
            keywords.input(claimJobMaterialPrefix, appBasedUtils.getStringData(manageClaimJobData, "CausalPartPrefix"), "material prefix");
            keywords.input(claimJobMaterialPartNo, appBasedUtils.getStringData(manageClaimJobData, "CausalPartNo"), "material part nnumber");
            keywords.input(claimJobMaterialserialNo, appBasedUtils.getStringData(manageClaimJobData, "CausalSerialNo"), "serial no.");
            keywords.input(claimJobMaterialdefectCode, appBasedUtils.getStringData(manageClaimJobData, "DefectCode"), "defect code");
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Updates other cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateOtherCostClaimJob(HashMap<String, Object> manageClaimJobData) {
        try {
            String otherCostQuantity[] = appBasedUtils.getStringData(manageClaimJobData, "QuantityOtherCost").split(";");
            keywords.scrollPage(driver, -250);
            keywords.clickOnLink(otherCostTabManageClaimJob, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 4, loadingIcon);
            if (otherCostQuantity.length == 1) {
                keywords.waitForVisibilityOfElement(driver, 10, quantityOtherCostTab.get(0));
                keywords.input(quantityOtherCostTab.get(0), otherCostQuantity[0], "other cost quantity");
                keywords.input(priceOtherCostTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "PriceOtherCost"), "price other cost");
                if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"))) {
                    keywords.input(descriptionOtherCostTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"),
                                   "Other cost description");
                }
                if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "CatergoryOtherCost"))) {
                    keywords.waitForVisibilityOfElement(driver, 10, categoryDropdownOtherCostTab.get(0));
                    keywords.selectVisibleText(categoryDropdownOtherCostTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "CatergoryOtherCost"));
                }
            } else {
                if (quantityOtherCostTab != null && quantityOtherCostTab.size() > 0) {
                    for (int i = 0; i < 3; i++) {
                        keywords.waitForVisibilityOfElement(driver, 10, quantityOtherCostTab.get(i));
                        keywords.input(quantityOtherCostTab.get(i), otherCostQuantity[i], "other cost quantity");
                        keywords.input(priceOtherCostTab.get(i), appBasedUtils.getStringData(manageClaimJobData, "PriceOtherCost"), "price other cost");
                        keywords.input(descriptionOtherCostTab.get(i), appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"),
                                       "Other cost description");
                        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"))) {
                            keywords.input(descriptionOtherCostTab.get(i), appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"),
                                           "Other cost description");
                        }
                        if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "CatergoryOtherCost"))) {
                            keywords.waitForVisibilityOfElement(driver, 10, categoryDropdownOtherCostTab.get(i));
                            keywords.selectVisibleText(categoryDropdownOtherCostTab.get(i),
                                                       appBasedUtils.getStringData(manageClaimJobData, "CatergoryOtherCost"));
                        }
                        if (i < 2 && otherCostQuantity.length != 1)
                            keywords.clickOnButton(driver, addRowButtonOtherCostTab);

                    }
                } else {
                    APP_LOGS.info("No data available to enter.!");
                }
            }

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }

    }

    /****************************************
     * #Description : Updates other cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateRemarksClaimJob(HashMap<String, Object> manageClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 20, remarksTabManageClaimJob);
        keywords.clickOnLink(remarksTabManageClaimJob, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.waitForVisibilityOfElement(driver, 20, complaintRemarksTab);
        keywords.input(complaintRemarksTab, appBasedUtils.getStringData(manageClaimJobData, "ComplaintRemarks"), "Complaint-Remarks");
        keywords.waitForVisibilityOfElement(driver, 20, causeRemarksTab);
        keywords.input(causeRemarksTab, appBasedUtils.getStringData(manageClaimJobData, "CauseRemarks"), "Cause-Remarks");
        keywords.input(correctionRemarksTab, appBasedUtils.getStringData(manageClaimJobData, "CorrectionRemarks"), "Correction-Remarks");
        keywords.scrolltoEndOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, commentRemarksTab);
        keywords.input(commentRemarksTab, appBasedUtils.getStringData(manageClaimJobData, "CommentsRemarks"), "Comments-Remarks");
        keywords.keyTab(commentRemarksTab);
    }

    /****************************************
     * #Description : Updates other cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateRemarksInReleasedClaimJob(HashMap<String, Object> manageClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 10, remarksTabManageClaimJob);
        keywords.clickOnLink(remarksTabManageClaimJob, driver);
        keywords.scrolltoEndOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, commentRemarksTab);
        keywords.input(commentRemarksTab, appBasedUtils.getStringData(manageClaimJobData, "CommentsRemarks"), "Correction-Remarks");
        keywords.keyTab(commentRemarksTab);
        saveClaimjobUpdateClaimJobFlow();
    }

    /****************************************
     * #Description : Updates other cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void updateTravelExpensesClaimJob(HashMap<String, Object> manageClaimJobData) {
        keywords.scrollPage(driver, -550);
        companyName = keywords.getTextValue(companyNameElement);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitForVisibilityOfElement(driver, 20, travelExpensesTabManageClaimJob);
            keywords.clickOnLink(travelExpensesTabManageClaimJob, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 10, travelHoursTravelExpenses);
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "TravelHourTravelExpenses"))) {
                keywords.input(travelHoursTravelExpenses, appBasedUtils.getStringData(manageClaimJobData, "TravelHourTravelExpenses"), "Travel hours");
                keywords.input(distanceAmountTravelExpenses, appBasedUtils.getStringData(manageClaimJobData, "TravelDistanceTravelExpenses"),
                               "Travel distance");
                keywords.input(otherAmountTravelExpenses, appBasedUtils.getStringData(manageClaimJobData, "OtherTravelTravelExpenses"),
                               "Other travel expenses");
            }
        }
    }

    /****************************************
     * #Description : Clicks on "Save claim job" button, and checks for error message on click save claim job button #Input Parameters: excel data
     ****************************************/
    public void saveClaimjobUpdateClaimJobFlow() {
        keywords.waitForVisibilityOfElement(driver, 10, saveClaimJobButton);
        keywords.clickOnButton(driver, saveClaimJobButton);
        if ((appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))
                || (!keywords.isErrorMessageVisible(driver, 25, errorMsgElement, validationMessageClose)))
            if (keywords.elementIsDisplayed(claimJobCheckErrorMessageHeader, "Check error message")) {
                APP_LOGS.info("Claim job is saved but contains the following check errors");
                keywords.printTextValueOFElements(claimJobCheckErrors);
            }
    }

    /****************************************
     * #Description : Clicks on "Save claim job" button, and checks for error message on click save claim job button
     ****************************************/
    public String saveClaimJobNewClaimJobFlow() {
        keywords.scrollPage(driver, -250);
        keywords.waitForVisibilityOfElement(driver, 10, saveClaimJobButton);
        keywords.clickOnButton(driver, saveClaimJobButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        String claimJobStatusInfo = getClaimStatus();
        if (!(claimJobStatusInfo.equals("Draft")) || (keywords.isErrorMessageVisible(driver, 10, errorMsgElement, validationMessageClose)
                || (appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup)))) {
            if (keywords.elementIsDisplayed(claimJobCheckErrorMessageHeader, "Check error message")) {
                APP_LOGS.info("Claim job is saved but contains the following check errors");
                keywords.printTextValueOFElements(claimJobCheckErrors);
            }
            keywords.waitForVisibilityOfElement(driver, 30, claimJobReferenceNo);
            claimReferenceNumber = keywords.getTextValue(claimJobReferenceNo);
            claimJobNumber = keywords.getTextValue(claimJobNo).trim();
            APP_LOGS.info("Claim job created successfully. Reference number: " + claimReferenceNumber + " - " + claimJobNumber);
            ExtentTestManager.getTest().log(Status.INFO, "Claim job created successfully. Reference number: " + claimReferenceNumber + " - " + claimJobNumber);
            ExtentTestManager.getTest().log(Status.INFO, "Claim job status is:" + claimJobStatusInfo);
        }
        return claimReferenceNumber;
    }

    /****************************************
     * #Description : From the list claim job Material instruction-Search result, persists first row data and opens claim job and validates the details
     ****************************************/
    public void searchWithMITypeInMaterialInstructionTab(HashMap<String, Object> listData) {
        // Search with MI type
        getDataFromMaterailInstructionTabAndOpenClaimJob();
        verifiesClaimJobOpenedFromMITab(applicationData);
    }

    /****************************************
     * #Description : From the list claim job Appeal-Search result, persists first row data and opens claim job and validates the details
     ****************************************/
    public void OpenAndUpdateAppealStatusClaimInAppealTab() {
        // Search with Appeal status
        getDataFromAppealTabAndOpenClaimJob();
        verifyTheClaimOpenedFromAppealTab(applicationData);
    }

    /****************************************
     * #Description : From the search result, persists first row data and opens first claim job.
     ****************************************/
    public void getDataFromNotFinishedTabToVerifyClaimJob() {
        try {
            String[] splittedStrings = null;
            applicationData.put("statusClaim", appBasedUtils.extractStringWithoutWhiteSpace(keywords.getAttributeValue(statusFromListClaim, "uib-tooltip")));
            splittedStrings = keywords.getTextValue(importerListClaimJob).split("-");
            applicationData.put("importerListClaimJob", splittedStrings[0].trim());
            splittedStrings = keywords.getTextValue(dealerListClaimJob).split("-");
            applicationData.put("dealerListClaimJob", splittedStrings[0].trim());
            applicationData.put("dealerListClaimJob", splittedStrings[0].trim());
            splittedStrings = ((keywords.getTextValue(claimRefNoFromList).trim()).split("-"));
            applicationData.put("claimRefClaim", splittedStrings[0].trim());
            applicationData.put("claimJobNo", splittedStrings[1].trim());
            applicationData.put("claimTypeFromListClaim", keywords.getTextValue(claimTypeFromListClaim).trim());
            applicationData.put("regDateClaim", keywords.getTextValue(regDateFromListClaim).trim());
            applicationData.put("chassisIdClaim", keywords.getTextValue(chassisIdFromListClaim).trim());
            applicationData.put("marketingTypeFromListClaim", keywords.getTextValue(marketingTypeFromListClaim).trim());
            applicationData.put("grandTotalFromClaim", (keywords.getTextValue(grandTotalFromListClaim).trim()));
            // click on ref no.
            keywords.clickOnButton(driver, claimRefNoFromList);
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(claimJobReferenceNo));
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : From the search result, persists first row data and opens first claim job.
     ****************************************/
    public void getDataFromMaterailInstructionTabAndOpenClaimJob() {
        try {
            String[] splitedString = null;
            applicationData.put("dealerNoMITab", appBasedUtils.extractStringWithoutWhiteSpace(keywords.getTextValue(dealerNoMaterialTab)));
            splitedString = ((keywords.getTextValue(refNoMaterialTab).trim()).split("-"));
            applicationData.put("RefNoMITab", splitedString[0].trim());
            applicationData.put("claimJobNoMITab", splitedString[1].trim());
            applicationData.put("claimTypeNoMITab", keywords.getTextValue(claimTypeMaterialTab).trim());
            applicationData.put("MINameNoMITab", keywords.getTextValue(miNameMaterialTab).trim());
            applicationData.put("registrationDateMITab", keywords.getTextValue(registrationDateMaterialTab).trim());
            keywords.clickOnButton(driver, refNoMaterialTab);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobReferenceNo);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : From the search result, persists first row data and opens first claim job.
     ****************************************/
    public void getDataFromAppealTabAndOpenClaimJob() {
        try {
            String[] splitedStrings = null;
            applicationData.put("statusClaim", keywords.getAttributeValue(statusFromListClaim, "uib-tooltip").trim());
            splitedStrings = keywords.getTextValue(importerListClaimJob).split("-");
            applicationData.put("importerListClaimJob", splitedStrings[0].trim());
            splitedStrings = keywords.getTextValue(dealerListClaimJob).split("-");
            applicationData.put("dealerListClaimJob", splitedStrings[0].trim());
            applicationData.put("dealerListClaimJob", splitedStrings[0].trim());
            splitedStrings = ((keywords.getTextValue(claimRefNoFromList).trim()).split("-"));
            applicationData.put("claimRefClaim", splitedStrings[0].trim());
            applicationData.put("claimJobNo", splitedStrings[1].trim());
            applicationData.put("claimTypeFromListClaim", keywords.getTextValue(claimTypeFromListClaim).trim());
            applicationData.put("regDateClaim", keywords.getTextValue(regDateFromListClaim).trim());
            applicationData.put("chassisIdClaim", keywords.getTextValue(chassisIdFromListClaim).trim());
            applicationData.put("appealStatusAppealTabListClaim", keywords.getTextValue(appealStatusAppealTabListClaim).trim());
            applicationData.put("marketingTypeAppealTabListClaim", keywords.getTextValue(marketingTypeAppealTabListClaim).trim());
            applicationData.put("grandTotalAppealTabListClaim", keywords.getTextValue(grandTotalAppealTabListClaim).trim());
            keywords.clickOnButton(driver, claimRefNoFromList);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobReferenceNo);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Verifies the claim job details against value persisted in the Map-collection
     ****************************************/
    public void verifyClaimJobClaimJobOpenedFromNotFinishedTab(HashMap<String, Object> applicationData) {
        openSelectedClaimJobAssert = new SoftAssert();
        appBasedUtils.asserVerification("Importer", appBasedUtils.getStringData(applicationData, "importerListClaimJob"),
                                        keywords.getTextValue(claimHeaderImporter).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Dealer", appBasedUtils.getStringData(applicationData, "dealerListClaimJob"),
                                        keywords.getTextValue(claimHeaderDealer).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("MarketingType", appBasedUtils.getStringData(applicationData, "marketingTypeFromListClaim"),
                                        keywords.getTextValue(claimHeaderMarketingType).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Claim status", appBasedUtils.getStringData(applicationData, "statusClaim"),
                                        appBasedUtils.extractStringWithoutWhiteSpace(keywords.getTextValue(claimJobStatus)), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Registration date", appBasedUtils.getStringData(applicationData, "regDateClaim"),
                                        (keywords.getTextValue(claimJobRegistrationDate)).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Claim job no. ", appBasedUtils.getStringData(applicationData, "claimJobNo"), keywords.getTextValue(claimHeaderJobNo),
                                        openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Chassis id", appBasedUtils.getStringData(applicationData, "chassisIdClaim").replaceAll("\\s", ""),
                                        (keywords.getTextValue(claimJobChassisId)).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Reference number", appBasedUtils.getStringData(applicationData, "claimRefClaim"),
                                        (keywords.getTextValue(claimJobReferenceNo)).trim(), openSelectedClaimJobAssert);
        keywords.scrolltoEndOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobGrandTotal);
        appBasedUtils.asserVerification("Grand total", appBasedUtils.getStringData(applicationData, "grandTotalFromClaim"),
                                        (keywords.getTextValue(claimJobGrandTotal)).trim(), openSelectedClaimJobAssert);

        // click on history tab to verify the claim status
        keywords.clickOnButton(driver, claimJobHistory);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobHistoryJobStatus);
        appBasedUtils.asserVerification("History tab-claim Status", appBasedUtils.getStringData(applicationData, "statusClaim"),
                                        keywords.getTextValue(claimJobHistoryJobStatus), openSelectedClaimJobAssert);
        openSelectedClaimJobAssert.assertAll();

    }

    /****************************************
     * #Description : Verifies the claim job details against value persisted in the Map-collection
     ****************************************/
    public void verifiesClaimJobOpenedFromMITab(HashMap<String, Object> applicationData) {
        openSelectedClaimJobAssert = new SoftAssert();
        keywords.waitForVisibilityOfElement(driver, 10, claimJobReferenceNo);
        appBasedUtils.asserVerification("DealerNumber", appBasedUtils.getStringData(applicationData, "dealerNoMITab"),
                                        keywords.getTextValue(claimHeaderDealer).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Registration number", appBasedUtils.getStringData(applicationData, "registrationDateMITab"),
                                        keywords.getTextValue(claimJobRegistrationDate).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Reference number", appBasedUtils.getStringData(applicationData, "RefNoMITab"),
                                        keywords.getTextValue(claimJobReferenceNo).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Claim job number", appBasedUtils.getStringData(applicationData, "claimJobNoMITab"),
                                        keywords.getTextValue(claimHeaderJobNo).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("MI Name", appBasedUtils.getStringData(applicationData, "MINameNoMITab"),
                                        keywords.getTextValue(claimJobMINameMaterialTab).trim(), openSelectedClaimJobAssert);
        openSelectedClaimJobAssert.assertAll();
    }

    /****************************************
     * #Description : Updates Labour, Material, OtherCost and Travel expense tabs and perform save operation.
     ****************************************/
    public void updateInformationInClaimTabs(HashMap<String, Object> manageClaimJobData) {
        companyName = keywords.getTextValue(companyNameElement);
        updateLabourTabInformation(manageClaimJobData);
        updateReplacedMaterialClaimJob(manageClaimJobData);
        updateOtherCostClaimJob(manageClaimJobData);
        updateRemarksClaimJob(manageClaimJobData);
        if (companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)))
            updateTravelExpensesClaimJob(manageClaimJobData);
    }

    /****************************************
     * #Description : Updates Labour, Material, OtherCost and Travel expense tabs and perform save operation.
     ****************************************/
    public void updateNoVehicleClaimJobTabs(HashMap<String, Object> manageClaimJobData) {
        companyName = keywords.getTextValue(companyNameElement);
        updateClaimJobInformationTab(manageClaimJobData);
        updatePartsClaimJobLabourTabInformation(manageClaimJobData);
        if ((appBasedUtils.getStringData(manageClaimJobData, "Repaired").equalsIgnoreCase("Yes"))) {
            updateRepairedMaterialClaimJob(manageClaimJobData);
        } else {
            updateReplacedMaterialClaimJob(manageClaimJobData);
        }
        if (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
            attachDocumentToClaimJob(manageClaimJobData);
        }
        updateOtherCostClaimJob(manageClaimJobData);
        updateRemarksClaimJob(manageClaimJobData);
    }

    /****************************************
     * #Description : Adds new claim job to existing claim #Input Parameters: excel data
     ****************************************/
    public void addNewVehilceClaimJobForExistingClaim(HashMap<String, Object> manageClaimJobData) {
        selectClaimTypeAndAttachToClaim(manageClaimJobData);
        updateLabourTabInformation(manageClaimJobData);
        updateReplacedMaterialClaimJob(manageClaimJobData);
        updateOtherCostClaimJob(manageClaimJobData);
        updateRemarksClaimJob(manageClaimJobData);
    }

    /****************************************
     * #Description : Adds new claim job to existing claim #Input Parameters: excel data
     ****************************************/
    public void addNewNoVehilceClaimJobForExistingClaim(HashMap<String, Object> manageClaimJobData) {
        selectClaimTypeAndAttachToClaim(manageClaimJobData);
        updateLabourTabInformation(manageClaimJobData);
        updateReplacedMaterialClaimJob(manageClaimJobData);
        updateOtherCostClaimJob(manageClaimJobData);
        updateRemarksClaimJob(manageClaimJobData);
    }

    /****************************************
     * #Description : Verifies the claim job details against value persisted in the Map-collection #Input Parameters:excel data
     ****************************************/
    public void verifyTheClaimOpenedFromAppealTab(HashMap<String, Object> applicationData) {
        openSelectedClaimJobAssert = new SoftAssert();
        appBasedUtils.asserVerification("Importer", appBasedUtils.getStringData(applicationData, "importerListClaimJob"),
                                        keywords.getTextValue(claimHeaderImporter).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Dealer", appBasedUtils.getStringData(applicationData, "dealerListClaimJob"),
                                        keywords.getTextValue(claimHeaderDealer).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Registration date", appBasedUtils.getStringData(applicationData, "regDateClaim"),
                                        (keywords.getTextValue(claimJobRegistrationDate)).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Claim job number", appBasedUtils.getStringData(applicationData, "claimJobNo"), keywords.getTextValue(claimHeaderJobNo),
                                        openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Chassis id", appBasedUtils.getStringData(applicationData, "chassisIdClaim").replaceAll("\\s", ""),
                                        (keywords.getTextValue(claimJobChassisId)).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Reference number", appBasedUtils.getStringData(applicationData, "claimRefClaim"),
                                        (keywords.getTextValue(claimJobReferenceNo)).trim(), openSelectedClaimJobAssert);
        appBasedUtils.asserVerification("Marketing type", appBasedUtils.getStringData(applicationData, "marketingTypeAppealTabListClaim"),
                                        keywords.getTextValue(claimHeaderMarketingType).trim(), openSelectedClaimJobAssert);
        keywords.scrolltoEndOfPage(driver);
        keywords.scrollPage(driver, 550);
        appBasedUtils.asserVerification("Grand total", appBasedUtils.getStringData(applicationData, "grandTotalAppealTabListClaim"),
                                        (keywords.getTextValue(claimJobGrandTotal)).trim(), openSelectedClaimJobAssert);

        // click on history tab to verify the claim status
        keywords.clickOnButton(driver, claimJobHistory);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobHistoryJobStatus);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(applicationData, "statusClaim"), keywords.getTextValue(claimJobHistoryJobStatus),
                                        openSelectedClaimJobAssert);

        keywords.clickOnButton(driver, remarksTabManageClaimJob);
        keywords.waitTime(1000);
        try {
            if ((appBasedUtils.getStringData(applicationData, "appealStatusAppealTabListClaim").trim()).equalsIgnoreCase("Appeal closed")) {
                appBasedUtils.asserVerification(appBasedUtils.getStringData(applicationData, "appealStatusAppealTabListClaim"),
                                                keywords.getTextValue(claimJobAppealStatusClosed), openSelectedClaimJobAssert);
            } else if (((appBasedUtils.getStringData(applicationData, "appealStatusAppealTabListClaim").trim()).equalsIgnoreCase("Appeal request"))
                    || ((appBasedUtils.getStringData(applicationData, "appealStatusAppealTabListClaim").trim()).equalsIgnoreCase("Appeal response"))) {
                String appealDropdownFirstValue = keywords.getFirstSelectedItemFromDropDown(appealStatusAppealTab);
                appBasedUtils.asserVerification(appBasedUtils.getStringData(applicationData, "appealStatusAppealTabListClaim"), appealDropdownFirstValue,
                                                openSelectedClaimJobAssert);
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "Appeal status did not match");
                Assert.fail();
            }
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
        openSelectedClaimJobAssert.assertAll();
    }

    /****************************************
     * #Description : selects a claim job from claitype dropdown element. #Input Parameters: excel data
     ****************************************/
    public WebElement getClaimTypeElement(HashMap<String, Object> mangageClaimJobData) {
        try {
            for (int i = 0; i < claimTypesDropdownValues.size(); i++) {

                if (keywords.getTextValue(claimTypesDropdownValues.get(i)).equals(mangageClaimJobData.get("ClaimType")))
                    return claimTypesDropdownValues.get(i);
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Element not found");
            APP_LOGS.info("Element  not found");
            Assert.fail("Element not found");
        }
        return null;
    }

    /****************************************
     * #Description : Verifies Labour net total in Labour tab and Claim job information tab,whether it matches or not. #Input Parameters: excel data
     ****************************************/
    public void verifyLabourNetTotalInLabourTabAndClaimJobInfomationTab() {
        keywords.waitForVisibilityOfElement(driver, 10, labourTabManageClaimJob);
        keywords.clickOnLink(labourTabManageClaimJob, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.waitForVisibilityOfElement(driver, 10, labourNetTotalLabourTabElement);
        String labourNetTotalLabourTab = keywords.getTextValue(labourNetTotalLabourTabElement);
        APP_LOGS.info("Labour net total in Labour tab: " + labourNetTotalLabourTab);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobInformationTabManageClaimJob);
        keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
        keywords.waitForVisibilityOfElement(driver, 10, labourNetTotalClaimJobInformationTabElement);
        String labourNetTotalClaimJobInfoTab = keywords.getTextValue(labourNetTotalClaimJobInformationTabElement);
        APP_LOGS.info("Labour net total in claim job informaiton tab: " + labourNetTotalClaimJobInfoTab);
        Assert.assertEquals(labourNetTotalLabourTab, labourNetTotalClaimJobInfoTab);
    }

    /****************************************
     * #Description : Verifies material net total in Labour tab and Claim job information tab, ,whether it matches or not. #Input Parameters: excel data
     ****************************************/
    public void verifyMaterialNetTotalInMaterialTabAndClaimJobInfomationTab() {
        keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterial);
        keywords.clickOnLink(claimJobMaterial, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.scrolltoEndOfPage(driver);
        keywords.waitForVisibilityOfElement(driver, 10, materialNetTotalMaterialTabElement);
        String materialrNetTotalMaterialTab = keywords.getTextValue(materialNetTotalMaterialTabElement);
        APP_LOGS.info("Material net total in material tab: " + materialrNetTotalMaterialTab);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobInformationTabManageClaimJob);
        keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
        keywords.waitForVisibilityOfElement(driver, 10, materialNetTotalClaimJobInformationTabElement);
        String materialNetTotalClaimJobInfoTab = keywords.getTextValue(materialNetTotalClaimJobInformationTabElement);
        APP_LOGS.info("Labour net total in claim job informaiton tab: " + materialNetTotalClaimJobInfoTab);
        Assert.assertEquals(materialrNetTotalMaterialTab, materialNetTotalClaimJobInfoTab);
    }

    /****************************************
     * #Description : Verifies material net total in Labour tab and Claim job information tab, ,whether it matches or not. #Input Parameters: excel data
     ****************************************/
    public void verifyOtherCostNetTotalInOtherCostTabAndClaimJobInfomationTab() {
        try {
            keywords.waitForVisibilityOfElement(driver, 15, otherCostTabManageClaimJob);
            keywords.clickOnButton(driver, otherCostTabManageClaimJob);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.scrolltoEndOfPage(driver);
            keywords.waitTime(300);
            keywords.waitForVisibilityOfElement(driver, 15, otherCostNetCost.get(0));
            String[] otherCostNetCostEntries = new String[otherCostNetCost.size()];
            for (int index = 0; index < otherCostNetCost.size(); index++) {
                otherCostNetCostEntries[index] = keywords.getTextValue(otherCostNetCost.get(index));
            }
            String otherCostNetCostInOtherCostTab = appBasedUtils.addDoubleValueInString(otherCostNetCostEntries);
            APP_LOGS.info("Material net total in material tab: " + otherCostNetCostInOtherCostTab);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobInformationTabManageClaimJob);
            keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 10, otherCostNetTotablClaimJobInformationTabElement);
            String otherCostTotalInClaimJobInfoTab = keywords.getTextValue(otherCostNetTotablClaimJobInformationTabElement);
            APP_LOGS.info("Labour net total in claim job informaiton tab: " + otherCostTotalInClaimJobInfoTab);
            Assert.assertEquals(otherCostNetCostInOtherCostTab, otherCostTotalInClaimJobInfoTab);
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Adds number of new labours to labour tab which is mentioned in the excel sheet #Input Parameters: excel data
     ****************************************/
    public void addNewLabourDetailsToClaim(HashMap<String, Object> manageClaimJobData) {
        String operationNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "OperationNumberLabour").split(";");
        for (int index = 1; index < operationNumbers.length; index++)
            addNewInLabourTab(appBasedUtils.getStringData(manageClaimJobData, "VST"), operationNumbers[index],
                              appBasedUtils.getStringData(manageClaimJobData, "OperationDescLabourTab"),
                              appBasedUtils.getStringData(manageClaimJobData, "LabourHours"),
                              appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"));
    }

    /****************************************
     * #Description : Adds a new labours to labour tab. #Input Parameters: excel data
     ****************************************/
    public void addNewInLabourTab(String IsVST, String operationNumber, String operationDescription, String operationHours, String operationQuantity) {
        try {
            WebElement vstCheckBoxElement;
            WebElement operationNumberElement;
            WebElement operationDescriptionElement;
            WebElement operationHoursElement;
            WebElement operationQuantiy;
            keywords.waitForVisibilityOfElement(driver, 10, labourTabManageClaimJob);
            keywords.clickOnLink(labourTabManageClaimJob, driver);
            keywords.waitForVisibilityOfElement(driver, 10, addRowButtonLabourTab);
            keywords.clickOnButton(driver, addRowButtonLabourTab);
            keywords.waitTime(500);
            keywords.waitForVisibilityOfElement(driver, 10, vstCheckboxLabourTab.get(vstCheckboxLabourTab.size() - 1));
            vstCheckBoxElement = vstCheckboxLabourTab.get(vstCheckboxLabourTab.size() - 1);
            if (IsVST.equalsIgnoreCase("NO")) {
                if (keywords.isCheckboxElementSelected(vstCheckBoxElement)) {
                    keywords.waitTime(500);
                    keywords.resetCheckboxStatus(vstCheckBoxElement);
                }
                if (!keywords.isCheckboxElementSelected(vstCheckBoxElement)) {
                    operationNumberElement = operationNumberLabourTab.get(operationNumberLabourTab.size() - 1);
                    keywords.waitForVisibilityOfElement(driver, 30, operationNumberElement);
                    keywords.input(operationNumberElement, operationNumber, "Operation number");
                    operationDescriptionElement = operationDescriptionLabourTab.get(operationDescriptionLabourTab.size() - 1);
                    keywords.input(operationDescriptionElement, operationDescription, "Operation description");
                    keywords.waitTime(500);
                    keywords.waitForVisibilityOfElement(driver, 30, hoursLabourTab.get(hoursLabourTab.size() - 1));
                    operationHoursElement = hoursLabourTab.get(hoursLabourTab.size() - 1);
                    keywords.input(operationHoursElement, operationHours, "Labour hours");
                    operationQuantiy = quantityLabourTab.get(quantityLabourTab.size() - 1);
                    keywords.input(operationQuantiy, operationQuantity, "Labour quantity");
                } else
                    APP_LOGS.info("unchecking VST checkbox is failed");
            } else if (IsVST.equalsIgnoreCase("YES")) {
                operationNumberElement = operationNumberLabourTab.get(operationNumberLabourTab.size() - 1);
                keywords.input(operationNumberElement, operationNumber, "Operation number");
                operationQuantiy = quantityLabourTab.get(quantityLabourTab.size() - 1);
                keywords.input(operationQuantiy, operationQuantity, "Labour quantity");
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Adds number of new material to material tab which is mentioned in the excel sheet #Input Parameters: excel data
     ****************************************/
    public void addNewMaterialDetailsToClaim(HashMap<String, Object> manageClaimJobData) {
        String partPrefixes[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartPrefix").split(";");
        String partNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartNo").split(";");
        for (int rowTobeAdded = 0; rowTobeAdded < partPrefixes.length; rowTobeAdded++) {
            addNewMaterialInMaterialTab(manageClaimJobData, partPrefixes[rowTobeAdded], partNumbers[rowTobeAdded]);
        }
    }

    /****************************************
     * #Description : Adds a new material to material tab #Input Parameters: excel data
     ****************************************/
    public void addNewMaterialInMaterialTab(HashMap<String, Object> manageClaimJobData, String partPrefix, String partNumber) {
        try {
            keywords.clickOnLink(claimJobMaterial, driver);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterialPrefix);
            WebElement replacedPartPrefix;
            WebElement replacedPartNumber;
            WebElement replacedPartSerialNumber;
            WebElement replacedPartQuantity;
            WebElement replacedPartPrice;
            keywords.waitForVisibilityOfElement(driver, 10, addRowButtonMaterialTab);
            keywords.clickOnButton(driver, addRowButtonMaterialTab);
            keywords.waitTime(50);
            keywords.waitForVisibilityOfElement(driver, 10, repairedPartPrefixInMaterial.get(repairedPartPrefixInMaterial.size() - 1));
            replacedPartPrefix = repairedPartPrefixInMaterial.get(repairedPartPrefixInMaterial.size() - 1);
            replacedPartNumber = repairedPartNumberInMaterial.get(repairedPartNumberInMaterial.size() - 1);
            replacedPartSerialNumber = repairedSerialNumberInMaterial.get(repairedSerialNumberInMaterial.size() - 1);
            replacedPartQuantity = repairedPartQuantiyInMaterial.get(repairedPartQuantiyInMaterial.size() - 1);
            keywords.input(replacedPartPrefix, partPrefix, "PartPrefix");
            keywords.input(replacedPartNumber, partNumber, "Part number");
            keywords.input(replacedPartSerialNumber, appBasedUtils.getStringData(manageClaimJobData, "ReplacedSerialNo"), "Serial number");
            keywords.input(replacedPartQuantity, appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"), "Replaced part quantity");
            if ((!appBasedUtils.getStringData(manageClaimJobData, "MaterialPrice").equals("NULL"))
                    && keywords.elementIsDisplayed(repairedPartPriceInMaterial.get(repairedPartPriceInMaterial.size() - 1), "Part price")) {
                replacedPartPrice = repairedPartPriceInMaterial.get(repairedPartPriceInMaterial.size() - 1);
                keywords.input(replacedPartPrice, appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"), "Replaced part quantity");
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Adds number of new entry to other cost tab which is mentioned in the excel sheet #Input Parameters: excel data
     ****************************************/
    public void addNewEntryInOtherCost(HashMap<String, Object> manageClaimJobData) {
        String otherCostQuantity[] = appBasedUtils.getStringData(manageClaimJobData, "QuantityOtherCost").split(";");
        for (int rowTobeAdded = 0; rowTobeAdded < otherCostQuantity.length; rowTobeAdded++) {
            addNewRownOtherCostTab(manageClaimJobData, otherCostQuantity[rowTobeAdded]);
            if (rowTobeAdded > 0) {
                keywords.clickOnButton(driver, addRowButtonOtherCostTab);
            }
        }
    }

    /****************************************
     * #Description : Adds number of new material to material tab which is mentioned in the excel sheet #Input Parameters: excel data
     ****************************************/
    public void addNewRownOtherCostTab(HashMap<String, Object> manageClaimJobData, String Quantity) {
        try {
            WebElement quantityOtherCost;
            WebElement DescOtherCost;
            WebElement PriceOtherCost;
            WebElement CategoryOtherCost;
            keywords.clickOnLink(otherCostTabManageClaimJob, driver);
            keywords.waitForVisibilityOfElement(driver, 10, addRowButtonOtherCostTab);
            keywords.waitForVisibilityOfElement(driver, 10, quantityOtherCostTab.get(quantityOtherCostTab.size() - 1));
            quantityOtherCost = quantityOtherCostTab.get(quantityOtherCostTab.size() - 1);
            keywords.input(quantityOtherCost, Quantity, "other cost quantity");
            PriceOtherCost = priceOtherCostTab.get(priceOtherCostTab.size() - 1);
            keywords.input(PriceOtherCost, appBasedUtils.getStringData(manageClaimJobData, "PriceOtherCost"), "price other cost");
            DescOtherCost = descriptionOtherCostTab.get(descriptionOtherCostTab.size() - 1);
            keywords.input(DescOtherCost, appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"), "Other cost description");
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "CatergoryOtherCost"))) {
                CategoryOtherCost = categoryDropdownOtherCostTab.get(categoryDropdownOtherCostTab.size() - 1);
                keywords.waitForVisibilityOfElement(driver, 10, CategoryOtherCost);
                keywords.selectVisibleText(CategoryOtherCost, appBasedUtils.getStringData(manageClaimJobData, "CatergoryOtherCost"));
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    public void validateIfCategoryisDisabledInOtherCostTab() {
        for (int i = 0; i < categoryDropdownOtherCostTab.size(); i++) {
            if (!keywords.elementIsEnabled(categoryDropdownOtherCostTab.get(i))) {
                APP_LOGS.info("OtherCost details are saved withOut SysParameter. i.e ApplyVATBasedOnMaterial is set to false in DB");
            }
        }
    }

    /****************************************
     * #Description : Copies existing material by number of times mentioned in the excel data. #Input Parameters: excel data
     ****************************************/
    public void copyMaterialDetailsInClaim(HashMap<String, Object> manageClaimJobData) {
        String partPrefixes[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartPrefix").split(";");
        for (int rowTobeAdded = 0; rowTobeAdded < partPrefixes.length; rowTobeAdded++) {
            copyMaterialInMaterialTab(manageClaimJobData);
        }
    }

    /****************************************
     * #Description : Copies the existing metial and enters the material quality. #Input Parameters: excel data
     ****************************************/
    public void copyMaterialInMaterialTab(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.clickOnLink(claimJobMaterial, driver);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterialPrefix);
            WebElement replacedPartSerialNumber;
            WebElement replacedPartQuantity;
            WebElement replacedPartPrice;
            keywords.waitForVisibilityOfElement(driver, 10, copyRowButtonMaterialTab);
            keywords.clickOnButton(driver, copyRowButtonMaterialTab);
            keywords.waitTime(500);
            keywords.waitForVisibilityOfElement(driver, 10, repairedSerialNumberInMaterial.get(repairedSerialNumberInMaterial.size() - 1));
            replacedPartSerialNumber = repairedSerialNumberInMaterial.get(repairedSerialNumberInMaterial.size() - 1);
            replacedPartQuantity = repairedPartQuantiyInMaterial.get(repairedPartQuantiyInMaterial.size() - 1);
            keywords.input(replacedPartSerialNumber, appBasedUtils.getStringData(manageClaimJobData, "ReplacedSerialNo"), "Serial number");
            keywords.input(replacedPartQuantity, appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"), "Replaced part quantity");
            if ((!appBasedUtils.getStringData(manageClaimJobData, "MaterialPrice").equals("NULL"))
                    && keywords.elementIsDisplayed(repairedPartPriceInMaterial.get(repairedPartPriceInMaterial.size() - 1), "Part price")) {
                replacedPartPrice = repairedPartPriceInMaterial.get(repairedPartPriceInMaterial.size() - 1);
                keywords.input(replacedPartPrice, appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"), "Replaced part quantity");
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Attaches the documents to claim jobs #Input Parameters: excel data
     ****************************************/
    public void attachDocumentToClaimJob(HashMap<String, Object> manageClaimJobData) {
        try {
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            String sPathSep = System.getProperty("file.separator");
            String documents[] = appBasedUtils.getStringData(manageClaimJobData, "Attachments").split(";");
            keywords.waitForVisibilityOfElement(driver, 20, attachmentTabManageClaimJob);
            keywords.clickOnButton(driver, attachmentTabManageClaimJob);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, expectAttachmentCheckBoxAttachmentTab);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", expectAttachmentCheckBoxAttachmentTab);

            keywords.setCheckboxIfNotSelected(expectAttachmentCheckBoxAttachmentTab);
            keywords.waitTime(200);
            if (keywords.isCheckboxElementSelected(expectAttachmentCheckBoxAttachmentTab)) {
                for (int index = 0; index < documents.length; index++) {
                    driver.switchTo().defaultContent();
                    File documentLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "Attachments" + sPathSep
                            + documents[index]);
                    if (!keywords.elementIsDisplayed(attachmentLimitMessage, "Attachment limit message")) {
                        keywords.clickOnLink(browseButtonAttachement, driver);
                        pdfReadAndValidate.uploadDocuments(baseclass.getBrowserType(), documentLocation);
                    }
                }
            } else
                APP_LOGS.info("Expected attachment checkbox is not selected");
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : After claim job saved, verifies the uploaded documents #Input Parameters: excel data
     ****************************************/
    public void verifyUploadedAttachments(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.waitForVisibilityOfElement(driver, 20, attachmentTabManageClaimJob);
            keywords.clickOnLink(attachmentTabManageClaimJob, driver);
            APP_LOGS.info("Uploaded attachements are:");
            keywords.printTextValueOFElements(uploadedAttachments);
            for (int index = 0; index < uploadedAttachments.size(); index++) {
                if (appBasedUtils.getStringData(manageClaimJobData, "Attachments").contains(keywords.getTextValue(uploadedAttachments.get(index)))) {
                    APP_LOGS.info("Uploaded attachement" + (index + 1) + " is verified.");

                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /****************************************
     * ; #Description : Releases the claim job, after release if claim job is not in "Ready for crediting" or "auto failed" refreshes the claim job
     ****************************************/
    public void releaseClaimjob() {

        try {
            keywords.scrollPage(driver, -250);
            if (!(getClaimStatus().equals("Error"))) {
                keywords.clickOnButton(driver, releaseClaimJobButton);
                keywords.waitTime1(2);
                if (userGroup.equalsIgnoreCase("dealer")) {
                    keywords.handleModalDailogues(driver);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 15, loadingIcon);// 25
                    keywords.waitForVisibilityOfElement(driver, 30, releaseClaimJobAlertMessage);// 30
                    keywords.waitTime1(4);
                    keywords.clickOnButton(driver, releaseClaimJobAlertMessage);
                    keywords.clickOnButton(driver, releaseClaimJobYesButton);
                    String[] scode = keywords.getTextValue(scenarioCode).split(" ");
                    scenarioValue = scode[0];
                }
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 25, loadingIcon);
                // keywords.waitForVisibilityOfElement(driver, 10, claimJobStatus);
                // String claimJobStatusInfo = keywords.removeTrailingAndLeadingWhiteSpace(keywords.getTextValue(claimJobStatus));
                String claimJobStatusInfo = getClaimStatus();
                if ((("Autofailed,Readyforcrediting,ReleasedOK".contains(claimJobStatusInfo.replaceAll("\\s", ""))))
                        || (appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))
                        || (keywords.isErrorMessageVisible(driver, 15, errorMsgElement, validationMessageClose)
                                || (!appBasedUtils.captureErrorLogMessages(driver, errorIconLink, firstErrorMessageInPopup)))) {
                    keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
                    keywords.waitTime1(2);
                    // keywords.waitForVisibilityOfElement(driver, 10, claimJobStatus);
                    // claimJobStatusInfo = keywords.removeWhiteSpace(keywords.getTextValue(claimJobStatus));
                    if (!("Autofailed,Readyforcrediting".contains(claimJobStatusInfo.replaceAll("\\s", ""))))
                        reloadClaimJob();
                    claimJobStatusInfo = keywords.removeTrailingAndLeadingWhiteSpace(keywords.getTextValue(claimJobStatus));
                    APP_LOGS.info("Claim released successfully. Claim status is: " + claimJobStatusInfo);
                    ExtentTestManager.getTest().log(Status.INFO, "Claim released successfully. Claim status is: " + claimJobStatusInfo);
                    keywords.wait(2);
                }
            } else
                APP_LOGS.info("Release button is not enabled");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /****************************************
     * #Description : Once claim job is released, updated appeal status to claim job.
     ****************************************/
    public void updateAppealStatusOfClaimJob(HashMap<String, Object> newClaimJobData) {
        keywords.scrollPage(driver, -250);
        keywords.waitForVisibilityOfElement(driver, 10, remarksTabManageClaimJob);
        keywords.clickOnButton(driver, remarksTabManageClaimJob);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobAppealStatus);
        keywords.selectVisibleText(claimJobAppealStatus, newClaimJobData.get("AppealStatus"));
        keywords.waitForVisibilityOfElement(driver, 10, saveClaimJobButton);
        keywords.clickOnButton(driver, saveClaimJobButton);
    }

    /****************************************
     * #Description : Reloades the claim job.
     ****************************************/
    public void reloadClaimJob() {
        keywords.waitForVisibilityOfElement(driver, 10, claimJobHeader);
        keywords.clickOnButton(driver, claimJobHeader);
        keywords.waitForVisibilityOfElement(driver, 10, claimRepairHeaderInformationElement);
        keywords.clickOnButton(driver, firstClaimJob);
        keywords.waitForVisibilityOfElement(driver, 10, claimJobInformationTabManageClaimJob);
    }

    public String getClaimStatus() {
        try {
            keywords.waitForVisibilityOfElement(driver, 15, claimJobInformationTabManageClaimJob);
            keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobStatus);
            return keywords.removeTrailingAndLeadingWhiteSpace(keywords.getTextValue(claimJobStatus));
        } catch (Exception e) {
            APP_LOGS.info(e);
        }
        return null;
    }

    public void verifyFieldsAndSupplyVSTLabourDetails(HashMap<String, Object> manageClaimJobData) {
        try {
            String operationNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "OperationNumberLabour").split(";");
            keywords.waitForVisibilityOfElement(driver, 15, labourTabManageClaimJob);
            keywords.scrollPage(driver, -250);
            keywords.clickOnLink(labourTabManageClaimJob, driver);
            WebElement vstCheckBoxElement = vstCheckboxLabourTab.get(0);
            keywords.waitForVisibilityOfElement(driver, 20, vstCheckBoxElement);
            if (appBasedUtils.getStringData(manageClaimJobData, "VST").equalsIgnoreCase("YES")) {
                boolean vstCheckboxStats = keywords.isCheckboxElementSelected(vstCheckBoxElement);
                if (vstCheckboxStats) {
                    APP_LOGS.info("VST checkbox status: " + vstCheckboxStats);
                    keywords.input(operationNumberLabourTab.get(0), operationNumbers[0], "Operation number");
                    keywords.input(quantityLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"), "Labour quantity");
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Updates Material cost in opened claim job #Input Parameters: excel data
     ****************************************/
    public void verifyPartPrefixAndEnterValues(HashMap<String, Object> manageClaimJobData) {
        try {
            String partPrefixes[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartPrefix").split(";");
            String partNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartNo").split(";");
            keywords.clickOnLink(claimJobMaterial, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterialPrefix);
            keywords.clickOnButton(driver, claimJobMaterialReplaced);
            String prefilledCausalPartPrefix = keywords.getAttributeValue(claimJobMaterialPrefix, "value");
            APP_LOGS.info("Prefilled causal part prefix value is: " + prefilledCausalPartPrefix);
            if (prefilledCausalPartPrefix.equals(null) || prefilledCausalPartPrefix.equals("")) {
                keywords.input(claimJobMaterialPrefix, appBasedUtils.getStringData(manageClaimJobData, "CausalPartPrefix"), "material prefix");
            }
            keywords.input(claimJobMaterialPartNo, appBasedUtils.getStringData(manageClaimJobData, "CausalPartNo"), "material part nnumber");
            keywords.input(claimJobMaterialserialNo, appBasedUtils.getStringData(manageClaimJobData, "CausalSerialNo"), "serial no.");
            keywords.input(claimJobMaterialdefectCode, appBasedUtils.getStringData(manageClaimJobData, "DefectCode"), "defect code");
            for (int i = 0; i < 1; i++) {
                if (i == 0) {
                    keywords.setCheckboxIfNotSelected(newMaterialRadioButton.get(i));
                }
                APP_LOGS.info("Prefilled replaced part prefix value is: " + keywords.getAttributeValue(repairedPartPrefixInMaterial.get(0), "value"));
                keywords.input(repairedPartPrefixInMaterial.get(i), partPrefixes[0], "PartPrefix");
                keywords.input(repairedPartNumberInMaterial.get(i), partNumbers[0], "Part number");
                keywords.input(repairedSerialNumberInMaterial.get(i), appBasedUtils.getStringData(manageClaimJobData, "ReplacedSerialNo"), "Serial number");
                keywords.input(repairedPartQuantiyInMaterial.get(i), appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"),
                               "Replaced part quantity");
                APP_LOGS.info("Is part price field enabled? : " + keywords.elementIsDisplayed(repairedPartPriceElement, "Part price"));
                if ((!appBasedUtils.getStringData(manageClaimJobData, "MaterialPrice").equals("NULL"))
                        && keywords.elementIsDisplayed(repairedPartPriceInMaterial.get(i), "Part price")) {
                    keywords.input(repairedPartPriceInMaterial.get(i), appBasedUtils.getStringData(manageClaimJobData, "MaterialQuantity"),
                                   "Replaced part quantity");
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    /****************************************
     * #Description : Clicks on "Delete claim job" button, and Does advanced search with reference number
     ****************************************/
    public void deleteClaimJob() {
        try {
            String claimJobStatus = getClaimStatus();
            if (claimJobStatus.equals("Error") || claimJobStatus.equals("Validated Ok") || claimJobStatus.equals("Draft")
                    || claimJobStatus.equals("Returned")) {
                keywords.waitForElementToBeClickable(driver, 10, deleteClaimJobButtonElement);
                keywords.clickOnButton(driver, deleteClaimJobButtonElement);
                keywords.waitTime1(2);
                keywords.handleModalDailogues(driver);
                Assert.assertEquals("You are about to delete the last claim job for this repair header. The deletion will also remove the repairing header.",
                                    keywords.getTextValue(claimJobDeleteModalMessage));
                keywords.waitTime1(2);
                keywords.clickOnButton(driver, claimJobDeleteModalYesButton);
                keywords.waitTime1(2);
                AdvanceSearch advancedSearch = new AdvanceSearch(driver);
                advancedSearch.navigateToAdvanceSearch();
                boolean deleteClaimJobStatus = advancedSearch.searchClaimUsingRefNumberAndReturnSearchStatus(claimReferenceNumber, claimJobNumber);
                if (deleteClaimJobStatus == false) {
                    APP_LOGS.info("claim job is deleted successfully. Reference number:" + claimReferenceNumber + ", JobNumber:" + claimJobNumber);
                    ExtentTestManager.getTest().log(Status.INFO,
                                                    "claim job is deleted successfully. Reference number: " + claimReferenceNumber + " - " + claimJobNumber);
                    Assert.assertFalse(deleteClaimJobStatus);
                } else
                    APP_LOGS.info("claim job deletion is failed");
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
    }

    public void forceClaimJob() {
        keywords.waitTime(1000);
        keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
        keywords.setCheckboxIfNotSelected(claimJobForceClaimCheckBox);
    }

    public void applyDebitCodeAndValidate(HashMap<String, Object> newClaimJob) {
        SoftAssert saMainDebitCode = new SoftAssert();
        String firstXpathOfDebitCodeSelection = "//a[text()='";
        String secondXpathOfDebitCodeSelection = "']";
        String debitCodeSelection = null;
        ExtentTestManager.getTest().log(Status.INFO, "Evaluating a claim job ");
        keywords.clickOnLink(claimJobAssessmentTab, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.input(applyDebitCodeMainDebitCode, appBasedUtils.getStringData(newClaimJob, "DebitCode"), "DebitCode");
        String expectedNetCost = keywords.getTextValue(applyDebitCodeNetCost);
        keywords.clickOnButton(driver, applyDebitCode);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 4, loadingIcon);

        if ((keywords.elementIsDisplayed(errorMsgElement, "error message iss displayed"))) {
            APP_LOGS.info(keywords.getTextValue(errorMsgElement));
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsgElement));
            Assert.fail(keywords.getTextValue(errorMsgElement));
        }

        keywords.clickOnButton(driver, percentageButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.clickOnButton(driver, labourDebitCodeSelection);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour") + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();
        keywords.clickOnButton(driver, materialDebitCodeSelection);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial") + secondXpathOfDebitCodeSelection;
        keywords.waitTime(1000);
        driver.findElement(By.xpath(debitCodeSelection)).click();
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.clickOnButton(driver, travelExpenseDebitCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "DebitCodeOfTravelExpense")
                    + secondXpathOfDebitCodeSelection;
            driver.findElement(By.xpath(debitCodeSelection)).click();
        }
        keywords.waitTime(1000);
        keywords.clickOnButton(driver, otherCostsDebitCodeSelection);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts")
                + secondXpathOfDebitCodeSelection;
        driver.findElement(By.xpath(debitCodeSelection)).click();
        keywords.input(labourTotalPercentage, appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"), "MainLabourPercentage");
        keywords.input(materialTotalPercentageList.get(0), appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"), "MainMaterialPercentage");
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitTime(500);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.input(travelTotalPercentage.get(0), appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"), "travelTotalPercentage");
        }
        keywords.input(otherCostTotalPercentage.get(0), appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"), "MainOtherCostPercentage");
        keywords.keyTab(otherCostTotalPercentage.get(0));

        // Click on save button and validate the data
        keywords.clickOnButton(driver, saveClaimJobButton);

        appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
        keywords.waitTime(2000);
        appBasedUtils.asserVerification("NetCostCost ", expectedNetCost, keywords.getTextValue(netCostTotal), saMainDebitCode);
        appBasedUtils.asserVerification("Labour debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour"),
                                        keywords.getAttributeValue(debitCodeOfLabourSplit.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("Material debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial"),
                                        keywords.getAttributeValue(debitCodeOfMaterial.get(0), "value"), saMainDebitCode);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitTime(500);
            appBasedUtils.asserVerification("Travel expense debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfTravelExpense"),
                                            keywords.getAttributeValue(debitCodeOfTravelExpense.get(0), "value"), saMainDebitCode);
        }
        appBasedUtils.asserVerification("OtherCost debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts"),
                                        keywords.getAttributeValue(debitCodeOfOtherCost.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("Labour percentage ", appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"),
                                        keywords.getAttributeValue(labourTotalPercentage, "value"), saMainDebitCode);
        appBasedUtils.asserVerification("Material percentage ", appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"),
                                        keywords.getAttributeValue(materialTotalPercentageList.get(0), "value"), saMainDebitCode);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)))
            appBasedUtils.asserVerification("TravelExpense percentage ", appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"),
                                            keywords.getAttributeValue(travelTotalPercentage.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("OtherCost percentage ", appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"),
                                        keywords.getAttributeValue(otherCostTotalPercentage.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("TotalNetCostInGrid ", expectedNetCost, keywords.getTextValue(netCostTotal), saMainDebitCode);
        appBasedUtils.asserVerification("Debit code applied ", appBasedUtils.getStringData(newClaimJob, "DebitCode"), keywords.getTextValue(debitCodeTotal),
                                        saMainDebitCode);
        saMainDebitCode.assertAll();

        releaseClaimjob();
        ExtentTestManager.getTest().log(Status.INFO, "100 % slipt is applied on claim ");
    }

    public void applyRejectCodeAndValidate(HashMap<String, Object> newClaimJob) {
        try {
            SoftAssert saMainDebitCode = new SoftAssert();
            String firstXpathOfDebitCodeSelection = "//a[contains(text(),'";
            String secondXpathOfDebitCodeSelection = "')]";
            String debitCodeSelection = null;
            ExtentTestManager.getTest().log(Status.INFO, "Evaluating a claim job ");
            // Apply main debit code
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.input(applyDebitCodeMainDebitCode, appBasedUtils.getStringData(newClaimJob, "DebitCode"), "DebitCode");
            String expectedNetCost = keywords.getTextValue(applyDebitCodeNetCost);
            keywords.clickOnButton(driver, applyDebitCode);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);

            if ((keywords.elementIsDisplayed(errorMsgElement, "error message iss displayed"))) {
                APP_LOGS.info(keywords.getTextValue(errorMsgElement));
                ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsgElement));
                Assert.fail(keywords.getTextValue(errorMsgElement));
            }
            keywords.clickOnButton(driver, labourRejectionCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            // Select material rejection code
            keywords.clickOnButton(driver, materialRejectionCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfMaterial")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();

            // Select travel expense yet to do
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.clickOnButton(driver, travelExpenseRejectionCodeSelection);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfTravelExpense")
                        + secondXpathOfDebitCodeSelection;
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }
            keywords.waitTime(1000);
            // other code rejectioncode selection
            keywords.clickOnButton(driver, costRejectionCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfOtherCost")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();

            if (keywords.elementIsEnabled(percentageButton))
                keywords.clickOnButton(driver, percentageButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);

            keywords.input(labourTotalPercentage, appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"), "MainLabourPercentage");
            keywords.input(materialTotalPercentageList.get(0), appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"), "MainMaterialPercentage");
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.waitTime(500);
                keywords.input(travelTotalPercentage.get(0), appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"), "travelTotalPercentage");
            }
            keywords.input(otherCostTotalPercentage.get(0), appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"), "MainOtherCostPercentage");
            keywords.keyTab(otherCostTotalPercentage.get(0));

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, labourTabManageClaimJob);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, claimJobAssessmentTab);

            // Click on save button and validate the data
            keywords.clickOnButton(driver, saveClaimJobButton);
            //
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.waitTime(2000);
            appBasedUtils.asserVerification("NetCostCost ", expectedNetCost, keywords.getTextValue(netCostTotal), saMainDebitCode);
            appBasedUtils.asserVerification("Labour debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour"),
                                            keywords.getAttributeValue(debitCodeOfLabourSplit.get(0), "value"), saMainDebitCode);
            appBasedUtils.asserVerification("Material debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial"),
                                            keywords.getAttributeValue(debitCodeOfMaterial.get(0), "value"), saMainDebitCode);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.waitTime(500);
                appBasedUtils.asserVerification("Travel expense debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfTravelExpense"),
                                                keywords.getAttributeValue(debitCodeOfTravelExpense.get(0), "value"), saMainDebitCode);
            }
            appBasedUtils.asserVerification("OtherCost debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts"),
                                            keywords.getAttributeValue(debitCodeOfOtherCost.get(0), "value"), saMainDebitCode);
            appBasedUtils.asserVerification("Labour percentage ", appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"),
                                            keywords.getAttributeValue(labourTotalPercentage, "value"), saMainDebitCode);
            appBasedUtils.asserVerification("Material percentage ", appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"),
                                            keywords.getAttributeValue(materialTotalPercentageList.get(0), "value"), saMainDebitCode);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)))
                appBasedUtils.asserVerification("TravelExpense percentage ", appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"),
                                                keywords.getAttributeValue(travelTotalPercentage.get(0), "value"), saMainDebitCode);
            appBasedUtils.asserVerification("OtherCost percentage ", appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"),
                                            keywords.getAttributeValue(otherCostTotalPercentage.get(0), "value"), saMainDebitCode);
            appBasedUtils.asserVerification("TotalNetCostInGrid ", expectedNetCost, keywords.getTextValue(netCostTotal), saMainDebitCode);
            appBasedUtils.asserVerification("Debit code applied ", appBasedUtils.getStringData(newClaimJob, "DebitCode"), keywords.getTextValue(debitCodeTotal),
                                            saMainDebitCode);
            saMainDebitCode.assertAll();

            releaseClaimjob();
            ExtentTestManager.getTest().log(Status.INFO, "100 % rejection code is applied on claim ");
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail("Failed: Claim save with full reject");
        }
    }

    public void partialRejectAndValidate(HashMap<String, Object> newClaimJob) {
        SoftAssert saMainDebitCode = new SoftAssert();
        String firstXpathOfDebitCodeSelection = "//a[contains(text(),'";
        String secondXpathOfDebitCodeSelection = "')]";
        String debitCodeSelection = null;
        companyName = keywords.getTextValue(companyNameElement);
        ExtentTestManager.getTest().log(Status.INFO, "Evaluating a claim job ");
        keywords.clickOnLink(claimJobAssessmentTab, driver);
        // Apply main debit code
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        keywords.input(applyDebitCodeMainDebitCode, appBasedUtils.getStringData(newClaimJob, "DebitCode"), "DebitCode");
        String expectedNetCost = keywords.getTextValue(applyDebitCodeNetCost);
        keywords.clickOnButton(driver, applyDebitCode);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 4, loadingIcon);

        if ((keywords.elementIsDisplayed(errorMsgElement, "error message iss displayed"))) {
            APP_LOGS.info(keywords.getTextValue(errorMsgElement));
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsgElement));
            Assert.fail(keywords.getTextValue(errorMsgElement));
        }
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);

        keywords.clickOnButton(driver, labourRejectionCodeSelection);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour")
                + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();

        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        // Select material rejection code
        keywords.clickOnButton(driver, materialRejectionCodeSelection);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfMaterial")
                + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();
        // Select travel expense yet to do
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJob, "TravelHourTravelExpenses"))) {
                keywords.clickOnButton(driver, travelExpenseRejectionCodeSelection);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfTravelExpense")
                        + secondXpathOfDebitCodeSelection;
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }
        }
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        // other code rejectioncode selection
        keywords.clickOnButton(driver, costRejectionCodeSelection);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfOtherCost")
                + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();

        if (keywords.elementIsEnabled(percentageButton))
            keywords.clickOnButton(driver, percentageButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        // keywords.clickOnButton(labourTotalPercentage);
        keywords.input(labourTotalPercentage, appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"), "MainLabourPercentage");
        keywords.waitTime(2000);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        keywords.clickOnButton(driver, labourTotalPercentage);
        keywords.keyEnter(labourTotalPercentage);
        keywords.focusingOutOfElementUsingAction(driver, labourTotalPercentage, claimJobTitle);
        // keywords.clickOnButton(materialTotalPercentage);
        keywords.input(materialTotalPercentageList.get(0), appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"), "MainMaterialPercentage");
        keywords.focusingOutOfElementUsingAction(driver, materialTotalPercentageList.get(0), claimJobTitle);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitTime(600);
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"))) {
                keywords.focusingOutOfElementUsingAction(driver, travelTotalPercentage.get(0), claimJobTitle);
                keywords.input(travelTotalPercentage.get(0), appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"), "travelTotalPercentage");
            }
        }
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        keywords.clickOnButton(driver, materialTotalPercentage);
        keywords.input(otherCostTotalPercentage.get(0), appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"), "MainOtherCostPercentage");
        keywords.focusingOutOfElementUsingAction(driver, otherCostTotalPercentage.get(0), claimJobTitle);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        keywords.waitTime1(2);
        keywords.clickOnButton(driver, labourTotalPercentage);
        keywords.keyEnter(labourTotalPercentage);
        keywords.focusingOutOfElementUsingAction(driver, labourTotalPercentage, claimJobTitle);
        keywords.clickOnButton(driver, labourSplitCost1DebitClick);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeLabour_1")
                + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();
        keywords.clickOnButton(driver, materialSplitCost1DebitClick);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeMaterial_1")
                + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        keywords.clickOnButton(driver, otherCostSplitCost1DebitClick);
        debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeOtherCost_1")
                + secondXpathOfDebitCodeSelection;
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        driver.findElement(By.xpath(debitCodeSelection)).click();
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.clickOnButton(driver, travelCostDebitCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeTravelExpense_1")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
        }

        /*
         * appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon); keywords.clickOnButton(splitCost1LabourRejectCode); debitCodeSelection =
         * firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour") + secondXpathOfDebitCodeSelection;
         * appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon); driver.findElement(By.xpath(debitCodeSelection)).click();
         *
         * appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon); keywords.clickOnButton(splitCost1MaterialRejectCode); debitCodeSelection =
         * firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour") + secondXpathOfDebitCodeSelection;
         * driver.findElement(By.xpath(debitCodeSelection)).click(); appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
         *
         * appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon); keywords.clickOnButton(splitCost1OtherCostRejectCode); debitCodeSelection =
         * firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour") + secondXpathOfDebitCodeSelection;
         * driver.findElement(By.xpath(debitCodeSelection)).click(); appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
         */

        keywords.clickOnButton(driver, claimJobAssessmentTab);
        // Click on save button and validate the data
        keywords.clickOnButton(driver, saveClaimJobButton);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 15, loadingIcon);
        keywords.waitTime(2000);
        appBasedUtils.asserVerification("NetCostCost ", expectedNetCost, keywords.getTextValue(netCostTotal), saMainDebitCode);
        appBasedUtils.asserVerification("Labour debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour"),
                                        keywords.getAttributeValue(debitCodeOfLabourSplit.get(0), "value"), saMainDebitCode);

        appBasedUtils.asserVerification("RejectCodeReasonOfLabour", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour"),
                                        keywords.getAttributeValue(labourRejectCodeListOfReason.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("MainPercentageOfLabour", appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"),
                                        keywords.getAttributeValue(labourTotalPercentage, "value"), saMainDebitCode);
        appBasedUtils.asserVerification("SplitLabourDebitCodeReason", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeLabour_1"),
                                        keywords.getAttributeValue(debitCodeOfLabourSplit.get(1), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("SplitLabourPercentage", appBasedUtils.getStringData(newClaimJob, "SplitcostPercentageOnLabour_1"),
                                        keywords.getAttributeValue(labourTotalPerncentageSplit.get(1), "value"), saMainDebitCode);

        // material validation
        appBasedUtils.asserVerification("Material debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial"),
                                        keywords.getAttributeValue(debitCodeOfMaterial.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("RejectCodeReasonOfMaterial", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfMaterial"),
                                        keywords.getAttributeValue(materialRejectCodeListOfReason.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("MainPercentageOfMaterial", appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"),
                                        keywords.getAttributeValue(materialTotalPercentageList.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("SplitMaterialDebitCodeReason", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeMaterial_1"),
                                        keywords.getAttributeValue(debitCodeOfMaterial.get(1), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("SplitMaterialPercentage", appBasedUtils.getStringData(newClaimJob, "SplitcostPercentageOnMaterial_1"),
                                        keywords.getAttributeValue(materialTotalPercentageList.get(1), "value"), saMainDebitCode);

        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            keywords.waitTime(500);
            // add if not enter validation
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJob, "TravelHourTravelExpenses"))) {
                appBasedUtils.asserVerification("Travel expense debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfTravelExpense"),
                                                keywords.getAttributeValue(debitCodeOfTravelExpense.get(0), "value"), saMainDebitCode);
                appBasedUtils.asserVerification("RejectCodeReasonOfTravel", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfTravelExpense"),
                                                keywords.getAttributeValue(travelRejectCodeListOfReason.get(0), "value"), saMainDebitCode);
                appBasedUtils.asserVerification("MainPercentageOfTravrel", appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"),
                                                keywords.getAttributeValue(travelTotalPercentage.get(0), "value"), saMainDebitCode);
                appBasedUtils.asserVerification("SplitTravelExpenseDebitCodeReason",
                                                appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeTravelExpense_1"),
                                                keywords.getAttributeValue(debitCodeOfTravelExpense.get(1), "value"), saMainDebitCode);
                appBasedUtils.asserVerification("SplitTravelExpensePercentage",
                                                appBasedUtils.getStringData(newClaimJob, "SplitcostPercentageOnTravelExpense_1"),
                                                keywords.getAttributeValue(travelTotalPercentage.get(1), "value"), saMainDebitCode);
            }
        }
        // othercost
        appBasedUtils.asserVerification("OtherCost debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts"),
                                        keywords.getAttributeValue(debitCodeOfOtherCost.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("RejectCodeReasonOfOtherCost", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfOtherCost"),
                                        keywords.getAttributeValue(otherCostRejectCodeListOfReason.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("MainPercentageOfOtherCost", appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"),
                                        keywords.getAttributeValue(otherCostTotalPercentage.get(0), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("SplitOtherCostDebitCodeReason", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeOtherCost_1"),
                                        keywords.getAttributeValue(debitCodeOfOtherCost.get(1), "value"), saMainDebitCode);
        appBasedUtils.asserVerification("SplitOtherCostPercentage", appBasedUtils.getStringData(newClaimJob, "SplitcostPercentageOnOtherCost_1"),
                                        keywords.getAttributeValue(otherCostTotalPercentage.get(1), "value"), saMainDebitCode);

        saMainDebitCode.assertAll();

        releaseClaimjob();
        ExtentTestManager.getTest().log(Status.INFO, "partial rejection is applied on claim ");

    }

    /****************************************
     * #Description : Supplies CAF value to CAF field in the labour tab
     ****************************************/
    private void enterLabourDetailsForContractClaims(HashMap<String, Object> manageClaimJobData) {
        if ((appBasedUtils.getStringData(manageClaimJobData, "SelectTypeOfConcern")).equalsIgnoreCase("Contract")) {
            keywords.waitForVisibilityOfElement(driver, 15, cafLabourTab.get(0));
            keywords.input(cafLabourTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "CAFLabour"), "Labour CAF ");
        }

    }

    /****************************************
     * #Description : Supplies CAF value to CAF field in the material tab
     ****************************************/
    private void enterMaterialDetailsForContractClaims(HashMap<String, Object> manageClaimJobData) {
        if ((appBasedUtils.getStringData(manageClaimJobData, "SelectTypeOfConcern")).equalsIgnoreCase("Contract")) {
            keywords.waitForVisibilityOfElement(driver, 15, cafMaterialTab.get(0));
            keywords.input(cafMaterialTab.get(0), appBasedUtils.getStringData(manageClaimJobData, "CAFMaterial"), "Materil CAF ");
        }
    }

    /****************************************
     * #Description : Validates the value populated in labour and material tab.
     ****************************************/
    public void validatecontractDetailsPopulatedInClaimJob(HashMap<String, Object> manageClaimJobData) {
        keywords.waitForVisibilityOfElement(driver, 10, labourTabManageClaimJob);
        keywords.clickOnLink(labourTabManageClaimJob, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.waitForVisibilityOfElement(driver, 10, labourNetTotalLabourTabElement);
        String labourHoursValuePopulated = keywords.getTextValue(labourHoursValue);
        keywords.waitForVisibilityOfElement(driver, 10, labourCAFValue);
        String labourCAFValuePopulated = keywords.getTextValue(labourCAFValue);
        String labourCostPerHourValuePopulated = keywords.getTextValue(labourCostPerHourValue);
        APP_LOGS.info("Validating values populated in Labours tab");
        APP_LOGS.info("Labour Hours picked is: " + labourHoursValuePopulated);
        APP_LOGS.info("Labour CAF picked is: " + labourCAFValuePopulated);
        APP_LOGS.info("Labour Cost/hours picked is: " + labourCostPerHourValuePopulated);
        Assert.assertEquals(labourCostPerHourValuePopulated, appBasedUtils.getStringData(manageClaimJobData, "LabourCostPerHour"));
        Assert.assertEquals(labourCAFValuePopulated, appBasedUtils.getStringData(manageClaimJobData, "ExpectedCAFLabour"));
        Assert.assertEquals(labourHoursValuePopulated, appBasedUtils.getStringData(manageClaimJobData, "ExpectedLabourHours"));
        keywords.waitForVisibilityOfElement(driver, 10, claimJobMaterial);
        keywords.clickOnLink(claimJobMaterial, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.waitForVisibilityOfElement(driver, 10, materialCAFValue);
        String materialCAFPopulated = keywords.getTextValue(materialCAFValue);
        APP_LOGS.info("Validating values populated in Material tab");
        APP_LOGS.info("Material CAF: " + materialCAFPopulated);
        Assert.assertEquals(materialCAFPopulated, appBasedUtils.getStringData(manageClaimJobData, "ExpectedCAFMaterial"));
    }

    public void getAutoexceptionReason(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
            APP_LOGS.info("Validating values populated in Material tab" + keywords.getTextValue(autoExceptionReason));
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(manageClaimJobData, "AutoExceptionReason"),
                                               keywords.getTextValue(autoExceptionReason)));
            ExtentTestManager.getTest().log(Status.INFO, "Claim job status is:" + keywords.getTextValue(autoExceptionReason));
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }

    }

    public void validateSavedDataInLabourTab(HashMap<String, Object> manageClaimJobData, String... args) {
        String[] operationNumbers = null;
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, labourTabManageClaimJob);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", labourTabManageClaimJob);
            keywords.clickOnLink(labourTabManageClaimJob, driver);
            APP_LOGS.info("Validating values saved in Labours tab");
            if ((args[0].equalsIgnoreCase("NewCampaign.xlsx"))
                    && (appBasedUtils.getStringData(manageClaimJobData, "SaveWithOperationNo").equalsIgnoreCase("No"))) {
                operationNumbers = appBasedUtils.getStringData(manageClaimJobData, "OperationId").split(";");
            } else {
                operationNumbers = appBasedUtils.getStringData(manageClaimJobData, "OperationNumberLabour").split(";");
            }
            keywords.waitTime1(2);
            if (labourTabOperationNoSavedValue != null && labourTabOperationNoSavedValue.size() > 0) {
                for (int i = 0; i < labourTabOperationNoSavedValue.size(); i++) {
                    if (appBasedUtils.getStringData(manageClaimJobData, "VST").equalsIgnoreCase("NO")) {
                        APP_LOGS.info(labourTabOperationNoSavedValue.get(i).getText());
                        Assert.assertEquals(labourTabOperationNoSavedValue.get(i).getText(), operationNumbers[i]);
                        Assert.assertEquals(labourTabOperationDescSavedValue.get(i).getText(),
                                            appBasedUtils.getStringData(manageClaimJobData, "OperationDescLabourTab"));
                        Assert.assertEquals(labourTabQuantitySavedValue.get(i).getText(), appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"));
                        Assert.assertEquals(labourTabHoursSavedValue.get(i).getText(), appBasedUtils.getStringData(manageClaimJobData, "LabourHours"));
                        labourNetCost = labourNet.get(i).getText();
                        keywords.waitTime1(2);
                    } else {
                        Assert.assertEquals(labourTabOperationNoSavedValue.get(i).getText(), operationNumbers[i]);
                        labourNetCost = labourNet.get(i).getText();
                        Assert.assertEquals(labourTabQuantitySavedValue.get(i).getText(), appBasedUtils.getStringData(manageClaimJobData, "LabourQuantity"));
                    }
                }
                APP_LOGS.info("All the data enterded in Labour tab is saved..!!");
            } else {
                APP_LOGS.info("No data to validate in Labour tab!");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /****************************************
     * #Description : Validates the total net value populated is equal to quantity * hours * costPerHour * CAF.
     ****************************************/
    public void validateLabourHoursCost(HashMap<String, Object> manageClaimJobData) {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
        keywords.waitForVisibilityOfElement(driver, 15, labourTabManageClaimJob);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].focus()", labourTabManageClaimJob);
        keywords.clickOnLink(labourTabManageClaimJob, driver);
        keywords.waitTime1(2);
        if (labourTabOperationNoSavedValue != null && labourTabOperationNoSavedValue.size() > 0) {
            for (int i = 0; i < labourTabOperationNoSavedValue.size(); i++) {
                keywords.waitTime1(2);
                int quantity = Integer.parseInt(labourTabQuantitySavedValue.get(i).getText());
                double hours = Double.parseDouble(labourTabHoursSavedValue.get(i).getText());
                double costPerHour = Double.parseDouble(labourTabCostPerHoursSavedValue.get(i).getText());
                double CAF = Double.parseDouble(appBasedUtils.getStringData(manageClaimJobData, "CAFLabour"));
                double netCost = quantity * hours * costPerHour * CAF;
                Assert.assertEquals(netCost, Double.parseDouble(labourNet.get(i).getText()));
            }
        }
    }

    public void validateSavedDataInMaterialTab(HashMap<String, Object> manageClaimJobData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, claimJobMaterial);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", claimJobMaterial);
            keywords.clickOnLink(claimJobMaterial, driver);
            keywords.waitTime1(2);

            APP_LOGS.info(" Validating values saved in Material tab");
            String partPrefixes[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartPrefix").split(";");
            String partNumbers[] = appBasedUtils.getStringData(manageClaimJobData, "ReplacedPartNo").split(";");
            keywords.waitForVisibilityOfElement(driver, 10, materialTabPartPrefixSavedValue);
            Assert.assertEquals(materialTabPartPrefixSavedValue.getText(), appBasedUtils.getStringData(manageClaimJobData, "CausalPartPrefix"));
            Assert.assertEquals(materialTabPartPartnoSavedValue.getText(), appBasedUtils.getStringData(manageClaimJobData, "CausalPartNo"));
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "CausalSerialNo"))) {
                Assert.assertEquals(materialTabSerialnoSavedValue.getText(), appBasedUtils.getStringData(manageClaimJobData, "CausalSerialNo"));
            }
            Assert.assertEquals(materialTabDefectCodeSavedValue.getText(), appBasedUtils.getStringData(manageClaimJobData, "DefectCode"));
            if (materialTabIncludedPartPrefixSavedValue != null && materialTabIncludedPartPrefixSavedValue.size() > 0) {
                for (int i = 0; i < materialTabIncludedPartPrefixSavedValue.size(); i++) {
                    APP_LOGS.info(materialTabIncludedPartPrefixSavedValue.get(i).getText());
                    Assert.assertEquals(materialTabIncludedPartPrefixSavedValue.get(i).getText(), partPrefixes[i]);
                    Assert.assertEquals(materialTabIncludedPartNoSavedValue.get(i).getText(), partNumbers[i]);
                    if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "ReplacedSerialNo"))) {
                        Assert.assertEquals(materialTabIncludedSerialnoSavedValue.get(i).getText(),
                                            appBasedUtils.getStringData(manageClaimJobData, "ReplacedSerialNo"));
                    }
                    materialNetCost = materialNet.get(i).getText();
                    // Assert.assertEquals(materialTabIncludedQuantitySavedValue.get(i).getText(), appBasedUtils.getStringData(manageClaimJobData,
                    // "MaterialQuantity"));
                }
                APP_LOGS.info("All the data enterded in Material tab is saved..!!");
            } else {
                APP_LOGS.info("No data to validate in Included material section.!");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    public void validateSavedDataInOtherCostTab(HashMap<String, Object> manageClaimJobData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, otherCostTabManageClaimJob);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", otherCostTabManageClaimJob);
            keywords.clickOnLink(otherCostTabManageClaimJob, driver);
            keywords.waitTime1(2);
            APP_LOGS.info("Validating values saved in OtherCost tab");
            String otherCostQuantity[] = appBasedUtils.getStringData(manageClaimJobData, "QuantityOtherCost").split(";");
            if (otherCostTabQuantitySavedValue != null && otherCostTabQuantitySavedValue.size() > 0) {
                for (int i = 0; i < otherCostTabQuantitySavedValue.size(); i++) {
                    APP_LOGS.info(otherCostTabQuantitySavedValue.get(i).getText());
                    Assert.assertEquals(otherCostTabQuantitySavedValue.get(i).getText(), otherCostQuantity[i]);
                    Assert.assertEquals(otherCostTabPriceSavedValue.get(i).getText(), appBasedUtils.getStringData(manageClaimJobData, "PriceOtherCost"));
                    if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"))) {
                        Assert.assertEquals(otherCostTabDescSavedValue.get(i).getText(),
                                            appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"));
                    }
                    otherNetCost = otherNet.get(i).getText();
                }
                APP_LOGS.info("All the data enterded in Other cost tab is saved..!!");
            } else {
                APP_LOGS.info("No data to validate in Other cost tab!");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    public void validateSavedDataInRemarksTab(HashMap<String, Object> manageClaimJobData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, remarksTabManageClaimJob);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", remarksTabManageClaimJob);
            keywords.clickOnLink(remarksTabManageClaimJob, driver);
            keywords.waitTime1(2);
            APP_LOGS.info("Validating values saved in Remarks tab");
            Assert.assertEquals(remarksTabComplaintSavedValue.getText().trim(), appBasedUtils.getStringData(manageClaimJobData, "ComplaintRemarks"));
            Assert.assertEquals(remarksTabCauseSavedValue.getText().trim(), appBasedUtils.getStringData(manageClaimJobData, "CauseRemarks"));
            Assert.assertEquals(remarksTabCorrectionSavedValue.getText().trim(), appBasedUtils.getStringData(manageClaimJobData, "CorrectionRemarks"));
            Assert.assertEquals(commentRemarksTab.getAttribute("value"), appBasedUtils.getStringData(manageClaimJobData, "CommentsRemarks"));
            APP_LOGS.info("All the data enterded in Remarks tab is saved..!!");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    public void validateSavedDataInTravelExpenseTab(HashMap<String, Object> manageClaimJobData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, travelExpensesTabManageClaimJob);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", travelExpensesTabManageClaimJob);
            keywords.clickOnLink(travelExpensesTabManageClaimJob, driver);
            keywords.waitTime1(4);
            APP_LOGS.info("Validating values saved in Travel Expense tab");
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "TravelHourTravelExpenses"))) {
                Assert.assertEquals(travelExpTabHoursSavedValue.getText().trim(), appBasedUtils.getStringData(manageClaimJobData, "TravelHourTravelExpenses"));
                Assert.assertEquals(travelExpTabDistanceSavedValue.getText().trim(),
                                    appBasedUtils.getStringData(manageClaimJobData, "TravelDistanceTravelExpenses"));
                Assert.assertEquals(travelExpTabOtherExpSavedValue.getText().trim(),
                                    appBasedUtils.getStringData(manageClaimJobData, "OtherTravelTravelExpenses"));
                APP_LOGS.info("All the data enterded in Travel Expense tab is saved..!!");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    public void validateSavedDataInClaimTabs(HashMap<String, Object> manageClaimJobData) {
        companyName = keywords.getTextValue(companyNameElement);
        validateSavedDataInLabourTab(manageClaimJobData, "NewCLaimJob.xlsx");
        validateSavedDataInMaterialTab(manageClaimJobData);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
            validateSavedDataInTravelExpenseTab(manageClaimJobData);
        } else {
            APP_LOGS.info("No Travel Expense tab for : " + companyName);
        }
        validateSavedDataInOtherCostTab(manageClaimJobData);
        validateSavedDataInRemarksTab(manageClaimJobData);

    }

    /*********************************************
     * #Description : This method is to retrun a claim job by doing advance search
     *******************************************/
    public String returnClaimJob(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.clickOnButton(driver, claimJobAssessmentTab);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            if (keywords.elementIsEnabled(deleteDebitStructure))
                keywords.clickOnButton(driver, deleteDebitStructure);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
            keywords.selectVisibleText(selectMINameInClaimJobInfo, "<Blank>");
            keywords.scrollPage(driver, 500);
            keywords.input(returnCodeValue, appBasedUtils.getStringData(manageClaimJobData, "ReturnCode"), "return code");
            keywords.clickOnButton(driver, activeCheckBox);
            keywords.clickOnButton(driver, saveClaimJobButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            if (keywords.isErrorMessageVisible(driver, 10, errorMsgElement, validationMessageClose)) {
                APP_LOGS.error(errorMsgElement);
                Assert.fail();
            }
            keywords.clickOnButton(driver, releaseClaimJobButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            APP_LOGS.info("Claim job status is: " + keywords.getTextValue(claimJobStatus) + ", for " + keywords.getTextValue(claimJobReferenceNo)
                    + " and Return Code is: " + keywords.getTextValue(returnCodeText));
            ExtentTestManager.getTest().log(Status.INFO,
                                            "Claim job status is: " + keywords.getTextValue(claimJobStatus) + ", for "
                                                    + keywords.getTextValue(claimJobReferenceNo) + " - " + keywords.getTextValue(claimJobNo)
                                                    + " and Return Code is: " + keywords.getTextValue(returnCodeText));
            assertEquals(keywords.getTextValue(claimJobStatus), "Returned");
            manageClaimJobData.put("RefNoFrom", keywords.getTextValue(claimJobReferenceNo));
            manageClaimJobData.put("RefNoTo", keywords.getTextValue(claimJobReferenceNo));
            manageClaimJobData.put("Status", keywords.getTextValue(claimJobStatus));
            manageClaimJobData.put("Importer", keywords.getTextValue(claimHeaderImporter));
            manageClaimJobData.put("Dealer", keywords.getTextValue(claimHeaderDealer));
            manageClaimJobData.put("ExpectedDealerValue", keywords.getTextValue(claimHeaderDealer));
            return keywords.getTextValue(claimJobReferenceNo);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
        return claimJobNumber;

    }

    /*********************************************
     * #Description : This method is to retrun a claim job by doing advance search
     *******************************************/
    public void getTheAppealStatusAfterReturningClaim() {
        try {
            keywords.clickOnButton(driver, remarksTabManageClaimJob);
            assertEquals(keywords.getFirstSelectedItemFromDropDown(appealStatusAppealTab), "<Select>");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to click on claim header and then close the claim job and advance search screen
     *******************************************/
    public void clickOnClaimJobHeader(HashMap<String, Object> manageClaimJobData) {
        try {
            // keywords.waitForPageLoadSuccessfully(driver);
            keywords.clickOnButton(driver, claimJobHeader);
            manageClaimJobData.put("ExpectedImportervalue", keywords.getTextValue(claimRepairheaderImporter));
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            keywords.clickOnButton(driver, closeButton);
            keywords.waitTime1(3);
            keywords.clickOnButton(driver, closeButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            // keywords.waitForPageLoadSuccessfully(driver);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to click on the appeal log and get the log info
     *******************************************/
    public void verifyTheLogsInApprealTab(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.clickOnButton(driver, remarksTabManageClaimJob);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 4, loadingIcon);
            keywords.clickOnButton(driver, claimJobAppealTabLogIcon);
            driver.switchTo().defaultContent();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            assertEquals(keywords.getTextValue(claimJobAppealTabStatusInHistory), appBasedUtils.getStringData(manageClaimJobData, "AppealStatus"));
            assertEquals(keywords.getTextValue(claimJobAppealTabUserIdInHistory), appBasedUtils.getStringData(manageClaimJobData, "UserID"));
            keywords.waitForVisibilityOfElement(driver, 10, remarksTabManageClaimJob);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to fetch the MI name on Claim and validate with the expected result
     *******************************************/

    public void validateMINameOnClaim(HashMap<String, Object> manageClaimJobData) {
        try {
            keywords.waitForVisibilityOfElement(driver, 15, claimJobInformationTabManageClaimJob);
            keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
            assertEquals(keywords.getFirstSelectedItemFromDropDown(miName), appBasedUtils.getStringData(manageClaimJobData, "MIName"));
            APP_LOGS.info("MI " + appBasedUtils.getStringData(manageClaimJobData, "MIName") + " is set correctly on Claim..!!");
            ExtentTestManager.getTest().log(Status.INFO,
                                            "MI: " + appBasedUtils.getStringData(manageClaimJobData, "MIName") + "- is set correctly on Claim..!!");
        } catch (Exception e) {
            APP_LOGS.error("MI has not been applied correctly on Claim");
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to validate hold for crediting
     *******************************************/
    public void validateHoldForCreditingandReason(String holdForCReason) {
        try {
            keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
            String Strholdforcreditingreason[] = keywords.getTextValue(holdForCreditingReason).split(" ");
            String holdforcreditingreason = Strholdforcreditingreason[1];
            APP_LOGS.info("Validating claim status " + keywords.getTextValue(holdForCrediting));
            keywords.waitTime1(5);
            assertTrue(keywords.assertContains(keywords.getTextValue(holdForCrediting).trim(), "Hold for crediting"));
            keywords.waitTime1(5);
            assertTrue(keywords.assertContains(holdForCReason, holdforcreditingreason));
            ExtentTestManager.getTest().log(Status.INFO, "Hold for crediting reason is: " + keywords.getTextValue(holdForCreditingReason));
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to update debit code in assessment tab and save it
     *******************************************/
    public void updateDebitCodeInAssessmenttab(HashMap<String, Object> newClaimJob) {
        try {
            APP_LOGS.info("Updating Debit code in assessment tab");
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.input(labourDebitCode, appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour"), "Debit code for labour");
            keywords.input(materialDebitCode, appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial"), "Debit code for material");
            keywords.input(otherCostDebitCode, appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts"), "Debit code for other cost");
            keywords.keyTab(otherCostDebitCode);
            keywords.clickOnButton(driver, saveClaimJobButton);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to validate debit code in assessment tab
     *******************************************/
    public void validateDebitCodeInAssessmenttab(HashMap<String, Object> newClaimJob) {
        try {
            keywords.waitTime1(5);
            SoftAssert saMainDebitCode = new SoftAssert();
            APP_LOGS.info("Validating Debit code in assessment tab");
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            keywords.waitTime1(5);
            appBasedUtils.asserVerification("Labour debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour"),
                                            keywords.getAttributeValue(debitCodeOfLabourSplit.get(0), "value"), saMainDebitCode);
            appBasedUtils.asserVerification("Material debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial"),
                                            keywords.getAttributeValue(debitCodeOfMaterial.get(0), "value"), saMainDebitCode);
            appBasedUtils.asserVerification("OtherCost debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts"),
                                            keywords.getAttributeValue(debitCodeOfOtherCost.get(0), "value"), saMainDebitCode);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method check if the claim is Auto failed with Failing reason:inspector report and checks for multiple inspector reports match.
     *******************************************/
    public void validateOneOrMultipleIRmatchedForClaim(HashMap<String, Object> manageClaimJobData) {
        try {
            String claimJobStatusInfo = getClaimStatus();
            if ((getClaimStatus().equals("Auto failed"))) {
                keywords.clickOnLink(claimJobInformationTabManageClaimJob, driver);
                String inspectorReportFailingreason = keywords.getTextValue(inspectorFailingReason);
                APP_LOGS.info("Validating claim status " + claimJobStatusInfo + " Failing reason " + inspectorReportFailingreason);
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(manageClaimJobData, "FailingReason"), inspectorReportFailingreason));
                ExtentTestManager.getTest().log(Status.INFO, "Claim job status is: " + claimJobStatusInfo);
                keywords.clickOnLink(inspectorReportTabManageClaimJob, driver);
                keywords.waitTime1(3);
                int totalInspectorReportsMatchedForClaim = totalInspectorReportsMatched.size();
                if (totalInspectorReportsMatchedForClaim > 1) {
                    APP_LOGS.info(" There are " + totalInspectorReportsMatchedForClaim + " multiple inspector reports matched for this claim, ");
                    ExtentTestManager.getTest().log(Status.INFO, totalInspectorReportsMatchedForClaim + " Multiple inspector reports matched for this claim");
                    APP_LOGS.info(" Marking first Inspector report as used for this Claim");
                    keywords.setCheckboxIfNotSelected(totalInspectorReportsMatched.get(0));
                    keywords.clickOnLink(viewInspectorReportslinks.get(0), driver);
                    keywords.clickOnButton(driver, saveClaimJobButton);
                    keywords.waitTime1(5);
                    assertEquals(keywords.getFirstSelectedItemFromDropDown(inspectorReportstatusDropdown),
                                 appBasedUtils.getStringData(manageClaimJobData, "Status"));

                } else {
                    keywords.selectVisibleText(inspectorReportstatusDropdown, "Used");
                    keywords.waitTime1(5);
                    assertEquals(keywords.getFirstSelectedItemFromDropDown(inspectorReportstatusDropdown),
                                 appBasedUtils.getStringData(manageClaimJobData, "Status"));
                    keywords.clickOnButton(driver, saveClaimJobButton);
                    APP_LOGS.info("One Inspector report matched for this claim");
                    ExtentTestManager.getTest().log(Status.INFO, "One Inspector report matched for this claim");
                }
            } else {
                APP_LOGS.info("Inspector report/s not matched for this claim");
                ExtentTestManager.getTest().log(Status.INFO, "Inspector report/s not matched for this claim");
                Assert.fail();
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    public void validateSavedDataInOtherCostTabForCampaignClaimSave(HashMap<String, Object> manageClaimJobData) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 1, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 15, otherCostTabManageClaimJob);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", otherCostTabManageClaimJob);
            keywords.clickOnLink(otherCostTabManageClaimJob, driver);
            keywords.waitTime1(2);
            APP_LOGS.info("Validating values saved in OtherCost tab");
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"))) {
                Assert.assertEquals(otherCostTabDescSavedValue.get(0).getText(), appBasedUtils.getStringData(manageClaimJobData, "DescriptionOtherCost"));
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to update labour CAF, material CAF, travel CAF and other cost CAF in Assessmenttab
     *******************************************/
    public void updateNewCAFInAssessmenttab(HashMap<String, Object> newClaimJob) {
        try {
            APP_LOGS.info("Updating Debit code in assessment tab");
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.input(labourCAF, appBasedUtils.getStringData(newClaimJob, "CAFLabour"), "Labour CAF");
            keywords.input(materialCAF, appBasedUtils.getStringData(newClaimJob, "CAFMaterial"), "Material CAF");
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
                if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJob, "CAFTravel"))) {
                    keywords.input(travelCAF, appBasedUtils.getStringData(newClaimJob, "CAFTravel"), "Travel CAF");
                }
            }
            keywords.input(otherCostCAF, appBasedUtils.getStringData(newClaimJob, "CAFOtherCost"), "Other cost CAF");
            keywords.keyTab(otherCostCAF);
            keywords.clickOnButton(driver, saveClaimJobButton);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to fetch the validate labour CAF, material CAF, travel CAF and other cost CAF in Assessmenttab
     *******************************************/
    public void validateCAFInAssessmenttab(HashMap<String, Object> newClaimJob) {
        try {
            SoftAssert assessCAF = new SoftAssert();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            SoftAssert saMainDebitCode = new SoftAssert();
            APP_LOGS.info("Validating Debit code in assessment tab");
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            keywords.waitTime1(5);
            appBasedUtils.asserVerification("Labour CAF: ", appBasedUtils.getStringData(newClaimJob, "CAFLabour"),
                                            keywords.getAttributeValue(labourCAF, "value"), saMainDebitCode);
            appBasedUtils.asserVerification("Material CAF: ", appBasedUtils.getStringData(newClaimJob, "CAFMaterial"),
                                            keywords.getAttributeValue(materialCAF, "value"), saMainDebitCode);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
                if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJob, "CAFTravel"))) {
                    appBasedUtils.asserVerification("Travel CAF: ", appBasedUtils.getStringData(newClaimJob, "CAFTravel"),
                                                    keywords.getAttributeValue(travelCAF, "value"), saMainDebitCode);
                }
            }
            appBasedUtils.asserVerification("OtherCost CAF: ", appBasedUtils.getStringData(newClaimJob, "CAFOtherCost"),
                                            keywords.getAttributeValue(otherCostCAF, "value"), saMainDebitCode);
            assessCAF.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to validate labour cost, material cost, travel cost and other cost net in Assessmenttab
     *******************************************/
    public void validateCostChangedInLabourMaterialOthertab(HashMap<String, Object> newClaimJob) {
        companyName = keywords.getTextValue(companyNameElement);
        keywords.clickOnLink(labourTabManageClaimJob, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.clickOnLinkUsingAction(labourTabRedIcon, driver);
        keywords.waitTime1(2);
        String LabourOriginalNetCost[] = keywords.getTextValue(originalNetCost).split("\n");
        String updatedLabourCost = labourNet.get(0).getText();
        keywords.clickOnLink(claimJobMaterial, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.clickOnLinkUsingAction(materialTabRedIcon, driver);
        keywords.waitTime1(2);
        String MaterialOriginalNetCost[] = keywords.getTextValue(originalNetCost).split("\n");
        String updatedMaterialCost = materialNet.get(0).getText();
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
            if (appBasedUtils.isThisFieldNeedsToBeEntered(appBasedUtils.getStringData(newClaimJob, "CAFTravel"))) {
                keywords.clickOnLink(travelExpensesTabManageClaimJob, driver);
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
                String updatedTravelHoursNET = travelHoursNET.get(0).getText();
                String updatedTravelDistanceNET = travelDistanceNET.get(0).getText();
                String updatedOtherTravelExpenseNET = otherTravelExpenseNET.get(0).getText();
                Assert.assertEquals(appBasedUtils.getStringData(newClaimJob, "TravelHourNetCost"), updatedTravelHoursNET);
                Assert.assertEquals(appBasedUtils.getStringData(newClaimJob, "TraveOtherNetCost"), updatedTravelDistanceNET);
                Assert.assertEquals(appBasedUtils.getStringData(newClaimJob, "TraveDistanceNetCost"), updatedOtherTravelExpenseNET);
            }
        } else {
            APP_LOGS.info("No Travel Expense tab for : " + companyName);
        }
        keywords.clickOnLink(otherCostTabManageClaimJob, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.clickOnLinkUsingAction(otherCostTabRedIcon, driver);
        keywords.waitTime1(2);
        String otherCostOriginalNetCost[] = keywords.getTextValue(originalNetCost).split("\n");
        String updatedOtherCost = otherNet.get(0).getText();
        Assert.assertEquals(Double.valueOf(labourNetCost.replaceAll("\\s+", "")), Double.valueOf(LabourOriginalNetCost[1]));
        Assert.assertEquals(Double.valueOf(materialNetCost.replaceAll("\\s+", "")), Double.valueOf(MaterialOriginalNetCost[1]));
        Assert.assertEquals(Double.valueOf(otherNetCost.replaceAll("\\s+", "")), Double.valueOf(otherCostOriginalNetCost[1]));
        Assert.assertEquals(updatedLabourCost, appBasedUtils.getStringData(newClaimJob, "LabourNetCost"));
        Assert.assertEquals(updatedMaterialCost, appBasedUtils.getStringData(newClaimJob, "MaterialNetCost"));
        Assert.assertEquals(updatedOtherCost, appBasedUtils.getStringData(newClaimJob, "OtherNetCost"));
    }

    public void setClaimReferenceNoToExcelSheet(HashMap<String, Object> newClaimJob) {
        claimExcelSheet = new ExcelReadAndWrite(".\\resources\\TestData\\" + BaseClass.getEnvironmentName() + "\\NewMaterialRequestAndNewClaim.xlsx",
                                                BaseClass.excelSheetName);
        claimExcelSheet.updateExcelRowData(Integer.valueOf(appBasedUtils.getStringData(newClaimJob, "RefNoRowNo")),
                                           Integer.valueOf(appBasedUtils.getStringData(newClaimJob, "RefNoColNo")), claimReferenceNumber);
    }

    /*********************************************
     * #Description : This method is to validate TMA Decision in Claim Job information tab
     *******************************************/
    public void validateTMADecisionInClaimJobInformationTab(HashMap<String, Object> newClaimJob) {
        saTMADecision = new SoftAssert();
        keywords.waitForVisibilityOfElement(driver, 15, claimJobInformationTabManageClaimJob);
        keywords.clickOnButton(driver, claimJobInformationTabManageClaimJob);
        keywords.waitForVisibilityOfElement(driver, 15, TMADecision);
        TMADecisionInClaimJobInformation = keywords.getTextValue(TMADecision);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(newClaimJob, "TMADecisionForPartlyApproved"), TMADecisionInClaimJobInformation,
                                        saTMADecision);
        ExtentTestManager.getTest().log(Status.INFO, "TMA Decision in Claim job information tab is: " + TMADecisionInClaimJobInformation);
        saTMADecision.assertAll();
    }

    public void closeThePageAndReturnToHomePage() {
        keywords.clickOnButton(driver, closeThePage);
        keywords.waitTime1(2);
    }

    /*********************************************
     * #Description : This method is to validate coverage in assessment tab
     *******************************************/
    public void validateCoverage(HashMap<String, Object> NewCoverage) {
        try {
            APP_LOGS.info("Validating coverage in assessment tab");
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 20, loadingIcon);
                Assert.assertEquals(appBasedUtils.getStringData(NewCoverage, "Expected Coverage"), keywords.getTextValue(coverageIdVCE.get(0)));
                Assert.assertEquals(appBasedUtils.getStringData(NewCoverage, "DebitCode"), keywords.getTextValue(debitCodeAssessment.get(0)));
            } else {
                Assert.assertEquals(appBasedUtils.getStringData(NewCoverage, "Expected Coverage"), keywords.getTextValue(coverageId.get(0)));
                Assert.assertEquals(appBasedUtils.getStringData(NewCoverage, "DebitCode"), keywords.getTextValue(debitCodeAssessment.get(0)));
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : This method is to split the cost till splitcost2 and validate saved cost and percentage values saved in assessment tab
     *******************************************/
    public void splitCostAndValidate(HashMap<String, Object> newClaimJob) {
        try {
            SoftAssert saMainDebitCode = new SoftAssert();
            String firstXpathOfDebitCodeSelection = "//a[contains(text(),'";
            String secondXpathOfDebitCodeSelection = "')]";
            String debitCodeSelection = null;
            companyName = keywords.getTextValue(companyNameElement);
            keywords.clickOnLink(claimJobAssessmentTab, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            keywords.waitTime1(2);
            keywords.clickOnButton(driver, labourSplitCost1DebitClick);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "Splitcost1DebitofTypeReject")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            keywords.clickOnButton(driver, materialSplitCost1DebitClick);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "Splitcost1DebitofTypeReject")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, otherCostSplitCost1DebitClick);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "Splitcost1DebitofTypeReject")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                // keywords.clickOnButton(travelSplitCost1DebitClick);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "Splitcost1DebitofTypeReject")
                        + secondXpathOfDebitCodeSelection;
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }

            keywords.clickOnButton(driver, splitCost1LabourRejectCode);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();

            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            // Select material rejection code
            keywords.clickOnButton(driver, splitCost1MaterialRejectCode);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfMaterial")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            // Select travel expense yet to do
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.clickOnButton(driver, travelExpenseRejectionCodeSelection);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfTravelExpense")
                        + secondXpathOfDebitCodeSelection;
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            // other code rejectioncode selection
            keywords.clickOnButton(driver, splitCost1OtherCostRejectCode);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfOtherCost")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();

            keywords.input(labourTotalPerncentageSplit.get(1), appBasedUtils.getStringData(newClaimJob, "SplitcostHalfPercentageOnLabour_1"),
                           "Splitcost1PercentageOnLabour");
            keywords.waitTime(2000);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.clickOnButton(driver, labourTotalPercentage);
            keywords.focusingOutOfElementUsingAction(driver, labourTotalPercentage, claimJobTitle);

            keywords.input(materialTotalPercentageList.get(1), appBasedUtils.getStringData(newClaimJob, "SplitcostHalfPercentageOnMaterial_1"),
                           "Splitcost1PercentageOnMaterial");
            keywords.focusingOutOfElementUsingAction(driver, materialTotalPercentageList.get(1), claimJobTitle);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.waitTime(600);
                keywords.focusingOutOfElementUsingAction(driver, travelTotalPercentage.get(1), claimJobTitle);
                keywords.input(travelTotalPercentage.get(1), appBasedUtils.getStringData(newClaimJob, "MainTravelExpensePercentage"), "travelTotalPercentage");
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.input(otherCostTotalPercentage.get(1), appBasedUtils.getStringData(newClaimJob, "SplitcostHalfPercentageOnOtherCost_1"),
                           "SplitCost1PercentageOtherCost");
            keywords.focusingOutOfElementUsingAction(driver, otherCostTotalPercentage.get(1), claimJobTitle);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            keywords.clickOnButton(driver, labourSplitCost2DebitClick);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeLabour_1")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            keywords.clickOnButton(driver, materialSplitCost2DebitClick);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeMaterial_1")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, otherCostSplitCost2DebitClick);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeOtherCost_1")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.clickOnButton(driver, travelSplitCost2DebitClick);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(newClaimJob, "Splitcost1DebitofTypeReject")
                        + secondXpathOfDebitCodeSelection;
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }
            keywords.clickOnButton(driver, saveClaimJobButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 15, loadingIcon);
            keywords.waitTime(2000);
            appBasedUtils.asserVerification("Labour debit code ", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeLabour_1"),
                                            keywords.getAttributeValue(labourSplitCost2DebitClick, "value"), saMainDebitCode);

            appBasedUtils.asserVerification("RejectCodeReasonOfLabour", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeMaterial_1"),
                                            keywords.getAttributeValue(materialSplitCost2DebitClick, "value"), saMainDebitCode);
            appBasedUtils.asserVerification("MainPercentageOfLabour", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeOtherCost_1"),
                                            keywords.getAttributeValue(otherCostSplitCost2DebitClick, "value"), saMainDebitCode);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                appBasedUtils.asserVerification("SplitLabourDebitCodeReason", appBasedUtils.getStringData(newClaimJob, "SplitCostDebitCodeTravel_1"),
                                                keywords.getAttributeValue(travelSplitCost2DebitClick, "value"), saMainDebitCode);
            }
            releaseClaimjob();
            ExtentTestManager.getTest().log(Status.INFO, "Cost is splitted till splicost2 for this claim ");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Failed: Split cost fucntionality failed for this claim");
        }
    }

    /*********************************************
     * #Description : This method is to validate the new stairs data in claim assessment tab.
     *******************************************/
    public void validateStairsInAssessmentTab(HashMap<String, Object> newClaimJob) {
        companyName = keywords.getTextValue(companyNameElement);
        SoftAssert saStairs = new SoftAssert();
        APP_LOGS.info("Updating Debit code in assessment tab");
        keywords.clickOnLink(claimJobAssessmentTab, driver);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        appBasedUtils.asserVerification("Labour debit code ", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfLabour"),
                                        keywords.getAttributeValue(splitCost1LabourDebitCodeField, "value"), saStairs);
        appBasedUtils.asserVerification("Debit Code Of Material", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfMaterial"),
                                        keywords.getAttributeValue(splitCost1MaterialDebitCodeField, "value"), saStairs);
        appBasedUtils.asserVerification("Debit Code Of OtherCosts", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfOtherCosts"),
                                        keywords.getAttributeValue(splitCost1OtherDebitCodeField, "value"), saStairs);
        appBasedUtils.asserVerification("Labour Reject code ", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfLabour"),
                                        keywords.getAttributeValue(splitCost1LabourRejectCodeField, "value"), saStairs);
        appBasedUtils.asserVerification("Reject Code Of Material", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfMaterial"),
                                        keywords.getAttributeValue(splitCost1MaterialRejectCodeField, "value"), saStairs);
        appBasedUtils.asserVerification("Reject Code Of OtherCosts", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfOtherCost"),
                                        keywords.getAttributeValue(splitCost1OtherRejectCodeField, "value"), saStairs);
        appBasedUtils.asserVerification("Labour Reject Percent ", appBasedUtils.getStringData(newClaimJob, "MainLabourPercentage"),
                                        keywords.getAttributeValue(splitCost1LabourPercentage, "value"), saStairs);
        appBasedUtils.asserVerification("Material Reject Percent", appBasedUtils.getStringData(newClaimJob, "MainMaterialPercentage"),
                                        keywords.getAttributeValue(splitCost1MaterialPercentage, "value"), saStairs);
        appBasedUtils.asserVerification("OtherCosts Reject Percent", appBasedUtils.getStringData(newClaimJob, "MainOtherCostPercentage"),
                                        keywords.getAttributeValue(splitCost1OtherPercentage, "value"), saStairs);
        if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
            appBasedUtils.asserVerification("Debit Code Of Travel", appBasedUtils.getStringData(newClaimJob, "DebitCodeOfTravel"),
                                            keywords.getAttributeValue(splitCost1TravelDebitCodeField, "value"), saStairs);
            appBasedUtils.asserVerification("Reject Code Of Travel", appBasedUtils.getStringData(newClaimJob, "RejectCodeReasonOfTravel"),
                                            keywords.getAttributeValue(splitCost1TravelRejectCodeField, "value"), saStairs);
            appBasedUtils.asserVerification("Travel Reject Percent", appBasedUtils.getStringData(newClaimJob, "MainTravelPercentage"),
                                            keywords.getAttributeValue(splitCost1TravelPercentage, "value"), saStairs);
        }
        saStairs.assertAll();
    }

    /****************************************************************************************************************
     * #Description : Verify Claim job title, Header tab, Save header button and New claim job button after save.
     ****************************************************************************************************************/
    public void verifyHeaderScreen(HashMap<String, Object> data) {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
        keywords.clickOnButton(driver, claimJobHeader);
        keywords.elementIsDisplayed(claimJobTitle, "Claim job title");
        keywords.elementIsDisplayed(claimJobHeader, "Header tab");
        keywords.elementIsEnabled(saveHeaderButton);
        keywords.elementIsEnabled(newClaimJobButton);
        ExtentTestManager.getTest().log(Status.INFO,
                                        "Claim job title, Header tab, Save header button " + "and New claim job button have been verified successfully");

    }

    /****************************************************************************************************************
     * #Description : Verify Repair Header section details after save.
     ****************************************************************************************************************/
    public void verifyRepairHeaderSection(HashMap<String, Object> data) {

        try {
            String importerName[] = keywords.getTextValue(importerValue).split(" ");
            String importerNo = importerName[0];
            Assert.assertEquals(importerNo, data.get("Importer"));

            String dealerName[] = keywords.getTextValue(dealerValue).split(" ");
            String dealerNo = dealerName[0];
            Assert.assertEquals(dealerNo, data.get("Dealer"));

            Assert.assertEquals(keywords.elementIsDisplayed(referenceNoValue, "Reference number value"), true);
            Assert.assertEquals(keywords.elementIsDisplayed(repairOrderNoValue, "Repair order number value"), true);
            Assert.assertEquals(keywords.getTextValue(companyValue), data.get("CompanyName"));
            Assert.assertEquals(keywords.getTextValue(currencyValue), data.get("Currency"));
        } catch (Exception e) {
            e.getMessage();
            Assert.fail();
        }

    }

    /****************************************************************************************************************
     * #Description : Verify Vehicle identification section details after save.
     ****************************************************************************************************************/
    public void verifyVehicleIdentificationSection(HashMap<String, Object> data) {
        try {
            Assert.assertEquals(keywords.getAttributeValue(vinValue, "value"), data.get("VIN"));
            Assert.assertEquals(keywords.getTextValue(deliveringImporterValue), data.get("DeliveringImporter"));
            Assert.assertEquals(keywords.getTextValue(deliveringDealerValue), data.get("DeliveringDealer"));
            Assert.assertEquals(keywords.getTextValue(deliveryDateValue), data.get("DeliveryDate"));
            Assert.assertEquals(keywords.getTextValue(usingCustomerValue), data.get("UsingCustomer"));
        } catch (Exception e) {
            e.getMessage();
            Assert.fail();
        }

    }

    /****************************************************************************************************************
     * #Description : Verify Header information is updated.
     ****************************************************************************************************************/
    public void verifyUpdatedHeaderInformation(HashMap<String, Object> data) {
        try {
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            Assert.assertEquals(keywords.getTextValue(usingCustomerValue), data.get("UpdatedUsingCustomer"));
            Assert.assertEquals(keywords.getTextValue(deliveringImporterValue), data.get("UpdatedDeliveringImporter"));
            Assert.assertEquals(keywords.getTextValue(deliveringDealerValue), data.get("UpdatedDeliveringDealer"));
            Assert.assertEquals(keywords.getTextValue(deliveryDateValue), data.get("UpdatedDeliveryDate"));
            APP_LOGS.info("Updated header information is verified successfully");
            ExtentTestManager.getTest().log(Status.INFO, "Updated using Customer is: " + keywords.getTextValue(usingCustomerValue));
            ExtentTestManager.getTest().log(Status.INFO, "Updated Delivering Importer is: " + keywords.getTextValue(deliveringImporterValue));
            ExtentTestManager.getTest().log(Status.INFO, "Updated Delivering Dealer is: " + keywords.getTextValue(deliveringDealerValue));
            ExtentTestManager.getTest().log(Status.INFO, "Updated Delivery Date is: " + keywords.getTextValue(deliveryDateValue));
            ExtentTestManager.getTest().log(Status.INFO, "Updated header information is verified successfully");

        } catch (Exception e) {
            e.getMessage();
            Assert.fail();
        }

    }
}