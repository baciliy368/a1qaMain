package pajeobject;

import aquality.selenium.browser.BrowserManager;
import aquality.selenium.forms.Form;
import framework.utils.JsonParser;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AuthorizationResultPage extends Form {
    private WebElement resultBody = BrowserManager.getBrowser().getDriver().findElement(By.tagName("body"));
    private JSONObject jsonResultOfAuthentication = new JSONObject(resultBody.getText());

    public AuthorizationResultPage() {
        super(By.tagName("body"), "json Result of Authentication");
    }

    public Object getValueOfAuthorization(String key) {
        return JsonParser.getValue(jsonResultOfAuthentication, key);
    }
}
