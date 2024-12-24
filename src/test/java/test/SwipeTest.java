package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

public class SwipeTest  extends AndroidBase {

    @Test
    public void swipeTest(){

        WebElement viewsEle =driver.findElement(AppiumBy.accessibilityId("Views"));
        viewsEle.click();

        WebElement galleryEle =driver.findElement(AppiumBy.accessibilityId("Gallery"));
        galleryEle.click();

        WebElement photosEle =driver.findElement(AppiumBy.accessibilityId("1. Photos"));
        photosEle.click();

        WebElement firstPhoto =driver.findElement(By.xpath("//android.widget.Gallery[@resource-id='io.appium.android.apis:id/gallery']/android.widget.ImageView[1]"));
        firstPhoto.click();

        Assert.assertEquals(firstPhoto.getAttribute("focusable"),"true");

        swipeGesture(firstPhoto,"left");

        try{
            Assert.assertEquals(firstPhoto.getAttribute("focusable"),"false");
        }catch(StaleElementReferenceException e){
            firstPhoto =driver.findElement(By.xpath("//android.widget.Gallery[@resource-id='io.appium.android.apis:id/gallery']/android.widget.ImageView[1]"));
            Assert.assertEquals(firstPhoto.getAttribute("focusable"),"false");

        }



    }
}
