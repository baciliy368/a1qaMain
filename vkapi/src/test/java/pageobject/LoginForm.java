package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import models.test.UserModel;
import org.openqa.selenium.By;

public class LoginForm extends Form {
    private ITextBox txbLoginField = new ElementFactory().getTextBox(By.id("index_email"), "login field");
    private ITextBox txbPasswordField = new ElementFactory().getTextBox(By.id("index_pass"), "pass field");
    private IButton btnSubmit = new ElementFactory().getButton(By.id("index_login_button"), "submit button");

    LoginForm() {
        super(By.id("index_forgot"), "login form");
    }

    public void loginUser(UserModel user) {
        typeLogin(user.getEmail());
        typePassword(user.getPassword());
        btnSubmit.click();
    }

    private void typeLogin(String login) {
        txbLoginField.type(login);
    }

    private void typePassword(String password) {
        txbPasswordField.type(password);
    }
}
