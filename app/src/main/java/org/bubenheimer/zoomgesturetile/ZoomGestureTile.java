package org.bubenheimer.zoomgesturetile;

import android.content.Intent;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public final class ZoomGestureTile extends TileService {
    private static final String DISPLAY_ZOOM_ENABLED = "accessibility_display_zoom_enabled";

    @Override
    public void onStartListening() {
        setState(isDisplayZoomEnabled());
    }

    @Override
    public void onClick() {
        if (!Utils.isWriteSecureSettingsPermissionGranted(this)) {
            final Intent mainActivity = new Intent(this, MainActivity.class);
            mainActivity.setAction(Utils.MISSING_PERMISSION);
            mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainActivity);
        } else {
            final Tile qsTile = getQsTile();
            final boolean enable = qsTile.getState() == Tile.STATE_INACTIVE;
            setState(enable);
            setDisplayZoomEnabled(enable);
        }
    }

    private void setState(final boolean enabled) {
        final Tile qsTile = getQsTile();
        qsTile.setState(enabled ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        qsTile.updateTile();
    }

    private boolean isDisplayZoomEnabled() {
        return Settings.Secure.getInt(getContentResolver(), DISPLAY_ZOOM_ENABLED, 0) != 0;
    }

    private void setDisplayZoomEnabled(final boolean enabled) {
        Settings.Secure.putInt(getContentResolver(), DISPLAY_ZOOM_ENABLED, enabled ? 1 : 0);
    }
}
