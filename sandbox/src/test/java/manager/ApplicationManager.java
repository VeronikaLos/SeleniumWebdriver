package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver driver;
    private LoginHelper session;

    public void init(String browser) {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            } else if ("firefox".equals(browser)) {
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
                driver = new FirefoxDriver(options);
            } else if ("edge".equals(browser)) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }

            //закрывает браузер/ Java перед выходом вызывает метод quit в переменной driver/ браузер закрывается после всех тестов.
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));

        }
    }

    public void loginAsAdmin() {
        session().loginAsAdmin("admin", "admin");
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

}
