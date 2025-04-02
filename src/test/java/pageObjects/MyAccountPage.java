package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//h2[normalize-space()=\"My Account\"]")
    WebElement cnfLogin;

    @FindBy(xpath = "//a[@class=\"list-group-item\" and text() = 'Logout']")
    WebElement logoutButton;
    public boolean cnfLoginCheck(){
        try {
            cnfLogin.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogoutButton(){
        logoutButton.click();
    }
}
