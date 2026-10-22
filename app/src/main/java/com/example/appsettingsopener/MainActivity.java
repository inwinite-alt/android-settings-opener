package com.example.appsettingsopener;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Реализация на чистом Java для разработчиков без Kotlin.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] actions = new String[] {
            Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS,
            Settings.ACTION_APPLICATION_SETTINGS,
            "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS"
        };

        boolean started = false;
        for (String action : actions) {
            try {
                Intent intent = new Intent(action);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                started = true;
                break;
            } catch (ActivityNotFoundException ignored) {
            }
        }

        if (!started) {
            try {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            } catch (Exception e) {
                Toast.makeText(this, "Не удалось открыть настройки приложений", Toast.LENGTH_LONG).show();
            }
        }

        finish();
    }
}
