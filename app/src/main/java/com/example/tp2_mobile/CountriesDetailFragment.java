package com.example.tp2_mobile;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class CountriesDetailFragment extends Fragment {

    private TextView countryNameTextView;
    private TextView capitalTextView;
    private TextView populationTextView;
    private ImageView flagImageView;

    //create a new instance of the fragment with the country details by passing the country object as an argument
    public static CountriesDetailFragment newInstance(Country country) {
        CountriesDetailFragment fragment = new CountriesDetailFragment();
        Bundle args = new Bundle();
        args.putString("country_name", country.getName());
        args.putString("country_flag", country.getFlag());
        args.putString("country_capital", country.getCapital());
        args.putInt("country_population", country.getPopulation());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment -> that's mean to load the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_countries_detail, container, false);
        //get the views from the layout
        countryNameTextView = view.findViewById(R.id.countryDetailTextView);
        capitalTextView = view.findViewById(R.id.capitalTextView);
        populationTextView = view.findViewById(R.id.populationTextView);
        flagImageView = view.findViewById(R.id.flagImageView);
        //get the arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            String countryName = args.getString("country_name");
            String countryFlag = args.getString("country_flag");
            String countryCapital = args.getString("country_capital");
            int countryPopulation = args.getInt("country_population");
            //set the values to the views
            countryNameTextView.setText("Contry name: " + countryName);
            capitalTextView.setText("Capitale: " + countryCapital);
            populationTextView.setText("Population: " + countryPopulation);

            //contryflag is a string like flag_usa
            // we need to loead the image from the drawable folder and use the valeu given by the string countryFlag
            //Log.d("FlagLoading", "Country flag string received: " + countryFlag);
            //passing the string countryFlag to the getIdentifier method to get the resource ID
            int flagResourceId = getResources().getIdentifier(countryFlag, "drawable", requireActivity().getPackageName());
            //Log.d("FlagLoading", "Resource ID for " + countryFlag + " = " + flagResourceId);
            flagImageView.setImageResource(flagResourceId);

        }

        return view;
    }
}
