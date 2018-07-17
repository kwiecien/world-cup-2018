package com.kk.worldcup2018;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.kk.worldcup2018.model.Standings;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.widget.FavoriteTeamWidget;

import org.parceler.Parcels;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class FavoriteTeamService extends IntentService {
    public static final String ACTION_UPDATE_FAVORITE_TEAM = "com.kk.worldcup2018.action.update_favorite_team";
    public static final String EXTRA_PARAM_TEAM = "com.kk.worldcup2018.extra.team";
    public static final String EXTRA_PARAM_STANDINGS = "com.kk.worldcup2018.extra.standings";

    public FavoriteTeamService() {
        super("FavoriteTeamService");
    }

    public static void startActionUpdateFavoriteTeamWidgets(Context context, Team newFavoriteTeam, Standings standings) {
        Intent intent = new Intent(context, FavoriteTeamService.class);
        intent.setAction(ACTION_UPDATE_FAVORITE_TEAM);
        intent.putExtra(EXTRA_PARAM_TEAM, Parcels.wrap(newFavoriteTeam));
        intent.putExtra(EXTRA_PARAM_STANDINGS, Parcels.wrap(standings));
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_FAVORITE_TEAM.equals(action)) {
                final Team team = Parcels.unwrap(intent.getParcelableExtra(EXTRA_PARAM_TEAM));
                final Standings standings = Parcels.unwrap(intent.getParcelableExtra(EXTRA_PARAM_STANDINGS));
                handleActionUpdateFavoriteTeam(team, standings);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpdateStandings(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action UpdateFavoriteTeam in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpdateFavoriteTeam(Team newTeam, Standings standings) {
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = widgetManager.getAppWidgetIds(new ComponentName(this, FavoriteTeamWidget.class));
        widgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_layout);
        FavoriteTeamWidget.updateFavoriteTeamWidgets(this, widgetManager, newTeam, standings, appWidgetIds);
    }
}
