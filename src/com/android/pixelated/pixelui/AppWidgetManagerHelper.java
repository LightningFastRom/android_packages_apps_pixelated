package com.android.pixelated.pixelui;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.pixelated.Launcher;
import com.android.pixelated.Utilities;

public class AppWidgetManagerHelper {
	
    private final int mAppWidgetId;
    private final AppWidgetManager mAppWidgetManager;

    public AppWidgetManagerHelper(Context context) {
        mAppWidgetManager = AppWidgetManager.getInstance(context);
        mAppWidgetId = getAppWidgetId(context);
    }

    public Bundle getAppWidgetOptions() {
        return mAppWidgetId != -1 ? mAppWidgetManager.getAppWidgetOptions(mAppWidgetId) : null;
    }

    public void updateAppWidgetOptions(Bundle bundle) {
        if (mAppWidgetId != -1) {
            mAppWidgetManager.updateAppWidgetOptions(mAppWidgetId, bundle);
        }
    }

    private int getAppWidgetId(Context context) {
        Object obj = 1;
        SharedPreferences prefs = Utilities.getPrefs(context);
        int i = prefs.getInt("bundle_store_widget_id", -1);
        Object obj2 = i == -1 ? 1 : null;
        ComponentName provider = new ComponentName(context, Launcher.class);
        if (obj2 == null) {
            AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(i);
            if (appWidgetInfo != null && provider.equals(appWidgetInfo.provider)) {
                obj = null;
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            return i;
        }
        AppWidgetHost appWidgetHost = new AppWidgetHost(context, 2048);
        appWidgetHost.deleteHost();
        int appWidgetId = appWidgetHost.allocateAppWidgetId();
        if (!mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId, provider)) {
            appWidgetHost.deleteAppWidgetId(appWidgetId);
            appWidgetId = -1;
        }
        prefs.edit().putInt("bundle_store_widget_id", appWidgetId).apply();
        return appWidgetId;
    }
}