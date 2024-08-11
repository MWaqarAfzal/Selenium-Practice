import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class MultipleTabWindows {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://rahulshettyacademy.com/angularpractice/");
        //      Open new TAB
        driver.switchTo().newWindow(WindowType.TAB);

        //      Open new WINDOW
        //driver.switchTo().newWindow(WindowType.WINDOW);

        //      Get the list of all opened windows
        Set<String> windowns = driver.getWindowHandles();
        //      Iterate and store windows handle in a variable
        Iterator<String> it = windowns.iterator();
        //      Move to the next window
        String parentWindow = it.next();
        String childWindow = it.next();
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/");

        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\wafzal\\screenshot.png"));

        //      Scroll the window
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");
        File src1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src1, new File("C:\\Users\\wafzal\\screenshot1.png"));
        String courses = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']")).get(1).getText();
        //      Move back to the Parent window
        driver.switchTo().window(parentWindow);
        WebElement name = driver.findElement(By.cssSelector(".form-control[name='name']"));
        name.sendKeys(courses);
        Thread.sleep(2000);
        File nameField = name.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(nameField, new File("NamefieldScreeshot.png"));
    }
}
