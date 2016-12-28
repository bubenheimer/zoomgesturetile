package org.bubenheimer.zoomgesturetile;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.bubenheimer.zoomgesturetile.databinding.ActivityMainBinding;

import static org.bubenheimer.zoomgesturetile.Utils.isWriteSecureSettingsPermissionGranted;

public final class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        setActionBar(binding.toolbar);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs_frame, new SettingsFragment())
                .commit();

        if (Utils.MISSING_PERMISSION.equals(getIntent().getAction())) {
            final Snackbar snackbar = Snackbar.make(binding.getRoot(),
                    R.string.permission_needs_to_be_granted, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }

    public static final class SettingsFragment extends PreferenceFragment
            implements Preference.OnPreferenceClickListener {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            getWriteSecureSettingsPreference().setOnPreferenceClickListener(this);
        }

        @Override
        public void onDestroy() {
            getWriteSecureSettingsPreference().setOnPreferenceClickListener(null);
            super.onDestroy();
        }

        @Override
        public boolean onPreferenceClick(final Preference preference) {
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.permission_request_title)
                    .setMessage(getString(R.string.write_secure_settings_permission_msg,
                            BuildConfig.APPLICATION_ID))
                    .show();
            return false;
        }

        @Override
        public void onResume() {
            super.onResume();

            final Context context = getContext();
            getWriteSecureSettingsPreference().setSummary(
                    isWriteSecureSettingsPermissionGranted(context) ?
                            R.string.granted : R.string.not_granted);
        }

        private Preference getWriteSecureSettingsPreference() {
            return findPreference(getContext().getString(R.string.pref_write_secure_settings));
        }
    }
}
