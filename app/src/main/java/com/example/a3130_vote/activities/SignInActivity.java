package com.example.a3130_vote.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.a3130_vote.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.steamcrafted.loadtoast.LoadToast;

import com.example.a3130_vote.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static android.text.TextUtils.isEmpty;

/**
 * The type Sign in activity.
 */
public class SignInActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;
    private EditText mUserNameEditText, mPasswordEditText;
    private LoadToast loadToast;
    /**
     * The constant stautsCode.
     */
    public static int stautsCode;
    /**
     * The constant username.
     */
    public static String username;
    /**
     * The constant useremail.
     */
    public static String useremail;

    //Set tag for log use
    private static final String TAG = "SigninActivity";


    /**
     * The Db.
     */
//initial the firestore database
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("Login");
        mUserNameEditText = findViewById(R.id.username);
        mPasswordEditText = findViewById(R.id.password);
        Button mSigninButton = findViewById(R.id.button);
        loadToast = new LoadToast(this);
        loadToast.setText("Logging in");

        sharedPrefs = new SharedPrefs(getApplicationContext());
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserNameEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "enter your username", Toast.LENGTH_SHORT).show();
                else if (mPasswordEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "enter your password", Toast.LENGTH_SHORT).show();
                else
                    doSignIn();
            }
        });
    }

    private void doSignIn() {
        //loadToast.show();

        db.collection("users")
                .whereEqualTo("userName", mUserNameEditText.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().isEmpty()){
                                Toast.makeText(getApplicationContext(), "Username doesn't exist. Please check and try again.", Toast.LENGTH_SHORT).show();
                            }else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //Log.d(TAG, document.getId() + " => " + document.getData());
                                    if (document.getString("password").equals(mPasswordEditText.getText().toString())) {
                                        if (document.getBoolean("isAdmin") == true) {
                                            Toast.makeText(getApplicationContext(), "Administrator Login Successfully.", Toast.LENGTH_SHORT).show();
                                            adminLoggedin();
                                        }else{
                                            username = document.getString("userName");
                                            useremail = document.getString("email");
                                            loggedin();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password Error. Please check and try again.", Toast.LENGTH_SHORT).show();
                                    }
                                    //}

                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Server Error. Please check and try again.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




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

    /**
     * Loggedin.
     */
    public void loggedin(){
        final Intent intent = new Intent(this, HomeActivity.class);

        DocumentReference docRef = db.collection("status").document("vote");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if(!document.getBoolean("freeze")){
                            Toast.makeText(getApplicationContext(), "Login Successfully.", Toast.LENGTH_SHORT).show();
                            //intent.putExtra("username", username);
                           // intent.putExtra("email", useremail);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "The administrator has froze the vote. Please try again later.", Toast.LENGTH_SHORT).show();
                        }

                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });



    }

    /**
     * Admin loggedin.
     */
    public void adminLoggedin(){
        Intent intent = new Intent(this, ManagerPanel.class);
        //intent.putExtra(HomeActivity.PREFERRED_USERNAME, user.getUserName());
        //intent.putExtra(HomeActivity.EMAIL_ADDRESS, user.getEmail());
        startActivity(intent);
    }
}
