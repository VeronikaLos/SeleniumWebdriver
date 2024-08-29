package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Page;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int findProductQuantity() {
        return driver.findElements(By.cssSelector("table.dataTable td.item")).size();

    }

    @FindBy(css = "button[type='submit'][name='remove_cart_item']")
    public WebElement removeButton;

    @FindBy(css = "div#cart a.link")
    public WebElement openCartButton;

    public String findTextTiVerify() {
        return driver.findElement(By.cssSelector("div#checkout-cart-wrapper em")).getText();
    }

    public void removeAll(int size) {
        int sizeNew = size;
        for (int i = 0; i < size; i++) {
            removeButton.click();
            //найти и нажать кнопку ремув
            sizeNew--;
            //ждем пока кол во товара будет на единицу меньше
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable td.item"), sizeNew));
        }
    }

}
