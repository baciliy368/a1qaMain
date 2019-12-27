package models.postsmodels;

public class PostModel {
    private int id;
    private int userId;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
