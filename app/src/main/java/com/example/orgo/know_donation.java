package com.example.orgo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class know_donation extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    LinearLayout layout_one;
    CardView card_one, second_card;
    ImageView img_one, one_img;
    TextView dis_one, first_txt;

    String txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_donation);

        drawerLayout = findViewById(R.id.donatebody);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);

        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);

        String[] heading = getResources().getStringArray(R.array.heading);
        String[] txt =  getResources().getStringArray(R.array.answers);

        ListView listView = findViewById(R.id.listview);
        knowdontnAdapter knad = new knowdontnAdapter(this, R.layout.organztn_layout, heading, txt);
        listView.setAdapter(knad);

        Button yesbtn = findViewById(R.id.yesbtn);
        Button nobtn = findViewById(R.id.nobtn);
        LinearLayout option = findViewById(R.id.option);

        String txt_name = getIntent().getStringExtra("username");
        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(know_donation.this, donate_body.class);
                intent.putExtra("username", txt_name);
                intent.putExtra("activity_name", "Donate Organ");
                intent.putExtra("checked", "2");
                startActivity(intent);
            }
        });
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(know_donation.this, "Thank you", Toast.LENGTH_SHORT).show();
                option.setVisibility(View.INVISIBLE);
            }
        });

//        dis_one = findViewById(R.id.dis_one);
//        layout_one = findViewById(R.id.layout_one);
//        card_one = findViewById(R.id.card);
//        img_one = findViewById(R.id.img_one);
//        one_img = findViewById(R.id.one_img);
//
//        card_one.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                int x = (dis_one.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                TransitionManager.beginDelayedTransition(layout_one, new AutoTransition());
//
//                dis_one.setVisibility(x);
//
//                  if(img_one.getVisibility()==View.VISIBLE) {
//                      img_one.setVisibility(View.INVISIBLE);
//                      one_img.setVisibility(View.VISIBLE);
//                  }
//                  else if(one_img.getVisibility()==View.VISIBLE) {
//                      one_img.setVisibility(View.INVISIBLE);
//                      img_one.setVisibility(View.VISIBLE);
//                  }
//            }
//        });


    }

}