package net.glm.service93s;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InsideService extends Service {

    public static final String TIME = "time";
    final String LOG_TAG = "myLogs";
    ExecutorService executorService;
    Object someObject;

    public InsideService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "InsideService onCreate");
        executorService = Executors.newFixedThreadPool(3);
        someObject = new Object();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "InsideService onStartCommand with id - " + startId);
        int time = intent.getIntExtra(TIME, 3);
        MyRun myRun = new MyRun(time,startId);
        executorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "InsideService on Destroy");
        someObject = null;
    }


    class MyRun implements Runnable {

        int time;
        int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun #" + startId + " constructor");

        }

        @Override
        public void run() {
            Log.d(LOG_TAG, " MyRun #" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG_TAG, " MyRun #" + startId + " Object " + someObject.getClass());
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, " MyRun #" + startId + " ERROR NULL Pointer");
            }

            Log.d(LOG_TAG, " MyRun #" + startId + " stopSelf");
            stopSelf(startId);

        }
    }
}
