package com.example.tp2_mobile;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exercice4Activity extends AppCompatActivity implements SensorEventListener {

    private TextView direction;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView accelerometerValue;
    private SeekBar seekBar;

    private int sensitivity;
    private long lastUpdate = 0;
    private static final int UPDATE_INTERVAL = 700; // millisecondes
    private static final float THRESHOLD = 0.5f; // Seuil minimum pour éviter les fausses détections

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice4);

        direction = findViewById(R.id.direction);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        accelerometerValue = findViewById(R.id.acceVal);
        direction.setText("Move the phone to detect the direction");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }

        sensitivity = seekBar.getProgress(); // Initialisation correcte

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sensitivity = progress; // change sensitivity
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    //detect the direction of the phone
    @Override
    public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        if (curTime - lastUpdate < UPDATE_INTERVAL) {
            return;
        }
        lastUpdate = curTime;

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelerometerValue.setText(String.format("X: %.2f, Y: %.2f, Z: %.2f", x, y, z));

            float adjustedSensitivity = sensitivity / 100.0f; // Normalisation de la sensibilité

            // compute dominant axe
            if (Math.abs(x) > Math.abs(y) && Math.abs(x) > Math.abs(z)) {
                if (Math.abs(x) > adjustedSensitivity) {
                    direction.setText(x > 0 ? "Right" : "Left");
                }
            } else {
                if (Math.abs(z) > adjustedSensitivity) {
                    direction.setText(z > 0 ? "Up" : "Down");
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // no need
    }
}
