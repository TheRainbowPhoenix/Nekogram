package tw.nekomimi.nekogram.helpers;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

import java.util.HashMap;

import tw.nekomimi.nekogram.Extra;

public class AnalyticsHelper {
    private static SharedPreferences preferences;

    public static boolean sendBugReport = false;
    public static boolean analyticsDisabled = true;
    public static String userId = null;

    public static void start(Application application) {
        preferences = application.getSharedPreferences("nekoanalytics", Application.MODE_PRIVATE);
        analyticsDisabled = true;
        sendBugReport = false;
        FileLog.d("Analytics disabled");
    }

    private static String generateUserID() {
        return Utilities.generateRandomString(32);
    }

    public static void trackScreenView() {
    }

    public static void trackEvent(String event) {
    }

    public static void trackEvent(String event, HashMap<String, String> map) {
    }

    public static boolean isSettingsAvailable() {
        return false;
    }

    public static void setAnalyticsDisabled() {
        AnalyticsHelper.analyticsDisabled = true;
        preferences.edit().putBoolean("analyticsDisabled", true).apply();
    }

    public static void toggleSendBugReport() {
        AnalyticsHelper.sendBugReport = false;
        preferences.edit().putBoolean("sendBugReport", false).apply();
    }
}
