package pageobject;

import aquality.selenium.forms.Form;
import framework.browser.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostsForm extends Form {

    PostsForm() {
        super(By.id("profile_wall"), "postsHeader");
    }

    private WebElement getPostWebElement(String id) {
        return Driver.getElementAfterWaitOfVisible(By.xpath(String.format("//div[contains(@data-post-id,'%s')]", id)));
    }

    public PostOnPage getPost(String id) {
        WebElement postWebElement = getPostWebElement(id);
        return new PostOnPage(postWebElement);
    }

    public boolean isPostDeleted(String id) {
        return Driver.isElementDeleted(getPostWebElement(id));
    }
}
