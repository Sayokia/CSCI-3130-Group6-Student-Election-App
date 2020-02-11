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
    EditText editText, editText2;
    Button button,log;
    TextView textView,signUp;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        button=findViewById(R.id.button);
        log=findViewById(R.id.log);

        //signUp=findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {

                                      public void onClick(View v) {
                                          String email = editText.getText().toString();
                                          String pwd = editText2.getText().toString();
                                          if (!(email.isEmpty() && pwd.isEmpty())) {
                                              if(isEmail(email)){
                                                  mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                                      @Override
                                                      public void onComplete(@NonNull Task<AuthResult> task) {
                                                          if(task.isSuccessful()){
                                                              startActivity(new Intent(MainActivity.this, homeActivity.class));

                                                          }
                                                          else{
                                                              Toast.makeText(MainActivity.this,"signup unsuccessful, please try again",Toast.LENGTH_LONG).show();

                                                          }

                                                      }
                                                  });

                                              }
                                              else{
                                                  Toast.makeText(MainActivity.this,"Invalid mailbox",Toast.LENGTH_LONG).show();

                                              }

                                          }
                                          if (email.isEmpty()) {
                                              editText.setError("Please enter your email");
                                              editText.requestFocus();
                                          } else if (pwd.isEmpty()) {
                                              editText.setError("Please enter your password");
                                              editText.requestFocus();
                                          } else if (email.isEmpty() && pwd.isEmpty()) {
                                              Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_LONG);
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
