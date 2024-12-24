package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {
    public AppiumDriver driver;
    public AppiumDriverLocalService service;;



    public Boolean waitforelementTobeDisplayed(WebElement ele){
        Boolean flag =ele.isDisplayed();
        return flag;

        //code to wait for element to be displayed
    }

    public List<HashMap<String,String>> jsonToMap(String jsonPath) throws IOException {


        String jsonContent =FileUtils.readFileToString(new File(jsonPath),Charsets.UTF_8);

        ObjectMapper mapper =new ObjectMapper();

       List<HashMap<String,String>> list = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){

        });
       return list;




    }

    public void appiumStartUp(String ipAddress,int port){

        service  =new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\91762\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
    }

    public String takeScreenShoot(String testCaseName,AppiumDriver driver) throws IOException {

        Date date =new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
       String formattedDate = format.format(date);
      System.out.println(formattedDate);
       File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String DestinationFile = System.getProperty("user.dir")+"\\target\\ExtentReports\\"+testCaseName+formattedDate+".PNG";

       FileUtils.copyFile(sourceFile,new File(DestinationFile));
       System.out.println(DestinationFile);
       return DestinationFile;

    }


}
