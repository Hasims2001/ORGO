package com.example.orgo;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.orgo.MainActivity;
import com.example.orgo.R;
import com.google.android.material.navigation.NavigationView;

public class side_menu extends ContextWrapper {

    public side_menu(Context context) {
        super(context);
    }
    public void initNav(final DrawerLayout drawerLayout, NavigationView navigationView, Toolbar toolbar, final boolean isFinish){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.homebtn) {
                    Intent intent = new Intent(getBaseContext(), dashboard.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.profilebtn) {
                    Intent intent = new Intent(getBaseContext(), user_profile.class);

                    startActivity(intent);
                    return true;
                } else if (id == R.id.dbodybtn) {
                    Intent intent = new Intent(getBaseContext(), body_direction.class);

                    startActivity(intent);
                    return true;
                } else if (id == R.id.mythbtn) {
                    Intent intent = new Intent(getBaseContext(), myth_fact.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.aboutbtn) {
                    Intent intent = new Intent(getBaseContext(), about_orgo.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.feedbtn){
                    Intent intent = new Intent(getBaseContext(), feed_back.class);

                    startActivity(intent);
                    return true;

                } else if (id == R.id.sharebtn) {
                    Intent myIntent = new Intent(Intent.ACTION_SEND);
                    myIntent.setType("text/plain");
                    String body = "This Application is Contain best things.Check this out on Playstore 'ORGO'";
                    String sub = "ORGO";
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                    myIntent.putExtra(Intent.EXTRA_TEXT,body);
                    startActivity(Intent.createChooser(myIntent, "Share Using"));
                    return true;
                } else if (id == R.id.logoutbtn) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                }


                return false;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(((Activity)getBaseContext()), drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


}