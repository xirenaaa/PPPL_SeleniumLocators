package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    private static String getBraveBinaryPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";
        } else if (os.contains("mac")) {
            return "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser";
        } else {
            return "/usr/bin/brave-browser";
        }
    }
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("147").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(getBraveBinaryPath());
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
