package com.example.residuosapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class HomeFragment extends Fragment {
    private GoogleMap map;

    public HomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle args = getArguments();
        double latitud = 0;
        double longitud = 0;

        try {
            latitud = args.getDouble("Latitud");
            longitud = args.getDouble("Longitud");
        } catch (NullPointerException ignored){

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapViewAddAlert);

        double finalLatitud = latitud;
        double finalLongitud = longitud;
        mapFragment.getMapAsync(googleMap -> {
            map = googleMap;

            if (finalLatitud != 0.0 && finalLongitud != 0.0){
                MarkerOptions options = new MarkerOptions();
                options.position(new LatLng(finalLatitud, finalLongitud));
                googleMap.addMarker(options);
            }
        });

        return v;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

}