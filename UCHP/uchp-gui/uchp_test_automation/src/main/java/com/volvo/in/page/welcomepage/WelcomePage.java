package com.volvo.in.page.welcomepage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.switchcompany.SwitchToSelectedCompany;

public class WelcomePage {
    public Logger APP_LOGS = Logger.getLogger(WelcomePage.class);
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    SoftAssert softAssert = new SoftAssert();
    AppBasedUtils appBasedUtils = new AppBasedUtils();

    @FindBy(how = How.XPATH, using = "//img[@class='home-welcome-image']")
    WebElement welcomeMessageElement;

    @FindBy(how = How.XPATH, using = "//p[@class='lead home-lead']")
    WebElement aboutUCHPElement;

    @FindBy(how = How.XPATH, using = "//h1[@class='app-title']")
    WebElement UCHPlogoElement;

    @FindBy(how = How.XPATH, using = "//div[@class='lead home-notice']")
    WebElement disclimerElement;

    @FindBy(how = How.XPATH, using = "//div[@class='container-fluid text-center']")
    WebElement copyrightHomePageElement;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement currentCompanyElement;

    @FindBy(how = How.ID, using = "username")
    WebElement userNameElement;

    @FindBy(how = How.ID, using = "password")
    WebElement passwordElement;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    WebElement passwordElementQA;

    public @FindBy(how = How.XPATH, using = "//span[@uib-tooltip='Logout']")
    WebElement logOutElement;

    @FindBy(how = How.XPATH, using = "//a[@title='Sign On']")
    WebElement signOnButtonElement;

    @FindBy(how = How.XPATH, using = "//input[@id='btnLogin']")
    WebElement loginButtonQA;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    @FindBy(how = How.XPATH, using = "//form[@autocomplete='off']")
    WebElement formElement;

    @FindBy(how = How.XPATH, using = "//img[@class='home-welcome-image img-responsive']")
    WebElement welcomePageImg;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Internal')]")
    WebElement loginPageLoadError;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homeButtonElement;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'You made changes on this')]")
    WebElement unsavedAlertText;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='vm.cancel()']")
    WebElement noButtonInUnsavedPopup;

    @FindBy(how = How.XPATH, using =  "//input[@name='user']")
    WebElement userNameElementQA;

    public enum EnumCompanyName {
        VTC, VBC, VCE, NAT, UD, VPC
    }

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void VerifyWelcomePage(HashMap<String, Object> data) {
        verifyWelcomePageElements(UCHPlogoElement, appBasedUtils.getStringData(data, "UCHPLogoText"), "UCHP logo", softAssert);
        verifyCompanyName(softAssert);
        verifyUCHPWelcomeMessage("UCHP welome message", softAssert);
        verifyWelcomePageElements(copyrightHomePageElement, appBasedUtils.getStringData(data, "CopyrightText"), "Copy right information", softAssert);
        verifyWelcomePageElements(aboutUCHPElement, appBasedUtils.getStringData(data, "AboutUCHPText"), "About UCHP message", softAssert);
        verifyWelcomePageElements(disclimerElement, appBasedUtils.getStringData(data, "DisclaimerText"), "Desclaimer message", softAssert);
        softAssert.assertAll();
    }

    public void verifyWelcomePageElements(WebElement welcomePageElement, String expectedUCHPLogoText, String elementName, SoftAssert softAssert) {
        try {
            keywords.APP_LOGS.info("Verifying \"" + elementName + "\" present in welcome page.");
            if (keywords.elementIsDisplayed(welcomePageElement, "Welcome page element")) {
                String logotext = welcomePageElement.getText();
                keywords.APP_LOGS.info("\"" + elementName + "\" Element is present in UCHP page");
                softAssert.assertTrue(expectedUCHPLogoText.equals(logotext));
            } else
                softAssert.assertTrue(false);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void verifyCompanyName(SoftAssert softAssert) {
        try {
            keywords.APP_LOGS.info("Verifying current company name present in welcome page");
            if (keywords.elementIsDisplayed(currentCompanyElement, "Company name element")) {
                keywords.APP_LOGS.info("Current company name is \"" + currentCompanyElement.getText() + "\"");
                softAssert.assertTrue(true);
            } else
                softAssert.assertTrue(false);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void verifyUCHPWelcomeMessage(String elementName, SoftAssert softAssert) {
        try {
            keywords.APP_LOGS.info("Verifying \"" + elementName + "\" in the  UCHP welcome page..");
            softAssert.assertTrue(keywords.elementIsDisplayed(welcomeMessageElement, "Welcome page element"));
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void switchToCompany(String expectedCompanyName, WebDriver driver) {
        try {
            //keywords.waitForPageLoadSuccessfully(driver);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(currentCompanyElement));
            String activeCompany = currentCompanyElement.getText();
            if (!(activeCompany.equalsIgnoreCase(expectedCompanyName))) {
                SwitchToSelectedCompany selectedCompany = new SwitchToSelectedCompany(driver);
                if (expectedCompanyName.equalsIgnoreCase("Volvo Truck Corporation")) {
                    selectedCompany.switchToSelectedCompany(driver, expectedCompanyName, EnumCompanyName.VTC);
                } else if (expectedCompanyName.equalsIgnoreCase("Volvo Bus Corporation")) {
                    selectedCompany.switchToSelectedCompany(driver, expectedCompanyName, EnumCompanyName.VBC);
                } else if (expectedCompanyName.equalsIgnoreCase("Volvo Construction Equipment")) {
                    selectedCompany.switchToSelectedCompany(driver, expectedCompanyName, EnumCompanyName.VCE);
                } else if (expectedCompanyName.equalsIgnoreCase("TSM Americas")) {
                    selectedCompany.switchToSelectedCompany(driver, expectedCompanyName, EnumCompanyName.NAT);
                } else if (expectedCompanyName.equalsIgnoreCase("UD Trucks Corporation")) {
                    selectedCompany.switchToSelectedCompany(driver, expectedCompanyName, EnumCompanyName.UD);
                } else if (expectedCompanyName.equalsIgnoreCase("Volvo Penta Corporation")) {
                    selectedCompany.switchToSelectedCompany(driver, expectedCompanyName, EnumCompanyName.VPC);
                } else {
                    Reporter.log("Invalid company name");
                    Assert.fail("Test execution failed while swtiching company");
                }
            } else
                keywords.APP_LOGS.info("Controls in the expected company " + expectedCompanyName);
            Reporter.log("Switched to company:  " + activeCompany);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void logout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(logOutElement));
        keywords.elementIsDisplayed(logOutElement, "LogOutElement");
        keywords.waitTime(3000);
       // keywords.clickOnButton(logOutElement);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", logOutElement);
        keywords.waitTime1(4);
        if(keywords.elementIsDisplayed(welcomePageImg, "UCHPlogo")){
//            driver.navigate().refresh();
            keywords.clickOnButton(driver, homeButtonElement);
            keywords.waitTime1(3);
            jse.executeScript("arguments[0].click();", logOutElement);
            keywords.waitTime1(2);
        }
        keywords.APP_LOGS.info("Logged out from the application");
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(signOnButtonElement));

        /*
         * if(keywords.elementIsDisplayed(LoginButton, "Login page")) {
         *
         * }
         */
    }

    public void login(String userName, String passWord, WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            keywords.waitForVisibilityOfElement(driver, 30, userNameElement);
            keywords.input(userNameElement, userName, "Username");
            keywords.input(passwordElement, passWord, "Password");
            keywords.keyTab(passwordElement);
            keywords.waitTime(1000);
            keywords.clickOnButton(driver, signOnButtonElement);
            if (keywords.elementIsDisplayed(commonMessageAfterAlert, "Error message")) {
                keywords.APP_LOGS.info("Application loading is failed with error message:" + keywords.getTextValue(commonMessageAfterAlert));
            } else if (keywords.elementIsDisplayed(welcomePageImg, "UCHPlogo")) {
                keywords.APP_LOGS.info("Login is successful");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            if(keywords.waitAndReturnVisibilityOfElement(driver, 10, loginPageLoadError))
                keywords.APP_LOGS.info("Login page loading has failed" +keywords.getTextValue(loginPageLoadError));
        }
    }

    /****************************************
     * #Description : When claim job saves with error message, after capturing the error message, this method is called for close unsaved data just to make sure
     * next operation not getting stuck.
     * @param driver TODO
     ******************************************/
    public void closeUnsavedAlertAndRemoveErrorIcon(WebDriver driver) {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ESCAPE).build().perform();
            keywords.waitForVisibilityOfElement(driver, 20, homeButtonElement);
            keywords.clickOnButton(driver, homeButtonElement);
            driver.switchTo().defaultContent();
            if (keywords.waitAndReturnVisibilityOfElement(driver, 3, unsavedAlertText)) {
                keywords.APP_LOGS.info(keywords.getTextValue(unsavedAlertText));
                keywords.clickOnButton(driver, noButtonInUnsavedPopup);
                keywords.waitForVisibilityOfElement(driver, 15, homeButtonElement);
                keywords.clickOnButton(driver, homeButtonElement);
            }
            if (BaseClass.refreshPageToRemoveErrorIcon) {
                appBasedUtils.reloadApplication(driver);
                driver.switchTo().defaultContent();
                keywords.waitForVisibilityOfElement(driver, 30, homeButtonElement);
                BaseClass.refreshPageToRemoveErrorIcon = false;
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void loginQA(String userName, String passWord) {
        try {
            //keywords.waitForPageLoadSuccessfully(driver);
            driver.switchTo().defaultContent();
            keywords.waitForVisibilityOfElement(driver, 10, userNameElementQA);
            keywords.input(userNameElementQA, userName, "Username");
            keywords.input(passwordElementQA, passWord, "Password");
            keywords.keyTab(passwordElementQA);
            keywords.clickOnButton(driver, loginButtonQA);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            if (keywords.elementIsDisplayed(commonMessageAfterAlert, "Error message")) {
                keywords.APP_LOGS.info("Application loading is failed with error message:" + keywords.getTextValue(commonMessageAfterAlert));
            } else if (keywords.elementIsDisplayed(welcomePageImg, "UCHPlogo")) {
                keywords.APP_LOGS.info("Login is successful to QA");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            if(keywords.waitAndReturnVisibilityOfElement(driver, 10, loginPageLoadError))
                keywords.APP_LOGS.info("Login page loading has failed" +keywords.getTextValue(loginPageLoadError));
        }
    }


}
