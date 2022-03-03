package com.example.orgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_work extends AppCompatActivity {

    CardView ins_details,organ_details,user_details, donor_details, recipient_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_work);

        ins_details = findViewById(R.id.ins_details);
        organ_details = findViewById(R.id.organ_details);
        user_details = findViewById(R.id.user_details);
        donor_details = findViewById(R.id.donor_details);
        recipient_details = findViewById(R.id.recipient_details);


        organ_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), organ_data.class)));

        user_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), user_data.class)));

        donor_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), donor_info.class)));

        recipient_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), recipient_info.class)));

        ins_details.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), institute.class)));
    }
}