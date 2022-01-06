package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class forgot_pwd extends AppCompatActivity {
    Button submit_btn;
    EditText txt_name;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;


    LinearLayout input_linear, otp_linear;
    Button otp_submit_btn;
    EditText numone, numtwo, numthree, numfour, numfive, numsix;
    TextView userno, resend_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        input_linear = findViewById(R.id.inputlinear);
        otp_linear = findViewById(R.id.otplinear);
        submit_btn = findViewById(R.id.submitbtn);
        txt_name = findViewById(R.id.usernamedata);

        numone = findViewById(R.id.num1);
        numtwo = findViewById(R.id.num2);
        numthree = findViewById(R.id.num3);
        numfour = findViewById(R.id.num4);
        numfive = findViewById(R.id.num5);
        numsix = findViewById(R.id.num6);


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
                                                                              otp_linear.setVisibility(View.VISIBLE);
                                                                              input_linear.setVisibility(View.INVISIBLE);

                                                                                databaseReference.child(usernamedata).addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                                       String phonenumber = String.valueOf(snapshot.child("number").getValue());
                                                                                        Toast.makeText(forgot_pwd.this, "Wait for 10 SECONDS", Toast.LENGTH_SHORT).show();

                                                                                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                                                                                "+91" + phonenumber,
                                                                                                60,
                                                                                                TimeUnit.SECONDS,
                                                                                                forgot_pwd.this,
                                                                                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                                                                                    @Override
                                                                                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                                                                                        Toast.makeText(forgot_pwd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                                                                        //  after clicking submit
                                                                                                        // Toast.makeText(forgot_pwd.this, "You compeleted half process", Toast.LENGTH_SHORT).show();
                                                                                                        otp_submit_btn = findViewById(R.id.otpsubmitbtn);

                                                                                                        resend_otp = findViewById(R.id.resendbtn);

                                                                                                        userno = findViewById(R.id.userdata2);
                                                                                                        userno.setText(String.format(
                                                                                                                "+91-%s", String.valueOf(snapshot.child("number").getValue())
                                                                                                        ));

                                                                                                        otp_submit_btn.setOnClickListener(new View.OnClickListener() {
                                                                                                            @Override
                                                                                                            public void onClick(View v) {
                                                                                                                String one = numone.getText().toString();
                                                                                                                String two = numtwo.getText().toString();
                                                                                                                String three = numthree.getText().toString();
                                                                                                                String four = numfour.getText().toString();
                                                                                                                String five = numfive.getText().toString();
                                                                                                                String six = numsix.getText().toString();

                                                                                                                if (!one.isEmpty() || !two.isEmpty() || !three.isEmpty() || !four.isEmpty() || !five.isEmpty() || !six.isEmpty()) {

                                                                                                                    String enteredotp = numone.getText().toString() + numtwo.getText().toString() + numthree.getText().toString() + numfour.getText().toString() + numfive.getText().toString() + numsix.getText().toString();

                                                                                                                    if (backendotp!=null){
                                                                                                                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                                                                                                                backendotp, enteredotp
                                                                                                                        );
                                                                                                                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                                                if(task.isSuccessful()){
                                                                                                                                    Toast.makeText(forgot_pwd.this,"Login Successfully!, Change Your Password in Profile section.",Toast.LENGTH_SHORT).show();
                                                                                                                                    Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                                                                                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                                                                                    startActivity(intent);
                                                                                                                                }else{
                                                                                                                                    Toast.makeText(forgot_pwd.this, "Please enter Correct OTP!", Toast.LENGTH_SHORT).show();
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });
                                                                                                                    }else{
                                                                                                                        Toast.makeText(forgot_pwd.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                                                                                                                    }

                                                                                                                } else {
                                                                                                                    Toast.makeText(forgot_pwd.this, "Please Enter 6 Digit OTP!", Toast.LENGTH_SHORT).show();
                                                                                                                }

                                                                                                            }

                                                                                                        });
//                                                                                                       resend_otp.setOnClickListener(new View.OnClickListener() {
//                                                                                                            @Override
//                                                                                                            public void onClick(View v) {
//                                                                                                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                                                                                                        "+91" + getIntent().getStringExtra("mobile"),
//                                                                                                                        60,
//                                                                                                                        TimeUnit.SECONDS,
//                                                                                                                        forgot_pwd.this,
//                                                                                                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                                                                                                            @Override
//                                                                                                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                                                                                                            }
//
//                                                                                                                            @Override
//                                                                                                                            public void onVerificationFailed(@NonNull FirebaseException e) {
//                                                                                                                                Toast.makeText(forgot_pwd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                                                                                                            }
//
//                                                                                                                            @Override
//                                                                                                                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                                                                                                                backendotp = newbackendotp;
//                                                                                                                                Toast.makeText(forgot_pwd.this, "OTP Sent Successful", Toast.LENGTH_SHORT).show();
//
//                                                                                                                            }
//                                                                                                                        }
//                                                                                                                );
//                                                                                                            }
//                                                                                                        });

                                                                                                    }
                                                                                                }
                                                                                        );

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

        cursermoving();
    }
    private void cursermoving() {
        numone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    numtwo.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numtwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    numthree.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numthree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    numfour.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numfour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    numfive.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numfive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    numsix.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}