package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()=\"My Account\"]")
    WebElement myAccountLink;

    @FindBy(xpath = "//a[normalize-space()=\"Register\"]")
    WebElement registerlink;

    @FindBy(xpath = "//ul[contains(@class,'dropdown')]//a[normalize-space()=\"Login\"]")
    WebElement loginLink;

    public void clickMyAccount(){
        myAccountLink.click();
    }

    public void clickRegister(){
        registerlink.click();
    }
    public void clickLogin(){
        loginLink.click();
    }
}
