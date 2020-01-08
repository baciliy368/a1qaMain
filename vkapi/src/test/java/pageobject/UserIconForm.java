package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserIconForm extends Form {
    private AccountControl accountControl = null;
    private IButton userLabel = new ElementFactory().getButton(By.id("top_profile_link"), "users Label");
    private IButton arrowMenu = new ElementFactory().getButton(By.xpath("//div[@class='top_profile_arrow']"), "arrow to open drop down menu");

    public UserIconForm() {
        super(By.id("top_profile_link"), "user Top Right Icon");
    }

    public void logout() {
        openDropDownMenu();
        accountControl.clickLogOut();
    }

    public String getIdOfUserNumbers() {
        return userLabel.getAttribute("href").replaceAll("[^\\d]", "");
    }

    private void openDropDownMenu() {
        arrowMenu.click();
        accountControl = new AccountControl();
    }
}
