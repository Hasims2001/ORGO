package com.example.orgo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView help_box, orgz_box, know_box, awer_box, law_box, trans_box, body_box, donate_box;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        donate_box = findViewById(R.id.onebox);
        body_box = findViewById(R.id.bodybox);
        trans_box = findViewById(R.id.transbox);
        know_box = findViewById(R.id.knowbox);
        awer_box = findViewById(R.id.awerbox);
        law_box = findViewById(R.id.lawbox);
        orgz_box = findViewById(R.id.orgzbox);
        help_box = findViewById(R.id.helpbox);


        drawerLayout = findViewById(R.id.dashboard);
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
                    Intent intent = new Intent(dashboard.this, dashboard.class);
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
                    Intent intent = new Intent(dashboard.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });


//      ---------------------------------
//      | know about Organ Donation box |
//      ---------------------------------
        know_box.setOnClickListener(v -> startActivity(new Intent(dashboard.this, know_donation.class)));


//      -------------------------
//      | Law on Transplant box |
//      -------------------------
        law_box.setOnClickListener(v -> startActivity(new Intent(dashboard.this, law_trans.class)));





//      --------------------------
//      | Donation Awareness box |
//      --------------------------
        awer_box.setOnClickListener(v -> startActivity(new Intent(dashboard.this, awareness.class)));


//      --------------------
//      | Organization box |
//      --------------------
        orgz_box.setOnClickListener(v -> startActivity(new Intent(dashboard.this, organization.class)));

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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

}