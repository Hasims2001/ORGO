package com.example.orgo;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;



public class dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView help_box;
//            , gov_box, law_box, trans_box, body_box, donate_box;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        help_box = findViewById(R.id.helpbox);
        drawerLayout = findViewById(R.id.dashboard);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//
//                if(id == R.id.profilebtn){
//                    Toast.makeText(getApplicationContext(),"profile!",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                else if (id == R.id.donarbtn){
//                    Toast.makeText(getApplicationContext(),"donar card!",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                else if(id == R.id.aboutbtn){
//                    Toast.makeText(getApplicationContext(),"about orgo!",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                else if(id == R.id.sharebtn){
//                    Toast.makeText(getApplicationContext(),"Share!",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                else if(id == R.id.logoutbtn){
////            Intent intent = new Intent(dashboard.this, MainActivity.class);
////            startActivity(intent);
//                    Toast.makeText(getApplicationContext(),"Logout!",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                switch (item.getItemId()){
//                    case R.id.profilebtn:
//                        Toast.makeText(getApplicationContext(),"profile!",Toast.LENGTH_SHORT).show();
//                        return true;
//
//                    case R.id.logoutbtn:
//                        Toast.makeText(getApplicationContext(),"Logout!",Toast.LENGTH_SHORT).show();
//                        return true;
//
//
//                }
//                return true;
//            }
//        });



//      ----------------
//      | helpline box |
//      ----------------
        String num = "+916355850257";
        help_box.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }


        });


    }
//    public void logoutclick(View v){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }


}