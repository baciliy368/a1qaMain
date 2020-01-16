package vk.enums;

public enum EndPoints {
    WALL_EDIT("wall.edit"),
    WALL_POST("wall.post"),
    PHOTOS_WALL_UPLOAD_SERVER("photos.getWallUploadServer"),
    SAVE_PHOTO("photos.saveWallPhoto"),
    CREATE_COMMENT("wall.createComment"),
    LIKES_IS_LIKED("likes.isLiked"),
    WALL_DELETE("wall.delete"),
    DOCS_WALL_UPLOAD_SERVER("docs.getWallUploadServer"),
    SAVE_DOCS("docs.save");
    private String action;

    EndPoints(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}
