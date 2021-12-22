package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class sign_up extends AppCompatActivity {
    Button submit_btn;
    EditText txt_name, txt_phone, txt_mail, txt_pwd, txt_conpwd;
    TextView txt_login;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        submit_btn = findViewById(R.id.submitbtn);
        txt_name = findViewById(R.id.username);
        txt_phone = findViewById(R.id.userphone);
        txt_mail = findViewById(R.id.usernamedata);
        txt_pwd = findViewById(R.id.password);
        txt_conpwd = findViewById(R.id.con_pwd);
        txt_login = findViewById(R.id.Login);

        submit_btn.setOnClickListener(v -> {
            String name = txt_name.getText().toString();
            String phone = txt_phone.getText().toString();
            String mail = txt_mail.getText().toString();
            String pwd = txt_pwd.getText().toString();
            String conpwd = txt_conpwd.getText().toString();

            if (!name.isEmpty()) {
                txt_name.setError(null);
                if (!phone.isEmpty()) {
                    txt_phone.setError(null);
                    if (!mail.isEmpty()) {
                        txt_mail.setError(null);
                        if (!pwd.isEmpty()) {
                            txt_pwd.setError(null);
                            if (!conpwd.isEmpty()) {
                                txt_conpwd.setError(null);
                                if (phone.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
                                    txt_phone.setError(null);
                                    if (mail.matches("^(.+)@(.+)$")) {
                                        txt_mail.setError(null);

                                        String usernamedata = txt_name.getText().toString();

                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        reference = firebaseDatabase.getReference("userdata");

                                        Query check_usermail = reference.orderByChild("name").equalTo(usernamedata);

                                        check_usermail.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()){
                                                    txt_name.setError("This Usernmame is already exists,Try to login");

                                                }else{
                                                    txt_name.setError(null);


                                                    if (pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
                                                        txt_pwd.setError(null);
                                                        if (conpwd.equals(pwd)) {
                                                            firebaseDatabase = FirebaseDatabase.getInstance();
                                                            reference = firebaseDatabase.getReference("userdata");

                                                            String name_store = txt_name.getText().toString();
                                                            String phone_store = txt_phone.getText().toString();
                                                            String mail_store = txt_mail.getText().toString();
                                                            String pwd_store = txt_pwd.getText().toString();
                                                            String conpwd_store = txt_conpwd.getText().toString();

                                                            storingdata storingdataobj = new storingdata(name_store, phone_store, mail_store, pwd_store, conpwd_store);

                                                            reference.child(name_store).setValue(storingdataobj);

                                                            Toast.makeText(getApplicationContext(),"Register Successfully!",Toast.LENGTH_SHORT).show();

                                                            Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                                            startActivity(intent);
                                                            finish();



                                                        } else {
                                                            txt_conpwd.setError("Password doesn't Match! Try Again...");
                                                        }
                                                    } else {
                                                        txt_pwd.setError("Try Different Password");
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });


                                    } else {
                                        txt_mail.setError("Invalid Email");
                                    }
                                }else{
                                    txt_phone.setError("Please Enter Valid Mobile Number");
                                }
                            } else {
                                txt_conpwd.setError("Please Enter Your Confirm Password");
                            }
                        } else {
                            txt_pwd.setError("Please Enter Your Password");
                        }
                    } else {
                        txt_mail.setError("Please Enter Your Mail Address ");
                    }
                } else {
                    txt_phone.setError("Please Enter Your Phone Number");
                }
            } else {
                txt_name.setError("Please Enter Your Full Name");
            }

        });
        txt_login.setOnClickListener(v -> startActivity(new Intent(sign_up.this, MainActivity.class)));


    }




}