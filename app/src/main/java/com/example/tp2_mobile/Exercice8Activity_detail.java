package com.example.tp2_mobile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2_mobile.R;

public class Exercice8Activity_detail extends AppCompatActivity {

    private TextView countryNameTextView;
    private TextView capitalTextView;
    private TextView populationTextView;
    private ImageView flagImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice8_details);

        countryNameTextView = findViewById(R.id.countryDetailTextView);
        capitalTextView = findViewById(R.id.capitalTextView);
        populationTextView = findViewById(R.id.populationTextView);
        flagImageView = findViewById(R.id.flagImageView);

        // Récupérer les informations passées via l'Intent
        String countryName = getIntent().getStringExtra("country_name");
        String countryFlag = getIntent().getStringExtra("country_flag");
        String countryCapital = getIntent().getStringExtra("country_capital");
        int countryPopulation = getIntent().getIntExtra("country_population", 0);

        // Afficher les informations dans les TextViews et l'ImageView
        countryNameTextView.setText("Nom du pays: " + countryName);
        capitalTextView.setText("Capitale: " + countryCapital);
        populationTextView.setText("Population: " + countryPopulation);

        // Charger le drapeau (en supposant que tu aies une méthode pour charger l'image)
        int flagResourceId = getResources().getIdentifier(countryFlag, "drawable", getPackageName());
        flagImageView.setImageResource(flagResourceId);
    }
}
