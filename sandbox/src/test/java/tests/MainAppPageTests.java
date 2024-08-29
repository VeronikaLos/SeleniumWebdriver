package tests;

import manager.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainAppPageTests extends TestBase {

    @Test
    public void canCheckAllStickers() {
        // Navigate to the target URL
        app.openMainPage();

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

    @Test
    public void canCheckItemAttribute() {
        app.openMainPage();
        /// 1. Main Page
        WebElement product = app.driver.findElements(By.cssSelector("div#box-campaigns li.product")).get(0);
        //на главной странице цвет текста названия товара
        var colorNameMainPage = product.findElement(By.cssSelector("div.name")).getCssValue("color");

        // Обычная цена
        var regularPriceMainPage = product.findElement(By.cssSelector("s.regular-price"));
        // сумма обычной цена
        var regularPriceAmountMainPage = regularPriceMainPage.getAttribute("textContent");
        // обычная цена зачёркнутая
        var regularPriceTextDecorationMainPage = regularPriceMainPage.getCssValue("text-decoration-line");
        // серый цвет
        var regularPriceColorMainPage = regularPriceMainPage.getCssValue("color");
        // размер шрифта
        var regularPriceSizeMainPage = regularPriceMainPage.getCssValue("font-size");

        // Аукционная цена
        var campaignPriceMainPage = product.findElement(By.cssSelector("div#box-campaigns li.product strong.campaign-price"));
        // сумма
        var campaignPriceAmountMainPage = campaignPriceMainPage.getAttribute("textContent");
        // жирная
        var campaignPriceFontWeightMainPage = campaignPriceMainPage.getCssValue("font-weight");
        // красная
        var campaignPriceColorMainPage = campaignPriceMainPage.getCssValue("color");
        // размер
        var campaignPriceSizeMainPage = campaignPriceMainPage.getCssValue("font-size");

        // 2. Product Page
        product.click();
        //на странице продукта цвет текста названия товара
        var colorProductPage = app.driver.findElement(By.cssSelector("h1.title")).getCssValue("color");

        // обычная цена
        var regularPriceProductPage = app.driver.findElement(By.cssSelector("s.regular-price"));
        // сумма
        var regularPriceAmountProductPage = regularPriceProductPage.getAttribute("textContent");
        // зачеркнута
        var regularPriceTextDecorationProductPage = regularPriceProductPage.getCssValue("text-decoration-line");
        // цвет
        var regularPriceColorProductPage = regularPriceProductPage.getCssValue("color");
        // размер шрифта
        var regularPriceSizeProductPage = regularPriceProductPage.getCssValue("font-size");

        // акционная цена
        var campaignPriceProductPage = app.driver.findElement(By.cssSelector("strong.campaign-price"));
        // сумма
        var campaignPriceAmountProductPage = campaignPriceProductPage.getAttribute("textContent");
        // жирная
        var campaignPriceFontWeightProductPage = campaignPriceProductPage.getCssValue("font-weight");
        // красная
        var campaignPriceColorProductPage = campaignPriceProductPage.getCssValue("color");
        // размер шрифта
        var campaignPriceSizeProductPage = campaignPriceProductPage.getCssValue("font-size");

        //проверки
        Assertions.assertEquals(colorNameMainPage, colorProductPage);

        Assertions.assertEquals(regularPriceAmountMainPage, regularPriceAmountProductPage);
        Assertions.assertEquals(regularPriceTextDecorationMainPage, regularPriceTextDecorationProductPage);
        app.verifyGreyColor(regularPriceColorMainPage);
        app.verifyGreyColor(regularPriceColorProductPage);

        Assertions.assertEquals(campaignPriceAmountMainPage, campaignPriceAmountProductPage);
        Assertions.assertEquals(campaignPriceFontWeightMainPage, campaignPriceFontWeightProductPage);
        app.verifyRedColor(campaignPriceColorMainPage);
        app.verifyRedColor(campaignPriceColorProductPage);

        Assertions.assertTrue(app.defineFontSize(campaignPriceSizeMainPage) > app.defineFontSize(regularPriceSizeMainPage));
        Assertions.assertTrue(app.defineFontSize(campaignPriceSizeProductPage) > app.defineFontSize(regularPriceSizeProductPage));

    }

    @Test
    public void canCheckUserRegistration() {
        app.openMainPage();
        app.driver.findElement(By.linkText("New customers click here")).click();
        String firstname = CommonFunctions.randomString(5);
        app.driver.findElement(By.name("firstname")).sendKeys(firstname);
        String lastname = CommonFunctions.randomString(5);
        app.driver.findElement(By.name("lastname")).sendKeys(lastname);
        app.driver.findElement(By.name("address1")).sendKeys("address1");
        app.driver.findElement(By.name("postcode")).sendKeys("12345");
        app.driver.findElement(By.name("city")).sendKeys("city");

        app.driver.findElement(By.className("select2-selection__arrow")).click();
        //app.driver.findElements(By.className("select2-results__option")).get(224).click();
        app.driver.findElement(By.cssSelector(".select2-results__option[id $= 'US']")).click();

//        Select list = new Select(app.driver.findElement(By.cssSelector("select[name=zone_code]")));
//        list.selectByValue("AZ");

        new Select(app.driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue("KS");
        String email = String.format("%s@gmail.com", firstname);
        app.driver.findElement(By.name("email")).sendKeys(email);
        app.driver.findElement(By.name("phone")).sendKeys("555");
        String password = CommonFunctions.randomString(6);
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.name("confirmed_password")).sendKeys(password);
        app.driver.findElement(By.name("create_account")).click();
        app.driver.findElement(By.linkText("Logout")).click();
        app.driver.findElement(By.name("email")).sendKeys(email);
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.name("login")).click();
        app.driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void canCheckAddItemsToCart() {
        app.openMainPage();
        app.addProductIntoCart();
        app.clearCart();
        Assertions.assertEquals(app.textToVerify(), "There are no items in your cart.");
    }
}

