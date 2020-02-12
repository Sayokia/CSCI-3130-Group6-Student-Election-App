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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logInActivity extends AppCompatActivity {
    EditText emailId, passwordId;
    Button logIn;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        emailId=findViewById(R.id.TextView_Email_ID);
        passwordId=findViewById(R.id.Text_View_Password_ID);
        logIn=findViewById(R.id.Button_LogIn);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthState=new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();

                if(mFirebaseUser!=null){
                    Toast.makeText(logInActivity.this,"log in success",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(logInActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(logInActivity.this,"please login", Toast.LENGTH_LONG);
                }
            }
        };

        logIn.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                int i=0;
                String email = emailId.getText().toString();
                String pwd = passwordId.getText().toString();
                if (pwd.isEmpty()) {
                    passwordId.setError("Please enter your password");
                    passwordId.requestFocus();
                    i=1;
                }
                if(email.isEmpty()) {
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();

                }



                if (!(email=="" ) && i==0) {
                    if(isEmail(email)){
                        //Toast.makeText(MainActivity.this, "Firebase cjjjnjnjnnjnnnfully haha", Toast.LENGTH_LONG).show();
                        mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(logInActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Toast.makeText(logInActivity.this,"signup successful",Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(logInActivity.this, HomeActivity.class));

                                }
                                else{
                                    Toast.makeText(logInActivity.this,"log in unsuccessful, please try again",Toast.LENGTH_LONG).show();

                                }

                            }
                        });

                    }
                    else{
                        emailId.setError("Please enter valid account information");
                        emailId.requestFocus();
                    }

                }


            }
            //startActivity(new Intent(logInActivity.this, Home2Activity.class));


        });



    }
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
