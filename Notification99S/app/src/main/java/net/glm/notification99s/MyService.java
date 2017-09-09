package net.glm.notification99s;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    NotificationManager nManager;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification(){

        //1 Stage
        Notification.Builder nBuilder = new Notification.Builder(this);
        nBuilder.setSmallIcon(R.drawable.ic_stat_name);
        nBuilder.setTicker("The small Dog");
        nBuilder.setWhen(System.currentTimeMillis());

        //3 Stage
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(MainActivity.FILE_NAME,"Some File Downloaded");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        //2 Stage
        nBuilder.setContentTitle("This is My title");
        nBuilder.setContentText("here will be Text");
        nBuilder.setContentIntent(pendingIntent);
        nBuilder.setAutoCancel(true);
        Notification notification = nBuilder.build();
        nManager.notify(1,notification);


    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}
