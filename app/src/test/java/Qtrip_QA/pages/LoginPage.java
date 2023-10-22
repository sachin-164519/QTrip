package Qtrip_QA.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Qtrip_QA.SeleniumWrapper;

public class LoginPage {
    ChromeDriver driver;
     String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";
    @FindBy(name = "email")
    WebElement username_txt_box;
    @FindBy(name = "password")
    WebElement password_txt_box;
    @FindBy(xpath="//button[contains(@class,'btn-login')]")
    WebElement loginBtn;

    public LoginPage(ChromeDriver driver){
        this.driver =driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToLoginPage(){
        SeleniumWrapper.navigate(driver, url);
    }

    public void performLogin(String username, String password) throws InterruptedException{
        SeleniumWrapper.sendKeys(username_txt_box, username);
        SeleniumWrapper.sendKeys(password_txt_box, password);
        SeleniumWrapper.click(loginBtn, driver);
        Thread.sleep(3000);
    }
}
