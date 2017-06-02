package net.glm.servicehomework;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    static final String LOG_TAG = "myLogs";
    private int counter = 0;
    private Thread serviceThread;
    private  boolean go = true;
    private IBinder binder = new MyBinder();
    private ServiceListener serviceListener;
    MainActivity mainActivity;


    public MyService() {
    }

    public void setServiceListener (ServiceListener serviceListener){
        this.serviceListener = serviceListener;
    }

    public void setMainActivity (MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"MyService in onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG,"MyService in onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG,"MyService in onUnbinde");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(LOG_TAG,"MyService in onRebinde");
        super.onRebind(intent);
    }

    public void showNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    }

    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    public interface ServiceListener{
        void goGo();
    }
}
