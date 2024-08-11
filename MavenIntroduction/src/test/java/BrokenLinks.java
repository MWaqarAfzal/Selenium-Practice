import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLinks {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        SoftAssert softAssert = new SoftAssert();
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            String linkText = link.getText();
            //      Create a URL class variable and call the openConnection() method
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            //      Call the setRequestMethod and pass the HEAD
            connection.setRequestMethod("HEAD");
            //      Make the connection by using the connect() method
            connection.connect();
            //      Get the response of the request and store in a variable
            int responseCode = connection.getResponseCode();
            softAssert.assertTrue(responseCode<400, "The link with Text " + linkText + "is broken with status code " + responseCode);
            /*
            if(responseCode > 400)
            {
                String brokenLinksText = link.getText();
                System.out.println("The link with Text " + brokenLinksText + " is broken " + "with status code " + responseCode);
                softAssert.assertTrue(false);
            }

             */

        }
        softAssert.assertAll();

    }
}
