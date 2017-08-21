package net.glm.loader135s;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 13/08/2017.
 */

public class TimeLoader extends Loader<String> {
    final String LOG_TAG = "myLogs";
    final int PAUSE = 5;

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:aa a";
    public final static String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";

    GetTimeTask getTimeTask;
    String format=null;

    public TimeLoader(Context context, Bundle args) {
        super(context);
        Log.d(LOG_TAG,hashCode() + "  -create TimeLoader");
        if (args!=null){
            format = args.getString(ARGS_TIME_FORMAT);
        }
        if (format == null || TextUtils.isEmpty(format)){
            format = TIME_FORMAT_SHORT;

        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG,hashCode() + "  -onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(LOG_TAG,hashCode() + "  -onStopLoading");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(LOG_TAG,hashCode() + "  -onForceLoading");
        if (getTimeTask != null){
            getTimeTask.cancel(true);
        }
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,format);
    }

    @Override
    protected void onAbandon() {  //Loader start to be not Active
        super.onAbandon();
        Log.d(LOG_TAG,hashCode() + "  -onAbandon");
    }

    @Override
    protected void onReset() {  // Destroy Loadre Call when call onDestroy in Activity or fragment
        //Not called when change orientation
        super.onReset();
        Log.d(LOG_TAG,hashCode() + "  -onReset");
    }

    void getResultFromTask (String result){
        deliverResult(result);
    }

    class GetTimeTask extends AsyncTask <String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_TAG,TimeLoader.this.hashCode() + "  -doInBackground");
            try{
                TimeUnit.SECONDS.sleep(PAUSE);
            }catch (InterruptedException e) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(params[0], Locale.getDefault());
            return sdf.format(new Date());
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_TAG,TimeLoader.this.hashCode() + "  -onPostExecute " + result);
            getResultFromTask(result);
        }
    }
}
