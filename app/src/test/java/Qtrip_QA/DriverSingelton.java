package Qtrip_QA;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingelton {
    private static ChromeDriver driver;
    private static DriverSingelton instanceOfDriverSingelton;

    private DriverSingelton(){
        WebDriverManager.chromedriver().timeout(30).setup();
        driver =  new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Driver Started");
    }

    private static void getDriverInstance(){
        if(instanceOfDriverSingelton==null){
            try{
                instanceOfDriverSingelton = new DriverSingelton();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static ChromeDriver getDriver(){
        getDriverInstance();
        return driver;
    }

    public static void close(){
        driver.close();
        instanceOfDriverSingelton=null;
    }
}
