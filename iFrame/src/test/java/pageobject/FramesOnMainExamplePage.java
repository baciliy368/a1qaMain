package pageobject;

public enum FramesOnMainExamplePage {
    textInputArea("mce_0_ifr");
    private String idStream;

    FramesOnMainExamplePage(String idStream) {
        this.idStream = idStream;
    }

    public String getIdStream() {
        return idStream;
    }
}
