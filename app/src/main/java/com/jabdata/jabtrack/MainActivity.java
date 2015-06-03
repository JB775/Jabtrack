package com.jabdata.jabtrack;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends ActionBarActivity implements GooglePlayServicesClient.ConnectionCallbacks,GooglePlayServicesClient.OnConnectionFailedListener,LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    //Map Variables
    private GoogleMap map;
    private LatLng currentLocation;
    private final LatLng LOCATION_TEST = new LatLng(40.285311,-75.402775);

    //Add GPSTracker Class??
    //GPSTracker gps;

    //GPS Variables
    private GoogleApiClient locationclient;
    private LocationRequest locationrequest;
    private double lat;
    private double long1;

    //Vehicle Status Variables
    private float mph;

    //Progress Dialog
    //Add Progress Dialog Variables Here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_TEST, 16);
//        map.animateCamera(update);
        Toast.makeText(
                getApplicationContext(),
                "Your Location is -\nLat: " + lat + "\nLong: "
                + long1, Toast.LENGTH_LONG
        ).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            long1 = location.getLongitude();
            mph = location.getSpeed();
         }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(Bundle bundle) {

        if(locationclient!=null && locationclient.isConnected()){
            locationrequest = LocationRequest.create();
            //location update frequency
            locationrequest.setInterval(600*1000);
            LocationServices.FusedLocationApi.requestLocationUpdates(locationclient, locationrequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onDisconnected() {

        if (locationclient != null)
            locationclient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
