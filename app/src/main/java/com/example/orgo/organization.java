package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class organization extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txt_one,txt_two, txt_three, txt_four;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        txt_one = findViewById(R.id.txtone);
        txt_two = findViewById(R.id.txttwo);
        txt_three = findViewById(R.id.txtthree);
        txt_four = findViewById(R.id.txtfour);

        drawerLayout = findViewById(R.id.knowpage);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.homebtn) {
                    Intent intent = new Intent(organization.this, dashboard.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.profilebtn) {
                    Toast.makeText(getApplicationContext(), "profile!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.donarbtn) {
                    Toast.makeText(getApplicationContext(), "donar card!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.aboutbtn) {
                    Toast.makeText(getApplicationContext(), "about orgo!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.sharebtn) {
                    Intent myIntent = new Intent(Intent.ACTION_SEND);
                    myIntent.setType("text/plain");
                    String body = "Your body here";
                    String sub = "Your Subject";
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                    myIntent.putExtra(Intent.EXTRA_TEXT,body);
                    startActivity(Intent.createChooser(myIntent, "Share Using"));
                    return true;
                } else if (id == R.id.logoutbtn) {
                    Intent intent = new Intent(organization.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });

        txt_one.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.organindia.org");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        txt_two.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.mohanfoundation.org");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        txt_three.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://dghs.gov.in/content/1353_3_NationalOrganTransplantProgramme.aspx#mid");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        txt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.kidney.org");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}