package tests.enums;

public enum EndPoint {
    POSTS,
    USERS;

    @Override
    public String toString() {
        return String.format("%s",super.toString().toLowerCase());
    }
}
