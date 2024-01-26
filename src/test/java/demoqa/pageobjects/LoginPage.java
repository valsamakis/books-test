package demoqa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public String url = "https://demoqa.com/login";

    By byUserName = By.cssSelector("#userName");
    By byPassword = By.cssSelector("#password");
    By byLoginBtn = By.cssSelector("button#login");
    By byInvalidUsernameOrPassword = By.cssSelector("#name");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginWithUser(String username, String password) {
        driver.findElement(byUserName).sendKeys(username);
        driver.findElement(byPassword).sendKeys(password);
        driver.findElement(byLoginBtn).click();
    }

    public String getErrorMessageForInvalidUser() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byInvalidUsernameOrPassword)).getText();
    }



}
