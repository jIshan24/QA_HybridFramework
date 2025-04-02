package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegisterTest extends BaseClass {

    @Test(groups = "Regression")
    public void verify_account_Register() {
        logger.info("*--Starting TC001_AccountRegisterTest--*");

        try {
            HomePage hp = new HomePage(driver);
            AccountRegisterPage accRegPage = new AccountRegisterPage(driver);

            hp.clickMyAccount();
            logger.info("*Clicked on MyAccount Link*");

            hp.clickRegister();

            accRegPage.setFirstName("John");
            accRegPage.setLastName("Wick");
            accRegPage.setEmailName(randomString()+"@dell.com");
            accRegPage.setTelephone("0123007321");

            String pass = randomString()+"@"+randomNumber();
            accRegPage.setPassword(pass);
            accRegPage.setConfirmPassword(pass);

            accRegPage.setRadioButton();
            accRegPage.setAgreeCheckbox();

            accRegPage.clickCountinue();

            logger.info("*Validating expected message*");
            String cnfMessage =  accRegPage.msgConf();
            logger.info("Actual confirmation message: " + cnfMessage);

            Assert.assertEquals(cnfMessage,"Your Account Has Been Created!");
        }
        catch (Exception e) {
            logger.error("Test Failed");
            logger.debug("Debug Logs");
            Assert.fail("Account already registered!");
        }

        logger.info("*--Finished TC001_AccountRegisterTest--*");
    }


}
