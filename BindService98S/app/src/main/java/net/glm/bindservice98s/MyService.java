package net.glm.bindservice98s;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
    private final int MIN = 0;
    private final int MAX = 100;

    private  int randomNumber;
    private  boolean isRandomNumberGenerationON;



    MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask timerTask;
    private  int interval = 1000;

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
                    randomNumber = new Random().nextInt(MAX)+MIN;
                    Log.d(LOG_TAG,"Run in timer with interval == " + interval + " ,Random Number - " + randomNumber +
                            " ,Run in Thread - " + Thread.currentThread().getId() + " with Name - " + Thread.currentThread().getName() );
                }
            };
            timer.schedule(timerTask,500,interval);

        }else Log.d(LOG_TAG,"In myShedule and interval ==  0 ");
    }

    long upInterval (int gap){
        interval = interval + gap;
        myShedule();
        return  interval;
    }

    long downInterval (int gap){
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

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "MyService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "MyService onDestroyed");

        if (timerTask !=null) {
            timerTask.cancel();
        }
        super.onDestroy();
    }

    public int getRandomNumber(){
        return randomNumber;
    }
    public int getInterval(){
        return interval;
    }

    class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
}
