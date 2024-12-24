package PajeObjects.Android;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends AndroidActions {

    AndroidDriver driver;
    public ProductPage(AndroidDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName1']")
    private   List<WebElement> productNameList;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
    private   List<WebElement> addToCartLink;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartLink;






    public void addProductToCart(String productName) {
        scrollByText(productName);
        for (int i = 0; i < productNameList.size(); i++) {
            if (productNameList.get(i).getText().equalsIgnoreCase(productName)) {
                addToCartLink.get(i).click();
            }
        }

    }

    public CartPage clickCartLink(){
        cartLink.click();
        VerifyPageTitle("Cart");
        return new CartPage(driver);
    }

}
