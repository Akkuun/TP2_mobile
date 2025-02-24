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

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            // Get all available sensors from the device
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            // Get all available sensors from the device
            List<Sensor> availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            Set<String> availableSensorTypes = new HashSet<>();
            for (Sensor sensor : availableSensors) {
                availableSensorTypes.add(sensor.getStringType());
            }

            StringBuilder sb = new StringBuilder();
            //get all possible sensor type from the Sensor class
            Field[] allPossibleSensorType = Sensor.class.getFields();
            //we loop to find the sensor type that are not available on the device
            for (Field typeSensor : allPossibleSensorType) {
                //if the field name start with STRING_TYPE_ it's a sensor type
                if (typeSensor.getName().startsWith("STRING_TYPE_")) {
                    try {
                        // Get the sensor type
                        String sensorType = (String) typeSensor.get(null);
                        // If the sensor type is not available on the device we put it in the string builder to display it
                        if (!availableSensorTypes.contains(sensorType)) {
                            sb.append(sensorType).append("\n");
                        }
                    }
                    // if the field is not accessible
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            textView.setText(sb.toString());
        });
    }
}