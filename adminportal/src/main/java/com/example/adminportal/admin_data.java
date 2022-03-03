package com.example.adminportal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class admin_data extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference paths, delparent;

    ListView adminlist;
    String[] str_username,str_usernumber;
    TableLayout admin_table;
    Button updateit, del, createbtn;
    EditText input_name, input_phone, input_mail, input_pwd, inputSearch;
    @Nullable
    ArrayList<String> username = new ArrayList<>();
    @Nullable ArrayList<String> usernumber = new ArrayList<>();
    String clicked_user;
    getdataAdapter ada;
    NestedScrollView scroll;
    String  user;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data);
        SharedPreferences sh = getSharedPreferences("AdminName", MODE_PRIVATE);
        user = sh.getString("user_name", "");

        adminlist = findViewById(R.id.adminlist);
        input_name = findViewById(R.id.input_name);
        input_phone = findViewById(R.id.input_ph);
        input_pwd = findViewById(R.id.input_pwd);
        input_mail = findViewById(R.id.input_mail);
        admin_table = findViewById(R.id.admin_table);
        updateit = findViewById(R.id.updateit);
        inputSearch =findViewById(R.id.inputSearch);
        scroll = findViewById(R.id.scoll);
        del = findViewById(R.id.del);
        createbtn = findViewById(R.id.createbtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        paths = firebaseDatabase.getReference("admindata");


        clicked_user = getIntent().getStringExtra("clicked");
        scroll.setVisibility(View.GONE);
        inputSearch.setVisibility(View.VISIBLE);
        adminlist.setVisibility(View.VISIBLE);

        createbtn.setOnClickListener(v -> {
            scroll.setVisibility(View.VISIBLE);
            inputSearch.setVisibility(View.GONE);
            adminlist.setVisibility(View.GONE);


            updateit.setOnClickListener(v1 -> infoupdate());

            del.setOnClickListener(v12 -> Toast.makeText(getApplicationContext(), "You are creating new user!", Toast.LENGTH_SHORT).show());
        });
        paths.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (clicked_user != null && !clicked_user.isEmpty()) {

                    Query check_user = paths.orderByChild("name").equalTo(clicked_user);
                    check_user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                scroll.setVisibility(View.VISIBLE);
                                inputSearch.setVisibility(View.GONE);
                                adminlist.setVisibility(View.GONE);
                                delparent = paths.child(clicked_user);
                                createbtn.setVisibility(View.GONE);
                                for (DataSnapshot getdata : snapshot.getChildren()) {

                                    input_name.setText(getdata.child("name").getValue(String.class));
                                    input_phone.setText(getdata.child("phone").getValue(String.class));
                                    input_mail.setText(getdata.child("mail").getValue(String.class));
                                    input_pwd.setText(getdata.child("pwd").getValue(String.class));


                                }

                                updateit.setOnClickListener(v -> infochecking());

                                del.setOnClickListener(v -> deleting());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    createbtn.setVisibility(View.VISIBLE);
                    scroll.setVisibility(View.GONE);
                    adminlist.setVisibility(View.VISIBLE);
                    inputSearch.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {

                        assert username != null;
                        username.add(snapshot.child("name").getValue(String.class));
                        assert usernumber != null;
                        usernumber.add(snapshot.child("phone").getValue(String.class));

                    }
                    str_username = new String[username.size()];
                    for (int i = 0; i < username.size(); i++) {
                        str_username[i] = username.get(i);
                    }
                    str_usernumber = new String[usernumber.size()];
                    for (int i = 0; i < usernumber.size(); i++) {
                        str_usernumber[i] = usernumber.get(i);

                    }
                    ada = new getdataAdapter(admin_data.this, R.layout.data_layout, str_username, str_usernumber);
                    adminlist.setAdapter(ada);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                admin_data.this.ada.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });


    }

    private void deleting() {
        delparent.removeValue();
        Toast.makeText(getApplicationContext(), "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    //for new admin
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void infoupdate() {

        String name = input_name.getText().toString().trim();
        String phone = input_phone.getText().toString().trim();
        String mail = input_mail.getText().toString().trim();
        String pwd = input_pwd.getText().toString().trim();



        if (!name.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !pwd.isEmpty()){

            Query name_check =  paths.orderByChild("name").equalTo(name);
            name_check.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){


//                       Toast.makeText(getApplicationContext(), "Username already Exists" , Toast.LENGTH_SHORT).show();
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {
                                paths.child(name).child("phone").setValue(phone);
                                paths.child(name).child("mail").setValue(mail);
                                paths.child(name).child("pwd").setValue(pwd);
                                paths.child(name).child("Attempted By").setValue(user);

                                Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }else{
                                Toast.makeText(getApplicationContext(), "Check Mail Address!", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Check Phone Number!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {

                                paths.child(name).child("name").setValue(name);
                                paths.child(name).child("phone").setValue(phone);
                                paths.child(name).child("mail").setValue(mail);
                                paths.child(name).child("pwd").setValue(pwd);
                                paths.child(name).child("Attempted By").setValue(user);

                                Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                                onBackPressed();

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
    //for existting admin
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void infochecking() {
       
        String name = input_name.getText().toString().trim();
        String phone = input_phone.getText().toString().trim();
        String mail = input_mail.getText().toString().trim();
        String pwd = input_pwd.getText().toString().trim();
        


        if (!name.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !pwd.isEmpty()){

            Query name_check =  paths.orderByChild("name").equalTo(name);
            name_check.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){


//                       Toast.makeText(getApplicationContext(), "Username already Exists" , Toast.LENGTH_SHORT).show();
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {
                                paths.child(name).child("phone").setValue(phone);
                                paths.child(name).child("mail").setValue(mail);
                                paths.child(name).child("pwd").setValue(pwd);
                                paths.child(name).child("last Attempted By").setValue(user);

                                Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }else{
                                Toast.makeText(getApplicationContext(), "Check Mail Address!", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Check Phone Number!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {

                                paths.child(name).child("name").setValue(name);
                                paths.child(name).child("phone").setValue(phone);
                                paths.child(name).child("mail").setValue(mail);
                                paths.child(name).child("pwd").setValue(pwd);
                                paths.child(name).child("last Attempted By").setValue(user);

                                delparent.removeValue();

                                Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                                onBackPressed();

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