package com.example.orgo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class law_trans extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button yesbtn, nobtn;
    LinearLayout option;
    String txt_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_trans);

        drawerLayout = findViewById(R.id.law_act);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);

        yesbtn = findViewById(R.id.yesbtn);
        nobtn = findViewById(R.id.nobtn);
        option = findViewById(R.id.optionch);

//        txt_name = getIntent().getStringExtra("username");
        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(law_trans.this, orgn_trans.class);
//                intent.putExtra("username", txt_name);
                startActivity(intent);
            }
        });
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(law_trans.this, "Thank you", Toast.LENGTH_SHORT).show();
                option.setVisibility(View.INVISIBLE);
            }
        });
    }
}