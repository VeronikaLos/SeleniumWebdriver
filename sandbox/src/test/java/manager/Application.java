package manager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public WebDriver driver;
    private WebDriverWait wait;
    private LoginHelper session;
    private MainPage mainPage;
    private CartPage cartPage;
    private ProductPage productPage;

    public Application() {
        init("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
    }

    public void init(String browser) {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-search-engine-choice-screen");
                driver = new ChromeDriver(options);
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

    //mainPage
    public void openMainPage() {
        mainPage.open();
    }


    //cartPage
    public void clearCart() {
        cartPage.openCartButton.click();
        cartPage.removeAll(cartPage.findProductQuantity());
    }

    //cartPage
    public String textToVerify() {
        return cartPage.findTextTiVerify();
    }

    //productPage
    public void addProductIntoCart() {
        for (int i = 0; i < 3; i++) {
            productPage.openProduct();
            // на странице товара проверяем есть ли опция select
            productPage.verifySelectOption();
            //находим кол-во товаров в корзине
            String quantity = productPage.findQuantity();
            //добавляем товар в корзину
            productPage.addToCartButton.click();
            //ждем пока старое кол во исчезнет
            productPage.waitUntilQuantityIncrease(quantity);
            //возвращаемся на главную страницу
            productPage.returnToMainPageButton.click();
        }
    }

    public void switchToNewWindow(String mainWindowId) {
        // подождать когда кол-во окон станет равно 2
        //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        wait.until((WebDriver d) -> ExpectedConditions.numberOfWindowsToBe(2));
        //получить идентификаторы окон
        Set<String> newWindows = driver.getWindowHandles();

        List<String> winds = newWindows.stream().filter(w -> !w.equals(mainWindowId)).toList();
        String newWind = winds.get(0);

        //Переключиться в новое окно
        driver.switchTo().window(String.valueOf(newWind));
        //закрыть окно
        driver.close();
        //Переключиться в основное окно
        driver.switchTo().window(mainWindowId);
    }

    public void verifyGreyColor(String color) {
        Pattern pattern = Pattern.compile("rgba?\\((\\d+),\\s*(\\d+),\\s*(\\d+)(?:,\\s*\\d*\\.*\\d+)?\\)");
        Matcher matcher = pattern.matcher(color);
        if (matcher.matches()) {
            int red = Integer.parseInt(matcher.group(1));
            int green = Integer.parseInt(matcher.group(2));
            int blue = Integer.parseInt(matcher.group(3));
            Assertions.assertTrue(red == green && green == blue);
        }
    }

    public void verifyRedColor(String color) {
        Pattern pattern = Pattern.compile("rgba?\\((\\d+),\\s*(\\d+),\\s*(\\d+)(?:,\\s*\\d*\\.*\\d+)?\\)");
        Matcher matcher = pattern.matcher(color);
        if (matcher.matches()) {
            //int red = Integer.parseInt(matcher.group(1));
            int green = Integer.parseInt(matcher.group(2));
            int blue = Integer.parseInt(matcher.group(3));
            Assertions.assertEquals(green, 0);
            Assertions.assertEquals(blue, 0);
        }
    }

    public double defineFontSize(String str) {
        Pattern pattern = Pattern.compile("(\\d+\\.?\\d*)");
        // Создание Matcher объекта
        Matcher matcher = pattern.matcher(str);
        // Поиск числа
        double number = 0;
        if (matcher.find()) {
            String numberStr = matcher.group(0);
            // Преобразование строки в тип double
            number = Double.parseDouble(numberStr);
        }
        return number;
    }

}
