package com.example.orgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class forgot_pwd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);
    }
    public void opensignup(View view){
        Intent intent = new Intent( this, sign_up.class);
        startActivity(intent);
    }
}