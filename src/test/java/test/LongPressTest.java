package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

public class LongPressTest extends AndroidBase {

    @Test
    public void test1() throws InterruptedException {
        WebElement viewsEle =driver.findElement(AppiumBy.accessibilityId("Views"));
        viewsEle.click();

        WebElement expandleListEle =driver.findElement(AppiumBy.accessibilityId("Expandable Lists"));
        expandleListEle.click();

        WebElement customerAdpEle =driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter"));
        customerAdpEle.click();

        WebElement peopleNameAdpEle =driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longClickGesture(peopleNameAdpEle);

        WebElement samplemenuEle =driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Sample menu']"));
        Assert.assertEquals(samplemenuEle.getText(),"Sample menu");

        Thread.sleep(2000);





    }

}
