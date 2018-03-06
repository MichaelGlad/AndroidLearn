package net.glm.geofencingtest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Michael on 02/03/2018.
 */

public class GeofenceTransitionsIntentService extends IntentService {
   private static final String TAG = "gfService";

   public GeofenceTransitionsIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
