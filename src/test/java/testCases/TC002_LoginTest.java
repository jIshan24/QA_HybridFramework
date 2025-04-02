package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {


    @Test(groups = "Sanity")
    public void verify_login_test(){
        logger.info("*--Starting TC002_LoginTest--*");
        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            MyAccountPage map = new MyAccountPage(driver);

            hp.clickMyAccount();
            hp.clickLogin();

            lp.setUserName(pro.getProperty("email"));
            lp.setPassword(pro.getProperty("password"));

            lp.clickLogin();

            Assert.assertTrue(map.cnfLoginCheck());
        }
        catch (Exception e){
            logger.error("Test Failed");
            logger.debug("Debug Logs");
            Assert.fail();
        }


    }


}
