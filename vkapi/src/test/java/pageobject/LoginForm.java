package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import framework.utils.Log;
import framework.utils.PropertiesReader;
import models.test.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import java.util.Arrays;

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
        if (!new NewsPage().isFormDisplayed(Long.parseLong(PropertiesReader.getValue("DEFAULT_TIME_OF_CUSTOM_WAITING")))) {
            ElementNotVisibleException elementNotVisibleException = new ElementNotVisibleException("invalid log or password");
            Log.error(Arrays.toString(elementNotVisibleException.getStackTrace()));
            throw elementNotVisibleException;
        }
    }

    private void typeLogin(String login) {
        txbLoginField.type(login);
    }

    private void typePassword(String password) {
        txbPasswordField.type(password);
    }
}
