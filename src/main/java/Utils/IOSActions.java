package Utils;


import com.google.common.annotations.VisibleForTesting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class IOSActions extends AppiumUtils{
    IOSDriver driver;

    public IOSActions(IOSDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
this.driver =driver;
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

    public void swipeGesturewithValue(WebElement element, String percentage){
        element.sendKeys(percentage);

        Assert.assertEquals(element.getAttribute("value").equals(percentage), true);


    }



    public void launchApp(String bundleId){

        Map<String,Object> map =new HashMap<>();
        map.put("bundleId",bundleId);
        //com.apple.mobileslideshow

        ((JavascriptExecutor) driver).executeScript("mobile:launchApp", map);



    }


}
