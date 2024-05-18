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

    @Test
    public void canCheckItemAttribute() {
        app.driver.get("http://localhost/litecart/en/");
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
        verifyGreyColor(regularPriceColorMainPage);
        verifyGreyColor(regularPriceColorProductPage);

        Assertions.assertEquals(campaignPriceAmountMainPage, campaignPriceAmountProductPage);
        Assertions.assertEquals(campaignPriceFontWeightMainPage, campaignPriceFontWeightProductPage);
        verifyRedColor(campaignPriceColorMainPage);
        verifyRedColor(campaignPriceColorProductPage);

        Assertions.assertTrue(defineFontSize(campaignPriceSizeMainPage) > defineFontSize(regularPriceSizeMainPage));
        Assertions.assertTrue(defineFontSize(campaignPriceSizeProductPage) > defineFontSize(regularPriceSizeProductPage));

    }
}
