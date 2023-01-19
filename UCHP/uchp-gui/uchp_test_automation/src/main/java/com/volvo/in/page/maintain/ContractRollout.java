package com.volvo.in.page.maintain;

import java.util.HashMap;

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
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class ContractRollout {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintain;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Contract ')]")
    WebElement contract;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Rollout')]")
    WebElement rollout;

    @FindBy(how = How.XPATH, using = "//h4[text()='Maintain contract rollout']")
    WebElement contractrollouttitle;

    @FindBy(how = How.XPATH, using = "//input[@id='importerNo']")
    WebElement importerno;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerNo']")
    WebElement dealerno;

    @FindBy(how = How.ID, using = "saveClaimInUCHP")
    WebElement saveclaiminuchpCheckBox;

    @FindBy(how = How.ID, using = "contractDefInUCHP")
    WebElement contractdefinuchpCheckBox;

    @FindBy(how = How.ID, using = "newRollout")
    WebElement newcontractrollout;

    @FindBy(how = How.ID, using = "saveRollout")
    WebElement save;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//select[@id='saveClaimInUCHP']")
    WebElement dropdownsaveClaimInUCHP;

    @FindBy(how = How.XPATH, using = "//select[@id='contractDefInUCHP']")
    WebElement dropdowncontractDefInUCHP;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-primary btn-collapse']")
    WebElement searchLinkExpand;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-sm pull-right ng-binding']")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0005']")
    WebElement gridColumnName;

    @FindBy(how = How.ID, using = "deleteRollout")
    WebElement deleteButton;

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000";

    public ContractRollout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToMaintainContractRollout() #Author : Dinesh #Description : This is a common function to
     * navigate from "Maintain" main menu to "Rollout" sub menu #Date of creation :16-06-17 #Input Parameters: #Name of person modifying: Dinesh #Date of
     * modification: 23-03-17
     ******************************************/
    public void navigateToContractRollout() {

        // To clear the search result each time refresh code is written
        keywords.waitTime(6);
        keywords.performSecondLevelMenuAction(driver, maintain, contract, rollout, contractrollouttitle);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : createContractRollout() #Author : Dinesh #Description : This is to create new contract rollout #Date
     * of creation :16-06-17 #Input Parameters: #Name of person modifying: Dinesh #Date of modification: 16-06-17
     ******************************************/
    public void createContratRollout(HashMap<String, Object> data) {
        softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(newcontractrollout));
        keywords.clickOnButton(driver, newcontractrollout);
        wait.until(ExpectedConditions.visibilityOf(importerno));
        keywords.input(importerno, data.get("Importer"), "impoter");
        keywords.input(dealerno, data.get("Dealer"), "Dealer");
        keywords.clickOnButton(driver, saveclaiminuchpCheckBox);
        keywords.clickOnButton(driver, contractdefinuchpCheckBox);
        keywords.clickOnButton(driver, save);
        if (keywords.elementIsDisplayed(errorMsg, "Error message pop up")) {
            String val = keywords.getTextValue(errorMsg);
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsg));
            keywords.clickOnButton(driver, validationMessageClose);
            Assert.fail(val);
        } else {
            softAssert.assertTrue(keywords.elementIsEnabled(deleteButton), "New contract Roll out is not saved");
            ExtentTestManager.getTest().log(Status.INFO, "New contract roll out is saved successfully");
        }
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : searchContractRollout() #Author : Dinesh #Description : This is to search contract rollout with save
     * claim in uchp and contract definition in uchp status as 'All' #Date of creation :16-06-17 #Input Parameters: #Name of person modifying: Dinesh #Date of
     * modification: 16-06-17
     ******************************************/
    public void searchContractRolloutWithFilter(HashMap<String, Object> data) {
        String[] columnHeader = { "Importer", "Dealer", "SaveclaiminUCHP", "ContractdefinitioninUCHP" };
        keywords.clickOnLink(searchLinkExpand, driver);
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(importerno));
        keywords.input(importerno, data.get("Importer"), "impoter");
        keywords.input(dealerno, data.get("Dealer"), "Dealer");
        keywords.selectVisibleText(dropdownsaveClaimInUCHP, (String) data.get("SaveClaimInUCHP"));
        keywords.selectVisibleText(dropdowncontractDefInUCHP, (String) data.get("ContractInUCHP"));
        keywords.clickOnButton(driver, searchButton);
        if (keywords.elementIsDisplayed(errorMsg, "Error message pop up")) {
            String val = keywords.getTextValue(errorMsg);
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsg));
            keywords.clickOnButton(driver, validationMessageClose);
            Assert.fail(val);
        }
        rolloutTableVerifySearchResultInGrid(data, columnHeader, "5 6 7 8");
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : searchContractRollout() #Author : Dinesh #Description : This is to search contract rollout with save
     * claim in uchp and contract definition in uchp status as 'All' #Date of creation :16-06-17 #Input Parameters: #Name of person modifying: Dinesh #Date of
     * modification: 16-06-17
     ******************************************/
    public void searchContractRolloutWithFilterToDelete(HashMap<String, Object> data) {
        String[] columnHeader = { "Importer", "Dealer", "SaveclaiminUCHP", "ContractdefinitioninUCHP" };
        keywords.clickOnLink(searchLinkExpand, driver);
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(importerno));
        keywords.input(importerno, data.get("Importer"), "impoter");
        keywords.input(dealerno, data.get("Dealer"), "Dealer");
        keywords.selectVisibleText(dropdownsaveClaimInUCHP, (String) data.get("SaveClaimInUCHP"));
        keywords.selectVisibleText(dropdowncontractDefInUCHP, (String) data.get("ContractInUCHP"));
        keywords.clickOnButton(driver, searchButton);
        if (keywords.elementIsDisplayed(errorMsg, "Error message pop up")) {
            String val = keywords.getTextValue(errorMsg);
            ExtentTestManager.getTest().log(Status.INFO, keywords.getTextValue(errorMsg));
            keywords.clickOnButton(driver, validationMessageClose);
            Assert.fail(val);
        }
        rolloutTableVerifySearchResultInGrid(data, columnHeader, "5 6 7 8 9");
    }

    public void rolloutTableVerifySearchResultInGrid(HashMap<String, Object> data, String columnheader[], String noOfColumnsToValidate) {

        int columnIndexInSearchGrid = 0;
        softAssert = new SoftAssert();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 12);
            wait.until(ExpectedConditions.visibilityOf(gridColumnName));
            for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {

                if (hexaStrToXpath.equals("5") || hexaStrToXpath.equals("6")) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                    appBasedUtils.textValidationGrid(driver, ((String) data.get(columnheader[columnIndexInSearchGrid])),
                                                     XpathFirstPart + hexaStrToXpath + "']", softAssert);
                    columnIndexInSearchGrid++;
                }

                else if (hexaStrToXpath.equals("7") || hexaStrToXpath.equals("8")) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");
                    appBasedUtils.dropdownValueValidationInGrid(data.get("ExpectedDropdownValues").toString(), XpathFirstPart + hexaStrToXpath + "']",
                                                                softAssert, driver);
                    columnIndexInSearchGrid++;
                }
                // Deleting functionality needs to be implemented here
                else if (hexaStrToXpath.equals("9")) {
                    keywords.APP_LOGS.info("Validating \"" + columnheader[columnIndexInSearchGrid] + "\" Column.");

                    columnIndexInSearchGrid++;
                } else {
                    keywords.APP_LOGS.info("Value of char didnot match" + hexaStrToXpath);
                }

            }
            softAssert.assertAll();
            ExtentTestManager.getTest().log(Status.PASS, "Search was successful");
        } catch (Exception e) {
            if (keywords.elementIsDisplayed(errorMsg, "Error message pop up")) {
                String val = keywords.getTextValue(errorMsg);
                Assert.fail("Error in validation" + val);
            } else {
                Assert.fail("Fail!!! Search result not found '");
            }
        }
    }

}
