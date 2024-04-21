import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenPage {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test() {
        driver.get("https://www.onliner.by/");
    }

    @Test
    public void canLoginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin2");

        driver.findElement(By.name("login")).click();
    }
}
