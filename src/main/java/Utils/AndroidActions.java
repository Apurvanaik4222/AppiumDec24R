package Utils;

import com.google.common.collect.ImmutableMap;
import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;

public class AndroidActions extends AppiumUtils {
    AndroidDriver driver;
    WebDriverWait wait;

    public AndroidActions(AndroidDriver driver){
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    private WebElement title;







    public void VerifyPageTitle( String titleValue) {
        try {
            wait.until(ExpectedConditions.attributeContains(title, "text", titleValue));
        }catch (StaleElementReferenceException e){
            wait.until(ExpectedConditions.attributeContains(title, "text", titleValue));
        }
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
        //Assert.assertTrue(flag);
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
