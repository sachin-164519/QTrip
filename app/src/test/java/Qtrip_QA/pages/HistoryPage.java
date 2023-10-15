package Qtrip_QA.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    ChromeDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/";
    int colCount=0;
    int rowCount=0;
    public String id="";

    public HistoryPage(ChromeDriver driver){
        this.driver =driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToHistoryPage(){

    }

    public void getReservation(){

    }

    public void cancelReservation(String id){

    }
}
