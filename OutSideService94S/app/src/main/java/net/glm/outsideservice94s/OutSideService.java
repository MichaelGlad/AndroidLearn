package net.glm.outsideservice94s;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class OutSideService extends Service {
    final String LOG_TAG = "myLogs";

    public OutSideService() {


    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"My Outside Service onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"My Outside Service onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"My Outside Service onStartCommand");
        readFlags(flags);

        MyRun myRun = new MyRun(startId);
        new Thread(myRun).start();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    void readFlags (int flags){
        if (flags == START_FLAG_REDELIVERY)
            Log.d(LOG_TAG," The Flag is START_FLAG_REDELIVERY ");
        if (flags == START_FLAG_RETRY)
            Log.d(LOG_TAG," The Flag is START_FLAG_RETRY ");
    }

    class MyRun implements Runnable{
        int startId;

        public MyRun(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            Log.d(LOG_TAG,"My OutSide Run #" + startId + " start");


            long endTime = System.currentTimeMillis() + 10*1000;

            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }



            Log.d(LOG_TAG,"My OutSide Run #" + startId + " end, stopSelfResult - " + stopSelfResult(startId));

        }
    }
}
