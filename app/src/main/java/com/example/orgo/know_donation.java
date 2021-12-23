package com.example.orgo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class know_donation extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    LinearLayout first_layout;
    CardView first_card, second_card;
    ImageView img_one, one_img;
    TextView first_dis, first_txt;
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

        first_dis = findViewById(R.id.first_dis);
        first_layout = findViewById(R.id.first_layout);
        first_card = findViewById(R.id.first_card);
        img_one = findViewById(R.id.img_one);
        one_img = findViewById(R.id.one_img);

        first_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                int x = (first_dis.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                TransitionManager.beginDelayedTransition(first_layout, new AutoTransition());

                first_dis.setVisibility(x);

                  if(img_one.getVisibility()==View.VISIBLE) {
                      img_one.setVisibility(View.INVISIBLE);
                      one_img.setVisibility(View.VISIBLE);
                  }
                  else if(one_img.getVisibility()==View.VISIBLE) {
                      one_img.setVisibility(View.INVISIBLE);
                      img_one.setVisibility(View.VISIBLE);
                  }
            }
        });


    }

}