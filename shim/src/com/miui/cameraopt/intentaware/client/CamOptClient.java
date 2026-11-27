package com.miui.cameraopt.intentaware.client;

import java.util.Map;
import org.json.JSONObject;

public class CamOptClient {
    public interface SocketListener {
        void onReceivedEvent(String event);
    }

    private static final CamOptClient sInstance = new CamOptClient();

    public static CamOptClient getInstance() {
        return sInstance;
    }

    public void disConect() {}
    public void onReceivedMessage(String msg) {}
    public void sendEvent(String event) {}
    public void sendForwardMessage(String src, String dst, Map<String, Object> data) {}
    public void sendMessage(int type, String msg) {}
    public void sendMessage(int type, Map<String, Object> data) {}
    public void sendMessage(int type, JSONObject json) {}
    public void setListener(SocketListener listener) {}
}
