package com.example.appsettingsopener

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

/**
 * Главная активность приложения.
 * При запуске сразу открывает системный экран со списком всех установленных приложений
 * и немедленно завершает свою работу (finish()), чтобы не оставлять пустой экран в стеке.
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Список Intent'ов в порядке приоритета для совместимости со всеми версиями Android
        val intents = listOf(
            // Основной системный Intent для списка всех приложений
            Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS),
            // Альтернативный Intent настроек приложений (Android 9+)
            Intent(Settings.ACTION_APPLICATION_SETTINGS),
            // Действие для Android 11+ (MANAGE_ALL_APPLICATIONS_SETTINGS)
            Intent("android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS")
        )

        var started = false
        for (intent in intents) {
            try {
                // Флаги для запуска из чистого контекста и исключения лишней истории
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                started = true
                break
            } catch (e: ActivityNotFoundException) {
                // Если данный Action не поддерживается конкретной прошивкой, пробуем следующий
            }
        }

        if (!started) {
            // Резервный вариант: открытие главного меню системных настроек
            try {
                startActivity(Intent(Settings.ACTION_SETTINGS))
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Не удалось открыть настройки приложений",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Завершаем текущую Activity, чтобы при нажатии "Назад" пользователь
        // возвращался на рабочий стол, а не в приложение
        finish()
    }
}
