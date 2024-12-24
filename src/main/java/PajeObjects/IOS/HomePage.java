package PajeObjects.IOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
public class HomePage {

    IOSDriver driver;

    public HomePage(IOSDriver driver){
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);


    }

    @iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='Message'`]")
    private WebElement messageEle;




}
