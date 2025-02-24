package com.example.tp2_mobile;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2_mobile.R;

public class Exercice8Activity_detail extends AppCompatActivity {

    private TextView countryDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice8_details);

        countryDetailTextView = findViewById(R.id.countryDetailTextView);

        // Récupérer le nom du pays passé dans l'Intent
        String countryName = getIntent().getStringExtra("country_name");

        // Afficher les détails du pays dans la TextView
        countryDetailTextView.setText("Détails du pays : " + countryName);
    }
}
