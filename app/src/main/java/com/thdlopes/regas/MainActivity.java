package com.thdlopes.regas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView moisture, waterLevel;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference moistureReference = database.getReference("moisturePercentage");
    DatabaseReference waterLevelReference = database.getReference("waterLevelPercentage");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moisture = findViewById(R.id.moisturePercentage);
        waterLevel = findViewById(R.id.waterLevelPercentage);
        moistureReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String moisturePercentage = dataSnapshot.getValue().toString();
                moisturePercentage = moisturePercentage + "%";
                moisture.setText(moisturePercentage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

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
}