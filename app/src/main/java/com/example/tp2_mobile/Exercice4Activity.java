package com.example.tp2_mobile;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Debug;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Exercice4Activity extends AppCompatActivity implements SensorEventListener {


    private TextView direction;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView accelerometerValue;
    private SeekBar seekBar;

    private int sensitivity;

    private long lastUpdate = 0;
    private static final int UPDATE_INTERVAL = 700; // milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice4);

        direction = findViewById(R.id.direction);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100); // Set a maximum value for sensitivity
        seekBar.setProgress(50);



        accelerometerValue = findViewById(R.id.acceVal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize SensorManager and get the accelerometer sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION).get(0);

        // Register the sensor listener for accelerometer
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        if (curTime - lastUpdate < UPDATE_INTERVAL) {
            return; // ne pas mettre à jour trop souvent
        }
        lastUpdate = curTime;

        // ONLY for accelerometer sensor
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {




            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            // Calculate the magnitude of the acceleration
            float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
            accelerometerValue.setText(String.format("X: %.2f, Y: %.2f, Z: %.2f", x, y, z));

            int sensitivity = seekBar.getProgress();



            // Déterminer la direction dominante en comparant les valeurs absolues
            if (Math.abs(x) > Math.abs(y) && Math.abs(x) > Math.abs(z)) {
                // Axe X dominant
                if (x > sensitivity) {
                    direction.setText("Right");
                } else if (x < -sensitivity) {
                    direction.setText("Left");
                }
            } else if (Math.abs(y) > Math.abs(x) && Math.abs(y) > Math.abs(z)) {
                // Axe Y dominant
                if (y > sensitivity) {
                    direction.setText("Front");
                } else if (y < -sensitivity) {
                    direction.setText("Back");
                }
            } else {
                // Axe Z dominant
                if (z > sensitivity) {
                    direction.setText("Up");
                } else if (z < -sensitivity) {
                    direction.setText("Down");
                }
            }





        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //change sensibility of the sensor




    }

}
