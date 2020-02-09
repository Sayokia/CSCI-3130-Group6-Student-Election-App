package com.example.a3330_vote_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.makeText;

public class logInActivity extends AppCompatActivity {
    EditText editText, editText2;
    Button button,log;
    TextView textView,signUp;
    FirebaseAuth mFirebaseAuth;

    FirebaseAuth.AuthStateListener mAuthState;







    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        button=findViewById(R.id.button);
        mFirebaseAuth = FirebaseAuth.getInstance();

        signUp=findViewById(R.id.textView);
/*

mAuthState=new FirebaseAuth.AuthStateListener() {


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser!=null){
            Toast.makeText(logInActivity.this,"log in success",Toast.LENGTH_LONG).show();
            Intent i=new Intent(logInActivity.this, homeActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(logInActivity.this,"please login", Toast.LENGTH_LONG);
        }
    }
};

*/

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)   {

                startActivity(new Intent(logInActivity.this, homeActivity.class));

            }
        });



    }
}
