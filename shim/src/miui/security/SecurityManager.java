package miui.security;

public class SecurityManager {
    public boolean checkAccessControlPass(String packageName) {
        return true;
    }

    public boolean getApplicationAccessControlEnabled(String packageName) {
        return false;
    }
}
