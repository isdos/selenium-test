package isdos;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main( String[] args ) throws Exception{
        WebDriver driver = new FirefoxDriver();
        String webAddr = args[0];
        String item = args[1];
        driver.get(webAddr);

        String workDir = System.getProperty("user.dir") + "\\screenshot.png";
        WebElement element = driver.findElement(By.id("header-search"));
        element.sendKeys(item);
        element.submit();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String price = driver.findElement(By.className("price")).getText();

        File source_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source_file, new File(workDir));


        driver.quit();
        System.out.println("\n The price of phone is " + price);
    }
}
