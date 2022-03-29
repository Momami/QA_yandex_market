package qa_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.time.Duration;

@Listeners(Listener.class)
public class TestBefore {
    public static WebDriver driver;
    public static Screen screen;
    public static final String PATH_FOR_SCREEN = new File("screen").getAbsolutePath();

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void preparation(ITestContext testContext) {
        //Указываем путь к драйверу
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("start-fullscreen");
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("disable-extensions"); // disabling extensions
        options.addArguments("disable-gpu"); // applicable to windows os only
        options.addArguments("disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("no-sandbox"); // Bypass OS security model
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        screen = new Screen(driver, PATH_FOR_SCREEN);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://market.yandex.ru/");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}

