package pageobject;

import aquality.selenium.forms.Form;
import framework.browser.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostOnPage extends Form {
    private static final By LIKE_LOCATOR = By.xpath("//a[contains(@class, 'like')]");
    private WebElement rootElement;

    PostOnPage(WebElement element) {
        super(By.xpath("//a[contains(@class, 'share')]"), "share button");
        rootElement = element;
    }

    public void clickLike() {
        rootElement.findElement(LIKE_LOCATOR).click();
    }

    public String getId() {
        return rootElement.getAttribute("data-post-id").replaceAll(".+_", "");
    }

    public String getText() {
        return rootElement.findElement(By.xpath("//div[contains(@class,'post_text')]")).getText();
    }

    public String getOwnerId() {
        return rootElement.getAttribute("data-post-id").replaceAll("_.+", "");
    }

    public boolean isCommentAdded() {
        return Driver.getElementAfterWaitOfVisible(By.xpath("//a[contains(@class,'replies_next')]")).isDisplayed();
    }

    public String getNameOfFileInPost() {
        return rootElement.findElement(By.xpath("//a[@class='page_doc_title']")).getText();
    }
}
