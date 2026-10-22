# Android App Settings Opener

Минималистичное Android-приложение, которое при открытии мгновенно перенаправляет пользователя на системный экран со списком всех установленных приложений Android.

## Как это устроено:
1. Приложение содержит единственную Activity (`MainActivity.kt`).
2. В методе `onCreate()` запускается Intent с действием `Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS`.
3. В `AndroidManifest.xml` установлен стиль `@android:style/Theme.NoDisplay`, благодаря чему приложение не мерцает белым окном, а сразу открывает системные настройки.
4. Вызов `finish()` сразу удаляет приложение из стека, поэтому при нажатии «Назад» пользователь возвращается на домашний экран.

## Как собрать APK в Android Studio:
1. Распакуйте архив проекта.
2. Откройте папку проекта в **Android Studio** (File -> Open).
3. Дождитесь синхронизации Gradle.
4. Запустите на устройстве или соберите APK: **Build -> Build Bundle(s) / APK(s) -> Build APK(s)**.

## Запуск через консоль ADB (для быстрой проверки):
```bash
adb shell am start -a android.settings.MANAGE_APPLICATIONS_SETTINGS
```
