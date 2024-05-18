package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

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
        app.driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> rowsCountries = app.driver.findElements(By.cssSelector("td#content tr.row td:nth-child(5)> a"));
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
            List<WebElement> zones = app.driver.findElements(By.cssSelector("table#table-zones tr td:nth-child(3)"));
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
}
