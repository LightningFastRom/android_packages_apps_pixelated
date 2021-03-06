package com.android.pixelated.pixelui;

import android.widget.RemoteViews;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class WeatherUpdateReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        WeatherListener.getInstance(context).bH((RemoteViews)intent.getParcelableExtra("com.google.android.apps.nexuslauncher.weather_view"));
    }
}
