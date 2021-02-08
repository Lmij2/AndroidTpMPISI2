package mpisi2.devtp.tp_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyWidget extends AppWidgetProvider {

    private Timer myTimer;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews views1 = new RemoteViews(context.getPackageName(), R.layout.interface_widget_layout);
            appWidgetManager.updateAppWidget(appWidgetId, views1);
        }

        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new MyTimerTask(context, appWidgetManager), 5, 1000);

    }

    private class MyTimerTask extends TimerTask {

        RemoteViews MyRemoteViews;
        AppWidgetManager MyAppWidgetProvider;
        ComponentName MyWidgetComponent;

        public MyTimerTask(Context context, AppWidgetManager appWidgetProvider){
            this.MyAppWidgetProvider = appWidgetProvider;
            MyRemoteViews = new RemoteViews(context.getPackageName(), R.layout.interface_widget_layout);
            MyWidgetComponent = new ComponentName(context, MyWidget.class);
        }

        @Override
        public void run() {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

            String timeStamp = sdf.format(date);
            this.MyRemoteViews.setTextViewText(R.id.tvTextWidget, timeStamp);
            this.MyAppWidgetProvider.updateAppWidget(MyWidgetComponent, MyRemoteViews);
        }
    }
}
