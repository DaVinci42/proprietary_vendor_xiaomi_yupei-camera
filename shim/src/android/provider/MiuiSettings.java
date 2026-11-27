package android.provider;

import android.content.ContentResolver;
import java.util.Collections;
import java.util.List;

public class MiuiSettings {
    public static class Global {
        public static boolean getBoolean(ContentResolver cr, String name) {
            return Settings.Global.getInt(cr, name, 0) != 0;
        }
    }

    public static class System {
        public static boolean getBoolean(ContentResolver cr, String name, boolean def) {
            return Settings.System.getInt(cr, name, def ? 1 : 0) != 0;
        }
    }

    public static class Secure {
        public static boolean getBoolean(ContentResolver cr, String name, boolean def) {
            return Settings.Secure.getInt(cr, name, def ? 1 : 0) != 0;
        }
    }

    public static class SettingsCloudData {
        public static List<?> getCloudDataList(ContentResolver cr, String moduleName) {
            return Collections.emptyList();
        }

        public static String getCloudDataString(
                ContentResolver cr, String moduleName, String key, String defValue) {
            return defValue;
        }
    }
}
