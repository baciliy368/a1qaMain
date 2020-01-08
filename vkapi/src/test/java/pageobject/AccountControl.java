package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

public class AccountControl {
    private IButton logOut = new ElementFactory().getButton(By.id("top_logout_link"), "logOut button in dropdown menu");

    public void clickLogOut() {
        logOut.click();
    }
}
