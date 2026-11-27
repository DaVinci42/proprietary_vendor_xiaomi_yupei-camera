package com.miui.cameraopt.quickcamera;

import android.content.Intent;
import org.json.JSONObject;

public class QuickCameraManager {
    private static final QuickCameraManager sInstance = new QuickCameraManager();

    public static QuickCameraManager getInstance() {
        return sInstance;
    }

    public boolean isEnabled() {
        return false;
    }

    public void notify(Intent intent) {}
    public void onBootCompleted() {}
    public boolean sendEvent(int event) { return false; }
    public boolean sendEvent(int event, String data) { return false; }
    public void updateCloudConfig(JSONObject json) {}
}
