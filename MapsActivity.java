package com.comsol.fleamarket_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Current Location OF Your GPS Location
        //Set the Current Location
        GPSTracker gpsTracker = new GPSTracker(this);
        double lat = gpsTracker.getLocation().getLatitude();
        double lng = gpsTracker.getLocation().getLongitude();


        //Set the GPS Button and Find you form the GPS Service
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        //Set the GPS LATLNG
        LatLng myGPSLocation = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(myGPSLocation).title("GPS Location").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gps_locator)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myGPSLocation));



        //Set The DEfualt LATLNG
        LatLng location1 = new LatLng(24.975719, 67.066402);
        mMap.addMarker(new MarkerOptions().position(location1).title("Noth Karachi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location1));
        LatLng location2 = new LatLng(24.985920, 67.065618);
        mMap.addMarker(new MarkerOptions().position(location2).title("Power House Chorwangi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location2));
        LatLng location3 = new LatLng(24.995712, 67.064942);
        mMap.addMarker(new MarkerOptions().position(location3).title("Road # 23"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location3));
        LatLng location4 = new LatLng(24.996043, 67.065801);
        mMap.addMarker(new MarkerOptions().position(location4).title("Omega Mall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location4));


        //Draw A Polyling
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(myGPSLocation,location1,location2,location3,location4)
                .width(5)
                .color(Color.RED));
    }
}