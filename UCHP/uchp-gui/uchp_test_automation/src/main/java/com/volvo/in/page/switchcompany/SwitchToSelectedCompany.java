package com.volvo.in.page.switchcompany;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.UIUtils;
import com.volvo.in.page.welcomepage.WelcomePage;

public class SwitchToSelectedCompany {

    WebDriver driver;
    AppBasedUtils app = new AppBasedUtils();
    UIUtils keywords = new UIUtils(driver);

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Switch company ')]")
    WebElement switchCompany;

    @FindBy(how = How.XPATH, using = "//span[text()='Volvo Bus Corporation']")
    WebElement VBC;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyName;

    @FindBy(how = How.XPATH, using = "//span[text()='Volvo Construction Equipment']")
    WebElement VCE;

    @FindBy(how = How.XPATH, using = "//span[text()='TSM Americas']")
    WebElement NAT;

    @FindBy(how = How.XPATH, using = "//span[text()='UD Trucks Corporation']")
    WebElement UD;

    @FindBy(how = How.LINK_TEXT, using = "Switch company")
    WebElement linkSwitchCompany;

    @FindBy(how = How.XPATH, using = "//span[text()='Volvo Penta Corporation']")
    WebElement VPC;

    @FindBy(how = How.XPATH, using = "//span[text()='Volvo Truck Corporation']")
    WebElement VTC;

    @FindBy(how = How.XPATH, using = "//input[@id='optionsRadios_2']")
    WebElement VBC_RadioButton;

    @FindBy(how = How.XPATH, using = "//input[@id='optionsRadios_1']")
    WebElement VTC_RadioButton;

    @FindBy(how = How.XPATH, using = "//input[@id='optionsRadios_4']")
    WebElement NAT_RadioButton;

    @FindBy(how = How.XPATH, using = "//input[@id='optionsRadios_3']")
    WebElement VCE_RadioButton;

    @FindBy(how = How.XPATH, using = "//input[@id='optionsRadios_5']")
    WebElement UD_RadioButton;

    @FindBy(how = How.XPATH, using = "//input[@id='optionsRadios_6']")
    WebElement VPC_RadioButton;

    @FindBy(how = How.XPATH, using = "//div[@ng-click='tapToast()']/div[@on='allowHtml']/*[contains(., 'referred company')]")
    WebElement preferredCompanyInfo;

    public SwitchToSelectedCompany(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    /***********************************************
     * #Description : This method is switch to particular company #Input Parameters: 1. driver: Driver instance 2. expectedCompanyName: Company to be switched
     * 3. enumcompanyName: Company
     ************************************************/
    public void switchToSelectedCompany(WebDriver driver, String expectedCompanyName, WelcomePage.EnumCompanyName enumcompanyName) {
        switch (enumcompanyName) {
        case VTC:
            keywords.performMenuAction(driver, switchCompany, VTC, companyName);
            closeParentWindow(expectedCompanyName);
            keywords.performMenuAction(driver, switchCompany, VTC_RadioButton, preferredCompanyInfo);
            break;

        case VBC:
            keywords.performMenuAction(driver, switchCompany, VBC, companyName);
            closeParentWindow(expectedCompanyName);
            keywords.performMenuAction(driver, switchCompany, VBC_RadioButton, preferredCompanyInfo);
            break;

        case VCE:
            keywords.performMenuAction(driver, switchCompany, VCE, companyName);
            closeParentWindow(expectedCompanyName);
            keywords.performMenuAction(driver, switchCompany, VCE_RadioButton, preferredCompanyInfo);
            break;

        case NAT:
            keywords.performMenuAction(driver, switchCompany, NAT, companyName);
            closeParentWindow(expectedCompanyName);
            keywords.performMenuAction(driver, switchCompany, NAT_RadioButton, preferredCompanyInfo);
            break;

        case UD:
            keywords.performMenuAction(driver, switchCompany, UD, companyName);
            closeParentWindow(expectedCompanyName);
            keywords.performMenuAction(driver, switchCompany, UD_RadioButton, preferredCompanyInfo);
            break;

        case VPC:
            keywords.performMenuAction(driver, switchCompany, VPC, companyName);
            closeParentWindow(expectedCompanyName);
            keywords.performMenuAction(driver, switchCompany, VPC_RadioButton, preferredCompanyInfo);
            break;
        }
    }

    /***********************************************
     * #Description : This method is to close the old window when, user switched to another company. #Input Parameters: Company name
     ************************************************/
    public void closeParentWindow(String expectedCompanyName) {
        try {
            BaseClass baseclass = new BaseClass();
            keywords.waitTime1(3);
            Set<String> windowids = driver.getWindowHandles();
            if (windowids.size() > 1) {
                Object[] arrayWindowHandles = windowids.toArray();
                String mainWindowId = arrayWindowHandles[0].toString();
                String ChildWindowId = arrayWindowHandles[1].toString();
                driver.switchTo().window(ChildWindowId);
                WebDriverWait wait1 = new WebDriverWait(driver, 20);
                wait1.until(ExpectedConditions.visibilityOf(companyName));
                String actCompany = companyName.getText();
                if (actCompany.equalsIgnoreCase(expectedCompanyName)) {
                    driver.switchTo().window(mainWindowId);
                    wait1.until(ExpectedConditions.visibilityOf(companyName));
                    keywords.waitTime1(2);
                    driver.close();
                    keywords.waitTime1(2);
                    driver.switchTo().window(ChildWindowId);
                    if (baseclass.getBrowserType().equalsIgnoreCase("IE") || baseclass.getBrowserType().equalsIgnoreCase("Internet Explorer")) {
                        driver.manage().window().maximize();
                    }
                } else {
                    driver.switchTo().window(mainWindowId);
                    if (actCompany.equalsIgnoreCase(expectedCompanyName)) {
                        driver.switchTo().window(ChildWindowId);
                        wait1.until(ExpectedConditions.visibilityOf(companyName));
                        keywords.waitTime1(2);
                        driver.close();
                        keywords.waitTime1(2);
                        driver.switchTo().window(mainWindowId);
                        if (baseclass.getBrowserType().equalsIgnoreCase("IE") || baseclass.getBrowserType().equalsIgnoreCase("Internet Explorer")) {
                            driver.manage().window().maximize();
                        }
                    } else {
                        keywords.APP_LOGS.info("Error in switching window");
                    }
                }
            }
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }
    }

    /***********************************************
     * #Description : This method is switch to particular company #Input parameters: 1. expectedComapany: Company to be switched 2. softAssert: Soft assert
     * instance
     ************************************************/
    public void switchToCompanyAndVerify(String expectedComapany, SoftAssert softAssert) {
        WelcomePage welcome = new WelcomePage(driver);
        welcome.switchToCompany(expectedComapany, driver);
        expectedComapany = expectedComapany.toUpperCase();
        String actualCompany = companyName.getText().toUpperCase();
        app.asserVerification(expectedComapany, actualCompany, softAssert);
    }

    /***********************************************
     * #Description : sets preferred company #Input parameters: 1. expectedComapany: Company to be switched 2. appUserName: application user 3. appPassword:
     * application password 4. softAssert: Softassert instance
     ************************************************/
    public void setPreferredCompanys(String expectedComapany, String appUserName, String appPassword, SoftAssert softAssert) {
        WelcomePage welcome = new WelcomePage(driver);
        WebDriverWait wait1 = new WebDriverWait(driver, 12);
        if (expectedComapany.equalsIgnoreCase("Volvo Bus Corporation")) {
            keywords.performMenuAction(driver, switchCompany, VBC_RadioButton, preferredCompanyInfo);
        } else if (expectedComapany.equalsIgnoreCase("Volvo Truck Corporation")) {
            keywords.performMenuAction(driver, switchCompany, VTC_RadioButton, preferredCompanyInfo);
        } else if (expectedComapany.equalsIgnoreCase("Volvo Construction Equipment")) {
            keywords.performMenuAction(driver, switchCompany, VCE_RadioButton, preferredCompanyInfo);
        } else if (expectedComapany.equalsIgnoreCase("TSM Americas")) {
            keywords.performMenuAction(driver, switchCompany, NAT_RadioButton, preferredCompanyInfo);
        } else if (expectedComapany.equalsIgnoreCase("UD Trucks Corporation")) {
            keywords.performMenuAction(driver, switchCompany, UD_RadioButton, preferredCompanyInfo);
        } else if (expectedComapany.equalsIgnoreCase("Volvo Penta Corporation")) {
            keywords.performMenuAction(driver, switchCompany, VPC_RadioButton, preferredCompanyInfo);
        }
        keywords.waitTime1(3);
        keywords.APP_LOGS.info("Preferred company is set to " + companyName.getText());
        welcome.logOutElement.click();
        welcome.login(appUserName, appPassword, driver);
        expectedComapany = expectedComapany.toUpperCase();
        wait1.until(ExpectedConditions.visibilityOf(companyName));
        String actualCompany = companyName.getText().toUpperCase();
        app.asserVerification(expectedComapany, actualCompany, softAssert);
    }

    public void VerifyPreferredCompanys(String expectedComapany, SoftAssert softAssert) {
        expectedComapany = expectedComapany.toUpperCase();
        Boolean isRadioButtonChecked = null;
        if (expectedComapany.equalsIgnoreCase("Volvo Bus Corporation")) {
            isRadioButtonChecked = keywords.navigateToMenuCheckIsElementSelected(driver, switchCompany, VBC_RadioButton);
        } else if (expectedComapany.equalsIgnoreCase("Volvo Truck Corporation")) {
            isRadioButtonChecked = keywords.navigateToMenuCheckIsElementSelected(driver, switchCompany, VTC_RadioButton);
        } else if (expectedComapany.equalsIgnoreCase("Volvo Construction Equipment")) {
            isRadioButtonChecked = keywords.navigateToMenuCheckIsElementSelected(driver, switchCompany, VCE_RadioButton);
        } else if (expectedComapany.equalsIgnoreCase("TSM Americas")) {
            isRadioButtonChecked = keywords.navigateToMenuCheckIsElementSelected(driver, switchCompany, NAT_RadioButton);
        } else if (expectedComapany.equalsIgnoreCase("UD Trucks Corporation")) {
            isRadioButtonChecked = keywords.navigateToMenuCheckIsElementSelected(driver, switchCompany, UD_RadioButton);
        } else if (expectedComapany.equalsIgnoreCase("Volvo Penta Corporation")) {
            isRadioButtonChecked = keywords.navigateToMenuCheckIsElementSelected(driver, switchCompany, VPC_RadioButton);
        }
        if (isRadioButtonChecked) {
            keywords.APP_LOGS.info("Preferred Company has been set is " + expectedComapany);
        }
        softAssert.assertTrue(isRadioButtonChecked, "Preferred company has been set is not matching");
    }

}
