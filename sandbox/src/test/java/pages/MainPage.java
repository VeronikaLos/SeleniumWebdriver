package pages;

import org.openqa.selenium.WebDriver;
import pages.Page;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost:8080/litecart/en/");

    }
}
