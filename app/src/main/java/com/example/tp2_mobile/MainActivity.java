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
    private Button goToExercice3Button;

    private Button goToExercice4Button;
    private Button goToExercice5Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        goToExercice1Button = findViewById(R.id.exercice1Button);
        goToExercice1Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice1Activity.class);
            startActivity(intent);
        });

        goToExercice2Button = findViewById(R.id.exercice2Button);
        goToExercice2Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice2Activity.class);
            startActivity(intent);
        });

        goToExercice3Button = findViewById(R.id.exercice3Button);
        goToExercice3Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice3Activity.class);
            startActivity(intent);
        });

        goToExercice4Button = findViewById(R.id.exercice4Button);
        goToExercice4Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice4Activity.class);
            startActivity(intent);
        });

        goToExercice5Button = findViewById(R.id.exercice5);
        goToExercice5Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Exercice5Activity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}