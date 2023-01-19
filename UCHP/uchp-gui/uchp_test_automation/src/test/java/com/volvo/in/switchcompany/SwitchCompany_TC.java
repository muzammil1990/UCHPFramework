package com.volvo.in.switchcompany;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.commonlibrary.ExcelReadAndWrite;
import com.volvo.in.page.switchcompany.SwitchToSelectedCompany;
import com.volvo.in.page.welcomepage.WelcomePage;

public class SwitchCompany_TC extends BaseClass{
	WebDriver driver;
	ExcelReadAndWrite excelReadWrite;
	List<Object> lstData;
	WelcomePage welcome;
	@BeforeClass
	@Parameters({ "Company"})
	public void setUp(String sheetName) {
		driver=getDriver();
		welcomePage = new WelcomePage(driver);
	}
	
	@Test
	public void TC_01_switchToCompanies(){
	    SoftAssert softAssert=new SoftAssert();
	    SwitchToSelectedCompany selectedCompany = new SwitchToSelectedCompany(driver);
	    selectedCompany.switchToCompanyAndVerify("Volvo Truck Corporation",softAssert);
	    selectedCompany.switchToCompanyAndVerify("Volvo Bus Corporation",softAssert);
	    selectedCompany.switchToCompanyAndVerify("Volvo Construction Equipment",softAssert);
	    selectedCompany.switchToCompanyAndVerify("TSM Americas",softAssert);
	    selectedCompany.switchToCompanyAndVerify("Volvo Penta Corporation",softAssert);
	    selectedCompany.switchToCompanyAndVerify("UD Trucks Corporation",softAssert);
	    softAssert.assertAll();	    
	}
	
	@Test
	@Parameters({ "userName","passWord"})
    public void TC_02_SetAndVerifyPreferredCompany_VBC(String appUerName,String appPassword){
        SoftAssert softAssert=new SoftAssert();
        SwitchToSelectedCompany selectedCompany = new SwitchToSelectedCompany(driver);
        selectedCompany.setPreferredCompanys("Volvo Bus Corporation",appUerName,appPassword,softAssert);
        softAssert.assertAll();     
    }
	
	@Test
	@Parameters({ "userName","passWord"})
	    public void TC_03_SetAndVerifyPreferredCompany_NAT(String appUerName,String appPassword){
	        SoftAssert softAssert=new SoftAssert();
	        SwitchToSelectedCompany selectedCompany = new SwitchToSelectedCompany(driver);
	        selectedCompany.setPreferredCompanys("TSM Americas",appUerName,appPassword,softAssert);
	        softAssert.assertAll();     
	 }
	
	@Test
    @Parameters({ "userName","passWord"})
    public void TC_04_VerifyPreferredCompanyNewSession_VBC(String appUerName,String appPassword){
        SoftAssert softAssert=new SoftAssert();
        SwitchToSelectedCompany selectedCompany = new SwitchToSelectedCompany(driver);
        selectedCompany.VerifyPreferredCompanys("Volvo Bus Corporation",softAssert);
        softAssert.assertAll();     
    }
	
	@Test
    @Parameters({ "userName","passWord"})
    public void TC_05_VerifyPreferredCompanyNewSession_NAT(String appUerName,String appPassword){
        SoftAssert softAssert=new SoftAssert();
        SwitchToSelectedCompany selectedCompany = new SwitchToSelectedCompany(driver);
        selectedCompany.VerifyPreferredCompanys("TSM Americas",softAssert);
        softAssert.assertAll();     
    }

	
		
}
