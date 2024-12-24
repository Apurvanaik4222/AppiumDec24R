package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

public class DragTest extends AndroidBase {

    @Test
    public void dragTest() throws InterruptedException {

        WebElement viewsEle =driver.findElement(AppiumBy.accessibilityId("Views"));
        viewsEle.click();

        WebElement dragEle =driver.findElement(AppiumBy.accessibilityId("Drag and Drop"));
        dragEle.click();

        WebElement sourceEle =driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
      dragGesture(sourceEle,617,555);

      Thread.sleep(2000);

        WebElement result =driver.findElement(By.id("io.appium.android.apis:id/drag_result_text"));
        Assert.assertEquals(result.getText(),"Dropped!");

    }

}
