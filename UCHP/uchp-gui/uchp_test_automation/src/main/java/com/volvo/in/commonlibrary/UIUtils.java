package com.volvo.in.commonlibrary;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

public class UIUtils {
    WebDriver driver;
    public Logger APP_LOGS = Logger.getLogger(UIUtils.class);
    BaseClass baseClass = new BaseClass();
    public UIUtils(WebDriver driver) { //here driver comes from Page object class
        this.driver = driver;
        PropertyConfigurator.configure("log4j.properties");
    }

    public UIUtils() {
    }

    /**
     * @param driver TODO
     * @param mainMenu
     *            main menu locator value to click
     * @param subMenu
     *            sub menu locator value to click
     * @param titleName
     *
     *            Name of the Menu to click
     */
    public void performMenuAction(WebDriver driver, WebElement mainMenu, WebElement subMenu, WebElement titleName) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebDriverWait wait1 = new WebDriverWait(baseClass.getDriver(), 30);
            wait1.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(mainMenu));
            jse.executeScript("arguments[0].click();", mainMenu);
            waitTime(2000);
            jse.executeScript("arguments[0].click();", subMenu);
            APP_LOGS.info("Clicked on main menu '" + getTextValue(mainMenu) + "'");
            waitForVisibilityOfElement(driver, 20, titleName);
            String pageTitle = titleName.getText();
            APP_LOGS.info("Navigated to menu '" + pageTitle + "'");
            if (BaseClass.test != null)
                BaseClass.test.log(Status.INFO, "Navigated to menu '" + pageTitle + "'");
        } catch (NoSuchElementException e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("Menu is not displayed");
            Assert.fail("Menu is not displayed\n" + e);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Menu is not displayed");
        }
    }

    /**
     * @param driver TODO
     * @param mainMenu
     *            main menu locator value to click
     * @param titleName
     *
     *            Name of the Menu to click
     * @param subMenu
     *            sub menu locator value to click
     */
    public void performSecondLevelMenuAction(WebDriver driver, WebElement mainMenu, WebElement subMenu1, WebElement subMenu2, WebElement titleName) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebDriverWait wait1 = new WebDriverWait(driver, 15);
            waitTime1(2);
            wait1.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(mainMenu));
            jse.executeScript("arguments[0].click();", mainMenu);
            waitTime1(2);
            Actions act = new Actions(driver);
            act.moveToElement(subMenu1);
            waitTime1(1);
            act.moveToElement(subMenu2);
            waitTime1(1);
            act.build().perform();
            waitTime1(1);
            jse.executeScript("arguments[0].click();", subMenu2);
            waitTime1(1);
            wait1.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(titleName));
            APP_LOGS.info("Clicked on main menu '" + getTextValue(mainMenu) + "'");
            String pageTitle=titleName.getText();
            APP_LOGS.info("Navigated to menu '" + pageTitle + "'");
            if(BaseClass.test!=null)
                BaseClass.test.log(Status.INFO,"Navigated to menu '" + pageTitle + "'");
        } catch (NoSuchElementException e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("Menu is not displayed");
            Assert.fail("Menu is not displayed");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Menu is not displayed");
        }
    }

    /**
     * @param fname
     *            File name to save the image
     * @return file name concatinated with time Take Screenshot
     */
    /**
     * @param driver TODO
     * @param fname
     *            File name to save the image
     * @return file name concatinated with time Take Screenshot
     */
    public String screenshot(String strFileName, WebDriver driver) {
        String fnameWithTimeStamp = new String();
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            fnameWithTimeStamp = System.getProperty("user.dir") + ".\\resources\\Screenshot\\" + strFileName + timeStamp + ".jpg";
           // fnameWithTimeStamp = "\\\\vcn.ds.volvo.net\\IT-GOT\\tempdisk\\UCHPTestAutomation\\Screenshot\\" + strFileName + timeStamp + ".jpg";
            File screnshotCaptured = new File(fnameWithTimeStamp);
            FileUtils.copyFile(scrFile, screnshotCaptured);
            APP_LOGS.info("Screenshot is captured for failed testcase. File name is:" + screnshotCaptured.getAbsolutePath());
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("Screenshot has not captured");
            Assert.fail("Screenshot has not captured");
        }
        return fnameWithTimeStamp;
    }

    public String screenshotExtentReport(WebDriver driver, String strFileName) {
        String fnameWithTimeStamp = new String();
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            fnameWithTimeStamp = System.getProperty("user.dir") + ".\\resources\\Screenshot\\" + strFileName + timeStamp + ".jpg";
            File screnshotCaptured = new File(fnameWithTimeStamp);
            FileUtils.copyFile(scrFile, screnshotCaptured);
            APP_LOGS.info("Screenshot is captured for failed testcase. File name is:" + screnshotCaptured.getAbsolutePath());
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("Screenshot has not captured");
            Assert.fail("Element not found Exception");
        }
        return fnameWithTimeStamp;

    }

    /**
     * @param inputBox
     *            input box locator value
     * @param obj
     *            text value to put inside input box
     */
    public void input(WebElement inputBox, Object obj, String fieldName) {
        try {
            String strText = obj.toString().trim();
            inputBox.clear();
            inputBox.sendKeys(strText);
            APP_LOGS.info(" Value '" + inputBox.getAttribute("value") + "' is set to \"" + fieldName + "\" text field");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(inputBox.getAttribute("id") + "  element not found");
            Reporter.log(DateUtility.getCurrentDate(null) + " " + inputBox.getAttribute("id") + " element not found");
            Assert.fail(inputBox.getAttribute("id") + "  element not found");
        } catch (Exception e) {
            APP_LOGS.info(inputBox.getAttribute("id") + "  element not found");
            Assert.fail(inputBox.getAttribute("id") + "  element not found");
        }
    }

    /**
     * @param driver TODO
     * @param btn
     *            variable name to click on element Click on button
     */
    public void clickOnButton(WebDriver driver, WebElement btn) {
        try {
            waitTime(1200);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", btn);
            APP_LOGS.info("Clicked on button");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(btn + "  element not found");
            Assert.fail(btn + "  element not found");
        } catch (Exception e) {
            APP_LOGS.info(btn + " element not found");
            Assert.fail(btn + "  element not found");
        }
    }

    /**
     * @param linktext
     *            to click on link Click on Link
     * @param driver TODO
     */
    public void clickOnLink(WebElement linktext, WebDriver driver) {
        try {
            String strLinkText = linktext.getText();
            linktext.click();
            APP_LOGS.info("Clicked on Link \"" + strLinkText + "\".");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(linktext.getAttribute("name") + "  element not found");
            Reporter.log(DateUtility.getCurrentDate()+ " " + linktext.getAttribute("name") + " element not found");
            Assert.fail(linktext.getAttribute("name") + "  element not found");
        } catch (Exception e) {
            APP_LOGS.info(linktext.getAttribute("name") + "  not found");
            Assert.fail(linktext.getAttribute("name") + "  element not found");
        }
    }

    /**
     * @param linktext
     * @param driver TODO
     * @param driver
     *            to click on link Click on Link
     */
    public void clickOnLinkUsingAction(WebElement linktext, WebDriver driver) {
        try {
            String strLinkText = linktext.getText();
            Actions ob = new Actions(driver);
            ob.moveToElement(linktext);
            ob.click(linktext);
            Action action = ob.build();
            action.perform();
            APP_LOGS.info("Clicked on Link \"" + strLinkText + "\".");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(linktext.getAttribute("name") + "  element not found");
            Reporter.log(DateUtility.getCurrentDate()+ " " + linktext.getAttribute("name") + " element not found");
            Assert.fail(linktext.getAttribute("name") + "  element not found");
        } catch (Exception e) {
            APP_LOGS.info(linktext.getAttribute("name") + "  not found");
            Assert.fail(linktext.getAttribute("name") + "  element not found");
        }
    }

    /**
     * @param eleText
     *            to get the visible text from front end
     * @return text present on the link Get text value from input field
     */
    public String getTextValue(WebElement eleText) {
        try {
            String actresult = eleText.getText().trim();
            return actresult;
        } catch (Exception e) {
            APP_LOGS.info(eleText + " was not found");
            Assert.fail("Element not found Exception");
        }
        return null;
    }

    /**
     * @param visibleText
     *            locator value to select the value from dropdown based on visible text
     * @param text
     *            locator xpath to input the value in dropdown Select value from drop down
     */
    public void selectVisibleText(WebElement visibleText, Object text) {
        try {
            Select select = new Select(visibleText);
            waitTime(2000);
            select.selectByVisibleText(text.toString());
            APP_LOGS.info(" Value '" + text.toString() + "' selected from dropdown");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(visibleText.getText() + " is not found. Can not select " + text.toString() + " value");
            Assert.fail("Element not found Exception");
        } catch (Exception e) {
            APP_LOGS.info(visibleText.getText() + "is not found");
            Assert.fail("Element not found Exception");
        }
    }

    /**
     * @param selectByIndexValue
     *            locator xpath value to select the value from dropdown based on index text
     * @param text
     *            locator xpath to input the value in dropdown Select value from drop down
     */
    public void selectByIndex(WebElement selectByIndexValue, String strText) {
        try {
            int inttext = Integer.parseInt(strText);
            new Select(selectByIndexValue).selectByIndex(inttext);
            APP_LOGS.info(selectByIndexValue + strText + " selected based on index");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(selectByIndexValue + "is not found");
            Assert.fail("Element not found Exception");
        } catch (Exception e) {
            APP_LOGS.info(selectByIndexValue + "is not found");
            Assert.fail("Element not found Exception");
        }

    }

    /**
     * @param dropdown locator
     *            locator return the first selected webelement from list
     * @return
     */
    public String getFirstSelectedItemFromDropDown(WebElement dropdown) {
        try {
           return  new Select(dropdown).getFirstSelectedOption().getText();
        } catch (NoSuchElementException e) {
            APP_LOGS.info("dropdown is not found");
            Assert.fail("dropdown not found Exception");
        } catch (Exception e) {
            APP_LOGS.info("dropdown is not found");
            Assert.fail("Element not found Exception");
        }
        return null;
    }
    /**
     * @param clearValue
     *            locator xpath to clear the value from text field Clear the value from text field
     */
    public void clearText(WebElement clearValue, String fieldName) {
        try {
            clearValue.clear();
            APP_LOGS.info("Value present in \"" + fieldName + "\"  text box is cleared");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(clearValue + "element is not found");
            Reporter.log(DateUtility.getCurrentDate()+ " " + "Element not found Exception");
            Assert.fail("Element not found Exception");
        } catch (Exception e) {
            APP_LOGS.info(clearValue + "element is not found");
            Assert.fail(DateUtility.getCurrentDate()+ " " + "Element not found Exception");
        }
    }

    /**
     * @param element
     *            locator xpath
     * @return boolean value based in availabilty of element Verify element is displayed
     */
    public boolean elementIsDisplayed(WebElement element, String strAboutElement) {
        try {
            if ((element.isDisplayed())) {
                APP_LOGS.info("\"" + element.getText() + "\" Element is displayed ");
                return true;
            }
        } catch (Exception e) {
            // APP_LOGS.info(e);
        }
        return false;
    }

    /**
     * @param element
     *            locator xpath
     * @return boolean value based in availabilty of element Verify element is displayed
     */
    public String getTextElementIsDisplayed(WebElement element) {
        try {
            if ((element.isDisplayed())) {
                APP_LOGS.info("\"" + element.getText() + "\" Element is displayed ");
                return element.getText();
            }
        } catch (Exception e) {
            APP_LOGS.info("");
        }
        return null;
    }

    /**
     * @param element
     *            locator xpath to get the back end attribute value
     * @return attribute value Get the back attribute value
     */
    public String getAttributeValue(WebElement element, String attributeName) {
        String strActResult = null;
        try {
            strActResult = element.getAttribute(attributeName).trim();
            APP_LOGS.info(strActResult + " is found");
        } catch (NoSuchElementException e) {
            APP_LOGS.info(element + " is not found");
            Assert.fail(DateUtility.getCurrentDate()+ " " + "Element not found Exception");
        } catch (Exception e) {

        }
        return strActResult;
    }

    /**
     * } Switch to alert dialog and make it active
     */
    public void defHandleModalDailogues(WebDriver driver) {
        try {
            driver.switchTo().activeElement();
        } catch (Exception e) {
            APP_LOGS.info(e);
        }
    }

    public void assertEqual(String strExpected, String strActual, SoftAssert softAssert) {
        try {
            softAssert.assertEquals(strActual, strExpected);
            APP_LOGS.info("Expected element is:  \"" + strExpected + "\". And actual element is: \"" + strActual + "\".");
        } catch (Exception e) {
            APP_LOGS.info(e);
        }

    }

    /**
     * APP_LOGS.info(element + "is not found"); Reporter.log(DateConversions.getCurrentDate()+ " " + "Element not found Exception");
     * Assert.fail("Element not found Exception"); } return strActResult; }
     *
     * /**
     *
     * @param element
     *            locator xpath
     * @return booelan value based on availability Verify element is enabled
     */
    public boolean elementIsEnabled(WebElement element) {
        try {
            if (element.isEnabled()) {
                APP_LOGS.info(element.getText() + " Element is enabled ");
                return true;
            } else {
                APP_LOGS.info(element + "Element is not enabled ");
                Reporter.log(DateUtility.getCurrentDate()+ " " + "Element not found Exception");
                return false;
            }
        } catch (Exception e) {
            APP_LOGS.info(element + "Element is not enabled ");
            Assert.fail(DateUtility.getCurrentDate()+ " " + "Element not found Exception");
            return false;
        }
    }

    /**
     * @param intTimeToWait
     *            time duration to wait
     * @param element
     *            locator value to click on element
     * @param str
     *            error message to be displayed if element is not clickable wait for click on element and wait for specified time
     */
    public boolean waitForElementToBeClickable(WebDriver driver, int intTimeToWait, WebElement element, String... str) {
        try {
            WebDriverWait explicit_wait_Example = new WebDriverWait(driver, intTimeToWait);
            explicit_wait_Example.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            if (str.length > 0) {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate(null) + " " + str[0] + "\n" + str[1]);
            } else {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0]);
            }
            Assert.fail("Element not found within " + intTimeToWait + " seconds");
        }
        return false;
    }

    /**
     * @param intTimeToWait
     *            wait time in seconds
     * @param elementToVisible
     *            locator xpath
     * @param str
     *            error message to display in log
     */
    public boolean waitForVisibilityOfElement(WebDriver driver1, int intTimeToWait, WebElement elementToVisible) {
        try {
            new WebDriverWait(driver1, intTimeToWait).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(elementToVisible));
            return true;
        } catch (Exception e) {
            APP_LOGS.error(e);
            APP_LOGS.error(e.getMessage());
            Assert.fail("Element not found within " + intTimeToWait + " seconds");
        }
        return false;
    }

    /**
     * #Description: Waits for particular amount of time until element clickable and returns the status. returns true if element is clickable returns false if
     * element is not clickable(not found)
     */
    /*
     * public boolean waitForClickabilityOfElement(WebDriver driver1, int intTimeToWait, WebElement elementToVisible) { try { WebDriverWait wait = new
     * WebDriverWait(driver1, intTimeToWait); wait.until(ExpectedConditions.elementToBeClickable(elementToVisible)); return true; } catch (Exception e) {
     * APP_LOGS.error(e); return false; } }
     */

    /**
     * #Description: Waits for particular amount of time until element visible and returns the status. returns true if element is visible returns false if
     * element is not visible (not found)
     */

    public boolean waitAndReturnVisibilityOfElement(WebDriver driver1, int intTimeToWait, WebElement elementToVisible) {
        try {
            new WebDriverWait(driver1, intTimeToWait).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(elementToVisible));
            return true;
        } catch (Exception e) {
            APP_LOGS.error(e);
            return false;
        }
    }

    /**
     * @param intTimeToWait
     *            wait time in seconds
     * @param elementToVisible
     *            locator xpath
     * @param str
     *            error message to display in log
     */
    public void waitForVisibilityOfElementAndCheckForSpecifiedErrorMessage(WebDriver driver, int intTimeToWait, WebElement elementToVisible,
            WebElement errorMessageElement, WebElement validationMessageClose) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(elementToVisible));
        } catch (Exception e) {
            if (elementIsDisplayed(errorMessageElement, "Error message pop up")) {
                String val = getTextValue(errorMessageElement);
                BaseClass.test.log(Status.ERROR, "Search failed with following error message:\n" + val);
                validationMessageClose.click();
                Assert.fail(val);
            } else {
                Assert.fail("Fail!!! Search result not found '");
            }
        }
    }

    /**
     * #Description: Waits for particular amount of time until error message visible if error message visible make test to fail.
     */
    public void checkVisibilityOfElementErrorMessage(WebDriver driver, int intTimeToWait, WebElement errorMessageElement, WebElement validationMessageClose) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(errorMessageElement));
            String messageText = errorMessageElement.getText();
            if (!messageText.contains("Success")) {
                BaseClass.test.log(Status.ERROR, "Following error message occured:\n" + messageText);
                APP_LOGS.error(messageText);
                BaseClass.refreshPageToRemoveErrorIcon = true;
                Assert.fail(messageText);
            }
            validationMessageClose.click();
        } catch (Exception e) {
        }
    }

    /**
     * #Description: Waits for particular amount of time until error message visible. if error message visible make test to fail. if error message not found,
     * returns false, so script continues.
     */
    public boolean isErrorMessageVisible(WebDriver driver, int intTimeToWait, WebElement errorMessageElement, WebElement validationMessageClose) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(errorMessageElement));
            String messageText = errorMessageElement.getText();
            if (!messageText.contains("Success")) {
                BaseClass.test.log(Status.ERROR, "Following error message occured:\n" + messageText);
                APP_LOGS.error(messageText);
                BaseClass.refreshPageToRemoveErrorIcon = true;
                Assert.fail(messageText);
                return true;
            }
            validationMessageClose.click();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * #Description: Waits for particular amount of time until succes message visible.
     * returns false, so script continues.
     */
    public boolean isSuccessMessageVisible(WebDriver driver, int intTimeToWait, WebElement errorMessageElement, WebElement validationMessageClose) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(errorMessageElement));
            String messageText = errorMessageElement.getText();
            if (messageText.contains("Success")) {
                BaseClass.test.log(Status.PASS, "Following message displayed:\n" + messageText);
                APP_LOGS.info(messageText);
                return true;
            }
            validationMessageClose.click();
        } catch (Exception e) {
            APP_LOGS.info(e.getMessage());
            Assert.fail("Claims were not updated");
        }
        return false;
    }

    /**
     * @param driver TODO
     * @param intTimeToWait
     *            wait time in seconds
     * @param linkToVisible
     *            locators xpath
     * @param str
     *            error message to display in logs
     */
    public void waitForVisibilityOfElementByLinkText(WebDriver driver, int intTimeToWait, WebElement linkToVisible, String... str) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated((By) linkToVisible));
        } catch (TimeoutException e) {
            if (str.length > 0) {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0] + "\n" + str[1]);
            } else {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0]);
            }
            Assert.fail("Element not found within " + intTimeToWait + " seconds");
        }
    }

    /**
     * @param intTimeToWait
     *            wait time in seconds
     * @param elementToInVisible
     *            locators xpath of element invisible
     * @param str
     *            error message to display in logs
     */
    public void waitForInVisibilityOfElement(WebDriver driver, int intTimeToWait, WebElement elementToInVisible, String... str) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(elementToInVisible));
        } catch (TimeoutException e) {
            if (str.length > 0) {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0] + "\n" + str[1]);
            } else {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0]);
            }
            Assert.fail("Element found within " + intTimeToWait + " seconds");
        }
    }

    /**
     * @param intTimeToWait
     *            wait time in seconds
     * @param elementToInVisible
     *            locator xpath to invisible
     * @param driver TODO
     * @param str
     *            error message to display in logs
     * @param text
     *            value to be invisible
     */
    public void waitForInVisibilityOfText(int intTimeToWait, WebElement elementToInVisible, String strText, WebDriver driver, String... str) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementWithText((By) elementToInVisible, strText));
        } catch (TimeoutException e) {
            if (str.length > 0) {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0] + "\n" + str[1]);
            } else {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0]);
            }
            Assert.fail("Element not found within " + intTimeToWait + " seconds");
        }
    }

    /**
     * @param driver TODO
     * @param intTimeToWait
     *            wait time in seconds
     * @param elementToVisible
     *            locator xpath to visible
     * @param str
     *            error message to display in logs
     * @param text
     *            value to be visible
     */
    public void waitForVisibilityOfText(WebDriver driver, int intTimeToWait, WebElement elementToVisible, String strText, String... str) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, intTimeToWait);
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.textToBePresentInElementValue(elementToVisible, strText));
        } catch (TimeoutException e) {
            if (str.length > 0) {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0] + "\n" + str[1]);
            } else {
                BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + str[0]);
            }
            Assert.fail("Element not found within " + intTimeToWait + " seconds");
        }
    }

    /**
     * @param element
     *            to perform enter key press from keyboard
     */
    public void dropDownKeyEnter(WebElement element) {
        try {
            element.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e) {
            BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + "Element not found");
            Assert.fail("Element not found");
        }
    }

    /**
     * @param element
     *            perform Tab from keyboard
     */
    public void keyTab(WebElement element) {
        try {
            element.sendKeys(Keys.TAB);
        } catch (NoSuchElementException e) {
            Assert.fail(DateUtility.getCurrentDate()+ " " + "Element not found");
        }
    }

    /**
     * @param element
     *            perform Tab and enterfrom keyboard
     */
    public void keyTabAndEnter(WebElement element) {
        try {
            element.sendKeys(Keys.ENTER);
            element.sendKeys(Keys.TAB);
        } catch (NoSuchElementException e) {
            Assert.fail(DateUtility.getCurrentDate()+ " " + "Element not found");
        }
    }

    public void keyEnter(WebElement element) {
        try {
            element.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e) {
            Assert.fail(DateUtility.getCurrentDate()+ " " + "Element not found");
        }
    }

    /**
     * @param intTimeToWait
     *            sleep time
     */
    public void waitTime(int intTimeToWait) {
        try {
            Thread.sleep(intTimeToWait);
        } catch (InterruptedException e) {
            Assert.fail("Element not found within " + intTimeToWait + " seconds");
        }
    }

    /**
     * Close the window
     */
    public void closeBrowser(WebDriver driver) {
        try {
            driver.close();
            APP_LOGS.info("--------------Application has close successfully------------------");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("Application has not close successfully");
            APP_LOGS.info(e);
            BaseClass.test.log(Status.ERROR, DateUtility.getCurrentDate()+ " " + "Application has not close successfully");
            Assert.fail("Application has not close successfully");
        }
    }

    /**
     * Switch to alert dialog and make it active
     */
    public void handleModalDailogues(WebDriver driver) {
        try {
            driver.switchTo().activeElement();
        } catch (Exception e) {
            BaseClass.test.log(Status.ERROR, "Alert is not displayed");
            Assert.fail("Alert is not displayed");
        }
    }

    public WebElement getDynamicWebElement(WebDriver driver, String firstString, int index, String secondString) {
        try {
            WebElement webelement = null;
            webelement = driver.findElement(By.xpath(firstString + index + secondString));
            return webelement;
        } catch (Exception e) {
            APP_LOGS.info(e);
        }
        return null;

    }

    public boolean assertContains(String strExpected, String strActual) {
        try {
            if (strActual.contains(strExpected)) {
                APP_LOGS.info("Expected data found!!. Expected Data is: \"" + strExpected + "\".");
                return true;
            } else {
                APP_LOGS.info("Expected data not found!!. Expected Data is: \"" + strExpected + "\". And Actual data is: " + strActual + ".");
                return false;
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.error(e);
        }
        return false;
    }

    /**
     * verify the list of items present in the dropdown
     */
    public void listOfItemsFromDropDown(WebElement dropdown, String expectedDropDownValues[], SoftAssert softAssert) {
        try {
            WebElement select = dropdown;
            List<WebElement> itemsInDropDown = select.findElements(By.tagName("option"));
            for (int i = 0; i < itemsInDropDown.size(); i++) {
                assertEqual(expectedDropDownValues[i], itemsInDropDown.get(i).getText(), softAssert);
            }
            softAssert.assertAll();
        } catch (Exception e) {
            Assert.fail("Element not found within " + dropdown);
        }
    }

    /**
     * Clears existing content of the object and copies values of previouse object to current object
     */
    public void clearAndCopyObject(List<String> previouseStringObject, List<String> currentStringObject) {
        try {
            previouseStringObject.clear();
            for (int i = 0; i < currentStringObject.size(); i++) {
                previouseStringObject.add(currentStringObject.get(i));
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.error(e);
        }
    }

    /**
     * Logs Labels and its corresponding value to log
     */
    public void printAllElements(List<String> stringListObject, String coverageColumnNames[]) {
        try {
            for (int j = 0; j < coverageColumnNames.length; j++) {
                APP_LOGS.info(coverageColumnNames[j] + ": " + stringListObject.get(j));
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.error(e);
        }
    }

    /**
     * Compares a list-collection with another list collection
     */
    public boolean compareList(List<String> listStringFirst, List<String> listStringSecond) {
        try {
            if (listStringFirst.equals(listStringSecond)) {
                return true;
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.error(e);
        }
        return false;
    }

    /**
     * Moves control from one element to another element
     * @param driver TODO
     */
    public void moveFocusToElement(WebDriver driver, WebElement mainMenu, WebElement subMenu) {
        try {
            Thread.sleep(5000);
            Actions act = new Actions(driver);
            act.moveToElement(mainMenu).contextClick().perform();
        } catch (NoSuchElementException e) {
            Assert.fail(subMenu + " was not displayed");
        } catch (Exception e) {
            Assert.fail(subMenu + " was not displayed");
        }
    }

    /**
     * verify the list of items present in the dropdown
     */
    public void verifyListOfItemsFromDropDown(WebElement dropdown, String expectedDropDownValues[], SoftAssert softAssert) {
        try {
            waitTime1(2);
            WebElement select = dropdown;
            List<WebElement> itemsInDropDown = select.findElements(By.tagName("option"));
            for (int i = 0; i < itemsInDropDown.size(); i++) {
                assertEqual(expectedDropDownValues[i], itemsInDropDown.get(i).getText(), softAssert);
            }
            softAssert.assertAll();
        } catch (Exception e) {
            BaseClass.test.log(Status.ERROR,
                               DateUtility.getCurrentDate()+ " " + "Mismatch in the names displayed in the dropdown" + e);
            softAssert.fail();
        }
    }

    /**
     * remove the white space from the string.
     */
    public String removeWhiteSpace(String stringWhiteSpace) {
        String strNoWhiteSpace = new String();
        try {
            strNoWhiteSpace = stringWhiteSpace.replaceAll("\\s", "");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return strNoWhiteSpace;
    }

    /**
     * remove the white space from the string.
     */
    public String removeTrailingAndLeadingWhiteSpace(String stringWhiteSpace) {
        String strNoWhiteSpace = new String();
        try {
            strNoWhiteSpace = stringWhiteSpace.trim();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return strNoWhiteSpace;
    }

    /**
     * #Project Name : UCHP - Automation #Function Name : clickOnMainMenu() #Author : Raju #Description : To click on the "Main" Menu item #Date of creation :
     * 26-10-2016 #inout Parameters: #Name of person modifying: Raju #Date of modification: 26-10-2016
     * @param driver TODO
     */
    public void clickOnMainMenu(WebElement mainMenu, WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions act = new Actions(driver);
            act.moveToElement(mainMenu).click().build().perform();
            APP_LOGS.info("Clicked on main menu '" + getTextValue(mainMenu) + "'");
        } catch (InterruptedException e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /**
     * #Project Name : UCHP - Automation #Function Name : waitTimeWithoutSleep() #Author : Raju #Description : Waits for amount of seconds mentioned #Date of
     * creation : 26-10-2016 #Input Parameters: #Name of person modifying: Raju #Date of modification: 26-10-2016
     */
    public void waitTime1(int seconds) {
        long expectedvalue = 0l;
        long now = System.currentTimeMillis();
        expectedvalue = now + (1000 * seconds);
        while (now < expectedvalue) {
            now = System.currentTimeMillis();
        }
    }

    /**
     * Sets preferred company and logs.
     */
//    public void performMenuAction(WebDriver driver, WebElement mainMenu, WebElement subMenu, WebElement notificationElement) {
//        try {
//            WebDriverWait wait1 = new WebDriverWait(driver, 12);
//            Actions act = new Actions(driver);
//            wait1.until(ExpectedConditions.visibilityOf(mainMenu));
//            act.moveToElement(mainMenu).click(mainMenu).perform();
//            wait1.until(ExpectedConditions.visibilityOf(subMenu));
//            act.moveToElement(subMenu).click().perform();
//            wait1.until(ExpectedConditions.visibilityOf(notificationElement));
//            elementIsDisplayed(notificationElement, "PreferedCompanyElement");
//            APP_LOGS.info(getTextValue(notificationElement));
//        } catch (Exception e) {
//            APP_LOGS.error(e);
//        }
//
//    }

    /**
     * Checks if current company element is set as preferred company.
     */
    public Boolean navigateToMenuCheckIsElementSelected(WebDriver driver, WebElement mainMenu, WebElement subMenu) {
        try {
            Actions act = new Actions(driver);
            waitForVisibilityOfElement(driver, 12, mainMenu);
            act.moveToElement(mainMenu).click(mainMenu).perform();
            waitForVisibilityOfElement(driver, 12, subMenu);
            Boolean isElementSelected = subMenu.isSelected();
            return isElementSelected;
        } catch (Exception e) {
            APP_LOGS.error(e);
        }
        return false;
    }

    /**
     * Common method to set the checkbox if it is not selected.f
     */
    public void setCheckboxIfNotSelected(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /**
     * Resets the checkbox.
     */
    public void resetCheckboxStatus(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /**
     * return true if checkbox is selected, return false if checkbox is not selected.
     */
    public boolean isCheckboxElementSelected(WebElement element) {
        try {
            if (element.isSelected()) {
                return true;
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return false;
    }

    /**
     * Scrolls current page by the index
     */
    public void scrollPage(WebDriver driver, int scrollBy) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + scrollBy + ")", "");
    }
    
    /**
     * scroll the till the end of the page
     */
    public void scrollPageTillBottomOfPage(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)");
    }

    /**
     * Logs the text value of list of webelements
     */
    public void printTextValueOFElements(List<WebElement> webelements) {
        for (int i = 0; i < webelements.size(); i++) {
            APP_LOGS.info(getTextValue(webelements.get(i)) + "\n");
        }
    }

    /**
     * Logs the text value of list of webelements
     */
    public String getTextValueOFElements(List<WebElement> webelements) {
        String elementValue = "";
        for (int i = 0; i < webelements.size(); i++) {
            elementValue = elementValue + "\n" + getTextValue(webelements.get(i));
        }
        return elementValue;
    }

    /**
     * Scroll to end of the web page.
     */
    public void scrolltoEndOfPage(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

    /**
     * Gets and returns nth dropdown value and assign it.
     */
    public String geDropdownValueOfParticularPosistion(WebElement dropdownElement, int position) {
        Select selectDropdownElement = new Select(dropdownElement);
        String dropdownValue = null;
        List<WebElement> listDropdownElements = selectDropdownElement.getOptions();
        for (int i = 0; i <= position; i++) {
            if (i == position)
                dropdownValue = listDropdownElements.get(position).getText();
        }
        return dropdownValue;
    }

    /**
     * Common method to focusing out of current element, some particular elements gets enabled if focus out happens properly.
     * @param driver TODO
     */
    public void focusingOutOfElementUsingAction(WebDriver driver, WebElement elementHavingFocus, WebElement nextElements) {
        Actions act = new Actions(driver);
        act.moveToElement(elementHavingFocus).click().perform();
        act.moveToElement(nextElements).click().perform();

    }

    /**
     * Common method to wait untill page load succesfully.
     */
    public void waitForPageLoadSuccessfully(WebDriver driver) {
        try {
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void refreshWindow(){
        baseClass.getDriver().navigate().refresh();
    }

}
