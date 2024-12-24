package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShoppingCartE2E  extends AndroidBase {

    @Test
    public void E2ETest() throws InterruptedException {

        List<String> productsToBeAdded = new ArrayList<String>();
        productsToBeAdded.add("Jordan 6 Rings");
        productsToBeAdded.add("PG 3");

        WebElement countryDropDownEle =driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
        countryDropDownEle.click();
        selectCountry("Argentina");

        WebElement nameEle =driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameEle.sendKeys("Radhika");

        selectGender("Female");

        WebElement shopBtnEle =driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        shopBtnEle.click();

       try {
           WebElement title = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
           waitPageToLoad(title, "text", "Products");
       }catch (StaleElementReferenceException e) {
           WebElement title = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
           waitPageToLoad(title, "text", "Products");
       }


       try {
           List<WebElement> productNameList = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
           addProductToCart(productsToBeAdded.get(0), productNameList);
       }catch (StaleElementReferenceException e){
              List<WebElement> productNameList = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
              addProductToCart(productsToBeAdded.get(0), productNameList);
       }

        try {
            List<WebElement> productNameList = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
            addProductToCart(productsToBeAdded.get(1), productNameList);
        }catch (StaleElementReferenceException e){
            List<WebElement> productNameList = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
            addProductToCart(productsToBeAdded.get(1), productNameList);
        }

        WebElement cartLink = driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart"));
        cartLink.click();

        //Cart page Title Check

        try {
            WebElement title = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
            waitPageToLoad(title, "text", "Cart");
        }catch (StaleElementReferenceException e) {
            WebElement title = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
            waitPageToLoad(title, "text", "Cart");
        }

        List<WebElement> CartProductNames =driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
        List<String> cartList =verifyProductInCart(CartProductNames);

        Assert.assertTrue(cartList.equals(productsToBeAdded));

        List<WebElement> productprices =driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        double totalCartPrice =getTotalcartPrice(productprices);

      double expectedPrice = Double.parseDouble(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1));

Assert.assertEquals(totalCartPrice,expectedPrice);


WebElement termsAndConditions =driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
longClickGesture(termsAndConditions);
WebElement cancelBtn =driver.findElement(By.id("android:id/button1"));
cancelBtn.click();

WebElement chcBx =driver.findElement(AppiumBy.className("android.widget.CheckBox"));
chcBx.click();

        WebElement purchaseLink =driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
        purchaseLink.click();

        Thread.sleep(6000);
       Set<String> set = driver.getContextHandles();
       set.stream().forEach(s->System.out.println(s));

       driver.context("WEBVIEW_com.androidsample.generalstore");

         driver.findElement(By.name("q")).sendKeys(Keys.SHIFT,"Apurva");
driver.findElement(By.name("q")).sendKeys(Keys.ENTER);













    /*    WebElement peopleNameAdpEle =driver.findElement(By.xpath(""));

        WebElement customerAdpEle1 =driver.findElement(AppiumBy.accessibilityId(""));
        customerAdpEle.click();

        WebElement peopleNameAdpEle2 =driver.findElement(By.xpath(""));*/


    }

    public void selectCountry(String country){

        WebElement countryEle =driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='"+country+"']"));
        scrollByText(country);
        countryEle.click();
    }

    public void selectGender(String gender){
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='"+gender+"']")).click();
    }


    public void waitPageToLoad(WebElement ele,String attribute,String value){
wait.until(ExpectedConditions.attributeContains(ele,attribute,value));
    }


    public void addProductToCart(String productName,List<WebElement> ele){
        scrollByText(productName);
        for(int i=0;i<ele.size();i++){
            if(ele.get(i).getText().equalsIgnoreCase(productName)){
            driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

    }

    public List<String> verifyProductInCart(List<WebElement> list){
        List<String> actualProductList =new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            actualProductList.add(list.get(i).getText());
        }
        return actualProductList;

    }

    public double getTotalcartPrice(List<WebElement> list){
        double sum=0;
        for(int i=0;i<list.size();i++){
           sum =sum + Double.parseDouble(list.get(i).getText().substring(1));
        }
        return sum;
    }
}
