package com.example.adminportal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class recipient_info extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference paths, delparent;

    ListView recipientlist;
    String[] str_useraadhar,str_username;
    TableLayout recipient_details;
    Button updateit;
    EditText input_recipientid, input_aadhar, input_name, input_phone, input_mail, input_address,input_age, input_username, input_checkup,inputSearch;
    @Nullable
    ArrayList<String> useraadhar = new ArrayList<>();
    @Nullable ArrayList<String> username = new ArrayList<>();
    String clicked_user;
    getdataAdapter ria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_info);
        firebaseDatabase = FirebaseDatabase.getInstance();
        paths = firebaseDatabase.getReference("recipient_info");
        recipient_details = findViewById(R.id.recipient_details);
        updateit = findViewById(R.id.updateit);
        input_recipientid = findViewById(R.id.input_recipientid);
        input_aadhar = findViewById(R.id.input_aadhar);
        input_name = findViewById(R.id.input_name);
        input_phone = findViewById(R.id.input_ph);
        input_mail = findViewById(R.id.input_mail);
        input_address = findViewById(R.id.input_address);
        input_age = findViewById(R.id.input_age);
        input_username = findViewById(R.id.input_username);
        input_checkup = findViewById(R.id.input_checkup);
        recipientlist = findViewById(R.id.recipientlist);

        clicked_user = getIntent().getStringExtra("clicked");

        paths.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (clicked_user != null && !clicked_user.isEmpty()) {

                    Query check_user = paths.orderByChild("aadhar").equalTo(clicked_user);
                    check_user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                recipient_details.setVisibility(View.VISIBLE);
                                updateit.setVisibility(View.VISIBLE);
                                inputSearch.setVisibility(View.INVISIBLE);
                                delparent = paths.child(clicked_user);

                                for (DataSnapshot getdata : snapshot.getChildren()) {
                                    input_recipientid.setText(getdata.child("donorid").getValue(String.class));
                                    input_aadhar.setText(getdata.child("aadhar").getValue(String.class));
                                    input_name.setText(getdata.child("name").getValue(String.class));
                                    input_phone.setText(getdata.child("phone").getValue(String.class));
                                    input_mail.setText(getdata.child("mail").getValue(String.class));
                                    input_address.setText(getdata.child("address").getValue(String.class));
                                    input_age.setText(getdata.child("s_age").getValue(String.class));
                                    input_username.setText(getdata.child("username").getValue(String.class));
                                    input_checkup.setText(getdata.child("medical_checkup").getValue(String.class));

                                }

                                updateit.setOnClickListener(v -> {
                                    infochecking();
                                });


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    recipient_details.setVisibility(View.INVISIBLE);
                    updateit.setVisibility(View.INVISIBLE);
                    inputSearch.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {

                        assert useraadhar != null;
                        useraadhar.add(snapshot.child("aadhar").getValue(String.class));
                        assert username != null;
                        username.add(snapshot.child("name").getValue(String.class));

                    }
                    str_useraadhar = new String[useraadhar.size()];
                    for (int i = 0; i < useraadhar.size(); i++) {
                        str_useraadhar[i] = useraadhar.get(i);
                    }
                    str_username = new String[username.size()];
                    for (int i = 0; i < username.size(); i++) {
                        str_username[i] = username.get(i);

                    }
                    ria = new getdataAdapter(recipient_info.this, R.layout.data_layout, str_useraadhar, str_username);
                    recipientlist.setAdapter(ria);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        inputSearch = findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                recipient_info.this.ria.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });


    }


    private void infochecking() {
        String id = input_recipientid.getText().toString().trim();
        String aadhar = input_aadhar.getText().toString().trim();
        String name = input_name.getText().toString().trim();
        String phone = input_phone.getText().toString().trim();
        String mail = input_mail.getText().toString().trim();
        String address = input_address.getText().toString().trim();
        String age = input_age.getText().toString().trim();
        String username = input_username.getText().toString().trim();
        String checkup = input_checkup.getText().toString().trim();


        if (!id.isEmpty() && !aadhar.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !address.isEmpty() && !age.isEmpty()  && !username.isEmpty()  && !checkup.isEmpty() ){
            Query name_check =  paths.orderByChild("aadhar").equalTo(aadhar);
            name_check.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
//                       Toast.makeText(getApplicationContext(), "Username already Exists" , Toast.LENGTH_SHORT).show();
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {
                                paths.child(aadhar).child("donorid").setValue(id);
                                paths.child(aadhar).child("name").setValue(name);
                                paths.child(aadhar).child("phone").setValue(phone);
                                paths.child(aadhar).child("mail").setValue(mail);
                                paths.child(aadhar).child("address").setValue(address);
                                paths.child(aadhar).child("s_age").setValue(age);
                                paths.child(aadhar).child("username").setValue(username);
                                paths.child(aadhar).child("medical_checkup").setValue(checkup);

                                Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(getApplicationContext(), "Check Mail Address!", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Check Phone Number!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {

                                paths.child(aadhar).child("donorid").setValue(id);
                                paths.child(aadhar).child("aadhar").setValue(aadhar);
                                paths.child(aadhar).child("name").setValue(name);
                                paths.child(aadhar).child("phone").setValue(phone);
                                paths.child(aadhar).child("mail").setValue(mail);
                                paths.child(aadhar).child("address").setValue(address);
                                paths.child(aadhar).child("s_age").setValue(age);
                                paths.child(aadhar).child("username").setValue(username);
                                paths.child(aadhar).child("medical_checkup").setValue(checkup);

                                delparent.removeValue();

                                Toast.makeText(getApplicationContext(), "updated Sucessful!", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(getApplicationContext(), "Check Mail Address!", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Check Phone Number!", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "Please Fill all Boxes!", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), admindashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}