package com.example.tp2_mobile;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Exercice2Activity extends AppCompatActivity {

    private Button searchDeviceButton;


    private ScrollView scrollView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice2);

        searchDeviceButton = findViewById(R.id.button2);
        scrollView = findViewById(R.id.viewDevice);
        textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchDeviceButton.setOnClickListener(v -> {
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            List<Sensor> availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            String[] allPossibleSensors = {
                    Sensor.STRING_TYPE_ACCELEROMETER,
                    Sensor.STRING_TYPE_AMBIENT_TEMPERATURE,
                    Sensor.STRING_TYPE_GRAVITY,
                    Sensor.STRING_TYPE_GYROSCOPE,
                    Sensor.STRING_TYPE_LIGHT,
                    Sensor.STRING_TYPE_LINEAR_ACCELERATION,
                    Sensor.STRING_TYPE_MAGNETIC_FIELD,
                    Sensor.STRING_TYPE_PRESSURE,
                    Sensor.STRING_TYPE_PROXIMITY,
                    Sensor.STRING_TYPE_RELATIVE_HUMIDITY,
                    Sensor.STRING_TYPE_ROTATION_VECTOR,
                    Sensor.STRING_TYPE_TEMPERATURE
            };

            StringBuilder sb = new StringBuilder();
            for (String sensorType : allPossibleSensors) {
                boolean isAvailable = false;
                for (Sensor sensor : availableSensors) {
                    if (sensor.getStringType().equals(sensorType)) {
                        isAvailable = true;
                        break;
                    }
                }
                if (!isAvailable) {
                    sb.append(sensorType).append("\n");
                }
            }
            textView.setText(sb.toString());
        });
    }
}