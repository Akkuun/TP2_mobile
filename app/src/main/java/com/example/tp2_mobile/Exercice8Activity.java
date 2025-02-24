package com.example.tp2_mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Exercice8Activity extends AppCompatActivity implements CountriesListFragment.OnCountrySelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice8);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                // add the fragment to the 'fragment_container' FrameLayout
                CountriesListFragment countriesListFragment = new CountriesListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, countriesListFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onCountrySelected(Country country) {
        // print the country details  when a country is selected
        CountriesDetailFragment detailsFragment = CountriesDetailFragment.newInstance(country);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
