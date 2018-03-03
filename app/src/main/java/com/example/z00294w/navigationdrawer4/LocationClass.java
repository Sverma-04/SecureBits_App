package com.example.z00294w.navigationdrawer4;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class LocationClass extends AppCompatActivity implements LocationListener {

    Button getLocationBtn;
    TextView locationText;
    String countryCode, countryName;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_location);

       getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        locationText = (TextView)findViewById(R.id.locationText);



        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

       locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2);
            locationText.setText(address);
            Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
            countryCode = addresses.get(0).getCountryCode();
            countryName = addresses.get(0).getCountryName();
            Toast.makeText(getApplicationContext(), countryCode, Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(getBaseContext(), newsAPIView.class);
//            intent.putExtra("COUNTRY_CODE", countryCode);
//            startActivity(intent);
        }catch(Exception e)
        {

        }


    }

//    public void sendToTMsource(View v) {
//
//
//        Intent intent_TMnewsSource = new Intent(LocationClass.this, TM_source.class);
//        startActivity(intent_TMnewsSource);
//
//    }
//    public void sendToNewsAPIView(View v) {
//        //Intent intent_newsList = new Intent(LocationClass.this, newsAPIView.class);
//        Intent intent = new Intent(getBaseContext(), newsAPIView.class);
//       intent.putExtra("COUNTRY_CODE", countryCode);
//       startActivity(intent);

       // startActivity(intent_newsList);

    //}

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(LocationClass.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}
