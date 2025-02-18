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

public class Exercice1Activity extends AppCompatActivity {

    private Button searchDeviceButton;
    private ScrollView scrollView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice1);

        searchDeviceButton = findViewById(R.id.button2);
        scrollView = findViewById(R.id.viewDevice);
        textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // When we click on our button, we display in the scroll view all the sensors available on the phone
        searchDeviceButton.setOnClickListener(v -> {
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

            StringBuilder sensorInfo = new StringBuilder();
            for (Sensor sensor : deviceSensors) {
                sensorInfo.append(sensor.getName()).append("\n");
            }
            if (textView != null) {
                textView.setText(sensorInfo.toString());
            }
        });
    }
}