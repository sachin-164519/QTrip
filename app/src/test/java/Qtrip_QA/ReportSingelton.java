package Qtrip_QA;

import java.io.File;
import java.io.IOException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ReportSingelton {
    static ExtentTest test;
    static ExtentReports report;
    static ReportSingleton reportSingletonInstance;

    private ReportSingleton(){
            report =  new ExtentReports(System.getProperty("user.dir")+"/OurExtentReport.html");
            report.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));
    }

    public static ExtentReports getReport(){
        if(reportSingletonInstance==null){
            reportSingletonInstance= new ReportSingleton();
        }
        return report;
    }

    public static String capture(RemoteWebDriver driver) throws IOException{
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir")+"/QTRIPImages/" + System.currentTimeMillis()+ ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }

    public static void closeReport(){
        report.flush();
    }
}
