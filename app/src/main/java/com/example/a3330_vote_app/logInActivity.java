package com.example.a3330_vote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class logInActivity extends AppCompatActivity {
    EditText editText, editText2;
    Button button,log;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)   {

                startActivity(new Intent(logInActivity.this, homeActivity.class));

            }
        });



    }
}
