package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class donor_alive extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String sel_state, sel_district, d_type, donortype;
    Spinner dage;
    private ArrayAdapter<CharSequence> ageAdapter;
    List age = new ArrayList<Integer>();

    private ArrayAdapter<CharSequence> dtype;
    Spinner dtype_spinner;

    EditText donor_name, donor_address, donor_phoneno, donor_mail, donor_aadhar;
    RadioGroup donor_gender;
    RadioButton radioid;
    TextView gender_txt, age_txt, type_txt;
    String sel_gender;
    Button submit_btn;
    private String selected_age, selected_type;

    List<Integer> ran_store = new ArrayList<Integer>();


    //  After submit

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, donateinfo;

    TextView out_id, out_name, out_address, out_phone, out_mail, out_aadhar, out_s_age;
    LinearLayout in_details, out_details;
    String[] i_names, i_address, i_phones, i_emails, i_websites;
    String details, myphone, txt_name;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_alive);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userdata");
        donateinfo = firebaseDatabase.getReference("userdata/Donate_info");


        drawerLayout = findViewById(R.id.donor_alive);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        sel_state = getIntent().getStringExtra("state");
        sel_district = getIntent().getStringExtra("district");
        d_type = getIntent().getStringExtra("d_type");
        txt_name = getIntent().getStringExtra("username");
        dage = findViewById(R.id.donor_age);

        age.add(Integer.toString(0));

        for (int i = 18; i <= 100; i++) {
            age.add(Integer.toString(i));
        }


        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, age);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dage.setAdapter(spinnerArrayAdapter);

        dage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_age = dage.getSelectedItem().toString(); // here is selected age

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dtype_spinner = findViewById(R.id.dtype_s);

        String[] value = getResources().getStringArray(R.array.dtype);

        dtype = ArrayAdapter.createFromResource(this, R.array.dtype, R.layout.spinner_layout);
        dtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dtype_spinner.setAdapter(dtype);

        for (int i = 0; i < 12; i++) {
            if (value[i].equals(d_type)) {
                dtype_spinner.setSelection(i);
            }
        }
        dtype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_type = dtype_spinner.getSelectedItem().toString(); // Here is donate type
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        donor_name = findViewById(R.id.donor_name);
        donor_address = findViewById(R.id.donor_address);
        donor_phoneno = findViewById(R.id.donor_phoneno);
        donor_mail = findViewById(R.id.donor_mail);
        donor_gender = findViewById(R.id.dgender);
        gender_txt = findViewById(R.id.textView7);
        age_txt = findViewById(R.id.textView8);
        type_txt = findViewById(R.id.textView9);
        donor_aadhar = findViewById(R.id.donor_aadhar);
        submit_btn = findViewById(R.id.submit_btn);

        final ProgressBar prog = findViewById(R.id.prog);

        submit_btn.setOnClickListener(v -> {



            final String name = donor_name.getText().toString().trim();
            final String address = donor_address.getText().toString().trim();
            final String phone = donor_phoneno.getText().toString().trim();
            final String mail = donor_mail.getText().toString().trim();
            final String aadhar = donor_aadhar.getText().toString().trim();
            final String s_age = selected_age;


            if (!name.isEmpty()) {
                donor_name.setError(null);
                if (!address.isEmpty()) {
                    donor_address.setError(null);
                    if (!phone.isEmpty()) {
                        donor_phoneno.setError(null);
                        if (!mail.isEmpty()) {
                            donor_mail.setError(null);
                            if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                                donor_phoneno.setError(null);
                                if (mail.matches("^(.+)@(.+)$")) {
                                    donor_mail.setError(null);
                                    if (!aadhar.isEmpty() & aadhar.length() == 12) {
                                        donor_aadhar.setError(null);
                                        //check for reattempt or not
                                        Query check_username = donateinfo.orderByChild("aadhar").equalTo(aadhar);

                                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()){
                                                    donor_aadhar.setError("Donor is Already Registered!");
                                                    donor_aadhar.requestFocus();
                                                }else{
                                                    donor_aadhar.setError(null);
                                                    // RadioButton
                                                    final int IDs = donor_gender.getCheckedRadioButtonId();
                                                    if (IDs == -1) {
                                                        gender_txt.setError("Select One");
                                                        gender_txt.requestFocus();
                                                    } else {
                                                        gender_txt.setError(null);
                                                        radioid = findViewById(IDs);
                                                        sel_gender = radioid.getText().toString();// here is sel_gender

                                                        // age spinner
                                                        if (selected_age.equals("0")) {
                                                            age_txt.setError("Select Donor Age");
                                                            age_txt.requestFocus();
                                                        } else {
                                                            age_txt.setError(null);

                                                            // donate type spinner
                                                            if (selected_type.equals("Select one")) {
                                                                type_txt.setError("Select Donate Type");
                                                                type_txt.requestFocus();
                                                            } else {
                                                                type_txt.setError(null);

                                                                switch (selected_type) {
                                                                    case "Bone":
                                                                        donortype = "DBone";
                                                                        break;
                                                                    case "Body":
                                                                        donortype = "DBody";
                                                                        break;
                                                                    case "Bone marrow":
                                                                        donortype = "DBonemarrow";
                                                                        break;
                                                                    case "Corneas":
                                                                        donortype = "DCorneas";
                                                                        break;
                                                                    case "Heart":
                                                                        donortype = "DHeart";
                                                                        break;
                                                                    case "Intestine":
                                                                        donortype = "DIntestine";
                                                                        break;
                                                                    case "Kidney":
                                                                        donortype = "DKidney";
                                                                        break;
                                                                    case "Liver":
                                                                        donortype = "DLiver";
                                                                        break;
                                                                    case "Lung":
                                                                        donortype = "DLung";
                                                                        break;
                                                                    case "Middle ear":
                                                                        donortype = "DMiddleear";
                                                                        break;
                                                                    case "Pancreas":
                                                                        donortype = "DPancreas";
                                                                        break;
                                                                    case "Skin":
                                                                        donortype = "DSkin";
                                                                        break;

                                                                }
                                                                Random rd = new Random();
                                                                int val = rd.nextInt(100000);
//                                                    for (int i = 0; i < ran_store.size(); i++){
//
//                                                        if (ran_store.get(i) == val){
//                                                            val = rd.nextInt(10000);
//                                                        }
//                                                    }
//                                                    ran_store.add(val);

                                                                final String donorid = donortype + String.valueOf(val);

                                                                prog.setVisibility(View.VISIBLE);
                                                                submit_btn.setVisibility(View.INVISIBLE);


                                                                storingdonordata store_donor_data = new storingdonordata(donorid, name, address, phone, mail, aadhar, s_age);
                                                                databaseReference.child("Donate_info").child(aadhar).setValue(store_donor_data);

                                                                in_details = findViewById(R.id.details);
                                                                out_details = findViewById(R.id.show_details);

                                                                Handler handler = new Handler();
                                                                handler.postDelayed(new Runnable() {
                                                                    public void run() {
                                                                        // yourMethod();
                                                                        prog.setVisibility(View.INVISIBLE);
                                                                        submit_btn.setVisibility(View.VISIBLE);

                                                                        in_details.setVisibility(View.GONE);
                                                                        out_details.setVisibility(View.VISIBLE);

                                                                    }
                                                                }, 4000);


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
                                                                out_s_age.setText("Age : " + s_age);

                                                                ListView contactlist = findViewById(R.id.contact);

                                                                String[] ins_name = getResources().getStringArray(R.array.ins_name);
                                                                String[] ins_address = getResources().getStringArray(R.array.ins_address);
                                                                String[] ins_phoneno = getResources().getStringArray(R.array.ins_phoneno);
                                                                String[] ins_email = getResources().getStringArray(R.array.ins_email);
                                                                String[] ins_website = getResources().getStringArray(R.array.ins_website);

                                                                switch (sel_district) {
//                                Andaman Nicobar state
                                                                    case "Nicobar":

                                                                    case "North Middle Andaman":

                                                                    case "South Andaman":
                                                                        i_names = Arrays.copyOfRange(ins_name, 0, 1);
                                                                        i_address = Arrays.copyOfRange(ins_address, 0, 1);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 0, 1);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 0, 1);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 0, 1);

                                                                        contactAdapter nicobar = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(nicobar);
                                                                        break;


//                                Andhra pradesh state
                                                                    case "Anantapur":
                                                                        i_names = Arrays.copyOfRange(ins_name, 1, 2);
                                                                        i_address = Arrays.copyOfRange(ins_address, 1, 2);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 1, 2);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 1, 2);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 1, 2);

                                                                        contactAdapter anantapur = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(anantapur);
                                                                        break;

                                                                    case "Chittoor":
                                                                        i_names = Arrays.copyOfRange(ins_name, 2, 3);
                                                                        i_address = Arrays.copyOfRange(ins_address, 2, 3);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 2, 3);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 2, 3);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 2, 3);

                                                                        contactAdapter chittoor = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(chittoor);
                                                                        break;

                                                                    case "East Godavari":
                                                                        i_names = Arrays.copyOfRange(ins_name, 3, 4);
                                                                        i_address = Arrays.copyOfRange(ins_address, 3, 4);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 3, 4);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 3, 4);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 3, 4);

                                                                        contactAdapter godavari = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(godavari);
                                                                        break;

                                                                    case "Guntur":
                                                                        i_names = Arrays.copyOfRange(ins_name, 4, 5); // 4,7
                                                                        i_address = Arrays.copyOfRange(ins_address, 4, 5);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 4, 5);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 4, 5);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 4, 5);

                                                                        contactAdapter guntur = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(guntur);
                                                                        break;

                                                                    case "Kadapa":
                                                                        i_names = Arrays.copyOfRange(ins_name, 7, 8); //7,9
                                                                        i_address = Arrays.copyOfRange(ins_address, 7, 8);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 7, 8);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 7, 8);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 7, 8);

                                                                        contactAdapter kadapa = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(kadapa);
                                                                        break;

                                                                    case "Krishna":
                                                                        i_names = Arrays.copyOfRange(ins_name, 9, 10);
                                                                        i_address = Arrays.copyOfRange(ins_address, 9, 10);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 9, 10);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 9, 10);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 9, 10);

                                                                        contactAdapter krishna = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(krishna);
                                                                        break;

                                                                    case "Kurnool":
                                                                        i_names = Arrays.copyOfRange(ins_name, 10, 11); // 10,12
                                                                        i_address = Arrays.copyOfRange(ins_address, 10, 11);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 10, 11);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 10, 11);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 10, 11);

                                                                        contactAdapter kurnool = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(kurnool);
                                                                        break;

                                                                    case "Nellore":
                                                                        i_names = Arrays.copyOfRange(ins_name, 12, 13); // 12,14
                                                                        i_address = Arrays.copyOfRange(ins_address, 12, 13);
                                                                        i_phones = Arrays.copyOfRange(ins_phoneno, 12, 13);
                                                                        i_emails = Arrays.copyOfRange(ins_email, 12, 13);
                                                                        i_websites = Arrays.copyOfRange(ins_website, 12, 13);

                                                                        contactAdapter nellore = new contactAdapter(donor_alive.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
                                                                        contactlist.setAdapter(nellore);
                                                                        break;

                                                                }

                                                                details = donorid + "\n" + name + "\n" + address + "\n" + phone + "\n" + mail + "\n" + aadhar + "\n" + s_age;
                                                                if(ContextCompat.checkSelfPermission(donor_alive.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                                                                    sendMessage();

                                                                }else{
                                                                    ActivityCompat.requestPermissions(donor_alive.this, new String[] {Manifest.permission.SEND_SMS}, 100);
                                                                }

                                                                //share data to Process
                                                                SharedPreferences sharedPref = getSharedPreferences("donote", MODE_PRIVATE);
                                                                SharedPreferences.Editor editor = sharedPref.edit();
                                                                editor.putString("did", donorid);
                                                                editor.putString("name", name);
                                                                editor.putString("address", address);
                                                                editor.putString("district", sel_district);
                                                                editor.putString("state", sel_state);
                                                                editor.putString("phone", phone);
                                                                editor.putString("mail", mail);
                                                                editor.putString("aadhar", aadhar);
                                                                editor.putString("gender", sel_gender);
                                                                editor.putString("age", s_age);
//                                                                editor.putString("i_names", Arrays.toString(i_names));
//                                                                editor.putString("i_address", Arrays.toString(i_address));
//                                                                editor.putString("i_phones", Arrays.toString(i_phones));
//                                                                editor.putString("i_emails", Arrays.toString(i_emails));
//                                                                editor.putString("i_websites", Arrays.toString(i_websites));
                                                                editor.apply();

                                                            }
                                                        }

                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    } else {
                                        donor_aadhar.setError("Enter Valid Aadhar Number");
                                    }
                                } else {
                                    donor_mail.setError("Enter Valid Mail Address");
                                }
                            } else {
                                donor_phoneno.setError("Enter Valid Number");
                            }
                        } else {
                            donor_mail.setError("Mail-Address can't be Empty!");
                        }
                    } else {
                        donor_phoneno.setError("Mobile Number can't be Empty!");
                    }
                } else {
                    donor_address.setError("Address can't be Empty!");
                }
            } else {
                donor_name.setError("Name can't be Empty!");
            }


        });


    }

    private void sendMessage() {
         myphone = "6355850257"; // in place of i_phone, used myphone number

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(myphone, null, details, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }else{
            Toast.makeText(getApplicationContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }
}