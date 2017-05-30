package net.glm.service95s;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BroadcastReceiverService extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService executorService;

    public BroadcastReceiverService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, " Broadcast Service onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, " Broadcast Service onStartComand with startId - " + startId);

        int time = intent.getIntExtra(MainActivity.PARAM_TIME,0);
        int task = intent.getIntExtra(MainActivity.PARAM_TASK,0);


        MyRun runInBroadcastReceiverService = new MyRun(time,startId,task);
        executorService.execute(runInBroadcastReceiverService);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, " Broadcast Service onDestroy");
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        int task;


        public MyRun(int time, int startId, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;

            Log.d(LOG_TAG,"MyRun #" + startId + " create");
        }


        @Override
        public void run() {
            Intent insideRunIntent = new Intent(MainActivity.BROADCAST_ACTION);

            Log.d(LOG_TAG,"MyRun #"+ startId+ " start, time = " + time);
            try {
                insideRunIntent.putExtra(MainActivity.PARAM_STATUS,MainActivity.STATUS_START)
                        .putExtra(MainActivity.PARAM_TASK,task);
                sendBroadcast(insideRunIntent);

                TimeUnit.SECONDS.sleep(time);

                insideRunIntent.putExtra(MainActivity.PARAM_STATUS,MainActivity.STATUS_FINISH)
                        .putExtra(MainActivity.PARAM_RESULT,time*1000);

                sendBroadcast(insideRunIntent);



            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(LOG_TAG," MyRun #" + startId + " finish, stopSelfResult = " + stopSelfResult(startId));

        }
    }
}
