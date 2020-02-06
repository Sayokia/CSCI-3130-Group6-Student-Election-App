package com.example.a3330_vote_app;
//package com.google.firebase.auth.FirebaseAuth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.*;


public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;
    Button button,log;
    TextView textView;
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
        button.setOnClickListener(new View.OnClickListener() {

                                      public void onClick(View v) {
                                          String email = editText.getText().toString();
                                          String pwd = editText2.getText().toString();
                                          if (email.isEmpty()) {
                                              editText.setError("Please enter your email");
                                              editText.requestFocus();
                                          } else if (pwd.isEmpty()) {
                                              editText.setError("Please enter your passward");
                                              editText.requestFocus();
                                          } else if (email.isEmpty() && pwd.isEmpty()) {
                                              Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_LONG);
                                          } else if (!(email.isEmpty() && pwd.isEmpty())) {
                                              //mFirebaseAuth.createUserWithEmailAndPassward(email,pwd).addConCompleteListner(MainActivity.this, new View.OnClickListener());

                                              //public void onCompelte(Task<AutoResult> task){
                                              //if(!task.isSuccessful()){
                                              // Toast.makeText(MainActivity.this, "sign up unsuccessful", Toast.LENGTH_SHORT).show();
                                              //}
                                              //else{
                                              startActivity(new Intent(MainActivity.this, homeActivity.class));
                                              //}
                                              // }
                                          }
                                      }
        });
            log.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v)   {

                        startActivity(new Intent(MainActivity.this, logInActivity.class));

                    }
        });





        Toast.makeText(MainActivity.this, "Fire base connected successfully haha", Toast.LENGTH_LONG).show();
    }
}
