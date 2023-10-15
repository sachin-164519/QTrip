package Qtrip_QA.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    public void bookAdventure(String name, String date, String count){

    }

    public boolean isBookingSuccessful(){
        return false;
    }
}
