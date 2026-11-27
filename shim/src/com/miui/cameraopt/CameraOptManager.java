package com.miui.cameraopt;

import android.content.ComponentName;
import android.content.Intent;

public class CameraOptManager {
    public static boolean init() {
        return false;
    }

    public static void adjBoost(String pkg, int pid, long duration, int level) {}
    public static void boostCameraByThreshold(long threshold) {}
    public static void deInitFileLock() {}
    public static String dumpBacktraceToFileTimeout(int pid, String path, int timeout, int flags) { return ""; }
    public static void enterUnderwaterMode(IUnderwaterCallback cb, IPowerButtonListener listener) {}
    public static void exitUnderwaterMode() {}
    public static String getSystemStatusJson(int type) { return "{}"; }
    public static void heartBeatForUnderWater() {}
    public static void initFileLock() {}
    public static boolean interceptAppRestartIfNeeded(String pkg, String proc, int pid, int uid) { return false; }
    public static boolean isAllowCameraRestart(String pkg) { return true; }
    public static boolean isCameraInForeground() { return false; }
    public static boolean isCameraScene() { return false; }
    public static boolean isHasCaptureTask() { return false; }
    public static boolean isPerfLockEnabled(String scene) { return false; }
    public static boolean isScenePsiHigh() { return false; }
    public static boolean isSupportFileLock() { return false; }
    public static boolean isUnderwaterModeActive() { return false; }
    public static void killCameraProvider(String pkg, String reason, boolean force) {}
    public static void notify(Intent intent) {}
    public static void notify3rdAppProviderUsed(String pkg, String reason) {}
    public static void notifyActivityChanged(ComponentName comp, int state) {}
    public static void notifyActivityDisplayChange(
            int uid, int pid, String pkg, String act, String prev, int d1, int d2, int d3, int d4) {}
    public static void notifyActivityStateChange(
            int uid, int pid, String pkg, String act, String prev, int s1, int s2) {}
    public static void notifyActivityStateChange(
            int uid, int pid, String pkg, String act, String prev, int s1, int s2, int s3) {}
    public static void notifyCameraPerformanceTime(String tag, String info, long timeMs) {}
    public static void notifyCameraPostProcessState() {}
    public static void notifyFocusWindowChanged(int uid, int pid, int hash, String pkg) {}
    public static void notifyHeavyScenarioChanged(boolean heavy) {}
    public static void notifyProcessDied(int pid, int uid, String pkg, String proc) {}
    public static void notifyProcessStarted(int pid, int uid, String pkg, String proc) {}
    public static void notifyStartActivityFinish(Intent intent, int res, long start, long end) {}
    public static void onTransitionAnimateStateChanged(boolean animating, int type) {}
    public static int perfLockAcquire(String scene, String pkg) { return -1; }
    public static int perfLockAcquire(String scene, String pkg, long timeout) { return -1; }
    public static int perfLockRelease(int handle) { return 0; }
    public static void reclaimMemoryForCamera(long size) {}
    public static void reclaimMemoryForCamera(long size, int type, int flags) {}
    public static void relayoutWindow(
            int a, int b, int c, String d, String e, int f, int g, int h, int i, int j, int k) {}
    public static void removeWindow(int a, int b, int c, String d, String e) {}
    public static void reportMemPressure(int level) {}
    public static void requestScreenState(boolean on) {}
    public static void sendCameraEvent(int event, String data) {}
    public static void sendEvent(int event) {}
    public static void sendEvent(int event, String data) {}
    public static boolean tryLockFile(String path) { return true; }
    public static void unlockFile(String path) {}
    public static void updateCameraBoosterCloudData(double version, String data) {}
}
