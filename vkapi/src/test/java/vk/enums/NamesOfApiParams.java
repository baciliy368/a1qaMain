package vk.enums;

public enum NamesOfApiParams {
    MESSAGE("message"),
    POST_ID("post_id"),
    OWNER_ID("owner_id"),
    USER_ID("user_id"),
    ITEM_ID("item_id"),
    TYPE("type"),
    FILE("file"),
    PHOTO("photo"),
    SERVER("server"),
    HASH("hash");
    private String action;

    NamesOfApiParams(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}
