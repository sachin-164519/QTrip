package Qtrip_QA.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Qtrip_QA.SeleniumWrapper;

public class AdventureDetailPage {
    ChromeDriver driver;
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameTxtBox;
    @FindBy(xpath = "//input[@name='date']")
    WebElement selectDate;
    @FindBy(xpath = "//input[@name='person']")
    WebElement personCount;
    @FindBy(className = "reserve-button")
    WebElement reserveBtn;
    @FindBy(id = "reserved-banner")
    WebElement successAlert;

    public AdventureDetailPage(ChromeDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void bookAdventure(String name, String date, String count) throws InterruptedException{
        SeleniumWrapper.sendKeys(nameTxtBox, name);
        SeleniumWrapper.sendKeys(selectDate, date);
        SeleniumWrapper.sendKeys(personCount, count);
        SeleniumWrapper.click(reserveBtn, driver);
        Thread.sleep(3000);
    }

    public boolean isBookingSuccessful(){
        if(successAlert.getText().contains("Greetings! Reservation for this adventure is successful")){
            return true;
        }else
        return false;
    }
}
