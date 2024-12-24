package test;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;
import testUtils.IOSBase;

public class IOSBasics extends IOSBase
{
    @Test
    public void test1(){

        //   WebElement wifiSettingsEle =driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']"));


        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name=='Message'`]"));

        driver.findElement(AppiumBy.iOSNsPredicateString("name=='Apurva' AND type BEGINSWITH[c] 'Test'"));

    }
}
