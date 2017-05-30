package net.glm.bindservice98s;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";

    MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask timerTask;
    long interval = 1000;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"MyService onCreate");
        timer = new Timer();
        myShedule();
    }

    private void myShedule() {
        if (timerTask !=null) {
            timerTask.cancel();
        }

        if(interval > 0 ){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(LOG_TAG,"Run in timer with interval == " + interval );
                }
            };
            timer.schedule(timerTask,1000,interval);

        }else Log.d(LOG_TAG,"In myShedule and interval == or < 0 ");
    }

    long upInterval (long gap){
        interval = interval + gap;
        myShedule();
        return  interval;
    }

    long downInterval (long gap){
        interval = interval - gap;
        if (interval < 0){
            interval = 0;
        }
        myShedule();
        return  interval;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onBind");
        return binder;
    }

    class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
}
