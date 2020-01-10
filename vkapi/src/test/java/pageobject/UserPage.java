package pageobject;

import aquality.selenium.elements.ElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserPage extends Form {
    private UserIconForm userIconForm = new UserIconForm();
    private QuickLoginForm quickLoginForm = new QuickLoginForm();

    public UserPage() {
        super(By.id("profile_edit_act"), "edit user profile");
    }

    public String getLinkOnImg(String idOfAttachment) {
        return new ElementFactory().getButton(By.xpath(String.format("//a[@href='/%s']", idOfAttachment)), "ing Button").getAttribute("style")
                .replaceAll(".+url\\(\"", "")
                .replaceAll("\".+", "");
    }

    public PostsForm getPostsForm() {
        return new PostsForm();
    }

    public String getIdOfUser() {
        return userIconForm.getIdOfUserNumbers();
    }

    public void  logOut() {
        userIconForm.logout();
    }

    public QuickLoginForm getQuickLoginForm() {
        return quickLoginForm;
    }
}
