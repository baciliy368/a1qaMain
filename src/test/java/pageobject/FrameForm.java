package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import aquality.selenium.logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class FrameForm extends Form {
    private Logger logger = Logger.getInstance();
    private ITextBox txbInputField = new ElementFactory().getTextBox(By.xpath("//body[@id='tinymce']/p"), "123");

    public FrameForm() {
        super(By.id("tinymce"), "bodyOfFrame");
    }

    public void selectAllText() {
        logger.info("Select all text");
        txbInputField.type(Keys.CONTROL + "a");
    }

    public void clearAndType(String text) {
        logger.info(String.format("clean and type text: %s", text));
        txbInputField.clearAndType(text);
    }

    public String getTextDefault() {
        logger.info("take default text");
        return txbInputField.getText();
    }

    public String getTextStrong() {
        logger.info("take strong text");
        return new ElementFactory()
                .findChildElement(txbInputField, By.xpath("/strong"), ElementType.LABEL, ElementState.EXISTS_IN_ANY_STATE)
                .getText();
    }
}
