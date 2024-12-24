package testUtils;

import PajeObjects.Android.FormPage;
import Utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
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
import java.util.Properties;

public class AndroidBase  extends AppiumUtils {
    public static AndroidDriver driver;
    public WebDriverWait wait;
    public FormPage formPage;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws URISyntaxException, IOException {
        FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
        Properties properties =new Properties();
        properties.load(fis);
        String ipAddress =System.getProperty("ipAddress")!=null?System.getProperty("ipAddress"):properties.getProperty("ipAddress");
        int port =System.getProperty("port")!=null?Integer.parseInt(System.getProperty("port")):Integer.parseInt(properties.getProperty("port"));

        appiumStartUp(ipAddress,port);

        UiAutomator2Options options =new UiAutomator2Options();
        options.setDeviceName("GoogleTestL");
        options.setApp("C:\\Users\\91762\\IdeaProjects\\AppiumDec24R\\src\\test\\java\\resources\\General-Store.apk");
        options.setChromedriverExecutable(System.getProperty("user.dir")+"\\Executables\\chromedriver.exe");
        driver =new AndroidDriver(service.getUrl(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         wait =new WebDriverWait(driver,Duration.ofSeconds(5));
         formPage =new FormPage(driver);
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


    public void longClickGesture(WebElement element) {

        ((JavascriptExecutor) driver).executeScript("mobile:longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", 2000

        ));
    }

        public void scrollByText(String txt){
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+txt+"\"));"));

        }

        public Boolean scrollGesture(String direction){

      Boolean flag= (Boolean)((JavascriptExecutor)driver).executeScript("mobile:scrollGesture",ImmutableMap.of(
                    "left",100,"top",100,"width",200,"height",200,
                    "direction",direction,
                    "percent",3.0
            ));
      return flag;


    }

    public void swipeGesture(WebElement element,String direction) {

        ((JavascriptExecutor) driver).executeScript("mobile:swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.1

        ));
    }

    public void dragGesture(WebElement element ,double x, double y){

        ((JavascriptExecutor) driver).executeScript("mobile:dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", x,
                "endY", y

        ));


    }

    public void startActivity(String packandActivityName){

        ((JavascriptExecutor)driver).executeScript("mobile: startActivity",ImmutableMap.of(
                "intent",packandActivityName
        ));
    }



}
