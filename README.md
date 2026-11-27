# Stock HyperOS 4 `MiuiCamera` Port (`yupei` / `yupei_d`)

Standalone, zero-AOSP-patch `MiuiCamera` integration (`6.6.000780.0`, overrides `Aperture`) for PixelOS 17.0 on Xiaomi Pad 8 / Pad 9 (`yupei` / `yupei_d`).

## Contents

- `shim/` (`miui-cameraopt`) — Minimal HyperOS framework Java shim (`FeatureParser`, `miui.os.Build`, `CameraOptManager`, `CamOptClient`, `QuickCameraManager`, `HapticFeedbackUtil`, `DisplayFeatureManager`, `MimotionUtils`, `MiuiSettings`).
- `gallery-bridge/` (`MiuiGalleryBridge`, `com.miui.gallery`) — Launcher-less `Theme.NoDisplay` bridge forwarding thumbnail clicks (`MAIN`, `VIEW_EMPTY_PHOTO`) and `com.android.camera.action.REVIEW` directly to Google Photos (`com.google.android.apps.photos`), inferring missing MIME types without re-granting MediaStore URIs.
- `etc/` — `device_features/{yupei,yupei_d}.xml`, `privapp-permissions`, `default-permissions`, and `miui-cameraopt.xml`.
- `proprietary/product/priv-app/MiuiCamera/MiuiCamera.apk` — **Git-ignored** closed-source stock APK. Extract locally from stock HyperOS `product` image before building:
  ```bash
  ./pixelos-ota-range -p product -o /tmp/yupei-product \
    "https://xiaomirom.com/en/rom/xiaomi-pad-8-yupei-china-fastboot-recovery-rom/"
  mkdir -p vendor/xiaomi/yupei-camera/proprietary/product/priv-app/MiuiCamera
  cp /tmp/yupei-product/product/priv-app/MiuiCamera/MiuiCamera.apk \
    vendor/xiaomi/yupei-camera/proprietary/product/priv-app/MiuiCamera/
  ```

## Integration & Opt-Out

Included via `$(call inherit-product-if-exists, vendor/xiaomi/yupei-camera/config.mk)` in `device/xiaomi/yupei/device.mk`. Removing this directory cleanly falls back to PixelOS stock `Aperture`.

## Maintenance

- **PixelOS upstream updates**: Keep `vendor/xiaomi/yupei-camera` in `LOCAL_PROTECTED_PATHS` (`pixelos-sync`). After Android API / SEPolicy bumps, check `adb logcat | grep avc:` and update `device/xiaomi/yupei/sepolicy/vendor/hal_camera.te` (`platform_app_36` / `hal_camera_default`). Do not override `config_defaultQrCodeComponent` so GMS Quick Settings QR scanner stays active.
- **Stock HyperOS updates**: Extract updated `product/priv-app/MiuiCamera/MiuiCamera.apk` locally into `proprietary/product/priv-app/MiuiCamera/` and sync `etc/device_features/yupei*.xml`; if a new APK throws `NoSuchMethodError` against `miui-cameraopt`, add the missing stub in `shim/src/`.
