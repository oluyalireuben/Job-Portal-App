package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        CardView postjob = findViewById(R.id.cardPostJob);
        postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobActivity.this, PostJobActivity.class));
            }
        });

        CardView addcompany = findViewById(R.id.cardSavedJobs);
        addcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobActivity.this, SavedJobsActivity.class));
            }
        });

        CardView viewjobs = findViewById(R.id.cardViewPostedJobs);
        viewjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobActivity.this, PostedJobsActivity.class));
            }
        });

        CardView viewcompanies = findViewById(R.id.cardRemove);
        viewcompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JobActivity.this, RemoveJob.class));
            }
        });

    }
}