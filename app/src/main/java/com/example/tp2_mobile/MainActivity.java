package com.example.tp2_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button goToExercice1Button;
    private Button goToExercice2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        goToExercice1Button = findViewById(R.id.Exercice1Button);
        goToExercice1Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice1Activity.class);
            startActivity(intent);
        });

        goToExercice2Button = findViewById(R.id.exercice2Button);
        goToExercice2Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice2Activity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}