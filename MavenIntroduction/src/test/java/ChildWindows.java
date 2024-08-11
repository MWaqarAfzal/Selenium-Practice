import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class ChildWindows
{
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.findElement(By.className("blinkingText")).click();

        //      Get Windows and Iterate it to reach the desired window
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parent = it.next();
        String child = it.next();
        Thread.sleep(2000);
        driver.switchTo().window(child);
        Thread.sleep(2000);
        //      Get the text and split --> Trim --> Split --> Get the 0th index value
        String emailID = driver.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim().split(" ")[0];
        driver.switchTo().window(parent);
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys(emailID);
    }
}
