package base;

import com.sun.org.apache.xpath.internal.operations.And;
import drivers.AndroidDriverInit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePageObject extends AndroidDriverInit {
    public AppiumDriver<AndroidElement> getDriver(){
        return driver;
    }
    public AndroidElement find(By locator){
        return getDriver().findElement(locator);
//        return getDriver().findElement(locator);
    }
    public void click(By locator){
        find(locator).click();
    }
    public void input(By locator, String text){
        AndroidElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator){
        return find(locator).getText();
    }

}
