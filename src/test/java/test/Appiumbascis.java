package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Appiumbascis extends AndroidBase {

    @Test
    public void Test1() throws URISyntaxException, MalformedURLException {
        WebElement prefernceEle =driver.findElement(AppiumBy.accessibilityId("Preference"));
        prefernceEle.click();

        WebElement prefernceDepEle =driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies"));
        prefernceDepEle.click();

        WebElement wifiChkBx =driver.findElement(By.id("android:id/checkbox"));
        wifiChkBx.click();

        WebElement wifiSettingsEle =driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']"));
        wifiSettingsEle.click();

        WebElement wifiSettingTextEle =driver.findElement(By.id("android:id/alertTitle"));
        Assert.assertEquals(wifiSettingTextEle.getText(),"WiFi settings");

        WebElement wifiTextBx =driver.findElement(By.xpath("//android.widget.EditText[@resource-id='android:id/edit']"));
        wifiTextBx.sendKeys("Apurva");

        WebElement okBtn =driver.findElement(By.id("android:id/button1"));
        okBtn.click();





    }
}
