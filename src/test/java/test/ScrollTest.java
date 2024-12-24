package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

public class ScrollTest extends AndroidBase {

    @Test
    public void scroll_Text(){

        WebElement viewsEle =driver.findElement(AppiumBy.accessibilityId("Views"));
        viewsEle.click();
        scrollByText("WebView");
    }


    @Test
    public void scroll(){
        WebElement viewsEle =driver.findElement(AppiumBy.accessibilityId("Views"));
        viewsEle.click();

        boolean flag =false;
        do {
            flag = scrollGesture( "down");
        }while(flag);




    }
}
