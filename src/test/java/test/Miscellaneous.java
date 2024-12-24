package test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

public class Miscellaneous extends AndroidBase {

    @Test
    public void test1(){
        startActivity("io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies");

        WebElement wifiChkBx =driver.findElement(By.id("android:id/checkbox"));
        wifiChkBx.click();

        DeviceRotation deviceRotation =new DeviceRotation(0,0,90);
        driver.rotate(deviceRotation);

        WebElement wifiSettingsEle =driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']"));
        wifiSettingsEle.click();

        WebElement wifiSettingTextEle =driver.findElement(By.id("android:id/alertTitle"));
        Assert.assertEquals(wifiSettingTextEle.getText(),"WiFi settings");

        //ClipBoard
        driver.setClipboardText("Apurva");

        WebElement wifiTextBx =driver.findElement(By.xpath("//android.widget.EditText[@resource-id='android:id/edit']"));
        wifiTextBx.sendKeys(driver.getClipboardText());

        WebElement okBtn =driver.findElement(By.id("android:id/button1"));
        okBtn.click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));




    }
}
