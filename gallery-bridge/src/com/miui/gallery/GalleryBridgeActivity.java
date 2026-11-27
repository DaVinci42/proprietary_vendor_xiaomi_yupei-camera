package com.miui.gallery;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class GalleryBridgeActivity extends Activity {
    private static final String TAG = "MiuiGalleryBridge";
    private static final String PHOTOS_PACKAGE = "com.google.android.apps.photos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forwardToPhotos(getIntent());
        finish();
    }

    private void forwardToPhotos(Intent incoming) {
        Uri data = incoming != null ? incoming.getData() : null;
        String type = incoming != null ? incoming.getType() : null;

        if (data != null) {
            if (type == null) {
                try {
                    type = getContentResolver().getType(data);
                } catch (RuntimeException ignored) {
                }
                if (type == null) {
                    type = data.toString().contains("/video/") ? "video/*" : "image/*";
                }
            }
            Intent view = new Intent(Intent.ACTION_VIEW);
            view.setDataAndType(data, type);
            view.setPackage(PHOTOS_PACKAGE);
            view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                startActivity(view);
                return;
            } catch (RuntimeException e) {
                Log.w(TAG, "Google Photos failed for ACTION_VIEW, falling back", e);
                view.setPackage(null);
                try {
                    startActivity(view);
                    return;
                } catch (RuntimeException ignored) {
                }
            }
        }

        Intent launch = getPackageManager().getLaunchIntentForPackage(PHOTOS_PACKAGE);
        if (launch != null) {
            launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                startActivity(launch);
            } catch (ActivityNotFoundException e) {
                Log.w(TAG, "Failed to launch Google Photos", e);
            }
        }
    }
}
