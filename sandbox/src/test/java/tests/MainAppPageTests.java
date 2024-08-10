package tests;

import manager.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    @Test
    public void canCheckUserRegistration() {
        app.driver.get("http://localhost:8080/litecart/en/");
        app.driver.findElement(By.linkText("New customers click here")).click();
        String firstname = CommonFunctions.randomString(5);
        app.driver.findElement(By.name("firstname")).sendKeys(firstname);
        String lastname = CommonFunctions.randomString(5);
        app.driver.findElement(By.name("lastname")).sendKeys(lastname);
        app.driver.findElement(By.name("address1")).sendKeys("address1");
        app.driver.findElement(By.name("postcode")).sendKeys("12345");
        app.driver.findElement(By.name("city")).sendKeys("city");
        app.driver.findElement(By.className("select2-selection__arrow")).click();
        app.driver.findElements(By.className("select2-results__option")).get(224).click();
        Select list = new Select(app.driver.findElement(By.cssSelector("select[name=zone_code]")));
        list.selectByValue("AZ");
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
        app.driver.get("http://localhost:8080/litecart/en/");
        WebDriverWait wait = new WebDriverWait(app.driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) {
            //берем первый товар в списке и открываем его
            app.driver.findElements(By.cssSelector("div#box-most-popular li")).get(0).click();

            // проверяем есть ли опция select
            int options = app.driver.findElements(By.cssSelector("div.buy_now tbody tr")).size();
            if (options == 2) {
                Select size = new Select(app.driver.findElement(By.cssSelector("td.options select")));
                size.selectByValue("Small");
            }

            //находим элемент кол-ва товаров в корзине
            WebElement cart = app.driver.findElement(By.cssSelector("div#cart a.content span.quantity"));
            String quantity = cart.getAttribute("textContent");

            //добавляем товар в корзину
            app.driver.findElement(By.cssSelector("button[type='submit'][name='add_cart_product']")).click();

            //ждем пока старое кол во исчезнет
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div#cart a.content span.quantity"), quantity));

            //возвращаемся на главную страницу
            app.driver.findElement(By.cssSelector("a[href='/litecart/']")).click();
        }
        // зайти в корзину
        app.driver.findElement(By.cssSelector("div#cart a.link")).click();
        //Узнать кол во продуктов в корзине
        int size = app.driver.findElements(By.cssSelector("table.dataTable td.item")).size();
        int sizeNew = size;
        for (int i = 0; i < size; i++) {
            //найти и нажать кнопку ремув
            app.driver.findElement(By.cssSelector("button[type='submit'][name='remove_cart_item']")).click();
            sizeNew--;
            //ждем пока кол во товара будет на единицу меньше
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable td.item"), sizeNew));
            System.out.println(size);
            System.out.println();
            System.out.println(sizeNew);
        }
        String text = app.driver.findElement(By.cssSelector("div#checkout-cart-wrapper em")).getText();
        Assertions.assertEquals(text, "There are no items in your cart.");
    }
}

