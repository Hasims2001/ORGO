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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import java.util.List;
import java.util.Random;

public class donor_alive extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String sel_state, sel_district,  donortype;
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
    private static final int REQUEST_CALL = 1;
    TextView out_id, out_name, out_address, out_phone, out_mail, out_aadhar, out_s_age, out_medical;
    LinearLayout in_details, out_details;
    String[] i_names, i_address, i_phones, i_emails, i_websites;
    String details, myphone, txt_name;
    String medical_checkup = "pending";


    DatabaseReference insinfo;
    ProgressBar prog;

    TextView insname, insaddress, insphone, insemail, inswebsite, drnameone, drnametwo, drnamethree, drphoneone, drphonetwo, drphonethree, dremailone, dremailtwo, dremailthree;
    String ins_name, ins_address, ins_phone, ins_email, ins_website, per_nameone, per_nametwo, per_namethree, per_phoneone, per_phonetwo, per_phonethree, per_emailone, per_emailtwo, per_emailthree;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_alive);



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("donor_info");
//        donateinfo = firebaseDatabase.getReference("userdata/Donate_info");


        drawerLayout = findViewById(R.id.donor_alive);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);

        in_details = findViewById(R.id.details);
        out_details = findViewById(R.id.show_details);

        in_details.setVisibility(View.VISIBLE);
        out_details.setVisibility(View.GONE);


        sel_state = getIntent().getStringExtra("state");
        sel_district = getIntent().getStringExtra("district");

        txt_name = getIntent().getStringExtra("username");
        dage = findViewById(R.id.donor_age);

        TextView callme = findViewById(R.id.callme);
        String num = "+916355850257";
        callme.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        });
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


        dtype_spinner = findViewById(R.id.orgn_spin);

        String[] value = getResources().getStringArray(R.array.dtype);

        dtype = ArrayAdapter.createFromResource(this, R.array.dtype, R.layout.spinner_layout);
        dtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dtype_spinner.setAdapter(dtype);


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

        prog = findViewById(R.id.prog);

        submit_btn.setOnClickListener(v -> {

//            Query count_atmp = databaseReference.orderByChild("username").equalTo(txt_name);

            databaseReference.orderByChild("username").startAt(txt_name).endAt(txt_name + "\uf8ff" ).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                       int counted = (int) snapshot.getChildrenCount();
//                        Toast.makeText(getApplicationContext(), Integer.toString(counted), Toast.LENGTH_SHORT).show();
                        if (counted < 3){
                            submitting();
                        }else{
                            Toast.makeText(getApplicationContext(),"Your limit has been exceeded!", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        submitting();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        });


    }

    private void submitting() {
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
                                    Query check_username = databaseReference.orderByChild("aadhar").equalTo(aadhar);

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
                                                            SharedPreferences sh = getSharedPreferences("UserInfo", MODE_PRIVATE);
                                                            final String username = sh.getString("user_name", "");

                                                            SharedPreferences sharedPreferences = getSharedPreferences("donoraadhar",MODE_PRIVATE);
                                                            SharedPreferences.Editor daadharnum = sharedPreferences.edit();
                                                            daadharnum.putString("daadhar", aadhar);
                                                            daadharnum.apply();

                                                            storingdonordata store_donor_data = new storingdonordata(donorid, name, address, sel_district, sel_state, phone, mail, aadhar, s_age, medical_checkup, username);
                                                            databaseReference.child(aadhar).setValue(store_donor_data);



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


                                                            out_id = findViewById(R.id.idone);
                                                            out_id.setText("ID : " + donorid);

                                                            out_name = findViewById(R.id.nameone);
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

                                                            out_medical = findViewById(R.id.process_act);
                                                            out_medical.setOnClickListener(v1 -> {
                                                                Intent intent = new Intent(donor_alive.this, process.class);
//                                                                    intent.putExtra("calling-activity", process.ActivityConstants.ACTIVITY_1);
//                                                                    intent.putExtra("calling-dashboard", dashboard.ActivityConstants.ACTIVITY_4);
                                                                startActivity(intent);
                                                            });

//                                                                ListView contactlist = findViewById(R.id.contact);
                                                            insinfo = firebaseDatabase.getReference("Institute");
                                                            Query check_ins = insinfo.child(sel_state).orderByChild("name").equalTo(sel_district);
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

                                                                        ins_name = snapshot.child(sel_district).child("ins_name").getValue(String.class);
                                                                        insname.setText(ins_name);
                                                                        ins_address = snapshot.child(sel_district).child("ins_address").getValue(String.class);
                                                                        insaddress.setText(ins_address);
                                                                        ins_phone = snapshot.child(sel_district).child("ins_phoneno").getValue(String.class);
                                                                        insphone.setText(ins_phone);
                                                                        ins_email = snapshot.child(sel_district).child("ins_email").getValue(String.class);
                                                                        insemail.setText(ins_email);
                                                                        ins_website = snapshot.child(sel_district).child("ins_website").getValue(String.class);
                                                                        inswebsite.setText(ins_website);


                                                                        per_nameone = snapshot.child(sel_district).child("per_name_one").getValue(String.class);
                                                                        drnameone.setText(per_nameone);
                                                                        per_nametwo = snapshot.child(sel_district).child("per_name_two").getValue(String.class);
                                                                        drnametwo.setText(per_nametwo);
                                                                        per_namethree = snapshot.child(sel_district).child("per_name_three").getValue(String.class);
                                                                        drnamethree.setText(per_namethree);

                                                                        per_phoneone = snapshot.child(sel_district).child("per_phone_one").getValue(String.class);
                                                                        drphoneone.setText(per_phoneone);
                                                                        per_phonetwo = snapshot.child(sel_district).child("per_phone_two").getValue(String.class);
                                                                        drphonetwo.setText(per_phonetwo);
                                                                        per_phonethree = snapshot.child(sel_district).child("per_phone_three").getValue(String.class);
                                                                        drphonethree.setText(per_phonethree);

                                                                        per_emailone = snapshot.child(sel_district).child("per_email_one").getValue(String.class);
                                                                        dremailone.setText(per_emailone);
                                                                        per_emailtwo = snapshot.child(sel_district).child("per_email_two").getValue(String.class);
                                                                        dremailtwo .setText(per_emailtwo);
                                                                        per_emailthree = snapshot.child(sel_district).child("per_email_three").getValue(String.class);
                                                                        dremailthree.setText(per_emailthree);

                                                                    }else{
                                                                        Toast.makeText(getApplicationContext(), "Data Doesn't Exists", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });


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