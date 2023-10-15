package Qtrip_QA.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventurePage {
    ChromeDriver driver;
     @FindBy(id ="duration-select")
    WebElement filterDrpDwn;
    @FindBy(id ="category-select")
    WebElement categoryDrpDwn;
    @FindBy(xpath ="//div[@class='activity-card']")
    List<WebElement> resultCount;
    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement clearFilter;
    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement clearCategory;
    @FindBy(id = "search-adventures")
    WebElement searchAdventure;
    @FindBy(xpath = "//div[@class='activity-card']/div/div/h5")
    WebElement adventureResult;

    public AdventurePage(ChromeDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void setFilterValue(){

    }

    public void setCategoryValue(){

    }

    public void getResultCount(){

    }

    public void selectAdventure(){
        
    }
}
