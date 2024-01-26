package demoqa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BooksListPage {

    WebDriver driver;

    public static final String url = "https://demoqa.com/books";

    public By rowsOfTable = By.cssSelector(".rt-tbody [role='rowgroup'] > div:not(.-padRow)");
    public By cellsInARowOfTable = By.cssSelector("[role='gridcell']");

    public BooksListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get(url);
    }

    public void searchFor(String term) {
        driver.findElement(By.cssSelector("#searchBox")).sendKeys(term);
        driver.findElement(By.cssSelector("#basic-addon2")).click();
    }

}
