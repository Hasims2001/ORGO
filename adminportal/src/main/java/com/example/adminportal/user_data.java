package com.example.adminportal;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class user_data extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference paths, delparent;

   ListView userlist;
    String[] str_username,str_usermail;
    TableLayout user_details;
    LinearLayout btns;
    Button updateit,  del, createbtn;;
    EditText input_name, input_phone, input_mail, input_pwd,input_conpwd, inputSearch;
  @Nullable ArrayList<String> username = new ArrayList<>();
  @Nullable ArrayList<String> usermail = new ArrayList<>();
    String clicked_user, user;
    getdataAdapter uda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        SharedPreferences sh = getSharedPreferences("AdminName", MODE_PRIVATE);
        user = sh.getString("user_name", "");

        firebaseDatabase = FirebaseDatabase.getInstance();
        paths = firebaseDatabase.getReference("userdata");
        user_details = findViewById(R.id.user_details);
        updateit = findViewById(R.id.updateit);
        input_name = findViewById(R.id.input_name);
        input_phone = findViewById(R.id.input_ph);
        input_mail = findViewById(R.id.input_mail);
        input_pwd = findViewById(R.id.input_pwd);
        input_conpwd = findViewById(R.id.input_conpwd);
        userlist = findViewById(R.id.userlist);
        del = findViewById(R.id.del);
        createbtn = findViewById(R.id.createbtn);
        btns = findViewById(R.id.btns);
        inputSearch = findViewById(R.id.inputSearch);

        user_details.setVisibility(View.GONE);
        inputSearch.setVisibility(View.VISIBLE);
        userlist.setVisibility(View.VISIBLE);
        btns.setVisibility(View.GONE);

        createbtn.setOnClickListener(v -> {
            user_details.setVisibility(View.VISIBLE);
            inputSearch.setVisibility(View.GONE);
            userlist.setVisibility(View.GONE);
            btns.setVisibility(View.VISIBLE);

            updateit.setOnClickListener(v1 -> infoupdate());

            del.setOnClickListener(v12 -> Toast.makeText(getApplicationContext(), "You are creating new user!", Toast.LENGTH_SHORT).show());
        });
        clicked_user = getIntent().getStringExtra("clicked");
//         if (clicked_user != null && !clicked_user.isEmpty()) {
            paths.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    if (clicked_user != null && !clicked_user.isEmpty()) {

                    Query check_user = paths.orderByChild("name").equalTo(clicked_user);
                    check_user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                user_details.setVisibility(View.VISIBLE);
                                btns.setVisibility(View.VISIBLE);
                                inputSearch.setVisibility(View.GONE);
                                createbtn.setVisibility(View.GONE);
                                delparent = paths.child(clicked_user);

                                for (DataSnapshot getdata : snapshot.getChildren()) {
                                    input_name.setText(getdata.child("name").getValue(String.class));
                                    input_phone.setText(getdata.child("number").getValue(String.class));
                                    input_mail.setText(getdata.child("mail").getValue(String.class));
                                    input_pwd.setText(getdata.child("pwd").getValue(String.class));
                                    input_conpwd.setText(getdata.child("conpwd").getValue(String.class));


                                }

                                updateit.setOnClickListener(v -> {
                                    infochecking();
                                });

                                del.setOnClickListener(v1 -> { deleting();});


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    }else{
                        user_details.setVisibility(View.GONE);
                        btns.setVisibility(View.GONE);
                        inputSearch.setVisibility(View.VISIBLE);
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {

                            assert username != null;
                            username.add(snapshot.child("name").getValue(String.class));
                            assert usermail != null;
                            usermail.add(snapshot.child("mail").getValue(String.class));

                        }
                        str_username = new String[username.size()];
                        for (int i = 0; i < username.size(); i++) {
                            str_username[i] = username.get(i);
                        }
                        str_usermail = new String[usermail.size()];
                        for (int i = 0; i < usermail.size(); i++) {
                            str_usermail[i] = usermail.get(i);

                        }
                        uda = new getdataAdapter(user_data.this, R.layout.data_layout, str_username, str_usermail);
                        userlist.setAdapter(uda);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
//        }else{
//            Query check_user = paths.orderByChild("name").equalTo(clicked_user);
//            check_user.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()){
//                        Toast.makeText(getApplicationContext(), "User Exists", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Userdosn't Exists", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }

        inputSearch = findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                user_data.this.uda.getFilter().filter(cs);
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

    private void infoupdate() {
        String name = input_name.getText().toString().trim();
        String phone = input_phone.getText().toString().trim();
        String mail = input_mail.getText().toString().trim();
        String pwd = input_pwd.getText().toString().trim();
        String conpwd = input_conpwd.getText().toString().trim();


        if (!name.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !pwd.isEmpty() && !conpwd.isEmpty()){
            Query name_check =  paths.orderByChild("name").equalTo(name);
            name_check.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
//                       Toast.makeText(getApplicationContext(), "Username already Exists" , Toast.LENGTH_SHORT).show();
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {
                                if (pwd.equals(conpwd)){
                                    paths.child(name).child("number").setValue(phone);
                                    paths.child(name).child("mail").setValue(mail);
                                    paths.child(name).child("pwd").setValue(pwd);
                                    paths.child(name).child("conpwd").setValue(conpwd);
                                    paths.child(name).child("Attempted By").setValue(user);
                                    Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Password is not match!", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Check Mail Address!", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Check Phone Number!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                            if (mail.matches("^(.+)@(.+)$")) {
                                if (pwd.equals(conpwd)){
                                    paths.child(name).child("name").setValue(name);
                                    paths.child(name).child("number").setValue(phone);
                                    paths.child(name).child("mail").setValue(mail);
                                    paths.child(name).child("pwd").setValue(pwd);
                                    paths.child(name).child("conpwd").setValue(conpwd);
                                    paths.child(name).child("Attempted By").setValue(user);

                                    Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Password is not match!", Toast.LENGTH_LONG).show();
                                }
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


    private void infochecking() {
        String name = input_name.getText().toString().trim();
        String phone = input_phone.getText().toString().trim();
        String mail = input_mail.getText().toString().trim();
        String pwd = input_pwd.getText().toString().trim();
        String conpwd = input_conpwd.getText().toString().trim();


        if (!name.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !pwd.isEmpty() && !conpwd.isEmpty()){
           Query name_check =  paths.orderByChild("name").equalTo(name);
           name_check.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if (snapshot.exists()){
//                       Toast.makeText(getApplicationContext(), "Username already Exists" , Toast.LENGTH_SHORT).show();
                       if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                           if (mail.matches("^(.+)@(.+)$")) {
                               if (pwd.equals(conpwd)){
                                   paths.child(name).child("number").setValue(phone);
                                   paths.child(name).child("mail").setValue(mail);
                                   paths.child(name).child("pwd").setValue(pwd);
                                   paths.child(name).child("conpwd").setValue(conpwd);

                                   Toast.makeText(getApplicationContext(), "updated Successfully!", Toast.LENGTH_SHORT).show();
                               }else{
                                   Toast.makeText(getApplicationContext(), "Password is not match!", Toast.LENGTH_LONG).show();
                               }
                           }else{
                               Toast.makeText(getApplicationContext(), "Check Mail Address!", Toast.LENGTH_LONG).show();
                           }

                       }else{
                           Toast.makeText(getApplicationContext(), "Check Phone Number!", Toast.LENGTH_LONG).show();
                       }
                   }else{
                       if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                           if (mail.matches("^(.+)@(.+)$")) {
                               if (pwd.equals(conpwd)){
                                   paths.child(name).child("name").setValue(name);
                                   paths.child(name).child("number").setValue(phone);
                                   paths.child(name).child("mail").setValue(mail);
                                   paths.child(name).child("pwd").setValue(pwd);
                                   paths.child(name).child("conpwd").setValue(conpwd);

                                   delparent.removeValue();

                                   Toast.makeText(getApplicationContext(), "updated Sucessful!", Toast.LENGTH_SHORT).show();
                               }else{
                                   Toast.makeText(getApplicationContext(), "Password is not match!", Toast.LENGTH_LONG).show();
                               }
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
      Intent  intent = new Intent(getApplicationContext(), admindashboard.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
    }

}