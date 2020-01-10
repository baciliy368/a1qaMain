package pageobject;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {
    private LoginForm loginForm = new LoginForm();

    public LoginPage() {
        super(By.id("index_login_button"), "login button");
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
