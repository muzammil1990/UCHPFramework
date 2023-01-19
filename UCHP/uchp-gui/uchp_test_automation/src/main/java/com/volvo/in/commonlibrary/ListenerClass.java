package com.volvo.in.commonlibrary;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

public class ListenerClass extends TestListenerAdapter  implements ITestListener{
    WebDriver driver;
    UIUtils keywords = new UIUtils(); 
    
    BaseClass base = new BaseClass();

    //Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
 
    public synchronized void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName());
        keywords.APP_LOGS.info("********Test started: "+result.getMethod().getMethodName()+"********");
    }
 
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }
 
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        keywords = new UIUtils(base.getDriver());
        try {
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(keywords.screenshot("ErrorFile", base.getDriver())).build();
            ExtentTestManager.getTest().log(Status.FAIL," Failure reason: " + result.getThrowable(),mediaModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }
    
    @Override
    public synchronized void onFinish(ITestContext context) {
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }
    
}
