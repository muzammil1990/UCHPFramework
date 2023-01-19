package com.volvo.in.page.maintain;

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

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.UIUtils;

public class ContractPriceSource {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert;
    public Logger APP_LOGS = Logger.getLogger(ContractPriceSource.class);

    @FindBy(how = How.ID, using = "newContractPriceSource")
    WebElement newContractPriceSource;

    @FindBy(how = How.ID, using = "priceSource")
    WebElement contractPriceSource;

    @FindBy(how = How.ID, using = "sequence")
    WebElement sequence;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-no btn-default ng-binding']")
    WebElement noPopup;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement errorMessageClose;

    @FindBy(how = How.XPATH, using = "//span[@ng-show='grid.options.totalItems > 0']")
    WebElement totalitem;

    @FindBy(how = How.XPATH, using = "//button[@ng-if='ctrl.isHistoryBackAvailable()']")
    WebElement closeContractPriceSource;

    @FindBy(how = How.XPATH, using = "//button[@id='saveContractPriceSource']")
    WebElement saveContractPriceSource;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintain;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Importer ')]")
    WebElement importersub;

    @FindBy(how = How.ID, using = "importer")
    WebElement importer;

    @FindBy(how = How.ID, using = "partNoPrefix")
    WebElement partNoPrefix;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Contract price source ')]")
    WebElement contractPriceSourceText;

    @FindBy(how = How.XPATH, using = "//h4[text()='Contract price source']")
    WebElement contractPriceSourceTitle;

    @FindBy(how = How.XPATH, using = "newContractPriceSource")
    WebElement newContractPriceSourceAdd;

    @FindBy(how = How.XPATH, using = "//input[@id='importer']")
    WebElement importerNo;

    @FindBy(how = How.XPATH, using = "//select[@id='priceSource']")
    WebElement dropdownSelectPricesource;

    @FindBy(how = How.XPATH, using = "sequence")
    WebElement dropdownSelectSequence;

    @FindBy(how = How.ID, using = "contractDefInUCHP")
    WebElement contractDefinUchpCheckBox;

    @FindBy(how = How.ID, using = "newRollout")
    WebElement newContractRollout;

    @FindBy(how = How.ID, using = "saveRollout")
    WebElement save;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//select[@id='saveClaimInUCHP']")
    WebElement dropdownSaveClaimInUCHP;

    @FindBy(how = How.XPATH, using = "//select[@id='contractDefInUCHP']")
    WebElement dropdownContractDefInUCHP;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-xs btn-primary btn-collapse']")
    WebElement searchLinkExpand;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-sm pull-right ng-binding']")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0005']")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "//div[@ng-switch-when='true']//.")
    WebElement errorMessage;

    @FindBy(how = How.ID, using = "deleteRollout")
    WebElement deleteButton;

    @FindBy(how = How.XPATH, using = ".//*[@id='grid_contract_price_source_list']/div[2]/div[1]/div[1]/span")
    WebElement noOfPage;

    @FindBy(how = How.XPATH, using = "//*[@id='grid_contract_price_source_list']/div[2]/div[1]/div[2]/select")
    WebElement noItemsPerPg;

    @FindBy(how = How.XPATH, using = "//button[@uib-tooltip ='Next']")
    WebElement nextButtn;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'VO')]")
    WebElement partprefix;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindAll({ @FindBy(xpath = "//div[@class='ng-isolate-scope']/div[1]/div/span") })
    List<WebElement> importerValueInGrid;

    @FindAll({ @FindBy(xpath = "//div[@class='ng-isolate-scope']/div[2]/div/span") })
    List<WebElement> partPrefixValueInGrid;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//span[@class='margin-right-5 ng-binding ng-scope']")
    WebElement priceSourceInClaimMaterialTab;

    @FindBy(how = How.XPATH, using = "//a[text()='Material']")
    WebElement claimJobMaterial;

    @FindBy(how = How.XPATH, using = "//input[@name='partNoPrefix']")
    WebElement claimJobMaterialPrefix;


    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000";

    public ContractPriceSource(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToMaintainImporterContractPriceSource() #Author : Dinesh #Description : This is a common
     * function to navigate from "Maintain" main menu to "ContractPriceSource" sub menu #Date of creation :16-06-17 #Input Parameters: #Name of person
     * modifying: Dinesh #Date of modification: 23-03-17
     ******************************************/
    public void navigateToMaintainImporterContractPriceSource() {

        // To clear the search result each time refresh code is written
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        keywords.performSecondLevelMenuAction(driver, maintain, importersub, contractPriceSourceText, contractPriceSourceTitle);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : SelectPriceSource() #Author : Aditi #Description : Selecting price source for a given part prefix.
     * function to Selecting price source for a given importer and part pre-fix. #Date of creation :05-07-18 #Input Parameters: #Name of person modifying:
     * Dinesh #Date of modification: 23-03-17
     ******************************************/
    public void selectPriceSource(HashMap<String, Object> newClaimJobData) {
            keywords.clickOnButton(driver, newContractPriceSource);
            keywords.input(importer, appBasedUtils.getStringData(newClaimJobData, "Importer"), "Importer");
            keywords.input(partNoPrefix, appBasedUtils.getStringData(newClaimJobData, "ReplacedPartPrefix"), "Partprefix");
            keywords.selectVisibleText(contractPriceSource, appBasedUtils.getStringData(newClaimJobData, "PartSource"));
            keywords.selectVisibleText(sequence, appBasedUtils.getStringData(newClaimJobData, "PriceSourceSequence"));
            keywords.clickOnButton(driver, saveContractPriceSource);
            keywords.waitTime1(3);
            if (keywords.assertContains("There already exists a price source. Could not create.", keywords.getTextElementIsDisplayed(errorMessage))) {
                keywords.waitTime1(2);
                keywords.clickOnButton(driver, closeContractPriceSource);
                keywords.waitTime1(2);
                keywords.clickOnButton(driver, noPopup);
                keywords.waitTime1(2);
            } else {
                keywords.clickOnButton(driver, closeContractPriceSource);
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 9, loadingIcon);
            keywords.selectVisibleText(noItemsPerPg, 50);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 9, loadingIcon);
            for (int i = 0; i < importerValueInGrid.size(); i++) {
                if (((appBasedUtils.getStringData(newClaimJobData, "Importer")).equalsIgnoreCase(importerValueInGrid.get(i).getText()))
                        && ((appBasedUtils.getStringData(newClaimJobData, "ReplacedPartPrefix")).equalsIgnoreCase(partPrefixValueInGrid.get(i).getText()))) {
                    keywords.clickOnButton(driver, importerValueInGrid.get(i));
                    keywords.selectVisibleText(contractPriceSource, appBasedUtils.getStringData(newClaimJobData, "PartSource"));
                    keywords.selectVisibleText(sequence, appBasedUtils.getStringData(newClaimJobData, "PriceSourceSequence"));
                    keywords.clickOnButton(driver, saveContractPriceSource);
                    keywords.clickOnButton(driver, closeContractPriceSource);
                    break;
                }
                if ((i == 9) && (keywords.elementIsEnabled(nextButtn))) {
                    keywords.clickOnButton(driver, nextButtn);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 9, loadingIcon);
                    i = 0;
                }
            }
    }
    /****************************************
     * This method compare the price source value in material tab with value of PriceSourceFromVIPS fetched from VIPS
     ******************************************/
    public void validateTheContractPriceSourceInClaim(HashMap<String, Object> newClaimJobData) {
        try {
            keywords.clickOnLink(claimJobMaterial, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            Assert.assertEquals(keywords.getTextValue(priceSourceInClaimMaterialTab),appBasedUtils.getStringData(newClaimJobData, "PriceSourceFromVIPS"));
            APP_LOGS.info("CreatedContractClaimJobAndVerifiedPartsPriceFetchingWithDiffrentPriceSourceCombinationSequenceYes");
    }catch(Exception e){
        APP_LOGS.error(e.getMessage());
        Assert.fail();
    }
   }
}