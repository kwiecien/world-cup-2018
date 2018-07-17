package com.kk.worldcup2018.view.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteTeamWidget extends AppWidgetProvider {

    private static Team favoriteTeam;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorite_team_widget);
        setWidgetText(favoriteTeam, views);
        setPendingIntent(context, views);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static void setPendingIntent(Context context, RemoteViews views) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
    }

    private static void setWidgetText(Team favoriteTeam, RemoteViews views) {
        if (favoriteTeam != null) {
            views.setTextViewText(R.id.widget_team, favoriteTeam.getName());
        }
    }

    public static void updateFavoriteTeamWidgets(Context context, AppWidgetManager appWidgetManager,
                                                 Team newFavoriteTeam, int[] appWidgetIds) {
        favoriteTeam = newFavoriteTeam;
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // TODO
    }

}

