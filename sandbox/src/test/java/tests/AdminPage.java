package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class AdminPage extends TestBase {

    @Test
    public void canLoginAsAdmin() {
        app.loginAsAdmin();
    }

    @Test
    public void canCheckAllMenuItems2() {
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
    public void canCheckAllStickers() {
        app.driver.get("http://localhost/litecart/en/");
        int x = app.driver.findElements(By.cssSelector("div.middle>div.content>div.box li")).size();
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                var item = app.driver.findElements(By.cssSelector("div.middle>div.content>div.box li")).get(i);
                int size = item.findElements(By.cssSelector("[class^=sticker]")).size();
                Assertions.assertEquals(size, 1);
            }
        }
    }
}
