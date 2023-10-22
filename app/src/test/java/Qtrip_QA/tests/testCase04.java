package Qtrip_QA.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Qtrip_QA.pages.AdventureDetailPage;
import Qtrip_QA.pages.AdventurePage;
import Qtrip_QA.pages.HistoryPage;
import Qtrip_QA.pages.HomePage;
import Qtrip_QA.pages.LoginPage;
import Qtrip_QA.pages.RegisterPage;

public class testCase04 extends BaseTest{
    public static String lastGeneratedUsername;
    public static String id;

    public static void logStatus(String type, String message, String status){
        System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    @Test(description = "verify booking history can be viewed",priority = 4, enabled = true, groups = {"Reliability_Flow"}, dataProviderClass = Qtrip_QA.DP.class, dataProvider = "dataProvider")
    public static void TestCase04(String username, String password, String data1, String data2, String data3) throws InterruptedException, IOException{
        logStatus("Page test", "verify booking history can be viewed", "started");
        Boolean status;
        List<String> input1 = Arrays.asList(data1.split(";"));
        List<String> input2 = Arrays.asList(data2.split(";"));
        List<String> input3 = Arrays.asList(data3.split(";"));
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.clickRegister();
        RegisterPage register = new RegisterPage(driver);
        register.registerNewUser(username, password, true);
        status = driver.getCurrentUrl().endsWith("/login");
        // Assert.assertTrue(status, "User not navigated to login page");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Login Navigated");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Login Navigation failed: "+e.getMessage());
        }
        lastGeneratedUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        login.performLogin(lastGeneratedUsername, password);
        status = home.isUserLoggedIn();
        // Assert.assertTrue(status, "User is not logged in");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"User is logged in");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "User login failed: "+e.getMessage());
        }
        home.searchCity(input1.get(0));
        Thread.sleep(3000);
        home.selectCity(input1.get(0));
        Thread.sleep(3000);
        AdventurePage  adPage = new AdventurePage(driver);
        adPage.selectAdventure(input1.get(1));
        AdventureDetailPage det = new AdventureDetailPage(driver);
        det.bookAdventure(input1.get(2), input1.get(3), input1.get(4));
        status = det.isBookingSuccessful();
        // Assert.assertTrue(status, "Booking not sucessful");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Booking successful");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Booking failed: "+e.getMessage());
        }
        home.navigateToHomePage();
        home.searchCity(input2.get(0));
        Thread.sleep(3000);
        home.selectCity(input2.get(0));
        Thread.sleep(3000);
        adPage.selectAdventure(input2.get(1));
        det.bookAdventure(input2.get(2), input2.get(3), input2.get(4));
        status = det.isBookingSuccessful();
        // Assert.assertTrue(status, "Booking not sucessful");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Booking successful");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Booking failed: "+e.getMessage());
        }
        home.navigateToHomePage();
        home.searchCity(input3.get(0));
        Thread.sleep(3000);
        home.selectCity(input3.get(0));
        Thread.sleep(3000);
        adPage.selectAdventure(input3.get(1));
        det.bookAdventure(input3.get(2), input3.get(3), input3.get(4));
        status = det.isBookingSuccessful();
        // Assert.assertTrue(status, "Booking not sucessful");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Booking successful");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Booking failed: "+e.getMessage());
        }
        HistoryPage hp = new HistoryPage(driver);
        hp.navigateToHistoryPage();
        hp.getReservation();
        home.navigateToHomePage();
        status = home.logOutUser();
        // Assert.assertTrue(status, "User should logout");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"User logout");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "User logout failed: "+e.getMessage());
        }
    }
}
