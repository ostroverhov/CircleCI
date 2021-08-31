import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
    }

    @Test
    public void successfulLogin() {
        WebElement loginButton = driver.findElement(By.xpath("//span[contains(text(), 'Log in') and @class='MuiButton-label']"));
        loginButton.click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("header.MuiPaper-root")).isDisplayed(), "check login form");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
