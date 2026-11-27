package miui.util;

import android.content.Context;
import android.os.VibrationAttributes;

public class HapticFeedbackUtil {
    public HapticFeedbackUtil(Context context, boolean once) {
    }

    public boolean isSupportLinearMotorVibrate() {
        return false;
    }

    public boolean isSupportLinearMotorVibrate(int effectId) {
        return false;
    }

    public boolean performExtHapticFeedback(int effectId) {
        return false;
    }

    public boolean performExtHapticFeedback(int effectId, double intensity, String reason) {
        return false;
    }

    public boolean performHapticFeedback(int effectId, double intensity, String reason) {
        return false;
    }

    public boolean performHapticFeedback(int effectId, boolean always) {
        return false;
    }

    public boolean performHapticFeedback(int effectId, boolean always, int effectKey) {
        return false;
    }

    public boolean performHapticFeedback(
            VibrationAttributes attrs, int effectId, double intensity, String reason) {
        return false;
    }

    public boolean performHapticFeedback(
            VibrationAttributes attrs, int effectId, boolean always) {
        return false;
    }

    public boolean performHapticFeedback(
            VibrationAttributes attrs, int effectId, boolean always, int effectKey) {
        return false;
    }
}
