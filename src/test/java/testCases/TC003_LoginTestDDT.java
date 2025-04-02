package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass {



    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "DDT")
    public void verify_loginDDT_test(String username, String pwd, String expRes){

        try {
            logger.info("*--Starting TC003_LoginDDTTest--*");

            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            MyAccountPage map = new MyAccountPage(driver);
            DataProviders dp = new DataProviders();

            logger.info("*--Navigating to MyAccount & clicking on Login--*");
            hp.clickMyAccount();
            hp.clickLogin();

            logger.info("*--Data getting entered into fields from Excel--*");
            lp.setUserName(username);
            lp.setPassword(pwd);

            logger.info("*--Clicking Login--*");
            lp.clickLogin();

            logger.info("*--Checking Positive and Negative cases --*");
            if(expRes.equalsIgnoreCase("valid")){
                if (map.cnfLoginCheck()){
                    map.clickLogoutButton();
                    Assert.assertTrue(true,"Valid Credentials --- Login Successful");
                }
                else {
                    Assert.fail("Valid Credentials --- Login Failed");
                }
            }

            if (expRes.equalsIgnoreCase("invalid")){
                if (map.cnfLoginCheck()){
                    map.clickLogoutButton();
                    Assert.fail("Invalid Credentials --- Login Successful");
                }
                else {
                    Assert.assertTrue(true,"Invalid Credentials --- Login Failed");
                }
            }



            //Assert.assertTrue(map.cnfLoginCheck());
        }
        catch (Exception e){
            logger.error("Test Failed");
            logger.debug("Debug Logs");
            Assert.fail();
        }

        logger.info("*--Finished TC003_LoginDDTTest--*");
    }


}
