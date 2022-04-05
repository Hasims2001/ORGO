package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class process extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView notsub;

//    String donorid, name, address, district, state, phone, mail, aadhar, sel_age, sel_gender,medicalcheck;
//    String[] i_names, i_address, i_phones, i_emails, i_websites;
//
//    String id, name, address,  phone, mail, aadhar, age;
//    TextView out_id, out_name, out_address, out_phone, out_mail, out_aadhar, out_s_age, out_medical;


    ListView user_d, user_r;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, donorinfo, recipientinfo;
    ArrayList<String> d_ids = new ArrayList<>();
    ArrayList<String> d_names = new ArrayList<>();
    ArrayList<String> r_ids = new ArrayList<>();
    ArrayList<String> r_names = new ArrayList<>();
    String[] str_d_ids, str_d_names;
    String[] str_r_ids, str_r_names;
    userdataAdapter donorview, recipientview;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        drawerLayout = findViewById(R.id.pro);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);

        notsub = findViewById(R.id.notsub);
        user_d = findViewById(R.id.user_donor);
        user_r = findViewById(R.id.user_recipient);
        SharedPreferences sh = getSharedPreferences("UserInfo", MODE_PRIVATE);
        final String user_name = sh.getString("user_name", "");

//        Toast.makeText(getApplicationContext(), user_name, Toast.LENGTH_SHORT).show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userdata");
        donorinfo = firebaseDatabase.getReference("donor_info");
        recipientinfo = firebaseDatabase.getReference("recipient_info");


         
        Query check_donor = donorinfo.orderByChild("username").equalTo(user_name);
        check_donor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    notsub.setVisibility(View.GONE);
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
//                        parents.add(childSnapshot.getKey());
                        String parents = childSnapshot.getKey();

                        assert parents != null;
                        d_ids.add(snapshot.child(parents).child("donorid").getValue(String.class));
                        d_names.add(snapshot.child(parents).child("name").getValue(String.class));

                    }
                    str_d_ids = new String[d_ids.size()];
                    for (int i = 0; i < d_ids.size(); i++) {
                        str_d_ids[i] = d_ids.get(i);
                    }
                    str_d_names = new String[d_names.size()];
                    for (int i = 0; i < d_names.size(); i++) {
                        str_d_names[i] = d_names.get(i);

                    }
                    donorview = new userdataAdapter(process.this, R.layout.user_data_layout, str_d_ids, str_d_names);
                    user_d.setAdapter(donorview);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Query check_recipient = recipientinfo.orderByChild("username").equalTo(user_name);
        check_recipient.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    notsub.setVisibility(View.GONE);
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String parents = childSnapshot.getKey();
                        assert parents != null;
                        r_ids.add(snapshot.child(parents).child("donorid").getValue(String.class));
                        r_names.add(snapshot.child(parents).child("name").getValue(String.class));

                    }
                    str_r_ids = new String[r_ids.size()];
                    for (int i = 0; i < r_ids.size(); i++) {
                        str_r_ids[i] = r_ids.get(i);
                    }
                    str_r_names = new String[r_names.size()];
                    for (int i = 0; i < r_names.size(); i++) {
                        str_r_names[i] = r_names.get(i);

                    }
                    recipientview = new userdataAdapter(process.this, R.layout.user_data_layout, str_r_ids, str_r_names);
                    user_r.setAdapter(recipientview);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


//    private void showing_donor(){
//        SharedPreferences sharedPreferences = getSharedPreferences("donote", MODE_PRIVATE);
//        donorid = sharedPreferences.getString("did", "");
//        name = sharedPreferences.getString("name", "");
//        address = sharedPreferences.getString("address", "");
//        district = sharedPreferences.getString("district", "");
//        state = sharedPreferences.getString("state", "");
//        phone = sharedPreferences.getString("phone", "");
//        mail = sharedPreferences.getString("mail", "");
//        aadhar = sharedPreferences.getString("aadhar", "");
//        sel_gender = sharedPreferences.getString("gender", "");
//        sel_age = sharedPreferences.getString("age", "");
//
//
//
//        Query check_aadhar = donorinfo.orderByChild("aadhar").equalTo(aadhar);
//        check_aadhar.addListenerForSingleValueEvent(new ValueEventListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // aadhar exist
//                    Query check_medical = donorinfo.orderByChild("medical_checkup").equalTo("pending");
//                    check_medical.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.exists()) {
//                                // medical checkup pending.
//                                medicalcheck = "pending";
//                                showingcard();
//                                out_medical.setTextColor(Color.parseColor("#FFFF00"));
//                                out_medical.setBackgroundColor(Color.parseColor("#000000"));
//
//                            }else{
//                                Query check_completed = donorinfo.orderByChild("medical_checkup").equalTo("completed");
//                                check_completed.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        if (snapshot.exists()){
//                                            // medical checkup completed
//                                            medicalcheck = "completed";
//                                            completed();
//                                            out_medical.setTextColor(Color.parseColor("#00FF00"));
//                                        }else{
//                                            Query check_rejected = donorinfo.orderByChild("medical_checkup").equalTo("rejected");
//                                            check_rejected.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                    if (snapshot.exists()){
//                                                        //medical chekup rejected.
//                                                        medicalcheck = "Rejected";
//                                                        showingcard();
//                                                        out_medical.setTextColor(Color.parseColor("#FF0000"));
//
//                                                    }else{
//                                                        notsub.setVisibility(View.VISIBLE);
//                                                        show_details.setVisibility(View.GONE);
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                                }
//                                            });
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                } else {
//                    notsub.setVisibility(View.VISIBLE);
//                    show_details.setVisibility(View.GONE);
//                    checked = 2;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    private  void showing_recipient(){
//        SharedPreferences sharedforrecipient = getSharedPreferences("recipient", MODE_PRIVATE);
//        donorid = sharedforrecipient.getString("did", "");
//        name = sharedforrecipient.getString("name", "");
//        address = sharedforrecipient.getString("address", "");
//        district = sharedforrecipient.getString("district", "");
//        state = sharedforrecipient.getString("state", "");
//        phone = sharedforrecipient.getString("phone", "");
//        mail = sharedforrecipient.getString("mail", "");
//        aadhar = sharedforrecipient.getString("aadhar", "");
//        sel_gender = sharedforrecipient.getString("gender", "");
//        sel_age = sharedforrecipient.getString("age", "");
//
//
//        Query check_recaadhar = recipientinfo.orderByChild("aadhar").equalTo(aadhar);
//        check_recaadhar.addListenerForSingleValueEvent(new ValueEventListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // aadhar exist
//                    Query check_medical = recipientinfo.orderByChild("medical_checkup").equalTo("pending");
//                    check_medical.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.exists()) {
//                                // medical checkup pending.
//                                medicalcheck = "pending";
//                                showingcard();
//                                out_medical.setTextColor(Color.parseColor("#FFFF00"));
//                                out_medical.setBackgroundColor(Color.parseColor("#000000"));
//
//                            }else{
//                                Query check_completed = recipientinfo.orderByChild("medical_checkup").equalTo("completed");
//                                check_completed.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        if (snapshot.exists()){
//                                            // medical checkup completed
//                                            medicalcheck = "completed";
//                                            completed();
//                                            out_medical.setTextColor(Color.parseColor("#00FF00"));
//                                        }else{
//                                            Query check_rejected = recipientinfo.orderByChild("medical_checkup").equalTo("rejected");
//                                            check_rejected.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                    if (snapshot.exists()){
//                                                        //medical chekup rejected.
//                                                        medicalcheck = "Rejected";
//                                                        showingcard();
//                                                        out_medical.setTextColor(Color.parseColor("#FF0000"));
//
//                                                    }else{
//                                                        notsub.setVisibility(View.VISIBLE);
//                                                        show_details.setVisibility(View.GONE);
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                                }
//                                            });
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                } else {
//                    notsub.setVisibility(View.VISIBLE);
//                    show_details.setVisibility(View.GONE);
//                    checked = 2;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

//    @SuppressLint("SetTextI18n")
//    private void showingcard() {
//        notsub.setVisibility(View.GONE);
//        show_details.setVisibility(View.VISIBLE);
//
//        out_id = findViewById(R.id.did);
//        out_id.setText("ID : " + donorid);
//
//        out_name = findViewById(R.id.dname);
//        out_name.setText("Name : " + name);
//
////        out_address = findViewById(R.id.daddress);
////        out_address.setText("Address : " + address);
////
////        out_phone = findViewById(R.id.dphone);
////        out_phone.setText("Mobile No. : " + phone);
////
////        out_mail = findViewById(R.id.dmail);
////        out_mail.setText("Mail : " + mail);
////
////        out_aadhar = findViewById(R.id.daadhar);
////        out_aadhar.setText("Aadhar No. : " + aadhar);
////
////        out_s_age = findViewById(R.id.dage);
////        out_s_age.setText("Age : " + sel_age);
////
////        out_medical = findViewById(R.id.medicalstatus);
////        out_medical.setText("Medical Checkup : " + medicalcheck);
////
////        ListView contactlist = findViewById(R.id.contact);
//
//
////        String[] ins_name = getResources().getStringArray(R.array.ins_name);
////        String[] ins_address = getResources().getStringArray(R.array.ins_address);
////        String[] ins_phoneno = getResources().getStringArray(R.array.ins_phoneno);
////        String[] ins_email = getResources().getStringArray(R.array.ins_email);
////        String[] ins_website = getResources().getStringArray(R.array.ins_website);
//
////        switch (district) {
//////                                Andaman Nicobar state
////            case "Nicobar":
////
////            case "North Middle Andaman":
////
////            case "South Andaman":
////                i_d_names = Arrays.copyOfRange(ins_name, 0, 1);
////                i_address = Arrays.copyOfRange(ins_address, 0, 1);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 0, 1);
////                i_emails = Arrays.copyOfRange(ins_email, 0, 1);
////                i_websites = Arrays.copyOfRange(ins_website, 0, 1);
////
////                contactAdapter nicobar = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(nicobar);
////                break;
////
////
//////                                Andhra pradesh state
////            case "Anantapur":
////                i_names = Arrays.copyOfRange(ins_name, 1, 2);
////                i_address = Arrays.copyOfRange(ins_address, 1, 2);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 1, 2);
////                i_emails = Arrays.copyOfRange(ins_email, 1, 2);
////                i_websites = Arrays.copyOfRange(ins_website, 1, 2);
////
////                contactAdapter anantapur = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(anantapur);
////                break;
////
////            case "Chittoor":
////                i_names = Arrays.copyOfRange(ins_name, 2, 3);
////                i_address = Arrays.copyOfRange(ins_address, 2, 3);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 2, 3);
////                i_emails = Arrays.copyOfRange(ins_email, 2, 3);
////                i_websites = Arrays.copyOfRange(ins_website, 2, 3);
////
////                contactAdapter chittoor = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(chittoor);
////                break;
////
////            case "East Godavari":
////                i_names = Arrays.copyOfRange(ins_name, 3, 4);
////                i_address = Arrays.copyOfRange(ins_address, 3, 4);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 3, 4);
////                i_emails = Arrays.copyOfRange(ins_email, 3, 4);
////                i_websites = Arrays.copyOfRange(ins_website, 3, 4);
////
////                contactAdapter godavari = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(godavari);
////                break;
////
////            case "Guntur":
////                i_names = Arrays.copyOfRange(ins_name, 4, 5); // 4,7
////                i_address = Arrays.copyOfRange(ins_address, 4, 5);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 4, 5);
////                i_emails = Arrays.copyOfRange(ins_email, 4, 5);
////                i_websites = Arrays.copyOfRange(ins_website, 4, 5);
////
////                contactAdapter guntur = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(guntur);
////                break;
////
////            case "Kadapa":
////                i_names = Arrays.copyOfRange(ins_name, 7, 8); //7,9
////                i_address = Arrays.copyOfRange(ins_address, 7, 8);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 7, 8);
////                i_emails = Arrays.copyOfRange(ins_email, 7, 8);
////                i_websites = Arrays.copyOfRange(ins_website, 7, 8);
////
////                contactAdapter kadapa = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(kadapa);
////                break;
////
////            case "Krishna":
////                i_names = Arrays.copyOfRange(ins_name, 9, 10);
////                i_address = Arrays.copyOfRange(ins_address, 9, 10);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 9, 10);
////                i_emails = Arrays.copyOfRange(ins_email, 9, 10);
////                i_websites = Arrays.copyOfRange(ins_website, 9, 10);
////
////                contactAdapter krishna = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(krishna);
////                break;
////
////            case "Kurnool":
////                i_names = Arrays.copyOfRange(ins_name, 10, 11); // 10,12
////                i_address = Arrays.copyOfRange(ins_address, 10, 11);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 10, 11);
////                i_emails = Arrays.copyOfRange(ins_email, 10, 11);
////                i_websites = Arrays.copyOfRange(ins_website, 10, 11);
////
////                contactAdapter kurnool = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(kurnool);
////                break;
////
////            case "Nellore":
////                i_names = Arrays.copyOfRange(ins_name, 12, 13); // 12,14
////                i_address = Arrays.copyOfRange(ins_address, 12, 13);
////                i_phones = Arrays.copyOfRange(ins_phoneno, 12, 13);
////                i_emails = Arrays.copyOfRange(ins_email, 12, 13);
////                i_websites = Arrays.copyOfRange(ins_website, 12, 13);
////
////                contactAdapter nellore = new contactAdapter(process.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
////                contactlist.setAdapter(nellore);
////                break;
////
////        }
//    }
//    @SuppressLint("SetTextI18n")
//    private void completed() {
//        ListView contactlist = findViewById(R.id.contact);
//        notsub.setVisibility(View.GONE);
//        show_details.setVisibility(View.VISIBLE);
//        contactbanner.setVisibility(View.GONE);
//        contactlist.setVisibility(View.GONE);
//        info_marq.setVisibility(View.VISIBLE);
//        donor_img.setVisibility(View.VISIBLE);

//        out_id = findViewById(R.id.did);
////        out_id.setText("ID : " + donorid);

//        out_name = findViewById(R.id.dname);
//        out_name.setText("Name : " + name);

//        out_address = findViewById(R.id.daddress);
//        out_address.setText("Address : " + address);
//
//        out_phone = findViewById(R.id.dphone);
//        out_phone.setText("Mobile No. : " + phone);
//
//        out_mail = findViewById(R.id.dmail);
//        out_mail.setText("Mail : " + mail);
//
//        out_aadhar = findViewById(R.id.daadhar);
//        out_aadhar.setText("Aadhar No. : " + aadhar);
//
//        out_s_age = findViewById(R.id.dage);
//        out_s_age.setText("Age : " + sel_age);
//
//        out_medical = findViewById(R.id.medicalstatus);
//        out_medical.setText("Medical Checkup : " + medicalcheck);


//    }



}