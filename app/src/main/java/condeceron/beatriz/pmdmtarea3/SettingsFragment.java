package condeceron.beatriz.pmdmtarea3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference languageElement = findPreference("language");
        if (languageElement != null) {
            languageElement.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    final View content = getLayoutInflater().inflate(R.layout.language_dialog, null);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String languageSelected = preferences.getString("language", "es");

                    if (languageSelected.equals("en")) {
                        RadioButton radioButton = content.findViewById(R.id.radio_english);
                        radioButton.setChecked(true);
                    } else {
                        RadioButton radioButton = content.findViewById(R.id.radio_spanish);
                        radioButton.setChecked(true);
                    }

                    builder.setTitle(R.string.change_language)
                            .setView(content)
                            .show();

                    RadioGroup radioGroup = content.findViewById(R.id.radio_group_languages);
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if (checkedId != -1) {
                                RadioButton selectedRadioButton = content.findViewById(checkedId);
                                String languageCode = selectedRadioButton.getTag().toString();

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("language", languageCode);
                                editor.apply();

                                changeLanguage(languageCode);
                            }
                        }
                    });

                    return true;
                }
            });
        }

        Preference aboutElement = findPreference("about");
        if (aboutElement != null) {
            aboutElement.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.about))
                        .setMessage(getString(R.string.author)+"\n"+getString(R.string.version))
                        .show();
                    return true;
                }
            });
        }

        Preference logoutElement = findPreference("logout");
        if (logoutElement != null) {
            logoutElement.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.logout))
                        .setMessage(R.string.logout_confirmation)
                        .setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        FirebaseAuth.getInstance().signOut();
                                        Intent intent = new Intent(getContext(), LoginActivity.class);
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton(R.string.no,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .show();
                    return true;
                }
            });
        }

    }

    private void changeLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        updateLanguagesView();
    }

    private void updateLanguagesView() {
        findPreference("language").setTitle(R.string.language);
        findPreference("delete_pokemon").setTitle(R.string.delete_pokemon);;
        findPreference("about").setTitle(R.string.about);;
        findPreference("logout").setTitle(R.string.logout);;
    }

}