package pageobject;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private ExampleForm exampleForm = new ExampleForm();

    public ExampleForm getExampleForm() {
        return exampleForm;
    }

    public MainPage() {
        super(By.xpath("//div[@class='example']"), "example");
    }
}
