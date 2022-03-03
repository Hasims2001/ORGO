package com.example.adminportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class admindashboard extends AppCompatActivity {
    String username;
    CardView admin_details, ins_details,organ_details,user_details, donor_details, recipient_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        SharedPreferences sh = getSharedPreferences("AdminName", MODE_PRIVATE);
        username = sh.getString("user_name", "");

        admin_details = findViewById(R.id.admin_details);
        ins_details = findViewById(R.id.ins_details);
        organ_details = findViewById(R.id.organ_details);
        user_details = findViewById(R.id.user_details);
        donor_details = findViewById(R.id.donor_details);
        recipient_details = findViewById(R.id.admin_table);

        admin_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), admin_data.class)));

        organ_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), organ_data.class)));

        user_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), user_data.class)));

        donor_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), donor_info.class)));

        recipient_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), recipient_info.class)));

        ins_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), institute.class)));

    }
}