package pajeobject;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.forms.Form;
import framework.utils.JsonParser;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AuthorizationResultPage extends Form {
    private WebElement jsnResult = BrowserManager.getBrowser().getDriver().findElement(By.tagName("//body"));
    private JSONObject obj = new JSONObject(jsnResult.getText());

    public AuthorizationResultPage() {
        super(By.tagName("//body"), "jsonResult");
    }

    public String getValueOfAuthorization(String key) {
        return JsonParser.getValue(obj, key);
    }
}
