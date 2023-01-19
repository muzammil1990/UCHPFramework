package com.volvo.in.page.welcomepage;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.UIUtils;

public class MenuSubMenuVerification {
    private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
    private static final String VOLVO_BUS_CORPORATION = "Volvo Bus Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";
    private static final String TSM_AMERICAS = "TSM Americas";
    private static final String UD_TRUCKS_CORPORATION = "UD Truck Corporation";
    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    SoftAssert softAssert = new SoftAssert();
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    public Logger APP_LOGS = Logger.getLogger(MenuSubMenuVerification.class);
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Crediting')]")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Print credit document')]")
    WebElement creditingPrintCreditDocumentElement;

    @FindBy(how = How.XPATH, using = "//print-credit-document/descendant::h4[contains(text(),'rint credit document')]")
    WebElement creditingPrintCreditDocumenHeadertElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Create outstanding claim summary')]")
    WebElement creditingCreateOutStandingClaimSummaryElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Create outstanding claim summary')]")
    WebElement creditingCreateOutStandingClaimSummaryCompanyElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Print outstanding claim summary ')]")
    WebElement creditingPrintOutStandingClaimSummaryElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'View scheduled credit run')]")
    WebElement creditingViewScheduledCreditRun;

    @FindBy(how = How.XPATH, using = "(//a[contains(text(), 'Create/modify scheduling')])[1]")
    WebElement creditingCreateModifyScheduling;

    @FindBy(how = How.XPATH, using = "(//a[contains(text(), 'Approve credit runs')])[1]")
    WebElement creditingApproveCreditRuns;

    @FindBy(how = How.XPATH, using = "(//a[contains(text(), 'Print outstanding claim summary (Company)')])[1]")
    WebElement creditingPrintOutStandingClaimSummaryCompanyElement;

    @FindBy(how = How.XPATH, using = "//create-outstanding-claim-summary/descendant::h4[contains(., 'reate outstanding claim summary')]")
    WebElement creditingCreateOutStandingClaimSummHeadertElement;

    @FindBy(how = How.XPATH, using = "//create-outstanding-claim-summary-company/descendant::h4[contains(., 'reate outstanding claim summary (Company)')]")
    WebElement creditingCreateOutStandingClaimSummCompanyHeadertElement;

    @FindBy(how = How.XPATH, using = "//print-outstanding-claim-summary/descendant::h4[contains(., 'rint outstanding claim summary')]")
    WebElement creditingPrintOutStandingClaimSummHeadertElement;

    @FindBy(how = How.XPATH, using = "(//print-outstanding-claim-summary-company/descendant::h4[contains(., 'Print outstanding claim summary (Company)')])[1]")
    WebElement creditingPrintOutStandingClaimSummCompanyHeadertElement;

    @FindBy(how = How.XPATH, using = "(//view-scheduled-credit-runs/descendant::h4[contains(., 'View scheduled credit runs')])[1]")
    WebElement creditingViewScheduledCreditRunsHeaderElement;

    @FindBy(how = How.XPATH, using = "//create-modify-schedule/descendant::h4[contains(., 'Create/modify scheduling')]")
    WebElement creditingCreateModifySchedulingElement;

    @FindBy(how = How.XPATH, using = "//approve-credit-runs/descendant::h4[contains(., 'Approve credit runs')]")
    WebElement creditingApproveCreditRunsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Vehicle')])[1]")
    WebElement vehicleMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//view-repair-history/descendant::h4[contains(., 'iew repair history')])[1]")
    WebElement vehicleViewRepairHistoryHeaderElement;

    @FindBy(how = How.XPATH, using = "//a[@ui-sref='app.view-repair-history' and contains(., 'View repair history ')]")
    WebElement vehicleViewRepairHistorySubMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'contract information')])[1]")
    WebElement vehicleViewContractInfoSubMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'contract coverage')])[1]")
    WebElement vehicleViewContractCoverageSubMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List check scenarios')])[1]")
    WebElement validationListCheckScenarios;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List coverage')])[1]")
    WebElement coverageListCoverage;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New coverage')])[1]")
    WebElement coverageNewCoverage;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List coverage per vehicle')])[1]")
    WebElement coverageListCoveragePerVehicle;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List coverage per machine')])[1]")
    WebElement coverageListCoveragePerMachine;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New coverage per vehicle')])[1]")
    WebElement coverageNewCoveragePerVehicle;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New coverage per machine')])[1]")
    WebElement coverageNewCoveragePerMachine;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New coverage per driveline')])[1]")
    WebElement coverageNewCoveragePerDriveline;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List coverage per driveline')])[1]")
    WebElement coverageListCoveragePerDriveline;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search and restart')])[1]")
    WebElement systemAdminSearchAndRestart;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List function group ranges')])[1]")
    WebElement coverageFunctionGroupRange;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List defect code ranges')])[1]")
    WebElement coverageDefectCodeRange;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List operation no. ranges')])[1]")
    WebElement coverageOperationNoRange;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List checks')])[1]")
    WebElement validationListChecks;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New check scenario')])[1]")
    WebElement validationNewCheckScenario;

    @FindBy(how = How.XPATH, using = "//a[@ui-sref='app.view-coverage' and contains(., 'View warranty coverage ')]")
    WebElement vehicleViewCoverageSubMenuElement;

    @FindBy(how = How.XPATH, using = "(//view-coverage/descendant::h4[contains(., 'View warranty coverage')])[1]")
    WebElement vehicleViewCoverageHeaderElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'View technical data ')])[1]")
    WebElement vehicleViewTechnicalDataSubMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'View campaign')])[1]")
    WebElement vehicleViewCampaignSubMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'cost history')])[1]")
    WebElement vehicleViewCostHistorySubMenuElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claimMainMenuElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintainMainMenuElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Inspector report ']")
    WebElement inspectorReportMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New claim job')])[1]")
    WebElement claimNewClaimJobElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New report')])[1]")
    WebElement inspectorReportNewReportElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search report')])[1]")
    WebElement inspectorReportSearchReportElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Claim tables')])[1]")
    WebElement maintainClaimTablesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Claim setup')])[1]")
    WebElement maintainClaimSetUpElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'TMA setup')])[1]")
    WebElement maintainTMASetUpElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Contract')])[1]")
    WebElement maintainContractElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Stairs')])[1]")
    WebElement stairs;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Hold for crediting')])[1]")
    WebElement holdForCrediting;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Campaign setup')])[1]")
    WebElement maintainCampaignSetup;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Dealer')])[2]")
    WebElement maintainDealer3;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Self-billing')])[1]")
    WebElement maintainSelfBilling;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Importer')])[1]")
    WebElement maintainImporterElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty area')])[1]")
    WebElement maintainWarrantyAreaElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty district')])[1]")
    WebElement maintainWarrantyDistrictElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Dealer')])[1]")
    WebElement maintainDealer2Element;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Business partner')])[1]")
    WebElement maintainBusinessPartnerElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Business partner')])[2]")
    WebElement maintainBusinessPartner;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Company')])[3]")
    WebElement maintainCompanyElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'VAT')])[4]")
    WebElement maintainVATElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Dealer notifications')])[1]")
    WebElement maintainDealerNoti;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Business partner notifications')])[1]")
    WebElement maintainBusinessPartnerNoti;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Auto exception')])[1]")
    WebElement autoForException;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List hold for crediting')])[1]")
    WebElement listHoldForCrediting;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List auto exception')])[1]")
    WebElement listAutoException;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List stairs')])[1]")
    WebElement listStairs;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Company')])[4]")
    WebElement maintainCompany;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Connections')])[1]")
    WebElement connections;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']/../descendant::a[2]")
    WebElement maintainImportertElement;

    @FindBy(how = How.XPATH, using = "//list-dealers/descendant::h4[contains(., 'Dealer')]")
    WebElement maintainDealer3tElement;

    @FindBy(how = How.XPATH, using = "//list-dealers/descendant::h4[contains(., 'Business partner')]")
    WebElement maintainBusinessPartner2Element;

    @FindBy(how = How.XPATH, using = "//maintain-self-billing/descendant::h4[contains(., 'Self-billing')]")
    WebElement maintainSelfBillingElement;

    @FindBy(how = How.XPATH, using = "//maintain-company/descendant::h4[contains(., 'Company')]")
    WebElement maintainCompany2Element;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'VAT agent')])[1]")
    WebElement maintainVATAgent;

    @FindBy(how = How.XPATH, using = "//maintain-vatagent/descendant::h4[contains(., 'VAT agent')]")
    WebElement maintainVATAgentElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Importer company VAT')])[1]")
    WebElement maintainImporterCompany;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty area company VAT')])[1]")
    WebElement maintainWarrantyAreaCompany;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'European VAT')])[1]")
    WebElement maintainEuropeanVAT;

    @FindBy(how = How.XPATH, using = "//maintain-company-importer-vat/descendant::h4[contains(., 'Importer company VAT')]")
    WebElement maintainImporterCompanyElement;

    @FindBy(how = How.XPATH, using = "//maintain-company-importer-vat/descendant::h4[contains(., 'Warranty area company VAT')]")
    WebElement maintainWarrantyAreaCompanyElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Machine')])[1]")
    WebElement machineMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Driveline')])[1]")
    WebElement drivelineMainMenuElement;

    @FindBy(how = How.XPATH, using = "//maintain-european-vat/descendant::h4[contains(., 'European VAT')]")
    WebElement maintainEuropeanVATElement;

    @FindBy(how = How.XPATH, using = "//list-vat/descendant::h4[contains(., 'List VAT')]")
    WebElement maintainListVATElement;

    @FindBy(how = How.XPATH, using = "//maintain-dealer-notifications/descendant::h4[contains(., 'Maintain dealer notifications')]")
    WebElement maintainDealerNotiElement;

    @FindBy(how = How.XPATH, using = "//maintain-dealer-notifications/descendant::h4[contains(., 'Maintain business partner notifications')]")
    WebElement maintainBusinessPartnerNotiElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Debit code')])[1]")
    WebElement maintainClaimTablesDebitCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Volvo prefix')])[1]")
    WebElement maintainClaimTablesVolvoPrefixElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Authorized currencies')])")
    WebElement maintainAuthorizedCurrency;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Claim job ref. no. series')])[1]")
    WebElement maintainClaimJobRef;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Rollout')])[1]")
    WebElement maintainClaimTablesRollOutElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Contract price source')])[1]")
    WebElement maintainImporterContractPriceSourceElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Defect code')])[1]")
    WebElement maintainClaimTablesDefectCodeElement;

    @FindBy(how = How.XPATH, using = "//maintain-debit-code/descendant::h4[contains(., 'Maintain debit code')]")
    WebElement maintainClaimTablesDebitCodeHeaderElement;

    @FindBy(how = How.XPATH, using = "//maintain-volvo-prefix/descendant::h4[contains(., 'Maintain volvo prefix')]")
    WebElement maintainClaimTablesVolvoPrefixHeaderElement;

    @FindBy(how = How.XPATH, using = "//maintain-authorized-currency/descendant::h4[contains(., 'Maintain authorized currency')]")
    WebElement maintainClaimSetUpAuthorizedCurrencyElement;

    @FindBy(how = How.XPATH, using = "//maintain-claim-ref-no-serie/descendant::h4[contains(., 'Maintain claim job ref')]")
    WebElement maintainClaimSetUpClaimJobRefElement;

    @FindBy(how = How.XPATH, using = "//maintain-contract-rollout/descendant::h4[contains(., 'Maintain contract rollout')]")
    WebElement maintainClaimTablesRolloutHeaderElement;

    @FindBy(how = How.XPATH, using = "//list-stairs/descendant::h4[contains(., 'List stairs')]")
    WebElement maintainListStairs;

    @FindBy(how = How.XPATH, using = "//stair-connections/descendant::h4[contains(., 'Connections')]")
    WebElement maintainConnections;

    @FindBy(how = How.XPATH, using = "//list-hold-for-crediting/descendant::h4[contains(., 'List hold for crediting')]")
    WebElement maintainListHoldForCrediting;

    @FindBy(how = How.XPATH, using = "//list-auto-exception/descendant::h4[contains(., 'List auto exception')]")
    WebElement maintainAutoException;

    @FindBy(how = How.XPATH, using = "//maintain-contract-price-source/descendant::h4[contains(., 'Contract price source')]")
    WebElement maintainImporterContractPriceSourceHeaderElement;

    @FindBy(how = How.XPATH, using = "//maintain-defect-code/descendant::h4[contains(., 'Maintain defect code')]")
    WebElement maintainClaimTablesDefectCodeHeaderElement;

    @FindBy(how = How.XPATH, using = "(//new-claim-job/descendant::h4[contains(., 'New claim job')])[1]")
    WebElement claimNewClaimJobHeaderElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List claim jobs')])[1]")
    WebElement claimListClaimJobElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Advanced search')])[1]")
    WebElement claimAdvancedSearchElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Update several claim jobs')])[1]")
    WebElement claimUpdateSeveralClaimJobElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search claim jobs ')])[1]")
    WebElement claimUpdateSeveralClaimJobSearchClaimJobsSubElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List order')])[1]")
    WebElement claimUpdateSeveralClaimJobListOrderSubElement;

    @FindBy(how = How.XPATH, using = "//list-claim-jobs-search-filter/descendant::h4[contains(., 'ist claim jobs')]")
    WebElement claimListClaimJobHeaderElement;

    @FindBy(how = How.XPATH, using = "//advanced-search/descendant::h4[contains(., 'dvanced search')]")
    WebElement claimAdvancedSearchHeaderElement;

    @FindBy(how = How.XPATH, using = "//inspector-report-details/descendant::h4[contains(., 'New inspector report')]")
    WebElement inspectorReportNewReportHeaderElement;

    @FindBy(how = How.XPATH, using = "//inspector-report/descendant::h4[contains(., 'Inspector report')]")
    WebElement inspectorReportSearchReportHeaderElement;

    @FindBy(how = How.XPATH, using = "//search-tma-information/descendant::h4[contains(., 'Search TMA information')]")
    WebElement searchTMAInfoElement;

    @FindBy(how = How.XPATH, using = "//multi-update-claim-jobs-search/descendant::h4[contains(., 'Search claim jobs')]")
    WebElement claimUpdateSeveralClaimJobsSearchClaimJobsHeaderElement;

    @FindBy(how = How.XPATH, using = "//multi-update-claim-jobs-list-order/descendant::h4[contains(., 'List order')]")
    WebElement claimUpdateSeveralClaimJobsListOrderHeaderElement;

    @FindBy(how = How.XPATH, using = "//label[contains(.,'Username or email')]")
    WebElement childWindowXpath;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//view-campaign/descendant::h4[contains(., 'View campaign')]")
    WebElement vehicleViewCampaignHeaderElement;

    @FindBy(how = How.XPATH, using = "//h4[@class='page-title ng-binding ng-scope']")
    WebElement vehicleViewCostHistoryHeaderElement;

    @FindBy(how = How.XPATH, using = "//view-contract-information/descendant::h4[contains(., 'View contract information')]")
    WebElement vehicleViewContractInfoHeaderElement;

    @FindBy(how = How.XPATH, using = "//view-contract-coverage/descendant::h4[contains(., 'View contract coverage')]")
    WebElement vehicleViewContractCoverageHeaderElement;

    @FindBy(how = How.XPATH, using = "//validation-scenario/descendant::h4[contains(., 'Check scenarios')]")
    WebElement validationCheckScenariosElement;

    @FindBy(how = How.XPATH, using = "//list-coverages/descendant::h4[contains(., 'Coverages specification')]")
    WebElement coverageCoverageSpecifElement;

    @FindBy(how = How.XPATH, using = "//coverage-details/descendant::h4[contains(., 'New coverage')]")
    WebElement coverageNewCoverageElement;

    @FindBy(how = How.XPATH, using = "//coverage-vehicle-rule-list/descendant::h4[contains(., 'List coverage per vehicle rule')]")
    WebElement coverageListCoveragePerRule;

    @FindBy(how = How.XPATH, using = "//coverage-vehicle-rule-list/descendant::h4[contains(., 'List coverage per machine rule')]")
    WebElement coverageListCoveragePerMachineRule;

    @FindBy(how = How.XPATH, using = "//coverage-vehicle-rule-new/descendant::h4[contains(., 'New coverage per vehicle rule')]")
    WebElement coverageNewCoveragePerRule;

    @FindBy(how = How.XPATH, using = "//coverage-vehicle-rule-new/descendant::h4[contains(., 'New coverage per machine rule')]")
    WebElement coverageNewCoveragePerMachineRule;

    @FindBy(how = How.XPATH, using = "//coverage-vehicle-rule-new/descendant::h4[contains(., 'New coverage per driveline rule')]")
    WebElement coverageNewCoverageDrivelinePerRule;

    @FindBy(how = How.XPATH, using = "//coverage-vehicle-rule-list/descendant::h4[contains(., 'List coverage per driveline rule')]")
    WebElement coverageListCoverageDrivelinePerRule;

    @FindBy(how = How.XPATH, using = "//restart-credit-search/descendant::h4[contains(., 'Search credit run')]")
    WebElement systemAdminSearchElement;

    @FindBy(how = How.XPATH, using = "//list-function-group-ranges/descendant::h4[contains(., 'List function')]")
    WebElement coverageListFuncGrpRangesElement;

    @FindBy(how = How.XPATH, using = "//list-defect-code-ranges/descendant::h4[contains(., 'List defect code')]")
    WebElement coverageDefectCodeElement;

    @FindBy(how = How.XPATH, using = "//list-operation-no-ranges/descendant::h4[contains(., 'List operation no')]")
    WebElement coverageOperationNoElement;

    @FindBy(how = How.XPATH, using = "//list-campaign/descendant::h4[contains(., 'List campaigns')]")
    WebElement campaignSearchCampaignElement;

    @FindBy(how = How.XPATH, using = "//validation-check/descendant::h4[contains(., 'Checks')]")
    WebElement validationCheckElement;

    @FindBy(how = How.XPATH, using = "//validation-scenario-add/descendant::h4[contains(., 'New check scenario')]")
    WebElement validationNewCheckScenarioElement;

    @FindBy(how = How.XPATH, using = "//img[@src='logo_volvo.gif']")
    WebElement externalSystemLoginPageBodyText;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Currency rates')])")
    WebElement maintainCurrencyRates;

    @FindBy(how = How.XPATH, using = "//maintain-currency-rate/descendant::h4[contains(., 'Maintain currency rate')]")
    WebElement maintainCurrencyRateElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Defect code')])")
    WebElement maintainDefectCode;

    @FindBy(how = How.XPATH, using = "//maintain-defect-code/descendant::h4[contains(., 'Maintain defect code')]")
    WebElement maintainDefectCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Function group')])")
    WebElement maintainFunctionGroup;

    @FindBy(how = How.XPATH, using = "//maintain-function-group/descendant::h4[contains(., 'Maintain function group')]")
    WebElement maintainFunctionGroupElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Return code')])")
    WebElement maintainReturnCode;

    @FindBy(how = How.XPATH, using = "//maintain-return-code/descendant::h4[contains(., 'Maintain return code')]")
    WebElement maintainReturnCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'CAF SCC')])")
    WebElement maintainCAFSCCGroup;

    @FindBy(how = How.XPATH, using = "//maintain-caf-scc-group/descendant::h4[contains(., 'Maintain CAF SCC')]")
    WebElement maintainCAFSCCElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Claim system parameter')])")
    WebElement maintainClaimSystemParameter;

    @FindBy(how = How.XPATH, using = "//maintain-system-params/descendant::h4[contains(., 'Claim system')]")
    WebElement maintainSystemParamElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Case source')])")
    WebElement maintainCaseSource;

    @FindBy(how = How.XPATH, using = "//maintain-case-source/descendant::h4[contains(., 'Maintain case source')]")
    WebElement maintainCaseSourceElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Country language')])")
    WebElement maintainCountryLanguage;

    @FindBy(how = How.XPATH, using = "//maintain-country-language/descendant::h4[contains(., 'Maintain country language')]")
    WebElement maintainCountryLanguageElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Debit code')])")
    WebElement maintainDebitCode;

    @FindBy(how = How.XPATH, using = "//maintain-debit-code/descendant::h4[contains(., 'Maintain debit code')]")
    WebElement maintainDebitCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Follow up code')])")
    WebElement maintainFollowUpCode;

    @FindBy(how = How.XPATH, using = "//maintain-follow-up-code/descendant::h4[contains(., 'Maintain follow up code')]")
    WebElement maintainFollowUpCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Reject code')])")
    WebElement maintainRejectCode;

    @FindBy(how = How.XPATH, using = "//maintain-reject-code/descendant::h4[contains(., 'Maintain reject code')]")
    WebElement maintainRejectCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Cost adjustment')])")
    WebElement maintainCostAdjustmentFactor;

    @FindBy(how = How.XPATH, using = "//maintain-cost-adj-factor/descendant::h4[contains(., 'Maintain cost adjustment')]")
    WebElement maintainCostAdjustmentFactorElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Material groups')])")
    WebElement maintainMaterialGroups;

    @FindBy(how = How.XPATH, using = "//maintain-material-group/descendant::h4[contains(., 'Maintain material group')]")
    WebElement maintainMaterialGroupsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'My not evaluated')])")
    WebElement maintainMyNotEvaluated;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Dealer')])[1]")
    WebElement maintainDealer;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List my not evaluated')])")
    WebElement maintainListMyNotEvaluated;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Dealer')])[2]")
    WebElement maintainSubMenuDealer;

    @FindBy(how = How.XPATH, using = "//list-my-not-evaluated/descendant::h4[contains(., 'List my not evaluated')]")
    WebElement maintainListMyNotEvaluatedElement;

    @FindBy(how = How.XPATH, using = "//list-dealers/descendant::h4[contains(., 'Dealer')]")
    WebElement maintainDealerElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Validation ']")
    WebElement validationMainMenuElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Coverage ']")
    WebElement coverageMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//*[@id='navbar-main']//a[text()='Campaign '])[1]")
    WebElement campaignMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'System admin')])[1]")
    WebElement systemAdminMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Campaign')])[2]")
    WebElement campaignCampaignElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search campaign')])")
    WebElement campaignSearchCampaign;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Alerts')])[1]")
    WebElement campaignAlertsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Follow up')])[1]")
    WebElement campaignFollowUpElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List campaign')])")
    WebElement campaignListCampaignElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'TMA')])")
    WebElement tmaMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Delivery confirm, receive')])")
    WebElement tmaDeliveryConfirmReceiveElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Storage and shipping')])[1]")
    WebElement tmaStorageAndShippingElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Material request')])[1]")
    WebElement tmaMaterialRequestElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search TMA information')])[1]")
    WebElement tmaSearchTMAInfoElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Manual start receive')])")
    WebElement tmaManualStartReceiveAnalyze;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Manual start delivery')])")
    WebElement tmaManualStartDeliveryConfirm;

    @FindBy(how = How.XPATH, using = "//start-receive-analyse/descendant::h4[contains(., 'Manual start receive')]")
    WebElement tmaManualStartReceiveElement;

    @FindBy(how = How.XPATH, using = "//start-delivery-confirm/descendant::h4[contains(., 'Manual start delivery')]")
    WebElement tmaManualStartDeliveryElement;

    @FindBy(how = How.XPATH, using = "//receive-analyse-search/descendant::h4[contains(., 'Search')]")
    WebElement tmaSearchElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Approval codes')])[1]")
    WebElement maintainApprovalCodes;

    @FindBy(how = How.XPATH, using = "//maintain-approval-codes/descendant::h4[contains(., 'Approval codes')]")
    WebElement maintainApprovalCodesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Material instruction')])[1]")
    WebElement maintainMaterialInstruction;

    @FindBy(how = How.XPATH, using = "//maintain-material-instruction/descendant::h4[contains(., 'Material instruction')]")
    WebElement maintainMaterialInstructionElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Pallet racks')])[2]")
    WebElement maintainPalletRacks;

    @FindBy(how = How.XPATH, using = "//maintain-pallet-racks/descendant::h4[contains(., 'Pallet racks')]")
    WebElement maintainPalletRacksElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Protocol demand')])[1]")
    WebElement maintainProtocolDemand;

    @FindBy(how = How.XPATH, using = "//maintain-protocol-demand/descendant::h4[contains(., 'Protocol demand')]")
    WebElement maintainProtocolDemandElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Rejection code')])")
    WebElement maintainRejectionCode;

    @FindBy(how = How.XPATH, using = "//maintain-rejection-codes/descendant::h4[contains(., 'Rejection code')]")
    WebElement maintainRejectionCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Time limits')])[1]")
    WebElement maintainTimeLimits;

    @FindBy(how = How.XPATH, using = "//time-limits-details/descendant::h4[contains(., 'Time limits')]")
    WebElement maintainTimeLimitsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Permission')])")
    WebElement maintainPermission;

    @FindBy(how = How.XPATH, using = "//tma-site-permissions-details/descendant::h4[contains(., 'Permission')]")
    WebElement maintainPermissionElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Outbound carrier')])[1]")
    WebElement maintainOutBoundCarrier;

    @FindBy(how = How.XPATH, using = "//maintain-outbound-carrier/descendant::h4[contains(., 'Outbound carrier')]")
    WebElement maintainOutBoundCarrierElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Function area')])")
    WebElement maintainFunctionArea;

    @FindBy(how = How.XPATH, using = "//maintain-function-areas/descendant::h4[contains(., 'Function area')]")
    WebElement maintainFunctionAreaElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Material requesters')])")
    WebElement maintainMaterialRequesters;

    @FindBy(how = How.XPATH, using = "//maintain-material-requesters/descendant::h4[contains(., 'Material requesters')]")
    WebElement maintainMaterialRequestersElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Other storage')])")
    WebElement maintainOtherStorageLoc;

    @FindBy(how = How.XPATH, using = "//maintain-other-storage-locations/descendant::h4[contains(., 'Other storage')]")
    WebElement maintainOtherStorageLocElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Receiving problem')])")
    WebElement maintainReceivingPrb;

    @FindBy(how = How.XPATH, using = "//maintain-receiving-problems/descendant::h4[contains(., 'Receiving problem')]")
    WebElement maintainReceivingPrbElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Third party')])[1]")
    WebElement maintainThirdPartyAdd;

    @FindBy(how = How.XPATH, using = "//maintain-third-party-addresses/descendant::h4[contains(., 'Third party')]")
    WebElement maintainThirdPartyAddElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'TMA sites')])[1]")
    WebElement maintainTMASites;

    @FindBy(how = How.XPATH, using = "//maintain-tma-sites/descendant::h4[contains(., 'TMA sites')]")
    WebElement maintainTMASitesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Inbound carrier')])")
    WebElement maintainInboundCarrier;

    @FindBy(how = How.XPATH, using = "//maintain-inbound-carrier/descendant::h4[contains(., 'Inbound carrier')]")
    WebElement maintainInboundCarrierElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Select fields for')])[1]")
    WebElement maintainSelectFieldForMR;

    @FindBy(how = How.XPATH, using = "//mtrl-request-select-fields-details/descendant::h4[contains(., 'Select fields for')]")
    WebElement maintainSelectFieldForMRElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Pallet racks')])[1]")
    WebElement tmaPalletRack;

    @FindBy(how = How.XPATH, using = "//material-storage/descendant::h4[contains(., 'Storage')]")
    WebElement tmaPalletRackElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search')])[2]")
    WebElement tmaSearchTMA;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Search')])[1]")
    WebElement tmaSearch;

    @FindBy(how = How.XPATH, using = "//material-storage/descendant::h4[contains(., 'Storage')]")
    WebElement tmaSearchTMAElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Transaction log')])[1]")
    WebElement tmaTransactionLog;

    @FindBy(how = How.XPATH, using = "//storage-transaction-log/descendant::h4[contains(., 'Storage transaction log')]")
    WebElement tmaTransactionLogElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Pallets for shipping')])[1]")
    WebElement tmaPalletsForShipping;

    @FindBy(how = How.XPATH, using = "//pallet-shipping/descendant::h4[contains(., 'Pallets for shipping')]")
    WebElement tmaPalletsForShippingElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Shipping history')])[1]")
    WebElement tmaShippingHistory;

    @FindBy(how = How.XPATH, using = "//shipping-history/descendant::h4[contains(., 'Shipping history')]")
    WebElement tmaShippingHistoryElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List material request')])[1]")
    WebElement tmaListMaterialReq;

    @FindBy(how = How.XPATH, using = "//list-material-requests/descendant::h4[contains(., 'Material request')]")
    WebElement tmaListMaterialReqElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Analysis summary')])[1]")
    WebElement tmaAnalysisSummary;

    @FindBy(how = How.XPATH, using = "//material-analysis-summary/descendant::h4[contains(., 'Analysis summary')]")
    WebElement tmaAnalysisSummaryElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'By lead time')])[1]")
    WebElement campaignByLeadTime;

    @FindBy(how = How.XPATH, using = "//lead-time-alert/descendant::h4[contains(., 'Alert by lead time')]")
    WebElement campaignByLeadTimeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'By lead time')])[2]")
    WebElement campaignByLeadTime2;

    @FindBy(how = How.XPATH, using = "//lead-time/descendant::h4[contains(., 'Follow up by lead time')]")
    WebElement campaignByLeadTime2Element;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'By current status')])[1]")
    WebElement campaignByCurrentStatus;

    @FindBy(how = How.XPATH, using = "//current-status/descendant::h4[contains(., 'Follow up by current status')]")
    WebElement campaignByCurrentStatusElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'By reason')])[1]")
    WebElement campaignByReason;

    @FindBy(how = How.XPATH, using = "//follow-up-reason/descendant::h4[contains(., 'Follow up by reason')]")
    WebElement campaignByReasonElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'By function group')])[1]")
    WebElement campaignByFunctionGroup;

    @FindBy(how = How.XPATH, using = "//follow-up-function-group/descendant::h4[contains(., 'Follow up by function group')]")
    WebElement campaignByFunctionGroupElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New stairs')])")
    WebElement maintainNewStairs;

    @FindBy(how = How.XPATH, using = "//manage-stairs/descendant::h4[contains(., 'Stairs')]")
    WebElement maintainNewStairsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New hold for crediting')])")
    WebElement maintainNewHoldForCrediting;

    @FindBy(how = How.XPATH, using = "//hold-for-crediting-details/descendant::h4[contains(., 'New hold for crediting')]")
    WebElement maintainNewHoldForCreditingElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Select fields')])")
    WebElement maintainSelectFields;

    @FindBy(how = How.XPATH, using = "//hold-for-crediting-select-fields/descendant::h4[contains(., 'Select fields')]")
    WebElement maintainSelectFieldsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New auto exception')])")
    WebElement maintainNewAutoException;

    @FindBy(how = How.XPATH, using = "//auto-exception-details/descendant::h4[contains(., 'New auto exception')]")
    WebElement maintainNewAutoExceptionElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Select fields')])[2]")
    WebElement maintainSelectFieldsAutoException;

    @FindBy(how = How.XPATH, using = "//auto-exception-select-fields/descendant::h4[contains(., 'Select fields for auto exception')]")
    WebElement maintainSelectFieldsAutoExceptionElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New my not evaluated')])[1]")
    WebElement maintainNewMyNotEvaluated;

    @FindBy(how = How.XPATH, using = "//my-not-evaluated-details/descendant::h4[contains(., 'New my not evaluated')]")
    WebElement maintainNewMyNotEvaluatedElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Region')])")
    WebElement maintainRegion;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Group')])")
    WebElement maintainGroup;

    @FindBy(how = How.XPATH, using = "//maintain-region/descendant::h4[contains(., 'Region')]")
    WebElement maintainRegionElement;

    @FindBy(how = How.XPATH, using = "//maintain-region/descendant::h4[contains(., 'Group')]")
    WebElement maintainGroupElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Supplier contribution')])")
    WebElement maintainSupplierContribution;

    @FindBy(how = How.XPATH, using = "//supplier-contribution-details/descendant::h4[contains(., 'Supplier contribution')]")
    WebElement maintainSupplierContributionElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Original documents')])")
    WebElement maintainOriginalDocuments;

    @FindBy(how = How.XPATH, using = "//maintain-original-document/descendant::h4[contains(., 'Original documents')]")
    WebElement maintainOriginalDocumentsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Campaign board assignees')])")
    WebElement maintainCampaignBoardAssignees;

    @FindBy(how = How.XPATH, using = "//maintain-cmp-board-assignee/descendant::h4[contains(., 'Campaign board assignees')]")
    WebElement maintainCampaignBoardAssigneesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Translation languages')])")
    WebElement maintainTranslationLanguages;

    @FindBy(how = How.XPATH, using = "//maintain-translation-lang/descendant::h4[contains(., 'Translation languages')]")
    WebElement maintainTranslationLanguagesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Importer campaign administrative info')])")
    WebElement maintainImporterCampaignAdministrative;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty area campaign administrative info')])")
    WebElement maintainWACampaignAdministrative;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty district campaign administrative info')])[1]")
    WebElement maintainWarrantyCampaignAdministrative;

    @FindBy(how = How.XPATH, using = "//maintain-importer-campaign/descendant::h4[contains(., 'Importer campaign administrative info')]")
    WebElement maintainImporterCampaignAdministrativeElement;

    @FindBy(how = How.XPATH, using = "//maintain-importer-campaign/descendant::h4[contains(., 'Warranty area campaign administrative info')]")
    WebElement maintainWACampaignAdministrativeElement;

    @FindBy(how = How.XPATH, using = "//maintain-importer-campaign/descendant::h4[contains(., 'Warranty district campaign administrative info')]")
    WebElement maintainWarrantyCampaignAdministrativeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'SCC types')])")
    WebElement maintainSCCTypes;

    @FindBy(how = How.XPATH, using = "//maintain-scc-types/descendant::h4[contains(., 'SCC types')]")
    WebElement maintainSCCTypesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Campaign conditions')])")
    WebElement maintainCampaignConditions;

    @FindBy(how = How.XPATH, using = "//maintain-campaign-condition/descendant::h4[contains(., 'Campaign conditions')]")
    WebElement maintainCampaignConditionsElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Material order parameters')])")
    WebElement maintainMaterialOrderParameters;

    @FindBy(how = How.XPATH, using = "//maintain-mtrl-order-param/descendant::h4[contains(., 'Material order parameters')]")
    WebElement maintainmaintainMaterialOrderParametersElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Lead time warning limits')])")
    WebElement maintainLeadTimeWarning;

    @FindBy(how = How.XPATH, using = "//maintain-lead-time-limit/descendant::h4[contains(., 'Lead time warning limits')]")
    WebElement maintainLeadTimeWarningElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Reason codes')])")
    WebElement maintainReasonCodes;

    @FindBy(how = How.XPATH, using = "//maintain-defect-code/descendant::h4[contains(., 'Reason codes')]")
    WebElement maintainReasonCodesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Importer')])[2]")
    WebElement maintainImporter;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty area')])[2]")
    WebElement maintainWarrantyArea;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Warranty district')])[2]")
    WebElement maintainWarrantyDistrict;

    @FindBy(how = How.XPATH, using = "//list-importers/descendant::h4[contains(., 'Importer')]")
    WebElement maintainImporter2Element;

    @FindBy(how = How.XPATH, using = "//list-importers/descendant::h4[contains(., 'Warranty area')]")
    WebElement maintainWarrantyArea2Element;

    @FindBy(how = How.XPATH, using = "//list-importers/descendant::h4[contains(., 'Warranty district')]")
    WebElement maintainWarrantyDistrict2Element;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'Document grouping')])[1]")
    WebElement maintainDocumentGrouping;

    @FindBy(how = How.XPATH, using = "//maintain-document-groupings/descendant::h4[contains(., 'Document grouping')]")
    WebElement maintainDocumentGroupingElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New campaign')])[1]")
    WebElement campaignNewCampaign;

    @FindBy(how = How.XPATH, using = "//manage-campaign/descendant::h4[contains(., 'Campaign')]")
    WebElement campaignNewCampaignElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'List campaign')])[1]")
    WebElement campaignListCampaign;

    @FindBy(how = How.XPATH, using = "//list-campaign/descendant::h4[contains(., 'List campaign')]")
    WebElement campaignListCampaign2Element;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New function group range')])[1]")
    WebElement coverageNewFunctionGroupRange;

    @FindBy(how = How.XPATH, using = "//function-group-range-details/descendant::h4[contains(., 'New function group range')]")
    WebElement coverageNewFuncGrpRangesElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New defect code range')])[1]")
    WebElement coverageNewDefectCodeRange;

    @FindBy(how = How.XPATH, using = "//defect-code-range-details/descendant::h4[contains(., 'New defect code')]")
    WebElement coverageNewDefectCodeElement;

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'New operation no. range')])[1]")
    WebElement coverageNewOperationNoRange;

    @FindBy(how = How.XPATH, using = "//operation-no-range-details/descendant::h4[contains(., 'New operation no')]")
    WebElement coverageNewOperationNoElement;

    public MenuSubMenuVerification(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Description : Verifies Claim,Vehicle,Crediting menu and sub menus
     *
     * #Input Parameters: excel data
     ******************************************/
    public void verifyrMenuSubmenu_Dealer(HashMap<String, Object> data) {
        try {
            verifyCreditingMenu_Dealer(data);
            verifyVehicleMenu(data);
            verifyClaimMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
        }
    }

    /*********************************
     * #Description : Verifies tabs and titles for claim handler central
     **********************************/
    public void verifyMenuSubmenu_Central(HashMap<String, Object> data) {
        try {
            verifyCreditingMenu(data);
            verifyVehicleMenu(data);
            verifyClaimMenu(data);
            checkAndRedirectToSubMenus(inspectorReportMainMenuElement, inspectorReportSearchReportElement, inspectorReportSearchReportHeaderElement,
                                       appBasedUtils.getStringData(data, "Inspector report-Search report"), "Inspector report", softAssert);
            verifyMaintainMenu(data);
            verifyValidationMenu(data);
            verifyCoverageMenu(data);
            verifyCampaignMenu(data);
            verifyTMAMenu_Central(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
        }
    }

    /**********************************
     * #Description : Verifies tabs and titles for system admin user
     ***********************************/
    public void verifyMenuSubmenu_SystemAdministrator(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            verifyTMAMenu(data);
            verifyVehicleMenu(data);
            verifyCampaignMenu(data);
            verifyCoverageMenu(data);
            verifyValidationMenu(data);
            checkAndRedirectToSubMenus(inspectorReportMainMenuElement, inspectorReportSearchReportElement, inspectorReportSearchReportHeaderElement,
                                       appBasedUtils.getStringData(data, "Inspector report-Search report"), "Inspector report", softAssert);
            verifyCreditingMenu(data);
            verifyMaintainMenu(data);
            verifySystemAdminMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /*************************************
     * #Description :Verifies tabs and titles for CampaignAdminCentral
     *************************************/
    public void verifyMenuSubmenu_CampaignAdminCentral(HashMap<String, Object> data) {
        try {
            verifyVehicleMenu(data);
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            verifyCoverageMenu(data);
            verifyMaintainMenu(data);
            verifyCampaignMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
        }
    }

    /***************************************
     * #Description : Verifies tabs and titles for ClaimAdminCentral
     **************************************/
    public void verifyMenuSubmenu_ClaimAdminCentral(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignSearchCampaign, campaignSearchCampaignElement,
                                             appBasedUtils.getStringData(data, "Campaign-SearchCampaign"), "List campaigns", softAssert);
            verifyVehicleMenu(data);
            verifyValidationMenu(data);
            verifyCampaignMenu(data);
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            checkAndRedirectToSubMenus(inspectorReportMainMenuElement, inspectorReportSearchReportElement, inspectorReportSearchReportHeaderElement,
                                       appBasedUtils.getStringData(data, "Inspector report-Search report"), "Inspector report", softAssert);
            verifyMaintainMenu(data);
            verifyCreditingMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /***************************************
     * #Description : Verifies tabs and titles for CoverageAdmin
     ****************************************/
    public void verifyMenuSubmenu_CoverageAdmin(HashMap<String, Object> data) {
        try {
            verifyVehicleMenu(data);
            verifyValidationMenu(data);
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            verifyCoverageMenu(data);
            verifyMaintainMenu(data);
            verifyCampaignMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /************************************
     * #Description : Verifies tabs and titles for CreditingAdmCentral
     *************************************/
    public void verifyMenuSubmenu_CreditingAdmCentral(HashMap<String, Object> data) {
        try {
            verifyCreditingMenu(data);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignSearchCampaign, campaignSearchCampaignElement,
                                             appBasedUtils.getStringData(data, "Campaign-SearchCampaign"), "List campaigns", softAssert);
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            verifyMaintainMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /***********************************
     * #Description : Verifies tabs and titles for TMAAdministrator
     ************************************/
    public void verifyMenuSubmenu_TMAAdministrator(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            verifyTMAMenu(data);
            verifyVehicleMenu(data);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignSearchCampaign, campaignSearchCampaignElement,
                                             appBasedUtils.getStringData(data, "Campaign-SearchCampaign"), "List campaigns", softAssert);
            verifyCoverageMenu(data);
            checkAndRedirectToSubMenus(inspectorReportMainMenuElement, inspectorReportSearchReportElement, inspectorReportSearchReportHeaderElement,
                                       appBasedUtils.getStringData(data, "Inspector report-Search report"), "Inspector report", softAssert);
            verifyMaintainMenu(data);
            softAssert.assertAll();
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /**********************************
     * #Description : Verifies tabs and titles for Inspector
     ***********************************/
    public void verifyMenuSubmenu_Inspector(HashMap<String, Object> data) {
        try {
            verifyVehicleMenu(data);
            verifyCoverageMenu(data);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignSearchCampaign, campaignSearchCampaignElement,
                                             appBasedUtils.getStringData(data, "Campaign-SearchCampaign"), "List campaigns", softAssert);
            checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                       appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
            verifyInspectorReportMenu(data);
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /**********************************
     * #Description : Verifies submenu items of Crediting menu
     ***********************************/
    public void verifyCreditingMenu_Dealer(HashMap<String, Object> data) {
        checkAndRedirectToSubMenus(creditingMainMenuElement, creditingPrintCreditDocumentElement, creditingPrintCreditDocumenHeadertElement,
                                   appBasedUtils.getStringData(data, "Crediting-Print credit document"), "Print credit documents", softAssert);
        checkAndRedirectToSubMenus(creditingMainMenuElement, creditingCreateOutStandingClaimSummaryElement, creditingCreateOutStandingClaimSummHeadertElement,
                                   appBasedUtils.getStringData(data, "Crediting-Create outstanding claim summary"), "Create outstanding claim summary (Dealer)",
                                   softAssert);
        checkAndRedirectToSubMenus(creditingMainMenuElement, creditingPrintOutStandingClaimSummaryElement, creditingPrintOutStandingClaimSummHeadertElement,
                                   appBasedUtils.getStringData(data, "Crediting-Print outstanding claim summary"), "Print outstanding claim summary (Dealer)",
                                   softAssert);
    }

    /*********************************
     * #Description : Verifies submenu items of Crediting menu
     **********************************/
    public void verifyCreditingMenu(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSubMenus(creditingMainMenuElement, creditingPrintCreditDocumentElement, creditingPrintCreditDocumenHeadertElement,
                                       appBasedUtils.getStringData(data, "Crediting-Print credit document"), "Print credit documents", softAssert);
            checkAndRedirectToSubMenus(creditingMainMenuElement, creditingCreateOutStandingClaimSummaryCompanyElement,
                                       creditingCreateOutStandingClaimSummCompanyHeadertElement,
                                       appBasedUtils.getStringData(data, "Crediting-Create outstanding claim summary company"),
                                       "Create outstanding claim summary (Company)", softAssert);
            checkAndRedirectToSubMenus(creditingMainMenuElement, creditingPrintOutStandingClaimSummaryElement,
                                       creditingPrintOutStandingClaimSummCompanyHeadertElement,
                                       appBasedUtils.getStringData(data, "Crediting-Print outstanding claim summary company"),
                                       "Print outstanding claim summary (Company)", softAssert);
            checkAndRedirectToSubMenus(creditingMainMenuElement, creditingViewScheduledCreditRun, creditingViewScheduledCreditRunsHeaderElement,
                                       appBasedUtils.getStringData(data, "Crediting-View scheduled credit runs"), "View scheduled credit runs", softAssert);
            checkAndRedirectToSubMenus(creditingMainMenuElement, creditingCreateModifyScheduling, creditingCreateModifySchedulingElement,
                                       appBasedUtils.getStringData(data, "Crediting-CreateModifyScheduling"), "Create/modify scheduling", softAssert);
            checkAndRedirectToSubMenus(creditingMainMenuElement, creditingApproveCreditRuns, creditingApproveCreditRunsElement,
                                       appBasedUtils.getStringData(data, "Crediting-ApproveCreditRuns"), "Approve credit runs", softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            keywords.APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            softAssert.assertTrue(false, "Menu and Submenu n");
        }
    }

    /*********************************
     * #Description : Verifies submenu items of Claim menu
     **********************************/
    public void verifyClaimMenu(HashMap<String, Object> data) {
        checkAndRedirectToSubMenus(claimMainMenuElement, claimNewClaimJobElement, claimNewClaimJobHeaderElement,
                                   appBasedUtils.getStringData(data, "Claim-New Claim Job"), "New claim job", softAssert);
        checkAndRedirectToSubMenus(claimMainMenuElement, claimAdvancedSearchElement, claimAdvancedSearchHeaderElement,
                                   appBasedUtils.getStringData(data, "Claim-Advanced Search"), "Advanced search", softAssert);
        checkAndRedirectToSubMenus(claimMainMenuElement, claimListClaimJobElement, claimListClaimJobHeaderElement,
                                   appBasedUtils.getStringData(data, "Claim- List claim Job"), "List claim jobs", softAssert);
        if (appBasedUtils.getStringData(data, "Claim- Update several claim jobs -Search claim job").equalsIgnoreCase("Yes")) {
            checkAndRedirectToSecondSubMenus(claimMainMenuElement, claimUpdateSeveralClaimJobElement, claimUpdateSeveralClaimJobSearchClaimJobsSubElement,
                                             claimUpdateSeveralClaimJobsSearchClaimJobsHeaderElement,
                                             appBasedUtils.getStringData(data, "Claim- Update several claim jobs -Search claim job"), "Search claim jobs",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(claimMainMenuElement, claimUpdateSeveralClaimJobElement, claimUpdateSeveralClaimJobListOrderSubElement,
                                             claimUpdateSeveralClaimJobsListOrderHeaderElement,
                                             appBasedUtils.getStringData(data, "Claim-Update several claim job-List order"), "List order", softAssert);
        }
        softAssert.assertAll();
    }

    /*********************************
     * #Description : Verifies submenu items of inspector menu
     **********************************/
    public void verifyInspectorReportMenu(HashMap<String, Object> data) {
        checkAndRedirectToSubMenus(inspectorReportMainMenuElement, inspectorReportNewReportElement, inspectorReportNewReportHeaderElement,
                                   appBasedUtils.getStringData(data, "Inspector report-New report"), "New inspector report", softAssert);
        checkAndRedirectToSubMenus(inspectorReportMainMenuElement, inspectorReportSearchReportElement, inspectorReportSearchReportHeaderElement,
                                   appBasedUtils.getStringData(data, "Inspector report-Search report"), "Inspector report", softAssert);
        softAssert.assertAll();
    }

    /*********************************
     * #Description : Verifies submenu items of Vehicle menu
     **********************************/
    public void verifyVehicleMenu(HashMap<String, Object> data) {
        try {
            if (companyNameElement.getText().equals(VOLVO_TRUCK_CORPORATION) || companyNameElement.getText().equals(VOLVO_BUS_CORPORATION)
                    || companyNameElement.getText().equals(TSM_AMERICAS) || companyNameElement.getText().equals(UD_TRUCKS_CORPORATION)) {
                String externalsytemVDAURL = appBasedUtils.getStringData(data, "ExternalSystemURL");
                checkAndRedirectToSubMenus(vehicleMainMenuElement, vehicleViewRepairHistorySubMenuElement, vehicleViewRepairHistoryHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View repair history"), "View repair history", softAssert);
                checkAndRedirectToSubMenus(vehicleMainMenuElement, vehicleViewCoverageSubMenuElement, vehicleViewCoverageHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View coverage"), "View warranty coverage", softAssert);
                validateExternalsystemURL(vehicleMainMenuElement, vehicleViewCampaignSubMenuElement, appBasedUtils.getStringData(data, "Vehicle-View campaign"),
                                          externalsytemVDAURL, softAssert);
                validateExternalsystemURL(vehicleMainMenuElement, vehicleViewTechnicalDataSubMenuElement,
                                          appBasedUtils.getStringData(data, "Vehicle-View technical data"), externalsytemVDAURL, softAssert);
            } else if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                String externalsytemURLVCE = appBasedUtils.getStringData(data, "ExternalSystemURL");
                checkAndRedirectToSubMenus(machineMainMenuElement, vehicleViewCampaignSubMenuElement, vehicleViewCampaignHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View campaign"), "View campaign", softAssert);
                validateExternalsystemURL(machineMainMenuElement, vehicleViewTechnicalDataSubMenuElement,
                                          appBasedUtils.getStringData(data, "Vehicle-View technical data"), externalsytemURLVCE, softAssert);
                checkAndRedirectToSubMenus(machineMainMenuElement, vehicleViewRepairHistorySubMenuElement, vehicleViewRepairHistoryHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View repair history"), "View repair history", softAssert);
                checkAndRedirectToSubMenus(machineMainMenuElement, vehicleViewCoverageSubMenuElement, vehicleViewCoverageHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View coverage"), "View warranty coverage", softAssert);
            }
            if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                String externalsytemURLVPC = appBasedUtils.getStringData(data, "ExternalSystemURL");
                validateExternalsystemURL(drivelineMainMenuElement, vehicleViewCampaignSubMenuElement,
                                          appBasedUtils.getStringData(data, "Vehicle-View campaign"), externalsytemURLVPC, softAssert);
                validateExternalsystemURL(drivelineMainMenuElement, vehicleViewTechnicalDataSubMenuElement,
                                          appBasedUtils.getStringData(data, "Vehicle-View technical data"), externalsytemURLVPC, softAssert);
                checkAndRedirectToSubMenus(drivelineMainMenuElement, vehicleViewRepairHistorySubMenuElement, vehicleViewRepairHistoryHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View repair history"), "View repair history", softAssert);
                checkAndRedirectToSubMenus(drivelineMainMenuElement, vehicleViewCoverageSubMenuElement, vehicleViewCoverageHeaderElement,
                                           appBasedUtils.getStringData(data, "Vehicle-View coverage"), "View warranty coverage", softAssert);
            }
            if (appBasedUtils.getStringData(data, "Vehicle-View vehicle cost history").equalsIgnoreCase("Yes")) {
                if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                    checkAndRedirectToSubMenus(machineMainMenuElement, vehicleViewCostHistorySubMenuElement, vehicleViewCostHistoryHeaderElement,
                                               appBasedUtils.getStringData(data, "Vehicle-View vehicle cost history"), "View machine cost history", softAssert);
                } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                    checkAndRedirectToSubMenus(drivelineMainMenuElement, vehicleViewCostHistorySubMenuElement, vehicleViewCostHistoryHeaderElement,
                                               appBasedUtils.getStringData(data, "Vehicle-View vehicle cost history"), "View driveline cost history",
                                               softAssert);
                } else {
                    checkAndRedirectToSubMenus(vehicleMainMenuElement, vehicleViewCostHistorySubMenuElement, vehicleViewCostHistoryHeaderElement,
                                               appBasedUtils.getStringData(data, "Vehicle-View vehicle cost history"), "View vehicle cost history", softAssert);
                }
            }
            if (appBasedUtils.getStringData(data, "Vehicle-View contract information").equalsIgnoreCase("Yes")) {
                if (companyNameElement.getText().equals(VOLVO_TRUCK_CORPORATION) || companyNameElement.getText().equals(VOLVO_BUS_CORPORATION)) {
                    checkAndRedirectToSubMenus(vehicleMainMenuElement, vehicleViewContractInfoSubMenuElement, vehicleViewContractInfoHeaderElement,
                                               appBasedUtils.getStringData(data, "Vehicle-View contract information"), "View contract information", softAssert);
                }
            }
            if (appBasedUtils.getStringData(data, "Vehicle-View contract coverage").equalsIgnoreCase("Yes")) {
                if (companyNameElement.getText().equals(VOLVO_TRUCK_CORPORATION) || companyNameElement.getText().equals(VOLVO_BUS_CORPORATION)) {
                    checkAndRedirectToSubMenus(vehicleMainMenuElement, vehicleViewContractCoverageSubMenuElement, vehicleViewContractCoverageHeaderElement,
                                               appBasedUtils.getStringData(data, "Vehicle-View contract coverage"), "View contract coverage", softAssert);
                }
            }
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /************************************
     * #Description : Verifies submenu items of maintain menu
     *************************************/
    public void verifyMaintainMenu(HashMap<String, Object> data) {
        try {
            if (companyNameElement.getText().equals(VOLVO_TRUCK_CORPORATION) || companyNameElement.getText().equals(VOLVO_BUS_CORPORATION)) {
                if (appBasedUtils.getStringData(data, "Maintain-ClaimTables-Volvo prefix").equalsIgnoreCase("Yes")) {
                    checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainClaimTablesVolvoPrefixElement,
                                                     maintainClaimTablesVolvoPrefixHeaderElement,
                                                     appBasedUtils.getStringData(data, "Maintain-ClaimTables-Volvo prefix"), "Maintain volvo prefix",
                                                     softAssert);
                }
            }
            if (appBasedUtils.getStringData(data, "Maintain-ClaimTables-Rollout").equalsIgnoreCase("Yes")) {
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainContractElement, maintainClaimTablesRollOutElement,
                                                 maintainClaimTablesRolloutHeaderElement, appBasedUtils.getStringData(data, "Maintain-ClaimTables-Rollout"),
                                                 "Maintain contract rollout", softAssert);
            }
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, stairs, listStairs, maintainListStairs,
                                             appBasedUtils.getStringData(data, "Maintain-Stairs-ListStairs"), "List stairs", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, stairs, connections, maintainConnections,
                                             appBasedUtils.getStringData(data, "Maintain-Stairs-Connections"), "Connections", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, stairs, maintainNewStairs, maintainNewStairsElement,
                                             appBasedUtils.getStringData(data, "Maintain-NewStairs"), "Stairs", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, holdForCrediting, listHoldForCrediting, maintainListHoldForCrediting,
                                             appBasedUtils.getStringData(data, "Maintain-HoldForCrediting-ListHoldForCrediting"), "List hold for crediting",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, holdForCrediting, maintainNewHoldForCrediting, maintainNewHoldForCreditingElement,
                                             appBasedUtils.getStringData(data, "Maintain-NewHoldForCrediting"), "New hold for crediting", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, holdForCrediting, maintainSelectFields, maintainSelectFieldsElement,
                                             appBasedUtils.getStringData(data, "Maintain-SelectFields"), "Select fields", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, autoForException, listAutoException, maintainAutoException,
                                             appBasedUtils.getStringData(data, "Maintain-AutoException-ListAutoException"), "List auto exceptions", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, autoForException, maintainNewAutoException, maintainNewAutoExceptionElement,
                                             appBasedUtils.getStringData(data, "Maintain-NewAutoException"), "New auto exception", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, autoForException, maintainSelectFieldsAutoException,
                                             maintainSelectFieldsAutoExceptionElement, appBasedUtils.getStringData(data, "Maintain-SelectFieldsAutoException"),
                                             "Select fields for auto exception", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainAuthorizedCurrency,
                                             maintainClaimSetUpAuthorizedCurrencyElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-AuthorizedCurrency"), "Maintain authorized currency",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainClaimJobRef, maintainClaimSetUpClaimJobRefElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-ClaimJobRef"), "Maintain claim job ref no. series",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainCurrencyRates, maintainCurrencyRateElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-CurrencyRates"), "Maintain currency rates", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainDefectCode, maintainDefectCodeElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-DefectCode"), "Maintain defect code", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainFunctionGroup, maintainFunctionGroupElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-FunctionGroup"), "Maintain function group", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainReturnCode, maintainReturnCodeElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-ReturnCode"), "Maintain return code", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainCAFSCCGroup, maintainCAFSCCElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-CAFSCCGroup"), "Maintain CAF SCC group", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainClaimSystemParameter, maintainSystemParamElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-ClaimSystemParameter"), "Claim system parameter",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainCaseSource, maintainCaseSourceElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-CaseSource"), "Maintain case source", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainCountryLanguage, maintainCountryLanguageElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-CountryLanguage"), "Maintain country language", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainDebitCode, maintainDebitCodeElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-DebitCode"), "Maintain debit code", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainFollowUpCode, maintainFollowUpCodeElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-FollowUpCode"), "Maintain follow up code", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainRejectCode, maintainRejectCodeElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-RejectCode"), "Maintain reject code", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainCostAdjustmentFactor,
                                             maintainCostAdjustmentFactorElement, appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-CostAdjustmentFactor"),
                                             "Maintain cost adjustment factor", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainClaimSetUpElement, maintainMaterialGroups, maintainMaterialGroupsElement,
                                             appBasedUtils.getStringData(data, "Maintain-ClaimSetUp-MaterialGroups"), "Maintain material group", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainApprovalCodes, maintainApprovalCodesElement,
                                             appBasedUtils.getStringData(data, "Maintain-TMASetUp-ApprovalCodes"), "Approval codes", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainMaterialInstruction, maintainMaterialInstructionElement,
                                             appBasedUtils.getStringData(data, "Maintain-TMASetUp-MaterialInstruction"), "Material instruction", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainPalletRacks, maintainPalletRacksElement,
                                             appBasedUtils.getStringData(data, "Maintain-TMASetUp-PalletRacks"), "Pallet racks", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainProtocolDemand, maintainProtocolDemandElement,
                                             appBasedUtils.getStringData(data, "Maintain-TMASetUp-ProtocalDemand"), "Protocol demand", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainRejectionCode, maintainRejectionCodeElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-RejectionCode"), "Rejection codes", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainTimeLimits, maintainTimeLimitsElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-TimeLimits"), "Time limits", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainPermission, maintainPermissionElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-Permission"), "Permission - other TMA sites", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainOutBoundCarrier, maintainOutBoundCarrierElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-OutBoundCarrier"), "Outbound carrier", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainFunctionArea, maintainFunctionAreaElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-FunctionArea"), "Function areas", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainMaterialRequesters, maintainMaterialRequestersElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-MaterialRequesters"), "Material requesters", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainOtherStorageLoc, maintainOtherStorageLocElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-OtherStorageLoc"), "Other storage locations", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainReceivingPrb, maintainReceivingPrbElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-ReceivingPrb"), "Receiving problems", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainThirdPartyAdd, maintainThirdPartyAddElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-ThirdPartyAdd"), "Third party addresses", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainTMASites, maintainTMASitesElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-TMASites"), "TMA sites", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainInboundCarrier, maintainInboundCarrierElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-InboundCarrier"), "Inbound carrier", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainTMASetUpElement, maintainSelectFieldForMR, maintainSelectFieldForMRElement,
                                             appBasedUtils.getStringData(data, " Maintain-TMASetUp-SelectFieldForMR"), "Select fields for material request",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainMyNotEvaluated, maintainListMyNotEvaluated, maintainListMyNotEvaluatedElement,
                                             appBasedUtils.getStringData(data, "Maintain-MyNotEvaluate-ListMyNotEvaluate"), "List my not evaluated",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainMyNotEvaluated, maintainNewMyNotEvaluated, maintainNewMyNotEvaluatedElement,
                                             appBasedUtils.getStringData(data, "Maintain-NewMyNotEvaluated"), "New my not evaluated", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainSupplierContribution, maintainSupplierContributionElement,
                                             appBasedUtils.getStringData(data, "Maintain-SupplierContribution"), "Supplier contribution", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainOriginalDocuments, maintainOriginalDocumentsElement,
                                             appBasedUtils.getStringData(data, "Maintain-OriginalDocuments"), "Original documents", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainCampaignBoardAssignees,
                                             maintainCampaignBoardAssigneesElement, appBasedUtils.getStringData(data, "Maintain-CampaignBoardAssignees"),
                                             "Campaign board assignees", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainTranslationLanguages, maintainTranslationLanguagesElement,
                                             appBasedUtils.getStringData(data, "Maintain-TranslationLanguages"), "Translation languages", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainImporterCampaignAdministrative,
                                             maintainImporterCampaignAdministrativeElement,
                                             appBasedUtils.getStringData(data, "Maintain-ImporterCampaignAdministrative"),
                                             "Importer campaign administrative info", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainSCCTypes, maintainSCCTypesElement,
                                             appBasedUtils.getStringData(data, "Maintain-SCCTypes"), "SCC types", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainCampaignConditions, maintainCampaignConditionsElement,
                                             appBasedUtils.getStringData(data, "Maintain-CampaignConditions"), "Campaign conditions", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainMaterialOrderParameters,
                                             maintainmaintainMaterialOrderParametersElement,
                                             appBasedUtils.getStringData(data, "Maintain-Maintain-MaterialOrderParameters"), "Material order parameters",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainLeadTimeWarning, maintainLeadTimeWarningElement,
                                             appBasedUtils.getStringData(data, "Maintain-LeadTimeWarning"), "Lead time warning limits", softAssert);
            checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainReasonCodes, maintainReasonCodesElement,
                                             appBasedUtils.getStringData(data, "Maintain-ReasonCodes"), "Reason codes", softAssert);
            if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainWACampaignAdministrative,
                                                 maintainWACampaignAdministrativeElement,
                                                 appBasedUtils.getStringData(data, "Maintain-ImporterCampaignAdministrative"),
                                                 "Warranty area campaign administrative info", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainRegion, maintainRegionElement,
                                                 appBasedUtils.getStringData(data, "Maintain-Region"), "Region", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainWarrantyAreaElement, maintainWarrantyArea, maintainWarrantyArea2Element,
                                                 appBasedUtils.getStringData(data, "Maintain-Importer"), "Warranty area", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainWarrantyAreaElement, maintainDocumentGrouping,
                                                 maintainDocumentGroupingElement, appBasedUtils.getStringData(data, "Maintain-DocumentGrouping"),
                                                 "Document groupings", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainDealer2Element, maintainDealer3, maintainDealer3tElement,
                                                 appBasedUtils.getStringData(data, "Maintain-Dealer"), "Dealer", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainDealer2Element, maintainSelfBilling, maintainSelfBillingElement,
                                                 appBasedUtils.getStringData(data, "Maintain-SelfBilling"), "Self-billing invoice number series", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainCompany, maintainCompany2Element,
                                                 appBasedUtils.getStringData(data, "Maintain-Company"), "Company", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainVATAgent, maintainVATAgentElement,
                                                 appBasedUtils.getStringData(data, "Maintain-VATAgent"), "VAT agent", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainWarrantyAreaCompany,
                                                 maintainWarrantyAreaCompanyElement, appBasedUtils.getStringData(data, "Maintain-ImporterCompanyVAT"),
                                                 "Warranty area company VAT", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainEuropeanVAT, maintainEuropeanVATElement,
                                                 appBasedUtils.getStringData(data, "Maintain-EuropeanVAT"), "European VAT", softAssert);
                checkAndRedirectToSubMenus(maintainMainMenuElement, maintainVATElement, maintainListVATElement,
                                           appBasedUtils.getStringData(data, "Maintain-ListVAT"), "List VAT", softAssert);
                checkAndRedirectToSubMenus(maintainMainMenuElement, maintainDealerNoti, maintainDealerNotiElement,
                                           appBasedUtils.getStringData(data, "Maintain-DealerNotification"), "Maintain dealer notifications", softAssert);
            } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainWarrantyCampaignAdministrative,
                                                 maintainWarrantyCampaignAdministrativeElement,
                                                 appBasedUtils.getStringData(data, "Maintain-ImporterCampaignAdministrative"),
                                                 "Warranty district campaign administrative info", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainGroup, maintainGroupElement,
                                                 appBasedUtils.getStringData(data, "Maintain-Region"), "Group", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainWarrantyDistrictElement, maintainWarrantyDistrict,
                                                 maintainWarrantyDistrict2Element, appBasedUtils.getStringData(data, "Maintain-Importer"), "Warranty district",
                                                 softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainWarrantyDistrictElement, maintainImporterContractPriceSourceElement,
                                                 maintainImporterContractPriceSourceHeaderElement,
                                                 appBasedUtils.getStringData(data, "Maintain-ContractPriceSource"), "Contract price source", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainBusinessPartnerElement, maintainBusinessPartner,
                                                 maintainBusinessPartner2Element, appBasedUtils.getStringData(data, "Maintain-Dealer"), "Business partner",
                                                 softAssert);
                checkAndRedirectToSubMenus(maintainMainMenuElement, maintainBusinessPartnerNoti, maintainBusinessPartnerNotiElement,
                                           appBasedUtils.getStringData(data, "Maintain-DealerNotification"), "Maintain business partner notifications",
                                           softAssert);
            } else {
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainImporterCampaignAdministrative,
                                                 maintainImporterCampaignAdministrativeElement,
                                                 appBasedUtils.getStringData(data, "Maintain-ImporterCampaignAdministrative"),
                                                 "Importer campaign administrative info", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCampaignSetup, maintainRegion, maintainRegionElement,
                                                 appBasedUtils.getStringData(data, "Maintain-Region"), "Region", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainImporterElement, maintainImporter, maintainImporter2Element,
                                                 appBasedUtils.getStringData(data, "Maintain-Importer"), "Importer", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainImporterElement, maintainDocumentGrouping, maintainDocumentGroupingElement,
                                                 appBasedUtils.getStringData(data, "Maintain-DocumentGrouping"), "Document groupings", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainDealer2Element, maintainDealer3, maintainDealer3tElement,
                                                 appBasedUtils.getStringData(data, "Maintain-Dealer"), "Dealer", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainDealer2Element, maintainSelfBilling, maintainSelfBillingElement,
                                                 appBasedUtils.getStringData(data, "Maintain-SelfBilling"), "Self-billing invoice number series", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainCompany, maintainCompany2Element,
                                                 appBasedUtils.getStringData(data, "Maintain-Company"), "Company", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainVATAgent, maintainVATAgentElement,
                                                 appBasedUtils.getStringData(data, "Maintain-VATAgent"), "VAT agent", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainImporterCompany, maintainImporterCompanyElement,
                                                 appBasedUtils.getStringData(data, "Maintain-ImporterCompanyVAT"), "Importer company VAT", softAssert);
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainCompanyElement, maintainEuropeanVAT, maintainEuropeanVATElement,
                                                 appBasedUtils.getStringData(data, "Maintain-EuropeanVAT"), "European VAT", softAssert);
                checkAndRedirectToSubMenus(maintainMainMenuElement, maintainVATElement, maintainListVATElement,
                                           appBasedUtils.getStringData(data, "Maintain-ListVAT"), "List VAT", softAssert);
                checkAndRedirectToSubMenus(maintainMainMenuElement, maintainDealerNoti, maintainDealerNotiElement,
                                           appBasedUtils.getStringData(data, "Maintain-DealerNotification"), "Maintain dealer notifications", softAssert);
            }
            if (companyNameElement.getText().equals(VOLVO_TRUCK_CORPORATION) || companyNameElement.getText().equals(VOLVO_BUS_CORPORATION)) {
                checkAndRedirectToSecondSubMenus(maintainMainMenuElement, maintainImporterElement, maintainImporterContractPriceSourceElement,
                                                 maintainImporterContractPriceSourceHeaderElement,
                                                 appBasedUtils.getStringData(data, "Maintain-ContractPriceSource"), "Contract price source", softAssert);
            }
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies external system URL
     *************************************/
    public void validateExternalsystemURL(WebElement mainMenu, WebElement subMenu, String menuValidateFlag, String externalsytemVDAURL, SoftAssert softAssert) {
        try {
            if (menuValidateFlag.equals("Yes")) {
                closeLoginWindow();
                keywords.clickOnLink(homePageLink, driver);
                keywords.waitForVisibilityOfElement(driver, 15, mainMenu);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                WebDriverWait wait1 = new WebDriverWait(driver, 30);
                wait1.until(ExpectedConditions.visibilityOf(mainMenu));
                jse.executeScript("arguments[0].click();", mainMenu);
                keywords.waitForVisibilityOfElement(driver, 10, subMenu);
                String subMenuText = keywords.getTextValue(subMenu);
                jse.executeScript("arguments[0].click();", subMenu);
                APP_LOGS.info("Navigated to menu '" + subMenuText + "'");
                keywords.waitTime1(3);
                Set<String> windowids = driver.getWindowHandles();
                Object[] arrayWindowHandles = windowids.toArray();
                String mainWindowId = arrayWindowHandles[0].toString();
                String ChildWindowId = arrayWindowHandles[1].toString();
                driver.switchTo().window(ChildWindowId);
                keywords.waitForVisibilityOfElement(driver, 15, externalSystemLoginPageBodyText);
                String currentlBrowserurl = driver.getCurrentUrl();
                keywords.APP_LOGS.info("Expected page title: " + externalsytemVDAURL + "\t::\tActual page title: " + currentlBrowserurl);
                if (currentlBrowserurl.contains(externalsytemVDAURL)) {
                    keywords.APP_LOGS.info("Redirected to correct external system URL");
                    softAssert.assertTrue(currentlBrowserurl.contains(externalsytemVDAURL), "\"External system URL is not matched\"");
                }
                keywords.waitTime1(1);
                driver.close();
                driver.switchTo().window(mainWindowId);
            } else {
                keywords.APP_LOGS.info("Menu is not within User context");
                softAssert.fail("\"Menu is not within User context\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*****************************************
     * #Description : Verifies sub menus
     ******************************************/
    public void checkAndRedirectToSubMenus(WebElement mainMenu, WebElement subMenu, WebElement subMenuPageHeader, String menuValidateFlag,
            String expectedHeaderText, SoftAssert softAssert) {
        try {
            if (menuValidateFlag.equalsIgnoreCase("Yes")) {
                //keywords.waitForPageLoadSuccessfully(driver);
                keywords.waitForVisibilityOfElement(driver, 20, homePageLink);
                keywords.clickOnLink(homePageLink, driver);
                keywords.waitForVisibilityOfElement(driver, 30, mainMenu);
                keywords.performMenuAction(driver, mainMenu, subMenu, subMenuPageHeader);
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
                keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 10, subMenuPageHeader, errorMsg, validationMessageClose);
                if (keywords.elementIsDisplayed(subMenuPageHeader, "Header text of sub menu")) {
                    String actualHeaderText = subMenuPageHeader.getText();
                    keywords.APP_LOGS.info("Expected page title: " + expectedHeaderText + "\t::\tActual page title: " + actualHeaderText);
                    softAssert.assertEquals(expectedHeaderText, actualHeaderText, "\"Sub menu page header is not matched\"");
                } else {
                    keywords.APP_LOGS.info("Submenu header not displayed");
                    softAssert.fail("\"Header element not no displayed\"");
                }
            } else {
                keywords.APP_LOGS.info(expectedHeaderText + "- Menu is not within User context");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            softAssert.fail("Menu is not within User context");
        }
    }

    /********************************************
     * #Description : Verifies sub menu items
     **************************************/
    public void checkAndRedirectToSecondSubMenus(WebElement mainMenu, WebElement firstSubMenu, WebElement secondSubMenu, WebElement subMenuPageHeader,
            String menuValidateFlag, String expectedHeaderText, SoftAssert softAssert) {
        if (menuValidateFlag.equals("Yes")) {
            keywords.waitForPageLoadSuccessfully(driver);
            keywords.waitForVisibilityOfElement(driver, 20, homePageLink);
            keywords.clickOnLink(homePageLink, driver);
            keywords.waitForVisibilityOfElement(driver, 20, mainMenu);
            keywords.performSecondLevelMenuAction(driver, mainMenu, firstSubMenu, secondSubMenu, subMenuPageHeader);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 20, subMenuPageHeader);
            if (keywords.elementIsDisplayed(subMenuPageHeader, "Header text of sub menu")) {
                String actualHeaderText = subMenuPageHeader.getText();
                keywords.APP_LOGS.info("Expected page title: " + expectedHeaderText + "\t::\tActual page title: " + actualHeaderText);
                softAssert.assertEquals(expectedHeaderText, actualHeaderText, "\"Sub menu page header is matched\"");
            } else {
                keywords.APP_LOGS.info("Sub menu page is not displayed");
                softAssert.fail("\"Sub menu page is not displayed\"");
            }
        } else {
            keywords.APP_LOGS.info(expectedHeaderText + " -Menu is not within User context");
        }
    }

    /***************************************
     * #Description : Closes the external system window #Input Parameters:
     ****************************************/
    public void closeLoginWindow() {
        try {
            Set<String> windowids = driver.getWindowHandles();
            if (windowids.size() > 1) {
                Object[] arrayWindowHandles = windowids.toArray();
                String mainWindowId = arrayWindowHandles[0].toString();
                String ChildWindowId = arrayWindowHandles[1].toString();
                driver.switchTo().window(ChildWindowId);
                if (keywords.elementIsDisplayed(childWindowXpath, "Child window element")) {
                    driver.close();
                    driver.switchTo().window(mainWindowId);
                }
                driver.switchTo().window(mainWindowId);
                if (keywords.elementIsDisplayed(childWindowXpath, "Child window elements")) {
                    driver.close();
                    driver.switchTo().window(ChildWindowId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies submenu items of Validation menu
     *************************************/
    public void verifyValidationMenu(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSubMenus(validationMainMenuElement, validationListCheckScenarios, validationCheckScenariosElement,
                                       appBasedUtils.getStringData(data, "Validate-ListCheckScenarios"), "Check scenarios", softAssert);
            checkAndRedirectToSubMenus(validationMainMenuElement, validationListChecks, validationCheckElement,
                                       appBasedUtils.getStringData(data, "Validate-ListChecks"), "Checks", softAssert);
            checkAndRedirectToSubMenus(validationMainMenuElement, validationNewCheckScenario, validationNewCheckScenarioElement,
                                       appBasedUtils.getStringData(data, "Validate-NewCheckScenario"), "New check scenario", softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies submenu items of coverage menu
     *************************************/
    public void verifyCoverageMenu(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageListCoverage, coverageCoverageSpecifElement,
                                       appBasedUtils.getStringData(data, "Coverage-ListCoverages"), "Coverages specification", softAssert);
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageFunctionGroupRange, coverageListFuncGrpRangesElement,
                                       appBasedUtils.getStringData(data, "Coverage-ListFunctionGroupRanges"), "List function group ranges", softAssert);
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageDefectCodeRange, coverageDefectCodeElement,
                                       appBasedUtils.getStringData(data, "Coverage-ListDefectCodeRanges"), "List defect code ranges", softAssert);
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageOperationNoRange, coverageOperationNoElement,
                                       appBasedUtils.getStringData(data, "Coverage-ListOperationNoRanges"), "List operation no. ranges", softAssert);
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewCoverage, coverageNewCoverageElement,
                                       appBasedUtils.getStringData(data, "Coverage-NewCoverages"), "New coverage", softAssert);
            if (companyNameElement.getText().equals(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewCoveragePerMachine, coverageNewCoveragePerMachineRule,
                                           appBasedUtils.getStringData(data, "Coverage-NewCoveragePerVehicleRule"), "New coverage per machine rule",
                                           softAssert);
                checkAndRedirectToSubMenus(coverageMainMenuElement, coverageListCoveragePerMachine, coverageListCoveragePerMachineRule,
                                           appBasedUtils.getStringData(data, "Coverage-ListCoveragePerVehicleRule"), "List coverage per machine rule",
                                           softAssert);
            } else if (companyNameElement.getText().equals(VOLVO_PENTA_CORPORATION)) {
                checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewCoveragePerDriveline, coverageNewCoverageDrivelinePerRule,
                                           appBasedUtils.getStringData(data, "Coverage-NewCoveragePerVehicleRule"), "New coverage per driveline rule",
                                           softAssert);
                checkAndRedirectToSubMenus(coverageMainMenuElement, coverageListCoveragePerDriveline, coverageListCoverageDrivelinePerRule,
                                           appBasedUtils.getStringData(data, "Coverage-ListCoveragePerVehicleRule"), "List coverage per driveline rule",
                                           softAssert);
            } else {
                checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewCoveragePerVehicle, coverageNewCoveragePerRule,
                                           appBasedUtils.getStringData(data, "Coverage-NewCoveragePerVehicleRule"), "New coverage per vehicle rule",
                                           softAssert);
                checkAndRedirectToSubMenus(coverageMainMenuElement, coverageListCoveragePerVehicle, coverageListCoveragePerRule,
                                           appBasedUtils.getStringData(data, "Coverage-ListCoveragePerVehicleRule"), "List coverage per vehicle rule",
                                           softAssert);
            }
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewFunctionGroupRange, coverageNewFuncGrpRangesElement,
                                       appBasedUtils.getStringData(data, "Coverage-NewFunctionGroupRanges"), "New function group range", softAssert);
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewDefectCodeRange, coverageNewDefectCodeElement,
                                       appBasedUtils.getStringData(data, "Coverage-NewDefectCodeRanges"), "New defect code range", softAssert);
            checkAndRedirectToSubMenus(coverageMainMenuElement, coverageNewOperationNoRange, coverageNewOperationNoElement,
                                       appBasedUtils.getStringData(data, "Coverage-NewOperationNoRanges"), "New operation no. range", softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies submenu items of campaign menu
     *************************************/
    public void verifyCampaignMenu(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignSearchCampaign, campaignSearchCampaignElement,
                                             appBasedUtils.getStringData(data, "Campaign-SearchCampaign"), "List campaigns", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignNewCampaign, campaignNewCampaignElement,
                                             appBasedUtils.getStringData(data, "Campaign-NewCampaign"), "Campaign", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignCampaignElement, campaignListCampaign, campaignListCampaign2Element,
                                             appBasedUtils.getStringData(data, "Campaign-ListCampaign"), "List campaigns", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignAlertsElement, campaignByLeadTime, campaignByLeadTimeElement,
                                             appBasedUtils.getStringData(data, "Campaign-ByLeadTime"), "Alert by lead time", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignFollowUpElement, campaignByLeadTime2, campaignByLeadTime2Element,
                                             appBasedUtils.getStringData(data, "Campaign-ByLeadTime2"), "Follow up by lead time", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignFollowUpElement, campaignByCurrentStatus, campaignByCurrentStatusElement,
                                             appBasedUtils.getStringData(data, "Campaign-ByCurrentStatus"), "Follow up by current status", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignFollowUpElement, campaignByReason, campaignByReasonElement,
                                             appBasedUtils.getStringData(data, "Campaign-ByReason"), "Follow up by reason", softAssert);
            checkAndRedirectToSecondSubMenus(campaignMainMenuElement, campaignFollowUpElement, campaignByFunctionGroup, campaignByFunctionGroupElement,
                                             appBasedUtils.getStringData(data, "Campaign-ByFunctionGroup"), "Follow up by function group", softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies submenu items of TMA menu
     *************************************/
    public void verifyTMAMenu_Central(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaDeliveryConfirmReceiveElement, tmaManualStartReceiveAnalyze, tmaManualStartReceiveElement,
                                             appBasedUtils.getStringData(data, "TMA-ReceiveAnalyze"), "Manual start receive/analyze", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaDeliveryConfirmReceiveElement, tmaManualStartDeliveryConfirm, tmaManualStartDeliveryElement,
                                             appBasedUtils.getStringData(data, "TMA-DeliveryConfirm"), "Manual start delivery confirm", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaDeliveryConfirmReceiveElement, tmaSearchTMA, tmaSearchElement,
                                             appBasedUtils.getStringData(data, "TMA-Search"), "Search", softAssert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies submenu items of TMA menu
     *************************************/
    public void verifyTMAMenu(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaDeliveryConfirmReceiveElement, tmaManualStartReceiveAnalyze, tmaManualStartReceiveElement,
                                             appBasedUtils.getStringData(data, "TMA-ReceiveAnalyze"), "Manual start receive/analyze", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaDeliveryConfirmReceiveElement, tmaManualStartDeliveryConfirm, tmaManualStartDeliveryElement,
                                             appBasedUtils.getStringData(data, "TMA-DeliveryConfirm"), "Manual start delivery confirm", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaDeliveryConfirmReceiveElement, tmaSearch, tmaSearchElement,
                                             appBasedUtils.getStringData(data, "TMA-Search"), "Search", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaStorageAndShippingElement, tmaPalletRack, tmaPalletRackElement,
                                             appBasedUtils.getStringData(data, "TMA-StorageAndShipping-PalletRacks"), "Storage", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaStorageAndShippingElement, tmaSearchTMA, tmaSearchTMAElement,
                                             appBasedUtils.getStringData(data, "TMA-StorageAndShipping-Search"), "Storage", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaStorageAndShippingElement, tmaTransactionLog, tmaTransactionLogElement,
                                             appBasedUtils.getStringData(data, "TMA-StorageAndShipping-TransactionLog"), "Storage transaction log", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaStorageAndShippingElement, tmaPalletsForShipping, tmaPalletsForShippingElement,
                                             appBasedUtils.getStringData(data, "TMA-StorageAndShipping-PalletsForShipping"), "Pallets for shipping",
                                             softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaStorageAndShippingElement, tmaShippingHistory, tmaShippingHistoryElement,
                                             appBasedUtils.getStringData(data, "TMA-StorageAndShipping-ShippingHistory"), "Shipping history", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaMaterialRequestElement, tmaListMaterialReq, tmaListMaterialReqElement,
                                             appBasedUtils.getStringData(data, "TMA-MaterialReq-List"), "Material request", softAssert);
            checkAndRedirectToSecondSubMenus(tmaMainMenuElement, tmaMaterialRequestElement, tmaAnalysisSummary, tmaAnalysisSummaryElement,
                                             appBasedUtils.getStringData(data, "TMA-MaterialReq-Analysis"), "Analysis summary", softAssert);
            checkAndRedirectToSubMenus(tmaMainMenuElement, tmaSearchTMAInfoElement, searchTMAInfoElement,
                                       appBasedUtils.getStringData(data, "TMA-SearchTMAInformation"), "Search TMA information", softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************
     * #Description : Verifies submenu items of System Admin menu
     *************************************/
    public void verifySystemAdminMenu(HashMap<String, Object> data) {
        try {
            checkAndRedirectToSubMenus(systemAdminMainMenuElement, systemAdminSearchAndRestart, systemAdminSearchElement,
                                       appBasedUtils.getStringData(data, "SystemAdmin-Search"), "Search credit run", softAssert);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
