package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class RightBarForm extends Form {
    private IButton btnMainPageOfUser = new ElementFactory().getButton(By.id("l_pr"), "user main page button");

    public RightBarForm() {
        super(By.id("l_vkp"), "vkPay");
    }

    public void clickMainUserPage() {
        btnMainPageOfUser.click();
    }
}
