package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id=\"input-email\"]")
    WebElement userName;

    @FindBy(xpath ="//input[@id=\"input-password\"]")
    WebElement password;

    @FindBy(xpath = "//input[@value=\"Login\"]")
    WebElement loginButton;



    public void setUserName(String username){
        userName.sendKeys(username);
    }

    public void setPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickLogin(){
        loginButton.click();
    }
}
