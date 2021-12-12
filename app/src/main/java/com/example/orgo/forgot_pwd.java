package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class forgot_pwd extends AppCompatActivity {
    Button submit_btn;
    EditText txt_name;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        submit_btn = findViewById(R.id.submitbtn);
        txt_name = findViewById(R.id.usernamedata);
        auth = FirebaseAuth.getInstance();

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_name.getText().toString();

                if(!username.isEmpty()){
                    final String usernamedata = txt_name.getText().toString();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("userdata");


                    Query check_username = databaseReference.orderByChild("name").equalTo(usernamedata);

                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                      @Override
                                                                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                          if (snapshot.exists()) {
                                                                              txt_name.setError(null);

                                                                                databaseReference.child(usernamedata).addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                        String emailvalue = String.valueOf(snapshot.child("mail").getValue());
                                                                                        Toast.makeText(forgot_pwd.this, emailvalue, Toast.LENGTH_SHORT).show();

                                                                                        auth.sendPasswordResetEmail(emailvalue).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                if(task.isSuccessful()){
                                                                                                  Toast.makeText(forgot_pwd.this, "Check your Mail inbox...", Toast.LENGTH_SHORT).show();
                                                                                                    startActivity(new Intent(forgot_pwd.this, MainActivity.class));
                                                                                                    finish();
                                                                                                }
                                                                                                else{

                                                                                                    Toast.makeText(forgot_pwd.this, "Error! " + task.getException(), Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            }
                                                                                        });

                                                                                    }

                                                                                    @Override
                                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                                    }
                                                                                });
//
                                                                          }else{
                                                                              txt_name.setError("User does not exists");
                                                                          }
                                                                      }

                                                                      @Override
                                                                      public void onCancelled(@NonNull DatabaseError error) {

                                                                      }
                                                                  });


                }else{
                    txt_name.setError("Please Enter Your Mail Address");
                }
            }
        });
        TextView signup_btn = findViewById(R.id.signupbtn);
        signup_btn.setOnClickListener(v -> startActivity(new Intent(forgot_pwd.this, sign_up.class)));


    }

}