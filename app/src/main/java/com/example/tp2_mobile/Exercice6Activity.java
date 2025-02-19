package com.example.tp2_mobile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Exercice6Activity extends AppCompatActivity {


    private ImageView imageView;


    private TextView detectionTextView;

    private SensorManager sensorManager;
    private Sensor proximitySensor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice6);

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.cat);

        detectionTextView = findViewById(R.id.detection);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        if (proximitySensor != null) {
            SensorEventListener proximitySensorListener = createProximitySensorListener();
            sensorManager.registerListener(proximitySensorListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private SensorEventListener createProximitySensorListener() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] < proximitySensor.getMaximumRange()) {
                    // Detected something nearby
                    imageView.setScaleX(0.3f);
                    imageView.setScaleY(0.3f);
                } else {
                    // Nothing is nearby
                    imageView.setScaleX(1f);
                    imageView.setScaleY(1f);
                }
                detectionTextView.setText("Proximity: " + event.values[0]);
                Log.d("ProximitySensor", "Proximity: " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // No need to implement this method
            }
        };
    }

}