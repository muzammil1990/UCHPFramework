package com.volvo.in.page.maintain;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.UIUtils;

public class StairsSplit {
    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction equipment";
    String companyName;
    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert softAssert;
    UIUtils keywords = new UIUtils(driver);
    public Logger APP_LOGS = Logger.getLogger(AutoException.class);
    public WebDriverWait wait = null;
    public String DescriptionText;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintain;

    @FindBy(how = How.XPATH, using = "//a[@class='dropdown-toggle' and contains(.,'Stairs ')]")
    WebElement stairsSubMenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New stairs ')]")
    WebElement newStairs;

    @FindBy(how = How.XPATH, using = "//h4[text()='Stairs']")
    WebElement newStairsTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='description']")
    WebElement descriptionElement;

    @FindBy(how = How.XPATH, using = "//select[@id='currency']")
    WebElement Currency;

    @FindBy(how = How.XPATH, using = "//select[@id='reduction_type']")
    WebElement reductionTypeDropDown;

    @FindBy(how = How.XPATH, using = "//button[@id='switch_button']")
    WebElement switchButton;

    public String MilageOpHrsAgeFromToElementsFirstPart = "//input[@id='step_";

    public String MilageFromElementSecondPart = "_milageFrom']";

    public String MilageToElementSecondPart = "_milageTo']";

    public String OpHoursFromElementSecondPart = "_opHoursFrom']";

    public String OpHoursToElementSecondPart = "_opHoursTo']";

    public String AgeFromElementSecondPart = "_ageFrom']";

    public String AgeToElementSecondPart = "_ageTo']";

    @FindBy(how = How.XPATH, using = "//div[input[@id='labour_debit_0_0']]/span/i")
    WebElement labourDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_debit_0_0']]/span/i")
    WebElement materialDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_debit_0_0']]/span/i")
    WebElement travelExpenseDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[contains(@id,'ost_debit_0_0')]]/span/i")
    WebElement otherCostsDebitCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='labour_reject_0_0']]/span/i")
    WebElement labourRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='material_reject_0_0']]/span/i")
    WebElement materialRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[@id='travel_reject_0_0']]/span/i")
    WebElement travelExpenseRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//div[input[contains(@id,'ost_reject_0_0')]]/span/i")
    WebElement costRejectionCodeSelection;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_total_amount_percentage_0']")
    WebElement labourTotalPercentage;

    @FindBy(how = How.XPATH, using = "//button[@id='addStep']")
    WebElement addStepButton;

    @FindBy(how = How.XPATH, using = "//input[@id='labour_factor_0_0']")
    WebElement labourPercentElement;

    @FindBy(how = How.XPATH, using = "//input[@id='material_factor_0_0']")
    WebElement materialPercentElement;

    @FindBy(how = How.XPATH, using = "//input[@id='travel_factor_0_0']")
    WebElement travelPercentElement;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'ost_factor_0_0')]")
    WebElement othercostPercentElement;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//button[@id='saveStair']")
    WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Edit connections')]")
    WebElement editConnectionsButton;

    @FindBy(how = How.XPATH, using = "//div[@class='row']//div[1]//dual-multiselect-listbox[1]//div[1]//div[2]//div[3]//input[1]")
    WebElement excludedCoverageFilterElement;

    @FindBy(how = How.XPATH, using = "//dual-multiselect-listbox[contains(@options,'.coverage')]/descendant::option")
    WebElement filteredCoverage;

    @FindBy(how = How.XPATH, using = "//dual-multiselect-listbox[contains(@options,'.coverage')]/../descendant::button[contains(@uib-tooltip,'Move selected items to left')]")
    WebElement includeCoverageButton;

    @FindBy(how = How.XPATH, using = "//button[@id='saveStairConnections']")
    WebElement saveConnectionsButton;

    @FindBy(how = How.XPATH, using = "//td[@class='minwidth100 width10 vtop ng-binding']")
    WebElement stairID;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'sccCoverageBox')]//parent::td[contains(@class,'width90')]")
    WebElement ConnectedCoverage;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement closeButton;

    public String firstXpathOfDebitCodeSelection = "//a[contains(text(),'";
    public String secondXpathOfDebitCodeSelection = "')]";
    public String debitCodeSelection = null;

    public StairsSplit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToNewStairs() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, stairsSubMenu, newStairs, newStairsTitle);
    }

    public void selectRedutionType(HashMap<String, Object> stairs) {
        keywords.waitTime1(3);
        if (appBasedUtils.getStringData(stairs, "ReductionType").equalsIgnoreCase("Amount")) {
            keywords.selectVisibleText(reductionTypeDropDown, appBasedUtils.getStringData(stairs, "ReductionType"));
            keywords.clickOnButton(driver, switchButton);

        } else {
            keywords.selectVisibleText(reductionTypeDropDown, appBasedUtils.getStringData(stairs, "ReductionType"));
        }
    }

    public void createNewStairs(HashMap<String, Object> stairs) {
            companyName = keywords.getTextValue(companyNameElement);
            keywords.waitTime1(3);
            keywords.input(descriptionElement, appBasedUtils.getStringData(stairs, "Description"), "Description");
            keywords.selectVisibleText(Currency, appBasedUtils.getStringData(stairs, "Currency"));
            selectRedutionType(stairs);
            for (int stepsTobeAdded = 0; stepsTobeAdded < 1; stepsTobeAdded++) {
                keywords.waitTime(500);
                if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                    String OpHoursFrom = MilageOpHrsAgeFromToElementsFirstPart + stepsTobeAdded + OpHoursFromElementSecondPart;
                    String OpHoursTo = MilageOpHrsAgeFromToElementsFirstPart + stepsTobeAdded + OpHoursToElementSecondPart;
                    keywords.input(driver.findElement(By.xpath(OpHoursFrom)), appBasedUtils.getStringData(stairs, "OpHoursFrom"), "OpHoursFrom");
                    keywords.input(driver.findElement(By.xpath(OpHoursTo)), appBasedUtils.getStringData(stairs, "OpHoursTo"), "OpHoursTo");
                } else {
                    String milageFrom = MilageOpHrsAgeFromToElementsFirstPart + stepsTobeAdded + MilageFromElementSecondPart;
                    String milageTo = MilageOpHrsAgeFromToElementsFirstPart + stepsTobeAdded + MilageToElementSecondPart;
                    keywords.input(driver.findElement(By.xpath(milageFrom)), appBasedUtils.getStringData(stairs, "MilageFrom"), "MilageFrom");
                    keywords.input(driver.findElement(By.xpath(milageTo)), appBasedUtils.getStringData(stairs, "MilageTo"), "MilageTo");
                }
                String ageFrom = MilageOpHrsAgeFromToElementsFirstPart + stepsTobeAdded + AgeFromElementSecondPart;
                String ageTo = MilageOpHrsAgeFromToElementsFirstPart + stepsTobeAdded + AgeToElementSecondPart;
                keywords.input(driver.findElement(By.xpath(ageFrom)), appBasedUtils.getStringData(stairs, "AgeFrom"), "AgeFrom");
                keywords.input(driver.findElement(By.xpath(ageTo)), appBasedUtils.getStringData(stairs, "AgeTo"), "AgeTo");
                supplyReductionPercentOrAmount(stairs);
                if (stepsTobeAdded > 1) {
                    keywords.clickOnButton(driver, addStepButton);
                }
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
                editConnections(stairs);
                validateStairsdata(stairs);
            }
    }

    public void supplyReductionPercentOrAmount(HashMap<String, Object> stairs) {
            companyName = keywords.getTextValue(companyNameElement);
            String firstXpathOfDebitCodeSelection = "//a[contains(text(),'";
            String secondXpathOfDebitCodeSelection = "')]";
            String debitCodeSelection = null;
            keywords.waitTime1(3);
            keywords.clickOnButton(driver, labourDebitCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "DebitCodeOfLabour") + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            keywords.waitTime1(3);
            keywords.clickOnButton(driver, materialDebitCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "DebitCodeOfMaterial") + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            keywords.waitTime1(3);
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.clickOnButton(driver, travelExpenseDebitCodeSelection);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "DebitCodeOfTravelExpense")
                        + secondXpathOfDebitCodeSelection;
                appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }
            keywords.waitTime1(3);
            keywords.clickOnButton(driver, otherCostsDebitCodeSelection);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "DebitCodeOfOtherCosts")
                    + secondXpathOfDebitCodeSelection;
            driver.findElement(By.xpath(debitCodeSelection)).click();
            keywords.clickOnButton(driver, labourRejectionCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "RejectCodeReasonOfLabour")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.clickOnButton(driver, materialRejectionCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "RejectCodeReasonOfMaterial")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                keywords.clickOnButton(driver, travelExpenseRejectionCodeSelection);
                debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "RejectCodeReasonOfTravelExpense")
                        + secondXpathOfDebitCodeSelection;
                keywords.scrollPage(driver, 500);
                keywords.waitTime1(3);
                driver.findElement(By.xpath(debitCodeSelection)).click();
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.clickOnButton(driver, costRejectionCodeSelection);
            debitCodeSelection = firstXpathOfDebitCodeSelection + appBasedUtils.getStringData(stairs, "RejectCodeReasonOfOtherCost")
                    + secondXpathOfDebitCodeSelection;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            driver.findElement(By.xpath(debitCodeSelection)).click();
            if (appBasedUtils.getStringData(stairs, "ReductionType").equalsIgnoreCase("Amount")) {
                keywords.input(labourPercentElement, appBasedUtils.getStringData(stairs, "MainLabourAmount"), "MainLabourAmount");
                keywords.waitTime1(3);
            } else {
                keywords.input(labourPercentElement, appBasedUtils.getStringData(stairs, "MainLabourPercentage"), "MainLabourPercentage");
                keywords.input(materialPercentElement, appBasedUtils.getStringData(stairs, "MainMaterialPercentage"), "MainMaterialPercentage");
                if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                    keywords.waitTime(500);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
                    keywords.input(travelPercentElement, appBasedUtils.getStringData(stairs, "MainTravelExpensePercentage"), "travelTotalPercentage");
                }
                keywords.waitTime1(3);
                keywords.waitForVisibilityOfElement(driver,8 , othercostPercentElement);
                keywords.input(othercostPercentElement, appBasedUtils.getStringData(stairs, "MainOtherCostPercentage"), "MainOtherCostPercentage");
                keywords.keyTab(othercostPercentElement);
            }
            keywords.clickOnButton(driver, saveButton);
    }

    public void editConnections(HashMap<String, Object> stairs) {
        try {
            keywords.waitTime1(3);
            keywords.waitForVisibilityOfElement(driver, 10, editConnectionsButton);
            keywords.clickOnButton(driver, editConnectionsButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].focus()", excludedCoverageFilterElement);
            keywords.input(excludedCoverageFilterElement, appBasedUtils.getStringData(stairs, "Coverage"), "FiletringTheCoverage");
            keywords.waitTime1(3);
            keywords.clickOnLinkUsingAction(filteredCoverage, driver);
            keywords.clickOnButton(driver, includeCoverageButton);
            keywords.waitTime1(3);
            keywords.clickOnButton(driver, saveConnectionsButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            keywords.clickOnButton(driver, closeButton);
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }

    }

    public void validateStairsdata(HashMap<String, Object> stairs) {
        try {
            keywords.waitTime1(3);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 10, loadingIcon);
            String stairIDvalue = keywords.getTextValue(stairID);
            keywords.APP_LOGS.info("New Stairs is created successfuly with Id: " + stairIDvalue);
            String ConnectedCoverages[] = appBasedUtils.getStringData(stairs, "Coverage").split(";");
            keywords.waitForVisibilityOfElement(driver, 10, ConnectedCoverage);
            keywords.waitTime1(3);
            String[] coveragesMappedToStairs = ((keywords.getTextValue(ConnectedCoverage).trim()).split(", "));
            for (int i = 0; i < ConnectedCoverages.length; i++) {
                Assert.assertEquals(coveragesMappedToStairs[i], coveragesMappedToStairs[i]);
            }
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }

    }
}
