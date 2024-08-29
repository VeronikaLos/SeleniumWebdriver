package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.Page;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifySelectOption() {
        if (driver.findElements(By.cssSelector("div.buy_now tbody tr")).size() == 2) {
            Select size = new Select(driver.findElement(By.cssSelector("td.options select")));
            size.selectByValue("Small");
        }
    }

    public String findQuantity() {
        WebElement cart = driver.findElement(By.cssSelector("div#cart a.content span.quantity"));
        return cart.getAttribute("textContent");
    }

    @FindBy(css = "button[type='submit'][name='add_cart_product']")
    public WebElement addToCartButton;

    @FindBy(css = "a[href='/litecart/']")
    public WebElement returnToMainPageButton;

    public void openProduct() {
        driver.findElements(By.cssSelector("div#box-most-popular li")).get(0).click();
    }

    public void waitUntilQuantityIncrease(String quantity) {
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div#cart a.content span.quantity"), quantity));
    }
}
