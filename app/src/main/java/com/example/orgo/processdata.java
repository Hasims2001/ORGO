package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class processdata extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String user_clicked;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, donorinfo, recipientinfo;
    TextView idone,nameone,daddress, dphone, dmail, daadhar, dage, medicalstatus, contactbanner;

    String parents,status, district, state, txt_name;

    TextView insname, insaddress, insphone, insemail, inswebsite, drnameone, drnametwo, drnamethree, drphoneone, drphonetwo, drphonethree, dremailone, dremailtwo, dremailthree;
    String ins_name, ins_address, ins_phone, ins_email, ins_website, per_nameone, per_nametwo, per_namethree, per_phoneone, per_phonetwo, per_phonethree, per_emailone, per_emailtwo, per_emailthree;
    FrameLayout donor_img;
    View contactthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processdata);

        drawerLayout = findViewById(R.id.prodata);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);


        user_clicked = getIntent().getStringExtra("clicked");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Institute");
        donorinfo = firebaseDatabase.getReference("donor_info");
        recipientinfo = firebaseDatabase.getReference("recipient_info");

        idone = findViewById(R.id.idone);
        nameone = findViewById(R.id.nameone);
        daddress = findViewById(R.id.daddress);
        dphone = findViewById(R.id.dphone);
        dmail = findViewById(R.id.dmail);
        daadhar = findViewById(R.id.daadhar);
        dage = findViewById(R.id.dage);
        medicalstatus = findViewById(R.id.medicalstatus);
        contactbanner = findViewById(R.id.contactbanner);
        contactthem =  findViewById(R.id.contactthem);

        // for donor
        Query check_d_user = donorinfo.orderByChild("donorid").equalTo(user_clicked);
        check_d_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        parents = childSnapshot.getKey();
                    }
                    idone.setText(user_clicked);
                    nameone.setText(snapshot.child(parents).child("name").getValue(String.class));
                    daddress.setText(snapshot.child(parents).child("address").getValue(String.class));
                    dphone.setText(snapshot.child(parents).child("phone").getValue(String.class));
                    dmail.setText(snapshot.child(parents).child("mail").getValue(String.class));
                    daadhar.setText(snapshot.child(parents).child("aadhar").getValue(String.class));
                    dage.setText(snapshot.child(parents).child("s_age").getValue(String.class));
                    status = snapshot.child(parents).child("medical_checkup").getValue(String.class);
                    medicalstatus.setText(status);
                    district = snapshot.child(parents).child("district").getValue(String.class);
                    state = snapshot.child(parents).child("state").getValue(String.class);

                    check_status_donor();
                }else{
                    // for recipient
                    Query check_r_user = recipientinfo.orderByChild("donorid").equalTo(user_clicked);
                    check_r_user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                    parents = childSnapshot.getKey();
                                }
                                idone.setText(user_clicked);
                                nameone.setText(snapshot.child(parents).child("name").getValue(String.class));
                                daddress.setText(snapshot.child(parents).child("address").getValue(String.class));
                                dphone.setText(snapshot.child(parents).child("phone").getValue(String.class));
                                dmail.setText(snapshot.child(parents).child("mail").getValue(String.class));
                                daadhar.setText(snapshot.child(parents).child("aadhar").getValue(String.class));
                                dage.setText(snapshot.child(parents).child("s_age").getValue(String.class));
                                status = snapshot.child(parents).child("medical_checkup").getValue(String.class);
                                medicalstatus.setText(status);
                                district = snapshot.child(parents).child("district").getValue(String.class);
                                state = snapshot.child(parents).child("state").getValue(String.class);

                                check_status_recipient();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void check_status_donor() {
        if (status.equals("pending")){
            medicalstatus.setTextColor(R.color.yellow);
            contactbanner.setVisibility(View.VISIBLE);
            Query check_ins = databaseReference.child(state).orderByChild("name").equalTo(district);
            check_ins.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        insname = findViewById(R.id.institute_name);
                        insaddress = findViewById(R.id.output_add);
                        insphone = findViewById(R.id.output_phone);
                        insemail = findViewById(R.id.output_email);
                        inswebsite = findViewById(R.id.output_website);

                        drnameone = findViewById(R.id.drnameone);
                        drnametwo = findViewById(R.id.drnametwo);
                        drnamethree = findViewById(R.id.drnamethree);

                        drphoneone = findViewById(R.id.drphoneone);
                        drphonetwo = findViewById(R.id.drphonetwo);
                        drphonethree = findViewById(R.id.drphonethree);

                        dremailone = findViewById(R.id.dremailone);
                        dremailtwo = findViewById(R.id.dremailtwo);
                        dremailthree = findViewById(R.id.dremailthree);

                        ins_name = snapshot.child(district).child("ins_name").getValue(String.class);
                        insname.setText(ins_name);
                        ins_address = snapshot.child(district).child("ins_address").getValue(String.class);
                        insaddress.setText(ins_address);
                        ins_phone = snapshot.child(district).child("ins_phoneno").getValue(String.class);
                        insphone.setText(ins_phone);
                        ins_email = snapshot.child(district).child("ins_email").getValue(String.class);
                        insemail.setText(ins_email);
                        ins_website = snapshot.child(district).child("ins_website").getValue(String.class);
                        inswebsite.setText(ins_website);


                        per_nameone = snapshot.child(district).child("per_name_one").getValue(String.class);
                        drnameone.setText(per_nameone);
                        per_nametwo = snapshot.child(district).child("per_name_two").getValue(String.class);
                        drnametwo.setText(per_nametwo);
                        per_namethree = snapshot.child(district).child("per_name_three").getValue(String.class);
                        drnamethree.setText(per_namethree);

                        per_phoneone = snapshot.child(district).child("per_phone_one").getValue(String.class);
                        drphoneone.setText(per_phoneone);
                        per_phonetwo = snapshot.child(district).child("per_phone_two").getValue(String.class);
                        drphonetwo.setText(per_phonetwo);
                        per_phonethree = snapshot.child(district).child("per_phone_three").getValue(String.class);
                        drphonethree.setText(per_phonethree);

                        per_emailone = snapshot.child(district).child("per_email_one").getValue(String.class);
                        dremailone.setText(per_emailone);
                        per_emailtwo = snapshot.child(district).child("per_email_two").getValue(String.class);
                        dremailtwo .setText(per_emailtwo);
                        per_emailthree = snapshot.child(district).child("per_email_three").getValue(String.class);
                        dremailthree.setText(per_emailthree);

                    }else{
                        Toast.makeText(getApplicationContext(), "Data Doesn't Exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else if (status.equals("completed")){
            medicalstatus.setTextColor(R.color.green);
            contactthem.setVisibility(View.GONE);
            donor_img = findViewById(R.id.donor_img);
            donor_img.setVisibility(View.VISIBLE);
            contactbanner.setText("Donor card will be sent to you.");
            contactbanner.setVisibility(View.VISIBLE);

        } else if (status.equals("rejected")){
            medicalstatus.setTextColor(R.color.red);
            contactbanner.setText("Sorry, You are not Eligible");
            contactbanner.setVisibility(View.VISIBLE);
            contactthem.setVisibility(View.GONE);
        }
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void check_status_recipient() {
        if (status.equals("pending")){
            medicalstatus.setTextColor(R.color.yellow);
            contactbanner.setVisibility(View.VISIBLE);
            Query check_ins = databaseReference.child(state).orderByChild("name").equalTo(district);
            check_ins.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        insname = findViewById(R.id.institute_name);
                        insaddress = findViewById(R.id.output_add);
                        insphone = findViewById(R.id.output_phone);
                        insemail = findViewById(R.id.output_email);
                        inswebsite = findViewById(R.id.output_website);

                        drnameone = findViewById(R.id.drnameone);
                        drnametwo = findViewById(R.id.drnametwo);
                        drnamethree = findViewById(R.id.drnamethree);

                        drphoneone = findViewById(R.id.drphoneone);
                        drphonetwo = findViewById(R.id.drphonetwo);
                        drphonethree = findViewById(R.id.drphonethree);

                        dremailone = findViewById(R.id.dremailone);
                        dremailtwo = findViewById(R.id.dremailtwo);
                        dremailthree = findViewById(R.id.dremailthree);

                        ins_name = snapshot.child(district).child("ins_name").getValue(String.class);
                        insname.setText(ins_name);
                        ins_address = snapshot.child(district).child("ins_address").getValue(String.class);
                        insaddress.setText(ins_address);
                        ins_phone = snapshot.child(district).child("ins_phoneno").getValue(String.class);
                        insphone.setText(ins_phone);
                        ins_email = snapshot.child(district).child("ins_email").getValue(String.class);
                        insemail.setText(ins_email);
                        ins_website = snapshot.child(district).child("ins_website").getValue(String.class);
                        inswebsite.setText(ins_website);


                        per_nameone = snapshot.child(district).child("per_name_one").getValue(String.class);
                        drnameone.setText(per_nameone);
                        per_nametwo = snapshot.child(district).child("per_name_two").getValue(String.class);
                        drnametwo.setText(per_nametwo);
                        per_namethree = snapshot.child(district).child("per_name_three").getValue(String.class);
                        drnamethree.setText(per_namethree);

                        per_phoneone = snapshot.child(district).child("per_phone_one").getValue(String.class);
                        drphoneone.setText(per_phoneone);
                        per_phonetwo = snapshot.child(district).child("per_phone_two").getValue(String.class);
                        drphonetwo.setText(per_phonetwo);
                        per_phonethree = snapshot.child(district).child("per_phone_three").getValue(String.class);
                        drphonethree.setText(per_phonethree);

                        per_emailone = snapshot.child(district).child("per_email_one").getValue(String.class);
                        dremailone.setText(per_emailone);
                        per_emailtwo = snapshot.child(district).child("per_email_two").getValue(String.class);
                        dremailtwo .setText(per_emailtwo);
                        per_emailthree = snapshot.child(district).child("per_email_three").getValue(String.class);
                        dremailthree.setText(per_emailthree);

                    }else{
                        Toast.makeText(getApplicationContext(), "Data Doesn't Exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else if (status.equals("completed")){
            medicalstatus.setTextColor(R.color.green);
            contactthem.setVisibility(View.GONE);

        } else if (status.equals("rejected")){
            medicalstatus.setTextColor(R.color.red);
            contactbanner.setText("Sorry, You are not Eligible!");
            contactbanner.setVisibility(View.VISIBLE);
            contactthem.setVisibility(View.GONE);
        }
    }
}