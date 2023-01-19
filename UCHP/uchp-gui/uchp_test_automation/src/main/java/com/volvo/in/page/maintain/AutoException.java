    package com.volvo.in.page.maintain;

    import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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


    public class AutoException {
        private static final String DD_MM_YYYY = "dd/MM/yyyy";
        WebDriver driver;
        AppBasedUtils appBasedUtils = new AppBasedUtils();
        BaseClass baseclass = new BaseClass();
        SoftAssert softAssert;
        UIUtils keywords = new UIUtils(driver);
        public Logger APP_LOGS = Logger.getLogger(AutoException.class);
        public WebDriverWait wait = null;
        public String DescriptionText;
        private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
        private static final String VOLVO_BUS_CORPORATION = "Volvo Bus Corporation";
        private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
        private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction equipment";

        @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
        WebElement maintain;

        @FindBy(how = How.XPATH, using = "//a[contains(text(),'Auto exception ')]")
        WebElement autoExceptionSubMenu;

        @FindBy(how = How.XPATH, using = "//a[contains(text(),'New auto exception ')]")
        WebElement newAutoException;

        @FindBy(how = How.XPATH, using = "//h4[text()='New auto exception']")
        WebElement newAutoExceptionTitle;

        @FindBy(how = How.XPATH, using = "//button[@id='getNewAutoException']")
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
        WebElement chassisNoCheckBoxForVTC;

        @FindBy(how = How.XPATH, using = "//span[text()='Chassis number series']/parent::td/preceding-sibling::td/input ")
        WebElement chassisNoSeriesCheckBoxForVTC;

        @FindBy(how = How.XPATH, using = "//span[text()='Serial no']/parent::td/preceding-sibling::td/input ")
        WebElement chassisNoCheckBoxForVCE;

        @FindBy(how = How.XPATH, using = "//span[text()='Sales model']/parent::td/preceding-sibling::td/input ")
        WebElement chassisNoSeriesCheckBoxForVCE;

        @FindBy(how = How.XPATH, using = "//span[text()='Driveline number']/parent::td/preceding-sibling::td/input ")
        WebElement chassisNoCheckBoxForVPC;

        @FindBy(how = How.XPATH, using = "//span[text()='Driveline number series']/parent::td/preceding-sibling::td/input ")
        WebElement chassisNoSeriesCheckBoxForVPC;

        @FindBy(how = How.XPATH, using = "//span[text()='Mileage(km)']/parent::td/preceding-sibling::td/input ")
        WebElement mileageCheckBox;

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

        @FindBy(how = How.XPATH, using = "//*[text() = 'Serial no']/../../td[3]")
        WebElement validateChassisNoForVCE;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Chassis number']/../../td[3]")
        WebElement validateChassisNoForVTC;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Chassis number series']/../../td[3]")
        WebElement validateChassisSeriesForVTC;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Sales model']/../../td[3]")
        WebElement validateChassisSeriesForVCE;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Driveline number']/../../td[3]")
        WebElement validateChassisNoForVPC;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Driveline number series']/../../td[3]")
        WebElement validateChassisSeriesForVPC;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Causal part no. prefix']/../../td[3]")
        WebElement validateCausalPartPrefix;

        @FindBy(how = How.XPATH, using = "//*[text() = 'Chassis number series']/../../td[3]")
        WebElement validateChassisNoSeriesForVTC;

        @FindBy(how = How.XPATH, using = "//*[text()='Mileage(km)']/../../td[3]")
        WebElement validateMileage;

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

        @FindBy(how = How.XPATH, using = "//div[div[label[@for='importerNo']]]/div[2]")
        WebElement validateImporter;

        @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
        WebElement companyNameElement;

        @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
        WebElement loadingIcon;

        @FindBy(how = How.XPATH, using = "//tr[td[span[contains(text(),'ne age')]]]//input")
        WebElement machinAge;

        @FindBy(how = How.XPATH, using = "//textarea[@id='includeRule_VEHICLEAGE']")
        WebElement machineAgeInputField;

        @FindBy(how = How.XPATH, using = "//tr[td[span[contains(text(),'ne age')]]]/td[3]")
        WebElement machineAgeLabel;



        public AutoException(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
        /****************************************
         * #Project Name : UCHP - Automation #Function Name : navigateToNewHoldForCrediting() #Author : Aditi #Description : This is a common
         * function to navigate from "Maintain" main menu to "Hold for crediting" sub menu #Date of creation :20-11-2018 #Input Parameters: #Name of person
         * modifying: Aditi #Date of modification: 20-11-2018
         ******************************************/
        public void navigateToAutoException() {
            keywords.waitTime1(6);
            keywords.performSecondLevelMenuAction(driver, maintain, autoExceptionSubMenu, newAutoException, newAutoExceptionTitle);
        }

        /****************************************
         * #Project Name : UCHP - Automation #Function Name : createAutoException() #Author : Aditi #Description :
         * Adding new auto exception for TOC contract #Date of creation :20-11-18
         * #Input Parameters: #Name of person
         * modifying: Aditi #Date of modification: 03-12-18
         ******************************************/
        public void createAutoException(HashMap<String, Object> autoException) {
            String companyName;
            keywords.waitTime1(4);
            companyName = keywords.getTextValue(companyNameElement);
            if ((companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION)) || (companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION))) {
            keywords.clickOnButton(driver, tocDropDownElement);
            keywords.selectVisibleText(tocDropDownElement, appBasedUtils.getStringData(autoException, "SelectTypeOfConcern"));
            keywords.clickOnButton(driver, Continue);
            keywords.waitTime1(2);
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.clickOnButton(driver, Currency);
            keywords.selectVisibleText(Currency, appBasedUtils.getStringData(autoException, "Currency"));
            keywords.clickOnButton(driver, Status);
            keywords.selectVisibleText(Status, "Active");
            keywords.clickOnButton(driver, toDateDatePickerIcon);
            keywords.clickOnButton(driver, toDateDatePickerIcon);
            keywords.input(toDate, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "Close date");
            keywords.input(Description, appBasedUtils.getStringData(autoException, "AutoExceptionReason"), "Description");
            keywords.input(importerNo, appBasedUtils.getStringData(autoException, "Importer"), "Importer (owner)");
            keywords.input(Priority, "1", "Priority");
            String firstXpath = "//label[contains(text(),'";
            String secondXpath = "')]/../input";
            String brand = firstXpath + appBasedUtils.getStringData(autoException, "VehicleBrand") + secondXpath;
            if (!driver.findElement(By.xpath(brand)).isSelected()) {
                driver.findElement(By.xpath(brand)).click();
            }
           String claimType = firstXpath + appBasedUtils.getStringData(autoException, "ClaimType") + secondXpath;
           if (!driver.findElement(By.xpath(claimType)).isSelected()) {
                driver.findElement(By.xpath(claimType)).click();
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.setCheckboxIfNotSelected(causalPartCheckBox);
            keywords.setCheckboxIfNotSelected(causalPartPreCheckBox);
            keywords.scrollPage(driver, 550);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.input(causalPart, appBasedUtils.getStringData(autoException, "CausalPartNo"), "CausalPartNo");
            keywords.input(causalPartPrefix,appBasedUtils.getStringData(autoException, "CausalPartPrefix"), "CausalPartNoPrefix");
            keywords.scrollPage(driver, 400);
            if((keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT))||(keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_PENTA_CORPORATION))){
                keywords.scrollPage(driver, -200);
                keywords.setCheckboxIfNotSelected(machinAge);
                keywords.input(machineAgeInputField,appBasedUtils.getStringData(autoException, "MachineAge"), "Machine Age");

            }else{
            keywords.setCheckboxIfNotSelected(mileageCheckBox);
            keywords.input(Mileage,appBasedUtils.getStringData(autoException, "Mileage"), "Mileage(KM)");
            }
            keywords.scrollPage(driver, 800);
            if (keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)){
            keywords.setCheckboxIfNotSelected(chassisNoCheckBoxForVCE);
            keywords.setCheckboxIfNotSelected(chassisNoSeriesCheckBoxForVCE);
            }
            else if(keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_PENTA_CORPORATION)){
                keywords.scrollPage(driver, -800);
                keywords.setCheckboxIfNotSelected(chassisNoCheckBoxForVPC);
                keywords.setCheckboxIfNotSelected(chassisNoSeriesCheckBoxForVPC);
            }else{
                keywords.scrollPage(driver, -1400);
                keywords.waitTime1(2);
                keywords.setCheckboxIfNotSelected(chassisNoCheckBoxForVTC);
                keywords.setCheckboxIfNotSelected(chassisNoSeriesCheckBoxForVTC);
            }
            keywords.input(chassisNo,appBasedUtils.getStringData(autoException, "ChassisNumber"), "ChassisNumber");
            keywords.input(chassisNoSeries,appBasedUtils.getStringData(autoException, "ChassisSeries"), "ChassisSeries");
            keywords.clickOnButton(driver, Save);
            keywords.waitTime1(5);
         
       }

        /****************************************
         * #Project Name : UCHP - Automation #Function Name : validateAutoException() #Author : Aditi #Description : Validate saved fields of
         * auto exception #Date of creation :20-11-18 #Input Parameters: #Name of person
         * modifying: Aditi #Date of modification: 20-11-18
         ******************************************/
        public void validateAutoException(HashMap<String, Object> autoException) {
           try {
            APP_LOGS.info("Validating Auto exception");
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "Currency"), keywords.getTextValue(validateCurrency)));
            assertTrue(keywords.assertContains("Active", keywords.getTextValue(validateStatus)));
            String Date[] = keywords.getTextValue(validateDate).split("-");
            assertTrue(keywords.assertContains(DateUtility.getCurrentDate(DD_MM_YYYY), Date[1]));
            assertTrue(keywords.assertContains((appBasedUtils.getStringData(autoException, "AutoExceptionReason")),
                                               keywords.getTextValue(validateDescription)));
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "SelectTypeOfConcern"), keywords.getTextValue(validateTOC)));
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "Importer"), keywords.getTextValue(validateImporter)));
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ClaimType"), keywords.getTextValue(validateClaimJobType)));
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "CausalPartNo"), keywords.getTextValue(validateCausalPart).trim()));
            assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "CausalPartPrefix"),
                                               keywords.getTextValue(validateCausalPartPrefix).trim()));
            if (keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) {
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ChassisNumber"),
                                                   keywords.getTextValue(validateChassisNoForVCE).trim()));
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ChassisSeries"),
                                                   keywords.getTextValue(validateChassisSeriesForVCE).trim()));
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "MachineAge"),
                                                   keywords.getTextValue(machineAgeLabel).trim()));
            } else if (keywords.getTextValue(companyNameElement).equalsIgnoreCase(VOLVO_PENTA_CORPORATION)) {
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ChassisNumber"),
                                                   keywords.getTextValue(validateChassisNoForVPC).trim()));
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ChassisSeries"),
                                                   keywords.getTextValue(validateChassisSeriesForVPC).trim()));
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "MachineAge"),
                                                   keywords.getTextValue(machineAgeLabel).trim()));
            } else {
                keywords.scrollPage(driver, 400);
                keywords.waitTime1(2);
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ChassisNumber"),
                                                   keywords.getTextValue(validateChassisNoForVTC).trim()));
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "ChassisSeries"),
                                                   keywords.getTextValue(validateChassisSeriesForVTC).trim()));
                assertTrue(keywords.assertContains(appBasedUtils.getStringData(autoException, "Mileage"), keywords.getTextValue(validateMileage).trim()));
            }
        } catch (Exception e) {
            APP_LOGS.info(e);
            Assert.fail();
        }
        }
    }