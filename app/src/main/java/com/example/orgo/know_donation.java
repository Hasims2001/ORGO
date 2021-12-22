package com.example.orgo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class know_donation extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    CardView firstbox, secondbox;
    ImageView img_one, one_img;
    TextView first_ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_donation);

        drawerLayout = findViewById(R.id.knowpage);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        firstbox = findViewById(R.id.onebox);
        img_one = findViewById(R.id.imgone);
        one_img = findViewById(R.id.oneimg);
        first_ans = findViewById(R.id.firstanswer);

        firstbox.setOnClickListener(v -> {
            if(img_one.getVisibility()==View.VISIBLE){
            img_one.setVisibility(View.INVISIBLE);
            one_img.setVisibility(View.VISIBLE);
            first_ans.setVisibility(View.VISIBLE);
            firstbox.setMinimumHeight(1000);
            first_ans.setMinimumHeight(1000);

            }
           else if(one_img.getVisibility()==View.VISIBLE){
                one_img.setVisibility(View.INVISIBLE);
                first_ans.setVisibility(View.INVISIBLE);
                img_one.setVisibility(View.VISIBLE);
//                firstbox.setMinimumHeight();
//                first_ans.setMinimumHeight(1000);
            }
        });
    }
}