package Qtrip_QA.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Qtrip_QA.SeleniumWrapper;

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
        SeleniumWrapper.navigate(driver, url);
    }

    public void clickRegister(){
        SeleniumWrapper.click(registerBtn, driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.or(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/register/")));
    }

    public boolean isUserLoggedIn(){
        if(logoutBtn.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    public boolean logOutUser(){
        try{
            SeleniumWrapper.click(logoutBtn, driver);
            Thread.sleep(3000);
            return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
    }

    public void searchCity(String city) throws InterruptedException{
        if(city!=null && !city.isEmpty()){
            Thread.sleep(3000);
            SeleniumWrapper.sendKeys(searchTextBox, city);
        }else{
            System.out.println(city+" is not valid");
        }
    }

    public String getAutoCompleteText(){
        return searchBox.getText();
    }

    public void selectCity(String city) throws InterruptedException{
        if(this.getAutoCompleteText().contains(city)){
            searchBox.click();
            Thread.sleep(3000);
        }
    }

}
