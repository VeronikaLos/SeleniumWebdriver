package tests;

import manager.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {
    public static Application app;


    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new Application();
        }
        //app.init("Edge");
        app.init(System.getProperty("browser", "chrome"));
    }

    @AfterEach
    public void tearDown() {
        app.driver.quit();
        app.driver = null;
    }
}
