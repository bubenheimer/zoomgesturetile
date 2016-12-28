package org.bubenheimer.zoomgesturetile;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

final class Utils {
    static final String MISSING_PERMISSION = "missing_permission";

    static boolean isWriteSecureSettingsPermissionGranted(final Context context) {
        return context.getPackageManager().checkPermission(
                Manifest.permission.WRITE_SECURE_SETTINGS,
                context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }

    private Utils() {
        throw new UnsupportedOperationException();
    }
}
