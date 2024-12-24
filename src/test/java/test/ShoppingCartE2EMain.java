package test;

import PajeObjects.Android.CartPage;
import PajeObjects.Android.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testUtils.AndroidBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ShoppingCartE2EMain extends AndroidBase {

    @Test(dataProvider="getData")
    public void E2ETest(HashMap<String,String> map) throws InterruptedException {

        List<String> productsToBeAdded = new ArrayList<String>();
        productsToBeAdded.add(map.get("product1"));
        productsToBeAdded.add(map.get("product2"));
        formPage.selectCountry(map.get("country"));
        formPage.enterName(map.get("name"));
        formPage.selectGender(map.get("gender"));
        ProductPage productPage =formPage.clickShopBtn();
        productPage.addProductToCart(productsToBeAdded.get(0));
        productPage.addProductToCart(productsToBeAdded.get(1));
       CartPage cartPage= productPage.clickCartLink();
        List<String> cartList =cartPage.verifyProductInCart();

        Assert.assertTrue(cartList.equals(productsToBeAdded));
        double totalCartPrice =cartPage.getTotalcartPrice();
        double expectedPrice =cartPage.getExpectedCartPrice();
        Assert.assertEquals(totalCartPrice, expectedPrice);

        cartPage.clickTandC();
        cartPage.clickOnPurchaseLink();


        Thread.sleep(8000);
        Set<String> set = driver.getContextHandles();
        set.stream().forEach(s -> System.out.println(s));

        driver.context("WEBVIEW_com.androidsample.generalstore");

        driver.findElement(By.name("q")).sendKeys(Keys.SHIFT, "Apurva");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);


    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBeforeMethod() {
        startActivity(" com.google.android.apps.nexuslauncher/com.google.android.apps.nexuslauncher.NexusLauncherActivity");
    }



    @DataProvider
    public Object[][] getData() throws IOException {
      List<HashMap<String,String>> list =  jsonToMap(System.getProperty("user.dir") + "\\src\\test\\java\\dataFiles\\productCart.json");

      return new Object[][]  {{list.get(0)}};


    }



    }



