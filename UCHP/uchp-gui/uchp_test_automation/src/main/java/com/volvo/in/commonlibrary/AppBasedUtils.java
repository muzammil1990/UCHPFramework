package com.volvo.in.commonlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

public class AppBasedUtils {
    SoftAssert softAssert = new SoftAssert();
    UIUtils keywords = new UIUtils();
    public static Logger APP_LOGS = Logger.getLogger(AppBasedUtils.class);
    public Properties config = null;

    /***************************************************
     * #Description : common method to validate the search result in grid
     **************************************************/
    public void validateGridData(WebDriver driver, String firstString, int index, String secondString, Object data) {
        WebElement searchResultInGrid = null;
        searchResultInGrid = getDynamicWebElement(driver, firstString, index, secondString);
        asserVerification(data.toString(), searchResultInGrid.getText(), softAssert);
    }

    /**
     * @param filename
     *            to read the properties values
     */
    public Properties getConfigProperties(String filename) {
        try {
            APP_LOGS.info("Config file loading");
            Path path = Paths.get("resources", "Configuration", filename + ".properties");
            APP_LOGS.info("Loading the data from config file, " + path);
            FileInputStream fs = new FileInputStream(path.toString());
            config = new Properties();
            config.load(fs);
        } catch (IOException e) {
            Reporter.log(filename + " : " + "File not found");
            Assert.fail("File not found");
            APP_LOGS.info("Properties file not loading");
        } catch (Exception e) {
            Reporter.log(filename + " : " + "File not found");
            Assert.fail("File not found");
            APP_LOGS.info("Properties file not loading");
        }
        return config;
    }

    /***************************************************
     * #Description : common method to compare expected with actual result and write report log message
     **************************************************/
    public void asserVerification(String strExpected, String strActual, SoftAssert softAssert) {
        try {
            softAssert.assertEquals(strActual, strExpected);
            keywords.APP_LOGS.info("Expected element is:  \"" + strExpected + "\"  And actual element is: \"" + strActual + "\".");

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /***************************************************
     * #Description : common method to compare expected with actual result and displays the column name
     **************************************************/
    public void asserVerification(String labelName, String strExpected, String strActual, SoftAssert softAssert) {
        try {
            softAssert.assertEquals(strActual, strExpected);
            keywords.APP_LOGS.info(labelName + ". Expected element is:  \"" + strExpected + "\"  And actual element is: \"" + strActual + "\".");

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /***************************************************
     * #Function Name : getDynamicWebElement() #Description : return the webelement xpath
     **************************************************/
    public WebElement getDynamicWebElement(WebDriver driver, String firstString, int index, String secondString) {
        try {
            WebElement webelement = null;
            webelement = driver.findElement(By.xpath(firstString + index + secondString));
            return webelement;
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }
        return null;
    }

    /*********************************************
     * #Description : Validates whether date is in the range specified.
     ********************************************/
    public void validateDate(WebDriver driver, String elementXpath, Object fromDate, Object toDate, SoftAssert softAssert) {
        try {
            Date from = DateUtility.parseToDateFormat(DateUtility.convertDateBetweenFormat((fromDate).toString()));
            Date to = DateUtility.parseToDateFormat(DateUtility.convertDateBetweenFormat((toDate).toString()));
            Thread.sleep(1000);
            List<WebElement> webelements = driver.findElements(By.xpath(elementXpath));
            if (webelements.size() != 0) {
                for (int j = 0; j < webelements.size(); j++) {
                    String strelementDate = webelements.get(j).getText();
                    Date elementDate = DateUtility.parseToDateFormat(DateUtility.convertDateBetweenFormat(strelementDate));
                    boolean creditingDateFlag = (elementDate.after(from) && elementDate.before(to)) || DateUtility.compareforEquality(elementDate, from)
                            || DateUtility.compareforEquality(elementDate, to);
                    if (creditingDateFlag) {
                        keywords.APP_LOGS.info("Element Date \"" + webelements.get(j).getText() + "\" is  matched with criteria, From Date: \""
                                + (String) fromDate + "\" To Date: \"" + (String) toDate + "\".");
                    } else {
                        keywords.APP_LOGS.info("Date is not matched. Element Date is:" + elementDate + ". Creation from date is:" + from
                                + ". Creation to Date is:" + to);
                        softAssert.assertTrue(false, "Search result creation date:" + strelementDate + "is not matched with search criteria:"
                                + (String) fromDate + " - " + (String) toDate);
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /*********************************************
     * #Description : Validates texts such as Company name, Dealer, Importer etc in the search grid.
     ********************************************/
    public void textValidationGrid(WebDriver driver, String expectedText, String elementXpath, SoftAssert softassert) {
        try {
            List<WebElement> webelEments = driver.findElements(By.xpath(elementXpath));
            if (webelEments.size() != 0) {
                for (int j = 0; j < webelEments.size(); j++) {
                    String actualValue = webelEments.get(j).getText();
                    keywords.assertEqual(expectedText, actualValue, softassert);
                }
            } else {
                BaseClass.test.log(Status.FAIL, expectedText + " not found");
                keywords.APP_LOGS.info(expectedText + " not found");
                Assert.fail(expectedText + " not found");
            }
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }
    }

    /*********************************************
     * #Description : Validates Brand in the search grid.
     ********************************************/
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void claimStatusIconValidationInGrid(String expectedDropDownValues, String elementXpath, SoftAssert softassert, WebDriver driver) {
        try {
            String[] value = expectedDropDownValues.split(",");
            Set expectedBrands = new HashSet(Arrays.asList(value));
            List<WebElement> webelEments = driver.findElements(By.xpath(elementXpath));
            softassert.assertEquals(webelEments.size() > 0, true, elementXpath + " elements not found in search result.");
            String brandNamesFromUCHPApp[] = new String[webelEments.size()];
            for (int j = 0; j < webelEments.size(); j++) {
                brandNamesFromUCHPApp[j] = webelEments.get(j).getAttribute("uib-tooltip");
                brandNamesFromUCHPApp[j] = brandNamesFromUCHPApp[j].trim();
                if (brandNamesFromUCHPApp[j].contains("(")) {
                    brandNamesFromUCHPApp[j] = (brandNamesFromUCHPApp[j].replace(brandNamesFromUCHPApp[j].substring(brandNamesFromUCHPApp[j].indexOf('('),
                                                                                                                    brandNamesFromUCHPApp[j].indexOf(')') + 1),
                                                                                 "")).trim();
                }
                boolean flag = expectedBrands.contains(brandNamesFromUCHPApp[j]);
                softassert.assertTrue(flag, "Mismatch occured! Expected:" + expectedDropDownValues + ". Actual:" + brandNamesFromUCHPApp[j] + "\".");
                APP_LOGS.info("Expected element is:  \"" + expectedDropDownValues + "\". And actual element is: \"" + brandNamesFromUCHPApp[j] + "\".");
            }
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }
    }

    /*********************************************
     * #Description : Validates Brand in the search grid.
     ********************************************/
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void dropdownValueValidationInGrid(String expectedDropDownValues, String elementXpath, SoftAssert softassert, WebDriver driver) {
        try {
            String[] value = expectedDropDownValues.split(",");
            Set expectedBrands = new HashSet(Arrays.asList(value));
            List<WebElement> webelEments = driver.findElements(By.xpath(elementXpath));
            softassert.assertEquals(webelEments.size() > 0, true, elementXpath + " elements not found in search result.");
            String brandNamesFromUCHPApp[] = new String[webelEments.size()];
            for (int j = 0; j < webelEments.size(); j++) {
                brandNamesFromUCHPApp[j] = webelEments.get(j).getText();
                boolean flag = expectedBrands.contains(brandNamesFromUCHPApp[j]);
                softassert.assertTrue(flag, "Actual value is not matching with expected value. Expected:\"" + expectedDropDownValues + "\". Actual:  \""
                        + brandNamesFromUCHPApp[j] + "\".");
                APP_LOGS.info("Expected element is:  \"" + expectedDropDownValues + "\". And actual element is: \"" + brandNamesFromUCHPApp[j] + "\".");
            }
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }
    }

    /*********************************************
     * #Description : Checks reference number in the GRID if in the from reference and to reference number
     ********************************************/
    public void verifySearchResultInGridBtwRangeForIntergerValue(WebDriver driver, String fromRange, String toRange, String elementXpath,
            SoftAssert softassert) {
        try {
            List<WebElement> webelEments = driver.findElements(By.xpath(elementXpath));
            if (webelEments.size() != 0) {
                for (int j = 0; j < webelEments.size(); j++) {
                    String actualValue = webelEments.get(j).getText();
                    if (actualValue.contains(" ")) {
                        actualValue = actualValue.substring(0, actualValue.indexOf(' '));
                    }
                    boolean NumberComparisionFlag = (Integer.parseInt(actualValue)) >= (Integer.parseInt(fromRange))
                            && (Integer.parseInt(actualValue)) <= (Integer.parseInt(toRange));
                    if (NumberComparisionFlag) {
                        keywords.APP_LOGS.info("Reference number is between the range : " + (Integer.parseInt(actualValue)) + " is between the range  "
                                + Integer.parseInt(fromRange) + " and  " + Integer.parseInt(toRange));
                    } else {
                        keywords.APP_LOGS.info("Number is not matched. Element Number is:" + actualValue + "Creation from date is:" + fromRange
                                + "Creation to Date is:" + toRange);
                        softAssert.assertTrue(false,
                                              "Number " + actualValue + " in the search is not matched with search criteria:" + fromRange + " - " + toRange);
                    }
                }
            }
        } catch (NumberFormatException e) {
            keywords.APP_LOGS.info(e);
        }
    }

    /*********************************************
     * #Description : Validates attachments, downloads documents, validate the text in the document.
     ********************************************/
    public void attachementValidationGrid(HashMap<String, Object> data, String elementXpath, SoftAssert softassert, WebDriver driver, String userRole) {
        try {
            String sPathSep = System.getProperty("file.separator");
            BaseClass baseclass = new BaseClass();
            keywords.waitTime1(2);
            List<WebElement> webelEments = driver.findElements(By.xpath(elementXpath));
            File downloadLocation = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation");
            File downloadedDocumentName = new File(System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "DownloadLocation" + sPathSep
                    + data.get("DownloadedFileName").toString());
            PDFReadAndValidate pdfReadAndValidate = new PDFReadAndValidate();
            pdfReadAndValidate.cleanFolder(downloadLocation);
            // keywords.APP_LOGS.info("Downloading \""+documentType+"\" document...");
            pdfReadAndValidate.downloadDocuments(baseclass.getBrowserType(), webelEments.get(0), baseclass.getDriver(), downloadLocation);
            keywords.waitTime1(10);
            pdfReadAndValidate.readPDFFile(new File(downloadLocation + sPathSep + data.get("DownloadedFileName").toString()), data);
            pdfReadAndValidate.validatePDFdata(data, downloadedDocumentName, (String) data.get("DocumentType") + userRole);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            keywords.APP_LOGS.error(e);
        }
    }

    /*********************************************
     * #Description : Displays texts such as Repair date claim job reference number, repair order no. etc in the view repair history page
     ********************************************/
    public void displayGridColumnValues(String xpathFirstPart, String xpathSecondPart, SoftAssert softassert, WebDriver driver) {
        try {
            List<WebElement> webelEments = driver.findElements(By.xpath(xpathFirstPart
                    + xpathSecondPart.substring(xpathSecondPart.indexOf('_') + 1, xpathSecondPart.length()) + "]"));
            if (webelEments.size() != 0) {
                for (int j = 0; j < webelEments.size(); j++) {
                    String actualValue = webelEments.get(j).getText();
                    /*
                     * if ((xpathSecondPart.equals("ClaimType_1") && j == 0) || (xpathSecondPart.equals("TypeOfConcern_1") && j == 0)) { keywords.APP_LOGS.info(
                     * "Number of times vehicle got repaired is: " + webelEments.size()); }
                     */
                    keywords.APP_LOGS.info("Column value:" + actualValue);
                }
            } else {
                softassert.assertTrue(false, "No repair history for the vehicle");
            }
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }

    }

    /*********************************************
     * #Description : Removes the job number i.e "refno.-job no.", returns reference number
     ********************************************/
    public String removeJobNumberFromReferenceNumber(String refAndJobNumber) {
        try {
            return refAndJobNumber.substring(0, refAndJobNumber.indexOf('-') - 1);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }

    /*********************************************
     * #Description : Removes the job number i.e "refno.-job no.", returns reference number
     ********************************************/
    public String getJobNumberFromReferenceNumber(String refAndJobNumber) {
        try {
            return refAndJobNumber.substring(refAndJobNumber.indexOf('-') + 2, refAndJobNumber.length());
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }

    /*********************************************
     * #Description : Removes the job number i.e "refno.-job no.", returns reference number
     ********************************************/
    public String extractStringFromOtherString(String mainString, String firstString, String secondString) {
        try {
            return mainString.substring(mainString.indexOf(firstString) + firstString.length(), mainString.indexOf(secondString)).trim();
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }

    /*********************************************
     * #Description : extract required string from the another string
     ********************************************/
    public String extractStringWithoutWhiteSpace(String stringWithWhiteSpace) {
        try {
            stringWithWhiteSpace = stringWithWhiteSpace.trim();
            if (stringWithWhiteSpace.contains("(")) {
                stringWithWhiteSpace = (stringWithWhiteSpace.replace(stringWithWhiteSpace.substring(stringWithWhiteSpace.indexOf('('),
                                                                                                    stringWithWhiteSpace.indexOf(')') + 1),
                                                                     "")).trim();
            }
            return stringWithWhiteSpace;
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return stringWithWhiteSpace;
    }

    /*********************************************
     * #Description : Checks if given string is equal to "NULL',"","NA" return true if it is not equal that mean if this
     ********************************************/
    public boolean isThisFieldNeedsToBeEntered(String isThisString) {
        try {
            if ((!isThisString.equalsIgnoreCase("NULL")) && (!isThisString.equalsIgnoreCase("")) && (!isThisString.equalsIgnoreCase("NA")))
                return true;
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return false;
    }

    /*********************************************
     * #Description : Checks if given string is equal to "NULL',"","NA" return true if it is not equal.
     ********************************************/
    public void enterValueToTextFieldOrDropdownBasedOnUserContext(String userContextForField, WebElement inputFieldElement, WebElement dropdownFieldElement,
            WebElement greyedOutLabelElement, String inputValue, WebDriver driver) {
        try {
            if (userContextForField.equalsIgnoreCase("TextBox")) {
                keywords.waitForVisibilityOfElement(driver, 30, inputFieldElement);
                keywords.waitTime(500);
                keywords.input(inputFieldElement, inputValue, "");
            } else if (userContextForField.equalsIgnoreCase("DropDownBox")) {
                keywords.waitForVisibilityOfElement(driver, 10, dropdownFieldElement);
                keywords.selectVisibleText(dropdownFieldElement, inputValue);
            } else if (userContextForField.equalsIgnoreCase("NonEditableLabel")) {
                keywords.APP_LOGS.info(keywords.getTextValue(greyedOutLabelElement) + " Value is picked.");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    /*********************************************
     * #Description : wait untill loading icon disappear, return false if loading icon disppear.
     ********************************************/
    public boolean waitUntilLoadingIconDisappear(WebDriver driver, int intTimeToWait, WebElement element) {
        try {
            driver.switchTo().defaultContent();
            boolean isVisible = true;
            int i = 1;
            while (isVisible) {
                isVisible = waitForVisibilityOfLoadingIconAndReturnStatus(driver, intTimeToWait, element);
                if (i == 3500) {
                    Assert.fail("Application is slow.");
                }
                i++;
            }
            return isVisible;
        } catch (Exception e) {
            keywords.APP_LOGS.info(e);
        }
        return false;
    }

    public boolean waitForVisibilityOfLoadingIconAndReturnStatus(WebDriver driver1, int intTimeToWait, WebElement elementToVisible) {
        try {
            new WebDriverWait(driver1, intTimeToWait).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(elementToVisible));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*********************************************
     * #Description : Clicks icon of error message container, capture the error message and makes test case fail. Parameters: 1. driver: Web driver instance. 2.
     * linkElement: Error icon button. 3. messagesInPopUp:
     ********************************************/
    public boolean captureErrorLogMessages(WebDriver driver, WebElement linkElement, WebElement messagesInPopUp) {
        try {
            if (keywords.waitAndReturnVisibilityOfElement(driver, 12, linkElement)) {
                keywords.clickOnLinkUsingAction(linkElement, driver);
                driver.switchTo().defaultContent();
                keywords.waitForVisibilityOfElement(driver, 12, messagesInPopUp);
                String val = keywords.getTextValue(messagesInPopUp);
                Actions action = new Actions(driver);
                action.sendKeys(Keys.ESCAPE).build().perform();
                BaseClass.test.log(Status.ERROR, "failed with following error message:\n" + val);
                keywords.APP_LOGS.error("failed with following error message:\n" + val);
                BaseClass.refreshPageToRemoveErrorIcon = true;
                Assert.fail(val);
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /*********************************************
     * #Description : Add double values from web element and return the sum.
     *
     ********************************************/
    public String addDoubleValueInString(String... firstStringValue) {
        try {
            Double result = 0.00;
            for (String str : firstStringValue) {
                result = result + Double.parseDouble(str);
            }
            DecimalFormat df = new DecimalFormat("0.00");
            return (df.format(result));
        } catch (NumberFormatException e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }

    /*********************************************
     * #Description : Add double values from web element and return the sum.
     *
     ********************************************/
    public String substractValueFromString(String firstStringValue, int numberToBeSubstracted) {
        try {
            int result = Integer.parseInt(firstStringValue) - numberToBeSubstracted;
            return "" + result;

        } catch (NumberFormatException e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }

    /*********************************************
     * #Description : returns the test data from the excel data
     *
     ********************************************/
    public String getStringData(HashMap<String, Object> testDataHolder, String key) {
        String testDataStr = null;
        try {
            if ((testDataHolder.get(key) != null)) {
                testDataStr = testDataHolder.get(key).toString();
            } else {
                keywords.APP_LOGS.info("Error occurred while reading test data.");
            }
            return testDataStr;
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            Assert.fail("Error occurred while reading test data.");
        }
        return testDataStr;
    }

    public void updateApplicationTestData(String testDataFileName, int rowNumber, int columnNumber, String newValue) {
        String sPathSep = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir") + sPathSep + "resources" + sPathSep + "TestData" + sPathSep + BaseClass.getEnvironmentName() + sPathSep
                + testDataFileName;
        APP_LOGS.info("Test data file is being updated is : " + fileName);
        ExcelReadAndWrite excelReadWrite = new ExcelReadAndWrite(fileName, BaseClass.excelSheetName);
        excelReadWrite.updateExcelRowData(rowNumber, columnNumber, newValue);
    }

    public void reloadApplication(WebDriver driver) {
        try {
            APP_LOGS.info("Reloading the application...");
            driver.navigate().refresh();
            // keywords.waitForPageLoadSuccessfully(driver);
            APP_LOGS.info("Application reloaded successfully...");
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void reSizeTheGridSizeInSearchResult(WebElement gridItemsToDisplay) {
        keywords.selectVisibleText(gridItemsToDisplay, "50");

    }

    /**
     * To check if a String is null empty or equal zero
     *
     * @param string
     *            the String to check
     *
     */

    public static boolean isStringNullOrEmptyOrZero(String string) {
        boolean answer = false;
        answer = isStringNullOrEmptyOrArg(string, "0.00");
        // if this is not true we still have to check if it is zero as "000..."
        if (!answer) {
            try {
                int stringInt = Integer.parseInt(string);
                if (stringInt == 0) {
                    answer = true;
                }
            } catch (NumberFormatException nfe) {
                // it is not a number so then it can't be zero
                answer = false;
            }
        }
        return answer;
    }

    /**
     * To check if a String is null empty or equals to arg
     *
     * @param string
     *            the String to check
     * @return true if string is null,empty(blank)or equal arg
     */

    public static boolean isStringNullOrEmptyOrArg(String string, String arg) {
        if (isStringNullOrEmpty(string)) {
            return true;
        } else if (string.equals(arg)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A utility to check if a String is null or empty
     *
     * @return true if it is null or empty(encloses only spaces or size zero)
     */

    public static boolean isStringNullOrEmpty(String string) {
        if (string == null || string.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A utility to check if a actual number is between the given expected range
     */

    public void isNumberBetweenRange(String actual, String expectedRangefrom, String expectedRangeTo) {
        if (!((Integer.valueOf(actual) >= Integer.valueOf(expectedRangefrom)) && (Integer.valueOf(actual) <= Integer.valueOf(expectedRangeTo)))) {
            APP_LOGS.info(actual + " is not between " + expectedRangefrom + " and " + expectedRangeTo);
            Assert.fail(actual + " is not between " + expectedRangefrom + " and " + expectedRangeTo);
        } else {
            APP_LOGS.info(actual + " is between " + expectedRangefrom + " and " + expectedRangeTo);
        }
    }

    /**
     * A utility to check if a actual date is between the given expected date range
     */

    public void isDateBetweenRange(String actual, String expectedRangefrom, String expectedRangeTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (sdf.parse(actual).after(sdf.parse(expectedRangefrom)) && (sdf.parse(actual).before(sdf.parse(expectedRangeTo)))) {
                APP_LOGS.info(actual + " is between " + expectedRangefrom + " and " + expectedRangeTo);
            } else {
                APP_LOGS.info(actual + " is not between " + expectedRangefrom + " and " + expectedRangeTo);
                Assert.fail(actual + " is not between " + expectedRangefrom + " and " + expectedRangeTo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * A utility to clear Screenshot folder
     */
    public static void clearScreeshotFolder() {
        String screenShotFolder;
        try {
            screenShotFolder = System.getProperty("user.dir") + ".\\resources\\Screenshot\\";
            File clearScreenShotCaptured = new File(screenShotFolder);
            FileUtils.cleanDirectory(clearScreenShotCaptured);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("Screenshot folder is not cleared");
            Assert.fail("Screenshot folder is not cleared");
        }
    }

    /**
     * A utility to clear DownloadLocation folder
     */
    public static void clearDownloadFolder() {
        String downloadLocationFolder;
        try {
            downloadLocationFolder = System.getProperty("user.dir") + ".\\resources\\DownloadLocation\\";
            File clearDownloadLocationFolder = new File(downloadLocationFolder);
            FileUtils.cleanDirectory(clearDownloadLocationFolder);
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            APP_LOGS.info("DownloadLocation folder is not cleared");
            Assert.fail("DownloadLocation folder is not cleared");
        }
    }
}
