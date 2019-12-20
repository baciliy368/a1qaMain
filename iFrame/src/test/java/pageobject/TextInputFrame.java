package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextInputFrame extends Form implements IFrame {
    private ITextBox txbInputField = new ElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/p"), "input field");

    public TextInputFrame() {
        super(By.id("tinymce"), "input Field Frame");
    }

    public void selectAllText() {
        txbInputField.type(Keys.CONTROL + "a");
    }

    public void clearAndType(String text) {
        txbInputField.clearAndType(text);
    }

    public String getTextDefault() {
        return txbInputField.getText();
    }

    public String getTextStrong() {
        return new ElementFactory()
                .findChildElement(txbInputField, By.xpath("/strong"), ElementType.LABEL, ElementState.EXISTS_IN_ANY_STATE)
                .getText();
    }

    @Override
    public String getId() {
        return "mce_0_ifr";
    }
}
