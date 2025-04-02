package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class ExtentReportUtility implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String reportName;

    public void onStart(ITestContext context){

        String timeStamp = new SimpleDateFormat("yyyy.MM. dd. HH.mm. ss").format(new Date());
        reportName = "Test-Report-"+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Submodule", "Customer");
        extent.setSystemInfo("Username",  System.getProperty("user.name"));

        String os = context.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("Operating System",os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser",browser);

        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups",includedGroups.toString());
        }


    }

    public void onTestSuccess(ITestResult testResult){
        test = extent.createTest(testResult.getTestClass().getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.log(Status.PASS,testResult.getName()+" got successfully executed");
    }

    public void onTestFailure(ITestResult testResult){
        test = extent.createTest(testResult.getTestClass().getName());
        test.assignCategory(testResult.getMethod().getGroups());
        test.log(Status.FAIL,testResult.getName()+" got failed");
        test.log(Status.INFO,testResult.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(testResult.getName());
            test.addScreenCaptureFromPath(imgPath);
        }
        catch (Exception ioEx){
            ioEx.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

/*

            try {
                URL url = new
                URL("file:///"+System.getProperty("user.dir")+"\reports\\"+repName);

                // Create the email message
                ImageHtmlEmail email = new ImageHtmlEmail();
                email. setDataSourceResolver(new DataSourceUrlResolver(url));

                email. setHostName("smtp.googlemail.com");
                email.setSmtpPort(465);
                email. setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
                email. setSSLOnConnect(true);
                email.setFrom("pavanoltraining@gmail.com"); //Sender
                email. setSubject("Test Results");
                email. setMsg("Please find Attached Report .... ");
                email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
                email.attach(url, "extent report", "please check report ... ");
                email.send(); // send the email
            }
            catch(Exception e) {
                e.printStackTrace();
            }
*/

