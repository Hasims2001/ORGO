package com.example.orgo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.UUID;
import java.util.concurrent.TimeUnit;



public class user_profile extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ImageView profilepic;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    TextView user_name;
    EditText username_in,number_in,email_in, password_in, confirmpassword_in;
    Button submit;
    String username, phone, mailadd, passwd,link;

    final String randomKey = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        drawerLayout = findViewById(R.id.profile);
        navigationView = findViewById(R.id.sidemenubar);
        toolbar = findViewById(R.id.Tool);
        setSupportActionBar(toolbar);



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userdata");

        SharedPreferences sh = getSharedPreferences("UserInfo", MODE_PRIVATE);
        username = sh.getString("user_name", "");

        side_menu draw = new side_menu(this);
        draw.initNav(drawerLayout, navigationView, toolbar, false);

        profilepic = findViewById(R.id.profile_ic);
        user_name = findViewById(R.id.user_name);
        username_in = findViewById(R.id.username_input);
        number_in = findViewById(R.id.number_input);
        email_in = findViewById(R.id.email_input);
        password_in = findViewById(R.id.password_input);
        confirmpassword_in = findViewById(R.id.confirmpassword_input);
        submit = findViewById(R.id.submit);




        user_name.setText(username);
        username_in.setText(username);

        getdata();


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });


        databaseReference.child(username).child("profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String img = snapshot.getValue(String.class);
                Picasso.get().load(img).into(profilepic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(user_profile.this, "Error, Profile Image", Toast.LENGTH_SHORT).show();
            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmpassword_in.getVisibility() == View.GONE){
                    confirmpassword_in.setVisibility(View.VISIBLE);
                }
                if (!username_in.getText().toString().isEmpty() && !number_in.getText().toString().isEmpty() && !email_in.getText().toString().isEmpty() && !password_in.getText().toString().isEmpty()){
                    if (!confirmpassword_in.getText().toString().isEmpty()){
                        confirmpassword_in.setError(null);
                        if ( confirmpassword_in.getText().toString().equals(password_in.getText().toString()) ){
                            if (username_in.getText().toString().equals(username) && number_in.getText().toString().equals(phone) && email_in.getText().toString().equals(mailadd) && password_in.getText().toString().equals(passwd)){
                                Toast.makeText(user_profile.this, "This Profile is Already Exists!", Toast.LENGTH_SHORT).show();
                            }
                            else{
//                            before update verify otp
                                Toast.makeText(user_profile.this, "Wait for 10 SECONDS", Toast.LENGTH_SHORT).show();
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+91" +number_in.getText().toString(),
                                        60,
                                        TimeUnit.SECONDS,
                                        user_profile.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                Toast.makeText(user_profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                Intent intent = new Intent(getApplicationContext(), verifyotp.class);
                                                intent.putExtra("name", username_in.getText().toString());
                                                intent.putExtra("mobile", number_in.getText().toString());
                                                intent.putExtra("mail", email_in.getText().toString());
                                                intent.putExtra("pwd", password_in.getText().toString());
                                                intent.putExtra("conpwd", confirmpassword_in.getText().toString());
                                                intent.putExtra("backendotp", backendotp);
                                                startActivity(intent);
                                                finish();

                                            }
                                        }
                                );
//

                            }
                        }else{
                            Toast.makeText(user_profile.this, "Your Password Doesn't Match!", Toast.LENGTH_SHORT).show();
                        }



                    }else{
                        confirmpassword_in.setError("Please Re-enter your password!");
                    }
                }else{
                    Toast.makeText(user_profile.this, "Please fill all boxes!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!= null){
            imageUri = data.getData();
            profilepic.setImageURI(imageUri);

            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();



        StorageReference riversRef = storageReference.child("images/" + randomKey);
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
               riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {
                    databaseReference.child(username).child("profile").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Snackbar.make(findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                   }
               });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage : " + (int) progressPercent + "%");
            }
        });

    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                phone = snapshot.child(username).child("number").getValue(String.class);
                number_in.setText(phone);
                mailadd = snapshot.child(username).child("mail").getValue(String.class);
                email_in.setText(mailadd);
                passwd = snapshot.child(username).child("pwd").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(user_profile.this, "Fail to get data. Try Again.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed(){
        if(confirmpassword_in.getVisibility() == View.VISIBLE){
            confirmpassword_in.setVisibility(View.GONE);
        }
        else{
            Intent intent = new Intent(user_profile.this, dashboard.class);
            startActivity(intent);
        }
    };

}