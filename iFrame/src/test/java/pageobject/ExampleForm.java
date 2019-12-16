package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ExampleForm extends Form {
    private InputTextFieldFrame frameForm = new InputTextFieldFrame();
    private IButton btnMakeTextBold = new ElementFactory().getButton(By.xpath("//div[@aria-label='Bold']"), "bold");

    public ExampleForm() {
        super(By.xpath("//div[@role='menubar']"), "menuBar");
    }

    public InputTextFieldFrame getFrameForm() {
        return frameForm;
    }

    public void clickBtnBold() {
        btnMakeTextBold.click();
    }

}
