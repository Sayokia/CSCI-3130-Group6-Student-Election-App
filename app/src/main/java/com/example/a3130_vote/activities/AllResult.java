package com.example.a3130_vote.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130_vote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

//[2]References from Firebase https://firebase.google.com/docs/firestore/quickstart
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * The type All result.
 */
public class AllResult extends AppCompatActivity {

    /**
     * The Db.
     */
    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Initialize Cloud Firestore

    /**
     * The Output.
     */
    ArrayList output = new ArrayList();

    /**
     * The Inputvalue.
     */
    ArrayAdapter<String>inputvalue;//create Arrayadapter show the final result
    //[3] references from Ralph Alex Charlemagne retrieved from https://www.youtube.com/watch?v=MCfsfgpmkEI

    private static final String TAG = "ShowResult";//Print log to terminal rather than remote console
    //[4] References from Arun George retrieved from https://stackoverflow.com/questions/11239882/why-use-tag-in-most-of-the-android-logging-code







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);




        final ListView listView = (ListView)findViewById(R.id.vote_result);

        db.collection("candidates") //[5]below database operation references from google Firebase manual
                // References from Firebase https://firebase.google.com/docs/firestore/quickstart

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        ArrayList output = new ArrayList();//create new object Arraylist to store "download" data

                        if (task.isSuccessful()) {
                            output.add("\nVP Election Result:\n");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getDouble("activity")==1){
                                    output.add("Candidate: " + document.getString("name") + "\nBallot: " +document.getDouble("ballot"));
                                }
                                 }

                            output.add("\n");

                            output.add("\nPresident Election Result:\n");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getDouble("activity")==2){

                                    output.add("Candidate: " + document.getString("name") + "\nBallot: " +document.getDouble("ballot"));
                                }
                            }
                            //store the raw data to the arraylist and format it

                            inputvalue = new ArrayAdapter<String>(AllResult.this, android.R.layout.simple_list_item_1,output);
                            listView.setAdapter(inputvalue); //[3] references from Ralph Alex Charlemagne retrieved from https://www.youtube.com/watch?v=MCfsfgpmkEI
                            back();
                        }
                        else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });






    }

    /**
     * Back.
     */
    public void back(){
        Button backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllResult.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
