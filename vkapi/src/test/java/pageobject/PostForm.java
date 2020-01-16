package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PostForm extends Form {
    private static final By LIKE_LOCATOR = By.xpath("//a[contains(@class, 'like')]");
    private ILabel rootElement;

    PostForm(ILabel element) {
        super(By.xpath("//a[contains(@class, 'share')]"), "share button");
        rootElement = element;
    }

    public void clickLike() {
        rootElement.getElement().findElement(LIKE_LOCATOR).click();
    }

    public String getId() {
        return rootElement.getAttribute("data-post-id").replaceAll(".+_", "");
    }

    public String getText() {
        return rootElement.getElement().findElement(By.xpath("//div[contains(@class,'post_text')]")).getText();
    }

    public String getOwnerId() {
        return rootElement.getAttribute("data-post-id").replaceAll("_.+", "");
    }

    public boolean isCommentAdded() {
        return new ElementFactory().getLabel(By.xpath("//a[contains(@class,'replies_next')]"), "Comment")
                .state().waitForDisplayed();
    }

    public String getNameOfFileInPost() {
        return rootElement.getElement().findElement(By.xpath("//a[@class='page_doc_title']")).getText();
    }
}
