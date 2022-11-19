package com.thdlopes.regas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thdlopes.regas.databinding.FragmentMoistureBinding;
import com.thdlopes.regas.databinding.FragmentWaterLevelBinding;

public class WaterLevelFragment extends Fragment {
    private FragmentWaterLevelBinding binding;
    private TextView waterLevel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference waterLevelReference = database.getReference("waterLevelPercentage");
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WaterLevelFragment() {
        // Required empty public constructor
    }

    public static WaterLevelFragment newInstance(String param1, String param2) {
        WaterLevelFragment fragment = new WaterLevelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWaterLevelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        waterLevel = view.findViewById(R.id.waterLevelPercentage);
        waterLevelReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String waterLevelPercentage = dataSnapshot.getValue().toString();
                waterLevelPercentage = waterLevelPercentage + "%";
                waterLevel.setText(waterLevelPercentage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}