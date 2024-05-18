package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

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


    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        //app.init("Edge");
        app.init(System.getProperty("browser", "chrome"));
    }

    //    @AfterEach
    //    public void tearDown() {
    //        driver.quit();
    //        driver = null;
    //    }
}
