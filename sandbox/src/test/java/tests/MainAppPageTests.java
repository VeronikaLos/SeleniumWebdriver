package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainAppPageTests extends TestBase {

    @Test
    public void canCheckAllStickers() {
        // Navigate to the target URL
        app.driver.get("http://localhost/litecart/en/");

        // Locate all product elements on the page
        List<WebElement> products = app.driver.findElements(By.cssSelector("li.product"));

        // Assert that there are products on the page
        Assertions.assertFalse(products.isEmpty(), "No products found on the page.");

        // Iterate through each product and check that it has exactly one sticker
        for (WebElement product : products) {
            List<WebElement> stickers = product.findElements(By.cssSelector("div.sticker"));
            Assertions.assertEquals(1, stickers.size(), "Product should have exactly one sticker.");
        }
    }
}
