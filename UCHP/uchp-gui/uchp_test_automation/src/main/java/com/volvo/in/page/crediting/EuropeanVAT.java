package com.volvo.in.page.crediting;

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
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.UIUtils;

public class EuropeanVAT {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();
    BaseClass baseClass = new BaseClass();

    public Logger APP_LOGS = Logger.getLogger(EuropeanVAT.class);
    
    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Ok')]")
    WebElement okButton;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsgElement;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "//a[contains(@ng-click,'checkErrorList')]/span")
    WebElement errorIconLink;

    @FindBy(how = How.XPATH, using = "//tbody/tr[contains(@ng-repeat,'d in vm.simpleList')][1]")
    WebElement firstErrorMessageInPopup;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Maintain')]")
    WebElement maintainMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[@class='dropdown-toggle'][contains(text(),'Company')]")
    WebElement companySubMenu;
    
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'European VAT')]")
    WebElement europeanVatMenu;

    @FindBy(how = How.XPATH, using = "//h4[@class='page-title ng-binding']")
    WebElement europeanVatTitle;

    @FindBy(how = How.XPATH, using = "//button[@id='vatAgent']")
    WebElement newEuropeanVatButton;

    @FindBy(how = How.XPATH, using = "//input[@name='vatCode_0']")
    WebElement vatCode1TextBoxElement;
    
    String vatCodeTextBoxElementFirstXpath = "//input[@name='vatCode_";
    String vatCodeTextBoxElementSecondXpath = "']";
    
    @FindBy(how = How.XPATH, using = "//button[@id='saveMaintain']")
    WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='ctrl.addRow()']")
    WebElement addRowButton;
    
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'save the changes now?')]")
    WebElement saveAlertMessage;
    
    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-no btn-default ng-binding']")
    WebElement saveNoButton;
    
    public EuropeanVAT(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*****************************************************
     * #Description : This is a common function to navigate to EuropeanVAT page
     ***************************************************/
    public void navigateToEuropeanVAT() {
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.performSecondLevelMenuAction(driver, maintainMainMenuElement, companySubMenu, europeanVatMenu, europeanVatTitle);
    }

    /*****************************************************
     * #Description : This is a common function to Check if an European VAT already exists, if not create a new one
     ***************************************************/
    public void createNewEuropeanVAT(HashMap<String, Object> europeanVATData) {
        try {
            String vatCodes[] = appBasedUtils.getStringData(europeanVATData, "VATcodes").split(";");
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 5, europeanVatTitle);
            keywords.clickOnButton(driver, newEuropeanVatButton);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            for (int i = 0; i <vatCodes.length ; i++) {
                keywords.input(driver.findElement(By.xpath(vatCodeTextBoxElementFirstXpath + i + vatCodeTextBoxElementSecondXpath)), vatCodes[i], "VATcode");
                if(i != vatCodes.length-1){
                keywords.clickOnButton(driver, addRowButton);
                }
            }
            keywords.clickOnButton(driver, saveButton);
            keywords.waitTime1(2);
            if (appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon)
                    || (keywords.isErrorMessageVisible(driver, 15, errorMsgElement, validationMessageClose))) {
                if (errorMsgElement.getText().equalsIgnoreCase("European VAT for company "+companyNameElement+" already exists.")) {
                    APP_LOGS.info("European VAT for company "+companyNameElement+" already exists.!!"); 
                    keywords.handleModalDailogues(driver);
                    appBasedUtils.waitUntilLoadingIconDisappear(driver, 25, loadingIcon);
                    keywords.waitForVisibilityOfElement(driver, 30, saveAlertMessage);
                    keywords.clickOnButton(driver, saveAlertMessage);
                    keywords.clickOnButton(driver, saveNoButton);
                }
                else
                {
                    Assert.fail("Failed: Could not create new European VAT.");
                }
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

}

