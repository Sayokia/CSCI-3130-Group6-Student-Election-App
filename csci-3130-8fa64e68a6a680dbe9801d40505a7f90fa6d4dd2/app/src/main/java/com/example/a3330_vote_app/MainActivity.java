package com.example.a3330_vote_app;
//package com.google.firebase.auth.FirebaseAuth;
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
import com.google.firebase.auth.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    EditText emailId, passId;
    Button signUp,log;
    // TextView textView,signUp;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId=findViewById(R.id.TextView_Email_ID);
        passId=findViewById(R.id.Text_View_Password_ID);
        signUp=findViewById(R.id.Button_LogIn);
        log=findViewById(R.id.log);

        //signUp=findViewById(R.id.textView);

        signUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int i=0;
                String email = emailId.getText().toString();
                String pwd = passId.getText().toString();
                if (pwd.isEmpty()) {
                    passId.setError("Please enter your password");
                    passId.requestFocus();
                    i=1;
                }
                if(email.isEmpty()) {
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();

                }



                if (!(email=="" ) && i==0) {
                    if(isEmail(email)){

                        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"SignUp successful",Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));

                                }
                                else{
                                    Toast.makeText(MainActivity.this,"signup unsuccessful, please try again",Toast.LENGTH_LONG).show();

                                }

                            }
                        });

                    }
                    else{
                        emailId.setError("Please enter valid email");
                        emailId.requestFocus();
                    }

                }


            }
        });

        log.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent i= new Intent(MainActivity.this,logInActivity.class);
                startActivity(new Intent(MainActivity.this, logInActivity.class));

            }
        });

        Toast.makeText(MainActivity.this, "Firebase connected successfully haha", Toast.LENGTH_LONG).show();
    }
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
