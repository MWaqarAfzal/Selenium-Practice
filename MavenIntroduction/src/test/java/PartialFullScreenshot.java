import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PartialFullScreenshot {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> windowns = driver.getWindowHandles();
        Iterator<String> ite = windowns.iterator();
        String parentWindow = ite.next();
        String childWindow = ite.next();

        File parentWindowScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(parentWindowScreenshot, new File("parentWindow.png"));

        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/");
        Thread.sleep(3000);
        File childWindowScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(childWindowScreenshot, new File("childWindow.png"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,2000)");
        Thread.sleep(2000);
        List<WebElement> coursesList = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']"));
        File courseScreenshot = coursesList.get(1).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(courseScreenshot, new File("courseScreenshot.png"));
        String courseName = coursesList.get(1).getText();

        driver.switchTo().window(parentWindow);
        Thread.sleep(2000);
        WebElement nameFiled = driver.findElement(By.cssSelector(".form-control[name='name']"));
        nameFiled.sendKeys(courseName);
        File nameFieldScreenshot = nameFiled.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(nameFieldScreenshot, new File("nameFieldScreenshot.png"));

    }
}
