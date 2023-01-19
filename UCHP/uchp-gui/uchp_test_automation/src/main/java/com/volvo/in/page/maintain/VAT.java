package com.volvo.in.page.maintain;

import java.util.HashMap;
import org.apache.log4j.Logger;
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

public class VAT {
    WebDriver driver;
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    BaseClass baseclass = new BaseClass();
    SoftAssert softAssert;
    UIUtils keywords = new UIUtils(driver);
    public Logger APP_LOGS = Logger.getLogger(VAT.class);
    String companyName;
    private static final String VOLVO_TRUCK_CORPORATION = "Volvo Truck Corporation";
    private static final String VOLVO_BUS_CORPORATION = "Volvo Bus Corporation";

    @FindBy(how = How.XPATH, using = "//*[@id='navbar-main']//a[text()='Maintain ']")
    WebElement maintain;

    @FindBy(how = How.XPATH, using = "//*[@class='nav navbar-nav']//a[text()='VAT ']")
    WebElement vatSubMenu;

    @FindBy(how = How.XPATH, using = "//h4[text()='List VAT']")
    WebElement listVATTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='company dark-blue-background ng-binding']")
    WebElement companyNameElement;

    @FindBy(how = How.XPATH, using = "//button[@id='newVat']")
    WebElement NewVAT;

    @FindBy(how = How.XPATH, using = "//select[@id='typeOfConcern']")
    WebElement TOC;

    @FindBy(how = How.XPATH, using = "//input[@id='importerNo']")
    WebElement importer;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerCheckbox']")
    WebElement dealerCheck;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerNo']")
    WebElement dealer;

    @FindBy(how = How.XPATH, using = "//*[text()='Continue']")
    WebElement continueBtn;

    @FindBy(how = How.XPATH, using = "//input[@id='vatLabourCode1']")
    WebElement labourCode;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterialCode1']")
    WebElement materialCode;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterialExchangePartCode1']")
    WebElement exchangeMaterialCode;

    @FindBy(how = How.XPATH, using = "//input[@id='vatOtherCostCode1']")
    WebElement otherCostCode;

    @FindBy(how = How.XPATH, using = "//input[@id='vatZeroPercentCode1']")
    WebElement VATCode;

    @FindBy(how = How.XPATH, using = "//input[@id='vatLabour1']")
    WebElement labourVAT;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterial1']")
    WebElement materialVAT;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterialExchangePart1']")
    WebElement exchangeMaterialVAT;

    @FindBy(how = How.XPATH, using = "//input[@id='vatOtherCost1']")
    WebElement otherCostVAT;

    @FindBy(how = How.XPATH, using = "//input[@id='vatQst1']")
    WebElement QST;

    @FindBy(how = How.XPATH, using = "//uchp-loader/descendant::span[contains(text(),'Loading')]")
    WebElement loadingIcon;

    @FindBy(how = How.XPATH, using = "//input[@id='vatLabourCode']")
    WebElement labourCodeImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterialCode']")
    WebElement materialCodeImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterialExchangePartCode']")
    WebElement exchangeMaterialCodeImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatOtherCostCode']")
    WebElement otherCostCodeImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatZeroPercentCode']")
    WebElement VATCodeImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatLabour']")
    WebElement labourVATImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterial']")
    WebElement materialVATImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatMaterialExchangePart']")
    WebElement exchangeMaterialVATImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatOtherCost']")
    WebElement otherCostVATImp;

    @FindBy(how = How.XPATH, using = "//input[@id='vatQst']")
    WebElement QSTImp;

    @FindBy(how = How.XPATH, using = "//button[@id='saveVatFormDetails']")
    WebElement save;

    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-times']")
    WebElement close;

    @FindBy(how = How.XPATH, using = "//input[@id='dealerCheckbox']")
    WebElement dealerCheckbox;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement Search;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Warranty')]")
    WebElement warranty;

    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-trash']")
    WebElement deleteIcon;

    @FindBy(how = How.XPATH, using = "//button[@id='deleteVatAllDealer']")
    WebElement delete;

    @FindBy(how = How.XPATH, using = "//div[@class='toast-message']")
    WebElement message;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-xs btn-yes btn-success ng-binding']")
    WebElement yes;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-block confirm-button ng-binding btn-danger']")
    WebElement yesPopup;

    public VAT(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : navigateToNewHoldForCrediting() #Author : Aditi #Description : This is a common function to navigate
     * from "Maintain" main menu to "Hold for crediting" sub menu #Date of creation :20-11-2018 #Input Parameters: #Name of person modifying: Aditi #Date of
     * modification: 20-11-2018
     ******************************************/
    public void navigateToVAT() {
        keywords.waitTime1(6);
        keywords.performMenuAction(driver, maintain, vatSubMenu, listVATTitle);
    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : createNewVat() #Author : Aditi #Description : Adding new VAT for TOC warranty #Date of creation
     * :05-08-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification: 05-08-19
     ******************************************/
    public void createNewVat(HashMap<String, Object> data) {
            keywords.waitTime1(4);
            companyName = keywords.getTextValue(companyNameElement);
            keywords.clickOnButton(driver, NewVAT);
            if (companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION) || companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION)) {
                keywords.selectVisibleText(TOC, appBasedUtils.getStringData(data, "TOC"));
            }
            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, continueBtn);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            keywords.input(labourCodeImp, appBasedUtils.getStringData(data, "Importer Labour code"), "Importer Labour code");
            keywords.input(materialCodeImp, appBasedUtils.getStringData(data, "Importer Material code"), "Importer Material code");
            keywords.input(exchangeMaterialCodeImp, appBasedUtils.getStringData(data, "Importer Exchange material code"), "Importer Exchange material code");
            keywords.input(otherCostCodeImp, appBasedUtils.getStringData(data, "Importer Other cost code"), "Importer Other cost code");
            keywords.input(VATCodeImp, appBasedUtils.getStringData(data, "Importer VAT code"), "Importer VAT code");

            keywords.input(labourVATImp, appBasedUtils.getStringData(data, "Importer Labour VAT"), "Labour VAT");
            keywords.input(materialVATImp, appBasedUtils.getStringData(data, "Importer Material VAT"), "Material VAT");
            keywords.input(exchangeMaterialVATImp, appBasedUtils.getStringData(data, "Importer Exchange material VAT"), "Importer Exchange material VAT");
            keywords.input(otherCostVATImp, appBasedUtils.getStringData(data, "Importer Other cost VAT"), "Importer Other cost VAT");
            keywords.input(QSTImp, appBasedUtils.getStringData(data, "Importer QST"), "Importer QST VAT");
            keywords.clickOnButton(driver, save);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, close);

            keywords.clickOnButton(driver, NewVAT);
            if (companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION) || companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION)) {
                keywords.selectVisibleText(TOC, appBasedUtils.getStringData(data, "TOC"));
            }
            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, dealerCheck);
            keywords.input(dealer, appBasedUtils.getStringData(data, "Dealer"), "Dealer");
            keywords.clickOnButton(driver, continueBtn);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.input(labourCode, appBasedUtils.getStringData(data, "Dealer Labour code"), "Dealer Labour code");
            keywords.input(materialCode, appBasedUtils.getStringData(data, "Dealer Material code"), "Dealer Material code");
            keywords.input(exchangeMaterialCode, appBasedUtils.getStringData(data, "Dealer Exchange material code"), "Dealer Exchange material code");
            keywords.input(otherCostCode, appBasedUtils.getStringData(data, "Dealer Other cost code"), "Dealer Other cost code");
            keywords.input(VATCode, appBasedUtils.getStringData(data, "Dealer VAT code"), "Dealer VAT code");

            keywords.input(labourVAT, appBasedUtils.getStringData(data, "Dealer Labour VAT"), "Dealer Labour VAT");
            keywords.input(materialVAT, appBasedUtils.getStringData(data, "Dealer Material VAT"), "Dealer Material VAT");
            keywords.input(exchangeMaterialVAT, appBasedUtils.getStringData(data, "Dealer Exchange material VAT"), "Dealer Exchange material VAT");
            keywords.input(otherCostVAT, appBasedUtils.getStringData(data, "Dealer Other cost VAT"), "Dealer Other cost VAT");
            keywords.input(QST, appBasedUtils.getStringData(data, "Dealer QST"), "Dealer QST VAT");
            keywords.clickOnButton(driver, save);
            keywords.clickOnButton(driver, close);


    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : updateExisitingVat() #Author : Aditi #Description : Updating existing VAT for TOC warranty #Date of
     * creation :05-08-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification: 05-08-19
     ******************************************/
    public void updateExisitingVat(HashMap<String, Object> data) {
            companyName = keywords.getTextValue(companyNameElement);
            keywords.waitTime1(4);
            if (companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION) || companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION)) {
                keywords.selectVisibleText(TOC, appBasedUtils.getStringData(data, "TOC"));
            }
            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, dealerCheckbox);
            keywords.clickOnButton(driver, Search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(warranty, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            keywords.input(labourCodeImp, appBasedUtils.getStringData(data, "Importer Labour code"), "Importer Labour code");
            keywords.input(materialCodeImp, appBasedUtils.getStringData(data, "Importer Material code"), "Importer Material code");
            keywords.input(exchangeMaterialCodeImp, appBasedUtils.getStringData(data, "Importer Exchange material code"), "Importer Exchange material code");
            keywords.input(otherCostCodeImp, appBasedUtils.getStringData(data, "Importer Other cost code"), "Importer Other cost code");
            keywords.input(VATCodeImp, appBasedUtils.getStringData(data, "Importer VAT code"), "Importer VAT code");

            keywords.input(labourVATImp, appBasedUtils.getStringData(data, "Importer Labour VAT"), "Labour VAT");
            keywords.input(materialVATImp, appBasedUtils.getStringData(data, "Importer Material VAT"), "Material VAT");
            keywords.input(exchangeMaterialVATImp, appBasedUtils.getStringData(data, "Importer Exchange material VAT"), "Importer Exchange material VAT");
            keywords.input(otherCostVATImp, appBasedUtils.getStringData(data, "Importer Other cost VAT"), "Importer Other cost VAT");
            keywords.input(QSTImp, appBasedUtils.getStringData(data, "Importer QST"), "Importer QST VAT");
            keywords.clickOnButton(driver, save);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, close);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, dealerCheckbox);
            keywords.input(dealer, appBasedUtils.getStringData(data, "Dealer"), "Dealer");
            keywords.clickOnButton(driver, Search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(warranty, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            keywords.input(labourCode, appBasedUtils.getStringData(data, "Dealer Labour code"), "Dealer Labour code");
            keywords.input(materialCode, appBasedUtils.getStringData(data, "Dealer Material code"), "Dealer Material code");
            keywords.input(exchangeMaterialCode, appBasedUtils.getStringData(data, "Dealer Exchange material code"), "Dealer Exchange material code");
            keywords.input(otherCostCode, appBasedUtils.getStringData(data, "Dealer Other cost code"), "Dealer Other cost code");
            keywords.input(VATCode, appBasedUtils.getStringData(data, "Dealer VAT code"), "Dealer VAT code");

            keywords.input(labourVAT, appBasedUtils.getStringData(data, "Dealer Labour VAT"), "Dealer Labour VAT");
            keywords.input(materialVAT, appBasedUtils.getStringData(data, "Dealer Material VAT"), "Dealer Material VAT");
            keywords.input(exchangeMaterialVAT, appBasedUtils.getStringData(data, "Dealer Exchange material VAT"), "Dealer Exchange material VAT");
            keywords.input(otherCostVAT, appBasedUtils.getStringData(data, "Dealer Other cost VAT"), "Dealer Other cost VAT");
            keywords.input(QST, appBasedUtils.getStringData(data, "Dealer QST"), "Dealer QST VAT");
            keywords.clickOnButton(driver, save);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnButton(driver, close);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            if (companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION) || companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION)) {
                companyName = keywords.getTextValue(companyNameElement);
                keywords.selectVisibleText(TOC, appBasedUtils.getStringData(data, "TOC"));
            }
            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, dealerCheckbox);
            keywords.clickOnButton(driver, Search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(warranty, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            Assert.assertEquals(keywords.getAttributeValue(labourCodeImp, "value"), appBasedUtils.getStringData(data, "Importer Labour code"));
            Assert.assertEquals(keywords.getAttributeValue(materialCodeImp, "value"), appBasedUtils.getStringData(data, "Importer Material code"),
                                "Importer Material code");
            Assert.assertEquals(keywords.getAttributeValue(exchangeMaterialCodeImp, "value"),
                                appBasedUtils.getStringData(data, "Importer Exchange material code"), "Importer Exchange material code");
            Assert.assertEquals(keywords.getAttributeValue(otherCostCodeImp, "value"), appBasedUtils.getStringData(data, "Importer Other cost code"),
                                "Importer Other cost code");
            Assert.assertEquals(keywords.getAttributeValue(VATCodeImp, "value"), appBasedUtils.getStringData(data, "Importer VAT code"), "Importer VAT code");

            Assert.assertEquals(keywords.getAttributeValue(labourVATImp, "value"), appBasedUtils.getStringData(data, "Importer Labour VAT"), "Labour VAT");
            Assert.assertEquals(keywords.getAttributeValue(materialVATImp, "value"), appBasedUtils.getStringData(data, "Importer Material VAT"),
                                "Material VAT");
            Assert.assertEquals(keywords.getAttributeValue(exchangeMaterialVATImp, "value"),
                                appBasedUtils.getStringData(data, "Importer Exchange material VAT"), "Importer Exchange material VAT");
            Assert.assertEquals(keywords.getAttributeValue(otherCostVATImp, "value"), appBasedUtils.getStringData(data, "Importer Other cost VAT"),
                                "Importer Other cost VAT");
            Assert.assertEquals(keywords.getAttributeValue(QSTImp, "value"), appBasedUtils.getStringData(data, "Importer QST"), "Importer QST VAT");

            keywords.clickOnButton(driver, close);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, dealerCheckbox);
            keywords.input(dealer, appBasedUtils.getStringData(data, "Dealer"), "Dealer");
            keywords.clickOnButton(driver, Search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(warranty, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);

            Assert.assertEquals(keywords.getAttributeValue(labourCode, "value"), appBasedUtils.getStringData(data, "Dealer Labour code"), "Dealer Labour code");
            Assert.assertEquals(keywords.getAttributeValue(materialCode, "value"), appBasedUtils.getStringData(data, "Dealer Material code"),
                                "Dealer Material code");
            Assert.assertEquals(keywords.getAttributeValue(exchangeMaterialCode, "value"), appBasedUtils.getStringData(data, "Dealer Exchange material code"),
                                "Dealer Exchange material code");
            Assert.assertEquals(keywords.getAttributeValue(otherCostCode, "value"), appBasedUtils.getStringData(data, "Dealer Other cost code"),
                                "Dealer Other cost code");
            Assert.assertEquals(keywords.getAttributeValue(VATCode, "value"), appBasedUtils.getStringData(data, "Dealer VAT code"), "Dealer VAT code");

            Assert.assertEquals(keywords.getAttributeValue(labourVAT, "value"), appBasedUtils.getStringData(data, "Dealer Labour VAT"), "Dealer Labour VAT");
            Assert.assertEquals(keywords.getAttributeValue(materialVAT, "value"), appBasedUtils.getStringData(data, "Dealer Material VAT"),
                                "Dealer Material VAT");
            Assert.assertEquals(keywords.getAttributeValue(exchangeMaterialVAT, "value"), appBasedUtils.getStringData(data, "Dealer Exchange material VAT"),
                                "Dealer Exchange material VAT");
            Assert.assertEquals(keywords.getAttributeValue(otherCostVAT, "value"), appBasedUtils.getStringData(data, "Dealer Other cost VAT"),
                                "Dealer Other cost VAT");
            Assert.assertEquals(keywords.getAttributeValue(QST, "value"), appBasedUtils.getStringData(data, "Dealer QST"), "Dealer QST VAT");
            keywords.clickOnButton(driver, save);
            keywords.clickOnButton(driver, close);

    }

    /****************************************
     * #Project Name : UCHP - Automation #Function Name : updateExisitingVat() #Author : Aditi #Description : Updating existing VAT for TOC warranty #Date of
     * creation :05-08-19 #Input Parameters: #Name of person modifying: Aditi #Date of modification: 05-08-19
     ******************************************/
    public void deleteExisitingVat(HashMap<String, Object> data) {
            String ActualnoItemMessageImp;
            String ActualnoItemMessageDealer;
            String noItemMessageImp = "199999\nNo items to display.\nYour search did not provide any matches.";
            companyName = keywords.getTextValue(companyNameElement);
            keywords.waitTime1(4);
            if (companyName.equalsIgnoreCase(VOLVO_TRUCK_CORPORATION) || companyName.equalsIgnoreCase(VOLVO_BUS_CORPORATION)) {
                companyName = keywords.getTextValue(companyNameElement);
                keywords.selectVisibleText(TOC, appBasedUtils.getStringData(data, "TOC"));
            }
            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.clickOnButton(driver, dealerCheckbox);
            keywords.clickOnButton(driver, Search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            keywords.clickOnLink(warranty, driver);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            keywords.clickOnButton(driver, delete);
            keywords.clickOnButton(driver, yes);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            ActualnoItemMessageImp = keywords.getTextValue(message);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            navigateToVAT();
            keywords.input(importer, appBasedUtils.getStringData(data, "Importer"), "Importer");
            keywords.input(dealer, appBasedUtils.getStringData(data, "Dealer"), "Dealer");
            keywords.clickOnButton(driver, Search);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 3, loadingIcon);
            keywords.clickOnButton(driver, deleteIcon);
            keywords.clickOnButton(driver, yesPopup);
            appBasedUtils.waitUntilLoadingIconDisappear(driver, 2, loadingIcon);
            ActualnoItemMessageDealer = keywords.getTextValue(message);
            if (ActualnoItemMessageImp.equals(noItemMessageImp)) {
                APP_LOGS.info("Importer VAT deleted");
            } else {
                Assert.fail(ActualnoItemMessageImp);
            }
            if (ActualnoItemMessageDealer.equals(noItemMessageImp)) {
                APP_LOGS.info("Dealer VAT deleted");
            } else {
                Assert.fail(ActualnoItemMessageDealer);
            }
    }
}
