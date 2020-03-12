package com.example.a3130_vote.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130_vote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ManagerPanel extends AppCompatActivity {

    //Set tag for log use
    private static final String TAG = "ManagerPanel";
    private static String stautsCode;


    //initial the firestore database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);



        DocumentReference docRef = db.collection("status").document("vote");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if(document.getBoolean("freeze") == true){
                            stautsCode = "Freeze";
                        }else{
                            stautsCode = "Normal";
                        }
                        TextView statusText = (TextView)findViewById(R.id.status);
                        statusText.setText(stautsCode);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        Button freezeButton = (Button)findViewById(R.id.freeze);
        Button recoverButton = (Button)findViewById(R.id.recover);
        freezeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference contact = db.collection("status").document("vote");
                contact.update("freeze", true)
                        .addOnSuccessListener(new OnSuccessListener < Void > () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Toast.makeText(MainActivity.this, "Updated Successfully",
                                // Toast.LENGTH_SHORT).show();
                                TextView statusText = (TextView)findViewById(R.id.status);
                                statusText.setText("Freeze");
                                Toast.makeText(ManagerPanel.this, "The vote system has been froze successfully. Users can not vote now. ", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        recoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference contact = db.collection("status").document("vote");
                contact.update("freeze", false)
                        .addOnSuccessListener(new OnSuccessListener < Void > () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Toast.makeText(MainActivity.this, "Updated Successfully",
                                // Toast.LENGTH_SHORT).show();
                                TextView statusText = (TextView)findViewById(R.id.status);
                                statusText.setText("Normal");
                                Toast.makeText(ManagerPanel.this, "The vote system has been recovered successfully. Users can vote now. ", Toast.LENGTH_SHORT).show();
                            }
                        });

                //Toast.makeText(ManagerPanel.this, "The vote system has been recovered successfully. Users can vote now. ", Toast.LENGTH_SHORT).show();
            }
        });






    }


}
