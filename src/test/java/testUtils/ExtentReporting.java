package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExtentReporting {

    public static ExtentReports getExtentObj() throws FileNotFoundException {

        File file =new File(System.getProperty("user.dir")+"\\target\\ExtentReports\\index.html");
        ExtentSparkReporter reporter =new ExtentSparkReporter(file);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Appium Automation");
        reporter.config().setReportName("Appium Automation Report");
        ExtentReports extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Apurva");
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("Browser","Chrome");
        return extent;


    }


}
