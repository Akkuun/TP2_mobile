package com.example.tp2_mobile;

import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;


public class Exercice7Activity extends AppCompatActivity {

    private Button refreshPosition;


    private TextView positionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice7);

        refreshPosition = findViewById(R.id.button2);
        positionTextView = findViewById(R.id.detection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the user's position when the page loads
        getUserPosition();

        // When we click on our button, we refresh the position
        refreshPosition.setOnClickListener(v -> {
            getUserPosition();
        });

    }
    private void getUserPosition() {
        // Use the location service of the phone
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //if we don't have the permission, we ask for it
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }
        // listen to the location changes
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                positionTextView.setText("Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //no need to implement this method
            }

            @Override
            public void onProviderEnabled(String provider) {
                //no need to implement this method
            }

            @Override
            public void onProviderDisabled(String provider) {
                //no need to implement this method
            }
        };

        //we laucnh the location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);


    }
}