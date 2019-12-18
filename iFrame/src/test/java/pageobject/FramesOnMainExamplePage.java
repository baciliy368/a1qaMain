package pageobject;

import framework.FrameManagerInterface;

public enum FramesOnMainExamplePage implements FrameManagerInterface {
    textInputArea("mce_0_ifr");
    private String idStream;

    FramesOnMainExamplePage(String idStream) {
        this.idStream = idStream;
    }

    @Override
    public String getIdStream() {
        return idStream;
    }
}
