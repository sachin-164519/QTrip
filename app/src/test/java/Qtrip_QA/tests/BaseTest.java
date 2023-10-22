package Qtrip_QA.tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
    public static ChromeDriver driver;
    public static ExtentTest test;
    
    @BeforeTest(enabled = true)
    public static void createDriver(ITestContext context) throws MalformedURLException{
        driver = DriverSingleton.getDriver();
        test = ReportSingleton.getReport().startTest(context.getName());
    }

    @AfterTest(enabled = true)
    public static void quitDriver() {
        System.out.println("close()");
        DriverSingleton.close();
        ReportSingleton.getReport().endTest(test);
    }

    @BeforeSuite(enabled = true)
    public static void intializeExtentReports(){
        ReportSingleton.getReport();
    }

    @AfterSuite(enabled = true)
    public static void tearDown(){
        System.out.println("reportClose");
        ReportSingleton.closeReport();
    }

    @AfterMethod(enabled = true)
    public static void checkStatus(ITestResult result) throws IOException{
        if(result.getStatus()==ITestResult.FAILURE){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Test case failed");
        }else if(result.getStatus()==ITestResult.SUCCESS){
            test.log(LogStatus.PASS,"Test Case pass");
        }
    
}
