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
    Button result,signUp,log;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId=findViewById(R.id.TextView_Email_ID);
        passId=findViewById(R.id.Text_View_Password_ID);
        signUp=findViewById(R.id.Button_LogIn);
        result=findViewById(R.id.result);
        log=findViewById(R.id.log);

        result.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AfterVoteActivity.class));

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int i=0;
                String email = emailId.getText().toString();
                String pwd = passId.getText().toString();
                if (passwordIsEmpty(pwd)) {
                    passId.setError("Please enter your password");
                    passId.requestFocus();
                    i=1;
                }
                if(emailIsEmpty(email)) {
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();
                }
                if (!emailIsEmpty(email) && i==0) {
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
                startActivity(new Intent(MainActivity.this, logInActivity.class));
            }
        });

        Toast.makeText(MainActivity.this, "Firebase connected successfully !!!", Toast.LENGTH_LONG).show();
    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public static boolean passwordIsEmpty(String password){
        if(password.isEmpty())
            return true;
        else
            return false;
    }
    public static boolean emailIsEmpty(String email){
        if(email.isEmpty())
            return true;
        else
            return false;
    }

}
