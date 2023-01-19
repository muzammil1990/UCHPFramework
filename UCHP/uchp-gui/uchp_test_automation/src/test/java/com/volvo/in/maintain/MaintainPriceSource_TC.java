/*package com.volvo.in.maintain;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volvo.in.commonlibrary.BaseClass;
import com.volvo.in.page.campaign.DealerOutstandingCampaign;

public class MaintainPriceSource_TC extends BaseClass {
    WebDriver driver;
    ContractPriceSource maintainPriceSource;
    
    @BeforeClass
    public void setUp() {
        driver=getDriver();
        maintainPriceSource = new ContractPriceSource(driver);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void TC_001_ValidatePriceSource()  {
        HashMap<String, Object> data = (HashMap<String, Object>) lstData.get(1);
        maintainPriceSource.navigateToDealerOutstandingCampaignInformation();
        maintainPriceSource.searchResponsibleDealerOutstandingCampaign(data);
    }
    
   
}
*/