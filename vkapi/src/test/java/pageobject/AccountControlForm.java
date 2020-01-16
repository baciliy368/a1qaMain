package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AccountControlForm extends Form {
    private IButton logOut = new ElementFactory().getButton(By.id("top_logout_link"), "logOut button in dropdown menu");

    protected AccountControlForm() {
        super(By.id("top_logout_link"), "logOut button in dropdown menu");
    }

    public void clickLogOut() {
        logOut.click();
    }
}
