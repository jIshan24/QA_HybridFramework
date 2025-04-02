package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public static Logger logger;
    public Properties pro;

    /*public BaseClass() {
        logger = LogManager.getLogger(this.getClass());
        System.out.println("🚀Logger initiated");
    }*/


    //@BeforeClass(groups = {"Sanity","Regression"})
    @BeforeClass(alwaysRun = true)
    @Parameters({"OS","browser"})
    public void setUp(String os,String browser) throws Exception {
        //System.out.println("setUp() Method Executed");
        //Config file loading
        //FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")+"resources\\config.properties");
        FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
        pro = new Properties();
        pro.load(fr);

        logger = LogManager.getLogger(this.getClass());

        System.out.println("🚀 BaseClass setUp() executed!");
        logger.info("Logger initialized successfully.");

        //This is IF-ELse block is for Selenium Grid Set up when we are fetching values form properties file
        if (pro.getProperty("execution_env").equalsIgnoreCase("remote")){

            String url = "http://localhost:4444/wd/hub";

            DesiredCapabilities desCap = new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")){  //We are getting this details from the XML so we have to check first
                desCap.setPlatform(Platform.WIN11);          //We can also use Switch
            }
            else if(os.equalsIgnoreCase("linux")){
                desCap.setPlatform(Platform.LINUX);
            }

            switch (browser.toLowerCase()){
                case "chrome"-> desCap.setBrowserName("chrome");
                case "firefox"-> desCap.setBrowserName("firefox");
                case "edge"-> desCap.setBrowserName("edge");
                default -> {
                    System.out.println("invalid Browser");
                    return;
                }
            }

            driver = new RemoteWebDriver(new URL(url),desCap);

        }
        else if (pro.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()){
                case "chrome"-> driver = new ChromeDriver();
                case "firefox"-> driver = new FirefoxDriver();
                case "edge"-> driver = new EdgeDriver();
                default -> {
                    System.out.println("invalid Browser");
                    return;
                }
            }
        }


        /*switch (browser.toLowerCase()){
            case "chrome"->driver = new ChromeDriver();
            case "firefox"-> driver = new FirefoxDriver();
            case "edge"-> driver = new EdgeDriver();
            default -> {
                System.out.println("invalid Browser");
                return;
            }
        }*/
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.get(pro.getProperty("appUrl"));
        driver.manage().window().maximize();

    }

    @AfterClass(alwaysRun = true)
    //@AfterClass(groups = {"Sanity","Regression"})
    public void tearDown(){
        driver.quit();
    }

    public String captureScreen(String title) throws Exception{
        if (driver == null) {
            throw new IllegalStateException("Driver is null! Screenshot cannot be captured.");
        }
        String timeStamp = new SimpleDateFormat("yyyy.MM. dd. HH.mm. ss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+title+"_"+timeStamp+".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }


    public String randomString(){
        String randomStr =  RandomStringUtils.randomAlphabetic(5);
        System.out.println(randomStr);
        return randomStr;
    }

    public String randomNumber(){
        String randumNum = RandomStringUtils.randomNumeric(9);
        return randumNum;
    }
}
