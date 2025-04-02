package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{
    public AccountRegisterPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@value='0']")
    WebElement radioButton;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement agreeCheckbox;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueButton;

    @FindBy(xpath ="//div[@class=\"col-sm-9\"]//h1")
    WebElement msgConfirmation;


    public void setFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void setLastName(String lName){
        lastName.sendKeys(lName);
    }

    public void setEmailName(String eMail){
        email.sendKeys(eMail);
    }
    public void setTelephone(String tPhone){
        telephone.sendKeys(tPhone);
    }

    public void setPassword(String pwd){
        password.sendKeys(pwd);
    }
    public void setConfirmPassword(String pwd){
        confirmPassword.sendKeys(pwd);
    }
    public void setRadioButton(){
        if(!radioButton.isSelected()){
            radioButton.click();
        }
    }
    public void setAgreeCheckbox(){
        agreeCheckbox.click();
    }

    public void clickCountinue(){
        continueButton.click();
    }

    public String msgConf(){
        try {
            System.out.println(msgConfirmation.getText());
            return msgConfirmation.getText();

        }
        catch (Exception e){
            return (e.getMessage());
        }

    }




}
