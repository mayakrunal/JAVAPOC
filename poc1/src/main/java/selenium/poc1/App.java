package selenium.poc1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/** Hello world! */
public class App {

    public static void main(final String[] args) {
        // Optional, if not specified, WebDriver will search your path for
        // chromedriver.
        System.setProperty("webdriver.chrome.driver", "C:/Users/kparekh011/Downloads/chromedriver_win32/chromedriver.exe");

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        final WebDriver driver = new ChromeDriver();

        // And now use this to visit Google
        driver.get("http://strl099068:9380/rpm/app.html#/login");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        final WebElement element = driver.findElement(By.id("clear-history"));

        // Close the browser
        driver.quit();
    }

}
