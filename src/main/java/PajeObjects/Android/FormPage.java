package PajeObjects.Android;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {

    AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropDownEle;

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameEle;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopBtnEle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Argentina']")
    private WebElement countryEle;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement male;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement female;


    public void selectCountry(String country){
        countryDropDownEle.click();
    scrollByText(country);
        countryEle.click();
    }

    public void enterName(String name){
        nameEle.sendKeys(name);
    }

    public void selectGender(String gender){
       if(gender.equalsIgnoreCase("Male"))
           male.click();
       else
           female.click();


    }
    public ProductPage clickShopBtn(){
        shopBtnEle.click();
        VerifyPageTitle("Products");
        return new ProductPage(driver);
    }


}
