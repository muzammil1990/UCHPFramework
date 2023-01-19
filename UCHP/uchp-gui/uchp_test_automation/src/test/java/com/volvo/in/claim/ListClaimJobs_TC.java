package com.volvo.in.claim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.volvo.in.commonlibrary.AppConstants;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.AdvanceSearch;
import com.volvo.in.page.claim.ListClaimJobs;
import com.volvo.in.page.welcomepage.WelcomePage;

public class ListClaimJobs_TC extends BaseClass {
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    WebDriver driver;
    ListClaimJobs listClaimJobs;

    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName" })
    @BeforeClass
    public void setUp(String browserType, String appURL, String sheetName, String testDataFileName,
            @Optional("MissingSecondaryDataFile") String secondaryDataFileName) throws IOException {
        String browserName = browserType;
        String URL = appURL;
        driver = setDriver(browserName, URL);
        listClaimJobs = new ListClaimJobs(driver);
        excelSheetName = sheetName;
        ExcelReadAndWrite excelReadWriteSecondaryDataFile;
        excelReadWrite = new ExcelReadAndWrite(AppConstants.RESOURCES_TEST_DATA + getEnvironmentName() + "\\" + testDataFileName, sheetName);
        lstData = excelReadWrite.readAll();
        if (!secondaryDataFileName.equals("MissingSecondaryDataFile")) {
            excelReadWriteSecondaryDataFile = new ExcelReadAndWrite(AppConstants.RESOURCES_TEST_DATA + getEnvironmentName() + "\\" + secondaryDataFileName,
                                                                    sheetName);
            lstSecondaryData = excelReadWriteSecondaryDataFile.readAll();
        }
    }
    @Parameters({ "Company", "userName", "passWord" })
    @BeforeMethod(alwaysRun = true)
    public void loginBeforeMethod(String sheetName, String userName, String password) {
        welcomePage = new WelcomePage(driver);
        applicationUserName = userName;
        if (getEnvironmentName().equalsIgnoreCase("QA")) {
            welcomePage.loginQA(userName, password);
        } else {
            welcomePage.login(userName, password, driver);
        }
        welcomePage.switchToCompany(sheetName, driver);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void TC01_VerifyListClaimJobsGUI_VerifySearchCriteriaGroups() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyButtonAndClaimTabs(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC02_ListClaimJobs_SearchAndVerifyClaimStatusInTMADecisiontab() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        listClaimJobs.navigateToListClaimJob();
        // Searches Importer,Dealer, search type and Verifies Importer dealer, ClaimStatusla,TMAStatus in the search result
        listClaimJobs.searchWithImporterDealerInTMADecisionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC03_ListClaimJobs_SearchAndVerifyTypeOfConcernRegDateInNotEvaluated() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(2);
        listClaimJobs.navigateToListClaimJob();
        // Search with Typeof concern,RegisterDate,NotEvalated
        listClaimJobs.listWithRegistrationDateInNotEvaluated(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC04_ListClaimJobs_SearchAndVerifyChassisIDRepDateInNotFinished() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(3);
        listClaimJobs.navigateToListClaimJob();
        // Search with repair date, Registration number, Chassis id and Verify ClaimStatus, Chassis id in Not finished search result
        listClaimJobs.listAndVerifyClaimStatusInNotFinished(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC05_ListClaimJobs_ClickTMADecisiontabVerifyResult() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(4);
        listClaimJobs.navigateToListClaimJob();
        // Verifies TMA decision column in the TMA decision tab
        listClaimJobs.ClickTMADecisiontabVerifyResult(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC06_ListClaimJobs_ClickNotFinishedtabVerifyResult() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(5);
        listClaimJobs.navigateToListClaimJob();
        // Verifies Status column in the not finished tab
        listClaimJobs.ClickNotFinishedtabVerifyResult(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC07_ListClaimJobs_ClickMaterialInstrunctionTabVerifyResult() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        listClaimJobs.navigateToListClaimJob();
        // Verifies MIType column in the material instruction tab
        listClaimJobs.ClickMatrlInstructiontabVerifyResult(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC08_ListClaimJobs_SearchAndVerifySendMITypeInMaterialInstrunction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(7);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Send" MIType and Verifies MIType in material instruction
        listClaimJobs.searchWithMITypeInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC09_ListClaimJobs_SearchAndVerifyMIDateInMaterialInstrunction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(8);
        listClaimJobs.navigateToListClaimJob();
        // Search and Verifies MIDate column in material instruction
        listClaimJobs.searchWithMIDateInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC10_ListClaimJobs_SearchAndVerifyMIPrintStatusPrentedInMaterialInstrunction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(9);
        listClaimJobs.navigateToListClaimJob();
        // Search and Verifies Printed claim in MI Print status column in material instruction
        listClaimJobs.searchWithMIPrintStatusInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC11_ListClaimJobs_SearchAndVerifyMIPrintStatusSelectedForPrintInMaterialInstrunction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(10);
        listClaimJobs.navigateToListClaimJob();
        // Search and Verifies Select for print claim in MI Print status column in material instruction
        listClaimJobs.searchWithMIPrintStatusInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC12_ListClaimJobs_ClickAppealTabVerifyResult() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(11);
        listClaimJobs.navigateToListClaimJob();
        // Search and Verifies Select for print claim in MI Print status column in material instruction
        listClaimJobs.ClickAppealtabVerifyResult(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC13_ListClaimJobs_SearchAndVerifyAppealStatusAllInAppeal() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(12);
        listClaimJobs.navigateToListClaimJob();
        // Search and Verifies All Appeal status column in Appeal
        listClaimJobs.searchWithAppealStatusInAppealTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC14_ListClaimJobs_SearchAndVerifyAppealRequestStatusInAppeal() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(13);
        listClaimJobs.navigateToListClaimJob();
        // Search and Verifies All Appeal status column in Appeal tab
        listClaimJobs.searchWithAppealStatusInAppealTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC15_ListClaimJobs_SearchAndVerifyClaimTypeVINInNotFinished() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(14);
        listClaimJobs.navigateToListClaimJob();
        // Search with VIN, Type of claim and Verify ClaimType in Not finished search result
        listClaimJobs.listAndVerifyClaimTypeInNotFinished(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC16_ListClaimJobs_SearchWithCausalPartsAndVerifyAppealResponseStatusInAppeal() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(15);
        listClaimJobs.navigateToListClaimJob();
        // Casual part prefix, Casual part number appeal status and Verifies "Appeal response" Appeal status column in Appeal tab
        listClaimJobs.searchWithCausalPartsAppealStatusInAppealTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC17_ListClaimJobs_SearchAndVerifySCCRepairOrderNumInNotFinished() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(16);
        listClaimJobs.navigateToListClaimJob();
        // Search Repair order number, SCC and Verifies Repair Ordernumber, ClaimStatus column in Not finished tab
        listClaimJobs.searchWithRepairOderNumInNotFinished(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC18_ListClaimJobs_SearchAndVerifyScrapMITypeInMaterialInstrunction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(17);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Scrap" MIType and Verifies MIType in material instruction
        listClaimJobs.searchWithMITypeInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC19_ListClaimJobs_SearchAndVerifyHoldMITypeInMaterialInstrunction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(18);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Scrap" MIType and Verifies MIType in material instruction
        listClaimJobs.searchWithMITypeInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC20_ListClaimJobs_SearchWithFollowUpCodeFunctionGroupVerifyResultInNotEvaluation() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(19);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Scrap" MIType and Verifies MIType in material instruction
        listClaimJobs.searchWithFollowUpCodeFunctionGroupInNotEvaluated(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC21_ListClaimJobs_standardSearchPrintMIOfHoldMITypeUsingRefNumberInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(20);
        listClaimJobs.navigateToListClaimJob();
        // Search with ReferenceNumber MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchUsingRefNumAndPrintMIInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC22_ListClaimJobs_ClickMyNotEvaluatedTabVerifyResult() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(21);
        listClaimJobs.navigateToListClaimJob();
        // Clicks on My not evaluated tab and verifies Status,Importer and Dealer column
        listClaimJobs.ClickMyNotEvaluatedtabVerifyResult(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC23_ListClaimJobs_SearchAndVerifyClaimTypeInMyNotEvaluated() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(22);
        listClaimJobs.navigateToListClaimJob();
        // Search with ClaimType and verifies Status,Importer and Dealer, claimType columns
        listClaimJobs.SearchAndVerifyClaimTypeInMyNotEvaluated(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC24_ListClaimJobs_standardSearchPrintMIOfHoldMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(23);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintMIInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
        listClaimJobs.testCaseAssertion.assertAll();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC25_ListClaimJobs_standardSearchPrintMIOfScrapMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(24);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintMIInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC26_ListClaimJobs_standardSearchPrintMIOfSendMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(25);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintMIInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC27_ListClaimJobs_PrintMIInMaterialInstructionTab() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(26);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.printMIInMaterialInstructionTab(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC28_ListClaimJobs_standardSearchPrintDeliveryNoteSummaryOfHoldMITypeMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(27);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.PrintDealerNoteSummaryOfMITypeInMaterialInstruction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC29_ListClaimJobs_standardSearchPrintDeliveryNoteSummaryOfSendMITypeMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(28);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.PrintDealerNoteSummaryOfMITypeInMaterialInstruction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC30_ListClaimJobs_standardSearchPrintDeliveryNoteSummaryOfScrapMITypeMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(29);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.PrintDealerNoteSummaryOfMITypeInMaterialInstruction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC31_ListClaimJobs_standardSearchPrintDeliveryNoteSummOfScrapMITypeUsingRefNumberInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(30);
        listClaimJobs.navigateToListClaimJob();
        // Search with ReferenceNumber MIType and Verifies PrintDealeNoteSummary functionality in material instruction
        listClaimJobs.standardSearchUsingRefNumAndPrintDeliveryNoteSummaryInMaterialInstruction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC32_ListClaimJobs_PrintDeliveryNoteSummInMaterialInstructionTab() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(31);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.printDeliveryNoteSummaryInMaterialInstructionTab(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC33_ListClaimJobs_standardSearchPrintDealerShippingDocumentOfSendMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(23);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintDealerShippingDocumentInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test(dependsOnMethods = { "TC33_ListClaimJobs_standardSearchPrintDealerShippingDocumentOfSendMITypeInMaterialInstruction" }, alwaysRun = true)
    public void TC34_ListClaimJobs_removeMIFromShipment() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(23);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
        listClaimJobs.removeMIFromShipment();
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC35_ListClaimJobs_standardSearchPrintDealerShippingDocumentOfSendMITypeWithCarrierAndTrackingID() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(24);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintDealerShippingDocumentInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test(dependsOnMethods = { "TC35_ListClaimJobs_standardSearchPrintDealerShippingDocumentOfSendMITypeWithCarrierAndTrackingID" }, alwaysRun = true)
    public void TC36_ListClaimJobs_removeMIFromShipment() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(23);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
        listClaimJobs.removeMIFromShipment();
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC37_ListClaimJobs_standardSearchNegFlowPrintDealerShippingDocumentOfHoldMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(25);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintDealerShippingDocumentNegFlowInMaterialInstruction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC38_ListClaimJobs_standardSearchNegFlowPrintDealerShippingDocumentOfScrapMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(26);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintDealerShippingDocumentNegFlowInMaterialInstruction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC39_ListClaimJobs_standardSearchPrintAlreadyPrintedDealerShippingDocumentOfSendMITypeInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(27);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchPrintDealerShippingDocumentInMaterialInstruction(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC40_ListClaimJobs_PrintDealerShippingDocumentOfSendMITypeInMaterialInstructionTab() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(28);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.printDealerShippingDocumentInMaterialInstructionTab(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC41_ListClaimJobs_standardSearchNegFlowRemoveFromShipmentInMaterialInstruction() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(29);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.standardSearchNegFlowRemoveFromShipmentInMaterialInstruction(data);
    }

    @Test
    public void TC42_ListClaimJobs_DeleteClaimJobInNotFinishedTab() {
        listClaimJobs.navigateToListClaimJob();
        // delete a first record and validated selected claim is deleted
        listClaimJobs.deleteClaimJobInNotFinishedTab();

    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC43_ListClaimJobs_PrintHoldScrapListOfScrapMITypeInMaterialInstructionTab() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(43);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.PrintHoldScrapListOfScrapMITypeInMaterialInstructionTab(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TC43_ListClaimJobs_PrintHoldScrapListOfHoldMITypeInMaterialInstructionTab() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(44);
        listClaimJobs.navigateToListClaimJob();
        // Search with "Hold" MIType and Verifies PrintMI in material instruction
        listClaimJobs.PrintHoldScrapListOfScrapMITypeInMaterialInstructionTab(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyClaimJobAfterMaterialInstructionAction(data);
    }

    @Test
    // Verifies Status column in the not finished tab
    public void TC08_selectMultipleClaimJob() {
        // HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(6);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.selectMultipleClaimJob();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT102_21_ListClaimJobContract() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(43);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.VerifyButtonAndClaimTabs(data);
        listClaimJobs.verifyTheClaimStatusInTheTabs(data);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.selectTypeOfConcern(data);
        listClaimJobs.searchWithImporterDealerAndClaimType(data);
        listClaimJobs.selectMultipleClaimJob();
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.selectTypeOfConcern(data);
        listClaimJobs.standardSearchPrintMIInMaterialInstruction(data);
        HashMap<String, Object> data1 = (HashMap<String, Object>) lstData.get(31);
        listClaimJobs.printDeliveryNoteSummaryInMaterialInstructionTab(data1);
        // AppealStatus
        listClaimJobs.ClickAppealtabVerifyResult(data);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void RT103_01_EvaluateClaimJobSearch() {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(44);
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.verifySearchFields();
        listClaimJobs.navigateToListClaimJob();
        listClaimJobs.selectTypeOfConcern(data);
        listClaimJobs.searchWithImporterDealerPartNumberNotEvaluated(data);
        listClaimJobs.verifyGrandTotalCostInSearchResultGridAndInClaimJobInformation(data);
    }
    
    @AfterMethod(alwaysRun = true)
    public void logoutAfterMethod(ITestResult result) {
            welcomePage.closeUnsavedAlertAndRemoveErrorIcon(driver);
            welcomePage.logout(driver);
       }
    
    @AfterClass(alwaysRun = true)
    protected void afterSuite() {
        driver.close();
    }
}
