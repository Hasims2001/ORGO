package com.example.adminportal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class institute extends AppCompatActivity {
    TextView stateview,districtview;
    Spinner indianstates,indiandistrict;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;
    String selectedstate, selecteddistrict;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference institutedata, storeref;

    Button nextbtn, changed;

    EditText insname, insaddress, insphone, insemail, inswebsite, drnameone, drnametwo, drnamethree, drphoneone, drphonetwo, drphonethree, dremailone, dremailtwo, dremailthree;
    String ins_name, ins_address, ins_phone, ins_email, ins_website, per_nameone, per_nametwo, per_namethree, per_phoneone, per_phonetwo, per_phonethree, per_emailone, per_emailtwo, per_emailthree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute);
        insname = findViewById(R.id.insname);
        insaddress = findViewById(R.id.insaddress);
        insphone = findViewById(R.id.insphone);
        insemail = findViewById(R.id.insemail);
        inswebsite = findViewById(R.id.inswebsite);
        drnameone = findViewById(R.id.drnameone);
        drnametwo = findViewById(R.id.drnametwo);
        drnamethree = findViewById(R.id.drnamethree);
        drphoneone = findViewById(R.id.drphoneone);
        drphonetwo = findViewById(R.id.drphonetwo);
        drphonethree = findViewById(R.id.drphonethree);
        dremailone = findViewById(R.id.dremailone);
        dremailtwo = findViewById(R.id.dremailtwo);
        dremailthree = findViewById(R.id.dremailthree);
        nextbtn = findViewById(R.id.nextbtn);
        changed = findViewById(R.id.changed);

        firebaseDatabase = FirebaseDatabase.getInstance();
        institutedata = firebaseDatabase.getReference("Institute");

        stateview = findViewById(R.id.state_view);
        districtview = findViewById(R.id.district_view);

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


        nextbtn.setOnClickListener(v -> {

            Query check_district = institutedata.child(selectedstate).orderByChild("name").equalTo(selecteddistrict);
            check_district.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
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


                        per_nameone= snapshot.child(selecteddistrict).child("per_name_one").getValue(String.class);
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
                    Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        });

        changed.setOnClickListener(v -> {
            storeref = institutedata.child(selectedstate).child(selecteddistrict);

            String i_name = insname.getText().toString().trim();
            String i_address = insaddress.getText().toString().trim();
            String i_phone = insphone.getText().toString().trim();
            String i_email = insemail.getText().toString().trim();
            String i_website = inswebsite.getText().toString().trim();
            String drone_name = drnameone.getText().toString().trim();
            String drtwo_name = drnametwo.getText().toString().trim();
            String drthree_name = drnamethree.getText().toString().trim();
            String drone_phone = drphoneone.getText().toString().trim();
            String drtwo_phone = drphonetwo.getText().toString().trim();
            String drthree_phone = drphonethree.getText().toString().trim();
            String drone_email = dremailone.getText().toString().trim();
            String drtwo_email = dremailtwo.getText().toString().trim();
            String drthree_email = dremailthree.getText().toString().trim();

            if (!i_name.isEmpty() && !i_address.isEmpty() && !i_phone.isEmpty() && !i_email.isEmpty() && !i_website.isEmpty() && !drone_name.isEmpty() && !drtwo_name.isEmpty() && !drthree_name.isEmpty() && !drone_phone.isEmpty() && !drtwo_phone.isEmpty() && !drthree_phone.isEmpty() && !drone_email.isEmpty() && !drtwo_email.isEmpty() && !drthree_email.isEmpty()  ){
                storeref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        storeref.child("ins_name").setValue(i_name);
                        storeref.child("ins_address").setValue(i_address);
                        storeref.child("ins_phoneno").setValue(i_phone);
                        storeref.child("ins_email").setValue(i_email);
                        storeref.child("ins_website").setValue(i_website);

                        storeref.child("per_name_one").setValue(drone_name);
                        storeref.child("per_name_two").setValue(drtwo_name);
                        storeref.child("per_name_three").setValue(drthree_name);

                        storeref.child("per_phone_one").setValue(drone_phone);
                        storeref.child("per_phone_two").setValue(drtwo_phone);
                        storeref.child("per_phone_three").setValue(drthree_phone);

                        storeref.child("per_email_one").setValue(drone_email);
                        storeref.child("per_email_two").setValue(drtwo_email);
                        storeref.child("per_email_three").setValue(drthree_email);

                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Eroor : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }else{
                Toast.makeText(institute.this, "Please fill all boxes!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}