package com.example.orgo;

import androidx.annotation.NonNull;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class orgn_trans extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String selectedstate, selecteddistrict;
    TextView stateview, districtview;
    Spinner indianstates, indiandistrict;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;
    RadioGroup rg;
    RadioButton radioid;
    TextView donor;
    private static final int REQUEST_CALL = 1;
    Button submit_btn;
    List agelist = new ArrayList<Integer>();
    Spinner recage, organ_spinner;
    private ArrayAdapter<CharSequence> orgn_type;
    private String selected_age, selected_type, selected_gender;
    EditText rec_name, rec_address, rec_phoneno, rec_mail,rec_aadhar;
    RadioGroup recipient_gender, donor_radio;
    RadioButton radio_id, recipientid;
    TextView gender_txt, age_txt, type_txt, donor_txt;
    String organtype, details,selected_donor;
    TextView out_id, out_name, out_address, out_phone, out_mail, out_aadhar, out_s_age, out_medical;
    String[] dr_names, dr_phones, dr_emails, i_names, i_address, i_phones, i_emails, i_websites;
    LinearLayout in_details, out_details;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference insinfo, recipientinfo;
    String medical_checkup = "pending";
    ProgressBar prog;
    String txt_name;
    TextView insname, insaddress, insphone, insemail, inswebsite, drnameone, drnametwo, drnamethree, drphoneone, drphonetwo, drphonethree, dremailone, dremailtwo, dremailthree;
    String ins_name, ins_address, ins_phone, ins_email, ins_website, per_nameone, per_nametwo, per_namethree, per_phoneone, per_phonetwo, per_phonethree, per_emailone, per_emailtwo, per_emailthree;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgn_trans);

        drawerLayout = findViewById(R.id.tran_act);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);

        in_details = findViewById(R.id.input_detalis);
        out_details = findViewById(R.id.show_details);

        in_details.setVisibility(View.VISIBLE);
        out_details.setVisibility(View.GONE);

        stateview = findViewById(R.id.state_view);
        districtview = findViewById(R.id.district_view);
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
        SharedPreferences sh = getSharedPreferences("UserInfo", MODE_PRIVATE);
        final String txt_name = sh.getString("user_name", "");

        indianstates = findViewById(R.id.indian_states);
        indiandistrict = findViewById(R.id.indian_district);

        stateAdapter = ArrayAdapter.createFromResource(this, R.array.indian_states, R.layout.spinner_layout);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        indianstates.setAdapter(stateAdapter);
        indianstates.setSelection(1);

        indianstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedstate = indianstates.getSelectedItem().toString();
                int parentID = parent.getId();
                if (parentID == R.id.indian_states){
                    switch (selectedstate){
                        case "Select Your State": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.indian_district, R.layout.spinner_layout);
                            break;
                        case "Andhra Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_andhra_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Arunachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_arunachal_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Assam": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_assam_districts, R.layout.spinner_layout);
                            break;
                        case "Bihar": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_bihar_districts, R.layout.spinner_layout);
                            break;
                        case "Chhattisgarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_chhattisgarh_districts, R.layout.spinner_layout);
                            break;
                        case "Goa": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_goa_districts, R.layout.spinner_layout);
                            break;
                        case "Gujarat": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_gujarat_districts, R.layout.spinner_layout);
                            break;
                        case "Haryana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_haryana_districts, R.layout.spinner_layout);
                            break;
                        case "Himachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_himachal_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Jharkhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_jharkhand_districts, R.layout.spinner_layout);
                            break;
                        case "Karnataka": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_karnataka_districts, R.layout.spinner_layout);
                            break;
                        case "Kerala": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_kerala_districts, R.layout.spinner_layout);
                            break;
                        case "Madhya Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_madhya_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Maharashtra": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_maharashtra_districts, R.layout.spinner_layout);
                            break;
                        case "Manipur": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_manipur_districts, R.layout.spinner_layout);
                            break;
                        case "Meghalaya": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_meghalaya_districts, R.layout.spinner_layout);
                            break;
                        case "Mizoram": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_mizoram_districts, R.layout.spinner_layout);
                            break;
                        case "Nagaland": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_nagaland_districts, R.layout.spinner_layout);
                            break;
                        case "Odisha": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_odisha_districts, R.layout.spinner_layout);
                            break;
                        case "Punjab": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_punjab_districts, R.layout.spinner_layout);
                            break;
                        case "Rajasthan": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_rajasthan_districts, R.layout.spinner_layout);
                            break;
                        case "Sikkim": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_sikkim_districts, R.layout.spinner_layout);
                            break;
                        case "Tamil Nadu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_tamil_nadu_districts, R.layout.spinner_layout);
                            break;
                        case "Telangana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_telangana_districts, R.layout.spinner_layout);
                            break;
                        case "Tripura": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_tripura_districts, R.layout.spinner_layout);
                            break;
                        case "Uttar Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_uttar_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Uttarakhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_uttarakhand_districts, R.layout.spinner_layout);
                            break;
                        case "West Bengal": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_west_bengal_districts, R.layout.spinner_layout);
                            break;
                        case "Andaman and Nicobar Islands": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_andaman_nicobar_districts, R.layout.spinner_layout);
                            break;
                        case "Chandigarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_chandigarh_districts, R.layout.spinner_layout);
                            break;
                        case "Dadra and Nagar Haveli": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_dadra_nagar_haveli_districts, R.layout.spinner_layout);
                            break;
                        case "Daman and Diu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_daman_diu_districts, R.layout.spinner_layout);
                            break;
                        case "Delhi": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_delhi_districts, R.layout.spinner_layout);
                            break;
                        case "Jammu and Kashmir": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_jammu_kashmir_districts, R.layout.spinner_layout);
                            break;
                        case "Lakshadweep": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_lakshadweep_districts, R.layout.spinner_layout);
                            break;
                        case "Ladakh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ladakh_districts, R.layout.spinner_layout);
                            break;
                        case "Puducherry": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_puducherry_districts, R.layout.spinner_layout);
                            break;
                        default:break;
                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    indiandistrict.setAdapter(districtAdapter);
                    indiandistrict.setSelection(1);
                    indiandistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selecteddistrict = indiandistrict.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        recipient age
        recage = findViewById(R.id.rec_age);

        agelist.add(Integer.toString(0));

        for (int i = 18; i <= 100; i++) {
            agelist.add(Integer.toString(i));
        }


        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, agelist);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recage.setAdapter(spinnerArrayAdapter);

        recage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_age = recage.getSelectedItem().toString(); // here is selected age

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        which organ ?
        organ_spinner = findViewById(R.id.orgn_spin);

//        String[] value = getResources().getStringArray(R.array.orgn_type);

        orgn_type = ArrayAdapter.createFromResource(this, R.array.orgn_type, R.layout.spinner_layout);
        orgn_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organ_spinner.setAdapter(orgn_type);


        organ_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_type = organ_spinner.getSelectedItem().toString(); // Here is needed organ type
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rec_name = findViewById(R.id.rec_name);
        rec_address = findViewById(R.id.rec_address);
        rec_phoneno = findViewById(R.id.rec_phoneno);
        rec_mail = findViewById(R.id.rec_mail);
        rec_aadhar = findViewById(R.id.rec_aadhar);
        gender_txt = findViewById(R.id.textView7);
        age_txt = findViewById(R.id.textView8);
        type_txt = findViewById(R.id.textView9);
        recipient_gender = findViewById(R.id.dgender);
        donor_radio = findViewById(R.id.donor_radio);
        donor_txt = findViewById(R.id.textView10);
        prog = findViewById(R.id.prog);
        submit_btn = findViewById(R.id.submit_btn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        recipientinfo = firebaseDatabase.getReference("recipient_info");
        submit_btn.setOnClickListener(v -> {

            recipientinfo.orderByChild("username").startAt(txt_name).endAt(txt_name + "\uf8ff" ).addListenerForSingleValueEvent(new ValueEventListener() {
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



//        submit.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View v) {
//                if (selectedstate.equals("Select Your State")) {
//                    Toast.makeText(orgn_trans.this, "Please Select your state from the list", Toast.LENGTH_LONG).show();
//                    stateview.setError("State is not Selected");
//                    stateview.requestFocus();
//
//                } else if (selecteddistrict.equals("Select Your District")) {
//                    Toast.makeText(orgn_trans.this, "Please Select your district from the list", Toast.LENGTH_LONG).show();
//                    districtview.setError("State is not Selected");
//                    districtview.requestFocus();
//                    stateview.setError(null);
//
//                } else {
////                   after selection of state and district
//                    stateview.setError(null);
//                    districtview.setError(null);
//
//                    Intent intent = new Intent(orgn_trans.this, orgn_trans.class);
//                    intent.putExtra("state", selectedstate);
//                    intent.putExtra("district", selecteddistrict);
////                            intent.putExtra("username", txt_name);
//                    startActivity(intent);
//                }
//            }
//        });
    }

    private void submitting() {
        final String name = rec_name.getText().toString().trim();
        final String address = rec_address.getText().toString().trim();
        final String phone = rec_phoneno.getText().toString().trim();
        final String mail = rec_mail.getText().toString().trim();
        final String aadhar = rec_aadhar.getText().toString().trim();
        final String s_age = selected_age;



        if (!name.isEmpty()) {
            rec_name.setError(null);
            if (!address.isEmpty()) {
                rec_address.setError(null);
                if (!phone.isEmpty()) {
                    rec_phoneno.setError(null);
                    if (!mail.isEmpty()) {
                        rec_mail.setError(null);
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            rec_phoneno.setError(null);
                            if (mail.matches("^(.+)@(.+)$")) {
                                rec_mail.setError(null);
                                if (!aadhar.isEmpty() & aadhar.length() == 12) {
                                    rec_aadhar.setError(null);
                                    //check for reattempt or not
                                    Query check_username = recipientinfo.orderByChild("aadhar").equalTo(aadhar);

                                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()){
                                                rec_aadhar.setError("Recipient is Already Registered!");
                                                rec_aadhar.requestFocus();
                                            }else{
                                                rec_aadhar.setError(null);
                                                // RadioButton
                                                final int IDs = recipient_gender.getCheckedRadioButtonId();
                                                if (IDs == -1) {
                                                    gender_txt.setError("Select One");
                                                    gender_txt.requestFocus();
                                                } else {
                                                    gender_txt.setError(null);
                                                    radio_id = findViewById(IDs);
                                                    selected_gender = radio_id.getText().toString();// here is sel_gender

                                                    // age spinner
                                                    if (selected_age.equals("0")) {
                                                        age_txt.setError("Select Recipient Age");
                                                        age_txt.requestFocus();
                                                    } else {
                                                        age_txt.setError(null);

                                                        // organ type spinner
                                                        if (selected_type.equals("Select one")) {
                                                            type_txt.setError("Select Donate Type");
                                                            type_txt.requestFocus();
                                                        } else {
                                                            type_txt.setError(null);

                                                            // RadioButton donor
//                                                                final int ID_donor = donor_radio.getCheckedRadioButtonId();
//                                                                if (ID_donor == -1) {
//                                                                    donor_txt.setError("Select One");
//                                                                    donor_txt.requestFocus();
//                                                                } else {
//                                                                    donor_txt.setError(null);
//                                                                    recipientid = findViewById(ID_donor);
//                                                                    selected_donor = recipientid.getText().toString();// here is selected_donor

//                                                                    if (selected_donor.equals("Yes")){
//                                                                        Toast.makeText(getApplicationContext(), "You selected yes", Toast.LENGTH_SHORT).show();
//                                                                    }else if (selected_donor.equals("No")){

                                                            switch (selected_type) {
                                                                case "Bone":
                                                                    organtype = "RBone";
                                                                    break;
                                                                case "Bone marrow":
                                                                    organtype = "RBonemarrow";
                                                                    break;
                                                                case "Corneas":
                                                                    organtype = "RCorneas";
                                                                    break;
                                                                case "Heart":
                                                                    organtype = "RHeart";
                                                                    break;
                                                                case "Intestine":
                                                                    organtype = "RIntestine";
                                                                    break;
                                                                case "Kidney":
                                                                    organtype = "RKidney";
                                                                    break;
                                                                case "Liver":
                                                                    organtype = "RLiver";
                                                                    break;
                                                                case "Lung":
                                                                    organtype = "RLung";
                                                                    break;
                                                                case "Middle ear":
                                                                    organtype = "RMiddleear";
                                                                    break;
                                                                case "Pancreas":
                                                                    organtype = "RPancreas";
                                                                    break;
                                                                case "Skin":
                                                                    organtype = "RSkin";
                                                                    break;

                                                            }
                                                            Random rd = new Random();
                                                            int val = rd.nextInt(100000);

                                                            final String recipientid = organtype + String.valueOf(val);

                                                            prog.setVisibility(View.VISIBLE);
                                                            submit_btn.setVisibility(View.INVISIBLE);

                                                            SharedPreferences sh = getSharedPreferences("UserInfo", MODE_PRIVATE);
                                                            final String username = sh.getString("user_name", "");

                                                            SharedPreferences sharedPreferences = getSharedPreferences("recipientaadhar",MODE_PRIVATE);
                                                            SharedPreferences.Editor aadharnum = sharedPreferences.edit();
                                                            aadharnum.putString("raadhar", aadhar);
                                                            aadharnum.apply();

                                                            storingdonordata store_donor_data = new storingdonordata(recipientid, name, address,selecteddistrict, selectedstate,  phone, mail, aadhar, s_age, medical_checkup, username);
                                                            recipientinfo.child(aadhar).setValue(store_donor_data);



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
                                                            out_id.setText("ID : " + recipientid);

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
//                                                                    startActivity(new Intent(orgn_trans.this, process.class));
                                                                Intent intent = new Intent(orgn_trans.this, process.class);
//                                                                    intent.putExtra("from", "Transplant");
//                                                                intent.putExtra("calling-activity", process.ActivityConstants.ACTIVITY_3);
//                                                                    intent.putExtra("calling-dashboard", dashboard.ActivityConstants.ACTIVITY_6);
                                                                startActivity(intent);
                                                            });

//                                                            ListView contactlist = findViewById(R.id.contact);


                                                            insinfo = firebaseDatabase.getReference("Institute");
                                                            Query check_ins = insinfo.child(selectedstate).orderByChild("name").equalTo(selecteddistrict);
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

                                                                        ins_name = snapshot.child(selecteddistrict).child("ins_name").getValue(String.class);
                                                                        insname.setText(ins_name);
                                                                        ins_address = snapshot.child(selecteddistrict).child("ins_address").getValue(String.class);
                                                                        insaddress.setText(ins_address);
                                                                        ins_phone = snapshot.child(selecteddistrict).child("ins_phoneno").getValue(String.class);
                                                                        insphone.setText(ins_phone);
                                                                        ins_email = snapshot.child(selecteddistrict).child("ins_email").getValue(String.class);
                                                                        insemail.setText(ins_email);
                                                                        ins_website = snapshot.child(selecteddistrict).child("ins_website").getValue(String.class);
                                                                        inswebsite.setText(ins_website);


                                                                        per_nameone = snapshot.child(selecteddistrict).child("per_name_one").getValue(String.class);
                                                                        drnameone.setText(per_nameone);
                                                                        per_nametwo = snapshot.child(selecteddistrict).child("per_name_two").getValue(String.class);
                                                                        drnametwo.setText(per_nametwo);
                                                                        per_namethree = snapshot.child(selecteddistrict).child("per_name_three").getValue(String.class);
                                                                        drnamethree.setText(per_namethree);

                                                                        per_phoneone = snapshot.child(selecteddistrict).child("per_phone_one").getValue(String.class);
                                                                        drphoneone.setText(per_phoneone);
                                                                        per_phonetwo = snapshot.child(selecteddistrict).child("per_phone_two").getValue(String.class);
                                                                        drphonetwo.setText(per_phonetwo);
                                                                        per_phonethree = snapshot.child(selecteddistrict).child("per_phone_three").getValue(String.class);
                                                                        drphonethree.setText(per_phonethree);

                                                                        per_emailone = snapshot.child(selecteddistrict).child("per_email_one").getValue(String.class);
                                                                        dremailone.setText(per_emailone);
                                                                        per_emailtwo = snapshot.child(selecteddistrict).child("per_email_two").getValue(String.class);
                                                                        dremailtwo .setText(per_emailtwo);
                                                                        per_emailthree = snapshot.child(selecteddistrict).child("per_email_three").getValue(String.class);
                                                                        dremailthree.setText(per_emailthree);

                                                                    }else{
                                                                        Toast.makeText(getApplicationContext(), "Data Doesn't Exists", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });

//                                                                        String[] ins_name = getResources().getStringArray(R.array.ins_name);
//                                                                        String[] ins_address = getResources().getStringArray(R.array.ins_address);
//                                                                        String[] ins_phoneno = getResources().getStringArray(R.array.ins_phoneno);
//                                                                        String[] ins_email = getResources().getStringArray(R.array.ins_email);
//                                                                        String[] ins_website = getResources().getStringArray(R.array.ins_website);
//
//                                                                        switch (selecteddistrict) {
////                                Andaman Nicobar state
//                                                                            case "Nicobar":
//
//                                                                            case "North Middle Andaman":
//
//                                                                            case "South Andaman":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 0, 1);
//                                                                                i_address = Arrays.copyOfRange(ins_address, 0, 1);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 0, 1);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 0, 1);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 0, 1);
//
//                                                                                contactAdapter nicobar = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(nicobar);
//                                                                                break;
//
//
////                                Andhra pradesh state
//                                                                            case "Anantapur":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 1, 2);
//                                                                                i_address = Arrays.copyOfRange(ins_address, 1, 2);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 1, 2);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 1, 2);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 1, 2);
//
//                                                                                contactAdapter anantapur = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(anantapur);
//                                                                                break;
//
//                                                                            case "Chittoor":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 2, 3);
//                                                                                i_address = Arrays.copyOfRange(ins_address, 2, 3);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 2, 3);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 2, 3);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 2, 3);
//
//                                                                                contactAdapter chittoor = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(chittoor);
//                                                                                break;
//
//                                                                            case "East Godavari":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 3, 4);
//                                                                                i_address = Arrays.copyOfRange(ins_address, 3, 4);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 3, 4);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 3, 4);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 3, 4);
//
//                                                                                contactAdapter godavari = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(godavari);
//                                                                                break;
//
//                                                                            case "Guntur":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 4, 5); // 4,7
//                                                                                i_address = Arrays.copyOfRange(ins_address, 4, 5);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 4, 5);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 4, 5);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 4, 5);
//
//                                                                                contactAdapter guntur = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(guntur);
//                                                                                break;
//
//                                                                            case "Kadapa":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 7, 8); //7,9
//                                                                                i_address = Arrays.copyOfRange(ins_address, 7, 8);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 7, 8);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 7, 8);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 7, 8);
//
//                                                                                contactAdapter kadapa = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(kadapa);
//                                                                                break;
//
//                                                                            case "Krishna":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 9, 10);
//                                                                                i_address = Arrays.copyOfRange(ins_address, 9, 10);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 9, 10);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 9, 10);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 9, 10);
//
//                                                                                contactAdapter krishna = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(krishna);
//                                                                                break;
//
//                                                                            case "Kurnool":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 10, 11); // 10,12
//                                                                                i_address = Arrays.copyOfRange(ins_address, 10, 11);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 10, 11);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 10, 11);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 10, 11);
//
//                                                                                contactAdapter kurnool = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(kurnool);
//                                                                                break;
//
//                                                                            case "Nellore":
//                                                                                i_names = Arrays.copyOfRange(ins_name, 12, 13); // 12,14
//                                                                                i_address = Arrays.copyOfRange(ins_address, 12, 13);
//                                                                                i_phones = Arrays.copyOfRange(ins_phoneno, 12, 13);
//                                                                                i_emails = Arrays.copyOfRange(ins_email, 12, 13);
//                                                                                i_websites = Arrays.copyOfRange(ins_website, 12, 13);
//
//                                                                                contactAdapter nellore = new contactAdapter(orgn_trans.this, R.layout.contact_layout, i_names, i_address, i_phones, i_emails, i_websites);
//                                                                                contactlist.setAdapter(nellore);
//                                                                                break;
//
//                                                                        }

                                                            details = recipientid + "\n" + name + "\n" + address + "\n" + phone + "\n" + mail + "\n" + aadhar + "\n" + s_age;
                                                            if (ContextCompat.checkSelfPermission(orgn_trans.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                                                                sendMessage();

                                                            } else {
                                                                ActivityCompat.requestPermissions(orgn_trans.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                                                            }

                                                            //share data to Process
                                                            SharedPreferences sharedPref = getSharedPreferences("recipient", MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = sharedPref.edit();
                                                            editor.putString("did", recipientid);
                                                            editor.putString("name", name);
                                                            editor.putString("address", address);
                                                            editor.putString("district", selecteddistrict);
                                                            editor.putString("state", selectedstate);
                                                            editor.putString("phone", phone);
                                                            editor.putString("mail", mail);
                                                            editor.putString("aadhar", aadhar);
                                                            editor.putString("gender", selected_gender);
                                                            editor.putString("age", s_age);

//                                                                editor.putString("i_names", Arrays.toString(i_names));
//                                                                editor.putString("i_address", Arrays.toString(i_address));
//                                                                editor.putString("i_phones", Arrays.toString(i_phones));
//                                                                editor.putString("i_emails", Arrays.toString(i_emails));
//                                                                editor.putString("i_websites", Arrays.toString(i_websites));
                                                            editor.apply();
//                                                                    }
//                                                                }
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
                                    rec_aadhar.setError("Enter Valid Aadhar Number");
                                }
                            } else {
                                rec_mail.setError("Enter Valid Mail Address");
                            }
                        } else {
                            rec_phoneno.setError("Enter Valid Number");
                        }
                    } else {
                        rec_mail.setError("Mail-Address can't be Empty!");
                    }
                } else {
                    rec_phoneno.setError("Mobile Number can't be Empty!");
                }
            } else {
                rec_address.setError("Address can't be Empty!");
            }
        } else {
            rec_name.setError("Name can't be Empty!");
        }
    }

    private void sendMessage() {
        String  myphone = "6355850257"; // in place of i_phone, used myphone number

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