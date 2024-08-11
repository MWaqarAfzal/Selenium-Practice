import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        WebElement search = driver.findElement(By.id("search-field"));
        search.sendKeys("Rice");

        List<WebElement> searchList = driver.findElements(By.xpath("//tr/td[1]"));
        List<WebElement> filteredList = searchList.stream().filter(s->s.getText().contains("Rice")).toList();
        Assert.assertEquals(searchList.size(), filteredList.size());
    }
}
