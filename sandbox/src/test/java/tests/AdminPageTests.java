package tests;

import manager.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AdminPageTests extends TestBase {

    @Test
    public void canLoginAsAdmin() {
        app.loginAsAdmin();
    }

    @Test
    public void canCheckAllMenuItems() {
        app.loginAsAdmin();
        int n = app.driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                app.driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(i).click();
                Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1")).isEmpty());
                int y = 1;
                for (int j = 0; j < y; j++) {
                    int x = app.driver.findElements(By.cssSelector("div#box-apps-menu-wrapper ul.docs")).size();
                    if (x > 0) {
                        y = app.driver.findElements(By.cssSelector("div#box-apps-menu-wrapper ul.docs li")).size();
                        app.driver.findElements(By.cssSelector("div#box-apps-menu-wrapper ul.docs li")).get(j).click();
                        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1")).isEmpty());
                    } else break;
                }
            }
        }
    }

    @Test
    public void canCheckAllCountries() {
        app.loginAsAdmin();
        app.driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        List<WebElement> rowsCountries = app.driver.findElements(By.cssSelector("td#content tr.row td:nth-child(5)> a"));
        //Что значит stream/map??
        List<String> result = rowsCountries.stream().map(WebElement::getText).toList();
        var result2 = result;
        result = result.stream().sorted().toList();
        Assertions.assertEquals(result, result2);

        //находим строки в которых хранится число зон в каждой стране
        List<WebElement> rowsZone = app.driver.findElements(By.cssSelector("td#content tr.row td:nth-child(6)"));
        List<String> countries = new ArrayList<>();
        //По каждой строке устраиваем цикл и из каждой строки забираем кол во зон
        for (WebElement row : rowsZone) {
            //var zoneCount = Integer.valueOf(row.getAttribute("textContent"));
            var zoneCount = Integer.parseInt(row.getAttribute("textContent"));
            //если кол во зон > 0
            if (zoneCount > 0) {
                //то по строке находим элемент и запоминаем имя его страны
                WebElement withCountNotNull2 = row.findElement(By.xpath("./preceding-sibling::td/a"));
                //WebElement withCountNotNull2 = row.findElement(By.xpath("./parent::tr/td[5]/a"));
                var name = withCountNotNull2.getAttribute("text");
                //добавляем имя страны в список
                countries.add(name);
            }
        }

        //берем список элементов где кол во зон > 0 и заходим внутрь
        for (String country : countries) {
            app.driver.findElement(By.xpath(String.format("//*[ text() = '%s']", country))).click();
            List<WebElement> zones = app.driver.findElements(By.xpath("//table[@id='table-zones']//a[@id='remove-zone']//parent::td//parent::tr/td[3]"));
            var zoneList = new ArrayList<String>();
            for (WebElement zone : zones) {
                var text = zone.getAttribute("textContent");
                zoneList.add(text);
            }
            var zoneListOld = zoneList;
            Collections.sort(zoneList);
            Assertions.assertEquals(zoneListOld, zoneList);
            app.driver.navigate().back();
        }
    }

    @Test
    public void canCheckZonesInCountries() {
        app.loginAsAdmin();
        app.driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        // Находим список стран, имеющих зоны
        List<WebElement> rowsCountries = app.driver.findElements(By.cssSelector("tr.row td:nth-child(3) > a"));
        //создаем стринг список из имен стран
        List<String> countriesName = rowsCountries.stream().map(WebElement::getText).toList();

        // Проходим по каждой стране
        for (String country : countriesName) {
            // Находим страну по имени и кликаем на нее
            app.driver.findElement(By.xpath(String.format("//*[ text() = '%s']", country))).click();

            //создаем список имен зон
            List<String> zoneNames = app.driver.findElements(By.cssSelector("table.dataTable td:nth-child(3) select [selected=\"selected\"]"))
                    .stream().map(WebElement::getText).toList();

            //создаем сортированный список имен зон
            List<String> zoneNamesSorted = zoneNames.stream().sorted().toList();

            // Проверяем, что оригинальный список и сортированный список совпадают
            Assertions.assertEquals(zoneNamesSorted, zoneNames);
            app.driver.navigate().back();
        }
    }

    @Test
    public void canCheckAddNewProduct() {
        app.loginAsAdmin();
        app.driver.get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog");

        // General tab
        app.driver.findElement(By.cssSelector("[href='http://localhost:8080/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();
        app.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        app.driver.findElement(By.cssSelector("[type='radio']")).click();
        String name = CommonFunctions.randomString(5);
        app.driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(name);
        String code = CommonFunctions.randomString(3);
        app.driver.findElement(By.cssSelector("[name='code']")).sendKeys(code);
        app.driver.findElement(By.cssSelector("input[value='1-2']")).click();
        WebElement quantity = app.driver.findElement(By.cssSelector("input[name=quantity]"));
        quantity.clear();
        quantity.sendKeys("20");
        String str = "src/test/resources/Files/img2024.png";
        Path path = Path.of(str).toAbsolutePath();
        app.driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(String.format("%s", path));
        app.driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01/01/2024");
        app.driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("12/31/2027");

        // Information tab
        app.driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        app.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Select manufacturer = new Select(app.driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        manufacturer.selectByValue("1");
        String keywords = CommonFunctions.randomString(3);
        app.driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys(keywords);
        String shortDescription = CommonFunctions.randomString(3);
        app.driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys(shortDescription);
        String description = CommonFunctions.randomString(10);
        app.driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys(description);
        String headTitle = CommonFunctions.randomString(6);
        app.driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys(headTitle);
        String metaDescription = CommonFunctions.randomString(7);
        app.driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys(metaDescription);

        //Prices tab
        app.driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        app.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement price = app.driver.findElement(By.cssSelector("input[name=purchase_price]"));
        price.clear();
        price.sendKeys("5.55");
        Select currencyPrice = new Select(app.driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        currencyPrice.selectByValue("USD");
        app.driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("11.55");
        app.driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("15.33");
        app.driver.findElement(By.cssSelector("button[name='save']")).click();
        WebElement search = app.driver.findElement(By.cssSelector("input[name='query']"));
        search.sendKeys(name);
        search.sendKeys(Keys.ENTER);
        String product = app.driver.findElement(By.cssSelector("tr.footer td")).getText();
        Assertions.assertEquals("Products: 1", product);
    }

    @Test
    public void canCheckLinksOnCountriesPage() {
        app.loginAsAdmin();
        WebDriverWait wait = new WebDriverWait(app.driver, Duration.ofSeconds(10));
        app.driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        app.driver.findElement(By.cssSelector("a[href='http://localhost:8080/litecart/admin/?app=countries&doc=edit_country']")).click();

        //получить айди текущего окна
        String mainWindowId = app.driver.getWindowHandle();

        List<WebElement> links = app.driver.findElements(By.cssSelector("i.fa-external-link"));

        for (WebElement link : links) {
            link.click();
            switchToNewWindow(wait, mainWindowId);
        }
    }

}
