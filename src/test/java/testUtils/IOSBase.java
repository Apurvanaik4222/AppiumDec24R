package testUtils;

import Utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class IOSBase extends AppiumUtils {
    AppiumDriverLocalService service;
    public IOSDriver driver;
    WebDriverWait wait;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws URISyntaxException, IOException {
        FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
        Properties properties =new Properties();
        properties.load(fis);

        appiumStartUp(properties.getProperty("ipAddress"),Integer.parseInt(properties.getProperty("port")));


        XCUITestOptions options =new XCUITestOptions();
        options.setDeviceName("Iphone 16 Pro Max");
        options.setPlatformVersion("16.2");
        options.setApp("C:\\Users\\91762\\IdeaProjects\\AppiumDec24R\\src\\test\\java\\resources\\General-Store.app");
        options.setWdaLaunchTimeout(Duration.ofSeconds(5));


        driver =new IOSDriver(service.getUrl(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         wait =new WebDriverWait(driver,Duration.ofSeconds(5));
    }



    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        service.stop();
    }


    public static void AppiumOperation(String fileName,String expectedVal) throws  InterruptedException, IOException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 30);
        long stopTime = cal.getTimeInMillis();
        long startTime = System.currentTimeMillis();
        boolean flag = false;
        String file = "output.txt";
        try {
            File f = new File(file);
            f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start " + fileName);
        // runtime.exec("cmd /c start dockerUp.bat");
        //Thread.sleep(3000);


        while (startTime < stopTime) {
            if (flag) {
                break;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains(expectedVal)) {
                    System.out.println("Found the text");
                    flag = true;
                    break;
                }
                currentLine = reader.readLine();

            }
            reader.close();

        }
        Assert.assertTrue(flag);
        System.out.println(fileName + ":Operation completed successfully");
    }


    public void touchandHoldGesture(WebElement element) {
        Map<String,Object> map =new HashMap<>();
        map.put("element",((RemoteWebElement)element).getId());
        map.put("duration",2000);

        ((JavascriptExecutor) driver).executeScript("mobile:touchAndHold", map);
    }



        public void scrollGesture(WebElement element ,String direction){

            Map<String,Object> map =new HashMap<>();
            map.put("element",((RemoteWebElement)element).getId());
            map.put("direction",direction);

            ((JavascriptExecutor) driver).executeScript("mobile:scroll", map);



    }

    public void swipeGesturewithValue(WebElement element,String percentage){
        element.sendKeys(percentage);
        Assert.assertTrue(element.getAttribute("value").equals(percentage));


    }



    public void launchApp(String bundleId){

        Map<String,Object> map =new HashMap<>();
        map.put("bundleId",bundleId);
        //com.apple.mobileslideshow

        ((JavascriptExecutor) driver).executeScript("mobile:launchApp", map);



    }



}
