package demoqa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    WebDriver driver;

    By byUserNameLabel = By.cssSelector("label#userName-value");
    By byLogoutBtn = By.xpath("//button[.='Log out']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }


    public String getUserName() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byUserNameLabel)).getText();
    }

    public void logout() {
        driver.findElement(byLogoutBtn).click();
    }
}
