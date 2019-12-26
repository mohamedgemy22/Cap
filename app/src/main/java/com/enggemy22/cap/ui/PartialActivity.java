package com.enggemy22.cap.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enggemy22.cap.R;
import com.enggemy22.cap.models.University;
import com.enggemy22.cap.splash.fianlSpalsh;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.enggemy22.cap.ui.HomeActivity.KEY_ACTRANGE;
import static com.enggemy22.cap.ui.HomeActivity.KEY_GPA;
import static com.enggemy22.cap.ui.HomeActivity.KEY_SATRANGE;

public class PartialActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListner {
    private RecyclerView recyclerView;
    private FirebaseFirestore mFirestore;
    private Task<QuerySnapshot> mQuery;
    private List<University> mlist;
    private String gpa;
    private String Actrange;
    private String satrange;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mlist = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        gpa = intent.getStringExtra(KEY_GPA);
        Actrange = intent.getStringExtra(KEY_ACTRANGE);
        satrange = intent.getStringExtra(KEY_SATRANGE);
        Log.e("mohamed", gpa);
        Log.e("mostafa", Actrange);
        Log.e("momo", satrange);

        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getData() {
        mlist = new ArrayList<>();
        mQuery = mFirestore.collection("universities/")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            University university = snapshot.toObject(University.class);
                            Log.e("mohamedMostafa", university.getGpa());
                            if
                            (
                                    Float.parseFloat(university.getGpa()) <= Float.parseFloat(gpa)
//                                    &&Float.parseFloat(university.getEst_score_range())<=Float.parseFloat(Actrange)
//                                    &&Float.parseFloat(university.getSat_score_range())<=Float.parseFloat(satrange)
                            )
                            {
                                mlist.add(university);
                            }
                        }
                        ItemAdapter adapter = new ItemAdapter(mlist, PartialActivity.this, PartialActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        final ProgressDialog progressDialog = new ProgressDialog(PartialActivity.this);
                        progressDialog.setTitle("Faild to get data .....!");
                        progressDialog.show();
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        final ProgressDialog progressDialog = new ProgressDialog(PartialActivity.this);
        progressDialog.setTitle(mlist.get(position).getName());
        progressDialog.show();
        startActivity(new Intent(PartialActivity.this, fianlSpalsh.class));
        finish();
    }
}
