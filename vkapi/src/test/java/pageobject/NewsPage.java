package pageobject;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {
    private RightBarForm rightBarForm = new RightBarForm();

    public NewsPage() {
        super(By.id("ui_rmenu_news"), "News marker");
    }

    public RightBarForm getRightBarForm() {
        return rightBarForm;
    }
}
