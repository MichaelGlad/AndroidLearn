package net.glm.locationproviderservicetest;

import android.*;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class LocationProviderService extends Service implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public final static String BROADCAST_ACTION = "receiveLocationFromService";
    static final String LATITUDE = "Latitude";
    static final String LONGITUDE = "Longitude";
    static final String MY_LOG = "MyLog";

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    //private FusedLocationProviderApi locationProviderApi = LocationServices.FusedLocationApi;

    private Double serviceLatitude, serviceLongitude;

    int serviceCount = 0;

    public LocationProviderService() {
    }

    @Override
    public void onCreate() {

        super.onCreate();
        initLocationRequest();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        googleApiClient.connect();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        googleApiClient.disconnect();
    }


    private void initLocationRequest() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = new LocationRequest()
                .setInterval(2 * 1000)
                .setFastestInterval(500)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void requestLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }


    @Override
    public void onLocationChanged(Location location) {


        serviceLatitude = location.getLatitude();
        serviceLongitude = location.getLongitude();


        Log.d(MY_LOG, " The Latitude: " + serviceLatitude + " and Longitude: " + serviceLongitude + " serviceCount is - " + serviceCount++);

        Intent serviceIntent = new Intent(BROADCAST_ACTION);
        serviceIntent.putExtra(LATITUDE, serviceLatitude)
                .putExtra(LONGITUDE, serviceLongitude);
        sendBroadcast(serviceIntent);

    }
}
