package Qtrip_QA.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Qtrip_QA.SeleniumWrapper;

public class HistoryPage {
    ChromeDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/";
    int colCount=0;
    int rowCount=0;
    public String id="";

    @FindBy(xpath = "//a[text()='Reservations']")
    public WebElement reserevationBtn;
    @FindBy(xpath = "//tbody[@id='reservation-table']/tr/th")
    public List<WebElement> tranIds;

    public HistoryPage(ChromeDriver driver){
        this.driver =driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToHistoryPage(){
        SeleniumWrapper.navigate(driver, url);
    }

    public void getReservation(){
        for(WebElement tranid : tranIds){
            System.out.println("transaction id :"+tranid.getText());
            this.id = tranid.getText();
        }
    }

    public void cancelReservation(String id){
        this.id =id;
        for(WebElement tranid: tranIds){
            if(tranid.getText().contains(id)){
                WebElement cancelBtn = driver.findElement(By.id(id));
                SeleniumWrapper.click(cancelBtn, driver);
            }
        }
    }
}
