package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestBase {
    protected static ApplicationManager app;

    public static void verifyGreyColor(String color) {
        Pattern pattern = Pattern.compile("rgba?\\((\\d+),\\s*(\\d+),\\s*(\\d+)(?:,\\s*\\d*\\.*\\d+)?\\)");
        Matcher matcher = pattern.matcher(color);
        if (matcher.matches()) {
            int red = Integer.parseInt(matcher.group(1));
            int green = Integer.parseInt(matcher.group(2));
            int blue = Integer.parseInt(matcher.group(3));
            Assertions.assertTrue(red == green && green == blue);
        }
    }

    public static void verifyRedColor(String color) {
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

    public static double defineFontSize(String str) {
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

    protected static void switchToNewWindow(WebDriverWait wait, String mainWindowId) {
        // подождать когда кол-во окон станет равно 2
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        //получить идентификаторы окон
        Set<String> newWindows = app.driver.getWindowHandles();

        List<String> winds = newWindows.stream().filter(w -> !w.equals(mainWindowId)).toList();
        String newWind = winds.get(0);

        //Переключиться в новое окно
        app.driver.switchTo().window(String.valueOf(newWind));
        //закрыть окно
        app.driver.close();
        //Переключиться в основное окно
        app.driver.switchTo().window(mainWindowId);
    }


    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
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
