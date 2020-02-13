package com.example.a3330_vote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


            Toast.makeText(getApplicationContext(),"welcome to our voting system",Toast.LENGTH_LONG).show();

    }
}
