package com.volvo.in.page.maintain;

import static org.testng.Assert.assertTrue;

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
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.UIUtils;

public class HoldForCrediting {
    WebDriver driver;
    private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
    private static final String VOLVO_BUS_CORPORATION = "Volvo Bus Corporation";
    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction Equipment";
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert openSelectedClaimJobAssert;
    UIUtils keywords = new UIUtils(driver);
    public WebDriverWait wait = null;
    public Logger APP_LOGS = Logger.getLogger(HoldForCrediting.class);
    public String descriptionText;
    String companyName;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintain;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Hold for crediting ')]")
    WebElement holdForCreditingSubMenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New hold for crediting ')]")
    WebElement newHoldForCrediting;

    @FindBy(how = How.XPATH, using = "//h4[text()='New hold for crediting']")
    WebElement newHoldForCreditingTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='getNewHoldForCrediting']")
    WebElement Continue;

    @FindBy(how = How.XPATH, using = "//select[@id='currency']")
    WebElement Currency;

    @FindBy(how = How.XPATH, using = "//select[@id='state']")
    WebElement Status;

    @FindBy(how = How.XPATH, using = "//div[@id='closeDate']/input")
    WebElement toDate;

    @FindBy(how = How.XPATH, using = "//input[@id='description']")
    WebElement Description;

    @FindBy(how = How.XPATH, using = "//input[@id='importerNo']")
    WebElement importerNo;

    @FindBy(how = How.XPATH, using = "//input[@id='prio']")
    WebElement Priority;

    @FindBy(how = How.XPATH, using = "//span[text()='Causal part no.']/parent::td/preceding-sibling::td/input ")
    WebElement causalPartCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Causal part no. prefix']/parent::td/preceding-sibling::td/input ")
    WebElement causalPartPreCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Chassis number']/parent::td/preceding-sibling::td/input ")
    WebElement chassisNoCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Chassis number series']/parent::td/preceding-sibling::td/input ")
    WebElement chassisNoSeriesCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Mileage(km)']/parent::td/preceding-sibling::td/input ")
    WebElement mileageCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Operating hours']/parent::td/preceding-sibling::td/input ")
    WebElement operatingHoursCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Driveline number']/parent::td/preceding-sibling::td/input ")
    WebElement drivelineNumberCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Driveline number series']/parent::td/preceding-sibling::td/input")
    WebElement drivelineNumberSeriesCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Sales model']/parent::td/preceding-sibling::td/input ")
    WebElement salesModelCheckBox;

    @FindBy(how = How.XPATH, using = "//span[text()='Serial no']/parent::td/preceding-sibling::td/input")
    WebElement serialNoCheckBox;

    @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_CAUSALPARTNO']")
    WebElement causalPart;

    @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_CAUSALPARTNOPREFIX']")
    WebElement causalPartPrefix;

    @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_CHASSISNUMBER']")
    WebElement chassisNo;

    @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_CHASSISNUMBERSERIES']")
    WebElement chassisNoSeries;

    @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_VEHICLEMILEAGE']")
    WebElement Mileage;

    @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_OPERATINGHOURS']")
    WebElement operatingHours;

    @FindBy(how = How.XPATH, using = "//div[@id='closeDate']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement toDateDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//button[@id='saveMaintain']")
    WebElement Save;

    @FindBy(how = How.XPATH, using = "//small[@class='app-desc ng-binding' ]")
    WebElement home;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfConcern']")
    WebElement tocDropDownElement;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Claim job type']/../td[3]")
    WebElement validateClaimJobType;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Causal part no.']/../../td[3]")
    WebElement validateCausalPart;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Chassis number']/../../td[3]")
    WebElement validateChassisNo;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Sales model']/../../td[3]")
    WebElement validateSalesModel;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Serial no']/../../td[3]")
    WebElement validateSerialNo;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Driveline number']/../../td[3]")
    WebElement validateDrivelineNo;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Driveline number series']/../../td[3]")
    WebElement validateDrivelineNoSeries;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Causal part no. prefix']/../../td[3]")
    WebElement validateCausalPartPrefix;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Chassis number series']/../../td[3]")
    WebElement validateChassisNoSeries;

    @FindBy(how = How.XPATH, using = "//*[text()='Mileage(km)']/../../td[3]")
    WebElement validateMileage;

    @FindBy(how = How.XPATH, using = "//*[text()='Operating hours']/../../td[3]")
    WebElement validateOperatingHours;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Currency')]/../../div[2]")
    WebElement validateCurrency;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Status')]/../../div[2]")
    WebElement validateStatus;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Date')]/../../div[4]")
    WebElement validateDate;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Description')]/../../div[2]")
    WebElement validateDescription;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Type of concern')]/../../div[2]")
    WebElement validateTOC;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Importer (owner)')]/../../div[2]")
    WebElement validateImporter;

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Warranty area')]/../../div[2]")
    WebElement validateWarrantyArea;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Description')]")
    WebElement descriptionHeading;

    public HoldForCrediting(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToNewHoldForCrediting() #Author : Aditi #Description : This is a common
     * function to navigate from "Maintain" main menu to "Hold for crediting" sub menu #Date of creation :20-11-2018 #Input Parameters: #Name of person
     * modifying: Aditi #Date of modification: 20-11-2018
     ******************************************/
    public void navigateToNewHoldForCrediting() {
        keywords.waitTime1(6);
        keywords.performSecondLevelMenuAction(driver, maintain, holdForCreditingSubMenu, newHoldForCrediting, newHoldForCreditingTitle);
    }


    /****************************************
     * #Project Name : UCHP - Automation #Function Name : createHoldForCrediting() #Author : Aditi #Description :
     * Adding new hold for crediting for TOC contract #Date of creation :20-11-18
     * #Input Parameters: #Name of person
     * modifying: Aditi #Date of modification: 12-12-18
     ******************************************/
    public void createHoldForCrediting(HashMap<String, Object> holdForCrediting) {
       try {
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return window.stop");
        companyName = keywords.getTextValue(companyNameElement);
        if ((companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION)) ||(companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION))) {
            keywords.clickOnButton(driver, tocDropDownElement);
            keywords.selectVisibleText(tocDropDownElement, appBasedUtils.getStringData(holdForCrediting, "SelectTypeOfConcern"));
            keywords.clickOnButton(driver, Continue);
            keywords.waitTime1(2);
        }
        keywords.clickOnButton(driver, Currency);
        keywords.selectVisibleText(Currency, appBasedUtils.getStringData(holdForCrediting, "Currency"));
        keywords.clickOnButton(driver, Status);
        keywords.selectVisibleText(Status, "Active");
        keywords.clickOnButton(driver, toDateDatePickerIcon);
        keywords.clickOnButton(driver, toDateDatePickerIcon);
        keywords.input(toDate, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Close date");
        keywords.clickOnButton(driver, Status);
        keywords.clickOnButton(driver, toDateDatePickerIcon);
        keywords.clickOnButton(driver, toDateDatePickerIcon);
        keywords.input(Description, appBasedUtils.getStringData(holdForCrediting, "HoldForCreditingReason")+ DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Description");
        keywords.input(importerNo, appBasedUtils.getStringData(holdForCrediting, "Importer"), "Importer (owner)");
        keywords.input(Priority, "1", "Priority");
        String firstXpath = "//label[contains(text(),'";
        String secondXpath = "')]/../input";
        String brand = firstXpath + appBasedUtils.getStringData(holdForCrediting, "VehicleBrand") + secondXpath;
        if (!driver.findElement(By.xpath(brand)).isSelected()) {
            driver.findElement(By.xpath(brand)).click();
        }
       String claimType = firstXpath + appBasedUtils.getStringData(holdForCrediting, "ClaimType") + secondXpath;
       if (!driver.findElement(By.xpath(claimType)).isSelected()) {
            driver.findElement(By.xpath(claimType)).click();
        }
        keywords.setCheckboxIfNotSelected(causalPartCheckBox);
        keywords.setCheckboxIfNotSelected(causalPartPreCheckBox);
        if (companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
            keywords.setCheckboxIfNotSelected(operatingHoursCheckBox);
            keywords.setCheckboxIfNotSelected(salesModelCheckBox);
            keywords.setCheckboxIfNotSelected(serialNoCheckBox);
            keywords.input(operatingHours,appBasedUtils.getStringData(holdForCrediting, "OperatingHours"), "Operating hours");
        }
         else if (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)){
             keywords.setCheckboxIfNotSelected(operatingHoursCheckBox);
             keywords.setCheckboxIfNotSelected(drivelineNumberCheckBox);
             keywords.setCheckboxIfNotSelected(drivelineNumberSeriesCheckBox);
             keywords.input(operatingHours,appBasedUtils.getStringData(holdForCrediting, "OperatingHours"), "Operating hours");
        }
         else{
            keywords.setCheckboxIfNotSelected(chassisNoCheckBox);
            keywords.setCheckboxIfNotSelected(chassisNoSeriesCheckBox);
            keywords.setCheckboxIfNotSelected(mileageCheckBox);
            keywords.input(Mileage,appBasedUtils.getStringData(holdForCrediting, "Mileage"), "Mileage(KM)");
            }
        keywords.input(chassisNo,appBasedUtils.getStringData(holdForCrediting, "ChassisNumber"), "ChassisNumber");
        keywords.input(chassisNoSeries,appBasedUtils.getStringData(holdForCrediting, "ChassisSeries"), "ChassisSeries");
        keywords.input(causalPart, appBasedUtils.getStringData(holdForCrediting, "CausalPartNo"), "CausalPartNo");
        keywords.input(causalPartPrefix,appBasedUtils.getStringData(holdForCrediting, "CausalPartPrefix"), "CausalPartNoPrefix");
        keywords.clickOnButton(driver, Save);
        keywords.waitTime1(5);
        descriptionText =appBasedUtils.getStringData(holdForCrediting, "HoldForCreditingReason")+ DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT);
        }catch (Exception e) {
           APP_LOGS.info(e);
           Assert.fail();
        }


    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : validateHoldForCrediting() #Author : Aditi #Description : Validate saved fields of
     * hold for crediting #Date of creation :20-11-18 #Input Parameters: #Name of person
     * modifying: Aditi #Date of modification: 12-12-18
     ******************************************/
    public void validateHoldForCrediting(HashMap<String, Object> holdForCrediting) {
       try {
           APP_LOGS.info("Validating Hold for crediting");
           assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "Currency"),keywords.getTextValue(validateCurrency)));
           assertTrue(keywords.assertContains("Active",keywords.getTextValue(validateStatus)));
           String Date[]= keywords.getTextValue(validateDate).split("-");
           assertTrue(keywords.assertContains(DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT),Date[1]));
           assertTrue(keywords.assertContains((appBasedUtils.getStringData(holdForCrediting, "HoldForCreditingReason")+DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT)),keywords.getTextValue(validateDescription)));
           assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "SelectTypeOfConcern"),keywords.getTextValue(validateTOC)));
           assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ClaimType"),keywords.getTextValue(validateClaimJobType)));
           assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "CausalPartNo"),keywords.getTextValue(validateCausalPart).trim()));
           assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "CausalPartPrefix"),keywords.getTextValue(validateCausalPartPrefix).trim()));
           if (companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)){
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "OperatingHours"),keywords.getTextValue(validateOperatingHours).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ChassisNumber"),keywords.getTextValue(validateSerialNo).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ChassisSeries"),keywords.getTextValue(validateSalesModel).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "Importer"),keywords.getTextValue(validateWarrantyArea)));
           }
           else if (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION)){
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "OperatingHours"),keywords.getTextValue(validateOperatingHours).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ChassisNumber"),keywords.getTextValue(validateDrivelineNo).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ChassisSeries"),keywords.getTextValue(validateDrivelineNoSeries).trim()));
           }
           else{
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "Mileage"),keywords.getTextValue(validateMileage).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ChassisNumber"),keywords.getTextValue(validateChassisNo).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "ChassisSeries"),keywords.getTextValue(validateChassisNoSeries).trim()));
               assertTrue(keywords.assertContains(appBasedUtils.getStringData(holdForCrediting, "Importer"),keywords.getTextValue(validateImporter)));
               }
       } catch (Exception e) {
           APP_LOGS.info(e);
           Assert.fail();
        }


    }
}