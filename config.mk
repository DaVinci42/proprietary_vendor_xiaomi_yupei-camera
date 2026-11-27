#
# SPDX-FileCopyrightText: 2026 The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

PRODUCT_PACKAGES += \
    MiuiCamera \
    MiuiGalleryBridge \
    miui-cameraopt

PRODUCT_COPY_FILES += \
    vendor/xiaomi/yupei-camera/etc/default-permissions-miuicamera.xml:$(TARGET_COPY_OUT_PRODUCT)/etc/default-permissions/default-permissions-miuicamera.xml \
    vendor/xiaomi/yupei-camera/etc/device_features/yupei.xml:$(TARGET_COPY_OUT_PRODUCT)/etc/device_features/yupei.xml \
    vendor/xiaomi/yupei-camera/etc/device_features/yupei_d.xml:$(TARGET_COPY_OUT_PRODUCT)/etc/device_features/yupei_d.xml \
    vendor/xiaomi/yupei-camera/etc/miui-cameraopt.xml:$(TARGET_COPY_OUT_PRODUCT)/etc/permissions/miui-cameraopt.xml \
    vendor/xiaomi/yupei-camera/etc/privapp-permissions-miuicamera.xml:$(TARGET_COPY_OUT_PRODUCT)/etc/permissions/privapp-permissions-miuicamera.xml

PRODUCT_PRODUCT_PROPERTIES += \
    ro.mi.os.version.code=4 \
    ro.miui.customized.region=global

PRODUCT_VENDOR_PROPERTIES += \
    persist.vendor.camera.privapp.list=org.lineageos.aperture,com.android.camera
