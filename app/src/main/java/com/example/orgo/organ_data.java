package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class organ_data extends AppCompatActivity {

    TextView stateview,districtview;
    Spinner indianstates,indiandistrict;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;
    String selectedstate, selecteddistrict;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference organdataref, storeref;

    Button nextbtn, changed;
    EditText bonevalue, marrowvalue, corneasvalue, heartvalue, intestinevalue,kidneyvalue, livervalue,lungvalue, middlevalue, pancreasvalue, skinvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_data);

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


        bonevalue = findViewById(R.id.bonevalue);
        marrowvalue = findViewById(R.id.marrowvalue);
        corneasvalue = findViewById(R.id.corneasvalue);
        heartvalue = findViewById(R.id.heartvalue);
        intestinevalue = findViewById(R.id.intestinevalue);
        kidneyvalue = findViewById(R.id.kidneyvalue);
        livervalue = findViewById(R.id.livervalue);
        lungvalue = findViewById(R.id.lungvalue);
        middlevalue = findViewById(R.id.middlevalue);
        pancreasvalue = findViewById(R.id.pancreasvalue);
        skinvalue = findViewById(R.id.skinvalue);
        nextbtn = findViewById(R.id.nextbtn);
        changed = findViewById(R.id.changed);
        
        firebaseDatabase = FirebaseDatabase.getInstance();
        organdataref = firebaseDatabase.getReference("organdata");



        nextbtn.setOnClickListener(v -> {

            Query check_district = organdataref.child(selectedstate).orderByChild("name").equalTo(selecteddistrict);
            check_district.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String bone = snapshot.child(selecteddistrict).child("Bone").getValue(String.class);
                        bonevalue.setText(bone);
                        String marrow = snapshot.child(selecteddistrict).child("Bone marrow").getValue(String.class);
                        marrowvalue.setText(marrow);
                        String corneas = snapshot.child(selecteddistrict).child("Corneas").getValue(String.class);
                        corneasvalue.setText(corneas);
                        String heart = snapshot.child(selecteddistrict).child("Heart").getValue(String.class);
                        heartvalue.setText(heart);
                        String intestine = snapshot.child(selecteddistrict).child("Intestine").getValue(String.class);
                        intestinevalue.setText(intestine);
                        String kidney = snapshot.child(selecteddistrict).child("Kidney").getValue(String.class);
                        kidneyvalue.setText(kidney);
                        String liver = snapshot.child(selecteddistrict).child("Liver").getValue(String.class);
                        livervalue.setText(liver);
                        String lung = snapshot.child(selecteddistrict).child("Lung").getValue(String.class);
                        lungvalue.setText(lung);
                        String middle = snapshot.child(selecteddistrict).child("Middle ear").getValue(String.class);
                        middlevalue.setText(middle);
                        String pancreas = snapshot.child(selecteddistrict).child("Pancreas").getValue(String.class);
                        pancreasvalue.setText(pancreas);
                        String skin = snapshot.child(selecteddistrict).child("Skin").getValue(String.class);
                        skinvalue.setText(skin);

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
           storeref = organdataref.child(selectedstate).child(selecteddistrict);

            String bone = bonevalue.getText().toString().trim();
            String marow = marrowvalue.getText().toString().trim();
            String corneas = corneasvalue.getText().toString().trim();
            String heart = heartvalue.getText().toString().trim();
            String intestine = intestinevalue.getText().toString().trim();
            String kidney = kidneyvalue.getText().toString().trim();
            String liver = livervalue.getText().toString().trim();
            String lung = lungvalue.getText().toString().trim();
            String middle = middlevalue.getText().toString().trim();
            String pancreas = pancreasvalue.getText().toString().trim();
            String skin = skinvalue.getText().toString().trim();

            if (!bone.isEmpty() && !marow.isEmpty() && !corneas.isEmpty() && !heart.isEmpty() && !intestine.isEmpty() && !kidney.isEmpty() && !liver.isEmpty() && !lung.isEmpty() && !middle.isEmpty() && !pancreas.isEmpty() && !skin.isEmpty()){
                storeref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        storeref.child("Bone").setValue(bone);
                        storeref.child("Bone marrow").setValue(marow);
                        storeref.child("Corneas").setValue(corneas);
                        storeref.child("Heart").setValue(heart);
                        storeref.child("Intestine").setValue(intestine);
                        storeref.child("Kidney").setValue(kidney);
                        storeref.child("Liver").setValue(liver);
                        storeref.child("Lung").setValue(lung);
                        storeref.child("Middle ear").setValue(middle);
                        storeref.child("Pancreas").setValue(pancreas);
                        storeref.child("Skin").setValue(skin);

                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Eroor : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }else{
                Toast.makeText(organ_data.this, "Please fill all boxes!", Toast.LENGTH_SHORT).show();
            }


        });



    }
}