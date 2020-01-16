package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PostsForm extends Form {

    PostsForm() {
        super(By.id("profile_wall"), "posts Header");
    }

    public PostForm getPost(String id) {
        final ILabel post = new ElementFactory().getLabel(By.xpath(String.format("//div[contains(@data-post-id,'%s')]", id)), "post");
        return new PostForm(post);
    }
}
