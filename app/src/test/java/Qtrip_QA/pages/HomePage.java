package Qtrip_QA.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    ChromeDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    @FindBy(xpath = "//a[contains(@class,'login register')]")
    WebElement registerBtn;
    @FindBy(xpath = "//div[contains(text(),'Logout')]")
    WebElement logoutBtn;
    @FindBy(className = "hero-input")
    WebElement searchTextBox;
    @FindAll({@FindBy(xpath = "//*[@id='results']/a"),
            @FindBy(xpath = "//*[@id='results']/h5")})
    WebElement searchBox;

    public HomePage(ChromeDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToHomePage(){

    }

    public void clickRegister(){

    }

    public boolean isUserLoggedIn(){
        return false;
    }

    public boolean logOutUser(){
        return false;
    }

    public void searchCity(){

    }

    public String getAutoCompleteText(){
        return "abc";
    }

    public void selectCity(String city){
        
    }

}
