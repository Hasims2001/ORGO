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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class verifyotp extends AppCompatActivity {

    Button submit_btn;
    EditText numone;
    EditText numtwo;
    EditText numthree;
    EditText numfour;
    EditText numfive;
    EditText numsix;

    TextView userno, resend_otp;
    String getotpbackend, name_store, phone_store, mail_store, pwd_store, conpwd_store;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);

        submit_btn = findViewById(R.id.otpsubmitbtn);
        numone = findViewById(R.id.num1);
        numtwo = findViewById(R.id.num2);
        numthree = findViewById(R.id.num3);
        numfour = findViewById(R.id.num4);
        numfive = findViewById(R.id.num5);
        numsix = findViewById(R.id.num6);

        resend_otp = findViewById(R.id.resendbtn);

      final ProgressBar prog = findViewById(R.id.progress);

        userno = findViewById(R.id.usernumber);
        userno.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getotpbackend = getIntent().getStringExtra("backendotp");
        name_store = getIntent().getStringExtra("name");
        phone_store = getIntent().getStringExtra("mobile");
        mail_store = getIntent().getStringExtra("mail");
        pwd_store = getIntent().getStringExtra("pwd");
        conpwd_store = getIntent().getStringExtra("conpwd");


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String one = numone.getText().toString();
                String two = numtwo.getText().toString();
                String three = numthree.getText().toString();
                String four = numfour.getText().toString();
                String five = numfive.getText().toString();
                String six = numsix.getText().toString();

                if (!one.isEmpty() || !two.isEmpty() || !three.isEmpty() || !four.isEmpty() || !five.isEmpty() || !six.isEmpty()) {

//                                        to check the otp is correct or not
                                        String enteredotp = numone.getText().toString() + numtwo.getText().toString() + numthree.getText().toString() + numfour.getText().toString() + numfive.getText().toString() + numsix.getText().toString();

                                        if (getotpbackend!=null){
                                            prog.setVisibility(View.VISIBLE);
                                            submit_btn.setVisibility(View.INVISIBLE);

                                            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                                    getotpbackend, enteredotp
                                            );
                                            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    prog.setVisibility(View.INVISIBLE);
                                                    submit_btn.setVisibility(View.VISIBLE);

                                                    if(task.isSuccessful()){

                                                            firebaseDatabase = FirebaseDatabase.getInstance();
                                                            reference = firebaseDatabase.getReference("userdata");


                                                            storingdata storingdataobj = new storingdata(name_store, phone_store, mail_store, pwd_store, conpwd_store);

                                                            reference.child(name_store).setValue(storingdataobj);

                                                            Toast.makeText(verifyotp.this,"Process Completed!",Toast.LENGTH_SHORT).show();


                                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(intent);
                                                    }
                                                    else{
                                                        Toast.makeText(verifyotp.this, "Please enter Correct OTP!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                        }else{
                                            Toast.makeText(verifyotp.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                                        }


                                    } else {
                                        Toast.makeText(verifyotp.this, "Please Enter 6 Digit OTP!", Toast.LENGTH_SHORT).show();
                                    }
            }
        });

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        verifyotp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(verifyotp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend = newbackendotp;
                                Toast.makeText(verifyotp.this, "OTP Sent Successful", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });

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