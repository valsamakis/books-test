package demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    private static WebDriver driver;

    public static WebDriver getDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
        prop.load(data);

        if (prop.getProperty("browser").equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
        } else if (prop.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            //driver = new EdgeDriver();
        }

        return driver;
    }


}
