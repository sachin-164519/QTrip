package Qtrip_QA.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
    ChromeDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register";
    public String lastGeneratedUsername = "";

    @FindBy(name="email")
    WebElement username_txt_box;
    @FindBy(name="password")
    WebElement password_txt_box;
    @FindBy(name="confirmpassword")
    WebElement confirmPassword_txt_box;
    @FindBy(xpath = "//button[contains(@class,'btn-login')]")
    WebElement submitBtn;

    public RegisterPage(ChromeDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public boolean registerNewUser(){
        return false;
    }

    
}
