package com.example.orgo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class myth_fact extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    SliderView sliderView;
    int[] images = {R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,
            R.drawable.slide4,
            R.drawable.slide5,
            R.drawable.slide6,
            R.drawable.slide7,
            R.drawable.slide8,
            R.drawable.slide9,
            R.drawable.slide10,
            R.drawable.slide11,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myth_fact);

        sliderView = findViewById(R.id.image_slider);

    SliderAdapter sliderAdapter = new SliderAdapter(images);
    sliderView.setSliderAdapter(sliderAdapter);
    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
    sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
    sliderView.startAutoCycle();



        drawerLayout = findViewById(R.id.myth_fact);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);
    }
}