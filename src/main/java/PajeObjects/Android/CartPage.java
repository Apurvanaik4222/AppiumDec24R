package PajeObjects.Android;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;
    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private   List<WebElement> CartProductNames;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private   List<WebElement> productPrices;

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement TotalPrice;

    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement tandCLink;

    @AndroidFindBy(id="android:id/button1")
    private WebElement cancelBtn;

    @AndroidFindBy(className="android.widget.CheckBox")
    private WebElement chcBx;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement purchaseLink;

    public List<String> verifyProductInCart() {
        List<String> actualProductList = new ArrayList<String>();
        for (int i = 0; i < CartProductNames.size(); i++) {
            actualProductList.add(CartProductNames.get(i).getText());
        }
        return actualProductList;

    }

    public double getTotalcartPrice() {
        double sum = 0;
        for (int i = 0; i < productPrices.size(); i++) {
            sum = sum + Double.parseDouble(productPrices.get(i).getText().substring(1));
        }
        return sum;
    }


    public double getExpectedCartPrice() {
   return Double.parseDouble(TotalPrice.getText().substring(1));
    }

    public void clickTandC() {
        longClickGesture(tandCLink);
        cancelBtn.click();
    }

    public void clickOnPurchaseLink(){
        chcBx.click();
        purchaseLink.click();
    }








}
