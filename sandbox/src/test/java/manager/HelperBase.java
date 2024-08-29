package manager;

import org.openqa.selenium.By;

public class HelperBase {
    protected final Application manager;

    public HelperBase(Application manager) {
        this.manager=manager;
    }

    protected void click (By locator) {
        manager.driver.findElement(locator).click();
    }
    protected void type (By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

}
