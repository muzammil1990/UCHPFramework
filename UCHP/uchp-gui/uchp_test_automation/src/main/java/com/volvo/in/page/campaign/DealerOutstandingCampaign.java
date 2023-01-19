package com.volvo.in.page.campaign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.AppBasedUtils;
import com.volvo.in.commonlibrary.UIUtils;

public class DealerOutstandingCampaign {
    WebDriver driver;
    UIUtils keywords = new UIUtils(driver);
    AppBasedUtils appBasedUtils = new AppBasedUtils();
    List<String> arrayList = new ArrayList<String>();
    public Logger APP_LOGS = Logger.getLogger(DealerOutstandingCampaign.class);

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Campaign')]")
    WebElement campaignMainMenu;

    @FindBy(how = How.XPATH, using = "(//a[contains(text(),'Dealer campaign information')])[1]")
    WebElement dealerCampaignInformagtionSubMenu;

    @FindBy(how = How.XPATH, using = "(//li/a[contains(text(),'Outstanding campaigns')])[1]")
    WebElement outstandingCampaignsSubMenu;

    @FindBy(how = How.XPATH, using = "//dealer-outstanding-campaigns/descendant::span[contains(text(),'Dealer outstanding campaigns')]")
    WebElement dealerOutstandingCampaignsPageTitle;

    @FindBy(how = How.ID, using = "importer_No")
    WebElement warrantyAreaDropdownBox;

    @FindBy(how = How.ID, using = "dealerNo")
    WebElement dealer;

    @FindBy(how = How.XPATH, using = "//select[@id='dealer']")
    WebElement dealerDropdown;

    @FindBy(how = How.ID, using = "DealerCriteria")
    WebElement dealerCriteriaDropdown;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Search')]")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//dealer-outstanding-campaigns/descendant::span[contains(text(),'Dealer outstanding machines')]")
    WebElement dealerOutstandingMachinesPageTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='ui-grid-cell-contents sortable-header']/descendant::span[contains(text(),'Campaign')]")
    WebElement gridColumnName;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement errorMsg;

    @FindBy(how = How.XPATH, using = "//button[@class='toast-close-button ng-scope']")
    WebElement validationMessageClose;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2 col-lg-2'])[2]/../descendant::span[1]")
    WebElement campaignID;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2 col-lg-2'])[4]/../descendant::span[1]")
    WebElement campaignTitle;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2 col-lg-2'])[6]/../descendant::span[1]")
    WebElement campaignClosingDate;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2 col-lg-2'])[2]/../descendant::span[2]")
    WebElement warrantyAreaValueOutStandingMachine;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2 col-lg-2'])[4]/../descendant::span[2]")
    WebElement dealerValueOutStandingMachine;

    @FindBy(how = How.XPATH, using = "(//div[@class='col-md-2 col-lg-2'])[6]/../descendant::span[2]")
    WebElement dealerCriteriaValueOutStandingMachine;

    @FindBy(how = How.XPATH, using = "(//div[@class='ui-grid-pager-count-container']/descendant::span)[2]")
    WebElement noOfMachinesInDealerOutstandingMachinesPage;

    @FindBy(how = How.XPATH, using = "(//div[@class='ng-scope'])[1]/div")
    WebElement commonMessageAfterAlert;

    String searchResultColumnheader[] = { "Campaign", "Title", "ClosingDate", "Machenes", "Machine outstanding" };

    public static String XpathFirstPart = "//div[div[div[@class='ui-grid-cell-contents ng-binding ng-scope']]]/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000";
    String alertTextMessage = new String();
    List<String> campaignInformation = new ArrayList<String>();

    public String searchResultFirstItemXpath = new String();

    public DealerOutstandingCampaign(WebDriver driver) {
        this.driver = driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void navigateToDealerOutstandingCampaignInformation() {
        keywords.performSecondLevelMenuAction(driver, campaignMainMenu, dealerCampaignInformagtionSubMenu,
                                              outstandingCampaignsSubMenu, dealerOutstandingCampaignsPageTitle);
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOf(dealerOutstandingCampaignsPageTitle));
    }

    /**
     * #Project Name : UCHP - Automation #Function Name : searchDealerOutstandingCampaign() #Author : Raju #Description : Searches Responsible dealer
     * outstanding campaigns #Date of creation : 21-08-2017 #Input Parameters: #Name of person modifying: Raju #Date of modification: 21-08-2017
     */
    public void searchResponsibleDealerOutstandingCampaign(HashMap<String, Object> data) {
        SoftAssert sa = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 4);
//        wait.until(ExpectedConditions.visibilityOf(warrantyAreaDropdownBox));
//        keywords.selectVisibleText(warrantyAreaDropdownBox, appBasedUtils.getStringData(data, "WarrantyArea"));
        wait.until(ExpectedConditions.visibilityOf(dealer));
        keywords.input(dealer, data.get("Dealer"), "Dealer");
        keywords.selectVisibleText(dealerCriteriaDropdown, appBasedUtils.getStringData(data, "DealerCriteria"));
        keywords.clickOnButton(driver, searchButton);
        gridValidation(searchResultColumnheader, "05 06 07 08 09", sa);
        openAndValidateCampaignInformation(data, sa);
        campaignInformation.clear();
        sa.assertAll();
    }

    /**
     * #Project Name : UCHP - Automation #Function Name : searchDealerOutstandingCampaign() #Author : Raju #Description : Searches Responsible dealer
     * outstanding campaigns #Date of creation : 21-08-2017 #Input Parameters: #Name of person modifying: Raju #Date of modification: 21-08-2017
     */
    public void searchSellingDealerOutstandingCampaign(HashMap<String, Object> data) {
        SoftAssert sa = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 35);
//        wait.until(ExpectedConditions.visibilityOf(warrantyAreaDropdownBox));
//        keywords.selectVisibleText(warrantyAreaDropdownBox, appBasedUtils.getStringData(data, "WarrantyArea"));
        keywords.waitTime1(3);
        wait.until(ExpectedConditions.visibilityOf(dealerDropdown));
        keywords.selectVisibleText(dealerDropdown, appBasedUtils.getStringData(data, "Dealer"));
        keywords.selectVisibleText(dealerCriteriaDropdown, appBasedUtils.getStringData(data, "DealerCriteria"));
        keywords.clickOnButton(driver, searchButton);
        gridValidation(searchResultColumnheader, "05 06 07 08 09", sa);
        openAndValidateCampaignInformation(data, sa);
        campaignInformation.clear();
        sa.assertAll();
    }

    /**
     * #Project Name : UCHP - Automation #Function Name : searchDealerOutstandingCampaign() #Author : Raju #Description : Searches Responsible dealer
     * outstanding campaigns #Date of creation : 21-08-2017 #Input Parameters: #Name of person modifying: Raju #Date of modification: 21-08-2017
     */
    public void searchServicingDealerOutstandingCampaign(HashMap<String, Object> data) {
        SoftAssert sa = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 35);
//        wait.until(ExpectedConditions.visibilityOf(warrantyAreaDropdownBox));
//        keywords.selectVisibleText(warrantyAreaDropdownBox, appBasedUtils.getStringData(data, "WarrantyArea"));
        keywords.waitTime1(2);
        wait.until(ExpectedConditions.visibilityOf(dealer));
        keywords.input(dealer, appBasedUtils.getStringData(data, "Dealer"), "Dealer");
        wait.until(ExpectedConditions.visibilityOf(dealerCriteriaDropdown));
        keywords.selectVisibleText(dealerCriteriaDropdown, appBasedUtils.getStringData(data, "DealerCriteria"));
        keywords.clickOnButton(driver, searchButton);
        gridValidation(searchResultColumnheader, "05 06 07 08 09", sa);
        openAndValidateCampaignInformation(data, sa);
        campaignInformation.clear();
        sa.assertAll();
    }

    /**
     * #Project Name : UCHP - Automation #Function Name : gridValidation() #Author : Raju #Description : To verify the result in the grid #Date of creation :
     * 18-08-2017 #Input Parameters: #Name of person modifying: Raju #Date of modification: 18-08-2017
     */
    public void gridValidation(String columnheader[], String noOfColumnsToValidate, SoftAssert sa) {
        alertTextMessage = keywords.getTextElementIsDisplayed(commonMessageAfterAlert);
        keywords.waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(driver, 6, gridColumnName, errorMsg, validationMessageClose);
        int columnIndexInSearchGrid = 0;
        try {
            keywords.waitForVisibilityOfElement(driver, 6, gridColumnName);
            for (String hexaStrToXpath : noOfColumnsToValidate.split("\\s")) {
                keywords.APP_LOGS.info("Displaying \"" + columnheader[columnIndexInSearchGrid] + "\" Column values...");
                displayGridColumnValues(hexaStrToXpath, sa, driver);
                columnIndexInSearchGrid++;
            }
            sa.assertAll();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Error in validation");
        }
    }

    /*********************************************
     * #Function Name : displayGridColumnValues #Description : Displays texts such as campaign, Title, Closing date, Machines and Machine outstanding
     * informations. #Author : Raju #Date of creation : 28-10-2016 #Name of person modifying: Tester #Date of modification: 01-August-2016
     ********************************************/
    public void displayGridColumnValues(String hexaCharToXpath, SoftAssert softassert, WebDriver driver) {
        String xpath = "//div[@class='ng-isolate-scope']/div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-00" + hexaCharToXpath + "']";
        List<WebElement> webelEments = driver.findElements(By.xpath(xpath));
        if (webelEments.size() != 0) {
            for (int j = 0; j < webelEments.size(); j++) {
                String actualValue = webelEments.get(j).getText();
                if ((hexaCharToXpath.equals("05") && j == 0)) {
                    searchResultFirstItemXpath = xpath;

                }
                if (j == 0) {
                    campaignInformation.add(webelEments.get(j).getText());
                }
                keywords.APP_LOGS.info("Column value:" + actualValue);
            }
        } else {
            softassert.assertTrue(false, "No repair history for the vehicle");
        }

    }

    public void openAndValidateCampaignInformation(HashMap<String, Object> data, SoftAssert sa) {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        keywords.clickOnLink(driver.findElement(By.xpath("(" + searchResultFirstItemXpath + ")[1]")), driver);
        keywords.waitTime1(2);
        sa.assertTrue(keywords.elementIsDisplayed(dealerOutstandingMachinesPageTitle, "Dealer outstanding machines"),
                      "Dealer outstanding machines element is not displayed");
        appBasedUtils.asserVerification(campaignInformation.get(0), campaignID.getText(), sa);
        appBasedUtils.asserVerification(campaignInformation.get(1), campaignTitle.getText(), sa);
        appBasedUtils.asserVerification(campaignInformation.get(2), campaignClosingDate.getText(), sa);
        appBasedUtils.asserVerification(appBasedUtils.getStringData(data, "ExpectedWarrantyArea"), warrantyAreaValueOutStandingMachine.getText(), sa);
        wait.until(ExpectedConditions.visibilityOf(dealerValueOutStandingMachine));
        appBasedUtils.asserVerification((appBasedUtils.getStringData(data, "ExpectedDealer")), dealerValueOutStandingMachine.getText(), sa);
        appBasedUtils.asserVerification((appBasedUtils.getStringData(data, "DealerCriteria")), dealerCriteriaValueOutStandingMachine.getText(), sa);
        wait.until(ExpectedConditions.visibilityOf(noOfMachinesInDealerOutstandingMachinesPage));
//        appBasedUtils.asserVerification(campaignInformation.get(4), appBasedUtils.extractStringFromOtherString(noOfMachinesInDealerOutstandingMachinesPage.getText(), "of", "items"), sa);
    }

}
