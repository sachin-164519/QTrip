package Qtrip_QA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWrapper {
    public static boolean click(WebElement elementToClick, ChromeDriver driver){
        if(elementToClick.isDisplayed()){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView;", elementToClick);
            elementToClick.click();
            return true;
        }else{
            return false;
        }
    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend){
        if(inputBox.isDisplayed()){
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        }else
            return false;
    }

    public static WebElement findElementWithRetry(ChromeDriver driver, By by, int reCount){
        WebElement element = null;
        for(int i=1; i<reCount;i++){
            try{
                element = driver.findElement(by);
                if(element.isDisplayed()){
                    return element;
                }
            }catch (Exception e){
                element = null;
            }
        }
        return element;
    }
}
