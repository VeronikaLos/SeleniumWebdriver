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
    public void canCheckAllMenuItems() {
        app.loginAsAdmin();

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Appearence')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Template')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-template']/a/span[contains(.,'Template')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Template')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-logotype']/a/span[contains(.,'Logotype')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Logotype')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Catalog')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Catalog')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-catalog']/a/span[contains(.,'Catalog')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Catalog')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-product_groups']/a/span[contains(.,'Product Groups')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Product Groups')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-option_groups']/a/span[contains(.,'Option Groups')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Option Groups')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='doc-manufacturers']/a/span[contains(.,'Manufacturers')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Manufacturers')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='doc-suppliers']/a/span[contains(.,'Suppliers')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Suppliers')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-delivery_statuses']/a/span[contains(.,'Delivery Statuses')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Delivery Statuses')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-sold_out_statuses']/a/span[contains(.,'Sold Out Statuses')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Sold Out Statuses')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-quantity_units']/a/span[contains(.,'Quantity Units')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Quantity Units')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-csv']/a/span[contains(.,'CSV Import/Export')]")).click();

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Countries')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Countries')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Currencies')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Currencies')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Customers')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Customers')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-customers']/a/span[contains(.,'Customers')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Customers')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-csv']/a/span[contains(.,'CSV Import/Export')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' CSV Import/Export')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-newsletter']/a/span[contains(.,'Newsletter')]")).click();

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Geo Zones')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Geo Zones')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Languages')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Languages')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-languages']/a/span[contains(.,'Languages')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Languages')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-storage_encoding']/a/span[contains(.,'Storage Encoding')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Storage Encoding')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Modules')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Job Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-jobs']/a/span[contains(.,'Background Jobs')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Job Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-customer']/a/span[contains(.,'Customer')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Customer Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-shipping']/a/span[contains(.,'Shipping')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Shipping Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-payment']/a/span[contains(.,'Payment')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Payment Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-order_total']/a/span[contains(.,'Order Total')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Order Total Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-order_success']/a/span[contains(.,'Order Success')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Order Success Modules')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-order_action']/a/span[contains(.,'Order Action')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Order Action Modules')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Orders')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Orders')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-orders']/a/span[contains(.,'Orders')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Orders')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-order_statuses']/a/span[contains(.,'Order Statuses')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Order Statuses')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Pages')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Pages')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Reports')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Monthly Sales')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-monthly_sales']/a/span[contains(.,'Monthly Sales')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Monthly Sales')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-most_sold_products']/a/span[contains(.,'Most Sold Products')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Most Sold Products')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-most_shopping_customers']/a/span[contains(.,'Most Shopping Customers')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Most Shopping Customers')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Settings')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-store_info']/a/span[contains(.,'Store Info')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-defaults']/a/span[contains(.,'Defaults')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-general']/a/span[contains(.,'General')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-listings']/a/span[contains(.,'Listings')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-images']/a/span[contains(.,'Images')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-checkout']/a/span[contains(.,'Checkout')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-advanced']/a/span[contains(.,'Advanced')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-security']/a/span[contains(.,'Security')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Settings')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Slides')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Slides')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Tax')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Tax')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-tax_classes']/a/span[contains(.,'Tax Classes')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Tax Classes')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-tax_rates']/a/span[contains(.,'Tax Rates')]")).click();

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Translations')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Translations')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-search']/a/span[contains(.,'Search Translations')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Search Translations')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-scan']/a/span[contains(.,'Scan Files')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Scan Files For Translations')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-csv']/a/span[contains(.,'CSV Import/Export')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' CSV Import/Export')]")).isEmpty());

        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'Users')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' Users')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(.,'vQmods')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' vQmods')]")).isEmpty());
        app.driver.findElement(By.xpath("//*[@id='doc-vqmods']/a/span[contains(.,'vQmods')]")).click();
        Assertions.assertFalse(app.driver.findElements(By.xpath("//*[@id='content']/h1[contains(.,' vQmods')]")).isEmpty());
    }
}
