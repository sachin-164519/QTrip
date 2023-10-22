package Qtrip_QA.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Qtrip_QA.pages.AdventureDetailPage;
import Qtrip_QA.pages.AdventurePage;
import Qtrip_QA.pages.HistoryPage;
import Qtrip_QA.pages.HomePage;
import Qtrip_QA.pages.LoginPage;
import Qtrip_QA.pages.RegisterPage;

public class testCase_03 extends BaseTest{
    public static String lastGeneratedUsername;
    public static String id;

    public static void logStatus(String type, String message, String status){
        System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    @Test(description = "verify that adventure booking and cancellation works fine",priority = 3, enabled = true, groups = {"Booking_and_Cancellation_Flow"}, dataProviderClass = Qtrip_QA.DP.class, dataProvider = "dataProvider")
    public static void TestCase03(String username, String password, String city, String adventure, String name, String date, String count) throws InterruptedException, IOException{
        logStatus("Page test", "verify that adventure booking and cancellation works fine", "started");
        Boolean status;
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
        home.searchCity(city);
        Thread.sleep(3000);
        home.selectCity(city);
        Thread.sleep(3000);
        AdventurePage  adPage = new AdventurePage(driver);
        adPage.selectAdventure(adventure);
        AdventureDetailPage det = new AdventureDetailPage(driver);
        det.bookAdventure(name, date, count);
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
        id = hp.id;
        hp.cancelReservation(id);
        driver.navigate().refresh();
        status = hp.tranIds.isEmpty();
        // Assert.assertTrue(status,"Transaction id is visible");
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,"Transaction id is not visible");
        }catch(AssertionError e){
            test.log(LogStatus.FAIL, test.addScreenCapture(ReportSingleton.capture(driver))+ "Transaction id is visible, so failed: "+e.getMessage());
        }
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
