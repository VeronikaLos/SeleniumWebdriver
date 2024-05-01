package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void loginAsAdmin(String user, String password) {
        //manager.driver.get("http://localhost/litecart/admin/");
        manager.driver.navigate().to("http://localhost/litecart/admin/");

        type(By.name("username"), user);
        type(By.name("password"), password);
        click(By.name("login"));
    }
}
