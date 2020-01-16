package pageobject;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class QuickLoginForm extends Form {

    protected QuickLoginForm() {
        super(By.id("quick_login"), "quick login form");
    }
}
