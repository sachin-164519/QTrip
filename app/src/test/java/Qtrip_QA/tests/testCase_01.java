package Qtrip_QA.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Qtrip_QA.pages.HomePage;
import Qtrip_QA.pages.LoginPage;
import Qtrip_QA.pages.RegisterPage;

public class testCase_01 extends BaseTest{
    
    public static String lastGeneratedUsername;

    public static void logStatus(String type, String message, String status){
        System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

     @Test(description = "Verify user registration", priority = 1, groups = {"Login_Flow"}, dataProviderClass = Qtrip_QA.DP.class, dataProvider = "dataProvider",enabled = true)
    public static void TestCase01(String UserName, String Password) throws InterruptedException, IOException{
        logStatus("Page test", "verify user registeration", "started");
        Boolean status;
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.clickRegister();
        RegisterPage register = new RegisterPage(driver);
        register.registerNewUser(UserName, Password, true);
        status = driver.getCurrentUrl().endsWith("/login");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Login Navigated");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Navigation failed: "+e.getMessage());
        }    
        lastGeneratedUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        login.performLogin(lastGeneratedUsername, Password);
        status = home.isUserLoggedIn();
        // Assert.assertTrue(status, "User is not logged in");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"User is logged in");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Login failed: "+e.getMessage());
        } 
        status = home.logOutUser();
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"User is logged out");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Logout failed: "+e.getMessage());
        }
    }

}
