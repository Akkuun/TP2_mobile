package com.example.tp2_mobile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountriesListFragment extends Fragment {

    private RecyclerView recyclerView;
    private OnCountrySelectedListener listener;

    public interface OnCountrySelectedListener {
        void onCountrySelected(Country country);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("France", "flag_france", "Paris", 67000000));
        countries.add(new Country("Spain", "flag_spain", "Madrid", 47000000));
        countries.add(new Country("Italy", "flag_italy", "Rome", 60000000));
        countries.add(new Country("Germany", "flag_germany", "Berlin", 83000000));
        countries.add(new Country("USA", "flag_usa", "Washington", 328000000));

        CountryAdapter adapter = new CountryAdapter(countries, country -> {
            if (listener != null) {
                listener.onCountrySelected(country);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCountrySelectedListener) {
            listener = (OnCountrySelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnCountrySelectedListener");
        }
    }
}
