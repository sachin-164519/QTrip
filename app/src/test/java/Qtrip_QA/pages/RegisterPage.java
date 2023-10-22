package Qtrip_QA.pages;

import java.util.UUID;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Qtrip_QA.SeleniumWrapper;

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

    public boolean registerNewUser(String username, String password, Boolean makeUserDyanmic) throws InterruptedException{
        String test_data_username;
        if(makeUserDyanmic){
            test_data_username = username + UUID.randomUUID().toString();
        }else{
            test_data_username = username;
        }
        SeleniumWrapper.sendKeys(username_txt_box, test_data_username);
        SeleniumWrapper.sendKeys(password_txt_box, password);
        SeleniumWrapper.sendKeys(confirmPassword_txt_box, password);
        SeleniumWrapper.click(submitBtn, driver);
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.or(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login/")));
        Thread.sleep(4000);
        this.lastGeneratedUsername = test_data_username;
        return this.driver.getCurrentUrl().endsWith("/login");
    }

    
}
