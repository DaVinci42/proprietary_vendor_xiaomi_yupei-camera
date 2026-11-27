package miui.os;

import java.lang.reflect.Method;

public class Build {
    private static String getSystemProperty(String key, String def) {
        try {
            Class<?> sp = Class.forName("android.os.SystemProperties");
            Method get = sp.getMethod("get", String.class, String.class);
            String val = (String) get.invoke(null, key, def);
            return val != null ? val : def;
        } catch (Throwable ignored) {
            return def;
        }
    }

    public static final String DEVICE = android.os.Build.DEVICE;
    public static final String MODEL = android.os.Build.MODEL;
    public static final boolean IS_INTERNATIONAL_BUILD =
            getSystemProperty("ro.product.mod_device", "").endsWith("_global")
                    || "global".equalsIgnoreCase(getSystemProperty("ro.miui.customized.region", ""));
    public static final boolean IS_GLOBAL_BUILD = IS_INTERNATIONAL_BUILD;
    public static final boolean IS_TABLET = true;
    public static final boolean IS_STABLE_VERSION = true;
    public static final boolean IS_DEVELOPMENT_VERSION = false;
    public static final boolean IS_ALPHA_BUILD = false;
    public static final boolean IS_DEBUGGABLE = false;
    public static final boolean IS_CTS_BUILD = false;
    public static final boolean IS_CM_CUSTOMIZATION = false;
    public static final boolean IS_CU_CUSTOMIZATION = false;
    public static final boolean IS_CT_CUSTOMIZATION = false;

    public static String getRegion() {
        return IS_INTERNATIONAL_BUILD ? "GLOBAL" : "CN";
    }

    public static boolean checkRegion(String region) {
        return region != null && region.equalsIgnoreCase(getRegion());
    }
}
