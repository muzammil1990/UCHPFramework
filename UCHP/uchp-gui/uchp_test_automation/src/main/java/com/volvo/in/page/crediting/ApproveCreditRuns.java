package com.volvo.in.page.crediting;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExtentTestManager;
import com.volvo.in.commonlibrary.UIUtils;

public class ApproveCreditRuns {
    WebDriver driver;
    UIUtils keywords = new UIUtils();
    BaseClass baseclass = new BaseClass();
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    SoftAssert softAssert = new SoftAssert();

    @FindBy(how = How.XPATH, using = "//input[@id='repairingImporter']")
    WebElement importerTextBoxElement;

    @FindBy(how = How.ID, using = "repairing_Importer")
    WebElement importerDropDownElement;

    @FindBy(how = How.ID, using = "repairing_Dealer")
    WebElement dealerDropDownElement;

    @FindBy(how = How.XPATH, using = "//input[@id='repairingDealer']")
    WebElement dealerTextBoxElement;

    @FindBy(how = How.XPATH, using = "//select[@id='droplistMenuBrand']")
    WebElement brandElement;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Create document')]")
    WebElement createDocumentBtnElement;

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Crediting ']")
    WebElement creditingMainMenuElement;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Approve credit runs')]")
    WebElement approveCreditRunsSubMenuElement;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Approve credit runs')]")
    WebElement approveCreditRunsTitleElement;

    @FindBy(how = How.XPATH, using = "//div[@ng-click='tapToast()']/div[@on='allowHtml']/*[contains(., 'Internal Server Error')]")
    WebElement internalServerError;

    @FindBy(how = How.XPATH, using = "//div[@class='toast uchp-toast uchp-toast-error']")
    WebElement captureErrorMsg;

    @FindBy(how = How.XPATH, using = "//a[@href='app']")
    WebElement homePageLink;
    
    @FindBy(how = How.XPATH, using = "//b[text()='No items to display']")
    WebElement searchResultNoItemToDisplay;
    
    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindAll({ @FindBy(xpath = "//div[contains(@ng-repeat,'rowContainer.renderedRows')]/div/div[10]")})
    List<WebElement> OccIDListElements;

    @FindBy(how = How.XPATH, using = "//button[@id='save_approve-credit-runs']")
    WebElement saveBtnElement;

    String occidCheckBoxFirstXpath = "//div[contains(text(),'";
    String occidCheckBoxSecondXpath = "')]//preceding::div[2]//following-sibling::div[4]/div/input";

    public ApproveCreditRuns(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void navigateToApproveCreditRuns(WebDriver driver) {
        keywords.waitForVisibilityOfElement(driver, 30, homePageLink);
        keywords.clickOnLink(homePageLink, driver);
        keywords.waitForVisibilityOfElement(driver, 8, creditingMainMenuElement);
    }

    public void approveCreditRun() {
        try {
            String Occid = CreateOrModifyCrediting.occasionId;
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 5, loadingIcon);
            keywords.performMenuAction(driver, creditingMainMenuElement, approveCreditRunsSubMenuElement, approveCreditRunsTitleElement);
            if ((keywords.elementIsDisplayed(searchResultNoItemToDisplay, "No items to display"))) {
                keywords.APP_LOGS.info("There is no credit run with occastion id:"+Occid+" exists"  );
                ExtentTestManager.getTest().log(Status.INFO, "There is no credit run with occastion id:"+Occid+" exists");
                Assert.fail();
            } else {
            for (int i = 0; i < OccIDListElements.size(); i++) {
                if (OccIDListElements.get(i).getText().equalsIgnoreCase(Occid)) {
                    keywords.waitTime1(2);
                    WebElement checkboxXpath = driver.findElement(By.xpath(occidCheckBoxFirstXpath + Occid + occidCheckBoxSecondXpath));
                    keywords.setCheckboxIfNotSelected(checkboxXpath);
                    keywords.clickOnButton(driver, saveBtnElement);
                }
            }
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
