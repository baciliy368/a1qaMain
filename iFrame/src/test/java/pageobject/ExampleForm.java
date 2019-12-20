package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ExampleForm extends Form {
    private TextInputFrame textInputFrame = new TextInputFrame();
    private IButton btnMakeTextBold = new ElementFactory().getButton(By.xpath("//div[@aria-label='Bold']"), "bold");

    public ExampleForm() {
        super(By.xpath("//div[@role='menubar']"), "Example");
    }

    public TextInputFrame getTextInputFrame() {
        return textInputFrame;
    }

    public void clickBtnBold() {
        btnMakeTextBold.click();
    }
}
