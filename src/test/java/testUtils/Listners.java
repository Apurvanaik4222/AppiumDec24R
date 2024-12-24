package testUtils;

import Utils.AppiumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Listners  extends AppiumUtils implements ITestListener {
   ExtentReports extent = ExtentReporting.getExtentObj();
   static ExtentTest test;

    public Listners() throws FileNotFoundException {
    }

    @Override
    public void onTestStart(ITestResult result) {
      test = extent.createTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
      test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
     test.log(Status.FAIL,result.getThrowable());
        try {
            driver =  (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String fpath =null;
        try {
            fpath = takeScreenShoot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.log(Status.INFO,"Image", MediaEntityBuilder.createScreenCaptureFromPath(fpath).build());
        //test.addScreenCaptureFromPath(fpath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
      extent.flush();
    }
}
