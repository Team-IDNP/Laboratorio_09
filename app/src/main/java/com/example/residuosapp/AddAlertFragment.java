package com.example.residuosapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class AddAlertFragment extends Fragment {

    GoogleMap mapAdd;
    LatLng positionAlert;

    public AddAlertFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addalert, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapViewAddAlert);

        mapFragment.getMapAsync(googleMap -> {
            googleMap.setOnMapClickListener(latLng -> {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("(" + latLng.latitude + ", " + latLng.longitude + ")");
                googleMap.clear();
                googleMap.addMarker(markerOptions);
                positionAlert = latLng;
            });
            mapAdd = googleMap;
        });

        view.findViewById(R.id.btn_report).setOnClickListener(this::registrar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void registrar(View v) {
        Bundle args = new Bundle();
        args.putDouble("Latitud", positionAlert.latitude);
        args.putDouble("Longitud", positionAlert.longitude);

        Fragment fragment = new HomeFragment();
        fragment.setArguments(args);

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment, "HOME_FRAGMENT")
                .addToBackStack("HOME_FRAGMENT")
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView toolbarT = getActivity().findViewById(R.id.toolbar_title);
        toolbarT.setText("Nueva alerta");
    }

}