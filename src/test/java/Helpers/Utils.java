package Helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class Utils {


    public static void ClickOnElement(WebDriver driver, WebDriverWait wait, By Element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
        driver.findElement(Element).click();
    }

    public static void waitVisibilityOfElement(WebDriverWait wait, By Element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
    }
    public static void takeScreenShot(WebDriver driver, Method method) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        LocalDate currentDateTime = LocalDate.now();
        FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/ScreenShots/"+method.getName() + currentDateTime +".png"));
    }
}
