import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebTest {

    private WebDriver driver;

    @BeforeEach
    public void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://web.automation.easyhire.me");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("finish set up");
    }

    @Test
    public void successfulLogin() {
        System.out.println("start test");
        WebElement loginButton = driver.findElement(By.xpath("//span[contains(text(), 'Log in') and @class='MuiButton-label']"));
        loginButton.click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("header.MuiPaper-root")).isDisplayed(), "check login form");
    }

    @AfterEach
    public void tearDown() {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
