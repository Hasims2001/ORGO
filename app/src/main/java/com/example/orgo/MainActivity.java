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

public class MainActivity extends AppCompatActivity {
    Button submit_btn;
    EditText txt_name, txt_pwd;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        submit_btn = (Button) findViewById(R.id.submitbtn);
        txt_name = (EditText) findViewById(R.id.usernamedata);
        txt_pwd = (EditText) findViewById(R.id.password);

        submit_btn.setOnClickListener(v -> {
            String username = txt_name.getText().toString();
            String pwd = txt_pwd.getText().toString();

            if (!username.isEmpty()) {
                txt_name.setError(null);
                if (!pwd.isEmpty()) {
                    txt_pwd.setError(null);

                    final String usernamedata = txt_name.getText().toString();
                    final String userpwddata = txt_pwd.getText().toString();

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("userdata");


                    Query check_username = databaseReference.orderByChild("name").equalTo(usernamedata);

                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                    txt_name.setError(null);
                                    String pwdchecking = snapshot.child(usernamedata).child("pwd").getValue(String.class);
                                    if (pwdchecking.equals(userpwddata)){
                                        txt_pwd.setError(null);
                                        Toast.makeText(getApplicationContext(),"Login Successfully!",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                        startActivity(intent);

                                        finish();


                                    }else{
                                        txt_pwd.setError("Wrong Password!");
                                    }

                            }else{
                                txt_name.setError("User does not exists");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    txt_pwd.setError("Please Enter Your Password");
                }

            }else{
                txt_name.setError("Please Enter Your Mail Address");
            }
        });



        TextView signup_btn = (TextView) findViewById(R.id.signupbtn);
        TextView forgot_password = (TextView)findViewById(R.id.forgotpwd);

        signup_btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, sign_up.class)));

        forgot_password.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, forgot_pwd.class)));

    }


}