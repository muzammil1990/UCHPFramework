package com.volvo.in.commonlibrary;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.volvo.in.page.welcomepage.WelcomePage;

public class BaseClass {
    protected static WebDriver driver;
    public static String gstrDownloadLocation;
    public static Logger APP_LOGS = Logger.getLogger(BaseClass.class);
    public ExcelReadAndWrite excelReadWrite;
    public static List<Object> lstData;
    public static List<Object> lstSecondaryData;
    public WelcomePage welcomePage;
    public static String applicationUserName;
    public static String excelSheetName;
    public static String testDataFileName;
    public static String userContextForImporter;
    public static String userContextForDealer;
    public static ExtentHtmlReporter extent;
    public static ExtentTest test;
    public static String browserName;
    public static String url;
    public static String testDataEnv;
    public static boolean refreshPageToRemoveErrorIcon = false;
    ExtentManager extentManager = null;

    public WebDriver getDriver() {
        return driver;
    }

    public String getBrowserType() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        return cap.getBrowserName();
    }

    protected WebDriver setDriver(String browserName, String appURL) {
        try {
            gstrDownloadLocation = System.getProperty(AppConstants.USER_DIR).replace("\\", "\\\\");
            gstrDownloadLocation = gstrDownloadLocation + AppConstants.RESOURCES_DOWNLOAD_LOCATION;
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = initChromeDriver(appURL);
            } else if (browserName.equalsIgnoreCase("IE")) {
                driver = initInternetExplorerDriver(appURL);
            } else if (browserName.equalsIgnoreCase("Firefox")) {
                driver = initFirefoxDriver(appURL);
            } else {
                Reporter.log("Invalid browser name");
            }
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return driver;
    }

    protected static WebDriver initChromeDriver(String appURL) {
        WebDriver chromeDriver = null;
        try {
            APP_LOGS.info("Launching google chrome with new profile..");
            System.setProperty("webdriver.chrome.driver", AppConstants.driverPath + "chromedriver.exe");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", System.getProperty(AppConstants.USER_DIR) + AppConstants.RESOURCES_DOWNLOAD_LOCATION);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-extensions");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            ChromeDriverService service = ChromeDriverService.createDefaultService();
            WebDriver driver = new ChromeDriver(caps);
            TimeUnit.SECONDS.sleep(1);
            driver.manage().deleteAllCookies();
            driver.navigate().to(appURL);
            chromeDriver = driver;

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return chromeDriver;
    }

    protected static WebDriver initFirefoxDriver(String appURL) {
        WebDriver firefoxDriver = null;
        try {
            APP_LOGS.info("Launching Firefox browser..");
            System.setProperty("webdriver.gecko.driver", AppConstants.driverPath + "geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            APP_LOGS.info(AppConstants.driverPath + "triggerGreckoDriver.bat");
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", gstrDownloadLocation);
            profile.setPreference("browser.download.alertOnEXEOpen", false);
            profile.setPreference("browser.helperApps.neverAsk.openFile",
                                  "text/csv,application/pdf,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                                  "text/csv,application/pdf,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
            profile.setPreference("browser.download.manager.focusWhenStarting", false);
            profile.setPreference("browser.download.manager.useWindow", false);
            profile.setPreference("browser.download.manager.showAlertOnComplete", false);
            profile.setPreference("browser.download.manager.closeWhenDone", false);
            profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
            profile.setPreference("pdfjs.disabled", true);
            profile.setPreference("plugin.state.flash", 0);
            profile.setPreference("webdriver.log.driver", "OFF");
            profile.setPreference("browser.tabs.remote.autostart", false);
            profile.setPreference("browser.tabs.remote.autostart.1", false);
            profile.setPreference("browser.tabs.remote.autostart.2", false);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            WebDriver driver = new FirefoxDriver(capabilities);
            driver.manage().deleteAllCookies();
            // driver.manage().window().maximize();
            driver.navigate().to(appURL);
            firefoxDriver = driver;
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return firefoxDriver;
    }

    protected static WebDriver initInternetExplorerDriver(String appURL) {
        APP_LOGS.info("Launching Internet Explorer browser..");
        System.setProperty("webdriver.ie.driver", AppConstants.driverPath + "IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("ignoreProtectedModeSettings", true);
        WebDriver driver = new InternetExplorerDriver(capabilities);
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        return driver;
    }

    @Parameters({ "browser","appURL", "Company", "userContextForImporter", "userContextForDealer" })
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("browser") String browser,String appURL, String companyName, String importerUserContext, String dealerUserContext, ITestContext ctx) {
            browserName = browser;
            url = appURL;
            companyName = companyName.replaceAll("\\s+", "");
            String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
            String extentReportFileName = suiteName + companyName + ".html";
            APP_LOGS.info("******** " + suiteName + " Started **************");
            ExtentHtmlReporter htmlReporter;
            userContextForImporter = importerUserContext;
            userContextForDealer = dealerUserContext;
            AppBasedUtils.clearScreeshotFolder();
            AppBasedUtils.clearDownloadFolder();
    }

//    @Parameters({ "browserType", "appURL", "Company", "testDataFileName", "secondaryDataFileName", "userName", "passWord" })
//    @BeforeClass(alwaysRun = true)
//    public void initializeTestBaseSetup(String browserType, String appURL, String sheetName, String testDataFileName, @Optional("MissingSecondaryDataFile") String secondaryDataFileName,
//            String userName, String password) {
//        try {
//            excelSheetName = sheetName;
//            ExcelReadAndWrite excelReadWriteSecondaryDataFile;
//            setDriver(browserType, appURL);
//            excelReadWrite = new ExcelReadAndWrite(RESOURCES_TEST_DATA + getEnvironmentName() + "\\" + testDataFileName, sheetName);
//            lstData = excelReadWrite.readAll();
//            if (!secondaryDataFileName.equals("MissingSecondaryDataFile")) {
//                excelReadWriteSecondaryDataFile = new ExcelReadAndWrite(RESOURCES_TEST_DATA + getEnvironmentName() + "\\" + secondaryDataFileName, sheetName);
//                lstSecondaryData = excelReadWriteSecondaryDataFile.readAll();
//            }
//        } catch (Exception e) {
//            APP_LOGS.info("Error....." + e.getStackTrace());
//        }
//    }

    /***************************************************
     * #Function Name : getTestCaseName() #Description : common method to get current test cases are being executed #Date of creation : 14-11-2016 #Author :
     * Raju #Name of person : #Modifying: Tester: #Date of modification:
     **************************************************/
/*    @BeforeMethod(alwaysRun = true)
    public void executeTestCase(Method method) {
        try {
            APP_LOGS.info(" *********** " + method.getName() + " *********** ");
            test = extent.starttTest(this.getClass().getSimpleName() + ":  " + method.getName());
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }*/

//    @AfterMethod(alwaysRun = true)
//    public void logOutFromApplicationsAndQuitBrowser() {
//        try {
////            extent.endTest(test);
//            extent.flush();
//            driver.quit();
//       } catch (Exception e) {
//
//          APP_LOGS.error(e.getMessage());
//       }
//    }

    @AfterSuite(alwaysRun = true)
    protected void afterSuite(ITestContext ctx) {
        APP_LOGS.info("******** " + ctx.getCurrentXmlTest().getSuite().getName() + " Ended *************");
    }

    /****************************************
     * #Description : This method is to get the environment from from the URL passed
     *
     ******************************************/
    public static String getEnvironmentName() {
        try {
            String[] env;
            int left = url.indexOf("-");
            int right = url.indexOf(".");
            String dummy = url.substring(left + 1, right);
            env = dummy.split("\\-");
            return env[0];
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
        return null;
    }
}
