package com.volvo.in.page.crediting;

import java.util.HashMap;

import org.apache.log4j.Logger;
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

public class ResendDistribution {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();
    BaseClass baseClass = new BaseClass();

    public Logger APP_LOGS = Logger.getLogger(ResendDistribution.class);
    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Claim ']")
    WebElement claim;

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

    @FindBy(how = How.XPATH, using = "(//a[contains(., 'System admin')])[1]")
    WebElement systemAdminMainMenuElement;

    @FindBy(how = How.XPATH, using = "(//a[text()='Resend distribution '])[1]")
    WebElement resendDistributionSubMenu;

    @FindBy(how = How.XPATH, using = "//list-resend-distribution/descendant::h4[contains(., 'Resend distribution')]")
    WebElement resendDistributionTitle;

    @FindBy(how = How.ID, using = "messageType")
    WebElement distTypeDropDownElement;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-row ng-scope'][1]/descendant::button")
    WebElement firstRowResendButton;

    @FindBy(how = How.XPATH, using = "//div[@class='popover ng-scope right popover-right'][1]/descendant::button[contains(text(),'YES')]")
    WebElement yesButton;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-row ng-scope'][1]/div/div[2]/div")
    WebElement firstRowOccID;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-row ng-scope'][1]/div/div[4]/div")
    WebElement firstRowResendtime;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs history-back ng-scope']")
    WebElement closeButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-contents-wrapper']")
    WebElement gridSearchResult;

    @FindBy(how = How.NAME, using = "creditOccId")
    WebElement creditOccId;

    public ResendDistribution(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*****************************************************
     * #Description : This is a common function to navigate to ResendDistribution page
     ***************************************************/
    public void navigateToResendDistribution() {
        keywords.waitTime1(2);
        keywords.waitForVisibilityOfElement(driver, 10, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        keywords.waitTime1(2);
        keywords.performMenuAction(driver, systemAdminMainMenuElement, resendDistributionSubMenu, resendDistributionTitle);
        keywords.waitTime1(2);
    }

    /*****************************************************
     * #Description : This is a common function to select distribution type from dropdown
     ***************************************************/
    public void selectDistributionType(HashMap<String, Object> systemAdminData) {
        try {
            keywords.waitTime1(2);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.waitForVisibilityOfElement(driver, 5, distTypeDropDownElement);
            keywords.selectByIndex(distTypeDropDownElement, appBasedUtils.getStringData(systemAdminData, "IndexValue"));
            keywords.waitTime1(2);
            clickOnSearchButton();
            if (appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon)
                    || (keywords.isErrorMessageVisible(driver, 15, errorMsgElement, validationMessageClose))) {
                Assert.fail("Failed: Your search did not provide any matches");
            }
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }

    /*****************************************************
     * #Description : This is a common function to click on search button
     ***************************************************/
    public void clickOnSearchButton() {
        keywords.waitTime1(2);
        appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
        keywords.clickOnButton(driver, searchButton);
        keywords.waitTime1(2);
    }

    /*****************************************************
     * #Description : This is a common function to select distribution type from dropdown
     ***************************************************/
    public void performAndValidateResendOperation(HashMap<String, Object> systemAdminData) {
        try {
            keywords.waitTime1(2);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            keywords.clickOnButton(driver, firstRowResendButton);
            keywords.handleModalDailogues(driver);
            keywords.clickOnButton(driver, yesButton);
            if (appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon)
                    || (keywords.isErrorMessageVisible(driver, 15, errorMsgElement, validationMessageClose))) {
                ExtentTestManager.getTest().log(Status.ERROR,
                                   "Could not resend the distribution with Credit occassion id:  " + keywords.getTextValue(firstRowResendtime));
                Assert.fail("Failed: Could not resend the distribution");
            }
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 8, loadingIcon);
            String resendDateTime = keywords.getTextValue(firstRowResendtime);
            String currentDate = DateUtility.getCETTimeFromCurrentIST(DateUtility.getCurrentDateAndTime());
            APP_LOGS.info("Validating Resent time is matching with current system time in CET");
            APP_LOGS.info("Current system time is: " + currentDate + "  " + "Resent time is: " + resendDateTime);
            Assert.assertEquals(currentDate, resendDateTime);
        } catch (Exception e) {
            APP_LOGS.error(e);
            Assert.fail();
        }
    }
}
