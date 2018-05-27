package org.bubenheimer.zoomgesturetile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import static org.bubenheimer.zoomgesturetile.Utils.isWriteSecureSettingsPermissionGranted;

public final class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBar(findViewById(R.id.toolbar));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs_frame, new SettingsFragment())
                .commit();
    }

    public static final class SettingsFragment extends PreferenceFragmentCompat
            implements Preference.OnPreferenceClickListener {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getWriteSecureSettingsPreference().setOnPreferenceClickListener(this);
        }

        @Override
        public void onCreatePreferences(
                final Bundle savedInstanceState,
                final String rootKey) {
            setPreferencesFromResource(R.xml.settings, rootKey);
        }

        @Override
        public void onDestroy() {
            getWriteSecureSettingsPreference().setOnPreferenceClickListener(null);
            super.onDestroy();
        }

        @Override
        public boolean onPreferenceClick(final Preference preference) {
            Utils.getPermissionsReminderDialog(getContext()).show();
            return false;
        }

        @Override
        public void onResume() {
            super.onResume();

            getWriteSecureSettingsPreference().setSummary(
                    isWriteSecureSettingsPermissionGranted(getContext()) ?
                            R.string.granted : R.string.not_granted);
        }

        private Preference getWriteSecureSettingsPreference() {
            return findPreference(getContext().getString(R.string.pref_write_secure_settings));
        }
    }
}
