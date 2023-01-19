package com.volvo.in.page.validation;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.DateUtility;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.claim.ManageClaimJobs;
import com.volvo.in.page.vehicle.ViewCoverage;

public class NewScenario {
    public Logger APP_LOGS = Logger.getLogger(ViewCoverage.class);
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();
    BaseClass baseclass = new BaseClass();

    private static final String VOLVO_PENTA_CORPORATION = "Volvo Penta Corporation";
    private static final String VOLVO_CONSTRUCTION_EQUIPMENT = "Volvo Construction equipment";
    String companyName;

    @FindBy(how = How.XPATH, using = "(//a[@class='dropdown-toggle'])[5]")
    WebElement validationMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New check scenario')]")
    WebElement newCheckScenarioSubMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'List check scenario')]")
    WebElement listCheckScenarioSubMenuElement;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement searchButtonElement;

    public String searchResultFirstPart = "//a[contains(@ng-click,'openSelectedScenario') and text()='";
    public String searchResultSecondPart = "']";

    @FindBy(how = How.XPATH, using = "//h4[@class='page-title ng-binding']")
    WebElement newCheckScenarioHeaderElement;

    @FindBy(how = How.ID, using = "name")
    WebElement nameElement;

    @FindBy(how = How.XPATH, using = "//textarea[@id='description']")
    WebElement descriptionElement;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfConcern']")
    WebElement typeOfConcernDropdownElement;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfClaim']")
    WebElement typeOfClaimDropdownElement;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfUpdate']")
    WebElement typeOfUpdateDropdownElement;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfCheck']")
    WebElement typeOfCheckDropdownElement;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Warranty')]")
    WebElement typeOfConcernTextElement;

    @FindBy(how = How.XPATH, using = "//select[@id='orgLevel']")
    WebElement orgLevelDropdownElement;

    @FindBy(how = How.XPATH, using = "//select[@id='brand']")
    WebElement brandDropdownElement;

    @FindBy(how = How.XPATH, using = "//input[@id='callingSystem']")
    WebElement callingSystemElement;

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @ng-model='ctrl.toggle.callingSystem']")
    WebElement callingSystemAllCheckBoxElement;

    @FindBy(how = How.XPATH, using = "//input[@id='callingSystemSituationNo']")
    WebElement callingSystemSituationNoElement;

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @ng-model='ctrl.toggle.callingSystemSituationNo']")
    WebElement callingSystemSituationNoAllCheckBoxElement;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input")
    WebElement repairDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement repairDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']/input")
    WebElement repairDateTo;

    @FindBy(how = How.XPATH, using = "//div[@id='repairDateTo']/input/../descendant::span[@class='glyphicon glyphicon-calendar']")
    WebElement repairDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input")
    WebElement registrationDateFrom;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateFrom']/input/../descendant::span[@class='glyphicon-calendar glyphicon']")
    WebElement registrationDateFromDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input")
    WebElement registrationDateTo;

    @FindBy(how = How.XPATH, using = "//div[@id='registrationDateTo']/input/../descendant::span[@class='glyphicon glyphicon-calendar']")
    WebElement registrationDateToDatePickerIcon;

    @FindBy(how = How.XPATH, using = "//input[@id='regionNo']")
    WebElement regionNoElement;

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and contains(@ng-change,'regionNo')]")
    WebElement regionNoAllCheckBoxElement;

    @FindBy(how = How.XPATH, using = "//input[@id='importerNo']")
    WebElement importerElement;

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and contains(@ng-change,'importer')]")
    WebElement importerAllCheckBoxElement;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerNo']")
    WebElement dealerElement;

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and contains(@ng-change,'dealer')]")
    WebElement dealerAllCheckBoxElement;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs btn-success ng-binding ng-scope']")
    WebElement saveButtonElement;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'View checks')]")
    WebElement viewChecksButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@class='dualmultiselect']//div[1]//input[1]")
    WebElement allChecksFilterInputElement;

    @FindBy(how = How.XPATH, using = "//select[@id='items_selected']/option[1]")
    WebElement FirstFilteredCheckElement;

    @FindBy(how = How.XPATH, using = "//span[@class='glyphicon glyphicon-chevron-right']")
    WebElement moveCheckToRightElement;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'View checks')]")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    WebElement searchedScenario;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Delete']")
    WebElement deleteButtonElement;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-content']")
    WebElement deleteScenarioAlertMessage;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs btn-yes btn-success ng-binding']")
    WebElement deleteScenarioYesButton;

    @FindBy(how = How.XPATH, using = "//b[text()='No items to display']")
    WebElement searchResultNoItemToDisplay;

    public NewScenario(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /*******************************************
     * #Description : Method to navigate from Validation -> List check scenario
     *******************************************/
    public void navigateToListCheckScenario() {
        try {
            keywords.waitTime(5);
            keywords.waitForVisibilityOfElement(driver, 20, validationMainMenuElement);
            keywords.performMenuAction(driver, validationMainMenuElement, listCheckScenarioSubMenuElement, newCheckScenarioHeaderElement);
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /*******************************************
     * #Description : Method to navigate from Validation -> New check scenario
     *******************************************/
    public void navigateToNewCheckScenario() {
        try {
            keywords.waitTime(5);
            keywords.waitForVisibilityOfElement(driver, 20, validationMainMenuElement);
            keywords.performMenuAction(driver, validationMainMenuElement, newCheckScenarioSubMenuElement, newCheckScenarioHeaderElement);
        } catch (Exception e) {
            keywords.APP_LOGS.error("failed!", e);
        }
    }

    /****************************************
     * ; #Description : Method to create a new scenario
     ****************************************/
    public void createNewCheckScenario(HashMap<String, Object> data) {
        try {
            keywords.waitTime1(4);
            keywords.input(nameElement, appBasedUtils.getStringData(data, "Name"), "Name");
            keywords.input(descriptionElement, appBasedUtils.getStringData(data, "Description"), "Description");
            keywords.selectVisibleText(typeOfConcernDropdownElement, appBasedUtils.getStringData(data, "TypeOfConcern"));
            keywords.selectVisibleText(typeOfClaimDropdownElement, appBasedUtils.getStringData(data, "TypeOfClaim"));
            keywords.selectVisibleText(typeOfUpdateDropdownElement, appBasedUtils.getStringData(data, "TypeOfUpdate"));
            keywords.selectVisibleText(typeOfCheckDropdownElement, appBasedUtils.getStringData(data, "TypeOfCheck"));
            keywords.selectVisibleText(orgLevelDropdownElement, appBasedUtils.getStringData(data, "OrganizationLevel"));
            keywords.selectVisibleText(brandDropdownElement, appBasedUtils.getStringData(data, "Brand"));
            if ((appBasedUtils.getStringData(data, "CallingSystem")).equalsIgnoreCase("All")) {
                keywords.setCheckboxIfNotSelected(callingSystemAllCheckBoxElement);
            } else {
                keywords.input(callingSystemElement, appBasedUtils.getStringData(data, "CallingSystem"), "Calling System");
            }
            keywords.waitTime1(2);
            if ((appBasedUtils.getStringData(data, "CallingSystemSituationNo")).equalsIgnoreCase("All")) {
                keywords.setCheckboxIfNotSelected(callingSystemAllCheckBoxElement);
            } else {
                keywords.input(callingSystemSituationNoElement, appBasedUtils.getStringData(data, "CallingSystemSituationNo"), "Calling system situation no.");
            }
            // Repair date picker
            int numberOfPreviousDays = 1;
            keywords.waitForVisibilityOfElement(driver, 10, repairDateFrom);
            keywords.input(repairDateFrom, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT), "RepairDateFrom");
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.clickOnButton(driver, repairDateFromDatePickerIcon);
            keywords.input(repairDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "RepairDateTo");
            keywords.clickOnButton(driver, repairDateToDatePickerIcon);
            keywords.clickOnButton(driver, repairDateToDatePickerIcon);
            // Registartion date picker
            keywords.input(registrationDateFrom, DateUtility.getPreviousDateFromCurrentDate(numberOfPreviousDays, DateUtility.DATE_ONLY_FORMAT),
                           "registrationDateFrom");
            keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
            keywords.clickOnButton(driver, registrationDateFromDatePickerIcon);
            keywords.input(registrationDateTo, DateUtility.getCurrentDate(DateUtility.DATE_ONLY_FORMAT), "registrationDateTo");
            keywords.clickOnButton(driver, registrationDateToDatePickerIcon);
            keywords.waitTime1(2);
            if ((appBasedUtils.getStringData(data, "RegionNo")).equalsIgnoreCase("All")) {
                keywords.setCheckboxIfNotSelected(regionNoAllCheckBoxElement);
            } else {
                keywords.input(regionNoElement, appBasedUtils.getStringData(data, "RegionNo"), "Region no.");
            }
            if ((appBasedUtils.getStringData(data, "Importer")).equalsIgnoreCase("All")) {
                keywords.setCheckboxIfNotSelected(importerAllCheckBoxElement);
            } else {
                keywords.input(importerElement, appBasedUtils.getStringData(data, "Importer"), "Importer");
            }
            if ((appBasedUtils.getStringData(data, "Dealer")).equalsIgnoreCase("All")) {
                keywords.setCheckboxIfNotSelected(dealerAllCheckBoxElement);
            } else {
                keywords.input(dealerElement, appBasedUtils.getStringData(data, "Dealer"), "Dealer");
            }
            keywords.clickOnButton(driver, saveButtonElement);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail();
        }
    }

    /****************************************
     * ; #Description : Adds the checks to the scenario
     ****************************************/
    public void selectChecks(HashMap<String, Object> data) {
        keywords.clickOnButton(driver, viewChecksButtonElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        keywords.input(allChecksFilterInputElement, appBasedUtils.getStringData(data, "CheksToBeAdded"), "Checks");
        keywords.clickOnLink(FirstFilteredCheckElement, driver);
        keywords.clickOnButton(driver, moveCheckToRightElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
        keywords.clickOnButton(driver, saveButtonElement);
        ExtentTestManager.getTest().log(Status.INFO, "Created the Scenario with the name " + appBasedUtils.getStringData(data, "Name") + " successfully");
    }

    /****************************************
     * ; #Description : Search the scenario with name and if already present, delete the same.
     ****************************************/
    public void searchScenarioAndDelete(HashMap<String, Object> data) {
        keywords.input(nameElement, appBasedUtils.getStringData(data, "Name"), "Name");
        keywords.clickOnButton(driver, searchButtonElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        if ((keywords.elementIsDisplayed(searchResultNoItemToDisplay, "No items to display"))) {
            keywords.APP_LOGS.info("The Scenario with the name " + appBasedUtils.getStringData(data, "Name") + " does not exists");
            ExtentTestManager.getTest().log(Status.INFO, "The Scenario with the name " + appBasedUtils.getStringData(data, "Name") + " does not exists");
            ExtentTestManager.getTest().log(Status.INFO, "Creating a new scenario with name " + appBasedUtils.getStringData(data, "Name"));
        } else {
            searchedScenario = driver.findElement(By.xpath(searchResultFirstPart + appBasedUtils.getStringData(data, "Name") + searchResultSecondPart));
            softAssert = new SoftAssert();
            try {
                ExtentTestManager.getTest().log(Status.INFO, "The Scenario with the name " + appBasedUtils.getStringData(data, "Name") + " already exists");
                ExtentTestManager.getTest().log(Status.INFO,
                                                "Deleting the Scenario with the name " + appBasedUtils.getStringData(data, "Name") + " and creating new one");
                if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                    keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 6, searchedScenario, errorMsg, validationMessageClose);
                    keywords.clickOnLink(searchedScenario, driver);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
                    keywords.clickOnLink(deleteButtonElement, driver);
                    keywords.handleModalDailogues(driver);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 25, loadingIcon);
                    keywords.waitForVisibilityOfElement(driver, 30, deleteScenarioAlertMessage);
                    keywords.waitTime1(4);
                    keywords.clickOnButton(driver, deleteScenarioAlertMessage);
                    keywords.clickOnButton(driver, deleteScenarioYesButton);
                }
            } catch (Exception e) {
                APP_LOGS.error(e.getMessage());
            }
        }

    }

    /****************************************
     * ; #Description : Search the scenario with name.
     ****************************************/
    public void searchScenarioAndValidate(HashMap<String, Object> data) {
        keywords.waitTime1(2);
        keywords.input(nameElement, ManageClaimJobs.scenarioValue, "Name");
        keywords.clickOnButton(driver, searchButtonElement);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
        if ((keywords.elementIsDisplayed(searchResultNoItemToDisplay, "No items to display"))) {
            keywords.APP_LOGS.info("The Scenario with the name does not exists");
            ExtentTestManager.getTest().log(Status.INFO, "The Scenario with the name does not exists");

        } else {
            searchedScenario = driver.findElement(By.xpath(searchResultFirstPart + appBasedUtils.getStringData(data, "scenarioValue")
                    + searchResultSecondPart));
            softAssert = new SoftAssert();
            try {
                ExtentTestManager.getTest().log(Status.INFO,
                                                "The Scenario with the name " + appBasedUtils.getStringData(data, "scenarioValue") + " already exists");

                if ((!appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon))) {
                    keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 6, searchedScenario, errorMsg, validationMessageClose);
                    keywords.clickOnLink(searchedScenario, driver);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
                }
                if ((companyName.equalsIgnoreCase(VOLVO_CONSTRUCTION_EQUIPMENT)) || (companyName.equalsIgnoreCase(VOLVO_PENTA_CORPORATION))) {
                    Assert.assertEquals(keywords.getTextValue(typeOfConcernTextElement), appBasedUtils.getStringData(data, "SelectTypeOfConcern"));
                } else {
                    Assert.assertEquals(keywords.getFirstSelectedItemFromDropDown(typeOfConcernDropdownElement),
                                        appBasedUtils.getStringData(data, "SelectTypeOfConcern"));
                }
                Assert.assertEquals(keywords.getFirstSelectedItemFromDropDown(typeOfClaimDropdownElement), appBasedUtils.getStringData(data, "ClaimType"));
            } catch (Exception e) {
                APP_LOGS.error(e.getMessage());
            }
        }
    }
}
