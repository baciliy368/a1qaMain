package models.posts;

public class PostModel {
    private String id;
    private int userId;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
