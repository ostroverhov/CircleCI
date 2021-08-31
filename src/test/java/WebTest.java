import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebTest {

    private WebDriver driver;

    @BeforeEach
    public void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://web.automation.easyhire.me");
        driver.manage().window().setSize(new Dimension(1920, 1080));
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
        screenshot();
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
