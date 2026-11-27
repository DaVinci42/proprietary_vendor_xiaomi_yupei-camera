package miui.hardware.display;

public class DisplayFeatureManager {
    private static final DisplayFeatureManager sInstance = new DisplayFeatureManager();

    public static DisplayFeatureManager getInstance() {
        return sInstance;
    }

    public void setScreenEffect(int mode, int value) {
    }
}
