package pageobject;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {
    private LoginForm loginForm = new LoginForm();

    public LoginPage() {
        super(By.xpath("//a[@class='header-logo']"), "logoTutBy");
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
