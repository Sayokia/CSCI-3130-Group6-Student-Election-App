package com.example.a3130_vote.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.a3130_vote.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.steamcrafted.loadtoast.LoadToast;

import org.example.a3130_vote.R;


public class SignUpActivity extends AppCompatActivity {

    private EditText mUserNameEditText, mFirstNameEditText, mLastNameEditText, mEmailEditText, mPasswordEditText;
    private LoadToast loadToast;

    // Solution used from Soler(2017) from https://stackoverflow.com/questions/37886301/tag-has-private-access-in-android-support-v4-app-fragmentactivity/37886383
    private static final String TAG = "SignUpActivity";
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loadToast = new LoadToast(this);
        loadToast.setText("Creating Account");

        mUserNameEditText = findViewById(R.id.username);
        mFirstNameEditText = findViewById(R.id.firstname);
        mLastNameEditText = findViewById(R.id.lastname);
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        Button mSignUpButton = findViewById(R.id.signup);


        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserNameEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter username", Toast.LENGTH_SHORT).show();
                else if (mFirstNameEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter your first name", Toast.LENGTH_SHORT).show();
                else if (mLastNameEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter your last name", Toast.LENGTH_SHORT).show();
                else if (mEmailEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter your email", Toast.LENGTH_SHORT).show();
                else if (mPasswordEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();
                else
                    doSignUp();
            }
        });

    }

    private void doSignUp() {
        User user = new User(mUserNameEditText.getText().toString(),mFirstNameEditText.getText().toString(),mLastNameEditText.getText().toString(),mEmailEditText.getText().toString(),mPasswordEditText.getText().toString());
        if (user.isValidUser()){
            // Add a new document with a generated ID
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
        else{
            if (!user.isValidEmail()){
                Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            }
            if (!user.isValidPassword()){
                Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        }
        return super.onOptionsItemSelected(item);
    }
}

