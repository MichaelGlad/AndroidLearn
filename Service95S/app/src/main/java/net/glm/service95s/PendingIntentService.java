package net.glm.service95s;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PendingIntentService extends Service {
    final String LOG_TAG = "myLogs";
    ExecutorService executorService;

    public PendingIntentService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, " PendingIntent Service onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, " PendingIntent Service onStartComand with startId - " + startId);

        int time = intent.getIntExtra(MainActivity.PARAM_TIME,0);
        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PARAM_PENDING_INTENT);
        MyRun runInPendingIntentService = new MyRun(time,startId,pendingIntent);
        executorService.execute(runInPendingIntentService);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, " PendingIntent Service onDestroy");
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        PendingIntent pendingIntent;

        public MyRun(int time, int startId, PendingIntent pendingIntent) {
            this.time = time;
            this.startId = startId;
            this.pendingIntent = pendingIntent;

            Log.d(LOG_TAG,"MyRun #" + startId + " create");
        }


        @Override
        public void run() {

            Log.d(LOG_TAG,"MyRun #"+ startId+ " start, time = " + time);
            try {
                pendingIntent.send(MainActivity.STATUS_START);

                TimeUnit.SECONDS.sleep(time);

                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT,time*100);
                pendingIntent.send(PendingIntentService.this,MainActivity.STATUS_FINISH,intent);

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(LOG_TAG," MyRun #" + startId + " finish, stopSelfResult = " + stopSelfResult(startId));

        }
    }
}
