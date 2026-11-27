package miui.util;

import android.os.Build;
import android.util.Log;
import android.util.Xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

public class FeatureParser {
    private static final String TAG = "FeatureParserShim";
    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_INTEGER = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_STRING_ARRAY = 4;
    public static final int TYPE_INTEGER_ARRAY = 5;
    public static final int TYPE_FLOAT = 6;

    private static final Map<String, Boolean> sBooleans = new HashMap<>();
    private static final Map<String, Integer> sIntegers = new HashMap<>();
    private static final Map<String, String> sStrings = new HashMap<>();
    private static final Map<String, Float> sFloats = new HashMap<>();
    private static final Map<String, String[]> sStringArrays = new HashMap<>();
    private static final Map<String, int[]> sIntArrays = new HashMap<>();

    static {
        loadFeatures();
    }

    private static void loadFeatures() {
        String[] candidates = new String[] {
            "/product/etc/device_features/" + Build.DEVICE + ".xml",
            "/product/etc/device_features/yupei.xml",
            "/system/etc/device_features/" + Build.DEVICE + ".xml",
            "/system/etc/device_features/yupei.xml",
        };
        File target = null;
        for (String path : candidates) {
            File f = new File(path);
            if (f.isFile() && f.canRead()) {
                target = f;
                break;
            }
        }
        if (target == null) {
            Log.w(TAG, "No device_features xml found");
            return;
        }
        try (InputStream is = new FileInputStream(target)) {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");
            int event = parser.getEventType();
            String currentArrayTag = null;
            String currentArrayName = null;
            List<String> strItems = null;
            List<Integer> intItems = null;

            while (event != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG) {
                    String tag = parser.getName();
                    if ("bool".equals(tag)) {
                        String name = parser.getAttributeValue(null, "name");
                        String text = parser.nextText();
                        if (name != null) {
                            sBooleans.put(name, Boolean.parseBoolean(text.trim()));
                        }
                    } else if ("integer".equals(tag)) {
                        String name = parser.getAttributeValue(null, "name");
                        String text = parser.nextText();
                        if (name != null) {
                            try {
                                sIntegers.put(name, Integer.parseInt(text.trim()));
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    } else if ("string".equals(tag)) {
                        String name = parser.getAttributeValue(null, "name");
                        String text = parser.nextText();
                        if (name != null) {
                            sStrings.put(name, text);
                        }
                    } else if ("float".equals(tag)) {
                        String name = parser.getAttributeValue(null, "name");
                        String text = parser.nextText();
                        if (name != null) {
                            try {
                                sFloats.put(name, Float.parseFloat(text.trim()));
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    } else if ("string-array".equals(tag)) {
                        currentArrayTag = tag;
                        currentArrayName = parser.getAttributeValue(null, "name");
                        strItems = new ArrayList<>();
                    } else if ("integer-array".equals(tag)) {
                        currentArrayTag = tag;
                        currentArrayName = parser.getAttributeValue(null, "name");
                        intItems = new ArrayList<>();
                    } else if ("item".equals(tag)) {
                        String text = parser.nextText();
                        if ("string-array".equals(currentArrayTag) && strItems != null) {
                            strItems.add(text);
                        } else if ("integer-array".equals(currentArrayTag) && intItems != null) {
                            try {
                                intItems.add(Integer.parseInt(text.trim()));
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                } else if (event == XmlPullParser.END_TAG) {
                    String tag = parser.getName();
                    if ("string-array".equals(tag) && currentArrayName != null && strItems != null) {
                        sStringArrays.put(currentArrayName, strItems.toArray(new String[0]));
                        currentArrayTag = null;
                        currentArrayName = null;
                        strItems = null;
                    } else if ("integer-array".equals(tag) && currentArrayName != null && intItems != null) {
                        int[] arr = new int[intItems.size()];
                        for (int i = 0; i < intItems.size(); i++) {
                            arr[i] = intItems.get(i);
                        }
                        sIntArrays.put(currentArrayName, arr);
                        currentArrayTag = null;
                        currentArrayName = null;
                        intItems = null;
                    }
                }
                event = parser.next();
            }
        } catch (Throwable t) {
            Log.e(TAG, "Failed to parse " + target, t);
        }
    }

    public static boolean hasFeature(String name, int type) {
        switch (type) {
            case TYPE_BOOLEAN: return sBooleans.containsKey(name);
            case TYPE_INTEGER: return sIntegers.containsKey(name);
            case TYPE_STRING: return sStrings.containsKey(name);
            case TYPE_STRING_ARRAY: return sStringArrays.containsKey(name);
            case TYPE_INTEGER_ARRAY: return sIntArrays.containsKey(name);
            case TYPE_FLOAT: return sFloats.containsKey(name);
            default: return false;
        }
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        Boolean v = sBooleans.get(name);
        return v != null ? v : defaultValue;
    }

    public static int getInteger(String name, int defaultValue) {
        Integer v = sIntegers.get(name);
        return v != null ? v : defaultValue;
    }

    public static String getString(String name) {
        return sStrings.get(name);
    }

    public static Float getFloat(String name, float defaultValue) {
        Float v = sFloats.get(name);
        return v != null ? v : defaultValue;
    }

    public static String[] getStringArray(String name) {
        return sStringArrays.get(name);
    }

    public static int[] getIntArray(String name) {
        return sIntArrays.get(name);
    }
}
