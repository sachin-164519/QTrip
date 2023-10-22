package Qtrip_QA.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Qtrip_QA.pages.AdventurePage;
import Qtrip_QA.pages.HomePage;

public class testCase_02 extends BaseTest{
    public static void logStatus(String type, String message, String status){
        System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    @Test(description = "verify search and filters work fine", priority =2, enabled = true, groups = {"Search_and_Filter_flow"}, dataProviderClass = Qtrip_QA.DP.class, dataProvider = "dataProvider")
    public static void TestCase02(String City, String Category, String Filter, String ExpectedFilteredResult, String ExpectedUnfilteredresult) throws InterruptedException, IOException{
        logStatus("Page test", "verify search and filters work fine", "started");
        boolean status;
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.searchCity(City);
        Thread.sleep(3000);
        status = home.getAutoCompleteText().contains(City);
        //Assert.assertTrue(status, "City is invalid");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"City is valid");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "City is not valid : "+e.getMessage());
        }
        home.selectCity(City);
        Thread.sleep(3000);
        status = driver.getCurrentUrl().endsWith("/?city="+City.toLowerCase());
        // Assert.assertTrue(status, "Not navigated to adventure page");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Navigated to adventure page");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Navigation to adventure page failed: "+e.getMessage());
        }
        AdventurePage ap = new AdventurePage(driver);
        ap.setFilterValue(Filter);
        ap.setCategoryValue(Category);
        Thread.sleep(3000);
        int ActualFilteredResult = ap.getResultCount();
        Assert.assertEquals(String.valueOf(ActualFilteredResult), ExpectedFilteredResult);
        ap.setFilterValue("reset");
        ap.setCategoryValue("reset");
        Thread.sleep(3000);
        int ActualUnfilteredResult = ap.getResultCount();
        Assert.assertEquals(String.valueOf(ActualUnfilteredResult), ExpectedUnfilteredresult);
        
    }
}
