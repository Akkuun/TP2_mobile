package com.example.tp2_mobile;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.content.Context;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Exercice5Activity extends AppCompatActivity implements SensorEventListener {

    private Button searchDeviceButton;
    private ScrollView scrollView;
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView accelerometerValue;
    private SeekBar seekBar;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private long lastFlashTime = 0;
    private static final long FLASH_DELAY = 1000; // 1 seconds delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice5);

        scrollView = findViewById(R.id.viewDevice);
        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(10); // Set a maximum value for sensitivity
        seekBar.setProgress(10);

        accelerometerValue = findViewById(R.id.acceVal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize SensorManager and get the accelerometer sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();

        for (Sensor currentSensor : sensorList) {
            // If the sensor is an accelerometer
            if (currentSensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometer = currentSensor;
                sensorText.append("New sensor detected: \n");
                sensorText.append("Name: ").append(currentSensor.getName()).append("\n");
                sensorText.append("Type: ").append(currentSensor.getType()).append("\n");
                sensorText.append("Vendor: ").append(currentSensor.getVendor()).append("\n");
                sensorText.append("Version: ").append(currentSensor.getVersion()).append("\n");
                sensorText.append("Power: ").append(currentSensor.getPower()).append("\n");
                sensorText.append("Resolution: ").append(currentSensor.getResolution()).append("\n");
                sensorText.append("Maximum Range: ").append(currentSensor.getMaximumRange()).append("\n");
                sensorText.append("\n");
                textView.setText(sensorText.toString());
            }
        }

        // Register the sensor listener for accelerometer
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }

        // Initialize CameraManager
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // ONLY for accelerometer sensor
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            // Calculate the magnitude of the acceleration
            float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
            accelerometerValue.setText(String.format("X: %.2f, Y: %.2f, Z: %.2f", x, y, z));

            int sensitivity = seekBar.getProgress();
            long currentTime = System.currentTimeMillis();
            //if we are 2 times superior than the sensivity and the last flash is superior than 2 seconds
            if (magnitude > 2*sensitivity && (currentTime - lastFlashTime) > FLASH_DELAY) {
                toggleFlashlight();
                lastFlashTime = currentTime;
            }
        }
    }

    private void toggleFlashlight() {
        try {
            if (isFlashOn) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashOn = false;
            } else {
                cameraManager.setTorchMode(cameraId, true);
                isFlashOn = true;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle sensor accuracy changes if necessary
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener to conserve battery
        sensorManager.unregisterListener(this);
    }
}