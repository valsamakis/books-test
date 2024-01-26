package demoqa;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import demoqa.pageobjects.BooksListPage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BooksListTest extends BaseTest {

    WebDriver driver;

    BooksListPage booksListPage;

    List<List<String>> testData;


    @BeforeMethod
    public void readTestData() throws IOException, CsvValidationException {
        testData = new ArrayList<>();
        CSVReader csvReader  = new CSVReader(new FileReader(System.getProperty("user.dir") +"\\tabledata.csv"));
        String[] values = null;
        while ((values = csvReader.readNext()) != null) {
            testData.add(Arrays.asList(values));
        }
    }

    @Test
    public void tableOfBooksTest() throws IOException, CsvValidationException {
        driver = getDriver();

        booksListPage = new BooksListPage(driver);
        booksListPage.navigateTo();

        booksListPage.searchFor("O'Reilly Media");


        List<WebElement> rows = driver.findElements(booksListPage.rowsOfTable);

        SoftAssertions softly = new SoftAssertions();

        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(booksListPage.cellsInARowOfTable);
            for (int j = 0; j < cells.size(); j++) {
                WebElement cell = cells.get(j);

                String expectedValue = testData.get(i).get(j);
                if (j == 0) {
                    String href = cell.findElement(By.tagName("img")).getDomAttribute("src");
                    softly.assertThat(href).isEqualTo(expectedValue);

                } else {
                    String cellData = cell.getText();
                    softly.assertThat(cellData).isEqualTo(expectedValue);
                }
            }
        }
        softly.assertAll();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
