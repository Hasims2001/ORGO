package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feed_back extends AppCompatActivity {

    String txt_name, mailing;
    TextView username, mailadd;
    EditText feed;
    Button submit;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        txt_name = getIntent().getStringExtra("user");

        drawerLayout = findViewById(R.id.feedback);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        username = findViewById(R.id.user);
        feed = findViewById(R.id.feed);
        mailadd = findViewById(R.id.mailadd);
        submit = findViewById(R.id.submit);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        username.setText(txt_name);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userdata");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mailing = snapshot.child(txt_name).child("mail").getValue(String.class);
                mailadd.setText(mailing);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(feed_back.this, "Fail to get data. Try Again.", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(v -> {
            if(!mailadd.getText().toString().equals("")){
                mailadd.setError(null);
                if (!feed.getText().toString().equals("")){
                    feed.setError(null);

                    databaseReference.child(txt_name).child("feedback").setValue(feed.getText().toString());

                    Toast.makeText(feed_back.this, "Thank you for your Feedback!", Toast.LENGTH_SHORT).show();

                }else{
                    feed.setError("Feedback cannot be empty!");
                }
            }else{
                mailadd.setError("Mail-Address cannot be empty!");
            }


        });

    }
}