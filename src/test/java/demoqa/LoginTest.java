package demoqa;

import demoqa.pageobjects.LoginPage;
import demoqa.pageobjects.ProfilePage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    WebDriver driver;

    LoginPage loginPage;
    ProfilePage profilePage;

    @BeforeMethod
    public void setup() throws IOException {

        driver = getDriver();

    }

    @Parameters({"username", "password"})
    @Test //(enabled = false)
    public void loginWithValidCredentialsAndLogout(String username, String password) {
        loginPage = new LoginPage(driver);

        driver.get(loginPage.url);

        loginPage.loginWithUser(username, password);
        profilePage = new ProfilePage(driver);
        Assertions.assertThat(profilePage.getUserName()).isEqualTo(username);

        profilePage.logout();
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(loginPage.url);

    }

    @Test
    public void loginWithInvalidPassword() {
        loginPage = new LoginPage(driver);

        driver.get(loginPage.url);

        loginPage.loginWithUser("upstream", "wrongpassword");
        Assertions.assertThat(loginPage.getErrorMessageForInvalidUser()).contains("Invalid username or password");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
