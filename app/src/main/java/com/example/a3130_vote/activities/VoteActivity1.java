package com.example.a3130_vote.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.util.Log;
import android.view.View;

import com.example.a3130_vote.ItemClickListener;
import com.example.a3130_vote.adapters.RecyclerViewAdapter1;
import com.example.a3130_vote.adapters.RecyclerViewAdapter2;

import com.example.a3130_vote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity1 extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerViewAdapter1 adapter1;
    private RecyclerViewAdapter2 adapter2;
    private final List<String> unSelected = new ArrayList<>();
    private List<String> selected;
    private Toolbar toolbar;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Solution used from Soler(2017) from https://stackoverflow.com/questions/37886301/tag-has-private-access-in-android-support-v4-app-fragmentactivity/37886383
    private static final String TAG = "VoteActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote1);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db.collection("candidates")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                unSelected.add(document.getString("name"));
                                adapter1.notifyDataSetChanged();
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        selected = new ArrayList<>();



        adapter1 = new RecyclerViewAdapter1(getApplicationContext(), unSelected);
        adapter2 = new RecyclerViewAdapter2(getApplicationContext(), selected);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView2 = findViewById(R.id.recyclerview2);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);
        adapter1.setClickListener(this);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter2);
        adapter2.setClickListener(this);

        adapter1.notifyDataSetChanged();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                moveItem(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                deleteItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView2);

    }


    private void deleteItem(int adapterPosition) {
        String s = selected.get(adapterPosition);
        selected.remove(adapterPosition);
        unSelected.add(s);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }




    private void moveItem(int adapterPosition, int adapterPosition1) {
        String s = selected.get(adapterPosition);
        selected.remove(adapterPosition);
        selected.add(adapterPosition1, s);
        adapter2.notifyItemMoved(adapterPosition, adapterPosition1);
    }

    @Override
    public void onClick(View view, int position) {
        if (view.getContentDescription().equals("add")) {
            String s = unSelected.get(position);
            unSelected.remove(position);
            selected.add(s);
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        } else {
            String s = selected.get(position);
            selected.remove(position);
            unSelected.add(s);
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        }
    }
}
