package com.example.adminportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
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

        submit_btn = findViewById(R.id.otpsubmitbtn);
        txt_name = findViewById(R.id.usernamedata);
        txt_pwd = findViewById(R.id.password);

        submit_btn.setOnClickListener(v -> {
            String username = txt_name.getText().toString();
            String pwd = txt_pwd.getText().toString();

            if (!username.isEmpty()) {
                txt_name.setError(null);
                if (!pwd.isEmpty()) {
                    txt_pwd.setError(null);

                    final String usernamedata = txt_name.getText().toString().trim();
                    final String userpwddata = txt_pwd.getText().toString().trim();

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("admindata");


                    Query check_username = databaseReference.orderByChild("name").equalTo(usernamedata);

                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                txt_name.setError(null);
                                String pwdchecking = snapshot.child(usernamedata).child("pwd").getValue(String.class);
                                if (pwdchecking.equals(userpwddata)){
                                    txt_pwd.setError(null);
                                    Toast.makeText(getApplicationContext(), "Welcome Admin!", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), admindashboard.class);
                                        startActivity(intent);
                                        SharedPreferences sharedPreferences = getSharedPreferences("AdminName",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_name", txt_name.getText().toString().trim());
                                        myEdit.apply();
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
                txt_name.setError("Please Enter Your Username");
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

}