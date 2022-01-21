package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class process extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView notsub;

    String donorid,name, address, district, state, phone, mail, aadhar, sel_age, sel_gender;
    String[] i_names, i_address, i_phones, i_emails, i_websites;

    TextView out_id, out_name,out_address, out_phone, out_mail,out_aadhar,out_s_age;

    LinearLayout show_details;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, donateinfo;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        drawerLayout = findViewById(R.id.pro);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        notsub = findViewById(R.id.notsub);
        show_details = findViewById(R.id.show_details);
        SharedPreferences sharedPreferences = getSharedPreferences("donote", MODE_PRIVATE);
         donorid = sharedPreferences.getString("did","");
         name = sharedPreferences.getString("name","");
        address = sharedPreferences.getString("address","");
          district = sharedPreferences.getString("district","");
          state = sharedPreferences.getString("state","");
          phone = sharedPreferences.getString("phone","");
          mail = sharedPreferences.getString("mail","");
          aadhar= sharedPreferences.getString("aadhar","");
        sel_gender = sharedPreferences.getString("gender","");
        sel_age =  sharedPreferences.getString("age","");
//          i_name = sharedPreferences.getString("i_names","");
//         i_address = sharedPreferences.getString("i_address","");
//         i_phone = sharedPreferences.getString("i_phones","");
//         i_email = sharedPreferences.getString("i_emails","");
//         i_website  = sharedPreferences.getString("i_websites","");



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userdata");
        donateinfo = firebaseDatabase.getReference("userdata/Donate_info");

        Query check_aadhar = donateinfo.orderByChild("aadhar").equalTo(aadhar);

        check_aadhar.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    notsub.setVisibility(View.GONE);
                    show_details.setVisibility(View.VISIBLE);

                    out_id = findViewById(R.id.did);
                    out_id.setText("ID : " + donorid);

                    out_name = findViewById(R.id.dname);
                    out_name.setText("Name : " + name);

                    out_address = findViewById(R.id.daddress);
                    out_address.setText("Address : " + address);

                    out_phone = findViewById(R.id.dphone);
                    out_phone.setText("Mobile No. : " + phone);

                    out_mail = findViewById(R.id.dmail);
                    out_mail.setText("Mail : " + mail);

                    out_aadhar = findViewById(R.id.daadhar);
                    out_aadhar.setText("Aadhar No. : " + aadhar);

                    out_s_age = findViewById(R.id.dage);
                    out_s_age.setText("Age : " + sel_age);

                    ListView contactlist = findViewById(R.id.contact);

                    String[] ins_name = getResources().getStringArray(R.array.ins_name);
                    String[] ins_address = getResources().getStringArray(R.array.ins_address);
                    String[] ins_phoneno = getResources().getStringArray(R.array.ins_phoneno);
                    String[] ins_email = getResources().getStringArray(R.array.ins_email);
                    String[] ins_website = getResources().getStringArray(R.array.ins_website);

                    switch (district) {
//                                Andaman Nicobar state
                        case "Nicobar":

                        case "North Middle Andaman":

                        case "South Andaman":
                            i_names = Arrays.copyOfRange(ins_name, 0, 1);
                            i_address = Arrays.copyOfRange(ins_address, 0, 1);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 0, 1);
                            i_emails = Arrays.copyOfRange(ins_email, 0, 1);
                            i_websites = Arrays.copyOfRange(ins_website, 0, 1);

                            contactAdapter nicobar = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(nicobar);
                            break;


//                                Andhra pradesh state
                        case "Anantapur":
                            i_names = Arrays.copyOfRange(ins_name, 1, 2);
                            i_address = Arrays.copyOfRange(ins_address, 1, 2);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 1, 2);
                            i_emails = Arrays.copyOfRange(ins_email, 1, 2);
                            i_websites = Arrays.copyOfRange(ins_website, 1, 2);

                            contactAdapter anantapur = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(anantapur);
                            break;

                        case "Chittoor":
                            i_names = Arrays.copyOfRange(ins_name, 2, 3);
                            i_address = Arrays.copyOfRange(ins_address, 2, 3);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 2, 3);
                            i_emails = Arrays.copyOfRange(ins_email, 2, 3);
                            i_websites = Arrays.copyOfRange(ins_website, 2, 3);

                            contactAdapter chittoor = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(chittoor);
                            break;

                        case "East Godavari":
                            i_names = Arrays.copyOfRange(ins_name, 3, 4);
                            i_address = Arrays.copyOfRange(ins_address, 3, 4);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 3, 4);
                            i_emails = Arrays.copyOfRange(ins_email, 3, 4);
                            i_websites = Arrays.copyOfRange(ins_website, 3, 4);

                            contactAdapter godavari = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(godavari);
                            break;

                        case "Guntur":
                            i_names = Arrays.copyOfRange(ins_name, 4, 5); // 4,7
                            i_address = Arrays.copyOfRange(ins_address, 4, 5);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 4, 5);
                            i_emails = Arrays.copyOfRange(ins_email, 4, 5);
                            i_websites = Arrays.copyOfRange(ins_website, 4, 5);

                            contactAdapter guntur = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(guntur);
                            break;

                        case "Kadapa":
                            i_names = Arrays.copyOfRange(ins_name, 7, 8); //7,9
                            i_address = Arrays.copyOfRange(ins_address, 7, 8);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 7, 8);
                            i_emails = Arrays.copyOfRange(ins_email, 7, 8);
                            i_websites = Arrays.copyOfRange(ins_website, 7, 8);

                            contactAdapter kadapa = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(kadapa);
                            break;

                        case "Krishna":
                            i_names = Arrays.copyOfRange(ins_name, 9, 10);
                            i_address = Arrays.copyOfRange(ins_address, 9, 10);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 9, 10);
                            i_emails = Arrays.copyOfRange(ins_email, 9, 10);
                            i_websites = Arrays.copyOfRange(ins_website, 9, 10);

                            contactAdapter krishna = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(krishna);
                            break;

                        case "Kurnool":
                            i_names = Arrays.copyOfRange(ins_name, 10, 11); // 10,12
                            i_address = Arrays.copyOfRange(ins_address, 10, 11);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 10, 11);
                            i_emails = Arrays.copyOfRange(ins_email, 10, 11);
                            i_websites = Arrays.copyOfRange(ins_website, 10, 11);

                            contactAdapter kurnool = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(kurnool);
                            break;

                        case "Nellore":
                            i_names = Arrays.copyOfRange(ins_name, 12, 13); // 12,14
                            i_address = Arrays.copyOfRange(ins_address, 12, 13);
                            i_phones = Arrays.copyOfRange(ins_phoneno, 12, 13);
                            i_emails = Arrays.copyOfRange(ins_email, 12, 13);
                            i_websites = Arrays.copyOfRange(ins_website, 12, 13);

                            contactAdapter nellore = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                            contactlist.setAdapter(nellore);
                            break;

                    }


                }else{
                    notsub.setVisibility(View.VISIBLE);
                    show_details.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}